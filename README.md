## Elasticsearch 介绍
Elasticsearch 是一个开源的分布式搜索和数据分析引擎，用于处理和存储大量结构化和非结构化数据。
## 项目意义

## 部署步骤
```shell
# 前提 环境
docker 24.0.5
docker-compose v2.6.1
# 准备一个干净的目录
mkdir elasticsearch-kibana
cd elasticsearch-kibana
# 将项目中doc/script/docker文件夹下的内容复制到当前目录
cd elasticsearch/scripts
# 把init.sh改成可执行文件并 执行
chmod +x init.sh
./init.sh
cd ..
docker-compose up -d
# 查看是否启动成功
docker ps
# 出现一下内容不报错，说明es部署成功了
d57f2a3e74dd   elasticsearch:8.19.0
# 修改doc/script/docker/kibana/docker-compose.yml和doc/script/docker/kibana/data/conf/kibana.yml中的两个ip地址成自己的
# 继续看doc/script/docker/kibana/scripts/init.md
```