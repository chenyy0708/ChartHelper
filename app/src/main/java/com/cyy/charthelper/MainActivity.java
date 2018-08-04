package com.cyy.charthelper;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cyy.charthelper.bean.TestBarData;
import com.cyy.charthelper.bean.TestLineData;
import com.cyy.charthelper.bean.TestPieData;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.horen.chart.barchart.BarChartHelper;
import com.horen.chart.barchart.IBarChartData;
import com.horen.chart.linechart.ILineChartData;
import com.horen.chart.linechart.LineChartHelper;
import com.horen.chart.piechart.IPieData;
import com.horen.chart.piechart.PieChartHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PieChart pieChart;
    private BarChart bar_chart;
    private BarChart single_bar_chart;
    private LineChart single_line_chart;
    private LineChart line_chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pieChart = findViewById(R.id.mPieChart);
        bar_chart = findViewById(R.id.bar_chart);
        single_bar_chart = findViewById(R.id.single_bar_chart);
        single_line_chart = findViewById(R.id.single_line_chart);
        line_chart = findViewById(R.id.line_chart);
        initPieChart();
        initMulitleBarChart();
        initSingleBarChart();
        initMulitleLineChart();
        initSingleLineChart();
    }

    private void initSingleLineChart() {
// 单个柱状图数据
        ArrayList<ILineChartData> entries = new ArrayList<>();
        for (int i1 = 0; i1 < 30; i1++) {
            entries.add(new TestLineData((Math.random() * 200), (i1 + 1) + "日"));
        }
        LineChartHelper barChartHelper = new LineChartHelper(single_line_chart);
        //颜色填充
        String[] colors = getResources().getStringArray(R.array.chart_colors);
        //创建多条折线的图表
        barChartHelper.showSingleLineChart(entries, Color.parseColor(colors[0]), 5);
    }

    private void initMulitleLineChart() {
        //线的名字集合
        List<String> names = new ArrayList<>();
        names.add("入库");
        names.add("出库");
        // 多条柱状图数据集合
        List<List<ILineChartData>> data = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            // 单个柱状图数据
            ArrayList<ILineChartData> entries = new ArrayList<>();
            for (int i1 = 0; i1 < 30; i1++) {
                entries.add(new TestLineData((Math.random() * 200), (i1 + 1) + "日"));
            }
            data.add(entries);
        }
        LineChartHelper lineChartHelper = new LineChartHelper(line_chart);
        //颜色填充
        String[] colors = getResources().getStringArray(com.horen.chart.R.array.chart_colors);
        List<Integer> chartColors = new ArrayList<>();
        for (String color : colors) {
            chartColors.add(Color.parseColor(color));
        }
        //创建多条折线的图表
        lineChartHelper.showMultipleLineChart(data, names, chartColors, 6);
    }

    private void initMulitleBarChart() {
//线的名字集合
        List<String> names = new ArrayList<>();
        names.add("入库");
        names.add("出库");
        names.add("在库");
        // 多条柱状图数据集合
        List<List<IBarChartData>> data = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            // 单个柱状图数据
            ArrayList<IBarChartData> entries = new ArrayList<>();
            entries.add(new TestBarData((Math.random() * 200), "IF1040"));
            entries.add(new TestBarData((Math.random() * 200), "IF1041"));
            entries.add(new TestBarData((Math.random() * 200), "IF330"));
            entries.add(new TestBarData((Math.random() * 200), "OF330"));
            entries.add(new TestBarData((Math.random() * 200), "OF890"));
            entries.add(new TestBarData((Math.random() * 200), "IF110"));
            data.add(entries);
        }

        BarChartHelper barChartHelper = new BarChartHelper(bar_chart);
        //颜色填充
        String[] colors = getResources().getStringArray(com.horen.chart.R.array.chart_colors);
        List<Integer> chartColors = new ArrayList<>();
        for (String color : colors) {
            chartColors.add(Color.parseColor(color));
        }
        //创建多条折线的图表
        barChartHelper.showBarChart(data, names, chartColors, 3);
    }

    private void initSingleBarChart() {
        //模拟数据
        ArrayList<IBarChartData> entries = new ArrayList<>();
        entries.add(new TestBarData((Math.random() * 80), "液袋"));
        entries.add(new TestBarData((Math.random() * 80), "拉阀"));
        entries.add(new TestBarData((Math.random() * 80), "喉箍"));
        entries.add(new TestBarData((Math.random() * 80), "阀门"));
        BarChartHelper barChartHelper = new BarChartHelper(single_bar_chart);
        //颜色填充
        String[] colors = this.getResources().getStringArray(com.horen.chart.R.array.chart_colors);
        //创建多条折线的图表
        barChartHelper.showBarChart(entries, Color.parseColor(colors[0]), 4);
    }

    private void initPieChart() {
        //模拟数据
        ArrayList<IPieData> entries = new ArrayList<>();
        entries.add(new TestPieData(40, "IF1040"));
        entries.add(new TestPieData(10, "IF1041"));
        entries.add(new TestPieData(20, "IF330"));
        entries.add(new TestPieData(15, "OF330"));
        entries.add(new TestPieData(10, "OF890"));
        entries.add(new TestPieData(5, "RTP"));
        new PieChartHelper.Builder()
                // 上下文
                .setContext(this)
                // 饼图
                .setPieChart(pieChart)
                // 绑定数据
                .setPieData(entries)
                // Label颜色
                .setLableTextColor(Color.WHITE)
                .setLableTextSize(14)
                // 饼图内标签文字
                .setDrawEntryLabels(true)
                // 动画时间
                .setDurationMillis(2000)
                // 动画类型
                .setEasing(Easing.EasingOption.EaseInOutQuad)
                // 旋转角度
                .setRawRotationAngle(90)
                // 中心图表孔旁边的透明圆的半径
                .setTransparentCircleRadiusPercent(10f)
                // 饼图Item被选中时变化的距离
                .setSelectionShift(10f)
                // 饼图区块之间的距离
                .setSliceSpace(5f)
                // 中心空白圆心大小
                .setHoleRadiusPercent(40f)
                // 是否开启标签
                .setLegendEnable(false)
                .build();
    }


}
