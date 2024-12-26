#bin/bash

cd ../../

git pull origin main
./gradlew buildFront build
docker build -t playlive .

cd infra/deploy || false

docker-compose down || false
docker-compose up -d
