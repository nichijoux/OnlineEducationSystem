<template>
  <div class="app-container">
    <!--查询表单开始-->
    <el-card class="operate-container" shadow="never" align="center">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="名称">
          <el-input v-model="queryCondition.name" placeholder="讲师名" />
        </el-form-item>

        <el-form-item label="头衔">
          <el-select
            v-model="queryCondition.level"
            clearable
            placeholder="头衔"
          >
            <el-option value="1" label="高级讲师" />
            <el-option value="0" label="首席讲师" />
          </el-select>
        </el-form-item>

        <el-form-item label="入驻时间">
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
            @click="getTeacherList()"
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
    <el-table :data="teacherList" border stripe>
      <!-- 序号列开始 -->
      <el-table-column label="#" width="40" align="center">
        <template slot-scope="scope">
          {{ (index - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <!-- 序号列结束 -->

      <el-table-column prop="name" label="名称" width="80" align="center" />

      <!-- 头衔列开始 -->
      <el-table-column label="头衔" width="80" align="center">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.level === 1" type="success" size="medium"
            >高级讲师</el-tag
          >
          <el-tag v-if="scope.row.level === 2" size="medium">首席讲师</el-tag>
        </template>
      </el-table-column>
      <!-- 头衔列结束 -->

      <!-- 头像列开始 -->
      <el-table-column prop="avatar" label="头像" width="150" align="center">
        <!-- 动态加载url -->
        <!-- imageurl是一个数组 -->
        <template slot-scope="scope">
          <img
            :src="scope.row.avatar"
            width="130"
            height="130"
            class="el-image__inner el-image__preview"
          />
        </template>
      </el-table-column>
      <!-- 头像列结束 -->

      <!-- 资历列开始 -->
      <el-table-column prop="career" label="资历" width="80" align="center" />
      <!-- 资历列结束 -->

      <!-- 简介列开始 -->
      <el-table-column prop="intro" label="简介" align="center" />
      <!-- 简介列结束 -->

      <!-- 排序列开始 -->
      <el-table-column prop="sort" label="排序" width="50" align="center" />
      <!-- 排序列结束 -->

      <!-- 入驻时间列开始 -->
      <el-table-column
        prop="createTime"
        label="入驻时间"
        width="160"
        align="center"
      />
      <!-- 入驻时间列结束 -->

      <!-- 对讲师修改、删除开始 -->
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/teacher/edit/' + scope.row.id">
            <el-button type="primary" icon="el-icon-edit" size="small" round
              >修改</el-button
            >
          </router-link>

          <el-button
            type="danger"
            icon="el-icon-delete"
            @click="deleteTeacher(scope.row.id, scope.row.name)"
            size="small"
            round
            >删除</el-button
          >
        </template>
      </el-table-column>
      <!-- 对讲师修改、删除结束 -->
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
import teacherAPI from "@/api/edu/teacher";

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
      teacherList: [],
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
    // 查询
    this.getTeacherList();
  },
  // 方法区
  methods: {
    // 判断查询条件是否存在
    hasQueryCondition() {
      return (
        this.queryCondition.hasOwnProperty("title") ||
        this.queryCondition.hasOwnProperty("level") ||
        this.queryCondition.hasOwnProperty("beginTime") ||
        this.queryCondition.hasOwnProperty("endTime")
      );
    },
    // 获取讲师列表
    getTeacherList(index = 1) {
      this.index = index;
      if (this.dateRange.length > 0) {
        this.queryCondition.beginTime = this.dateRange[0];
        this.queryCondition.endTime = this.dateRange[1];
      }
      if (!this.hasQueryCondition()) {
        teacherAPI.pageQueryTeacher(this.index, this.limit).then((response) => {
          this.teacherList = response.data.records;
          this.total = Number(response.data.total);
        });
      } else {
        teacherAPI
          .pageQueryTeacher(this.index, this.limit, this.queryCondition)
          .then((response) => {
            this.teacherList = response.data.records;
            this.total = Number(response.data.total);
          });
      }
    },
    // 删除讲师
    deleteTeacher(teacherId, teacherName) {
      this.$confirm(`确定要删除[ ${teacherName} ]吗?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        // 调用接口删除
        teacherAPI.deleteTeacher(teacherId).then((response) => {
          // 提示
          const messageBox = this.$createElement;

          this.$notify({
            message: messageBox("i", { style: "color: teal" }, "删除成功"),
            duration: 1500,
          });
          // 刷新
          this.getTeacherList();
        });
      });
    },
    // 每页记录数改变
    handleSizeChange(limit) {
      this.limit = limit;
      this.getTeacherList();
    },
    // 当前页改变
    handleCurrentChange(index) {
      this.getTeacherList(index);
    },
    // 重置查询条件
    resetQueryCondition() {
      this.queryCondition = {};
      this.dateRange = {};
      this.getTeacherList();
    },
  },
};
</script>

<style scoped></style>
