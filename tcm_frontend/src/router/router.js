import { createRouter, createWebHistory } from "vue-router";
import HomePage from "@/pages/HomePage.vue";

const routes = [
    { path: "/", name: "Home", component: HomePage },
    { path: "/:catchAll(.*)", redirect: "/404" }
]

const router = createRouter({
    history: createWebHistory(),
    routes: routes,
})

export default router;