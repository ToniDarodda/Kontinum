package com.kontinum.repository

import com.kontinum.model.BusinessData
import com.kontinum.model.User
import com.kontinum.service.business.BusinessService
import com.kontinum.service.business.dto.*

class BusinessRepositoryImpl(private val businessService: BusinessService) {
    suspend fun createBusiness(data: BusinessCreateDTO): String? {
        return this.businessService.createBusiness(data)
    }

    suspend fun loginBusiness(data: BusinessGetDTO): String? {
        return this.businessService.loginBusiness(data)
    }

    suspend fun getBusiness(businessId: Int): BusinessData? {
        return this.businessService.getBusiness(businessId)
    }

    suspend fun getBusinessUser(businessId: Int): List<User> {
        return this.businessService.getBusinessUser(businessId)
    }

    suspend fun patchBusiness(businessId: Int, data: BusinessPatchDTO): Int {
        return this.businessService.patchBusiness(businessId, data)
    }

    suspend fun deleteBusiness(businessId: Int): Unit {
        return this.businessService.deleteBusiness(businessId)
    }

}