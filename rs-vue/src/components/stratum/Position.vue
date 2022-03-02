<template>
  <div style="margin-top: 40px">
    <el-breadcrumb separator="/" style=" margin-bottom:40px">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>公司等级管理</el-breadcrumb-item>
      <el-breadcrumb-item>岗位中心</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="home" style="margin-left: -220px">
      <search-bar
        @onSearch="searchList"
        ref="searchBar"
        style="margin-left: 100px"
        @onAddShow="showAddDialog"
        @onDeleteMore="removeByMoreId"
      ></search-bar>

      <div style="margin-left: 250px">
        <el-table
          ref="multipleTable"
          :data="list"
          tooltip-effect="dark"
          style="width: 100%"
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" width="55"> </el-table-column>
          <el-table-column
            type="index"
            label="序号"
            width="60"
          ></el-table-column>
          <el-table-column prop="positionNum" label="职位编号"> </el-table-column>
          <el-table-column prop="positionName" label="职位名称"> </el-table-column>
          <el-table-column prop="positionType" label="类型"> </el-table-column>
          <el-table-column prop="positionDescribe" label="职位描述"> </el-table-column>
          <el-table-column prop="updateTime" label="创立时间"> </el-table-column>
          <el-table-column label="操作" width="180px">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="primary"
                icon="el-icon-edit"
                @click="showEditDialog(scope.row.id)"
                >编辑
              </el-button>
              <el-button
                size="mini"
                type="danger"
                icon="el-icon-delete"
                @click="removeById(scope.row.id)"
                >删除
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
      <el-dialog
        title="添加"
        :visible.sync="addDialogVisible"
        width="50%"
        @close="addDialogClosed"
      >
        <el-form
          :model="addForm"
          ref="addFormRef"
          :rules="addFormRules"
          label-width="100px"
        >
          <el-form-item label="职位编号" prop="positionNum">
            <el-input v-model="addForm.positionNum"></el-input>
          </el-form-item>
          <el-form-item label="职位名称" prop="positionName">
            <el-input v-model="addForm.positionName"></el-input>
          </el-form-item>
          <el-form-item label="岗位" prop="positionType">
            <el-input v-model="addForm.positionType"></el-input>
          </el-form-item>
          <el-form-item label="职位描述" prop="positionDescribe">
            <el-input v-model="addForm.positionDescribe"></el-input>
          </el-form-item>
        </el-form>
        <!-- 底部 -->
        <span slot="footer" class="dialog-footer">
          <el-button @click="addDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="add">确 定</el-button>
        </span>
      </el-dialog>

      <!-- 编辑框 -->
      <el-dialog
        title="编辑角色"
        :visible.sync="editDialogVisible"
        width="50%"
        @close="editDialogClosed"
      >
        <el-form
          :model="editForm"
          ref="editFormRef"
          :rules="editFormRules"
          label-width="100px"
        >
          <el-form-item label="职位编号" prop="positionNum">
            <el-input v-model="editForm.positionNum"></el-input>
          </el-form-item>
          <el-form-item label="职位名称" prop="positionName">
            <el-input v-model="editForm.positionName"></el-input>
          </el-form-item>
          <el-form-item label="岗位" prop="positionType">
            <el-input v-model="editForm.positionType"></el-input>
          </el-form-item>
          <el-form-item label="职位描述" prop="positionDescribe">
            <el-input v-model="editForm.positionDescribe"></el-input>
          </el-form-item>
        </el-form>
        <!-- 底部 -->
        <span slot="footer" class="dialog-footer">
          <el-button @click="editDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="update">保 存</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import SearchBar from "./SearchBar";
export default {
  name: "StaffInfo",
  components: { SearchBar },
  data() {
    return {
      //封装的查询参数
      queryInfo: {
        query: "",
        pagenum: 1,
        pagesize: 10,
      },

      list: [],
      total: 0,
      addDialogVisible: false,

      ids: [],
      delarr: [],

      addForm: {
        positionNum: "",
        positionName: "",
        positionType: "",
        positionDescribe: ""
      },

      addFormRules: {
        positionNum: [
          { required: true, message: "请输入职位编号", trigger: "blur" },
          {
            min: 1,
            max: 20,
            message: "长度在 1 到 20 个数字",
            trigger: "blur",
          },
        ],
        positionName: [
          { required: true, message: "请输入职位名称", trigger: "blur" },
          {
            min: 1,
            max: 25,
            message: "长度在 1 到 25 个汉字",
            trigger: "blur",
          },
        ],
        positionType: [
          { required: true, message: "请输入该岗位类型", trigger: "blur" },
          {
            min: 1,
            max:25,
            message: "长度在 1 到 25 个汉字",
            trigger: "blur",
          },
        ],
        positionDescribe: [
          { required: true, message: "请输入职位的描述", trigger: "blur" },
          {
            min: 1,
            max: 200,
            message: "长度在 1 到 100 个汉字",
            trigger: "blur",
          },
        ],
      },

      editDialogVisible: false,

      editForm: {
        positionNum: "",
        positionName: "",
        positionType: "",
        positionDescribe: ""
      },
      editFormRules: {
        positionNum: [
          { required: true, message: "请输入职位编号", trigger: "blur" },
          {
            min: 1,
            max: 20,
            message: "长度在 1 到 20 个数字",
            trigger: "blur",
          },
        ],
        positionName: [
          { required: true, message: "请输入职位名称", trigger: "blur" },
          {
            min: 1,
            max: 25,
            message: "长度在 1 到 25 个汉字",
            trigger: "blur",
          },
        ],
        positionType: [
          { required: true, message: "请输入该岗位类型", trigger: "blur" },
          {
            min: 1,
            max:25,
            message: "长度在 1 到 25 个汉字",
            trigger: "blur",
          },
        ],
        positionDescribe: [
          { required: true, message: "请输入职位的描述", trigger: "blur" },
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
  },
  //方法
  methods: {
    //查询  async  await
    async getList() {
      console.log(this.queryInfo.query);
      // {k1:v1,k2:v2}  ---> ?k1=v1&k2=v2    query=&pagenum=1&pagesize=10
      console.log(this.queryInfo, this.$qs.stringify(this.queryInfo));

      const { data: res } = await this.$http.post(
        "/position/list",
        this.$qs.stringify(this.queryInfo)
      );

      console.log(res);

      if (res.code !== 200) {
        return this.$message.error("获取列表失败！,错误信息为：" + res.msg);
      }

      this.list = res.data.records;

      this.total = res.data.total;
    },
    async searchList(){
      this.queryInfo.query = this.$refs.searchBar.keywords
      console.log(this.queryInfo.query);
      // {k1:v1,k2:v2}  ---> ?k1=v1&k2=v2    query=&pagenum=1&pagesize=10
      console.log(this.queryInfo, this.$qs.stringify(this.queryInfo));

      const { data: res } = await this.$http.post(
        "/position/list",
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

    //添加
    //弹框
    showAddDialog() {
      this.addDialogVisible = true;
    },

    add() {
      this.$refs.addFormRef.validate(async (valid) => {
        console.log(valid);

        if (!valid) return;

        console.log(this.addForm);

        const { data: res } = await this.$http.post(
          "/position/save",
          this.addForm
        );

        console.log(res);

        if (res.code !== 200) {
          return this.$message.error("添加信息失败！,错误信息为：" + res.msg);
        }

        this.$message.success("添加信息成功！");

        this.addDialogVisible = false;

        this.getList();
      });
    },

    // @close="addDialogClosed"
    addDialogClosed() {
      console.log(this.$refs); //可以获取所有带ref属性的元素
      this.$refs.addFormRef.resetFields(); //清空表单
    },

    //更新
    async showEditDialog(staff_id) {
      console.log(staff_id);
      const { data: res } = await this.$http.get("/position/info/" + staff_id);
      if (res.code !== 200) {
        return this.$message.error("查询信息失败！");
      }
      console.log(res.data);
      this.editForm = res.data; // 对象复制
      this.editDialogVisible = true;
    },
    update() {
      this.$refs.editFormRef.validate(async (valid) => {
        if (!valid) return;

        const { data: res } = await this.$http.post(
          "/position/update",
          this.editForm
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
      });
    },
    editDialogClosed() {
      this.$refs.editFormRef.resetFields();
    },
    handleSelectionChange(val) {
      this.ids = val;
    },
    removeById(id) {
      console.log(id);
      this.$confirm("此操作将永久删除数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(async () => {
          // 删除逻辑
          const { data: res } = await this.$http.post("/position/delete", [
            id,
          ]);

          if (res.code !== 200) {
            return this.$message.error("删除信息失败！");
          }

          this.$message.success("删除信息成功！");

          this.getList();
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    removeByMoreId() {
      const length = this.ids.length;
      for (let i = 0; i < length; i++) {
        this.delarr.push(this.ids[i].id);
      }
      console.log(this.delarr);
      this.$confirm("此操作将永久删除数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(async () => {
          // 删除逻辑
          const { data: res } = await this.$http.post(
            "/position/delete",
            this.delarr
          );

          if (res.code !== 200) {
            return this.$message.error("删除信息失败！");
          }

          this.$message.success("删除信息成功！");

          this.getList();
          this.ids.length = 0
          this.delarr.length = 0
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
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
