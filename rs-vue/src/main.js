// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import vuex from './vuex'
import qs from "qs"
import * as echarts from 'echarts'
axios.defaults.baseURL = 'http://localhost:9004'

Vue.prototype.$axios = axios
Vue.config.productionTip = false

Vue.use(ElementUI)


//挂载到vue原型，相当于vue实例多了一个属性 $http，可以使用this.$http
Vue.prototype.$http = axios

// this.$qs
Vue.prototype.$qs=qs;

Vue.prototype.$echarts = echarts

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  vuex,
  render: h => h(App),
  components: { App },
  template: '<App/>'
})
