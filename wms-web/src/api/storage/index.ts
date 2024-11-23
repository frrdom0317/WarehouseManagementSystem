//仓库管理相关API接口

import request from "@/util/request";
import type {requestStoragePage, storage, storageResponse} from "@/api/storage/type";

enum API {
    UP_OR_ADD='/storage/saveOrMod',
    DELETE = '/storage/delete',
    LIST_PAGE = '/storage/listReturnResult',
    LIST = '/storage/list'
}

//添加或修改
export const reqUpdateOrAddStorage = (data:storage) => request.post<any,storageResponse>(API.UP_OR_ADD,data)

//删除
export const reqDeleteStorage = (id : number) => request.get<any,storageResponse>(`${API.DELETE}?id=${id}`)

//查询
export const reqStoragePage = (data:requestStoragePage) => request.post<any,storageResponse>(API.LIST_PAGE,data)

export const reqStorageList = () => request.get<any,storageResponse>(API.LIST)