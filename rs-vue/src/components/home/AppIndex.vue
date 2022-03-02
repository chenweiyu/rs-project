<template>
  <div style="margin-top: 40px">
    <side-menu id="side-menu"></side-menu>
    <div class="home" style="margin-left: 350px">
      <div id="header-div">
        <div style="height: 150px"></div>
        <carousel></carousel>
        <quick-nav
          style="float: right; margin-top: -450px; margin-right: 480px"
        ></quick-nav>
      </div>
      <!-- <update-card id="update-card"></update-card>
        <slogan id="slogan"></slogan> -->
      <div class="baobiao">
        <div style="width: 100%; height: 40px">
          <h3 style="float: left">新聘员工报表</h3>
          <br /><br /><br />
          <div>
            <el-form :model="queryInfo" label-width="70px">
              <el-col :span="8" style="margin-left: 140px">
                <el-form-item label="开始日期">
                  <el-date-picker
                    v-model="queryInfo.OntrialBeginTime"
                    type="date"
                    format="yyyy-MM-dd"
                    value-format="yyyy-MM-dd"
                    placeholder="选择日期"
                  >
                  </el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="结束日期">
                  <el-date-picker
                    v-model="queryInfo.OntrialEndTime"
                    type="date"
                    format="yyyy-MM-dd"
                    value-format="yyyy-MM-dd"
                    placeholder="选择日期"
                  >
                  </el-date-picker>
                </el-form-item>
              </el-col>
              <el-button type="primary" @click="getNewStaffList"
                >查 询</el-button
              >
            </el-form>
          </div>
        </div>
        <el-table
          ref="multipleTable"
          :data="list"
          tooltip-effect="dark"
          style="width: 100%"
          @selection-change="handleSelectionChange"
          stripe
          border
        >
          <el-table-column type="selection" width="55"> </el-table-column>
          <el-table-column
            type="index"
            label="序号"
            width="60"
          ></el-table-column>
          <el-table-column prop="staff_name" label="姓名"> </el-table-column>
          <el-table-column prop="sex" label="性别"> </el-table-column>
          <el-table-column prop="section_name" label="部门名称">
          </el-table-column>
          <el-table-column prop="position_name" label="职位名称">
          </el-table-column>
          <el-table-column prop="into_time" label="入职时间"> </el-table-column>
          <el-table-column prop="highest_education" label="学历">
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

        <div style="width: 100%; height: 40px">
          <h3 style="float: left">离职员工报表</h3>
          <br /><br /><br />
          <div>
            <el-form :model="queryLeaveInfo" label-width="70px">
              <el-col :span="8" style="margin-left: 140px">
                <el-form-item label="开始日期">
                  <el-date-picker
                    v-model="queryLeaveInfo.OntrialBeginTime"
                    type="date"
                    format="yyyy-MM-dd"
                    value-format="yyyy-MM-dd"
                    placeholder="选择日期"
                  >
                  </el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="结束日期">
                  <el-date-picker
                    v-model="queryLeaveInfo.OntrialEndTime"
                    type="date"
                    format="yyyy-MM-dd"
                    value-format="yyyy-MM-dd"
                    placeholder="选择日期"
                  >
                  </el-date-picker>
                </el-form-item>
              </el-col>
              <el-button type="primary" @click="getLeaveStaffList"
                >查 询</el-button
              >
            </el-form>
          </div>
        </div>
        <el-table
          ref="multipleTable"
          :data="leavelist"
          tooltip-effect="dark"
          style="width: 100%"
          @selection-change="handleSelectionChange"
          stripe
          border
        >
          <el-table-column type="selection" width="55"> </el-table-column>
          <el-table-column
            type="index"
            label="序号"
            width="60"
          ></el-table-column>
          <el-table-column prop="staff_id" label="编号"> </el-table-column>
          <el-table-column prop="staff_name" label="姓名"> </el-table-column>

          <el-table-column prop="section_name" label="部门名称">
          </el-table-column>
          <el-table-column prop="position_name" label="职位名称">
          </el-table-column>
          <el-table-column prop="leave_time" label="离职时间">
          </el-table-column>
          <el-table-column prop="remark" label="离职原因"> </el-table-column>
        </el-table>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="queryLeaveInfo.pagenum"
          :page-size="queryLeaveInfo.pagesize"
          :page-sizes="[5, 10, 20, 40]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="leavetotal"
          style="margin-top:20px;margin-buttom-20px"
        >
        </el-pagination>

        <div style="width: 100%; height: 40px">
          <h3 style="float: left">部门调动员工报表</h3>
          <br /><br /><br />
          <div>
            <el-form :model="querytransferSection" label-width="70px">
              <el-col :span="8" style="margin-left: 100px">
                <el-form-item label="开始日期">
                  <el-date-picker
                    v-model="querytransferSection.OntrialBeginTime"
                    type="date"
                    format="yyyy-MM-dd"
                    value-format="yyyy-MM-dd"
                    placeholder="选择日期"
                  >
                  </el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="结束日期">
                  <el-date-picker
                    v-model="querytransferSection.OntrialEndTime"
                    type="date"
                    format="yyyy-MM-dd"
                    value-format="yyyy-MM-dd"
                    placeholder="选择日期"
                  >
                  </el-date-picker>
                </el-form-item>
              </el-col>
              <el-button type="primary" @click="getTransferSectionList"
                >查 询</el-button
              >
            </el-form>
          </div>
        </div>
        <el-table
          ref="multipleTable"
          :data="transferSectionlist"
          tooltip-effect="dark"
          style="width: 100%"
          @selection-change="handleSelectionChange"
          border
          stripe
        >
          <el-table-column type="selection" width="55"> </el-table-column>
          <el-table-column
            type="index"
            label="序号"
            width="60"
          ></el-table-column>
          <el-table-column prop="staff_name" label="姓名"> </el-table-column>
          <el-table-column prop="sex" label="性别"> </el-table-column>
          <el-table-column prop="section_name" label="原部门名称">
          </el-table-column>
          <el-table-column prop="section_after" label="新部门名称">
          </el-table-column>
          <el-table-column prop="begin_time" label="调动时间">
          </el-table-column>
          <el-table-column prop="reason" label="调动原因"> </el-table-column>
        </el-table>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="querytransferSection.pagenum"
          :page-size="querytransferSection.pagesize"
          :page-sizes="[5, 10, 20, 40]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="transferSectiontotal"
          style="margin-top:20px;margin-buttom-20px"
        >
        </el-pagination>

        <div style="width: 100%; height: 40px">
          <h3 style="float: left">人事月報</h3>
          <br /><br /><br />
          <div>
            <el-form :model="queryrsMonth" label-width="70px">
              <el-col :span="8" style="margin-left: 180px">
                <el-form-item label="开始日期">
                  <el-date-picker
                    v-model="queryrsMonth.OntrialBeginTime"
                    type="date"
                    format="yyyy-MM-dd"
                    value-format="yyyy-MM-dd"
                    placeholder="选择日期"
                  >
                  </el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="结束日期">
                  <el-date-picker
                    v-model="queryrsMonth.OntrialEndTime"
                    type="date"
                    format="yyyy-MM-dd"
                    value-format="yyyy-MM-dd"
                    placeholder="选择日期"
                  >
                  </el-date-picker>
                </el-form-item>
              </el-col>
              <el-button type="primary" @click="getRsMonthList"
                >查 询</el-button
              >
            </el-form>
          </div>
        </div>
        <br />
        <h5 style="float: left;margin-left:100px">公司学历统计图</h5>
        <br />
        <br />
        <br />
        <div id="t1" ref="t1ref" style="width: 100%; height: 400px"></div>
        <el-table
          ref="multipleTable"
          :data="rsMonthlist"
          tooltip-effect="dark"
          style="width: 100%"
          @selection-change="handleSelectionChange"
          border
          stripe
        >
          <el-table-column prop="section_name" label="部门名称" align="center">
          </el-table-column>
          <el-table-column label="人数统计" align="center">
            <el-table-column prop="ycrs" label="月初人数" :formatter="if_null">
            </el-table-column>
            <el-table-column prop="ymrs" label="月末人数" :formatter="if_null1">
            </el-table-column>
            <el-table-column
              prop="xrz"
              label="本月新入职"
              :formatter="if_null2"
            >
            </el-table-column>
            <el-table-column prop="lz" label="本月离职" :formatter="if_null3">
            </el-table-column>
            <el-table-column prop="dr" label="本月调入" :formatter="if_null4">
            </el-table-column>
            <el-table-column prop="dc" label="本月调出" :formatter="if_null5">
            </el-table-column>
          </el-table-column>
          <el-table-column label="学历统计" align="center">
            <el-table-column prop="yjs" label="研究生"> </el-table-column>
            <el-table-column prop="bk" label="本科"> </el-table-column>
            <el-table-column prop="dz" label="大专"> </el-table-column>
            <el-table-column prop="gz" label="高中及以下"> </el-table-column>
          </el-table-column>
        </el-table>
      </div>
      <about id="about"></about>
    </div>
  </div>
</template>

<script>
import Carousel from "./Carousel";
import QuickNav from "./QuickNav";
import Slogan from "./Slogan";
import About from "@/components/common/About";
import UpdateCard from "./UpdateCard";
import SideMenu from "./SideMenu";
export default {
  name: "AppIndex",
  components: { Carousel, QuickNav, Slogan, About, UpdateCard, SideMenu },
  data() {
    return {
      total: 0,
      list: [],
      queryInfo: {
        OntrialBeginTime: "",
        OntrialEndTime: "",
        pagenum: 1,
        pagesize: 10,
      },

      leavetotal: 0,
      leavelist: [],
      queryLeaveInfo: {
        OntrialBeginTime: "",
        OntrialEndTime: "",
        pagenum: 1,
        pagesize: 10,
      },

      transferSectiontotal: 0,
      transferSectionlist: [],
      querytransferSection: {
        OntrialBeginTime: "",
        OntrialEndTime: "",
        pagenum: 1,
        pagesize: 10,
      },

      rsMonthlist: [],
      queryrsMonth: {
        OntrialBeginTime: "",
        OntrialEndTime: "",
      },
    };
  },
  created() {
    this.getNewStaffList();
    this.getLeaveStaffList();
    this.getTransferSectionList();
    this.getRsMonthList();
  },
  mounted: function () {
    this.drawT1();
  },
  methods: {
    async drawT1() {
      //获取数据
      const { data: res } = await this.$http.get("/staffInfo/xltj");
      console.log(res);

      if (res.code !== 200) {
        return this.$message.error("查询信息失败！" + res.msg);
      }
      // let month = res.data.map((x) => {
      //   return x.month;
      // });
      // let num = res.data.map((x) => {
      //   return x.num;
      // });
      // console.log(month, num);
      // if (myChart1 != null && myChart1 != "" && myChart1 != undefined) {
      //   myChart1.dispose(); //销毁
      // }
      // 基于准备好的dom，初始化echarts实例    ,this.$echarts
      var myChart1 = this.$echarts.init(document.getElementById("t1"));

      // 指定图表的配置项和数据,对象
      // var option = {
      //   title: {
      //     text: "每月博客发表数量",
      //   },
      //   tooltip: {},
      //   legend: {
      //     data: ["数量"],
      //   },
      //   xAxis: {
      //     data: month,
      //   },
      //   yAxis: {},
      //   series: [
      //     {
      //       name: "数量",
      //       type: "line",
      //       data: num,
      //     },
      //   ],
      // };
      var option = {
        series: [
          {
            name: "访问来源",
            type: "pie",
            radius: "55%",
            roseType: "angle",
            data: [
            ]
          },
        ],
      };
      option.series[0].data = res.data
      // console.log("---msg---", res.data);
      console.log("---msg1---", option);
      myChart1.setOption(option);
    },

    if_null: function (row, column) {
      return row.ycrs ? row.ycrs : 0;
    },
    if_null1: function (row, column) {
      return row.ymrs ? row.ymrs : 0;
    },
    if_null2: function (row, column) {
      return row.xrz ? row.xrz : 0;
    },
    if_null3: function (row, column) {
      return row.lz ? row.lz : 0;
    },
    if_null4: function (row, column) {
      return row.dr ? row.dr : 0;
    },
    if_null5: function (row, column) {
      return row.dc ? row.dc : 0;
    },
    async getNewStaffList() {
      const { data: res } = await this.$http.post(
        "/staffInfo/newList",
        this.$qs.stringify(this.queryInfo)
      );
      if (res.code !== 200) {
        return this.$message.error("获取列表失败！,错误信息为：" + res.msg);
      }
      this.list = res.data.records;
      this.total = res.data.total;
      console.log("list", this.list);
      console.log("total", this.total);
    },
    async getLeaveStaffList() {
      console.log("this", this.queryLeaveInfo);
      const { data: res } = await this.$http.post(
        "/leaveWork/listLeaveWork",
        this.$qs.stringify(this.queryLeaveInfo)
      );

      console.log(res);

      if (res.code !== 200) {
        return this.$message.error("获取列表失败！,错误信息为：" + res.msg);
      }

      this.leavelist = res.data.records;

      this.leavetotal = res.data.total;
    },
    async getTransferSectionList() {
      const { data: res } = await this.$http.post(
        "/transferSection/querylist",
        this.$qs.stringify(this.querytransferSection)
      );

      console.log(res);

      if (res.code !== 200) {
        return this.$message.error("获取列表失败！,错误信息为：" + res.msg);
      }
      this.transferSectionlist = res.data.records;
      this.transferSectiontotal = res.data.total;
    },

    async getRsMonthList() {
      const { data: res } = await this.$http.post(
        "/section/querylist",
        this.$qs.stringify(this.queryrsMonth)
      );

      console.log(res);

      if (res.code !== 200) {
        return this.$message.error("获取列表失败！,错误信息为：" + res.msg);
      }
      //this.rsMonthlist = res.data;
      console.log(res.data);

      let k1 = res.data.k1;
      let k = res.data.k;
      let k2 = res.data.k2;
      let k3 = res.data.k3;
      let k4 = res.data.k4;
      let k5 = res.data.k5;
      let k6 = res.data.k6;

      for (let j = 0; j < k.length; j++) {
        if (k1[j] == null) {
          k1[j] = k[j];
        }
        if (k2[j] == null) {
          k2[j] = k[j];
        }
        if (k3[j] == null) {
          k3[j] = k[j];
        }
        if (k4[j] == null) {
          k4[j] = k[j];
        }
        if (k5[j] == null) {
          k5[j] = k[j];
        }
        if (k6[j] == null) {
          k6[j] = k[j];
        }
        Object.assign(k1[j], k3[j], k[j], k2[j], k4[j], k5[j], k6);
      }

      this.rsMonthlist = k1;
      this.rsMonthtotal = res.data.total;
    },
    handleSelectionChange(val) {
      this.ids = val;
    },
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
.baobiao {
  margin-top: 10px;
  margin-bottom: 20px;
}
</style>
