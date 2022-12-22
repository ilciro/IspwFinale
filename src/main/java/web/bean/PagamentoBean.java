package web.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import laptop.model.Pagamento;
import laptop.model.User;
import laptop.utilities.ConnToDb;

public class PagamentoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String query="";
	private static String eccezione="eccezione ottenuta:";
	private int id;
	private String metodo;
	private int esito; //0 ok 1 fallito
	private String nomeUtente;
	private float ammontare;
	private String tipo;
	private int idOggetto;
	private List<Pagamento> listaPagamenti;
public void inserisciPagamento(Pagamento p) throws SQLException {
		

		
		

		query="INSERT INTO ispw.pagamento(metodo,esito,nomeUtente,spesaTotale,eMail,tipoAcquisto,idProd) values (?,?,?,?,?,?,?)";

		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			
			
			prepQ.setString(1,p.getMetodo()); // 
			prepQ.setInt(2,p.getEsito());
			prepQ.setString(3,p.getNomeUtente());
			prepQ.setFloat(4,p.getAmmontare());
			prepQ.setString(5, User.getInstance().getEmail());
			prepQ.setString(6,p.getTipo());
			prepQ.setInt(7, p.getId());
			prepQ.executeUpdate();
		}catch(SQLException e)
		{
						java.util.logging.Logger.getLogger("insert pagamento").log(Level.INFO, eccezione, e);
		}
		
		
		}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getMetodo() {
	return metodo;
}

public void setMetodo(String metodo) {
	this.metodo = metodo;
}

public int getEsito() {
	return esito;
}

public void setEsito(int esito) {
	this.esito = esito;
}

public String getNomeUtente() {
	return nomeUtente;
}

public void setNomeUtente(String nomeUtente) {
	this.nomeUtente = nomeUtente;
}

public float getAmmontare() {
	return ammontare;
}

public void setAmmontare(float ammontare) {
	this.ammontare = ammontare;
}

public String getTipo() {
	return tipo;
}

public void setTipo(String tipo) {
	this.tipo = tipo;
}

public int getIdOggetto() {
	return idOggetto;
}

public void setIdOggetto(int idOggetto) {
	this.idOggetto = idOggetto;
}

public int retUltimoOrdine() throws SQLException 
{
	int id=0;
	query="select count(*) as massimo from pagamento";
	try(Connection conn=ConnToDb.generalConnection();
			PreparedStatement prepQ=conn.prepareStatement(query);)
	{
		ResultSet rs=prepQ.executeQuery();
		while ( rs.next() ) {
			id=rs.getInt("massimo");

		}
	}catch(SQLException e)
	{
					java.util.logging.Logger.getLogger("ultimo ordine").log(Level.INFO, eccezione, e);
	}
			
	return id;
	
}

public boolean annullaOrdine(int idC) throws SQLException
{
	boolean state=false;
	int row=0;
	
	query="delete from pagamento where id_op=?";
	try(Connection conn=ConnToDb.generalConnection();
			PreparedStatement prepQ=conn.prepareStatement(query);)
	{
		prepQ.setInt(1,idC);
		row=prepQ.executeUpdate();
		if(row==1)
			state=true;
	}catch(SQLException e)
	{
					java.util.logging.Logger.getLogger("annulla ordine").log(Level.INFO, eccezione, e);
	}
		
		return state;

	}
	
public List<Pagamento> getPagamentiList() throws SQLException  {

	List<Pagamento> catalogo=new ArrayList<>();
	query="SELECT id_op,metodo,esito,nomeUtente,spesaTotale,tipoAcquisto,idProd from ispw.pagamento where eMail=?";
	try(Connection conn=ConnToDb.generalConnection();
			PreparedStatement prepQ=conn.prepareStatement(query);)
	{
		prepQ.setString(1, User.getInstance().getEmail());
		ResultSet rs=prepQ.executeQuery();
	while(rs.next())
	{


		catalogo.add(new Pagamento (rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getFloat(5),rs.getString(6)));

	}
	}catch(SQLException e)
	{
					java.util.logging.Logger.getLogger("lista pagamenti").log(Level.INFO, eccezione, e);
	}

return catalogo;
}

public List<Pagamento> getListaPagamenti() {
	return listaPagamenti;
}

public void setListaPagamenti(List<Pagamento> listaPagamenti) {
	this.listaPagamenti = listaPagamenti;
}


}
