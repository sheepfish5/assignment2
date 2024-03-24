# 分布式计算第二次作业

## 要求

客户端与服务器端利用RPC机制进行协作，服务器至少暴露如下RPC接口：

~~~Java
/* 1. 添加一条学生信息 */
bool add(Student stu) 

/* 2. 查询指定ID号的学生信息 */
Student queryByID(int stuID)

/* 3. 按姓名查询符合条件的学生信息 */
Students queryByName(String name)

/* 4. 删除指定ID号的学生信息 */
bool delete(int stuID)
~~~

## 实现计划

使用MySQL作为DBMS，用Go做后端，不用ORM框架，用较为原生的database/sql包访问数据库，使用Java写客户端。客户端用gRPC调用后端。
