OBS 를 통해 실시간 방송을 송출할 수 있습니다.   
![1.png](..%2F1.png)

실시간 방송 시청 화면
![2.png](..%2F2.png)


## nginx-rtmp module을 사용
nginx-rtmp module은 nginx에 RTMP 서버를 추가할 수 있도록 해주는 모듈입니다.
OBS를 통해 방송을 송출하기 위해서는 nginx-rtmp module을 사용해야 합니다.
infra/nginx.conf 파일을 참고해 주세요    

RTMP로 송출된 방송은 HLS(HTTP Live Streaming)로 변환되어 송출됩니다.