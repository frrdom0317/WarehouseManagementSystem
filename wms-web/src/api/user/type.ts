export interface userInfo{
    id?:number
    age?:number|string
    isValid?:string
    name?:string
    no:string
    password:string
    phone?:number|string
    roleId?:number
    sex?:number
}

export interface userListResponse {
    code:number
    data?:userInfo[]
    msg:string
    total?:number
}
interface arg{
    name:string,
    sex:number | string,
    roleId:number | string
}

export interface requestUserPage{
    pageSize:number,
    pageNum:number,
    param?:arg
}