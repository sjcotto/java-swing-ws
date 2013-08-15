/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;

/**
 *
 * @author gonzalo
 */
public class FiltroRangoDividendosEmpate extends DataFiltro{
    private float rangoInicial;
    private float rangoFinal;

    public FiltroRangoDividendosEmpate(float rangoI, float rangoF) {
        this.rangoInicial = rangoI;
        this.rangoFinal = rangoF;
    }

    public boolean cumpleFiltro (DataPartido dataP) {
        Dividendos div = dataP.getDividendos();
        // Se tiene que cumplir que los 3 div estan en el rango
        float divEmp = div.getDividendoEmpate();

        return (divEmp >= this.rangoInicial && divEmp <=this.rangoFinal);
    }

    public float getRangoInicial(){
        return rangoInicial;
    }
    
    public float getRangoFinal(){
        return rangoFinal;
    }

    public void setRangoInicial(float r){
        rangoInicial=r;
    }

    public void setRangoFinal(float r){
        rangoFinal=r;
    }
}
