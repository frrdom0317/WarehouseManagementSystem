<script setup lang="ts">
import {ref,onMounted,reactive} from "vue"
import useGoodsTypeStore from "@/stores/goodsType"
import {Search} from "@element-plus/icons-vue"
import {ElMessage} from "element-plus"
import type {FormInstance,FormRules} from "element-plus"
import type {storage, storageResponse} from "@/api/storage/type";
import type {goodsType} from "@/api/goodstype/type";

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
//是否显示对话框
let showDialog = ref(false)
//表单对象
let formRef = ref<FormInstance>()
//表单数据
let form = reactive<goodsType>({
  name:"",
  remark:""
})
let goodsTypeStore = useGoodsTypeStore()

//获取分页数据
const getStoragePageInfo = async ()=>{
  let res:storageResponse = await goodsTypeStore.goodsTypeInfo({
    pageNum: pageNum.value,
    pageSize: pageSize.value,
    param:{
      name:name.value,
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

//条数的点击回调
const currentSizeChange = ()=>{
  pageNum.value = 1
  getStoragePageInfo()
}

//重置按钮的回调
const reset = ()=>{
  name.value = ""
  getStoragePageInfo()
}

//新增按钮的回调
const add = ()=>{
  formRef.value?.resetFields()
  Object.assign(form,{
    id:"",
    name:"",
    remark:""
  })
  showDialog.value = true
}
//编辑按钮的回调
const edit = (row:any)=>{
  formRef.value?.resetFields()
  Object.assign(form,row)
  showDialog.value = true
}
//删除按钮的回调
const remove = async (id:number)=>{
  try {
    await goodsTypeStore.goodsTypeDelete(id)
    ElMessage({
      type:'success',
      message:'删除成功'
    })
    await getStoragePageInfo()
  }catch (e){
    ElMessage({
      type:'error',
      message:'删除失败'
    })
  }
}
//对话框-确认按钮点击回调
const save = async ()=>{
  await formRef.value?.validate()
  try{
    await goodsTypeStore.goodsTypeModOrAdd(form)
    ElMessage({
      type:'success',
      message:form.id ? '修改' : '添加' + '成功'
    })
    //隐藏对话框
    showDialog.value = false
    //刷新数据
    await getStoragePageInfo()
  }catch (e) {
    ElMessage({
      type:'error',
      message:'添加物品分类信息失败'
    })
  }
}
/*表单校验规则*/
const validateName = (rule:any,value: any, callback: any)=>{
  if (value.length >= 3 && value.length <= 8){
    callback()
  }else{
    callback(new Error("分类名长度在3~8个字符之间"))
  }
}
const validateRemark =  (rule:any,value: any, callback: any)=>{
  if (value.length <= 100){
    callback()
  }else{
    callback(new Error("备注长度在100个字符之内"))
  }
}

const rules = reactive<FormRules<storage>>({
  name:[{required: true,validator:validateName,trigger:'blur'}],
  remark:[{required: true,validator:validateRemark,trigger:'blur'}],
})

onMounted(()=>{
  getStoragePageInfo()
})
</script>

<template>
  <div>
    <el-input v-model="name" placeholder="请输入仓库名" style="width:200px;margin-right: 10px;" :prefix-icon="Search" @keyup.enter="getStoragePageInfo"></el-input>

    <el-button type="primary" style="margin-left: 10px;" @click="getStoragePageInfo">查询</el-button>
    <el-button type="success" @click="reset">重置</el-button>
    <el-button type="danger" @click="add">新增</el-button>
  </div>
  <!--列表显示-->
  <el-scrollbar style="height: 600px;margin-top: 15px;">
    <el-table :data="tableData" border :header-cell-style="{ background: '#f3f3f3' }">
      <el-table-column prop="id" label="id" width="60" />
      <el-table-column prop="name" label="仓库名" width="180" />
      <el-table-column prop="remark" label="备注" width="380" />
      <el-table-column label="操作" >
        <template #default="scope">
          <el-button type="success" size="small" @click="edit(scope.row)">编辑</el-button>
          <el-popconfirm title="你确认删除吗?" @confirm="remove(scope.row.id)">
            <template #reference>
              <el-button type="danger">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
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
        @current-change="getStoragePageInfo"
        style="justify-content: center"
    />
  </div>
  <!--对话框-->
  <el-dialog
      v-model="showDialog"
      :title="form.id?'修改物品分类':'添加物品分类'"
      width="30%"
      align-center>
    <!--表单-->
    <el-form  ref="formRef" :model="form" :rules="rules"  label-width="auto" style="max-width: 600px">
      <el-form-item label="分类名" prop="name">
        <el-col :span="20">
          <el-input :disabled="form.id ? true : false" v-model="form.name" />
        </el-col>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-col :span="20">
          <el-input  type="textarea" v-model="form.remark" />
        </el-col>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="showDialog = false">取消</el-button>
        <el-button type="primary" @click="save">
          确认
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style scoped>

</style>