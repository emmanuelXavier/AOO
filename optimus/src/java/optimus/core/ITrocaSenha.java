/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package optimus.core;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author OPTIMUS
 */
public interface ITrocaSenha extends IValidacao{
    public int trocarSenha(String antigaSenha, String novaSenha, int id, HttpServletRequest request);

}
