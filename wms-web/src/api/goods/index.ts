//物品分类
import request from "@/util/request";
import type {goods, goodsResponse, requestGoodsPage} from "@/api/goods/type";

enum API {
    LIST_PAGE = '/goods/listReturnResult',
    UP_OR_ADD = '/goods/saveOrMod',
    DELETE = '/goods/delete'
}

//查询
export const reqGoodsPage = (data:requestGoodsPage) => request.post<any,goodsResponse>(API.LIST_PAGE,data)
//修改或添加
export const reqUpdateOrAddGoods = (data:goods) => request.post<any,goodsResponse>(API.UP_OR_ADD,data)
//删除
export const reqDeleteGoods = (id:number) => request.get<any,goodsResponse>(`${API.DELETE}?id=${id}`)