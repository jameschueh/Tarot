<template>
  <div>
    <vue-particles
      color="#555555"
      :particleOpacity="0.3"
      :particlesNumber="60"
      shapeType="circle"
      :particleSize="3"
      linesColor="#888888"
      :linesWidth="1"
      :lineLinked="true"
      :lineOpacity="0.6"
      :linesDistance="150"
      :moveSpeed="2"
      :hoverEffect="true"
      hoverMode="grab"
      :clickEffect="true"
      clickMode="push"
      class="lizi"
    >
    </vue-particles>
    <div>
      <div class="info-box">
        <h1 style="font-size: 38px;">祝福：</h1>
        <p class="boxFont">{{ blessingText }}</p>
      </div>
      <div class="info-box">
        <h1 style="font-size: 38px;">注意事項：</h1>
        <p class="boxFont">{{ cautionText }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      blessingText:
        "願塔羅牌的智慧引導你，照亮你的生命之路，帶來洞察和啟示。願它們幫助你找到答案，帶來平靜和智慧。",
      cautionText:
        "在使用塔羅牌時，請保持開放的心態，不要過度解讀或固執於特定結果。牌卡僅為引導工具，最終決策和行動仍需憑直覺和自己的判斷。",
    };
  },
};
</script>

<style>
#particles-js {
  width: 100%;
  width: calc(100% - 55px);
  height: calc(100% - 100px);
  position: absolute;
}

.info-box {
  background-color: rgba(200, 200, 200, 0.2);
  padding: 20px;
  border-radius: 5px;
  margin: 20px;
  line-height: 250px;
}

.boxFont {
  font-size: 20px;
  line-height: 10px;
}
</style>
