# spring-boot-model
### spring boot 新建项目模型，可直接复用。
- 支持 正常/异常处理统一格式返回。
- 支持 mybatis-plus 多数据源动态切换，并与 druid 适配，有具体单元测试用例。
- 支持 mybatis-plus 分页，分页时，current 从 1 开始。
- 支持 spring boot 单元测试。
- 添加 http 请求、图片流 工具类
- 支持日志功能： 添加 logback 日志框架，实现根据动态参数获取日志路径
- 新增 ThreadPoolTaskExecutor 多线程调用，测试用例： 通过继承 CommandLineRunner 接口，来启动多个线程
- spring @Transactional 场景在测 ...
- 添加自定义打包方式

### 复用模板项目，改名等实操记录：

- 修改项目目录名称
- 用idea打开，右键 rename 改名
- 修改 pom 文件的 artifactId 和 name。
- 修改 包名，右键 包 --> Refactor --> Rename --> Rename package，并修改 pom 文件的 groupId 。
- 修改启动类名称：xxxApplication
- 修改 Configurations
![](https://cdn.jsdelivr.net/gh/841809077/blog-img/20200101/20200117172105.png)
- 如果包名之前被修改，则需要修改 @MapperScan({"xx.xx.xx.mapper"}) 的值
- 如果包名之前被修改，则需要修改 mybatis-plus.typeAliasesPackage 的值，比如：xx.xx.xx.entity
- 执行 mvn clean 命令，用于清除 target 缓存。否则可能会报：spring boot org.apache.ibatis.binding.BindingException: Invalid bound statement (not found) 错误。