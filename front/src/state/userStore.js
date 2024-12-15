import Vuex from "vuex";

const store = new Vuex.Store({
    state: {
        user: null,
        accessToken: null,
        refreshToken: null
    },
    mutations: {
        setUser(state, user) {
            state.user = user;
        },
        setAccessToken(state, accessToken) {
            state.accessToken = accessToken;
        },
        setRefreshToken(state, refreshToken) {
            state.refreshToken = refreshToken;
        }
    }
})

export default store;
