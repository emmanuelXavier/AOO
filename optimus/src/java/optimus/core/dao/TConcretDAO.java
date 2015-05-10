/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package optimus.core.dao;

import java.net.UnknownHostException;
import optimus.core.conn.TAbstractInstrucoes;
import optimus.core.conn.TConnectionPostgreSql;
import optimus.core.*;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.Timestamp;
import javax.servlet.http.HttpSession;
import optimus.core.beans.TLogAcesso;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * TConcretDAO - classe de concreta para gerar instruções sql no banco
 * @author Administrator
 */
public class TConcretDAO {

    private TStrategyContexto contexto;
    private TAbstractInstrucoes sql;
    private String[] campos;
    private String[] valores;
    private Field[] fields;
    private Connection con = null;
    private TConnectionPostgreSql connection;
    private String iSQL = null; //instrução sql

    public TConcretDAO() {
        getInstrucoes();
        try {
            connection = new TConnectionPostgreSql();
            con = connection.getSingleConnection();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    protected void getInstrucoes() {
        contexto = new TStrategyContexto("optimus.core.conn.TInstrucaoPostgres");
        sql = (TAbstractInstrucoes) contexto.factoryStrategy();
    }

    public boolean insert(Object objeto, String tabela, HttpSession session) throws SQLException, IllegalArgumentException, IllegalArgumentException {
        try {
            //vetor de string com total de atributos da classe
            campos = new String[objeto.getClass().getDeclaredFields().length];
            valores = new String[campos.length];
            //vertor de atributos do objeto
            fields = objeto.getClass().getDeclaredFields();

            for (int i = 1; i < campos.length; i++) {
                fields[i].setAccessible(true);
                campos[i] = fields[i].getName();

                //verifica se o atributo do objeto esta nulo
                if (fields[i].get(objeto) != null) {
                    valores[i] = (String) fields[i].get(objeto).toString();
                } else {
                    String tipo = fields[i].getType().getName();
                    if (tipo.equals("int")) {
                        valores[i] = "0";
                    } else if (tipo.equals("java.lang.String")) {
                        valores[i] = "";
                    } else if (tipo.equals("Double")) {
                        valores[i] = "0";
                    } else if (tipo.equals("Float")) {
                        valores[i] = "0";
                    }

                }
            }

            this.iSQL = sql.Insert(tabela, campos, valores);
            //nonilton
           // System.out.println(this.iSQL);
            
            PreparedStatement st = this.con.prepareStatement(iSQL.toUpperCase());
            st.execute();
            st.close();

            this.insertLog(this.iSQL, tabela, session);

            return true;
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TConcretDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TConcretDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertLog(String sql, String tabela, HttpSession session) throws SQLException, IllegalArgumentException, IllegalArgumentException {
        try {
            TLogAcesso ac = new TLogAcesso();
            //pega o ip do host
            InetAddress adr = InetAddress.getLocalHost();

            String host = adr.getHostName();
            String ip = adr.getHostAddress();

            String usuario = session.getAttribute("usuario").toString();
            String grupo = session.getAttribute("grupo").toString();
            String data = ac.getTime();

            String nSQL = " insert into si015 (";
            nSQL += "grupo, usuario, data, tabela, instrucaosql, host, ip) values (";
            nSQL += "?,?,?,?,?,?,?) ";
            PreparedStatement st = this.con.prepareStatement(nSQL);

            st.setInt(1, Integer.parseInt(grupo));
            st.setInt(2, Integer.parseInt(usuario));
            st.setTimestamp(3, Timestamp.valueOf(data));
            st.setString(4, tabela.toUpperCase());
            st.setString(5, sql.toUpperCase());
            st.setString(6, host.toUpperCase());
            st.setString(7, ip);

            st.execute();
            st.close();

            return true;

        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return false;
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean update(Object objeto, String tabela, String filter, HttpSession session) throws SQLException, IllegalArgumentException, IllegalArgumentException {
        try {
            campos = new String[objeto.getClass().getDeclaredFields().length];
            valores = new String[campos.length];
            fields = objeto.getClass().getDeclaredFields();

            for (int i = 0; i < campos.length; i++) {
                fields[i].setAccessible(true);
                campos[i] = fields[i].getName();

                //verifica se o atributo do objeto esta nulo
                if (fields[i].get(objeto) != null) {
                    valores[i] = "'" + (String) fields[i].get(objeto).toString() + "'";
                } else {
                    valores[i] = "''";
                }

            }

            this.iSQL = sql.Update(tabela, campos, valores, filter);
            //nonilton
            //System.out.println(this.iSQL);
            
            PreparedStatement st = this.con.prepareStatement(iSQL.toUpperCase());
            st.execute();
            st.close();
            //log
            this.insertLog(this.iSQL, tabela, session);

            return true;
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TConcretDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TConcretDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet select(Object objeto, String tabela, String filter, String limite) throws SQLException, IllegalArgumentException, NoSuchFieldException {
        try {
            campos = new String[objeto.getClass().getDeclaredFields().length];
            fields = objeto.getClass().getDeclaredFields();

            for (int i = 0; i < campos.length; i++) {
                fields[i].setAccessible(true);
                campos[i] = fields[i].getName();
            }

            this.iSQL = sql.Select(tabela, campos, filter, limite);
            //nonilton
            System.out.println(this.iSQL.toUpperCase());
            PreparedStatement st = this.con.prepareStatement(iSQL.toUpperCase());
            return st.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public List select(String tabela, Object objeto, String filter, String limite) throws SQLException, IllegalArgumentException, NoSuchFieldException {
        try {
            campos = new String[objeto.getClass().getDeclaredFields().length];
            fields = objeto.getClass().getDeclaredFields();

            for (int i = 0; i < campos.length; i++) {
                fields[i].setAccessible(true);
                campos[i] = fields[i].getName();
            }

            this.iSQL = sql.Select(tabela, campos, filter, limite);
            //nonilton
            System.out.println(this.iSQL);
            
            PreparedStatement st = this.con.prepareStatement(iSQL.toUpperCase());

            ResultSet rs = st.executeQuery();

            // Cria a lista de objetos....
            List<Object> objgen = new ArrayList<Object>();

            String tipo = null;

            while (rs.next()) {
                //seta os valores do objeto
                Object obj;
                try {
                    obj = objeto.getClass().newInstance();

                    for (int i = 0; i < campos.length; i++) {
                        tipo = fields[i].getType().getName();

                        if (tipo.equals("int")) {
                            this.fields[i].set(obj, rs.getInt(this.campos[i]));
                        } else if (tipo.equals("java.lang.String")) {
                            this.fields[i].set(obj, rs.getString(this.campos[i]));
                        } else if (tipo.equals("Double")) {
                            this.fields[i].set(obj, rs.getDouble(this.campos[i]));
                        } else if (tipo.equals("Float")) {
                            this.fields[i].set(obj, rs.getFloat(this.campos[i]));
                        }

                        tipo = null;
                    }

                    objgen.add(obj);
                } catch (InstantiationException ex) {
                    Logger.getLogger(TConcretDAO.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(TConcretDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return objgen;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean delete(String tabela, String filter, HttpSession session) throws SQLException, IllegalArgumentException, IllegalArgumentException {
        try {
            this.iSQL = sql.Delete(tabela, filter);

           // System.out.println(this.iSQL);
            PreparedStatement st = this.con.prepareStatement(iSQL);
            st.execute();
            //log
            this.insertLog(this.iSQL, tabela, session);

            return true;
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TConcretDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet selectmax(String tabela, String campo, String filtro) throws SQLException {
        try {
            iSQL = sql.Selectmax(tabela, campo, filtro);
            //System.out.println(iSQL);
            PreparedStatement st = this.con.prepareStatement(iSQL);
            ResultSet rs = st.executeQuery();

            return rs;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet selectCount(String tabela, String campo, String filtro) throws SQLException {
        try {
            iSQL = sql.selectCount(tabela, campo, filtro);
            PreparedStatement st = this.con.prepareStatement(iSQL);
            ResultSet rs = st.executeQuery();
            return rs;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    //pega a sequence de qualquer objeto
    public String getSequence(String sequence) {
        try {
            iSQL = sql.getSequence(sequence);
            PreparedStatement st = this.con.prepareStatement(iSQL);
            String seq = null;

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                seq = rs.getString(1);
            }
            return seq;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public String getNextVal(String sequence) {
        try {
            iSQL = sql.getNextVal(sequence);
            PreparedStatement st = this.con.prepareStatement(iSQL);
            String seq = null;

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                seq = rs.getString(1);
            }
            return seq;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    //gera somente a sequencia para o boleto
    public boolean geraSequenceBoleto(String tabela) {
        try {
            iSQL = " insert into "+tabela+" (data) values (CURRENT_DATE) ";
            PreparedStatement st = this.con.prepareStatement(iSQL);
            return st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public Connection getCon() {
        return this.con;
    }
    //pega a sequence de qualquer objeto
    public String getLastValue(String sequence) {
        try {
            iSQL = sql.getLastValue(sequence);
            PreparedStatement st = this.con.prepareStatement(iSQL);
            String seq = null;

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                seq = rs.getString(1);
            }
            return seq;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
    
    public String totalResultSet(String tabela){
       try {
            iSQL = sql.totalResultSet(tabela);
            PreparedStatement st = this.con.prepareStatement(iSQL);
            String total = null;

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                total = rs.getString(1);
            }
            return total;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } 
    }
}
