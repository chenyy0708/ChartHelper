# ChartHelper
## MPAndroidChart辅助类，减少初始化属性和设置数据等操作。

[![](https://jitpack.io/v/chenyy0708/ChartHelper.svg)](https://jitpack.io/#chenyy0708/ChartHelper)

## 导入地址
`implementation 'com.github.chenyy0708:DMvp:lastVersion'`


![示例图1](https://github.com/chenyy0708/ChartHelper/blob/master/img/%E7%A4%BA%E4%BE%8B%E5%9B%BE.png)

![示例图1](https://github.com/chenyy0708/ChartHelper/blob/master/img/%E7%A4%BA%E4%BE%8B%E5%9B%BE2.png)




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

> 数据bean只需要实现IPieData接口

```

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

```


## 2.柱状图

![柱状图](https://github.com/chenyy0708/ChartHelper/blob/master/img/%E5%8D%95%E6%9F%B1%E7%8A%B6%E5%9B%BE%E5%92%8C%E5%A4%9A%E6%9F%B1%E7%8A%B6%E5%9B%BE.png)

### 属性

```
new BarChartHelper.Builder()
                .setContext(this)
                // 柱状图
                .setBarChart(bar_chart)
                // 多柱状图
                .setBarSetData(data)
                // 单柱状图
                .setBarData(data.get(0))
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
```


> 数据bean只需要实现IBarData接口

```
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
```

