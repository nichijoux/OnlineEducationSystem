<template>
  <div>
    <!-- 幻灯片 开始 -->
    <div v-swiper:mySwiper="swiperOption">
      <div class="swiper-wrapper">
        <!-- 将bannerList单向绑定到banner上用于显示 -->
        <div
          v-for="banner in bannerList"
          :key="banner.id"
          class="swiper-slide"
          style="background: #040b1b"
        >
          <img
            :src="banner.imageUrl"
            :alt="banner.title"
            style="height: 100%"
          />
        </div>
      </div>
      <div class="swiper-pagination swiper-pagination-white"></div>
      <div
        class="swiper-button-prev swiper-button-white"
        slot="button-prev"
      ></div>
      <div
        class="swiper-button-next swiper-button-white"
        slot="button-next"
      ></div>
    </div>
    <!-- 幻灯片 结束 -->

    <div id="aCoursesList">
      <!-- 网校课程 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">热门课程</span>
            </h2>
          </header>
          <div>
            <article class="comm-course-list">
              <ul class="of" id="bna">
                <li v-for="(course, index) in courseList" v-bind:key="index">
                  <div class="cc-l-wrap">
                    <section class="course-img">
                      <img
                        :src="course.cover"
                        class="img-responsive"
                        :alt="course.title"
                      />
                      <div class="cc-mask">
                        <a
                          :href="'/course/' + course.id"
                          title="开始学习"
                          class="comm-btn c-btn-1"
                          >开始学习</a
                        >
                      </div>
                    </section>
                    <h3 class="hLh30 txtOf mt10">
                      <a
                        href="#"
                        :title="course.title"
                        class="course-title fsize18 c-333"
                        >{{ course.title }}</a
                      >
                    </h3>
                    <section class="mt10 hLh20 of">
                      <!-- v-if做判断,价格为0才显示免费 -->
                      <span
                        class="fr jgTag bg-green"
                        v-if="Number(course.price) === 0"
                      >
                        <i class="c-fff fsize12 f-fA">免费</i>
                      </span>
                      <span class="fr jgTag bg-red" v-else>
                        <i class="c-fff fsize12 f-fA"> ￥{{ course.price }}></i>
                      </span>
                      <!-- {{}}为插值表达式 -->
                      <span class="fl jgAttr c-ccc f-fA">
                        <i class="c-999 f-fA">{{ course.buyCount }}人学习</i>
                        |
                        <i class="c-999 f-fA">{{ course.viewCount }}浏览</i>
                      </span>
                    </section>
                  </div>
                </li>
              </ul>
              <div class="clear"></div>
            </article>
            <section class="tac pt20">
              <a href="/course" title="全部课程" class="comm-btn c-btn-2"
                >全部课程</a
              >
            </section>
          </div>
        </section>
      </div>
      <!-- /网校课程 结束 -->

      <!-- 网校名师 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">名师大咖</span>
            </h2>
          </header>
          <div>
            <article class="i-teacher-list">
              <ul class="of">
                <li v-for="(teacher, index) in teacherList" v-bind:key="index">
                  <section class="i-teach-wrap">
                    <div class="i-teach-pic">
                      <a :href="'/teacher/' + teacher.id" :title="teacher.name">
                        <img :alt="teacher.name" :src="teacher.avatar" />
                      </a>
                    </div>
                    <div class="mt10 hLh30 txtOf tac">
                      <a
                        :href="'/teacher/' + teacher.id"
                        :title="teacher.name"
                        class="fsize18 c-666"
                        >{{ teacher.name }}</a
                      >
                    </div>
                    <div class="hLh30 txtOf tac">
                      <span class="fsize14 c-999">{{ teacher.intro }}</span>
                    </div>
                    <div class="mt15 i-q-txt">
                      <p class="c-999 f-fA">{{ teacher.career }}</p>
                    </div>
                  </section>
                </li>
              </ul>
              <div class="clear"></div>
            </article>
            <section class="tac pt20">
              <a href="/teacher" title="全部讲师" class="comm-btn c-btn-2"
                >全部讲师</a
              >
            </section>
          </div>
        </section>
      </div>
      <!-- /网校名师 结束 -->
    </div>
  </div>
</template>

<script>
import bannerAPI from "@/api/cms/banner";
import indexAPI from "@/api/index";

export default {
  data() {
    return {
      swiperOption: {
        //自动切换幻灯片
        autoplay: true,
        //切换速度
        speed: 300,
        //配置分页
        pagination: {
          //分页的dom节点
          el: ".swiper-pagination",
        },
        //配置导航
        navigation: {
          nextEl: ".swiper-button-next", //下一页dom节点
          prevEl: ".swiper-button-prev", //前一页dom节点
        },
      },
      //banner数组
      bannerList: [],
      //热门讲师数组
      teacherList: [],
      //热门课程数组
      courseList: [],
    };
  },
  created() {
    // 获取banner信息
    this.getBannerList();
    // 获取热门老师和课程信息
    this.getHotTeacherAndHotCourse();
  },
  methods: {
    // 获取Banner列表
    getBannerList() {
      bannerAPI.getAllBanner().then((response) => {
        this.bannerList = response.data;
      });
    },
    // 获取热门老师和课程的信息
    getHotTeacherAndHotCourse() {
      indexAPI.getHotTeacherAndHotCourse().then((response) => {
        this.teacherList = response.data.hotTeacher;
        this.courseList = response.data.hotCourse;
      });
    },
  },
};
</script>

<style scoped>
.bannerHeight {
  height: 500px;
}
.bg-red {
  background: red;
}
.cc-l-wrap {
  width: 267.5px;
  height: 220px;
}
.i-teach-wrap {
  width: 267.5px;
  height: 330px;
}
</style>
