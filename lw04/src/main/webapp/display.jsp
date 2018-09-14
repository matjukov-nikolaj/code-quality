<%@page import="java.util.Map" %>
<%@page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Map<String, Integer> workingLinks = (Map<String, Integer>) request.getAttribute("workingLinks");
    Map<String, Integer> brokenLinks = (Map<String, Integer>) request.getAttribute("brokenLinks");
    String time = request.getAttribute("time").toString();
%>
<%!
    private void PrintLinks(Map<String, Integer> links, JspWriter out)
    {
        try {
            for (String url : links.keySet()) {
                String key = url.toString();
                String value = links.get(url).toString();
                out.print("<p>" + "Code: " + value);
                out.print("<a href=" + key + ">" + "   " + key + "</a>");
                out.print("</p>");
                out.print("<br/>");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
%>
<form method="get">
    <input type="submit" value="Go back">
</form>
<%
    out.print("<p>" + "Program execution time: " + time + "s" + "</p>");
%>
<table width="100%" cellspacing="0" cellpadding="5">
    <tr>
        <td width="50%" valign="top">
            <%
                out.print("<p>Number of working Links: " + workingLinks.size() + "</p>");
                PrintLinks(workingLinks, out);
            %>
        </td>
        <td width="50%" valign="top">
            <%
                out.print("<p>Number of broken Links: " + brokenLinks.size() + "</p>");
                PrintLinks(brokenLinks, out);
            %>
        </td>
    </tr>
</table>
</body>
</html>
