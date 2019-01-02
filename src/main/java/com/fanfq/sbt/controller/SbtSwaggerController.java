package com.fanfq.sbt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fanfq.common.util.model.result.JsonResult;
import com.fanfq.common.util.model.result.ResultCode;
import com.fanfq.sbt.config.MyConfig;
import com.fanfq.sbt.model.Sbt;
import com.fanfq.sbt.service.SbtService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("swg")
public class SbtSwaggerController {

	@Autowired
	private SbtService sbtService;
	
	@Autowired
	private MyConfig myConfig;
	
	@RequestMapping("/test")
	public ResponseEntity<JsonResult> test(){
		JsonResult js = new JsonResult(ResultCode.OK,myConfig.getSimpleProp());
		return new ResponseEntity<JsonResult>(js,HttpStatus.OK);
	}
	
// http://localhost:8083/swagger-ui.html#/	
//	@ApiOperaction表示这是一个需要Swagger修饰的接口，其中表明了接口名称，请求方式、备注说明等信息。
//	@ApiImplicitParam表示该接口输入的参数： 
//	name表示参数名称； 
//	value表示参数说明； 
//	paramType表示传入类型，请求头传入写query,JSON类型传入写json； 
//	defaultValue表示默认值； 
//	required表示参数是否必须传。
//  https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
	
	@ApiOperation(value="cha哈哈",notes="这是个测试")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="query",name="token",dataType="String",required=true,value="token",defaultValue="123456"),
		@ApiImplicitParam(paramType="query",name="uname",dataType="String",required=false,value="用户名",defaultValue="")
	})
	@RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<JsonResult> list(){
		List<Sbt> sbts = sbtService.selectAll();
		JsonResult js = new JsonResult(ResultCode.OK,sbts);
		return new ResponseEntity<JsonResult>(js,HttpStatus.OK);
	}
}
