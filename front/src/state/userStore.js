import Vuex from "vuex";
import createPersistedState from 'vuex-persistedstate';
import {v4 as uuidv4} from 'uuid';

const store = new Vuex.Store({
    state: {
        user: null,
        accessToken: null,
        refreshToken: null,
        clientUUID: null
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
        setClientUuid(state) {
            if(state.clientUUID == null) {
                state.clientUUID = uuidv4();
            }
        }
    },
    plugins: [createPersistedState()]
})

export default store;
