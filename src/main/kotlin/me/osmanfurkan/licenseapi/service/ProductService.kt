package me.osmanfurkan.licenseapi.service

import javax.persistence.EntityNotFoundException
import me.osmanfurkan.licenseapi.dto.product.CreateProductRequest
import me.osmanfurkan.licenseapi.dto.product.ProductDto
import me.osmanfurkan.licenseapi.dto.product.UpdateProductRequest
import me.osmanfurkan.licenseapi.dto.product.toDto
import me.osmanfurkan.licenseapi.model.Product
import me.osmanfurkan.licenseapi.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
) {
    fun getProducts(): List<ProductDto> = productRepository.findAll().map { it.toDto() }

    internal fun findProductById(id: Int) =
        productRepository.findById(id).orElseThrow { EntityNotFoundException("Product not found") }

    private fun findProductByName(productName: String) =
        productRepository.findByProductName(productName).orElseThrow {
            EntityNotFoundException("Product not found")
        }

    fun getProductById(id: Int): ProductDto = findProductById(id).toDto()

    fun getProductByName(productName: String) = findProductByName(productName).toDto()

    fun createProduct(createProductRequest: CreateProductRequest): ProductDto {
        val product = Product(productName = createProductRequest.productName)
        return productRepository.save(product).toDto()
    }

    fun updateProduct(id: Int, updateProductRequest: UpdateProductRequest): ProductDto {
        val product = findProductById(id)
        product.productName = updateProductRequest.productName
        return productRepository.save(product).toDto()
    }

    fun deleteProduct(id: Int) {
        productRepository.deleteById(id)
    }
}
