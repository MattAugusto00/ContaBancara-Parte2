public class ContaBancaria {
  private double saldo;
  private double limite;
  private Cliente c1;
  private int nroConta;
  private static int ultimaConta=100;

  public ContaBancaria(Cliente c1, double limite){
    this.c1=c1;
    this.limite=-limite;
    this.saldo=0;
    this.nroConta=ultimaConta++;
  }

  public ContaBancaria(Cliente c1, double limite, double saldo){
    this.c1=c1;
    this.limite=-limite;
    this.saldo=saldo;
    this.nroConta=ultimaConta++;
  }

  public double getSaldo(){
    return saldo;
  }

  public boolean sacar(double valor){
    if(this.saldo-valor>=this.limite){
      this.saldo-=valor;
      return true;
    }
    return false;
  }

  public void depositar(double valor){
    this.saldo+=valor;
  }

  public int getNroConta(){
    return nroConta;
  }

  public boolean transferir(int valor, ContaBancaria contaDestino){
    if(this.sacar(valor)==true){
      contaDestino.depositar(valor);
      return true;
    }
    return false;
  }
}
