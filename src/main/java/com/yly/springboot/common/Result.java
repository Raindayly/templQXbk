package com.yly.springboot.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 *
 * 接口统一返回包装类
 */

public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean success;
    private String message;
    private Integer code;
    private long timestamp = System.currentTimeMillis();
    private T result;

    public Result() {
    }

    public static long getSerialVersionUID() {
        return 1L;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}


//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Result {
//    private String code;
//    private String msg;
//    private Object data;
//
//    public static Result success(){
//        return new Result(Result_Code.CODE_200.getCode(), Result_Code.CODE_200.getMsg() ,null);
//    }
//    public static Result success(Object data){
//        return new Result(Result_Code.CODE_200.getCode(),Result_Code.CODE_200.getMsg(),data);
//    }
//
//    public static Result error(){
//        return new Result(Result_Code.CODE_500.getCode(),Result_Code.CODE_500.getMsg(),null);
//    }
//    public static Result error(String code,String msg){
//        return new Result(code,msg,null);
//    }
//
//}
