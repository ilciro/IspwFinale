package web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

import web.bean.CartaCreditoBean;
import web.bean.GiornaleBean;
import web.bean.LibroBean;
import web.bean.PagamentoBean;
import web.bean.RivistaBean;
import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.model.CartaDiCredito;
import laptop. model.Pagamento;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Rivista;
import laptop.utilities.ConnToDb;

@WebServlet("/CartaCreditoServlet")
public class CartaCreditoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CartaCreditoBean ccB=new CartaCreditoBean();
	private static CartaDiCredito cc;
	private Libro l=new Libro();
	private LibroBean lB=new LibroBean();
	private PagamentoBean pB=new PagamentoBean();
	private Giornale g=new Giornale();
	private GiornaleBean gB=new GiornaleBean();
	private Rivista r=new Rivista();
	private RivistaBean rB=new RivistaBean();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String n=req.getParameter("nomeL");
		String c=req.getParameter("cognomeL");
		String numero=req.getParameter("cartaL");
		String scad=req.getParameter("scadL");
		String civ=req.getParameter("passL");
		String invia=req.getParameter("buttonI");
		String annulla=req.getParameter("buttonA");
		//String registra=req.getParameter("regB");
		//String generaLista=req.getParameter("prendiDB");
		try {
		if(annulla!=null && annulla.equals("annulla"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/acquista.jsp"); 
			view.forward(req,resp);
		}
		if(invia!=null && invia.equals("paga"))
		{
			ccB.setNome(n);
			ccB.setCiv(c);
			ccB.setNumeroCC(numero);
			ccB.setCognome(c);
			
			ccB.setDataScad(new SimpleDateFormat("yyyy/mm/dd").parse(scad));
			ccB.setCiv(civ);
			ccB.setPrezzoTransazione(SystemBean.getIstance().getSpesaT());
			
			if(controllaPag(scad, ccB.getNumeroCC(), ccB.getCiv()))
			{
				//aggiungo carta al db 
				
				Date sqlDate = null;
				java.util.Date utilDate;
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

			  
			         utilDate = format.parse(scad);
			         sqlDate = new java.sql.Date(utilDate.getTime());
			         
			        
			         
			         

					   
			    
			    
					aggiungiCartaDB(ccB.getNome(), ccB.getCognome(), ccB.getNumeroCC(), sqlDate, ccB.getCiv(), SystemBean.getIstance().getSpesaT());
				
					//inserisco nel db
					
					cc=new CartaDiCredito();
					
					cc.setTipo(2);
					cc.setNumeroCC(ccB.getNumeroCC());
					cc.setAmmontare(1000.0);
					cc.setScadenza(sqlDate);
					cc.setNomeUser(ccB.getNome());
					cc.setPrezzoTransazine(SystemBean.getIstance().getSpesaT());
					
					insCC(cc);
					
					if(SystemBean.getIstance().isNegozioSelezionato()==true)
					{
						RequestDispatcher view = getServletContext().getRequestDispatcher("/negozi.jsp"); 
						view.forward(req,resp);
					}
					else {
					RequestDispatcher view = getServletContext().getRequestDispatcher("/download.jsp"); 
					view.forward(req,resp);
					}
					
			}
			
			
		}
		
	} catch (ParseException |SQLException  e) {
		e.printStackTrace();
	}
	}
	
	private boolean controllaPag(String d, String c,String civ) {
		int x;

		 int anno;
		 int mese;
		 int giorno;
		String[] verifica=null;
		String appoggio="";
		int cont=0;
		boolean state=false;


		

		appoggio = appoggio + d;
		

		  anno = Integer.parseInt((String) appoggio.subSequence(0, 4));
		  mese = Integer.parseInt((String) appoggio.subSequence(5, 7));
		  giorno = Integer.parseInt((String) appoggio.subSequence(8, 10));
		
		if (anno > 2020 && (mese >= 1 && mese <= 12) && (giorno >= 1 && giorno <= 31) && c.length() <= 20 && civ.length()==3 ) {
			
				
					 verifica= c.split("-");
					
					for ( x=0; x<verifica.length; x++) {
							if(verifica[x].length()==4)
							{
								cont++;
							}
					}
					if (cont==4)
					{
						state=true;
					}
					
				

		} 
		
		
		return state;

	}
	
	public void aggiungiCartaDB(String n, String c, String cod, java.sql.Date data, String civ, float prezzo)
			throws SQLException {
		
		
		
			cc = new CartaDiCredito(n, c, cod,  data, civ, prezzo);
						
			cc.setPrezzoTransazine(SystemBean.getIstance().getSpesaT());
			insCC(cc);
						
			Pagamento p;
			 p=new Pagamento(0,"cc",0,cc.getNomeUser(),SystemBean.getIstance().getSpesaT(),null);
				p.setMetodo("cc");
				p.setNomeUtente(cc.getNomeUser());
				String tipo=SystemBean.getIstance().getType();
				if(tipo.equals("libro"))
				{
					//prenod spesa da vis
					l.setId(SystemBean.getIstance().getId());
					p.setAmmontare(SystemBean.getIstance().getSpesaT());
					p.setId(l.getId());
					p.setTipo(lB.retTip(l));
				}
			
				if(tipo.equals("giornale"))
				{
					//prenod spesa da vis
					g.setId(SystemBean.getIstance().getId());
					p.setAmmontare(SystemBean.getIstance().getSpesaT());
					p.setId(g.getId());
					p.setTipo(gB.retTip(g));
					
				}
				
				if(tipo.equals("rivista"))
				{
					//prenod spesa da vis
					r.setId(SystemBean.getIstance().getId());
					p.setAmmontare(SystemBean.getIstance().getSpesaT());
					p.setId(r.getId());
					p.setTipo(rB.retTip(r));
					
				}
								
				pB.inserisciPagamento(p);
		
		

	}

	public void insCC(CartaDiCredito cc) throws SQLException
	{

		String query="insert into cartacredito (nomeP,cognomeP,codiceCarta,scad,codicePin,ammontare)  values(?,?,?,?,?,?)";
				 
		
			try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);)
			{
				prepQ.setString(1,cc.getNomeUser());
				prepQ.setString(2, cc.getCognomeUser());
				prepQ.setString(3, cc.getNumeroCC());
				prepQ.setDate(4, (Date) cc.getScadenza());
				prepQ.setString(5,cc.getCiv());
				//in alternativa vis.getSpesa
				prepQ.setFloat(6, cc.getPrezzoTransazine());
				prepQ.executeUpdate();
			}catch(SQLException e)
			{
				java.util.logging.Logger.getLogger("report libro").log(Level.SEVERE,"\n eccezione ottenuta .",e);

			}


	}

}
