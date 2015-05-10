 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package optimus.core;


/**
 *
 * @author Administrator
 */
public class TStrategyContexto {

    private IStrategy strategySelected;
    private String strategy;

    public TStrategyContexto(String estrategia) {
        this.strategy = estrategia;
    }

    public IStrategy factoryStrategy() {

        try {
            
            this.strategySelected = (IStrategy) (Class.forName(this.strategy)).newInstance();
            return this.strategySelected;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
