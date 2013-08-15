/// PAQUETE APUESTAS

package Objetos;

import java.util.List;
import java.util.ArrayList;
import DataTypes.DataPaqueteApuestas;
import DataTypes.DataApuesta;
import DataTypes.EstadoPaqueteApuestas;
import DataTypes.DataPaqueteApuestasPersistencia;

public class PaqueteApuestas {
    private List<Apuesta> apuestas;
    private EstadoPaqueteApuestas estadoPaq;
    private int nroApuestasPendientes;
    private Usuario usuario;
	private int id;
	
	public PaqueteApuestas(Usuario usu) {
        this.apuestas = new ArrayList<Apuesta>();
        this.estadoPaq = EstadoPaqueteApuestas.Pendiente;
        this.nroApuestasPendientes = 0;
        this.usuario = usu;
        this.id=usu.getIdPaqueteNuevo();
    }
	
	//para crear un paquete al cargar
    public PaqueteApuestas(DataPaqueteApuestasPersistencia dPAP,Usuario usu){
        this.estadoPaq=dPAP.getEstadoPaquete();
        this.id=dPAP.getIdPaquete();
        this.nroApuestasPendientes=dPAP.getNroApuestasPendientes();
        this.apuestas = new ArrayList<Apuesta>();
        this.estadoPaq =dPAP.getEstadoPaquete();
        this.usuario = usu;
    }

   
    
    public EstadoPaqueteApuestas getEstadoPaq(){
        return this.estadoPaq;
    }

    public DataPaqueteApuestas getDataPaqueteApuestas() {
        DataPaqueteApuestas dataPaqApu = new DataPaqueteApuestas();
        List<DataApuesta> listDataApu = new ArrayList<DataApuesta>();

        for (Apuesta apuesta: apuestas) {
            DataApuesta dataAp = apuesta.getDataApuesta();
            listDataApu.add(dataAp);
        }

        float beneficio = 0;
        for(Apuesta apu: this.apuestas) {
            beneficio += apu.getMonto()*apu.getDividendo();
        }

        dataPaqApu.setApuestas(listDataApu);
        dataPaqApu.setMontoTotal(beneficio);
        dataPaqApu.setEstadoPaquete(this.estadoPaq);

        return dataPaqApu;
    }

    public void agregarApuestaPaquete(Apuesta a) {
        apuestas.add(a);
        this.nroApuestasPendientes++;
    }

    public void terminarApuesta(Apuesta a) {
        this.nroApuestasPendientes--;
        if (this.nroApuestasPendientes==0) {
            // Tengo que haber ganado todas las apuestas
            boolean acertoApuestas = true;
            for (int i=0; i<this.apuestas.size()&&acertoApuestas;i++) {
                acertoApuestas = this.apuestas.get(i).getEstadoApuesta()==DataTypes.EstadoApuesta.Gano;
            }
            if (acertoApuestas) {
                
                this.estadoPaq=EstadoPaqueteApuestas.Ganado;
                // Actualizarle la plata al usuario
                float beneficioTotal = 0;
                for (int i=0; i<this.apuestas.size();i++) {
                    beneficioTotal += this.apuestas.get(i).getDividendo()*this.apuestas.get(i).getMonto();
                }
                this.usuario.setSaldo(this.usuario.getSaldo()+beneficioTotal*5/100);
            }
            else
                this.estadoPaq=EstadoPaqueteApuestas.Perdido;
        }
    }
    
     //16-10
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return this.id;
    }
    

    public DataTypes.DataPaqueteApuestasPersistencia getDataPAPersistencia(){
		return new DataTypes.DataPaqueteApuestasPersistencia (this.id,this.usuario.getNick(),this.nroApuestasPendientes,this.estadoPaq);
	}

    
    public String getNickUsuario(){
        return this.usuario.getNick();
    }
    public void agregarApuestaPersistencia(Apuesta a){//aÃ±ade la apuesta sin afectar otras variables
        apuestas.add(a);
    }
    public void setNroAPendientes(int nroAP){
        this.nroApuestasPendientes=nroAP;
    }

}