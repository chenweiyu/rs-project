<template>
  <div style="margin-bottom: 30px;">
    <el-input
      @keyup.enter.native="search"
      placeholder="请输入内容进行搜索..."
      prefix-icon="el-icon-search"
      size="small"
      style="width: 400px;margin-right: 10px;"
      v-model="keywords">
      <template #append>
              <el-button icon="el-icon-search" @click="search"></el-button>
            </template>
    </el-input>
    <el-button type="primary" @click="showAddDialog" >添加</el-button>
    <el-button type="primary" @click="deleteMore">批量删除</el-button>
    <!-- <el-button size="small" type="primary" icon="el-icon-search" @click="searchClick">搜索</el-button> -->
  </div>
</template>

<script>
export default {
  name: 'SearchBar',
  data () {
    return {
      keywords: '',
      books: [],
      cardLoading: []
    }
  },
  methods: {
    search () {
      this.$emit('onSearch')
    },
    showAddDialog () {
      this.$emit('onAddShow')
    },
    deleteMore () {
      this.$emit('onDeleteMore')
    }
  }
}
</script>

<style scoped>
</style>
