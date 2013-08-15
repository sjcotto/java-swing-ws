#!bin/bash

ant jar

cp -r images dist
cp web_services.xml dist
cp install.sh dist

cd dist

tar -cvzf DispositivoMovil.tar.gz *

cd ..

