package com.liumulin;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.Collections;

/**
 * @author liuqiang
 * @since 2022-06-01
 */
public class Gen {
    public static void main(String[] args) {
//        System.out.println(new File("system.dir"));
//        System.out.println(new File("src").getAbsolutePath());
        String file = Gen.class.getClassLoader().getResource("/").getFile();
        System.out.println("file = " + file);
    }
    public static void main1(String[] args) {
//        FastAutoGenerator.create("url", "username", "password")
//                .globalConfig(builder -> {
//                    builder.author("baomidou") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
//                            .outputDir("D://"); // 指定输出目录
//                })
//                .packageConfig(builder -> {
//                    builder.parent("com.baomidou.mybatisplus.samples.generator") // 设置父包名
//                            .moduleName("system") // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile., new File("sys"))); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("t_simple") // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
//                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();
    }
}
