let memberStore = new Vuex.Store({
    state: {
        member: '',
        accessToken: '',
        refreshToken: ''
    },
    mutations: {
        setMember: function (state, member) {
            state.member = member;
        },
        setAccessToken: function (state, accessToken) {
            state.accessToken = accessToken;
        },
        setRefreshToken: function (state, refreshToken) {
            state.refreshToken = refreshToken;
        }
    },
    getters: {
    },
    actions: {}
})