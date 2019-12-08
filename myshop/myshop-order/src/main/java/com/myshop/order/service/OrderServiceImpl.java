package com.myshop.order.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myshop.order.domain.OrderItemVO;
import com.myshop.order.domain.OrderRequest;
import com.myshop.order.domain.OrderVO;
import com.myshop.order.domain.Product;
import com.myshop.order.entity.OrderEntity;
import com.myshop.order.entity.OrderItemEntity;
import com.myshop.order.repository.OrderItemRepository;
import com.myshop.order.repository.OrderRepository;
import com.myshop.order.service.product.proxy.ProductServiceProxy;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProductServiceProxy productService;

	@Override
	public OrderVO placeOrder(OrderRequest orderRequest) {
		OrderEntity order = new OrderEntity();
		order.setEmail(orderRequest.getEmail());
		order.setOrderTime(Instant.now());
		OrderEntity savedOrder = orderRepository.save(order);
		
		orderRequest.getProducts().stream().map(productService::get)
				.map(p -> this.mapToOrderItem(p, savedOrder)).forEach(orderItemRepository::save);
		savedOrder.setItems(orderItemRepository.findByOrder(savedOrder));
		
		return mapToOrderVO(savedOrder);
	}

	@Override
	public List<OrderVO> findOrdersBetweenTimeRange(Instant from, Instant to) {
		return orderRepository.findBetweenOrderTimeRange(from, to).
				stream().map(this::mapToOrderVO).collect(Collectors.toList());
	}

	private OrderItemEntity mapToOrderItem(Product product, OrderEntity order) {
		OrderItemEntity orderItem = new OrderItemEntity();
		orderItem.setPrice(product.getPrice());
		orderItem.setProductId(product.getId());
		orderItem.setOrder(order);
		return orderItem;
	}
	
	private OrderItemVO mapToOrderItemVO(OrderItemEntity entity) {
		OrderItemVO orderItem = new OrderItemVO();
		orderItem.setPrice(entity.getPrice());
		orderItem.setProductId(entity.getProductId());
		orderItem.setId(entity.getId());
		return orderItem;
	}
	
	private OrderVO mapToOrderVO(OrderEntity orderEntity) {
		OrderVO order = new OrderVO();
		order.setId(orderEntity.getId());
		order.setEmail(orderEntity.getEmail());
		order.setItems(orderEntity.getItems().stream().map(this::mapToOrderItemVO).collect(Collectors.toList()));
		order.setOrderTime(orderEntity.getOrderTime());
		BigDecimal orderPrice = orderEntity.getItems().stream().map(OrderItemEntity::getPrice).reduce(BigDecimal.ZERO,
				BigDecimal::add);
		order.setOrderPrice(orderPrice);
		return order;
	}
	
}
