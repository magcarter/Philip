<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import url="/Include/header.jsp">
    <c:param name="title" value="Home" />
</c:import>
<section class="sectionClass sectionBackGround">
    <h1>Java Exception</h1>
    <p>An Exception was thrown by the application.</p>
    
    <h3>Exception Type:</h3>
    <p>${pageContext.exception["class"]}</p>
    
    <h3>Exception Message:</h3>
    <p>${pageContext.exception.message}</p>
    
    <h3>Stack Trace:</h3>
    <c:forEach var="trace" items="${pageContext.exception.stackTrace}">
    <p>${trace}</p>
    </c:forEach >
    
</section>

<c:import url="/Include/footer.jsp">
    <c:param name="page" value="index.jsp" />
</c:import>