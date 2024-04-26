let videoComponent = {
    data() {
        return {
            channel: '',
        };
    },
    template: `
        <video id="video-player"
           poster="https://afreehp.kr/update/bnr/bnr_penalty_type_1.png"
           playsinline="" autoplay muted controls x-webkit-airplay="" webkit-playsinline=""
           controlsList="noplaybackrate nodownload" width="1327px"></video>
    `,
    methods: {
        check: function () {
            const self = this;
            axios.get('/api/stage/check/' + this.channel)
            .then(function (response) {
                if (response.data === true) {
                    self.playHls();
                } else {
                    alert("오프라인입니다.");
                }
            })
            .catch(function () {
                alert("잠시 후 다시 시도해주세요.");
            });
        },
        playHls: function () {
            var video = document.getElementById('video-player');
            var videoSrc = "/api/stage/live" + this.channel;

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