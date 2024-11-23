<script setup lang="ts">
import {ref,onMounted,reactive} from "vue"
import useUserStore from "@/stores/user"
import {Search} from "@element-plus/icons-vue"
import type {userInfo, userListResponse} from "@/api/user/type"
import {ElMessage} from "element-plus"
import type {FormInstance,FormRules} from "element-plus"

//用户数据
let tableData:any = ref([])
//显示条数
let pageSize:any = ref(5)
//当前显示页码
let pageNum:any = ref(1)
//总条数
let total:any = ref(0)
//用户名
let name = ref("")
//性别
let sex = ref("")
//性别下拉菜单项
let sexs = ref([
  {
    value:0,
    label:'女'
  },
  {
    value:1,
    label:'男'
  }
])
//是否显示对话框
let showDialog = ref(false)
//表单对象
let formRef = ref<FormInstance>()
//表单数据
let form = reactive<userInfo>({
  no:"",
  name:"",
  password:"",
  age:"",
  phone:"",
  sex:1,
  roleId:1
})
let userStore = useUserStore()

//获取用户列表 分页查询
const getUserPageInfo =async ()=>{
  let res:userListResponse = await userStore.userInfo({
    pageNum: pageNum.value,
    pageSize: pageSize.value,
    param:{
      name:name.value,
      sex:sex.value,
      roleId:form.roleId as number
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
  getUserPageInfo()
}

//重置按钮的回调
const reset = ()=>{
  name.value = ""
  sex.value = ""
  getUserPageInfo()
}

//新增按钮的回调
const add = ()=>{
  formRef.value?.resetFields()
  Object.assign(form,{
    id:"",
    no:"",
    name:"",
    password:"",
    age:"",
    phone:"",
    sex:1,
    roleId:1
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
    await userStore.userRemove(id)
    ElMessage({
      type:'success',
      message:'删除用户成功'
    })
    getUserPageInfo()
  }catch (e){
    ElMessage({
      type:'error',
      message:'删除用户失败'
    })
  }
}
//对话框-确认按钮点击回调
const save = async ()=>{
  await formRef.value?.validate()
  try{
    await userStore.userModOrAdd(form)
    ElMessage({
      type:'success',
      message:form.id ? '修改' : '添加' + '用户成功'
    })
    //隐藏对话框
    showDialog.value = false
    //刷新数据
    getUserPageInfo()
  }catch (e) {
    ElMessage({
      type:'error',
      message:'添加用户失败'
    })
  }
}

/*表单校验规则
* 名字 三到八个字符
* 账号 三到八个字符 且数据库中没有(修改时不需要)
* 密码 三到八个字符
* 年龄 1~120
* 电话 11位
* */
const validateName = (rule:any,value: any, callback: any)=>{
  if (value.length >= 3 && value.length <= 8){
    callback()
  }else{
    callback(new Error("名字长度在3~8个字符之间"))
  }
}
const validateNo =  (rule:any,value: any, callback: any)=>{
  if (value.length >= 3 && value.length <= 8){
    userStore.noExist(value).then(res =>{
      callback()
    },error=>{
      callback(new Error("此账号已经存在"))
    })
  }else{
    callback(new Error("账号长度在3~8个字符之间"))
  }
}
const validatePasswd = (rule:any,value: any, callback: any)=>{
  if (value.length >= 3 && value.length <= 8){
    callback()
  }else{
    callback(new Error("密码长度在3~8个字符之间"))
  }
}
const validateAge = (rule:any,value: any, callback: any)=>{
  if (value >= 1 && value <= 120){
    callback()
  }else{
    callback(new Error("年龄在1~120之间"))
  }
}
const validatePhone = (rule:any,value: any, callback: any)=>{
  if (/^1[3-9]\d{9}$/.test(value)){
    callback()
  }else{
    callback(new Error("电话号码不符合规范(必须以1开头且位数为11位)"))
  }
}
const rules = reactive<FormRules<userInfo>>({
  name:[{required: true,validator:validateName,trigger:'blur'}],
  no:[{required: true,validator:validateNo,trigger:'blur'}],
  password:[{required: true,validator:validatePasswd,trigger:'blur'}],
  age:[{required: true,validator:validateAge,trigger:'blur'}],
  phone:[{required: true,validator:validatePhone,trigger:'blur'}],
})

onMounted(()=>{
  getUserPageInfo()
})
</script>

<template>
  <div>
    <el-input v-model="name" placeholder="请输入名字" style="width:200px;margin-right: 10px;" :prefix-icon="Search" @keyup.enter="getUserPageInfo"></el-input>
    <el-select v-model="sex" placeholder="请选择性别" style="width: 200px;">
      <el-option
          v-for="item in sexs"
          :key="item.value"
          :label="item.label"
          :value="item.value"
      />
    </el-select>
    <el-button type="primary" style="margin-left: 10px;" @click="getUserPageInfo">查询</el-button>
    <el-button type="success" @click="reset">重置</el-button>
    <el-button type="danger" @click="add">新增</el-button>
  </div>
  <!--列表显示-->
  <el-scrollbar style="height: 600px;margin-top: 15px;">
    <el-table :data="tableData" border :header-cell-style="{ background: '#f3f3f3' }">
      <el-table-column prop="id" label="id" width="60" />
      <el-table-column prop="no" label="账号" width="180" />
      <el-table-column prop="name" label="姓名" width="180" />
      <el-table-column prop="age" label="年龄" width="80" />
      <el-table-column prop="sex" label="性别" width="80">
        <template #default="scope">
          <el-tag :type="scope.row.sex === 1 ? 'primary':'success'">
            {{ scope.row.sex === 1 ? '男':'女' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="roleId" label="角色" width="120" >
        <template #default="scope">
          <el-tag :type="scope.row.roleId === 0 ? 'danger':(scope.row.roleId === 1 ? 'primary':'success')">
            {{ scope.row.roleId === 0 ? '超级管理员':(scope.row.roleId === 1 ? '管理员':'用户') }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="phone" label="电话" width="180" />
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
        @current-change="getUserPageInfo"
        style="justify-content: center"
    />
  </div>
  <!--对话框-->
  <el-dialog
      v-model="showDialog"
      :title="form.id?'修改用户':'添加用户'"
      width="30%"
      align-center>
    <!--表单-->
    <el-form  ref="formRef" :model="form" :rules="rules"  label-width="auto" style="max-width: 600px">
      <el-form-item label="账号" prop="no">
        <el-col :span="20">
          <el-input :disabled="form.id ? true : false" v-model="form.no" />
        </el-col>
      </el-form-item>
      <el-form-item label="名字" prop="name">
        <el-col :span="20">
          <el-input v-model="form.name" />
        </el-col>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-col :span="20">
          <el-input v-model="form.password" />
        </el-col>
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-col :span="20">
          <el-input v-model="form.age" />
        </el-col>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="form.sex">
          <el-radio :value="0">女</el-radio>
          <el-radio :value="1">男</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="电话" prop="phone">
        <el-col :span="20">
          <el-input v-model="form.phone" />
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