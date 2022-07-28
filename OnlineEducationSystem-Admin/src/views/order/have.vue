<template>
  <div class="app-container">
    <!--查询表单开始-->
    <el-card class="operate-container" shadow="never" align="center">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="讲师姓名">
          <el-input v-model="queryCondition.teacherName" placeholder="讲师名" />
        </el-form-item>

        <el-form-item label="课程名">
          <el-input v-model="queryCondition.courseTitle" placeholder="课程名" />
        </el-form-item>

        <el-form-item label="订单产生时间">
          <el-date-picker
            v-model="dateRange"
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

        <!-- 查询表单操作开始 -->
        <div>
          <el-button
            type="primary"
            icon="el-icon-search"
            @click="getOrderList()"
            >查询</el-button
          >
          <el-button
            type="warning"
            icon="el-icon-refresh-left"
            @click="resetQueryCondition()"
            >清空</el-button
          >
        </div>
        <!-- 查询表单操作结束 -->
      </el-form>
    </el-card>
    <!-- 查询表单结束 -->

    <el-divider></el-divider>

    <!-- 数据表格 -->
    <el-table :data="orderList" border stripe>
      <!-- 序号列开始 -->
      <el-table-column label="#" width="40" align="center">
        <template slot-scope="scope">
          {{ (index - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <!-- 序号列结束 -->

      <el-table-column prop="orderNo" label="订单号" align="center" />

      <!-- 讲师姓名列开始 -->
      <el-table-column label="讲师姓名" align="center">
        <template slot-scope="scope">
          <el-tag type="success">{{ scope.row.teacherName }}</el-tag>
        </template>
      </el-table-column>
      <!-- 讲师姓名列结束 -->

      <!-- 课程名开始 -->
      <el-table-column align="center" label="课程名">
        <template slot-scope="scope">
          <el-tag type="primary">{{ scope.row.courseTitle }}</el-tag>
        </template>
      </el-table-column>
      <!-- 课程名结束 -->

      <!-- 课程封面开始 -->
      <el-table-column
        prop="courseCover"
        label="课程封面"
        width="150"
        align="center"
      >
        <!-- 动态加载url -->
        <!-- imageurl是一个数组 -->
        <template slot-scope="scope">
          <img
            :src="scope.row.courseCover"
            width="140"
            class="el-image__inner el-image__preview"
          />
        </template>
      </el-table-column>
      <!-- 课程封面结束 -->

      <!-- 会员昵称列开始 -->
      <el-table-column prop="nickname" label="会员昵称" align="center" />
      <!-- 会员昵称列结束 -->

      <!-- 订单创建时间列开始 -->
      <el-table-column
        prop="createTime"
        label="创建时间"
        width="160"
        align="center"
      />
      <!-- 订单创建时间列结束 -->
    </el-table>

    <!-- 分页插件开始 -->
    <el-pagination
      :current-page="index"
      :page-size="limit"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :page-sizes="[5, 10, 20, 30, 40, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper"
      style="padding: 30px 0; text-align: center"
    >
    </el-pagination>
    <!-- 分页插件结束 -->
    <!-- 快速回到顶部开始 -->
    <el-backtop></el-backtop>
    <!-- 快速回到顶部结束 -->
  </div>
</template>

<script>
import orderAPI from "@/api/order/order";

export default {
  // 数据区
  data() {
    return {
      // 当前页
      index: 1,
      // 每页记录数
      limit: 10,
      // 总记录条数
      total: 0,
      // 讲师列表
      orderList: [],
      // 查询条件
      queryCondition: {
        status: 1,
      },
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
  // 渲染前执行
  created() {
    this.getOrderList();
  },
  // 方法区
  methods: {
    // 判断查询条件是否存在
    hasQueryCondition() {
      return (
        this.queryCondition.hasOwnProperty("status") ||
        this.queryCondition.hasOwnProperty("teacherName") ||
        this.queryCondition.hasOwnProperty("courseTitle") ||
        this.queryCondition.hasOwnProperty("beginTime") ||
        this.queryCondition.hasOwnProperty("endTime")
      );
    },
    // 分页查询订单信息
    getOrderList(index = 1) {
      this.index = index;
      if (this.dateRange.length > 0) {
        this.queryCondition.beginTime = this.dateRange[0];
        this.queryCondition.endTime = this.dateRange[1];
      }
      if (!this.hasQueryCondition()) {
        orderAPI.pageQueryOrder(this.index, this.limit).then((response) => {
          this.orderList = response.data.records;
          this.total = Number(response.data.total);
        });
      } else {
        orderAPI
          .pageQueryOrder(this.index, this.limit, this.queryCondition)
          .then((response) => {
            this.orderList = response.data.records;
            this.total = Number(response.data.total);
          });
      }
    },
    // 每页记录数改变
    handleSizeChange(limit) {
      this.limit = limit;
      this.getOrderList();
    },
    // 当前页改变
    handleCurrentChange(index) {
      this.getOrderList(index);
    },
    // 重置查询条件
    resetQueryCondition() {
      this.queryCondition = { status: 1 };
      this.dateRange = {};
      this.getOrderList();
    },
  },
};
</script>

<style scoped></style>
