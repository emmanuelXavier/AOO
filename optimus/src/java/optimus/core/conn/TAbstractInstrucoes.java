/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package optimus.core.conn;
import optimus.core.*;

/**
 * 
 * @author Administrator
 */
public abstract class TAbstractInstrucoes implements IStrategy {
   /* @Select - Retorna a instrução SQL select para o execução no banco de dados
    * @param tabela - nome da tabela
    * @param campos - vetor de campos a serem retornados
    * @param filtro - String com as opções de filtro para a instrução sql
    * */
    public abstract String Select(String tabela, String[] campos,String filtro, String limite);
    /*@Insert - Retorna a instrução SQL insert para o execução no banco de dados
    * @param tabela - nome da tabela
    * @param campos - vetor de campos a serem retornados
    * @param valores - vetor de valores dos campos da instrução sql
    * */
    public abstract String Insert(String tabela, String[] campos, String[] valores);
    /*@Update - Retorna a instrução SQL update para o execução no banco de dados
    * @param tabela - nome da tabela
    * @param campos - vetor de campos a serem retornados
    * @param valores - String com os valores dos campos da instrução sql
     *@param filtro  - String com as opções de filtro para a instrução sql
    * */
    public abstract String Update(String tabela, String[] campos, String[] valores, String filtro);
    /*@Delete - Retorna a instrução SQL para o execução no banco de dados
    * @param tabela - nome da tabela
    * @param filtro - String com as opções de filtro para a instrução sql
    * */
    public abstract String Delete(String tabela, String filtro);
    /*
     * Selectmax - Retorna o maximo valor de um campo de uma determinada tabela
     * @tabela - nome da tabela
     * @campo - campo que se deseja obter o maximo valor
     * @filtro - String com as opções de filtro para a instrução sql
     */ 
    public abstract String Selectmax(String tabela, String campo, String filtro);
    /*
     * SelectCount - Retorna o numero de registros do retorno
     * @tabela - nome da tabela
     * @filtro - String com as opções de filtro para a instrução sql
     */ 
    public abstract String selectCount(String tabela, String campo, String filtro);
    
    /*
     * getSequence - Retorna o id do objeto sequence do banco de dados
     * @sequence - nome do objeto sequence no banco
     */
    public abstract String getSequence(String sequence);
    public abstract String getNextVal(String sequence);
    
    public abstract String getLastValue(String sequence);
    
    public abstract String totalResultSet(String tabela);
}

