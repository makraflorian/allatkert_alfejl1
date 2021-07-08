<%--
  Created by IntelliJ IDEA.
  User: Flórián
  Date: 2021. 04. 19.
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Regisztráció</title>
    <link rel="stylesheet" href="../css/style.css">
    <script src="../js/script.js"></script>
    <jsp:include page="common/header.jsp"/>
</head>
<body>
<h1 onclick="window.location='/allatkert_webapp_war/index.jsp'" class="cimsor">Állatkert</h1>
<div class="container">
    <form action="../RegisztController" method="post">

        <div class="form-group">
            <label for="name">Név</label>
            <input required id="name" name="name" type="text" class="form-control"/>
        </div>

        <div class="form-group">
            <label for="email">E-mail</label>
            <input required name="email" type="email" class="form-control" id="email"/>
        </div>

        <button id="submit" type="submit" class="btn btn-primary">Regisztráció</button>
    </form>
</div>

</body>
</html>
