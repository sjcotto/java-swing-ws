/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import DataTypes.*;
import Interface.*;
import Excepciones.*;
import java.util.List;
import static org.junit.Assert.*;

/**
 *
 * @author gonzalo
 */
public class ControladorCompeticionesTest {

    public ControladorCompeticionesTest() {
    }

    @org.junit.BeforeClass
    public static void setUpClass() throws Exception {
        Fabrica f = new Fabrica();
        IControladorEquipos ice = f.getIControladorEquipos();
        IControladorCompeticiones icc = f.getIControladorCompeticiones();
        IControladorJugadores icj = f.getIControladorJugadores();

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
            for (int i=1;i<=5;i++)
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

            // CREO COMPETICION LIGA ID=3, EQ1=id1 EQ2=id2 EQ3=id3 EQ4=id4
            icc.ingresarCompeticion("comp3",TipoCompeticion.Liga,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);
            icc.seleccionarEquipo(3);
            icc.seleccionarEquipo(4);
            TipoCriterio[] dc = {TipoCriterio.GolesFavor,TipoCriterio.DiferenciaGoles,TipoCriterio.Resultado};
            icc.ingresarOrdenLiga(dc);
            icc.ingresarDividendoEquipo(1,2);
            icc.ingresarDividendoEquipo(2,4);
            icc.ingresarDividendoEquipo(3,6);
            icc.ingresarDividendoEquipo(4,8);
            icc.darDeAltaCompetencia();
            icc.finalizar();

            // CREO COMPETICION LIGA ID=4, EQ1 EQ4
            icc.ingresarCompeticion("comp4",TipoCompeticion.Liga,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(4);
            TipoCriterio[] dc1 = {TipoCriterio.GolesFavor,TipoCriterio.DiferenciaGoles,TipoCriterio.Resultado};
            icc.ingresarOrdenLiga(dc1);
            icc.ingresarDividendoEquipo(1,4);
            icc.ingresarDividendoEquipo(4,4);
            icc.darDeAltaCompetencia();
            icc.finalizar();

            assertTrue(icc.listarLigasNoDefinidas().size()==2);

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue(false);
        }

        ////////////////////////
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


    /**
     * Test of listarPartidosIndividualesNoDefinidos met System.out.println("Listo los partidos individuales no definidos");hod, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testListarPartidosIndividualesNoDefinidos() throws Exception {
        System.out.println("listarPartidosIndividualesNoDefinidos");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        List<DataPartidoIndividualNoDefinido> result = icc.listarPartidosIndividualesNoDefinidos();

        // Los partidos ind no definidos tienen id=1 e id=2
        assertEquals(2,result.size());

        int [] actuals = new int[10];
        int [] expecteds=new int[10];
        for (int i=0; i<actuals.length; i++)  actuals[i]= 0;
        for (int i=0; i<expecteds.length; i++)  expecteds[i]= 0;
        // Los partidos ind no definidos tienen id=1 e id=2
        expecteds[1] = expecteds[2] = 1;

        for (int i=0; i<result.size(); i++) {
            DataPartidoIndividualNoDefinido data = result.get(i);
            actuals[data.getIdCompeticion()] = 1;
        }
        assertArrayEquals(expecteds, actuals);
    }

    /**
     * Test of ingresarDatosPartidoIndividual method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testIngresarDatosPartidoIndividual() {
        System.out.println("ingresarDatosPartidoIndividual");
        IControladorCompeticiones icc = new ControladorCompeticiones();

        
        int idComp = 0;
        int idEqLocal = 0;
        int idEqVisi = 0;
        DataFechaHora fechaHora = new DataFechaHora(1,8,2001,14,20);
        String lugar = "lug0";
        
        try {
            icc.ingresarDatosPartidoIndividual(idComp, idEqLocal, idEqVisi, fechaHora, lugar);
            assertFalse(true);
            fail("Se esperaba que entre al ExNoExisteCompeticion");
        }
        catch (ExNoExisteCompeticion exc) {
            assertFalse(false);
        }
        catch (ExNoExistePartidoIndividual exc) {
            assertFalse(true);
        }
        catch (ExPartidoYaDefinido exc) {
            assertFalse(true);
        }
        catch (ExFechaInvalida exc) {
            assertFalse(true);
        }
        catch (ExEquipoInvalido exc) {
            assertFalse(true);
        }
        


        idComp = 1;
        idEqLocal = 1;
        idEqVisi = 3;
        lugar = "lug0";

        try {
            icc.ingresarDatosPartidoIndividual(idComp, idEqLocal, idEqVisi, fechaHora, lugar);
            assertFalse(true);
            fail("Se esperaba que entre al ExEquipoInvalido");
        }
        catch (ExNoExisteCompeticion exc) {
            assertFalse(true);
        }
        catch (ExNoExistePartidoIndividual exc) {
            assertFalse(true);
        }
        catch (ExPartidoYaDefinido exc) {
            assertFalse(true);
        }
        catch (ExFechaInvalida exc) {
            assertFalse(true);
        }
        catch (ExEquipoInvalido exc) {
            assertFalse(false);
        }

        idComp = 1;
        idEqLocal = 3;
        idEqVisi = 1;
        lugar = "lug0";

        try {
            icc.ingresarDatosPartidoIndividual(idComp, idEqLocal, idEqVisi, fechaHora, lugar);
            assertFalse(true);
            fail("Se esperaba que entre al ExEquipoInvalido");
        }
        catch (ExNoExisteCompeticion exc) {
            assertFalse(true);
        }
        catch (ExNoExistePartidoIndividual exc) {
            assertFalse(true);
        }
        catch (ExPartidoYaDefinido exc) {
            assertFalse(true);
        }
        catch (ExFechaInvalida exc) {
            assertFalse(true);
        }
        catch (ExEquipoInvalido exc) {
            assertFalse(false);
        }

        idComp = 1;
        idEqLocal = 1;
        idEqVisi = 1;
        lugar = "lug0";

        try {
            icc.ingresarDatosPartidoIndividual(idComp, idEqLocal, idEqVisi, fechaHora, lugar);
            assertFalse(true);
            fail("Se esperaba que entre al ExEquipoInvalido");
        }
        catch (ExNoExisteCompeticion exc) {
            assertFalse(true);
        }
        catch (ExNoExistePartidoIndividual exc) {
            assertFalse(true);
        }
        catch (ExPartidoYaDefinido exc) {
            assertFalse(true);
        }
        catch (ExFechaInvalida exc) {
            assertFalse(true);
        }
        catch (ExEquipoInvalido exc) {
            assertFalse(false);
        }


        idComp = 3;
        idEqLocal = 1;
        idEqVisi = 2;
        lugar = "lug0";

        try {
            icc.ingresarDatosPartidoIndividual(idComp, idEqLocal, idEqVisi, fechaHora, lugar);
            assertFalse(true);
            fail("Se esperaba que entre al ExNoExistePartidoIndividual");
        }
        catch (ExNoExisteCompeticion exc) {
            assertFalse(true);
        }
        catch (ExNoExistePartidoIndividual exc) {
            assertFalse(false);
        }
        catch (ExPartidoYaDefinido exc) {
            assertFalse(true);
        }
        catch (ExFechaInvalida exc) {
            assertFalse(true);
        }
        catch (ExEquipoInvalido exc) {
            assertFalse(true);
        }

        idComp = 1;
        idEqLocal = 1;
        idEqVisi = 2;
        lugar = "lug0";

        try {
            icc.ingresarDatosPartidoIndividual(idComp, idEqLocal, idEqVisi, fechaHora, lugar);
            assertFalse(false);
            //fail("Se definio el partido");
        }
        catch (ExNoExisteCompeticion exc) {
            assertFalse(true);
        }
        catch (ExNoExistePartidoIndividual exc) {
            assertFalse(true);
        }
        catch (ExPartidoYaDefinido exc) {
            assertFalse(true);
        }
        catch (ExFechaInvalida exc) {
            assertFalse(true);
        }
        catch (ExEquipoInvalido exc) {
            assertFalse(true);
        }
        catch (Exception exc) {
            assertFalse(false);
        }

        idComp = 1;
        idEqLocal = 1;
        idEqVisi = 2;
        lugar = "lug0";

        try {
            icc.ingresarDatosPartidoIndividual(idComp, idEqLocal, idEqVisi, fechaHora, lugar);
            assertFalse(true);
            fail("Se esperaba que entre al ExPartidoYaDefinido");
        }
        catch (ExNoExisteCompeticion exc) {
            assertFalse(true);
        }
        catch (ExNoExistePartidoIndividual exc) {
            assertFalse(true);
        }
        catch (ExPartidoYaDefinido exc) {
            assertFalse(false);
        }
        catch (ExFechaInvalida exc) {
            assertFalse(true);
        }
        catch (ExEquipoInvalido exc) {
            assertFalse(true);
        }
        
    }

    /**
     * Test of listarLigasNoDefinidas method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testListarLigasNoDefinidas() {
        System.out.println("listarLigasNoDefinidas");
        IControladorCompeticiones icc = new ControladorCompeticiones();
       
        List result = icc.listarLigasNoDefinidas();
        // Las ligas no definidos tienen id=3 e id=4
        assertEquals(2,result.size());

        int [] actuals = new int[10];
        int [] expecteds=new int[10];
        for (int i=0; i<actuals.length; i++)  actuals[i]= 0;
        for (int i=0; i<expecteds.length; i++)  expecteds[i]= 0;

        expecteds[3] = expecteds[4] = 1;

        for (int i=0; i<result.size(); i++) {
            DataIdNombre data = (DataIdNombre) result.get(i);
            actuals[data.getId()] = 1;
        }
        assertArrayEquals(expecteds, actuals);
    }

    /**
     * Test of ingresarIdLiga method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testIngresarIdLiga() {
        //ExNoExisteCompeticion,ExNoExisteLiga,ExLigaYaDefinida;
        System.out.println("ingresarIdLiga");
        IControladorCompeticiones icc = new ControladorCompeticiones();

        /// La liga 3 tiene los equipos 1,2,3,4 y no se definio ningun partido
        /// por lo tanto tiene que mostrar todos los cruces
       
        List<DataPartidoNoDefinidoLiga> result = null;

        //assertArrayEquals(expecteds, actuals);
        int[][] esperado = new int[4][4];
        int[][] resultado= new int[4][4];
        for (int i=0; i<=3; i++) {
            for (int j=0; j<=3; j++)
                if (i!=j) {
                    esperado[i][j]=1;
                    resultado[i][j]=0;
                }  else {
                    esperado[i][j]=0;
                    resultado[i][j]=0;
                }
        }

        try {
            result = icc.ingresarIdLiga(3);
            assertTrue(true);
            assertTrue(result.size()==12);
            for (DataPartidoNoDefinidoLiga data: result) {
                resultado[data.getIdEquipoLocal()-1][data.getIdEquipoVisitante()-1]=1;
            }
            for (int i=0; i<=3; i++) {
                assertArrayEquals(esperado[i], resultado[i]);
            }

        }
        catch (ExNoExisteCompeticion e) {
            assertTrue(false);
        }
        catch (ExNoExisteLiga e) {
            assertTrue(false);
        }
        catch (ExLigaYaDefinida e) {
            assertTrue(false);
        }
        catch (Exception e) {
            assertTrue(false);
        }


        /// No existe competicion
        try {
            result = icc.ingresarIdLiga(5);
            assertTrue(false);

        }
        catch (ExNoExisteCompeticion e) {
            assertTrue(true);
        }
        catch (ExNoExisteLiga e) {
            assertTrue(false);
        }
        catch (ExLigaYaDefinida e) {
            assertTrue(false);
        }
        catch (Exception e) {
            assertTrue(false);
        }

        /// No existe liga
        try {
            result = icc.ingresarIdLiga(1);
            assertTrue(false);

        }
        catch (ExNoExisteCompeticion e) {
            assertTrue(false);
        }
        catch (ExNoExisteLiga e) {
            assertTrue(true);
        }
        catch (ExLigaYaDefinida e) {
            assertTrue(false);
        }
        catch (Exception e) {
            assertTrue(false);
        }

        try {
            //Definio la liga con id 4
            DataFechaHora fechaHora = new DataFechaHora(1,1,1,1,1);
            icc.ingresarIdLiga(4);
            icc.seleccionarEncuentroADefinir(1,4,fechaHora,"lug");
            icc.ingresarIdLiga(4);
            icc.seleccionarEncuentroADefinir(4,1,fechaHora,"lug");
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        /// LIga 4 ya definida
        try {
            result = icc.ingresarIdLiga(4);
            assertTrue(false);

        }
        catch (ExNoExisteCompeticion e) {
            assertTrue(false);
        }
        catch (ExNoExisteLiga e) {
            assertTrue(false);
        }
        catch (ExLigaYaDefinida e) {
            assertTrue(true);
        }
        catch (Exception e) {
            assertTrue(false);
        }
        
        
    }

    /**
     * Test of seleccionarEncuentroADefinir method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testSeleccionarEncuentroADefinir() {
        System.out.println("seleccionarEncuentroADefinir");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        //public List<DataPartidoNoDefinidoLiga> ingresarIdLiga(int idLiga)
        //public DataResultadoAltaPartido seleccionarEncuentroADefinir (int

        DataFechaHora fechaHora = new DataFechaHora(1,1,1,1,1);
        String lugar = "lug";

        DataResultadoAltaPartido expResult = null;
        DataResultadoAltaPartido result = null;


        /// Pruebo que no existe Competicion asignada
        try {
            result = icc.seleccionarEncuentroADefinir(1,2, fechaHora, lugar);//idEqLocal,idEqVisi,fechaHora,lugar
            assertTrue(false);
        }
        //ExCompetenciaNoAsignada,ExFechaInvalida,ExEquipoInvalido,ExPartidoYaDefinido;
        catch (ExCompetenciaNoAsignada exc) {
            assertTrue(true);
        }
        catch (ExFechaInvalida exc) {
            assertTrue(false);
        }
        catch (ExEquipoInvalido exc) {
            assertTrue(false);
        }
        catch (ExPartidoYaDefinido e) {
            assertTrue(false);
        }
        catch (Exception e) {
            assertTrue(false);
        }

        /// Pruebo que la fecha es invalida
        try {
            icc.ingresarIdLiga(3);
        }
        catch (Exception exc) {
            assertTrue(false);
        }
        fechaHora = new DataFechaHora(1,1,1,1,70);
        try {
            result = icc.seleccionarEncuentroADefinir(1,2, fechaHora, lugar);//idEqLocal,idEqVisi,fechaHora,lugar
            assertTrue(false);
        }
        //ExCompetenciaNoAsignada,ExFechaInvalida,ExEquipoInvalido,ExPartidoYaDefinido;
        catch (ExCompetenciaNoAsignada exc) {
            assertTrue(false);
        }
        catch (ExFechaInvalida exc) {
            assertTrue(true);
        }
        catch (ExEquipoInvalido exc) {
            assertTrue(false);
        }
        catch (ExPartidoYaDefinido e) {
            assertTrue(false);
        }
        catch (Exception e) {
            assertTrue(false);
        }
        
        
        /// Pruebo equipo invalido
        fechaHora = new DataFechaHora(1,1,1,1,1);
        try {
            icc.ingresarIdLiga(3);
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        fechaHora = new DataFechaHora(1,1,1,1,1);

        try {
            result = icc.seleccionarEncuentroADefinir(1,1, fechaHora, "lug");//idEqLocal,idEqVisi,fechaHora,lugar
            assertTrue(false);
        }
        //ExCompetenciaNoAsignada,ExFechaInvalida,ExEquipoInvalido,ExPartidoYaDefinido;
        catch (ExCompetenciaNoAsignada exc) {
            assertTrue(false);
        }
        catch (ExFechaInvalida exc) {
            assertTrue(false);
        }
        catch (ExEquipoInvalido exc) {
            assertTrue(true);
        }
        catch (ExPartidoYaDefinido e) {
            assertTrue(false);
        }
        catch (Exception e) {
            assertTrue(false);
        }

        // Pruebo equipo invalido
        try {
            icc.ingresarIdLiga(3);
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }
        
        fechaHora = new DataFechaHora(1,1,1,1,1);
        
        try {
            result = icc.seleccionarEncuentroADefinir(0,1, fechaHora, "lug");//idEqLocal,idEqVisi,fechaHora,lugar
            assertTrue(false);
        }
        //ExCompetenciaNoAsignada,ExFechaInvalida,ExEquipoInvalido,ExPartidoYaDefinido;
        catch (ExCompetenciaNoAsignada exc) {
            assertTrue(false);
        }
        catch (ExFechaInvalida exc) {
            assertTrue(false);
        }
        catch (ExEquipoInvalido exc) {
            assertTrue(true);
        }
        catch (ExPartidoYaDefinido e) {
            assertTrue(false);
        }   
        catch (Exception e) {
            assertTrue(false);
        }

        // Pruebo equipo invalido
        try {
            icc.ingresarIdLiga(3);
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        fechaHora = new DataFechaHora(1,1,1,1,1);

        try {
            result = icc.seleccionarEncuentroADefinir(4,5, fechaHora, "lug");//idEqLocal,idEqVisi,fechaHora,lugar
            assertTrue(false);
        }
        //ExCompetenciaNoAsignada,ExFechaInvalida,ExEquipoInvalido,ExPartidoYaDefinido;
        catch (ExCompetenciaNoAsignada exc) {
            assertTrue(false);
        }
        catch (ExFechaInvalida exc) {
            assertTrue(false);
        }
        catch (ExEquipoInvalido exc) {
            assertTrue(true);
        }
        catch (ExPartidoYaDefinido e) {
            assertTrue(false);
        }
        catch (Exception e) {
            assertTrue(false);
        }

        // Pruebo asignar bien
        try {
            icc.ingresarIdLiga(3);
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        fechaHora = new DataFechaHora(1,1,1,1,1);

        try {
            result = icc.seleccionarEncuentroADefinir(1,2, fechaHora, "lug");//idEqLocal,idEqVisi,fechaHora,lugar
            assertTrue(icc.listarLigasNoDefinidas().size()==1);
            assertTrue(true);
        }
        //ExCompetenciaNoAsignada,ExFechaInvalida,ExEquipoInvalido,ExPartidoYaDefinido;
        catch (ExCompetenciaNoAsignada exc) {
            assertTrue(false);
        }
        catch (ExFechaInvalida exc) {
            assertTrue(false);
        }
        catch (ExEquipoInvalido exc) {
            assertTrue(false);
        }
        catch (ExPartidoYaDefinido e) {
            assertTrue(false);
        }
        catch (Exception e) {
            assertTrue(false);
        }

        // Pruebo partido ya definido
        try {
            icc.ingresarIdLiga(3);
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        fechaHora = new DataFechaHora(1,1,1,1,1);

        try {
            result = icc.seleccionarEncuentroADefinir(1,2, fechaHora, "lug");//idEqLocal,idEqVisi,fechaHora,lugar
            assertTrue(false);
        }
        //ExCompetenciaNoAsignada,ExFechaInvalida,ExEquipoInvalido,ExPartidoYaDefinido;
        catch (ExCompetenciaNoAsignada exc) {
            assertTrue(false);
        }
        catch (ExFechaInvalida exc) {
            assertTrue(false);
        }
        catch (ExEquipoInvalido exc) {
            assertTrue(false);
        }
        catch (ExPartidoYaDefinido e) {
            assertTrue(icc.listarLigasNoDefinidas().size()==1);
            assertTrue(true);
        }
        catch (Exception e) {
            assertTrue(false);
        }


    }

    /**
     * Test of ingresarCompeticion method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testIngresarCompeticion() {
        System.out.println("ingresarCompeticion");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        icc.ingresarCompeticion("comp1", TipoCompeticion.Copa,"");
        icc.ingresarCompeticion("comp2", TipoCompeticion.Liga,"");
        icc.ingresarCompeticion("comp3", TipoCompeticion.Liga,"");
        icc.ingresarCompeticion("comp4", TipoCompeticion.PartidoIndividual,"");
        assertTrue(true);
    }

    /**
     * Test of listarEquipos method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testListarEquipos() {
        System.out.println("listarEquipos");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        List<DataEquipo> equipos = icc.listarEquipos();
         // Los partidos ind no definidos tienen id=1 e id=2
        assertEquals(5,equipos.size());

        int [] actuals = new int[10];
        int [] expecteds=new int[10];
        for (int i=0; i<actuals.length; i++)  actuals[i]= 0;
        for (int i=0; i<expecteds.length; i++)  expecteds[i]= 0;
        // Los equipos son del 1 al 5
        expecteds[1] = expecteds[2] = expecteds[3] = expecteds[4] = expecteds[5] = 1;
        for (DataEquipo dataEq : equipos) {
            actuals[dataEq.getId()] = 1;
        }

        assertArrayEquals(expecteds, actuals);
    }

    /**
     * Test of seleccionarEquipo method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testSeleccionarEquipo() {
        System.out.println("seleccionarEquipo");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        //ExNoExisteEquipo, ExEquipoYaSeleccionado, ExDatosNoAsignados;

        // Pruebo ExDatosNoAsignados sin asociarle un tipo a la competicion
        icc.ingresarCompeticion("comp1",null,"");
        try {
            icc.seleccionarEquipo(1);
            assertTrue(false);
        } 
        catch (ExNoExisteEquipo e) {
            assertTrue(false);
        }
        catch (ExEquipoYaSeleccionado e) {
            assertTrue(false);
        }
        catch (ExDatosNoAsignados e) {
            assertTrue(true);
        }
        catch (Exception e) {
            assertTrue(false);
        }

        // Pruebo ExDatosNoAsignados sin asociarle nombre a la competicion
        icc.ingresarCompeticion(null,TipoCompeticion.PartidoIndividual,"");
        try {
            icc.seleccionarEquipo(1);
            assertTrue(false);
        }
        catch (ExNoExisteEquipo e) {
            assertTrue(false);
        }
        catch (ExEquipoYaSeleccionado e) {
            assertTrue(false);
        }
        catch (ExDatosNoAsignados e) {
            assertTrue(true);
        }
        catch (Exception e) {
            assertTrue(false);
        }

        // Pruebo que no exista equipo
        icc.ingresarCompeticion("comp1",TipoCompeticion.PartidoIndividual,"");
        try {
            icc.seleccionarEquipo(0);
            assertTrue(false);
        }
        catch (ExNoExisteEquipo e) {
            assertTrue(true);
        }
        catch (ExEquipoYaSeleccionado e) {
            assertTrue(false);
        }
        catch (ExDatosNoAsignados e) {
            assertTrue(false);
        }
        catch (Exception e) {
            assertTrue(false);
        }

        // Selecciono bien
        icc.ingresarCompeticion("comp1",TipoCompeticion.PartidoIndividual,"");
        try {
            icc.seleccionarEquipo(1);
            assertTrue(true);
        }
        catch (ExNoExisteEquipo e) {
            assertTrue(false);
        }
        catch (ExEquipoYaSeleccionado e) {
            assertTrue(false);
        }
        catch (ExDatosNoAsignados e) {
            assertTrue(false);
        }
        catch (Exception e) {
            assertTrue(false);
        }

        // Pruebo que ya asigno el equipo
        icc.ingresarCompeticion("comp1",TipoCompeticion.PartidoIndividual,"");
        try {
            icc.seleccionarEquipo(1);
            assertTrue(false);
        }
        catch (ExNoExisteEquipo e) {
            assertTrue(false);
        }
        catch (ExEquipoYaSeleccionado e) {
            assertTrue(true);
        }
        catch (ExDatosNoAsignados e) {
            assertTrue(false);
        }
        catch (Exception e) {
            assertTrue(false);
        }
        
    }

    /**
     * Test of seleccionCorrecta method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testSeleccionCorrecta() {
        System.out.println("seleccionCorrecta");
        IControladorCompeticiones icc = new ControladorCompeticiones();

        /// Pruebo que la competicion es Liga y mete 0 equipo
        try {
            icc.ingresarCompeticion("compTest",TipoCompeticion.Liga,"");
            assertTrue(true);
            //icc.seleccionarEquipo(1);
        }
        catch (Exception exc) {
            assertTrue(false);
        }
        assertFalse(icc.seleccionCorrecta());
        icc.finalizar();

        /// Pruebo que la competicion es Liga y mete 1 equipo
        try {
            icc.ingresarCompeticion("compTest",TipoCompeticion.Liga,"");
            icc.seleccionarEquipo(1);
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }
        assertFalse(icc.seleccionCorrecta());
        icc.finalizar();

        /// Pruebo que la competicion es Liga y mete 5 equipo
        try {
            icc.ingresarCompeticion("compTest",TipoCompeticion.Liga,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);
            icc.seleccionarEquipo(3);
            icc.seleccionarEquipo(4);
            icc.seleccionarEquipo(5);
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        assertTrue(icc.seleccionCorrecta());
        icc.finalizar();

        /// Pruebo que la competicion es PartidoIndividual y mete 0 equipo
        try {
            icc.ingresarCompeticion("compTest",TipoCompeticion.PartidoIndividual,"");
            assertTrue(true);
            //icc.seleccionarEquipo(1);
        }
        catch (Exception exc) {
            assertTrue(false);
        }
        assertFalse(icc.seleccionCorrecta());
        icc.finalizar();

        /// Pruebo que la competicion es PartidoIndividual y mete 1 equipo
        try {
            icc.ingresarCompeticion("compTest",TipoCompeticion.PartidoIndividual,"");
            icc.seleccionarEquipo(1);
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }
        assertFalse(icc.seleccionCorrecta());
        icc.finalizar();

        /// Pruebo que la competicion es PartidoIndividual y mete 5 equipo
        try {
            icc.ingresarCompeticion("compTest",TipoCompeticion.PartidoIndividual,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);
            icc.seleccionarEquipo(3);
            icc.seleccionarEquipo(4);
            icc.seleccionarEquipo(5);
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        assertFalse(icc.seleccionCorrecta());
        icc.finalizar();

        /// Pruebo que la competicion es PartidoIndividual y mete 2 equipo
        try {
            icc.ingresarCompeticion("compTest",TipoCompeticion.PartidoIndividual,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        assertTrue(icc.seleccionCorrecta());
        icc.finalizar();

        /// Pruebo que la competicion es Copa y mete 0 equipo
        try {
            icc.ingresarCompeticion("compTest",TipoCompeticion.Copa,"");
            assertTrue(true);
            //icc.seleccionarEquipo(1);
        }
        catch (Exception exc) {
            assertTrue(false);
        }
        assertFalse(icc.seleccionCorrecta());
        icc.finalizar();

        /// Pruebo que la competicion es Copa y mete 1 equipo
        try {
            icc.ingresarCompeticion("compTest",TipoCompeticion.Copa,"");
            icc.seleccionarEquipo(1);
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }
        assertFalse(icc.seleccionCorrecta());
        icc.finalizar();

        /// Pruebo que la competicion es Copa y mete 5 equipo
        try {
            icc.ingresarCompeticion("compTest",TipoCompeticion.Copa,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);
            icc.seleccionarEquipo(3);
            icc.seleccionarEquipo(4);
            icc.seleccionarEquipo(5);
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        assertFalse(icc.seleccionCorrecta());
        icc.finalizar();

        /// Pruebo que la competicion es Copa y mete 4 equipo
        try {
            icc.ingresarCompeticion("compTest",TipoCompeticion.Copa,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);
            icc.seleccionarEquipo(3);
            icc.seleccionarEquipo(4);
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        assertTrue(icc.seleccionCorrecta());
        icc.finalizar();

    }

    /**
     * Test of ingresarOrdenLiga method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testIngresarOrdenLiga() {
        System.out.println("ingresarOrdenLiga");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        //public void ingresarOrdenLiga(TipoCriterio[] dc)
        //    throws ExCriteriosInvalidos, ExEquipoInvalido,ExDividendosInvalidos;

        try {
            icc.ingresarCompeticion("compTest",TipoCompeticion.Liga,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);
            icc.seleccionarEquipo(3);
            icc.seleccionarEquipo(4);
            icc.seleccionarEquipo(5);
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Pruebo arreglo de criterios null
        try {
            icc.ingresarOrdenLiga(null);
            assertTrue(false);
        }
        catch (ExCriteriosInvalidos exc) {
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }
        
        TipoCriterio[] ldc = {TipoCriterio.DiferenciaGoles,TipoCriterio.GolesFavor,TipoCriterio.Resultado,
                              TipoCriterio.DiferenciaGoles,TipoCriterio.GolesFavor};
        // Pruebo arreglo de criterios de 5 elementos
        try {
            icc.ingresarOrdenLiga(ldc);
            assertTrue(false);
        }
        catch (ExCriteriosInvalidos exc) {
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }
        
        TipoCriterio[] ldc1 = {TipoCriterio.DiferenciaGoles};
        // Pruebo arreglo de criterios de 1 elemento
        try {
            icc.ingresarOrdenLiga(ldc1);
            assertTrue(false);
        }
        catch (ExCriteriosInvalidos exc) {
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }
        
        TipoCriterio[] ldc2 = {TipoCriterio.DiferenciaGoles,TipoCriterio.Resultado,TipoCriterio.Resultado};
        // Pruebo arreglo de criterios de 3 elementos con uno repetido
        try {
            icc.ingresarOrdenLiga(ldc2);
            assertTrue(false);
        }
        catch (ExCriteriosInvalidos exc) {
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }
        
        
        TipoCriterio[] ldc3 = {TipoCriterio.DiferenciaGoles,TipoCriterio.Resultado,TipoCriterio.GolesFavor};
        // Pruebo arreglo de criterios de 3 elementos bien
        try {
            icc.ingresarOrdenLiga(ldc3);
            assertTrue(true);
        }
        catch (ExCriteriosInvalidos exc) {
            assertTrue(false);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        
    }

    /**
     * Test of ingresarDividendoEquipo method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testIngresarDividendoEquipo() {
        System.out.println("ingresarDividendoEquipo");
        ///ingresarDividendoEquipo(int id,float dividendo)
        ///throws ExEquipoInvalido,ExDividendosInvalidos,ExDatosNoAsignados,ExDividendosYaAsignados;
        IControladorCompeticiones icc = new ControladorCompeticiones();
        TipoCriterio[] ldc3 = {TipoCriterio.DiferenciaGoles,TipoCriterio.Resultado,TipoCriterio.GolesFavor};

        icc.finalizar();
        // Pruebo datos no asignados
        try {
            icc.ingresarDividendoEquipo(1,2);
            assertTrue(false);
        }
        catch (ExEquipoInvalido exc){
            assertTrue(false);
        }
        catch (ExDividendosInvalidos exc){
            assertTrue(false);
        }
        catch (ExDatosNoAsignados exc){
            assertTrue(true);
        }
        catch (ExDividendosYaAsignados exc){
            assertTrue(false);
        }
        catch (Exception exc){
            assertTrue(false);
        }

        try {
            icc.ingresarCompeticion("compTest",TipoCompeticion.Liga,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);
            icc.seleccionarEquipo(3);
            icc.seleccionarEquipo(4);
            icc.seleccionarEquipo(5);
            icc.ingresarOrdenLiga(ldc3);
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Pruebo equipo invalido
        try {
            icc.ingresarDividendoEquipo(0,2);
            assertTrue(false);
        }
        catch (ExEquipoInvalido exc){
            assertTrue(true);
        }
        catch (ExDividendosInvalidos exc){
            assertTrue(false);
        }
        catch (ExDatosNoAsignados exc){
            assertTrue(false);
        }
        catch (ExDividendosYaAsignados exc){
            assertTrue(false);
        }
        catch (Exception exc){
            assertTrue(false);
        }

        // Pruebo dividendo invalido
        try {
            icc.ingresarDividendoEquipo(1,-3);
            assertTrue(false);
        }
        catch (ExEquipoInvalido exc){
            assertTrue(false);
        }
        catch (ExDividendosInvalidos exc){
            assertTrue(true);
        }
        catch (ExDatosNoAsignados exc){
            assertTrue(false);
        }
        catch (ExDividendosYaAsignados exc){
            assertTrue(false);
        }
        catch (Exception exc){
            assertTrue(false);
        }

        // Pruebo dividendo bien
        try {
            icc.ingresarDividendoEquipo(1,2);
            assertTrue(true);
        }
        catch (ExEquipoInvalido exc){
            assertTrue(false);
        }
        catch (ExDividendosInvalidos exc){
            assertTrue(false);
        }
        catch (ExDatosNoAsignados exc){
            assertTrue(false);
        }
        catch (ExDividendosYaAsignados exc){
            assertTrue(false);
        }
        catch (Exception exc){
            assertTrue(false);
        }

        // Pruebo dividendo ya asignado
        try {
            icc.ingresarDividendoEquipo(1,2);
            assertTrue(false);
        }
        catch (ExEquipoInvalido exc){
            assertTrue(false);
        }
        catch (ExDividendosInvalidos exc){
            assertTrue(false);
        }
        catch (ExDatosNoAsignados exc){
            assertTrue(false);
        }
        catch (ExDividendosYaAsignados exc){
            assertTrue(true);
        }
        catch (Exception exc){
            assertTrue(false);
        }


        
    }

    /**
     * Test of mostrarInfoCompetencia method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testMostrarInfoCompetencia() {
        System.out.println("mostrarInfoCompetencia");
        IControladorCompeticiones icc = new ControladorCompeticiones();

        TipoCriterio[] ldc3 = {TipoCriterio.DiferenciaGoles,TipoCriterio.Resultado,TipoCriterio.GolesFavor};
        icc.finalizar();

        try {
            icc.ingresarCompeticion("compTest",TipoCompeticion.Liga,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);
            icc.seleccionarEquipo(3);
            icc.seleccionarEquipo(4);
            icc.seleccionarEquipo(5);
            icc.ingresarOrdenLiga(ldc3);
            icc.ingresarDividendoEquipo(1,2);
            icc.ingresarDividendoEquipo(2,3);
            icc.ingresarDividendoEquipo(3,4);
            icc.ingresarDividendoEquipo(4,5);
            icc.ingresarDividendoEquipo(5,6);
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        DataCompeticion dataC = icc.mostrarInfoCompetencia();
        assertTrue(dataC.getId()==5);
        assertTrue(dataC.getNombre().equals("compTest"));
        assertTrue(dataC.getPathImage().equals(""));
        assertTrue(dataC.getTipo()==TipoCompeticion.Liga);
        assertTrue(dataC.getEstaDefinida()==false);

        List<DataDivEquipo> divEquipos = dataC.getDividendos();
        assertTrue(divEquipos.size()==5);

        icc.finalizar();
        try {
            icc.ingresarCompeticion("compTest2",TipoCompeticion.Copa,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);
            icc.seleccionarEquipo(3);
            icc.seleccionarEquipo(4);
            icc.ingresarDividendoEquipo(1,2);
            icc.ingresarDividendoEquipo(2,3);
            icc.ingresarDividendoEquipo(3,4);
            icc.ingresarDividendoEquipo(4,5);
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        dataC = icc.mostrarInfoCompetencia();
        assertTrue(dataC.getId()==5);
        assertTrue(dataC.getNombre().equals("compTest2"));
        assertTrue(dataC.getPathImage().equals(""));

        assertTrue(dataC.getTipo()==TipoCompeticion.Copa);
        assertTrue(dataC.getEstaDefinida()==false);

        divEquipos = dataC.getDividendos();
        assertTrue(divEquipos.size()==4);

        icc.finalizar();
        try {
            icc.ingresarCompeticion("compTest3",TipoCompeticion.PartidoIndividual,"");
            icc.seleccionarEquipo(5);
            icc.seleccionarEquipo(4);
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        dataC = icc.mostrarInfoCompetencia();
        assertTrue(dataC.getId()==5);
        assertTrue(dataC.getNombre().equals("compTest3"));
        assertTrue(dataC.getPathImage().equals(""));
        assertTrue(dataC.getTipo()==TipoCompeticion.PartidoIndividual);
        assertTrue(dataC.getEstaDefinida()==false);

        divEquipos = dataC.getDividendos();
        assertTrue(divEquipos.isEmpty());


    }

    /* TENGO HASTA EL MOMENTO:
            JUGADORES: 1..10
            EQUIPOS: 1..5

            COMPETICIONES:
                1-comp1-PartidoIndividual-Eq1=1-Eq2=2
                2-comp2-PartidoIndividual-Eq1=3-Eq2=4
                3-comp3-Liga-(GolFav,DifGol,Res)-Eq1(2)-Eq2(4)-Eq3(6)-Eq4(8)
                4-comp4-Liga-(GolFav,DifGol,Res)-Eq1(4)-Eq4(4)

            PARTIDOS (idComp,idPart,loc,vis)
                1-1-Eq1-Eq2 (Definido)
                4-1-Eq1-Eq4 (Definido)
                4-2-Eq4-Eq1 (Definido)
                3-1-Eq1-Eq2 (Definido)
     */



    /**
     * Test of darDeAltaCompetencia method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testDarDeAltaCompetencia() {
        System.out.println("darDeAltaCompetencia");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        
        TipoCriterio[] ldc1 = {TipoCriterio.DiferenciaGoles,TipoCriterio.Resultado,TipoCriterio.GolesFavor};
        TipoCriterio[] ldc4 = {TipoCriterio.Resultado,TipoCriterio.GolesFavor,TipoCriterio.DiferenciaGoles};

        icc.finalizar();
        try {
            icc.ingresarCompeticion("ligaTest1",TipoCompeticion.Liga,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);
            icc.ingresarOrdenLiga(ldc1);
            icc.ingresarDividendoEquipo(1,2);
            icc.ingresarDividendoEquipo(2,3);
            assertTrue(icc.darDeAltaCompetencia()==5);
            assertTrue(icc.listarLigasNoDefinidas().size()==2);
            icc.finalizar();
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        try {
            icc.ingresarCompeticion("copaTest2",TipoCompeticion.Copa,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);
            icc.seleccionarEquipo(3);
            icc.seleccionarEquipo(4);
            //icc.ingresarOrdenLiga(ldc1);
            icc.ingresarDividendoEquipo(1,2);
            icc.ingresarDividendoEquipo(2,3);
            icc.ingresarDividendoEquipo(3,2);
            icc.ingresarDividendoEquipo(4,3);
            assertTrue(icc.darDeAltaCompetencia()==6);
            icc.finalizar();
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        try {
            icc.ingresarCompeticion("indTest3",TipoCompeticion.PartidoIndividual,"");
            icc.seleccionarEquipo(3);
            icc.seleccionarEquipo(4);
            //icc.ingresarOrdenLiga(ldc1);
            assertTrue(icc.darDeAltaCompetencia()==7);
            icc.finalizar();
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        try {
            icc.ingresarCompeticion("ligaTest4",TipoCompeticion.Liga,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);
            icc.seleccionarEquipo(3);
            icc.ingresarOrdenLiga(ldc4);
            icc.ingresarDividendoEquipo(1,2);
            icc.ingresarDividendoEquipo(2,3);
            icc.ingresarDividendoEquipo(3,3);
            assertTrue(icc.darDeAltaCompetencia()==8);
            assertTrue(icc.listarLigasNoDefinidas().size()==3);
            icc.finalizar();
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        try {
            icc.ingresarCompeticion("copaTest5",TipoCompeticion.Copa,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);
            //icc.ingresarOrdenLiga(ldc1);
            icc.ingresarDividendoEquipo(1,2);
            icc.ingresarDividendoEquipo(2,3);
            assertTrue(icc.darDeAltaCompetencia()==9);
            icc.finalizar();
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        try {
            icc.ingresarCompeticion("indTest6",TipoCompeticion.PartidoIndividual,"");
            icc.seleccionarEquipo(2);
            icc.seleccionarEquipo(4);
            //icc.ingresarOrdenLiga(ldc1);
            assertTrue(icc.darDeAltaCompetencia()==10);
            icc.finalizar();
        }
        catch (Exception exc) {
            assertTrue(false);
        }


    }

    /**
     * Test of finalizar method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testFinalizar() {
        System.out.println("finalizar");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        icc.finalizar();
    }

    /*  jugadores 1..10
        equipos: 1..5

        competiciones:
        1-comp1-PartidoIndividual-Eq1-Eq2
        2-comp2-PartidoIndividual-Eq3-Eq4
        3-comp3-Liga-(GolFav,DifGol,Res)-Eq1(2)-Eq2(4)-Eq3(6)-Eq4(8)
        4-comp4-Liga(Definida)-(GolFav,DifGol,Res)-Eq1(4)-Eq4(4)
        5-ligaTest1-Liga-(DifGol,Res,GolFav)-Eq1(2)-Eq2(3)
        6-copaTest2-Copa-Eq1(2)-Eq2(3)-Eq3(2)-Eq4(3)
        7-indTest3-PartidoIndividual-Eq3-Eq4
        8-ligaTest4-Liga-(Res,GolFav,DifGol)-Eq1(2)-Eq2(3)-Eq3(3)
        9-copaTest5-Copa-Eq1(2)-Eq2(3)
        10-indTest6-PartidoIndividual-Eq2-Eq4

        partidos (idComp,idPart,loc,vis)
        1-1-Eq1-Eq2 (Definido)
        4-1-Eq1-Eq4 (Definido)
        4-2-Eq4-Eq1 (Definido)
        3-1-Eq1-Eq2 (Definido)
    */

    /**
     * Test of listarCopaNoDefinida method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testListarCopaNoDefinida() {
        System.out.println("listarCopaNoDefinida");
        IControladorCompeticiones icc = new ControladorCompeticiones();

        List<DataIdNombre> copasNoDef = icc.listarCopaNoDefinida();

        assertEquals(2,copasNoDef.size());

        int [] actuals = new int[10];
        int [] expecteds=new int[10];
        for (int i=0; i<actuals.length; i++)  actuals[i]= 0;
        for (int i=0; i<expecteds.length; i++)  expecteds[i]= 0;

        expecteds[6] = expecteds[9] = 1;

        for (int i=0; i<copasNoDef.size(); i++) {
            DataIdNombre data = copasNoDef.get(i);
            actuals[data.getId()] = 1;
        }
        assertArrayEquals(expecteds, actuals);


    }

    /*
     * Test of ingresarDatosLlaveDeCopa method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testIngresarDatosLlaveDeCopa() throws Exception {
        System.out.println("ingresarDatosLlaveDeCopa");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        //List<DataLlave> ingresarDatosLlaveDeCopa(int idCopa,String nombreLlave)
        //    throws ExNoExisteCompeticion,ExCopaYaDefinida,ExYaExisteLLave;

        /// Pruebo que no existe competicion
        try {
            List<DataLlave> llavesCreadas = icc.ingresarDatosLlaveDeCopa(11,"semi1");
            assertTrue(false);
            assertTrue(llavesCreadas.isEmpty());
        }
        catch (ExNoExisteCompeticion exc){
            assertTrue(true);
        }
        catch (ExCopaYaDefinida exc){
            assertTrue(false);
        }
        catch (ExYaExisteLLave exc){
            assertTrue(false);
        }
        catch (Exception exc){
            assertTrue(false);
        }

        /// Pruebo que no existe competicion de copa con ese id
        try {
            List<DataLlave> llavesCreadas = icc.ingresarDatosLlaveDeCopa(1,"semi1");
            assertTrue(false);
            assertTrue(llavesCreadas.isEmpty());
        }
        catch (ExNoExisteCompeticion exc){
            assertTrue(true);
        }
        catch (ExCopaYaDefinida exc){
            assertTrue(false);
        }
        catch (ExYaExisteLLave exc){
            assertTrue(false);
        }
        catch (Exception exc){
            assertTrue(false);
        }

        /// Defino la copa con id=9
        try {
            //icc.listarCopaNoDefinida();
            icc.ingresarDatosLlaveDeCopa(9,"final");
            assertTrue(icc.listarEquiposNoAsignadosCopa().size()==2);
            icc.ingresarDatosPartida(2,1);
            icc.ingresarTipoLlave(false,true);
            icc.confirmarAltaLlaveCopa(new DataFechaHora(1,1,1,1,1),"lugCopa");
            assertTrue(icc.listarCopaNoDefinida().size()==1);
            
            
        }
        catch (ExNoExisteCompeticion exc){
            assertTrue(false);
        }
        catch (ExCopaYaDefinida exc){
            assertTrue(false);
        }
        catch (ExYaExisteLLave exc){
            assertTrue(false);
        }
        catch (Exception exc){
            assertTrue(false);
        }


        /// Pruebo que copa ya esta definida
        try {
            List<DataLlave> llavesCreadas = icc.ingresarDatosLlaveDeCopa(9,"semi1");
            assertTrue(false);
            //assertTrue(llavesCreadas.isEmpty());
        }
        catch (ExNoExisteCompeticion exc){
            assertTrue(false);
        }
        catch (ExCopaYaDefinida exc){
            assertTrue(true);
        }
        catch (ExYaExisteLLave exc){
            assertTrue(false);
        }
        catch (Exception exc){
            assertTrue(false);
        }

        /// Creo llave semi1 en copa id=6
        try {
            //icc.listarCopaNoDefinida();
            icc.ingresarDatosLlaveDeCopa(6,"semi1");
            assertTrue(icc.listarEquiposNoAsignadosCopa().size()==4);
            icc.ingresarDatosPartida(1,2);
            icc.ingresarTipoLlave(true,false);
            icc.confirmarAltaLlaveCopa(new DataFechaHora(1,1,1,1,1),"lugCopa");
            assertTrue(icc.listarCopaNoDefinida().size()==1);

        }
        catch (ExNoExisteCompeticion exc){
            assertTrue(false);
        }
        catch (ExCopaYaDefinida exc){
            assertTrue(false);
        }
        catch (ExYaExisteLLave exc){
            assertTrue(false);
        }
        catch (Exception exc){
            assertTrue(false);
        }

        /// Pruebo que ya existe llave con mimsmo nombre
        try {
            List<DataLlave> llavesCreadas = icc.ingresarDatosLlaveDeCopa(6,"semi1");
            assertTrue(false);
            //assertTrue(llavesCreadas.isEmpty());
        }
        catch (ExNoExisteCompeticion exc){
            assertTrue(false);
        }
        catch (ExCopaYaDefinida exc){
            assertTrue(false);
        }
        catch (ExYaExisteLLave exc){
            assertTrue(true);
        }
        catch (Exception exc){
            assertTrue(false);
        }

    }
    /*
        jugadores 1..10
        equipos: 1..5

        competiciones:
        1-comp1-PartidoIndividual-Eq1-Eq2
        2-comp2-PartidoIndividual-Eq3-Eq4
        3-comp3-Liga-(GolFav,DifGol,Res)-Eq1(2)-Eq2(4)-Eq3(6)-Eq4(8)
        4-comp4-Liga(Definida)-(GolFav,DifGol,Res)-Eq1(4)-Eq4(4)
        5-ligaTest1-Liga-(DifGol,Res,GolFav)-Eq1(2)-Eq2(3)
        6-copaTest2-Copa-Eq1(2)-Eq2(3)-Eq3(2)-Eq4(3)
        7-indTest3-PartidoIndividual-Eq3-Eq4
        8-ligaTest4-Liga-(Res,GolFav,DifGol)-Eq1(2)-Eq2(3)-Eq3(3)
        9-copaTest5-Copa(Definida)-Eq1(2)-Eq2(3)
        10-indTest6-PartidoIndividual-Eq2-Eq4

        partidos (idComp,idPart,loc,vis)
        1-1-Eq1-Eq2 (Definido)
        4-1-Eq1-Eq4 (Definido)
        4-2-Eq4-Eq1 (Definido)
        3-1-Eq1-Eq2 (Definido)
        9-1-Eq2-Eq1-"final" (Definido)
        6-1-Eq1-Eq2-"semi1"
     */
    /**
     * Test of listarEquiposNoAsignadosCopa method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testListarEquiposNoAsignadosCopa() throws Exception {
        System.out.println("listarEquiposNoAsignadosCopa");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        IControladorEquipos ice = new ControladorEquipos();
        //public List<DataIdNombre> listarEquiposNoAsignadosCopa()
        //    throws ExDatosNoAsignados,ExCopaYaDefinida;

        /// Pruebo datos no asignados
        try {
            icc.listarEquiposNoAsignadosCopa();
            assertTrue(false);
        }
        catch (ExDatosNoAsignados exc) {
            assertTrue(true);
        }
        catch (ExCopaYaDefinida exc) {
            assertTrue(false);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        /// Meto unos equipos mas hasta llegar a 8
        /// Creo una copa con los 8
        /// meto algunas llaves y veo

        // CREO EQUIPOS
        for (int i=6;i<=10;i++)
            ice.darAltaEquipo("eq"+i,"");

        try {
            icc.ingresarCompeticion("copaTest11",TipoCompeticion.Copa,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);
            icc.seleccionarEquipo(3);
            icc.seleccionarEquipo(4);
            icc.seleccionarEquipo(5);
            icc.seleccionarEquipo(6);
            icc.seleccionarEquipo(7);
            icc.seleccionarEquipo(8);
            //icc.ingresarOrdenLiga(ldc1);
            icc.ingresarDividendoEquipo(1,2);
            icc.ingresarDividendoEquipo(2,4);
            icc.ingresarDividendoEquipo(3,6);
            icc.ingresarDividendoEquipo(4,8);
            icc.ingresarDividendoEquipo(5,10);
            icc.ingresarDividendoEquipo(6,12);
            icc.ingresarDividendoEquipo(7,14);
            icc.ingresarDividendoEquipo(8,16);
            assertTrue(icc.darDeAltaCompetencia()==11);
            icc.finalizar();

            //icc.listarCopaNoDefinida();
            icc.ingresarDatosLlaveDeCopa(11,"cuartos1");
            assertTrue(icc.listarEquiposNoAsignadosCopa().size()==8);
            icc.ingresarDatosPartida(1,2);
            icc.ingresarTipoLlave(true,false);
            icc.confirmarAltaLlaveCopa(new DataFechaHora(1,1,1,1,1),"lugCopa");
            assertTrue(icc.listarCopaNoDefinida().size()==2);

            //icc.listarCopaNoDefinida();
            icc.ingresarDatosLlaveDeCopa(11,"cuartos4");
            assertTrue(icc.listarEquiposNoAsignadosCopa().size()==6);
            icc.ingresarDatosPartida(7,8);
            icc.ingresarTipoLlave(true,false);
            icc.confirmarAltaLlaveCopa(new DataFechaHora(1,1,1,1,1),"lugCopa");
            assertTrue(icc.listarCopaNoDefinida().size()==2);

            icc.ingresarDatosLlaveDeCopa(11,"cuartos3");
            assertTrue(icc.listarEquiposNoAsignadosCopa().size()==4);
            icc.limpiarMemoriaAuxiliar();
        }
        catch (Exception exc) {
            assertTrue(false);
        }


        

    }

    /*
     *  jugadores 1..10
        equipos: 1..10

        competiciones:
        1-comp1-PartidoIndividual-Eq1-Eq2
        2-comp2-PartidoIndividual-Eq3-Eq4
        3-comp3-Liga-(GolFav,DifGol,Res)-Eq1(2)-Eq2(4)-Eq3(6)-Eq4(8)
        4-comp4-Liga(Definida)-(GolFav,DifGol,Res)-Eq1(4)-Eq4(4)
        5-ligaTest1-Liga-(DifGol,Res,GolFav)-Eq1(2)-Eq2(3)
        6-copaTest2-Copa-Eq1(2)-Eq2(3)-Eq3(2)-Eq4(3)
        7-indTest3-PartidoIndividual-Eq3-Eq4
        8-ligaTest4-Liga-(Res,GolFav,DifGol)-Eq1(2)-Eq2(3)-Eq3(3)
        9-copaTest5-Copa-Eq1(2)-Eq2(3)
        10-indTest6-PartidoIndividual-Eq2-Eq4
        11-copaTest11-Copa-Eq1..Eq8 // Dividendos: idEq*2

        partidos (idComp,idPart,loc,vis)
        1-1-Eq1-Eq2 (Definido)
        4-1-Eq1-Eq4 (Definido)
        4-2-Eq4-Eq1 (Definido)
        3-1-Eq1-Eq2 (Definido)
        9-1-Eq2-Eq1-"final" (Definido)
        6-1-Eq1-Eq2-"semi1"
        11-1-Eq1-Eq2-"cuartos1" (Definido)
        11-2-Eq7-Eq8-"cuartos4" (Definido)
     */

    /**
     * Test of ingresarDatosPartida method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testIngresarDatosPartida() throws Exception {
        System.out.println("ingresarDatosPartida");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        IControladorEquipos ice = new ControladorEquipos();
        //public void ingresarDatosPartida(int idEqL,int idEqV)
        //    throws ExDatosNoAsignados,ExCopaYaDefinida,ExNoExisteEquipo,
        //       ExEquipoInvalido;
        
        // Pruebo que los datos no esten asignados
        try{
            icc.limpiarMemoriaAuxiliar();
            icc.ingresarDatosPartida(5,6);
            
        }
        catch (ExDatosNoAsignados exc) {
            assertTrue(true);
        }
        catch (ExCopaYaDefinida exc) {
            assertTrue(false);
        }
        catch (ExNoExisteEquipo exc) {
            assertTrue(false);
        }
        catch (ExEquipoInvalido exc) {
            assertTrue(false);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Pruebo que no existe el equipo
        try{
            icc.limpiarMemoriaAuxiliar();
            icc.ingresarDatosLlaveDeCopa(11,"cuartos2");
            icc.ingresarDatosPartida(0,6);
            
        }
        catch (ExDatosNoAsignados exc) {
            assertTrue(false);
        }
        catch (ExCopaYaDefinida exc) {
            assertTrue(false);
        }
        catch (ExNoExisteEquipo exc) {
            assertTrue(true);
        }
        catch (ExEquipoInvalido exc) {
            assertTrue(false);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

         // Pruebo que ya se asigno el equipo a una fase inicial
        try{
            icc.limpiarMemoriaAuxiliar();
            icc.ingresarDatosLlaveDeCopa(11,"cuartos2");
            icc.ingresarDatosPartida(8,6);

        }
        catch (ExDatosNoAsignados exc) {
            assertTrue(false);
        }
        catch (ExCopaYaDefinida exc) {
            assertTrue(false);
        }
        catch (ExNoExisteEquipo exc) {
            assertTrue(false);
        }
        catch (ExEquipoInvalido exc) {
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

    }

    /**
     * Test of ingresarLlavesPredecesoras method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testIngresarLlavesPredecesoras() throws Exception {
        System.out.println("ingresarLlavesPredecesoras");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        //public void ingresarLlavesPredecesoras(String llaveLocal,String llaveVisita)
        //    throws ExLLavesInvalidas,ExNoExisteLaLlave, ExLlavesDeDistintasFases,
        //        ExLlavesDifierenEnSucesoras;

        // Meto una llave "cuartos2"
        // Creo "semi1"

        try {
            icc.ingresarDatosLlaveDeCopa(11,"cuartos2");
            assertTrue(icc.listarEquiposNoAsignadosCopa().size()==4);
            icc.ingresarDatosPartida(3,4);
            icc.ingresarTipoLlave(true,false);
            icc.confirmarAltaLlaveCopa(new DataFechaHora(1,1,1,1,1),"lugCopa");
            assertTrue(icc.listarCopaNoDefinida().size()==2);

            icc.ingresarDatosLlaveDeCopa(11,"semi1");
            assertTrue(icc.listarEquiposNoAsignadosCopa().size()==2);
            icc.ingresarLlavesPredecesoras("cuartos1","cuartos2");
            icc.ingresarTipoLlave(true,false);
            icc.confirmarAltaLlaveCopa(new DataFechaHora(1,1,1,1,1),"lugCopa");
            
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Pruebo que una llaves son iguales
        try {    
            icc.ingresarDatosLlaveDeCopa(11,"cuartos3");
            assertTrue(icc.listarEquiposNoAsignadosCopa().size()==2);
            icc.ingresarLlavesPredecesoras("cuartos","cuartos");
            assertTrue(false);

        }
        catch (ExLLavesInvalidas exc) {
            assertTrue(true);
        }
        catch (ExNoExisteLaLlave exc) {
            assertTrue(false);
        }
        catch (ExLlavesDeDistintasFases exc) {
            assertTrue(false);
        }
        catch (ExLlavesDifierenEnSucesoras exc) {
            assertTrue(false);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Pruebo que una llave no existe
        try {
            icc.limpiarMemoriaAuxiliar();
            icc.ingresarDatosLlaveDeCopa(11,"cuartos3");
            assertTrue(icc.listarEquiposNoAsignadosCopa().size()==2);
            icc.ingresarLlavesPredecesoras("cuartos1","cuartos");
            assertTrue(false);

        }
        catch (ExLLavesInvalidas exc) {
            assertTrue(false);
        }
        catch (ExNoExisteLaLlave exc) {
            assertTrue(true);
        }
        catch (ExLlavesDeDistintasFases exc) {
            assertTrue(false);
        }
        catch (ExLlavesDifierenEnSucesoras exc) {
            assertTrue(false);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Pruebo que son llaves de distintas fases
        try {
            icc.limpiarMemoriaAuxiliar();
            icc.ingresarDatosLlaveDeCopa(11,"cuartos3");
            assertTrue(icc.listarEquiposNoAsignadosCopa().size()==2);
            icc.ingresarLlavesPredecesoras("cuartos1","semi1");
            assertTrue(false);

        }
        catch (ExLLavesInvalidas exc) {
            assertTrue(false);
        }
        catch (ExNoExisteLaLlave exc) {
            assertTrue(false);
        }
        catch (ExLlavesDeDistintasFases exc) {
            assertTrue(true);
        }
        catch (ExLlavesDifierenEnSucesoras exc) {
            assertTrue(false);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Pruebo que son de igual fase pero una tiene un sucesor
        try {
            icc.limpiarMemoriaAuxiliar();
            icc.ingresarDatosLlaveDeCopa(11,"cuartos3");
            assertTrue(icc.listarEquiposNoAsignadosCopa().size()==2);
            icc.ingresarLlavesPredecesoras("cuartos1","cuartos4");
            assertTrue(false);

        }
        catch (ExLLavesInvalidas exc) {
            assertTrue(false);
        }
        catch (ExNoExisteLaLlave exc) {
            assertTrue(false);
        }
        catch (ExLlavesDeDistintasFases exc) {
            assertTrue(false);
        }
        catch (ExLlavesDifierenEnSucesoras exc) {
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Pruebo que tengo llaves de misma fase y que ambas tienen un sucesor pero no son semifinal
        try {
            icc.limpiarMemoriaAuxiliar();
            icc.ingresarDatosLlaveDeCopa(11,"cuartos3");
            assertTrue(icc.listarEquiposNoAsignadosCopa().size()==2);
            icc.ingresarLlavesPredecesoras("cuartos1","cuartos2");
            assertTrue(false);

        }
        catch (ExLLavesInvalidas exc) {
            assertTrue(true);
        }
        catch (ExNoExisteLaLlave exc) {
            assertTrue(false);
        }
        catch (ExLlavesDeDistintasFases exc) {
            assertTrue(false);
        }
        catch (ExLlavesDifierenEnSucesoras exc) {
            assertTrue(false);
        }
        catch (Exception exc) {
            assertTrue(false);
        }
        
    }

    /**
     * Test of ingresarTipoLlave method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testIngresarTipoLlave() throws Exception {
        System.out.println("ingresarTipoLlave");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        //ingresarTipoLlave(boolean tieneSucesora,boolean esFinal)
        //    throws ExParametrosInvalidos;

        // Pruebo que meto llave inicial y no tiene sucesor pero tengo mas de 2 equipos
        try {
            icc.ingresarDatosLlaveDeCopa(11,"cuartos3");
            assertTrue(icc.listarEquiposNoAsignadosCopa().size()==2);
            icc.ingresarDatosPartida(5,6);
            icc.ingresarTipoLlave(false,false);
            assertTrue(false);

        }
        catch (ExParametrosInvalidos exc){
            assertTrue(true);
        }
        catch (Exception exc){
            assertTrue(false);
        }

        // Pruebo que meto llave inicial y no tiene sucesor y es final pero tengo mas de 2 equipos
        try {
            icc.ingresarDatosLlaveDeCopa(11,"cuartos3");
            assertTrue(icc.listarEquiposNoAsignadosCopa().size()==2);
            icc.ingresarDatosPartida(5,6);
            icc.ingresarTipoLlave(false,true);
            assertTrue(false);

        }
        catch (ExParametrosInvalidos exc){
            assertTrue(true);
        }
        catch (Exception exc){
            assertTrue(false);
        }

        try {
            icc.ingresarCompeticion("copaTest12",TipoCompeticion.Copa,"");
            icc.seleccionarEquipo(10);
            icc.seleccionarEquipo(9);
            icc.ingresarDividendoEquipo(10,10);
            icc.ingresarDividendoEquipo(9,9);
            assertTrue(icc.darDeAltaCompetencia()==12);
            icc.finalizar();
        }
        catch (Exception exc){
            assertTrue(false);
        }

        // Pruebo que meto llave inicial y tiene sucesor pero tengo dos equipos nomas
        try {
            icc.ingresarDatosLlaveDeCopa(12,"fin");
            assertTrue(icc.listarEquiposNoAsignadosCopa().size()==2);
            icc.ingresarDatosPartida(9,10);
            icc.ingresarTipoLlave(true,false);
            assertTrue(false);

        }
        catch (ExParametrosInvalidos exc){
            assertTrue(true);
        }
        catch (Exception exc){
            assertTrue(false);
        }

        // Pruebo que meto llave inicial y tiene sucesor y es final pero tengo dos equipos nomas
        try {
            icc.ingresarDatosLlaveDeCopa(12,"fin");
            assertTrue(icc.listarEquiposNoAsignadosCopa().size()==2);
            icc.ingresarDatosPartida(9,10);
            icc.ingresarTipoLlave(true,true);
            assertTrue(false);

        }
        catch (ExParametrosInvalidos exc){
            assertTrue(true);
        }
        catch (Exception exc){
            assertTrue(false);
        }

        // Pruebo que meto llave inicial y no tiene sucesor y es no final pero tengo dos equipos nomas
        try {
            icc.ingresarDatosLlaveDeCopa(12,"fin");
            assertTrue(icc.listarEquiposNoAsignadosCopa().size()==2);
            icc.ingresarDatosPartida(9,10);
            icc.ingresarTipoLlave(false,false);
            assertTrue(false);

        }
        catch (ExParametrosInvalidos exc){
            assertTrue(true);
        }
        catch (Exception exc){
            assertTrue(false);
        }

        // Meto una llave semi en el copa id=6
        try {
            assertTrue(icc.listarCopaNoDefinida().size()==3);
            icc.ingresarDatosLlaveDeCopa(6,"semi2");
            assertTrue(icc.listarEquiposNoAsignadosCopa().size()==2);
            icc.ingresarDatosPartida(3,4);
            icc.ingresarTipoLlave(true,false);
            icc.confirmarAltaLlaveCopa(new DataFechaHora(1,1,1,1,1),"lugCopa");

        }
        catch (Exception exc){
            assertTrue(false);
        }

        // Pruebo que meto llave en fase final y tiene sucesora
        try {
            icc.ingresarDatosLlaveDeCopa(6,"fin");
            assertTrue(icc.listarEquiposNoAsignadosCopa().isEmpty());
            icc.ingresarLlavesPredecesoras("semi1","semi2");
            icc.ingresarTipoLlave(true,false);
            assertTrue(false);

        }
        catch (ExParametrosInvalidos exc){
            assertTrue(true);
        }
        catch (Exception exc){
            assertTrue(false);
        }

        // Meto una llave tercer en el copa id=6
        try {
            assertTrue(icc.listarCopaNoDefinida().size()==3);
            icc.ingresarDatosLlaveDeCopa(6,"tercer");
            assertTrue(icc.listarEquiposNoAsignadosCopa().isEmpty());
            icc.ingresarLlavesPredecesoras("semi1","semi2");
            icc.ingresarTipoLlave(false,false);
            icc.confirmarAltaLlaveCopa(new DataFechaHora(1,1,1,1,1),"lugCopa");

        }
        catch (Exception exc){
            assertTrue(false);
        }

        // Pruebo que meto llave 3er 4to puesto pero ya tengo una
        try {
            assertTrue(icc.listarCopaNoDefinida().size()==3);
            icc.ingresarDatosLlaveDeCopa(6,"terce");
            assertTrue(icc.listarEquiposNoAsignadosCopa().isEmpty());
            icc.ingresarLlavesPredecesoras("semi1","semi2");
            icc.ingresarTipoLlave(false,false);
            icc.confirmarAltaLlaveCopa(new DataFechaHora(1,1,1,1,1),"lugCopa");

        }
        catch (ExParametrosInvalidos exc){
            assertTrue(true);
        }
        catch (Exception exc){
            assertTrue(false);
        }

        
    }

/*
*  jugadores 1..10
  equipos: 1..10

  competiciones:
  1-comp1-PartidoIndividual-Eq1-Eq2 !!!!!!!!!!!!!DEFINIDA
  2-comp2-PartidoIndividual-Eq3-Eq4
  3-comp3-Liga-(GolFav,DifGol,Res)-Eq1(2)-Eq2(4)-Eq3(6)-Eq4(8)
  4-comp4-Liga(Definida)-(GolFav,DifGol,Res)-Eq1(4)-Eq4(4) !!!!!!!!!!!!!DEFINIDA
  5-ligaTest1-Liga-(DifGol,Res,GolFav)-Eq1(2)-Eq2(3)
  6-copaTest2-Copa-Eq1(2)-Eq2(3)-Eq3(2)-Eq4(3)
  7-indTest3-PartidoIndividual-Eq3-Eq4
  8-ligaTest4-Liga-(Res,GolFav,DifGol)-Eq1(2)-Eq2(3)-Eq3(3)
  9-copaTest5-Copa-Eq1(2)-Eq2(3) !!!!!!!!!!!!!DEFINIDA
  10-indTest6-PartidoIndividual-Eq2-Eq4
  11-copaTest11-Copa-Eq1..Eq8 // Dividendos: idEq*2
  12-copaTest12-Copa-Eq9-Eq10

  partidos (idComp,idPart,loc,vis)
  1-1-Eq1-Eq2 (Definido)
  3-1-Eq1-Eq2 (Definido)
  4-1-Eq1-Eq4 (Definido)
  4-2-Eq4-Eq1 (Definido)
  6-1-Eq1-Eq2-"semi1"
  6-2-Eq3-Eq4-"semi2"
  6-3-"tercer"-"semi1"-"semi2" (No definido)
  9-1-Eq2-Eq1-"final" (Definido)
  11-1-Eq1-Eq2-"cuartos1" (Definido)
  11-2-Eq7-Eq8-"cuartos4" (Definido)
  11-3-Eq3-Eq4-"cuartos2" (Definido)
  11-4-"cuartos1"-"cuartos2"-"semi1" (No definido)

*/

    /**
     * Test of confirmarAltaLlaveCopa method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testConfirmarAltaLlaveCopa() {
        System.out.println("confirmarAltaLlaveCopa");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        // confirmarAltaLlaveCopa(DataFechaHora fechaHora,String lugar);

        //listarPartidosDivNoAsig();
        //public void asignarDividendos(int idPart,int idComp, float divL, float divV, float divE)
        //  throws ExNoExisteCompeticion, ExNoExistePartidoEnCompeticion,
        //      ExDividendosYaAsignados,ExDividendosInvalidos;

        

        // Asigno dividendos al partido cuartos1 lo termino y veo tener el ganador en la llave semi1
        try {
            // Asigno dividendo al cuartos1
            assertTrue(icc.listarCopaNoDefinida().size()==3);
            assertTrue(icc.listarPartidosDivNoAsig().size()==10);
            icc.asignarDividendos(1,11,111,112,113);

            // termino el cuartos1
            DataFechaHora dfh = new DataFechaHora(1,1,1,1,1);
            DataInfoPartido dataIP = new DataInfoPartido(1,11,"copaTest11",TipoCompeticion.Copa, // idPart, idC, nomC, tipoC
                "Eq1","Eq2",1,2,"Ubilla",dfh,"cuartos1","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            icc.listarPartidosConDivAsignado(11);
            DataPartido dataP = icc.seleccionarPartido(dataIP);
            assertTrue(dataP.getDataInfoPart().getNomLocal().equals("Eq1"));
            assertTrue(dataP.getDataInfoPart().getNomVisita().equals("Eq2"));
            assertTrue(dataP.getDataInfoPart().getNomLlave().equals("cuartos1"));
            icc.ingresarResultado(2,0,1,0);
            icc.ingresarJugador(1,1);
            icc.ingresarJugador(4,1);
            icc.ingresarJugador(2,2);
            icc.ingresarJugador(3,2);
            DataResumen dr = icc.listarIngresos();
            icc.confirmarIngreso(true);

            // Si o si en la llave con nombre semi1 tiene que estar el equipo ganador Eq1
            icc.verInfoCompeticion(11);
            List<DataPartido> partidos = icc.infoPartidosCompeticion();
            assertTrue(partidos.size()==4);
            for(DataPartido partido: partidos) {
                if (partido.getDataInfoPart().getNomLlave().equals("semi1")){
                    assertTrue(partido.getDataInfoPart().getIdLocal()==1);
                    assertTrue(partido.getDataInfoPart().getIdVisita()==-1);
                }
            }

            // Asigno dividendo al cuartos4
            assertTrue(icc.listarCopaNoDefinida().size()==3);
            icc.asignarDividendos(2,11,2,5,6);

            // Termino el partido  Eq7-Eq8-"cuartos4" (Definido)
            dataIP = new DataInfoPartido(2,11,"copaTest11",TipoCompeticion.Copa, // idPart, idC, nomC, tipoC
                "Eq7","Eq8",7,8,"Ubilla",dfh,"cuartos4","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            icc.listarPartidosConDivAsignado(11);
            dataP = icc.seleccionarPartido(dataIP);
            icc.ingresarResultado(0,0,0,1);
            icc.ingresarJugador(1,7);
            icc.ingresarJugador(4,8);
            icc.ingresarJugador(2,8);
            icc.ingresarJugador(3,8);
            icc.ingresarJugador(8,8);
            dr = icc.listarIngresos();
            icc.confirmarIngreso(true);

            System.out.println("*************************************************** cerca");

            // Si o si en la llave con nombre cuartos4 tiene que estar el equipo ganador Eq1
            icc.verInfoCompeticion(11);
            partidos = icc.infoPartidosCompeticion();
            assertTrue(partidos.size()==4);
            for(DataPartido partido: partidos) {
                if (partido.getDataInfoPart().getNomLlave().equals("cuartos4")){
                    assertTrue(partido.getEstaFinalizado()==true);
                    assertTrue(partido.getGolesL()==0 && partido.getGolesV()==0);
                } 
            }

            // Asigno dividendo al cuartos2
            assertTrue(icc.listarCopaNoDefinida().size()==3);
            icc.asignarDividendos(3,11,8,9,11);

            // Termino el partido cuartos2 y gano el eq4

            dataIP = new DataInfoPartido(3,11,"copaTest11",TipoCompeticion.Copa, // idPart, idC, nomC, tipoC
                "Eq3","Eq4",3,4,"Ubilla",dfh,"cuartos2","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            icc.listarPartidosConDivAsignado(11);
            dataP = icc.seleccionarPartido(dataIP);
            icc.ingresarResultado(0,0,5,0);
            icc.ingresarJugador(1,3);
            icc.ingresarJugador(4,3);
            icc.ingresarJugador(2,3);
            icc.ingresarJugador(3,4);
            icc.ingresarJugador(8,4);
            dr = icc.listarIngresos();
            icc.confirmarIngreso(true);

            // Si o si en la llave con nombre semi1 tiene que estar el equipo ganador Eq4
            icc.verInfoCompeticion(11);
            partidos = icc.infoPartidosCompeticion();
            assertTrue(partidos.size()==4);
            for(DataPartido partido: partidos) {
                if (partido.getDataInfoPart().getNomLlave().equals("semi1")){
                    assertTrue(partido.getEstaFinalizado()==false);
                    assertTrue(partido.getDataInfoPart().getNomLlavePreLoc().equals("cuartos1"));
                    assertTrue(partido.getDataInfoPart().getNomLlavePreVis().equals("cuartos2"));
                    assertTrue(partido.getDataInfoPart().getIdVisita()==4);
                }
            }

            // Creo cuartos3
            assertTrue(icc.listarCopaNoDefinida().size()==3);
            icc.ingresarDatosLlaveDeCopa(11,"cuartos3");
            assertTrue(icc.listarEquiposNoAsignadosCopa().size()==2);
            icc.ingresarDatosPartida(5,6);
            icc.ingresarTipoLlave(true,false);
            icc.confirmarAltaLlaveCopa(new DataFechaHora(1,1,1,1,1),"lugCopa");

            // Creo semi2
            assertTrue(icc.listarCopaNoDefinida().size()==3);
            icc.ingresarDatosLlaveDeCopa(11,"semi2");
            assertTrue(icc.listarEquiposNoAsignadosCopa().isEmpty());
            icc.ingresarLlavesPredecesoras("cuartos3","cuartos4");
            icc.ingresarTipoLlave(true,false);
            icc.confirmarAltaLlaveCopa(new DataFechaHora(1,1,1,1,1),"lugCopa");

            // Si o si en la llave con nombre semi2 tiene que estar el equipo ganador Eq8
            icc.verInfoCompeticion(11);
            partidos = icc.infoPartidosCompeticion();
            assertTrue(partidos.size()==6);
            for(DataPartido partido: partidos) {
                if (partido.getDataInfoPart().getNomLlave().equals("semi2")){
                    assertTrue(partido.getEstaFinalizado()==false);
                    assertTrue(partido.getDataInfoPart().getNomLlavePreLoc().equals("cuartos3"));
                    assertTrue(partido.getDataInfoPart().getNomLlavePreVis().equals("cuartos4"));
                    assertTrue(partido.getDataInfoPart().getIdVisita()==8);
                }
            }
            
            // Creo tercer puesto
            assertTrue(icc.listarCopaNoDefinida().size()==3);
            icc.ingresarDatosLlaveDeCopa(11,"tercer");
            icc.ingresarLlavesPredecesoras("semi1","semi2");
            icc.ingresarTipoLlave(false,false);
            icc.confirmarAltaLlaveCopa(new DataFechaHora(1,1,1,1,1),"lugCopa");

            // tercer y cuarto puesto queda definida!
            icc.verInfoCompeticion(11);
            partidos = icc.infoPartidosCompeticion();
            assertTrue(partidos.size()==7);
            for(DataPartido partido: partidos) {
                if (partido.getDataInfoPart().getNomLlave().equals("tercer")){
                    assertTrue(partido.getEstaFinalizado()==false);
                    assertTrue(partido.getDataInfoPart().getNomLlavePreLoc().equals("semi1"));
                    assertTrue(partido.getDataInfoPart().getNomLlavePreVis().equals("semi2"));
                }
            }

            // Asigno dividendo al semi1
            assertTrue(icc.listarCopaNoDefinida().size()==3);
            icc.asignarDividendos(4,11,111,112,113);


            /// termino el semi1
            dataIP = new DataInfoPartido(4,11,"copaTest11",TipoCompeticion.Copa, // idPart, idC, nomC, tipoC
                "Eq1","Eq4",1,4,"Ubilla",dfh,"semi1","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            icc.listarPartidosConDivAsignado(11);
            dataP = icc.seleccionarPartido(dataIP);
            icc.ingresarResultado(0,0,5,0);
            icc.ingresarJugador(1,1);
            icc.ingresarJugador(3,4);
            dr = icc.listarIngresos();   
            icc.confirmarIngreso(true);
            // Si o si en la llave tercer puesto tiene que estar el eq4
            icc.verInfoCompeticion(11);
            partidos = icc.infoPartidosCompeticion();
            assertTrue(partidos.size()==7);
            for(DataPartido partido: partidos) {
                if (partido.getDataInfoPart().getNomLlave().equals("tercer")){
                    assertTrue(partido.getEstaFinalizado()==false);
                    assertTrue(partido.getDataInfoPart().getNomLlavePreLoc().equals("semi1"));
                    assertTrue(partido.getDataInfoPart().getNomLlavePreVis().equals("semi2"));
                    assertTrue(partido.getDataInfoPart().getIdLocal()==1);
                    assertTrue(partido.getDataInfoPart().getIdVisita()==-1);
                }
            }

            // Creo final
            assertTrue(icc.listarCopaNoDefinida().size()==3);
            icc.ingresarDatosLlaveDeCopa(11,"final");
            icc.ingresarLlavesPredecesoras("semi1","semi2");
            icc.ingresarTipoLlave(false,true);
            icc.confirmarAltaLlaveCopa(new DataFechaHora(1,1,1,1,1),"lugCopa");

            // si o si Eq1 es el local de la final
            icc.verInfoCompeticion(11);
            partidos = icc.infoPartidosCompeticion();
            assertTrue(partidos.size()==8);
            for(DataPartido partido: partidos) {
                if (partido.getDataInfoPart().getNomLlave().equals("final")){
                    assertTrue(partido.getEstaFinalizado()==false);
                    assertTrue(partido.getDataInfoPart().getNomLlavePreLoc().equals("semi1"));
                    assertTrue(partido.getDataInfoPart().getNomLlavePreVis().equals("semi2"));
                    assertTrue(partido.getDataInfoPart().getIdLocal()==4);
                    assertTrue(partido.getDataInfoPart().getIdVisita()==-1);
                }
            }

            assertTrue(icc.listarCopaNoDefinida().size()==2);

            // Asigno dividendo al cuartos3
            assertTrue(icc.listarCopaNoDefinida().size()==2);
            icc.asignarDividendos(5,11,111,112,113);


            /// termino el cuartos3
            dataIP = new DataInfoPartido(5,11,"copaTest11",TipoCompeticion.Copa, // idPart, idC, nomC, tipoC
                "Eq5","Eq6",5,6,"Ubilla",dfh,"cuartos3","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            icc.listarPartidosConDivAsignado(11);
            dataP = icc.seleccionarPartido(dataIP);
            icc.ingresarResultado(0,1,0,0);
            icc.ingresarJugador(9,5);
            icc.ingresarJugador(10,6);
            dr = icc.listarIngresos();
            icc.confirmarIngreso(true);
            // Si o si en la llave semi2 tiene que estar eq5
            icc.verInfoCompeticion(11);
            partidos = icc.infoPartidosCompeticion();
            assertTrue(partidos.size()==8);
            for(DataPartido partido: partidos) {
                if (partido.getDataInfoPart().getNomLlave().equals("semi2")){
                    assertTrue(partido.getEstaFinalizado()==false);
                    assertTrue(partido.getDataInfoPart().getNomLlavePreLoc().equals("cuartos3"));
                    assertTrue(partido.getDataInfoPart().getNomLlavePreVis().equals("cuartos4"));
                    assertTrue(partido.getDataInfoPart().getIdVisita()==8);
                    assertTrue(partido.getDataInfoPart().getIdLocal()==5);
                }
                if (partido.getDataInfoPart().getNomLlave().equals("semi1")){
                    assertTrue(partido.getEstaFinalizado()==true);
                }
                if (partido.getDataInfoPart().getNomLlave().equals("cuartos3")){
                    assertTrue(partido.getEstaFinalizado()==true);
                }
                if (partido.getDataInfoPart().getNomLlave().equals("cuartos4")){
                    assertTrue(partido.getEstaFinalizado()==true);
                }
                if (partido.getDataInfoPart().getNomLlave().equals("final")){   
                    assertTrue(partido.getDataInfoPart().getIdVisita()==-1);
                    assertTrue(partido.getDataInfoPart().getIdLocal()==4);
                }
            }

            // Asigno dividendo al semi2
            assertTrue(icc.listarCopaNoDefinida().size()==2);
            icc.asignarDividendos(6,11,111,112,113);

            /// termino el semi2
            dataIP = new DataInfoPartido(6,11,"copaTest11",TipoCompeticion.Copa, // idPart, idC, nomC, tipoC
                "Eq5","Eq8",5,8,"Ubilla",dfh,"semi2","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            icc.listarPartidosConDivAsignado(11);
            dataP = icc.seleccionarPartido(dataIP);
            icc.ingresarResultado(9,0,0,0);
            icc.ingresarJugador(9,5);
            icc.ingresarJugador(8,8);
            dr = icc.listarIngresos();
            icc.confirmarIngreso(true);
            // Si o si en la llave final tengo al 1-5 y en el tercer 8-4
            icc.verInfoCompeticion(11);
            partidos = icc.infoPartidosCompeticion();
            assertTrue(partidos.size()==8);
            System.out.println("***************************************************** FINAL");
            for(DataPartido partido: partidos) {
                if (partido.getDataInfoPart().getNomLlave().equals("final")){
                    assertTrue(partido.getEstaFinalizado()==false);
                    assertTrue(partido.getDataInfoPart().getNomLlavePreLoc().equals("semi1"));
                    assertTrue(partido.getDataInfoPart().getNomLlavePreVis().equals("semi2"));
                    assertTrue(partido.getDataInfoPart().getIdVisita()==5);
                    assertTrue(partido.getDataInfoPart().getIdLocal()==4);
                }
                if (partido.getDataInfoPart().getNomLlave().equals("tercer")){
                    assertTrue(partido.getEstaFinalizado()==false);
                    assertTrue(partido.getDataInfoPart().getNomLlavePreLoc().equals("semi1"));
                    assertTrue(partido.getDataInfoPart().getNomLlavePreVis().equals("semi2"));
                    assertTrue(partido.getDataInfoPart().getIdVisita()==8);
                    assertTrue(partido.getDataInfoPart().getIdLocal()==1);
                }

            }
            System.out.println("***************************************************** HOLA");

            // Asigno dividendo al tercer
            assertTrue(icc.listarCopaNoDefinida().size()==2);
            icc.asignarDividendos(7,11,111,112,113);


            /// termino el tercer
            dataIP = new DataInfoPartido(7,11,"copaTest11",TipoCompeticion.Copa, // idPart, idC, nomC, tipoC
                "Eq1","Eq8",1,8,"Ubilla",dfh,"tercer","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            icc.listarPartidosConDivAsignado(11);
            dataP = icc.seleccionarPartido(dataIP);
            icc.ingresarResultado(0,0,1,0);
            icc.ingresarJugador(8,8);
            icc.ingresarJugador(3,1);
            dr = icc.listarIngresos();
            icc.confirmarIngreso(true);
            // Si o si en la llave final tengo al 1-5 y en el tercer 8-4
            icc.verInfoCompeticion(11);
            partidos = icc.infoPartidosCompeticion();
            assertTrue(partidos.size()==8);
            for(DataPartido partido: partidos) {
                if (partido.getDataInfoPart().getNomLlave().equals("final")){
                    assertTrue(partido.getDataInfoPart().getNomLlavePreLoc().equals("semi1"));
                    assertTrue(partido.getDataInfoPart().getNomLlavePreVis().equals("semi2"));
                    assertTrue(partido.getDataInfoPart().getIdVisita()==5);
                    assertTrue(partido.getDataInfoPart().getIdLocal()==4);
                    assertTrue(partido.getEstaFinalizado()==false);
                }
                if (partido.getDataInfoPart().getNomLlave().equals("tercer")){
                    
                    assertTrue(partido.getDataInfoPart().getNomLlavePreLoc().equals("semi1"));
                    assertTrue(partido.getDataInfoPart().getNomLlavePreVis().equals("semi2"));
                    assertTrue(partido.getDataInfoPart().getIdVisita()==8);
                    assertTrue(partido.getDataInfoPart().getIdLocal()==1);
                    assertTrue(partido.getEstaFinalizado()==true);

                }

            }

            // Asigno dividendo a la final
            assertTrue(icc.listarCopaNoDefinida().size()==2);
            icc.asignarDividendos(8,11,111,112,113);


            /// termino la final
            dataIP = new DataInfoPartido(8,11,"copaTest11",TipoCompeticion.Copa, // idPart, idC, nomC, tipoC
                "Eq4","Eq5",4,5,"Ubilla",dfh,"final","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            icc.listarPartidosConDivAsignado(11);
            dataP = icc.seleccionarPartido(dataIP);
            icc.ingresarResultado(1,0,0,0);
            icc.ingresarJugador(1,4);
            icc.ingresarJugador(5,5);
            dr = icc.listarIngresos();
            icc.confirmarIngreso(true);
            // Si o si en la llave final tengo al 1-5 y en el tercer 8-4
            icc.verInfoCompeticion(11);
            partidos = icc.infoPartidosCompeticion();
            assertTrue(partidos.size()==8);
            for(DataPartido partido: partidos) {
                if (partido.getDataInfoPart().getNomLlave().equals("final")){
                    assertTrue(partido.getDataInfoPart().getNomLlavePreLoc().equals("semi1"));
                    assertTrue(partido.getDataInfoPart().getNomLlavePreVis().equals("semi2"));
                    assertTrue(partido.getDataInfoPart().getIdVisita()==5);
                    assertTrue(partido.getDataInfoPart().getIdLocal()==4);
                    assertTrue(partido.getEstaFinalizado()==true);
                }
                if (partido.getDataInfoPart().getNomLlave().equals("tercer")){
                    assertTrue(partido.getDataInfoPart().getNomLlavePreLoc().equals("semi1"));
                    assertTrue(partido.getDataInfoPart().getNomLlavePreVis().equals("semi2"));
                    assertTrue(partido.getDataInfoPart().getIdVisita()==8);
                    assertTrue(partido.getDataInfoPart().getIdLocal()==1);
                    assertTrue(partido.getEstaFinalizado()==true);

                }

            }



        }
        catch (Exception exc){
            System.out.println(exc.getMessage());
            assertTrue(false);
        }




    }

    /**
     * Test of listarCompeticionesNoFinalizadas method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testListarCompeticionesNoFinalizadas() {
        System.out.println("listarCompeticionesNoFinalizadas");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        List<DataIdNombre> list = icc.listarCompeticionesNoFinalizadas();
        assertTrue(list.isEmpty());
        try {
            // Asigno dividendo al partido con id=1
            icc.asignarDividendos(1,1,2,2,3);
            list = icc.listarCompeticionesNoFinalizadas();
            assertTrue(list.size()==1);
            icc.asignarDividendos(1,4,2,2,3);
            list = icc.listarCompeticionesNoFinalizadas();
            assertTrue(list.size()==2);
            icc.asignarDividendos(1,9,2,2,3);
            list = icc.listarCompeticionesNoFinalizadas();
            assertTrue(list.size()==3);

        }
        catch (Exception exc) {
            System.out.println(exc.getMessage());
            assertTrue(false);
        }

    }

    /*
*  jugadores 1..10
  equipos: 1..10
eq1 - jug1,jug4
eq2-jug2,jug3
eq3-jug1,jug2,jug4
eq4-jug3,jug8
eq5-jug9-jug5
eq6-jug10
eq7 - jug1
eq8-jug2,jug3,jug4,jug8

  competiciones:
  1-comp1-PartidoIndividual-Eq1-Eq2 !!!!!!!!!!!!DEFINIDA
  2-comp2-PartidoIndividual-Eq3-Eq4 !!!!!!!!!!!!! NO DEFINIDA
  3-comp3-Liga-(GolFav,DifGol,Res)-Eq1(2)-Eq2(4)-Eq3(6)-Eq4(8) !!!!!!!!!!!!! NO DEFINIDA
  4-comp4-Liga(Definida)-(GolFav,DifGol,Res)-Eq1(4)-Eq4(4) !!!!!!!!!!!!!DEFINIDA
  5-ligaTest1-Liga-(DifGol,Res,GolFav)-Eq1(2)-Eq2(3) !!!!!!!!!!!!! NO DEFINIDA
  6-copaTest2-Copa-Eq1(2)-Eq2(3)-Eq3(2)-Eq4(3) !!!!!!!!!!!!! NO DEFINIDA
  7-indTest3-PartidoIndividual-Eq3-Eq4 !!!!!!!!!!!!! NO DEFINIDA
  8-ligaTest4-Liga-(Res,GolFav,DifGol)-Eq1(2)-Eq2(3)-Eq3(3)
  9-copaTest5-Copa-Eq1(2)-Eq2(3) !!!!!!!!!!!!!DEFINIDA
  10-indTest6-PartidoIndividual-Eq2-Eq4 !!!!!!!!!!!!! NO DEFINIDA
  11-copaTest11-Copa-Eq1..Eq8 // Dividendos: idEq*2 !!!!!!!!!!!!! FINALIZADA
  12-copaTest12-Copa-Eq9-Eq10 NO DEFINIDA

  partidos (idComp,idPart,loc,vis)
  1-1-Eq1-Eq2 (DivAsig)
  3-1-Eq1-Eq2 (Definido)
  4-1-Eq1-Eq4 (DivAsig)
  4-2-Eq4-Eq1 (Definido)
  6-1-Eq1-Eq2-"semi1" (Finalizado)
  6-2-Eq3-Eq4-"semi2" (Finalizado)
  6-3-"tercer"-"semi1"-"semi2" (Definido)
  9-1-Eq2-Eq1-"final" (DivAsi)
  11-1-Eq1-Eq2-"cuartos1" (Finalizado) Gano Eq1
  11-2-Eq7-Eq8-"cuartos4" (Finalizado) Gano Eq8
  11-3-Eq3-Eq4-"cuartos2" (Finalizado) Gano 4
  11-4-Eq1-Eq4-"semi1" (Finalizado) Gano 1
  11-5-Eq5-Eq6-"cuartos3" (Finalizado) Gano 5
  11-6-Eq5-Eq8-"semi2" (Finalizado) Gano 5
  11-7-Eq4-Eq8-"tercer" (Finalizado) Gano 8
  11-8-Eq1-Eq5-"final" (Finalizado) Gano 1
*/


    /**
     * Test of listarPartidosDefinidos method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void listarPartidosConDivAsignado() {
        System.out.println("listarPartidosConDivAsignado");
        IControladorCompeticiones icc = new ControladorCompeticiones();


        // Pruebo que no existe competicion
        try {
            List<DataInfoPartido> infoPartido = icc.listarPartidosConDivAsignado(0);
            assertTrue(false);
        }
        catch (ExNoExisteCompeticion exc) {
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        try {
            List<DataInfoPartido> infoPartidos = icc.listarPartidosConDivAsignado(1);
            assertTrue(infoPartidos.size()==1);
            assertTrue(infoPartidos.get(0).getIdPar()==1);

            infoPartidos = icc.listarPartidosConDivAsignado(2);
            assertTrue(infoPartidos.isEmpty());

            infoPartidos = icc.listarPartidosConDivAsignado(3);
            assertTrue(infoPartidos.isEmpty());

            infoPartidos = icc.listarPartidosConDivAsignado(4);
            assertTrue(infoPartidos.size()==1);
            assertTrue(infoPartidos.get(0).getIdPar()==1);

            infoPartidos = icc.listarPartidosConDivAsignado(11);
            assertTrue(infoPartidos.isEmpty());

        }
        catch (ExNoExisteCompeticion exc) {
            assertTrue(false);
        }
        catch (Exception exc) {
            assertTrue(false);
        }
    }

    /**
     * Test of seleccionarPartido method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testSeleccionarPartido() {
        System.out.println("seleccionarPartido");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        //seleccionarPartido(DataInfoPartido dataIP)
        //    throws ExDatosNoAsignados,ExPartidoNoFinalizable;//cambio

        DataFechaHora dfh = new DataFechaHora(1,1,1,1,1);
        DataInfoPartido dataIP = null;
        
        // Pruebo que no existen datos asignados
        try {
            dataIP = new DataInfoPartido(1,11,"copaTest11",TipoCompeticion.Copa, // idPart, idC, nomC, tipoC
                "Eq1","Eq2",1,2,"Ubilla",dfh,"","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora
            
            icc.limpiarMemoriaAuxiliar();
            //List<DataInfoPartido> infoPartido = icc.listarPartidosConDivAsignado(0);
            icc.seleccionarPartido(dataIP);
            assertTrue(false);

        }
        catch (ExDatosNoAsignados exc) {
            assertTrue(true);
        }
        catch (ExPartidoNoFinalizable exc) {
            assertTrue(false);
        }
        catch (Exception exc) {
            assertTrue(false);
        }
        
        // Pruebo que no esta el partido en la competicion partido individual
        try {
            dataIP = new DataInfoPartido(2,1,"comp1",TipoCompeticion.PartidoIndividual, // idPart, idC, nomC, tipoC
                "Eq1","Eq2",1,2,"Ubilla",dfh,"","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora
            
            List<DataInfoPartido> infoPartido = icc.listarPartidosConDivAsignado(1);
            icc.seleccionarPartido(dataIP);
            assertTrue(false);
        }
        catch (ExDatosNoAsignados exc) {
            assertTrue(false);
        }
        catch (ExPartidoNoFinalizable exc) {
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }
        
        // Pruebo que no esta definido el partido para una liga
        try {
            dataIP = new DataInfoPartido(2,3,"comp3",TipoCompeticion.Liga, // idPart, idC, nomC, tipoC
                "Eq3","Eq2",3,2,"Ubilla",dfh,"","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora
            
            List<DataInfoPartido> infoPartido = icc.listarPartidosConDivAsignado(3);
            icc.seleccionarPartido(dataIP);
            assertTrue(false);
        }
        catch (ExDatosNoAsignados exc) {
            assertTrue(false);
        }
        catch (ExPartidoNoFinalizable exc) {
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Pruebo que no esta definido el partido para una copa
        try {
            dataIP = new DataInfoPartido(9,11,"copaTest11",TipoCompeticion.Copa, // idPart, idC, nomC, tipoC
                "Eq3","Eq2",3,2,"Ubilla",dfh,"llave1","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            List<DataInfoPartido> infoPartido = icc.listarPartidosConDivAsignado(11);
            icc.seleccionarPartido(dataIP);
            assertTrue(false);
        }
        catch (ExDatosNoAsignados exc) {
            assertTrue(false);
        }
        catch (ExPartidoNoFinalizable exc) {
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Pruebo partido sin dividendos asignados partido individual
        try {
            // Defino partido individual
            icc.ingresarDatosPartidoIndividual(2,4,3,dfh,"lug");

            dataIP = new DataInfoPartido(1,2,"comp2",TipoCompeticion.PartidoIndividual, // idPart, idC, nomC, tipoC
                "Eq4","Eq3",4,3,"Ubilla",dfh,"","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            List<DataInfoPartido> infoPartido = icc.listarPartidosConDivAsignado(2);
            icc.seleccionarPartido(dataIP);
            assertTrue(false);
        }
        catch (ExDatosNoAsignados exc) {
            assertTrue(false);
        }
        catch (ExPartidoNoFinalizable exc) {
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Pruebo partido sin div para liga
        try {
            dataIP = new DataInfoPartido(1,3,"comp3",TipoCompeticion.Liga, // idPart, idC, nomC, tipoC
                "Eq1","Eq2",1,2,"Ubilla",dfh,"","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            List<DataInfoPartido> infoPartido = icc.listarPartidosConDivAsignado(3);
            icc.seleccionarPartido(dataIP);
            assertTrue(false);
        }
        catch (ExDatosNoAsignados exc) {
            assertTrue(false);
        }
        catch (ExPartidoNoFinalizable exc) {
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Pruebo que no esta definido el partido para una copa
        try {
            dataIP = new DataInfoPartido(3,6,"copaTest2",TipoCompeticion.Copa, // idPart, idC, nomC, tipoC
                "Eq3","Eq2",3,2,"Ubilla",dfh,"semi2","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            List<DataInfoPartido> infoPartido = icc.listarPartidosConDivAsignado(6);
            icc.seleccionarPartido(dataIP);
            assertTrue(false);
        }
        catch (ExDatosNoAsignados exc) {
            assertTrue(false);
        }
        catch (ExPartidoNoFinalizable exc) {
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }



        // Pruebo partido finalizado para liga
        try {
            // finalizo partido 1 de liga 4
            dataIP = new DataInfoPartido(1,4,"comp4",TipoCompeticion.Liga, // idPart, idC, nomC, tipoC
                "Eq1","Eq4",1,4,"Ubilla",dfh,"","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            icc.listarPartidosConDivAsignado(4);
            icc.seleccionarPartido(dataIP);
            icc.ingresarResultado(0,0,0,0);
            icc.ingresarJugador(1,1);
            icc.ingresarJugador(3,4);
            icc.listarIngresos();
            icc.confirmarIngreso(true);

            List<DataInfoPartido> infoPartido = icc.listarPartidosConDivAsignado(4);
            icc.seleccionarPartido(dataIP);
            assertTrue(false);
        }
        catch (ExDatosNoAsignados exc) {
            assertTrue(false);
        }
        catch (ExPartidoNoFinalizable exc) {
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Pruebo con part finalizado para una copa
        try {
            dataIP = new DataInfoPartido(8,11,"copaTest11",TipoCompeticion.Copa, // idPart, idC, nomC, tipoC
                "Eq1","Eq5",1,5,"Ubilla",dfh,"final","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            List<DataInfoPartido> infoPartido = icc.listarPartidosConDivAsignado(11);
            icc.seleccionarPartido(dataIP);
            assertTrue(false);
        }
        catch (ExDatosNoAsignados exc) {
            assertTrue(false);
        }
        catch (ExPartidoNoFinalizable exc) {
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }


    }

    /**
     * Test of ingresarResultado method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testIngresarResultado() throws Exception {
        System.out.println("ingresarResultado");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        //public void ingresarResultado(int golesLocal,int penalLocal,
        //    int golesVisitante,int penalVisita)
        //        throws ExDatosNoAsignados,ExGolesInvalidos;


        // Pruebo que no existen los datos cargados
        try {
            icc.limpiarMemoriaAuxiliar();
            icc.listarPartidosConDivAsignado(1);
            icc.ingresarResultado(1,2,1,5);
            assertTrue(false);
        }
        catch (ExDatosNoAsignados exc) {
            assertTrue(true);
        }
        catch (ExGolesInvalidos exc) {
            assertTrue(false);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        DataFechaHora dfh = new DataFechaHora(1,1,1,1,1);
        DataInfoPartido dataIP = null;

        // Pruebo que no existen datos asignados
        try {
            dataIP = new DataInfoPartido(1,11,"copaTest11",TipoCompeticion.Copa, // idPart, idC, nomC, tipoC
                "Eq1","Eq2",1,2,"Ubilla",dfh,"","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            icc.limpiarMemoriaAuxiliar();
            //List<DataInfoPartido> infoPartido = icc.listarPartidosConDivAsignado(0);
            icc.seleccionarPartido(dataIP);
            icc.ingresarResultado(1,2,1,5);
            assertTrue(false);
        }
        catch (ExDatosNoAsignados exc) {
            assertTrue(true);
        }
        catch (ExGolesInvalidos exc) {
            assertTrue(false);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Pruebo que los goles son invalidos
        try {
            dataIP = new DataInfoPartido(1,1,"comp1",TipoCompeticion.PartidoIndividual, // idPart, idC, nomC, tipoC
                "Eq1","Eq2",1,2,"Ubilla",dfh,"","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            icc.listarPartidosConDivAsignado(1);
            icc.seleccionarPartido(dataIP);
            icc.ingresarResultado(1,-2,1,5);
            assertTrue(false);
        }
        catch (ExDatosNoAsignados exc) {
            assertTrue(false);
        }
        catch (ExGolesInvalidos exc) {
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Pruebo que los goles son iguales y penales iguales en copa
        try {
            dataIP = new DataInfoPartido(1,9,"copaTest5",TipoCompeticion.Copa, // idPart, idC, nomC, tipoC
                "Eq2","Eq1",2,1,"Ubilla",dfh,"final","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            icc.listarPartidosConDivAsignado(9);
            icc.seleccionarPartido(dataIP);
            icc.ingresarResultado(0,0,0,0);
            assertTrue(false);
        }
        catch (ExDatosNoAsignados exc) {
            assertTrue(false);
        }
        catch (ExGolesInvalidos exc) {
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }
    }

    /**
     * Test of listaJugadores method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testListaJugadores() {
        System.out.println("listaJugadores");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        // public List<DataIdNombre> listaJugadores();

        List<DataIdNombre> dataJugadores = icc.listaJugadores();

        assertEquals(10,dataJugadores.size());

        int [] actuals = new int[15];
        int [] expecteds=new int[15];
        for (int i=0; i<actuals.length; i++)  actuals[i]= 0;
        for (int i=0; i<expecteds.length; i++)  expecteds[i]= 1;
        
        expecteds[0] = expecteds[11] = expecteds[12] =expecteds[13] =expecteds[14] =0;

        for (DataIdNombre dataIN: dataJugadores){
            actuals[dataIN.getId()] = 1;
        }

        assertArrayEquals(expecteds,actuals);
    }

    /**
     * Test of ingresarJugador method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testIngresarJugador() {
        System.out.println("ingresarJugador");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        //public void ingresarJugador(int idJugador,int idEquipo)
        //    throws ExNoExisteEquipo,ExNoExisteJugador,ExJugadorYaIngresado;

        // Creo un partido de comp4 que es liga y le asigno dividendos
        DataFechaHora dfh = null;
        try {
            dfh = new DataFechaHora(1,1,1,1,1);
            icc.ingresarIdLiga(3);
            icc.seleccionarEncuentroADefinir (3,4,dfh,"lug");
            // Asigno dividendo al partido
            icc.asignarDividendos(2,3,6,6,6);
        }
        catch (Exception exc){
            assertTrue(false);
        }


        // Pruebo que no existe equipo
        try{
            DataInfoPartido dataIP = new DataInfoPartido(2,3,"comp3",TipoCompeticion.Liga, // idPart, idC, nomC, tipoC
                "Eq3","Eq4",3,4,"Ubilla",dfh,"","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            icc.listarPartidosConDivAsignado(3);
            icc.seleccionarPartido(dataIP);
            icc.ingresarResultado(1,0,0,0);
            icc.ingresarJugador(1,0);
            assertTrue(false);
        } 
        catch (ExNoExisteEquipo e) {
            assertTrue(true);
        }
        catch (ExNoExisteJugador e) {
            assertTrue(false);
        }
        catch (ExJugadorYaIngresado e) {
            assertTrue(false);
        }
        catch (Exception e) {
            assertTrue(false);
        }

        // Pruebo que el equipo no juega la liga
        try{
            DataInfoPartido dataIP = new DataInfoPartido(2,3,"comp3",TipoCompeticion.Liga, // idPart, idC, nomC, tipoC
                "Eq3","Eq4",3,4,"Ubilla",dfh,"","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            icc.listarPartidosConDivAsignado(3);
            icc.seleccionarPartido(dataIP);
            icc.ingresarResultado(1,0,0,0);
            icc.ingresarJugador(1,8);
            assertTrue(false);
        }
        catch (ExNoExisteEquipo e) {
            assertTrue(true);
        }
        catch (ExNoExisteJugador e) {
            assertTrue(false);
        }
        catch (ExJugadorYaIngresado e) {
            assertTrue(false);
        }
        catch (Exception e) {
            assertTrue(false);
        }

        // Pruebo que el equipo juega la liga pero no esta asignado al partido
        try{
            DataInfoPartido dataIP = new DataInfoPartido(2,3,"comp3",TipoCompeticion.Liga, // idPart, idC, nomC, tipoC
                "Eq3","Eq4",3,4,"Ubilla",dfh,"","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            icc.listarPartidosConDivAsignado(3);
            icc.seleccionarPartido(dataIP);
            icc.ingresarResultado(1,0,0,0);
            icc.ingresarJugador(1,1);
            assertTrue(false);
        }
        catch (ExNoExisteEquipo e) {
            assertTrue(true);
        }
        catch (ExNoExisteJugador e) {
            assertTrue(false);
        }
        catch (ExJugadorYaIngresado e) {
            assertTrue(false);
        }
        catch (Exception e) {
            assertTrue(false);
        }

        // Pruebo que no existe el jugador
        try{
            DataInfoPartido dataIP = new DataInfoPartido(2,3,"comp3",TipoCompeticion.Liga, // idPart, idC, nomC, tipoC
                "Eq3","Eq4",3,4,"Ubilla",dfh,"","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            icc.listarPartidosConDivAsignado(3);
            icc.seleccionarPartido(dataIP);
            icc.ingresarResultado(1,0,0,0);
            icc.ingresarJugador(11,3);
            assertTrue(false);
        }
        catch (ExNoExisteEquipo e) {
            assertTrue(false);
        }
        catch (ExNoExisteJugador e) {
            assertTrue(true);
        }
        catch (ExJugadorYaIngresado e) {
            assertTrue(false);
        }
        catch (Exception e) {
            assertTrue(false);
        }
        // Agrego el jugador 1 al equipo 3
        try{
            DataInfoPartido dataIP = new DataInfoPartido(2,3,"comp3",TipoCompeticion.Liga, // idPart, idC, nomC, tipoC
                "Eq3","Eq4",3,4,"Ubilla",dfh,"","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            icc.listarPartidosConDivAsignado(3);
            icc.seleccionarPartido(dataIP);
            icc.ingresarResultado(1,0,0,0);
            icc.ingresarJugador(1,3);
        }
        catch (Exception e) {
            assertTrue(false);
        }

        // Pruebo que ya se agrego el jugador al equipo local,
        try{
            icc.ingresarJugador(1,3);
            assertTrue(false);
        }
        catch (ExNoExisteEquipo e) {
            assertTrue(false);
        }
        catch (ExNoExisteJugador e) {
            assertTrue(false);
        }
        catch (ExJugadorYaIngresado e) {
            assertTrue(true);
        }
        catch (Exception e) {
            assertTrue(false);
        }

        // Pruebo que ya se agrego el jugador al equipo visitante
        try{
            icc.ingresarJugador(1,4);
            assertTrue(false);
        }
        catch (ExNoExisteEquipo e) {
            assertTrue(false);
        }
        catch (ExNoExisteJugador e) {
            assertTrue(false);
        }
        catch (ExJugadorYaIngresado e) {
            assertTrue(true);
        }
        catch (Exception e) {
            assertTrue(false);
        }


    }

    /**
     * Test of listarIngresos method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testListarIngresos() {
        System.out.println("listarIngresos");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        //public DataResumen listarIngresos()
        //    throws ExDatosNoAsignados;

        // Creo un partido de comp4 que es liga y le asigno dividendos
        DataFechaHora dfh = new DataFechaHora(1,1,1,1,1);
        DataInfoPartido dataIP = null;

        icc.limpiarMemoriaAuxiliar();
        /// pruebo que los datos no esten asignados
        try{
            DataResumen dataR = icc.listarIngresos();
            assertTrue(false);
        }
        catch (ExDatosNoAsignados e) {
            assertTrue(true);
        }
        catch (Exception e) {
            assertTrue(false);
        }

        // Pongo el partido
        try{
            dataIP = new DataInfoPartido(2,3,"comp3",TipoCompeticion.Liga, // idPart, idC, nomC, tipoC
                "Eq3","Eq4",3,4,"Ubilla",dfh,"","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            icc.listarPartidosConDivAsignado(3);
            icc.seleccionarPartido(dataIP);
            icc.ingresarResultado(1,0,0,0);
            icc.ingresarJugador(1,3);
            icc.ingresarJugador(2,4);
            icc.ingresarJugador(3,3);
            icc.ingresarJugador(4,4);
            icc.ingresarJugador(5,3);
            icc.ingresarJugador(6,4);
            icc.ingresarJugador(7,3);
            icc.ingresarJugador(8,4);
            icc.ingresarJugador(9,3);
            icc.ingresarJugador(10,4);
        }
        catch (Exception e) {
            assertTrue(false);
        }

        // Veo el dataR
        try{
            DataResumen dataR = icc.listarIngresos();

            List<DataIdNombre> locales = dataR.getJugadoresLocal();
            List<DataIdNombre> visitantes = dataR.getJugadoresVisita();
            int[] actualesL = new int[11];
            int[] esperadosL = new int[11];
            int[] actualesV = new int[11];
            int[] esperadosV = new int[11];
            for (int i=0;i<11;i++) actualesL[i]=actualesV[i]=esperadosL[i]=esperadosV[i]=0;
            esperadosL[1] = esperadosL[3] = esperadosL[5] = esperadosL[7] = esperadosL[9] = 1;
            esperadosV[2] = esperadosV[4] = esperadosV[6] = esperadosV[8] = esperadosV[10] = 1;

            System.out.println("localess: ");
            for (DataIdNombre dataIN: locales){
                System.out.println(dataIN.getId()+"-");
                actualesL[dataIN.getId()]=1;
            }
            System.out.println("visitantes: ");
            for (DataIdNombre dataIN: visitantes){
                System.out.println(dataIN.getId()+"-");
                actualesV[dataIN.getId()]=1;
            }
            assertArrayEquals(esperadosL,actualesL);
            assertArrayEquals(esperadosV,actualesV);

            assertTrue(dataR.getGolesLocal()==1);
            assertTrue(dataR.getGolesVisita()==0);
            
        }
        catch (Exception e) {
            assertTrue(false);
        }
    }

    /*  jugadores 1..10
  equipos: 1..10
eq1 - jug1,jug4
eq2-jug2,jug3
eq3-jug1,jug2,jug4
eq4-jug3,jug8
eq5-jug9-jug5
eq6-jug10
eq7 - jug1
eq8-jug2,jug3,jug4,jug8

  competiciones:
  1-comp1-PartidoIndividual-Eq1-Eq2 !!!!!!!!!!!!DEFINIDA
  2-comp2-PartidoIndividual-Eq3-Eq4 !!!!!!!!!!!!! NO DEFINIDA
  3-comp3-Liga-(GolFav,DifGol,Res)-Eq1(2)-Eq2(4)-Eq3(6)-Eq4(8) !!!!!!!!!!!!! NO DEFINIDA
  4-comp4-Liga(Definida)-(GolFav,DifGol,Res)-Eq1(4)-Eq4(4) !!!!!!!!!!!!!DEFINIDA
  5-ligaTest1-Liga-(DifGol,Res,GolFav)-Eq1(2)-Eq2(3) !!!!!!!!!!!!! NO DEFINIDA
  6-copaTest2-Copa-Eq1(2)-Eq2(3)-Eq3(2)-Eq4(3) !!!!!!!!!!!!! NO DEFINIDA
  7-indTest3-PartidoIndividual-Eq3-Eq4 !!!!!!!!!!!!! NO DEFINIDA
  8-ligaTest4-Liga-(Res,GolFav,DifGol)-Eq1(2)-Eq2(3)-Eq3(3)
  9-copaTest5-Copa-Eq1(2)-Eq2(3) !!!!!!!!!!!!!DEFINIDA
  10-indTest6-PartidoIndividual-Eq2-Eq4 !!!!!!!!!!!!! NO DEFINIDA
  11-copaTest11-Copa-Eq1..Eq8 // Dividendos: idEq*2 !!!!!!!!!!!!! FINALIZADA
  12-copaTest12-Copa-Eq9-Eq10 NO DEFINIDA

  partidos (idComp,idPart,loc,vis)
  1-1-Eq1-Eq2 (DivAsig)
  2-1-Eq4-Eq3 (Definido)
  3-1-Eq1-Eq2 (Definido)
  3-2-Eq3-Eq4 (DivAsig)
  4-1-Eq1-Eq4 (Finalizado) Empate
  4-2-Eq4-Eq1 (Definido)
  6-1-Eq1-Eq2-"semi1" (Finalizado)
  6-2-Eq3-Eq4-"semi2" (Finalizado)
  6-3-"tercer"-"semi1"-"semi2" (Definido)
  9-1-Eq2-Eq1-"final" (DivAsi)
  11-1-Eq1-Eq2-"cuartos1" (Finalizado) Gano Eq1
  11-2-Eq7-Eq8-"cuartos4" (Finalizado) Gano Eq8
  11-3-Eq3-Eq4-"cuartos2" (Finalizado) Gano 4
  11-4-Eq1-Eq4-"semi1" (Finalizado) Gano 1
  11-5-Eq5-Eq6-"cuartos3" (Finalizado) Gano 5
  11-6-Eq5-Eq8-"semi2" (Finalizado) Gano 5
  11-7-Eq4-Eq8-"tercer" (Finalizado) Gano 8
  11-8-Eq1-Eq5-"final" (Finalizado) Gano 1

*/


    /**
     * Test of confirmarIngreso method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testConfirmarIngreso() {
        System.out.println("confirmarIngreso");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        //public void confirmarIngreso(boolean confirmar)
        //    throws ExDatosNoAsignados;
        
        icc.limpiarMemoriaAuxiliar();
        // Pruebo que falten datos
        try {
            icc.confirmarIngreso(true);
            assertTrue(false);
        }
        catch (ExDatosNoAsignados e) {
            assertTrue(true);
        }
        catch (Exception e) {
            assertTrue(false);
        }

        // Voy a finalizar el partido 2 de liga id=3 llamada comp3
        DataFechaHora dfh = new DataFechaHora(1,1,1,1,1);
        DataInfoPartido dataIP = null;
        // Pongo el partido
        try{
            dataIP = new DataInfoPartido(2,3,"comp3",TipoCompeticion.Liga, // idPart, idC, nomC, tipoC
                "Eq3","Eq4",3,4,"Ubilla",dfh,"","",""); //nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            List<DataInfoPartido> list = icc.listarPartidosConDivAsignado(3);
            // Vemos como esta el partido con div asig
            boolean encontre = false;
            for (DataInfoPartido dataInfoP: list) {
                if (dataInfoP.getIdPar()==2){
                    encontre = true;
                }
            }
            assertTrue(encontre);

            icc.seleccionarPartido(dataIP);
            icc.ingresarResultado(1,0,0,0);
            icc.ingresarJugador(1,3);
            icc.ingresarJugador(2,4);
            icc.ingresarJugador(3,3);
            icc.ingresarJugador(4,4);
            icc.ingresarJugador(5,3);
            icc.ingresarJugador(6,4);
            icc.ingresarJugador(7,3);
            icc.ingresarJugador(8,4);
            icc.ingresarJugador(9,3);
            icc.ingresarJugador(10,4);
            icc.listarIngresos();
        }
        catch (Exception e) {
            assertTrue(false);
        }

        // Pruebo finalizar
        try{
            icc.confirmarIngreso(true);
            // No lo tengo que encontrar con div asig pero no fin
            List<DataInfoPartido> list = icc.listarPartidosConDivAsignado(3);
            // Vemos como esta el partido con div asig
            boolean encontre = false;
            for (DataInfoPartido dataInfoP: list) {
                if (dataInfoP.getIdPar()==2){
                    encontre = true;
                }
            }
            assertTrue(!encontre);

        }
        catch (Exception e) {
            assertTrue(false);
        }


    }

    /**
     * Test of limpiarMemoriaAuxiliar method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testLimpiarMemoriaAuxiliar() {
        System.out.println("limpiarMemoriaAuxiliar");
        ControladorCompeticiones icc = new ControladorCompeticiones();
        icc.limpiarMemoriaAuxiliar();
        icc.limpiarMemoriaAuxiliar();
        icc.limpiarMemoriaAuxiliar();
        icc.limpiarMemoriaAuxiliar();
        icc.limpiarMemoriaAuxiliar();
        icc.limpiarMemoriaAuxiliar();
        assertTrue(true);
    }

    /*  jugadores 1..10
  equipos: 1..10
eq1 - jug1,jug4
eq2-jug2,jug3
eq3-jug1,jug2,jug4
eq4-jug3,jug8
eq5-jug9-jug5
eq6-jug10
eq7 - jug1
eq8-jug2,jug3,jug4,jug8

  competiciones:
  1-comp1-PartidoIndividual-Eq1-Eq2 !!!!!!!!!!!!DEFINIDA
  2-comp2-PartidoIndividual-Eq3-Eq4 !!!!!!!!!!!!! NO DEFINIDA
  3-comp3-Liga-(GolFav,DifGol,Res)-Eq1(2)-Eq2(4)-Eq3(6)-Eq4(8) !!!!!!!!!!!!! NO DEFINIDA
  4-comp4-Liga(Definida)-(GolFav,DifGol,Res)-Eq1(4)-Eq4(4) !!!!!!!!!!!!!DEFINIDA
  5-ligaTest1-Liga-(DifGol,Res,GolFav)-Eq1(2)-Eq2(3) !!!!!!!!!!!!! NO DEFINIDA
  6-copaTest2-Copa-Eq1(2)-Eq2(3)-Eq3(2)-Eq4(3) !!!!!!!!!!!!! NO DEFINIDA
  7-indTest3-PartidoIndividual-Eq3-Eq4 !!!!!!!!!!!!! NO DEFINIDA
  8-ligaTest4-Liga-(Res,GolFav,DifGol)-Eq1(2)-Eq2(3)-Eq3(3)
  9-copaTest5-Copa-Eq1(2)-Eq2(3) !!!!!!!!!!!!!DEFINIDA
  10-indTest6-PartidoIndividual-Eq2-Eq4 !!!!!!!!!!!!! NO DEFINIDA
  11-copaTest11-Copa-Eq1..Eq8 // Dividendos: idEq*2 !!!!!!!!!!!!! FINALIZADA
  12-copaTest12-Copa-Eq9-Eq10 NO DEFINIDA

  partidos (idComp,idPart,loc,vis)
  1-1-Eq1-Eq2 (DivAsig)
  2-1-Eq4-Eq3 (Definido)
  3-1-Eq1-Eq2 (Definido)
  3-2-Eq3-Eq4 (Finalizado)
  4-1-Eq1-Eq4 (Finalizado) Empate
  4-2-Eq4-Eq1 (Definido)
  6-1-Eq1-Eq2-"semi1" (Finalizado)
  6-2-Eq3-Eq4-"semi2" (Finalizado)
  6-3-"tercer"-"semi1"-"semi2" (Definido)
  9-1-Eq2-Eq1-"final" (DivAsi)
  11-1-Eq1-Eq2-"cuartos1" (Finalizado) Gano Eq1
  11-2-Eq7-Eq8-"cuartos4" (Finalizado) Gano Eq8
  11-3-Eq3-Eq4-"cuartos2" (Finalizado) Gano 4
  11-4-Eq1-Eq4-"semi1" (Finalizado) Gano 1
  11-5-Eq5-Eq6-"cuartos3" (Finalizado) Gano 5
  11-6-Eq5-Eq8-"semi2" (Finalizado) Gano 5
  11-7-Eq4-Eq8-"tercer" (Finalizado) Gano 8
  11-8-Eq1-Eq5-"final" (Finalizado) Gano 1

*/

    /**
     * Test of listarCompetencias method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testListarCompetencias() {
        System.out.println("listarCompetencias");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        List<DataIdNombre> list = icc.listarCompetencias();
        assertTrue(list.size()==12);
        for (DataIdNombre dataIdN: list) {
            switch (dataIdN.getId()) {
                case 1:
                    assertTrue(dataIdN.getNombre().equals("comp1"));
                    break;
                case 2:
                    assertTrue(dataIdN.getNombre().equals("comp2"));
                    break;
                case 3:
                    assertTrue(dataIdN.getNombre().equals("comp3"));
                    break;
                case 4:
                    assertTrue(dataIdN.getNombre().equals("comp4"));
                    break;
                case 5:
                    assertTrue(dataIdN.getNombre().equals("ligaTest1"));
                    break;
                case 6:
                    assertTrue(dataIdN.getNombre().equals("copaTest2"));
                    break;
                case 7:
                    assertTrue(dataIdN.getNombre().equals("indTest3"));
                    break;
                case 8:
                    assertTrue(dataIdN.getNombre().equals("ligaTest4"));
                    break;
                case 9:
                    assertTrue(dataIdN.getNombre().equals("copaTest5"));
                    break;
                case 10:
                    assertTrue(dataIdN.getNombre().equals("indTest6"));
                    break;
                case 11:
                    assertTrue(dataIdN.getNombre().equals("copaTest11"));
                    break;
                case 12:
                    assertTrue(dataIdN.getNombre().equals("copaTest12"));
                    break;
            }
        }

        


    }

    /**
     * Test of verInfoCompeticion method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testVerInfoCompeticion() {
        System.out.println("verInfoCompeticion");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        //public DataCompeticion verInfoCompeticion(int id)
        //    throws ExNoExisteCompeticion;

        // pruebo que no existe comopeticion
        try {
            DataCompeticion dataC = icc.verInfoCompeticion(13);
            assertTrue(false);
        }
        catch (ExNoExisteCompeticion exc){
            assertTrue(true);
        }
        catch (Exception exc){
            assertTrue(false);
        }

        // pruebo part individual definido
        try {
            DataCompeticion dataC = icc.verInfoCompeticion(2);
            assertTrue(dataC.getId()==2);
            assertTrue(dataC.getNombre().equals("comp2"));
            assertTrue(dataC.getEstaDefinida());
            assertTrue(dataC.getTipo()==TipoCompeticion.PartidoIndividual);
            
        }
        catch (ExNoExisteCompeticion exc){
            assertTrue(false);
        }
        catch (Exception exc){
            assertTrue(false);
        }

        // pruebo part individual no def
        try {
            DataCompeticion dataC = icc.verInfoCompeticion(7);
            assertTrue(dataC.getId()==7);
            assertTrue(dataC.getNombre().equals("indTest3"));
            assertTrue(!dataC.getEstaDefinida());
            assertTrue(dataC.getTipo()==TipoCompeticion.PartidoIndividual);

        }
        catch (ExNoExisteCompeticion exc){
            assertTrue(false);
        }
        catch (Exception exc){
            assertTrue(false);
        }

        // pruebo liga
        try {
            DataCompeticion dataC = icc.verInfoCompeticion(3);
            assertTrue(dataC.getId()==3);
            assertTrue(dataC.getNombre().equals("comp3"));
            assertTrue(!dataC.getEstaDefinida());
            assertTrue(dataC.getTipo()==TipoCompeticion.Liga);
            assertTrue(dataC.getDividendos().size()==4);

        }
        catch (ExNoExisteCompeticion exc){
            assertTrue(false);
        }
        catch (Exception exc){
            assertTrue(false);
        }

        // pruebo copa
        try {
            DataCompeticion dataC = icc.verInfoCompeticion(11);
            assertTrue(dataC.getId()==11);
            assertTrue(dataC.getNombre().equals("copaTest11"));
            assertTrue(dataC.getEstaDefinida());
            assertTrue(dataC.getTipo()==TipoCompeticion.Copa);
            assertTrue(dataC.getDividendos().size()==8);

        }
        catch (ExNoExisteCompeticion exc){
            assertTrue(false);
        }
        catch (Exception exc){
            assertTrue(false);
        }
    }

    /**
     * Test of infoPartidosCompeticion method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testInfoPartidosCompeticion() {
        System.out.println("infoPartidosCompeticion");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        //public List<DataPartido> infoPartidosCompeticion()
        //    throws ExDatosNoAsignados;

        icc.finalizarVerDetallesComp();
        // pruebo que no tengo datos suficientes
        try {
            //DataCompeticion dataC = icc.verInfoCompeticion(13);
            icc.infoPartidosCompeticion();
            assertTrue(false);
        }
        catch (ExDatosNoAsignados exc){
            assertTrue(true);
        }
        catch (Exception exc){
            assertTrue(false);
        }

        // pruebo partido ind
        try {
            icc.verInfoCompeticion(1);
            List<DataPartido> part = icc.infoPartidosCompeticion();
            assertTrue(part.size()==1);
            icc.verInfoCompeticion(7);
            part = icc.infoPartidosCompeticion();
            assertTrue(part.isEmpty());
            icc.verInfoCompeticion(11);
            part = icc.infoPartidosCompeticion();
            assertTrue(part.size()==8);
            icc.verInfoCompeticion(6);
            part = icc.infoPartidosCompeticion();
            assertTrue(part.size()==3);
            icc.verInfoCompeticion(3);
            part = icc.infoPartidosCompeticion();
            assertTrue(part.size()==2);
        }
        catch (ExDatosNoAsignados exc){
            assertTrue(false);
        }
        catch (Exception exc){
            assertTrue(false);
        }
    }

    /**
     * Test of listarJugadoresPorBando method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testListarJugadoresPorBando() {
        System.out.println("listarJugadoresPorBando");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        //public DataJugPartido listarJugadoresPorBando(int idPart)
        //    throws ExDatosNoAsignados, ExNoExistePartidoEnCompeticion;

        icc.finalizarVerDetallesComp();
        // pruebo que no tengo datos suficientes
        try {
            //DataCompeticion dataC = icc.verInfoCompeticion(13);
            icc.listarJugadoresPorBando(3);
            assertTrue(false);
        }
        catch (ExDatosNoAsignados exc){
            assertTrue(true);
        }
        catch (Exception exc){
            assertTrue(false);
        }

        // pruebo que no existe partido en part ind
        try {
            icc.verInfoCompeticion(1);
            DataJugPartido jugPorBando = icc.listarJugadoresPorBando(2);
            assertTrue(false);
        }
        catch (ExDatosNoAsignados exc){
            assertTrue(false);
        }
        catch (ExNoExistePartidoEnCompeticion exc){
            assertTrue(true);
        }
        catch (Exception exc){
            assertTrue(false);
        }
        // pruebo que no existe partido en copa
        try {
            icc.verInfoCompeticion(12);
            DataJugPartido jugPorBando = icc.listarJugadoresPorBando(1);
            assertTrue(false);
        }
        catch (ExDatosNoAsignados exc){
            assertTrue(false);
        }
        catch (ExNoExistePartidoEnCompeticion exc){
            assertTrue(true);
        }
        catch (Exception exc){
            assertTrue(false);
        }

        // pruebo que no existe partido en liga
        try {
            icc.verInfoCompeticion(3);
            DataJugPartido jugPorBando = icc.listarJugadoresPorBando(3);
            assertTrue(false);
        }
        catch (ExDatosNoAsignados exc){
            assertTrue(false);
        }
        catch (ExNoExistePartidoEnCompeticion exc){
            assertTrue(true);
        }
        catch (Exception exc){
            assertTrue(false);
        }


        // pruebo uno en copa y tiene que tener cada cuadro mas de un jugador
        try {
            icc.verInfoCompeticion(11);
            DataJugPartido jugPorBando = icc.listarJugadoresPorBando(3);
            assertTrue(jugPorBando.getJugadoresLocal().size()>1 && jugPorBando.getJugadoresVisita().size()>1);
        }
        catch (ExDatosNoAsignados exc){
            assertTrue(false);
        }
        catch (ExNoExistePartidoEnCompeticion exc){
            assertTrue(false);
        }
        catch (Exception exc){
            assertTrue(false);
        }


    }

    /**
     * Test of finalizarVerDetallesComp method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testFinalizarVerDetallesComp() {
        System.out.println("finalizarVerDetallesComp");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        icc.finalizarVerDetallesComp();
        icc.finalizarVerDetallesComp();
        icc.finalizarVerDetallesComp();
        icc.finalizarVerDetallesComp();

    }

    /**
     * Test of listarPartidosDivNoAsig method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testListarPartidosDivNoAsig() {
        System.out.println("listarPartidosDivNoAsig");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        List<DataInfoPartido> list = icc.listarPartidosDivNoAsig();



        assertTrue(list.size()==5);
        for (DataInfoPartido data: list) {
            switch (data.getIdComp()) {
                case 2:
                    assertTrue(data.getIdLocal()==4);
                    assertTrue(data.getIdVisita()==3);
                    break;
                case 3:
                    assertTrue(data.getIdLocal()==1);
                    assertTrue(data.getIdVisita()==2);
                    break;
                case 4:
                    assertTrue(data.getIdLocal()==4);
                    assertTrue(data.getIdVisita()==1);
                    break;
            }
        }
    }

    /**
     * Test of asignarDividendos method, of class ControladorCompeticiones.
     */
    @org.junit.Test
    public void testAsignarDividendos() {
        System.out.println("asignarDividendos");
        IControladorCompeticiones icc = new ControladorCompeticiones();
        //asignarDividendos(int idPart,int idComp, float divL, float divV, float divE)
        //throws ExNoExisteCompeticion, ExNoExistePartidoEnCompeticion,
        //     ExDividendosYaAsignados,ExDividendosInvalidos;
        
        
        // Pruebo que no existe la competicion
        try {
            icc.asignarDividendos(1,0,2,2,2);//(int idPart,int idComp, float divL, float divV, float divE);
            assertTrue(false);
        }
        catch (ExNoExisteCompeticion exc) {
            assertTrue(true);
        }
        catch (ExNoExistePartidoEnCompeticion exc) {
            assertTrue(false);
        }
        catch (ExDividendosYaAsignados exc) {
            assertTrue(false);
        }
        catch (ExDividendosInvalidos exc) {
            assertTrue(false);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Pruebo que no exite el partido en la competicion
        try {
            icc.asignarDividendos(0,1,2,2,2);//(int idPart,int idComp, float divL, float divV, float divE);
            assertTrue(false);
        }
        catch (ExNoExisteCompeticion exc) {
            assertTrue(false);
        }
        catch (ExNoExistePartidoEnCompeticion exc) {
            assertTrue(true);
        }
        catch (ExDividendosYaAsignados exc) {
            assertTrue(false);
        }
        catch (ExDividendosInvalidos exc) {
            assertTrue(false);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Pruebo que el partido ya tiene los dividendos asignados en copa pero no finalizo
        try {
            icc.asignarDividendos(1,9,2,2,2);//(int idPart,int idComp, float divL, float divV, float divE);
            assertTrue(false);
        }
        catch (ExNoExisteCompeticion exc) {
            assertTrue(false);
        }
        catch (ExNoExistePartidoEnCompeticion exc) {
            assertTrue(false);
        }
        catch (ExDividendosYaAsignados exc) {
            assertTrue(true);
        }
        catch (ExDividendosInvalidos exc) {
            assertTrue(false);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Pruebo que el partido de liga esta finalizado
        try {
            icc.asignarDividendos(1,4,2,2,2);//(int idPart,int idComp, float divL, float divV, float divE);
            assertTrue(false);
        }
        catch (ExNoExisteCompeticion exc) {
            assertTrue(false);
        }
        catch (ExNoExistePartidoEnCompeticion exc) {
            assertTrue(false);
        }
        catch (ExDividendosYaAsignados exc) {
            assertTrue(true);
        }
        catch (ExDividendosInvalidos exc) {
            assertTrue(false);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Pruebo que los dividendos son invalidos
        try {
            icc.asignarDividendos(1,2,-2,2,2);//(int idPart,int idComp, float divL, float divV, float divE);
            assertTrue(false);
        }
        catch (ExNoExisteCompeticion exc) {
            assertTrue(false);
        }
        catch (ExNoExistePartidoEnCompeticion exc) {
            assertTrue(false);
        }
        catch (ExDividendosYaAsignados exc) {
            assertTrue(false);
        }
        catch (ExDividendosInvalidos exc) {
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Pruebo que los dividendos son invalidos
        try {
            icc.asignarDividendos(1,2,2,2,1);//(int idPart,int idComp, float divL, float divV, float divE);
            assertTrue(false);
        }
        catch (ExNoExisteCompeticion exc) {
            assertTrue(false);
        }
        catch (ExNoExistePartidoEnCompeticion exc) {
            assertTrue(false);
        }
        catch (ExDividendosYaAsignados exc) {
            assertTrue(false);
        }
        catch (ExDividendosInvalidos exc) {
            assertTrue(true);
        }
        catch (Exception exc) {
            assertTrue(false);
        }


        
    }

    @org.junit.Test
    public void probarEventos(){
        //IControladorCompeticiones icc = new ControladorCompeticiones();
        //IControladorCompeticiones icj = new ControladorCompeticiones();
        try{
        //creo y finalizo un partido y le pongo los eventos
            TipoPosicion tipoPos = TipoPosicion.Delantero;
            DataFechaHora fechaHora = new DataFechaHora(3,12,1981,0,0);

            DataTypes.DataJugador dataJ = new DataJugador(1,"1","1",tipoPos,fechaHora,0,"USA",1.70,80,"");

            Fabrica f = new Fabrica();
            IControladorEquipos ice = f.getIControladorEquipos();
            IControladorCompeticiones icc = f.getIControladorCompeticiones();
            IControladorJugadores icj = f.getIControladorJugadores();


            dataJ = new DataJugador(1,"a","dasd",tipoPos,fechaHora,0,"USA",1.80,80,"");
            int id1 =icj.ingresarJugador(dataJ);

            dataJ = new DataJugador(2,"b","dasd",tipoPos,fechaHora,0,"USA",1.80,80,"");
            int id2 =icj.ingresarJugador(dataJ);

            dataJ = new DataJugador(3,"c","dd",tipoPos,fechaHora,0,"USA",2.06,120,"");
            int id3=icj.ingresarJugador(dataJ);

            int idEq1=ice.darAltaEquipo("eq1","");
            int idEq2=ice.darAltaEquipo("eq2","");

            //creo una competicion parido individual
            icc.ingresarCompeticion("Comp",TipoCompeticion.PartidoIndividual,"");
            icc.seleccionarEquipo(idEq1);
            icc.seleccionarEquipo(idEq2);

            int cc=icc.darDeAltaCompetencia();
            icc.finalizar();

            icc.ingresarDatosPartidoIndividual(cc,idEq1, idEq2,fechaHora,"Lugar");
            icc.asignarDividendos(1,cc,(float)2.1,(float) 1.2, (float)1.3);

            //los dividendos

            List<DataInfoPartido> lista=icc.listarPartidosConDivAsignado(cc);
            icc.seleccionarPartido(lista.get(0));
            icc.ingresarResultado(2,0,1,0);//2 a 1 gano el local
            icc.ingresarJugador(id1,idEq1); //en el equipo 1 va uno solo

            icc.ingresarJugador(id2,idEq2);
            icc.ingresarJugador(id3,idEq2);

            //ingreso de eventos
            DataIdNombre j1 = new DataIdNombre(id1,"a","");
            DataIdNombre j2 = new DataIdNombre(id2,"b","");
            DataIdNombre j3 = new DataIdNombre(id3,"c","");
            DataEvento de;

            de= new DataEvento(1,TipoPeriodo.PrimerTiempo,j1,null,TipoEvento.Gol,false);//los voy a identificar por el minuto.. no queda otra, primer gol 1 a 0
            icc.ingresarEventos(de);

            System.out.print("e1");
            de= new DataEvento(2,TipoPeriodo.SegundoTiempo,j1,null,TipoEvento.Gol,false);//segundo gol, 2 a 0
            icc.ingresarEventos(de);

            System.out.print("e2");

            de= new DataEvento(3,TipoPeriodo.SegundoTiempo,j1,null,TipoEvento.Tarjeta,false);//tarjeta amarilla
            icc.ingresarEventos(de);

            System.out.print("e3");

            de= new DataEvento(4,TipoPeriodo.SegundoTiempo,j2,null,TipoEvento.Gol,false);//tercer gol, 2 a 1 gl visitante
            icc.ingresarEventos(de);

            System.out.print("e4");

            de= new DataEvento(5,TipoPeriodo.SegundoTiempo,j2,null,TipoEvento.Tarjeta,true);//tarjeta roja para j2
            icc.ingresarEventos(de);

            

            
            de= new DataEvento(6,TipoPeriodo.SegundoTiempo,j2,j3,TipoEvento.Sustitucion,false);
            icc.ingresarEventos(de);

            

            icc.listarIngresos();
            icc.confirmarIngreso(true);

            //ya esta ingresado el partido con sus eventos..
            //
            //vemos los detalles de la competicion

            icc.verInfoCompeticion(cc);

            //System.out.print(icc.infoPartidosCompeticion().size());
            
            DataPartido data = icc.infoPartidosCompeticion().get(0);


            System.out.print("verInfo");
            List<DataEvento> list = data.getEventos();

            

            for (DataEvento dte:list){
                if (dte.getMinuto()==1){
                    if ( (dte.getPeriodo()==TipoPeriodo.PrimerTiempo) & (dte.getJugador1().getId()==j1.getId()) & (dte.getTipoevento() == TipoEvento.Gol)){
                        assert(true);
                    }else
                        assert(false);
                    
                }else if (dte.getMinuto()==2){
                    if ( (dte.getPeriodo()==TipoPeriodo.SegundoTiempo) & (dte.getJugador1().getId()==j1.getId()) & (dte.getTipoevento() == TipoEvento.Gol)){
                        assert(true);
                    }else
                        assert(false);
                }else if (dte.getMinuto()==3){
                    if ( (dte.getPeriodo()==TipoPeriodo.SegundoTiempo) & (dte.getJugador1().getId()==j1.getId()) & (dte.getTipoevento() == TipoEvento.Tarjeta) & (dte.getAmarilla()==false)){
                        assert(true);
                    }else
                        assert(false);
                    System.out.print("p2");
                }else if (dte.getMinuto()==4){
                    if ( (dte.getPeriodo()==TipoPeriodo.SegundoTiempo) & (dte.getJugador1().getId()==j2.getId()) & (dte.getTipoevento() == TipoEvento.Gol)){
                        assert(true);
                    }else
                        assert(false);
                }else if (dte.getMinuto()==5){
                    if ( (dte.getPeriodo()==TipoPeriodo.SegundoTiempo) & (dte.getJugador1().getId()==j2.getId()) & (dte.getTipoevento() == TipoEvento.Tarjeta) & (dte.getAmarilla()==true)){
                        assert(true);
                    }else
                        assert(false);
                    System.out.print("p3");
                }else if (dte.getMinuto()==6){
                    if ( (dte.getPeriodo()==TipoPeriodo.SegundoTiempo) & (dte.getJugador1().getId()==j2.getId()) & (dte.getTipoevento() == TipoEvento.Sustitucion) & (dte.getJugador2().getId()==j3.getId())){
                        assert(true);
                    }else
                        assert(false);
                }

            }


        }
        catch(Exception e){
            System.out.println(e.getMessage());
            assertTrue(false);
        }

       try {
            Fabrica f = new Fabrica();
            IControladorDatos icd = f.getIControladorDatos();
            icd.guardar("testComp.xls");
            icd.cargar("testComp.xls");
            icd.guardar("testComp2.xls");
        }
        catch (Exception exc){
            assertTrue(false);
        }
    }

}

