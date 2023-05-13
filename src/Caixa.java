import java.util.Scanner;

public class Caixa {
  private ContaBancaria c1;
  private ContaBancaria c2;
  private Cliente cliente;
  private Scanner entrada=new Scanner(System.in);

  public Caixa(){
    this.c1=null;
    this.c2=null;
    this.cliente=null;
  }

  public void criarConta(){
    System.out.println("Digite seu nome: ");
    String nome=entrada.nextLine();
    System.out.println("Digite seu CPF: ");
    String cpf=entrada.nextLine();

    for(int i=0; i<2; i++){
      if(i==1){
        System.out.println("Digite o tipo da conta: \n" +
        "Digite 1(Conta sem informar o saldo)" +
        "ou Digite 2(Conta com saldo)"
        );
        String tipoConta=entrada.nextLine();
        System.out.println("Digite o limite da conta: ");
        double limite=Double.parseDouble(entrada.nextLine());
  
        cliente=new Cliente(nome, cpf);
  
        if(tipoConta.equals("1")){
          this.c1=new ContaBancaria(cliente, limite);
          System.out.println("Numero da conta: " + this.c1.getNroConta());
        }else if(tipoConta.equals("2")){
          System.out.println("Digite o saldo da conta: ");
          int saldo=Integer.parseInt(entrada.nextLine());
          this.c1=new ContaBancaria(cliente, limite, saldo);
          System.out.println("Numero da conta: " + this.c1.getNroConta());
        }else{
          System.out.println("Tipo de conta invalido!");
        }
      }else{
        System.out.println("Digite o tipo da conta: \n" +
        "Digite 1(Conta sem informar o saldo)" +
        "ou Digite 2(Conta com saldo)"
        );
        String tipoConta=entrada.nextLine();
        System.out.println("Digite o limite da conta: ");
        double limite=Double.parseDouble(entrada.nextLine());

        cliente=new Cliente(nome, cpf);

        if(tipoConta.equals("1")){
          this.c2=new ContaBancaria(cliente, limite);
          System.out.println("Numero da conta: " + this.c2.getNroConta());
        }else if(tipoConta.equals("2")){
          System.out.println("Digite o saldo da conta: ");
          int saldo=Integer.parseInt(entrada.nextLine());
          this.c2=new ContaBancaria(cliente, limite, saldo);
          System.out.println("Numero da conta: " + this.c2.getNroConta());
        }else{
          System.out.println("Tipo de conta invalido!");
        }
      }
    }
  }

  public void getSaldo(int nroConta){
    if(nroConta==this.c1.getNroConta()){
      System.out.println("Nome: " + this.cliente.getNome() + ", Saldo: " + this.c1.getSaldo());
    }else if(nroConta==this.c2.getNroConta()){
      System.out.println("Nome: " + this.cliente.getNome() + ", Saldo: " + this.c2.getSaldo());
    }else{
      System.out.println("Numero de conta invalida!");
    }
  }

  public void depositar(int nroConta, double valor){
    if(nroConta==this.c1.getNroConta()){
      this.c1.depositar(valor);
    }else if(nroConta==this.c2.getNroConta()){
      this.c2.depositar(valor);
    }else{
      System.out.println("Numero de conta invalida!");
    }
  }

  public void sacar(int nroConta, double valor){
    if(this.c1.sacar(valor)==true && nroConta==this.c1.getNroConta()){
      System.out.println("Saque realizado com sucesso!");
    }else if(this.c2.sacar(valor)==true && nroConta==this.c2.getNroConta()){
      System.out.println("Saque realizado com sucesso!");
    }else if(nroConta!=this.c1.getNroConta() && nroConta!=this.c2.getNroConta()){
      System.out.println("Numero de conta invalida!");
    }else{
      System.out.println("Limite de valor da conta atingido!");
    }
  }

  public void transferir(int nroContaOrigem, int nroContaDestino){
    System.out.println("Digite o valor a ser transferido: ");
    int valor=Integer.parseInt(entrada.nextLine());
    
    if(this.c1.getNroConta()==nroContaOrigem && this.c2.getNroConta()==nroContaDestino && this.c1.transferir(valor, c2)==true){
      System.out.println("Transferencia realizada com sucesso!");
    }else if(this.c2.getNroConta()==nroContaOrigem && this.c1.getNroConta()==nroContaDestino && this.c2.transferir(valor, c1)==true){
      System.out.println("Transferencia realizada com sucesso!");
    }else if(this.c1.getNroConta()!=nroContaOrigem && this.c2.getNroConta()!=nroContaOrigem){
      System.out.println("Transferencia invalida! Numero Origem inexistente.");
    }else if(this.c1.getNroConta()!=nroContaDestino && this.c2.getNroConta()!=nroContaDestino){
      System.out.println("Transferencia invalida! Numero Destino inexistente.");
    }else{
      System.out.println("Transferencia invalida! Valor de transferencia acima do limite!");
    }
  }

  public void menu(){
    System.out.println(
    "1. Criar Conta \n"+
    "2. Consultar Saldo \n"+
    "3. Depositar \n"+
    "4. Sacar \n"+
    "5. Transferir \n"+
    "6. Sair"
    );
  }

  public void executar(){
    menu();
    String opc=entrada.nextLine();
    int valor;
    int nroConta;

    while(!opc.equals("6")){
      if(opc.equals("1")){
        criarConta();
      }else if(opc.equals("2")){
        System.out.println("Informe o numero da conta: ");
        nroConta=Integer.parseInt(entrada.nextLine());
        getSaldo(nroConta);
      }else if(opc.equals("3")){
        System.out.println("Informe o numero da conta: ");
        nroConta=Integer.parseInt(entrada.nextLine());
        System.out.println("Digite o valor que deseja depositar: ");
        valor=Integer.parseInt(entrada.nextLine());
        depositar(nroConta, valor);
      }else if(opc.equals("4")){
        System.out.println("Informe o numero da conta: ");
        nroConta=Integer.parseInt(entrada.nextLine());
        System.out.println("Digite o valor que deseja sacar: ");
        valor=Integer.parseInt(entrada.nextLine());
        sacar(nroConta, valor);
      }else if(opc.equals("5")){
        System.out.println("Digite o numero da conta Origem: ");
        int nroContaOrigem=Integer.parseInt(entrada.nextLine());
        System.out.println("Digite o numero da conta Destino: ");
        int nroContaDestino=Integer.parseInt(entrada.nextLine());
        transferir(nroContaOrigem, nroContaDestino);
      }else{
        System.out.println("Opcao invalida!");
      }
      menu();
      opc=entrada.nextLine();
    }
    entrada.close();
  }
}