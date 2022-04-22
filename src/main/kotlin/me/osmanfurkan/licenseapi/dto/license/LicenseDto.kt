package me.osmanfurkan.licenseapi.dto.license

import java.time.LocalDateTime
import java.util.UUID

data class LicenseDto(
    val id: Long,
    val licenseId: UUID,
    val productName: String,
    val clientDiscordAddress: String,
    val createdDate: LocalDateTime
)
