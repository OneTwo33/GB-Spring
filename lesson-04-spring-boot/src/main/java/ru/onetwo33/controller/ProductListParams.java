package ru.onetwo33.controller;

import java.math.BigDecimal;

public class ProductListParams {

    private String titleFilter;
    private BigDecimal minCostFilter;
    private BigDecimal maxCostFilter;
    private Integer page = 1;
    private Integer size;
    private String sort;
    private String direction = "asc";

    public String getTitleFilter() {
        return titleFilter;
    }

    public void setTitleFilter(String titleFilter) {
        this.titleFilter = titleFilter;
    }

    public BigDecimal getMinCostFilter() {
        return minCostFilter;
    }

    public void setMinCostFilter(BigDecimal minCostFilter) {
        this.minCostFilter = minCostFilter;
    }

    public BigDecimal getMaxCostFilter() {
        return maxCostFilter;
    }

    public void setMaxCostFilter(BigDecimal maxCostFilter) {
        this.maxCostFilter = maxCostFilter;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
