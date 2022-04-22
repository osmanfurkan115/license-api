package me.osmanfurkan.licenseapi.repository

import me.osmanfurkan.licenseapi.model.License
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface LicenseRepository : JpaRepository<License, Long> {
    @Query("select (count(l) > 0) from License l where l.licenseId = ?1 and l.product.productName = ?2")
    fun existsByLicenseIdAndProductName(licenseId: UUID, productName: String): Boolean
    fun findByLicenseId(licenseId: UUID): Optional<License>
    fun deleteByLicenseId(licenseId: UUID)
}