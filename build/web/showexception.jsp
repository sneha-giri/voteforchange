<%-- 
    Document   : showexception
    Created on : 10 May, 2021, 10:20:25 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Exception ex=(Exception)request.getAttribute("Exception");
    System.out.println("Exception is"+ex);
    out.println("Some Exception occured"+ex.getMessage());
    %>