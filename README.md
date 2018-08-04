# ChartHelper
MPAndroidChart

## 1. 饼状图

![饼图](https://github.com/chenyy0708/ChartHelper/blob/master/img/%E9%A5%BC%E5%9B%BE.png)

### 属性

```
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
```


![示例图1](https://github.com/chenyy0708/ChartHelper/blob/master/img/%E7%A4%BA%E4%BE%8B%E5%9B%BE.png)

![示例图1](https://github.com/chenyy0708/ChartHelper/blob/master/img/%E7%A4%BA%E4%BE%8B%E5%9B%BE2.png)
