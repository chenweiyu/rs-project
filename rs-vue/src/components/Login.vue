<template>
  <body id="poster">
    <el-form class="login-container" label-position="left"
           label-width="0px"
           :model="loginForm"
           ref="loginFormRef"
        :rules="loginFormRules">
      <h3 class="login_title">自然商场</h3>
      <el-form-item>
        <el-input type="text" v-model="loginForm.name"
                auto-complete="off" placeholder="账号"></el-input>
        </el-form-item>
      <el-form-item>
      <el-input type="password" v-model="loginForm.password"
                auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="login">登录</el-button>
      </el-form-item>
    </el-form>
  </body>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      loginForm: {
        name: '',
        password: ''
      },
      responseResult: [],
      loginFormRules: {
        name: [
          {
            required: true,
            message: "请输入登录名",
            trigger: "blur",
          },
          {
            min: 3,
            max: 10,
            message: "登录名长度在 3 到 10 个字符",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur",
          },
          {
            min: 5,
            max: 15,
            message: "密码长度在 5 到 15 个字符",
            trigger: "blur",
          },
        ],
      },
    }
  },
  methods: {
    login () {
      console.log("進來了", this.loginForm)
      this.$refs.loginFormRef.validate(async (valid) => {
        if (!valid) return;

       //前端密码加密
        const { data: res } = await this.$http.post(
          "/user/login",
          this.loginForm
        );

        if (res.code !== 200)
          return this.$message.error("登录失败,原因：" + res.msg);

        this.$message.success("登录成功!");
        // 1. 将登录成功之后的 token,保存到客户端的 sessionStorage(会话机制/只在当前页面生效)中 localStorage(持久话机制/关闭页面也不会忘记数据)
        //   1.1 项目中除了登录之外的API接口,必须在登录之后才能访问
        //   1.2 token 只应在当前网站打开期间生效, 所以将 token 保存在 sessionStorage中
        console.log("res",res)
        window.sessionStorage.setItem("token", res.data);   //存储到sessionStorage ，前端存储
        // 2. 通过编程式路由导航跳转到后台主页,路由地址是 /home
        this.$router.push("/index");
      });
    }
  }
}
</script>
<style>
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
  #poster {
    background:url("../assets/img/bg/login.jpg") no-repeat;
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
  body{
    margin: 0px;
  }
</style>
