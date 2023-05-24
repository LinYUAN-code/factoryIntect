import { createApp } from "vue";
import router from "./router";
import App from "./App.vue";
import "./global.scss";

Array.prototype.repeat = function (num) {
  const ans = [];
  for (let i = 0; i < num; i++) {
    ans.push(...this);
  }
  return ans;
};

createApp(App).use(router).mount("#app");
