<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- 讲师列表 开始 -->
    <section class="container">
      <header class="comm-title all-teacher-title">
        <h2 class="fl tac">
          <span class="c-333">全部讲师</span>
        </h2>
        <section class="c-tab-title">
          <a id="subjectAll" title="全部" href="#"></a>
        </section>
      </header>
      <section class="c-sort-box unBr">
        <div>
          <!-- /无数据提示 开始-->
          <section class="no-data-wrap" v-if="data.total == 0">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam"
              >没有相关数据，小编正在努力整理中...</span
            >
          </section>
          <!-- /无数据提示 结束-->

          <!-- 数据列表  开始 -->
          <article class="i-teacher-list" v-if="data.total > 0">
            <ul class="of">
              <li v-for="teacher in data.records" :key="teacher.id">
                <section class="i-teach-wrap">
                  <div class="i-teach-pic">
                    <a
                      :href="'/teacher/' + teacher.id"
                      :title="teacher.name"
                      
                    >
                      <img
                        :src="teacher.avatar"
                        :alt="teacher.name"
                        height="142"
                      />
                    </a>
                  </div>
                  <div class="mt10 hLh30 txtOf tac">
                    <a
                      :href="'/teacher/' + teacher.id"
                      :title="teacher.name"
                      target="_blank"
                      class="fsize18 c-666"
                      >{{ teacher.name }}</a
                    >
                  </div>
                  <div class="hLh30 txtOf tac">
                    <span class="fsize14 c-999">{{ teacher.career }}</span>
                  </div>
                  <div class="mt15 i-q-txt">
                    <p class="c-999 f-fA">{{ teacher.intro }}</p>
                  </div>
                </section>
              </li>
            </ul>
            <div class="clear"></div>
          </article>
        </div>
        <!-- 数据列表 结束 -->

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
      </section>
    </section>
    <!-- /讲师列表 结束 -->
  </div>
</template>
<script>
//导入api
import teacherAPI from "@/api/edu/teacher";

export default {
  data() {
    return {
      // 当前页
      page: 1,
      // 返回的数据,包括records,total等数据
      data: {},
    };
  },
  // 渲染前执行
  created() {
    this.gotoPage(1);
  },
  methods: {
    gotoPage(page = 1) {
      this.page = page;
      teacherAPI.pageQueryTeacher(page, 8).then((response) => {
        this.data = response.data;
        // 需要在此进行类型转换,延迟到渲染时转换会莫名其妙报一个Invalid array length的错误
        this.data.pages = Number(this.data.pages);
      });
    },
  },
};
</script>
