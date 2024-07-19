package com.huice.health.common.core;

import java.io.Serializable;
import java.util.List;

/**
 * @param <T>
 * @author Yida Yang
 * @since 2024-06-03
 */
public class PageImpl<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 返回集合
     */
    private List<T> list;

    /**
     * 大小
     */
    private Long count;

    public PageImpl() {
    }

    public PageImpl(List<T> list, Long count) {
        this.list = list;
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "PageImpl{" +
                "list=" + list +
                ", count=" + count +
                '}';
    }
}
