<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<html>

<head>
    <jsp:include page="/pages/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
    <title>Főoldal</title>
</head>

<body>
<div class="container">
<div style="text-align: center">
<h1>Üdvözöljük állatkertünkben!</h1>
</div>
<br>
<p>Állatkertünkben mindenféle állatot örekbe lehet fogadni.</p>

<p>Ezzel a virtuális örökbefogadással lehetősége van pénzzel, illetve állateledel adománnyal támogatni és segíteni az állatainkat.</p>
<p>Állatkertünk állatainak sokat jelent segítsége, adománya, és nekünk is sokat tud segíteni a gondozásukban.</p>
<p>Az örökbefogadható állatok böngészéséhez nyomja meg az alábbi gombot.</p>
<button type="button" class="btn btn-primary" onclick="window.location='/allatkert_webapp_war/pages/allatok.jsp'">Örökbefogadható állatok</button>
<p> </p>
<p>Ha még nem vagy nálunk tag bátran regisztrálj.</p>
<button type="button" class="btn btn-primary" onclick="window.location='/allatkert_webapp_war/pages/regiszt.jsp'">Regisztrálok</button>
</div>

</body>

</html>
