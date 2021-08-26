package ru.gb.pugacheva.spring7.springdata.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.pugacheva.spring7.springdata.model.Product;
import ru.gb.pugacheva.spring7.springdata.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Page<Product> findAll(int pageNumber, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    public void saveNewProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void updateProduct (Product product){
        productRepository.updateProduct(product.getId(), product.getTitle(),product.getPrice());
    }

//    //вариант , когда через реквестпарамы работаем, но тоже не работает
//    public void updateProduct (Long id, String title,int price){
//        productRepository.updateProduct(id, title,price);
//    }

    public List<Product> findAllByPriceLessThanMax(int maxPrice) {
        return productRepository.findAllByPriceLessThanEqual(maxPrice);
    }

    public List<Product> findAllByPriceMoreThanMin(int minPrice) {
        return productRepository.findAllByPriceGreaterThanEqual(minPrice);
    }

    public List<Product> findAllByPriceBetween(int minPrice, int maxPrice) {
        return productRepository.findAllByPriceBetween(minPrice, maxPrice);
    }

}


//    //старый вариант, когда возвращали весь лист продуктов
//    public List<Product> findAll() {
//        return productRepository.findAll();
//    }
