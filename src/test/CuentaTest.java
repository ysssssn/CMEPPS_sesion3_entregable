package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pkg.Cuenta;
import pkg.Movimiento;

class CuentaTest {

    private Cuenta cta;

    @BeforeEach
    void setUp() {
        cta = new Cuenta(100);
    }

    @Test
    void testIngreso() {
        cta.ingreso(25);
        assertEquals(125, cta.getSaldo(), 0.001);
    }

    @Test
    void testReintegro() {
        cta.reintegro(25);
        assertEquals(75, cta.getSaldo(), 0.001);
    }

    @Test
    void testIngresoRegistraMovimiento() {
        cta.ingreso(25);

        assertEquals(1, cta.getMovimientos().size());
        Movimiento m = cta.getMovimientos().get(0);
        assertEquals(25, m.getImporte(), 0.001);
        assertEquals(Movimiento.Signo.H, m.getSigno());
    }

    @Test
    void testReintegroRegistraMovimiento() {
        cta.reintegro(25);

        assertEquals(1, cta.getMovimientos().size());
        Movimiento m = cta.getMovimientos().get(0);
        assertEquals(25, m.getImporte(), 0.001);
        assertEquals(Movimiento.Signo.D, m.getSigno());
    }

    @Test
    void testIngresoNegativoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> cta.ingreso(-1));
    }

    @Test
    void testIngresoCeroLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> cta.ingreso(0));
    }

    @Test
    void testReintegroNegativoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> cta.reintegro(-1));
    }

    @Test
    void testReintegroCeroLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> cta.reintegro(0));
    }

    @Test
    void testImporteInvalidoNoAlteraLaCuenta() {
        assertThrows(IllegalArgumentException.class, () -> cta.ingreso(-1));
        assertThrows(IllegalArgumentException.class, () -> cta.reintegro(-1));

        assertEquals(100, cta.getSaldo(), 0.001);
        assertEquals(0, cta.getMovimientos().size());
    }
}