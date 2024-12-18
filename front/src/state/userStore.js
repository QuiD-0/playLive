import Vuex from "vuex";
import createPersistedState from 'vuex-persistedstate';

const store = new Vuex.Store({
    state: {
        user: {
            type: Object,
            id: Number,
            email: String,
            nickname: String,
            avatar: String,
        },
        accessToken: null,
        refreshToken: null,
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
        },
        logout(state) {
            state.user = null;
            state.accessToken = null;
            state.refreshToken = null;
        }
    },
    plugins: [createPersistedState({
        key: 'userStore',
    })]
})

export default store;
