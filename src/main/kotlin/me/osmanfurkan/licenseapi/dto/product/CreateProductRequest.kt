package me.osmanfurkan.licenseapi.dto.product

import javax.validation.constraints.NotBlank

data class CreateProductRequest(
    @NotBlank val productName: String,
)