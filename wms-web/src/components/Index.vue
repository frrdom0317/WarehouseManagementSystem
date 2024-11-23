<script setup lang="ts">
import { ref,onBeforeUnmount,onMounted } from 'vue'
import Aside from "@/components/Aside.vue";
import Header from "@/components/Header.vue";
import useSettingStore from "@/stores/setting"

let settingStore = useSettingStore()
</script>

<template>
  <el-container class="layout-container-demo" style="height: 100%">
    <el-aside width="200px" :class="{fold: settingStore.isFold ? true : false}">
      <Aside/>
    </el-aside>
    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <Header/>
      </el-header>
      <el-main>
        <!--<Main/>-->
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<style scoped>
.layout-container-demo .el-header {
  position: relative;
  //background-color: var(--el-color-primary-light-7);
  border-bottom: rgb(209 208 208 / 90%) 1px solid;
  color: var(--el-text-color-primary);
}
.layout-container-demo .el-aside {
  color: var(--el-text-color-primary);
  background: var(--el-color-primary-light-8);
}
.layout-container-demo .el-menu {
  border-right: none;
}
.layout-container-demo .el-main {
  padding: 10px;
}
.layout-container-demo .toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
}

.fold {
  width: 60px;
}

</style>