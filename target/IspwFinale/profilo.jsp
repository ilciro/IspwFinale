<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang=it>
<head>
<link rel="stylesheet" href="css//profilo.css">
<meta charset="UTF-8">
<title>Profilo page</title>
</head>
<body>

<h1>Benvenuto nella schermata del profilo</h1>

<h2> Scegliere cosa fare</h2>

<form action="ProfiloServlet" method="post">
<div>
nome utente:
<input type="text" name="nomeL" id="nomeL" value="${beanUb.getNome()}">
<br>
<br>
cognome utente:
<input type="text" id="cognomeL" name="cognomeL" value="${beanUb.getCognome() }">
<br>
<br>
email utente:
<input type="text" id="emailL" name="emaiL" value="${beanUb.getEmail() }">
<br>
<br>
data nascita:
<input type="text" id="dataL" name="dataL" value="${beanUb.getDataDiNascita() }">
<br>
<br>

<table>
<caption>
lista pagamenti 
</caption>
<tr>
<th>
id operazione
</th>
<th>
metodo acquisto
</th>
<th>
esito
</th>
<th>
nome utente
</th>
<th>
spesa totale
</th>
<th>
tipo acquisto
</th>
<th>
id prodotto
</th>
</tr>

<c:forEach items="#{beanP.listaPagamenti }" var="lista">


<tr>


<td>${ lista.getId() }</td>
<td>${ lista.getMetodo() }</td>
<td>${ lista.esito() }</td>
<td>${ lista.getNomeUtente() }</td>
<td>${ lista.getAmmontare() }</td>
<td>${ lista.getTipo() }</td>
<td>${ lista.getIdOggetto()}</td>

</tr>
</c:forEach>

</table>

<input type="submit" name="prendiDatiB" id="prendiDatiB" value="prendi dati" class="prendiDati">
<input type="submit" name="modificaB" id="modificaB" value="modifica" class="modifica">
<input type="submit" name="ordiniB" id="ordiniB" value="cronologia ordini" class="ordini">
<input type="submit" name="cancellaB" id="cancellaB" value="cancella" class="cancella">
<input type="submit" name="indietroB" id="indietroB" value="indietro" class="annulla">

<p class="exception">
${beanUb.getMex() }
</p>
</div>
</form>

</body>
</html>