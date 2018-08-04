package com.horen.chart.piechart;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.horen.chart.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :ChenYangYi
 * @date :2018/08/02/09:53
 * @description :饼状图工具类
 * @github :https://github.com/chenyy0708
 */
public class PieChartHelper {

    /**
     * 初始化饼状图
     *
     * @param context  上下文
     * @param entries  数据集
     * @param pieChart 饼状图
     * @param title    标题
     */
    public static void setData(Context context, List<IPieData> entries, PieChart pieChart, String title) {
        //设置为TRUE的话，图标中的数据自动变为percent
        pieChart.setUsePercentValues(false);
        pieChart.setExtraOffsets(30, 10, 30, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawCenterText(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);
        // 中心空白圆心大小
        pieChart.setHoleRadius(70f);
        pieChart.setDrawEntryLabels(false);
        // 中心图表孔旁边的透明圆的半径
        pieChart.setTransparentCircleRadius(0);
        pieChart.animateY(500, Easing.EasingOption.EaseInOutQuad);
        // 输入标签样式
        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setEntryLabelTextSize(12f);
        Legend mLegend = pieChart.getLegend();
        //设置禁用比例块
        mLegend.setEnabled(false);
        // 初始化图表数据
        initData(context, entries, pieChart, title);
    }

    private static void initData(Context context, List<IPieData> entries, PieChart pieChart, String title) {
        ArrayList<PieEntry> pieEntries = new ArrayList<PieEntry>();
        for (IPieData entry : entries) {
            pieEntries.add(new PieEntry(entry.getValue(), entry.getLabelName()));
        }
        //饼图数据集
        PieDataSet dataSet = new PieDataSet(pieEntries, title);
        // 百分比
        dataSet.setValueFormatter(new PercentFormatter());
        dataSet.setValueTextColor(Color.parseColor("#FFFFFF"));
        dataSet.setValueTextSize(12);
        // 设置文字透明，不显示
//        dataSet.setValueTextColor(Color.parseColor("#00000000"));
//        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        //饼图区块之间的距离
        dataSet.setSliceSpace(0f);
        //饼图Item被选中时变化的距离
        dataSet.setSelectionShift(2f);
        //颜色填充
        String[] colors = context.getResources().getStringArray(R.array.chart_colors);
        List<Integer> chartColors = new ArrayList<>();
        for (String color : colors) {
            chartColors.add(Color.parseColor(color));
        }
        dataSet.setColors(chartColors);
        //数据填充
        PieData piedata = new PieData(dataSet);
        //设置饼图上显示数据的字体大小
        piedata.setValueTextSize(15f);
        pieChart.setData(piedata);
        //在给定的数据集中突出显示给定索引的值
        pieChart.highlightValues(null);
        //刷新
        pieChart.invalidate();
    }
}
