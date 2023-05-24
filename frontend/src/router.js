import { createRouter, createWebHashHistory } from "vue-router";
import Home from "./views/Home.vue";
import Login from "./views/Login.vue";
import Registe from "./views/Registe.vue";
import Result from "./views/Result.vue";
import NotFound from "./views/NotFound.vue";

const routes = [
  { path: "/", component: Home },
  { path: "/login", component: Login },
  { path: "/registe", component: Registe },
  { path: "/result", component: Result },
  { path: "/:pathMatch(.*)", component: NotFound },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
