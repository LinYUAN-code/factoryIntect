<script setup>
</script>
 
<template>
  <div class="outer-container">
    <div class="login-container">
      <div class="login-text">注册</div>
      <div class="input-container">
        <el-input
          class="input1"
          v-model="input"
          placeholder="请输入你的用户名"
        />
        <el-input
          class="input2"
          v-model="input"
          type="password"
          placeholder="请输入你的密码"
          show-password
        />
      </div>
      <div class="btn-container">
        <el-button class="re-btn" type="primary">注册</el-button>
      </div>
    </div>
  </div>
</template>

<style lang='scss' scoped>
.outer-container {
  width: 100vw;
  height: 100vh;
  background-image: url("/b2.jpeg");
  background-repeat: no-repeat;
  background-size: cover;
  position: fixed;
  top: 0%;
}
.login-container {
  width: 300px;
  background-color: white;
  margin: 0 auto;
  margin-top: 300px;
  padding: 40px 20px;
  padding-bottom: 10px;
  border-radius: 10px;
  box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.3);
  .login-text {
    color: rgba(0, 0, 0, 0.6);
    font-size: 20px;
    font-weight: 500;
    margin-bottom: 10px;
    text-align: center;
  }
  .input-container {
    display: flex;
    flex-direction: column;
    width: 300px;
    .input1 {
      margin-top: 0px;
    }
    .input2 {
      margin-top: 20px;
    }
  }
  .btn-container {
    margin-top: 15px;
    display: flex;
    flex-direction: row-reverse;
    .re-btn {
      margin-right: 10px;
    }
  }
}
</style>