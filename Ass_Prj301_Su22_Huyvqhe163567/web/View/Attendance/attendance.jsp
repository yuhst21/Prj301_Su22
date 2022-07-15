<%-- 
    Document   : attendance
    Created on : Jun 29, 2022, 10:40:14 PM
    Author     : win
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <form action="attend" method="POST">
            <input type="hidden" value="${session.sessionid}" name="sessionID" />
            <p>Attendance for <b>${session.group.sub.subjectname}</b> with lecturer <b>${session.taker.lname}</b> 
                at ${session.slot.slotname}</p>
            <table border="2">
                <tr>
                    <td>No</td>
                    <td>Group</td>
                    <td>Student Code</td>
                    <td>Student Name</td>
                    <td>Image</td>    
                    <td>Status</td>
                    <td>Comment</td>
                </tr>
                <c:forEach var="stu" items="${requestScope.students}">
                    <tr>

                        <td>${students.indexOf(stu)+1} </td>

                        <td> ${stu.group.get(0).gname}</td>
                        <td>
                            ${stu.scode}
                            <input type="hidden"name="${stu.sid}" value="${stu.sid}">
                        </td>
                        <td>
                            ${stu.sname}
                            <input type="hidden"name="'studentName'+'${stu.sid}'" value="${stu.sname}">                        
                        </td>                         
                        <td>
                            <img src="img/empty-avatar.jpg" alt=""/>
                        </td>         
                        <td>                                         
                            <input type="radio" name="status${stu.sid}" value="false"
                                   <c:if test="${!stu.stuAttend(session)}">
                                       checked="checked"
                                   </c:if>
                                   /> absent
                            <input type="radio" name="status${stu.sid}" value="true"
                                   <c:if test="${stu.stuAttend(session)}">
                                       checked="checked"
                                   </c:if>   
                                   /> present   
                            <input type="hidden" name="component" value="${stu.sid}"/>
                        </td>
                        <td><input type="text" name="comment${e.student.id}"></td>
                    </tr>
                </c:forEach>
            </table> </br>
            <input type="submit" value="Save" />


        </form>
    </body>
</html>
