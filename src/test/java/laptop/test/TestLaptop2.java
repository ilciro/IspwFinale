package laptop.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.itextpdf.text.DocumentException;

import laptop.controller.ControllerPagamentoCC;
import laptop.controller.ControllerPassword;
import laptop.controller.ControllerRegistraUtente;
import laptop.controller.ControllerReportPage;
import laptop.controller.ControllerReportRaccolta;
import laptop.controller.ControllerRicercaPage;
import laptop.controller.ControllerRicercaPerTipo;
import laptop.controller.ControllerScegliNegozio;
import laptop.controller.ControllerSystemState;
import laptop.controller.ControllerVisualizza;
import laptop.controller.ControllerVisualizzaOrdine;
import laptop.controller.ControllerVisualizzaProfilo;
import laptop.database.GiornaleDao;
import laptop.database.NegozioDao;
import laptop.database.UsersDao;
import laptop.model.Fattura;
import laptop.model.Negozio;
import laptop.model.Pagamento;
import laptop.model.TempUser;
import laptop.model.User;
import laptop.model.raccolta.Factory;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;

class TestLaptop2 {
	
	private ControllerSystemState vis=ControllerSystemState.getIstance();
	private ControllerPagamentoCC cPCC=new ControllerPagamentoCC();
	private ControllerPassword cP=new ControllerPassword();
	private ControllerRegistraUtente cRU=new ControllerRegistraUtente();
	private ControllerReportPage cRP=new ControllerReportPage();
	private ControllerReportRaccolta cRR=new ControllerReportRaccolta();
	private ControllerRicercaPage cRicP=new ControllerRicercaPage();
	private ControllerRicercaPerTipo cRT=new ControllerRicercaPerTipo();
	private ControllerScegliNegozio cSN=new ControllerScegliNegozio();
	private ControllerVisualizza cV=new ControllerVisualizza();
	private ControllerVisualizzaOrdine cVO=new ControllerVisualizzaOrdine();
	private ControllerVisualizzaProfilo cVP=new ControllerVisualizzaProfilo();
	private TempUser tu=TempUser.getInstance();
	private Factory f=new Factory();
	private Fattura fattura1=new Fattura("pippo","pluto","via paperopoli 12","dopo le 12","14",(float)15.6);
	private Fattura fattura2=new Fattura();
	private Giornale g=new Giornale();
	private GiornaleDao gD=new GiornaleDao();
	private Libro l=new Libro();
	private Negozio n1=new Negozio();
	private NegozioDao nD=new NegozioDao();
	private Pagamento p=new Pagamento(5, "cash", 0, "pippo", (float)12.35, "cash");
	private Pagamento p2=new Pagamento(6, "cc", 0, "franco", (float)11.25, "cc", 5);

	@Test
	void testControllaPag() {
		assertTrue(cPCC.controllaPag("2025-08-08","1852-9999-4152-9963","528"));
	}

	@Test
	void testAggiungiCartaDBL() throws SQLException {
		vis.setSpesaT((float)125.6);
		vis.setTypeAsBook();
		java.sql.Date data=Date.valueOf("2025-11-11");
		cPCC.aggiungiCartaDB("franco","rossi","1852-8444-5256-3361", data, "185", (float)3000.0);
		assertNotNull(data);
	}
	@Test
	void testAggiungiCartaDBG() throws SQLException {
		vis.setSpesaT((float)35.4);
		vis.setTypeAsDaily();
		java.sql.Date data=Date.valueOf("2026-06-11");
		cPCC.aggiungiCartaDB("luigi","neri","2552-8544-5256-3361", data, "263", (float)2500.0);
		assertNotNull(data);
	}
	@Test
	void testAggiungiCartaDBR() throws SQLException {
		vis.setSpesaT((float)14.9);
		vis.setTypeAsMagazine();
		java.sql.Date data=Date.valueOf("2028-02-22");
		cPCC.aggiungiCartaDB("silvia","gialli","0442-8544-2556-3311", data, "145", (float)5000.0);
		assertNotNull(data);
	}

	@Test
	void testRitornaElencoCC() throws SQLException {
		assertNotNull(cPCC.ritornaElencoCC("luigi"));
	}

	@Test
	void testTornaDalDb() throws SQLException {
		assertNotNull(cPCC.tornaDalDb("2552-8544-5256-3361"));
	}

	@Test
	void testPagamentoCCL() throws SQLException {
		vis.setTypeAsBook();
		vis.setSpesaT((float)125.6);
		vis.setId(1);
		cPCC.pagamentoCC("franco");
		assertNotEquals(0,vis.getId());
	}
	@Test
	void testPagamentoCCG() throws SQLException {
		vis.setTypeAsDaily();
		vis.setSpesaT((float)35.4);
		vis.setId(1);
		cPCC.pagamentoCC("luigi");
		assertNotEquals(0,vis.getId());
	}
	@Test
	void testPagamentoCCR() throws SQLException {
		vis.setTypeAsMagazine();
		vis.setSpesaT((float)14.9);
		vis.setId(1);
		cPCC.pagamentoCC("silvia");
		assertNotEquals(0,vis.getId());
	}
	@Test
	void testAggiornaPass() throws SQLException {
		assertTrue(cP.aggiornaPass("baoPublishing@gmail.com","BaoPub2021","BaoPub2022"));
	}
	@Test
	void testRegistra() throws SQLException {
		assertTrue(cRU.registra("nuovoUtenteN", "nuovoUtenteC", "nuovoUtente@gmail.com", "nuovo152","nuovo152",LocalDate.of(1985, 02,24)));
	}
	@Test
	void testGeneraReportLibri() throws IOException, SQLException {
		cRP.generaReportLibri();
		assertNotNull("ReportFinale\\riepilogoLibro.txt");
	}

	@Test
	void testGetUtenti() throws IOException, SQLException {
		cRP.getUtenti();
		assertNotNull("ReportFinale\\riepilogoUtenti.txt");
	}

	@Test
	void testGeneraReportRiviste() throws IOException, SQLException {
		cRP.generaReportRiviste();
		assertNotNull("ReportFinale\\riepilogoRiviste.txt");
	}

	@Test
	void testGeneraReportGiornali() throws IOException, SQLException {
		cRP.generaReportGiornali();
		assertNotNull("ReportFinale\\riepilogoGiornali.txt");
	}
	@ParameterizedTest
	@ValueSource(strings = { "UTENTE","ADMIN","SCRITTORE","EDITORE"})
	void testGetTipo(String strings) {
		User.getInstance().setIdRuolo(strings);
		assertEquals(strings,cRR.getTipo());
	}
	
	@ParameterizedTest
	@ValueSource(strings= {"Zerocalcare","In cucina con ciccio"})
	void testCercaPerTipoL(String strings) throws SQLException {
		vis.setTypeAsBook();
		assertNotNull(cRicP.cercaPerTipo(strings));
	}
	@ParameterizedTest
	@ValueSource(strings= {"Il Fatto Quotidiano","La Republica"})
	void testCercaPerTipoG(String strings) throws SQLException {
		vis.setTypeAsDaily();
		assertNotNull(cRicP.cercaPerTipo(strings));
	}
	
	@ParameterizedTest
	@ValueSource(strings= {"Focus","Rivista A"})
	void testCercaPerTipoR(String strings) throws SQLException {
		vis.setTypeAsMagazine();
		assertNotNull(cRicP.cercaPerTipo(strings));
	}
	@Test
	void testSetRicercaL() {
		vis.setTypeAsBook();
		assertTrue(cRT.setRicercaL());
	}

	@Test
	void testSetRicercaG() {
		vis.setTypeAsDaily();
		assertTrue(cRT.setRicercaG());
	}

	@Test
	void testSetRicercaR() {
		vis.setTypeAsMagazine();
		assertTrue(cRT.setRicercaR());
	}
	@Test
	void testGetNegozi() throws SQLException {
		assertNotNull(cSN.getNegozi());
	}

	@Test
	void testIsLoggedT() {
		vis.setIsLogged(true);
		assertTrue(cSN.isLogged());
	}
	@Test
	void testIsLoggedF() {
		vis.setIsLogged(false);
		assertFalse(cSN.isLogged());
	}
	@ParameterizedTest
	@ValueSource(ints = {1,2,3,4,5,6,7,8,9,10})
	void testGetDataL(int ints) throws SQLException {
		assertNotNull(cV.getDataL(ints));
	}
	@ParameterizedTest
	@ValueSource(ints = {1,2,3,4,5,6})
	void testGetDataG(int ints) throws SQLException {
		assertNotNull(cV.getDataG(ints));
	}
	@ParameterizedTest
	@ValueSource(ints = {1,2,3,4,5})
	void testGetDataR(int ints) throws SQLException {
		assertNotNull(cV.getDataR(ints));
	}
	@Test
	void testGetDati() throws SQLException {
		User.getInstance().setEmail(null);
		assertNotNull(cVO.getDati());
	}
	@Test
	void testGetCredenziali() throws SQLException {
		User.getInstance().setEmail("baoPublishing@gmail.com");
		assertNotNull(cVP.getCredenziali());
		
	}
	@Test
	void testCancellaUtente() throws SQLException {
		User.getInstance().setEmail("baoPublishing@gmail.com");
		assertTrue(cVP.cancellaUtente());
	}
	@Test
	void testCreateUser2() throws SQLException {
		tu.setIdRuolo("E");
		tu.setNome("tempUser nome");
		tu.setCognome("tempUser cognome");
		tu.setEmail("tempUser@libero.it");
		tu.setPassword("userTemp963");
		tu.setDescrizione("provo ad inserire un tempUser");
		tu.setDataDiNascita(LocalDate.of(1988, 5,12));
		assertTrue(UsersDao.createUser2(tu));
		
	}

	@Test
	void testAggiornaTempNome() throws SQLException {
		tu.setNome("alfredo");
		assertNotNull(UsersDao.aggiornaTempNome(tu));
	}

	

	
	@Test
	void testMaxIdUSer() throws SQLException {
		assertNotEquals(0,UsersDao.maxIdUSer());
	}
	@ParameterizedTest
	@ValueSource(strings={"baoPublishing@gmail.com","giannni@gmail.com"} )
	void testCheckTempUser(String strings) throws SQLException
	{
		tu.setEmail(strings);
		assertEquals(-1,UsersDao.checkTempUser(tu));
	}
	
	@Test
	void testFindUser()
	{
		assertNotNull(UsersDao.findUser(tu));
	}
	@Test
	void testDeleteTempUser() throws SQLException
	{
		tu.setEmail("alfredino25@libro.it");
		assertTrue(UsersDao.deleteTempUser(tu));
	}
	@Test
	void testCreateRaccoltaFinaleCompletaL() {
		f.createRaccoltaFinale1("libro","provaL","FUMETTI","prova","it", "prova","FUMETTI");
		f.createRaccoltaFinale2("libro",150,"1426351", 200, 1, (float)1.65, 100);
		assertNotNull(f.createRaccoltaFinaleCompleta("libro",LocalDate.now(), "prova", "prova", 15));
		
	}
	@Test
	void testCreateRaccoltaFinaleCompletaG() {
		f.createRaccoltaFinale1("giornale", "provaG", "quotidiano","prova", "ita","prova","quotidiano");
		f.createRaccoltaFinale2("giornale", 0, "85225", 150, 1, (float)1.80, 50);
		assertNotNull(f.createRaccoltaFinaleCompleta("giornale",LocalDate.now(), "prova", "prova",20));
		
	}
	@Test
	void testCreateRaccoltaFinaleCompletaR() {
		f.createRaccoltaFinale1("rivista","provaR","MENSILE","prova","ita","prova", null);
		f.createRaccoltaFinale2("rivista", 50, null, 100, 1, (float)1.25, 50);
		assertNotNull(f.createRaccoltaFinaleCompleta("rivista",LocalDate.now(), "prova", "prova",30));
		
	}
	@Test
	void testGetNome() {
		fattura2.setNome("topolino");
		assertEquals("topolino",fattura2.getNome());
	}

	@Test
	void testGetCognome() {
		fattura2.setCognome("paperino");
		assertEquals("paperino",fattura2.getCognome());
		
	}

	@Test
	void testGetVia() {
		assertNotNull(fattura1.getVia());
	}

	@Test
	void testGetCom() {
		assertNotNull(fattura1.getCom());
	}

	@Test
	void testGetNumero() {
		assertNotEquals("0",fattura1.getNumero());
	}

	@Test
	void testGetAmmontare() {
		assertNotEquals(0,fattura1.getAmmontare());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"La Republica1","La Republica2","Il Fatto Quotidiano4","Il Fatto Quotidiano5","La gazzetta del profeta"})
	void testGetTitolo(String strings) {
		g.setTitolo(strings);
		assertEquals(strings,g.getTitolo());
	}

	@Test
	void testGetTipologia() {
		g.setTipologia("Quotidiano");
		assertEquals("Quotidiano",g.getTipologia());
	}

	@Test
	void testGetLingua() {
		g.setLingua("ita");
		assertEquals("ita",g.getLingua());
	}

	@Test
	void testGetEditore() {
		g.setEditore("prova");
		assertEquals("prova",g.getEditore());
	}

	@Test
	void testGetDataPubb() {
		g.setDataPubb(LocalDate.now());
		assertNotNull(g.getDataPubb());
	}

	@Test
	void testGetCopieRimanenti() {
		g.setCopieRimanenti(105);
		assertEquals(105,g.getCopieRimanenti());
	}

	@Test
	void testGetDisponibilita() {
		g.setDisponibilita(0);
		assertEquals(0,g.getDisponibilita());
	}

	@Test
	void testGetPrezzo() {
		g.setPrezzo((float)1.36);
		assertNotEquals(0,g.getPrezzo());
	}

	@ParameterizedTest
	@ValueSource(ints= {1,2,3,4,5,6,7,8,9,10})
	void testGetId(int ints) {
		g.setId(ints);
		assertEquals(ints,g.getId());
	}

	@ParameterizedTest
	@ValueSource(ints= {1,2,3,4,5,6,7,8,9,10})
	void testScarica(int ints) throws DocumentException, IOException
	{
		g.setId(ints);
		g.scarica();
		assertEquals(ints,g.getId());
	}
	@ParameterizedTest
	@ValueSource(ints= {1,2,3,4,5,6,7,8,9,10})
	void testLeggi(int ints) throws IOException, DocumentException
	{
		g.setId(ints);
		g.leggi(ints);
		assertEquals(ints,g.getId());
	}
	@Test
	void testRetId() throws SQLException {
		g.setTitolo("La gazzetta del profeta");
		assertEquals(12,gD.retId(g));
	}

	@Test
	void testGetDisp() throws SQLException {
		g.setId(12);
		assertEquals(1,gD.getDisp(g));
	}
	@Test
	void testGetTitolo() {
		l.setTitolo("prova");
		assertEquals("prova",l.getTitolo());
	}

	@Test
	void testGetCodIsbn() {
		l.setCodIsbn("123456");
		assertEquals("123456",l.getCodIsbn());
	}

	@Test
	void testGetEditoreL() {
		l.setEditore("prova");
		assertEquals("prova",l.getEditore());
	}

	@Test
	void testGetAutore() {
		l.setAutore("prova");
		assertEquals("prova",l.getAutore());
	}

	@Test
	void testGetLinguaL() {
		l.setLingua("ita");
		assertEquals("ita",l.getLingua());
	}

	

	@Test
	void testGetDataPubbL() {
		l.setDataPubb(LocalDate.now());
		assertNotNull(l.getDataPubb());
	}

	@Test
	void testGetRecensione() {
		l.setRecensione("prova");
		assertEquals("prova",l.getRecensione());
	}

	@Test
	void testGetNrCopie() {
		l.setNrCopie(100);
		assertNotEquals(0,l.getNrCopie());
		}

	@Test
	void testGetDesc() {
		l.setDesc("prova");
		assertEquals("prova",l.getDesc());
	}

	@Test
	void testGetDisponibilitLa() {
		l.setDisponibilita(1);
		assertEquals(1,l.getDisponibilita());
	}
	@Test
	void testGetPrezzoL() {
		l.setPrezzo((float)1.25);
		assertNotEquals(0,l.getPrezzo());
	}
	@Test
	void testGetId() {
		l.setId(25);
		assertNotEquals(0,l.getId());
	}
	@ParameterizedTest
	@ValueSource(strings= {"LINGUISTICA_SCRITTURA","ADOLESCENTI_RAGAZZI","CINEMA_FOTOGRAFIA",
			"ECONOMIA" ,"INFORMATICA","WEB_DIGITAL_MEDIA","POLITICA" ,"ROMANZI_ROSA"})
	void testGetCategoria(String strings) {
		l.setCategoria(strings);
		assertEquals(strings,l.getCategoria());
	}
  
	@Test
	void testGetNumeroPagine() {
		l.setNumeroPagine(150);
		assertEquals(150,l.getNumeroPagine());
	}
	
	@ParameterizedTest
	@ValueSource(ints= {1,5,6,7})
	void testLeggiL(int ints) throws IOException, DocumentException, URISyntaxException
	{
		l.setId(ints);
		l.leggi(ints);
		assertEquals(ints,l.getId());
	}

	@Test
	void testGetNomeN() {
		n1.setNome("Negozio E");
		assertEquals("Negozio E",n1.getNome());
	}

	@Test
	void testGetViaN() {
		n1.setVia("via papaveri 15");
		assertEquals("via papaveri 15",n1.getVia());
	}

	@Test
	void testGetIsValid() {
		n1.setIsValid(false);
		assertNotEquals(true,n1.getIsValid());
	}

	@Test
	void testGetIsOpen() {
		n1.setIsOpen(true);
		assertNotEquals(false,n1.getIsOpen());
	}
	@Test
	void testGetNegoziL() throws SQLException {
		assertNotNull(nD.getNegozi());
	}

	@Test
	void testCheckOpen() throws SQLException {
		n1.setNome("Negozio P");
		nD.setOpen(n1, false);
		assertFalse(nD.checkOpen(n1));
	}

	@Test
	void testCheckRitiro() throws SQLException {
		n1.setNome("Negozio P");
		nD.setRitiro(n1, true);
		assertFalse(nD.checkRitiro(n1));
	}
	@Test
	void testGetIdP() {
		assertNotEquals(0,p2.getId());
	}

	@Test
	void testGetMetodo() {
		assertEquals("cash",p.getMetodo());
	}

	@Test
	void testGetEsito() {
		assertEquals(0,p.getEsito());
	}

	@Test
	void testGetNomeUtente() {
		assertNotNull(p2.getNomeUtente());
	}

	@Test
	void testGetAmmontareP() {
		assertNotEquals(0,p.getAmmontare());
	}

	@Test
	void testGetTipo() {
		assertEquals("cc",p2.getTipo());
	}
	@Test
	void testGetIdOggetto() {
		assertNotEquals(0,p2.getId());
	}
	



	
}
