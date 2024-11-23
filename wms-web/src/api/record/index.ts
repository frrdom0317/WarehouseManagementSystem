//记录管理相关的api
import request from "@/util/request";
import type {recordRes, recordResponse, requestRecordPage} from "@/api/record/type";

enum API {
    LIST_PAGE = '/record/listReturnResult',
    ADD = '/record/save'
}

//查询记录列表 可以传入物品id、物品分类id、仓库id 去做条件查询
export const reqRecordList = (data:requestRecordPage)=> request.post<any,recordResponse>(API.LIST_PAGE,data)
//新增记录 出库or入库
export const reqAddRecord = (data:recordRes) => request.post<any,recordResponse>(API.ADD,data)