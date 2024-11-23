//引入element-plus提供的全部的图标组件
import * as ElemetPlusIconsVue from '@element-plus/icons-vue'

//使用插件 遍历注册所有图标组件
export default {
    install(app: any) {
        //@ts-ignore
        for (const [key, component] of Object.entries(ElemetPlusIconsVue)) {
            app.component(key, component)
        }
    }
}