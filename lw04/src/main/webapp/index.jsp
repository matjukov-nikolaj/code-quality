<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form id="form" method="post">
    Please enter URL:
    <input type="text" name="url" value="">
    <BR>
    <input type="submit" value="Submit">
</form>

<table>
    <tr><td>
        <%
            for (Map.Entry<String, Integer> entry : ${workingLinks.workingLinks}.entrySet()) {
                System.out.println("Url : " + entry.getKey() + " Value : " + entry.getValue());
            }
        %>
    </td><td>
        <%

        %>
    </td>
</table>

</body>
</html>
