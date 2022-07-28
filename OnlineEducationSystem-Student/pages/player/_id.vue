<template>
  <div>
    <!-- 阿里云视频播放器样式 -->
    <link
      rel="stylesheet"
      href="https://g.alicdn.com/de/prismplayer/2.8.1/skins/default/aliplayer-min.css"
    />
    <!-- 阿里云视频播放器脚本 -->
    <script
      charset="utf-8"
      type="text/javascript"
      src="https://g.alicdn.com/de/prismplayer/2.8.1/aliplayer-min.js"
    />
    <!-- 定义播放器dom -->

    <div id="J_prismPlayer" class="prism-player" style="height: 700px" />
  </div>
</template>
<script>
import vodAPI from "@/api/vod/vod";

export default {
  layout: "video",
  asyncData({ params, error }) {
    //动态路由页面是什么，parms后面就跟什么
    return vodAPI.getPlayAuth(params.id).then((response) => {
      return {
        id: params.id,
        playAuth: response.data,
      };
    });
  },

  /**
   * 页面渲染完成时：此时js脚本已加载，Aliplayer已定义，可以使用mounted()
   * 如果在created生命周期函数中使用，Aliplayer is not defined错误
   */
  mounted() {
    new Aliplayer(
      {
        id: "J_prismPlayer",
        vid: this.id, // 视频id
        playauth: this.playAuth, // 播放凭证
        encryptType: "1", // 如果播放加密视频，则需设置encryptType=1，非加密视频无需设置此项
        width: "100%",
        height: "500px",
        cover: "", // 封面
        qualitySort: "asc", // 清晰度排序
        mediaType: "video", // 返回音频还是视频
        autoplay: false, // 自动播放
        isLive: false, // 直播
        rePlay: false, // 循环播放
        preload: true,
        controlBarVisibility: "hover", // 控制条的显示方式：鼠标悬停
        useH5Prism: true, // 播放器类型：html5
      },
      function (player) {
        console.log("nichijoux's 播放器创建成功");
      }
    );
  },
};
</script>
