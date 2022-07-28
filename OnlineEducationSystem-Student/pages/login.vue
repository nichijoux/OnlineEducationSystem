<template>
  <div class="main">
    <div class="title">
      <a class="active" href="/login">登录</a>
      <span>·</span>
      <a href="/register">注册</a>
    </div>

    <div class="sign-up-container">
      <el-form ref="userForm" :model="user">
        <el-form-item
          class="input-prepend restyle"
          prop="mobile"
          :rules="[
            { required: true, message: '请输入手机号码', trigger: 'blur' },
            { trigger: 'blur' },
          ]"
        >
          <div>
            <el-input type="text" placeholder="手机号" v-model="user.mobile" />
            <i class="iconfont icon-phone" />
          </div>
        </el-form-item>

        <el-form-item
          class="input-prepend"
          prop="password"
          :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]"
        >
          <div>
            <el-input
              type="password"
              placeholder="密码"
              v-model="user.password"
            />
            <i class="iconfont icon-password" />
          </div>
        </el-form-item>

        <div class="btn">
          <input
            type="button"
            class="sign-in-button"
            value="登录"
            @click="submitLogin()"
          />
        </div>
        <div class="btn">
          <input
            type="button"
            class="return-home-page-button"
            value="返回首页"
            @click="returnHomePage()"
          />
        </div>
      </el-form>
      <!-- 更多登录方式 -->
      <div class="more-sign">
        <h6>社交帐号登录</h6>
        <ul>
          <li>
            <a
              id="weixin"
              class="weixin"
              target="_blank"
              href="http://localhost:8160/api/ucenter/wx/userLogin"
              ><i class="iconfont icon-weixin"
            /></a>
          </li>
          <li>
            <a id="qq" class="qq" target="_blank" href="#"
              ><i class="iconfont icon-qq"
            /></a>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import "~/assets/css/sign.css";
import "~/assets/css/iconfont.css";
import cookie from "js-cookie";
import loginAPI from "@/api/login";
import MD5 from "js-md5";

export default {
  layout: "sign",
  data() {
    return {
      user: {
        mobile: "",
        password: "",
      },
      //登录成功的用户数据
      loginInfo: {
        id: "",
        age: "",
        avatar: "",
        mobile: "",
        nickname: "",
        sex: "",
      },
    };
  },
  created() {},

  methods: {
    //第一步：调用接口进行登录，返回token字符串
    submitLogin() {
      // 需要先将密码进行MD5加密
      this.user.password = MD5(this.user.password);

      loginAPI.submitLogin(this.user).then((response) => {
        //第二步：将token放到cookie
        //cookie.set(name,value,domain作用范围)  domain:表示cookie在什么样的请求才会传递
        //只要带localhost:的url都会传递该cookie

        cookie.set("oes_token", response.data, { domain: "localhost" });

        //第三步：request.js中创建http request拦截器

        //第四步：调用接口，根据token获取用户信息，为了首页面显示
        loginAPI.getUserInfo().then((response) => {
          //获取返回的用户信息，存入cookie中

          this.loginInfo = response.data;

          cookie.set("oes_ucenter", JSON.stringify(this.loginInfo), {
            domain: "localhost",
          });

          if (cookie) {
            //登录成功，跳转到首页
            window.location.href = "/";
          }
        });
      });
    },
    returnHomePage() {
      window.location.href = "/";
    },
  },
};
</script>

<style scoped>
.el-form-item__error {
  z-index: 9999999;
}
.return-home-page-button {
  margin-top: 20px;
  width: 100%;
  padding: 9px 18px;
  font-size: 18px;
  border: none;
  border-radius: 25px;
  color: #ffffff;
  background-color: #31d071;
  cursor: pointer;
}
</style>
