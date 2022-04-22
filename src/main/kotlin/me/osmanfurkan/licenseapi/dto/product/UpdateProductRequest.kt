package me.osmanfurkan.licenseapi.dto.product

import javax.validation.constraints.NotBlank

data class UpdateProductRequest(
    @NotBlank val productName: String
)
