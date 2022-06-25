package com.example.demo.repository;

import com.example.demo.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetail, Integer> {

    @Query(value = "SELECT * FROM orderdetail  WHERE productid = :id", nativeQuery = true)
    List<OrderDetail> searchProductid(@Param("id") int id);
}
