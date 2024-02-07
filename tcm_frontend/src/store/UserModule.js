const userModule = {
    namespaced: true,
    state: {
        name: localStorage.getItem("name") ? localStorage.getItem("name") : "",
        surname: localStorage.getItem("surname") ? localStorage.getItem("surname") : "",
        lastVisitedCategory: localStorage.getItem("lastVisitedCategory") ? localStorage.getItem("lastVisitedCategory") : "",
        lastVisitedCourse: localStorage.getItem("lastVisitedCourse") ? localStorage.getItem("lastVisitedCourse") : "",
        tokenBalance: localStorage.getItem("tokenBalance") ? localStorage.getItem("tokenBalance") : 0,
    },
    getters: {
        isLoggedIn: (state) => !!state.name && !!state.surname,
    },
    mutations: {
        login(state, name, surname) {
            state.name = name;
            state.surname = surname;
            localStorage.setItem("name", name);
            localStorage.setItem("surname", surname);
        },
        logout(state) {
            state.name = "";
            state.surname = "";
            localStorage.removeItem("name");
            localStorage.removeItem("surname");
        },
    },
    actions: {
    },
};

export default userModule;