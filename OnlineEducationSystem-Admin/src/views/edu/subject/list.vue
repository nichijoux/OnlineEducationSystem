<template>
  <div class="app-container">
    <div class="m-center">
      <el-input placeholder="输入关键字进行过滤" v-model="filterText">
      </el-input>
      <!-- 添加一级科目按钮和导出科目按钮 -->
      <div class="m-div-center">
        <el-button type="primary" icon="el-icon-plus" @click="addTopSubject"
          >添加一级科目</el-button
        >
        <el-button
          type="warning"
          icon="el-icon-download"
          @click="exportAllSubject"
          >导出所有科目</el-button
        >
      </div>
      <div class="down-tree">
        <!-- 表格数据开始 -->
        <el-tree
          node-key="id"
          ref="tree"
          :data="subjectList"
          :default-expand-all="true"
          :expand-on-click-node="false"
          :filter-node-method="filterNode"
        >
          <span class="custom-tree-node" slot-scope="{ node, data }">
            <!-- 顶部level图标 -->
            <i class="top-node-icon" v-if="data.level == 0"></i>
            <!-- 其余层level图标 -->
            <i class="child-node-icon" v-else></i>

            <span>{{ data.title }}</span>
            <span v-if="data.children && data.children.length > 0">（{{
              data.children.length
            }}）</span>
            <span style="position: absolute; right: 0">
              <el-button
                type="text"
                size="medium"
                @click="() => addChildSubject(data)"
              >
                添加
              </el-button>
              <el-button
                type="text"
                size="medium"
                @click="() => updateSubjectTitle(data)"
                >编辑</el-button
              >
              <el-button
                type="text"
                size="medium"
                @click="() => deleteSubject(node, data)"
              >
                删除
              </el-button>
            </span>
          </span>
        </el-tree>
        <!-- 表格数据结束 -->
        <!-- 快速回到顶部开始 -->
        <el-backtop></el-backtop>
        <!-- 快速回到顶部结束 -->
      </div>
    </div>
  </div>
</template>

<script>
import subjectAPI from "@/api/edu/subject";

export default {
  // 数据区
  data() {
    return {
      // 学科列表
      subjectList: [],
      // 过滤文字
      filterText: "",
    };
  },
  // 监控
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val);
    },
  },
  // 渲染前执行
  created() {
    this.getAllSubject();
  },
  // 方法区
  methods: {
    // 获取所有学科
    getAllSubject() {
      subjectAPI.getAllSubject().then((response) => {
        this.subjectList = response.data;
      });
    },
    // 添加一级学科
    addTopSubject() {
      this.$prompt("请输入要添加的一级学科名称", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        inputValidator(value) {
          if (value == undefined || value.length == 0) {
            return "输入不能为空";
          }
        },
        inputErrorMessage: "输入不能为空",
      }).then(({ value }) => {
        const topSubject = {
          title: value,
        };
        // 尝试在数据库中添加数据
        subjectAPI.addChildSubject(0, topSubject).then((response) => {
          // 添加成功则需要重新刷新数据
          this.getAllSubject();
        });
      });
    },
    // 为subjectId下添加子节点
    addChildSubject(subject) {
      this.$prompt(
        "请输入要在[ " + subject.title + " ]下添加的新类别的名称",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          inputValidator(value) {
            if (value == undefined || value.length == 0) {
              return "输入不能为空";
            }
          },
          inputErrorMessage: "输入不能为空",
        }
      ).then(({ value }) => {
        // 这里的变量名似乎必须为value,改为其他的会undefined
        const childSubject = {
          title: value,
        };
        // 尝试在数据库中添加数据
        subjectAPI
          .addChildSubject(subject.id, childSubject)
          .then((response) => {
            // 修改成功则需要重新刷新数据
            this.getAllSubject();
          });
      });
    },
    // 将id为{subjectId}的科目的title改为{title}
    updateSubjectTitle(subject) {
      this.$prompt("请输入要修改[ " + subject.title + " ]的名称", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        inputValidator(value) {
          if (value == undefined || value.length == 0) {
            return "输入不能为空";
          }
        },
        inputErrorMessage: "输入不能为空",
      }).then(({ value }) => {
        // 复制一个对象
        const { ...modifySubject } = subject;
        modifySubject.title = value;
        // 修改数据库
        subjectAPI.updateSubject(modifySubject).then((response) => {
          // 修改成功则还需要修改当前节点的名称
          subject.title = value;
          this.$message({
            type: "success",
            message: "更新成功",
          });
        });
      });
    },
    // 根据学科id删除科目分类及其子节点
    deleteSubject(node, subject) {
      this.$confirm("确定要删除[ " + subject.title + " ]及其子科目吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        subjectAPI.deleteSubject(subject.id).then((response) => {
          // 如果数据库删除成功,则这里也需要改变
          const parent = node.parent;
          const children = parent.data.children || parent.data;
          const index = children.findIndex((d) => d.id == subject.id);
          children.splice(index, 1);
          this.$message({
            type: "success",
            message: "删除成功",
          });
        });
      });
    },
    // 过滤节点
    filterNode(value, data) {
      if (!value) return true;
      // 设置不区分大小写
      return data.title.indexOf(value) !== -1;
    },
    // 用 excel 导出所有学科
    exportAllSubject() {},
  },
};
</script>

<style scoped>
.down-tree {
  overflow: auto;
  max-width: 800px;
  background-color: #f5f8fa;
  border-radius: 5px;
  border: 1px solid #d3dbde;
  margin-left: 10px;
  margin-top: 10px;
  padding: 10px;
}
.el-tree {
  min-width: 100%;
  display: inline-block;
}
.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  /* justify-content: space-between; */
  font-size: 14px;
  padding-right: 20px;
}
.m-center {
  display: block;
  max-width: 800px;
  margin: 2% auto;
}
.m-div-center {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 10px;
}
.top-node-icon {
  background: url("../../../assets/images/folder.png");
  background-size: 25px;
  width: 25px;
  height: 25px;
  margin-right: 5px;
}
.child-node-icon {
  background: url("../../../assets/images/book.png");
  background-size: 27px;
  width: 27px;
  height: 20px;
  margin-right: 5px;
}
</style>
