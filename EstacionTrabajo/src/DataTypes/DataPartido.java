/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTypes;

import java.util.List;

public class DataPartido implements Comparable {
    private DataInfoPartido dataInfoPart;
    private Dividendos dividendos;
    private boolean estaFinalizado;
    private int golesL;
    private int golesV;
    private int penalesL;
    private int penalesV;
    private java.util.Map<GolesKey,Float> golesDivs;
    private List<DataTypes.DataEvento> eventos;
    private List<Integer> jugLocales;
    private List<Integer> jugVisitante;
    private List<DataApuestaPersistencia> apuestas;

    public DataPartido() {}

    public DataPartido(DataInfoPartido dataIP, Dividendos div, boolean fin,
                        int golesLocal, int golesVisita, int penL, int penV,
                        List<Integer> jL,List<Integer>jV,List<DataApuestaPersistencia> apuestas,
                        java.util.Map<GolesKey,Float> golesDiv){
        dataInfoPart = dataIP;
        dividendos = div;
        estaFinalizado = fin;
        golesL = golesLocal;
        golesV = golesVisita;
        this.penalesL=penL;
        this.penalesV=penV;
        this.jugLocales=jL;
        this.jugVisitante=jV;
        this.apuestas=apuestas;
        this.golesDivs=golesDiv;
        this.eventos = new java.util.ArrayList<DataTypes.DataEvento>();
    }

    public DataPartido(DataInfoPartido dataIP, Dividendos div, boolean fin,
                        int golesLocal, int golesVisita){
        dataInfoPart = dataIP;
        dividendos = div;
        estaFinalizado = fin;
        golesL = golesLocal;
        golesV = golesVisita;
        this.penalesL=-1;
        this.penalesV=-1;

        this.eventos = new java.util.ArrayList<DataTypes.DataEvento>();
    }

    public DataPartido(DataInfoPartido dataIP, Dividendos div) {

        this.dataInfoPart=dataIP;
        this.dividendos=div;
        estaFinalizado = true;
        golesL = -1;
        golesV = -1;
        this.penalesL=-1;
        this.penalesV=-1;

        this.eventos = new java.util.ArrayList<DataTypes.DataEvento>();
    }

    public DataPartido(DataInfoPartido dataIP, Dividendos div, boolean fin,
                        int golesLocal, int golesVisita, int penL, int penV){
        dataInfoPart = dataIP;
        dividendos = div;
        estaFinalizado = fin;
        golesL = golesLocal;
        golesV = golesVisita;
        this.penalesL=penL;
        this.penalesV=penV;

        this.eventos = new java.util.ArrayList<DataTypes.DataEvento>();
    }


    public DataInfoPartido getDataInfoPart () {
        return dataInfoPart;
    }
    public Dividendos getDividendos () {
        return dividendos;
    }
    public boolean getEstaFinalizado () {
        return estaFinalizado;
    }
    public int getGolesL () {
        return golesL;
    }
    public int getGolesV () {
        return golesV;
    }

    public int getPenalesL () {
        return this.penalesL;
    }
    public int getPenalesV () {
        return this.penalesV;
    }
    
    public List<DataApuestaPersistencia> getApuestas(){
        return this.apuestas;
    }

    public java.util.Map<GolesKey,Float> getGolesDivs(){
        return this.golesDivs;
    }

    public List<DataEvento> getEventos(){
        return this.eventos;
    }
    public List<Integer> getJugLocales() {
        return this.jugLocales;
    }
    public List<Integer> getJugVisitante(){
        return this.jugVisitante;
    }



    public void setDataInfoPart(DataInfoPartido d){
        dataInfoPart=d;
    }

    public void setDividendos(Dividendos div){
        dividendos=div;
    }

    public void setEstaFinalizado(boolean f){
        estaFinalizado=f;
    }

    public void setGolesL(int g){
        golesL=g;
    }

    public void setGolesV(int g){
        golesV=g;
    }

    public void setPenalesL(int g){
        penalesL=g;
    }

    public void setPenalesV(int g){
        penalesV=g;
    }

    public void setEventos(List<DataTypes.DataEvento> l){
        this.eventos=l;
    }

    public void setApuestas(List<DataApuestaPersistencia> l) {
        this.apuestas = l;
    }

    public void setGolesDivs(java.util.Map<GolesKey,Float> m) {
        this.golesDivs = m;
    }

    public void setJugLocales(List<Integer> l) {
        this.jugLocales = l;
    }

    public void setJugVisitante(List<Integer> l) {
        this.jugVisitante = l;
    }


    public int compareTo (Object d){
        DataPartido dp = (DataPartido) d;
        return this.getDataInfoPart().getFechaHora().compareTo(dp.getDataInfoPart().getFechaHora())*(-1);
    }
}
