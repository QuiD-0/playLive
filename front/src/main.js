import {createApp} from 'vue'
import App from './App.vue'
import Router from './module/router.js'

createApp(App)
    .use(Router)
    .mount('#app')
