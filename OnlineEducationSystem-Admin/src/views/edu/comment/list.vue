<template>
  <div class="app-container">
    <!--查询表单开始-->
    <el-card class="operate-container" shadow="never" align="center">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="名称">
          <el-input v-model="queryCondition.nickname" placeholder="用户昵称" />
        </el-form-item>

        <el-form-item label="评论星级">
          <el-select
            v-model="queryCondition.star"
            clearable
            placeholder="评论星级"
          >
            <el-option value="1" label="一星" />
            <el-option value="2" label="二星" />
            <el-option value="3" label="三星" />
            <el-option value="4" label="四星" />
            <el-option value="5" label="五星" />
          </el-select>
        </el-form-item>

        <el-form-item label="评论时间">
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
            @click="getCommentList()"
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

    <!-- 评论数据表单 -->
    <el-table :data="commentList" border stripe>
      <!-- 序号列开始 -->
      <el-table-column label="#" width="40" align="center">
        <template slot-scope="scope">
          {{ (index - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <!-- 序号列结束 -->

      <el-table-column prop="nickname" label="用户昵称" width="130" align="center" />

      <!-- 评论课程 -->
      <el-table-column prop="courseName" label="评论课程" align="center" />

      <!-- 课程封面列开始 -->
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
            width="130"
            height="100"
            class="el-image__inner el-image__preview"
          />
        </template>
      </el-table-column>
      <!-- 课程封面列结束 -->

      <!-- 评论内容 -->
      <el-table-column prop="content" label="评论内容" align="center" />

      <!-- 评论星级 -->
      <el-table-column label="评论星级" align="center">
        <template slot-scope="scope">
          <el-rate v-model="scope.row.star" :colors="colors" disabled show-text>
          </el-rate>
        </template>
      </el-table-column>

      <!-- 评论时间 -->
      <el-table-column prop="createTime" label="评论时间" align="center" />

      <!-- 操作 -->
      <el-table-column label="操作" width="150" align="center">
        <template slot-scope="scope">
          <el-button
            type="danger"
            icon="el-icon-delete"
            @click="deleteComment(scope.row.id)"
            round
            >删除
          </el-button>
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
import commentAPI from "@/api/edu/comment";

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
      // 评论列表
      commentList: [],
      // 评论星级颜色
      colors: ["#99A9BF", "#F7BA2A", "#FF9900"],
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
    this.getCommentList();
  },
  // 方法区
  methods: {
    // 判断查询条件是否存在
    hasQueryCondition() {
      return (
        this.queryCondition.hasOwnProperty("nickname") ||
        this.queryCondition.hasOwnProperty("star") ||
        this.queryCondition.hasOwnProperty("beginTime") ||
        this.queryCondition.hasOwnProperty("endTime")
      );
    },
    // 获取评论列表
    getCommentList(index = 1) {
      this.index = index;
      if (this.dateRange.length > 0) {
        this.queryCondition.beginTime = this.dateRange[0];
        this.queryCondition.endTime = this.dateRange[1];
      }
      if (!this.hasQueryCondition()) {
        commentAPI.pageQueryComment(this.index, this.limit).then((response) => {
          this.commentList = response.data.records;
          this.total = Number(response.data.total);
        });
      } else {
        commentAPI
          .pageQueryComment(this.index, this.limit, this.queryCondition)
          .then((response) => {
            this.commentList = response.data.records;
            this.total = Number(response.data.total);
          });
      }
    },
    // 删除评论
    deleteComment(commentId) {
      this.$confirm("此操作将删除该评论,是否继续", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        // 调用API进行删除
        commentAPI.deleteComment(commentId).then((response) => {
          this.$message.success("删除评论成功");
          this.getCommentList();
        });
      });
    },
    // 每页记录数改变
    handleSizeChange(limit) {
      this.limit = limit;
      this.getCommentList();
    },
    // 当前页改变
    handleCurrentChange(index) {
      this.getCommentList(index);
    },
    // 重置查询条件
    resetQueryCondition() {
      this.queryCondition = {};
      this.dateRange = {};
      this.getCommentList();
    },
  },
};
</script>

<style scoped></style>
