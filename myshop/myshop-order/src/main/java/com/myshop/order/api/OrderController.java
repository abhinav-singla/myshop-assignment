package com.myshop.order.api;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myshop.order.domain.OrderRequest;
import com.myshop.order.domain.OrderVO;
import com.myshop.order.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/orders")
	public OrderVO placeOrder(@RequestBody OrderRequest orderRequest) {
		return orderService.placeOrder(orderRequest);
	}
	
	@GetMapping("/orders/from/{from}/to/{to}")
	public List<OrderVO> getOrders(@PathVariable("from") String from,
			@PathVariable("to") String to) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss-z");
		return orderService.findOrdersBetweenTimeRange(Instant.from(formatter.parse(from)), Instant.from(formatter.parse(to)));
	}
	
	
}
