package me.osmanfurkan.licenseapi.dto.client

import me.osmanfurkan.licenseapi.model.Client

fun Client.toDto(): ClientDto {
    return ClientDto(
        id = id!!,
        discordAddress = discordAddress,
        licenseIdList = licenseList.map { it.licenseId },
        createdDate = createdDate
    )
}
