<template>
  <div class="app-container">
    <!--查询表单开始-->
    <el-card class="operate-container" shadow="never" align="center">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="banner标题">
          <el-input v-model="queryCondition.title" placeholder="banner标题" />
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
            @click="getBannerList()"
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

    <!-- banner数据开始 -->
    <el-table :data="bannerList" border fit highlight-current-row>
      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">
          {{ (index - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="title" label="图片标题" align="center" />
      <el-table-column prop="imageUrl" label="图片" width="160" align="center">
        <!-- 动态加载url -->
        <!-- imageurl是一个数组 -->
        <template slot-scope="scope">
          <img :src="scope.row.imageUrl" width="150" />
        </template>
      </el-table-column>

      <el-table-column prop="linkUrl" label="点击后跳转页面" align="center" />

      <el-table-column prop="createTime" label="添加时间" align="center" />
      <el-table-column prop="updateTime" label="更新时间" align="center" />

      <el-table-column prop="sort" label="排序" width="80" align="center" />

      <el-table-column label="是否启用" align="center">
        <template slot-scope="scope">
          <el-switch
            style="display: block"
            v-model="scope.row.isEnable"
            active-color="#13ce66"
            inactive-color="#ff4949"
            active-text="启用"
            inactive-text="禁用"
            @change="enableOrDisableBanner(scope.row.id, $event)"
          >
          </el-switch>
        </template>
      </el-table-column>

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/banner/edit/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit" round
              >修改</el-button
            >
          </router-link>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            round
            @click="deleteBanner(scope.row.id, scope.row.title)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- banner数据结束 -->

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
import bannerAPI from "@/api/cms/banner";

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
      // banner列表
      bannerList: [],
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
    this.getBannerList();
  },
  // 方法区
  methods: {
    // 判断查询条件是否存在
    hasQueryCondition() {
      return (
        this.queryCondition.hasOwnProperty("title") ||
        this.queryCondition.hasOwnProperty("beginTime") ||
        this.queryCondition.hasOwnProperty("endTime")
      );
    },
    // 获取banner列表
    getBannerList(index = 1) {
      this.index = index;
      if (this.dateRange.length > 0) {
        this.queryCondition.beginTime = this.dateRange[0];
        this.queryCondition.endTime = this.dateRange[1];
      }
      // 调用API进行查询
      if (!this.hasQueryCondition()) {
        bannerAPI.pageQueryBanner(this.index, this.limit).then((response) => {
          this.bannerList = response.data.records;
          this.total = Number(response.data.total);
        });
      } else {
        bannerAPI
          .pageQueryBanner(this.index, this.limit, this.queryCondition)
          .then((response) => {
            this.bannerList = response.data.records;
            this.total = Number(response.data.total);
          });
      }
    },
    // 启用或禁用banner
    enableOrDisableBanner(bannerId, isEnable) {
      bannerAPI.enableOrDisableBanner(bannerId, isEnable).then((response) => {
        this.$message.success(isEnable ? "启用成功" : "禁用成功");
      });
    },
    // 删除banner
    deleteBanner(bannerId, bannerTitle) {
      this.$confirm(`确定要删除banner[ ${bannerTitle} ]吗？`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        bannerAPI.deleteBanner(bannerId).then((response) => {
          this.$message.success("删除成功");
          // 刷新数据
          this.getBannerList();
        });
      });
    },
    // 每页记录数改变
    handleSizeChange(limit) {
      this.limit = limit;
      this.getBannerList();
    },
    // 当前页改变
    handleCurrentChange(index) {
      this.getBannerList(index);
    },
    // 重置查询条件
    resetQueryCondition() {
      this.queryCondition = {};
      this.dateRange = {};
      this.getBannerList();
    },
  },
};
</script>

<style scoped></style>
