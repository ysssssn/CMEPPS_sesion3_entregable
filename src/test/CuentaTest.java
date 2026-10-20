package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pkg.Cuenta;

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
}