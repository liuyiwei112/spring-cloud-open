package com.finance.common.utils;

import java.io.Serializable;
import java.util.List;

public class Pager<T> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8584851417359686326L;
    /**
     * 当前页数
     */
    private int page = 1;
    /**
     * 每页条数
     */
    private int pageSize = 20;
    /**
     * 总条数
     */
    private long totalCount;
    /**
     * 总页数
     */
    private long totalPageCount;
    /**
     * 查询返回列表
     */
    private List<T> list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(long totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void paging(int page,int pageSize,int totalCount){
        this.page = page;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPageCount = totalCount%pageSize == 0?totalCount/pageSize:totalCount/pageSize+1;
    }
}
