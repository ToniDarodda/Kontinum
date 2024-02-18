package com.kontinum.service.business

import com.kontinum.model.Business
import com.kontinum.model.BusinessData
import com.kontinum.model.User
import com.kontinum.model.Users
import com.kontinum.service.business.dto.*
import com.kontinum.util.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class BusinessService(private val tokenManager: TokenManager) : BusinessInterface {

    private fun resultRowToUser(row: ResultRow) = User(
        id = row[Users.id],
        firstName = row[Users.firstName],
        lastName = row[Users.lastName],
        email = row[Users.email],
    )
    private fun rowToBusiness(row: ResultRow) = BusinessData(
        id = row[Business.id],
        businessName = row[Business.businessName],
        businessEmail = row[Business.businessEmail],
        businessPhoneNumber = row[Business.businessPhoneNumber],
        businessLegalInformation = row[Business.businessLegalInformation],
        businessLocation = row[Business.businessLocation],
        password = row[Business.password]
    )
    override suspend fun createBusiness(data: BusinessCreateDTO): String? {
         val createdBusiness = transaction {
            val createdBusiness = Business.insert {
                it[businessName] = data.businessName
                it[businessEmail] = data.businessEmail
                it[businessPhoneNumber] = data.businessPhoneNumber
                it[businessLegalInformation] = data.businessLegalInformation
                it[businessLocation] = data.businessLocation
                it[password] = hashPassword(data.password)
            }
            createdBusiness.resultedValues?.singleOrNull()?.let(::rowToBusiness)
        }
        val token = createdBusiness?.let { tokenManager.generateToken(it.id) }
        return token;
    }

    override suspend fun loginBusiness(data: BusinessGetDTO): String? {

        val businessUser = transaction {
            val retrievedUser = Business.selectAll().where { Business.businessEmail eq data.mail }
            retrievedUser.singleOrNull()?.let(::rowToBusiness)
        }

        if (businessUser == null) {
            return null
        }

        if (!checkPassword(businessUser.password, data.password)) {
            return null
        }

        val token = tokenManager.generateToken(businessUser.id)

        return token
    }

    override suspend fun getBusiness(businessId: Int): BusinessData? {
        return transaction {
            val retrievedBusiness = Business.selectAll().where { Business.id eq businessId }

            retrievedBusiness.singleOrNull()?.let(::rowToBusiness)
        }
    }

    override suspend fun getBusinessUser(businessId: Int): List<User> {
        return transaction {

            val retrievedUsers = Users.selectAll().where { Users.businessId eq businessId }
            retrievedUsers.map(::resultRowToUser)

        }
    }

    override suspend fun patchBusiness(businessId: Int, data: BusinessPatchDTO): Int {
        return transaction {
            Business.update({ Business.id eq businessId}) {

                data.businessName?.let { nonNullBusinessName ->
                    it[businessName] = nonNullBusinessName
                }

                data.businessEmail?.let { nonNullBusinessEmail ->
                    it[businessEmail] = nonNullBusinessEmail
                }

                data.businessLocation?.let { nonNullBusinessLocation ->
                    it[businessLocation] = nonNullBusinessLocation
                }

                data.businessPhoneNumber?.let { nonNullBusinessPhoneNumber ->
                    it[businessPhoneNumber] = nonNullBusinessPhoneNumber
                }

                data.businessLegalInformation?.let { nonNUllBusinessLegalInformation ->
                    it[businessLegalInformation] = nonNUllBusinessLegalInformation
                }

                data.password?.let { nonNullPassword ->
                    it[password] = nonNullPassword
                }
            }
        }
    }

    override suspend fun deleteBusiness(businessId: Int): Unit {
        transaction {
            Business.deleteWhere { Business.id eq businessId }
        }
    }

}
