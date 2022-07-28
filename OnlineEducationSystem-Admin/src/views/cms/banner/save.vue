<template>
  <div class="app-container">
    <el-form label-width="120px">
      <!-- banner标题 -->
      <el-form-item label="banner标题">
        <el-input v-model="banner.title" width="400" />
      </el-form-item>
      <!-- banner排序 -->
      <el-form-item label="banner排序">
        <el-input-number
          v-model="banner.sort"
          controls-position="right"
          :min="0"
        />
      </el-form-item>

      <!-- banner跳转链接 -->
      <el-form-item label="banner跳转链接">
        <el-input v-model="banner.linkUrl" width="400px" />
      </el-form-item>

      <!-- banner图片 -->
      <el-form-item label="banner图片">
        <el-upload
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :action="BASE_API"
          class="banner-uploader"
        >
          <img :src="banner.imageUrl" class="banner-uploader" />
        </el-upload>
      </el-form-item>

      <!-- 保存按钮开始 -->
      <el-form-item>
        <div class="m-center">
          <el-button type="primary" @click="addOrUpdate">保存</el-button>
        </div>
      </el-form-item>
      <!-- 保存按钮结束 -->
    </el-form>
  </div>
</template>

<script>
import bannerAPI from "@/api/cms/banner";

export default {
  // 数据区
  data() {
    return {
      // banner表单
      banner: {
        // 标题
        title: "",
        // 排序
        sort: 0,
        // 点击后跳转链接
        linkUrl: "",
      },
      // 上传地址
      BASE_API: "http://localhost:8500/admin/oss/uploadAvatarFile",
      // 允许的图片类型
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
    if (this.$route.params && this.$route.params.id) {
      // 获取bannerId
      this.banner.id = this.$route.params.id;
      // 初始化
      this.getBanner(this.banner.id);
    } else {
      // 清空表单
      this.banner = {};
      this.banner.imageUrl =
        "https://img2.huashi6.com/images/resource/2009/01/24/28h33392p0.jpg";
    }
  },
  // 路由监听
  watch: {
    $route(to, from) {
      //路由变化方式，路由发生变化，方法就会执行
      if (this.$route.params && this.$route.params.id) {
        // 获取bannerId
        this.banner.id = this.$route.params.id;
        // 初始化
        this.getBanner(this.banner.id);
      } else {
        // 清空表单
        this.banner = {};
        this.banner.imageUrl =
          "https://img2.huashi6.com/images/resource/2009/01/24/28h33392p0.jpg";
      }
    },
  },
  // 方法区
  methods: {
    // 获取banner信息
    getBanner(bannerId) {
      bannerAPI.getBanner(bannerId).then((response) => {
        this.banner = response.data;
      });
    },
    // 添加Banner的方法
    addBanner() {
      bannerAPI.addBanner(this.banner).then((response) => {
        // 提示信息
        this.$message.success("添加成功");
        // 回到列表页面 路由跳转
        this.$router.push({ path: "/banner/list" });
      });
    },
    // 更新banner的方法
    updateBanner() {
      bannerAPI.updateBanner(this.banner).then((response) => {
        // 提示信息
        this.$message.success("更新成功");
        // 回到列表页面 路由跳转
        this.$router.push({ path: "/banner/list" });
      });
    },
    // 添加或者更新
    addOrUpdate() {
      if (!this.banner.id) {
        this.addBanner();
      } else {
        this.updateBanner();
      }
    },
    // 上传封面成功调用的方法
    handleAvatarSuccess(res, file) {
      this.banner.imageUrl = res.data;
      this.$forceUpdate();
    },
    //上传之前调用的方法
    beforeAvatarUpload(file) {
      if (this.allowImageType.indexOf(file.type.toLowerCase()) === -1) {
        this.$message.error("上传文件不符!");
      }

      if (file.size / 1024 / 1024 > 3) {
        this.$message.error("上传Banner图片大小不能超过 3MB!");
        return false;
      }
      return true;
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

.banner-uploader {
  max-width: 1000px;
}
@media screen and (min-width: 300px) and (max-width: 500px) {
  .banner-uploader {
    max-width: 300px;
  }
}
</style>
