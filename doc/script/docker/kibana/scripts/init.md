# 部署kibana步骤
```shell
# 进入docker
docker exec -it elasticsearch-saRr /bin/bash
# 创建kibana用户
/usr/share/elasticsearch/bin/elasticsearch-service-tokens create elastic/kibana kibana01
# 将输出的token拷贝到kibana的./data/conf/kibana.yml文件中
如：AAEAAWVsYXN0aWMva2liYW5hL2tpYmFuYTAxOlB2S0NBdWtsUWc2MUlIS1c0NHJuMGc
```
