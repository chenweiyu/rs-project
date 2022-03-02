<template>
  <div style="margin-top: 40px">
    <el-breadcrumb separator="/" style="margin-bottom: 40px">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>员工调配管理</el-breadcrumb-item>
      <el-breadcrumb-item>员工岗位调动</el-breadcrumb-item>
    </el-breadcrumb>
    <staff-side-menu id="side-menu"></staff-side-menu>
    <div class="home" style="margin-left: -220px">
      <!-- <search-bar
        @onSearch="searchList"
        ref="searchBar"
        style="margin-left: 100px"
      ></search-bar> -->

      <div style="margin-left: 200px; margin-top: 20px; margin-bottom:40px">
        <el-input
          @keyup.enter.native="getList"
          placeholder="请输入内容进行搜索..."
          prefix-icon="el-icon-search"
          size="small"
          style="width: 400px; margin-right: 10px"
          v-model="queryInfo.query"
        >
          <template #append>
            <el-button icon="el-icon-search" @click="getList"></el-button>
          </template>
        </el-input>
      </div>

      <h4 style="float: left; margin-left: 250px; margin-top: -20px">
        员工在职列表
      </h4>
      <div style="margin-left: 250px">
        <el-table
          ref="multipleTable"
          :data="list"
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
          <el-table-column prop="position_name" label="岗位名称">
          </el-table-column>
          <el-table-column prop="position_num" label="岗位编制">
          </el-table-column>
          <el-table-column label="操作" width="180px">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="primary"
                icon="el-icon-edit"
                @click="staffInfo(scope.row.staff_id)"
                >调动
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

        <hr style="margin-top: 20px; margin-buttom: 20px" />

        <!-- <search-bar
          @onSearch="searchList"
          ref="searchBar"
          style="margin-left: 100px; margin-top: 20px"
        ></search-bar> -->
        <div style="margin-top: 40px; margin-bottom:40px">
        <el-input
          @keyup.enter.native="getList"
          placeholder="请输入内容进行搜索..."
          prefix-icon="el-icon-search"
          size="small"
          style="width: 400px; margin-right: 10px"
          v-model="queryInfo.query"
        >
          <template #append>
            <el-button icon="el-icon-search" @click="getList"></el-button>
          </template>
        </el-input>
      </div>
        <h4 style="float: left; margin-top: -20px">员工岗位调动后列表</h4>
        <div>

          <el-table
            ref="multipleTable"
            :data="listTransfer"
            tooltip-effect="dark"
            style="width: 100%"
          >
            <el-table-column
              type="index"
              label="序号"
              width="60"
            ></el-table-column>
            <el-table-column prop="staff_id" label="员工编号">
            </el-table-column>
            <el-table-column prop="staff_name" label="姓名"> </el-table-column>
            <el-table-column prop="position_name" label="调转前的岗位">
            </el-table-column>
            <el-table-column prop="position_after" label="调转后的岗位">
            </el-table-column>
            <el-table-column prop="transfer_type" label="调转类型">
            </el-table-column>
            <el-table-column prop="reason" label="原因"> </el-table-column>
            <el-table-column prop="remark" label="备注"> </el-table-column>
            <el-table-column prop="transfer_time" label="调转时间">
            </el-table-column>
          </el-table>
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="queryInfo.pagenum"
            :page-size="queryInfo.pagesize"
            :page-sizes="[5, 10, 20, 40]"
            layout="total, sizes, prev, pager, next, jumper"
            :total="totalTransfer"
            style="margin-top:20px;margin-buttom-20px"
          >
          </el-pagination>
        </div>
        <el-dialog
          title="调动员工的岗位"
          :visible.sync="transferDialogVisible"
          width="50%"
          @close="editDialogClosed"
        >
          <el-form
            :model="transferForm"
            ref="transferFormRef"
            :rules="transferFormRules"
            label-width="140px"
          >
            <el-form-item label="员工编号" prop="staff_id">
              <el-input
                v-model="transferForm.staff_id"
                :disabled="true"
              ></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="staff_name">
              <el-input
                v-model="transferForm.staff_name"
                :disabled="true"
              ></el-input>
            </el-form-item>
            <el-row>
              <el-col :span="10">
                <el-form-item label="当前岗位" prop="position_name">
                  <el-input
                    v-model="transferForm.position_name"
                    :disabled="true"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="11" style="padding-left: 20px">
                <el-form-item label="请选择调转后的岗位" prop="positionName">
                  <el-select
                    v-model="transferForm.position_after"
                    placeholder="请选择岗位"
                  >
                    <el-option
                      v-for="item in allCheckList"
                      :key="item.id"
                      :label="item.positionName"
                      :value="item.positionName"
                    >
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>

                  <el-form-item label="调转类型" prop="transfer_type">
                <el-select
                  v-model="transferForm.transfer_type"
                  placeholder="请选择调转类型"
                >
                  <el-option
                    v-for="(item, index) in TypeList"
                    :key="index"
                    :label="item"
                    :value="item"
                  >
                  </el-option>
                </el-select>
              </el-form-item>


            <el-form-item label="调转原因" prop="reason">
              <el-input
                type="textarea"
                v-model="transferForm.reason"
              ></el-input>

            </el-form-item>


            <el-form-item label="备注">
              <el-input
                type="textarea"
                v-model="transferForm.remark"
              ></el-input>
            </el-form-item>
            <el-row>
              <el-col :span="10">
                <el-form-item label="调转时间">
                  <el-date-picker
                    v-model="transferForm.transfer_time"
                    type="date"
                    format="yyyy-MM-dd"
                    value-format="yyyy-MM-dd"
                    placeholder="选择日期"
                  >
                  </el-date-picker>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
          <!-- 底部 -->
          <span slot="footer" class="dialog-footer">
            <el-button @click="transferDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="saveTransferPosition"
              >保 存</el-button
            >
          </span>
        </el-dialog>
      </div>
    </div>
  </div>
</template>

<script>
import SearchBar from "./SearchBar";
import StaffSideMenu from "./StaffSideMenu";
export default {
  name: "StaffInfo",
  components: { SearchBar, StaffSideMenu },
  data() {
    return {
      //封装的查询参数
      queryInfo: {
        query: "",
        pagenum: 1,
        pagesize: 5,
      },

      list: [],
      listTransfer: [],
      total: 0,
      totalTransfer: 0,
      allCheckList: [],

      ids: [],
      delarr: [],
      value: "",

      transferDialogVisible: false,

      TypeList: ["升职", "降职"],
      transferForm: {
        staff_id: "",
        position_name: "",
        transfer_type: "",
        position_after: "",
        reason: "",
        remark: "",
        transfer_time: "",
      },
      transferFormRules: {
        reason: [
          { required: true, message: "请输入调转原因", trigger: "blur" },
          {
            min: 1,
            max: 200,
            message: "长度在 1 到 100 个汉字",
            trigger: "blur",
          },
        ],
        position_after: [
          { required: true, message: "请输入调转后的岗位", trigger: "blur" },
          {
            min: 1,
            max: 200,
            message: "长度在 1 到 100 个汉字",
            trigger: "blur",
          },
        ],
        transfer_type: [
          { required: true, message: "请输入调转类型", trigger: "blur" },
          {
            min: 1,
            max: 200,
            message: "长度在 1 到 100 个汉字",
            trigger: "blur",
          },
        ],
        transfer_time: [
          { required: true, message: "请输入调转时间", trigger: "blur" },
          {
            min: 1,
            max: 200,
            message: "长度在 1 到 100 个汉字",
            trigger: "blur",
          },
        ],
      },
    };
  },
   //生命函数
  created() {
    this.getList();
    this.getListTransfer();
  },
  //方法
  methods: {
    async getAllPosition() {
      const { data: res } = await this.$http.post("/position/list");
      console.log(res);
      if (res.code !== 200) {
        return this.$message.error("获取列表失败！");
      }
      this.allCheckList = res.data;
    },
    //查询  async  await
    async getList() {

      const { data: res } = await this.$http.post(
        "/staffInfo/list",
        this.$qs.stringify(this.queryInfo)
      );

      console.log("mas---", res.data);

      if (res.code !== 200) {
        return this.$message.error("获取列表失败！,错误信息为：" + res.msg);
      }

      this.list = res.data.records;

      this.total = res.data.total;
    },
    async getListTransfer() {
      const { data: res } = await this.$http.post(
        "/transferPosition/list",
        this.$qs.stringify(this.queryInfo)
      );

      console.log(res);

      if (res.code !== 200) {
        return this.$message.error("获取列表失败！,错误信息为：" + res.msg);
      }

      this.listTransfer = res.data.records;

      this.totalTransfer = res.data.total;
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
      this.getAllPosition();
      console.log(staff_id);
      const { data: res } = await this.$http.get("/staffInfo/info/" + staff_id);
      if (res.code !== 200) {
        return this.$message.error("查询信息失败！");
      }
      console.log(res.data);
      this.transferForm.staff_id = res.data.staffId; // 对象复制
      this.transferForm.staff_name = res.data.staffName;
      this.transferForm.position_name = res.data.positionName;
      this.transferDialogVisible = true;
    },
    saveTransferPosition() {
      console.log("msg---", this.transferForm)
      this.$refs.transferFormRef.validate(async (valid) => {
        if (!valid) return;

        const { data: res } = await this.$http.post(
          "/transferPosition/save",
          this.transferForm
        );
        if (res.code !== 200) {
          return this.$message.error("更新信息失败！");
        }

        // 提示修改成功
        this.$message.success("更新信息成功！");
        // 关闭对话框
        this.transferDialogVisible = false;
        // 刷新数据列表
        this.getList();
        this.getListTransfer();
      });
    },
    editDialogClosed() {
      this.$refs.transferFormRef.resetFields();
    },
  },
};
</script>

<style scoped>
.home {
  width: 1250px;
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
el-input {
  width: 80px;
}
</style>
