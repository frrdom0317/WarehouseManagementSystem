<script setup lang="ts">
import {ref} from "vue"
import {useRouter} from "vue-router"
import {ArrowDown,Expand,Fold,CircleCloseFilled} from "@element-plus/icons-vue"
import useSettingStore from "@/stores/setting"
import useUserStore from "@/stores/user"
import useAsideStore from "@/stores/aside";
import {constantRoutes} from "@/router/routes";

let settingStore = useSettingStore()
let userStore = useUserStore()
let asideStore = useAsideStore()
let $router = useRouter()
let showDialog = ref(false)
const toUser = ()=>{
  $router.push('/home')
}

const logout = ()=>{
  userStore.userLogout()
  asideStore.menuRoutes = []
  $router.push("/login")
}

const collapse = ()=>{
  settingStore.isFold = ! settingStore.isFold
}
</script>

<template>
  <div class="box">
    <el-icon style="font-size: 20px;cursor: pointer" @click="collapse">
      <component :is="settingStore.isFold ? Fold : Expand"/>
    </el-icon>
    <div class="title">
      <span>欢迎来到仓库管理系统</span>
    </div>
    <div class="toolbar" >
      <span>{{userStore.user.name}}</span>
      <el-dropdown>
        <el-icon style="margin-left: 5px; margin-top: 1px">
          <ArrowDown />
        </el-icon>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="toUser">个人中心</el-dropdown-item>
            <el-dropdown-item @click="showDialog = true">退出</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>

  <!-- 弹窗 -->
  <el-dialog width="30%" v-model="showDialog" :show-close="false" append-to-body class="myDialogClass">
    <!--弹窗标题-->
    <template #header="{ close, titleId }">
      <div class="myDialogHeader">
        <span :id="titleId" class="dlgTitleClass" style="margin: 10px 10px;">提示</span>
        <el-button style="margin: 8px;" type="info" :icon="CircleCloseFilled" circle  @click="close" size="small">
          <el-icon style="font-size: 26px;" color="#ffffff"><CircleCloseFilled /></el-icon>
        </el-button>
      </div>
    </template>
    您确认要退出登录吗？
    <!--弹窗底部-->
    <template #footer>
      <span class="myDialogFooter">
          <el-button type="primary" @click="logout">确定</el-button>
          <el-button type="primary" @click="showDialog = false">取消</el-button>
      </span>
    </template>

  </el-dialog>
</template>

<style scoped>
.box {
  display: flex;
  align-items: center;
  height: 100%
}
.title {
  flex: 1;
  text-align: center;
  font-size: 34px;
  font-weight: 700
}

.myDialogClass {
  /*scoped 模式下  background-color 不生效 直接写到style*/
  background-color: rgb(224,242,241) !important;
  border-radius: 10px;
}
.myDialogClass .dlgTitleClass {
  color: rgb(26,147,144);
}

.el-dialog .el-dialog__header
{
  margin: 0px !important;
  padding: 0px !important;
  padding-left: 0px !important;
}
.el-dialog .myDialogHeader {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  height: 40px;
  /* background-color: #ff00ff; */
  border-bottom: 1px solid rgb(42,155,152);
  margin: 0px 10px;
  font-size: 18px;
  font-weight: bolder;
}

.el-dialog .el-dialog__footer
{
  padding: 10px;
}

.myDialogFooter .el-button
{
  border-color: rgb(42,155,152);
  background-color: rgb(242,249,249);
  color: rgb(42,155,152);
}

</style>