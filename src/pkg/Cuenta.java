package pkg;

public class Cuenta {

    private double saldo;

    public Cuenta(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void ingreso(double importe) {
        saldo += 25;
    }

    public void reintegro(double importe) {
        saldo -= 25;
    }
}