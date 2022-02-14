<%-- 
    Document   : bonus
    Created on : 20 oct. 2021, 17:19:03
    Author     : sherm
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bonus Calculation Page</title>
    </head>
    <body>
        <h1>Bonus Calculation</h1><br>
        <br>
        <c:forEach items="${requete}" var="index">
            <h3>Social security number: ${! (0 == index.numssn) ? index.numssn: "Not specified"}</h3>
            <h3>Multiplier: ${! (0 == index.multiple) ? index.multiple : "Not specified"}</h3>
            <h3>Bonus: ${! (0 == index.bonus) ? index.bonus : "Multiplier not specified"}</h3><br>
            <br>
        </c:forEach>
    </body>
</html>
