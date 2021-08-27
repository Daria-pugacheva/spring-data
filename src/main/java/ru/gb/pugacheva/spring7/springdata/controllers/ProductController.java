package ru.gb.pugacheva.spring7.springdata.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.pugacheva.spring7.springdata.dtos.ProductDto;
import ru.gb.pugacheva.spring7.springdata.exceptions.MarketError;
import ru.gb.pugacheva.spring7.springdata.exceptions.ResourceNotFoundException;
import ru.gb.pugacheva.spring7.springdata.model.Product;
import ru.gb.pugacheva.spring7.springdata.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final int PAGESIZE = 10;

    @GetMapping("/{id}") //пока товары по id запрашиваются только из таблицы существующих товаров,так что ошибка исключена. Обработка исключения - задел на будущее
    public ProductDto findProductById(@PathVariable Long id) {
        Product product = productService.findProductById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product id= " + id + " not found"));
        return new ProductDto(product);
    }

    @GetMapping()
    public Page<ProductDto> findAll(@RequestParam(defaultValue = "1", name = "page") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, PAGESIZE).map(ProductDto::new);
    }

//    @GetMapping("/order/{productId}")
//    public List <ProductDto> sendOrderInfo (@PathVariable Long productId){
//        return productService.addProductInOrder(productId);
//    }

    @PostMapping()
    public void saveNewProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        productService.saveNewProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @PutMapping()
    public void updateProduct (@RequestBody ProductDto productDto){
        productService.updateProductFromDto(productDto);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchResourceNotFoundException (ResourceNotFoundException e){
        return new ResponseEntity<>(new MarketError(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/products/filtermax/{maxPrice}") // неследство прошлой домашки без апгрейда
    public List<Product> findAllByPriceLessThanMax(@PathVariable int maxPrice) {
        return productService.findAllByPriceLessThanMax(maxPrice);
    }

    @GetMapping("/products/filtermin/{minPrice}") // неследство прошлой домашки без апгрейда
    public List<Product> findAllByPriceMoreThanMin(@PathVariable int minPrice) {
        return productService.findAllByPriceMoreThanMin(minPrice);
    }

    @GetMapping("/products/filterbetween/{minPrice}/{maxPrice}") // неследство прошлой домашки без апгрейда
    public List<Product> findAllByPriceBetmeen(@PathVariable int minPrice, @PathVariable int maxPrice) {
        return productService.findAllByPriceBetween(minPrice, maxPrice);
    }

}


//    //старый вариант, когда возвращали весь лист продуктов, а не по листам.
//    @GetMapping("/products")
//    public List<ProductDto> findAll(){
//        return productService.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
//    }
//    @GetMapping("/products/delete/{id}") //cтарый вариант через запрос из строки браузера
//    public void deleteProductById(@PathVariable Long id) {
//        productService.deleteProductById(id);
//    }

//    //вариант метода удаления без применения принципов REST
//    @GetMapping("/products/delete")
//    public void deleteProductById(@RequestParam Long id) {
//        productService.deleteProductById(id);
//    }

//    //мой изначальный так себе вариант обновления инфо о продукте
//    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
//    public void updateProduct(@RequestBody ProductDto productDto) {
//        if(!productService.findProductById(productDto.getId()).isPresent()){
//            throw new ResourceNotFoundException("Product id= " + productDto.getId() + " not found");
//        } // ВОПРОС: можно ведь так прокинуть исключение?
//        Product productForUpdate = new Product();
//        productForUpdate.setId(productDto.getId());
//        productForUpdate.setTitle(productDto.getTitle());
//        productForUpdate.setPrice(productDto.getPrice());
//        productService.updateProduct(productForUpdate);
//    }
//    //вариант обновления через реквестПарамы, но тоже не работает
//    @PutMapping
//    public void updateProduct(@RequestParam Long id, @RequestParam String title, @RequestParam int price) {
//        productService.updateProduct(id,title,price);
//    }

