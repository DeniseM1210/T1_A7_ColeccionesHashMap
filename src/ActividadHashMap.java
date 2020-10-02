import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Alumno{
	private String nombre;
	private byte edad;
	private String carrera;
	private Calendar fechaInsc;
	
	public Alumno(String nombre, byte edad, String carrera, Calendar fechaIns) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.carrera = carrera;
		this.fechaInsc = fechaIns;
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
		return fechaInsc;
	}
	public void setFechaIns(Calendar fechaIns) {
		this.fechaInsc = fechaIns;
	}
	@Override
	public String toString() {
		return "Alumno [Nombre: " + nombre + ", Edad: " + edad + ", Carrera:" + carrera + ", Fecha de inscripción: =" 
				+ fechaInsc.get(Calendar.DATE)+"/"+(fechaInsc.get(Calendar.MONTH)+1)+ "/" + fechaInsc.get(Calendar.YEAR) +"]";
	}
	
}//clase Alumno

class ColeccionAlumnos{
	static Scanner entrada = new Scanner(System.in);
	private Map<Integer, Alumno> mapAlumnos = new HashMap<Integer, Alumno>();
	
	public ColeccionAlumnos(Map<Integer, Alumno> mapAlumnos) {
		super();
		this.mapAlumnos = mapAlumnos;
	}
	public ColeccionAlumnos() {
	}
	
	public Map<Integer, Alumno> getMapAlumnos() {
		return mapAlumnos;
	}
	public void setMapAlumnos(Map<Integer, Alumno> mapAlumnos) {
		this.mapAlumnos = mapAlumnos;
	}
	
	public static int validacionNatural() {
		int ret = 0;
		boolean err = false;
		do {
			try {
				ret = entrada.nextInt();
			} catch (java.util.InputMismatchException e) {
				System.out.println("entrada no valida, intente de nuevo:");
				entrada.nextLine();
				err=true;
			}
			if (ret>0) {
				err=false;
			}else {
				System.out.println("solo números naturales");
				err=true;
			}
		}while(err);
		return ret;
	}
	public static byte validacionNaturalb() {
		return (byte)validacionNatural();
	}
	public static String validacionCarrera() {
		boolean err=false;
		String carrera;
		int i=0;
		do {
			if(i==0) {
				System.out.println("carrera:");
			}
			i++;
			carrera = entrada.nextLine().toLowerCase();
			if(carrera.contains("isc")||carrera.contains("iia")||carrera.contains("im")||carrera.contains("la")||carrera.contains("cp")){
				err=false;
				
			}else {
				if(i!=1) {
					System.out.println("no existe la carrera especificada, ingrese la carrera de nuevo");
				}
				err=true;
			}
		}while (err);
		return carrera;
	}
	public static Calendar validacionFecha() {
		int fch[]=new int[3];
		boolean err=false;
		do {
			err=false;
			System.out.println("fecha(dd/mm/aaaa):");
			String entry = entrada.nextLine();
			try {
				fch[0]=Integer.parseInt(entry.substring(0,2));
				fch[1]=Integer.parseInt(entry.substring(3,5))-1;
				fch[2]=Integer.parseInt(entry.substring(6,10));
			} catch (Exception e) {
				System.out.println("entrada no valida, intente de nuevo");
				err=true;
			}
		}while(err);
		Calendar fecha = Calendar.getInstance();
		fecha.set(fch[2], fch[1], fch[0]);
		return fecha;
	}
	
	
	
	public void llenarList(int cant) {
		Map<Integer, Alumno> mapAlumnos = new HashMap<Integer, Alumno>();
		
		for (int i = 0; i < cant; i++) {
			System.out.println("Alumno " + (i + 1));
			System.out.println("Ingrese el nombre: ");
			String nombre = entrada.nextLine();
			System.out.println("Ingrese la edad: ");
			byte edad; 
			edad = ColeccionAlumnos.validacionNaturalb();
			String carrera = ColeccionAlumnos.validacionCarrera();
			Calendar fechaInsc = ColeccionAlumnos.validacionFecha();
			Alumno al = new Alumno(nombre, edad, carrera, fechaInsc);
			System.out.println(al);
			mapAlumnos.put(i, al);
		}
		
		this.setMapAlumnos(mapAlumnos);
	}
	
	public void vaciarList() {
		Map<Integer, Alumno> vaciar = new HashMap<Integer, Alumno>();
		this.setMapAlumnos(vaciar);
		System.out.println("La lista ha sido vaciada!");
	}
	
	public void mostrarAlPorCarrera(String carrera) {
		System.out.println("Filtrar por carrera:" + carrera);
		Map<Integer, Alumno> mapAlumnos = this.getMapAlumnos();
		for (int i = 0; i < mapAlumnos.size(); i++) {
			Alumno alTot = mapAlumnos.get(i);
			if(alTot.getCarrera().contains(carrera)) {
				System.out.println(alTot);
			}
		}
	}
	
	public double calcPromEdades() {
		Map<Integer, Alumno> mapAlumnos = this.getMapAlumnos();
		double prom = 0;
		for (int i = 0; i < mapAlumnos.size(); i++) {
			Alumno alTot = mapAlumnos.get(i);
			prom = prom + alTot.getEdad();
		}
		prom = prom/mapAlumnos.size();
		return prom;
	}
	
	public void filtroFecha(Calendar fecha) {
		System.out.println("Filtro de fecha: ");
		Map<Integer, Alumno> mapAlumnos = this.getMapAlumnos();
		for (int i = 0; i < mapAlumnos.size(); i++) {
			Alumno alTot = mapAlumnos.get(i);
			int alF = alTot.getFechaIns().compareTo(fecha);
			
			if(alF == 1) {
				System.out.println(alTot);
			}
		}
	}
	
}

public class ActividadHashMap {

	public static void main(String[] args) {
		ColeccionAlumnos coAl = new ColeccionAlumnos();
		
		Calendar fecha = Calendar.getInstance();
		fecha.set(2016, 8, 10);
		
		coAl.llenarList(5);
		coAl.vaciarList();
		

	}

}
