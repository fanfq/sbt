package com.fanfq.plugin.exception;

import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fanfq.common.util.model.result.JsonResult;
import com.fanfq.common.util.model.result.ResultCode;

@ControllerAdvice
//全局的异常处理，针对restcontroller
@RestControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

	/**
     * 非法访问
     * 
     * @param ex 非法访问的异常对象
     * @return 响应体
     */
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<JsonResult> handleFileNotFoundException(
    		HttpServletRequest req,FileNotFoundException ex) {
        logger.error("操作失败：{}", ex.getMessage());


        return new ResponseEntity<JsonResult>(new JsonResult(ResultCode.NOT_FOUND), HttpStatus.NOT_FOUND);
    }
    
    /**
     * 非法访问
     * 
     * @param ex 非法访问的异常对象
     * @return 响应体
     */
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<JsonResult> handleNumberFormatException(
    		NumberFormatException ex) {
        logger.error("操作失败：{}", ex.getMessage());


        return new ResponseEntity<>(new JsonResult(ResultCode.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    /**
     * 重复操作
     * 
     * @param ex 重复操作的异常对象
     * @return 响应体
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<JsonResult> handleMethodArgumentTypeMismatchException(
    		MethodArgumentTypeMismatchException ex) {
        logger.error("操作失败：{}", ex.getMessage());



        return new ResponseEntity<>(new JsonResult(ResultCode.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }


    /**
     * 其他异常都按照"未知异常"处理
     * 
     * @param e 异常对象
     * @return 响应体
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<JsonResult> otherException(Exception e) {

        logger.error("操作失败：{}", e.getMessage());
        return new ResponseEntity<>(new JsonResult(ResultCode.BAD_REQUEST), HttpStatus.BAD_REQUEST);

    }
}

