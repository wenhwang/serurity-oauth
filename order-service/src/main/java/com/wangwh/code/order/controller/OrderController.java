package com.wangwh.code.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

	@GetMapping("/get/{id}")
	public String getOrder(@PathVariable("id") int id){
		return "order:"+id;
	}

	@GetMapping("/get/list")
	@PreAuthorize("hasAuthority('RWX')")
	public String getOrders(){
		return "all order  " + this.getUsername();
	}

	private String getUsername(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof  UserDetails ){
			return ((UserDetails) principal).getUsername();
		}
		return principal.toString();
	}

}
