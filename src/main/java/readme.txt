// 将jar包做成本地maven的依赖
1) 安装好maven
2) 在jar包所在的位置执行maven命令
mvn install:install-file -DgroupId=org.jpcl.util -DartifactId=util -Dversion=1.0 -Dpackaging=jar -Dfile=util-1.0-SNAPSHOT.jar
3) 执行成功后 出现BUILD SUCCESS 即成功
4) 取到自己maven的仓库中可以找到该jar包

<dependency>
    <groupId>org.jpcl.util</groupId>
    <artifactId>util</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>