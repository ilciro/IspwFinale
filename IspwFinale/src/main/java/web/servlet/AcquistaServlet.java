package web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import laptop.exception.IdException;
import web.bean.AcquistaBean;
import web.bean.GiornaleBean;
import web.bean.LibroBean;
import web.bean.RivistaBean;
import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AcquistaServlet")
public class AcquistaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AcquistaBean aB=new AcquistaBean();
	private LibroBean lB=new LibroBean();
	private GiornaleBean gB=new GiornaleBean();
	private RivistaBean rB=new RivistaBean();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String generaNome=req.getParameter("buttonNome");
		String q=req.getParameter("quantita");
		String calcola=req.getParameter("totaleB");
		String metodo=req.getParameter("metodoP");
		String negozio=req.getParameter("negozioB");
		SystemBean.getIstance().setMetodoP(metodo);
		String download=req.getParameter("pdfB");
		float costo=(float)0.0;
		try {
		
		if(generaNome!=null && generaNome.equals("prendi titolo"))
		{
			if(SystemBean.getIstance().getType().equals("libro"))
				aB.setTitolo(lB.getNomeL());
			else if(SystemBean.getIstance().getType().equals("giornale"))
				aB.setTitolo(gB.getNomeG());
			else if(SystemBean.getIstance().getType().equals("rivista"))
		    	aB.setTitolo(rB.getNomeR());
		
		req.setAttribute("beanA",aB);
		RequestDispatcher view = getServletContext().getRequestDispatcher("/acquista.jsp"); 
		view.forward(req,resp);
		
		}
		if(calcola!=null && calcola.equals("calcola"))
		{
			if(!"".equals(generaNome) && (Integer.parseInt(q)<lB.getRimanenza()))
			{
				if(SystemBean.getIstance().getType().equals("libro"))
				{
					costo=Integer.parseInt(q)*lB.getPrezzo();
					aB.setPrezzo(costo);
					SystemBean.getIstance().setQuantita(Integer.parseInt(q));
					SystemBean.getIstance().setSpesaT(costo);
				}
				if(SystemBean.getIstance().getType().equals("giornale"))
				{
					costo=Integer.parseInt(q)*gB.getPrezzo();
					aB.setPrezzo(costo);
					SystemBean.getIstance().setQuantita(Integer.parseInt(q));
					SystemBean.getIstance().setSpesaT(costo);
				}
				if(SystemBean.getIstance().getType().equals("rivista"))
				{
					costo=Integer.parseInt(q)*rB.getPrezzo();
					aB.setPrezzo(costo);
					SystemBean.getIstance().setQuantita(Integer.parseInt(q));
					SystemBean.getIstance().setSpesaT(costo);
				}
				req.setAttribute("beanA",aB);
				req.setAttribute("bean1", SystemBean.getIstance());
				RequestDispatcher view = getServletContext().getRequestDispatcher("/acquista.jsp"); 
				view.forward(req,resp);
			}
			else {
				aB.setMex(new IdException("quantita eccede la scorta nel magazzino"));
				req.setAttribute("beanA",aB);
				RequestDispatcher view = getServletContext().getRequestDispatcher("/acquista.jsp"); 
				view.forward(req,resp);
			}
		}
		if(negozio!=null && negozio.equals("ritiro in negozio"))
		{
			SystemBean.getIstance().setNegozioSelezionato(true);
			if(SystemBean.getIstance().getMetodoP().equals("cash"))
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/fattura.jsp"); 
				view.forward(req,resp);
			}
			else if(SystemBean.getIstance().getMetodoP().equals("cCredito"))
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/cartaCredito.jsp"); 
				view.forward(req,resp);
			}
		}
		if(download!=null && download.equals("scarica il pdf"))
		{
			SystemBean.getIstance().setNegozioSelezionato(false);
			if(SystemBean.getIstance().getMetodoP().equals("cash"))
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/fattura.jsp"); 
				view.forward(req,resp);
			}
			else if(SystemBean.getIstance().getMetodoP().equals("cCredito") )
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/cartaCredito.jsp"); 
				view.forward(req,resp);
			}
		}
		
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	}

}
