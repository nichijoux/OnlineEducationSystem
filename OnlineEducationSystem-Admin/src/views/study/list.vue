<template>
  <div class="app-container">
    <!--查询表单开始-->
    <el-card class="operate-container" shadow="never" align="center">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="用户昵称">
          <el-input v-model="queryCondition.nickname" placeholder="用户昵称" />
        </el-form-item>

        <el-form-item label="课程名称">
          <el-input
            v-model="queryCondition.courseName"
            placeholder="课程名称"
          />
        </el-form-item>

        <el-form-item label="学习时间">
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
            @click="getStudyRecordList()"
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

    <!-- 数据表格 -->
    <el-table :data="studyRecordList" border stripe>
      <!-- 序号列开始 -->
      <el-table-column label="#" width="40" align="center">
        <template slot-scope="scope">
          {{ (index - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <!-- 序号列结束 -->

      <!-- 用户昵称开始 -->
      <el-table-column
        prop="nickname"
        label="用户昵称"
        width="80"
        align="center"
      />
      <!-- 用户昵称结束 -->

          <!-- 课程封面列开始 -->
      <el-table-column
        prop="courseCover"
        label="课程封面"
        width="200"
        align="center"
      >
        <!-- 动态加载url -->
        <!-- imageurl是一个数组 -->
        <template slot-scope="scope">
          <img
            :src="scope.row.courseCover"
            width="180"
            height="170"
            class="el-image__inner el-image__preview"
          />
        </template>
      </el-table-column>
      <!-- 课程封面列结束 -->

      <el-table-column prop="courseName" label="课程名" align="center" />

      <el-table-column prop="videoName" label="小节名" align="center" />

      <el-table-column prop="teacherName" label="讲师姓名" align="center" />

      <!-- 学习时间列开始 -->
      <el-table-column
        prop="createTime"
        label="学习时间"
        align="center"
      />
      <!-- 学习时间列结束 -->
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
import recordAPI from "@/api/edu/record";

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
      // 学习列表
      studyRecordList: [],
      // 查询条件
      queryCondition: {},
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
    this.getStudyRecordList();
  },
  // 方法区
  methods: {
    // 判断查询条件是否存在
    hasQueryCondition() {
      return (
        this.queryCondition.hasOwnProperty("nickname") ||
        this.queryCondition.hasOwnProperty("courseName") ||
        this.queryCondition.hasOwnProperty("beginTime") ||
        this.queryCondition.hasOwnProperty("endTime")
      );
    },
    // 查询学习记录
    getStudyRecordList(index = 1) {
      this.index = index;
      if (this.dateRange.length > 0) {
        this.queryCondition.beginTime = this.dateRange[0];
        this.queryCondition.endTime = this.dateRange[1];
      }
      if (!this.hasQueryCondition()) {
        recordAPI
          .pageQueryStudyRecord(this.index, this.limit)
          .then((response) => {
            this.studyRecordList = response.data.records;
            this.total = Number(response.data.total);
          });
      } else {
        recordAPI
          .pageQueryStudyRecord(this.index, this.limit, this.queryCondition)
          .then((response) => {
            this.studyRecordList = response.data.records;
            this.total = Number(response.data.total);
          });
      }
    },

    // 每页记录数改变
    handleSizeChange(limit) {
      this.limit = limit;
      this.getStudyRecordList();
    },
    // 当前页改变
    handleCurrentChange(index) {
      this.getStudyRecordList(index);
    },
    // 重置查询条件
    resetQueryCondition() {
      this.queryCondition = {};
      this.dateRange = {};
      this.getStudyRecordList();
    },
  },
};
</script>

<style scoped></style>
