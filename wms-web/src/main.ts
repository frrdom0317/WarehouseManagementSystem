import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import 'element-plus/dist/index.css'
import './assets/global.css'

const app = createApp(App)
//引入自定义插件对象:注册整个项目全局组件
import globalComponent from '@/util/component'

app.use(createPinia())
app.use(router)
app.use(globalComponent)
app.use(ElementPlus,{locale:zhCn})
app.mount('#app')
