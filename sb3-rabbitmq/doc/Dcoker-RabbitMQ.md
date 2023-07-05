# 官网文档link
https://rabbitmq.com/download.html
# 拉取镜像
```shell
docker pull rabbitmq:3.12-management
```

# 运行容器(方式1)
```shell
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management
```

# 运行容器 (方式2)
### 注意3.9版本的默认的guest用户需要 tls 认证 这里使用自定义的用户名密码登录
```shell
docker run --name rabbitmq -p 5672:5672 -p 15672:15672 \
-e RABBITMQ_DEFAULT_USER=rabbitmq -e RABBITMQ_DEFAULT_PASS=123456 \
-d rabbitmq:3.12-management
```