package com.kontinum.service.business

import com.kontinum.model.BusinessData
import com.kontinum.service.business.dto.BusinessCreateDTO
import com.kontinum.service.business.dto.BusinessGetDTO
import com.kontinum.service.business.dto.BusinessPatchDTO

interface BusinessInterface {
    suspend fun createBusiness(data: BusinessCreateDTO): BusinessData?

    suspend fun loginBusiness(data: BusinessGetDTO): String?

    suspend fun getBusiness(businessId: Int): BusinessData?

    suspend fun patchBusiness(businessId: Int, data: BusinessPatchDTO): Int

    suspend fun deleteBusiness(businessId: Int): Unit
}