package com.yly.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author yly
 * @since 2022-07-30
 */
@Data
@TableName("sys_role")
@ApiModel(value = "Role对象", description = "")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
      private String id;

      @ApiModelProperty("名称")
      private String name;

      @ApiModelProperty("唯一标识")
      private String flag;


      @ApiModelProperty("描述")
      private String description;

}
