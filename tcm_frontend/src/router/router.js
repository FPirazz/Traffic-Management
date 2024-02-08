import { createRouter, createWebHistory } from "vue-router";
import HomePage from "@/pages/HomePage.vue";
import Register from "@/pages/creds/Register.vue";
import Login from "@/pages/creds/Login.vue";
import Intersections from "@/pages/intersection/Intersections.vue"
import IntersectionPage from "@/pages/intersection/IntersectionPage.vue"

const routes = [
    { path: "/", name: "Home", component: HomePage },
    { path: "/user/register", name: "Register", component: Register},
    { path: "/user/login", name: "Login", component: Login},
    { path: "/intersections", name: "Intersections", component: Intersections},
    { path: "/intersections/1", name: "IntersectionPage", component: IntersectionPage},

    { path: "/logout", name: "Logout", component: HomePage },
    { path: "/:catchAll(.*)", redirect: "/404" }
]

const router = createRouter({
    history: createWebHistory(),
    routes: routes,
})

export default router;