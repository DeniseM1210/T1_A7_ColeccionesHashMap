import java.util.Calendar;

class Alumno{
	private String nombre;
	private byte edad;
	private String carrera;
	private Calendar fechaIns;
	public Alumno(String nombre, byte edad, String carrera, Calendar fechaIns) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.carrera = carrera;
		this.fechaIns = fechaIns;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public byte getEdad() {
		return edad;
	}
	public void setEdad(byte edad) {
		this.edad = edad;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public Calendar getFechaIns() {
		return fechaIns;
	}
	public void setFechaIns(Calendar fechaIns) {
		this.fechaIns = fechaIns;
	}
	
	
}//clase Alumno

public class ActividadHashMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
