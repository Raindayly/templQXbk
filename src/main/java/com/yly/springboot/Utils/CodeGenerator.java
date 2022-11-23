package com.yly.springboot.Utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;


public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:p6spy:mysql://localhost:3306/management?serverTimezone=GMT%2b8",
                "root", "root")
                .globalConfig(builder -> {
                    builder.author("yly") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C://Users//YLY//Desktop//用户管理系统//后端//springboot//src//main//java"); // 指定输出目录

                })
                .packageConfig(builder -> {
                    builder.parent("com.yly.springboot") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "C://Users//YLY//Desktop//用户管理系统//后端//springboot//src//main//resources//mapper")); // 设置mapperXml生成路径

                })
                .strategyConfig(builder -> {
                    builder.addInclude("sys_role_menu_relation") // 设置需要生成的表名
                            .addTablePrefix("sys_") // 设置过滤表前缀
                            .controllerBuilder()
                            .enableRestStyle()
                            .entityBuilder()
                            .enableLombok()
                            .naming(NamingStrategy.underline_to_camel);
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
