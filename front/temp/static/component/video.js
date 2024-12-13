let videoComponent = {
    template: `
        <video id="video-player" class="m-5"
           poster="https://afreehp.kr/update/bnr/bnr_penalty_type_1.png"
           playsinline="" autoplay muted controls x-webkit-airplay="" webkit-playsinline=""
           controlsList="noplaybackrate nodownload" width="1200px"></video>
    `,
    data() {
        return {
            channel: '',
        };
    },
    methods: {
        check: function () {
            const self = this;
            let callback = function (result) {
                if (result === true) {
                    self.playHls();
                }
            }
            getAxios('/api/stage/check/' + this.channel, {}, callback);
        },
        playHls: function () {
            var video = document.getElementById('video-player');
            var videoSrc = "/api/stage/live/" + this.channel;

            if (video.canPlayType('application/vnd.apple.mpegurl')) {
                video.src = "http://146.56.115.136:8000/hls/" + this.channel
                    + ".m3u8";

            } else if (Hls.isSupported()) {
                var hls = new Hls();
                hls.loadSource(videoSrc);
                hls.attachMedia(video);
            }
            video.play();
        },
    },
    computed: {
        updateChannel: function () {
            return channelStore.state.channel;
        }
    },
    watch: {
        updateChannel: function (val) {
            this.channel = val;
            this.check();
        }
    },
}
