package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM products WHERE id = :p_id ", nativeQuery = true)
    void deleteProductById(@Param("p_id") int product_id);

    @Query(value = "SELECT * FROM products as p WHERE p.name LIKE %:name%", nativeQuery = true)
    List<Product> searchProduct(@Param("name") String name);

    @Query(value = "SELECT * FROM products LIMIT 4", nativeQuery = true)
    List<Product> getProduct();

    @Query(value = "SELECT * FROM products WHERE category_id =:c_id", nativeQuery = true)
    List<Product> getProductByCateId(@Param("c_id") int id);

    @Query(value = "SELECT * FROM products WHERE id =:id", nativeQuery = true)
    Product getDetailProduct(@Param("id") int id);

//    @Query(value = "SELECT * FROM products WHERE price<=20000", nativeQuery = true)
//    List<Product> getListProductLess20000();
//
//    @Query(value = "SELECT * FROM products WHERE price>=20000 AND price<=50000", nativeQuery = true)
//    List<Product> getListProductBigger20000AndLess50000();
//
//    @Query(value = "SELECT * FROM products WHERE price >= 50000 AND price <= 100000", nativeQuery = true)
//    List<Product> getListProductBigger50000AndLess100000();
//
//    @Query(value = "SELECT * FROM products WHERE price>=100000", nativeQuery = true)
//    List<Product> getListProductBigger100000();

    @Query(value = "SELECT * FROM products ORDER BY price", nativeQuery = true)
    List<Product> getProductByPriceOrderBy( );



    @Query(value = "SELECT * FROM products ORDER BY price DESC", nativeQuery = true)
    List<Product> getProductByPriceOrderByDesc();

    @Query(value = "SELECT * FROM products WHERE category_id =?1 ORDER BY price DESC", nativeQuery = true)
    List<Product> getProductByPriceOrderByDescCategory( int id);

    @Query(value = "SELECT * FROM products WHERE category_id =:c_id ORDER BY price", nativeQuery = true)
    List<Product> getProductByPriceOrderByCategory(@Param("c_id") int id);

}
