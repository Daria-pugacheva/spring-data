package ru.gb.pugacheva.spring7.springdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gb.pugacheva.spring7.springdata.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceLessThanEqual(int maxPrice);

    List<Product> findAllByPriceGreaterThanEqual(int minPrice);

    List<Product> findAllByPriceBetween(int minPrice, int maxPrice);

    @Modifying
    @Query("update Product p set p.title = :title, p.price = :price where p.id = :id")
    void updateProduct(@Param("id") Long id, @Param("title") String title, @Param("price") int price);
}
