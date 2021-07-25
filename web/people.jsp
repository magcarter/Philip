
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import url="/Include/header.jsp">
    <c:param name="title" value="People" />
</c:import>

<section id="container" class="sectionClass sectionBackGround">
     <div><p>I am the people page</p>
         <div class="peoplePicsDiv">
         <img class="peoplePics" src="images/frizzledCochin.JPG" alt=""/>
         </div>
<!--         <img class="peoplePics" src="images/captain.JPG" alt=""/>
         <img class="peoplePics" src="images/silkie chick.JPG" alt=""/>-->
     </div>
</section>

<c:import url="/Include/footer.jsp">
</c:import>