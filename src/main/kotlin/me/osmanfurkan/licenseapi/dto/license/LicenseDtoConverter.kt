package me.osmanfurkan.licenseapi.dto.license

import me.osmanfurkan.licenseapi.model.License

fun License.toDto(): LicenseDto =
    LicenseDto(
        id = id!!,
        licenseId = licenseId,
        productName = product.productName,
        clientDiscordAddress = client.discordAddress,
        createdDate = createdDate,
    )