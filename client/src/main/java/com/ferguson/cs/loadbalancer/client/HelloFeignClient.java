package com.ferguson.cs.loadbalancer.client;

import java.util.Date;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "hello-service", url = "http://localhost:8090")
public interface HelloFeignClient {
	@GetMapping("/hello")
	String hello();

	@GetMapping("/hello/{name}")
	String hello(@PathVariable("name") String name);

	@PostMapping("/hello")
	String helloDate(Date today);
}
