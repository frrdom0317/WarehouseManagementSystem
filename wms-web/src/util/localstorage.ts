
export const SET_USER = (data:any)=>{
    localStorage.setItem("user",JSON.stringify(data))
}

export const GET_USER = ()=>{
    return JSON.parse(localStorage.getItem('user') as string)
}

export const REMOVE_USER = ()=>{
    localStorage.removeItem('user')
}