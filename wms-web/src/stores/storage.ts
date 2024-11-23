import {defineStore} from "pinia"
import type {requestStoragePage, storage} from "@/api/storage/type";
import {reqDeleteStorage, reqStorageList, reqStoragePage, reqUpdateOrAddStorage} from "@/api/storage";

const useStorageStore = defineStore('StorageStore',{
    actions:{
        //获取仓库数据
        async storageInfo(data:requestStoragePage){
            let res = await reqStoragePage(data)
            if (res.code == 200) {
                return res
            } else {
                return Promise.reject(res.msg)
            }
        },
        //新增或修改
        async storageModOrAdd(data:storage){
            let res =await reqUpdateOrAddStorage(data)
            if (res.code == 200){
                return "ok"
            }else {
                return Promise.reject(new Error(res.msg))
            }
        },
        async storageDelete(id:number){
            let res = await reqDeleteStorage(id)
            if (res.code == 200){
                return "ok"
            }else {
                return Promise.reject(res.msg)
            }
        },
        async storageList(){
            let res = await reqStorageList()
            if (res.code == 200)
                return res.data
            else
                return Promise.reject(res.msg)

        }
    }
})

export default useStorageStore