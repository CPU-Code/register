'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

// 定义全局常量值

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  // BASE_API 定义开发环境下的后端api接口的主机地址
  //BASE_API: '"https://easy-mock.com/mock/5950a2419adc231f356a6636/vue-admin"',
  //BASE_API: '"http://localhost:8201"',
  BASE_API: '"http://localhost:8202"',
})
