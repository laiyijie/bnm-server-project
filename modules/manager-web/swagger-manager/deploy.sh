ossutil cp -f ./admin.yaml oss://bnm-swagger/admin.yaml
rm -rf jsclient
java -jar swagger-codegen-cli-laiyijie.jar generate -i admin.yaml -l typescript-angular -o ./jsclient -c ./angularjsclientconfig.json
cnpm install 
tsc
npm version 2.0.$(date +%s)
npm login  <<EOF
bangnongmang
memeda00
huangxinhe@bangnongmang.cn
EOF
npm publish
