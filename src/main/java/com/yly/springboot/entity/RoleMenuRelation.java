package com.yly.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author yly
 * @since 2022-08-03
 */
@Getter
@Setter
  @TableName("sys_role_menu_relation")
@ApiModel(value = "RoleMenuRelation对象", description = "")
public class RoleMenuRelation implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("role_id")
      private String roleid;

      @ApiModelProperty("menu_id")
      private String menuid;


}
