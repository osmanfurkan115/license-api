package me.osmanfurkan.licenseapi.service

import me.osmanfurkan.licenseapi.dto.client.UpdateClientRequest
import me.osmanfurkan.licenseapi.model.Client
import me.osmanfurkan.licenseapi.repository.ClientRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.*
import javax.persistence.EntityNotFoundException

internal class ClientServiceTest {
    @Mock
    private lateinit var clientRepository: ClientRepository
    private lateinit var clientService: ClientService

    private val client = Client(id = 1L, discordAddress = "test#2455")

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        clientService = ClientService(clientRepository)
    }

    @Test
    internal fun `should get clients`() {
        `when`(clientRepository.findAll()).thenReturn(listOf(client))

        val clients = clientService.getClients()

        assertEquals(1, clients.size)
        assertEquals(client.discordAddress, clients[0].discordAddress)
        assertEquals(client.createdDate, clients[0].createdDate)
    }

    @Test
    internal fun `should get client by discord address`() {
        `when`(clientRepository.findByDiscordAddress(client.discordAddress)).thenReturn(Optional.of(client))

        val foundClient = clientService.getClientByDiscordAddress(client.discordAddress)

        assertEquals(client.id, foundClient.id)
        assertEquals(client.discordAddress, foundClient.discordAddress)
    }

    @Test
    internal fun `given an invalid discord address should throw entity not found exception`() {
        `when`(clientRepository.findByDiscordAddress(client.discordAddress)).thenReturn(Optional.empty())

        assertThrows<EntityNotFoundException> {
            clientService.getClientByDiscordAddress(client.discordAddress)
        }
    }

    @Test
    internal fun `should get client by id`() {
        `when`(clientRepository.findById(client.id!!)).thenReturn(Optional.of(client))

        val foundClient = clientService.getClientById(client.id!!)

        assertEquals(client.id, foundClient.id)
        assertEquals(client.discordAddress, foundClient.discordAddress)
    }

    @Test
    internal fun `given an invalid id should throw entity not found exception`() {
        `when`(clientRepository.findById(client.id!!)).thenReturn(Optional.empty())

        assertThrows<EntityNotFoundException> {
            clientService.getClientById(1L)
        }
    }

    @Test
    internal fun `should update client`() {
        `when`(clientRepository.findById(client.id!!)).thenReturn(Optional.of(client))
        `when`(clientRepository.save(client)).thenReturn(client)

        val updatedClient = clientService.updateClient(client.id!!, UpdateClientRequest(discordAddress = client.discordAddress))

        assertEquals(client.id, updatedClient.id)
        assertEquals(client.discordAddress, updatedClient.discordAddress)
    }
}