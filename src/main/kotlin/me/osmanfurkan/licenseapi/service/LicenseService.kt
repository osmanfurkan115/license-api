package me.osmanfurkan.licenseapi.service

import java.util.*
import javax.persistence.EntityNotFoundException
import me.osmanfurkan.licenseapi.dto.license.CreateLicenseRequest
import me.osmanfurkan.licenseapi.dto.license.LicenseDto
import me.osmanfurkan.licenseapi.dto.license.toDto
import me.osmanfurkan.licenseapi.model.License
import me.osmanfurkan.licenseapi.repository.LicenseRepository
import org.springframework.stereotype.Service

@Service
class LicenseService(
    private val licenseRepository: LicenseRepository,
    private val clientService: ClientService,
    private val productService: ProductService
) {

    fun getLicenses() = licenseRepository.findAll().map { it.toDto() }

    fun getLicenseByLicenseId(licenseId: UUID) =
        licenseRepository
            .findByLicenseId(licenseId)
            .orElseThrow { EntityNotFoundException("License not found") }
            .toDto()

    fun existsByLicenseId(licenseId: UUID, productName: String): Boolean = licenseRepository.existsByLicenseIdAndProductName(licenseId, productName)

    fun createLicense(createLicenseRequest: CreateLicenseRequest): LicenseDto {
        val license =
            License(
                product = productService.findProductById(createLicenseRequest.productId),
                client = clientService.findClientById(createLicenseRequest.clientId),
            )
        return licenseRepository.save(license).toDto()
    }

    fun deleteLicense(licenseId: UUID) {
        licenseRepository.deleteByLicenseId(licenseId)
    }
}
