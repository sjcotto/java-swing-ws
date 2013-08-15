package Objetos;

import DataTypes.DataEquipoLiga;

public class InfoEquipoLiga extends InfoEquipoCampeonato implements Comparable{

	//Ver Colecciones como atrubutos., tiene un Equipo como atrubuto
	//Atributos
	private int puntos=0;
        private int jugados=0,ganados=0,empatados=0,perdidos=0;
        private int golesAFavor=0,golesEnContra=0;
        private int posicion;

        // NUEVO!!
        private char comparar;

        public void setCompararPor (char comp){
            this.comparar=comp;
        }
        // NUEVO!!

	//Contructores
	public InfoEquipoLiga(float dc,Equipo equipo, Campeonato c) {
            super(dc,equipo,c);
	}

	//Funciones get y set
        public int getPuntos(){
            return this.puntos;
        }
        
        public int getDiferencia(){
            return golesAFavor-golesEnContra;
        }

        public int getGolesAFavor(){
            return golesAFavor;
        }

        public int getGolesEnContra(){
            return this.golesEnContra;
        }

        

        @Override
        public int compareTo (Object i){
            if(this.comparar=='p'){//puntos
                 InfoEquipoLiga iEL = (InfoEquipoLiga) i;
                 if (this.puntos> iEL.getPuntos()){
                    return 1;
                 }
                 if (this.puntos< iEL.getPuntos()){
                    return -1;
                 }
            }   
            if(this.comparar=='d'){//diferencia
                 InfoEquipoLiga iEL = (InfoEquipoLiga) i;
                 if (this.getDiferencia()> iEL.getDiferencia()){
                    return 1;
                 }
                 if (this.getDiferencia()< iEL.getDiferencia()){
                    return -1;
                 }
            }      
            if(this.comparar=='g'){//goles a favor
                 InfoEquipoLiga iEL = (InfoEquipoLiga) i;
                 if (this.getGolesAFavor()> iEL.getGolesAFavor()){
                    return 1;
                 }
                 if (this.getGolesAFavor()< iEL.getGolesAFavor()){
                    return -1;
                 }
            }   
            return 0;
         }


        public int getPosicion(){
            return this.posicion;
        }
        
        // TABLA DE POSICIONES
        public void actualizarPuntos(int aFavor,int enContra){
            ++jugados;
            golesAFavor+=aFavor;
            golesEnContra+=enContra;
            if(aFavor>enContra){
                ++ganados;
                puntos+=3;
            }
            if(aFavor<enContra){
                ++perdidos;
            }
            if(aFavor==enContra){
                ++empatados;
                ++puntos;
            }
        }

        public void cambiarPosicion(int pos){
           posicion=pos;
        }

        public DataEquipoLiga getDataEquipoLiga() {
            //DataEquipoLiga (int idEq, String nomEq, int pos, int puntos,
            //int partEmp, int partGan, int partPer, int golF, int golC) {
            return new DataEquipoLiga(this.e.getId(),this.e.getNombre(),
                    this.posicion, this.puntos, this.empatados, this.ganados,
                        this.perdidos,this.golesAFavor, this.golesEnContra);
        }
}


