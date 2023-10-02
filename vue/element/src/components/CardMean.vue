<template>
  <div>
    <div class="tarotstable">
      <div class="tarot" v-for="card in tarot" :key="card.id">
        <div class="card-content" @click="showTarotDetails(card)">
          <img class="img" :src="getTarotImageUrl(card.id)" />
          <h2 class="tarot-names">{{ card.name_ch }} / {{ card.name_en }}</h2>
        </div>
      </div>
    </div>

    <!-- 卡片詳細資料彈出式對話框 -->
    <el-dialog
      :visible.sync="showDetails"
      title="卡片詳細資料"
      width="30%"
      class="tarotListTitle"
    >
      <!-- 這裡顯示卡片的詳細資料 -->
      <div v-if="selectedTarot">
        <img
          class="tarotListimg"
          :src="getTarotImageUrl(selectedTarot.id)"
          style="line-height: 3em"
        />
        <div class="namefont" style="line-height: 3em; font-size: 24px">
          {{ selectedTarot.name_ch }} / {{ selectedTarot.name_en }}
        </div>
        <!-- 這裡添加其他卡片詳細資料，例如描述等 -->
        <ul class="tarotList" style="list-style: none">
          <li>正位關鍵字：{{ selectedTarot.up }}</li>
          <br />
          <li>正位意思：{{ selectedTarot.upmean }}</li>
          <br />
          <li>逆位關鍵字：{{ selectedTarot.down }}</li>
          <br />
          <li>逆位意思：{{ selectedTarot.downmean }}</li>
          <br />
          <li>說明：{{ selectedTarot.mean }}</li>
          <br />
          <li>建議：{{ selectedTarot.suggest }}</li>
        </ul>
      </div>
      <span slot="footer" class="footerButton">
        <center>
          <el-button @click="showDetails = false">關閉</el-button>
        </center>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "CardMean",
  data() {
    return {
      tarot: [],
      showDetails: false,
      selectedTarot: null,
    };
  },
  mounted() {
    this.getTarot();
  },
  methods: {
    showTarotDetails(card) {
      this.selectedTarot = card;
      this.showDetails = true;
    },
    getTarot() {
      this.$axios
        .get("http://localhost:8888/api/Tarot")
        .then((resp) => (this.tarot = resp.data));
    },
    getTarotImageUrl(id) {
      return `http://localhost:8888/api/Tarot/tarotImages/${id}`;
    },
  },
};
</script>


<style>
.tarotstable {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}
.tarot {
  width: 300px;
  margin-right: 10px;
  margin-left: 10px;
  transform: translateY(0px);
  transition: all 0.5s;
  cursor: pointer;
  padding: auto;
  text-align: center;
}
.tarot .img{
  height: 250px;
  height: auto;
  max-width: 100%;
  width: 100%;
  vertical-align: middle;
}
.tarot-names {
  margin-top: 10px;
  font-weight: bold;
  font-size: 16px;
}
.namefont {
  font-weight: bold;
}
.tarotList {
  font-size: 16px;
  line-height: 24px;
  letter-spacing: 3px;
  text-align: left;
}
.el-dialog__footer {
  height: 100px;
}
.el-dialog__footer .footerButton {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 50px;
}
.card-content {
  padding: 20px;
  position: relative;
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #f9f9f9;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: background-color 0.3s ease;
  margin: 10px;
  margin-right: 10px;
  margin-left: 10px;
  background-color: #fff;
  transform: translateY(0px);
  transition: all 0.5s;
  cursor: pointer;
}
.card-content .h2 {
  font-size: 24px;
  font-weight: bold;
  border-bottom: 1px solid #333333;
  padding-bottom: 10px;
  margin-bottom: 10px;
  text-align: center;
}
.card-content:hover {
  background-color: #e1e1e1;
}

.tarotListimg{
  height: 450px;
  height: auto;
  width: 250px;
  vertical-align: middle;
}
</style>
