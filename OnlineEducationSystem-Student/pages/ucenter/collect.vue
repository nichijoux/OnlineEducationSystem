<template>
  <div class="app-container">
    <article class="col-7 f1">
      <div class="u-r-cont">
        <!-- 收藏的课程 -->
        <section class="mt30">
          <div>
            <header class="comm-title all-teacher-title c-course-content">
              <h2 class="fl tac">
                <span class="c-333">收藏课程</span>
              </h2>
              <section class="c-tab-title">
                <a href="javascript: void(0)">&nbsp;</a>
              </section>
            </header>
            <!-- /无数据提示 开始-->
            <section class="no-data-wrap" v-if="courseList.length == 0">
              <em class="icon30 no-data-ico">&nbsp;</em>
              <span class="c-666 fsize14 ml10 vam"
                >没有收藏的课程哦,快去收藏吧</span
              >
            </section>
            <!-- /无数据提示 结束-->
            <article class="comm-course-list">
              <ul class="of">
                <li v-for="course in courseList" :key="course.id">
                  <div class="cc-l-wrap">
                    <section class="course-img">
                      <img :src="course.cover" class="img-responsive" />
                      <div class="cc-mask">
                        <a
                          :href="'/course/' + course.id"
                          title="开始学习"
                          target="_blank"
                          class="comm-btn c-btn-1"
                          >开始学习</a
                        >
                      </div>
                    </section>
                    <h3 class="hLh30 txtOf mt10">
                      <a
                        :href="'/course/' + course.id"
                        :title="course.title"
                        target="_blank"
                        class="course-title fsize18 c-333"
                        >{{ course.title }}</a
                      >
                    </h3>
                  </div>
                </li>
              </ul>
              <div class="clear"></div>
            </article>
          </div>
        </section>

        <!-- 公共分页 开始 -->
        <div>
          <div class="paging">
            <!-- undisable这个class是否存在，取决于数据属性hasPrevious -->
            <a
              :class="{ undisable: !data.hasPrevious }"
              href="#"
              title="首页"
              @click.prevent="gotoPage(1)"
              >首页</a
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
              title="尾页"
              @click.prevent="gotoPage(data.pages)"
              >尾页</a
            >

            <div class="clear"></div>
          </div>
        </div>
        <!-- 公共分页 结束 -->
      </div>
    </article>
  </div>
</template>

<script>
import courseAPI from "@/api/edu/course";

export default {
  layout: "center",
  // 数据区
  data() {
    return {
      // 当前页
      page: 1,
      // 每页记录数
      limit: 8,
      // 收藏课程列表
      courseList: [],
      // 分页数据
      data: {},
    };
  },
  // 渲染前执行
  created() {
    this.gotoPage();
  },
  // 方法区
  methods: {
    // 获取收藏的课程
    gotoPage(page = 1) {
      this.page = page;
      courseAPI
        .pageQueryCollectCourse(this.page, this.limit)
        .then((response) => {
          this.data = response.data;
          this.data.pages = Number(this.data.pages);
          this.courseList = this.data.records;
        });
    },
  },
};
</script>

<style scoped></style>
