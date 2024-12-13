let viewerComponent = {
    template: `
        <div>
          <span>{{ viewerCount }}</span><span>명 시청중</span>
        </div>
      `,
    data() {
        return {
            channel: '',
            viewerCount: 0
        };
    },
    methods: {
        getViewerCount: function () {
            const self = this;
            let callback = function (result) {
                self.viewerCount = result;
            }
            getAxios('/api/stage/viewer/' + this.channel, {}, callback);
        },
        heartbeat: function () {
            postAxios('/api/stage/viewer/' + this.channel, {}, function () {});
        }
    },
    computed: {
        updateChannel: function () {
            return channelStore.state.channel;
        }
    },
    watch: {
        updateChannel: function (val) {
            this.channel = val;
            setInterval(this.getViewerCount, 10_000);
            setInterval(this.heartbeat, 10_000);
        }
    },
}