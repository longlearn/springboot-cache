# springboot-cache
SpringBoot高级应用--缓存
## 一. Java Caching JSR107介绍
JSR是Java Specification Requests 的缩写 ，Java规范请求，故名思议提交Java规范，大家一同遵守这个规范的话，会让大家‘沟通’起来更加轻松， JSR-107呢就是关于如何使用缓存的规范。
* 1.JRS-107有什么？
    + 向电脑申请一块空间作为缓存;
    + 为缓存定义你自己的数据结构;
    + 向缓存中写数据;
    + 向缓存中读数据;
    + 不再使用缓存时，清空你锁申请的内存空间. 
    -------------------
    > 大概这么多吧，当然里面还有很多细节性的东西，比如过期设置呀，分布式设置呀，是不是要持久化呀，是不是要支持事务呀，要不要加锁呀.......       
    > JSR-107呢就是对缓存常用的操作做了一个抽象，然后给出一个API接口，不同的缓存产品只要实现了这些接口就可以了。使用缓存的用户能也只要调用这些接口就能得到不同产品的缓存服务，而不用悲催的来来回回学习不同缓存的API，更加悲催的是API还没有看明白，某技术就已经黄了。
    -----------------
* 2.Java Caching定义了5个核心接口:`CacheProvider`，`CacheManager`，`Cache`，`Entry`（记录），
`Expire` [ɪkˈspaɪəri]（过期时间）
    + CachingProvider: CachingProvider定义了创建、配置、获取、管理和控制多个CacheManager。一个应用可
以在运行期访问多个CachingProvider。
    + CacheManager: CacheManager定义了创建、配置、获取、管理和控制多个唯一命名的Cache，这些Cache
存在于CacheManager的上下文中。一个CacheManager仅被一个CachingProvider所拥有。
    + Cache: Cache是一个类似Map的数据结构并临时存储以Key为索引的值。一个Cache仅被一个
CacheManager所拥有。
    + Entry： Entry是一个存储在Cache中的key-value对。
    + Expiry: Expiry 每一个存储在Cache中的条目有一个定义的有效期。一旦超过这个时间，条目为过期
的状态。一旦过期，条目将不可访问、更新和删除。缓存有效期可以通过ExpiryPolicy设置。
* ![JavaCache](https://github.com/longlearn/springboot-cache/blob/master/imgs/javaCache1.png)
-------------------
## 二. Spring缓存
* 1.Spring缓存抽象  
    Spring从3.1开始定义了org.springframework.cache.Cache
    和org.springframework.cache.CacheManager接口来统一不同的缓存技术；
    并支持使用JCache（JSR-107）注解简化我们开发；
    + Cache接口为缓存的组件规范定义，包含缓存的各种操作集合；
    + Cache接口下Spring提供了各种xxxCache的实现；如RedisCache，EhCacheCache , 
ConcurrentMapCache等；
    + 每次调用需要缓存功能的方法时，Spring会检查检查指定参数的指定的目标方法是否
已经被调用过；如果有就直接从缓存中获取方法调用后的结果，如果没有就调用方法
并缓存结果后返回给用户。下次调用直接从缓存中获取。
    + 使用Spring缓存抽象时我们需要关注以下两点；
        1. 确定方法需要被缓存以及他们的缓存策略
        2. 从缓存中读取之前缓存存储的数据
    + ![springCache1](https://github.com/longlearn/springboot-cache/blob/master/imgs/springCache1.png)
* 2.几个重要概念&缓存注解
    + `Cache` : 缓存接口，定义缓存操作。实现有：`RedisCache`、`EhCacheCache`、
`ConcurrentMapCache`等
    + `CacheManager` : 缓存管理器，管理各种缓存（Cache）组件
    + `@Cacheable` : 主要针对方法配置，能够根据方法的请求参数对其结果进行缓存
    + `@CacheEvict` : 清空缓存
    + `@CachePut` : 保证方法被调用，又希望结果被缓存
    + `@EnableCaching` : 开启基于注解的缓存
    + `keyGenerator` : 缓存数据时key生成策略
    + `serialize` : 缓存数据时value序列化策略
* 3.`@Cacheable`,`@CachePut`,`@CacheEvict` 主要参数
    + ![springCache2](https://github.com/longlearn/springboot-cache/blob/master/imgs/springCache2.png)
    + ![springCache3](https://github.com/longlearn/springboot-cache/blob/master/imgs/springCache3.png)
---------------
## 三. SpringBoot缓存使用
* 1.搭建环境  
    `pom.xml` 文件要加入Cache缓存的组件
    ``` xml
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.1</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <!-- 缓存组件 -->
        <dependency>
            <groupId>javax.cache</groupId>
            <artifactId>cache-api</artifactId>

        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.37</version>
        </dependency>
    </dependencies>
    ```
* 2.@EnableCaching开启缓存  
    ![springbootCache1](https://github.com/longlearn/springboot-cache/blob/master/imgs/springbootCache1.png)
    

    
