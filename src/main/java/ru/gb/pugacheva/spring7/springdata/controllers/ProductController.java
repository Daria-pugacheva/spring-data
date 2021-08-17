package ru.gb.pugacheva.spring7.springdata.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.pugacheva.spring7.springdata.dtos.ProductDto;
import ru.gb.pugacheva.spring7.springdata.model.Product;
import ru.gb.pugacheva.spring7.springdata.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return new ProductDto(productService.findProductById(id).get());
    }

    @GetMapping("/products")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @PostMapping("/products")
    public void saveNewProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        productService.saveNewProduct(product);
    }

    @GetMapping("/products/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @GetMapping("/products/filtermax/{maxPrice}")
    public List<Product> findAllByPriceLessThanMax(@PathVariable int maxPrice) {
        return productService.findAllByPriceLessThanMax(maxPrice);
    }

    @GetMapping("/products/filtermin/{minPrice}")
    public List<Product> findAllByPriceMoreThanMin(@PathVariable int minPrice) {
        return productService.findAllByPriceMoreThanMin(minPrice);
    }

    @GetMapping("/products/filterbetween/{minPrice}/{maxPrice}")
    public List<Product> findAllByPriceBetmeen(@PathVariable int minPrice, @PathVariable int maxPrice) {
        return productService.findAllByPriceBetween(minPrice, maxPrice);
    }

}
