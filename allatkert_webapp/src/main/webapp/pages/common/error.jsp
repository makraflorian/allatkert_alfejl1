<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container"> <!-- ERROR MSG -->
    <c:if test="${param.error != null}">
        <div style="color: crimson; text-align: center">
            <h4>Hiba történt!</h4>
            <p>Próbálja újra!</p>
        </div>
    </c:if>

    <c:if test="${param.succ != null}">
        <div style="color: crimson; text-align: center">
            <h4>Köszönjük az örökbefogadását!</h4>
            <h5>Az örökbefogadás részleteivel kapcsolatban email-ben keressük.</h5>
            <p>Ha még szeretne, nyugodtan fogadja örökbe egy másik lakónkat!</p>
        </div>
    </c:if>

</div>