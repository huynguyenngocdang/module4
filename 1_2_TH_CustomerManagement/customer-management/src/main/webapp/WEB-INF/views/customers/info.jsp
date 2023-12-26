
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customer Details</title>
</head>
<body>
<style>
    table {
        border: 1px solid #000;
    }

    th, td {
        border: 1px dotted #555;
    }
</style>
<table>
    <caption>Customers List</caption>
    <tbody>
        <tr>
            <td>
                ID
            </td>
            <td>
                <c:out value="${customer.id}"/>
            </td>
        </tr>
        <tr>
            <td>
                Name
            </td>
            <td>
                <c:out value="${customer.name}"/>
            </td>
        </tr>
        <tr>
            <td>
                Email
            </td>
            <td>
                <c:out value="${customer.email}"/>
            </td>
        </tr>
        <tr>
            <td>
                Address
            </td>
            <td>
                <c:out value="${customer.address}"/>
            </td>
        </tr>
        <tr>
            <td><a href="/">Return to index</a></td>
            <td></td>

        </tr>

    </tbody>
</table>
</body>
</html>