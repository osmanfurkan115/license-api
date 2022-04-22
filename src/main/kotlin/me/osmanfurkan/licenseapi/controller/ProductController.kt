package me.osmanfurkan.licenseapi.controller

import me.osmanfurkan.licenseapi.dto.product.CreateProductRequest
import me.osmanfurkan.licenseapi.dto.product.ProductDto
import me.osmanfurkan.licenseapi.dto.product.UpdateProductRequest
import me.osmanfurkan.licenseapi.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/product")
class ProductController(private val productService: ProductService) {

    @GetMapping fun getAll(): ResponseEntity<List<ProductDto>> = ResponseEntity.ok(productService.getProducts())

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): ResponseEntity<ProductDto> = ResponseEntity.ok(productService.getProductById(id))

    @GetMapping("/filter/{name}")
    fun getByProductName(@PathVariable name: String): ResponseEntity<ProductDto> =
        ResponseEntity.ok(productService.getProductByName(name))

    @PostMapping
    fun create(@Valid @RequestBody createProductRequest: CreateProductRequest): ResponseEntity<ProductDto> =
        ResponseEntity.ok(productService.createProduct(createProductRequest))

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @Valid @RequestBody updateProductRequest: UpdateProductRequest): ResponseEntity<ProductDto> =
        ResponseEntity.ok(productService.updateProduct(id, updateProductRequest))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int) = ResponseEntity.ok(productService.deleteProduct(id))
}
