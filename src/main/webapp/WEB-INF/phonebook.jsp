<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Phone book with Spring MVC</title>
    <style>
        div:not(#phoneBook) {
            position: absolute;
            width: 50%;
            height: 50%;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
        }

        #phoneBook {
            height: 100%;
            width: 100%;
            overflow: scroll;
        }

        td {
            border: 1px solid black;
            padding: 2em;
            margin: 0;
        }

        table {
            width: 100%;
        }

        form > input[type=submit] {
            padding-left: 2em;
            padding-right: 2em;
            margin-bottom: 1em;
        }
    </style>
</head>
<body>
<div>
    ${error}
    <form method="POST">
        <input type="submit" name="sort" value="${sorting}"/>
    </form>
    <form method="POST">
        <input type="text" name="searchName" placeholder="Search by name"/>
        or
        <input type="text" name="searchPhone" placeholder="Search by telephone number"/>
        <input type="submit" name="search" value="Search"/>
    </form>
    <div id="phoneBook">
        <table>
            <tr>
                <th>Name</th>
                <th>Phone number</th>
                <th>Address</th>
            </tr>
            <c:forEach items="${phonebookListings}" var="phonebookListing">
                <tr>
                    <td>${phonebookListing.name}</td>
                    <td>${phonebookListing.phoneNumber}</td>
                    <td>${phonebookListing.address}</td>

                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
