package com.myshop.order.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myshop.order.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

	@Query("select o from OrderEntity o where o.orderTime >= :from and o.orderTime <= :to")
	List<OrderEntity> findBetweenOrderTimeRange(@Param("from") Instant from, @Param("to") Instant to);
}
