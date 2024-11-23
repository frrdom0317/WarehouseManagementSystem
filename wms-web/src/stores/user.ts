import {defineStore} from "pinia"
import type {requestUserPage, userInfo, userListResponse} from "@/api/user/type"
import {
    reqAddUser,
    reqDeleteUser,
    reqFindNo,
    reqUpdateOrAddUser,
    reqUpdateUser, reqUserList,
    reqUserLogin,
    reqUserPage
} from "@/api/user";
import {ElMessage} from "element-plus";
import {GET_USER, REMOVE_USER, SET_USER,} from "@/util/localstorage";

const useUserStore = defineStore("UserStore",{
    actions: {
        //查询用户
        async userInfo(data: requestUserPage) {
            let res: userListResponse = await reqUserPage(data)
            if (res.code == 200) {
                return res
            } else {
                ElMessage({
                    type: 'error',
                    message: '获取数据失败'
                })
                return Promise.reject(res.msg)
            }
        },
        //增加用户
        async userAdd(data: userInfo) {
            let res: userListResponse = await reqAddUser(data)
            if (res.code == 200) {
                return "ok"
            } else {
                ElMessage({
                    type: 'error',
                    message: '添加用户失败'
                })
                return Promise.reject(res.msg)
            }
        },
        //账户是否存在
        async noExist(no: string) {
            let res:userListResponse = await reqFindNo(no)
            if (res.code != 200) {
                return "ok"
            }else {
                return Promise.reject(res.msg)
            }

        },
        //修改用户
        async userMod(data:userInfo){
            let res = await reqUpdateUser(data)
            if (res.code == 200){
                return "ok"
            }else {
                return Promise.reject(res.msg)
            }
        },
        //删除用户
        async userRemove(id:number){
            let res = await reqDeleteUser(id)
            if (res.code == 200){
                return "ok"
            }else {
                return Promise.reject(res.msg)
            }
        },
        //修改或添加用户
        async userModOrAdd(data: userInfo){
            let res = await reqUpdateOrAddUser(data)
            if (res.code == 200){
                return "ok"
            }else {
                return Promise.reject(new Error(res.msg))
            }
        },
        //用户登录
        async userLogin(data:userInfo){
            let res = await reqUserLogin(data)
            if (res.code == 200){
                SET_USER(res.data)
                this.user = GET_USER()
                return "ok"
            }else {
                return Promise.reject(new Error(res.msg))
            }
        },
        async userInfoList(){
            let res = await reqUserList()
            if (res.code == 200)
                return res
            else
                return Promise.reject(res.msg)
        },
        //退出登录
        userLogout(){
            REMOVE_USER()
            this.user = ""
        }
    },
    state:()=>{
        return {
            user:GET_USER() || ""
        }
    }
})
export default useUserStore