<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>用户登陆</p>
    </header>

    <!-- 表单部分 -->
    <ul class="form-box">
      <li>
        <div class="title">手机号码：</div>
        <div class="content">
          <input type="text" v-model="userid" placeholder="手机号码" />
        </div>
      </li>
      <li>
        <div class="title">密码：</div>
        <div class="content">
          <input type="password" v-model="password" placeholder="密码" />
        </div>
      </li>
    </ul>

    <div class="button-login">
      <button @click="login">登陆</button>
    </div>
    <div class="button-register">
      <button @click="register">去注册</button>
    </div>

    <!-- 底部菜单部分 -->
    <Footer></Footer>
  </div>
</template>

<script setup>
import Footer from "../components/Footer.vue";
import { ref, inject } from "vue";
import qs from "qs";
import { useRouter } from "vue-router";
import { setSessionStorage } from "../common.js";

const userid = ref("");
const password = ref("");

const router = useRouter();
const axios = inject("axios");
const login = () => {
  if (userid.value == "") {
    alert("手机号码不能为空！");
    return;
  }
  if (password.value == "") {
    alert("密码不能为空！");
    return;
  }

  //登录
  axios
    .post(
      "UserController/getUserByIdByPass",
      qs.stringify({ userid: userid.value, password: password.value })
    )
    .then((res) => {
      console.log(res.data);
      let user = res.data;
      if (user == null) {
        alert("用户名或密码错误");
      } else {
        user.userImg = "";
        setSessionStorage("user", user);
        router.go(-1);
      }
    })
    .catch((err) => {
      console.log(err);
    });
};

const register = () => {
  router.push({ path: "register" });
};

</script>

<style scoped>
/****************** 总容器 ******************/
.wrapper {
  width: 100%;
  height: 100%;
}

/****************** header部分 ******************/
.wrapper header {
  width: 100%;
  height: 12vw;
  background-color: #0097ff;
  color: #fff;
  font-size: 4.8vw;

  position: fixed;
  left: 0;
  top: 0;
  z-index: 1000;

  display: flex;
  justify-content: center;
  align-items: center;
}

/****************** 表单部分 ******************/
.wrapper .form-box {
  width: 100%;
  margin-top: 12vw;
}
.wrapper .form-box li {
  box-sizing: border-box;
  padding: 4vw 3vw 0 3vw;
  display: flex;
  align-items: center;
}
.wrapper .form-box li .title {
  flex: 0 0 18vw;
  font-size: 3vw;
  font-weight: 700;
  color: #666;
}
.wrapper .form-box li .content {
  flex: 1;
}
.wrapper .form-box li .content input {
  border: none;
  outline: none;
  width: 100%;
  height: 4vw;
  font-size: 3vw;
}
.wrapper .button-login {
  width: 100%;
  box-sizing: border-box;
  padding: 4vw 3vw 0 3vw;
}
.wrapper .button-login button {
  width: 100%;
  height: 10vw;
  font-size: 3.8vw;
  font-weight: 700;
  color: #fff;
  background-color: #38ca73;
  border-radius: 4px;

  border: none;
  outline: none;
}
.wrapper .button-register {
  width: 100%;
  box-sizing: border-box;
  padding: 4vw 3vw 0 3vw;
}
.wrapper .button-register button {
  width: 100%;
  height: 10vw;
  font-size: 3.8vw;
  font-weight: 700;
  /*与上面登陆按钮不同的只有颜色、背景色、边框不同*/
  color: #666;
  background-color: #eee;
  border: solid 1px #ddd;
  border-radius: 4px;

  border: none;
  outline: none;
}
</style>
