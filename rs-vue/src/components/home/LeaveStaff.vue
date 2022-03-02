<template>
  <div style="margin-top: 40px">
    <side-menu id="side-menu"></side-menu>
    <div class="home" style="margin-left: 400px">
      <leave-search-bar
        @onSearch="searchList"
        ref="searchBar"
        style="margin-left: 100px; margin-top: 20px"
      ></leave-search-bar>
      <div>
        <el-table
          ref="multipleTable"
          :data="listLeaveWork"
          tooltip-effect="dark"
          style="width: 100%"
        >
          <el-table-column
            type="index"
            label="序号"
            width="60"
          ></el-table-column>
          <el-table-column prop="staff_id" label="员工编号"> </el-table-column>
          <el-table-column prop="staff_name" label="姓名"> </el-table-column>
          <el-table-column prop="section_name" label="部门"> </el-table-column>
          <el-table-column prop="position_name" label="岗位"> </el-table-column>
          <el-table-column prop="leave_type" label="离职类型">
          </el-table-column>
          <el-table-column prop="leave_time" label="离职时间">
          </el-table-column>
          <el-table-column prop="leave_go" label="离职去向"> </el-table-column>
          <el-table-column prop="is_into" label="是否进入人才库">
          </el-table-column>
          <el-table-column prop="remark" label="备注"> </el-table-column>
        </el-table>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="queryInfo.pagenum"
          :page-size="queryInfo.pagesize"
          :page-sizes="[5, 10, 20, 40]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="totalLeaveWork"
          style="margin-top:20px;margin-buttom-20px"
        >
        </el-pagination>
      </div>
      <el-dialog
        title="员工离职申请表单"
        :visible.sync="leaveDialogVisible"
        width="50%"
        @close="editDialogClosed"
      >
        <el-form
          :model="leaveForm"
          ref="leaveFormRef"
          :rules="leaveFormRules"
          label-width="110px"
        >
          <el-form-item label="员工编号" prop="staff_id">
            <el-input v-model="leaveForm.staff_id" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="staff_name">
            <el-input
              v-model="leaveForm.staff_name"
              :disabled="true"
            ></el-input>
          </el-form-item>
          <!-- <el-row>
              <el-col :span="10"> -->
          <el-form-item label="部门" prop="section_name">
            <el-input
              v-model="leaveForm.section_name"
              :disabled="true"
            ></el-input>
          </el-form-item>
          <el-form-item label="岗位" prop="position_name">
            <el-input
              v-model="leaveForm.position_name"
              :disabled="true"
            ></el-input>
          </el-form-item>
          <!--</el-col>
              <el-col :span="11" style="padding-left: 20px">
                <el-form-item label="请选择调转后的部门" prop="sectionName">
                  <el-select
                    v-model="transferForm.sectionAfter"
                    placeholder="请选择部门"
                  >
                    <el-option
                      v-for="item in allCheckList"
                      :key="item.id"
                      :label="item.section_name"
                      :value="item.section_name"
                    >
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row> -->
          <el-form-item label="离职类型" prop="leave_type">
            <el-select
              v-model="leaveForm.leave_type"
              placeholder="请选择类型"
              style="margin-right: 390px"
            >
              <el-option
                v-for="(item, index) in allTypeList"
                :key="index"
                :label="item"
                :value="item"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="离职去向" prop="leave_go">
            <el-input v-model="leaveForm.leave_go"></el-input>
          </el-form-item>
          <el-form-item label="是否进入人才库" prop="isInto">
            <el-select
              v-model="leaveForm.isInto"
              placeholder="请选择"
              style="margin-right: 390px"
            >
              <el-option
                v-for="(item, index) in allIsIntoList"
                :key="index"
                :label="item"
                :value="item"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="离职日期">
            <el-date-picker
              v-model="leaveForm.leave_time"
              type="date"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              placeholder="选择日期"
              style="margin-right: 390px"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="备注">
            <el-input type="textarea" v-model="leaveForm.remark"></el-input>
          </el-form-item>
        </el-form>
        <!-- 底部 -->
        <span slot="footer" class="dialog-footer">
          <el-button @click="leaveDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="saveLeaveWorkSection"
            >确 定</el-button
          >
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import About from "@/components/common/About";
import SideMenu from "./SideMenu";
import LeaveSearchBar from "./LeaveSearchBar";
export default {
  name: "AppIndex",
  components: { About, SideMenu, LeaveSearchBar },
  data() {
    return {
      //封装的查询参数
      queryInfo: {
        query: "",
        pagenum: 1,
        pagesize: 5,
      },

      list: [],
      listLeaveWork: [],
      total: 0,
      totalLeaveWork: 0,
      allTypeList: ["辞职", "退休", "休假"],
      allIsIntoList: ["是", "否"],

      ids: [],
      delarr: [],
      value: "",

      leaveDialogVisible: false,

      leaveForm: {
        staff_id: "",
        staff_name: "",
        section_name: "",
        position_name: "",
        leave_type: "",
        remark: "",
        leave_go: "",
        isInto: "",
        leave_time: "",
      },
      leaveFormRules: {
        leave_type: [
          { required: true, message: "请选择离职类型", trigger: "blur" },
          {
            min: 1,
            max: 20,
            message: "长度在 1 到 10 个汉字",
            trigger: "blur",
          },
        ],
        isInto: [
          { required: true, message: "请选择是否进入人才库", trigger: "blur" },
          {
            min: 1,
            max: 20,
            message: "长度在 1 到 10 个汉字",
            trigger: "blur",
          },
        ],
        leave_time: [
          { required: true, message: "请选择离职时间", trigger: "blur" },
          {
            min: 1,
            max: 20,
            message: "长度在 1 到 20 个字符",
            trigger: "blur",
          },
        ],
      },
    };
  },
  //生命函数
  created() {
    this.getList();
    this.getListLeaveWork();
  },
  //方法
  methods: {
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
</style>
