package me.osmanfurkan.licenseapi.dto.client

import java.time.LocalDateTime
import java.util.UUID

data class ClientDto(
    val id: Long,
    val discordAddress: String,
    val licenseIdList: List<UUID>,
    val createdDate: LocalDateTime,
)
