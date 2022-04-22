package me.osmanfurkan.licenseapi.repository

import me.osmanfurkan.licenseapi.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ProductRepository : JpaRepository<Product, Int> {
    fun findByProductName(productName: String): Optional<Product>
}