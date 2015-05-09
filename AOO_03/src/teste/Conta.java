package teste;

 public class Conta {
    private int codigo;
    public String agencia;
    private String numeroConta;
    private String nome;
    private String senha;
    private float saldo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    public float getSaldo(){
        return saldo;
    }
   
    
     void sacar(float valor){
         saldo = saldo - valor;
     }
     
     void depositar(float valor){
         saldo = saldo + valor;
     }
}


