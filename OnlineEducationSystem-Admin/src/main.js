import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/zh-CN' // lang i18n

// 滑动验证码
import SlideVerify from 'vue-monoplasty-slide-verify'

import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'

import '@/icons' // icon
import '@/permission' // permission control

// 判断是否有按钮权限
import { hasButtonPermission } from './utils/permission'

// set ElementUI lang to EN
Vue.use(ElementUI, { locale })
// 如果想要中文版 element-ui，按如下方式声明
// Vue.use(ElementUI)

Vue.use(SlideVerify)

Vue.config.productionTip = false

// 判断有无权限
Vue.prototype.hasPermission = hasButtonPermission

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
