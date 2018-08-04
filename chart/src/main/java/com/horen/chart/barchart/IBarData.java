package com.horen.chart.barchart;

/**
 * @author :ChenYangYi
 * @date :2018/08/03/13:16
 * @description :柱状图值
 * @github :https://github.com/chenyy0708
 */
public interface IBarData {
    /**
     * 图表Y轴值
     */
    float getValue();

    /**
     * X轴对应Name
     */
    String getLabelName();
}
