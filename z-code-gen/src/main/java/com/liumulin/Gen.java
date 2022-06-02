package com.liumulin;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;

/**
 * @author liuqiang
 * @since 2022-06-01
 */
public class Gen {
    public static final String THIS_MODULE_NAME = "z-code-gen"; // 当前项目模块名称
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "123456";
    public static final String AUTHOR = "Daniel Liu";
    public static final String[] TABLES = {"user_info", "config"};
    private static String projectPath = System.getProperty("user.dir");  // 获取当前项目的工作目录

    public static void main(String[] args) {
//        System.out.println(System.getProperty("line.separator"));
//        String os = System.getProperty("os.name").toLowerCase();
//        System.out.println("os = " + os);
        FastAutoGenerator.create(DB_URL, USERNAME, PASSWORD)
                .globalConfig(builder -> {
                    builder.author(AUTHOR) // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .disableOpenDir() // 执行完毕不打开文件夹
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(getOutputDir()); // 指定输出/写入目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.liumulin") // 设置父包名
//                            .moduleName("") // 设置父包模块名
                            .entity("entity")
                            .controller("controller")
                            .service("service")
                            .serviceImpl("service.impl")
//                            .service(null)
//                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .xml("mapper.xml");
//                            .other("other")
//                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,"C:\\")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(TABLES) // 设置需要生成的表名
                            .addTablePrefix("t_", "c_") // 设置过滤表前缀

                            .serviceBuilder() // 开启 service 策略配置
//                            .formatServiceFileName("%sService") // 取消 Service 前的 I

                            .controllerBuilder() // 开启 controller 策略配置
                            .enableRestStyle() // 配置 restful 风格
                            .enableHyphenStyle() // url 中驼峰转连字符

                            .entityBuilder() // 开启实体类策略配置
                            .enableLombok() // 开启 lombok
                            .enableChainModel(); // 开启 lombok 链式操作
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }

    private static String getOutputDir() {
        if (!projectPath.endsWith(THIS_MODULE_NAME)) {  //解决IDEA中 项目目录问题
            projectPath += File.separator + THIS_MODULE_NAME;
        }
        return projectPath + "/src/main/java";
    }

}
