package com.yly.springboot.service.impl;

import com.yly.springboot.entity.Role;
import com.yly.springboot.mapper.RoleMapper;
import com.yly.springboot.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yly
 * @since 2022-07-30
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
