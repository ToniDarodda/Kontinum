package com.kontinum.service.purchaseDetail.dto

import kotlinx.serialization.Serializable

@Serializable
data class PurchaseDetailsPatchDTO(val purchaseId: Int?, val cocktailId: Int?, val purchasePrice: Int?)