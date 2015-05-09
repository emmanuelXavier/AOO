package principal;

import javax.swing.JOptionPane;


public class Main {

    public static void main(String[] args) {
        formuarioThiago();
        formularioJackson();
        formularioRafael();
    }
    
    private static void formularioJackson(){
        String cpf = JOptionPane.showInputDialog("Informe o cpf");
        boolean resultado = validarCpf(cpf);
        if (resultado == true)
            JOptionPane.showMessageDialog(null, "Válido");
        else
            JOptionPane.showMessageDialog(null, "Inválido");
    }
    
    private static void formuarioThiago(){
        String cpf = JOptionPane.showInputDialog("Informe o cpf");
        if (validarCpf(cpf) == true)
            JOptionPane.showMessageDialog(null, "Válido");
        else
            JOptionPane.showMessageDialog(null, "Inválido");
    }
    
    private static void formularioRafael(){
        String cpf = JOptionPane.showInputDialog("Informe o cpf");
        if (validarCpf(cpf))
            JOptionPane.showMessageDialog(null, "válido");
        else 
            JOptionPane.showMessageDialog(null, "Inválido");
    }
    
    
    private static boolean validarCpf(String cpf){
        if (cpf.equals("123"))
            return true;
        else
            return false;
    }
    
}
