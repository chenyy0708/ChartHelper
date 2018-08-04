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
import com.horen.chart.barchart.IBarData;
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
        List<List<IBarData>> data = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            // 单个柱状图数据
            ArrayList<IBarData> entries = new ArrayList<>();
            entries.add(new TestBarData((Math.random() * 200), "IF1040"));
            entries.add(new TestBarData((Math.random() * 200), "IF1041"));
            entries.add(new TestBarData((Math.random() * 200), "IF330"));
            entries.add(new TestBarData((Math.random() * 200), "OF330"));
            entries.add(new TestBarData((Math.random() * 200), "OF890"));
            entries.add(new TestBarData((Math.random() * 200), "IF110"));
            data.add(entries);
        }
        //颜色填充
        String[] colors = getResources().getStringArray(com.horen.chart.R.array.chart_colors);
        List<Integer> chartColors = new ArrayList<>();
        for (String color : colors) {
            chartColors.add(Color.parseColor(color));
        }
        new BarChartHelper.Builder()
                .setContext(this)
                // 柱状图
                .setBarChart(bar_chart)
                // 多柱状图
                .setBarSetData(data)
                // 单柱状图
//                .setBarData(entries)
                // 多柱状图 标签名集合
                .setLabels(names)
                // 一页X轴显示个数
                .setDisplayCount(3)
                // 标签显示隐藏
                .setLegendEnable(false)
                // 标签文字大小
                .setLegendTextSize(20)
                // 是否显示右边Y轴
                .setyAxisRightEnable(false)
                // X,Y轴是否绘制网格线
                .setDrawGridLines(false)
                // 缩放
                .setScaleEnabled(true)
                // 是否可以通过双击屏幕放大图表
                .setDoubleTapToZoomEnabled(true)
                // 柱状图描述 图表右下角
                .setDescriptionEnable(false)
                // 按比例放缩柱状图
                .setPinchZoom(true)
                // 多柱状图 每组柱之间的宽度,只在多柱状图生效
                .setGroupSpace(0.4f)
                // 单柱状图 每个柱的宽度，只在单柱状图生效
                .setBarWidth(0.3f)
                // x,y轴动画时间和类型
                .setDurationMillis(2000)
                .setEasing(Easing.EasingOption.Linear)
                // 单柱状图颜色
                .setBarColor(Color.parseColor("#0000FF"))
                // 多柱状图颜色
                .setBarColors(chartColors)
                // X轴是否显示自定义数据，在IBarData接口中定义
                .setXValueEnable(true)
                .build();
    }

    private void initSingleBarChart() {
        //模拟数据
        ArrayList<IBarData> entries = new ArrayList<>();
        entries.add(new TestBarData((Math.random() * 80), "液袋"));
        entries.add(new TestBarData((Math.random() * 80), "拉阀"));
        entries.add(new TestBarData((Math.random() * 80), "喉箍"));
        entries.add(new TestBarData((Math.random() * 80), "阀门"));
        //创建多条折线的图表
        new BarChartHelper.Builder()
                .setContext(this)
                .setBarChart(single_bar_chart)
                .setBarData(entries)
                // 一页X轴显示个数
                .setDisplayCount(4)
                // 标签显示隐藏
                .setLegendEnable(false)
                // 标签文字大小
                .setLegendTextSize(20)
                // 是否显示右边Y轴
                .setyAxisRightEnable(false)
                // X,Y轴是否绘制网格线
                .setDrawGridLines(false)
                // 缩放
                .setScaleEnabled(true)
                // 是否可以通过双击屏幕放大图表
                .setDoubleTapToZoomEnabled(true)
                // 柱状图描述 图表右下角
                .setDescriptionEnable(false)
                // 按比例放缩柱状图
                .setPinchZoom(true)
                // 多柱状图 每组柱之间的宽度,只在多柱状图生效
                .setGroupSpace(0.12f)
                // 单柱状图 每个柱的宽度，只在单柱状图生效
                .setBarWidth(0.6f)
                // x,y轴动画时间和类型
                .setDurationMillis(2000)
                .setEasing(Easing.EasingOption.Linear)
                // 单柱状图颜色
                .setBarColor(Color.parseColor("#00FF00"))
                // X轴是否显示自定义数据，在IBarData接口中定义
                .setXValueEnable(true)
                .build();
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
