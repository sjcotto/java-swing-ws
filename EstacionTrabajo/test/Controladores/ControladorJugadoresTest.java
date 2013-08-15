
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package Controladores;

//~--- non-JDK imports --------------------------------------------------------

import DataTypes.DataFechaHora;
import DataTypes.DataIdNombre;
import DataTypes.DataInfoPartido;
import DataTypes.DataJugador;
import DataTypes.DataPartido;
import DataTypes.DataResumen;
import DataTypes.TipoCompeticion;
import DataTypes.TipoPosicion;

import Excepciones.ExNoExisteJugador;
import Excepciones.ExNoExisteJugadorEnMemoria;

import Interface.*;

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
 * @author tprog084
 */
public class ControladorJugadoresTest {
    public ControladorJugadoresTest() {}

    @BeforeClass
    public static void setUpClass() throws Exception {
        IControladorCompeticiones icc = new ControladorCompeticiones();
        IControladorEquipos       ice = new ControladorEquipos();
        IControladorJugadores     icj = new ControladorJugadores();

        // Ingreso Equipos
        int idEq1 = ice.darAltaEquipo("Eq1", "");
        int idEq2 = ice.darAltaEquipo("Eq2", "");
        int idEq3 = ice.darAltaEquipo("Eq3", "");
        int idEq4 = ice.darAltaEquipo("Eq4", "");

        // Ingreso Jugadores
        DataFechaHora df    = new DataFechaHora(21, 11, 1986, 0, 0);
        DataFechaHora df1   = new DataFechaHora(10, 12, 1987, 0, 0);
        DataFechaHora df2   = new DataFechaHora(21, 04, 1991, 0, 0);
        DataFechaHora df3   = new DataFechaHora(14, 07, 1975, 0, 0);
        TipoPosicion  tp    = TipoPosicion.Golero;
        TipoPosicion  tp1   = TipoPosicion.Delantero;
        TipoPosicion  tp2   = TipoPosicion.Volante;
        TipoPosicion  tp3   = TipoPosicion.Defensa;
        DataJugador   data  = new DataJugador(1, "j1", "a c", tp, df, 24, "d", 1.9, 54.5, "");
        DataJugador   data1 = new DataJugador(2, "j2", "b c", tp1, df1, 23, "d", 1.4, 60.2, "");
        DataJugador   data2 = new DataJugador(3, "j3", "c c", tp2, df2, 20, "d", 1.6, 87.2, "");
        DataJugador   data3 = new DataJugador(4, "j4", "d c", tp3, df3, 36, "d", 1.3, 71.2, "");

        icj.ingresarJugador(data);
        icj.ingresarJugador(data1);
        icj.ingresarJugador(data2);
        icj.ingresarJugador(data3);

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
            boolean       ok1    = icc.ingresarDatosPartidoIndividual(1, 1, 2, fecha1, "Ubilla");    // (idComp,idEqLocal,idEqVisi,fecha,lugar)

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
    }

    @AfterClass
    public static void tearDownClass() throws Exception {}

    @Before
    public void setUp() {}

    @After
    public void tearDown() {}

    /**
     * Test of ingresarJugador method, of class ControladorJugadores.
     */
    @Test
    public void testIngresarJugador() {
        System.out.println("ingresarJugador");

        DataFechaHora        df       = new DataFechaHora(29, 12, 1987, 0, 0);
        DataFechaHora        df1      = new DataFechaHora(11, 12, 1989, 0, 0);
        DataFechaHora        df2      = new DataFechaHora(21, 05, 1999, 0, 0);
        DataFechaHora        df3      = new DataFechaHora(14, 07, 1974, 0, 0);
        TipoPosicion         tp       = TipoPosicion.Golero;
        TipoPosicion         tp1      = TipoPosicion.Delantero;
        TipoPosicion         tp2      = TipoPosicion.Volante;
        TipoPosicion         tp3      = TipoPosicion.Defensa;
        DataJugador          data     = new DataJugador(5, "j5", "a c", tp, df, 24, "d", 1.9, 54.5, "");
        DataJugador          data1    = new DataJugador(6, "j6", "b c", tp1, df1, 23, "d", 1.4, 60.2, "");
        DataJugador          data2    = new DataJugador(7, "j7", "c c", tp2, df2, 20, "d", 1.6, 87.2, "");
        DataJugador          data3    = new DataJugador(8, "j8", "d c", tp3, df3, 36, "d", 1.3, 71.2, "");
        ControladorJugadores instance = new ControladorJugadores();

        // Testeamos los id`s
        int result = instance.ingresarJugador(data);

        assertEquals(result, 5);
        result = instance.ingresarJugador(data1);
        assertEquals(result, 6);
        result = instance.ingresarJugador(data2);
        assertEquals(result, 7);
        result = instance.ingresarJugador(data3);
        assertEquals(result, 8);

        // Testeamos por nombres
        String nom;

        try {
            nom = instance.seleccionarJugador(5).getNombre();
            assertEquals(nom, "j5");
            nom = instance.seleccionarJugador(6).getNombre();
            assertEquals(nom, "j6");
            nom = instance.seleccionarJugador(7).getNombre();
            assertEquals(nom, "j7");
            nom = instance.seleccionarJugador(8).getNombre();
            assertEquals(nom, "j8");
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    /**
     * Test of listaJugadores method, of class ControladorJugadores.
     */
    @Test
    public void testListaJugadores() {
        System.out.println("listaJugadores");

        ControladorJugadores instance  = new ControladorJugadores();
        String               nom       = "j";
        List<DataIdNombre>   result    = instance.listaJugadores();
        int[]                esperado  = new int[101];
        int[]                resultado = new int[101];

        for (int i = 0; i < 9; i++) {
            esperado[i]  = 1;
            resultado[i] = 0;
        }

        esperado[0] = 0;

        for (DataIdNombre din : result) {
            resultado[din.getId()] = 1;
            assertEquals(din.getNombre(), "j" + Integer.toString(din.getId()));
        }

        assertArrayEquals(esperado, resultado);
    }

    /**
     * Test of eliminarJugador method, of class ControladorJugadores.
     */
    @Test
    public void testEliminarJugador() {
        System.out.println("eliminarJugador");

        ControladorJugadores instance = new ControladorJugadores();
        boolean              ok       = false;

        try {
            ok = instance.eliminarJugador(12);
        } catch (ExNoExisteJugador e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }

        try {
            instance.eliminarJugador(1);
        } catch (Exception e) {
            assertTrue(false);
        }

        assertFalse(ok);
    }

    @Test
    public void testConfirmarEliminacion() {
        System.out.println("confirmarEliminacion");

        ControladorJugadores instance = new ControladorJugadores();

        try {
            instance.confirmarEliminacion();
            fail("No Existe Jugador en Memoria");
        } catch (ExNoExisteJugadorEnMemoria e) {
            assertTrue(true);
        } catch (Exception e) {}

        boolean ok = false;

        try {
            ok = instance.eliminarJugador(1);

            if (ok) {
                instance.confirmarEliminacion();
            }

            assertFalse(ok);
        } catch (Exception e) {}

        try {
            ok = instance.eliminarJugador(7);

            if (ok) {
                instance.confirmarEliminacion();
            }
        } catch (Exception e) {}

        List<DataIdNombre> l            = instance.listaJugadores();
        boolean            no_encuentro = true;

        for (int i = 0; (i < l.size()) && no_encuentro; i++) {
            DataIdNombre idn = l.get(i);

            if (idn.getId() == 7) {
                no_encuentro = false;
            }
        }

        assertTrue(no_encuentro);
    }

    @Test
    public void testSeleccionarJugador() {
        IControladorJugadores icj = new ControladorJugadores();
        DataJugador           dj  = null;

        try {
            dj = icj.seleccionarJugador(7);
            fail("No Existe Jugador");
        } catch (ExNoExisteJugador e) {
            assertTrue(true);
        } catch (Exception e) {}

        try {
            dj = icj.seleccionarJugador(2);
        } catch (Exception e) {
            assertTrue(false);
        }

        assertEquals("j2", dj.getNombre());
        assertEquals(2, dj.getId());
        assertEquals(TipoPosicion.Delantero, dj.getPosicion());

        DataFechaHora df11        = new DataFechaHora(29, 11, 1980, 0, 0);
        TipoPosicion  tp2         = TipoPosicion.Volante;
        DataJugador   datosNuevos = new DataJugador(87, "j9", "a c", tp2, df11, 24, "d", 1.9, 54.5, "");

        try {
            icj.modificarDatos(datosNuevos);
        } catch (Exception e) {}
    }

    @Test
    public void testModificarDatos() {
        DataFechaHora df11 = new DataFechaHora(29, 11, 1980, 0, 0);
        DataFechaHora df1  = new DataFechaHora(11, 11, 1998, 0, 0);
        DataFechaHora df2  = new DataFechaHora(21, 7, 1994, 0, 0);
        DataFechaHora df3  = new DataFechaHora(17, 9, 1980, 0, 0);

        System.out.println("modificarDatos");

        TipoPosicion         tp2         = TipoPosicion.Volante;
        DataJugador          dj          = null;
        DataJugador          datosNuevos = new DataJugador(87, "j44", "a c", tp2, df11, 24, "d", 1.9, 54.5, "");
        ControladorJugadores instance    = new ControladorJugadores();

        try {
            instance.modificarDatos(datosNuevos);
            fail("No Existe Jugador en Memoria Temporal");
        } catch (ExNoExisteJugadorEnMemoria e) {
            assertTrue(true);
        } catch (Exception e) {}

        try {
            dj = instance.seleccionarJugador(4);
            instance.modificarDatos(datosNuevos);
        } catch (Exception e) {
            assertTrue(false);
        }

        try {
            assertEquals(instance.seleccionarJugador(4).getNombre(), "j44");
            assertEquals(instance.seleccionarJugador(4).getPosicion(), TipoPosicion.Volante);
            assertEquals(instance.seleccionarJugador(4).getFechaDeNacimiento(), df11);
        } catch (Exception e) {}
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
