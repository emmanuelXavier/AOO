/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package optimus.core;

import java.sql.Connection;
import java.sql.SQLException;
import optimus.core.dao.TConcretDAO;
import optimus.core.dao.TGenericDAO;

/**
 * TTransacao - classe responsavel pelo controle transacional dos objetos
 * @author PR0GR@MM3R
 */
public class TTransacao extends TGenericDAO {

    private Connection conn;

    public TTransacao() {
        TConcretDAO dao = new TConcretDAO();
        this.conn = dao.getCon();
    }

    public TTransacao(boolean setAutoCommit) throws SQLException{
        this.setAutoCommit(setAutoCommit);
    }

    //commit - grava dados fisicamente no banco
    public boolean commit() throws SQLException {
     //       this.conn.commit();
            return true;
    }

   //desfaz alterações em caso de erro
    public boolean rollback() throws SQLException {
       //     this.conn.rollback();
            return true;
    }

    // define se o objeto vai comitar no banco automaticamente
    public void setAutoCommit(boolean autocommit) throws SQLException {
            this.conn.setAutoCommit(autocommit);
    }
}
