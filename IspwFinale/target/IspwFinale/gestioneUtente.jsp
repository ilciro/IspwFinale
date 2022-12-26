<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang=it>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css//gestioneUtente.css">
<title>management page</title>
</head>
<body>

<h1> Benvenuto nella pagina della gestione per utente</h1>

<h2> Scegliere cosa fare... Per modificare/eliminare inserire id utente corrispondente</h2>

<form action="GestioneUtenteServlet" method="post">
<div>

<label for="userTextArea"></label>
<textarea rows="10" cols="100" disabled>



<c:forEach items="#{beanUB.listaUtenti }" var="lista">
${lista }



</c:forEach>


</textarea>

<br>
<br>

<input type="submit" id="buttonGenera" name="buttonGenera" value="genera lista" class="genera">
<br>
<br>
<input type="submit" id="buttonAdd" name="buttonAdd" value="inserisci" class="aggiungi">

<p>
Inserire ID:
<input type="text" name="idL" id="idL">

<input type="submit" id="buttonMod" name="buttonMod" value="modifica" class="modifica">


<input type="submit" id="buttonCanc" name="buttonCanc" value="cancella" class="elimina">
</p>
<input type="submit" id="buttonI" name="buttonI" value="indietro" class="indietro">

</div>
</form>
</body>
</html>