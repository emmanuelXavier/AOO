/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package optimus.core;
    /*
     * classe TFilter - gera filtros de instruções sql
     * @author Administrator
     * @criterio[] - Vetor de campos criterios
     * @campo[]    - Vetor de campos do filtro
     * @operador   - Vetor de operadores
     * @valor      - Vetor de objetos valores
     */
public class TFilter {
    private String[] criterio;
    private String[] campo;
    private String[] operador;
    private String[] valor;
    
    public TFilter(String[] criterio, String[] campo, String[] operador, Object[] valor) {
        this.criterio = criterio;
        this.campo    = campo;
        this.operador = operador;
        this.valor    = getTipo(valor);
    }
    
    public TFilter() {
        String[] criterio = new String[1];
        String[] campo = new String[1];
        String[] operador  = new String[1];
        String[] valor = new String[1];
        
        criterio[0] = " ";
        campo[0] = " ";
        operador[0] = " ";
        valor[0] = " ";
                
        this.criterio = criterio;
        this.campo    = campo;
        this.operador = operador;
        this.valor    = valor;
    }

    private String[] getTipo(Object[] valor) {
        return (String[]) valor;
    }

    public String getFiltro() {
        String sfilter = null;

        for (int i = 0; i < this.criterio.length; i++) {
            if (i < 1) {
                sfilter = this.criterio[i] + this.campo[i] + this.operador[i] + this.valor[i];
            } else {
                sfilter +=this.criterio[i] + this.campo[i] + this.operador[i] + this.valor[i];
            }
        }

        
        return sfilter;
    }
}
