package com.lidiwen.utils.excel;

import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 获取模板文件的工具类
 * Created by QuanQqqqq 2018/5/21
 */
public class TemplateFileUtil {

    public static FileInputStream getTemplates(String tempName) throws FileNotFoundException {
        return new FileInputStream(ResourceUtils.getFile("classpath:excel-templates/" + tempName));
    }
}
