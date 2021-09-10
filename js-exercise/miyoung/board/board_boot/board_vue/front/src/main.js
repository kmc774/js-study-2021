import Vue from 'vue'
import App from './App.vue'
import router from '@/routes/router' // Path를 잡아준다.
import Axios from 'axios' // Axios 전역처리
import {BoardService} from "@/service/BoardService";


// ------------ [s] BootStrap ------------
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css' // BootStrap

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
// ------------ [e] BootStrap ------------

Vue.config.productionTip = false
Vue.prototype.$axios = Axios;
Vue.prototype.$setDataAttributes = function ( target, src ) {
   Object.keys(src).forEach(function (key) {
      Vue.set(target, key, src[key]); // 또는
   })
}

const APP_PORT = process.env.VUE_APP_PORT;
new Vue({ // 앱을 Mount 할 때 router를 사용하겠다는 뜻
   router, // router.js의 export값과 같아야 한다.
   provide : {
      boardService: new BoardService(APP_PORT)
   },

   render: h => h(App)
}).$mount('#app')





