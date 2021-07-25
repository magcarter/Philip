<%-- 
    Document   : header
    Created on : Sep 24, 2020, 5:17:49 PM
    Author     : Maggie
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">  
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>  
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>  
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
        <link href="<c:url value='/styles/main.css'/>" rel="stylesheet" type="text/css"/>

        <title>${param.title}</title>
    </head>
    <body>
        <header>
            <!--<h1 id="headerText">Philip Kiser</h1>-->
            <nav><a href="<c:url value='/Customer?actionRequest=home'/>">Home</a>
                <a href="<c:url value='/Customer?actionRequest=research'/>">Research</a>
                <a href="<c:url value='/Customer?actionRequest=people'/>">People</a>
                <a href="<c:url value='/Customer?actionRequest=teaching'/>">Teaching</a>
                <a href="<c:url value='/Customer?actionRequest=publications'/>">Publications</a></nav>
        </header>       
