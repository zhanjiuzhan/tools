package org.jpcl.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class PageUtils<T> {
    /**
     * 一页显示的条目数
     */
    private int pageSize;

    /**
     * 当前的页码
     */
    private int currentPage;

    /**
     * 总条目数
     */
    private int count;

    /**
     * 显示的信息
     */
    private List<T> data;

    public PageUtils(){
        this.pageSize = 10;
        this.currentPage = 1;
        this.data = new ArrayList<>(0);
    }

    public PageUtils(Page page, int count, List<T> data){
        this.pageSize = page.getCurrentPageSize();
        this.currentPage = page.getCurrentPage();
        this.count = count;
        this.data = data;
    }

    public PageUtils(int count){
        this();
        this.setCount(count);
    }

    public PageUtils(int count, int pageSize){
        this(count);
        this.setPageSize(pageSize);
    }

    public PageUtils(int count, int pageSize, int currentPage){
        this(count, pageSize);
        this.setCurrentPage(currentPage);
    }

    public PageUtils(int count, int pageSize, int currentPage, List<T> data){
        this(count, pageSize, currentPage);
        this.setData(data);
    }

    public int getStartItem() {
        return pageSize * (currentPage - 1);
    }

    public int getPageSize() {
        return pageSize;
    }

    public PageUtils setPageSize(int pageSize) {
        if (pageSize < 1) {
            this.pageSize = 1;
        } else {
            this.pageSize = pageSize;
        }
        return this;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public PageUtils setCurrentPage(int currentPage) {
        if (currentPage < 1) {
            this.currentPage = 1;
        } else {
            this.currentPage = currentPage;
        }
        return this;
    }

    public int getCount() {
        return count;
    }

    public PageUtils setCount(int count) {
        if (count < 0) {
            this.count = 0;
        } else {
            this.count = count;
        }
        return this;
    }

    public List<T> getData() {
        return data;
    }

    public PageUtils setData(List<T> data) {
        if (data != null) {
            this.data = data;
        }
        return this;
    }

    public interface Page {
        /**
         * 取得当前的页数
         * @return
         */
        Integer getCurrentPage();

        /**
         * 取得当前的每页大小
         * @return
         */
        Integer getCurrentPageSize();
    }
}
