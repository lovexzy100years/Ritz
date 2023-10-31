import { createApp } from 'vue'
import App from './App.vue'
import router from './router';
//默认
//createApp(App).mount('#app')
//挂载路由
createApp(App).use(router).mount('#app')
