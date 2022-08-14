<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- /课程详情 开始 -->

    <!-- 课程分类  开始 -->
    <section class="container">
      <section class="path-wrap txtOf hLh30">
        <a href="/" title class="c-999 fsize14">首页</a>
        \
        <a href="/course" title class="c-999 fsize14">课程列表</a>
        \
        <span class="c-333 fsize14">{{ course.subjectName }}</span>
        \
        <span class="c-333 fsize14">{{ course.typeName }}</span>
        \
        <a href="#" title class="c-999 fsize14">{{ course.title }}</a>
      </section>
      <!-- 课程分类  结束 -->
      <div>
        <article class="c-v-pic-wrap" style="height: 357px">
          <section class="p-h-video-box" id="videoPlay">
            <img
              :src="course.cover"
              :alt="course.title"
              class="dis c-v-pic myImg"
            />
          </section>
        </article>
        <aside class="c-attr-wrap">
          <section class="ml20 mr15">
            <h2 class="hLh30 txtOf mt15">
              <span class="c-fff fsize24">{{ course.title }}</span>
            </h2>
            <section class="c-attr-jg">
              <span class="c-fff">价格：</span>
              <b class="c-yellow" style="font-size: 24px"
                >￥{{ course.price }}</b
              >
            </section>
            <section class="c-attr-mt c-attr-undis">
              <span class="c-fff fsize14"
                >主讲： {{ course.teacherName }}&nbsp;&nbsp;&nbsp;</span
              >
            </section>
            <section class="c-attr-mt of">
              <span class="ml10 vam">
                <em
                  class="icon18 scIcon"
                  style="background: yellow"
                  v-if="haveCollectCourse"
                ></em>
                <em class="icon18 scIcon" v-else></em>
                <el-button
                  type="text"
                  @click="collectCourse"
                  v-if="!haveCollectCourse"
                  >收藏</el-button
                >
                <el-button type="text" @click="cancelCollectCourse" v-else
                  >取消收藏</el-button
                >
              </span>
            </section>
            <section
              class="c-attr-mt"
              v-if="isbuyCourse || Number(course.price) == 0"
            >
              <a
                :href="'/player/' + 'cc03514545394a79b111e37eb86e855c'"
                title="立即观看"
                class="comm-btn c-btn-3"
                >立即观看</a
              >
            </section>
            <section class="c-attr-mt" v-else>
              <a
                @click="createOrder()"
                href="#"
                title="立即购买"
                class="comm-btn c-btn-3"
                >立即购买</a
              >
            </section>
          </section>
        </aside>
        <aside class="thr-attr-box">
          <ol class="thr-attr-ol">
            <li>
              <span class="c-fff f-fM">购买数</span>
              <br />
              <h6 class="c-fff f-fM mt10">{{ course.saleCount }}</h6>
            </li>
            <li>
              <aside>
                <span class="c-fff f-fM">课时数</span>
                <br />
                <h6 class="c-fff f-fM mt10">{{ course.lessonNum }}</h6>
              </aside>
            </li>
            <li>
              <aside>
                <span class="c-fff f-fM">浏览数</span>
                <br />
                <h6 class="c-fff f-fM mt10">{{ course.viewCount }}</h6>
              </aside>
            </li>
          </ol>
        </aside>
        <div class="clear"></div>
      </div>
      <!-- 课程封面、大纲介绍 -->
      <div class="mt20 c-infor-box">
        <article class="fl col-7">
          <section class="mr30">
            <div class="i-box">
              <div>
                <section id="c-i-tabTitle" class="c-infor-tabTitle c-tab-title">
                  <a name="c-i" class="current" title="课程详情">课程详情</a>
                </section>
              </div>
              <article class="ml10 mr10 pt20">
                <!-- 课程大纲(章节,视频) -->
                <div>
                  <h6 class="c-g-content c-infor-title">
                    <span>课程大纲</span>
                  </h6>
                  <section class="mt20">
                    <div class="lh-menu-wrap">
                      <menu id="lh-menu" class="lh-menu mt10 mr10">
                        <ul>
                          <!-- 文件目录 -->
                          <li
                            v-for="chapter in chapterList"
                            :key="chapter.id"
                            class="lh-menu-stair"
                          >
                            <a
                              href="javascript: void(0)"
                              :title="chapter.title"
                              class="current-1"
                            >
                              <em class="lh-menu-i-1 icon18 mr10"></em
                              >{{ chapter.title }}
                            </a>
                            <ol class="lh-menu-ol" style="display: block">
                              <li
                                v-for="video in chapter.children"
                                :key="video.id"
                                class="lh-menu-second ml30"
                              >
                                <a
                                  v-if="
                                    isbuyCourse || Number(course.price) == 0
                                  "
                                  :href="'/player/' + video.videoSourceId"
                                  title
                                  target="_blank"
                                  @click="
                                    addStudyRecord(
                                      video.title,
                                      video.videoSourceId
                                    )
                                  "
                                >
                                  <span
                                    class="fr"
                                    v-if="
                                      isbuyCourse || Number(course.price) == 0
                                    "
                                  >
                                    <i class="free-icon vam mr10">立即观看</i>
                                  </span>
                                  <span class="fr" v-else>
                                    <i
                                      class="free-icon vam mr10"
                                      style="background-color: red"
                                      >立即购买</i
                                    >
                                  </span>
                                  <em class="lh-menu-i-2 icon16 mr5">&nbsp;</em
                                  >{{ video.title }}
                                </a>
                                <a v-else @click="pleaseBuy"
                                  ><span
                                    class="fr"
                                    v-if="
                                      isbuyCourse || Number(course.price) == 0
                                    "
                                  >
                                    <i class="free-icon vam mr10">立即观看</i>
                                  </span>
                                  <span class="fr" v-else>
                                    <i
                                      class="free-icon vam mr10"
                                      style="background-color: red"
                                      >立即购买</i
                                    >
                                  </span>
                                  <em class="lh-menu-i-2 icon16 mr5">&nbsp;</em
                                  >{{ video.title }}
                                </a>
                              </li>
                            </ol>
                          </li>
                        </ul>
                      </menu>
                    </div>
                  </section>
                </div>
                <!-- 课程介绍 -->
                <div>
                  <h6 class="mt50 c-i-content c-infor-title">
                    <span>课程介绍</span>
                  </h6>
                  <div class="course-txt-body-wrap">
                    <section class="course-txt-body">
                      <p v-html="course.description">
                        {{ course.description }}
                      </p>
                    </section>
                  </div>
                </div>
              </article>
            </div>
          </section>
        </article>
        <aside class="fl col-3">
          <div class="i-box">
            <div>
              <section class="c-infor-tabTitle c-tab-title">
                <a title href="javascript:void(0)">主讲讲师</a>
              </section>
              <section class="stud-act-list">
                <ul style="height: auto">
                  <li>
                    <div class="u-face">
                      <a :href="'/teacher/' + course.teacherId" target="_blank">
                        <img :src="course.avatar" width="50" height="50" alt />
                      </a>
                    </div>
                    <section class="hLh30 txtOf">
                      <a class="c-333 fsize16 fl" href="#">{{
                        course.teacherName
                      }}</a>
                    </section>
                    <section class="hLh20 txtOf">
                      <span class="c-999">{{ course.intro }}</span>
                    </section>
                  </li>
                </ul>
              </section>
            </div>
          </div>
        </aside>
        <div class="clear"></div>
      </div>
      <!-- 课程评论 -->
      <div class="mt50 commentHtml">
        <div>
          <h6 class="c-c-content c-infor-title" id="i-art-comment">
            <span class="commentTitle">💬课程评论</span>
          </h6>
          <section class="lh-bj-list pr mt20 replyhtml">
            <ul>
              <li class="unBr">
                <aside class="noter-pic">
                  <img
                    width="50"
                    height="50"
                    class="picImg"
                    src="~/assets/img/avatar-boy.gif"
                  />
                </aside>
                <div class="of">
                  <section class="n-reply-wrap">
                    <el-rate
                      show-text
                      v-model="comment.star"
                      :colors="['#99A9BF', '#F7BA2A', '#FF9900']"
                    >
                    </el-rate>
                    <fieldset>
                      <textarea
                        name=""
                        v-model="comment.content"
                        placeholder="输入您要评论的文字"
                        id="commentContent"
                      ></textarea>
                    </fieldset>
                    <p class="of mt5 tar pl10 pr10">
                      <span class="fl"
                        ><tt
                          class="c-red commentContentmeg"
                          style="display: none"
                        ></tt
                      ></span>
                      <input
                        type="button"
                        @click="addComment()"
                        value="回复"
                        class="lh-reply-btn"
                      />
                    </p>
                  </section>
                </div>
              </li>
            </ul>
          </section>
          <section class="">
            <section class="question-list lh-bj-list pr">
              <ul class="pr10">
                <li v-for="(comment, index) in data.records" v-bind:key="index">
                  <aside class="noter-pic">
                    <img
                      width="50"
                      height="50"
                      class="picImg"
                      :src="comment.avatar"
                    />
                  </aside>
                  <div class="of">
                    <span class="fl">
                      <font class="fsize12 c-blue">
                        {{ comment.nickname }}</font
                      >
                      <font class="fsize12 c-999 ml5">评论：</font></span
                    >
                  </div>
                  <div>
                    <el-rate
                      show-text
                      disabled
                      v-model="comment.star"
                      :colors="['#99A9BF', '#F7BA2A', '#FF9900']"
                    ></el-rate>
                  </div>
                  <div class="noter-txt mt5">
                    <p>{{ comment.content }}</p>
                  </div>
                  <div class="of mt5">
                    <span class="fr"
                      ><font class="fsize12 c-999 ml5">{{
                        comment.createTime
                      }}</font></span
                    >
                  </div>
                </li>
              </ul>
            </section>
          </section>
        </div>
      </div>
      <!-- 课程评论 结束 -->

      <!-- 公共分页 开始 -->
      <div class="paging">
        <!-- undisable这个class是否存在，取决于数据属性hasPrevious -->
        <a
          :class="{ undisable: !data.hasPrevious }"
          href="#"
          title="首页"
          @click.prevent="gotoPage(1)"
          >首</a
        >
        <a
          v-if="data.hasPrevious"
          href="#"
          title="前一页"
          @click.prevent="gotoPage(Number(data.current) - 1)"
          >&lt;</a
        >
        <a
          v-for="page in data.pages"
          :key="page"
          :class="{
            current: data.current == page,
            undisable: data.current == page,
          }"
          :title="'第' + page + '页'"
          href="#"
          @click.prevent="gotoPage(page)"
          >{{ page }}</a
        >
        <a
          v-if="data.hasNext"
          href="#"
          title="后一页"
          @click.prevent="gotoPage(Number(data.current) + 1)"
          >&gt;</a
        >
        <a
          :class="{ undisable: !data.hasNext }"
          href="#"
          title="末页"
          @click.prevent="gotoPage(data.pages)"
          >末</a
        >
        <div class="clear" />
      </div>
      <!-- 公共分页 结束 -->
    </section>
    <!-- /课程详情 结束 -->
  </div>
</template>
<script>
import courseAPI from "@/api/edu/course";
import commentAPI from "@/api/edu/comment";
import orderAPI from "@/api/order/order";
import studyAPI from "@/api/edu/record";

export default {
  data() {
    return {
      // 评论数据
      data: {},
      // 当前页
      page: 1,
      // 每页记录数
      limit: 4,
      // 总数据
      total: 10,
      // 评论实体
      comment: {
        content: "",
        courseId: "",
        // 评论星级
        star: 5,
      },
      // 课程实体
      course: {},
      // 章节列表
      chapterList: [],
      // 是否购买了课程
      isbuyCourse: false,
      // 是否收藏了课程
      haveCollectCourse: false,
    };
  },
  created() {
    this.courseId = this.$route.params.id;
    //页面渲染之前执行
    this.initCourseInfo();
    this.initComment();
  },
  methods: {
    //获取课程详情
    initCourseInfo() {
      courseAPI.getCourseInfo(this.courseId).then((response) => {
        this.course = response.data.courseUserInfoVO;
        this.chapterList = response.data.chapterAndVideoList;
        this.isbuyCourse = response.data.haveBuyCourse;
        this.haveCollectCourse = response.data.haveCollectCourse;
      });
    },

    // 获取评论
    initComment() {
      commentAPI
        .pageQueryComment(this.courseId, this.page, this.limit)
        .then((response) => {
          this.data = response.data;
          this.data.pages = Number(this.data.pages);
        });
    },

    // 添加评论
    addComment() {
      if (this.comment.content == "") {
        alert("请输入评论");
      } else {
        this.comment.courseId = this.courseId;
        commentAPI.addComment(this.comment).then((response) => {
          if (response.success) {
            //提示信息
            this.$message({
              type: "success",
              message: "评论成功",
            });
            //清空评论框
            this.comment.content = "";
            //刷新评论列表
            this.initComment();
          } else {
            //提示信息
            this.$message.error("请先登录");
          }
        });
      }
    },

    // 分页查询评论
    gotoPage(page) {
      commentAPI
        .pageQueryComment(this.courseId, page, this.limit)
        .then((response) => {
          this.data = response.data;
          this.data.pages = Number(this.data.pages);
        });
    },

    //创建订单
    createOrder() {
      orderAPI.createOrder(this.courseId).then((response) => {
        //获取订单id
        //生成订单后，跳转到订单显示页面
        this.$router.push({ path: "/orders/" + response.data }); //动态路由页面
      });
    },
    // 请购买弹窗
    pleaseBuy() {
      this.$message.warning("请购买课程");
    },
    // 收藏课程
    collectCourse() {
      courseAPI.collectCourse(this.courseId).then((response) => {
        this.$message.success("收藏课程成功");
        this.haveCollectCourse = true;
      });
    },
    // 取消收藏课程
    cancelCollectCourse() {
      courseAPI.cancelCollectCourse(this.courseId).then((response) => {
        this.$message.warning("取消收藏课程成功");
        this.haveCollectCourse = false;
      });
    },
    // 添加学习记录
    addStudyRecord(videoName, videoSourceId) {
      // 调用api
      studyAPI
        .addStudyRecord(
          this.course.teacherName,
          this.course.title,
          this.course.cover,
          videoName,
          videoSourceId,
          this.course.id
        )
        .then((response) => {});
    },
  },
};
</script>

<style scoped>
.myImg {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
}
</style>
