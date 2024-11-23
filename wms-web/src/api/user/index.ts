import request from "@/util/request"
import type {userListResponse, requestUserPage, userInfo} from "@/api/user/type"

enum API {
    LIST = '/user/list',
    LIST_P = '/user/listP',
    LIST_PAGE = '/user/listReturnResult',
    ADD_USER="/user/save",
    FIND_NO = "/user/findByNo",
    UPDATE_USER = "/user/mod",
    DELETE_USER = "/user/delete",
    UP_OR_ADD = "/user/saveOrMod",
    LOGIN_USER = '/user/login'
}

//获取所有
export const reqUserList = () => request.get<any,userListResponse>(API.LIST_P)
export const reqUserPage= (data: requestUserPage) => request.post<any,userListResponse>(API.LIST_PAGE,data)

//新增用户
export const reqAddUser = (data:userInfo) => request.post<any,userListResponse>(API.ADD_USER,data)

//账号是否存在
export const reqFindNo = (no:string) => request.get<any,userListResponse>(`${API.FIND_NO}?no=${no}`)

//修改用户
export const reqUpdateUser = (data:userInfo) => request.post<any,userListResponse>(API.UPDATE_USER,data)

//删除用户
export const reqDeleteUser = (id:number) => request.get<any,userListResponse>(`${API.DELETE_USER}?id=${id}`)
//修改或添加
export const reqUpdateOrAddUser = (data:userInfo) => request.post<any,userListResponse>(API.UP_OR_ADD,data)
//用户登录
export const reqUserLogin = (data:userInfo) => request.post<any,userListResponse>(API.LOGIN_USER,data)