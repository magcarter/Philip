<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import url="/Include/header.jsp">
    <c:param name="title" value="404_error" />
</c:import>
<section class="sectionClass sectionBackGround">
    <h1>Error 404</h1>
    <p>The page you were looking for cannot be found.</p>
</section>

<c:import url="/Include/footer.jsp">
    <c:param name="page" value="404_error.jsp" />
</c:import>
   
