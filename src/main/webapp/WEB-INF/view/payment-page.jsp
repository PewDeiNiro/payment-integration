<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <title>Пополнение счета</title>
    </head>
    <body>
        <center>
            <br/><br/><h1><b>Платеж №${id}</b></h1><br/><h2>${status}</h2><br/>
            <h3><b>Данные тестовой карты:</b></h3>
            <p>Номер карты: 5124 5855 6345 6201</p>
            <p>Срок действия: 01/25</p>
            <p>CVV: 123</p><br/><br/>
            <form:form modelAttribute="cardDetails" method="post">
                <b>Номер карты: </b><form:input path="number" title="Номер карты"/><br/>
                <b>Срок действия карты: </b><form:input path="expire" title="Срок действия карты"/><br/>
                <b>CVV: </b><form:input path="cvv" title="cvv"/><br><br/>
                <input type="submit" value="Оплатить"/><br/>
            </form:form>
            <form:form action="cancelInvoice/${id}" method="post">
                <input type="submit" value="Отменить">
            </form:form>
        </center>
    </body>
</html>
