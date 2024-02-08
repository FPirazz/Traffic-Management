const userModule = {
    namespaced: true,
    state: {
        name: localStorage.getItem("name") ? localStorage.getItem("name") : "",
        surname: localStorage.getItem("surname") ? localStorage.getItem("surname") : "",
        role: localStorage.getItem("role") ? localStorage.getItem("role") : "",
    },
    getters: {
        name: (state) => state.name,
        surname: (state) => state.surname,
        role: (state) => state.role,
        isLoggedIn: (state) => !!state.name && !!state.surname,
    },
    mutations: {
        loginName(state, name) {
            state.name = name;
            localStorage.setItem("name", name);
        },
        loginSurname(state, surname) {
            state.surname = surname;
            localStorage.setItem("surname", surname);
        },
        loginRole(state, role) {
            state.role = role;
            localStorage.setItem("role", role);
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