# springboot-cache
SpringBoot高级应用--缓存
## 一.Java Caching JSR107介绍
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
