package com.wangwh.code.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
@Slf4j
public class UaaController {

	@RequestMapping("/login/client")
	public String  grantToken(String code){
		log.debug("oauth code : {}",code);
		return "111111";
	}
}
