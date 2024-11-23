<script setup lang="ts">
defineProps(['menuList'])
</script>
<script lang="ts">
export default {
  name:'Menu'
}
</script>

<template>
  <template v-for="m in menuList" :key="m.path">
    <template v-if="!m.children">
      <el-menu-item v-if="m.meta.show" :index="m.path">
        <el-icon>
          <component :is="m.meta.icon"/>
        </el-icon>
        <template #title>
          <span>{{m.meta.title}}</span>
        </template>
      </el-menu-item>
    </template>
    <template v-if="m.children && m.children.length == 1">
      <el-menu-item v-if="m.children[0].meta.show" :index="m.children[0].path">
        <el-icon>
          <component :is="m.children[0].meta.icon"/>
        </el-icon>
        <template #title>
          <span>{{m.children[0].meta.title}}</span>
        </template>
       </el-menu-item>
    </template>
    <el-sub-menu v-if="m.children && m.children.length > 1" :index="m.path">
      <template #title>
        <el-icon>
          <component :is="m.meta.icon"/>
        </el-icon>
        <span>{{ m.meta.title }}</span>
      </template>
      <Menu :menu-list="m.children"/>
    </el-sub-menu>
  </template>
</template>

<style scoped>

</style>