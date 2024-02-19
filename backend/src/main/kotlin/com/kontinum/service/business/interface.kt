package com.kontinum.service.business

import com.kontinum.model.BusinessData
import com.kontinum.model.User
import com.kontinum.service.business.dto.*

interface BusinessInterface {
    suspend fun createBusiness(data: BusinessCreateDTO): String?

    suspend fun loginBusiness(data: BusinessGetDTO): String?

    suspend fun getBusiness(businessId: Int): BusinessData?

    suspend fun getBusinessUser(businessId: Int): List<User>;

    suspend fun patchBusiness(businessId: Int, data: BusinessPatchDTO): Int

    suspend fun deleteBusiness(businessId: Int): Unit
}