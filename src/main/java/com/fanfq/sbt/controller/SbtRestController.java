package com.fanfq.sbt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fanfq.common.util.model.result.JsonResult;
import com.fanfq.common.util.model.result.ResultCode;
import com.fanfq.sbt.config.MyConfig;
import com.fanfq.sbt.model.Sbt;
import com.fanfq.sbt.service.SbtService;

@RestController
@RequestMapping("sbt")
public class SbtRestController {
	
	@Autowired
	private SbtService sbtService;
	
	@Autowired
	private MyConfig myConfig;
	
	@RequestMapping("/test")
	public ResponseEntity<JsonResult> test(){
		JsonResult js = new JsonResult(ResultCode.OK,myConfig.getSimpleProp());
		return new ResponseEntity<JsonResult>(js,HttpStatus.OK);
	}
	
	@RequestMapping("/list")
	public ResponseEntity<JsonResult> list(){
		List<Sbt> sbts = sbtService.selectAll();
		JsonResult js = new JsonResult(ResultCode.OK,sbts);
		return new ResponseEntity<JsonResult>(js,HttpStatus.OK);
	}
	
	@RequestMapping("/online")
	public ResponseEntity<JsonResult> online(){
		
		return null;
	}
	
	@RequestMapping("/login")
	public ResponseEntity<JsonResult> login(@RequestParam String name,@RequestParam String password){
		return null;
	}
	
	@RequestMapping("/register")
	public ResponseEntity<JsonResult> register(Sbt sbt){
		sbtService.insertSbt(sbt);
		JsonResult js = new JsonResult(ResultCode.OK);
		return new ResponseEntity<JsonResult>(js,HttpStatus.OK);
	}

}
