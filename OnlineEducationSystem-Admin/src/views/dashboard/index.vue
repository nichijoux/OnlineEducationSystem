<template>
  <div class="dashboard-container">
    <div class="dashboard-text">name: {{ name }}</div>
    <div class="dashboard-text">roles: {{ roles }}</div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "nichijoux-dashboard",
  computed: {
    ...mapGetters(["name", "roles"]),
  },
  data() {
    return {
    };
  },
};
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}

.image {
  width: 100px;
  height: 100px;
}
.m-center {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
