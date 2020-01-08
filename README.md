# spring-boot-model
spring boot 新建项目模型，可直接复用。
- 支持 正常/异常处理统一格式返回。
- 支持 mybatis-plus 多数据源动态切换，并与 druid 适配，有具体单元测试用例。
- 支持 mybatis-plus 分页，分页时，current 从 1 开始。
- 支持 spring boot 单元测试。
- 添加 http 请求、图片流 工具类
- 支持日志功能： 添加 logback 日志框架，实现根据动态参数获取日志路径
- 新增 ThreadPoolTaskExecutor 多线程调用，测试用例： 通过继承 CommandLineRunner 接口，来启动多个线程
- spring @Transactional 场景在测 ...