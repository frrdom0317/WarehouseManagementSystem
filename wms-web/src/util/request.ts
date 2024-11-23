import axios from "axios"
import {ElMessage} from "element-plus"

const request = axios.create({
    baseURL:'http://localhost:8090/',
    timeout: 5000
})

request.interceptors.response.use(res => {
    //简化数据
    return res.data
},error => {
    let status = error.status
    let message = ''
    switch (status){
        case 401:
            message = 'token过期'
            break;
        case 403:
            message = '无权访问'
            break;
        case 404:
            message = '请求路径出错'
            break
        case 500:
            message = '服务器出错'
            break
        default:
            message = '网络异常'
    }
    ElMessage({
        type:"error",
        message
    })
})
export default request