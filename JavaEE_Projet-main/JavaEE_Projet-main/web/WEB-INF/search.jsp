<%@page import="models.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    ArrayList<User> list = (ArrayList<User>) request.getAttribute("parameters");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body class="container">
        <h1>User search</h1>
        <p>Il y a <%=list.size()%> utilisateurs correspondants</p>
        <table class="table">
        <%
            for(User item : list) {
                if(item.getID()!=0){
            %>
                <tr>
                    <td><%=item.getID() %></td>
                    <td><%=item.getFirstname() %></td>
                    <td><%=item.getLastname() %></td>
                    <td><%=item.getUsername() %></td>
                </tr>
            <%
                }
            }
            %>
        </table>
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>

