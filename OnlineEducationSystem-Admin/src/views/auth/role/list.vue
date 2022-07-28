<template>
  <div class="app-container">
    <!-- 查询表单开始 -->
    <el-card class="operate-container" shadow="never" align="center">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="角色名">
          <el-input v-model="queryCondition.name" placeholder="角色名" />
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
          <el-button type="primary" icon="el-icon-search" @click="getRoleList()"
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
        class="role-button"
        @click="openRoleInfoDialog()"
        >添加角色</el-button
      >
      <el-button type="danger" class="role-button" @click="batchDeleteRole()"
        >批量删除</el-button
      >
    </div>

    <el-divider></el-divider>

    <!-- 角色列表 -->
    <el-table
      v-loading="dataLoading"
      :data="roleList"
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

      <el-table-column prop="roleName" label="角色名称" align="center" />

      <el-table-column prop="remark" label="角色备注" align="center" />

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
            @change="enableOrDisableRole(scope.row.id, $event)"
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
            @click="() => openRolePermissionDialog(scope.row)"
            >分配权限</el-button
          >
          <!-- 编辑角色按钮 -->
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-edit"
            @click="() => openRoleInfoDialog(scope.row)"
            >编辑角色</el-button
          >
          <!-- 删除角色按钮 -->
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="deleteRole(scope.row.id, scope.row.roleName)"
            >删除角色</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 编辑角色信息对话框开始 -->
    <el-dialog
      :visible.sync="roleInfoDialogVisible"
      :title="roleInfoDialogTitle"
    >
      <el-form ref="roleInfoForm" :model="roleInfo" :rules="roleInfoRules">
        <!-- 角色名称 -->
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="roleInfo.roleName" />
        </el-form-item>
        <!-- 角色备注 -->
        <el-form-item label="角色备注" prop="remark">
          <el-input v-model="roleInfo.remark" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer m-center">
        <el-button type="primary" @click="addOrUpdateRoleInfo()"
          >确定</el-button
        >
        <el-button type="warning" @click="resetRoleInfo()">取消</el-button>
      </div>
    </el-dialog>
    <!-- 编辑角色信息对话框结束 -->

    <!-- 编辑角色权限对话框开始 -->
    <el-dialog :visible.sync="rolePermissionDialogVisible" title="分配角色权限">
      <el-tree
        :data="rolePermission"
        show-checkbox
        node-key="id"
        ref="permissionTree"
        highlight-current
        :props="permissionTreeProps"
      ></el-tree>
      <div class="m-center">
        <el-button type="primary" @click="updateRolePermission()"
          >保存</el-button
        >
        <el-button type="warning" @click="resetRolePermission()"
          >取消</el-button
        >
      </div>
    </el-dialog>
    <!-- 编辑角色权限对话框结束 -->

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
import roleAPI from "@/api/auth/role";
import menuAPI from "@/api/auth/permission";

// 编辑角色的对象
const roleInfoForm = {
  // 角色名
  roleName: "",
  // 角色备注
  remark: "",
};

// 编辑权限的对象
const rolePermissionTree = [];

export default {
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
      // 角色列表
      roleList: [],
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

      // 角色信息部分
      // 编辑角色的对象
      roleInfo: { ...roleInfoForm },
      // 编辑角色对话框标题和显示
      roleInfoDialogTitle: "编辑角色信息",
      roleInfoDialogVisible: false,
      // 角色信息表单规则
      roleInfoRules: {
        roleName: [
          { required: true, message: "请输入角色名", trigger: "blur" },
        ],
        remark: [
          { required: true, message: "请输入角色备注", trigger: "blur" },
        ],
      },

      // 角色权限部分
      // 要编辑的角色id
      roleId: "",
      // 编辑角色权限的对象
      rolePermission: [],
      // 编辑角色对话框
      rolePermissionDialogVisible: false,
      permissionTreeProps: {
        children: "children",
        label: "name",
      },
    };
  },

  // 生命周期函数：内存准备完毕，页面尚未渲染
  created() {
    // 获取角色信息
    this.getRoleList();
  },

  methods: {
    // 判断查询条件是否存在
    hasQueryCondition() {
      return (
        this.queryCondition.hasOwnProperty("name") ||
        this.queryCondition.hasOwnProperty("beginTime") ||
        this.queryCondition.hasOwnProperty("endTime")
      );
    },
    // 加载讲师列表数据
    getRoleList(index = 1) {
      this.index = index;
      if (this.dateRange.length > 0) {
        this.queryCondition.beginTime = this.dateRange[0];
        this.queryCondition.endTime = this.dateRange[1];
      }
      if (!this.hasQueryCondition()) {
        roleAPI.pageQueryRole(this.index, this.limit).then((response) => {
          this.roleList = response.data.records;
          this.total = Number(response.data.total);
          // 数据加载并绑定成功
          this.dataLoading = false;
        });
      } else {
        roleAPI
          .pageQueryRole(this.index, this.limit, this.queryCondition)
          .then((response) => {
            this.roleList = response.data.records;
            this.total = Number(response.data.total);
            // 数据加载并绑定成功
            this.dataLoading = false;
          });
      }
    },
    // 根据id删除数据
    deleteRole(id, roleName) {
      this.$confirm(`确定要删除角色[ ${roleName} ]吗?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          roleAPI.deleteRole(id).then((response) => {
            this.$message.success("删除成功");
            this.getRoleList();
          });
        })
        .catch(() => {
          this.$message.info("已取消删除");
        });
    },
    // 批量删除
    batchDeleteRole() {
      if (this.multipleSelection.length === 0) {
        this.$message.warning("请选择要删除的记录!");
        return;
      }
      this.$confirm("此操作将永久删除这些角色, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          // 遍历selection，将id取出放入id列表
          let idList = [];
          this.multipleSelection.forEach((item) => {
            idList.push(item.id);
          });
          // 调用api
          roleAPI.batchDeleteRole(idList).then((response) => {
            this.$message.success("删除成功");
            this.getRoleList();
          });
        })
        .catch(() => {
          this.$message.info("已取消删除");
        });
    },
    // 当表格复选框选项发生变化的时候触发
    handleSelectionChange(selection) {
      this.multipleSelection = selection;
    },
    // 每页记录数改变
    handleSizeChange(limit) {
      this.limit = limit;
      this.getRoleList();
    },
    // 当前页改变
    handleCurrentChange(index) {
      this.getRoleList(index);
    },
    // 重置查询条件
    resetQueryCondition() {
      this.queryCondition = {};
      this.dateRange = {};
      this.getRoleList();
    },
    // 编辑角色信息对话框
    openRoleInfoDialog(data) {
      // 打开对话框
      this.roleInfoDialogVisible = true;
      // 赋予表单对象
      if (data) {
        this.roleInfo = data;
        this.roleInfoDialogTitle = "编辑角色信息";
      } else {
        this.roleInfo = { ...roleInfoForm };
        this.roleInfoDialogTitle = "添加角色";
      }
    },
    // 添加角色
    addRoleInfo(roleInfo) {
      roleAPI
        .addRole(roleInfo)
        .then((response) => {
          this.$message.success("添加角色成功");
          // 获取数据
          this.getRoleList();
          // 关闭对话框
          this.roleInfoDialogVisible = false;
          // 重置表单
          this.roleInfo = { ...roleInfoForm };
        })
        .catch((error) => {
          // 关闭对话框
          this.roleInfoDialogVisible = false;
          // 重置表单
          this.roleInfo = { ...roleInfoForm };
          this.$message.error("添加角色失败" + error);
        });
    },
    // 修改角色信息
    updateRoleInfo(roleInfo) {
      roleAPI
        .updateRole(this.roleInfo)
        .then((response) => {
          this.$message.success("更新成功");
          // 获取数据
          this.getRoleList();
          // 关闭对话框
          this.roleInfoDialogVisible = false;
          // 重置表单
          this.roleInfo = { ...roleInfoForm };
        })
        .catch((error) => {
          // 关闭对话框
          this.roleInfoDialogVisible = false;
          // 重置表单
          this.roleInfo = { ...roleInfoForm };
          this.$message.error("修改角色信息失败" + error);
        });
    },
    // 添加或者更新角色信息
    addOrUpdateRoleInfo() {
      this.$refs.roleInfoForm.validate((valid) => {
        if (valid) {
          if (this.roleInfo.id) {
            this.updateRoleInfo(this.roleInfo);
          } else {
            this.addRoleInfo(this.roleInfo);
          }
        } else {
          this.$message.warning("表单不合法");
        }
      });
    },
    // 启用或禁用角色
    enableOrDisableRole(id, value) {},
    // 重置RoleInfo表单
    resetRoleInfo() {
      // 关闭对话框
      this.roleInfoDialogVisible = false;
      // 重置表单
      this.roleInfo = { ...roleInfoForm };
    },

    //把json数据转成string再转成对象,根据Key获取value数据,并判断是否被选中
    getJsonToList(list, jsonList) {
      //遍历这个集合对象，获取key的值
      for (var i = 0; i < jsonList.length; i++) {
        if (jsonList[i]["select"] == true) {
          list.push(jsonList[i]["id"]);
        }
        if (jsonList[i]["children"] != null) {
          this.getJsonToList(list, jsonList[i]["children"]);
        }
      }
    },
    // 获取角色对应的权限
    getRolePermission(roleId) {
      menuAPI.getRolePermission(roleId).then((response) => {
        this.rolePermission = response.data;
        let jsonList = JSON.parse(JSON.stringify(this.rolePermission));
        let permissionList = [];
        this.getJsonToList(permissionList, jsonList[0]["children"]);
        // 设置被选择的格子
        this.$refs.permissionTree.setCheckedKeys(permissionList);
      });
    },
    // 打开权限分配框
    openRolePermissionDialog(role) {
      this.rolePermissionDialogVisible = true;
      this.roleId = role.id;
      this.getRolePermission(role.id);
    },
    // 更新角色的权限
    updateRolePermission() {
      // 不要用尚硅谷讲的那个element-ui,应该使用下面的方法解决
      let idList = this.$refs.permissionTree
        .getHalfCheckedKeys()
        .concat(this.$refs.permissionTree.getCheckedKeys())
        .join(",");
      // 调用API分配权限
      menuAPI.assignRolePermission(this.roleId, idList).then((response) => {
        this.$message.success("修改权限成功");
        this.rolePermissionDialogVisible = false;
      });
    },
    // 关闭角色权限菜单
    resetRolePermission() {
      this.rolePermissionDialogVisible = false;
      this.rolePermission = [...rolePermissionTree];
    },
  },
};
</script>

<style scoped>
.role-button {
  width: 100px;
}
.m-center {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
