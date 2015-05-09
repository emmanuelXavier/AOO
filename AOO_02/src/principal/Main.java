package principal;

import javax.swing.JOptionPane;


public class Main {

    public static void main(String[] args) {
        formularioJackson();
        formularioTiago();
        formularioRafael();
    }
    
    private static void formularioJackson(){
        Pessoa objeto = new Pessoa();
        objeto.setCpf(JOptionPane.showInputDialog("Infome o cpf"));
    }
    
    private static void formularioTiago(){
        Pessoa objeto = new Pessoa();
        objeto.setCpf(JOptionPane.showInputDialog("Infome o cpf"));
    }
        
    private static void formularioRafael(){
        Pessoa objeto = new Pessoa();
        objeto.setCpf(JOptionPane.showInputDialog("Infome o cpf"));
    }
    
}
