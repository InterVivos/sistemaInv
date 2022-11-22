package sistemaInv.backend;

import java.sql.PreparedStatement;

import su.interference.api.GenericResult;
import su.interference.persistent.Session;
import su.interference.sql.ResultSet;

public class OperacionesDB
{
	public OperacionesDB() {
		// TODO Auto-generated constructor stub
	}
	
	public static void ejecutarComando(String comando)
	{
		Session sesion = Session.getSession();
		try
		{
			ResultSet rs = sesion.execute(comando);
			Object fg = sesion.newEntity(null, null);
			Object o = rs.poll(sesion);
		    while (o != null)
		    {
		        final GenericResult r = (GenericResult) o;
		        System.out.println(r.getValueByName("ddeptName") + ":" +
		            r.getValueByName("eempName") + ":" + 
		            r.getValueByName("edescript"));
		        o = rs.poll(sesion);
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
