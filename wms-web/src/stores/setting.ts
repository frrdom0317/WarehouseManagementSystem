import {defineStore} from "pinia"

const useSettingStore = defineStore("Setting",{
    state:()=>{
        return{
            //是否折叠
            isFold:false
        }
    }
})

export default useSettingStore