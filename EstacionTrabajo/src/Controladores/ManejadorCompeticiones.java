/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import java.util.Map;
import java.util.HashMap;
import Objetos.Competicion;


/**
 *
 * @author gonzalo
 */
public class ManejadorCompeticiones {
    // ATRIBUTOS
    private Map<Integer,Competicion> competiciones;
    private int idCompetencia;
    
    // Singleton
    private static ManejadorCompeticiones instanciaMC = null;

    // SINGLETON
    public static ManejadorCompeticiones getInstance(){
	if (instanciaMC == null)
	    instanciaMC = new ManejadorCompeticiones();
	return instanciaMC;
    }

    private ManejadorCompeticiones() {
        this.competiciones  = new HashMap<Integer,Competicion>();
        this.idCompetencia = 1;
    }

    public Map<Integer,Competicion> getCompeticiones() {
        return this.competiciones;
    }

    public int getIdCompetencia() {
        return this.idCompetencia;
    }

    public void setIdCompetencia(int id) {
        this.idCompetencia = id;
    }
}
