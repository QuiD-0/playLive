let counterComponent = {
  props: ['channel'],
  template: `
        <div>
          <span>{{ viewerCount }}</span><span>명 시청중</span>
        </div>
      `,
  data() {
    return {
      viewerCount: 0
    };
  },
  methods: {
    getViewerCount: function () {
      const self = this;
      axios.get('/api/stage/viewer/' + this.channel)
      .then(function (response) {
        self.viewerCount = response.data;
      })
      .catch(function () {
      });
    },
    heartbeat: function () {
      axios.post('/api/stage/viewer/' + this.channel)
    }
  },
  watch : {
    channel : function() {
      setInterval(this.getViewerCount, 10_000);
      setInterval(this.heartbeat, 10_000);
    }
  }
}