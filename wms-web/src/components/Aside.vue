<script setup lang="ts">
import useSettingStore from "@/stores/setting"
import useAsideStore from "@/stores/aside"
import {useRoute} from "vue-router"
import Menu from "@/components/menu/index.vue"
import {ref} from "vue";

let settingStore = useSettingStore()
let asideStore = useAsideStore()
let $route = useRoute()



</script>

<template>
  <el-scrollbar>
    <el-menu
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        :default-active="$route.path"
        style="height: 100vh"
        :collapse="settingStore.isFold"
        :collapse-transition="false"
        router
    >
    <Menu :menu-list="asideStore.menuRoutes[0]?.children"/>

    </el-menu>
  </el-scrollbar>
</template>

<style scoped>

</style>