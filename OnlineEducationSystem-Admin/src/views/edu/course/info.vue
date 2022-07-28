<template>
  <div class="app-container">
    <h2 style="text-align: center">发布新课程</h2>
    <!-- element-ui步骤开始 -->
    <el-steps
      :active="1"
      finish-status="success"
      simple
      style="margin-bottom: 40px"
    >
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="发布课程" />
    </el-steps>
    <!-- element-ui步骤结束 -->

    <el-form
      label-width="120px"
      :model="courseInfo"
      :rules="rules"
      ref="courseInfoForm"
    >
      <el-form-item label="课程标题">
        <el-input
          v-model="courseInfo.title"
          placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"
        />
      </el-form-item>

      <el-form-item label="课程分类">
        <el-select
          v-model="courseInfo.subjectId"
          placeholder="一级分类"
          @change="subjectLevelOneChanged"
        >
          <el-option
            v-for="subject in subjectOneList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"
          />
        </el-select>

        <!-- 二级分类 -->
        <el-select
          v-model="courseInfo.typeId"
          placeholder="二级分类"
          style="margin-left: 10px"
        >
          <el-option
            v-for="subject in subjectTwoList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"
          />
        </el-select>
      </el-form-item>

      <!-- 课程讲师 -->
      <el-form-item label="课程讲师">
        <el-select v-model="courseInfo.teacherId" placeholder="请选择">
          <!--遍历所有讲师-->
          <el-option
            v-for="teacher in teacherList"
            :key="teacher.id"
            :label="teacher.name"
            :value="teacher.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="总课时">
        <el-input-number
          :min="0"
          v-model="courseInfo.lessonNum"
          controls-position="right"
          placeholder="请填写课程的总课时数"
        />
      </el-form-item>

      <el-form-item label="课程简介">
        <tinymce :height="300" v-model="courseInfo.description" />
      </el-form-item>

      <!-- 课程封面-->
      <el-form-item label="课程封面">
        <el-upload
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :action="BASE_API"
          class="avatar-uploader"
        >
          <img :src="courseInfo.cover" alt="默认封面" class="avatar-uploader" />
        </el-upload>
      </el-form-item>

      <el-form-item label="课程价格">
        <el-input-number
          :min="0"
          v-model="courseInfo.price"
          controls-position="right"
          placeholder="免费课程请设置为0元"
        />
        元
      </el-form-item>

      <el-form-item>
        <div class="m-center">
          <el-button type="primary" @click="addOrUpdate"
            >保存并下一步</el-button
          >
        </div>
      </el-form-item>
    </el-form>

    <!-- 快速回到顶部开始 -->
    <el-backtop></el-backtop>
    <!-- 快速回到顶部结束 -->
  </div>
</template>

<script>
import courseAPI from "@/api/edu/course";
import teacherAPI from "@/api/edu/teacher";
import subjectAPI from "@/api/edu/subject";
// 富文本框组件
import Tinymce from "@/components/Tinymce";

export default {
  components: { Tinymce },
  // 数据区
  data() {
    return {
      // 要填写的课程id
      courseInfo: {
        // 课程标题
        title: "",
        // 一级分类id
        subjectId: "",
        // 二级分类id
        typeId: "",
        // 讲师id
        teacherId: "",
        // 总课时
        lessonNum: 0,
        // 课程描述
        description: "",
        cover:
          "https://img2.huashi6.com/images/resource/2009/01/24/28h33392p0.jpg",
        // 课程价格
        price: 0,
      },
      // 讲师列表
      teacherList: [],
      // 科目一级分类
      subjectOneList: [],
      // 科目二级分类
      subjectTwoList: [],
      // 上传路径
      BASE_API: "http://localhost:8500/admin/oss/uploadAvatarFile",
      // 表单规则
      rules: {
        title: [
          { required: true, message: "请输入课程标题", trigger: "blur" },
          { min: 2, message: "课程标题最小为2个字符", trigger: "blur" },
        ],
      },
      // 允许的图片类型列表
      allowImageType: [
        "image/png",
        "image/jpg",
        "image/jpeg",
        "image/bmp",
        "image/gif",
        "image/webp",
        "image/psd",
        "image/svg",
        "image/tiff",
      ],
    };
  },
  // 渲染前执行
  created() {
    // 获取讲师列表
    this.getTeacherList();
    // 获取科目一级列表
    this.getLevelOneSubject();
    //获取路由id值
    if (this.$route.params && this.$route.params.id) {
      this.courseInfo.id = this.$route.params.id;
      //调用根据id查询课程的方法
      this.getCourse(this.courseInfo.id);
    }
  },
  // 方法区
  methods: {
    // 根据id获取课程信息
    getCourse(id) {
      courseAPI.getCourse(id).then((response) => {
        this.courseInfo = response.data;
        // 遍历查询一二级类型
        for (var i = 0; i < this.subjectOneList.length; i++) {
          const oneSubject = this.subjectOneList[i];
          // 找到一级分类
          if (oneSubject.id == this.courseInfo.subjectId) {
            this.subjectTwoList = oneSubject.children;
          }
        }
      });
    },
    // 获取所有讲师
    getTeacherList() {
      teacherAPI.getAllTeacher().then((response) => {
        this.teacherList = response.data;
      });
    },
    // 获取所有一级学科
    getLevelOneSubject() {
      subjectAPI.getAllSubject().then((response) => {
        this.subjectOneList = response.data;
      });
    },
    // 一级分类改变
    subjectLevelOneChanged(value) {
      //value就是一级分类id值
      //遍历所有的分类，包含一级和二级
      for (var i = 0; i < this.subjectOneList.length; i++) {
        //每个一级分类
        var oneSubject = this.subjectOneList[i];
        //判断：所有一级分类id 和 点击一级分类id是否一样
        if (value === oneSubject.id) {
          //从一级分类获取里面所有的二级分类
          this.subjectTwoList = oneSubject.children;
          //把二级分类id值清空
          this.courseInfo.typeId = "";
        }
      }
    },
    // 封面上传前处理
    beforeAvatarUpload(file) {
      const fileType = file.type;
      const isImage = this.allowImageType.indexOf(fileType.toLowerCase()) != -1;
      if (!isImage) {
        this.$message({
          type: "error",
          message: "上传文件应为图片格式",
        });
        return false;
      }
      if (file.size / 1024 / 1024 > 3) {
        this.$message.error("上传封面图片大小不能超过3MB!");
        return false;
      }
      return true;
    },
    // 上传封面成功的回调函数
    handleAvatarSuccess(res, file) {
      this.courseInfo.cover = res.data;
    },
    // 添加课程
    addCourse() {
      courseAPI.addCourse(this.courseInfo).then((response) => {
        this.$message.success("添加课程信息成功");
        // 跳转到第二步
        this.$router.push({ path: "/course/chapter/" + response.data });
      });
    },
    // 更新课程
    updateCourse() {
      courseAPI.updateCourse(this.courseInfo).then((response) => {
        this.$message.success("修改课程信息成功");
        // 跳转到第二步
        this.$router.push({ path: "/course/chapter/" + this.courseInfo.id });
      });
    },
    // 添加或者修改
    addOrUpdate() {
      this.$refs.courseInfoForm.validate((valid) => {
        if (valid) {
          // 添加或者更新
          if (!this.courseInfo.id) {
            // 添加课程
            this.addCourse();
          } else {
            // 修改课程
            this.updateCourse();
          }
        } else {
          this.$message({
            type: "error",
            message: "请输入必要信息",
          });
        }
      });
    },
  },
};
</script>

<style scoped>
.m-center {
  display: flex;
  justify-content: center;
  align-items: center;
}

.avatar-uploader {
  max-width: 1100px;
}

.m-position-right {
  position: absolute;
  right: 0;
}

@media screen and (min-width: 300px) and (max-width: 500px) {
  .avatar-uploader {
    max-width: 300px;
  }
  img {
    max-width: 300px;
  }
}
</style>
