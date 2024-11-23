<script setup lang="ts">
import {ref,onMounted,reactive} from "vue"
import useGoodsStore from "@/stores/goods"
import {Search} from "@element-plus/icons-vue"
import {ElMessage} from "element-plus"
import type {FormInstance,FormRules} from "element-plus"
import type {goods, goodsResponse} from "@/api/goods/type";
import useStorageStore from "@/stores/storage";
import useGoodsTypeStore from "@/stores/goodsType";
import type {storage} from "@/api/storage/type";
import useUserStore from "@/stores/user"
import type {recordRes} from "@/api/record/type";
import record from "@/stores/record";
import useRecordStore from "@/stores/record";

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
let form = reactive<goods>({
  name:"",
  storage:"",
  goodstype:"",
  count:"",
  remark:""
})
//物品分类数据
let goodsTypeInfo:any = ref([])
//仓库数据
let storageInfo:any = ref([])
//选择的仓库id
let storageSelect = ref('')
//选择的物品分类id
let goodsTypeSelect = ref('')
//出入口对话框显示
let showIODialog = ref(false)
//用户列表
let users = ref([])
//入库/出库表单数据
let recordForm = reactive<recordRes>({
  goodsname:'',
  action:1 //默认入库
})
let recordFormRef = ref()

let userStore = useUserStore()
let goodsStore = useGoodsStore()
let storageStore = useStorageStore()
let goodsTypeStore = useGoodsTypeStore()
let recordStore = useRecordStore()
//获取物品列表 分页查询
const getGoodsPageInfo = async () => {
  let res:goodsResponse = await goodsStore.goodsInfo({
    pageNum: pageNum.value,
    pageSize: pageSize.value,
    param:{
      name:name.value,
      //@ts-ignore
      goodstype:goodsTypeSelect.value,
      //@ts-ignore
      storage:storageSelect.value
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

//新增按钮的回调
const add = ()=>{
  formRef.value?.resetFields()
  Object.assign(form,{
    id:"",
    name:"",
    remark:"",
    storage:"",
    count:"",
    goodstype:""
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
    await goodsStore.goodsDelete(id)
    ElMessage({
      type:'success',
      message:'删除成功'
    })
    await getGoodsPageInfo()
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
    await goodsStore.goodsModOrAdd(form)
    ElMessage({
      type:'success',
      message:form.id ? '修改' : '添加' + '成功'
    })
    //隐藏对话框
    showDialog.value = false
    //刷新数据
    await getGoodsPageInfo()
  }catch (e) {
    ElMessage({
      type:'error',
      message:'添加物品信息失败'
    })
  }
}
/*表单校验规则*/
const validateName = (rule:any,value: any, callback: any)=>{
  if (value.length >= 2 && value.length <= 8){
    callback()
  }else{
    callback(new Error("物品名长度在2~8个字符之间"))
  }
}
const validateCount =  (rule:any,value: any, callback: any)=>{
  if (/^[0-9]*[1-9][0-9]*$/.test(value)){
    if (parseInt(value) <= 9999){
      callback()
    }else{
      callback(new Error("数量在9999之内"))
    }
  }else {
    callback(new Error("数量必须为正整数"))
  }
}

const rules = reactive<FormRules<goods>>({
  name:[{required: true,validator:validateName,trigger:'blur'}],
  count:[{required:true,validator:validateCount,trigger:'blur'}],
  storage:[{required:true,message:'请选择仓库',trigger:'blur'}],
  goodstype:[{required:true,message:'请选择分类',trigger:'blur'}]
})

//格式化仓库的展示 会将表格中对应这一行的数据信息传入
const formatterStorage = (row:goods)=>{
  let tem = storageInfo.value.find((item:storage) => {
        return item.id == row.storage
  })
  return tem.name || row.storage
}
//格式化分类的展示 会将表格中对应这一行的数据信息传入
const formatterGoodsType = ((row:goods)=>{
  let tem = goodsTypeInfo.value.find((item:storage) => {
    return item.id == row.goodstype
  })
  console.log(tem)
  return tem.name || row.goodstype
})

const clickIO = ()=>{
  Object.assign(recordForm,{
    goodsname:'',
    goods:'',
    adminId:'',
    action:1,
    createTime:'',
    remark:''
  })
  recordFormRef.value?.resetFields()
}
//入库按钮点击回调
const inGoods = (row:goods)=>{
  clickIO()
  recordForm.goodsname = row.name
  recordForm.goods = row.id
  recordForm.adminId = userStore.user.id
  showIODialog.value = true
}
//出库按钮点击回调
const outGoods = (row:goods)=>{
  clickIO()
  recordForm.action = 2
  recordForm.goodsname = row.name
  recordForm.goods = row.id
  recordForm.adminId = userStore.user.id
  showIODialog.value = true
}
//选择下拉菜单
const focus = async ()=>{
  let res = await userStore.userInfoList()
  //@ts-ignore
  users.value = res.data
}
//出入库确认按钮
const saveIO = async ()=>{
  await recordFormRef.value?.validate()
  try{
    recordForm.createTime = new Date()
    await recordStore.recordAdd(recordForm)
    ElMessage({
      type:'success',
      message:'添加成功'
    })
    //隐藏对话框
    showIODialog.value = false
    //刷新数据
    await getGoodsPageInfo()
  }catch (e) {
    ElMessage({
      type:'error',
      message:'添加失败'
    })
  }
}
const recordRules = reactive({
  userId:[{required: true,message:'申请人不能为空',trigger:'blur'}],
  count:[{required:true,validator:validateCount,trigger:'blur'}]
})
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
    <el-button type="danger" @click="add" v-if="userStore.user.roleId != 2">新增</el-button>
  </div>
  <!--列表显示-->
  <el-scrollbar style="height: 600px;margin-top: 15px;">
    <el-table :data="tableData" border :header-cell-style="{ background: '#f3f3f3' }">
      <el-table-column prop="id" label="id" width="60" />
      <el-table-column  prop="name" label="物品名" width="180" />
      <el-table-column :formatter="formatterStorage" prop="storage" label="仓库" width="180" />
      <el-table-column :formatter="formatterGoodsType"  prop="goodstype" label="分类" width="180" />
      <el-table-column prop="count" label="数量" width="150" />
      <el-table-column prop="remark" label="备注" />
      <el-table-column label="操作"  width="300">
        <template #default="scope">
          <el-button type="info"  @click="inGoods(scope.row)" v-if="userStore.user.roleId != 2">入库</el-button>
          <el-button type="warning"  @click="outGoods(scope.row)" v-if="userStore.user.roleId != 2">出库</el-button>
          <el-button type="success"  @click="edit(scope.row)" v-if="userStore.user.roleId != 2">编辑</el-button>
          <el-popconfirm title="你确认删除吗?" @confirm="remove(scope.row.id)">
            <template #reference>
              <el-button type="danger" v-if="userStore.user.roleId != 2">删除</el-button>
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
        @current-change="getGoodsPageInfo"
        style="justify-content: center"
    />
  </div>
  <!--新增/修改对话框-->
  <el-dialog
      v-model="showDialog"
      :title="form.id?'修改物品':'添加物品'"
      width="30%"
      align-center>
    <!--表单-->
    <el-form  ref="formRef" :model="form" :rules="rules"  label-width="auto" style="max-width: 600px">
      <el-form-item label="物品名" prop="name">
        <el-col :span="20">
          <el-input :disabled="form.id ? true : false" v-model="form.name" />
        </el-col>
      </el-form-item>

      <el-form-item label="仓库" prop="storage">
        <el-select
          v-model.number="form.storage"
          placeholder="请选择仓库"
          style="width:297px;height: 34px">
        <el-option
            v-for="item in storageInfo"
            :key="item.id"
            :label="item.name"
            :value="item.id"
        />
      </el-select>
      </el-form-item>
      <el-form-item label="分类" prop="goodstype">
        <el-select
            v-model.number="form.goodstype"
            placeholder="请选择分类"
            style="width:297px;height: 34px">
          <el-option
              v-for="item in goodsTypeInfo"
              :key="item.id"
              :label="item.name"
              :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="数量" prop="count">
        <el-col :span="20">
          <el-input v-model="form.count" />
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
  <!--入库对话框-->
  <el-dialog
      v-model="showIODialog"
      title="出入库"
      width="30%"
      align-center>
    <!--表单-->
    <el-form  ref="recordFormRef" :model="recordForm" :rules="recordRules"  label-width="auto" style="max-width: 600px">
      <el-form-item label="物品名">
        <el-col :span="20">
          <el-input disabled v-model="recordForm.goodsname" />
        </el-col>
      </el-form-item>
      <el-form-item label="申请人" prop="userId">
        <el-select @focus="focus" v-model="recordForm.userId" placeholder="请选择申请人" style="width: 305px;height: 32px" placement="bottom-start" >
          <el-option v-for="u in users" :label="u.name" :value="u.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="数量" prop="count">
        <el-col :span="20">
          <el-input v-model="recordForm.count" />
        </el-col>
      </el-form-item>
      <el-form-item label="备注">
        <el-col :span="20">
          <el-input  type="textarea" v-model="recordForm.remark" />
        </el-col>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="showIODialog = false">取消</el-button>
        <el-button type="primary" @click="saveIO">
          确认
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style scoped>

</style>