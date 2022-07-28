<template>
  <div class="app-container">
    <div class="m-center">
      <!--表单-->
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <el-select v-model="queryType" clearable placeholder="请选择">
            <el-option label="学员登录数统计" value="login_num" />
            <el-option label="学员注册数统计" value="register_num" />
            <el-option label="课程播放数统计" value="video_view_num" />
            <el-option label="每日课程数统计" value="course_num" />
          </el-select>
        </el-form-item>

        <el-form-item label="查询时间">
          <el-date-picker
            v-model="dateRange"
            value-format="yyyy-MM-dd"
            type="datetimerange"
            align="right"
            unlink-panels
            range-separator="至"
            start-placeholder="开始月份"
            end-placeholder="结束月份"
            :picker-options="pickerOptions"
            clearable
          >
          </el-date-picker>
        </el-form-item>

        <el-button type="primary" icon="el-icon-search" @click="showChart()"
          >查询</el-button
        >
      </el-form>
    </div>

    <div class="chart-container">
      <div id="chart" class="chart" style="height: 500px; width: 100%" />
    </div>
  </div>
</template>
<script>
import * as echarts from "echarts";
import statisticsAPI from "@/api/statistics/daily";

export default {
  data() {
    return {
      xData: [],
      yData: [],
      // 查询条件类型
      queryType: "",
      // 时间范围
      dateRange: [],
      // 时间选择器的快速选择
      pickerOptions: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            },
          },
        ],
      },
    };
  },
  methods: {
    showChart() {
      if (this.queryType == "") {
        this.$message.warning("请选择类型");
        return;
      }
      if (this.dateRange.length < 2) {
        this.$message.warning("请选择日期");
        return;
      }
      const type = this.queryType;
      const begin = this.dateRange[0];
      console.log(begin)
      const end = this.dateRange[1];
      statisticsAPI.getStatisticsData(type, begin, end).then((response) => {
        this.yData = response.data.countList;
        this.xData = response.data.dateList;
        //调用下面生成图表的方法，改变值
        this.setChart();
      });
    },
    setChart() {
      // 基于准备好的dom，初始化echarts实例
      this.chart = echarts.init(document.getElementById("chart"));
      // console.log(this.chart)

      // 指定图表的配置项和数据
      var option = {
        title: {
          text: "数据统计",
        },
        tooltip: {
          trigger: "axis",
        },
        dataZoom: [
          {
            show: true,
            height: 30,
            xAxisIndex: [0],
            bottom: 30,
            start: 10,
            end: 80,
            handleIcon:
              "path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z",
            handleSize: "110%",
            handleStyle: {
              color: "#d3dee5",
            },
            textStyle: {
              color: "#fff",
            },
            borderColor: "#90979c",
          },
          {
            type: "inside",
            show: true,
            height: 15,
            start: 1,
            end: 35,
          },
        ],
        // x轴是类目轴（离散数据）,必须通过data设置类目数据
        xAxis: {
          type: "category",
          data: this.xData,
        },
        // y轴是数据轴（连续数据）
        yAxis: {
          type: "value",
        },
        // 系列列表。每个系列通过 type 决定自己的图表类型
        series: [
          {
            // 系列中的数据内容数组
            data: this.yData,
            // 折线图
            type: "line",
          },
        ],
      };

      this.chart.setOption(option);
    },
  },
};
</script>

<style scoped>
.m-center {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
