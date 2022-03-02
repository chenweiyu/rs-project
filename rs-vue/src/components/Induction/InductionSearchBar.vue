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
    <el-link href="/AddStaff"><el-button type="primary" size="small" @click="showAddDialog" plain>员工入职操作</el-button></el-link>
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
    }
  }
}
</script>

<style scoped>
</style>
