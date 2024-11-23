import {defineStore} from "pinia"
import type {recordRes, requestRecordPage} from "@/api/record/type";
import {reqAddRecord, reqRecordList} from "@/api/record";

const useRecordStore = defineStore('RecordStore',{
    actions:{
        async recordInfo(data:requestRecordPage){
            let res = await reqRecordList(data)
            if (res.code == 200)
                return res
            else
                return Promise.reject(res.msg)
        },
        async recordAdd(data:recordRes) {
            let res = await reqAddRecord(data)
            if (res.code == 200)
                return "ok"
            else
                return Promise.reject(res.msg)
        }
    }
})

export default useRecordStore