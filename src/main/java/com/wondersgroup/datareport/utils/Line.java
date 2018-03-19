package com.wondersgroup.datareport.utils;

import java.util.Date;
import java.util.List;

/**
 * @projectName:datareport
 * @packageName:com.wondersgroup.datareport.utils
 * @authorName:wangjiaming
 * @createDate:2018-03-07
 * @editor:IntelliJ IDEA
 * @other:
 **/
public class Line {
    public String legendData;
    public List<String> xAxisData;
    public List<String> seriesData;

    public String getLegendData() {
        return legendData;
    }

    public void setLegendData(String legendData) {
        this.legendData = legendData;
    }

    public List<String> getxAxisData() {
        return xAxisData;
    }

    public void setxAxisData(List<String> xAxisData) {
        this.xAxisData = xAxisData;
    }

    public List<String> getSeriesData() {
        return seriesData;
    }

    public void setSeriesData(List<String> seriesData) {
        this.seriesData = seriesData;
    }
}
