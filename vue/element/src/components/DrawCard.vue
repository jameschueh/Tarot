<template>
  <div>
    <div class="block">
      <span class="demonstration" style="font-size: 32px">請拉動下方長條選擇抽牌數量</span>
      <center>
      <el-slider
        v-model="selectedCount"
        :step="1"
        :min="1"
        :max="5"
        width="500px"
        show-stops
        :marks="marks"
      >
      </el-slider>
      </center>
    </div>
    <el-button type="primary" :disabled="isDrawing" @click="drawCards">
      抽牌
    </el-button>
    <el-button type="primary" :disabled="!isDrawing" @click="unlockDraw">
      重新抽牌
    </el-button>
    <el-button type="primary" :disabled="!isDrawing" @click="addRecord">
      新增抽牌紀錄
    </el-button>

    <!-- 顯示卡片 -->
    <div v-show="cards.length > 0" class="tarotsTable">
      <ul
        v-for="card in cards"
        :key="card.id"
        class="cardList"
        style="list-style: none"
      >
        <img :src="getTarotImageUrl(card.id)" class="tarot-img" />
        <li class="tarot-names">
          卡牌：{{ card.name_ch }} / {{ card.name_en }}
        </li>
        <li>正逆位：{{ ifUpDown(card.updown) }}</li>
        <li>關鍵字：{{ card.updownmean }}</li>
        <li>解析：{{ card.updownmeandetail }}</li>
      </ul>
    </div>

    <el-dialog title="新增抽牌紀錄" :visible.sync="isAddingRecord">
      <el-form ref="recordForm" label-width="100px" v-show="cards">
        <el-form-item label="日期：">
          <el-date-picker
            v-model="date"
            type="date"
            size="small"
            placeholder="選擇日期"
          ></el-date-picker>
        </el-form-item>
        <div v-show="cards.length > 0" class="cardLog">
          <div v-for="(card, index) in cards" :key="card.id">
            <p>
              卡牌 {{ index + 1 }}：　{{ card.name_ch }} /
              {{ card.updown == "down" ? "反面" : "正面" }}
            </p>
          </div>
        </div>
        <el-form-item label="註記：">
          <el-input type="textarea" v-model="mark" :rows="4"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancelAddRecord">取消</el-button>
        <el-button type="primary" @click="postCardLog">儲存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import moment from "moment";

export default {
  name: "DrawCard",
  data() {
    return {
      selectedCount: 5,
      isDrawing: false, // 新增 isDrawing 屬性
      cards: [], // 存放抽取的卡牌
      marks: {
        0: "0",
        1: "1",
        2: "2",
        3: "3",
        4: "4",
        5: "5",
      },
      isAddingRecord: false, // 新增抽牌紀錄表單是否可見
      date: "", // 日期
      card1: "", // 卡牌1
      card2: "", // 卡牌2
      card3: "", // 卡牌3
      card4: "", // 卡牌4
      card5: "", // 卡牌5
      mark: "", // 註記
    };
  },
  methods: {
    ifUpDown: function (value) {
      if (value == null) {
        return "無";
      } else {
        return value == "down" ? "逆位" : "正位";
      }
    },
    ifValueUpDown(value) {
      switch (value) {
        case 0:
          return null;
        case value:
          return value;
        case "up":
          return 0;
        case "down":
          return 1;
        default:
          return null;
      }
    },
    async postCardLog() {
      if (!this.mark || !this.date) {
        this.$message.error("日期及註記為必填欄位");
        return;
      }
      if (this.mark.length > 100) {
        this.$message.error("註記不得超過 100 個字!");
        return;
      }

      const cardData = [];

      for (let i = 0; i < this.selectedCount; i++) {
        cardData.push({
          name_ch: this.ifValueUpDown(this.cards[i].name_ch),
          updown: this.ifValueUpDown(this.cards[i].updown),
        });
      }

      const dataToPost = {
        date: moment(this.date).add(8, "hours"),
        mark: this.mark,
        card1: this.cards[0] ? this.cards[0].name_ch : null,
        card1updown: this.cards[0]
          ? this.cards[0].updown === "down"
            ? 0
            : 1
          : null,
        card2: this.cards[1] ? this.cards[1].name_ch : null,
        card2updown: this.cards[1]
          ? this.cards[1].updown === "down"
            ? 0
            : 1
          : null,
        card3: this.cards[2] ? this.cards[2].name_ch : null,
        card3updown: this.cards[2]
          ? this.cards[2].updown === "down"
            ? 0
            : 1
          : null,
        card4: this.cards[3] ? this.cards[3].name_ch : null,
        card4updown: this.cards[3]
          ? this.cards[3].updown === "down"
            ? 0
            : 1
          : null,
        card5: this.cards[4] ? this.cards[4].name_ch : null,
        card5updown: this.cards[4]
          ? this.cards[4].updown === "down"
            ? 0
            : 1
          : null,
      };

      this.$axios
        .post("http://localhost:8888/api/CardLog", dataToPost)
        .then((resp) => {
          if (resp) {
            this.isAddingRecord = false;
            this.resetForm();
            this.cancelAddRecord();
            this.$message({
              type: "success",
              message: "新增成功!",
            });
          }
        })
        .catch(() => {
          resp = err;
          this.$message({
            type: "info",
            message: "已取消新增",
          });
        });
    },
    addRecord() {
      this.isAddingRecord = true;
      this.cards.date = new Date().toISOString().split("T")[0];
      this.cards.card1;
      this.cards.card1updown;
      this.cards.card2;
      this.cards.card2updown;
      this.cards.card3;
      this.cards.card3updown;
      this.cards.card4;
      this.cards.card4updown;
      this.cards.card5;
      this.cards.card5updown;
    },
    cancelAddRecord() {
      this.isAddingRecord = false;
      // 清空表單資料
      this.resetForm();
    },
    resetForm() {
      this.date = "";
      this.card1 = "";
      this.card1updown = "";
      this.card2 = "";
      this.card2updown = "";
      this.card3 = "";
      this.card3updown = "";
      this.card4 = "";
      this.card4updown = "";
      this.card5 = "";
      this.card5updown = "";
      this.mark = "";
    },
    unlockDraw() {
      this.isDrawing = false; // 解鎖抽牌，同時鎖定重新抽牌
    },
    getTarotImageUrl(id) {
      return `http://localhost:8888/api/Tarot/tarotImages/${id}`;
    },
    updateSelectedCount() {
      // 在拉動 Slider 時更新顯示的數量
    },
    drawCards() {
      this.isDrawing = true;
      this.$axios
        .get(`http://localhost:8888/api/Drawcards?count=${this.selectedCount}`)
        .then((response) => {
          this.cards = response.data;
        })
        .catch((error) => {
          this.$message.error("抽牌失敗：" + error.message);
        });
    },
  },
};
</script>

<style>
.tarotsTable {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  transition: background-color 0.3s ease;
  border-radius: 5px;
  position: relative;
}
.cardList {
  width: 300px;
  margin-right: 10px;
  margin-left: 10px;
  border: 5px solid #fff;
  transform: translateY(0px);
  font-size: 16px;
  line-height: 28px;
  letter-spacing: 3px;
  text-align: left;
  padding: 10px;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: #f9f9f9;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: background-color 0.3s ease;
}

.tarot-img {
  height: 250px;
  height: auto;
  max-width: 100%;
  width: 100%;
  vertical-align: top;
}

.tarot-names {
  font-weight: bold;
  font-size: 16px;
  text-align: left;
  margin-top: 10px;
}

.cardList li {
  margin-bottom: 12px;
}

.el-dialog {
  height: 900px;
  height: auto;
  width: 500px;
  line-height: 4em;
}

.cardLog {
  text-align: left;
  vertical-align: middle;
  font-size: 14px;
  line-height: 40px;
  padding: 0 12px 0 0;
  margin-left: 2.5em;
}

.el-form-item__content {
  text-align: left;
}

.el-slider {
  width: 500px;
  height: 120px;
  text-align: center;
}

.el-slider__marks-text {
  font-size: 36px;
}
</style>
