<template>
  <div style="margin-top: 40px">
     <el-breadcrumb separator="/" style="margin-left: 340px">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>角色列表</el-breadcrumb-item>
    </el-breadcrumb>
    <side-menu id="side-menu"></side-menu>
    <div class="home">
      <div style="margin-bottom:20px; margin-left:220px">
        <el-input
          @keyup.enter.native="search"
          placeholder="请输入内容进行搜索..."
          prefix-icon="el-icon-search"
          size="small"
          style="width: 400px; margin-right: 10px"
          v-model="queryInfo"
        >
          <template #append>
            <el-button icon="el-icon-search" @click="search"></el-button>
          </template>
        </el-input>
        <el-button type="primary" @click="showAddDialog">添加</el-button>
        <el-button type="primary" @click="removeById">批量删除</el-button>
      </div>

      <h3 style="margin-left: 250px; float: left; margin-bottom: 20px">
        角色管理表
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
          <el-table-column label="角色名" prop="roleName"></el-table-column>

          <el-table-column label="权限码" prop="rolePerms"></el-table-column>

          <el-table-column label="备注" prop="remark"></el-table-column>
          <!-- 自定义列 -->
          <el-table-column label="操作" width="270px">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="primary"
                icon="el-icon-edit"
                @click="showEditDialog(scope.row.id)"
                >编辑
              </el-button>

              <!-- <el-button
                size="mini"
                type="primary"
                icon="el-icon-delete"
                @click="showAuthDialog(scope.row.id)"
                >授权
              </el-button> -->

              <el-button
                size="mini"
                type="primary"
                icon="el-icon-delete"
                @click="showDisDialog(scope.row.id)"
                >分配
              </el-button>
            </template>
          </el-table-column>
        </el-table>
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
          <el-form-item label="角色名" prop="roleName">
            <el-input v-model="addForm.roleName"></el-input>
          </el-form-item>
          <!-- <el-form-item label="权限码" prop="rolePerms">
          <el-input v-model="addForm.rolePerms"></el-input>
        </el-form-item> -->
          <el-form-item label="备注" prop="remark">
            <el-input v-model="addForm.remark"></el-input>
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
          <el-form-item label="角色名" prop="roleName">
            <el-input v-model="editForm.roleName"></el-input>
          </el-form-item>
          <!-- <el-form-item label="权限码" prop="rolePerms">
          <el-input v-model="editForm.rolePerms"></el-input>
        </el-form-item> -->
          <el-form-item label="备注" prop="remark">
            <el-input v-model="editForm.remark"></el-input>
          </el-form-item>
        </el-form>
        <!-- 底部 -->
        <span slot="footer" class="dialog-footer">
          <el-button @click="editDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="update">确 定</el-button>
        </span>
      </el-dialog>

      <!-- 分配框 -->
      <el-dialog title="分配角色" :visible.sync="disDialogVisible" width="50%">
        <!-- 穿梭框， v-model="yesDis" 已选中数组，:data="noDis" 全部数据-->
        <el-transfer
          filterable
          filter-placeholder="请输入搜索信息"
          v-model="yesDis"
          :data="noDis"
          :titles="['未分配给用户', '已分配给用户']"
          style="width: 80%; margin: 0px auto"
        >
        </el-transfer>

        <!-- 底部 -->
        <span slot="footer" class="dialog-footer">
          <el-button @click="disDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="distribute">确 定</el-button>
        </span>
      </el-dialog>

      <!-- 授权框 -->
      <el-dialog title="角色授权" :visible.sync="authDialogVisible" width="50%">
        <vue-json-editor
          v-model="editForm.rolePerms"
          :showBtns="false"
          :mode="'code'"
          lang="zh"
        />

        <!-- 底部 -->
        <span slot="footer" class="dialog-footer">
          <el-button @click="authDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="authorize">确 定</el-button>
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
      list: [],

      multipleSelection: [], //多选  id

      /// ////////////////////添加框  form，验证
      addDialogVisible: false,
      addForm: {
        roleName: "",
        rolePerms: "",
        remark: "",
      },
      addFormRules: {
        roleName: [{ required: true, message: "请输入", trigger: "blur" }],
        // rolePerms: [
        //   { required: true, message: "请输入", trigger: "blur" },
        // ],
      },

      /// ////////////////////修改框  form，验证
      editForm: {
        roleName: "",
        rolePerms: "",
        remark: "",
      },
      editDialogVisible: false,
      editFormRules: {
        roleName: [{ required: true, message: "请输入", trigger: "blur" }],
        // rolePerms: [
        //   { required: true, message: "请输入", trigger: "blur" },
        // ],
      },

      ////////////////////////////分配角色
      disDialogVisible: false,
      //全部
      noDis: [],
      //已选
      yesDis: [],
      rid: "", //角色id

      authDialogVisible: false,
    };
  },
  //生命函数
  created() {
    this.getList();
  },
  //方法
  methods: {
    async getList() {
      //query=abc&pagenum=1&pagesize=5
      console.log(this.$qs.stringify(this.queryInfo));
      const { data: res } = await this.$http.post("/role/list");
      console.log(res);
      if (res.code !== 200) {
        return this.$message.error("获取列表失败！");
      }
      this.list = res.data;
    },

    ////////////////////添加
    add() {
      this.$refs.addFormRef.validate(async (valid) => {
        if (!valid) return;
        // 可以发起添加用户的网络请求
        const { data: res } = await this.$http.post("/role/save", this.addForm);

        if (res.code !== 200) {
          return this.$message.error("添加信息失败！");
        }

        this.$message.success("添加信息成功！");
        this.addDialogVisible = false;
        this.getList();
      });
    },

    /// ////////////////////编辑功能
    async showEditDialog(id) {
      console.log(id);
      const { data: res } = await this.$http.get("/role/info/" + id);
      if (res.code !== 200) {
        return this.$message.error("查询信息失败！");
      }
      console.log(res.data);
      this.editForm = res.data; // 对象复制
      this.editDialogVisible = true;
    },

    ////////////////////更新
    update() {
      this.$refs.editFormRef.validate(async (valid) => {
        if (!valid) return;
        //
        const { data: res } = await this.$http.post(
          "/role/update",
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
          const { data: res } = await this.$http.post("/role/delete", ids);

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

    async showAuthDialog(id) {
      console.log(id);
      const { data: res } = await this.$http.get("/role/info/" + id);
      if (res.code !== 200) {
        return this.$message.error("查询信息失败！");
      }

      this.editForm = res.data;

      this.authDialogVisible = true;
    },

    async authorize() {
      this.editForm.rolePerms = JSON.stringify(this.editForm.rolePerms);
      console.log(this.editForm);

      const { data: res } = await this.$http.post(
        "/role/update",
        this.editForm
      );
      if (res.code !== 200) {
        return this.$message.error("更新信息失败！");
      }

      // 提示修改成功
      this.$message.success("更新信息成功！");
      // 关闭对话框
      this.authDialogVisible = false;
      // 刷新数据列表
      this.getList();
    },

    /// ////////////////////分配功能
    async showDisDialog(id) {
      const { data: res } = await this.$http.get("/role/transfer/" + id);
      if (res.code !== 200) {
        return this.$message.error("查询信息失败！");
      }

      //已分配
      this.yesDis = res.data.yesdis.map((x) => {
        return x.id;
      });
      //全部[{key,label},{}]
      this.noDis = res.data.all.map((x) => {
        return {
          key: x.id,
          label: x.name,
        };
      });

      this.rid = id; //角色id

      console.log(this.noDis, this.yesDis);

      this.disDialogVisible = true;
    },

    ////////////保存分配
    async distribute() {
      console.log("this.yesDis:", this.yesDis);
      //将角色id加到数组最后
      this.yesDis.push(this.rid);
      console.log("this.yesDis:", this.yesDis);
      const { data: res } = await this.$http.post(
        "/role/savetransfer",
        this.yesDis
      );
      if (res.code !== 200) {
        return this.$message.error("查询信息失败！");
      }

      this.$message.success("分配成功！");

      this.disDialogVisible = false;
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

    /// ////////////////////添加功能
    showAddDialog() {
      this.addDialogVisible = true;
    },
    addDialogClosed() {
      this.$refs.addFormRef.resetFields();
    },
    editDialogClosed() {
      this.$refs.editFormRef.resetFields();
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
