
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package Controladores;

//~--- non-JDK imports --------------------------------------------------------

import DataTypes.DataEquipo;
import DataTypes.DataFechaHora;
import DataTypes.DataIdNombre;
import DataTypes.DataInfoPartido;
import DataTypes.DataJugador;
import DataTypes.DataPartido;
import DataTypes.DataResumen;
import DataTypes.TipoCompeticion;
import DataTypes.TipoPosicion;

import Excepciones.ExNoExisteEquipo;

import Interface.*;

import Objetos.Equipo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

//~--- JDK imports ------------------------------------------------------------

import java.util.List;

/**
 *
 * @author pablito
 */
public class ControladorEquiposTest {
    public ControladorEquiposTest() {}

    @BeforeClass
    public static void setUpClass() throws Exception {}

    @AfterClass
    public static void tearDownClass() throws Exception {}

    @Before
    public void setUp() {}

    @After
    public void tearDown() {}


    /**
     * Test of darAltaEquipo method, of class ControladorEquipos.
     */
    @Test
    public void testDarAltaEquipo() {
        System.out.println("darAltaEquipo");

        String             nombre = "Equipo";
        String             image  = "Imagen";
        int                result;
        int                expResult;
        ControladorEquipos instance = new ControladorEquipos();

        for (int i = 1; i < 100; i++) {
            nombre    = nombre + i;
            image     = image + i;
            expResult = i;
            result    = instance.darAltaEquipo(nombre, image);
            assertEquals(expResult, result);
            assertEquals(nombre, instance.buscarEquipo(i).getNombre());
            nombre = "Equipo";
            image  = "Imagen";
        }

        nombre = nombre + 50;
        image  = image + 50;
        result = instance.darAltaEquipo(nombre, image);
        assertEquals(100, result);
        assertEquals(nombre, instance.buscarEquipo(100).getNombre());
    }

    /**
     * Test of listarEquipos method, of class ControladorEquipos.
     */
    @Test
    public void testListarEquipos() {
        System.out.println("listarEquipos");

        ControladorEquipos ce        = new ControladorEquipos();
        List<DataEquipo>   eqs       = ce.listarEquipos();
        String             nom       = "Equipo";
        int[]              esperado  = new int[101];
        int[]              resultado = new int[101];

        for (int i = 0; i < 101; i++) {
            esperado[i]  = 1;
            resultado[i] = 0;
        }

        esperado[0] = 0;

        for (DataEquipo din : eqs) {
            resultado[din.getId()] = 1;
        }

        assertArrayEquals(esperado, resultado);
    }

    /**
     *
     * Test of seleccionarEquipo method, of class ControladorEquipos.
     */
    @Test
    public void testSeleccionarEquipo() {
        System.out.println("seleccionarEquipo");
        System.out.println("-------------------");

        IControladorCompeticiones icc = new ControladorCompeticiones();
        IControladorEquipos       ice = new ControladorEquipos();

        // Ingreso Jugadores
        DataFechaHora        df       = new DataFechaHora(21, 11, 1986, 0, 0);
        DataFechaHora        df1      = new DataFechaHora(10, 12, 1987, 0, 0);
        DataFechaHora        df2      = new DataFechaHora(21, 04, 1991, 0, 0);
        DataFechaHora        df3      = new DataFechaHora(14, 07, 1975, 0, 0);
        TipoPosicion         tp       = TipoPosicion.Golero;
        TipoPosicion         tp1      = TipoPosicion.Delantero;
        TipoPosicion         tp2      = TipoPosicion.Volante;
        TipoPosicion         tp3      = TipoPosicion.Defensa;
        DataJugador          data     = new DataJugador(1, "j1", "a c", tp, df, 24, "d", 1.9, 54.5, "");
        DataJugador          data1    = new DataJugador(2, "j2", "b c", tp1, df1, 23, "d", 1.4, 60.2, "");
        DataJugador          data2    = new DataJugador(3, "j3", "c c", tp2, df2, 20, "d", 1.6, 87.2, "");
        DataJugador          data3    = new DataJugador(4, "j4", "d c", tp3, df3, 36, "d", 1.3, 71.2, "");
        ControladorJugadores instance = new ControladorJugadores();
        int                  result   = instance.ingresarJugador(data);

        result = instance.ingresarJugador(data1);
        result = instance.ingresarJugador(data2);
        result = instance.ingresarJugador(data3);

        try {

            // Ingreso Partido Individual
            System.out.println("Agrego PartInd con Eq-id=1 y Eq-id=2");
            icc.ingresarCompeticion("comp1", TipoCompeticion.PartidoIndividual, "");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);

            int idComp1 = icc.darDeAltaCompetencia();

            icc.finalizar();

            // Defino Partido Individual
            DataFechaHora fecha1 = new DataFechaHora(1, 8, 2011, 4, 11);
            boolean ok1 = icc.ingresarDatosPartidoIndividual(1, 1, 2, fecha1, "Ubilla");    // (idComp,idEqLocal,idEqVisi,fecha,lugar)

            if (ok1) {
                System.out.println("Comp bien definida");
            } else {
                System.out.println("Mal operacion");
            }

            icc.finalizar();

            // Asigno Dividendos A Partido Individual
            System.out.println("Asigno dividendos al partido idComp=1 idPart=1");
            icc.asignarDividendos(1, 1, 2, 3, 4);    // (idPar,idComp,divL,divV,divE)

            // Finalizamos el Partido
            System.out.println("Finalizare el partido idC=1 idPart=1");

            DataInfoPartido dataIP = new DataInfoPartido(1, 1, "comp1", TipoCompeticion.PartidoIndividual,    // idPart, idC, nomC, tipoC
                "Equipo1", "Equipo2", 1, 2, "Ubilla", fecha1, "", "", "");    // nomEqLoc,nomEqVis,idEqLoc,idEqVis,lugar,fechaHora

            System.out.println("Selecciono el partido idPart=1");
            icc.listarPartidosConDivAsignado(1);

            DataPartido dataP = icc.seleccionarPartido(dataIP);

            System.out.println(dataP.getDividendos().toString() + "-" + dataP.getEstaFinalizado());
            System.out.println("Seleccion OK");
            System.out.println("Resultado 2-1");
            icc.ingresarResultado(2, 0, 1, 0);
            System.out.println("Resultado OK");
            System.out.println("Ingreso jugadores");
            icc.ingresarJugador(1, 1);
            icc.ingresarJugador(4, 1);
            icc.ingresarJugador(2, 2);
            icc.ingresarJugador(3, 2);
            System.out.println("Ingreso jugadores OK");
            System.out.println("Confirma");

            DataResumen dr = icc.listarIngresos();

            icc.confirmarIngreso(true);
            System.out.println("Confirma OK");
        } catch (Exception e) {
            assertTrue(false);
        }

        // Testeamos seleccionarEquipo
        List<DataIdNombre> jugs;

        try {
            jugs = ice.seleccionarEquipo(1);

            for (DataIdNombre id : jugs) {
                assertEquals(id.getNombre(), "j" + Integer.toString(id.getId()));
            }
        } catch (ExNoExisteEquipo e) {
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(false);
        }

        try {
            jugs = ice.seleccionarEquipo(180);
            fail("Excecpcion No ExisteEquipo");
        } catch (ExNoExisteEquipo e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetDataEquipo() {
        System.out.println("getDataEquipo");

        ControladorEquipos instance = new ControladorEquipos();
        String             nombre   = "Equipo";

        for (int id = 1; id < 50; id++) {
            DataEquipo result = instance.getDataEquipo(id);

            assertEquals(nombre + id, result.getNombre());
            assertEquals(id, result.getId());
        }
    }

    @Test
    public void testBuscarEquipo() {
        System.out.println("buscarEquipo");

        String             nombre   = "Equipo";
        ControladorEquipos instance = new ControladorEquipos();

        for (int id = 50; id < 100; id++) {
            Equipo result = instance.buscarEquipo(id);

            assertEquals(nombre + id, result.getNombre());
            assertEquals(id, result.getId());
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
