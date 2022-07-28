<template>
  <div class="app-container">
    <!--查询表单开始-->
    <el-card class="operate-container" shadow="never" align="center">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="课程名称">
          <el-input v-model="queryCondition.title" placeholder="课程名称" />
        </el-form-item>

        <el-form-item label="课程状态">
          <el-select
            v-model="queryCondition.status"
            clearable
            placeholder="课程状态"
          >
            <el-option :value="1" label="已发布" />
            <el-option :value="0" label="未发布" />
          </el-select>
        </el-form-item>

        <el-form-item label="课程开始时间">
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
            @click="getCourseList()"
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

    <el-divider></el-divider>

    <!-- 数据表格 -->
    <el-table :data="courseList" border fit highlight-current-row>
      <el-table-column label="序号" width="50" align="center">
        <template slot-scope="scope">
          {{ (index - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column
        prop="title"
        label="课程名称"
        width="80"
        align="center"
      />

      <el-table-column label="课程状态" width="80" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === 1" type="success" size="medium">
            已发布</el-tag
          >
          <el-tag v-else type="primary" size="medium"> 未发布</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="teacherName"
        label="讲师姓名"
        width="85"
        align="center"
      />

      <el-table-column prop="cover" label="课程封面" width="200" align="center">
        <template slot-scope="scope">
          <img :src="scope.row.cover" width="200" />
        </template>
      </el-table-column>
      <el-table-column
        prop="lessonNum"
        label="课时数"
        width="80"
        align="center"
      />

      <el-table-column
        prop="saleCount"
        label="销售数量"
        width="85"
        align="center"
      />

      <el-table-column
        prop="viewCount"
        label="浏览数量"
        width="120"
        align="center"
      />

      <el-table-column
        prop="createTime"
        label="添加时间"
        width="160"
        align="center"
      />

      <el-table-column label="操作" width="230" align="center">
        <template slot-scope="scope">
          <router-link :to="'/course/Info/' + scope.row.id">
            <el-button
              type="primary"
              class="m-button"
              icon="el-icon-edit"
              size="medium"
              >编辑课程信息</el-button
            >
          </router-link>
          <router-link :to="'/course/chapter/' + scope.row.id">
            <el-button
              type="warning"
              class="m-button"
              icon="el-icon-edit"
              size="medium"
              >编辑课程大纲</el-button
            >
          </router-link>
          <el-button
            type="danger"
            class="m-button"
            icon="el-icon-delete"
            size="medium"
            @click="deleteCourse(scope.row.id, scope.row.title)"
            >删除课程</el-button
          >
        </template>
      </el-table-column>
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
import courseAPI from "@/api/edu/course";

export default {
  // 数据区
  data() {
    return {
      // 课程列表
      courseList: [],
      // 当前页
      index: 1,
      // 每页记录数
      limit: 10,
      // 总记录数
      total: 0,
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
    this.getCourseList();
  },
  // 方法区
  methods: {
    // 判断查询条件是否存在
    hasQueryCondition() {
      return (
        this.queryCondition.hasOwnProperty("title") ||
        this.queryCondition.hasOwnProperty("status") ||
        this.queryCondition.hasOwnProperty("beginTime") ||
        this.queryCondition.hasOwnProperty("endTime")
      );
    },
    // 获取课程列表
    getCourseList(index = 1) {
      this.index = index;
      if (this.dateRange.length > 0) {
        this.queryCondition.beginTime = this.dateRange[0];
        this.queryCondition.endTime = this.dateRange[1];
      }
      if (!this.hasQueryCondition()) {
        courseAPI.pageQueryCourse(this.index, this.limit).then((response) => {
          this.courseList = response.data.records;
          this.total = Number(response.data.total);
        });
      } else {
        courseAPI
          .pageQueryCourse(this.index, this.limit, this.queryCondition)
          .then((response) => {
            this.courseList = response.data.records;
            this.total = Number(response.data.total);
          });
      }
    },
    // 删除课程
    deleteCourse(courseId, courseTitle) {
      this.$confirm(`确定要删除课程:[ ${courseTitle} ]吗?`, "提示", {
        cancelButtonClass: "取消",
        confirmButtonText: "确定",
        type: "warning",
      }).then(() => {
        courseAPI.deleteCourse(courseId).then((response) => {
          this.$message.success("删除成功");
          // 刷新数据
          this.getCourseList();
        });
      });
    },
    // 每页记录数改变
    handleSizeChange(limit) {
      this.limit = limit;
      this.getCourseList();
    },
    // 当前页改变
    handleCurrentChange(index) {
      this.getCourseList(index);
    },
    // 重置查询条件
    resetQueryCondition() {
      this.queryCondition = {};
      this.dateRange = {};
      this.getCourseList();
    },
  },
};
</script>

<style scoped>
.m-button {
  margin-top: 5px;
  margin-bottom: 5px;
  width: 150px;
}
</style>
