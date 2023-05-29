import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import 'font-awesome/css/font-awesome.min.css'

import { getSessionStorage } from './common.js'

router.beforeEach(function(to, from, next) {
    let user = getSessionStorage('user');
    //除了登陆、注册、首页、商家列表、商家信息之外，都需要判断是否登陆了
    if (!(to.path == '/' || to.path == '/index' || to.path == '/businessList' || to.path == '/businessInfo' || to.path ==
            '/login' || to.path == '/register')) {
        if (user == null) {
            router.push('/login');
        }
    }
    next();
});

createApp(App).use(store).use(router).mount('#app')
