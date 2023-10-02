<template>
  <div>
    <el-table
      class="tablebox"
      size="small"
      stripe
      :data="searchCardLog"
      v-model.trim="search"
      style="width: 100%"
      :default-sort="{ prop: 'date', order: 'descending' }"
    >
      <el-table-column label="日期" prop="date" width="100px" sortable>
        <template v-slot="date">
          {{ formatDate(date.row.date) }}
        </template>
      </el-table-column>
      <el-table-column label="卡牌1" prop="card1,card1updown" width="200px">
        <template slot-scope="scope1">
          {{ ifNull(scope1.row.card1) }} /
          {{ ifUpDown(scope1.row.card1updown) }}
        </template>
      </el-table-column>
      <el-table-column label="卡牌2" prop="card2,card2updown" width="200px">
        <template slot-scope="scope2">
          {{ ifNull(scope2.row.card2) }} /
          {{ ifUpDown(scope2.row.card2updown) }}
        </template>
      </el-table-column>
      <el-table-column label="卡牌3" prop="card3,card3updown" width="200px">
        <template slot-scope="scope3">
          {{ ifNull(scope3.row.card3) }} /
          {{ ifUpDown(scope3.row.card3updown) }}
        </template>
      </el-table-column>
      <el-table-column label="卡牌4" prop="card4,card4updown" width="200px">
        <template slot-scope="scope4">
          {{ ifNull(scope4.row.card4) }} /
          {{ ifUpDown(scope4.row.card4updown) }}
        </template>
      </el-table-column>
      <el-table-column label="卡牌5" prop="card5,card5updown" width="200px">
        <template slot-scope="scope5">
          {{ ifNull(scope5.row.card5) }} /
          {{ ifUpDown(scope5.row.card5updown) }}
        </template>
      </el-table-column>
      <el-table-column label="註記" prop="mark" width="400px">
        <template slot-scope="mark">
          {{ ifNull(mark.row.mark) }}
        </template>
      </el-table-column>
      <el-table-column text-align="right">
        <template slot="header" slot-scope="scope">
          <el-input
            class="search"
            v-model.trim="search"
            size="mini"
            placeholder="依註記內容搜尋..."
            @keydown.enter.native="search"
            clearable
          />
        </template>
        <template slot-scope="scope">
          <el-button
            style="margin-block-end: 1px"
            size="mini"
            type="primary"
            @click="showUpdateCardLog(scope.$index, scope.row)"
            >編輯</el-button
          >
          <el-button
            size="mini"
            type="danger"
            style="margin-block-end: 1px"
            @click="handleDelete(scope.$index, scope.row)"
            >刪除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="編輯卡牌紀錄" center :visible.sync="dialogVisible">
      <el-form label-width="80px">
        <el-form-item label="日期">
          <el-date-picker
            size="small"
            class="updateCardLog"
            v-model="updateCardLog.date"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="選擇日期"
            @input="$forceUpdate()"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="卡牌1">
          <el-input
            :value="`${ifNull(updateCardLog.card1)} / ${ifUpDown(
              updateCardLog.card1updown
            )}`"
            size="small"
            class="updateCardLog"
            readonly
          ></el-input>
        </el-form-item>
        <el-form-item label="卡牌2">
          <el-input
            :value="`${ifNull(updateCardLog.card2)} / ${ifUpDown(
              updateCardLog.card2updown
            )}`"
            size="small"
            class="updateCardLog"
            readonly
          ></el-input>
        </el-form-item>
        <el-form-item label="卡牌3">
          <el-input
            :value="`${ifNull(updateCardLog.card3)} / ${ifUpDown(
              updateCardLog.card3updown
            )}`"
            size="small"
            class="updateCardLog"
            readonly
          ></el-input>
        </el-form-item>
        <el-form-item label="卡牌4">
          <el-input
            :value="`${ifNull(updateCardLog.card4)} / ${ifUpDown(
              updateCardLog.card4updown
            )}`"
            size="small"
            class="updateCardLog"
            readonly
          ></el-input>
        </el-form-item>
        <el-form-item label="卡牌5">
          <el-input
            :value="`${ifNull(updateCardLog.card5)} / ${ifUpDown(
              updateCardLog.card5updown
            )}`"
            size="small"
            class="updateCardLog"
            readonly
          ></el-input>
        </el-form-item>

        <el-form-item label="註記">
          <el-input
            v-model="updateCardLog.mark"
            size="small"
            class="updateCardLog"
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button size="small" type="primary" @click="doUpdateCardLog"
          >確定</el-button
        >
        <el-button size="small" @click="dialogVisible = false">取消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import moment from "moment";
export default {
  name: "CardLog",
  data() {
    return {
      cardLog: [],
      search: "",
      dialogVisible: false,
      updateCardLog: {
        date: "",
        mark: "",
      },
    };
  },
  created() {
    this.getCardLog();
  },
  computed: {
    searchCardLog() {
      //抓數據
      const { search, cardLog } = this;
      //過濾的數據
      let fCardLog;
      //進行過濾
      fCardLog = cardLog.filter((m) => m.mark.indexOf(search) !== -1);

      return fCardLog;
    },
  },
  mounted() {
    this.getCardLog();
  },
  methods: {
    filteredCardLog() {
      const search = this.search.trim().toLowerCase();
      return this.cardLog.filter((item) => {
        return item.mark.toLowerCase().includes(search);
      });
    },
    doUpdateCardLog() {
      if (!this.updateCardLog.date || !this.updateCardLog.mark) {
        this.$message.error("請輸入日期及註記欄位!");
        return;
      }

      // 檢查註記是否超過 100 個字
      if (this.updateCardLog.mark.length > 100) {
        this.$message.error("註記不得超過 100 個字!");
        return;
      }
      // 創建要更新的數據對象
      const dataToUpdate = {
        id: this.updateCardLog.id,
        date: moment(this.updateCardLog.date).add(8, "hours"),
        mark: this.updateCardLog.mark,
      };
      // 执行提交操作
      this.$axios
        .patch(
          "http://localhost:8888/api/CardLog/" + dataToUpdate.id,
          dataToUpdate
        )
        .then((resp) => {
          if (resp) {
            this.dialogVisible = false;
            this.getCardLog();
            this.$message({
              type: "success",
              message: "已更新紀錄!",
            });
          }
        })
        .catch((error) => {
          console.error("請求失敗:", error);
          // 返回錯誤訊息
          if (error.response && error.response.data) {
            this.$message.error(error.response.data.message);
          } else {
            this.$message.error("請求失敗，請重試。");
          }
        });
    },
    getCardLog() {
      this.$axios
        .get("http://localhost:8888/api/CardLog", { params: this.search })
        .then((resp) => (this.cardLog = resp.data));
    },
    showUpdateCardLog(index, data) {
      Object.assign(this.updateCardLog, data);
      this.dialogVisible = true;
    },
    handleDelete(index, data) {
      this.$confirm(
        "此操作將刪除日期為[" +
          moment(data.date).format("YYYY-MM-DD") +
          "]註記為[" +
          data.mark +
          "]的該筆紀錄, 是否繼續?",
        "提示",
        {
          confirmButtonText: "確定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(() => {
          this.$axios
            .delete("http://localhost:8888/api/CardLog/" + data.id)
            .then((resp) => {
              this.$message({
                type: "success",
                message: "刪除成功!",
              });
              if (resp) {
                this.getCardLog();
              }
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消刪除",
          });
        });
    },
    ifUpDown: function (value) {
      if (value == null) {
        return "無";
      } else {
        return value == false ? "反面" : "正面";
      }
    },
    ifNull: function (value) {
      return value == null ? "無" : value;
    },
    formatDate: function (date) {
      return moment(date).format("YYYY-MM-DD");
    },
  },
};
</script>

<style>
.updateCardLog {
  width: 300px;
  text-align: right;
  margin-left: 6px !important;
}

.updateCardLog .el-tag .el-tag--light {
  width: 300px;
  text-align: right;
  margin-left: 6px !important;
}

.el-table__header-wrapper {
  height: 120px;
}

.el-table td,
.el-table th {
  padding: 12px;
  text-align: center;
}

.el-dialog {
  height: 700px;
  width: 500px;
}

.el-tag .el-tag-light {
  text-align: left;
  margin-left: 2px !important;
}

.el-input__inner {
  flex: 2;
  width: 300px;
}

.el-dialog {
  line-height: 4em;
}

.el-tag .el-tag--light {
  margin-left: 0px !important;
}
</style>
