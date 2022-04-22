package me.osmanfurkan.licenseapi.dto.client

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class UpdateClientRequest(
    @NotEmpty
    @Size(min = 7, max = 37)
    val discordAddress: String,
)
