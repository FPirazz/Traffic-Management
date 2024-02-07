const userModule = {
    namespaced: true,
    state: {
        email: localStorage.getItem("email") ? localStorage.getItem("email") : "",
        lastVisitedCategory: localStorage.getItem("lastVisitedCategory") ? localStorage.getItem("lastVisitedCategory") : "",
        lastVisitedCourse: localStorage.getItem("lastVisitedCourse") ? localStorage.getItem("lastVisitedCourse") : "",
        tokenBalance: localStorage.getItem("tokenBalance") ? localStorage.getItem("tokenBalance") : 0,
    },
    getters: {
        email: (state) => state.email,
        lastVisitedCategory: (state) => state.lastVisitedCategory,
        lastVisitedCourse: (state) => state.lastVisitedCourse,
        isLoggedIn: (state) => !!state.email,
        tokenBalance: (state) => state.tokenBalance,
    },
    mutations: {
        login(state, email) {
            state.email = email;
            localStorage.setItem("email", email);
        },
        logout(state) {
            state.email = "";
            localStorage.removeItem("email");
        },
    },
    actions: {
    },
};

export default userModule;