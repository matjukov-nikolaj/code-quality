<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="get">
    <input type="submit" value="Go back">
</form>
<%
    String message = request.getAttribute("message").toString();
    if (!message.equals("")) {
        out.print("<p>" + message + "</p>");
    } else {
        List<Double> result = (List<Double>) request.getAttribute("result");
        for (int i = 0; i < result.size(); ++i) {
            Double item = result.get(i);
            Integer Index = i + 1;
            out.print("<p>" + "Root " + Index + " is " + item + "</p>");
        }
    }
%>
</body>
</html>
