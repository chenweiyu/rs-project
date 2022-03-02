<template>
  <el-card class="card-carousel">
  <el-carousel  class="push" :interval="4000" arrow="always">
    <el-carousel-item v-for="item in items" :key="item.id">
      <a href="#" target="_blank">
        <img :src=item.img alt="" class="carousel_img">
      </a>

    </el-carousel-item>
  </el-carousel>
  </el-card>
</template>

<script>
export default {
  name: 'Carousel',
  data: function () {
    return {
      items: [{
        id: 1,
        title: 'How2J.cn - Java 全栈学习网站',
        img: '../../../static/carousel/1.jpg',
        },
      {
        id: 2,
        title: 'Vue.js - 渐进式 JavaScript 框架',
        img: '../../../static/carousel/2.jpg',

      },
      {
        id: 3,
        title: 'element-ui - 网站快速成型工具',
        img: '../../../static/carousel/3.jpg',

      }]
    }
  }
}
</script>

<style>
  .el-carousel__item h3 {
    color: black;
    font-size: 15px;
    /*line-height: 300px;*/
    bottom: 43px;
    float: left;
    position: relative;
  }
  .card-carousel {
    /*margin: 0 auto;*/
    margin-left: 20px;
    width: 680px;
    height: 320px;
  }
  /* .push {
    /*margin-left: -15px;*/
  .el-carousel__item:nth-child(2n) {
    background: black;
  }
  .el-carousel__item:nth-child(2n+1) {
    background: black;
  }
  .carousel_img {
    height: 100%;
    width: 100%;
    text-align: center;
  }
</style>
