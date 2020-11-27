### Requirements

------

- CentOS (local virtual machine or cloud instance)

- Docker installed

  

### Installation

------

First, switch to root.

```
$ su root
```

#### MySQL Installation

------

Download `mysql` docker image from Docker Hub.

```
$ docker pull mysql:5.7
```

Start a MySQL container.

```
$ docker run -p 3306:3306 --restart always --name mysql \
-v /mydata/mysql/log:/var/log/mysql \
-v /mydata/mysql/data:/var/lib/mysql \
-v /mydata/mysql/conf:/etc/mysql \
-e MYSQL_ROOT_PASSWORD=root \
-d mysql:5.7
```

> Flag explanation:
>
> 1. `-p 3306:3306` : mapping of ports `host:container`
> 2. `--restart always` : container always restart if it stops (Docker daemon restart/reboot of Docker host)
> 3. `-v /mydata/mysql/log:/var/log/mysql` : bind mount directory `host:container` ; create a new directory `/mydata/mysql/log` if it doesn't exist on Docker host yet
> 4. `--name mysql` : `mysql` is the name we want to assign to docker container
> 5. `-e MYSQL_ROOT_PASSWORD=root` : `root` is the password to be set for the MYSQL root user
> 6. `-d mysql:5.7` :`mysql:5.7` is the name of docker image we have downloaded from Docker Hub

Create a new MySQL configuration file.

```
$ vi /mydata/mysql/conf/my.cnf
```

> Note : Make sure /mydata/mysql/conf  directory already exists on Docker host.

Press `i` in vi (visual editor) and add the configuration below.

```
[client]
default-character-set=utf8

[mysql]
default-character-set=utf8

[mysqld]
init_connect='SET collation_connection = utf8_unicode_ci'
init_connect='SET NAMES utf8'
character-set-server=utf8
collation-server=utf8_unicode_ci
skip-character-set-client-handshake
skip-name-resolve
```

Press `esc` and then type `:wq` to save the file.

Restart MySQL container to reflect the changes. We should be able to see `my.cnf` inside the container.

```
$ docker restart mysql
$ docker exec -it mysql /bin/bash
$ cat/etc/mysql/my.cnf

$ exit
```

Exit from MySQL container before proceeding with Redis installation.

#### Redis Installation

------

Download `redis` docker image from Docker Hub register.

```
$ docker pull redis
```

Create a new directory and add a new Redis configuration file.

```
$ mkdir -p /mydata/redis/conf
$ vi /mydata/redis/conf/redis.conf
```

Add the configuration below. This persists Redis data.

```
appendonly yes
```

Start a Redis container.

```
$ docker run -p 6379:6379 --restart always --name redis \
-v /mydata/redis/data:/data \
-v /mydata/redis/conf/redis.conf:/etc/redis/redis.conf \
-d redis redis-server /etc/redis/redis.conf
```

> Note : 
>
> 1. `/mydata/redis/data` directory will be automatically created as it doesn't exist on Docker host yet
> 2. `-v /mydata/redis/conf/redis.conf:/etc/redis/redis.conf` : bind mount `redis.conf` configuration file we have created above

Check the configuration file inside the container.

```
$ docker exec -it redis /bin/bash
$ cat /etc/redis/redis.conf

$ exit
```



### Reference

------

[Install Docker Engine on CentOS](https://docs.docker.com/engine/install/centos/)

[Use bind mounts](https://docs.docker.com/storage/bind-mounts/)

[Use volumes](https://docs.docker.com/storage/volumes/)

[Container restart policy](https://docs.docker.com/config/containers/start-containers-automatically/)

[MySQL - Docker hub](https://hub.docker.com/_/mysql)

[Redis - Docker hub](https://hub.docker.com/_/redis)

[Redis Configuration](https://redis.io/topics/config)

