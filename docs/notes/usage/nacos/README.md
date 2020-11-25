# Nacos

Nacos is an easy-to-use dynamic service discovery, configuration and service management platform for building cloud native applications. Examples below illustrates how to use Nacos for configuration management and service discovery. 

> Note: All the examples below show you how to use Nacos in the context of this project. You can follow along with the source code open. Make sure Nacos is installed.

## Nacos Config Example

**Basic**

1. Add dependecy using spring-cloud-starter-alibaba-nacos-config in the pom.xml file in your Spring Cloud project (already configured in `pom.xml` file in `mall-common` module).

   ```
   <dependency>
   	<groupId>com.alibaba.cloud</groupId>
       <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
   </dependency>
   ```

2. Add Nacos config metadata configuration to file `/src/main/resources/bootstrap.properties`. Taking `mall-coupon/src/main/resources/bootstrap.properties` as example:

   ```
   spring.application.name=mall-coupon
   spring.cloud.nacos.config.server-addr=127.0.0.1:8848
   ```

3. The application will get the configuration from Nacos Server and put it in the Spring Environment's PropertySources. We use the @Value annotation to inject the configuration into the name and age fields, and add @RefreshScope annotation to enable dynamic refresh.

   > Note : Configuration in Nacos server has priority over the configuration in `/src/main/resources/application.properties`.

   ```
   @RefreshScope
   @RestController
   @RequestMapping("coupon/coupon")
   public class CouponController{
   	
   	@Value("${coupon.user.name}")
   	private String name;
   	@Value("${coupon.user.age}")
   	private Integer age;
   	
   	@RequestMapping("/test")
   	public R test(){
   		return R.ok().put("name", name).put("age", age);
   	}
   }
   ```

   

4. Go to [127.0.0.1:8848/nacos](http://127.0.0.1:8848/nacos). Default username and password is `nacos`. Create configuration in Nacos server. Configuration can also be done with command line. More example see [here](https://github.com/alibaba/spring-cloud-alibaba/tree/master/spring-cloud-alibaba-examples/nacos-example/nacos-config-example).

   > Note : Data ID field must be in the format `${spring.application.name}.properties` 
   >
   > In this case, it is `mall-coupon.properties` as we configure `${spring.application.name}` in `/src/main/resources/bootstrap.properties`

   ![Add Config](https://user-images.githubusercontent.com/22387966/100243330-b38ae900-2f70-11eb-9632-622a6b4b59da.png)

   ![Add Config 2](https://user-images.githubusercontent.com/22387966/100244256-ae7a6980-2f71-11eb-848c-5e82960389e8.png)

5. Go to [localhost:7000/coupon/coupon/test](http://localhost:7000/coupon/coupon/test) (server port was configured at 7000 in application.yml file). We should be able to see the following in the browser.

   ```
   {"msg":"success","code":0,"name":"Alex","age":25}
   ```

   

6. Change the configuration in Nacos server by clicking edit button in Operation column. Refresh the REST endpoint. We should be able to see the latest data from Nacos server.

   

**Namespace and Group Concept**



## Nacos Discovery Example