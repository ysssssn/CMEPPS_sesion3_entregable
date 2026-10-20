package pkg;

public class Movimiento {

    public enum Signo {
        D,
        H
    }

    private final double importe;
    private final Signo signo;
    private final String detalle;

    public Movimiento(double importe, Signo signo, String detalle) {
        this.importe = importe;
        this.signo = signo;
        this.detalle = detalle;
    }

    public double getImporte() {
        return importe;
    }

    public Signo getSigno() {
        return signo;
    }

    public String getDetalle() {
        return detalle;
    }
}