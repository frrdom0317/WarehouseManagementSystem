export interface Menu{
    id: number,
    menucode: string,
    menuname: string,
    menulevel: string,
    menuparentcode: null,
    menuclick: string,
    menuright: string,
    menucomponent: string,
    menuicon: string
}

export interface MenuResult {
    code:number,
    msg:string,
    total:number,
    data?:Menu[]
}