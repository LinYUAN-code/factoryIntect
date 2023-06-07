<script setup>
import { ref } from "vue";
import { login } from "../utils/request";
import { useRouter } from "vue-router";
const name = ref("");
const password = ref("");
const router = useRouter();

const handleLogin = async () => {
  const res = await login({
    name: name.value,
    password: password.value,
  });
  console.log(res.data);
  if (!res.data.data) {
    name.value = "";
    password.value = "";
    alert("密码错误");
    return;
  }
  localStorage.setItem("name", name.value);
  localStorage.setItem("token", res.data.data);
  router.push("/");
};

const gotoRegiste = () => {
  router.push("/registe");
};
</script>
 
<template>
  <div class="outer-container">
    <div class="login-container">
      <div class="login-text">登录</div>
      <div class="input-container">
        <el-input
          class="input1"
          v-model="name"
          placeholder="请输入你的用户名"
        />
        <el-input
          class="input2"
          v-model="password"
          type="password"
          placeholder="请输入你的密码"
          show-password
        />
      </div>
      <div class="btn-container">
        <el-button type="primary" @click="handleLogin">登录</el-button>
        <el-button class="re-btn" type="primary" @click="gotoRegiste"
          >注册</el-button
        >
      </div>
    </div>
  </div>
</template>

<style lang='scss' scoped>
.outer-container {
  width: 100vw;
  height: 100vh;
  background-image: url("/b1.jpg");
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
  box-shadow: 1px 1px 5px rgba(0, 0, 0, 0.3);
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