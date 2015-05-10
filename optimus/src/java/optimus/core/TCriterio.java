/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package optimus.core;

import java.util.ArrayList;
import java.util.List;

/**
 * TCriterio - Cria criterios para strings sql
 * @param campo = nome do campo que vai compor o filter
 * @param operador = tipo de operador que vai compor o filter
 * @param valor = valor do campo para compor o filter

 * Observação - agora é possivel passar o operador OR ou or para o objeto TCriterio para operação com subselects
 * @author OPTIMUS
 */
public class TCriterio {

    private List<String> campo = new ArrayList<String>();
    private List<String> operador = new ArrayList<String>();
    private List<Object> valor = new ArrayList<Object>();

    public void add(String campo, String operador, Object valor) {
        this.campo.add(campo);
        this.operador.add(operador);
        this.valor.add(valor);
    }

    public TFilter getFilter() {

        String[] crit = new String[this.campo.size()];
        String[] camp = new String[this.campo.size()];
        String[] oper = new String[this.campo.size()];
        String[] val = new String[this.campo.size()];

        for (int i = 0; i < this.campo.size(); i++) {
            if (i == 0) {
                crit[i] = " where ";
            } else {
                crit[i] = " and ";
            }
            camp[i] = this.campo.get(i);
            oper[i] = this.operador.get(i);
            val[i] = (String) this.valor.get(i);
        }

        return new TFilter(crit, camp, oper, val);
    }
}
