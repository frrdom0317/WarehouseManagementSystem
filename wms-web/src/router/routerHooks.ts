import type {Menu} from "@/api/menu/type";
import router from "@/router/index"
import type {RouteRecordRaw} from "vue-router";
// vue3 + vite中的动态引入组件的方法
const loadView = import.meta.glob('../views/**/*.vue')
export const addRoute=(menuList:Menu[])=>{
    let routes = router.options.routes
    let menus = loadView
    routes.forEach(item => {
        if (item.path=='/'){
            menuList.forEach(m =>{
                let str = '../views/'+m.menucomponent
                let dynamicRoute = {
                    name: m.menuclick,
                    path: '/' + m.menuclick,
                    meta: {title: m.menuname, icon: m.menuicon, show: true},
                    component: loadView[`../views/${m.menucomponent}`]
                }
                item.children?.push(dynamicRoute)
                //清空路由 防止多次添加
                //@ts-ignore
                router.removeRoute(item.name)
            })
            router.addRoute(item)
        }
    })
}
