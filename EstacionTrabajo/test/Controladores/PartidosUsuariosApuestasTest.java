/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;
import Interface.*;
import DataTypes.DataFechaHora;
import DataTypes.TipoPosicion;
import DataTypes.DataJugador;
import DataTypes.TipoCompeticion;
import DataTypes.TipoSexo;
import DataTypes.TipoCriterio;
import DataTypes.TipoApuesta;
import DataTypes.DataDetalleApuesta;
import Excepciones.ExCompeticionFinalizada;
import java.util.*;
import DataTypes.TipoDividendo;
import DataTypes.DataInfoPartido;
import DataTypes.EstadoApuesta;
import DataTypes.TipoCampeonatoApuesta;
import static org.junit.Assert.*;


public class PartidosUsuariosApuestasTest {
    public PartidosUsuariosApuestasTest() {
    }
    
    @org.junit.BeforeClass
    public static void setUpClass() throws Exception {
        Fabrica f = new Fabrica();
        IControladorEquipos ice = f.getIControladorEquipos();
        IControladorCompeticiones icc = f.getIControladorCompeticiones();
        IControladorJugadores icj = f.getIControladorJugadores();
        IControladorUsuarios icu = f.getIControladorUsuarios();
        
        
        icu.registrarUsuario("nom1","nick1","mail1","dir1","pass1",TipoSexo.Hombre,"pais1","ci1",new DataFechaHora(10,12,2000,2,2));
        icu.registrarUsuario("nom2","nick2","mail2","dir2","pass2",TipoSexo.Hombre,"pais2","ci2",new DataFechaHora(10,12,2000,2,2));
        icu.registrarUsuario("nom3","nick3","mail3","dir3","pass3",TipoSexo.Hombre,"pais3","ci3",new DataFechaHora(10,12,2000,2,2));
        icu.registrarUsuario("nom4","nick4","mail4","dir4","pass4",TipoSexo.Hombre,"pais4","ci4",new DataFechaHora(10,12,2000,2,2));
        icu.registrarUsuario("nom5","nick5","mail5","dir5","pass5",TipoSexo.Hombre,"pais5","ci5",new DataFechaHora(10,12,2000,2,2));
       

        try {
            // CREO JUGADORES
            DataFechaHora fechaHora = new DataFechaHora(1,8,2001,14,20);
            DataJugador dataJ;
            TipoPosicion tipo = null;
            for (int i=1;i<=10;i++) {
                if (i%4==0)
                    tipo = TipoPosicion.Golero;
                else if (i%3==0)
                    tipo = TipoPosicion.Defensa;
                else if (i%2==0)
                    tipo = TipoPosicion.Volante;
                else
                    tipo = TipoPosicion.Delantero;

                dataJ = new DataJugador(i,"jug"+i,"jugNomC"+i,tipo,fechaHora,i,"Lug1",i/2+1,i*5,"");
                icj.ingresarJugador(dataJ);
            }

            // CREO EQUIPOS
            for (int i=1;i<=9;i++)
                ice.darAltaEquipo("eq"+i,"");

            // CREO COMPETICION PARTIDOINDIVIDUAL ID=1, EQ1=id1 EQ2=id2
            icc.ingresarCompeticion("comp1",TipoCompeticion.PartidoIndividual,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);
            icc.darDeAltaCompetencia();
            icc.finalizar();


            // CREO COMPETICION PARTIDOINDIVIDUAL ID=2, EQ1=id3 EQ2=id4 
            icc.ingresarCompeticion("comp2",TipoCompeticion.PartidoIndividual,"");
            icc.seleccionarEquipo(3);
            icc.seleccionarEquipo(4);
            icc.darDeAltaCompetencia();
            icc.finalizar();
            
            
            // CREO COMPETICION PARTIDOINDIVIDUAL ID=3, EQ1=id1 EQ2=id2
            icc.ingresarCompeticion("comp1",TipoCompeticion.PartidoIndividual,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);
            icc.darDeAltaCompetencia();
            icc.finalizar();
            
            icc.ingresarDatosPartidoIndividual(3, 1, 2, new DataFechaHora(1,1,2009,1,1), null);
            icc.asignarDividendos(1, 3, 4, 2, 6);

            // CREO COMPETICION PARTIDOINDIVIDUAL ID=4, EQ1=id3 EQ2=id4 
            icc.ingresarCompeticion("comp2",TipoCompeticion.PartidoIndividual,"");
            icc.seleccionarEquipo(3);
            icc.seleccionarEquipo(4);
            icc.darDeAltaCompetencia();
            icc.finalizar();
            
            icc.ingresarDatosPartidoIndividual(4, 3, 4, new DataFechaHora(1,1,2009,1,1), null);
            icc.asignarDividendos(1, 4, 2, 7, 6);
            
            
            // CREO COMPETICION PARTIDOINDIVIDUAL ID=5, EQ1=id3 EQ2=id4 
            icc.ingresarCompeticion("comp2",TipoCompeticion.PartidoIndividual,"");
            icc.seleccionarEquipo(3);
            icc.seleccionarEquipo(4);
            icc.darDeAltaCompetencia();
            icc.finalizar();
            
            icc.ingresarDatosPartidoIndividual(5, 3, 4, new DataFechaHora(1,1,2009,1,1), null);
            icc.asignarDividendos(1, 5, 4, 10, 20);
            
            
            // CREO COMPETICION PARTIDOINDIVIDUAL ID=6, EQ1=id3 EQ2=id4 
            icc.ingresarCompeticion("comp2",TipoCompeticion.PartidoIndividual,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(4);
            icc.darDeAltaCompetencia();
            icc.finalizar();
            
            icc.ingresarDatosPartidoIndividual(6, 4, 1, new DataFechaHora(1,1,2009,1,1), null);
            icc.asignarDividendos(1, 6, 2, 12, 3);
            
        
            
            // CREO COMPETICION LIGA ID=7, EQ1=id1 EQ2=id2 EQ3=id3 EQ4=id4
            icc.ingresarCompeticion("comp3",TipoCompeticion.Liga,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);
            icc.seleccionarEquipo(3);
            TipoCriterio[] dc = {TipoCriterio.GolesFavor,TipoCriterio.DiferenciaGoles,TipoCriterio.Resultado};
            icc.ingresarOrdenLiga(dc);
            icc.ingresarDividendoEquipo(1,2);
            icc.ingresarDividendoEquipo(2,4);
            icc.ingresarDividendoEquipo(3,6);
            icc.darDeAltaCompetencia();
            icc.finalizar();
            
            icc.ingresarIdLiga(7);
            icc.seleccionarEncuentroADefinir(2, 3, new DataFechaHora(1,1,2000,1,1),"");
            icc.asignarDividendos(1, 7, 4, 6, 3);
            icc.ingresarIdLiga(7);
            icc.seleccionarEncuentroADefinir(1, 3, new DataFechaHora(1,1,2000,1,1),"");
            icc.asignarDividendos(2, 7, 4, 8, 23);
            icc.ingresarIdLiga(7);
            icc.seleccionarEncuentroADefinir(2, 1, new DataFechaHora(1,1,2000,1,1),"");
            icc.asignarDividendos(3, 7, 4, 6, 6);
            icc.ingresarIdLiga(7);
            
            // CREO COMPETICION LIGA ID=8, EQ1 EQ4
            icc.ingresarCompeticion("comp4",TipoCompeticion.Liga,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(4);
            TipoCriterio[] dc1 = {TipoCriterio.GolesFavor,TipoCriterio.DiferenciaGoles,TipoCriterio.Resultado};
            icc.ingresarOrdenLiga(dc1);
            icc.ingresarDividendoEquipo(1,4);
            icc.ingresarDividendoEquipo(4,4);
            icc.darDeAltaCompetencia();
            icc.finalizar();
            
            
            // CREO COMPETICION LIGA ID=9, EQ1 EQ4
            icc.ingresarCompeticion("comp4",TipoCompeticion.Copa,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);
            icc.seleccionarEquipo(3);
            icc.seleccionarEquipo(4);
            icc.seleccionarEquipo(5);
            icc.seleccionarEquipo(6);
            icc.seleccionarEquipo(7);
            icc.seleccionarEquipo(8);       
            icc.ingresarDividendoEquipo(1,4);
            icc.ingresarDividendoEquipo(2,3);
            icc.ingresarDividendoEquipo(3,17);
            icc.ingresarDividendoEquipo(4,7);
            icc.ingresarDividendoEquipo(5,8);
            icc.ingresarDividendoEquipo(6,4);
            icc.ingresarDividendoEquipo(7,11);
            icc.ingresarDividendoEquipo(8,3);
            icc.darDeAltaCompetencia();
            icc.finalizar();
            
            
            icc.ingresarDatosLlaveDeCopa(9, "OCTAVOS1");
            icc.ingresarDatosPartida(3, 2);
            icc.ingresarTipoLlave(true, false);
            icc.confirmarAltaLlaveCopa(new DataFechaHora(3,3,2000,3,3), "");
            icc.asignarDividendos(1, 9, 2, 2, 4);
            
            icc.ingresarDatosLlaveDeCopa(9, "OCTAVOS2");
            icc.ingresarDatosPartida(1, 6);
            icc.ingresarTipoLlave(true, false);
            icc.confirmarAltaLlaveCopa(new DataFechaHora(3,3,2000,3,3), "");
            icc.asignarDividendos(2, 9, 2, 5, 9);
            
            icc.ingresarDatosLlaveDeCopa(9, "OCTAVOS3");
            icc.ingresarDatosPartida(4, 5);
            icc.ingresarTipoLlave(true, false);
            icc.confirmarAltaLlaveCopa(new DataFechaHora(3,3,2000,3,3), "");
            icc.asignarDividendos(3, 9, 8, 6, 11);
            
            icc.ingresarDatosLlaveDeCopa(9, "OCTAVOS4");
            icc.ingresarDatosPartida(7, 8);
            icc.ingresarTipoLlave(true, false);
            icc.confirmarAltaLlaveCopa(new DataFechaHora(3,3,2000,3,3), "");
            icc.asignarDividendos(4, 9, 2, 7, 31);
            
            icc.ingresarDatosLlaveDeCopa(9, "CUARTOS1");
            icc.ingresarLlavesPredecesoras("OCTAVOS1", "OCTAVOS2");
            icc.ingresarTipoLlave(true,false);
            icc.confirmarAltaLlaveCopa(new DataFechaHora(3,3,2000,3,3), "");
            
            
            // CREO COMPETICION LIGA ID=10, EQ1 EQ4
            icc.ingresarCompeticion("comp4",TipoCompeticion.Liga,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);
            TipoCriterio[] dc2 = {TipoCriterio.GolesFavor,TipoCriterio.DiferenciaGoles,TipoCriterio.Resultado};
            icc.ingresarOrdenLiga(dc2);
            icc.ingresarDividendoEquipo(1,15);
            icc.ingresarDividendoEquipo(2,12);
            icc.darDeAltaCompetencia();
            icc.finalizar();
            
            icc.ingresarIdLiga(10);
            icc.seleccionarEncuentroADefinir(1, 2, new DataFechaHora(1,1,2000,1,1),"");
            icc.asignarDividendos(1, 10, 5, 6, 3);
            icc.ingresarIdLiga(10);
            icc.seleccionarEncuentroADefinir(2, 1, new DataFechaHora(1,1,2000,1,1),"");
            icc.asignarDividendos(2, 10, 5, 8, 9);

            assertTrue(icc.listarLigasNoDefinidas().size()==2);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue(false);
        }
    }
    
    
    @org.junit.AfterClass
    public static void tearDownClass() throws Exception {
    }

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }
    
    
    private void compararResultados(List<DataDetalleApuesta> list_da, DataDetalleApuesta[] resultados){
         int k;
         int h = 0;
         for (DataDetalleApuesta dda : list_da){           
             k = 0;
             boolean enc = false;
             while (!enc && k < list_da.size()){

                 if (dda.getBeneficioEsp()==resultados[k].getBeneficioEsp() && dda.getMonto()==resultados[k].getMonto()
                     && dda.getDividendo()==resultados[k].getDividendo() && dda.getTipoApuesta()==resultados[k].getTipoApuesta()
                     && dda.getEstadoApuesta().compareTo(resultados[k].getEstadoApuesta()) ==0&& dda.getFechaApuesta().compareTo(resultados[k].getFechaApuesta())==0)
                     enc = true;
                 else
                     k++;
             }
             h++;
             
             
             System.out.print("nro:"+h+"     Tipo de apuesta: ");
             if (TipoApuesta.Campeonato.compareTo(dda.getTipoApuesta())==0)
                 System.out.println("equipo");
             else
                 System.out.println("partido");
             System.out.println("Dividendo = "+dda.getDividendo()+"  Beneficio = "+dda.getBeneficioEsp()+
                                "   Monto = "+dda.getMonto()+"\n");

             //System.out.println("r ees  "+r);
             assertFalse(k == list_da.size());
         }
    }
    
    @org.junit.Test
    public void testFinalizarPartidoYApuestas(){
        IControladorUsuarios icu = (new Fabrica()).getIControladorUsuarios();
        IControladorCompeticiones icc = (new Fabrica()).getIControladorCompeticiones();

        
        
        
        IControladorFecha icf = (new Fabrica()).getIControladorFecha();
        // seteo una fecha
        try{
            icu.iniciarSesion("nick1", "pass1");
            icu.ingresarSaldoUsuario(500);
            icf.setFecha(new DataFechaHora(4,4,2009,2,1));
        }
        catch(Exception e){}
        

        try{
            // apuesto al partido individual id=3
            icu.seleccionarCompeticion(3);
            icu.apostarPartido(TipoDividendo.Local, 100);
            icu.confirmarAltaApuesta(true,false);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            fail();
        }
        
        try{
            icf.setFecha(new DataFechaHora(4,2,2008,2,1));
        }
        catch(Exception e){
             System.out.println(e.getMessage());
            fail();
        }
        
        try{
              // apuesto al partido individual id=4
            icu.seleccionarCompeticion(4);
            icu.apostarPartido(TipoDividendo.Visitante, 200);
            icu.confirmarAltaApuesta(true,false);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            fail();
        }
        
        
        /*    public DataInfoPartido (int idPartido, int idCompeticion, String nombCompeticion,
                            TipoCompeticion tipoCompeticion, String nomEqL,
                            String nomEqV, int idEqL, int idEqV, String lug,
                            DataFechaHora dfh, String nomLl, String nomLlPreL,
                            String nomLlPreV) {*/
        

        try{
             icc.listarPartidosConDivAsignado(3);
             icc.seleccionarPartido(new DataInfoPartido(1,3,"",TipoCompeticion.PartidoIndividual,"","",1,2,"",
                                    new DataFechaHora(2,10,2001,2,2),"","",""));
             icc.ingresarResultado(3, 0, 2, 0);
             icc.ingresarJugador(1, 1);
             icc.ingresarJugador(2, 1);
             icc.ingresarJugador(3, 2);
             icc.ingresarJugador(4, 2);
             icc.listarIngresos();
             
             icc.confirmarIngreso(true);
             icc.finalizar();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            fail();
        }
        
        /*
        List<DataDetalleApuesta> list_da=null;
        try{
            list_da = icu.mostrarHistorialApuestas(new DataFechaHora(1,1,2000,1,1), new DataFechaHora(1,1,2012,1,1));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            fail();
        }
      
        int size = 2;
        DataDetalleApuesta[] resultados = new DataDetalleApuesta[size];
        
        resultados[0] = new DataDetalleApuesta(EstadoApuesta.Gano,400,100,4,TipoApuesta.Partido,
                        new DataFechaHora(4,4,2009,2,1));
        resultados[1] = new DataDetalleApuesta(EstadoApuesta.Pendiente,1400,200,7,TipoApuesta.Partido,
                        new DataFechaHora(4,2,2008,2,1));
        
        assertTrue((long)list_da.size()==(long)resultados.length && list_da.size()==size);
        this.compararResultados(list_da,resultados);
        */
        
        try{
             icc.listarPartidosConDivAsignado(4);
             icc.seleccionarPartido(new DataInfoPartido(1,4,"",TipoCompeticion.PartidoIndividual,"","",3,4,"",
                                    new DataFechaHora(2,10,2001,2,2),"","",""));
             icc.ingresarResultado(2, 0, 6, 0);
             icc.ingresarJugador(1, 3);
             icc.ingresarJugador(2, 4);
             icc.ingresarJugador(3, 3);
             icc.ingresarJugador(4, 4);
             icc.listarIngresos();
             
             icc.confirmarIngreso(true);
             icc.finalizar();
             
        }
        catch(Exception e){}
        /*
        list_da=null;
        try{
            list_da = icu.mostrarHistorialApuestas(new DataFechaHora(1,1,2000,1,1), new DataFechaHora(1,1,2012,1,1));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            fail();
        }
        
        
        size = 2;
        resultados = new DataDetalleApuesta[size];
        
        resultados[0] = new DataDetalleApuesta(EstadoApuesta.Gano,400,100,4,TipoApuesta.Partido,
                        new DataFechaHora(4,4,2009,2,1));
        resultados[1] = new DataDetalleApuesta(EstadoApuesta.Gano,1400,200,7,TipoApuesta.Partido,
                        new DataFechaHora(4,2,2008,2,1));
        
        assertTrue((long)list_da.size()==(long)resultados.length && list_da.size()==size);
        this.compararResultados(list_da,resultados);
        */
        try{
            icu.cerrarSesion();
        }catch(Exception e){fail();}
        
       
        
        
        try{
            DataFechaHora f1 = new DataFechaHora(1,1,2000,1,1);
            DataFechaHora f2 = new DataFechaHora(1,1,2012,1,1);
            
            icu.iniciarSesion("nick2","pass2");
            icf.setFecha(new DataFechaHora(12,4,2010,2,1));
            icu.ingresarSaldoUsuario(1000);
            icu.seleccionarCompeticion(5);
            icu.apostarPartido(TipoDividendo.Empate,400);
            icu.confirmarAltaApuesta(true,false);
            
            icf.setFecha(new DataFechaHora(11,1,2011,2,1));
            icu.seleccionarCompeticion(6);
            icu.apostarPartido(TipoDividendo.Visitante,200);
            icu.confirmarAltaApuesta(true,false);
            
            
            icu.cerrarSesion();
            
            
            icu.iniciarSesion("nick3","pass3");
            icu.ingresarSaldoUsuario(4000);
            
            
            icu.seleccionarCompeticion(5);
            icu.apostarPartido(TipoDividendo.Local,20);
            icu.confirmarAltaApuesta(true,false);
            
            icu.seleccionarCompeticion(6);
            icu.apostarPartido(TipoDividendo.Local,200);
            icu.confirmarAltaApuesta(true,false);
            
            icf.setFecha(new DataFechaHora(7,4,2011,2,1));
            icu.seleccionarCompeticion(5);
            icu.apostarPartido(TipoDividendo.Empate,300);
            icu.confirmarAltaApuesta(true,false);
            
            icu.seleccionarCompeticion(6);
            icu.apostarPartido(TipoDividendo.Visitante,100);
            icu.confirmarAltaApuesta(true,false);
            
            icu.seleccionarCompeticion(6);
            icu.apostarPartido(TipoDividendo.Visitante,700);
            icu.confirmarAltaApuesta(true,false);
            
            icu.cerrarSesion();
            
            
            // finalizo partido id=5
            icc.listarPartidosConDivAsignado(5);
            icc.seleccionarPartido(new DataInfoPartido(1,5,"",TipoCompeticion.PartidoIndividual,"","",3,4,"",
                                    new DataFechaHora(2,10,2001,2,2),"","",""));
            icc.ingresarResultado(2, 0, 2, 0);
            icc.ingresarJugador(1, 3);
            icc.ingresarJugador(2, 4);
            icc.ingresarJugador(3, 3);
            icc.ingresarJugador(4, 4);
            icc.listarIngresos();
            icc.confirmarIngreso(true);
            icc.finalizar();
            
            
            
            icu.iniciarSesion("nick2", "pass2");
          /*
            list_da = icu.mostrarHistorialApuestas(new DataFechaHora(1,1,2000,1,1), new DataFechaHora(1,1,2050,1,1));
            
            size = 2;
            resultados = new DataDetalleApuesta[size];
            
            resultados[0] = new DataDetalleApuesta(EstadoApuesta.Gano,8000,400,20,TipoApuesta.Partido,
                                                    new DataFechaHora(12,4,2010,2,1));
            resultados[1] = new DataDetalleApuesta(EstadoApuesta.Pendiente,2400,200,12,TipoApuesta.Partido,
                                                    new DataFechaHora(11,1,2011,2,1));
            
            assertTrue(list_da.size()==resultados.length && list_da.size()==2);
            this.compararResultados(list_da, resultados);
            */
            icu.cerrarSesion();
            icu.iniciarSesion("nick3", "pass3");
            /*
            list_da = icu.mostrarHistorialApuestas(f1, f2);
            
            size = 5;
            resultados = new DataDetalleApuesta[size];
            
            resultados[0] = new DataDetalleApuesta(EstadoApuesta.Perdio,80,20,4,TipoApuesta.Partido,
                                                    new DataFechaHora(11,1,2011,2,1));
            resultados[1] = new DataDetalleApuesta(EstadoApuesta.Pendiente,400,200,2,TipoApuesta.Partido,
                                                    new DataFechaHora(11,1,2011,2,1));
            resultados[2] = new DataDetalleApuesta(EstadoApuesta.Pendiente,1200,100,12,TipoApuesta.Partido,
                                                    new DataFechaHora(7,4,2011,2,1));
            resultados[3] = new DataDetalleApuesta(EstadoApuesta.Pendiente,8400,700,12,TipoApuesta.Partido,
                                                    new DataFechaHora(7,4,2011,2,1));
            resultados[4] = new DataDetalleApuesta(EstadoApuesta.Gano,6000,300,20,TipoApuesta.Partido,
                                                    new DataFechaHora(7,4,2011,2,1));
            
            this.compararResultados(list_da, resultados);*/
            icu.cerrarSesion();
            
            icc.listarPartidosConDivAsignado(6);
            icc.seleccionarPartido(new DataInfoPartido(1,6,"",TipoCompeticion.PartidoIndividual,"","",1,4,"",
                                    new DataFechaHora(2,10,2001,2,2),"","",""));
            icc.ingresarResultado(2, 0, 3, 0);
            icc.ingresarJugador(1, 1);
            icc.ingresarJugador(2, 4);
            icc.ingresarJugador(3, 1);
            icc.ingresarJugador(4, 4);
            icc.listarIngresos();
            icc.confirmarIngreso(true);
            icc.finalizar();
            
            
            icu.iniciarSesion("nick2", "pass2");
            /*list_da = icu.mostrarHistorialApuestas(new DataFechaHora(1,1,2000,1,1), new DataFechaHora(1,1,2050,1,1));
            
            size = 2;
            resultados = new DataDetalleApuesta[size];
            
            resultados[0] = new DataDetalleApuesta(EstadoApuesta.Gano,8000,400,20,TipoApuesta.Partido,
                                                    new DataFechaHora(12,4,2010,2,1));
            resultados[1] = new DataDetalleApuesta(EstadoApuesta.Gano,2400,200,12,TipoApuesta.Partido,
                                                    new DataFechaHora(11,1,2011,2,1));
            
            assertTrue(list_da.size()==resultados.length && list_da.size()==2);
            this.compararResultados(list_da, resultados);
            */
            icu.cerrarSesion();
            
            
            
            icu.iniciarSesion("nick3", "pass3");
            /*
            list_da = icu.mostrarHistorialApuestas(f1, f2);
            
            size = 5;
            resultados = new DataDetalleApuesta[size];
            
            resultados[0] = new DataDetalleApuesta(EstadoApuesta.Perdio,80,20,4,TipoApuesta.Partido,
                                                    new DataFechaHora(11,1,2011,2,1));
            resultados[1] = new DataDetalleApuesta(EstadoApuesta.Perdio,400,200,2,TipoApuesta.Partido,
                                                    new DataFechaHora(11,1,2011,2,1));
            resultados[2] = new DataDetalleApuesta(EstadoApuesta.Gano,1200,100,12,TipoApuesta.Partido,
                                                    new DataFechaHora(7,4,2011,2,1));
            resultados[3] = new DataDetalleApuesta(EstadoApuesta.Gano,8400,700,12,TipoApuesta.Partido,
                                                    new DataFechaHora(7,4,2011,2,1));
            resultados[4] = new DataDetalleApuesta(EstadoApuesta.Gano,6000,300,20,TipoApuesta.Partido,
                                                    new DataFechaHora(7,4,2011,2,1));
            
            this.compararResultados(list_da, resultados);*/
            icu.cerrarSesion();
            
            
            
            // APUESTAS A COMPETICIONES
            try{
                icu.iniciarSesion("nick4", "pass4");
                icf.setFecha(new DataFechaHora(6,7,2011,1,1));
                icu.ingresarSaldoUsuario(2000);
                
                // apuesta a ganador de campeonato (3)
                icu.seleccionarCompeticion(7);
                icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
                icu.apostarCampeonato(1, 100);
                icu.confirmarAltaApuesta(true,false);
                
                icu.seleccionarCompeticion(7);
                icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
                icu.apostarCampeonato(2, 600);
                icu.confirmarAltaApuesta(true,false);
                
                icu.seleccionarCompeticion(9);
                icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
                icu.apostarCampeonato(3, 200);
                icu.confirmarAltaApuesta(true,false);
                
                // apuesta por partido (copa y liga)
                
                icu.seleccionarCompeticion(7);
                icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Partido);
                icu.seleccionarPartidoCamp(3);
                icu.apostarPartido(TipoDividendo.Local, 50);
                icu.confirmarAltaApuesta(true,false);
                
                icu.seleccionarCompeticion(7);
                icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Partido);
                icu.seleccionarPartidoCamp(2);
                icu.apostarPartido(TipoDividendo.Empate, 100);
                icu.confirmarAltaApuesta(true,false);
                
                icu.seleccionarCompeticion(9);
                icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Partido);
                icu.seleccionarPartidoCamp(3);
                icu.apostarPartido(TipoDividendo.Visitante, 150);
                icu.confirmarAltaApuesta(true,false);
                
                icu.seleccionarCompeticion(9);
                icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Partido);
                icu.seleccionarPartidoCamp(2);
                icu.apostarPartido(TipoDividendo.Empate, 200);
                icu.confirmarAltaApuesta(true,false);
                
                icu.seleccionarCompeticion(9);
                icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Partido);
                icu.seleccionarPartidoCamp(1);
                icu.apostarPartido(TipoDividendo.Empate, 200);
                icu.confirmarAltaApuesta(true,false);
                
                
                icu.cerrarSesion();
                
                
                icu.iniciarSesion("nick5", "pass5");
                icu.ingresarSaldoUsuario(500);
                
                try{
                    
                    icu.seleccionarCompeticion(9);
                    icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
                    icu.apostarCampeonato(7, 100);
                    icu.confirmarAltaApuesta(true,false);
                    
                    icu.seleccionarCompeticion(9);
                    icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Partido);
                    icu.seleccionarPartidoCamp(3);
                    icu.apostarPartido(TipoDividendo.Visitante, 100);
                    icu.confirmarAltaApuesta(true,false);
                    
                    icu.seleccionarCompeticion(7);
                    icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
                    icu.apostarCampeonato(2, 200);
                    icu.confirmarAltaApuesta(true,false);
                    
                    
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                    fail();
                }
                
                
                icu.cerrarSesion();
                
                
                
                icu.iniciarSesion("nick4", "pass4");
                /*
                size = 8;
                resultados = new DataDetalleApuesta[size];
                list_da = icu.mostrarHistorialApuestas(f1, f2);
                
                resultados[0] = new DataDetalleApuesta(EstadoApuesta.Pendiente,200,100,2,TipoApuesta.Campeonato,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[1] = new DataDetalleApuesta(EstadoApuesta.Pendiente,2400,600,4,TipoApuesta.Campeonato,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[2] = new DataDetalleApuesta(EstadoApuesta.Pendiente,3400,200,17,TipoApuesta.Campeonato,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[3] = new DataDetalleApuesta(EstadoApuesta.Pendiente,200,50,4,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[4] = new DataDetalleApuesta(EstadoApuesta.Pendiente,2300,100,23,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[5] = new DataDetalleApuesta(EstadoApuesta.Pendiente,900,150,6,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[6] = new DataDetalleApuesta(EstadoApuesta.Pendiente,1800,200,9,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[7] = new DataDetalleApuesta(EstadoApuesta.Pendiente,800,200,4,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                
                this.compararResultados(list_da, resultados);
                */
                
                icc.listarPartidosConDivAsignado(7);
                icc.seleccionarPartido(new DataInfoPartido(1,7,"",TipoCompeticion.Liga,"","",1,3,"",
                                        new DataFechaHora(2,10,2001,2,2),"","",""));
                icc.ingresarResultado(4, 0, 2, 0);
                icc.ingresarJugador(1, 3);
                icc.ingresarJugador(2, 1);
                icc.listarIngresos();
                icc.confirmarIngreso(true);
                icc.finalizar();
                
                icc.listarPartidosConDivAsignado(7);
                icc.seleccionarPartido(new DataInfoPartido(2,7,"",TipoCompeticion.Liga,"","",2,3,"",
                                        new DataFechaHora(2,10,2001,2,2),"","",""));
                icc.ingresarResultado(1, 0, 1, 0);
                icc.ingresarJugador(1, 3);
                icc.ingresarJugador(2, 2);
                icc.listarIngresos();
                icc.confirmarIngreso(true);
                icc.finalizar();
                
                icc.listarPartidosConDivAsignado(7);
                icc.seleccionarPartido(new DataInfoPartido(3,7,"",TipoCompeticion.Liga,"","",2,1,"",
                                        new DataFechaHora(2,10,2001,2,2),"","",""));
                icc.ingresarResultado(4, 0, 2, 0);
                icc.ingresarJugador(1, 2);
                icc.ingresarJugador(2, 1);
                icc.listarIngresos();
                icc.confirmarIngreso(true);
                icc.finalizar();
                /*
                resultados[0] = new DataDetalleApuesta(EstadoApuesta.Pendiente,200,100,2,TipoApuesta.Campeonato,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[1] = new DataDetalleApuesta(EstadoApuesta.Pendiente,2400,600,4,TipoApuesta.Campeonato,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[2] = new DataDetalleApuesta(EstadoApuesta.Pendiente,3400,200,17,TipoApuesta.Campeonato,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[3] = new DataDetalleApuesta(EstadoApuesta.Gano,200,50,4,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[4] = new DataDetalleApuesta(EstadoApuesta.Gano,2300,100,23,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[5] = new DataDetalleApuesta(EstadoApuesta.Pendiente,900,150,6,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[6] = new DataDetalleApuesta(EstadoApuesta.Pendiente,1800,200,9,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[7] = new DataDetalleApuesta(EstadoApuesta.Pendiente,800,200,4,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));

                
                
                list_da = icu.mostrarHistorialApuestas(f1, f2);
                this.compararResultados(list_da, resultados);
*/
                icc.ingresarIdLiga(7);
                icc.seleccionarEncuentroADefinir(1, 2, new DataFechaHora(1,1,2000,1,1),"");
                icc.asignarDividendos(4, 7, 7, 7, 7);
                icc.ingresarIdLiga(7);
                icc.seleccionarEncuentroADefinir(3, 2, new DataFechaHora(1,1,2000,1,1),"");
                icc.asignarDividendos(5, 7, 10, 6, 32);
                icc.ingresarIdLiga(7);
                icc.seleccionarEncuentroADefinir(3, 1, new DataFechaHora(1,1,2000,1,1),"");
                icc.asignarDividendos(6, 7, 4, 2, 8);
                
                
                
                icc.listarPartidosConDivAsignado(7);
                icc.seleccionarPartido(new DataInfoPartido(4,7,"",TipoCompeticion.Liga,"","",1,2,"",
                                        new DataFechaHora(2,10,2001,2,2),"","",""));
                icc.ingresarResultado(2, 0, 2, 0);
                icc.ingresarJugador(1, 2);
                icc.ingresarJugador(2, 1);
                icc.listarIngresos();
                icc.confirmarIngreso(true);
                icc.finalizar();
                
                
                icc.listarPartidosConDivAsignado(7);
                icc.seleccionarPartido(new DataInfoPartido(5,7,"",TipoCompeticion.Liga,"","",3,2,"",
                                        new DataFechaHora(2,10,2001,2,2),"","",""));
                icc.ingresarResultado(3, 0, 2, 0);
                icc.ingresarJugador(1, 3);
                icc.ingresarJugador(2, 2);
                icc.listarIngresos();
                icc.confirmarIngreso(true);
                icc.finalizar();
                
                icc.listarPartidosConDivAsignado(7);
                icc.seleccionarPartido(new DataInfoPartido(6,7,"",TipoCompeticion.Liga,"","",3,1,"",
                                        new DataFechaHora(2,10,2001,2,2),"","",""));
                icc.ingresarResultado(4, 0, 5, 0);
                icc.ingresarJugador(1, 3);
                icc.ingresarJugador(2, 1);
                icc.listarIngresos();
                icc.confirmarIngreso(true);
                icc.finalizar();
                
  /*
                resultados[0] = new DataDetalleApuesta(EstadoApuesta.Gano,200,100,2,TipoApuesta.Campeonato,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[1] = new DataDetalleApuesta(EstadoApuesta.Perdio,2400,600,4,TipoApuesta.Campeonato,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[2] = new DataDetalleApuesta(EstadoApuesta.Pendiente,3400,200,17,TipoApuesta.Campeonato,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[3] = new DataDetalleApuesta(EstadoApuesta.Gano,200,50,4,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[4] = new DataDetalleApuesta(EstadoApuesta.Gano,2300,100,23,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[5] = new DataDetalleApuesta(EstadoApuesta.Pendiente,900,150,6,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[6] = new DataDetalleApuesta(EstadoApuesta.Pendiente,1800,200,9,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[7] = new DataDetalleApuesta(EstadoApuesta.Pendiente,800,200,4,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));

                list_da = icu.mostrarHistorialApuestas(f1, f2);
                this.compararResultados(list_da, resultados);
    */
                
                
                icc.listarPartidosConDivAsignado(9);
                icc.seleccionarPartido(new DataInfoPartido(1,9,"",TipoCompeticion.Copa,"","",3,2,"",
                                        new DataFechaHora(2,10,2001,2,2),"OCTAVOS1","",""));
                
                
                icc.ingresarResultado(1, 0, 0, 0);
                icc.ingresarJugador(1, 3);
                icc.ingresarJugador(2, 2);
                icc.listarIngresos();
                icc.confirmarIngreso(true);
                icc.finalizar();
                
                
                icc.listarPartidosConDivAsignado(9);
                icc.seleccionarPartido(new DataInfoPartido(2,9,"",TipoCompeticion.PartidoIndividual,"","",1,6,"",
                                        new DataFechaHora(2,10,2001,2,2),"OCTAVOS2","",""));
                icc.ingresarResultado(4, 3, 4, 1);
                icc.ingresarJugador(1, 1);
                icc.ingresarJugador(2, 6);
                icc.listarIngresos();
                icc.confirmarIngreso(true);
                icc.finalizar();
                
                icc.listarPartidosConDivAsignado(9);
                icc.seleccionarPartido(new DataInfoPartido(3,9,"",TipoCompeticion.PartidoIndividual,"","",4,5,"",
                                        new DataFechaHora(2,10,2001,2,2),"OCTAVOS3","",""));
                icc.ingresarResultado(2, 4, 2, 5);
                icc.ingresarJugador(1, 5);
                icc.ingresarJugador(2, 4);
                icc.listarIngresos();
                icc.confirmarIngreso(true);
                icc.finalizar();
                
      /*
                list_da = icu.mostrarHistorialApuestas(f1, f2);
                resultados[0] = new DataDetalleApuesta(EstadoApuesta.Gano,200,100,2,TipoApuesta.Campeonato,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[1] = new DataDetalleApuesta(EstadoApuesta.Perdio,2400,600,4,TipoApuesta.Campeonato,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[2] = new DataDetalleApuesta(EstadoApuesta.Pendiente,3400,200,17,TipoApuesta.Campeonato,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[3] = new DataDetalleApuesta(EstadoApuesta.Gano,200,50,4,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[4] = new DataDetalleApuesta(EstadoApuesta.Gano,2300,100,23,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[5] = new DataDetalleApuesta(EstadoApuesta.Gano,900,150,6,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[6] = new DataDetalleApuesta(EstadoApuesta.Perdio,1800,200,9,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[7] = new DataDetalleApuesta(EstadoApuesta.Perdio,800,200,4,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                
                   
                this.compararResultados(list_da, resultados);
        */
                icc.listarPartidosConDivAsignado(9);
                icc.seleccionarPartido(new DataInfoPartido(4,9,"",TipoCompeticion.PartidoIndividual,"","",7,8,"",
                                        new DataFechaHora(2,10,2001,2,2),"OCTAVOS4","",""));
                icc.ingresarResultado(1, 0, 2, 0);
                icc.ingresarJugador(1, 8);
                icc.ingresarJugador(2, 7);
                icc.listarIngresos();
                icc.confirmarIngreso(true);
                icc.finalizar();
                
                icc.asignarDividendos(5, 9, 2, 2, 3);
                
                icc.listarPartidosConDivAsignado(9);
                icc.seleccionarPartido(new DataInfoPartido(5,9,"",TipoCompeticion.PartidoIndividual,"","",3,1,"",
                                        new DataFechaHora(2,10,2001,2,2),"CUARTOS1","OCTAVOS1","OCTAVOS2"));
                icc.ingresarResultado(3, 0, 1, 0);
                icc.ingresarJugador(1, 3);
                icc.ingresarJugador(2, 1);
                icc.listarIngresos();
                icc.confirmarIngreso(true);
                icc.finalizar();
                
                

                icc.ingresarDatosLlaveDeCopa(9, "CUARTOS2");
                icc.ingresarLlavesPredecesoras("OCTAVOS3", "OCTAVOS4");
                icc.ingresarTipoLlave(true,false);
                icc.confirmarAltaLlaveCopa(new DataFechaHora(3,3,2000,3,3), "");
                icc.asignarDividendos(6, 9, 2, 2,22);
                
                icc.listarPartidosConDivAsignado(9);
                icc.seleccionarPartido(new DataInfoPartido(6,9,"",TipoCompeticion.PartidoIndividual,"","",5,8,"",
                                        new DataFechaHora(2,10,2001,2,2),"CUARTOS2","OCTAVOS3","OCTAVOS4"));
                icc.ingresarResultado(3, 0, 4, 0);
                icc.ingresarJugador(1, 5);
                icc.ingresarJugador(2, 8);
                icc.listarIngresos();
                icc.confirmarIngreso(true);
                icc.finalizar();
                
                
                icc.ingresarDatosLlaveDeCopa(9, "FINAL");
                icc.ingresarLlavesPredecesoras("CUARTOS1", "CUARTOS2");
                icc.ingresarTipoLlave(false,true);
                icc.confirmarAltaLlaveCopa(new DataFechaHora(3,3,2000,3,3), "");
                
                icc.asignarDividendos(7, 9, 5, 20,50);
                
                
                ///APUESTO A LA FINAL
                icu.seleccionarCompeticion(9);
                icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Partido);
                icu.seleccionarPartidoCamp(7);
                icu.apostarPartido(TipoDividendo.Local, 400);
                icu.confirmarAltaApuesta(true,false);
                
                
                icc.listarPartidosConDivAsignado(9);
                icc.seleccionarPartido(new DataInfoPartido(7,9,"",TipoCompeticion.PartidoIndividual,"","",3,8,"",
                                        new DataFechaHora(2,10,2001,2,2),"FINAL","CUARTOS1","CUARTOS2"));
                icc.ingresarResultado(2, 5, 2, 4);
                icc.ingresarJugador(1, 3);
                icc.ingresarJugador(2, 8);
                icc.listarIngresos();
                icc.confirmarIngreso(true);
                icc.finalizar();
                
          /*
                size = 9;
                resultados = new DataDetalleApuesta[size];
                
                list_da = icu.mostrarHistorialApuestas(f1, f2);
                resultados[0] = new DataDetalleApuesta(EstadoApuesta.Gano,200,100,2,TipoApuesta.Campeonato,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[1] = new DataDetalleApuesta(EstadoApuesta.Perdio,2400,600,4,TipoApuesta.Campeonato,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[2] = new DataDetalleApuesta(EstadoApuesta.Gano,3400,200,17,TipoApuesta.Campeonato,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[3] = new DataDetalleApuesta(EstadoApuesta.Gano,200,50,4,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[4] = new DataDetalleApuesta(EstadoApuesta.Gano,2300,100,23,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[5] = new DataDetalleApuesta(EstadoApuesta.Gano,900,150,6,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[6] = new DataDetalleApuesta(EstadoApuesta.Perdio,1800,200,9,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[7] = new DataDetalleApuesta(EstadoApuesta.Perdio,800,200,4,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[8] = new DataDetalleApuesta(EstadoApuesta.Gano,2000,400,5,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                
                list_da = icu.mostrarHistorialApuestas(f1, f2);
                this.compararResultados(list_da, resultados);
            */
                
                try{
                    icu.seleccionarCompeticion(9);
                    fail();
                }
                catch(ExCompeticionFinalizada e){assertTrue(true);}
                
                icu.cerrarSesion();
                icu.iniciarSesion("nick5", "pass5");
              /*
                size = 3;
                resultados = new DataDetalleApuesta[size];
                
                list_da = icu.mostrarHistorialApuestas(f1, f2);
                
                resultados[0] = new DataDetalleApuesta(EstadoApuesta.Perdio,1100,100,11,TipoApuesta.Campeonato,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[1] = new DataDetalleApuesta(EstadoApuesta.Perdio,800,200,4,TipoApuesta.Campeonato,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[2] = new DataDetalleApuesta(EstadoApuesta.Gano,600,100,6,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                
                this.compararResultados(list_da, resultados);

                */
                try{
                    icu.seleccionarCompeticion(10);
                    icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
                    icu.apostarCampeonato(1, 50);
                    icu.confirmarAltaApuesta(true,false);
                    
                    icu.cerrarSesion();
                    
                    icu.iniciarSesion("nick1", "pass1");
                    icu.seleccionarCompeticion(10);
                    icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
                    icu.apostarCampeonato(1, 20);
                    icu.confirmarAltaApuesta(true,false);
                    
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                    fail();
                }  
                
                 icc.listarPartidosConDivAsignado(10);
                icc.seleccionarPartido(new DataInfoPartido(1,10,"",TipoCompeticion.PartidoIndividual,"","",1,2,"",
                                        new DataFechaHora(2,10,2001,2,2),"FINAL","CUARTOS1","CUARTOS2"));
                icc.ingresarResultado(2, 0, 2, 0);
                icc.ingresarJugador(1, 1);
                icc.ingresarJugador(2, 2);
                icc.listarIngresos();
                icc.confirmarIngreso(true);
                icc.finalizar();
                
                icc.listarPartidosConDivAsignado(10);
                icc.seleccionarPartido(new DataInfoPartido(2,10,"",TipoCompeticion.PartidoIndividual,"","",2,1,"",
                                        new DataFechaHora(2,10,2001,2,2),"FINAL","CUARTOS1","CUARTOS2"));
                icc.ingresarResultado(2, 0, 2,0);
                icc.ingresarJugador(1, 1);
                icc.ingresarJugador(2, 2);
                icc.listarIngresos();
                icc.confirmarIngreso(true);
                icc.finalizar();
                /*
                list_da = icu.mostrarHistorialApuestas(f1, f2);
                size = 3;

                resultados = new DataDetalleApuesta[size];

                resultados[0] = new DataDetalleApuesta(EstadoApuesta.Gano,400,100,4,TipoApuesta.Partido,
                                new DataFechaHora(4,4,2009,2,1));
                resultados[1] = new DataDetalleApuesta(EstadoApuesta.Gano,1400,200,7,TipoApuesta.Partido,
                                new DataFechaHora(4,2,2008,2,1));
                resultados[2] = new DataDetalleApuesta(EstadoApuesta.Gano,300,20,15,TipoApuesta.Campeonato,
                                new DataFechaHora(6,7,2011,1,1));
                
                this.compararResultados(list_da, resultados);
                */
                icu.cerrarSesion();
                
                
                icu.iniciarSesion("nick5", "pass5");
                /*
                
                size = 4;
                resultados = new DataDetalleApuesta[size];
                
                list_da = icu.mostrarHistorialApuestas(f1, f2);
                
                resultados[0] = new DataDetalleApuesta(EstadoApuesta.Perdio,1100,100,11,TipoApuesta.Campeonato,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[1] = new DataDetalleApuesta(EstadoApuesta.Perdio,800,200,4,TipoApuesta.Campeonato,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[2] = new DataDetalleApuesta(EstadoApuesta.Gano,600,100,6,TipoApuesta.Partido,
                                                    new DataFechaHora(6,7,2011,1,1));
                resultados[3] = new DataDetalleApuesta(EstadoApuesta.Gano,750,50,15,TipoApuesta.Campeonato,
                                new DataFechaHora(6,7,2011,1,1));
                
                this.compararResultados(list_da, resultados);
                */
                icu.cerrarSesion();
                
                
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                fail();
            }
          
        }catch(Exception e){
            System.out.println(e.getMessage());
            fail();
        }

        try {
            Fabrica f = new Fabrica();
            IControladorDatos icd = f.getIControladorDatos();
            icd.guardar("testApu.xls");
            icd.cargar("testApu.xls");
            icd.guardar("testApu2.xls");
        }
        catch (Exception exc){
            assertTrue(false);
        }
    }
       
}
