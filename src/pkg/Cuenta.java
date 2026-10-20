package pkg;

import java.util.ArrayList;
import java.util.List;

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
    }

    public void reintegro(double importe) {
        saldo -= importe;
    }
}