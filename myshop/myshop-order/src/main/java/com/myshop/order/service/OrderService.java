package com.myshop.order.service;

import java.time.Instant;
import java.util.List;

import com.myshop.order.domain.OrderRequest;
import com.myshop.order.domain.OrderVO;

public interface OrderService {

	OrderVO placeOrder(OrderRequest orderRequest);
	
	List<OrderVO> findOrdersBetweenTimeRange(Instant from, Instant to);
	
}
