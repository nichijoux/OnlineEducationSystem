<template>
  <div class="app-container">
    <!-- 查询表单开始 -->
    <el-card class="operate-container" shadow="never" align="center">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="用户名">
          <el-input v-model="queryCondition.name" placeholder="用户名" />
        </el-form-item>

        <el-form-item label="创建时间">
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
          <el-button type="primary" icon="el-icon-search" @click="getUserList()"
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

    <!-- 工具条 -->
    <div class="m-center">
      <el-button
        type="success"
        class="user-button"
        @click="openUserInfoDialog()"
        >添加用户</el-button
      >
      <el-button type="danger" class="user-button" @click="batchDeleteUser()"
        >批量删除</el-button
      >
    </div>

    <el-divider></el-divider>

    <!-- 用户列表数据开始 -->
    <el-table
      v-loading="dataLoading"
      :data="userList"
      stripe
      border
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" />

      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">
          {{ (index - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="username" label="用户名" align="center" />

      <!-- 是否启用开始 -->
      <el-table-column label="是否启用" align="center">
        <template slot-scope="scope">
          <el-switch
            style="display: block"
            v-model="scope.row.isEnable"
            active-color="#13ce66"
            inactive-color="#ff4949"
            active-text="启用"
            inactive-text="禁用"
            @change="enableOrDisableUser(scope.row.id, $event)"
          ></el-switch>
        </template>
      </el-table-column>
      <!-- 是否启用结束 -->

      <el-table-column label="操作" width="360px" align="center">
        <template slot-scope="scope">
          <el-button
            type="warning"
            size="mini"
            icon="el-icon-setting"
            @click="() => openUserRoleDialog(scope.row)"
            >分配角色</el-button
          >
          <!-- 编辑角色按钮 -->
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-edit"
            @click="() => openUserInfoDialog(scope.row)"
            >编辑用户</el-button
          >
          <!-- 删除角色按钮 -->
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="deleteUser(scope.row.id, scope.row.username)"
            >删除用户</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- 用户列表数据结束 -->

    <!-- 用户信息修改对话框开始 -->
    <el-dialog
      :visible.sync="userInfoDialogVisible"
      :title="userInfoDialogTitle"
      ><el-form
        :model="userInfo"
        :rules="userInfoRules"
        ref="userInfoForm"
        label-width="120px"
      >
        <el-form-item label="用户名" prop="username">
          <el-input v-model="userInfo.username" />
        </el-form-item>
        <el-form-item label="用户密码" prop="password">
          <el-input v-model="userInfo.password" />
        </el-form-item>
      </el-form>
      <!-- 保存和取消按钮 -->
      <div slot="footer" class="dialog-footer m-center">
        <el-button type="primary" @click="addOrUpdateUserInfo()"
          >确定</el-button
        >
        <el-button type="warning" @click="resetUserInfo()">取消</el-button>
      </div>
    </el-dialog>
    <!-- 用户信息修改对话框结束 -->

    <!-- 用户角色分配对话框开始 -->
    <el-dialog :visible.sync="userRoleDialogVisible" title="角色分配">
      <div class>
        <el-checkbox
          :indeterminate="isIndeterminate"
          v-model="checkAll"
          @change="handleCheckAllChange"
          >全选</el-checkbox
        >
        <div style="margin: 15px 0"></div>
        <el-checkbox-group
          v-model="checkedRoles"
          @change="handleCheckedRolesChange"
        >
          <el-checkbox v-for="role in roles" :label="role.id" :key="role.id">{{
            role.roleName
          }}</el-checkbox>
        </el-checkbox-group>
        <br />
        <div class="m-center">
          <el-button type="primary" @click="updateUserRole()">保存</el-button>
        </div>
      </div>
    </el-dialog>
    <!-- 用户角色分配对话框结束 -->

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
import userAPI from "@/api/auth/user";

// 用户信息表单
const userInfoForm = {
  username: "",
  password: "",
};

export default {
  // 数据区
  data() {
    return {
      // 数据是否正在加载
      dataLoading: true,
      // 当前页
      index: 1,
      // 每页记录数
      limit: 10,
      // 总记录条数
      total: 0,
      // 用户列表
      userList: [],
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
      // 批量选择中选择的记录列表
      multipleSelection: [],

      // 用户信息修改部分
      // 编辑用户信息的对象
      userInfo: { ...userInfoForm },
      // 编辑用户信息对话框标题和显示
      userInfoDialogVisible: false,
      userInfoDialogTitle: "",
      // 用户信息表单规则
      userInfoRules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 3, message: "密码至少为6个字符", trigger: "blur" },
        ],
      },

      // 用户角色分配部分
      // 用户角色对话框显示
      userRoleDialogVisible: false,
      // 用户角色
      userId: "",
      // 是否全部选中
      checkAll: false,
      // 选中的角色
      checkedRoles: [],
      // 所有的角色
      roles: [],
      // 该作用未知
      isIndeterminate: true,
    };
  },
  // 渲染前执行
  created() {
    this.getUserList();
  },
  // 方法区
  methods: {
    // 判断查询条件是否存在
    hasQueryCondition() {
      return (
        this.queryCondition.hasOwnProperty("name") ||
        this.queryCondition.hasOwnProperty("beginTime") ||
        this.queryCondition.hasOwnProperty("endTime")
      );
    },
    // 获取用户列表
    getUserList(index = 1) {
      this.index = index;
      if (this.dateRange.length > 0) {
        this.queryCondition.beginTime = this.dateRange[0];
        this.queryCondition.endTime = this.dateRange[1];
      }
      if (!this.hasQueryCondition()) {
        userAPI.pageQueryUser(this.index, this.limit).then((response) => {
          this.userList = response.data.records;
          this.total = Number(response.data.total);
          // 数据加载并绑定成功
          this.dataLoading = false;
        });
      } else {
        userAPI
          .pageQueryUser(this.index, this.limit, this.queryCondition)
          .then((response) => {
            this.userList = response.data.records;
            this.total = Number(response.data.total);
            // 数据加载并绑定成功
            this.dataLoading = false;
          });
      }
    },
    // 删除用户
    deleteUser(userId, username) {
      this.$confirm(`确定要删除用户[ ${username} ]吗?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        userAPI.deleteUser(userId).then((response) => {
          this.$message.success("删除成功");
          this.getUserList();
        });
      });
    },
    // 批量删除用户
    batchDeleteUser() {
      if (this.multipleSelection.length === 0) {
        this.$message.warning("请选择要删除的记录");
        return;
      }
      this.$confirm("此操作将永久删除这些用户, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          // 遍历selecttion将id去除放入id列表
          let idList = [];
          this.multipleSelection.forEach((item) => {
            idList.push(item.id);
          });
          // 调用API
          userAPI.batchDeleteUser(idList).then((response) => {
            this.$message.success("删除成功");
            // 刷新用户数据
            this.getUserList();
          });
        })
        .catch(() => {
          this.$message.info("已取消删除");
        });
    },

    // 启用或者禁用用户
    enableOrDisableUser(userId, isEnable) {
      //TODO
    },
    // 添加用户信息
    addUserInfo(userInfo) {
      userAPI
        .addUser(userInfo)
        .then((response) => {
          this.$message.success("添加用户成功");
          // 刷新数据
          this.getUserList();
          // 关闭对话框
          this.userInfoDialogVisible = false;
          // 重置表单信息
          this.userInfo = { ...userInfoForm };
        })
        .catch((err) => {
          this.$message.error("添加角色信息失败" + err);
          // 关闭对话框
          this.userInfoDialogVisible = false;
          // 重置表单信息
          this.userInfo = { ...userInfoForm };
        });
    },
    // 修改用户信息
    updateUserInfo(userInfo) {
      userAPI
        .updateUser(userInfo)
        .then((response) => {
          this.$message.success("修改用户成功");
          // 刷新数据
          this.getUserList();
          // 关闭对话框
          this.userInfoDialogVisible = false;
          // 重置表单信息
          this.userInfo = { ...userInfoForm };
        })
        .catch((err) => {
          this.$message.error("修改角色信息失败" + err);
          // 关闭对话框
          this.userInfoDialogVisible = false;
          // 重置表单信息
          this.userInfo = { ...userInfoForm };
        });
    },
    // 添加或者修改用户信息
    addOrUpdateUserInfo() {
      this.$refs.userInfoForm.validate((valid) => {
        if (valid) {
          if (this.userInfo.id) {
            // 更新用户数据
            this.updateUserInfo(this.userInfo);
          } else {
            // 添加用户数据
            this.addUserInfo(this.userInfo);
          }
        } else {
          this.$message.warning("表单数据不合法");
        }
      });
    },
    // 打开用户信息对话框
    openUserInfoDialog(user) {
      // 打开对话框
      this.userInfoDialogVisible = true;
      // 传递数据
      if (user) {
        this.userInfo = user;
        this.userInfoDialogTitle = "编辑用户信息";
      } else {
        this.userInfo = { ...userInfoForm };
        this.userInfoDialogTitle = "添加用户";
      }
    },
    // 关闭用户信息对话框
    resetUserInfo() {
      this.userInfoDialogVisible = false;
      this.userInfo = { ...userInfoForm };
    },

    // 全选回调函数
    handleCheckAllChange(value) {
      this.checkedRoles = value ? this.roles : [];
      this.isIndeterminate = false;
    },
    // 角色选择改变的回调函数
    handleCheckedRolesChange(value) {
      let checkedCount = value.length;
      this.checkAll = checkedCount === this.roles.length;
      this.isIndeterminate =
        checkedCount > 0 && checkedCount < this.roles.length;
    },
    // 把json数据转成string再转成对象，根据Key获取value数据
    getJsonToList(json, key) {
      //把JSON字符串转成对象
      var list = JSON.parse(JSON.stringify(json));
      //var list = JSON.parse(json)
      var strText = [];
      //遍历这个集合对象，获取key的值
      for (var i = 0; i < list.length; i++) {
        strText.push(list[i][key]);
      }
      return strText;
    },
    // 打开用户角色分配对话框
    openUserRoleDialog(user) {
      this.userRoleDialogVisible = true;
      this.userId = user.id;
      // 获取选中的角色
      userAPI.getUserRole(user.id).then((response) => {
        let jsonObejct = response.data.assignRoles;
        this.checkedRoles = this.getJsonToList(jsonObejct, "id");
        this.roles = response.data.allRolesList;
      });
    },
    // 更新用户角色
    updateUserRole() {
      let idList = this.checkedRoles.join(",");
      userAPI.assignRole(this.userId, idList).then((response) => {
        this.$message.success("保存成功");
        this.userRoleDialogVisible = false;
      });
    },

    // 当表格复选框选项发生变化的时候触发
    handleSelectionChange(selection) {
      this.multipleSelection = selection;
    },
    // 每页记录数改变
    handleSizeChange(limit) {
      this.limit = limit;
      this.getUserList();
    },
    // 当前页改变
    handleCurrentChange(index) {
      this.getUserList(index);
    },
    // 重置查询条件
    resetQueryCondition() {
      this.queryCondition = {};
      this.dateRange = {};
      this.getUserList();
    },
  },
};
</script>

<style scoped>
.user-button {
  width: 100px;
}
.m-center {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
