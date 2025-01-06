## 라이브 스트리밍 서비스

RTMP 방식으로 라이브 스트리밍을 송출할 수 있는 서비스입니다.
OBS 를 통해 실시간 방송을 송출할 수 있습니다.

라이브 방송 목록 화면
![1.png](/assets/home.png)

실시간 방송 시청 화면
![2.png](/assets/stream.png)


## nginx-rtmp module을 사용
nginx-rtmp module은 nginx에 RTMP 서버를 추가할 수 있도록 해주는 모듈입니다.
OBS를 통해 방송을 송출하기 위해서는 nginx-rtmp module을 사용해야 합니다.
infra/rtmp/nginx.conf 파일을 참고해 주세요    

RTMP로 송출된 방송은 HLS(HTTP Live Streaming)로 변환되어 송출됩니다.
