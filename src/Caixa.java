import java.util.ArrayList;
import java.util.Scanner;

public class Caixa {
  private ContaBancaria c1;
  private ArrayList<ContaBancaria> contas;
  private Cliente cliente;
  private Scanner entrada=new Scanner(System.in);

  public Caixa(){
    this.c1=null;
    this.contas=new ArrayList<>();
    this.cliente=null;
  }

  public void criarConta(){
    System.out.println("Digite seu nome: ");
    String nome=entrada.nextLine();
    System.out.println("Digite seu CPF: ");
    String cpf=entrada.nextLine();

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
      this.contas.add(c1);
      System.out.println("Numero da conta: " + this.c1.getNroConta());
    }else if(tipoConta.equals("2")){
      System.out.println("Digite o saldo da conta: ");
      int saldo=Integer.parseInt(entrada.nextLine());
      this.c1=new ContaBancaria(cliente, limite, saldo);
      this.contas.add(c1);
      System.out.println("Numero da conta: " + this.c1.getNroConta());
    }else{
      System.out.println("Tipo de conta invalido!");
    }
  }

  public void getSaldo(int nroConta){
    for (ContaBancaria conta : contas) {
      if(nroConta==conta.getNroConta()){
        System.out.println("Nome: " + conta.getCliente().getNome() + "; Saldo: " + conta.getSaldo());  
      }
    }
  }

  public void depositar(int nroConta, double valor){
    for (ContaBancaria conta : contas) {
      if(nroConta==conta.getNroConta()){
        conta.depositar(valor);
      }
    }
  }

  public void sacar(int nroConta, double valor){
    for (ContaBancaria conta : contas) {
      if(conta.sacar(valor)==true && nroConta==conta.getNroConta()){
        System.out.println("Saque realizado com sucesso!");
      }else if(conta.sacar(valor)==false && nroConta==conta.getNroConta()){
        System.out.println("Impossivel realizar saque. Limite atingido!");
      }
    }
  }

  public void transferir(int nroContaOrigem, int nroContaDestino){
    System.out.println("Digite o valor a ser transferido: ");
    int valor=Integer.parseInt(entrada.nextLine());

    for (ContaBancaria contaOrigem : contas) {
      for (ContaBancaria contaDestino : contas) {
        if(contaOrigem.getNroConta()==nroContaOrigem && contaDestino.getNroConta()==nroContaDestino && contaOrigem.transferir(valor, contaDestino)==true){
          System.out.println("Transferencia realizada com sucesso!");
        }
      }
    }
  }

  public void listarContas(){
    System.out.println("Lista de Contas criadas:");
    for (ContaBancaria conta : contas) {
      System.out.println("Numero: " + conta.getNroConta() + "; Nome: " + conta.getCliente().getNome());
    }
  }

  public void removerConta(int nroConta){
    for (int i=0; i<contas.size(); i++) {
      if(nroConta==contas.get(i).getNroConta() && contas.get(i).getSaldo()==0){
        this.contas.remove(i);
        System.out.println("Conta removida com sucesso!");
      }else if(nroConta==contas.get(i).getNroConta() && contas.get(i).getSaldo()>0){
        System.out.println("Nao foi possivel cancelar conta com saldo disponivel!");
      }else if(nroConta==contas.get(i).getNroConta() && contas.get(i).getSaldo()<0){
        System.out.println("Nao foi possivel cancelar conta em debito!");
      }
    }
  }

  public void menu(){
    System.out.println(
    "1. Criar Conta \n"+
    "2. Consultar Saldo \n"+
    "3. Depositar \n"+
    "4. Sacar \n"+
    "5. Transferir \n"+
    "6. Listar Contas \n"+
    "7. Remover Conta \n"+
    "8. Sair"
    );
  }

  public void executar(){
    menu();
    String opc=entrada.nextLine();
    int valor;
    int nroConta;

    while(!opc.equals("8")){
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
      }else if(opc.equals("6")){
        listarContas();
      }else if(opc.equals("7")){
        System.out.println("Informe o numero da conta: ");
        nroConta=Integer.parseInt(entrada.nextLine());
        removerConta(nroConta);
      }else{
        System.out.println("Opcao invalida!");
      }
      menu();
      opc=entrada.nextLine();
    }
    entrada.close();
  }
}