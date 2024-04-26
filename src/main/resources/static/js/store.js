let store = new Vuex.Store({
  state: {
    channel: ''
  },
  mutations: {
    setChannel: function (state, channel) {
      state.channel = channel;
    }
  },
  getters: {
  },
  actions: {
  }
});
