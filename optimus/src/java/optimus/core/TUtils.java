/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package optimus.core;

import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.NumberFormatter;
import optimus.core.dao.TConcretDAO;

/**
 * TUtils - Classe de utilidades
 * @author Administrador
 */
public class TUtils {

    //função para criptografar senha
    @SuppressWarnings("static-access")
    public static String Cripto(String senha) {

        TCripto crpt = new TCripto(senha);
        return crpt.getHash();
    }

    /*
     * getTime - obtem a data e hora do sistema
     */
    public static String getTime() {
        Calendar cal = Calendar.getInstance();
        DateFormat dt = null;
        dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dt.format(cal.getTime());
    }

    public static String formatoAcesso(Date data) {
        DateFormat dt = new SimpleDateFormat("dd/MM/yy - HH:mm");
        return dt.format(data);
    }

    /*
     * getAno - retorna somente os digitos do ano no formato dd/mm/aaaa
     * @author Nonilton
     */
    public static String getAno() {
        String ano;
        ano = getDate();
        ano = (String) formataData(ano, 0);
        return ano.substring(6, 10);
    }

    public static String getsemestre() {
        String ano;
        int semestre;

        ano = getDate();
        ano = (String) formataData(ano, 0);

        semestre = Integer.parseInt(ano.substring(3, 5));
        if (semestre <= 6) {
            return 1 + "";
        } else {
            return 2 + "";
        }

    }
    //retorna a data do sistema

    public static String getDate() {
        Calendar cal = Calendar.getInstance();
        DateFormat dt = null;
        dt = new SimpleDateFormat("yyyy-MM-dd");
        return dt.format(cal.getTime());
    }
    //retorna a data do sistema para o boleto

    public static String getDateBoleto() {
        Calendar cal = Calendar.getInstance();
        DateFormat dt = null;
        dt = new SimpleDateFormat("dd/MM/yyyy");
        return dt.format(cal.getTime());
    }

    public static String getDateVencimeto() {
        Calendar cal = Calendar.getInstance();
        DateFormat dt = null;
        dt = new SimpleDateFormat("dd/MM/yyyy");
        cal.add(Calendar.DATE, 5);
        return dt.format(cal.getTime());

    }

    public static String setDateVencimeto(String dataVencimento) {
        Calendar cal = Calendar.getInstance();
        DateFormat dt = null;
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date data;
        try {
            data = (Date) formatter.parse(dataVencimento);

            cal.setTime(data);

            dt = new SimpleDateFormat("dd/MM/yyyy");
            cal.add(Calendar.DATE, 1);
        } catch (ParseException ex) {
            Logger.getLogger(TUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt.format(cal.getTime());

    }

    public static boolean geraSequence(String tabela) {
        TConcretDAO dao = new TConcretDAO();
        return dao.geraSequenceBoleto(tabela);

    }

    //formata valor monetarios - Nonilton Alves
    public String formataValor(String val) {
        String zeros = "0000000000";
        DefaultFormatter formatter = new NumberFormatter(new DecimalFormat("#,##0.00"));
        String valor = "";

        try {
            valor = formatter.valueToString(new Double(val));
        } catch (Exception ex) {
        }

        valor = valor.replace(",", "").replace(".", "");
        String valorTitulo = zeros.substring(0, zeros.length() - valor.length()) + valor;
        return valorTitulo;
    }
    // formataData - Formatada um valor data para o formato dd/MM/aaaa
    // Nonilton Alves

    public String formataData(String val) {
        String dia, mes, ano;

        dia = val.substring(0, 2);
        mes = val.substring(2, 4);
        ano = val.substring(4, 8);

        return dia + "/" + mes + "/" + ano;

    }
    // formataData - Formatada um valor data para o formato yyyy-MM-dd
    // Carlos

    public String getFormataData(String val) {
        String dia, mes, ano;

        dia = val.substring(0, 1);
        mes = val.substring(2, 4);
        ano = val.substring(6, 10);

        return ano + "-" + mes + "-" + dia;

    }

    public static String formataData(String val, int i) {
        String data = "";
        if ((val.equals("")) == false && (val != null)) {
            String dia, mes, ano;
            dia = val.substring(8, 10);
            mes = val.substring(5, 7);
            ano = val.substring(0, 4);
            data = dia + "/" + mes + "/" + ano;
        }

        return data;

    }
    //retorna a data do sistema

    public static String getFormatDate(String data) throws Exception {
        Calendar cal = Calendar.getInstance();
        DateFormat dt = null;
        dt = new SimpleDateFormat("yyyy-MM-dd");
        return dt.format(TUtils.formatacaoData(data));
    }

    /** 
     * Converte uma String para um objeto Date. Caso a String seja vazia ou nula,  
     * retorna null - para facilitar em casos onde formulários podem ter campos 
     * de datas vazios. 
     * @param data String no formato dd/MM/yyyy a ser formatada 
     * @return Date Objeto Date ou null caso receba uma String vazia ou nula 
     * @throws Exception Caso a String esteja no formato errado 
     */
    public static java.util.Date formatacaoData(String data) throws Exception {
        if (data == null || data.equals("")) {
            return null;
        }
        Date date = null;

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        date = (java.util.Date) formatter.parse(data);

        return date;
    }

    public static Date formataDate(String data) throws Exception {
        if (data == null || data.equals("")) {
            return null;
        }

        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = new java.sql.Date(((java.util.Date) formatter.parse(data)).getTime());
        } catch (ParseException e) {
            throw e;
        }
        return date;
    }

    public static Calendar setCalendar(String data) {
        Date date = null;
        Calendar cal = null;
        SimpleDateFormat sdf = null;
        try {
            sdf = new SimpleDateFormat("dd/MM/yyyy");
            date = (Date) sdf.parse(data);
            cal = Calendar.getInstance();
            cal.setTime(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;

    }

    public static String getDateVencimetoServico(String dataVencimento, int i) throws ParseException {


        String dtVenc = dataVencimento;
        System.out.println("TUtils:" + dtVenc);
        DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        Date data = dt.parse(dtVenc);
        cal.setTime(data);
        cal.add(Calendar.MONTH, i);
        return dt.format(cal.getTime());

    }

    public static String getDateCompetencia(String dataVencimento) throws ParseException {
        DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        Date data = dt.parse(dataVencimento);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(data);
        cal2.set(Calendar.DATE, 1);
        cal2.set(Calendar.DAY_OF_MONTH, cal2.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal2.set(Calendar.HOUR_OF_DAY, 23);
        cal2.set(Calendar.MINUTE, 59);
        cal2.set(Calendar.SECOND, 59);
        cal2.set(Calendar.MILLISECOND, 999);
        return dt.format(cal2.getTime());
    }

    public static String getDateVencimetoServicoSimples() throws ParseException {

        Calendar cal = Calendar.getInstance();
        DateFormat dt = null;
        dt = new SimpleDateFormat("dd/MM/yyyy");
        cal.add(Calendar.DATE, 15);
        return dt.format(cal.getTime());


    }
}


