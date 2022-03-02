<template>
  <div style="margin-bottom: 30px">
    <el-input
      @keyup.enter.native="search"
      placeholder="请输入内容进行搜索..."
      prefix-icon="el-icon-search"
      size="small"
      style="width: 400px; margin-right: 10px"
      v-model="keywords"
    >
      <template #append>
        <el-button icon="el-icon-search" @click="search"></el-button>
      </template>
    </el-input>
    <el-button size="mini" type="primary" icon="el-icon-edit" @click="normal"
      >正常</el-button
    >
    <el-button size="mini" type="primary" icon="el-icon-edit" @click="delay"
      >延期</el-button
    >
    <el-button
      size="mini"
      type="danger"
      icon="el-icon-delete"
      @click="noEmploy"
      >不录用
    </el-button>
    <!-- <el-button size="small" type="primary" icon="el-icon-search" @click="searchClick">搜索</el-button> -->
  </div>
</template>

<script>
export default {
  name: "OntrialSearchBar",
  data() {
    return {
      keywords: "",
      books: [],
      cardLoading: [],
    };
  },
  methods: {
    search() {
      this.$emit("onSearch");
    },
    normal() {
      this.$emit("onNormal");
    },
    delay() {
      this.$emit("onDel");
    },
    noEmploy() {
      this.$emit("onNoEmploy");
    }
  },
};
</script>

<style scoped>
</style>
