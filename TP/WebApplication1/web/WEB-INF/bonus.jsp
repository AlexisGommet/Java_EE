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
        <title>Bonus Calculation</title>
    </head>
    <body>
        <h1>Bonus Calculation</h1>
        <h3>Social security number: ${! (0 == requete.number) ? requete.number : "Not specified"}</h3>
        <h3>Multiplier: ${! (0 == requete.multiple) ? requete.multiple : "Not specified"}</h3>
        <h3>Bonus: ${! (0 == requete.multiple) ? requete.bonus : "Multiplier not specified"}</h3>
    </body>
</html>
