export interface goods {
    id?:number,
    name:string,
    storage:number|string,
    goodstype:number|string,
    count:number|string,
    remark:string
}
interface arg {
    name:string,
    goodstype:number|string,
    storage:number|string
}
export interface requestGoodsPage {
    pageSize:number,
    pageNum:number,
    param?:arg
}
export interface goodsResponse{
    code:number
    data?:goods[]
    msg:string
    total?:number
}