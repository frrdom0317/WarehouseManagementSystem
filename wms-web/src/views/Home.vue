<script setup lang="ts">
import useUserStore from "@/stores/user"
import { UserFilled,Iphone,Stamp,Coin } from '@element-plus/icons-vue'
import {myDate} from "@/util/date"
import {ref,onBeforeUnmount} from "vue";

let userStore = useUserStore()
let msg = ref(myDate())
//开启定时器
let time = setInterval(()=>{
  //调用获取年月日 时分秒 的方法
  msg.value = myDate()
},1000)

//销毁之前 清理定时器
onBeforeUnmount(()=>{
  clearInterval(time)
})
</script>

<template>
  <div style="background-color: rgba(229,229,229,0.63);height: 100%">
    <h1 style="display: flex;font-size: 30px;justify-content: center">欢迎你！{{userStore.user.name}}</h1>
    <el-descriptions column="2" border style="margin:20px">
      <template #title>
          <h4>个人中心</h4>
      </template>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon>
              <UserFilled/>
            </el-icon>
            账号
          </div>
        </template>
        {{ userStore.user?.no }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon>
              <Iphone/>
            </el-icon>
            电话
          </div>
        </template>
        {{ userStore.user?.phone }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon>
              <Stamp/>
            </el-icon>
            性别
          </div>
        </template>
        <el-tag size="small">{{ userStore.user?.sex == 0 ? '女' : '男' }}</el-tag>
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon>
              <Coin/>
            </el-icon>
            角色
          </div>
        </template>
        <el-tag size="small" type="success">{{ userStore.user?.roleId == 0 ? '超级管理员' : (userStore.user?.roleId == 1 ? '管理员' : '普通用户')}}</el-tag>
      </el-descriptions-item>
    </el-descriptions>
    <div style="display: flex;justify-content: center;">
      <h2 style="font-weight: 500;">今天是: {{ msg }}</h2>
    </div>
  </div>
</template>

<style scoped>

</style>