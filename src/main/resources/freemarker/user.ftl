user.ftl

=======================================================
list對象中包括引用對象
<#list userList as user>
  用户名：${user.userName}
  密  码：${user.userPassword}
  年  龄: ${user.age}
    <#list user.teachers as item>
       老师姓名:${item.userName}
    </#list> 
 
</#list>