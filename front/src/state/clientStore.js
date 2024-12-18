import Vuex from "vuex";
import createPersistedState from 'vuex-persistedstate';
import {v4 as uuidv4} from 'uuid';

const store = new Vuex.Store({
    state: {
        clientUUID: null,
        watchingChannel: null,
    },
    mutations: {
        setClientUuid(state) {
            if(state.clientUUID == null) {
                state.clientUUID = uuidv4();
            }
        },
        setWatchingChannel(state, channel) {
            state.watchingChannel = channel;
        },
    },
    plugins: [createPersistedState({
        key: 'clientStore',
    })]
})

export default store;
