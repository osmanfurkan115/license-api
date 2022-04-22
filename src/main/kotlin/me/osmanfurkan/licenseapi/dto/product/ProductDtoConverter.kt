package me.osmanfurkan.licenseapi.dto.product

import me.osmanfurkan.licenseapi.model.Product

fun Product.toDto(): ProductDto {
    return ProductDto(id = id!!, productName = productName, licenseIdList = licenseList.map { it.licenseId })
}
