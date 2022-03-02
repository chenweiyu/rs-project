<template>
  <div style="margin-top: 40px">
    <el-breadcrumb separator="/" style="margin-bottom: 40px">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>员工调配管理</el-breadcrumb-item>
      <el-breadcrumb-item>员工试用期管理</el-breadcrumb-item>
    </el-breadcrumb>
    <staff-side-menu id="side-menu"></staff-side-menu>
    <div class="home" style="margin-left: -220px">
      <ontrial-search-bar
        @onSearch="searchList"
        ref="searchBar"
        style="margin-left: 100px"
        @onNormal="OnWork"
        @onDel="OnDelay"
        @onNoEmploy="NoEmployment"
      ></ontrial-search-bar>

      <h4 style="float:left; margin-left:250px">员工试用期管理</h4>
      <div style="margin-left: 250px">
        <el-table
          ref="multipleTable"
          :data="list"
          tooltip-effect="dark"
          style="width: 100%"
          @selection-change="handleSelectionChange"
          border
        >
          <el-table-column type="selection" width="55"> </el-table-column>
          <el-table-column
            type="index"
            label="序号"
            width="60"
          ></el-table-column>
          <el-table-column prop="staff_id" label="员工编号"> </el-table-column>
          <el-table-column prop="staff_name" label="姓名"> </el-table-column>
          <el-table-column prop="position_name" label="职位"> </el-table-column>
          <el-table-column prop="section_name" label="部门"> </el-table-column>
          <el-table-column label="状态">
            <template slot-scope="scope">
            <el-tag
              :key="index"
              style="margin: 5px"

            >
              {{scope.row.status}}
            </el-tag>
          </template>
          </el-table-column>
          <el-table-column prop="begin_time" label="试用期开始时间">
          </el-table-column>
          <el-table-column prop="end_time" label="试用期结束时间">
          </el-table-column>
          <!-- <el-table-column label="操作" width="180px">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="primary"
                icon="el-icon-edit"
                @click="OnWork(scope.row.staff_id)"
                >转正
              </el-button>
              <el-button
                size="mini"
                type="danger"
                icon="el-icon-delete"
                @click="OnDelay(scope.row.staff_id)"
                >延期
              </el-button>
              <el-button
                size="mini"
                type="primary"
                icon="el-icon-edit"
                @click="NoEmployment(scope.row.staff_id)"
                >不录用
              </el-button>
            </template>
          </el-table-column> -->
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
  </div>
</template>

<script>
import StaffSideMenu from "./StaffSideMenu";
import OntrialSearchBar from "./OntrialSearchBar";
export default {
  name: "Ontrial",
  components: { OntrialSearchBar, StaffSideMenu },
  data() {
    return {
      //封装的查询参数
      queryInfo: {
        query: "",
        pagenum: 1,
        pagesize: 10,
      },
      ids: [],
      staff_id: 0,
      list: [],
      total: 0,
      addDialogVisible: false,
    };
  },
  //生命函数
  created() {
    this.getList();
  },
  //方法
  methods: {
    //查询  async  await
    async getList() {
      console.log(this.queryInfo.query);
      // {k1:v1,k2:v2}  ---> ?k1=v1&k2=v2    query=&pagenum=1&pagesize=10
      console.log(this.queryInfo, this.$qs.stringify(this.queryInfo));

      const { data: res } = await this.$http.post(
        "/ontrial/list",
        this.$qs.stringify(this.queryInfo)
      );

      console.log(res);

      if (res.code !== 200) {
        return this.$message.error("获取列表失败！,错误信息为：" + res.msg);
      }

      this.list = res.data.records;

      this.total = res.data.total;
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
    handleSelectionChange(val) {
      this.ids = val;
    },
    async OnWork() {
      console.log(this.ids[0].staff_id)
      if (this.ids.length !== 1 || this.ids == null) {
        return this.$message.error("请选择一个进行操作！");
      }
      const { data: res } = await this.$http.post("/ontrial/onWork/" + this.ids[0].staff_id);
      if (res.code !== 200) {
        return this.$message.error("状态调整为正常操作失败！");
      }

      // 提示修改成功
      this.$message.success("状态调整为正常操作成功！");
      this.getList();
    },
    async OnDelay(staff_id) {
      if (this.ids.length !== 1 || this.ids == null) {
        return this.$message.error("请选择一个进行操作！");
      }
      const { data: res } = await this.$http.post("/ontrial/onDelay/" + this.ids[0].staff_id);
      if (res.code !== 200) {
        return this.$message.error("状态调整为延期操作失败！");
      }

      // 提示修改成功
      this.$message.success("状态调整为延期操作成功！");
      this.getList();
    },
    async NoEmployment(staff_id) {
      if (this.ids.length !== 1 || this.ids == null) {
        return this.$message.error("请选择一个进行操作！");
      }
      const { data: res } = await this.$http.post("/ontrial/noEmployment/" + this.ids[0].staff_id);
      if (res.code !== 200) {
        return this.$message.error("状态调整为不录用操作失败！");
      }

      // 提示修改成功
      this.$message.success("状态调整为不录用操作成功！");
      this.getList();
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
</style>
