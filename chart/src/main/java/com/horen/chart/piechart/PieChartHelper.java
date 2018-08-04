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

    private PieChart pieChart;

    private Context mContext;
    /**
     * 饼状图数据
     */
    private List<IPieData> pieData;
    /**
     * 饼状图是否显示百分比
     */
    private boolean mUsePercentValues;
    /**
     * 描述
     */
    private boolean mDescriptionEnable;
    /**
     * 旋转角度
     */
    private float mRawRotationAngle;
    /**
     * 点击高亮
     */
    private boolean mHighLightPerTapEnabled;
    /**
     * 开启旋转
     */
    private boolean mRotateEnabled;
    /**
     * 中心空白圆心大小
     */
    private float mHoleRadiusPercent;
    /**
     * 设置饼图上面的文字是否显示，不显示的话只显示百分比
     */
    private boolean mDrawEntryLabels;
    /**
     * 标签
     */
    private boolean mLegendEnable;
    /**
     * 中心图表孔旁边的透明圆的半径
     */
    private float mTransparentCircleRadiusPercent;
    /**
     * 动画时间
     */
    private int durationMillis;
    /**
     * 动画类型
     */
    private Easing.EasingOption easing;
    /**
     * Label文字颜色
     */
    private int lableTextColor;
    /**
     * 文字大小
     */
    private int lableTextSize;
    /**
     * 是否隐藏饼状图百分比
     */
    private boolean percentEnable;
    /**
     * 饼图区块之间的距离
     */
    private float mSliceSpace;
    /**
     * 饼图Item被选中时变化的距离
     */
    private float selectionShift;
    /**
     * 标题
     */
    private String mTitle;
    /**
     * 颜色集合
     */
    private List<Integer> mPieColors;

    private PieChartHelper(Builder builder) {
        this.mContext = builder.mContext;
        this.pieData = builder.pieData;
        this.mUsePercentValues = builder.mUsePercentValues;
        this.mDescriptionEnable = builder.mDescriptionEnable;
        this.mRawRotationAngle = builder.mRawRotationAngle;
        this.mHighLightPerTapEnabled = builder.mHighLightPerTapEnabled;
        this.mRotateEnabled = builder.mRotateEnabled;
        this.mHoleRadiusPercent = builder.mHoleRadiusPercent;
        this.mDrawEntryLabels = builder.mDrawEntryLabels;
        this.mLegendEnable = builder.mLegendEnable;
        this.mTransparentCircleRadiusPercent = builder.mTransparentCircleRadiusPercent;
        this.durationMillis = builder.durationMillis;
        this.easing = builder.easing;
        this.lableTextColor = builder.lableTextColor;
        this.lableTextSize = builder.lableTextSize;
        this.percentEnable = builder.percentEnable;
        this.selectionShift = builder.selectionShift;
        this.mPieColors = builder.mPieColors;
        this.pieChart = builder.pieChart;
        this.mTitle = builder.mTitle;
        this.mSliceSpace = builder.mSliceSpace;
        initPieChart();
    }

    /**
     * 初始化饼状图
     */
    private void initPieChart() {
        //设置为TRUE的话，图标中的数据自动变为percent
        pieChart.setUsePercentValues(percentEnable);
        pieChart.getDescription().setEnabled(mDescriptionEnable);
        pieChart.setRotationAngle(mRawRotationAngle);
        pieChart.setRotationEnabled(mRotateEnabled);
        pieChart.setHighlightPerTapEnabled(mHighLightPerTapEnabled);
        // 中心空白圆心大小
        pieChart.setHoleRadius(mHoleRadiusPercent);
        pieChart.setDrawEntryLabels(mDrawEntryLabels);
        // 中心图表孔旁边的透明圆的半径
        pieChart.setTransparentCircleRadius(mTransparentCircleRadiusPercent);
        pieChart.animateY(durationMillis, easing);
        Legend mLegend = pieChart.getLegend();
        //设置禁用比例块
        mLegend.setEnabled(mLegendEnable);
        // 初始化图表数据
        setData();
    }

    private void setData() {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        for (IPieData entry : pieData) {
            pieEntries.add(new PieEntry(entry.getValue(), entry.getLabelName()));
        }
        //饼图数据集
        PieDataSet dataSet = new PieDataSet(pieEntries, mTitle);
        // 百分比
        dataSet.setValueFormatter(new PercentFormatter());
        dataSet.setValueTextColor(lableTextColor);
        dataSet.setValueTextSize(lableTextSize);
        // 不显示百分比
        if (!percentEnable) {
            dataSet.setValueTextColor(Color.parseColor("#00000000"));
        }
        //饼图区块之间的距离
        dataSet.setSliceSpace(mSliceSpace);
        //饼图Item被选中时变化的距离
        dataSet.setSelectionShift(selectionShift);
        dataSet.setColors(mPieColors);
        //数据填充
        PieData piedata = new PieData(dataSet);
        pieChart.setData(piedata);
        pieChart.invalidate();
    }


    public static class Builder {

        public Builder() {
        }

        private PieChart pieChart;

        private Context mContext;
        /**
         * 饼状图数据
         */
        private List<IPieData> pieData;
        /**
         * 饼状图是否显示百分比
         */
        private boolean mUsePercentValues = false;
        /**
         * 描述
         */
        private boolean mDescriptionEnable = false;
        /**
         * 旋转角度
         */
        private float mRawRotationAngle = 0f;
        /**
         * 点击高亮
         */
        private boolean mHighLightPerTapEnabled = true;
        /**
         * 开启旋转
         */
        private boolean mRotateEnabled = true;
        /**
         * 中心空白圆心大小
         */
        private float mHoleRadiusPercent = 50f;
        /**
         * 设置饼图上面的文字是否显示，不显示的话只显示百分比
         */
        private boolean mDrawEntryLabels = false;
        /**
         * 标签
         */
        private boolean mLegendEnable = true;
        /**
         * 中心图表孔旁边的透明圆的半径
         */
        private float mTransparentCircleRadiusPercent = 10f;
        /**
         * 动画时间
         */
        private int durationMillis = 500;
        /**
         * 动画类型
         */
        private Easing.EasingOption easing = Easing.EasingOption.EaseInOutQuad;
        /**
         * Label文字颜色
         */
        private int lableTextColor = Color.WHITE;
        /**
         * 文字大小
         */
        private int lableTextSize = 12;
        /**
         * 是否隐藏饼状图百分比
         */
        private boolean percentEnable = true;
        /**
         * 饼图区块之间的距离
         */
        private float mSliceSpace = 0f;
        /**
         * 饼图Item被选中时变化的距离
         */
        private float selectionShift = 2f;
        /**
         * 颜色集合
         */
        private List<Integer> mPieColors;
        /**
         * 标题
         */
        private String mTitle;

        public Builder setContext(Context mContext) {
            this.mContext = mContext;
            return this;
        }

        public Builder setPieData(List<IPieData> pieData) {
            this.pieData = pieData;
            return this;
        }

        public Builder setUsePercentValues(boolean mUsePercentValues) {
            this.mUsePercentValues = mUsePercentValues;
            return this;
        }

        public Builder setDescriptionEnable(boolean mDescriptionEnable) {
            this.mDescriptionEnable = mDescriptionEnable;
            return this;
        }

        public Builder setRawRotationAngle(float mRawRotationAngle) {
            this.mRawRotationAngle = mRawRotationAngle;
            return this;
        }

        public Builder setHighLightPerTapEnabled(boolean mHighLightPerTapEnabled) {
            this.mHighLightPerTapEnabled = mHighLightPerTapEnabled;
            return this;
        }

        public Builder setRotateEnabled(boolean mRotateEnabled) {
            this.mRotateEnabled = mRotateEnabled;
            return this;
        }

        public Builder setHoleRadiusPercent(float mHoleRadiusPercent) {
            this.mHoleRadiusPercent = mHoleRadiusPercent;
            return this;
        }

        public Builder setDrawEntryLabels(boolean mDrawEntryLabels) {
            this.mDrawEntryLabels = mDrawEntryLabels;
            return this;
        }

        public Builder setLegendEnable(boolean mLegendEnable) {
            this.mLegendEnable = mLegendEnable;
            return this;
        }

        public Builder setTransparentCircleRadiusPercent(float mTransparentCircleRadiusPercent) {
            this.mTransparentCircleRadiusPercent = mTransparentCircleRadiusPercent;
            return this;
        }

        public Builder setDurationMillis(int durationMillis) {
            this.durationMillis = durationMillis;
            return this;
        }

        public Builder setEasing(Easing.EasingOption easing) {
            this.easing = easing;
            return this;
        }

        public Builder setLableTextColor(int lableTextColor) {
            this.lableTextColor = lableTextColor;
            return this;
        }

        public Builder setLableTextSize(int lableTextSize) {
            this.lableTextSize = lableTextSize;
            return this;
        }

        public Builder setPercentEnable(boolean percentEnable) {
            this.percentEnable = percentEnable;
            return this;
        }

        public Builder setSliceSpace(float mSliceSpace) {
            this.mSliceSpace = mSliceSpace;
            return this;
        }

        public Builder setSelectionShift(float selectionShift) {
            this.selectionShift = selectionShift;
            return this;
        }

        public Builder setPieColors(List<Integer> mPieColors) {
            this.mPieColors = mPieColors;
            return this;
        }

        public Builder setPieChart(PieChart pieChart) {
            this.pieChart = pieChart;
            return this;
        }

        public Builder setTitle(String mTitle) {
            this.mTitle = mTitle;
            return this;
        }

        public void build() {
            if (mPieColors == null) {
                //颜色填充
                String[] colors = mContext.getResources().getStringArray(R.array.chart_colors);
                List<Integer> chartColors = new ArrayList<Integer>();
                for (String color : colors) {
                    chartColors.add(Color.parseColor(color));
                }
                mPieColors = chartColors;
            }
            new PieChartHelper(this);
        }
    }
}
