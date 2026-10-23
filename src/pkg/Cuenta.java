package pkg;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cuenta {

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
        return movimientos;
    }

    public List<String> getErrores() {
        return errores;
    }

    public void ingreso(double importe) {
        saldo += importe;
        movimientos.add(new Movimiento(importe, Movimiento.Signo.H, "Ingreso"));
    }

    public void reintegro(double importe) {
        if (saldo - importe < -500) {
            errores.add(String.format(Locale.ROOT,
                    "Fondos insuficientes (saldo %.2f euros) en la cuenta %s para el reintegro de %.2f euros",
                    saldo, numero, importe));
            return;
        }
        saldo -= importe;
        movimientos.add(new Movimiento(importe, Movimiento.Signo.D, "Reintegro"));
    }
}