echo eliminando archivos
rm -rf build
rm -rf dist
echo compilando
ant
cd dist
echo Dir del .war
pwd 
echo ServidorWeb.war