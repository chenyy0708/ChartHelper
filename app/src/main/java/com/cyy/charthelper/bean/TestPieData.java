package com.cyy.charthelper.bean;

import com.horen.chart.piechart.IPieData;

/**
 * @author :ChenYangYi
 * @date :2018/08/03/13:02
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class TestPieData implements IPieData {
    private String name;
    private double valueData;

    public TestPieData(double valueData, String name) {
        this.name = name;
        this.valueData = valueData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 值
     */
    @Override
    public float getValue() {
        return (float) valueData;
    }

    /**
     * 对应名字
     */
    @Override
    public String getLabelName() {
        return name;
    }

    public void setValue(double value) {
        this.valueData = value;
    }
}
