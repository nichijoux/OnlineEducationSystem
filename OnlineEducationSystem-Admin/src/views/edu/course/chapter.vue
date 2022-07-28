<template>
  <div class="app-container">
    <h2 style="text-align: center">发布新课程</h2>
    <!-- element-ui步骤开始 -->
    <el-steps
      :active="2"
      finish-status="success"
      simple
      style="margin-bottom: 40px"
    >
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="发布课程" />
    </el-steps>
    <!-- element-ui步骤结束 -->

    <!-- 添加章节按钮 -->
    <el-button type="text" @click="openChapterDialog()">添加章节</el-button>

    <!-- 章节小节显示 -->
    <ul class="chapterList">
      <li v-for="chapter in chapterVideoList" :key="chapter.id">
        <p>
          {{ chapter.title }}

          <span class="acts">
            <!--chapter p 样式加 position: relative;，act 样式加 position: relative;z-index: 1;-->
            <el-button type="text" @click="openVideoDialog(chapter.id)"
              >添加小节</el-button
            >
            <el-button type="text" @click="openEditChapterDialog(chapter.id)"
              >编辑</el-button
            >
            <el-button
              type="text"
              @click="deleteChapter(chapter.id, chapter.title)"
              >删除</el-button
            >
          </span>
        </p>

        <!-- 视频 -->
        <ul class="chapterList videoList">
          <li v-for="video in chapter.children" :key="video.id">
            <p>
              {{ video.title }}

              <span class="acts">
                <!--chapter p 样式加 position: relative;，act 样式加 position: relative;z-index: 1;-->

                <el-button
                  style=""
                  type="text"
                  @click="openEditVideoDialog(video.id)"
                  >编辑</el-button
                >
                <el-button
                  type="text"
                  @click="deleteVideo(video.id, video.title)"
                  >删除</el-button
                >
              </span>
            </p>
          </li>
        </ul>
      </li>
    </ul>

    <!-- 上下步,路由按钮 -->
    <div align="center">
      <el-button type="primary" @click="previous">上一步</el-button>
      <el-button type="primary" @click="next">下一步</el-button>
    </div>

    <!-- 添加和修改章节表单 -->
    <el-dialog :visible.sync="dialogChapterFormVisible" title="添加章节">
      <el-form :model="chapter" label-width="120px">
        <el-form-item label="章节标题">
          <el-input v-model="chapter.title" />
        </el-form-item>
        <el-form-item label="章节排序">
          <el-input-number
            v-model="chapter.sort"
            :min="0"
            controls-position="right"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addOrUpdateChapter">确 定</el-button>
        <el-button @click="dialogChapterFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加和修改课时表单 -->
    <el-dialog :visible.sync="dialogVideoFormVisible" title="添加课时">
      <el-form :model="video" label-width="120px">
        <el-form-item label="课时标题">
          <el-input v-model="video.title" />
        </el-form-item>
        <el-form-item label="课时排序">
          <el-input-number
            v-model="video.sort"
            :min="0"
            controls-position="right"
          />
        </el-form-item>
        <el-form-item label="是否免费">
          <el-radio-group v-model="video.isFree">
            <el-radio :label="1" :value="1" selected>免费</el-radio>
            <el-radio :label="0" :value="0">默认</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="上传视频">
          <!-- 上传视频开始 -->
          <el-upload
            :before-remove="handleBeforeVideoDelete"
            :on-remove="handleVideoDelete"
            :on-exceed="handleUploadExceed"
            :on-success="handleUploadSuccess"
            :file-list="fileList"
            :action="BASE_API"
            :limit="1"
            class="upload-demo"
          >
            <el-button size="small" type="primary">上传视频</el-button>
            <el-tooltip placement="right-end">
              <div slot="content">
                最大支持1G，<br />
                支持3GP、ASF、AVI、DAT、DV、FLV、F4V、<br />
                GIF、M2T、M4V、MJ2、MJPEG、MKV、MOV、MP4、<br />
                MPE、MPG、MPEG、MTS、OGG、QT、RM、RMVB、<br />
                SWF、TS、VOB、WMV、WEBM 等视频格式上传
              </div>
              <i class="el-icon-question" />
            </el-tooltip>
          </el-upload>
          <!-- 上传视频结束 -->
        </el-form-item>
      </el-form>
      <!-- 弹框按钮开始 -->
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addOrUpdateVideo">确 定</el-button>
        <el-button @click="dialogVideoFormVisible = false">取 消</el-button>
      </div>
      <!-- 弹框按钮结束 -->
    </el-dialog>

    <!-- 快速回到顶部开始 -->
    <el-backtop></el-backtop>
    <!-- 快速回到顶部结束 -->
  </div>
</template>

<script>
import chapterAPI from "@/api/edu/chapter";
import videoAPI from "@/api/edu/video";
import vodAPI from "@/api/vod/vod";

export default {
  data() {
    return {
      // 课程id
      courseId: "",
      // 章节小节列表
      chapterVideoList: [],
      // 封装章节数据
      chapter: {
        // 章节标题
        title: "",
        // 章节排序
        sort: 0,
      },
      // 封装小节数据
      video: {
        // 小节名称
        title: "",
        // 章节id
        chapterId: "",
        // 小节排序
        sort: 0,
        // 是否免费,1免费,0收费
        isFree: 1,
        // 云端视频资源
        videoSourceId: "",
        // 原始文件名称
        videoOriginalName: "",
      },
      // 章节弹框
      dialogChapterFormVisible: false,
      // 小节弹框
      dialogVideoFormVisible: false,
      // 文件上传列表
      fileList: [],
      // 接口API
      BASE_API: "http://localhost:8500/admin/vod/uploadAliVideo",
    };
  },
  created() {
    if (this.$route.params && this.$route.params.id) {
      this.courseId = this.$route.params.id;
      // 根据课程id查询所有的章节和小节
      this.getAllChapterAndVideo();
    }
  },
  methods: {
    // 根据课程id获取所有课程章节和小节
    getAllChapterAndVideo() {
      chapterAPI.getAllChapterAndVideo(this.courseId).then((response) => {
        this.chapterVideoList = response.data;
      });
    },
    // 打开章节弹框
    openChapterDialog() {
      this.dialogChapterFormVisible = true;
      // 表单数据清空
      this.chapter.title = "";
      this.chapter.sort = 0;
    },
    // 修改章节框数据回显
    openEditChapterDialog(chapterId) {
      this.dialogChapterFormVisible = true;
      // 调用接口获取数据
      chapterAPI.getChapter(chapterId).then((response) => {
        this.chapter = response.data;
      });
    },
    // 打开小节弹框
    openVideoDialog(chapterId) {
      this.dialogVideoFormVisible = true;
      // 清空数据
      this.video = {
        // 小节名称
        title: "",
        // 章节id
        chapterId: chapterId,
        // 小节排序
        sort: 0,
        // 是否免费,1免费,0收费
        isFree: 1,
        // 云端视频资源
        videoSourceId: "",
        // 原始文件名称
        videoOriginalName: "",
      };
      this.fileList = [];
    },
    // 修改小节弹框数据回显
    openEditVideoDialog(videoId) {
      this.dialogVideoFormVisible = true;
      // 调用接口获取数据
      videoAPI.getVideo(videoId).then((response) => {
        this.video = response.data;
      });
    },
    // 添加章节
    addChapter() {
      //设置课程id到chapter对象里面
      this.chapter.courseId = this.courseId;
      chapterAPI.addChapter(this.chapter).then((response) => {
        // 关闭弹框
        this.dialogChapterFormVisible = false;
        // 提示
        this.$message.success("添加章节成功");
        // 刷新页面
        this.getAllChapterAndVideo();
      });
    },
    // 更新章节
    updateChapter() {
      chapterAPI.updateChapter(this.chapter).then((response) => {
        // 关闭弹框
        this.dialogChapterFormVisible = false;
        // 提示
        this.$message.success("修改章节成功");
        // 刷新页面
        this.getAllChapterAndVideo();
      });
    },
    // 删除章节
    deleteChapter(chapterId, chapterTitle) {
      this.$confirm(
        `该操作将删除章节[ ${chapterTitle} ]及其下属小节,是否继续`,
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      ).then(() => {
        chapterAPI.deleteChapter(chapterId).then((response) => {
          // 删除成功
          this.$message.success("删除章节成功");
          // 刷新页面
          this.getAllChapterAndVideo();
        });
      });
    },
    // 添加或者修改章节信息
    addOrUpdateChapter() {
      if (this.chapter.id) {
        this.updateChapter();
      } else {
        this.addChapter();
      }
    },
    // 添加小节
    addVideo() {
      this.video.courseId = this.courseId;
      videoAPI.addVideo(this.video).then((response) => {
        // 关闭弹框
        this.dialogVideoFormVisible = false;
        // 提示信息
        this.$message.success("添加小节成功");
        // 刷新页面
        this.getAllChapterAndVideo();
      });
    },
    // 修改小节
    updateVideo() {
      videoAPI.updateVideo(this.video).then((response) => {
        // 关闭弹框
        this.dialogVideoFormVisible = false;
        // 提示信息
        this.$message.success("修改小节成功");
        // 刷新页面
        this.getAllChapterAndVideo();
      });
    },
    // 删除小节
    deleteVideo(videoId, videoTitle) {
      this.$confirm(`此操作将删除小节:[ ${videoTitle} ]及其视频`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        videoAPI.deleteVideo(videoId).then((response) => {
          // 提示信息
          this.$message.success("删除小节成功");
          // 刷新页面
          this.getAllChapterAndVideo();
        });
      });
    },
    // 添加或者更新小节
    addOrUpdateVideo() {
      if (this.video.id) {
        this.updateVideo();
      } else {
        this.addVideo();
      }
    },
    // 上传视频成功的回调函数
    handleUploadSuccess(response, file, fileList) {
      // 上传视频id赋值
      this.video.videoSourceId = response.data;
      // 上传视频名称赋值
      this.video.videoOriginalName = file.name;
    },
    // 删除前提示
    handleBeforeVideoDelete(file, fileList) {
      return this.$confirm(`确定移除${file.name}？`);
    },
    // 删除上传的视频的回调函数
    handleVideoDelete() {
      vodAPI.deleteAliVideo(this.video.videoSourceId).then((response) => {
        this.$message.success("删除成功");
        // 文件列表清空
        this.fileList = [];
        // 视频id和视频名称清空
        this.video.videoSourceId = "";
        this.video.videoOriginalName = "";
      });
    },
    // 上传达到限度的回调函数
    handleUploadExceed() {
      this.$message.warning("想要重新上传视频,请先删除已上传的视频");
    },

    // 返回到编辑课程基本信息
    previous() {
      this.$router.push({ path: "/course/info/" + this.courseId });
    },
    next() {
      // 去到发布课程
      this.$router.push({ path: "/course/publish/" + this.courseId });
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

.chapterList {
  position: relative;
  list-style: none;
  margin: 0;
  padding: 0;
}
.chapterList li {
  position: relative;
}
.chapterList p {
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border: 1px solid #ddd;
  background-color: rgb(170, 226, 243);
  border-radius: 10px;
}
.chapterList .acts {
  float: right;
  font-size: 14px;
}

.videoList {
  padding-left: 50px;
}
.videoList p {
  float: left;
  font-size: 14px;
  margin: 10px 0;
  padding: 10px;
  height: 50px;
  line-height: 30px;
  width: 100%;
  border: 1px dotted #ddd;
  border-radius: 10px;
  background-color: rgb(224, 246, 152);
}
</style>
