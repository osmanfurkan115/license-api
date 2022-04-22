package me.osmanfurkan.licenseapi.controller

import java.util.*
import javax.validation.Valid
import me.osmanfurkan.licenseapi.dto.license.CreateLicenseRequest
import me.osmanfurkan.licenseapi.dto.license.LicenseDto
import me.osmanfurkan.licenseapi.service.LicenseService
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/license")
class LicenseController(private val licenseService: LicenseService) {

    @GetMapping
    fun getAll(): ResponseEntity<List<LicenseDto>> = ResponseEntity.ok(licenseService.getLicenses())

    @GetMapping("/{licenseId}")
    fun getById(@PathVariable licenseId: UUID): ResponseEntity<LicenseDto> =
        ResponseEntity.ok(licenseService.getLicenseByLicenseId(licenseId))

    @PostMapping("/{licenseId}")
    fun existsByLicenseId(@PathVariable licenseId: UUID, @RequestParam productName: String): ResponseEntity<Boolean> =
        ResponseEntity.ok(licenseService.existsByLicenseId(licenseId, productName))

    @PostMapping
    fun create(@Valid @RequestBody createLicenseRequest: CreateLicenseRequest) =
        ResponseEntity.ok(licenseService.createLicense(createLicenseRequest))

    @DeleteMapping("/{licenseId}")
    @Transactional
    fun delete(@PathVariable licenseId: UUID) = licenseService.deleteLicense(licenseId)
}
