package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GrupoTest {
    
    private Grupo grupo;

    @BeforeEach
    public void setUp() throws ClubException {
        grupo = new Grupo("G1", "Natación", 20, 10, 50.0);
    }

    @Test
    public void crearGrupoConDatosValidos() throws ClubException {
        Grupo nuevoGrupo = new Grupo("G2", "Fútbol", 15, 5, 30.0);
        assertNotNull(nuevoGrupo);
        assertEquals("G2", nuevoGrupo.getCodigo());
        assertEquals("Fútbol", nuevoGrupo.getActividad());
        assertEquals(15, nuevoGrupo.getPlazas());
        assertEquals(5, nuevoGrupo.getMatriculados());
        assertEquals(30.0, nuevoGrupo.getTarifa());
    }

    @Test
    public void crearGrupoConPlazasNegativasDebeLanzarExcepcion() {
        assertThrows(ClubException.class, () -> new Grupo("G3", "Basket", -5, 2, 40.0));
    }

    @Test
    public void crearGrupoConTarifaNegativaDebeLanzarExcepcion() {
        assertThrows(ClubException.class, () -> new Grupo("G4", "Vóley", 10, 2, -10.0));
    }

    @Test
    public void matricularEstudiantesConPlazasDisponibles() throws ClubException {
        grupo.matricular(5);
        assertEquals(15, grupo.getMatriculados());
        assertEquals(5, grupo.plazasLibres());
    }

    @Test
    public void matricularMasEstudiantesQuePlazasDisponiblesDebeLanzarExcepcion() {
        assertThrows(ClubException.class, () -> grupo.matricular(15));
    }

    @Test
    public void actualizarPlazasConValorValido() throws ClubException {
        grupo.actualizarPlazas(25);
        assertEquals(25, grupo.getPlazas());
    }

    @Test
    public void actualizarPlazasConValorMenorQueMatriculadosDebeLanzarExcepcion() {
        assertThrows(ClubException.class, () -> grupo.actualizarPlazas(5));
    }

    @Test
    public void verificarEqualsParaGruposIguales() throws ClubException {
        Grupo otroGrupo = new Grupo("G1", "Natación", 20, 10, 50.0);
        assertEquals(grupo, otroGrupo);
    }

    @Test
    public void verificarEqualsParaGruposDiferentes() throws ClubException {
        Grupo otroGrupo = new Grupo("G2", "Natación", 20, 10, 50.0);
        assertNotEquals(grupo, otroGrupo);
    }

    @Test
    public void verificarHashCodeParaGruposIguales() throws ClubException {
        Grupo otroGrupo = new Grupo("G1", "Natación", 20, 10, 50.0);
        assertEquals(grupo.hashCode(), otroGrupo.hashCode());
    }

}
