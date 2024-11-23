
export interface storage {
    id?:number,
    name:string,
    remark:string
}
export interface storageResponse {
    code:number
    data?:storage[]
    msg:string
    total?:number
}
interface arg {
    name:string
}
export interface requestStoragePage {
    pageSize:number,
    pageNum:number,
    param?:arg
}