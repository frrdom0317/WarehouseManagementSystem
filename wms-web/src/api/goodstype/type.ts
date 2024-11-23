export interface goodsType {
    id?:number,
    name:string,
    remark:string
}
interface arg {
    name:string
}
export interface requestGoodsTypePage {
    pageSize:number,
    pageNum:number,
    param?:arg
}
export interface goodsTypeResponse{
    code:number
    data?:goodsType[]
    msg:string
    total?:number
}