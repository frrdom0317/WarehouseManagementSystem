<script setup lang="ts">
import {User,Lock} from "@element-plus/icons-vue"
import { useRouter, useRoute } from 'vue-router'
import { reactive, ref } from 'vue'
import useUserStore from "@/stores/user";
import { ElNotification} from 'element-plus'
import type { FormInstance } from 'element-plus'
import type { userInfo } from '@/api/user/type'
import {date} from "@/util/date";
import useAsideStore from "@/stores/aside";
import {addRoute} from "@/router/routerHooks";
import type {Menu} from "@/api/menu/type";
import router from "@/router";

let $router = useRouter()
let $route = useRoute()
let asideStore = useAsideStore()
//el-form实例对象
let loginRef = ref<FormInstance>()

let form:userInfo  = reactive({
  no: '',
  password: ''
})
let loading = ref(false)
let userStore = useUserStore()

//登录按钮点击回调
const login = async () => {
  await loginRef.value?.validate()
  loading.value = true
  try {
    //保证登陆成功
    await userStore.userLogin(form)
    //登录成功获取菜单列表
    // let res = await asideStore.menuList(userStore.user.roleId)
    //@ts-ignore
    //页面跳转 ==> 编程式导航
    // let redirect: any = $route.query.redirect
    // $router.push({ path: redirect || '/' })
    $router.push({ path: '/' })
    ElNotification({
      type: 'success',
      message: "欢迎回来!",
      title: `HI,${date()}好!`
    })
    loading.value = false
  } catch (error) {
    ElNotification({
      type: 'error',
      message: (error as Error).message
    })
    loading.value = false
  }

}

const validatorNo = (rule: any, value: any, callback: any) => {
  if (value != '') {
    callback()
  } else {
    callback('账户不能为空')
  }
}
const validatorPassword = (rule: any, value: any, callback: any) => {
  if (value != '') {
    callback()
  } else {
    callback('密码不能为空')
  }
}
//表单校验规则
const formRule = {
  no: [
    { validator: validatorNo, trigger: 'change' }
  ],
  password: [
    { validator: validatorPassword, trigger: 'change' }
  ]
}
</script>
<template>
  <div class="container">
    <el-row>
      <el-col :span="12" :xs="0"></el-col>
      <el-col :span="12" :xs="24">
        <el-form class="login_form" ref="loginRef" :model="form" :rules="formRule">
          <h1>Hello</h1>
          <h2>欢迎来到仓库管理系统</h2>
          <el-form-item prop="no">
            <el-input v-model="form.no" :prefix-icon="User" />
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="form.password" type="password" show-password :prefix-icon="Lock" />
          </el-form-item>
          <el-form-item>
            <el-button class="login_btn" type="primary" @click="login" :loading="loading">
              登录
            </el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped>
.container {
  width: 100%;
  height: 100vh;
  background: url('@/assets/images/background.jpg') no-repeat;
  background-size: cover;
}

.login_form {
  display: block;
  position: relative;
  width: 80%;
  top: 30vh;
  padding: 40px;
}

.login_form h1 {
  color: white;
  font-size: 40px;
}

.login_form h2 {
  color: white;
  font-size: 20px;
  margin: 20px 0px;
}

.login_btn {
  width: 100%;
}
</style>