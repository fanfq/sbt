package com.fanfq.sbt.controller;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/web")
public class SbtWebController {
	
	@RequestMapping("/hello")
	public String hello(Map<String, Object> model,@RequestParam(required=false,defaultValue="Fred") String name) {
		// = new HashMap<>();
		model.put("ts", System.currentTimeMillis());
		model.put("msg", "hello, "+name);
		return "hello"; //自动寻找resources/templates中名字为hello.ftl/hello.vm的文件作为模板，拼装后返回
	}
}

