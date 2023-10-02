<template>
  <div id="app">
    <el-container style="height: 100vh; flex-direction: column">
      <el-header>
        <!--菜單-->
        <el-menu
          :default-active="activeIndex"
          @select="handleSelect"
          class="el-menu-demo"
          mode="horizontal"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
        >
          <el-menu-item index="/Index">首頁</el-menu-item>
          <el-menu-item index="/CardMean">牌義說明</el-menu-item>
          <el-menu-item index="/DrawCard">抽牌</el-menu-item>
          <el-menu-item index="/CardLog">歷史紀錄</el-menu-item>
        </el-menu>
      </el-header>
      <el-main style="flex: 1; overflow-y: auto">
        <router-view></router-view>
      </el-main>
    </el-container>
    <el-footer>©2023 James Chueh for the coding.</el-footer>
  </div>
</template>

<script>
import router from "./router";

export default {
  name: "App",
  data() {
    return {
      activeIndex: this.$route.path,
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key + keyPath);
      this.$router.push(key);
    },
  },
  components: { router },
};
</script>

<style>
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>
