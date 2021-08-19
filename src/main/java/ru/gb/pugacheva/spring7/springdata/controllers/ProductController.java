package ru.gb.pugacheva.spring7.springdata.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.pugacheva.spring7.springdata.dtos.ProductDto;
import ru.gb.pugacheva.spring7.springdata.model.Product;
import ru.gb.pugacheva.spring7.springdata.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final int PAGESIZE = 10;

    @GetMapping("/products/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return new ProductDto(productService.findProductById(id).get());
    }

    @GetMapping("/products")
    public Page<ProductDto> findAll(@RequestParam(defaultValue = "1", name = "page") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, PAGESIZE).map(ProductDto::new);
    }

//    //старый вариант, когда возвращали весь лист продуктов, а не по листам.
//    @GetMapping("/products")
//    public List<ProductDto> findAll(){
//        return productService.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
//    }

    @PostMapping("/products")
    public void saveNewProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        productService.saveNewProduct(product);
    }

//    @GetMapping("/products/delete/{id}") //cтарый вариант через запрос из строки браузера
//    public void deleteProductById(@PathVariable Long id) {
//        productService.deleteProductById(id);
//    }

    @GetMapping("/products/delete")
    public void deleteProductById(@RequestParam Long id) {
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
