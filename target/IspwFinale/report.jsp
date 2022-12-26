<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang=en-it>
<head>
<meta charset="ISO-8859-1">
<title>report page</title>
</head>
<link href="css//report.css" rel="stylesheet" type="text/css">
<body>

<h1> Scegliere una tra le seguenti checkbox</h1>

<form action="ReportServlet" method="post">
<div>
<label for="totaleC">
totale
</label>
<input type="checkbox" id="totaleC" name="totaleC" value="totale">

<label for="totaleL">
libri
</label>
<input type="checkbox" id="totaleL" name="totaleL" value="libri">

<label for="totaleG">
giornale
</label>
<input type="checkbox" id="totaleG" name="totaleG" value="giornale">

<label for="totaleR">
rivista
</label>
<input type="checkbox" id="totaleR" name="totaleR" value="rivista">

<label for="totaleRacc">
raccolta
</label>
<input type="checkbox" id="totaleRacc" name="totaleRacc" value="raccolta">

<input type="submit" id="scelta" name="scelta" class="invia">

<input type="submit" id="buttonI" name="buttonI" value="indietro" class="annulla">


<br>
<br>

<label for="tArea"></label>
<textarea rows="100" cols="145" name="tArea" id="tArea" placeholder="tArea" class="tArea">
${bean.getS() }
</textarea>

</div>


</form>
</body>
</html>