<template>
  <el-form
    :model="addStaffForm"
    ref="addStaffFormRef"
    :rules="addStaffFormRules"
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
            <el-form-item label="员工编号" prop="staffId">
              <el-input v-model="addStaffForm.staffId"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="staffName">
              <el-input v-model="addStaffForm.staffName"></el-input>
            </el-form-item>
            <el-row>
              <el-col :span="10">
                <el-form-item label="性别" prop="sex">
                  <el-select
                    v-model="addStaffForm.sex"
                    placeholder="请选择性别"
                  >
                    <el-option
                      v-for="(item, index) in allSexList"
                      :key="index"
                      :label="item"
                      :value="item"
                    >
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="10">
                <el-form-item label="出生日期">
                  <el-date-picker
                    v-model="addStaffForm.birthday"
                    type="date"
                    format="yyyy-MM-dd"
                    value-format="yyyy-MM-dd"
                    placeholder="选择日期"
                  >
                  </el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
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
                  <img v-if="avatar" :src="avatar" class="avatar" />
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
        <el-form-item label="身份证号" prop="identity">
          <el-input v-model="addStaffForm.identity"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="staffPhone">
          <el-input v-model="addStaffForm.staffPhone"></el-input>
        </el-form-item>
        <el-form-item label="籍贯" prop="nativePlace">
          <el-input v-model="addStaffForm.nativePlace"></el-input>
        </el-form-item>
        <el-form-item label="出生地" prop="birthPlace">
          <el-input v-model="addStaffForm.birthPlace"></el-input>
        </el-form-item>
        <el-form-item label="户口所在地" prop="registeredResidence">
          <el-input v-model="addStaffForm.registeredResidence"></el-input>
        </el-form-item>
      </div>

      <div v-show="active == 1">
        <el-row>
          <el-col :span="10">
            <el-form-item label="部门" prop="sectionId">
              <el-select
                v-model="addStaffForm.sectionId"
                placeholder="请选择部门"
              >
                <el-option
                  v-for="item in allSectionList"
                  :key="item.id"
                  :label="item.section_name"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="岗位" prop="positionId">
              <el-select
                v-model="addStaffForm.positionId"
                placeholder="请选择岗位"
              >
                <el-option
                  v-for="item in allPositionList"
                  :key="item.id"
                  :label="item.positionName"
                  :value="item.id"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="10">
            <el-form-item label="入职日期" prop="intoTime">
              <el-date-picker
                v-model="addStaffForm.intoTime"
                type="date"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                placeholder="选择日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="参加工作日期" prop="attendTime">
              <el-date-picker
                v-model="addStaffForm.attendTime"
                type="date"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                placeholder="选择日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="10">
            <el-form-item label="用工形式" prop="workForm">
              <el-select
                v-model="addStaffForm.workForm"
                placeholder="请选择用工形式"
              >
                <el-option
                  v-for="(item, index) in allWorkFormList"
                  :key="index"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="人员来源" prop="peopleSource">
              <el-select
                v-model="addStaffForm.peopleSource"
                placeholder="请选择人员来源"
              >
                <el-option
                  v-for="(item, index) in allPeopleSourceList"
                  :key="index"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="10">
            <el-form-item label="最高学历" prop="highestEducation">
              <el-select
                v-model="addStaffForm.highestEducation"
                placeholder="请选择最高学历"
              >
                <el-option
                  v-for="(item, index) in allHighestEducationList"
                  :key="index"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="最高学位" prop="highestDegree">
              <el-select
                v-model="addStaffForm.highestDegree"
                placeholder="请选择最高学位"
              >
                <el-option
                  v-for="(item, index) in allHighestDegreeList"
                  :key="index"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="毕业院校" prop="graduate_school">
          <el-input v-model="addStaffForm.graduateSchool"></el-input>
        </el-form-item>
        <el-form-item label="所学专业" prop="major">
          <el-input v-model="addStaffForm.major"></el-input>
        </el-form-item>
        <el-row>
          <el-col :span="10">
            <el-form-item label="毕业日期">
              <el-date-picker
                v-model="addStaffForm.graduateTime"
                type="date"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                placeholder="选择日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="是否有试用期" prop="isOntrial">
              <el-select
                v-model="addStaffForm.isOntrial"
                placeholder="请选择是或否"
              >
                <el-option
                  v-for="(item, index) in allOntrialList"
                  :key="index"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row v-show="addStaffForm.isOntrial == '是'">
          <el-col :span="10">
            <el-form-item label="试用期开始日期">
              <el-date-picker
                v-model="addStaffForm.OntrialBeginTime"
                type="date"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                placeholder="选择日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="试用期结束日期">
              <el-date-picker
                v-model="addStaffForm.OntrialEndTime"
                type="date"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                placeholder="选择日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
      </div>

      <div v-show="active == 2">
        <el-form-item label="民族" prop="nation">
          <el-input v-model="addStaffForm.nation"></el-input>
        </el-form-item>
        <el-row>
          <el-col :span="10">
            <el-form-item label="政治面貌" prop="politicalOutlook">
              <el-select
                v-model="addStaffForm.politicalOutlook"
                placeholder="请选择政治面貌"
              >
                <el-option
                  v-for="(item, index) in allPoliticalOutlookList"
                  :key="index"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="婚姻状况" prop="maritalStatus">
              <el-select
                v-model="addStaffForm.maritalStatus"
                placeholder="请选择婚姻状况"
              >
                <el-option
                  v-for="(item, index) in allMaritalStatusList"
                  :key="index"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="血型" prop="bloodType">
              <el-select
                v-model="addStaffForm.bloodType"
                placeholder="请选择血型"
              >
                <el-option
                  v-for="(item, index) in allBloodTypeList"
                  :key="index"
                  :label="item"
                  :value="item"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="addStaffForm.email"></el-input>
        </el-form-item>
        <el-form-item label="身高" prop="height">
          <el-input v-model="addStaffForm.height"></el-input>
        </el-form-item>
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
        v-text="active == 2 ? '申請' : '下一步'"
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
      action: "http://localhost:9001/staffInfo/addStaff/avatar",
      avatar: "",
      active: 0,
      allCheckList: [],
      allSexList: ["男", "女"],
      allSectionList: [],
      allPositionList: [],
      allWorkFormList: ["正式员工", "临时员工"],
      allPeopleSourceList: ["校园招聘", "社会招聘", "其它"],
      allHighestEducationList: ["高中及以下", "大专", "本科", "研究生"],
      allHighestDegreeList: [
        "无学位",
        "学士",
        "双学士",
        "硕士",
        "博士",
        "博士后",
      ],
      allPoliticalOutlookList: ["党员", "预备党员", "团员", "群众"],
      allBloodTypeList: ["A 型", "B 型", "AB 型", "O 型", "其它血型"],
      allMaritalStatusList: ["未婚", "已婚", "离婚", "丧偶", "其它"],
      allOntrialList: ["是", "否"],

      addStaffForm: {
        staffId: "",
        staffName: "",
        sex: "",
        identity: "",
        birthday: "",
        staffPhone: "",
        birthPlace: "",
        registeredResidence: "",
        sectionId: "",
        positionId: "",
        intoTime: "",
        attendTime: "",
        workForm: "",
        peopleSource: "",
        highestEducation: "",
        highestDegree: "",
        graduateSchool: "",
        major: "",
        graduateTime: "",
        isOntrial: "",
        OntrialBeginTime: "",
        OntrialEndTime: "",
        nation: "",
        politicalOutlook: "",
        email: "",
        height: "",
        bloodType: "",
        maritalStatus: "",
      },
      addStaffFormRules: {
        staffId: [
          { required: true, message: "请输入员工编号", trigger: "blur" },
          {
            min: 1,
            max: 20,
            message: "长度在 1 到 20 个数字",
            trigger: "blur",
          },
        ],
        staffName: [
          { required: true, message: "请输入员工姓名", trigger: "blur" },
          {
            min: 1,
            max: 10,
            message: "长度在 1 到 5 个汉字",
            trigger: "blur",
          },
        ],
        sex: [{ required: true, message: "请选择性别", trigger: "blur" }],
        identity: [
          { required: true, message: "请输入身份证号码", trigger: "blur" },
          {
            min: 1,
            max: 18,
            message: "长度在 1 到 18 个数字",
            trigger: "blur",
          },
        ],
        birthday: [
          { required: true, message: "请选择出身日期", trigger: "blur" },
        ],
        staffPhone: [
          { required: true, message: "请输入手机联系方式", trigger: "blur" },
          {
            min: 1,
            max: 11,
            message: "长度在 1 到 11 个数字",
            trigger: "blur",
          },
        ],
        sectionId: [
          { required: true, message: "请选择任职部门", trigger: "blur" },
        ],
        positionId: [
          { required: true, message: "请选择任职岗位", trigger: "blur" },
        ],
        intoTime: [
          { required: true, message: "请选择入职日期", trigger: "blur" },
        ],
        attendTime: [
          { required: true, message: "请选择参加工作日期", trigger: "blur" },
        ],
        workForm: [
          { required: true, message: "请选择工作方式", trigger: "blur" },
        ],
        peopleSource: [
          { required: true, message: "请选择人员来源", trigger: "blur" },
        ],
        highestEducation: [
          { required: true, message: "请选择最高学历", trigger: "blur" },
        ],
        highestDegree: [
          { required: true, message: "请选择最高学位", trigger: "blur" },
        ],
        isOntrial: [
          { required: true, message: "请选择是或否", trigger: "blur" },
        ],
        OntrialBeginTime: [
          { required: true, message: "请选择试用期开始日期", trigger: "blur" },
        ],
        OntrialEndTime: [
          { required: true, message: "请选择试用期结束日期", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getAllSection();
    this.getAllPosition();
  },
  methods: {
    handleAvatarChange(file, filelist) {
      this.avatar = URL.createObjectURL(file.raw);
      console.log("----前端图片地址---" + this.avatar);
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
      let flag = 0;
      let flag1 = 0;
      if (this.active === 0) {
        this.$refs.addStaffFormRef.validateField(
          [
            "staff_id",
            "staff_name",
            "sex",
            "identity",
            "birthday",
            "staff_phone",
          ],
          async (valid) => {
            if (valid) {
              flag = 1;
            }
          }
        );
        if (flag === 0) {
          this.active++;
        }
      } else if (this.active === 1) {
        this.getAllSection();
        this.$refs.addStaffFormRef.validateField(
          [
            "section_name",
            "position_name",
            "into_time",
            "attend_time",
            "work_form",
            "people_source",
            "highest_education",
            "highest_degree",
            "isOntrial",
          ],
          async (valid) => {
            if (valid) {
              flag1 = 1;
            }
          }
        );
        if (this.addStaffForm.isOntrial === "是") {
          this.$refs.addStaffFormRef.validateField(
            ["OntrialBeginTime", "OntrialEndTime"],
            async (valid) => {
              if (valid) {
                flag1 = 1;
              }
            }
          );
        }
        if (flag1 === 0) {
          this.active++;
        }
      } else {
        this.active = 0;
        console.log(this.addStaffForm);
        this.$refs.upload.submit();
        // let config = {
        //   headers: {
        //     "Content-Type": "multipart/form-data",
        //   },
        // };
        const { data: res } = await this.$http.post(
          "/staffInfo/addStaff/save",
          this.addStaffForm
        );
        console.log(res);
        if (res.code !== 200) {
          return this.$message.error("添加信息失败！,错误信息为：" + res.msg);
        }
        this.$router.push({
        path: 'induction'
      })
        this.$message.success("添加信息成功！");
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
