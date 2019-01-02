package com.fanfq.sbt.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fanfq.common.util.model.result.JsonResult;
import com.fanfq.common.util.model.result.ResultCode;
import com.fanfq.sbt.annotation.Permission;

@RestController
public class AdminController {

	/**
	 * 登录
	 * @param response：用于保存token到cookie中
	 * @param map：包含userName和password
	 * @return
	 */
	@Permission
	@RequestMapping("/admin")
	public JsonResult login(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> map) {
//		if(userName.equals(map.get("userName")) && pw.equals(map.get("password"))){
//			return new JsonResult(ResultCode.OK, "登录成功！", null);
//		}else {
//			
//		}
		return new JsonResult(ResultCode.BAD_REQUEST, "登录失败！", null);
	}
}
