const userModule = {
    namespaced: true,
    state: {
        name: localStorage.getItem("name") ? localStorage.getItem("name") : "",
        surname: localStorage.getItem("surname") ? localStorage.getItem("surname") : "",
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