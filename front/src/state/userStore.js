import Vuex from "vuex";
import createPersistedState from 'vuex-persistedstate';

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
    },
    plugins: [createPersistedState()]
})

export default store;
