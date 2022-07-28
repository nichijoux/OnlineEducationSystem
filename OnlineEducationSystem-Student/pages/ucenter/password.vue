<template>
  <article class="col-7 fl">
    <div class="u-r-cont">
      <section v-if="!isUseWeChat">
        <!-- 手机号 -->
        <div style="margin-right: 10%">
          <el-form label-width="30px" :model="params">
            <el-form-item
              class="input-prepend restyle no-radius"
              prop="mobile"
              :rules="[
                { required: true, message: '请输入手机号码', trigger: 'blur' },
                { validator: checkPhone, trigger: 'blur' },
              ]"
            >
              <div>
                <el-input
                  type="text"
                  placeholder="请输入该用户的手机号"
                  v-model="params.mobile"
                />
                <i class="iconfont icon-phone" />
              </div>
            </el-form-item>

            <!-- 验证码 -->
            <el-form-item
              class="input-prepend restyle no-radius"
              prop="code"
              :rules="[
                { required: true, message: '请输入验证码', trigger: 'blur' },
              ]"
            >
              <div
                style="
                  width: 100%;
                  display: block;
                  float: right;
                  position: relative;
                "
              >
                <el-input
                  type="text"
                  placeholder="验证码"
                  v-model="params.code"
                />
                <i class="iconfont icon-phone" />
              </div>
              <div
                class="btn"
                style="position: absolute; right: 0; top: 6px; width: 20%"
              >
                <a
                  href="javascript:"
                  type="button"
                  @click="getCodeFun()"
                  :value="codeTest"
                  style="border: none; background-color: none"
                  >{{ codeTest }}</a
                >
              </div>
            </el-form-item>

            <!-- 密码 -->
            <el-form-item
              class="input-prepend"
              prop="password"
              :rules="[
                { required: true, message: '请输入密码', trigger: 'blur' },
              ]"
            >
              <div>
                <el-input
                  type="password"
                  placeholder="设置新密码"
                  v-model="params.password"
                />
                <i class="iconfont icon-password" />
              </div>
            </el-form-item>

            <el-form-item>
              <el-button
                :disabled="saveBtnDisabled"
                plain="true"
                type="primary"
                @click="submitChange()"
                >修改</el-button
              >
            </el-form-item>
          </el-form>
        </div>
      </section>
      <section v-else>
        <div class="zh">您使用的是微信登录,无法修改密码</div>
      </section>
    </div>
  </article>
</template>

<script>
import loginAPI from "@/api/login";
import registerAPI from "@/api/register";
import cookie from "js-cookie";
import MD5 from "js-md5";

export default {
  layout: "center",
  data() {
    return {
      token: "",
      loginInfo: {
        id: "",
        age: "",
        avatar: "",
        mobile: "",
        nickname: "",
        sex: "",
      },
      memberInfo: {
        id: "",
        age: "",
        avatar: "",
        mobile: "",
        nickname: "",
        sex: "",
        sign: "",
      },
      params: {
        //封装注册输入的数据
        mobile: "", //手机号
        code: "", //验证码
        nickname: "", //昵称
        password: "",
      },
      sending: true, //是否发送验证码
      second: 60, //倒计时间
      codeTest: "获取验证码",
      saveBtnDisabled: false, //保存按钮是否禁用
      isUseWeChat: false, // 是否使用微信
    };
  },
  created() {
    //获取路径里面token值
    this.token = this.$route.query.token;
    if (this.token) {
      //判断路径是否有token值
      this.wxLogin();
    }
    this.showInfoFromCookie();
  },

  methods: {
    //修改密码提交的方法
    submitChange() {
      this.params.password = MD5(this.params.password);
      loginAPI.updatePassword(this.params).then((response) => {
        //提示密码修改成功
        this.$message.success("修改密码成功! ✌");
        //跳转到登陆页面
        this.$router.push({
          path: "/login",
        });
      });
    },

    //验证码
    //给已经输入的手机号发送验证码
    getCodeFun() {
      registerAPI.getVerificationCode(this.params.mobile).then((response) => {
        //提示发送验证码成功
        this.$message.success("验证码已发送🆗");
        //点完发送  60秒内不让再发
        this.sending = false;
        //调用倒计时的方法
        this.timeDown();
      });
    },

    //定时器  发送验证码倒数
    timeDown() {
      let result = setInterval(() => {
        //setInterval定时器
        --this.second;
        this.codeTest = this.second;
        if (this.second < 1) {
          clearInterval(result);
          this.sending = true;
          this.second = 60;
          this.codeTest = "获取验证码";
        }
      }, 1000);
    },

    save() {
      loginAPI.updataUserInfo(this.memberInfo).then((response) => {
        //修改成功
        //提示成功
        this.$message.success("修改成功！ 🧙‍♂️");
        this.showInfoFromCookie();
      });
    },

    //从cookie中获取用户信息
    showInfoFromCookie() {
      //从cookie中获取用户信息
      var userStr = cookie.get("oes_ucenter");
      //userStr是字符串   需要转换为json对象
      if (userStr) {
        this.loginInfo = JSON.parse(userStr);
        userStr = JSON.parse(userStr);
      }
      this.isUseWeChat = String(userStr.openid) != "null";

      loginAPI.getUserInfo().then((response) => {
        this.loginInfo = response.data;
        cookie.set("oes_ucenter", JSON.stringify(this.loginInfo), {
          domain: "localhost",
        });
      });
    },

    //微信登录显示的方法
    wxLogin() {
      //把token值放到cookie里面
      cookie.set("oes_token", this.token, {
        domain: "localhost",
      });

      //调用接口，根据token值获取用户信息
      loginAPI.getUserInfo().then((response) => {
        this.loginInfo = response.data;
      });
    },

    // 正则表达式判断手机号是否合规
    checkPhone(rule, value, callback) {
      if (!/^1[34578]\d{9}$/.test(value)) {
        return callback(new Error("手机号码格式不正确"));
      }
      return callback();
    },
  },
};
</script>

<style>
.zh {
  font-size: 30px;
  position: absolute;
  margin: auto;
}
</style>
