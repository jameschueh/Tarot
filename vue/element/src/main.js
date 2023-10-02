// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueParticles from 'vue-particles'
import axios from 'axios'
import moment from 'moment'

Vue.config.productionTip = false

Vue.use(ElementUI);

Vue.use(router);

Vue.use(VueParticles);

Vue.prototype.$axios = axios

Vue.filter('datefmt', function (input, fmtstring) {
  return moment(input).format(fmtstring);
});

Vue.prototype.$axios = axios;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router: router,
  components: { App },
  template: '<App/>'
})

const instance = axios.create({
  baseURL: 'http://localhost:8888/api', // API 地址
});

instance.interceptors.response.use(
  (response) => response,
  (error) => {
    console.error('請求失敗:', error);
    return Promise.reject(error);
  }
);

export default instance;
