<%@page language="java" import="java.util.*" %>
<html>
<head>
  Test
</head>
<body>

    <%Iterator itr;%>
    <% List data= (List)request.getAttribute("data");
        for (itr=data.iterator(); itr.hasNext(); )
    %>
 </body>
</html>