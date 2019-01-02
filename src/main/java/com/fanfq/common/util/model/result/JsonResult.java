package com.fanfq.common.util.model.result;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResult implements Serializable {
	private static final long serialVersionUID = 1L;
	private int code;
	private String msg;
	private Object data;
//	private String method;
	
	public JsonResult() {
		this.setCode(ResultCode.OK);
		this.setMessage("ok");
	}
	
	public JsonResult(ResultCode code) {
		this.setCode(code);
		this.setMessage(code.msg());
	}
	
	public JsonResult(ResultCode code, String message) {
		this.setCode(code);
		this.setMessage(message);
	}
	
	public JsonResult(ResultCode code, String message, Object data) {
		this.setCode(code);
		this.setMessage(message);
		this.setData(data);
	}
	
	public JsonResult(ResultCode code, Object data) {
		this.setCode(code);
		this.setMessage(code.msg());
		this.setData(data);
	}
	
//	public JsonResult(ResultCode code, String message, String method, Object data) {
//		this.setCode(code);
//		if(CheckUtil.checkStrIsNull(message)) {
//			this.setMessage(code.msg());
//		}else {
//			this.setMessage(message);
//		}
//		this.setMethod(method);
//		this.setData(data);
//	}
	
//	public String getMethod() {
//		return method;
//	}
//
//	public void setMethod(String method) {
//		this.method = method;
//	}

	public int getCode() {
		return code;
	}
	public void setCode(ResultCode code) {
		this.code = code.code();
	}
	public String getMsg() {
		return msg;
	}
	public void setMessage(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
