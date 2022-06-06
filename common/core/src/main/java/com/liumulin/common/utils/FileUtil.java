package com.liumulin.common.utils;

import com.liumulin.common.exceptions.CustomException;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 工具类编写范例，使用重载编写不同参数类型的函数组
 *
 * @author liuqiang
 */
public class FileUtil {

    private static final String DEFAULT_CHARSET = "UTF-8";

    public static List<String> readFile2List(String filename) throws IOException {
        return readFile2List(filename, DEFAULT_CHARSET);
    }

    public static List<String> readFile2List(String filename, String charset) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filename);
        return readFile2List(fileInputStream, charset);
    }

    public static List<String> readFile2List(File file) throws IOException {
        return readFile2List(file, DEFAULT_CHARSET);
    }

    public static List<String> readFile2List(File file, String charset) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        return readFile2List(fileInputStream, charset);
    }

    public static List<String> readFile2List(InputStream fileInputStream) throws IOException {
        return readFile2List(fileInputStream, DEFAULT_CHARSET);
    }

    public static List<String> readFile2List(InputStream inputStream, String charset) throws IOException {
        List<String> list = new ArrayList<String>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream, charset));

            String s = null;
            while ((s = br.readLine()) != null) {
                list.add(s);
            }
        } finally {
            IOUtils.closeQuietly(br);
        }

        return list;
    }

    /**
     * 获取文件的后缀名
     * @param appendDot 是否拼接.
     * @return
     */
    public static String getFileSuffix(String fullFileName, boolean appendDot){
        if(fullFileName == null || fullFileName.indexOf(".") < 0 || fullFileName.length() <= 1) {
            return "";
        }
        return (appendDot? "." : "") + fullFileName.substring(fullFileName.lastIndexOf(".") + 1);
    }


    /** 获取有效的图片格式， 返回null： 不支持的图片类型 **/
    public static String getImgSuffix(String filePath){

        String suffix = getFileSuffix(filePath, false).toLowerCase();
//        if(CS.ALLOW_UPLOAD_IMG_SUFFIX.contains(suffix)){
//            return suffix;
//        }
//        throw new BizException("不支持的图片类型");
        throw new CustomException("不支持的图片类型");
    }
}
