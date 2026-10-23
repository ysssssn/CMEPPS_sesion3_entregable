package pkg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Cuenta {

    private static final double LIMITE_DESCUBIERTO = 500;

    private final String numero;
    private double saldo;
    private final List<Movimiento> movimientos = new ArrayList<>();
    private final List<String> errores = new ArrayList<>();

    public Cuenta(double saldo) {
        this("", saldo);
    }

    public Cuenta(String numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Movimiento> getMovimientos() {
        return Collections.unmodifiableList(movimientos);
    }

    public List<String> getErrores() {
        return Collections.unmodifiableList(errores);
    }

    public void ingreso(double importe) {
        saldo += importe;
        registrarMovimiento(Movimiento.Signo.H, importe, "Ingreso");
    }

    public void reintegro(double importe) {
        if (!permiteReintegro(importe)) {
            registrarError(importe);
            return;
        }
        saldo -= importe;
        registrarMovimiento(Movimiento.Signo.D, importe, "Reintegro");
    }

    private boolean permiteReintegro(double importe) {
        return saldo - importe >= -LIMITE_DESCUBIERTO;
    }

    private void registrarMovimiento(Movimiento.Signo signo, double importe, String detalle) {
        movimientos.add(new Movimiento(importe, signo, detalle));
    }

    private void registrarError(double importe) {
        errores.add(String.format(Locale.ROOT,
                "Fondos insuficientes (saldo %.2f euros) en la cuenta %s para el reintegro de %.2f euros",
                saldo, numero, importe));
    }
}