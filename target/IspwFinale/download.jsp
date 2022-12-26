<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<form action="DownloadServlet" method="post">
<div>
titolo del libro da scaricare:
<input type="text" id="titoloL" name="titoloL" value="${SystemBean.getIstance().getTitolo()}">
</div>
<br>
<div>
<input type="submit" class="invia" id="downloadB" name="downloadB" value="scarica e leggi">
<input type="submit" class="annulla" id="annullaB" name="annullaB" value="annulla">
</div>
</form>
</body>
</html>