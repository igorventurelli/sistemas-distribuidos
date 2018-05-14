#!/bin/bash

echo "Inicializando o build da API Java..."

cd api/source/
mvn clean install

echo "Build realizado com sucesso!"
echo "Movendo o jar para a /api/jar"

cd target/
mv sis-dist-0.0.1-SNAPSHOT.jar card.jar
cp -R card.jar ../../jar/
cd ..
rm -rf target

echo "Jar copiado."

echo "Inicializando o build do Client Go"

cd ..
cd ..
cd client-2/go/source
go build card.go

echo "Build realizado com sucesso!"
echo "Movendo o arquivo compilado para client-2/go"

cp -R card ../
rm card

echo "Arquivo copiado"

cd ..
cd ..
cd ..

echo "Script finalizado. Iniciando Docker"

docker-compose build
docker-compose up -d