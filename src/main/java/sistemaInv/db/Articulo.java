package sistemaInv.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name="Articulo", indexes= {@Index(name="codigoArticuloPk", columnList="codigo", unique=true)})
public class Articulo
{
	@Column
	@Id
	private String codigo;
	@Column
	private String nombre;
	@Column
	private Date fecha;
	@Column
	private double costo;
	@Column
	private int cantidad;
	@Column
	private double precio;
	
	public Articulo()
	{
		// TODO Auto-generated constructor stub
	}

	public void insertarDatos(String[] datos)
	{
		this.codigo = datos[0];
		this.nombre = datos[1];
		try
		{
			this.fecha = new SimpleDateFormat("dd/MM/yyyy").parse(datos[2]);
			this.costo = Double.parseDouble(datos[3]);
			this.cantidad = Integer.parseInt(datos[4]);
			this.precio = Double.parseDouble(datos[5]);
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
}
