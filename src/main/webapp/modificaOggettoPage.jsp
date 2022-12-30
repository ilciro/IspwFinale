<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
 <%@ page import= "web.bean.SystemBean" %>
<!DOCTYPE html>
<html lang=it>
<head>
<link rel="stylesheet" href="css//modificaOggetto.css">
<meta charset="UTF-8">
<title>modif object page</title>
</head>
<body>
<h1>Modificare oggetto scelto</h1>

<h2> Scegliere i campi da modificare</h2>


<c:set var = "tipo" scope = "session" value = "${SystemBean.getIstance().getType() }"/>

<form action="ModificaLibroServlet" method="post">

<c:choose>
<c:when test="${ tipo=='libro'}">
<div class="column">
titolo:
<input type="text" id="titoloL" name="titoloL" value="${beanMOB.getMiaLista().get(0).getTitolo() }">
<br>
<br>
numero pagine:
<input type="text" id="pagineL" name="pagineL" value="${beanMOB.getMiaLista().get(0).getNumeroPagine() }">
<br>
<br>
codice isbn:
<input type="text" id="codiceL" name="codiceL" value="${beanMOB.getMiaLista().get(0).getCodIsbn() }">
<br>
<br>
editore:
<input type="text" id="editoreL" name="editoreL" value="${beanMOB.getMiaLista().get(0).getEditore() }">
<br>
<br>
autore:
<input type="text" id="autoreL" name="autoreL" value="${beanMOB.getMiaLista().get(0).getAutore() }">
<br>
<br>
lingua:
<input type="text" id="linguaL" name="linguaL" value="${beanMOB.getMiaLista().get(0).getLingua() }">
<br>
<br>
categoria:
<input type="text" id="categoriaL" name="categoriaL" value="${beanMOB.getMiaLista().get(0).getCategoria() }">
<br>
<br>
data pubblicazione 
<input type="text" id="dataL" name="dataL" value="${beanMOB.getMiaLista().get(0).getDataPubb() }">
<br>
<br>
recensione:
<label for="recL"></label>
<textarea rows="10" cols="50" id="recL" name="recL">
${beanMOB.getMiaLista().get(0).getRecensione() }
</textarea>
<br>
<br>
nr copie rimanenti:
<input type="text" id="copieL" name="copieL" value="${beanMOB.getMiaLista().get(0).getNrCopie() }">
<br>
<br>
descrizione :
<label for="descL"></label>
<textarea rows="10" cols="50" name="descL" id="descL">
${beanMOB.getMiaLista().get(0).getDesc() }</textarea>
<br>
<br>
disponibilita:
<input type="text" id="dispL" name="dispL" value="${beanMOB.getMiaLista().get(0).getDisponibilita() }">
<br>
<br>
prezzo:
<input type="text" id="prezzoL" name="prezzoL" value="${beanMOB.getMiaLista().get(0).getPrezzo() }">
<br>
<br>

<input type="submit" id="listaB" name="listaB" value="prendi dati" class="genera">


</div>
<div class="column">
elenco categorie possibili:
<label for="categoriePossibili"></label>
<textarea rows="10" cols="50" id="categoriePossibili" name="categoriePossibili" >
${beanMOBL.setCategorie()}
</textarea>
<input type="submit" id="buttonCat" name="buttonCat" value="elenco categorie" class="categorie">
</div>
<div class="column">
 titolo da aggiornare:
 <input type="text" id="titoloNL" name="titoloNL">
 <br>
<br>
numero pagine da aggiornare:
<input type="text" id="pagineNL" name="pagineNL" >
<br>
<br>
codice isbn da aggiornare:
<input type="text" id="codiceNL" name="codiceNL" >
<br>
<br>
editore da aggiornare:
<input type="text" id="editoreNL" name="editoreNL" >
<br>
<br>
autore da aggiornare:
<input type="text" id="autoreNL" name="autoreNL" >
<br>
<br>
lingua da aggiornare:
<input type="text" id="linguaNL" name="linguaNL">
<br>
<br>
categoria da aggiornare:
<input type="text" id="categoriaNL" name="categoriaNL" >
<br>
<br>
data pubblicazione da aggiornare (usare il '/');
<input type="text" id="dataNL" name="dataNL">
<br>
<br>
recensione da aggiornare:
<label for="recNL"></label>
<textarea rows="10" cols="50" id="recNL" name="recNL">
</textarea>
<br>
<br>
nr copie rimanenti da aggiornare:
<input type="text" id="copieNL" name="copieNL" >
<br>
<br>
descrizione da aggiornare:
<label for="descNL"></label>
<textarea rows="10" cols="50" name="descNL" id="descNL">
</textarea>
<br>
<br>
disponibilita da aggiornare:
<input type="text" id="dispNL" name="dispNL" >
<br>
<br>
prezzo da aggiornare:
<input type="text" id="prezzoNL" name="prezzoNL" >
<br>
<br>

<input type="submit" id="buttonI" name="buttonI" value="aggiorna" class="invia">
<input type="submit" id="buttonA" name="buttonA" value="indietro" class="annulla">

</div>
</c:when>
</c:choose>
</form>

<form action="ModificaGiornaleServlet" method="post">

<c:choose>
<c:when test="${ tipo=='giornale'}">
<div class="columnG">
<!--  assegnare a mob il gironale -->
titolo:
<input type="text" id="titoloG" name="titoloG" value="${beanMOB.getMiaLista().get(0).getTitolo() }">
<br>
<br>

tipologia:
<input type="text" id="tipoG" name="tipoG" value="QUOTIDIANO">
<br>
<br>
lingua:
<input type="text" id="linguaG" name="linguaG" value="${beanMOB.getMiaLista().get(0).getLingua() }">
<br>
<br>
editore:
<input type="text" id="editoreG" name="editoreG" value="${beanMOB.getMiaLista().get(0).getEditore() }">
<br>
<br>
data pubb (yyyy/mm/dd) :
<input type="text" id="dataG" name="dataG" value="${beanMOB.getMiaLista().get(0).getDataPubb() }">
<br>
<br>
copie rimanenti:
<input type="text" id="copieG" name="copieG" value="${beanMOB.getMiaLista().get(0).getCopieRimanenti() }"> 
<br>
<br>
disponibilita (0->no 1->si):
<input type="text" id="dispG" name="dispG" value="${beanMOB.getMiaLista().get(0).getDisponibilita() }">
<br>
<br>
prezzo:
<input type="text" id="prezzoG" name="prezzoG" value="${beanMOB.getMiaLista().get(0).getPrezzo() }">
<br>
<br>
<input type="submit" id="buttonG" name="buttonG" value="prendi dati" class="genera">

</div>
<div class="columnG">
<!--  roba nuova -->
titolo da modificare:
<input type="text" id="titoloNG" name="titoloNG">
<br>
<br>

tipologia da modificare:
<input type="text" id="tipoG" name="tipoG" value="QUOTIDIANO" disabled>
<br>
<br>
lingua da modificare:
<input type="text" id="linguaNG" name="linguaNG">
<br>
<br>
editore da modificare:
<input type="text" id="editoreNG" name="editoreNG" >
<br>
<br>
data pubb (yyyy/mm/dd) da modificare :
<input type="text" id="dataNG" name="dataNG" >
<br>
<br>
copie rimanenti da modificare:
<input type="text" id="copieNG" name="copieNG" > 
<br>
<br>
disponibilita (0->no 1->si) da modificare:
<input type="text" id="dispNG" name="dispNG" >
<br>
<br>
prezzo da modificare:
<input type="text" id="prezzoNG" name="prezzoNG">
<br>
<br>
<br>
<br>
<input type="submit" id="confermaB" name="confermaB" value="conferma" class="invia">
<input type="submit" id="annullaB" name="annullaB" value="indietro" class="annulla">
</div>
</c:when>
</c:choose>
</form>

<form action="ModificaRivistaServlet" method="post">
<c:choose>
<c:when test="${ tipo=='rivista'}">
<div class="column">
titolo:
<input type="text" id="titoloR" name="titoloR" value="${beanMOB.getMiaLista().get(0).getTitolo() }">
<br>
<br>
categoria:
<input type="text" id="categoriaR" name="categoriaR" value="${beanMOB.getMiaLista().get(0).getTipologia() }">
<br>
<br>
autore:
<input type="text" id="autoreR" name="autoreR" value="${beanMOB.getMiaLista().get(0).getAutore() }">
<br>
<br>
lingua:
<input type="text" id="linguaR" name="linguaR" value="${beanMOB.getMiaLista().get(0).getLingua() }">
<br>
<br>
editore:
<input type="text" id="editoreR" name="editoreR" value="${beanMOB.getMiaLista().get(0).getEditore() }">
<br>
<br>
descrizione:
<label for="descR"></label>
<textarea rows="10" cols="50">
${beanMOB.getMiaLista().get(0).getDescrizione() }
</textarea>
<br>
<br>
data pubblicazione:
<input type="text" id="dataR" name="dataR" value="${beanMOB.getMiaLista().get(0).getDataPubb() }">
<br>
<br>
disponibilita:
<input type="text" id="dispL" name="dispL" value="${beanMOB.getMiaLista().get(0).getDisp() }">
<br>
<br>
prezzo:
<input type="text" id="prezzoL" name="prezzoL" value="${beanMOB.getMiaLista().get(0).getPrezzo() }">
<br>
<br>
copieRimanenti :
<input type="text" id="copieR" name="copieR" value="${beanMOB.getMiaLista().get(0).getCopieRim() }">
<br>
<br>

<input type="submit" id="listaB" name="listaB" value="prendi dati" class="genera">


</div>
<div class="column">
elenco categorie possibili:
<label for="categoriePossibili"></label>
<textarea rows="10" cols="50" id="categoriePossibili" name="categoriePossibili" >
${beanMOBR.elencoCategorie()}
</textarea>
</div>
<div class="column">

titolo da aggiornare:
<input type="text" id="titoloNR" name="titoloNR">
<br>
<br>
categoria  da aggiornare:
<input type="text" id="categoriaNR" name="categoriaNR" >
<br>
<br>
autore da aggiornare:
<input type="text" id="autoreNR" name="autoreNR" >
<br>
<br>
lingua da aggiornare:
<input type="text" id="linguaNR" name="linguaNR" >
<br>
<br>
editore da aggiornare:
<input type="text" id="editoreNR" name="editoreNR" >
<br>
<br>
descrizione da aggiornare:
<label for="descNR"></label>
<textarea rows="10" cols="50" id="descNR" name="descNR">
</textarea>
<br>
<br>
data pubblicazione (yyyy/mm/dd):
<input type="text" id="dataNR" name="dataNR" >
<br>
<br>
disponibilita da aggiornare:
<input type="text" id="dispNR" name="dispNR">
<br>
<br>
prezzo da aggiornare:
<input type="text" id="prezzoNR" name="prezzoNR" >
<br>
<br>
copieRimanenti da aggiornare :
<input type="text" id="copieNR" name="copieNR" >
<br>
<br>

<input type="submit" id="buttonI" name="buttonI" value="aggiorna" class="invia">
<input type="submit" id="buttonA" name="buttonA" value="indietro" class="annulla">

</div>



</c:when>
</c:choose>
</form>


</body>
</html>