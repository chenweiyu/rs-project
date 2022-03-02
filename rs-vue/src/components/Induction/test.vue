<template>
  <el-form
    :model="addStaffForm"
    ref="addStaffFormRef"
    label-width="140px"
  >
    <div style="width: 980px; margin-left: 250px">
      <el-steps
        :active="active"
        finish-status="success"
        simple
        style="margin-top: 20px; margin-bottom: 20px"
      >
        <el-step title="身份信息"></el-step>
        <el-step title="入职信息"></el-step>
        <el-step title="其他"></el-step>
      </el-steps>
      <p
        style="
          color: red;
          font-size: 8px;
          margin-left: -830px;
          margin-bottom: 20px;
        "
      >
        请注意！标注 "*" 的是必填项
      </p>

      <div v-show="active == 0">
        <el-row>
          <el-col :span="19">
            <el-form-item label="员工编号" prop="staff_id">
              <el-input v-model="addStaffForm.staff_id"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="staff_name">
              <el-input v-model="addStaffForm.staff_name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <div style="width: 145px; height: 195px; margin-left: 60px">
              <div class="img-style">
                <el-upload
                  :action="action"
                  :show-file-list="false"
                  :auto-upload="false"
                  :on-change="handleAvatarChange"
                  :before-upload="beforeAvatarUpload"
                  ref="upload"
                  name="avatar"
                >
                  <img v-if="addStaffForm.avatar" :src="addStaffForm.avatar" class="avatar" />
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
              </div>
              <div style="width: 140px; height: 30px">
                <span
                  style="
                    font-size: 14px;
                    line-height: 30px;
                    text-algin: center;
                    color: #d69d9d;
                  "
                  >上传个人照片</span
                >
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
      <div v-show="active == 1">
        <img
              v-if="addStaffForm.avatar"
              :src="addStaffForm.avatar"
              class="avatar"
            />
      </div>
      <!-- <el-button style="margin-top: 12px;" @click="next">下一步</el-button> -->
      <el-button
        round
        size="mini"
        v-if="active != 0"
        @click="active--"
        v-show="this.active < 3"
        >上一步</el-button
      >
      <el-button
        type="primary"
        round
        size="mini"
        @click="next"
        v-text="active == 2 ? '完成' : '下一步'"
        v-show="this.active < 3"
      ></el-button>
    </div>
  </el-form>
</template>

<script>
export default {
  name: "AddStaff",
  data() {
    return {
      action: "http://localhost:9001" + "/staffInfo/addStaff/avatar",
      active: 0,

      addStaffForm: {
        staff_id: "",
        staff_name: "",
        avatar: ""
      },
    };
  },
  created() {
    this.getAllSection();
    this.getAllPosition();
  },
  methods: {
    handleAvatarChange(file, filelist) {
      this.addStaffForm.avatar = URL.createObjectURL(file.raw);
      console.log("----前端图片地址---" + this.addStaffForm.avatar);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    async getAllSection() {
      const { data: res } = await this.$http.post("/section/list");
      console.log(res);
      if (res.code !== 200) {
        return this.$message.error("获取列表失败！");
      }
      this.allSectionList = res.data;
      console.log(this.allSectionList);
    },
    async getAllPosition() {
      const { data: res } = await this.$http.post("/position/list");
      console.log(res);
      if (res.code !== 200) {
        return this.$message.error("获取列表失败！");
      }
      this.allPositionList = res.data;
    },
    async next() {
      if (this.active === 0) {

          this.active++;
      } else if (this.active === 1) {
          this.active++;
      } else {
        this.active = 0;
        console.log(this.addStaffForm);
        this.$refs.upload.submit();
        console.log("可以下来")
        // let config = {
        //   headers: {
        //     "Content-Type": "multipart/form-data",
        //   },
        // };
        // const { data: res } = await this.$http.post(
        //   "/staffInfo/addStaff/save1",
        //   this.addStaffForm
        // );
        // console.log(res);
        // if (res.code !== 200) {
        //   return this.$message.error("添加信息失败！,错误信息为：" + res.msg);
        // }
        // this.$message.success("添加信息成功！");
      }
    },
  },
};
</script>

<style scoped>
.img-style {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  width: 140px;
  height: 160px;
}

/* .avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
} */
.img-style:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 140px;
  height: 160px;
  line-height: 160px;
  text-align: center;
}
.avatar {
  width: 140px;
  height: 160px;
  display: block;
  border-radius: 6px;
}
</style>
