import {defineStore} from "pinia"
import type {goods, requestGoodsPage} from "@/api/goods/type";
import {reqDeleteGoods, reqGoodsPage, reqUpdateOrAddGoods} from "@/api/goods";

const useGoodsStore = defineStore('GoodsStore',{
    actions:{
        async goodsInfo (data:requestGoodsPage){
            console.log(11)
            let res = await reqGoodsPage(data)
            if (res.code == 200){
                return res
            }else {
                return Promise.reject(res.msg)
            }
        },
        async goodsModOrAdd(data:goods){
            let res = await reqUpdateOrAddGoods(data)
            if (res.code == 200){
                return "ok"
            }else {
                return Promise.reject(res.msg)
            }
        },
        async goodsDelete(id:number){
            let res = await reqDeleteGoods(id)
            if (res.code == 200){
                return "ok"
            }else {
                return Promise.reject(res.msg)
            }
        }
    }
})

export default useGoodsStore