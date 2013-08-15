/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;

/**
 *
 * @author gonzalo
 */
public class DataInfoPartido implements Comparable {
    // ATRIBUTOS
    private int idPar;
    private int idComp;
    private String nomComp;
    private TipoCompeticion tipoC;
    private String nomLocal;
    private String nomVisita;
    private int idLocal;
    private int idVisita;
    private String lugar;
    private DataFechaHora fechaHora;
    private String nomLlave;
    private String nomLlavePreLoc;
    private String nomLlavePreVis;
    private boolean tercer;

    public DataInfoPartido() {}

    public DataInfoPartido (int idPartido, int idCompeticion, String nombCompeticion,
                            TipoCompeticion tipoCompeticion, String nomEqL,
                            String nomEqV, int idEqL, int idEqV, String lug,
                            DataFechaHora dfh, String nomLl, String nomLlPreL,
                            String nomLlPreV) {
        idPar = idPartido;
        idComp = idCompeticion;
        nomComp = nombCompeticion;
        tipoC = tipoCompeticion;
        nomLocal = nomEqL;
        nomVisita = nomEqV;
        idLocal = idEqL;
        idVisita = idEqV;
        lugar = lug;
        fechaHora = dfh;
        nomLlave = nomLl;
        nomLlavePreLoc = nomLlPreL;
        nomLlavePreVis = nomLlPreV;

    }
    
    public DataInfoPartido (int idPartido, int idCompeticion, String nombCompeticion,
                            TipoCompeticion tipoCompeticion, String nomEqL,
                            String nomEqV, int idEqL, int idEqV, String lug,
                            DataFechaHora dfh, String nomLl, String nomLlPreL,
                            String nomLlPreV, boolean tercer) {
        idPar = idPartido;
        idComp = idCompeticion;
        nomComp = nombCompeticion;
        tipoC = tipoCompeticion;
        nomLocal = nomEqL;
        nomVisita = nomEqV;
        idLocal = idEqL;
        idVisita = idEqV;
        lugar = lug;
        fechaHora = dfh;
        nomLlave = nomLl;
        nomLlavePreLoc = nomLlPreL;
        nomLlavePreVis = nomLlPreV;
        this.tercer = tercer;

    }

    // OPERACIONES
    public int getIdPar() {
        return idPar;
    }
    public int getIdComp() {
        return idComp;
    }
    public String getNomComp() {
        return nomComp;
    }
    public TipoCompeticion getTipoC() {
        return tipoC;
    }
    public String getNomLocal() {
        return nomLocal;
    }
    public String getNomVisita() {
        return nomVisita;
    }
    public int getIdLocal() {
        return idLocal;
    }
    public int getIdVisita() {
        return idVisita;
    }
    public String getLugar() {
        return lugar;
    }
    public DataFechaHora getFechaHora() {
        return fechaHora;
    }
    public String getNomLlave() {
        return nomLlave;
    }
    public String getNomLlavePreLoc() {
        return nomLlavePreLoc;
    }
    public String getNomLlavePreVis() {
        return nomLlavePreVis;
    }

    public boolean getEsTercerPuesto(){
        return this.tercer;
    }

    public boolean getTercer(){
        return tercer;
    }

    public int compareTo (Object d){
        DataInfoPartido dp = (DataInfoPartido) d;
        return this.getFechaHora().compareTo(dp.getFechaHora());
    }

    public void setIdPar(int i){
        idPar= i;
    }

    public void setIdComp(int i){
        idComp= i;
    }

    public void setIdLocal(int i){
        idLocal=i;
    }

    public void setIdVisita(int i){
        idVisita=i;
    }

    public void setFechaHora(DataFechaHora f){
        fechaHora=f;
    }

    public void setNomLlavePreLoc(String nom){
        nomLlavePreLoc=nom;
    }
    public void setNomLlavePreVis(String nom){
        nomLlavePreVis=nom;
    }

    public void setTercer(boolean b){
        tercer=b;
    }

    public void setNomLocal(String nom){
        nomLocal=nom;
    }

    public void setNomVisita(String nom){
        nomVisita=nom;
    }

    public void setNomComp(String nom){
        nomComp=nom;
    }

    public void setLugar(String l){
        lugar=l;
    }

    public void setTipoC(TipoCompeticion c){
        tipoC=c;
    }

    public void setNomLlave(String nomLl) {
        this.nomLlave = nomLl;
    }
}

