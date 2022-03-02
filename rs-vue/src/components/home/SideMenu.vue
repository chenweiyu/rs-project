<template>
  <el-menu
    default-active="1"
    style="width: 250px"
    @open="handleOpen"
    @close="handleClose"
  >
  <a href="/index">
      <el-menu-item index="1">
        <i class="el-icon-document"></i>
        <span slot="title">人事月报</span>
      </el-menu-item>
    </a>
    <!-- <a href="#update-card"> -->
    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-menu"></i><span slot="title">员工调配管理</span>
      </template>
      <el-menu-item-group>
        <!-- <template slot="title">员工试用期管理</template> -->
        <a href="/info"><el-menu-item index="2-1"><i class="el-icon-menu"></i><span slot="title">员工信息中心</span></el-menu-item></a>
        <a href="/transfer_section"><el-menu-item index="2-2"><i class="el-icon-menu"></i><span slot="title">员工部门调动</span></el-menu-item></a>
        <a href="/transfer_position"><el-menu-item index="2-3"><i class="el-icon-menu"></i><span slot="title">员工岗位调动</span></el-menu-item></a>
        <a href="/leaveWork"><el-menu-item index="2-4"><i class="el-icon-menu"></i><span slot="title">员工离职管理</span></el-menu-item></a>
        <a href="/ontrial"><el-menu-item index="2-5"><i class="el-icon-menu"></i><span slot="title">员工试用期管理</span></el-menu-item></a>

      </el-menu-item-group>
    </el-submenu>
    <!-- </a> -->
    <a href="/induction">
      <el-menu-item index="3">
        <i class="el-icon-document"></i>
        <span slot="title">员工入职管理</span>
      </el-menu-item>
    </a>
    <!-- <a href="#about"> -->
    <el-submenu index="4">
      <template slot="title">
        <i class="el-icon-setting"></i>
        <span slot="title">公司等级管理</span>
      </template>
      <a href="/section"><el-menu-item index="4-1"><i class="el-icon-menu"></i><span slot="title">部门管理</span></el-menu-item></a>
      <a href="/position"><el-menu-item index="4-2"><i class="el-icon-menu"></i><span slot="title">岗位中心</span></el-menu-item></a>
    </el-submenu>
    <!-- </a> -->
    <el-submenu index="5">
      <template slot="title">
        <i class="el-icon-setting"></i>
        <span slot="title">系统管理</span>
      </template>
      <a href="/sysUser"><el-menu-item index="5-1"><i class="el-icon-menu"></i><span slot="title">用户管理</span></el-menu-item></a>
      <a href="/sysRole"><el-menu-item index="5-2"><i class="el-icon-menu"></i><span slot="title">角色管理</span></el-menu-item></a>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
  },
};
</script>

<style>
a {
  text-decoration: none;
  color: inherit;
}
</style>
