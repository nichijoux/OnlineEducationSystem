<template>
  <div class="app-container">
    <div class="m-center">
      <!-- 输入表单 -->
      <el-form
        label-width="100px"
        :model="teacher"
        :rules="rules"
        ref="teacherForm"
      >
        <!-- 讲师名称 -->
        <el-form-item label="讲师名称" prop="name">
          <el-input v-model="teacher.name" class="m-input" />
        </el-form-item>
        <!-- 讲师排序 -->
        <el-form-item label="讲师排序" prop="sort">
          <el-input-number v-model="teacher.sort" :min="0" />
        </el-form-item>
        <!-- 讲师头衔 -->
        <el-form-item label="讲师头衔" prop="level">
          <el-select v-model="teacher.level">
            <!--
            数据类型一定要和取出的json中的一致，否则没法回填
            因此，这里value使用动态绑定的值，保证其数据类型是number
            -->
            <el-option :value="1" label="高级讲师" />
            <el-option :value="2" label="首席讲师" />
          </el-select>
        </el-form-item>
        <!-- 讲师资历 -->
        <el-form-item label="讲师资历" prop="career">
          <el-input v-model="teacher.career" class="m-input" />
        </el-form-item>

        <!-- 讲师简介 -->
        <el-form-item label="讲师简介" prop="intro">
          <el-input
            v-model="teacher.intro"
            :rows="10"
            type="textarea"
            class="m-input"
          />
        </el-form-item>

        <!-- 讲师头像开始 -->
        <el-form-item label="讲师头像" prop="avatar">
          <div class="m-center">
            <!-- 头衔缩略图 如果报错image的错误  要改成String(teacher.avatar)-->
            <pan-thumb :image="teacher.avatar" />
          </div>
          <div class="m-center">
            <!-- 文件上传按钮 -->
            <el-button
              type="primary"
              icon="el-icon-upload"
              @click="imagecropperShow = true"
              size="small"
              style="margin-top: 10px"
              >更换头像
            </el-button>
          </div>

          <!--
            v-show：是否显示上传组件
            :key：类似于id，如果一个页面多个图片上传控件，可以做区分
            :url：后台上传的url地址
            @close：关闭上传组件
            @crop-upload-success：上传成功后的回调 
              <input type="file" name="file"/>
          -->
          <image-cropper
            v-show="imagecropperShow"
            :width="300"
            :height="300"
            :key="imagecropperKey"
            :url="uploadAvatarURL"
            field="file"
            @close="closeUploadBox"
            @crop-upload-success="uploadSuccess"
          />
        </el-form-item>
        <!-- 讲师头像结束 -->
        <!-- 保存按钮开始 -->
        <el-form-item align="center">
          <el-button type="primary" @click="addOrUpdate()" size="small"
            >保存</el-button
          >
        </el-form-item>
        <!-- 保存按钮结束 -->
      </el-form>
    </div>
  </div>
</template>

<script>
import teacherAPI from "@/api/edu/teacher";
// 上传组件
import ImageCropper from "@/components/ImageCropper";
import PanThumb from "@/components/PanThumb";

export default {
  components: {
    ImageCropper,
    PanThumb,
  },
  data() {
    return {
      // 要传的讲师对象
      teacher: {
        // 讲师姓名
        name: "",
        // 排序
        sort: 0,
        // 默认头衔
        level: 1,
        // 讲师资历
        career: "",
        // 讲师简介
        intro: "",
        // 讲师头像
        avatar: "",
      },
      // 上传的URL地址
      uploadAvatarURL: "http://localhost:8500/admin/oss/uploadAvatarFile",
      // 表单规则
      rules: {
        name: [
          { required: true, message: "请输入讲师姓名", trigger: "blur" },
          { min: 2, max: 15, message: "长度在2到15个字符", trigger: "blur" },
        ],
        career: [
          { required: true, message: "请输入讲师资历", trigger: "blur" },
        ],
        intro: [{ required: true, message: "请输入讲师简介", trigger: "blur" }],
      },
      // 上传弹框组件是否显示
      imagecropperShow: false,
      // 上传组件的标识
      imagecropperKey: 0,
      // 保存按钮是否禁用
      saveButtonDisable: false,
    };
  },
  // 渲染前执行
  created() {
    //获取路径id值，根据id查询得到数据
    if (this.$route.params.id) {
      const id = this.$route.params.id;
      this.getTeacher(id);
    }
  },
  methods: {
    // 上传成功回调
    handleAvatarSuccess(res, file) {
      if (res.code == 20000) {
        this.teacher.avatar = res.data;
        // 强制重新渲染
        this.$forceUpdate();
      } else {
        this.$message.error("上传失败 （非0）");
      }
    },
    //根据id查询讲师
    getTeacher(id) {
      teacherAPI.getTeacher(id).then((response) => {
        this.teacher = response.data;
      });
    },
    // 添加讲师
    addTeacher() {
      //添加
      teacherAPI.addTeacher(this.teacher).then((response) => {
        //提示
        this.$message({
          type: "success",
          message: "添加成功!",
        });
        //跳转列表页面
        this.$router.push({ path: "/teacher/list" });
      });
    },
    //修改
    updateTeacher() {
      //添加
      teacherAPI.updateTeacher(this.teacher).then((response) => {
        //提示
        this.$message({
          type: "success",
          message: "修改成功!",
        });
        //跳转列表页面
        this.$router.push({ path: "/teacher/list" });
      });
    },
    //添加和修改
    addOrUpdate() {
      this.$refs.teacherForm.validate((valid) => {
        if (valid) {
          if (!this.teacher.id) {
            //没有id，说明是添加
            this.addTeacher();
          } else {
            //有id，说明是修改
            this.updateTeacher();
          }
        } else {
          this.$message({
            type: "error",
            message: "请输入必要信息",
          });
        }
      });
    },
    // 关闭上传弹框
    closeUploadBox() {
      this.imagecropperShow = false;
      //上传组件初始化(否则只能上传显示一次)
      this.imagecropperKey = this.imagecropperKey + 1;
    },
    // 上传成功的回调函数
    uploadSuccess(data) {
      this.imagecropperShow = false;
      //上传之后接口返回图片地址
      this.teacher.avatar = data;
      //上传组件初始化(否则只能上传显示一次)
      this.imagecropperKey = this.imagecropperKey + 1;
    },
  },
};
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  width: 400px;
  height: 400px;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 150px;
  height: 150px;
  line-height: 150px;
  text-align: center;
}
.avatar {
  width: 150px;
  height: 150px;
  display: block;
}
.m-input {
  width: 500px;
}
.m-center {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
