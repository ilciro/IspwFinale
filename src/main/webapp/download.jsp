<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    
<!DOCTYPE html>
<html lang=it>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css//download.css">

<title>Download page</title>
</head>
<body>
<h1> Benvenuto nella schermata per il download</h1>
<br>
<br>


<c:set var = "tipo" scope = "session" value = "${bean1.getType() }"/>

<c:choose>
<c:when test="${ tipo=='libro'}">
<form action="DownloadServlet" method="post">
<div>
titolo del libro da scaricare:
<input type="text" id="titoloL" name="titoloL" value="${bean1.getTitolo()}">
</div>
<br>
<div>
<input type="submit" class="invia" id="downloadB" name="downloadB" value="scarica e leggi">
<input type="submit" class="annulla" id="annullaB" name="annullaB" value="annulla">
</div>
</form>
</c:when>
<c:when test="${ tipo=='giornale'}">
<form action="DownloadServletG1" method="post">
<div>
titolo del giornale da scaricare:
<input type="text" id="titoloL" name="titoloL" value="${bean1.getTitolo()}">
</div>
<br>
<div>
<input type="submit" class="invia" id="downloadB" name="downloadB" value="scarica e leggi">
<input type="submit" class="annulla" id="annullaB" name="annullaB" value="annulla">
</div>
</form>
</c:when>
<c:when test="${ tipo=='rivista'}">
<form action="DownloadServletR1" method="post">
<div>
titolo della rivista da scaricare:
<input type="text" id="titoloL" name="titoloL" value="${bean1.getTitolo()}">
</div>
<br>
<div>
<input type="submit" class="invia" id="downloadB" name="downloadB" value="scarica e leggi">
<input type="submit" class="annulla" id="annullaB" name="annullaB" value="annulla">
</div>
</form>
</c:when>
</c:choose>

</body>
</html>