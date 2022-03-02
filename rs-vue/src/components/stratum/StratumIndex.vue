<template>
  <div style="margin-top: 40px">
    <stratum-side-menu id="side-menu"></stratum-side-menu>
      <div class="home" style="margin-left:350px">
        <router-view></router-view>
        <!-- <update-card id="update-card"></update-card>
        <slogan id="slogan"></slogan> -->
        <about id="about"></about>
      </div>
  </div>
</template>
<script>
import StratumSideMenu from './StratumSideMenu'
import About from '@/components/common/About'
export default {
  name: 'StratumIndex',
  components: {StratumSideMenu, About}
}
</script>
<style scoped>
  .home {
    width: 990px;
    margin-left: auto;
    margin-right: auto;
    margin-top: -20px;
  }
  #header-div {
    padding-bottom: 20px;
    padding-left: 5px;
    background-color: white;
  }
  #side-menu {
    position: fixed;
    margin-left: 50%;
    left: -680px;
    top: 100px;
  }
  #about{
    margin-top: 20px;
  }
</style>
