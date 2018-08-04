package com.horen.chart.barchart;

import android.graphics.Color;
import android.graphics.Matrix;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.horen.chart.barchart.IBarChartData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :ChenYangYi
 * @date :2018/08/02/11:09
 * @description :柱状图工具类
 * @github :https://github.com/chenyy0708
 */
public class BarChartHelper {
    private BarChart mBarChart;
    private YAxis yAxis;
    private XAxis xAxis;

    public BarChartHelper(BarChart barChart) {
        this.mBarChart = barChart;
        yAxis = mBarChart.getAxisLeft();
        xAxis = mBarChart.getXAxis();
        //背景颜色
        mBarChart.setBackgroundColor(Color.WHITE);
        // 隐藏右边Y轴
        mBarChart.getAxisRight().setEnabled(false);
        mBarChart.setScaleEnabled(false);
        //设置是否可以通过双击屏幕放大图表。默认是true
        mBarChart.setDoubleTapToZoomEnabled(false);
        //设置描述
        mBarChart.getDescription().setEnabled(false);
        //设置按比例放缩柱状图
        mBarChart.setPinchZoom(true);
        mBarChart.setExtraBottomOffset(10);
        mBarChart.setExtraTopOffset(30);
    }

    /**
     * 初始化LineChart
     */
    private void initLineChart() {
        //折线图例 标签 设置
        Legend legend = mBarChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(11f);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        //x坐标轴设置
        XAxis xAxis = mBarChart.getXAxis();
        // x轴对齐位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 隐藏网格线
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        //y轴设置
        YAxis leftAxis = mBarChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        //保证Y轴从0开始，不然会上移一点
        leftAxis.setAxisMinimum(0f);
    }

    /**
     * 展示柱状图(多条)
     *
     * @param barSetData   多个柱状图数据
     * @param labels       标签集合
     * @param barColors    颜色
     * @param displayCount 一页显示柱状图数
     */
    public void showBarChart(List<List<IBarChartData>> barSetData, List<String> labels, List<Integer> barColors, int displayCount) {
        // 初始化图表X、Y轴属性
        initLineChart();
        // X轴真实显示lable
        List<String> xValue = new ArrayList<>();
        // 多柱状图数据集
        BarData data = new BarData();
        // 多柱状图循环，得到每一种柱状图集合数据
        for (int i = 0; i < barSetData.size(); i++) {
            List<IBarChartData> barSetDatum = barSetData.get(i);
            // 单种柱状图数据集
            ArrayList<BarEntry> entries = new ArrayList<>();
            for (int j = 0; j < barSetDatum.size(); j++) {
                if (!xValue.contains(barSetDatum.get(j).getLabelName())) {
                    xValue.add(barSetDatum.get(j).getLabelName());
                }
                entries.add(new BarEntry(j, barSetDatum.get(j).getValue()));
            }
            // 数据集合标签名
            BarDataSet barDataSet = new BarDataSet(entries, labels.get(i));
            barDataSet.setColor(barColors.get(i));
            barDataSet.setValueTextColor(barColors.get(i));
            barDataSet.setValueTextSize(10f);
            data.addDataSet(barDataSet);
        }
        //设置标签居中
        xAxis.setCenterAxisLabels(true);
        xAxis.setLabelCount(xValue.size());
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValue));
        mBarChart.setData(data);
        // 每个组之间的宽度
        float groupSpace = 0.25f;
        // 每组柱之间的宽度
        float barSpace = (1 - groupSpace) / barSetData.size() / 10;
        // 柱宽度
        float barWidth = (1 - groupSpace) / barSetData.size() / 10 * 9;
        data.setBarWidth(barWidth);
        mBarChart.getXAxis().setAxisMinimum(0);
        mBarChart.getXAxis().setAxisMaximum(mBarChart.getBarData().getGroupWidth(groupSpace, barSpace) * xValue.size() + 0);
        data.groupBars(0, groupSpace, barSpace);
        //设置动画效果
        Matrix m = new Matrix();
        //两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的2倍
        if (xValue.size() > displayCount) {
            m.postScale(xValue.size() / displayCount, 1f);
        }
        //将图表动画显示之前进行缩放
        mBarChart.getViewPortHandler().refresh(m, mBarChart, false);
        mBarChart.animateY(500, Easing.EasingOption.Linear);
        mBarChart.animateX(500, Easing.EasingOption.Linear);
    }

    /**
     * 展示柱状图(一条)
     *
     * @param barChartData 单个柱状图数据
     * @param color        柱状图颜色
     * @param displayCount 一页显示柱状图数
     */
    public void showBarChart(List<IBarChartData> barChartData, int color, int displayCount) {
        initLineChart();
        // X轴真实显示lable
        List<String> xValue = new ArrayList<>();
        // Y轴图标数据
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < barChartData.size(); i++) {
            xValue.add(barChartData.get(i).getLabelName());
            entries.add(new BarEntry((float) i, barChartData.get(i).getValue()));
        }
        //折线图例 标签 设置
        mBarChart.getLegend().setEnabled(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValue));
        // 每一个BarDataSet代表一类柱状图
        BarDataSet barDataSet = new BarDataSet(entries, "");
        barDataSet.setColor(color);
        barDataSet.setValueTextColor(color);
        barDataSet.setValueTextSize(10f);
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);
        BarData data = new BarData(dataSets);
        mBarChart.setData(data);
        float barWidth = 0.3f;
        data.setBarWidth(barWidth);
        mBarChart.getLegend().setEnabled(false);
        //设置动画效果
        Matrix m = new Matrix();
        //两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的2倍
        if (xValue.size() > displayCount) {
            m.postScale(xValue.size() / displayCount, 1f);
        }
        //将图表动画显示之前进行缩放
        mBarChart.getViewPortHandler().refresh(m, mBarChart, false);
        mBarChart.animateY(500, Easing.EasingOption.Linear);
        mBarChart.animateX(500, Easing.EasingOption.Linear);
    }
}
