<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- /课程列表 开始 -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">全部课程</span>
        </h2>
      </header>
      <section class="c-sort-box">
        <section class="c-s-dl">
          <dl>
            <dt>
              <span class="c-999 fsize14">课程类别</span>
            </dt>
            <dd class="c-s-dl-li">
              <ul class="clearfix">
                <li>
                  <a title="全部" href="#" @click="getTypeList('')">全部</a>
                </li>
                <!-- 一级分类 -->
                <li v-for="(subject, index) in subjectList" :key="index">
                  <a
                    :title="subject.title"
                    href="javascript:void(0);"
                    @click="getTypeList(subject.id, index)"
                    :class="{ active: oneIndex == index }"
                    >{{ subject.title }}</a
                  >
                </li>
              </ul>
            </dd>
          </dl>
          <dl>
            <dt>
              <span class="c-999 fsize14">学科</span>
            </dt>
            <dd class="c-s-dl-li">
              <ul class="clearfix">
                <!-- 二级分类 -->
                <li v-for="(type, index) in typeList" :key="index">
                  <a
                    :title="type.title"
                    href="javascript:void(0);"
                    @click="searchByCondition(type.id, index)"
                    :class="{ active: twoIndex == index }"
                    >{{ type.title }}</a
                  >
                </li>
              </ul>
            </dd>
          </dl>
          <div class="clear"></div>
        </section>
        <div class="js-wrap">
          <section class="fr">
            <span class="c-ccc">
              <i class="c-master f-fM">1</i>/
              <i class="c-666 f-fM">1</i>
            </span>
          </section>
          <section class="fl">
            <ol class="js-tap clearfix">
              <li :class="{ 'current bg-orange': saleCountSort != '' }">
                <a
                  title="销量"
                  href="javascript:void(0);"
                  @click="searchSaleCount()"
                  >销量
                  <span :class="{ hide: saleCountSort == '' }">↓</span>
                </a>
              </li>
              <li :class="{ 'current bg-orange': createTimeSort != '' }">
                <a
                  title="最新"
                  href="javascript:void(0);"
                  @click="searchCreateTime()"
                  >最新
                  <span :class="{ hide: createTimeSort == '' }">↓</span>
                </a>
              </li>
              <li :class="{ 'current bg-orange': priceSort != '' }">
                <a
                  title="价格"
                  href="javascript:void(0);"
                  @click="searchPrice()"
                  >价格&nbsp;
                  <span :class="{ hide: priceSort == '' }">↓</span>
                </a>
              </li>
            </ol>
          </section>
        </div>

        <!-- 课程数据显示 -->
        <div class="mt40">
          <!-- /无数据提示 开始-->
          <section class="no-data-wrap" v-if="Number(data.total) == 0">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam"
              >没有相关数据，小编正在努力整理中...</span
            >
          </section>
          <!-- /无数据提示 结束-->
          <article class="comm-course-list" v-if="data.total > 0">
            <ul class="of" id="bna">
              <li v-for="record in data.records" :key="record.id">
                <div class="cc-l-wrap">
                  <section class="course-img">
                    <img
                      :src="record.cover"
                      class="img-responsive"
                      :alt="record.title"
                    />
                    <div class="cc-mask">
                      <a
                        :href="'/course/' + record.id"
                        title="开始学习"
                        class="comm-btn c-btn-1"
                        >开始学习</a
                      >
                    </div>
                  </section>
                  <h3 class="hLh30 txtOf mt10">
                    <a
                      :href="'/course/' + record.id"
                      :title="record.title"
                      class="course-title fsize18 c-333"
                      >{{ record.title }}</a
                    >
                  </h3>
                  <section class="mt10 hLh20 of">
                    <span
                      class="fr jgTag bg-green"
                      v-if="Number(record.price) === 0"
                    >
                      <i class="c-fff fsize12 f-fA">免费</i>
                    </span>
                    <span
                      class="fr jgTag bg-red"
                      v-if="Number(record.price) > 0"
                    >
                      <i class="c-fff fsize12 f-fA">{{
                        Number(record.price)
                      }}</i>
                    </span>
                    <span class="fl jgAttr c-ccc f-fA">
                      <i class="c-999 f-fA">{{ record.viewCount }}人学习</i>
                      |
                      <i class="c-999 f-fA">9634评论</i>
                    </span>
                  </section>
                </div>
              </li>
            </ul>
            <div class="clear"></div>
          </article>
        </div>

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
              @click.prevent="gotoPage(data.current - 1)"
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
      </section>
    </section>
    <!-- /课程列表 结束 -->
  </div>
</template>
<script>
import courseAPI from "@/api/edu/course";
import subjectAPI from "@/api/edu/subject";

export default {
  data() {
    return {
      // 当前页
      index: 1,
      // 课程分页列表
      data: {},
      // 一级分类列表
      subjectList: [],
      // 二级分类列表
      typeList: [],
      // 查询表单对象
      queryCondition: {},
      oneIndex: -1,
      twoIndex: -1,
      // 关注度排序
      saleCountSort: "",
      // 最新排序
      createTimeSort: "",
      // 价格排序
      priceSort: "",
    };
  },
  created() {
    // 查询第一页数据
    this.queryCourse();
    // 初始化一级分类
    this.getAllSubject();
  },

  methods: {
    // 查询课程列表,第一次进入页面
    queryCourse() {
      courseAPI.pageQueryCourse(1, 8).then((response) => {
        this.data = response.data;
        this.data.pages = Number(this.data.pages);
      });
    },

    // 查询一级分类
    getAllSubject() {
      subjectAPI.getAllSubject().then((response) => {
        this.subjectList = response.data;
      });
    },

    // 分页切换
    gotoPage(index) {
      courseAPI
        .pageQueryCourse(index, 8, this.queryCondition)
        .then((response) => {
          this.data = response.data;
          this.data.pages = Number(this.data.pages);
        });
    },

    // 点击一级分类名称，查询其所有二级分类
    getTypeList(subjectId, index) {
      // 点击某个一级分类。进行查询
      this.queryCondition.subjectId = subjectId;
      // 使样式生效
      this.oneIndex = index;
      // 清空二级样式
      this.twoIndex = -1;
      this.queryCondition.typeId = "";
      this.typeList = [];
      // 点击某个一级分类。进行条件查询
      this.gotoPage(1);
      // 这里查询出某个一级分类下面所有的二级分类，并赋值给typeList
      for (let i = 0; i < this.subjectList.length; i++) {
        if (subjectId === this.subjectList[i].id) {
          this.typeList = this.subjectList[i].children;
        }
      }
    },
    // 点击某个二级分类实现条件查询
    searchByCondition(typeId, index) {
      this.queryCondition.typeId = typeId;
      this.twoIndex = index;
      // 查询
      this.gotoPage(1);
    },
    // 购买量查询
    searchSaleCount() {
      this.saleCountSort = "1";
      this.createTimeSort = "";
      this.priceSort = "";
      this.queryCondition.saleCountSort = this.saleCountSort;
      this.queryCondition.createTimeSort = this.createTimeSort;
      this.queryCondition.priceSort = this.priceSort;
      this.gotoPage(this.index);
    },
    // 更新时间查询
    searchCreateTime() {
      this.saleCountSort = "";
      this.createTimeSort = "1";
      this.priceSort = "";
      this.queryCondition.saleCountSort = this.saleCountSort;
      this.queryCondition.createTimeSort = this.createTimeSort;
      this.queryCondition.priceSort = this.priceSort;
      this.gotoPage(this.index);
    },
    // 价格查询
    searchPrice() {
      this.saleCountSort = "";
      this.createTimeSort = "";
      this.priceSort = "1";
      this.queryCondition.saleCountSort = this.saleCountSort;
      this.queryCondition.createTimeSort = this.createTimeSort;
      this.queryCondition.priceSort = this.priceSort;
      this.gotoPage(this.index);
    },

    //点击全部，清空二级分类列表
    clean() {
      this.subSubjectList = "";
    },
  },
};
</script>
<style scoped>
.active {
  background: #3edeb3;
}
.hide {
  display: none;
}
.show {
  display: block;
}
.bg-red {
  background: red;
}
.cc-l-wrap {
  width: 267.5px;
  height: 220px;
}
.c-s-dl-li .clearfix {
  height: 40px;
}
</style>
