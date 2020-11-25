## Quick Start

This topic is about how to set up and run Nacos locally.

**Step 1: Download the binary package**

Download the package from the [latest stable release](https://github.com/alibaba/nacos/releases). Recommended version is [1.3.1](https://github.com/alibaba/nacos/releases/tag/1.3.1).

Take release nacos-server-1.3.1.zip as example:

```
unzip nacos-server-1.3.1.zip
cd nacos/bin
```

**Step 2: Start Server**

*Linux/Unix/Mac*

Run the following command to start(standalone means non-cluster mode):

```
sh startup.sh -m standalone
```

*Windows*

Run the following command to start(standalone means non-cluster mode), alternatively double click on `startup.cmd:`

```
cmd startup.cmd -m standalone
```

Step 3: Open on web browser

Go to [127.0.0.1:8848/nacos](127.0.0.1:8848/nacos). 

Default username and password is `nacos`.

**Step 3: Shutdown Server**

*Linux/Unix/Mac*

```
sh shutdown.sh
```

*Windows*

click on `shutdown.cmd` or run the following command:

```
cmd shutdown.cmd 
```



## Reference

[Nacos Quick Start/Download](https://nacos.io/en-us/docs/quick-start.html)

[Nacos Quick Start for Spring Cloud Projects](https://nacos.io/en-us/docs/quick-start-spring-cloud.html)

