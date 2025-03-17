package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClubDeportivoTest {
    private ClubDeportivo club;

    @BeforeEach
    public void setUp() throws ClubException {
        club = new ClubDeportivo("Test Club", 5);
    }

    @Test
    public void testCrearClubConNumeroNegativoDebeLanzarExcepcion() {
        assertThrows(ClubException.class, () -> new ClubDeportivo("Club Erroneo", -1));
    }

    @Test
    public void testAnyadirActividadCorrectamente() throws ClubException {
        Grupo grupo = new Grupo("001", "Futbol", 20, 10, 50.0);
        club.anyadirActividad(grupo);
        assertEquals(10, club.plazasLibres("Futbol"));
    }

    @Test
    public void testAnyadirGrupoDuplicadoDebeActualizarPlazas() throws ClubException {
        Grupo grupo1 = new Grupo("001", "Futbol", 20, 10, 50.0);
        Grupo grupo2 = new Grupo("001", "Futbol", 30, 10, 50.0);
        club.anyadirActividad(grupo1);
        club.anyadirActividad(grupo2);
        assertEquals(20, club.plazasLibres("Futbol"));
    }

    @Test
    public void testMatricularPersonasSinSuficientesPlazasDebeLanzarExcepcion() throws ClubException {
        Grupo grupo = new Grupo("001", "Natacion", 10, 10, 60.0);
        club.anyadirActividad(grupo);
        assertThrows(ClubException.class, () -> club.matricular("Natacion", 5));
    }

    @Test
    public void testIngresosCorrectos() throws ClubException {
        club.anyadirActividad(new Grupo("001", "Yoga", 20, 5, 30.0));
        club.anyadirActividad(new Grupo("002", "Karate", 25, 10, 40.0));
        assertEquals(5 * 30.0 + 10 * 40.0, club.ingresos());
    }
}
