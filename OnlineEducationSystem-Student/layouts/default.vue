<template>
  <div class="in-wrap">
    <!-- 公共头引入 -->
    <header id="header">
      <section class="container">
        <h1 id="logo">
          <a href="#" title="OES在线教育系统">
            <img
              src="~/assets/img/logo.png"
              width="100%"
              alt="OES在线教育系统"
            />
          </a>
        </h1>
        <div class="h-r-nsl">
          <!-- 固定路由跳转 -->
          <ul class="nav">
            <router-link to="/" tag="li" active-class="current" exact>
              <a>首页</a>
            </router-link>
            <router-link to="/course" tag="li" active-class="current">
              <a>课程</a>
            </router-link>
            <router-link to="/teacher" tag="li" active-class="current">
              <a>名师</a>
            </router-link>
            <!-- <router-link to="/article" tag="li" active-class="current">
              <a>文章</a>
            </router-link>
            <router-link to="/qa" tag="li" active-class="current">
              <a>问答</a>
            </router-link> -->
          </ul>
          <!-- / nav -->
          <ul class="h-r-login">
            <!-- 判断是否显示用户信息 -->
            <li v-if="!loginInfo.id" id="no-login">
              <a href="/login" title="登录">
                <em class="icon18 login-icon">&nbsp;</em>
                <span class="vam ml5">登录</span>
              </a>
              |
              <a href="/register" title="注册">
                <span class="vam ml5">注册</span>
              </a>
            </li>
            <li v-if="loginInfo.id" id="is-login-one" class="mr10">
              <a id="headerMsgCountId" href="#" title="消息">
                <em class="icon18 news-icon">&nbsp;</em>
              </a>
              <q class="red-point" style="display: none">&nbsp;</q>
            </li>
            <li v-if="loginInfo.id" id="is-login-two" class="h-r-user">
              <a href="/ucenter/basic" title>
                <el-dropdown>
                  <span class="el-dropdown-link">
                    <img
                      :src="loginInfo.avatar"
                      width="30"
                      height="30"
                      class="vam picImg"
                      alt
                    />
                    <span id="userName" class="vam disIb">{{
                      loginInfo.nickname
                    }}</span
                    ><i class="el-icon-arrow-down el-icon--right"></i>
                  </span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item
                      ><router-link :to="{ path: '/ucenter/basic' }"
                        >基本信息</router-link
                      ></el-dropdown-item
                    >
                    <el-dropdown-item>
                      <router-link :to="{ path: '/ucenter/password' }"
                        >修改密码</router-link
                      ></el-dropdown-item
                    >
                  </el-dropdown-menu>
                </el-dropdown>
              </a>
              <a
                href="javascript:void(0);"
                title="退出"
                @click="logout()"
                class="ml5"
                >退出</a
              >
            </li>
            <!-- /未登录显示第1 li；登录后显示第2，3 li -->
          </ul>
          <aside class="h-r-search">
            <form action="#" method="post">
              <label class="h-r-s-box">
                <input
                  type="text"
                  placeholder="输入你想学的课程"
                  name="queryCourse.courseName"
                  value
                />
                <button type="submit" class="s-btn">
                  <em class="icon18">&nbsp;</em>
                </button>
              </label>
            </form>
          </aside>
        </div>
        <aside class="mw-nav-btn">
          <div class="mw-nav-icon"></div>
        </aside>
        <div class="clear"></div>
      </section>
    </header>

    <!-- /公共头引入 -->

    <!-- 加载pages目录文件 -->
    <nuxt />
    <!-- 加载pages目录文件 -->

    <!-- 公共底引入 -->
    <footer id="footer">
      <section class="container">
        <div class>
          <h4 class="hLh30">
            <span class="fsize18 f-fM c-999">友情链接</span>
          </h4>
          <ul class="of flink-list">
            <li>
              <a
                href="https://github.com/nichijoux/"
                title="nichijoux"
                target="_blank"
                >nichijoux</a
              >
            </li>
          </ul>
          <div class="clear"></div>
        </div>
        <div class="b-foot">
          <section class="fl col-7">
            <section class="mr20">
              <section class="b-f-link">
                <a href="#" title="关于我们" target="_blank">关于我们</a>|
                <a href="#" title="联系我们" target="_blank">联系我们</a>|
                <a href="#" title="帮助中心" target="_blank">帮助中心</a>|
                <a href="#" title="资源下载" target="_blank">资源下载</a>|
                <span>服务热线：010-111111(北京) 0755-1313115(深圳)</span>
                <span>Github：nichijoux</span>
              </section>
              <section class="b-f-link mt10">
                <span>©2022课程 版权鸣谢谷粒学院</span>
              </section>
            </section>
          </section>
          <aside class="fl col-3 tac mt15">
            <section class="gf-tx">
              <span>
                <img src="~/assets/img/wx-icon.png" alt />
              </span>
            </section>
            <section class="gf-tx">
              <span>
                <img src="~/assets/img/wb-icon.png" alt />
              </span>
            </section>
          </aside>
          <div class="clear"></div>
        </div>
      </section>
    </footer>
    <!-- /公共底引入 -->
  </div>
</template>

<script>
import "~/assets/css/reset.css";
import "~/assets/css/theme.css";
import "~/assets/css/global.css";
import "~/assets/css/web.css";
import "~/assets/css/base.css";
import "~/assets/css/activity_tab.css";
import "~/assets/css/bottom_rec.css";
import "~/assets/css/nice_select.css";
import "~/assets/css/order.css";
import "~/assets/css/swiper-3.3.1.min.css";
import "~/assets/css/pages-weixinpay.css";

import cookie from "js-cookie";
import userAPI from "@/api/login";

export default {
  data() {
    return {
      token: "",
      loginInfo: {
        id: "",
        openid: "",
        mobile: "",
        password: "",
        nickname: "",
        sex: "",
        age: "",
        avatar: "",
      },
    };
  },
  created() {
    //获取url中的token
    this.token = this.$route.query.token;
    //如果url中有token，表示采用微信登录
    if (this.token) {
      this.wxLogin();
    }
    //展示用户信息
    this.showUserInfo();
  },
  methods: {
    wxLogin() {
      if (this.token == "") return;

      //把token存入cookie中,token domain修改
      cookie.set("oes_token", this.token, { domain: "localhost" });

      //登录成功根据token获取用户信息
      userAPI.getUserInfo().then((response) => {
        this.loginInfo = response.data;
        //将用户信息存入cookie,token domain修改
        cookie.set("oes_ucenter", JSON.stringify(this.loginInfo), {
          domain: "localhost",
        });
      });
    },

    //左上角显示登录后的用户信息
    showUserInfo() {
      //从cookie中获取用户信息,从cookie中获取到的是字符串，而页面显示需要json
      // var userStr = "{'name' : 'wcd','age':'20'}"
      //采用JSON.parse将字符串转化为json  {'name' : wcd ,'age' : 20}
      var userStr = cookie.get("oes_ucenter");
      if (typeof(userStr) != "undefined" && userStr != "") {
        console.log(userStr)
        this.loginInfo = JSON.parse(userStr);
      }
    },

    //退出账号
    logout() {
      // domain 修改
      cookie.set("oes_ucenter", "", { domain: "localhost" });
      cookie.set("oes_token", "", { domain: "localhost" });

      window.location.href = "/";
    },
  },
};
</script>
