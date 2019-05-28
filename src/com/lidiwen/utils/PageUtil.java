package com.lidiwen.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 分页详情
 * @Author: Shawn Van
 * @CreateDate: 2019/04/13 下午 01:08
 */
@Data
public class PageUtil implements Serializable {
    private static final long serialVersionUID = 746111558653435181L;
    //当前页
    private int page;
    //每页显示的记录数
    private int pageSize;
    //总记录数
    private int totalCount;
    //总页数=总记录数totalCount/每页显示的记录数pageSize
    private int totalPage;

    public PageUtil() {

    }

    public PageUtil(int page, int pageSize, int totalCount) {
        this.page = page;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
    }
}
