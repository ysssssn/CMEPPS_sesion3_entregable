package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pkg.Cuenta;
import pkg.Movimiento;
import pkg.Movimiento.Signo;

class test0014 {

    private static final double DELTA = 0.001;

    private Cuenta cta12345;
    private Cuenta cta67890;

    @BeforeEach
    void setUp() {
        cta12345 = new Cuenta("12345", 50);
        cta67890 = new Cuenta("67890", 0);
    }

    private static void assertMovimiento(Movimiento m, double importe, Signo signo) {
        assertEquals(importe, m.getImporte(), DELTA);
        assertEquals(signo, m.getSigno());
    }

    @Test
    void test12345() {
        cta12345.reintegro(200);
        cta12345.ingreso(100);
        cta12345.reintegro(200);

        assertEquals("12345", cta12345.getNumero());
        assertEquals(-250, cta12345.getSaldo(), DELTA);

        List<Movimiento> movs = cta12345.getMovimientos();
        assertEquals(3, movs.size());
        assertMovimiento(movs.get(0), 200, Signo.D);
        assertMovimiento(movs.get(1), 100, Signo.H);
        assertMovimiento(movs.get(2), 200, Signo.D);

        assertTrue(cta12345.getErrores().isEmpty());
    }

    @Test
    void test67890() {
        cta67890.reintegro(350);
        cta67890.reintegro(200);
        cta67890.reintegro(150);
        cta67890.ingreso(50);
        cta67890.reintegro(100);

        assertEquals("67890", cta67890.getNumero());
        assertEquals(-450, cta67890.getSaldo(), DELTA);

        List<Movimiento> movs = cta67890.getMovimientos();
        assertEquals(3, movs.size());
        assertMovimiento(movs.get(0), 350, Signo.D);
        assertMovimiento(movs.get(1), 150, Signo.D);
        assertMovimiento(movs.get(2), 50, Signo.H);

        List<String> errores = cta67890.getErrores();
        assertEquals(2, errores.size());
        assertEquals("Fondos insuficientes (saldo -350.00 euros) en la cuenta 67890 para el reintegro de 200.00 euros", errores.get(0));
        assertEquals("Fondos insuficientes (saldo -450.00 euros) en la cuenta 67890 para el reintegro de 100.00 euros", errores.get(1));
    }
}