/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package optimus.core.conn;

/**
 *TInstrucaoPostgres - Gera instruções sql para o banco postgres
 * @author Administrator
 */
public class TInstrucaoPostgres extends TAbstractInstrucoes {

    @Override
    public String Select(String tabela, String[] campos, String filtro, String limite) {
        String sql = " select ";
        int ct = campos.length - 1;

        for (int i = 0; i < campos.length; i++) {
            sql += campos[i].toString();
            if (i < ct) {
                sql += ", ";
            }
        }

        sql += " from " + tabela;
        if (!filtro.equals("") || filtro != null) {
            sql += filtro;
        }

        if (!limite.equals("") || limite != null) {
            sql += limite;
        }/*else
        {
        sql += " limit 100 ";
        }
         */ //limite foi retirado para testes de desempenho Nonilton 03/07/2008

        return sql.toLowerCase();
    }

    @Override
    public String Insert(String tabela, String[] campos, String[] valores) {
        String sql = " insert into ";
        sql += tabela;
        sql += " (";

        int ct = campos.length - 1;

        /*
         * o laço abaixo inicia-se do 1 devido a uma restrição do banco postgre
         * que não incrementa automatica o campo serial se o mesmo for declarado na 
         * instrução sql.
         * Nonilton Alves 14/05/2008
         * 
         */

        for (int i = 1; i < campos.length; i++) {
            sql += campos[i].toString();
            if (i < ct) {
                sql += ", ";
            }
        }
        sql += ") ";

        sql += " values (";

        for (int i = 1; i < valores.length; i++) {
            sql += "'" + valores[i].toString() + "'";

            if (i < ct) {
                sql += ", ";
            }
        }

        sql += ") ";

        return sql.toLowerCase();

    }

    @Override
    public String Update(String tabela, String[] campos, String[] valores, String filtro) {
        String sql = "update " + tabela + " set ";

        int ct = campos.length - 1;
        for (int i = 0; i < campos.length; i++) {
            sql += campos[i].toString() + " = " + valores[i].toString();
            if (i < ct) {
                sql += ", ";
            }

        }

        if (!filtro.equals("") || filtro != null) {
            sql += filtro;
        }

        return sql.toLowerCase();
    }

    @Override
    public String Delete(String tabela, String filtro) {
        String sql = " delete from " + tabela;
        if (!filtro.equals("") || filtro != null) {
            sql += filtro;
        }

        return sql.toLowerCase();
    }

    @Override
    public String Selectmax(String tabela, String campo, String filtro) {
        String sql = " select max(" + campo + ") as id from " + tabela;
        if (!filtro.equals("") || filtro != null) {
            sql += filtro;
        }

        return sql;
    }

    @Override
    public String selectCount(String tabela, String campo, String filtro) {

        String sql = " select count(id) as id," + campo + " from " + tabela;
        if (!filtro.equals("") || filtro != null) {
            sql += filtro;
        }

        sql += " group by " + campo;

        if (tabela.equals("mav019") || tabela.equals("mav012") || tabela.equals("mav032") || tabela.equals("mav029")) {


            sql = " select count(distinct(idhorario)) as idhorario, data, assunto from " + tabela;


            if (!filtro.equals("") || filtro != null) {
                sql += filtro;
            }

            sql += " group by " + campo;

        }
        System.out.print(sql);
        return sql;
    }

    @Override
    public String getSequence(String sequence) {
        String sql = " select currval('" + '"' + sequence + '"' + "') as sequence ";
        //System.out.println(sql);
        return sql;
    }

    @Override
    public String getNextVal(String sequence) {
        String sql = " select nextval('" + '"' + sequence + '"' + "') as sequence ";
        // System.out.println(sql);
        return sql;
    }

    @Override
    public String getLastValue(String sequence) {
        String sql = " select last_value from " + sequence + " as sequence ";
        // System.out.println(sql);
        return sql;
    }

    @Override
    public String totalResultSet(String tabela) {
        String sql = " select count(*) from " + tabela;
        return sql;
    }
}
