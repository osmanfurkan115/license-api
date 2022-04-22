package me.osmanfurkan.licenseapi.service

import me.osmanfurkan.licenseapi.repository.ClientRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

internal class LicenseServiceTest {
    @Mock
    private lateinit var clientRepository: ClientRepository
    private lateinit var clientService: ClientService


    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        clientService = ClientService(clientRepository)
    }

    @Test
    fun getLicenses() {
    }

    @Test
    fun getLicenseByLicenseId() {
    }

    @Test
    fun existsByLicenseId() {
    }

    @Test
    fun createLicense() {
    }

    @Test
    fun deleteLicense() {
    }
}