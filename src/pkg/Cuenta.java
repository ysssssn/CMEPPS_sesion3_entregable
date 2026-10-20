package pkg;

public class Cuenta {

	private double saldo;
	
	public Cuenta(double saldo){
		super();
		this.saldo = saldo;
	}

	public double getSaldo(){
		return saldo;
	}
	
	public void setSaldo(double saldo){
		this.saldo = saldo;
	}
	
	public void ingreso(double i){
		this.saldo += 25;
	}
	
	public void reintegro(double i){
	}
}
