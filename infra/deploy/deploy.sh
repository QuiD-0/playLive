#bin/bash

curl -L -o source.zip https://github.com/QuiD-0/playLive/archive/refs/heads/main.zip
rm -rf playLive-main
unzip source.zip
copy ./env/application-live.yaml ./playLive-main/src/main/resources/application-live.yaml
copy ./env/.env ./playLive-main/front/.env

cd playLive-main || exit

./gradlew clean buildFront build
docker build -t playlive .

cd infra/deploy || false

docker-compose down || false
docker-compose up -d

