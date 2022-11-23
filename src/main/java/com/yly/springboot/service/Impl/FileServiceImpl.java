package com.yly.springboot.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yly.springboot.entity.Files;
import com.yly.springboot.mapper.FileMapper;
import com.yly.springboot.service.FileService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, Files> implements FileService {


    @Override
    public void deleteIds(List<String> ids) {
        ids.stream().forEach((v)->{
            Files files = baseMapper.selectById(v);
            String fileAddress = files.getFileAddress();
            File file = new File(fileAddress);
            if(file.exists()){
                file.delete();
            }

        });
        baseMapper.deleteBatchIds(ids);
    }
}
