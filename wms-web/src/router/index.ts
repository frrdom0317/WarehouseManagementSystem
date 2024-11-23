import { createRouter, createWebHistory } from 'vue-router'
import {constantRoutes} from "@/router/routes";
import useAsideStore from "@/stores/aside";
import useUserStore from "@/stores/user";
import {createPinia} from "pinia";
import {GET_USER, REMOVE_USER} from "@/util/localstorage";

let asideStore = useAsideStore(createPinia())
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: constantRoutes,
})

router.beforeEach(async (to, from, next) => {
  if (to.path == '/login'){
    if (GET_USER()){
      next('/')
    }else{
      REMOVE_USER()
      next()
    }
  }else {
    let user = GET_USER()
    if (!user){
      next('/login')
    }else {
      try {
        if (asideStore.menuRoutes.length && to.name!= null){
          next()
        }else {
          await asideStore.menuList(user.roleId)
          console.log(router.getRoutes())
          // 如果 addRoutes 并未完成，路由守卫会一层一层的执行执行，直到 addRoutes 完成，找到对应的路由
          next({...to})
          // next()
        }
      }catch (e){
        next('/login')
      }
    }
  }
})
export default router
