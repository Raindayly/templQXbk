package com.yly.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yly.springboot.entity.Files;

import java.util.List;


public interface FileService extends IService<Files> {
    void deleteIds(List<String> ids);
}
