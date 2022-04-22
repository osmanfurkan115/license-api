package me.osmanfurkan.licenseapi.dto.license

data class CreateLicenseRequest(
    val clientId: Long,
    val productId: Int,
)
