package me.osmanfurkan.licenseapi.dto.product

import java.util.UUID

data class ProductDto(
    val id: Int,
    val productName: String,
    val licenseIdList: List<UUID>
)
