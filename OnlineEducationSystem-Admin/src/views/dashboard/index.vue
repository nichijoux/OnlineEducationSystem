<template>
  <div class="dashboard-container">
    <div>
      <el-row :gutter="40" class="panel-group">
        <!-- 用户人数开始 -->
        <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
          <div class="card-panel" @click="handleSetLineChartData('newVisitis')">
            <div class="card-panel-icon-wrapper icon-people">
              <svg-icon icon-class="peoples" class-name="card-panel-icon" />
            </div>
            <div class="card-panel-description">
              <div class="card-panel-text">用户数</div>
              <count-to
                :start-val="0"
                :end-val="userCount"
                :duration="2600"
                class="card-panel-num"
              />
            </div>
          </div>
        </el-col>
        <!-- 用户人数结束 -->

        <!-- 课程数开始 -->
        <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
          <div class="card-panel" @click="handleSetLineChartData('messages')">
            <div class="card-panel-icon-wrapper icon-message">
              <svg-icon icon-class="course" class-name="card-panel-icon" />
            </div>
            <div class="card-panel-description">
              <div class="card-panel-text">课程数</div>
              <count-to
                :start-val="0"
                :end-val="courseCount"
                :duration="3000"
                class="card-panel-num"
              />
            </div>
          </div>
        </el-col>
        <!-- 课程数结束 -->

        <!-- 订单数开始 -->
        <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
          <div class="card-panel" @click="handleSetLineChartData('purchases')">
            <div class="card-panel-icon-wrapper icon-money">
              <svg-icon icon-class="money" class-name="card-panel-icon" />
            </div>
            <div class="card-panel-description">
              <div class="card-panel-text">订单数</div>
              <count-to
                :start-val="0"
                :end-val="orderCount"
                :duration="3200"
                class="card-panel-num"
              />
            </div>
          </div>
        </el-col>
        <!-- 订单数结束 -->

        <!-- 评论数开始 -->
        <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
          <div class="card-panel" @click="handleSetLineChartData('shoppings')">
            <div class="card-panel-icon-wrapper icon-shopping">
              <svg-icon icon-class="message" class-name="card-panel-icon" />
            </div>
            <div class="card-panel-description">
              <div class="card-panel-text">评论数</div>
              <count-to
                :start-val="0"
                :end-val="commentCount"
                :duration="3600"
                class="card-panel-num"
              />
            </div>
          </div>
        </el-col>
        <!-- 评论数结束 -->
      </el-row>
    </div>
  </div>
</template>

<script>
import CountTo from "vue-count-to";
import dashboardAPI from "@/api/statistics/dashboard";

export default {
  name: "nichijoux-dashboard",
  components: {
    CountTo,
  },
  data() {
    return {
      userCount: 0,
      courseCount: 0,
      orderCount: 0,
      commentCount: 0,
    };
  },
  // 启动后
  created() {
    this.getStatisticsData();
  },
  // 方法区
  methods: {
    // 获取统计数据
    getStatisticsData() {
      dashboardAPI.getStatisticsData().then((response) => {
        this.userCount = Number(response.data.userCount);
        this.courseCount = Number(response.data.courseCount);
        this.orderCount = Number(response.data.orderCount);
        this.commentCount = Number(response.data.commentCount);
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}

.image {
  width: 100px;
  height: 100px;
}
.m-center {
  display: flex;
  justify-content: center;
  align-items: center;
}

.panel-group {
  margin-top: 18px;

  .card-panel-col {
    margin-bottom: 32px;
  }

  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, 0.05);
    border-color: rgba(0, 0, 0, 0.05);

    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }

      .icon-people {
        background: #40c9c6;
      }

      .icon-message {
        background: #36a3f7;
      }

      .icon-money {
        background: #f4516c;
      }

      .icon-shopping {
        background: #34bfa3;
      }
    }

    .icon-people {
      color: #40c9c6;
    }

    .icon-message {
      color: #36a3f7;
    }

    .icon-money {
      color: #f4516c;
    }

    .icon-shopping {
      color: #34bfa3;
    }

    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }

    .card-panel-icon {
      float: left;
      font-size: 48px;
    }

    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;

      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }

      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}

@media (max-width: 550px) {
  .card-panel-description {
    display: none;
  }

  .card-panel-icon-wrapper {
    float: none !important;
    width: 100%;
    height: 100%;
    margin: 0 !important;

    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
    }
  }
}
</style>
