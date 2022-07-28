<template>
  <div class="app-container">
    <el-input
      v-model="filterText"
      placeholder="输入关键字过滤"
      style="margin-bottom: 30px"
    />

    <el-table
      :data="menuList"
      style="width:100% margin-bottom:20px"
      row-key="id"
      border
      ref="menuTree"
      :filter-node-method="filterMenu"
      :tree-props="{ children: 'children' }"
    >
      <!-- 名称开始 -->
      <el-table-column prop="name" label="名称"></el-table-column>
      <!-- 名称结束 -->

      <!-- 访问路径开始 -->
      <el-table-column prop="path" label="访问路径"></el-table-column>
      <!-- 访问路径结束 -->

      <!-- 组件路径开始 -->
      <el-table-column prop="component" label="组件路径"></el-table-column>
      <!-- 组件路径结束 -->

      <!-- 权限值开始 -->
      <el-table-column prop="permissionValue" label="权限值"></el-table-column>
      <!-- 权限值结束 -->

      <!-- 图标开始 -->
      <el-table-column prop="icon" label="菜单图标"></el-table-column>
      <!-- 图标结束 -->

      <!-- 隐藏开始 -->
      <el-table-column label="是否隐藏" width="100">
        <template slot-scope="scope">
          {{ scope.row.hidden }}
        </template>
      </el-table-column>
      <!-- 隐藏结束 -->

      <!-- 是否启用开始 -->
      <el-table-column label="是否启用">
        <template slot-scope="scope">
          <el-switch
            style="display: block"
            v-model="scope.row.isEnable"
            active-color="#13ce66"
            inactive-color="#ff4949"
            active-text="启用"
            inactive-text="禁用"
            @change="enableOrDisablePermission(scope.row.id, $event)"
          ></el-switch>
        </template>
      </el-table-column>
      <!-- 是否启用结束 -->

      <!-- 排序列开始 -->
      <el-table-column
        prop="sort"
        label="菜单排序"
        width="80"
        align="center"
      ></el-table-column>
      <!-- 排序列结束 -->

      <!-- 操作列开始 -->
      <el-table-column label="操作" width="150" align="right">
        <template slot-scope="scope">
          <!-- 如果是顶级或者一级菜单,则可以继续添加菜单 -->
          <el-button
            v-if="scope.row.level == 0 || scope.row.level == 1"
            type="text"
            size="mini"
            @click="
              () => {
                menuDialogVisivle = true;
                menu.pid = scope.row.id;
              }
            "
            >添加菜单
          </el-button>
          <!-- 如果是左侧二级菜单则只能加入按钮权限 -->
          <el-button
            v-if="scope.row.level == 2"
            type="text"
            size="mini"
            @click="
              () => {
                // 开启权限对话框
                permissionDialogVisible = true;
                permission.pid = scope.row.id;
              }
            "
            >添加功能
          </el-button>
          <!-- 对于按钮权限则需要修改功能 -->
          <el-button
            v-if="scope.row.level == 3"
            type="text"
            size="mini"
            @click="() => openUpdateButtonPermission(scope.row)"
            >修改权限值
          </el-button>
          <!-- 对于顶级和一级菜单需要[修改]功能 -->
          <el-button
            v-if="scope.row.level != 3"
            type="text"
            size="mini"
            @click="() => openUpdateMenuPermission(scope.row)"
            >修改
          </el-button>
          <!-- 所有菜单均可删除 -->
          <el-button
            type="text"
            size="mini"
            @click="deletePermission(scope.row.id)"
            >删除
          </el-button>
        </template>
      </el-table-column>
      <!-- 操作列结束 -->
    </el-table>

    <!-- 添加或修改菜单的窗口 -->
    <el-dialog :visible.sync="menuDialogVisivle" :title="menuDialogTitle">
      <el-form
        ref="menu"
        :model="menu"
        :rules="menuValidateRules"
        label-width="120px"
      >
        <!-- 菜单名称 -->
        <el-form-item label="菜单名称" prop="name">
          <el-input v-model="menu.name" />
        </el-form-item>
        <!-- 访问路径 -->
        <el-form-item label="访问路径" prop="path">
          <el-input v-model="menu.path" />
        </el-form-item>
        <!-- 组件路径 -->
        <el-form-item label="组件路径" prop="component">
          <el-input v-model="menu.component" />
        </el-form-item>
        <!-- 菜单图标 -->
        <el-form-item label="菜单图标" prop="icon">
          <el-input v-model="menu.icon" />
        </el-form-item>
        <!-- 重定向路径 -->
        <el-form-item label="重定向路径" prop="redirect">
          <el-input v-model="menu.redirect" />
        </el-form-item>
        <!-- 是否隐藏 -->
        <el-form-item label="是否隐藏" prop="hidden">
          <el-select v-model="menu.hidden">
            <el-option label="显示" :value="false" select />
            <el-option label="隐藏" :value="true" />
          </el-select>
        </el-form-item>
        <!-- 排序 -->
        <el-form-item label="排序" prop="sort">
          <el-input-number
            v-model="menu.sort"
            placeholder="0"
            :min="0"
          ></el-input-number>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addOrUpdateMenuPermission()"
          >确 定</el-button
        >
        <el-button type="info" @click="resetFormData()">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改按钮权限的窗口 -->
    <el-dialog :visible.sync="permissionDialogVisible" title="添加功能">
      <el-form
        ref="permission"
        :model="permission"
        :rules="permissionValidateRules"
        label-width="120px"
      >
        <el-form-item label="功能名称" prop="name">
          <el-input v-model="permission.name" />
        </el-form-item>
        <el-form-item label="功能权限值" prop="permissionValue">
          <el-input v-model="permission.permissionValue" />
        </el-form-item>
        <el-form-item label="访问路径" prop="path">
          <el-input v-model="permission.path" />
        </el-form-item>
        <el-form-item label="组件路径" prop="component">
          <el-input v-model="permission.component" />
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input-number
            v-model="permission.sort"
            placeholder="0"
            :min="0"
          ></el-input-number>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addOrUpdateButtonPermision()"
          >确 定</el-button
        >
        <el-button type="info" @click="resetFormData()">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import menuAPI from "@/api/auth/permission";

// 菜单表单实体
const menuForm = {
  // 菜单名
  name: "",
  // 菜单父id
  pid: 0,
  // 菜单访问路径
  path: "",
  // 菜单组件路径
  component: "",
  // 重定向路径
  redirect: "",
  // 菜单类型(1为菜单,2为按钮)
  type: 1,
  // 图标
  icon: "",
  // 是否隐藏
  hidden: false,
  // 排序
  sort: 0,
};

// 按钮权限表单实体
const permissionForm = {
  // 按钮权限名称
  name: "",
  // 按钮权限父id
  pid: 0,
  // 按钮访问路径
  path: "",
  // 按钮组件路径
  component: "",
  // 按钮权限的权限值
  permissionValue: "",
  // 菜单类型(1为菜单,2为按钮)
  type: 2,
  // 是否隐藏
  hidden: true,
  // 排序
  sort: 0,
};

export default {
  data() {
    return {
      filterText: "",
      // 菜单列表
      menuList: [],
      // 添加菜单的对话框标题
      menuDialogTitle: "添加菜单",
      // 是否显示菜单对话框
      menuDialogVisivle: false,
      // 权限菜单对话框
      permissionDialogVisible: false,
      // 要提交的菜单和按钮权限实体
      menu: { ...menuForm },
      permission: { ...permissionForm },
      // 菜单输入规则
      menuValidateRules: {
        name: [{ required: true, message: "请输入菜单名", trigger: "blur" }],
        path: [{ required: true, message: "请输入菜单路径", trigger: "blur" }],
        component: [
          { required: true, message: "请输入组件名称", trigger: "blur" },
        ],
      },
      // 权限输入规则
      permissionValidateRules: {
        // 权限名
        name: [{ required: true, message: "请输入功能名称", trigger: "blur" }],
        // 权限值
        permissionValue: [
          { required: true, message: "请输入功能权限值", trigger: "blur" },
        ],
      },
    };
  },
  // 监听上面文本框搜索
  watch: {
    // 过滤权限
    filterText(val) {
      this.$refs.menuTree.filter(val);
    },
  },

  created() {
    this.getAllPermission();
  },

  methods: {
    // 获取所有的权限菜单
    getAllPermission() {
      menuAPI.getAllPermission().then((response) => {
        this.menuList = response.data;
      });
    },
    // 过滤菜单
    filterMenu(value, data) {
      if (!value) return true;
      return data.name.indexOf(value) !== -1;
    },
    // 添加顶级菜单权限
    addTopMenu() {
      menuAPI
        .addPermission(this.menu)
        .then((response) => {
          // 添加成功则关闭菜单
          this.menuDialogVisivle = false;
          this.$message.success("添加顶级菜单成功");
          // 刷新页面
          this.getAllPermission();
          // 清空menu实体
          this.menu = { ...menuForm };
        })
        .catch((response) => {
          // 添加失败也需要关闭菜单
          this.menuDialogVisivle = false;
          // 失败提示(因为拦截器已经将response拦截,因此此时直接使用response)
          this.$message.error("添加顶级菜单失败,错误原因:" + response);
          // 清空menu实体
          this.menu = { ...menuForm };
        });
    },
    // 添加普通菜单权限
    addNormalMenu() {
      menuAPI
        .addPermission(this.menu)
        .then((response) => {
          // 添加成功则关闭菜单
          this.menuDialogVisivle = false;
          // 提示成功
          this.$message.success("添加普通菜单成功");
          // 刷新页面
          this.getAllPermission();
          // 清空menu实体
          this.menu = { ...menuForm };
        })
        .catch((response) => {
          // 添加失败也应关闭菜单
          this.menuDialogVisivle = false;
          // 提示失败(因为拦截器已经将response拦截,因此此时直接使用response)
          this.$message.error("添加普通菜单失败" + response);
          // 把menu清空
          this.menu = { ...menuForm };
        });
    },
    // 修改菜单权限
    updateMenu(menu) {
      menuAPI.updatePermission(menu).then((response) => {
        // 关闭菜单权限对话框
        this.menuDialogVisivle = false;
        this.$message.success("修改成功");
        // 刷新页面
        this.getAllPermission();
        // 清空menu实体
        this.menu = { ...menuForm };
      });
    },
    // 添加或者修改menu
    addOrUpdateMenuPermission() {
      this.$refs.menu.validate((valid) => {
        if (valid) {
          if (!this.menu.id) {
            // 添加
            if (this.menu.pid == 1) {
              // 顶级菜单的添加(其父菜单为[全部权限])
              this.addTopMenu();
            } else {
              // 顶级菜单下的子菜单的添加
              this.addNormalMenu();
            }
          } else {
            // 修改
            this.updateMenu(this.menu);
          }
        }
      });
    },
    // 添加按钮权限
    addButtonPermission(buttonPermission) {
      menuAPI.addPermission(buttonPermission).then((response) => {
        // 关闭按钮权限对话框
        this.permissionDialogVisible = false;
        this.$message.success("添加按钮权限成功");
        // 刷新页面
        this.getAllPermission();
        // 清空permission实体
        this.permission = { ...permissionForm };
      });
    },
    // 修改按钮权限
    updateButtonPermission(buttonPermission) {
      menuAPI.updatePermission(buttonPermission).then((response) => {
        // 关闭按钮权限对话框
        this.permissionDialogVisible = false;
        this.$message.success("修改成功");
        // 刷新页面
        this.getAllPermission();
        // 清空permission实体
        this.permission = { ...permissionForm };
      });
    },
    // 添加按钮权限
    addOrUpdateButtonPermision() {
      this.$refs.permission.validate((valid) => {
        if (valid) {
          if (this.permission.id) {
            // 修改按钮权限
            this.updateButtonPermission(this.permission);
          } else {
            // 添加按钮权限
            this.addButtonPermission(this.permission);
          }
        }
      });
    },
    // 删除菜单
    deletePermission(permissionId) {
      this.$confirm("此操作将永久删除该记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          return menuAPI.deletePermission(permissionId);
        })
        .then(() => {
          this.getAllPermission(); // 刷新列表
          this.$message.success("删除成功!");
        })
        .catch((response) => {
          // 失败
          if (response === "cancel") {
            this.$message.info("已取消删除");
          } else {
            this.$message.error("删除失败");
          }
        });
    },
    // 修改权限,直接获取table中的数据
    openUpdateMenuPermission(data) {
      // 打开菜单对话框
      this.menuDialogVisivle = true;
      // 赋予menu数据
      this.menu = data;
    },
    // 修改按钮权限(直接从table中获取数据)
    openUpdateButtonPermission(data) {
      // 打开按钮权限对话框
      this.permissionDialogVisible = true;
      // 赋予按钮权限
      this.permission = data;
    },
    // 启用否禁用某个菜单或权限
    enableOrDisablePermission(id, isEnable) {
      //TODO
    },
    // 清空数据
    resetFormData() {
      this.permissionDialogVisible = false;
      this.menuDialogVisivle = false;
      this.menu = { ...menuForm };
      this.permission = { ...permissionForm };
    },
  },
};
</script>
