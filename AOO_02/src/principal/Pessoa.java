package principal;

import javax.swing.JOptionPane;

public class Pessoa {
    
    private String nome;
    private String cpf;
    
    
    public void setCpf(String cpf){
        this.cpf = cpf;
        if (validarCpf())
            JOptionPane.showMessageDialog(null, "válido");
        else
            JOptionPane.showMessageDialog(null, "invalido");
    }
    
    
    private boolean validarCpf(){
        if (cpf.equals("123"))
            return true;
        else 
            return false;
    }
    
}
