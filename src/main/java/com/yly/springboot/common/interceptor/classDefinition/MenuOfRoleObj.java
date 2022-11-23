package com.yly.springboot.common.interceptor.classDefinition;

import lombok.Data;

import java.util.List;

@Data
public  class MenuOfRoleObj {
    String currentRoleId;
    List<String> currentRoleMenuIdArr;
}
