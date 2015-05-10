/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package optimus.core.dao;

import optimus.core.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 * TGeneric - classe para gerar funções de manipulação de dados generica.
 * @author Administrator
 */
public class TGenericDAO implements IStrategy {

    public final boolean insert(String tabela, HttpSession session) {
        try {
            TConcretDAO dao = new TConcretDAO();
            return dao.insert(this, tabela, session);
        } catch (SQLException ex) {
            Logger.getLogger(TGenericDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public final boolean update(String tabela, TFilter filter, HttpSession session) throws SQLException {
        TConcretDAO dao = new TConcretDAO();
        return dao.update(this, tabela, filter.getFiltro(), session);
    }

    public final boolean delete(String tabela, TFilter filter, HttpSession session) throws SQLException {
        TConcretDAO dao = new TConcretDAO();
        return dao.delete(tabela, filter.getFiltro(), session);
    }

    public final Object select(String tabela, TFilter filter, String limite) throws SQLException, IllegalArgumentException {
        try {
            TConcretDAO dao = new TConcretDAO();
            return dao.select(this, tabela, filter.getFiltro(), limite);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(TGenericDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public final List select(TFilter filter, String tabela, String limite) throws SQLException, IllegalArgumentException {
        try {
            TConcretDAO dao = new TConcretDAO();
            return dao.select(tabela, this, filter.getFiltro(), limite);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(TGenericDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @SuppressWarnings("static-access")
    public final String Cripto(String senha) {
        TUtils util = new TUtils();
        return util.Cripto(senha);
    }

    public final ResultSet selectmax(String tabela, String campo, TFilter filter) {
        try {
            TConcretDAO dao = new TConcretDAO();
            return dao.selectmax(tabela, campo, filter.getFiltro());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public final ResultSet selectCount(String tabela, String campo, TFilter filter) {
        try {

            TConcretDAO dao = new TConcretDAO();
            return dao.selectCount(tabela, campo, filter.getFiltro());
        } catch (SQLException ex) {
            Logger.getLogger(TGenericDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public final String getSequence(String sequence) {
        try {
            TConcretDAO dao = new TConcretDAO();
            return dao.getSequence(sequence);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;

        }
    }

    public final String getLastValue(String sequence) {
        try {
            TConcretDAO dao = new TConcretDAO();
            return dao.getLastValue(sequence);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;

        }
    }

    public final String getNextVal(String sequence){
        try {
            TConcretDAO dao = new TConcretDAO();
            return dao.getNextVal(sequence);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;

        }
    }
    
    public final String totalResultSet(String tabela) {
        try {
            TConcretDAO dao = new TConcretDAO();
            return dao.totalResultSet(tabela);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;

        }
    }
}
