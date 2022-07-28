<template>
  <article class="col-7 fl">
    <div class="u-r-cont">
      <section>
        <!-- 表格 -->
        <el-form label-width="120px">
          <el-form-item label="昵称">
            <el-input v-model="memberInfo.nickname" />
          </el-form-item>
          <el-form-item label="性别">
            <el-select v-model="memberInfo.sex" clearable placeholder="请选择">
              <!--
                数据类型一定要和取出的json中的一致，否则没法回填
                value使用动态绑定的值，性别由1 2代替
              -->
              <el-option :value="1" label="女" />
              <el-option :value="2" label="男" />
            </el-select>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input-number
              v-model="memberInfo.age"
              :min="0"
              controls-position="right"
            />
          </el-form-item>
          <!-- 头像 -->
          <el-form-item label="头像">
            <el-upload
              :show-file-list="true"
              :on-success="handleAvatarSuccess"
              :on-error="handleAvatarError"
              :before-upload="beforeAvatarUpload"
              class="avatar-uploader"
              :action="BASE_API"
            >
              <img v-if="memberInfo.avatar" :src="memberInfo.avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon" />
            </el-upload>
          </el-form-item>
          <el-form-item label="个性签名">
            <el-input v-model="memberInfo.sign" :rows="8" type="textarea" />
          </el-form-item>

          <el-form-item>
            <el-button plain="true" type="primary" @click="save"
              >保存</el-button
            >
          </el-form-item>
        </el-form>
      </section>
    </div>
  </article>
</template>

<script>
import loginAPI from '@/api/login'
import memberAPI from "@/api/ucenter/member";
import cookie from "js-cookie";

export default {
  layout: "center",
  data() {
    return {
      token: "",
      memberInfo: {
        id: "",
        age: "",
        avatar: "",
        mobile: "",
        nickname: "",
        sex: "",
        sign: "",
      },
      BASE_API: "http://localhost:8500/admin/oss/uploadAvatarFile",
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
  created() {
    var userStr = cookie.get("oes_ucenter");
    //userStr是字符串   需要转换为json对象
    if (userStr) {
      this.memberInfo = JSON.parse(userStr);
    }
    return;
  },

  methods: {
    save() {
      memberAPI.updateUserInfo(this.memberInfo).then((response) => {
        //修改成功
        //提示成功
        this.$message.success("修改成功！ 🧙‍♂️");
      });
    },

    // 头像
    // 文件上传成功
    handleAvatarSuccess(response) {
      if (response.success) {
        this.memberInfo.avatar = response.data;
        // 强制重新渲染
        this.$forceUpdate();
      } else {
        this.$message.error("上传失败! （非20000）");
      }
    },

    // 文件上传失败（http）
    handleAvatarError() {
      this.$message.error("上传失败! （http失败）");
    },

    // 上传校验
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
        return;
      }
      return true;
    },

    //从cookie中获取用户信息
    showInfoFromCookie() {
      //从cookie中获取用户信息
      var userStr = cookie.get("oes_ucenter");
      //userStr是字符串   需要转换为json对象
      if (userStr) {
        this.memberInfo = JSON.parse(userStr);
        return;
      }
      loginAPI.getUserInfo().then((response) => {
        // 重新设置cookie
        this.memberInfo = response.data;
        cookie.set("oes_ucenter", JSON.stringify(this.memberInfo), {
          domain: "localhost",
        });
      });
    },
  },
};
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader .avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar-uploader img {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
