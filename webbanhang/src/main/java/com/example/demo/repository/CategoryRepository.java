package com.example.demo.repository;

import com.example.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM categories WHERE id = :c_id ", nativeQuery = true)
    void deleteCategoryById(@Param("c_id") int category_id);

}
