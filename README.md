# 分布式计算第二次作业

XDU 2024春 大三下 

## 要求

客户端与服务器端利用RPC机制进行协作，服务器至少暴露如下RPC接口：

~~~Java
/* 1. 添加一条学生信息 */
bool addStudent(Student stu) 

/* 2. 查询指定ID号的学生信息 */
Student queryByID(int stuID)

/* 3. 按姓名查询符合条件的学生信息 */
Students queryByName(String name)

/* 4. 删除指定ID号的学生信息 */
bool deleteByID(int stuID)
~~~

## implement plan

用Go做后端，不用ORM框架，用较为原生的database/sql包访问Mysql，使用Java Swing写客户端。客户端用gRPC调用后端。

## 根据Protocol Buffer生成Go代码

~~~cmd
# compile student_service.proto
protoc --go_out=goserver --go_opt=module=sheepfish5.com/goserver --go-grpc_out=goserver --go-grpc_opt=module=sheepfish5.com/goserver student_service.proto
~~~