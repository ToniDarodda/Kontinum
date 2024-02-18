package com.kontinum.service.user


import com.kontinum.model.User
import com.kontinum.model.Users
import com.kontinum.service.user.dto.UserCreateDTO
import com.kontinum.service.user.dto.UserPatchDTO
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class UserService : UserInterface {

    private fun resultRowToUser(row: ResultRow) = User(
        id = row[Users.id],
        firstName = row[Users.firstName],
        lastName = row[Users.lastName],
        email = row[Users.email],
    )

    override suspend fun createUser(data: UserCreateDTO, businessId: Int): User? {

        val throwIfExist = this.getUserByEmail(data.email)

        if (throwIfExist != null) return null

        return transaction {

            val insertedUser = Users.insert {
                it[firstName] = data.firstName
                it[lastName] = data.lastName
                it[email] = data.email
                it[this.businessId] = businessId
            }

            insertedUser.resultedValues?.singleOrNull()?.let(::resultRowToUser)
        }
    }

    override suspend fun getUser(userId: Int): User? {
        return transaction {

            val retrievedUser = Users.selectAll().where(Users.id eq userId)

            retrievedUser.map(::resultRowToUser).singleOrNull()
        }
    }



    override suspend fun getUsers(businessId: Int): List<User> {
        return transaction {

            val allUser = Users.selectAll().where { Users.businessId eq businessId }

            allUser.map(::resultRowToUser)
        }
    }

    override suspend fun getUserByEmail(email: String): User? {
        return transaction {
            val retrievedUser = Users.selectAll().where { Users.email eq email }
            retrievedUser.map(::resultRowToUser).singleOrNull()
        }
    }

    override suspend fun patchUser(userId: Int, data: UserPatchDTO): Int {
        return transaction {

            Users.update({ Users.id eq userId }) {

                data.firstName?.let { nonNullFirstName ->
                    it[firstName] = nonNullFirstName
                }

                data.lastName?.let { nonNullLastName ->
                    it[lastName] = nonNullLastName
                }

                data.email?.let { nonNullEmail ->
                    it[email] = nonNullEmail
                }
            }

        }
    }

    override suspend fun deleteUser(userId: Int): Unit {
        transaction {

            Users.deleteWhere { Users.id eq userId }

        }
    }

}