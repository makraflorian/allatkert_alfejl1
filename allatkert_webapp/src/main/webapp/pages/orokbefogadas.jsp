<%--
  Created by IntelliJ IDEA.
  User: Flórián
  Date: 2021. 04. 18.
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="common/header.jsp"/>
    <link rel="stylesheet" href="../css/style.css">
    <script src="../js/script.js"></script>
    <title>Örökbefogadás</title>
</head>
<body>
<jsp:include page="/OrokbefogadasController"/>

<%--<%--%>
<%--    System.out.println(request.getParameter("allat_id"));--%>
<%--    System.out.println(request.getParameter("allat_name"));--%>
<%--%>--%>
<div class="container white_bg">
    <h4 style="color: brown">A mezők kitöltése kötelező!</h4>
<form action="../OrokbefogadasController" method="post">

    <div class="form-group">
        <label for="email">E-mail</label>
        <input required name="email" type="email" class="form-control" id="email"/>
    </div>

    <div class="form-group">
        <label for="n_name">Állat neve</label>
        <p class="note">Ezt a mezőt csak akkor töltheted ki, ha az örökbefogadandó állatot még nem nevezték el.</p>
        <input id="n_name" name="n_name" type="text" class="form-control" ${param.allat_name ne 'null' && param.allat_name ne '' ? 'disabled' : 'required' }/>
    </div>

    <div class="form-group">
        <label>Támogatás típusa</label><br>
        <input type="radio" id="penz" name="tipus" value="Pénzösszeg" required>
        <label for="penz">Pénzösszeg</label><br>
        <input type="radio" id="eledel" name="tipus" value="Állateledel" required>
        <label for="eledel">Állateledel</label><br>

    </div>

    <div class="form-group">
        <label for="mennyiseg">Mennyiség</label>
        <p class="note"><span id="penz_radio">Pénzösszeg esetén Ft-ban</span>, <span id="eledel_radio">Állateledel esetén kg-ban értetődő.</span></p>
        <input required id="mennyiseg" name="mennyiseg" type="number" class="form-control" />
    </div>
    <div class="form-group">
        <label for="gyakorisag">Gyakoriság</label>
        <p class="note">Pl: Havonta, Hetente, Évente, stb...</p>
        <input required id="gyakorisag" name="gyakorisag" type="text" class="form-control"/>
    </div>
    <button id="submit" type="submit" class="btn btn-primary">Örökbefogad</button>
</form>
</div>

</body>
</html>
