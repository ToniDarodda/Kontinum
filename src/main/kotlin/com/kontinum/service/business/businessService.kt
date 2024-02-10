package com.kontinum.service.business

import com.kontinum.model.Business
import com.kontinum.model.BusinessData
import com.kontinum.service.business.dto.BusinessCreateDTO
import com.kontinum.service.business.dto.BusinessPatchDTO
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class BusinessService : BusinessInterface {

    private fun rowToBusiness(row: ResultRow) = BusinessData(
        id = row[Business.id],
        businessName = row[Business.businessName],
        businessEmail = row[Business.businessEmail],
        businessPhoneNumber = row[Business.businessPhoneNumber],
        businessLegalInformation = row[Business.businessLegalInformation],
        businessLocation = row[Business.businessLocation]
    )
    override suspend fun createBusiness(data: BusinessCreateDTO): BusinessData? {
        return transaction {
            val createdBusiness = Business.insert {
                it[businessName] = data.businessName
                it[businessEmail] = data.businessEmail
                it[businessPhoneNumber] = data.businessPhoneNumber
                it[businessLegalInformation] = data.businessLegalInformation
                it[businessLocation] = data.businessLocation
            }
            createdBusiness.resultedValues?.singleOrNull()?.let(::rowToBusiness)
        }
    }

    override suspend fun getBusiness(businessId: Int): BusinessData? {
        return transaction {
            val retrievedBusiness = Business.selectAll().where { Business.id eq businessId }

            retrievedBusiness.singleOrNull()?.let(::rowToBusiness)
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
            }
        }
    }

    override suspend fun deleteBusiness(businessId: Int): Unit {
        transaction {
            Business.deleteWhere { Business.id eq businessId }
        }
    }

}
