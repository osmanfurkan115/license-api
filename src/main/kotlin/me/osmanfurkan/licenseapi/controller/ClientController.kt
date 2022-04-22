package me.osmanfurkan.licenseapi.controller

import javax.validation.Valid
import me.osmanfurkan.licenseapi.dto.client.ClientDto
import me.osmanfurkan.licenseapi.dto.client.CreateClientRequest
import me.osmanfurkan.licenseapi.dto.client.UpdateClientRequest
import me.osmanfurkan.licenseapi.service.ClientService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/client")
class ClientController(private val clientService: ClientService) {

    @GetMapping fun getAll(): ResponseEntity<List<ClientDto>> = ResponseEntity.ok(clientService.getClients())

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<ClientDto> = ResponseEntity.ok(clientService.getClientById(id))

    @GetMapping("/discord/{address}")
    fun getByDiscordAddress(@PathVariable address: String): ResponseEntity<ClientDto> =
        ResponseEntity.ok(clientService.getClientByDiscordAddress(address))

    @PostMapping
    fun create(@Valid @RequestBody createClientRequest: CreateClientRequest) =
        ResponseEntity.ok(clientService.createClient(createClientRequest))

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody updateClientRequest: UpdateClientRequest) =
        ResponseEntity.ok(clientService.updateClient(id, updateClientRequest))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = ResponseEntity.ok(clientService.deleteClient(id))
}
