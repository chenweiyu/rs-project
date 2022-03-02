<template>
  <div style="margin-top: 40px">
    <el-breadcrumb separator="/" style="margin-left: 340px">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>
    <side-menu id="side-menu"></side-menu>

    <div class="home">
      <div style="margin-bottom:20px; margin-left:220px">
        <el-input
          @keyup.enter.native="getList"
          placeholder="请输入内容进行搜索..."
          prefix-icon="el-icon-search"
          size="small"
          style="width: 400px; margin-right: 10px"
          v-model="queryInfo.name"
        >
          <template #append>
            <el-button icon="el-icon-search" @click="getList"></el-button>
          </template>
        </el-input>
        <el-button type="primary" @click="showAddDialog">添加</el-button>
        <el-button type="primary" @click="removeById">批量删除</el-button>
      </div>

      <h3 style="margin-left: 250px; float: left; margin-bottom: 20px">
        用户管理表
      </h3>
      <div style="margin-left: 250px">
        <el-table
          :data="list"
          border
          stripe
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" width="60"></el-table-column>
          <el-table-column
            type="index"
            label="序号"
            width="60"
          ></el-table-column>
          <el-table-column label="用户名" prop="name" width="120"></el-table-column>
          <el-table-column label="邮箱" prop="email" width="120"></el-table-column>
          <el-table-column label="生日" prop="birthday" width="120"></el-table-column>
          <el-table-column label="角色">
            <template slot-scope="scope">
              <el-tag
                type="warning"
                v-for="(item, index) in scope.row.myRoleList"
                :key="index"
                style="margin: 5px"
              >
                {{ item.roleName }}
              </el-tag>
            </template>
          </el-table-column>
          <!-- 自定义列 -->
          <el-table-column label="操作" width="200" >
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
                @click="removeById([scope.row.id])"
                >删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>

        <!-- 2.3 分页区域 -->
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="queryInfo.pagenum"
          :page-size="queryInfo.pagesize"
          :page-sizes="[5, 10, 20, 40]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
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
          <el-form-item label="部门编号" prop="sectionNum">
            <el-input v-model="addForm.sectionNum"></el-input>
          </el-form-item>
          <el-form-item label="部门名称" prop="section_name">
            <el-input v-model="addForm.section_name"></el-input>
          </el-form-item>
          <el-form-item label="部门位置" prop="sectionPlace">
            <el-input v-model="addForm.sectionPlace"></el-input>
          </el-form-item>
        </el-form>
        <!-- 底部 -->
        <span slot="footer" class="dialog-footer">
          <el-button @click="addDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="add">确 定</el-button>
        </span>
      </el-dialog>

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
          <el-form-item label="用户名" prop="name">
            <el-input v-model="addForm.name"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="addForm.password"></el-input>
          </el-form-item>
          <el-form-item label="生日" prop="birthday">
            <el-date-picker
              v-model="addForm.birthday"
              type="date"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              placeholder="选择日期"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="addForm.email"></el-input>
          </el-form-item>

          <el-form-item label="角色" prop="yesCheckList">
            <el-checkbox-group v-model="addForm.yesCheckList">
              <el-checkbox
                v-for="item in allCheckList"
                :label="item.id"
                :key="item.id"
                >{{ item.roleName }}</el-checkbox
              >
            </el-checkbox-group>
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
          <el-form-item label="用户名" prop="name">
            <el-input v-model="editForm.name"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input
              v-model="editForm.password"
              placeholder="不更新密码，请不要输入信息"
            ></el-input>
          </el-form-item>
          <el-form-item label="生日" prop="birthday">
            <el-date-picker
              v-model="editForm.birthday"
              type="date"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              placeholder="选择日期"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="editForm.email"></el-input>
          </el-form-item>

          <!--  v-model="editForm.yesCheckList" 双向绑定 -->
          <el-form-item label="角色" prop="yesCheckList">
            <el-checkbox-group v-model="editForm.yesCheckList">
              <el-checkbox
                v-for="item in allCheckList"
                :label="item.id"
                :key="item.id"
                >{{ item.roleName }}</el-checkbox
              >
            </el-checkbox-group>
          </el-form-item>
        </el-form>
        <!-- 底部 -->
        <span slot="footer" class="dialog-footer">
          <el-button @click="editDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="update">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import SearchBar from "./SearchBar";
import SideMenu from "./SideMenu";
export default {
  name: "User",
  components: { SearchBar, SideMenu },
  data() {
    return {
      file: "", //传往后台文件

      /// ////////////////////列表 el-table
      list: [],

      multipleSelection: [], //多选  id

      /// ////////////////////分页查询   list->page
      queryInfo: {
        name: "", //查询参数
        rid: "", //角色ID
        pagenum: 1, // 当前的页数
        pagesize: 10, // 当前每页显示多少条数据
      },
      total: 0,

      //角色选择
      allCheckList: [],
      /// ////////////////////添加框  form，验证
      addDialogVisible: false,
      addForm: {
        name: "",
        password: "",
        birthday: "",
        email: "",
        yesCheckList: [],
      },

      addFormRules: {
        name: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          {
            min: 1,
            max: 25,
            message: "长度在 1 到 25 个字符",
            trigger: "blur",
          },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          {
            min: 6,
            max: 8,
            message: "长度在 6 到 8 个字符",
            trigger: "blur",
          },
        ],
        yesCheckList: { required: true, message: "请选择", trigger: "blur" },
      },

      /// ////////////////////修改框  form，验证
      editForm: {},
      editDialogVisible: false,
      editFormRules: {
        name: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          {
            min: 1,
            max: 25,
            message: "长度在 1 到 25 个字符",
            trigger: "blur",
          },
        ],
        yesCheckList: { required: true, message: "请选择", trigger: "blur" },
      },
    };
  },
  //生命函数
  created() {
    this.getList();
    this.getAllRoles();
  },
  //方法
  methods: {

    ////////////////////添加
    add() {
      this.$refs.addFormRef.validate(async (valid) => {
        if (!valid) return;
        // 可以发起添加用户的网络请求
        //带图片，构造form
        let fd = new FormData();
        fd.append("file", this.file.raw);
        fd.append("user", JSON.stringify(this.addForm));

        const { data: res } = await this.$http.post("/user/save", fd);

        if (res.code !== 200) {
          return this.$message.error("添加信息失败！");
        }

        this.$message.success("添加信息成功！");
        this.addDialogVisible = false;
        this.getList();
      });
    },

    ///////////////////////////////////
    //查询所有角色
    async getAllRoles() {
      const { data: res } = await this.$http.post("/role/list");
      console.log(res);
      if (res.code !== 200) {
        return this.$message.error("获取列表失败！");
      }
      this.allCheckList = res.data;
    },

    /// //////////////////// 获取所有角色的列表
    async getList() {
      //query=abc&pagenum=1&pagesize=5
      console.log(this.$qs.stringify(this.queryInfo));
      const { data: res } = await this.$http.post(
        "/user/list",
        this.$qs.stringify(this.queryInfo)
      );
      console.log(res);
      if (res.code !== 200) {
        return this.$message.error("获取列表失败！");
      }
      this.list = res.data.records;
      this.total = res.data.total;
    },

    /// ////////////////////编辑功能
    async showEditDialog(id) {
      console.log(id);
      const { data: res } = await this.$http.get("/user/info/" + id);
      if (res.code !== 200) {
        return this.$message.error("查询信息失败！");
      }
      console.log("-----", res.data);
      this.editForm = res.data; // 对象复制
      // this.editForm.yesCheckList=[9,10]
      this.editDialogVisible = true;
    },

    ////////////////////更新
    update() {
      this.$refs.editFormRef.validate(async (valid) => {
        if (!valid) return;
        //

        let fd = new FormData();
        fd.append("file", this.file.raw);
        fd.append("user", JSON.stringify(this.editForm));

        const { data: res } = await this.$http.post("/user/update", fd);
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

    /// ////////////////////删除功能   ，实现批量删除
    removeById(ids) {
      console.log("-----", ids);
      if (ids.length == 0) return this.$message.error("请先选择至少一条");

      // 弹框
      this.$confirm("此操作将永久删除数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(async () => {
          // 删除逻辑
          const { data: res } = await this.$http.post("/user/delete", ids);

          if (res.code !== 200) {
            return this.$message.error("删除信息失败！" + res.msg);
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

    ///////////////////////////////////
    // 多选,组成id数组
    handleSelectionChange(val) {
      console.log(val);
      //复制id到multipleSelection   map  [3,2,5,6,7]
      this.multipleSelection = val.map((x) => {
        return x.id;
      });
      console.log("选择的id：", this.multipleSelection);
    },
    /// ////////////////////分页方法
    // 监听 pagesize 改变的事件
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.queryInfo.pagesize = val;
      this.getList();
    },

    // 监听 页码值 改变的事件
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.queryInfo.pagenum = val; // 获取新页码
      this.getList();
    },

    /// ////////////////////添加功能
    showAddDialog() {
      this.addDialogVisible = true;
    },
    addDialogClosed() {
      this.$refs.addFormRef.resetFields();
      console.log("====", this.$refs.addFormRef);
    },
    editDialogClosed() {
      this.$refs.editFormRef.resetFields();
      console.log("****", this.$refs.editFormRef);
    },
  },
};
</script>

<style scoped>
.home {
  width: 1250px;
  margin-left: auto;
  margin-right: auto;
  margin-top: 20px;
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
