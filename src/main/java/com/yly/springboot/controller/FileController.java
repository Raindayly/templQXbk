package com.yly.springboot.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yly.springboot.common.Result;
import com.yly.springboot.common.ResultUtil;
import com.yly.springboot.common.Result_Code;
import com.yly.springboot.entity.Files;
import com.yly.springboot.exception.ServiceException;
import com.yly.springboot.mapper.FileMapper;
import com.yly.springboot.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * 这个注解将yml文件里的参数引用过来
     */
    @Value("${file.upload.path}")
    private String fileUploadPath;

    @Resource
    private FileMapper fileMapper;

    @Resource
    private FileService fileService;

    /**
     * @param file 前端传过来的文件
     * @return
     * @throws IOException
     */
    @PostMapping("/image/upload")
    public String upload(@RequestParam("file") MultipartFile file,HttpServletResponse rep) throws IOException {


//        rep.setContentType();
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();

        File uploadParentFile = new File(fileUploadPath);
        if (!uploadParentFile.exists()) {
            uploadParentFile.mkdir();
        }
        String uuid = IdUtil.fastSimpleUUID();
        String fileUuid = uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + fileUuid);
        //给文件创造一个标识码

        file.transferTo(uploadFile);



        Image image = ImageIO.read(uploadFile);

        if (image == null){
            return null;
        }
        String md5 = SecureUtil.md5(uploadFile);
        Files md5file = getMd5FileList(md5);

        String url;


        if (md5file != null) {
            url = md5file.getUrl();
            String address = md5file.getFileAddress();
            if (!new File(address).exists()) {

                //防止删除本地图片后图片去重功能失效
                url = "http://localhost:9090/file/" + fileUuid;
                Files updateFile = new Files();
                updateFile.setId(md5file.getId());
                updateFile.setType(type);
                updateFile.setSize(size / 1024);
                updateFile.setUrl(url);
                updateFile.setFileAddress(fileUploadPath+fileUuid);
                fileMapper.updateById(updateFile);
            } else {
                uploadFile.delete();
            }

        } else {
            /**
             * 这个方法是把获取到的文件存储到磁盘上去
             */
            url = "http://localhost:9090/file/" + fileUuid;
            String address = fileUploadPath + fileUuid;
            Files saveFile = new Files();
            saveFile.setName(originalFilename);
            saveFile.setType(type);
            saveFile.setSize(size / 1024);
            saveFile.setUrl(url);
            saveFile.setMd5(md5);
            saveFile.setFileAddress(address);
            fileMapper.insert(saveFile);
        }
        return url;
    }



    @GetMapping("/{fileUuid}")
    public void download(@PathVariable String fileUuid, HttpServletResponse response) throws IOException {
        File uploadFile = new File(fileUploadPath + fileUuid);
        Image image = ImageIO.read(uploadFile);

        if (image != null){
            response.setContentType("image/webp");
        }else {
            response.setContentType("application/octet-stream");
        }
        ServletOutputStream outputStream = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUuid, "UTF-8"));

        //读取文件的字节流

        outputStream.write(FileUtil.readBytes(uploadFile));
        outputStream.flush();
        outputStream.close();
    }

    public Files getMd5FileList(String md5) {
        QueryWrapper<Files> filesQueryWrapper = new QueryWrapper<>();
        filesQueryWrapper.eq("md5", md5);
        List<Files> filesList = fileMapper.selectList(filesQueryWrapper);
        return filesList.size() == 0 ? null : filesList.get(0);
    }

    @GetMapping("/page")
    public Result findPage(Integer pageNum, Integer pageSize, @RequestParam(defaultValue = "") String name) {
        QueryWrapper<Files> userQueryWrapper = new QueryWrapper<>();
        if (!name.equals("")) {
            userQueryWrapper.like("name", name);
        }
        Page<Files> objectPage = new Page<>(pageNum, pageSize);

        return new ResultUtil<>().setData(fileMapper.selectPage(objectPage, userQueryWrapper));
    }

    @PostMapping("/delete")
    public Result info(@RequestBody List<String> ids) {
        fileService.deleteIds(ids);
        return new ResultUtil<>().setSuccessMsg(Result_Code.CODE_200.getMsg());
    }


}
