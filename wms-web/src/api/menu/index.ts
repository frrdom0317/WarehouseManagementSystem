//菜单相关api接口
import request from "@/util/request";
import type {MenuResult} from "@/api/menu/type";
enum API {
    LIST_MENU = "/menu/list"
 }
 //根据用户的roleId去查询所对应的菜单数据
 export const reqMenuList =  (roleId:number)=>request.get<any,MenuResult>(`${API.LIST_MENU}?roleId=${roleId}`)