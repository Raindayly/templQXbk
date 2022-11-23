package com.yly.springboot.mapper;

import com.yly.springboot.entity.RoleMenuRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yly
 * @since 2022-08-03
 */
public interface RoleMenuRelationMapper extends BaseMapper<RoleMenuRelation> {

    @Select("SELECT menuid FROM sys_role_menu_relation where roleid = #{roleid}")
    List<String> selectMenusByRoleId(@Param("roleid")String roleid);
}
