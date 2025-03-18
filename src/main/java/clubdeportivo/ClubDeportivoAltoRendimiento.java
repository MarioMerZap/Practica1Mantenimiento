package clubdeportivo;


public class ClubDeportivoAltoRendimiento extends ClubDeportivo{
	private int maximoPersonasGrupo;
	private double incremento;
	
	public ClubDeportivoAltoRendimiento(String nombre, int maximo, double incremento) throws ClubException {
		super(nombre);
		if (maximo <= 0 || incremento <= 0) {
			throw new ClubException("ERROR: valores 0 o negativos.");
		}
		this.maximoPersonasGrupo = maximo;  // 🔥 Asegurar que maximoPersonasGrupo se guarda correctamente
		this.incremento = incremento;
	}
	
	
	
	public ClubDeportivoAltoRendimiento(String nombre, int tam, int maximo, double incremento) throws ClubException {
		super(nombre,tam);
		if (maximo<=0 || incremento<=0) {
			throw new ClubException("ERRORES: valores 0 o negativos.");
		}
		maximoPersonasGrupo=maximo;
		this.incremento=incremento;
	}
	
	// El club de alto rendimiento tiene limitadas las plazas. Si el número de plazas que se recibe como parametro es mayor que el permitido, 
	// se establece su valor al maximo permitido por grupo para el club.
	public void anyadirActividad(String[] datos) throws ClubException {
		if (datos.length < 5) {
			throw new ClubException("ERROR: faltan datos");
		}
		if (datos[0] == null || datos[1] == null) { 
			throw new ClubException("ERROR: actividad o código no pueden ser nulos");
		}
		try {
			int plazas = Integer.parseInt(datos[2]);
			int matriculados = Integer.parseInt(datos[3]);
			double tarifa = Double.parseDouble(datos[4]);
	
			//Corregimos la lógica para que plazas siempre tome el valor correcto
			int plazasFinales = Math.min(plazas, maximoPersonasGrupo);
	
			//Asegurar que los matriculados no superen las plazas finales
			if (matriculados > plazasFinales) {
				matriculados = plazasFinales;
			}
	
			Grupo g = new Grupo(datos[0], datos[1], plazasFinales, matriculados, tarifa);
			super.anyadirActividad(g);
	
		} catch (NumberFormatException e) {
			throw new ClubException("ERROR: formato de número incorrecto");
		}
	}
	
	
	
	
	
	// Los ingresos del club tienen
	public double ingresos() {
		double cantidad = super.ingresos();
		return cantidad+cantidad*(incremento/100);
	}
	
}
