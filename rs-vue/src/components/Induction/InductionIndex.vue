<template>
  <div style="margin-top: 40px">
    <side-menu id="side-menu"></side-menu>
    <div class="home" style="margin-left: 140px">
      <router-view />
    </div>
  </div>
</template>

<script>
import SideMenu from "../home/SideMenu";
import About from "@/components/common/About";
export default {
  name: "InductionIndex",
  components: { SideMenu, About },
};
</script>

<style scoped>
.home {
  width: 990px;
  margin-left: auto;
  margin-right: auto;
  margin-top: -20px;
}
#side-menu {
  position: fixed;
  margin-left: 50%;
  left: -680px;
  top: 100px;
}
</style>
