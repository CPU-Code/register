import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// 修改语言为 zh-CN，使用中文语言环境
import locale from 'element-ui/lib/locale/lang/zh-CN' // lang i18n

import '@/styles/index.scss' // global css

import App from './App'
// 引入路由模块
import router from './router'
import store from './store'

import '@/icons' // icon
import '@/permission' // permission control

Vue.use(ElementUI, { locale })

Vue.config.productionTip = false

// 项目js入口文件，项目的所有前端功能都在这个文件中引入和定义，并初始化全局的Vue对象

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
