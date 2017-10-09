ossutil cp -f ./main.yaml oss://bnm-swagger/main.yaml
rm -rf jsclient
java -jar swagger-codegen-cli-laiyijie.jar generate -i main.yaml -l typescript-angular -o ./jsclient -c ./angularjsclientconfig.json
cnpm install 
tsc
npm version 2.0.$(date +%s)
npm login  <<EOF
bangnongmang
memeda00
huangxinhe@bangnongmang.cn
EOF
npm publish
java -jar swagger-codegen-cli-laiyijie.jar generate -DuseRxJava=true -i main.yaml -l java -o ./javaclient -c ./javaclientconfig.json
cd ./javaclient
mvn --batch-mode release:update-versions -DdevelopmentVersion=2.0.$(date +%s)-SNAPSHOT
mvn deploy
