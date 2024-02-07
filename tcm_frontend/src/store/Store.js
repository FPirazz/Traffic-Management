import { createStore } from "vuex";
import userModule from "./UserModule.js";

export default createStore({
    modules: {
        user: userModule,
    },
});