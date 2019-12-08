package com.myshop.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myshop.order.entity.OrderEntity;
import com.myshop.order.entity.OrderItemEntity;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long>{

	List<OrderItemEntity> findByOrder(OrderEntity order);
}
