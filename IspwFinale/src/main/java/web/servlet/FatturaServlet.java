package web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import web.bean.FatturaBean;
import web.bean.LibroBean;
import web.bean.PagamentoBean;
import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.model.Fattura;
import laptop.model.Pagamento;
@WebServlet("/FatturaServlet")
public class FatturaServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FatturaBean fB=new FatturaBean();
	private static  Fattura f=new Fattura();
	
	private LibroBean lB=new LibroBean();
	private PagamentoBean pB=new PagamentoBean();
	private static Pagamento p=new Pagamento();
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome=req.getParameter("nomeL");
		String cognome=req.getParameter("cognomeL");
		String indirizzo=req.getParameter("indirizzoL");
		String com=req.getParameter("com");
		String invia=req.getParameter("buttonC");
		String annullaO=req.getParameter("annulla");
		
		if(checkData(fB.getNome(),fB.getCognome(),fB.getIndirizzo()) && (invia!=null )&& invia.equals("procedi"))
		{
			
			fB.setNome(nome);
			fB.setCognome(cognome);
			fB.setIndirizzo(indirizzo);
			fB.setComunicazioni(com);
			
			f.setNome(fB.getNome());
			f.setCognome(fB.getCognome());
			f.setVia(fB.getIndirizzo());
			f.setCom(fB.getComunicazioni());
			f.setAmmontare(SystemBean.getIstance().getSpesaT());
			
			pB.setId(0);
			pB.setMetodo(SystemBean.getIstance().getMetodoP());
			pB.setAmmontare(SystemBean.getIstance().getSpesaT());
			pB.setEsito(0);
			pB.setNomeUtente(fB.getNome());
			pB.setTipo(lB.getCategoriaD());
		
			p.setId(pB.getId());
			p.setMetodo(pB.getMetodo());
			p.setAmmontare(pB.getAmmontare());
			p.setEsito(pB.getEsito());
			p.setNomeUtente(pB.getNomeUtente());
			p.setTipo(pB.getTipo());
			
			try {
				fB.inserisciFattura(f);
				pB.inserisciPagamento(p);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
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
		
		
		if(annullaO!=null && annullaO.equals("annulla"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/acquista.jsp"); 
			view.forward(req,resp);
		}
	
		/*
		else {
			fB.setMex(new IdException("dati errati!!!"));
			req.setAttribute("beanF", fB);
			RequestDispatcher view = getServletContext().getRequestDispatcher("/fattura.jsp"); 
			view.forward(req,resp);
		}
		*/
		}
	
		private  boolean checkData(String nome,String cognome ,String indirizzo)
		{
			boolean status=false;
			if(!"".equals(nome) && !"".equals(cognome) && !"".equals(indirizzo))
				status=true;
			System.out.println("status :"+status);
			return status;
		}
	
	

}