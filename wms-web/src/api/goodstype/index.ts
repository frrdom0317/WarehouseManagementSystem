//物品分类
import request from "@/util/request";
import type {goodsType, goodsTypeResponse, requestGoodsTypePage} from "@/api/goodstype/type";

enum API {
    LIST_PAGE = '/goodstype/listReturnResult',
    UP_OR_ADD = '/goodstype/saveOrMod',
    DELETE = '/goodstype/delete',
    LIST = '/goodstype/list'
}

//查询
export const reqGoodsTypePage = (data:requestGoodsTypePage) => request.post<any,goodsTypeResponse>(API.LIST_PAGE,data)
//修改或添加
export const reqUpdateOrAddGoodsType = (data:goodsType) => request.post<any,goodsTypeResponse>(API.UP_OR_ADD,data)
//删除
export const reqDeleteGoodsType = (id:number) => request.get<any,goodsTypeResponse>(`${API.DELETE}?id=${id}`)
export const reqGoodsTypeList = ()=>request.get<any,goodsTypeResponse>(API.LIST)