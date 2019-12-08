package com.myshop.order.service.product.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.myshop.order.domain.Product;


@FeignClient(name = "product")
@RibbonClient(name = "product")
public interface ProductServiceProxy {

	@GetMapping("/products/{id}")
	public Product get(@PathVariable Long id);
}
