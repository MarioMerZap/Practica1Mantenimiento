package clubdeportivo;

public class Grupo {
	private String codigo;
	private String actividad;
	private int nplazas;
	private int nmatriculados;
	private double tarifa;
	
	public Grupo(String codigo, String actividad, int nplazas,  int matriculados, double tarifa) throws ClubException {
		if (nplazas<=0 || matriculados<0 || tarifa <=0) {
			throw new ClubException("ERROR: los datos numéricos no pueden ser menores o iguales que 0.");
		}
		if (matriculados>nplazas) {
			throw new ClubException("ERROR: El número de plazas es menor que el de matriculados.");
		}
		this.codigo=codigo;
		this.actividad=actividad;
		this.nplazas=nplazas;
		this.nmatriculados=matriculados;
		this.tarifa=tarifa;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getActividad() {
		return actividad;
	}

	public int getPlazas() {
		return nplazas;
	}

	public int getMatriculados() {
		return nmatriculados;
	}
	
	public double getTarifa() {
		return tarifa;
	}
	
	public int plazasLibres() {
		return nplazas-nmatriculados;
	}
	
	public void actualizarPlazas(int n) throws ClubException {
		if (n <= 0 || n < nmatriculados) {  
			throw new ClubException("ERROR: número de plazas inválido.");
		}
		nplazas = n;
	}
	
	
	public void matricular(int n) throws ClubException {
		if (n <= 0) {
			throw new ClubException("ERROR: número de matriculados inválido.");
		}
		if (plazasLibres() < n) {
			throw new ClubException("ERROR: no hay plazas libres suficientes. Plazas libres: " 
									+ plazasLibres() + ", intento de matrícula: " + n);
		}
		nmatriculados += n;
	}
	
	
	public String toString() {
		return "("+ codigo + " - "+actividad+" - " + tarifa + " euros "+ "- P:" + nplazas +" - M:" +nmatriculados+")";
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;  
		if (!(o instanceof Grupo)) return false;
		Grupo otro = (Grupo) o;
		return this.codigo.equalsIgnoreCase(otro.codigo) &&
			   this.actividad.equalsIgnoreCase(otro.actividad);
	}
	
	
	public int hashCode() {
		return codigo.toUpperCase().hashCode()+actividad.toUpperCase().hashCode();
	}
}
