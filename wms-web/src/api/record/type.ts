export interface recordRes{
    id?:number,
    goods?:number,
    userId?:number,
    adminId?:number,
    count?:number,
    createTime?:Date,
    remark?:string,
    username?:string,
    adminname?:string,
    storagename?:string,
    goodsname:string,
    goodstypename?:string,
    action:number //1入库 2出库
}

export interface recordResponse {
    code:number
    data?:recordRes[]
    msg:string
    total?:number
}

interface arg {
    name:string,//物品名
    goodstype:number|string,
    storage:number|string
}
export interface requestRecordPage {
    pageSize:number,
    pageNum:number,
    param?:arg
}