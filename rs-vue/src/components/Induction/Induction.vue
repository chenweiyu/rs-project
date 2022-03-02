<template>
  <div style="margin-top: 40px">
    <el-breadcrumb separator="/" style="margin-bottom: 40px; margin-left: 200px">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>员工入职管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- <side-menu id="side-menu"></side-menu> -->
    <div class="home">
      <induction-search-bar
        @onSearch="searchList"
        ref="searchBar"
        style="margin-left: 100px"
      ></induction-search-bar>

      <h4 style="float:left; margin-left:250px; margin-top:-20px">员工在职列表</h4>
      <div style="margin-left: 250px">
        <el-table
          ref="multipleTable"
          :data="list"
          tooltip-effect="dark"
          style="width: 100%"
          border
          stripe
        >
          <el-table-column
            type="index"
            label="序号"
            width="60"
          ></el-table-column>
          <el-table-column prop="staff_id" label="员工编号"> </el-table-column>
          <el-table-column prop="staff_name" label="姓名"> </el-table-column>
          <el-table-column prop="sex" label="性别"> </el-table-column>
          <el-table-column prop="section_name" label="部门"> </el-table-column>
          <el-table-column prop="position_name" label="职位"> </el-table-column>
          <el-table-column prop="into_time" label="入职时间"> </el-table-column>
          <el-table-column label="操作" width="100px">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="info"
                icon="el-icon-message"
                plain
                @click="staffInfoDraw(scope.row.staff_id)"
                >详情
              </el-button>
              <!-- {{scope.row}} -->
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="queryInfo.pagenum"
          :page-size="queryInfo.pagesize"
          :page-sizes="[5, 10, 20, 40]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          style="margin-top:20px;margin-buttom-20px"
        >
        </el-pagination>
      </div>
    </div>
    <el-drawer
      title="员工信息详情"
      :visible.sync="drawer"
      :before-close="handleClose"
      size="50%"
    >
      <div>
        <!-- <el-button type="info" size="mini">完善个人信息</el-button> -->
        <el-form
          :model="infoList"
          ref="staffFormRef"
          :rules="addStaffFormRules"
          label-width="100px"
          style="clear: both"
        >
          <h4>员工身份信息</h4>
          <el-row>
            <el-col :span="16">
              <el-form-item label="员工编号" prop="staffId">
                <el-input
                  v-model="infoList.staffId"
                  :disabled="true"
                ></el-input>
              </el-form-item>
              <el-form-item label="姓名" prop="staffName">
                <el-input v-model="infoList.staffName"></el-input>
              </el-form-item>
              <el-row>
                <el-col :span="10">
                  <el-form-item label="性别" prop="sex">
                    <el-select v-model="infoList.sex" placeholder="请选择性别">
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
                      v-model="infoList.birthday"
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
                    <img
                      v-if="infoList.avatar"
                      :src="avatarUrl"
                      class="avatar"
                    />
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
                    >修改个人照片</span
                  >
                </div>
              </div>
            </el-col>
          </el-row>
          <el-form-item label="身份证号" prop="identity">
            <el-input v-model="infoList.identity"></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="staffPhone">
            <el-input v-model="infoList.staffPhone"></el-input>
          </el-form-item>
          <el-form-item label="籍贯" prop="nativePlace">
            <el-input v-model="infoList.nativePlace"></el-input>
          </el-form-item>
          <el-form-item label="出生地" prop="birthPlace">
            <el-input v-model="infoList.birthPlace"></el-input>
          </el-form-item>
          <el-form-item label="户口所在地" prop="registeredResidence">
            <el-input v-model="infoList.registeredResidence"></el-input>
          </el-form-item>
          <h4>员工入职信息</h4>
          <el-row>
            <el-col :span="10">
              <el-form-item label="部门" prop="sectionName">
                <el-input
                  v-model="infoList.sectionName"
                  :disabled="true"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="岗位" prop="positionName">
                <el-input
                  v-model="infoList.positionName"
                  :disabled="true"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="10">
              <el-form-item label="入职日期" prop="intoTime">
                <el-date-picker
                  v-model="infoList.intoTime"
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
                  v-model="infoList.attendTime"
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
                  v-model="infoList.workForm"
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
                  v-model="infoList.peopleSource"
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
                  v-model="infoList.highestEducation"
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
              <el-form-item label="最高学位" prop="highestdegree">
                <el-select
                  v-model="infoList.highestDegree"
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
          <el-form-item label="毕业院校" prop="graduateSchool">
            <el-input v-model="infoList.graduateSchool"></el-input>
          </el-form-item>
          <el-form-item label="所学专业" prop="major">
            <el-input v-model="infoList.major"></el-input>
          </el-form-item>
          <el-row>
            <el-col :span="10">
              <el-form-item label="毕业日期">
                <el-date-picker
                  v-model="infoList.graduateTime"
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
                  v-model="infoList.isOntrial"
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
          <el-row v-show="infoList.isOntrial == '是'">
            <el-col :span="10">
              <el-form-item label="试用期开始日期">
                <el-date-picker
                  v-model="infoList.OntrialBeginTime"
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
                  v-model="infoList.OntrialEndTime"
                  type="date"
                  format="yyyy-MM-dd"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期"
                >
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <h4>其他信息</h4>
          <el-form-item label="民族" prop="nation">
            <el-input v-model="infoList.nation"></el-input>
          </el-form-item>
          <el-row>
            <el-col :span="10">
              <el-form-item label="政治面貌" prop="politicalOutlook">
                <el-select
                  v-model="infoList.politicalOutlook"
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
                  v-model="infoList.maritalStatus"
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
                  v-model="infoList.bloodType"
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
            <el-input v-model="infoList.email"></el-input>
          </el-form-item>
          <el-form-item label="身高" prop="height">
            <el-input v-model="infoList.height"></el-input>
          </el-form-item>
        </el-form>
        <!-- 底部 -->
        <span slot="footer" class="drawer-footer">
          <el-button type="primary" @click="update">保 存</el-button>
        </span>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import InductionSearchBar from "./InductionSearchBar";
import SideMenu from "../home/SideMenu";
export default {
  name: "StaffInfo",
  components: { InductionSearchBar, SideMenu },
  data() {
    return {
      action: "http://localhost:9001/staffInfo/addStaff/avatar",
      avatarUrl: "",
      avatar: "",
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

      drawer: false,
      //封装的查询参数
      queryInfo: {
        query: "",
        pagenum: 1,
        pagesize: 10,
      },

      list: [],
      infoList: [],
      total: 0,
      totalLeaveWork: 0,
      allTypeList: ["辞职", "退休", "休假"],
      allIsIntoList: ["是", "否"],

      value: "",

      leaveDialogVisible: false,
      infoList: {
        OntrialBeginTime: "",
        OntrialEndTime: "",
        attendTime: "",
        avatar: "",
        birthPlace: "",
        birthday: "",
        bloodType: "",
        email: "",
        graduateSchool: "",
        graduateTime: "",
        height: "",
        highestDegree: "",
        highestEducation: "",
        id: "",
        identity: "",
        intoTime: "",
        isOntrial: "",
        major: "",
        maritalStatus: "",
        nation: "",
        nativePlace: "",
        ontrialBeginTime: "",
        ontrialEndTime: "",
        peopleSource: "",
        politicalOutlook: "",
        positionId: "",
        registeredResidence: "",
        sectionId: "",
        sex: "",
        staffId: "",
        staffName: "",
        staffPhone: "",
        workForm: "",
      },
      addStaffFormRules: {
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
  //生命函数
  created() {
    this.getList();
  },
  //方法
  methods: {
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
    handleAvatarChange(file, filelist) {
      this.infoList.avatar = URL.createObjectURL(file.raw);
      this.avatarUrl = this.infoList.avatar
      console.log("----前端图片地址---" + this.infoList.avatar);
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
    async update() {
      console.log("----msg------");
      this.$refs.upload.submit();
      const { data: res } = await this.$http.post(
        "/staffInfo/induction/update",
        this.infoList
      );
      if (res.code !== 200) {
        return this.$message.error("更新信息失败！");
      }

      // 提示修改成功
      this.$message.success("更新信息成功！");
      // 关闭对话框
      this.editDialogVisible = false;
      // 刷新数据列表
      this.getList();
    },
    async staffInfoDraw(staff_id) {
      console.log(staff_id);
      const { data: res } = await this.$http.get("/staffInfo/info/" + staff_id);
      if (res.code !== 200) {
        return this.$message.error("查询信息失败！");
      }
      console.log(res.data);
      this.infoList = res.data; // 对象复制
      this.getAllSection();
      this.getAllPosition();
      this.avatarUrl = "http://localhost:9001/" + this.infoList.avatar;
      console.log("---msg---", this.avatarUrl);
      this.drawer = true;
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },
    //查询  async  await
    async getList() {
      console.log(this.queryInfo.query);
      // {k1:v1,k2:v2}  ---> ?k1=v1&k2=v2    query=&pagenum=1&pagesize=10
      console.log(this.queryInfo, this.$qs.stringify(this.queryInfo));

      const { data: res } = await this.$http.post(
        "/staffInfo/list",
        this.$qs.stringify(this.queryInfo)
      );

      console.log(res);

      if (res.code !== 200) {
        return this.$message.error("获取列表失败！,错误信息为：" + res.msg);
      }

      this.list = res.data.records;

      this.total = res.data.total;
    },
    async getListLeaveWork() {
      const { data: res } = await this.$http.post(
        "/leaveWork/list",
        this.$qs.stringify(this.queryInfo)
      );

      console.log(res);

      if (res.code !== 200) {
        return this.$message.error("获取列表失败！,错误信息为：" + res.msg);
      }

      this.listLeaveWork = res.data.records;

      this.totalLeaveWork = res.data.total;
    },
    async searchList() {
      this.queryInfo.query = this.$refs.searchBar.keywords;
      console.log(this.queryInfo.query);
      // {k1:v1,k2:v2}  ---> ?k1=v1&k2=v2    query=&pagenum=1&pagesize=10
      console.log(this.queryInfo, this.$qs.stringify(this.queryInfo));

      const { data: res } = await this.$http.post(
        "/staffInfo/list",
        this.$qs.stringify(this.queryInfo)
      );

      console.log(res);

      if (res.code !== 200) {
        return this.$message.error("获取列表失败！,错误信息为：" + res.msg);
      }

      this.list = res.data.records;

      this.total = res.data.total;
    },

    //分页
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.queryInfo.pagesize = val;
      this.getList();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.queryInfo.pagenum = val; // 获取新页码
      this.getList();
    },

    async staffInfo(staff_id) {
      console.log(staff_id);
      const { data: res } = await this.$http.get("/staffInfo/info/" + staff_id);
      if (res.code !== 200) {
        return this.$message.error("查询信息失败！");
      }
      console.log(res.data);
      this.leaveForm = res.data; // 对象复制
      this.leaveDialogVisible = true;
    },
    saveLeaveWorkSection() {
      console.log("结果是: ", this.leaveForm);
      this.$refs.leaveFormRef.validate(async (valid) => {
        if (!valid) return;

        const { data: res } = await this.$http.post(
          "/leaveWork/save",
          this.leaveForm
        );
        if (res.code !== 200) {
          return this.$message.error("离职申请失败！");
        }

        // 提示修改成功
        this.$message.success("离职申请成功！");
        // 关闭对话框
        this.leaveDialogVisible = false;
        // 刷新数据列表
        this.getList();
        this.getListLeaveWork();
      });
    },
    editDialogClosed() {
      this.$refs.leaveFormRef.resetFields();
    },
  },
};
</script>

<style scoped>
.home {
  width: 1250px;
  margin-top: -20px;
}
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
.drawer-footer {
  float: right;
}
</style>
