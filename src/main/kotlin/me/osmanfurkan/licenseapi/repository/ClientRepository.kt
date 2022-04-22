package me.osmanfurkan.licenseapi.repository

import me.osmanfurkan.licenseapi.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ClientRepository : JpaRepository<Client, Long> {
    fun findByDiscordAddress(discordAddress: String): Optional<Client>
}