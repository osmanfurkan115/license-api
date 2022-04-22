package me.osmanfurkan.licenseapi.service

import javax.persistence.EntityNotFoundException
import me.osmanfurkan.licenseapi.dto.client.ClientDto
import me.osmanfurkan.licenseapi.dto.client.CreateClientRequest
import me.osmanfurkan.licenseapi.dto.client.UpdateClientRequest
import me.osmanfurkan.licenseapi.dto.client.toDto
import me.osmanfurkan.licenseapi.model.Client
import me.osmanfurkan.licenseapi.repository.ClientRepository
import org.springframework.stereotype.Service

@Service
class ClientService(
    private val clientRepository: ClientRepository,
) {
    fun getClients() = clientRepository.findAll().map { it.toDto() }

    fun getClientByDiscordAddress(discordAddress: String) =
        clientRepository.findByDiscordAddress(discordAddress).orElseThrow {
            EntityNotFoundException("Client not found")
        }.toDto()

    internal fun findClientById(id: Long) =
        clientRepository.findById(id).orElseThrow { EntityNotFoundException("Client not found") }

    fun getClientById(id: Long) = findClientById(id).toDto()

    fun createClient(createClientRequest: CreateClientRequest): ClientDto {
        val client =
            Client(
                discordAddress = createClientRequest.discordAddress,
            )
        return clientRepository.save(client).toDto()
    }

    fun updateClient(id: Long, updateClientRequest: UpdateClientRequest): ClientDto {
        val client = findClientById(id)
        client.discordAddress = updateClientRequest.discordAddress
        return clientRepository.save(client).toDto()
    }

    fun deleteClient(id: Long) = clientRepository.deleteById(id)
}
