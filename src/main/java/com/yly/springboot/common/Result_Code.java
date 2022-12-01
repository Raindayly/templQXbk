package com.yly.springboot.common;

public enum Result_Code {

    //操作成功
    CODE_200(200,"操作成功"),


    //一般错误
    CODE_500(500,"系统错误"),
    CODE_400(400,"表单错误"),
    CODE_600(600,"业务错误"),

    //登录时错误
    CODE_601(601,"用户名或密码错误"),
    CODE_602(602,"用户不存在"),
    CODE_603(603,"用户名或密码为空"),

    //token错误
    CODE_620(620,"token验证失败"),
    CODE_621(621,"token过期");



    private final Integer code;
    private final String msg;
    Result_Code(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }
    public Integer getCode() {
        return this.code;
    }
}
