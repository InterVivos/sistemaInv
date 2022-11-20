package sistemaInv.db;

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
