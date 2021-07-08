<%--
  Created by IntelliJ IDEA.
  User: Flórián
  Date: 2021. 04. 17.
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Állatok</title>
    <link rel="stylesheet" href="../css/style.css">
    <jsp:include page="common/header.jsp"/>

</head>
<body>
    <jsp:include page="/AllatokController"/>
<h1 onclick="window.location='/allatkert_webapp_war/index.jsp'" class="cimsor">Állatkert</h1>
    <jsp:include page="common/error.jsp"/>
    <div class="container white_bg">
        <table class="table">
            <thead>
            <tr>
                <th class="allatok_cella"></th>
                <th class="allatok_cella">Név</th>
                <th class="allatok_cella">Faj</th>
                <th class="allatok_cella">Kép</th>
                <th class="allatok_cella">Bemutatkozó szöveg</th>
                <th class="allatok_cella">Születési év</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <h3>Örökbefogadásra váró állataink:</h3>
<%--            szures elnevezhetoseg alapjan--%>
    <div class="row">
            <button type="button" class="btn btn-primary" onclick="window.location='/allatkert_webapp_war/pages/allatok.jsp'" style="margin-right: 10px">Összes állat</button>
            <button type="button" class="btn btn-primary" onclick="window.location='/allatkert_webapp_war/pages/allatok.jsp?no_name=1'">Elnevezhető állatok</button>
    </div>
<%--            osszes allat--%>
<c:if test="${param.no_name ne 1}">
            <c:forEach var="item" items="${requestScope.allatList}">
            <form action="OrokbefogadasController" method="post">
                <tr>
                    <td><label for="id"></label>
                        <input disabled name="id" type="hidden" class="form-control" id="id" value="${item.id}"/></td>
                    <td class="allatok_cella">${item.name}</td>
                    <td class="allatok_cella">${item.faj}</td>
                    <td class="allatok_cella"><img src="data:image/png;base64,${item.pic}" height="200"></td>
                    <td class="allatok_cella">${item.bemutat}</td>
                    <td class="allatok_cella">${item.dateOfBirth}</td>
                    <td class="allatok_cella">
<%--                        URL-ben elkudljuk az id-t meg a nevet--%>
                        <button type="button" class="btn btn-primary" onclick="window.location='/allatkert_webapp_war/pages/orokbefogadas.jsp?allat_id=${item.id}&allat_name=${item.name}'">Örökbefogadás</button>
                    </td>
                </tr>
            </form>
            </c:forEach>
</c:if>
<%--            elnevezheto allatok ha url-ben van no_name=1--%>
            <c:if test="${param.no_name eq 1}">
                <c:forEach var="item" items="${requestScope.noNameAllatList}">
                    <form action="OrokbefogadasController" method="post">
                        <tr>
                            <td><label for="id"></label>
                                <input disabled name="id" type="hidden" class="form-control" id="id" value="${item.id}"/></td>
                            <td class="allatok_cella">${item.name}</td>
                            <td class="allatok_cella">${item.faj}</td>
                            <td class="allatok_cella"><img src="data:image/png;base64,${item.pic}" height="200"></td>
                            <td class="allatok_cella">${item.bemutat}</td>
                            <td class="allatok_cella">${item.dateOfBirth}</td>
                            <td onclick="valami(${item.id})" class="allatok_cella">
                                    <%--                        URL-ben elkudljuk az id-t meg a nevet--%>
                                <button type="button" class="btn btn-primary" onclick="window.location='/allatkert_webapp_war/pages/orokbefogadas.jsp?allat_id=${item.id}&allat_name=${item.name}'">Örökbefogadás</button>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>

</body>
</html>
