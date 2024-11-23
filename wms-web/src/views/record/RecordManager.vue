<script setup lang="ts">
import {ref,onMounted,reactive} from "vue"
import useRecordStore from "@/stores/record"
import {Search} from "@element-plus/icons-vue"
import {ElMessage} from "element-plus"
import useStorageStore from "@/stores/storage";
import useGoodsTypeStore from "@/stores/goodsType";
import type {recordRes, recordResponse} from "@/api/record/type";
import useUserStore from "@/stores/user";

//用户数据
let tableData:any = ref([])
//显示条数
let pageSize:any = ref(5)
//当前显示页码
let pageNum:any = ref(1)
//总条数
let total:any = ref(0)
//仓库名
let name = ref("")
//物品分类数据
let goodsTypeInfo:any = ref([])
//仓库数据
let storageInfo:any = ref([])
//选择的仓库id
let storageSelect = ref('')
//选择的物品分类id
let goodsTypeSelect = ref('')


let recordStore = useRecordStore()
let storageStore = useStorageStore()
let goodsTypeStore = useGoodsTypeStore()
let userStore = useUserStore()
//获取记录列表 分页查询
const getGoodsPageInfo = async () => {
  let res:recordResponse = await recordStore.recordInfo({
    pageNum: pageNum.value,
    pageSize: pageSize.value,
    param:{
      name:name.value,
      //@ts-ignore
      goodstype:goodsTypeSelect.value,
      //@ts-ignore
      storage:storageSelect.value,
      roleId:userStore.user.roleId,
      userId:userStore.user.id,
    }
  })
  if (res.code == 200){
    tableData.value = res.data
    total.value = res.total
  }else {
    ElMessage({
      type:'error',
      message:'获取数据失败'
    })
  }
}
//获取所有物品分类数据
const getGoodsTypeInfo = async ()=>{
  let res = await goodsTypeStore.goodsTypeList()
  goodsTypeInfo.value = res
}
//获取所有仓库数据
const getStorageInfo = async ()=>{
  let res = await storageStore.storageList()
  storageInfo.value = res
}
//条数的点击回调
const currentSizeChange = ()=>{
  pageNum.value = 1
  getGoodsPageInfo()
}

//重置按钮的回调
const reset = ()=>{
  name.value = ""
  goodsTypeSelect.value = ''
  storageSelect.value = ''
  getGoodsPageInfo()
}

onMounted(()=>{
  getGoodsTypeInfo()
  getStorageInfo()
  console.log(storageInfo)
  console.log(goodsTypeInfo)
  getGoodsPageInfo()
})
</script>

<template>
  <div>
    <el-input v-model="name" placeholder="请输入物品名" style="width:200px;margin-right: 10px;" :prefix-icon="Search" @keyup.enter="getGoodsPageInfo"></el-input>
    <el-select
        v-model.number="storageSelect"
        placeholder="请选择仓库"
        style="width:200px;margin-right: 10px;"
    >
      <el-option
          v-for="item in storageInfo"
          :key="item.id"
          :label="item.name"
          :value="item.id"
      />
    </el-select>
    <el-select
        v-model.number="goodsTypeSelect"
        placeholder="请选择分类"
        style="width:200px;margin-right: 10px;"
    >
      <el-option
          v-for="item in goodsTypeInfo"
          :key="item.id"
          :label="item.name"
          :value="item.id"
      />
    </el-select>
    <el-button type="primary" style="margin-left: 10px;" @click="getGoodsPageInfo">查询</el-button>
    <el-button type="success" @click="reset">重置</el-button>
  </div>
  <!--列表显示-->
  <el-scrollbar style="height: 600px;margin-top: 15px;">
    <el-table :data="tableData" border :header-cell-style="{ background: '#f3f3f3' }">
      <el-table-column prop="id" label="id" width="60" />
      <el-table-column  prop="goodsname" label="物品名" width="180" />
      <el-table-column prop="storagename" label="仓库" width="180" />
      <el-table-column prop="goodstypename" label="分类" width="180" />
      <el-table-column prop="adminname" label="操作人" width="180" />
      <el-table-column prop="username" label="申请人" width="180" />
      <el-table-column prop="count" label="数量" width="180" />
      <el-table-column prop="createTime" label="操作时间" width="180" />
      <el-table-column prop="remark" label="备注" width="280" />
    </el-table>
  </el-scrollbar>
  <!--分页-->
  <div style="margin-top: 30px">
    <el-pagination
        v-model:current-page="pageNum"
        v-model:page-size="pageSize"
        :page-sizes="[ 5, 10, 20, 30 ]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="currentSizeChange"
        @current-change="getGoodsPageInfo"
        style="justify-content: center"
    />
  </div>
</template>

<style scoped>

</style>