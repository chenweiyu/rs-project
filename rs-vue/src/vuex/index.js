import Vue from 'vue'
import Vuex from 'vuex'

//状态
Vue.use(Vuex)

const vuex= new Vuex.Store({
  //变量
  state: {
    baseURL:'http://localhost:9001/'   //后台API根路径
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})
//抛出，外面可以引用
export default vuex
