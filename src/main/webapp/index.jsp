<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>

<fmt:setLocale value="${param.lang}" />
<fmt:setBundle basename="/localization/message" />

<html lang="${param.lang}">
<head>
    <title>Developer CRUD REST App</title>
<body>

<ul>
    <li><a href="?lang=en"><fmt:message key="label.lang.en" /></a></li>
    <li><a href="?lang=ru"><fmt:message key="label.lang.ru" /></a></li>
</ul>

<h1>
    <fmt:message key="welcome.message" />
</h1>
<p><fmt:message key="intro.message" /></p>
<p><fmt:message key="basicEntity.message" /></p>
<p><fmt:message key="classDiagram.message" /></p>
<p><img src="img/ClassDiagram.jpg" alt="class diagram"></p>
<br>
<p><fmt:message key="usability.message" /></p>
<p><fmt:message key="layers.message" /></p>
<ul>
    <li><fmt:message key="model.message" /></li>
    <li><fmt:message key="rest.message" /></li>
    <li><fmt:message key="service.message" /></li>
    <li><fmt:message key="repo.message" /></li>
    <li><fmt:message key="storage.message" /></li>
</ul>
<br>
<p><fmt:message key="chain.message" /></p>
<p><img src="img/SequenceDiagram.jpg" alt="sequence diagram for POST request"></p>
<br>
<p><fmt:message key="repositoryDescription.message" /></p>
<br>
<p><fmt:message key="endpoints.message" /></p>
<ul>
    <li><fmt:message key="skillEndPoint.message" /></li>
    <li><fmt:message key="accountEndPoint.message" /></li>
    <li><fmt:message key="developerEndPoint.message" /></li>
</ul>
<br>
<p><a href="documentation.html"><fmt:message key="swaggerDocLink.message" /></a></p>
</body>
</html>