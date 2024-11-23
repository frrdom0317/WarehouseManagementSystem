import {defineStore} from "pinia"
import type {goodsType, requestGoodsTypePage} from "@/api/goodstype/type";
import {reqDeleteGoodsType, reqGoodsTypeList, reqGoodsTypePage, reqUpdateOrAddGoodsType} from "@/api/goodstype";

const useGoodsTypeStore = defineStore('GoodsTypeStore',{
    actions:{
        async goodsTypeInfo (data:requestGoodsTypePage){
            console.log(11)
            let res = await reqGoodsTypePage(data)
            if (res.code == 200){
                return res
            }else {
                return Promise.reject(res.msg)
            }
        },
        async goodsTypeModOrAdd(data:goodsType){
            let res = await reqUpdateOrAddGoodsType(data)
            if (res.code == 200){
                return "ok"
            }else {
                return Promise.reject(res.msg)
            }
        },
        async goodsTypeDelete(id:number){
            let res = await reqDeleteGoodsType(id)
            if (res.code == 200){
                return "ok"
            }else {
                return Promise.reject(res.msg)
            }
        },
        async goodsTypeList(){
            let res = await reqGoodsTypeList()
            if (res.code == 200)
                return res.data
            else
                return Promise.reject(res.msg)
        }
    }
})

export default useGoodsTypeStore