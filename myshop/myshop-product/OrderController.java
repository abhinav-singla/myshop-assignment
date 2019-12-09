package com.myshop.order.api;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/orders/")
	@ApiOperation(value = "Place on order by providing orderRequest",
			notes = "Provide the placed order details after receiving an order request",
			response = OrderVO.class)
	public OrderVO placeOrder(
			@ApiParam(value = "Order Request containing user email and list of ids for products", required = true) 
			@RequestBody OrderRequest orderRequest) {
		return orderService.placeOrder(orderRequest);
	}
	
	@GetMapping("/orders/from/{from}/to/{to}")
	@ApiOperation(value = "Provide details of orders between given from and to time range (inclusive both)",
	notes = "Provide details of orders for given from and to time",
	response = ArrayList.class)
	public List<OrderVO> getOrders(
			@ApiParam(value = "from date-time should be in format yyyy-MM-dd-HH:mm:ss-z (Example 2019-12-09-10:00:00-CET)", required = true) 
			@PathVariable("from") String from,
			@ApiParam(value = "to date-time should be in format yyyy-MM-dd-HH:mm:ss-z (Example 2019-12-09-10:00:00-CET)", required = true) 
			@PathVariable("to") String to) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss-z");
		return orderService.findOrdersBetweenTimeRange(Instant.from(formatter.parse(from)), Instant.from(formatter.parse(to)));
	}
	
	
}
