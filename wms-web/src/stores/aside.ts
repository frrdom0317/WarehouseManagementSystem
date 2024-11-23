import {defineStore} from "pinia"
import {constantRoutes} from "@/router/routes"
import {reqMenuList} from "@/api/menu"
import {addRoute} from "@/router/routerHooks";

const useAsideStore = defineStore('AsideStore',{
    state(){
        return{
            menuRoutes:constantRoutes,
        }
    },
    actions:{
        //菜单列表
        async menuList(roleId:number){
            let res = await reqMenuList(roleId)
            if (res.code == 200){
                //@ts-ignore
                addRoute(res.data)
                return "ok"
            }else {
                return Promise.reject(new Error(res.msg))
            }
        }
    }
})

export default useAsideStore