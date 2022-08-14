<template>
  <div class="app-container">
    <article class="col-7 f1">
      <div class="u-r-cont">
        <!-- 时间线 -->
        <el-timeline>
          <el-timeline-item
            v-for="(study, index) in studyList"
            :key="index"
            :timestamp="study.studyTime"
            placement="top"
          >
            <el-card>
              <section>
                <img :src="study.cover" />
              </section>
              <h3 class="hLh30 txtOf mt10">
                <a
                  :href="'/course/' + study.id"
                  :title="study.title"
                  target="_blank"
                  class="course-title fsize18 c-333"
                  >{{ study.title }}</a
                >
                ——
                <a
                  :href="'/player/' + study.videoSourceId"
                  :title="study.title"
                  target="_blank"
                  class="course-title fsize18 c-333"
                  >{{ study.videoName }}</a
                >
              </h3>
            </el-card>
          </el-timeline-item>
        </el-timeline>

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
import studyAPI from "@/api/edu/record";

export default {
  layout: "center",
  // 数据区
  data() {
    return {
      // 当前页
      page: 1,
      // 每页记录数
      limit: 8,
      // 学习课程列表
      studyList: [],
      // 分页数据
      data: {},
    };
  },
  // 渲染前执行
  created() {
    this.gotoPage(1);
  },
  // 方法区
  methods: {
    gotoPage(page) {
      this.page = page;
      studyAPI
        .pageQueryUserStudyRecord(this.page, this.limit)
        .then((response) => {
          this.data = response.data;
          this.data.pages = Number(this.data.pages);
          this.studyList = this.data.records;
        });
    },
  },
};
</script>

<style scoped>
.m-center {
  display: block;
  max-width: 800px;
  margin: 2% auto;
}
</style>
