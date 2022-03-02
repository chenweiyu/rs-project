<template>
  <div style="margin-top: 40px">
    <staff-side-menu id="side-menu"></staff-side-menu>
    <div class="home" style="margin-left: -220px">
      <!-- <search-bar
        @onSearch="searchList"
        ref="searchBar"
        style="margin-left: 100px"
        @onAddShow="showAddDialog"
        @onDeleteMore="removeByMoreId"
      ></search-bar> -->
      <div style="margin-left: 200px; margin-top: 20px">
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
          <el-table-column prop="staff_id" label="员工编号"> </el-table-column>
          <el-table-column prop="staff_name" label="姓名"> </el-table-column>
          <el-table-column prop="sex" label="性别"> </el-table-column>
          <el-table-column prop="position_name" label="职位"> </el-table-column>
          <el-table-column prop="staff_phone" label="电话"> </el-table-column>
          <el-table-column prop="section_name" label="部门"> </el-table-column>
          <el-table-column label="操作" width="180px">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="primary"
                icon="el-icon-edit"
                @click="showEditDialog(scope.row.staff_id)"
                >编辑
              </el-button>
              <el-button
                size="mini"
                type="danger"
                icon="el-icon-delete"
                @click="removeById(scope.row.staff_id)"
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
          <el-form-item label="员工编号" prop="staff_id">
            <el-input v-model="addForm.staff_id"></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="staff_name">
            <el-input v-model="addForm.staff_name"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-input v-model="addForm.sex"></el-input>
          </el-form-item>
          <el-form-item label="职位" prop="position_name">
            <el-input v-model="addForm.position_name"></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="staff_phone">
            <el-input v-model="addForm.staff_phone"></el-input>
          </el-form-item>
          <el-form-item label="部门" prop="section_name">
            <el-input v-model="addForm.section_name"></el-input>
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
          <el-form-item label="员工编号" prop="staff_id">
            <el-input v-model="editForm.staff_id"></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="staff_name">
            <el-input v-model="editForm.staff_name"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-input v-model="editForm.sex"></el-input>
          </el-form-item>
          <el-form-item label="职位" prop="position_name">
            <el-input v-model="editForm.position_name"></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="staff_phone">
            <el-input v-model="editForm.staff_phone"></el-input>
          </el-form-item>
          <el-form-item label="部门" prop="section_name">
            <el-input v-model="editForm.section_name"></el-input>
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
        pagesize: 10,
      },

      list: [],
      total: 0,
      addDialogVisible: false,

      ids: [],
      delarr: [],

      addForm: {
        staff_id: "",
        staff_name: "",
        sex: "",
        position_name: "",
        staff_phone: "",
        section_name: "",
      },

      addFormRules: {
        staff_id: [
          { required: true, message: "请输入员工编号", trigger: "blur" },
        ],
        staff_name: [
          { required: true, message: "请输入员工姓名", trigger: "blur" },
          {
            min: 1,
            max: 20,
            message: "长度在 3 到 20 个字符",
            trigger: "blur",
          },
        ],
        sex: [
          { required: true, message: "请输入员工性别", trigger: "blur" },
          {
            min: 1,
            max: 20,
            message: "长度在 1 到 20 个字符",
            trigger: "blur",
          },
        ],
        position_name: [
          { required: true, message: "请输入员工职位", trigger: "blur" },
          {
            min: 1,
            max: 50,
            message: "长度在 1 到 50 个字符",
            trigger: "blur",
          },
        ],
        staff_phone: [
          { required: true, message: "请输入员工手机号码", trigger: "blur" },
          {
            min: 1,
            max: 11,
            message: "长度在 1 到 11 个字符",
            trigger: "blur",
          },
        ],
        section_name: [
          { required: true, message: "请输入员工的部门", trigger: "blur" },
          {
            min: 1,
            max: 20,
            message: "长度在 1 到 20 个字符",
            trigger: "blur",
          },
        ],
      },

      editDialogVisible: false,

      editForm: {
        staff_id: "",
        staff_name: "",
        sex: "",
        position_name: "",
        staff_phone: "",
        section_name: "",
      },
      editFormRules: {
        staff_id: [
          { required: true, message: "请输入员工编号", trigger: "blur" },
        ],
        staff_name: [
          { required: true, message: "请输入员工姓名", trigger: "blur" },
          {
            min: 1,
            max: 20,
            message: "长度在 3 到 20 个字符",
            trigger: "blur",
          },
        ],
        sex: [
          { required: true, message: "请输入员工性别", trigger: "blur" },
          {
            min: 1,
            max: 20,
            message: "长度在 1 到 20 个字符",
            trigger: "blur",
          },
        ],
        position_name: [
          { required: true, message: "请输入员工职位", trigger: "blur" },
          {
            min: 1,
            max: 50,
            message: "长度在 1 到 50 个字符",
            trigger: "blur",
          },
        ],
        staff_phone: [
          { required: true, message: "请输入员工手机号码", trigger: "blur" },
          {
            min: 1,
            max: 11,
            message: "长度在 1 到 11 个字符",
            trigger: "blur",
          },
        ],
        section_name: [
          { required: true, message: "请输入员工的部门", trigger: "blur" },
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
          "/staffInfo/save",
          this.$qs.stringify(this.addForm)
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
      const { data: res } = await this.$http.get("/staffInfo/info/" + staff_id);
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
          "/staffInfo/update",
          this.$qs.stringify(this.editForm)
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
    removeById(staff_id) {
      console.log(staff_id);
      this.$confirm("此操作将永久删除数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(async () => {
          // 删除逻辑
          const { data: res } = await this.$http.post("/staffInfo/delete", [
            staff_id,
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
        this.delarr.push(this.ids[i].staff_id);
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
            "/staffInfo/delete",
            this.delarr
          );

          if (res.code !== 200) {
            return this.$message.error("删除信息失败！");
          }

          this.$message.success("删除信息成功！");

          this.getList();
          this.ids.length = 0;
          this.delarr.length = 0;
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
