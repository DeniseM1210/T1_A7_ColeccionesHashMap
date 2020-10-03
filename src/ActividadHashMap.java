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
	
	public static int validacionNum() {
		int r = 0;
		boolean error = false;
		do {
			try {
				r = entrada.nextInt();
			} catch (java.util.InputMismatchException e) {
				System.out.println("Error, intente de nuevo:");
				entrada.nextLine();
				error=true;
			}
			if (r>0) {
				error=false;
			}else {
				System.out.println("Solo ingrese números");
				error=true;
			}
		}while(error);
		return r;
	}
	public static byte validacionNum2() {
		return (byte)validacionNum();
	}
	public static String validacionCarrera() {
		boolean error=false;
		String carrera;
		int i=0;
		do {
			if(i==0) {
				System.out.println("Carrera:");
			}
			i++;
			carrera = entrada.nextLine().toLowerCase();
			if(carrera.contains("isc")||carrera.contains("iia")||carrera.contains("im")||carrera.contains("la")||carrera.contains("cp")){
				error=false;
				
			}else {
				if(i!=1) {
					System.out.println("No existe la carrera ingresada, intentelo de nuevo");
				}
				error=true;
			}
		}while (error);
		return carrera;
	}
	public static Calendar validacionFecha() {
		int fech[]=new int[3];
		boolean error=false;
		do {
			error=false;
			System.out.println("Fecha(dd/mm/aaaa):");
			String fecha = entrada.nextLine();
			try {
				fech[0]=Integer.parseInt(fecha.substring(0,2));
				fech[1]=Integer.parseInt(fecha.substring(3,5))-1;
				fech[2]=Integer.parseInt(fecha.substring(6,10));
			} catch (Exception e) {
				System.out.println("Fecha no valida, intente de nuevo");
				error=true;
			}
		}while(error);
		Calendar fecha = Calendar.getInstance();
		fecha.set(fech[2], fech[1], fech[0]);
		return fecha;
	}
	
	
	
	public void llenarList(int cant) {
		Map<Integer, Alumno> mapAlumnos = new HashMap<Integer, Alumno>();
		
		for (int i = 0; i < cant; i++) {
			entrada.nextLine(); //Después de terminar de registrar a un alumno se debe de dar enter. 
			System.out.println("Alumno " + (i + 1));
			System.out.println("Ingrese el nombre: ");
			String nombre = entrada.nextLine();
			System.out.println("Ingrese la edad: ");
			byte edad; 
			edad = ColeccionAlumnos.validacionNum2();
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
		System.out.println("Alumnos inscritos después de la fecha: ");
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
		
		byte opcion = 0;
		byte opcion2 = 0;
		
		do {
			System.out.println("1- Llenar lista");
			System.out.println("2- Vaciar lista");
			System.out.println("3- Mostrar alumnos por carrera");
			System.out.println("4- Calcular promedio de las edades");
			System.out.println("5- Mostrar alumnos inscritos después de la fecha indicada (10/08/2016)");
			System.out.println("6- Salir del menú");
		opcion = ColeccionAlumnos.validacionNum2();
		switch(opcion) {
		case 1: 
			coAl.llenarList(5);
			break;
		case 2:
			coAl.vaciarList();
			break;
		case 3:
			String carrera="";
			do {
			System.out.println("1- ISC");
			System.out.println("2- IIA");
			System.out.println("3- IM");
			System.out.println("4- LA");
			System.out.println("5- CP");
			opcion2 = ColeccionAlumnos.validacionNum2();
			switch (opcion2) {
			case 1:
				carrera="isc";
				opcion2=6;
				break;
			case 2:
				carrera="iia";
				opcion2=6;
				break;
			case 3:
				carrera="im";
				opcion2=6;
				break;
			case 4:
				carrera="la";
				opcion2=6;
				break;
			case 5:
				carrera="cp";
				opcion2=6;
				break;
			default:
				System.out.println("Opcion no valida");
				opcion2=7;
				break;
				}
			}while(opcion2!=6);
			coAl.mostrarAlPorCarrera(carrera);
			break;
		case 4:
			System.out.println("Promedio de edades: "+coAl.calcPromEdades());
			break;
		case 5:
			coAl.filtroFecha(fecha);
			break;
		case 6:
			break;
		default:
			System.out.println("Opcion no Valida");
			break;
		}
	}while(opcion != 6);
		

	}

}
