## spring-boot-model
更多 spring boot 实战知识点可参考：[━Start。平常心_](https://841809077.github.io/categories/springboot)

### 复用模板项目，改名等实操记录：

由于 mybatis-demo 项目有接口全局异常处理，可以以此项目为模板复用。 
- 修改项目目录名称
- 用 idea 打开，右键 rename 改名
- 修改 pom 文件的 artifactId 和 name。
- 修改 包名，右键 包 --> Refactor --> Rename --> Rename package，并修改 pom 文件的 groupId 。
- 修改启动类名称：xxxApplication
- 修改 Configurations
![](https://cdn.jsdelivr.net/gh/841809077/blog-img/20200101/20200117172105.png)
- 如果包名之前被修改，则需要修改 @MapperScan({"xx.xx.xx.mapper"}) 的值
- 如果包名之前被修改，则需要修改 mybatis-plus.typeAliasesPackage 的值，比如：xx.xx.xx.entity
- 执行 mvn clean 命令，用于清除 target 缓存。否则可能会报：spring boot org.apache.ibatis.binding.BindingException: Invalid bound statement (not found) 错误。