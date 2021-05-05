import Vue from 'vue'
import Router from 'vue-router'
import { normalizeURL, decode } from 'ufo'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'

const _363ee3fa = () => interopDefault(import('..\\pages\\patient\\index.vue' /* webpackChunkName: "pages/patient/index" */))
const _26fe807e = () => interopDefault(import('..\\pages\\user\\index.vue' /* webpackChunkName: "pages/user/index" */))
const _05e0e27d = () => interopDefault(import('..\\pages\\weixin\\callback.vue' /* webpackChunkName: "pages/weixin/callback" */))
const _1129b71b = () => interopDefault(import('..\\pages\\hospital\\detail\\_hoscode.vue' /* webpackChunkName: "pages/hospital/detail/_hoscode" */))
const _25f0853c = () => interopDefault(import('..\\pages\\hospital\\notice\\_hoscode.vue' /* webpackChunkName: "pages/hospital/notice/_hoscode" */))
const _a83ba5ec = () => interopDefault(import('..\\pages\\hospital\\_hoscode.vue' /* webpackChunkName: "pages/hospital/_hoscode" */))
const _59754d6c = () => interopDefault(import('..\\pages\\index.vue' /* webpackChunkName: "pages/index" */))

const emptyFn = () => {}

Vue.use(Router)

export const routerOptions = {
  mode: 'history',
  base: '/',
  linkActiveClass: 'nuxt-link-active',
  linkExactActiveClass: 'nuxt-link-exact-active',
  scrollBehavior,

  routes: [{
    path: "/patient",
    component: _363ee3fa,
    name: "patient"
  }, {
    path: "/user",
    component: _26fe807e,
    name: "user"
  }, {
    path: "/weixin/callback",
    component: _05e0e27d,
    name: "weixin-callback"
  }, {
    path: "/hospital/detail/:hoscode?",
    component: _1129b71b,
    name: "hospital-detail-hoscode"
  }, {
    path: "/hospital/notice/:hoscode?",
    component: _25f0853c,
    name: "hospital-notice-hoscode"
  }, {
    path: "/hospital/:hoscode?",
    component: _a83ba5ec,
    name: "hospital-hoscode"
  }, {
    path: "/",
    component: _59754d6c,
    name: "index"
  }],

  fallback: false
}

export function createRouter (ssrContext, config) {
  const base = (config._app && config._app.basePath) || routerOptions.base
  const router = new Router({ ...routerOptions, base  })

  // TODO: remove in Nuxt 3
  const originalPush = router.push
  router.push = function push (location, onComplete = emptyFn, onAbort) {
    return originalPush.call(this, location, onComplete, onAbort)
  }

  const resolve = router.resolve.bind(router)
  router.resolve = (to, current, append) => {
    if (typeof to === 'string') {
      to = normalizeURL(to)
    }
    return resolve(to, current, append)
  }

  return router
}
