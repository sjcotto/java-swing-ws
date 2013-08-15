/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;
import Interface.*;
import Objetos.*;
import DataTypes.*;
import java.util.*;
import Excepciones.*;
import static org.junit.Assert.*;



public class ControladorUsuarioTest {
    
    public ControladorUsuarioTest(){
        
    }
    
    @org.junit.BeforeClass
    public static void setUpClass() throws Exception{
        Fabrica f = new Fabrica();
        int i;
        DataFechaHora aux = new DataFechaHora(2,2,2000,2,2);
        IControladorCompeticiones icc = f.getIControladorCompeticiones();
        
        IControladorEquipos ice = f.getIControladorEquipos();
        IControladorJugadores icj = f.getIControladorJugadores();
        IControladorUsuarios icu = f.getIControladorUsuarios();
        icu.registrarUsuario("nomA", "nickA", "mailA", "dirA", "passA", TipoSexo.Mujer, "paisA", "ciaA", aux);
        icu.registrarUsuario("nomB", "nickB", "mailB", "dirB", "passB", TipoSexo.Hombre, "paisB", "ciaB", aux);

        try{
            DataFechaHora fechaHora = new DataFechaHora(1,8,2001,14,20);
            DataJugador dataJ;
            TipoPosicion tipo = null;
            for (i=1;i<=15;i++) {
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
        }
        catch (Exception e){}

        for (i=1;i<=9;i++)
                ice.darAltaEquipo("eq"+i,"");
        
        int a,b,c;
        
        
        for (i = 1; i <= 21; i++){
            if (i <= 10){
                icc.ingresarCompeticion("comp1",TipoCompeticion.PartidoIndividual,"");
                a = (i*i%9)+1;
                b = (i+4)%9+1;
                icc.seleccionarEquipo(a);
                icc.seleccionarEquipo(b);
                icc.darDeAltaCompetencia();
                icc.finalizar();
                
                if (i <= 8){
                    icc.ingresarDatosPartidoIndividual(i, a, b, aux, "lugarp1");
                    if (i <= 5)
                        icc.asignarDividendos(1, i, ((4*i)%20)+2, (i*i%20)+2, (i+3)%20+2);
                }
            }
            else{
                if (i <= 18){
                    icc.ingresarCompeticion("comp"+(i-5), TipoCompeticion.Liga, "");

                    icc.seleccionarEquipo(1);
                    icc.seleccionarEquipo(2);
                    icc.seleccionarEquipo(3);
                    TipoCriterio[] dc2 = {TipoCriterio.GolesFavor,TipoCriterio.DiferenciaGoles,TipoCriterio.Resultado};
                    icc.ingresarOrdenLiga(dc2);
                    icc.ingresarDividendoEquipo(1,8);
                    icc.ingresarDividendoEquipo(2,40-2*i);
                    icc.ingresarDividendoEquipo(3,3*i);
                    icc.setMontoPenca(200);
                    icc.darDeAltaCompetencia();
                    icc.finalizar();

                    c = 0;
                    //System.out.println("i es "+i);
                    
                    if (i <= 15){
                        for (int j = 0; j < 3; j++){
                            for (int k = 0; k < 3; k++)
                                if (j!=k){
                                    icc.ingresarIdLiga(i);
                                    icc.seleccionarEncuentroADefinir(j+1, k+1, new DataFechaHora(12,12,2010,15,20), "lugarp1");
                                    if (j <= 1){
                                        icc.asignarDividendos(++c, i, 2*i*(j+2)-7,((3*i*(k+2))%13)+2, (4*i*i*(j+3))%25+2);
                                        //System.out.println("c es "+c);
                                        //System.out.println("div loc "+(2*i*(j+2)-7)+" div visit "+(((3*i*(k+2))%13)+2)+" div empate: "+((4*i*i*(j+3))%25+2));
                                    }
                                }
                        }
                    }
                }
                else{
                    icc.ingresarCompeticion("comp"+(i-5), TipoCompeticion.Copa, "");

                    icc.seleccionarEquipo(1);
                    icc.seleccionarEquipo(2);
                    icc.seleccionarEquipo(3);
                    icc.seleccionarEquipo(4);
                    if (i==20) {
                        icc.agregarIJC(5,5);
                        icc.agregarIJC(6,6);
                        icc.agregarIJC(7,7);
                        icc.agregarIJC(8,8);
                    }
                    icc.ingresarDividendoEquipo(1,11);
                    icc.ingresarDividendoEquipo(2,4);
                    icc.ingresarDividendoEquipo(3,3*i);
                    icc.ingresarDividendoEquipo(4,(7+3*i)%13+2);
                    icc.setMontoPenca(200);
                    icc.darDeAltaCompetencia();
                    icc.finalizar();
                    
                    c = 0;
                    if (i % 2 != 0){
                        //System.out.println("i es"+i);
                        icc.ingresarDatosLlaveDeCopa(i,"cuartos1");
                        icc.ingresarDatosPartida(2,1);
                        icc.ingresarTipoLlave(true,false);
                        icc.confirmarAltaLlaveCopa(new DataFechaHora(1,1,1,1,1),"lugCopa");
                        
                        icc.asignarDividendos(++c, i, (7*i)%13+2, (21-i)%25+2, 21);
                        //System.out.println("div loc "+((7*i)%13+2)+" div visit "+((21-i)%25+2)+" div empate: "+(21));
                        
                        icc.ingresarDatosLlaveDeCopa(i,"cuartos2");
                        icc.ingresarDatosPartida(3,4);
                        icc.ingresarTipoLlave(true,false);
                        icc.confirmarAltaLlaveCopa(new DataFechaHora(1,1,1,1,1),"lugCopa");
                       
                     
                        icc.ingresarDatosLlaveDeCopa(i,"final");
                        icc.ingresarLlavesPredecesoras("cuartos1", "cuartos2");
                        icc.ingresarTipoLlave(false,true);
                        icc.confirmarAltaLlaveCopa(new DataFechaHora(1,1,1,1,1),"lugCopa");
                        
                    }
                    else{
                        icc.ingresarDatosLlaveDeCopa(i,"cuartos1");
                        icc.ingresarDatosPartida(2,1);
                        icc.ingresarTipoLlave(true,false);
                        icc.confirmarAltaLlaveCopa(new DataFechaHora(1,1,1,1,1),"lugCopa");
                        
                        icc.ingresarDatosLlaveDeCopa(i,"cuartos2");
                        icc.ingresarDatosPartida(3,4);
                        icc.ingresarTipoLlave(true,false);
                        icc.confirmarAltaLlaveCopa(new DataFechaHora(1,1,1,1,1),"lugCopa");
                    }
                }
            }
        }
  
    }
    
    
    private void compararResultados(List<DataApuesta> list_da, DataApuesta[] resultados){
         int k;
         int h = 0;
         for (DataApuesta dda : list_da){           
             k = 0;
             boolean enc = false;
             while (!enc && k < list_da.size()){

                 if (dda.getBeneficio()==resultados[k].getBeneficio() && dda.getMonto()==resultados[k].getMonto()
                     && dda.getDividendo()==resultados[k].getDividendo()
                     && dda.getEstadoApuesta().compareTo(resultados[k].getEstadoApuesta())==0)
                     enc = true;
                 else
                     k++;
             }
             h++;
             
             
             //System.out.print("nro:"+h+"     Tipo de apuesta: ");
             //if (TipoApuesta.Campeonato.compareTo(dda.getTipoApuesta())==0)
                 //System.out.println("equipo");
             //else
                 //System.out.println("partido");
             //System.out.println("Dividendo = "+dda.getDividendo()+"  Beneficio = "+dda.getBeneficioEsp()+
              //                  "   Monto = "+dda.getMonto()+"\n");
             
             //System.out.println("r ees  "+r);
             assertFalse(k == list_da.size());
         }
    } 
    
    @org.junit.Test
    public void testRegistrarUsuario(){
        DataFechaHora aux = new DataFechaHora(2,2,2000,2,2);
        IControladorUsuarios icu = (new Fabrica()).getIControladorUsuarios();

        boolean registroExitoso = icu.registrarUsuario("nomA", "nickA", "mailD", "dirH", "passK", TipoSexo.Mujer, "paisC", "ciC",aux);

        if (!registroExitoso)
            assertTrue(true);
        else
            assertTrue(false);


        registroExitoso = icu.registrarUsuario("nomJ", "nickL", "mailB", "dirK", "passL", TipoSexo.Mujer, "paisC", "ciC",aux);
        if (!registroExitoso)
            assertTrue(true);
        else
            assertTrue(false);
        

        registroExitoso = icu.registrarUsuario("nomR", "nickB", "mailO", "dirK", "passT", TipoSexo.Mujer, "paisU", "ciC",aux);
        if (!registroExitoso)
            assertTrue(true);
        else
            assertTrue(false);

        registroExitoso = icu.registrarUsuario("nomC", "nickC", "mailC", "dirB", "passB", TipoSexo.Mujer, "paisB", "ciB",aux);
        if (!registroExitoso)
            assertTrue(false);

        registroExitoso = icu.registrarUsuario("nomC", "nickd", "mailD", "dirC", "passC", TipoSexo.Mujer, "paisC", "ciC",aux);
        if (!registroExitoso)
            assertTrue(false);

    }
    
    
    
    @org.junit.Test
    public void testIniciarSesion(){
        //DataFechaHora aux = new DataFechaHora(2,2,2000,2,2);
        IControladorUsuarios icu = (new Fabrica()).getIControladorUsuarios();

        boolean inicioSes = icu.iniciarSesion("nick23", "pass66");
        if (inicioSes)
            assertTrue(false);

        inicioSes = icu.iniciarSesion("nickA", "pass6");
        if (inicioSes)
            assertTrue(false);

        inicioSes = icu.iniciarSesion("nickB", "pass2");
        if (inicioSes)
            assertTrue(false);

        inicioSes = icu.iniciarSesion("nick2112", "pass2");
        if (inicioSes)
            assertTrue(false);

        inicioSes = icu.iniciarSesion("nickA", "passA");
        if (!inicioSes)
            assertTrue(false);
        
    }
    
    
    @org.junit.Test
    public void testCerrarSesion(){
        IControladorUsuarios icu = (new Fabrica()).getIControladorUsuarios();
        try{
            icu.iniciarSesion("nickA", "passA");
            icu.cerrarSesion();
        }
        catch(Exception e){fail();}
        
        try{
            icu.iniciarSesion("nickA", "passA");
            icu.cerrarSesion();
            icu.cerrarSesion();
            fail();
        }
        catch(Exception e){assertTrue(true);}
        
        try{
            icu.cerrarSesion();
            icu.iniciarSesion("nickA", "passA");
            icu.cerrarSesion();
            fail();
        }
        catch(Exception e){assertTrue(true);}
    }
    
    
    @org.junit.Test
    public void testVerPerfilUsuarioLogueado(){
        IControladorUsuarios icu = (new Fabrica()).getIControladorUsuarios();
        DataTypes.DataUsuario dau = null;
        try{
            dau = icu.verPerfilUsuarioLogueado();
            fail();
        }
        catch(Exception e){assertTrue(true);}
        
        try{
            icu.iniciarSesion("nickA", "passA");
            icu.ingresarSaldoUsuario(100);
            dau = icu.verPerfilUsuarioLogueado();    
        }
        catch(Exception e){ fail();}
        
        assertEquals(dau.getCI(),"ciaA");
        assertEquals(dau.getDir(),"dirA");
        assertEquals(dau.getEmail(),"mailA");
        assertEquals(dau.getNick(),"nickA");
        assertEquals((long)dau.getSaldo(),(long)100);
        assertEquals(dau.getSexo(),TipoSexo.Mujer);
        assertEquals(dau.getPais(),"paisA");
        

        try{
            icu.cerrarSesion();
            icu.iniciarSesion("nickB", "passB");
            dau = icu.verPerfilUsuarioLogueado();   
        }
        catch(Exception e){             fail();}
        
        assertEquals(dau.getCI(),"ciaB");
        assertEquals(dau.getDir(),"dirB");
        assertEquals(dau.getEmail(),"mailB");
        assertEquals(dau.getNick(),"nickB");
        assertEquals((long)dau.getSaldo(),(long)0);
        assertEquals(dau.getSexo(),TipoSexo.Hombre);
        assertEquals(dau.getPais(),"paisB");
        
    }
    
    
    @org.junit.Test
    public void testIngresarSaldoMonedero(){
        IControladorUsuarios icu = (new Fabrica()).getIControladorUsuarios();
        try{
            icu.ingresarSaldoUsuario(-4);
            fail();
        }
        catch(Exception e){assertTrue(true);}
        
        try{
            icu.iniciarSesion("nickA", "passA");
            icu.ingresarSaldoUsuario(-4);
            fail();
        }
        catch(ExMontoInvalido e){assertTrue(true);}
        catch(Exception e){assertTrue(false);}
        
        
        try{
            icu.iniciarSesion("nickA", "passA");
            icu.ingresarSaldoUsuario(0);
            fail();
        }
        catch(ExMontoInvalido e){assertTrue(true);}
        catch(Exception e){assertTrue(false);}
        
        try{
            icu.iniciarSesion("nickA", "passA");
            icu.ingresarSaldoUsuario(10);
            //fail();
        }
        catch(Exception e){fail();}
        
        
    }
    
    
    
    @org.junit.Test
    public void testSeleccionarCompeticion (){
        IControladorUsuarios icu = (new Fabrica()).getIControladorUsuarios();
        DataFechaHora aux = new DataFechaHora(2,2,2000,2,2);
        
        
        try{
            icu.registrarUsuario("nom1", "nick1", "mail1", "dir1", "pass1", TipoSexo.Mujer, "pais1", "ci1", aux);
            icu.iniciarSesion("nick1", "pass1");
            icu.ingresarSaldoUsuario(500);
        }
        catch(Exception e){fail();}

        
        try{
            icu.seleccionarCompeticion(9);
            fail();
        }
        catch(ExNoExisteCompeticion e){
            assertTrue(false);
        }
        catch(ExCompeticionFinalizada e){
            assertTrue(false);
        }
        catch(ExDivsNoAsignados e){
            assertTrue(true);
        }
        

        try{
            icu.seleccionarCompeticion(24);
            fail();
        }
        catch(ExNoExisteCompeticion e){
            assertTrue(true);
        }
        catch(ExCompeticionFinalizada e){
            assertTrue(false);
        }
        catch(ExDivsNoAsignados e){
            assertTrue(false);
        }

        try{
            icu.seleccionarCompeticion(10);
            fail();
        }
        catch(ExNoExisteCompeticion e){
            assertTrue(false);
        }
        catch(ExCompeticionFinalizada e){
            assertTrue(false);
        }
        catch(ExDivsNoAsignados e){
            assertTrue(true);
        }


        try{
            icu.seleccionarCompeticion(3);
        }
        catch(ExNoExisteCompeticion e){
            assertTrue(false);
        }
        catch(ExCompeticionFinalizada e){
            assertTrue(false);
        }
        catch(ExDivsNoAsignados e){
            assertTrue(false);
        }
        
        try{
            icu.seleccionarCompeticion(13);
        }
        catch(ExNoExisteCompeticion e){
            assertTrue(false);
        }
        catch(ExCompeticionFinalizada e){
            assertTrue(false);
        }
        catch(ExDivsNoAsignados e){
            assertTrue(false);
        }
        
        try{
            icu.seleccionarCompeticion(20);
        }
        catch(ExNoExisteCompeticion e){
            assertTrue(false);
        }
        catch(ExCompeticionFinalizada e){
            assertTrue(false);
        }
        catch(ExDivsNoAsignados e){
            assertTrue(false);
        }
        
        try{
            icu.confirmarAltaApuesta(false,false);
            icu.cerrarSesion();
        }
        catch(Exception e){fail();}
       
    }
    
    
    @org.junit.Test
    public void testApostarPartido (){
        
        IControladorUsuarios icu = (new Fabrica()).getIControladorUsuarios();
        DataFechaHora aux = new DataFechaHora(2,2,2000,2,2);
        
        
       
        try{
        icu.registrarUsuario("nom2", "nick2", "mail2", "dir2", "pass2", TipoSexo.Mujer, "pais2", "ci2", aux);
        icu.iniciarSesion("nick2", "pass2");
        icu.ingresarSaldoUsuario(100);
        icu.seleccionarCompeticion(3);
        }
        catch(Exception e){}
        
        try{
            icu.apostarPartido(TipoDividendo.Local, 120);
            fail();
        }
        catch(Exception e){
            assertTrue(true);
        }
        
        try{
            icu.apostarPartido(TipoDividendo.Local, 0);
            fail();
        }
        catch(Exception e){
            assertTrue(true);
        }
        
        
        
        float monto = 33;
        float saldo=0; 
        try{
            saldo = icu.mostrarSaldoUsuario();
            icu.apostarPartido(TipoDividendo.Local, monto);
        }
        catch(Exception e){
            assertTrue(false);
        }
        
        
        DataApuesta res = icu.mostrarApuesta();
        DataApuesta expected = new DataApuestaPartido(monto,saldo+monto*14,monto*14,
                14, null, TipoDividendo.Local,EstadoApuesta.Pendiente,0);
        
        
        org.junit.Assert.assertEquals("mal beneficio",(long)res.getBeneficio(),(long)expected.getBeneficio());
        org.junit.Assert.assertEquals("mal dividendo",(long)res.getDividendo(),(long)expected.getDividendo());
        org.junit.Assert.assertEquals("mal monto",(long)res.getMonto(),(long)expected.getMonto());
        org.junit.Assert.assertEquals("mal saldo nuevo",(long)res.getSaldoNuevo(),(long)expected.getSaldoNuevo());
        
        try{
            icu.confirmarAltaApuesta(true,false);
        }catch(Exception e){fail();}
        
        try{
        icu.seleccionarCompeticion(4);
        }
        catch(Exception e){fail();}
        
        monto = 10;
        
        try{
            saldo = icu.mostrarSaldoUsuario();
            icu.apostarPartido(TipoDividendo.Empate, monto);
        }
        catch(Exception e){
            assertTrue(false);
        }
        
        
        res = icu.mostrarApuesta();
        expected = new DataApuestaPartido(monto,saldo+monto*9,monto*9,9,null,
                TipoDividendo.Empate,EstadoApuesta.Pendiente,0);
        
        
        org.junit.Assert.assertEquals("mal beneficio",(long)res.getBeneficio(),(long)expected.getBeneficio());
        org.junit.Assert.assertEquals("mal dividendo",(long)res.getDividendo(),(long)expected.getDividendo());
        org.junit.Assert.assertEquals("mal monto",(long)res.getMonto(),(long)expected.getMonto());
        org.junit.Assert.assertEquals("mal saldo nuevo",(long)res.getSaldoNuevo(),(long)expected.getSaldoNuevo());
        
        try{
            icu.confirmarAltaApuesta(true,false);
            icu.cerrarSesion();
        }
        catch(Exception e){fail();}
         
    }
    
    
    @org.junit.Test
    public void testApostarCampeonato() throws Exception{
        
        IControladorUsuarios icu = (new Fabrica()).getIControladorUsuarios();
        DataFechaHora aux = new DataFechaHora(2,2,2000,2,2);
        icu.registrarUsuario("nom3", "nick3", "mail3", "dir3", "pass3", TipoSexo.Mujer, "pais3", "ci3", aux);
        icu.iniciarSesion("nick3", "pass3");
        
        
        
        /// APUESTO A CAMPEONATO POR EQUIPO
        try{
            icu.ingresarSaldoUsuario(50);
            icu.seleccionarCompeticion(11);
            icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
            icu.apostarCampeonato(11, 8);
            fail();
        }
        catch(ExMontoInvalido e){
            assertTrue(false);
        }
        catch(ExNoExisteEquipo e){
            assertTrue(true);
        }
        catch(ExEquipoInvalido e){
            assertTrue(false);
        }
        
        
        
        try{
            icu.apostarCampeonato(5, 8);
            fail();
        }
        catch(ExMontoInvalido e){
            assertTrue(false);
        }
        catch(ExNoExisteEquipo e){
            assertTrue(false);
        }
        catch(ExEquipoInvalido e){
            assertTrue(true);
        }
        
        
        try{
            icu.apostarCampeonato(3, 70);
            fail();
        }
        catch(ExMontoInvalido e){
            assertTrue(true);
        }
        catch(ExNoExisteEquipo e){
            assertTrue(false);
        }
        catch(ExEquipoInvalido e){
            assertTrue(false);
        }
        
       
        
        float monto = 20;
        float saldo = icu.mostrarSaldoUsuario();
        try{
            icu.apostarCampeonato(3, monto);
        }
        catch(ExMontoInvalido e){
            assertTrue(false);
        }
        catch(ExNoExisteEquipo e){
            assertTrue(false);
        }
        catch(ExEquipoInvalido e){
            assertTrue(false);
        }
        
        DataApuesta res;
        DataApuesta expected;
        res = icu.mostrarApuesta();
        expected = new DataApuestaCampeon(monto,saldo+33*monto,33*monto,33,null,
                null,EstadoApuesta.Pendiente,0);

        
        
        org.junit.Assert.assertEquals("mal beneficio",(long)res.getBeneficio(),(long)expected.getBeneficio());
        org.junit.Assert.assertEquals("mal dividendo",(long)res.getDividendo(),(long)expected.getDividendo());
        org.junit.Assert.assertEquals("mal monto",(long)res.getMonto(),(long)expected.getMonto());
        org.junit.Assert.assertEquals("mal saldo nuevo",(long)res.getSaldoNuevo(),(long)expected.getSaldoNuevo());
        
        icu.confirmarAltaApuesta(true,false);
        
        
        try{
            icu.seleccionarCompeticion(13);
            icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
        }
        catch(Exception e){
            fail();
        }
        
        monto = 20;
        saldo = icu.mostrarSaldoUsuario();
        try{
            icu.apostarCampeonato(1, monto);
        }
        catch(ExMontoInvalido e){
            assertTrue(false);
        }
        catch(ExNoExisteEquipo e){
            assertTrue(false);
        }
        catch(ExEquipoInvalido e){
            assertTrue(false);
        }

        
        res = icu.mostrarApuesta();
        expected = new DataApuestaCampeon(monto,saldo+8*monto,8*monto,8,null,null,
                EstadoApuesta.Pendiente,0);
        
        org.junit.Assert.assertEquals("mal beneficio",(long)res.getBeneficio(),(long)expected.getBeneficio());
        org.junit.Assert.assertEquals("mal dividendo",(long)res.getDividendo(),(long)expected.getDividendo());
        org.junit.Assert.assertEquals("mal monto",(long)res.getMonto(),(long)expected.getMonto());
        org.junit.Assert.assertEquals("mal saldo nuevo",(long)res.getSaldoNuevo(),(long)expected.getSaldoNuevo());
        
        icu.confirmarAltaApuesta(true,false);
        
        
        
        try{
            icu.seleccionarCompeticion(20);
            icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
        }
        catch(Exception e){
            fail();
        }
        
        monto = 10;
        saldo = icu.mostrarSaldoUsuario();
        try{
            icu.apostarCampeonato(4, monto);
        }
        catch(ExMontoInvalido e){
            assertTrue(false);
        }
        catch(ExNoExisteEquipo e){
            assertTrue(false);
        }
        catch(ExEquipoInvalido e){
            assertTrue(false);
        }

        
        res = icu.mostrarApuesta();
        expected = new DataApuestaCampeon(monto,saldo+4*monto,4*monto,4,null,null,EstadoApuesta.Pendiente,0);

        org.junit.Assert.assertEquals("mal beneficio",(long)res.getBeneficio(),(long)expected.getBeneficio());
        org.junit.Assert.assertEquals("mal dividendo",(long)res.getDividendo(),(long)expected.getDividendo());
        org.junit.Assert.assertEquals("mal monto",(long)res.getMonto(),(long)expected.getMonto());
        org.junit.Assert.assertEquals("mal saldo nuevo",(long)res.getSaldoNuevo(),(long)expected.getSaldoNuevo());
        
        icu.confirmarAltaApuesta(true,false);
        
        
        assertEquals((long)(icu.mostrarSaldoUsuario()),(long)0);
        
        /// AHORA APUESTO A PARTIDO DE CAMPEONATO
        
        
        try{

            icu.ingresarSaldoUsuario(200);
            icu.seleccionarCompeticion(14);
            icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Partido);
        }
        catch(Exception e){
            assertTrue(false);
        }
        
        

        try{
            icu.seleccionarPartidoCamp(7);
            fail();
        }
        catch (ExPartidoCampInvalido e){
            System.out.println(e.getMessage());
            assertTrue(true);
        }
        catch (ExDivsNoAsignados e){
            assertTrue(false);
        }
        
        try{
            icu.seleccionarPartidoCamp(5);
            fail();
        }
        catch (ExPartidoCampInvalido e){
            assertTrue(false);
            fail();
        }
        catch (ExDivsNoAsignados e){
            assertTrue(true);
        }
        
        try{
            icu.seleccionarPartidoCamp(6);
            fail();
        }
        catch (ExPartidoCampInvalido e){
            assertTrue(false);
            fail();
        }
        catch (ExDivsNoAsignados e){
            assertTrue(true);
        }
        
        try{
            icu.seleccionarPartidoCamp(2);
        }
        catch (Exception e){fail();}

        DataInfoPartDividendos data = null;
        try{
            icu.seleccionarPartidoCamp(4);

            data = icu.mostrarDatosPartido();
        }catch(Exception e){fail();}
        
        assertEquals((long)data.getDividendos().getDividendoLocal(),(long)77);
        assertEquals((long)data.getDividendos().getDividendoEmpate(),(long)13);
        assertEquals((long)data.getDividendos().getDividendoVisita(),(long)14);
        assertEquals(data.getEquipoLocal().getId(),2);
        assertEquals((long)data.getEquipoVisitante().getId(),3);
        
        
        icu.apostarPartido(TipoDividendo.Local, 100);

        
        res = icu.mostrarApuesta();
        
        assertEquals((long)res.getDividendo(),77);
        assertEquals((long)res.getMonto(),100);
        assertEquals((long)res.getBeneficio(),100*77);

        icu.confirmarAltaApuesta(true,false);
        
        
        
        
        
            //////// otra apùesta -- a copa :)
        try{
            icu.seleccionarCompeticion(19);
        }
        catch(Exception e){
            fail();
        }
        try{
            icu.seleccionarPartidoCamp(4);
            fail();
        }
        catch (ExPartidoCampInvalido e){
            assertTrue(true);
        }
        catch (ExDivsNoAsignados e){
            assertTrue(true);
        }
        
        try{
            icu.seleccionarPartidoCamp(3);
            fail();
        }
        catch (ExPartidoCampInvalido e){
            assertTrue(false);
        }
        catch (ExDivsNoAsignados e){
            assertTrue(true);
        }
        
        try{
            icu.seleccionarPartidoCamp(1);
        }
        catch (ExPartidoCampInvalido e){
            assertTrue(false);
        }
        catch (ExDivsNoAsignados e){
            assertTrue(false);
        }
        
        data = icu.mostrarDatosPartido();
        
        assertEquals((long)data.getDividendos().getDividendoLocal(),(long)5);
        assertEquals((long)data.getDividendos().getDividendoEmpate(),(long)21);
        assertEquals((long)data.getDividendos().getDividendoVisita(),(long)4);
        assertEquals(data.getEquipoLocal().getId(),2);
        assertEquals((long)data.getEquipoVisitante().getId(),1);
        
        icu.apostarPartido(TipoDividendo.Visitante, 60);
        
        res = icu.mostrarApuesta();
        
        assertEquals((long)res.getDividendo(),4);
        assertEquals((long)res.getMonto(),60);
        assertEquals((long)res.getBeneficio(),60*4);

        icu.confirmarAltaApuesta(true,false);
        icu.cerrarSesion();
    }
    
    @org.junit.Test
    public void testMostrarHistorialApuestas() throws Exception{
        
    
         IControladorUsuarios icu = (new Fabrica()).getIControladorUsuarios();
         DataFechaHora aux = new DataFechaHora(2,2,2000,2,2);
         icu.registrarUsuario("nom4", "nick4", "mail4", "dir4", "pass4", TipoSexo.Mujer, "pais4", "ci4", aux);
         icu.iniciarSesion("nick4", "pass4");
         

         Integer[] montos = {100,200,50,50,50,20,30};
         Integer[][] divsPartidos = {{6,3,6},{10,6,7},{14,11,8},{18,18,9},{2,7,10}};
         Integer[][] divsCampeonatos = {{8,18,33,0},{8,16,36,0},{8,14,39,0},{8,12,42,0},{8,10,45,0},
                                         {8,8,48,0},{8,6,51,0},{8,4,54,0},{11,4,57,10},{11,4,60,13},{11,4,63,7}};
         Integer[] comps_elejidas = {1,12,3,15,4,21,2};
         Integer[] eqs_elejidos = {2,1,4};
         Integer[] tipos_ap_ps = {0,2,0,1};
                

         try{
             icu.ingresarSaldoUsuario(500);
             icu.seleccionarCompeticion(1);
             icu.apostarPartido(TipoDividendo.Local, montos[0]);
             icu.confirmarAltaApuesta(true,false);
         }
         catch(Exception e){fail();}
         
         
         try{
            icu.seleccionarCompeticion(12);
         }
         catch(Exception e){}
         try{
             icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
             icu.apostarCampeonato(2, montos[1]);
             icu.confirmarAltaApuesta(true,false);
         }
         catch(Exception e){fail();}

         
         try{
             icu.seleccionarCompeticion(3);
             icu.apostarPartido(TipoDividendo.Empate, montos[2]);
             icu.confirmarAltaApuesta(true,false);
         }
         catch(Exception e){fail();}


         try{
             icu.seleccionarCompeticion(15);
             icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
             icu.apostarCampeonato(1, montos[3]);
             icu.confirmarAltaApuesta(true,false);
         }
         catch(Exception e){fail();}
         

         try{
             icu.seleccionarCompeticion(4);
             icu.apostarPartido(TipoDividendo.Local, montos[4]);
             icu.confirmarAltaApuesta(true,false);
         }
         catch(Exception e){fail();}


         try{
             icu.seleccionarCompeticion(21);
             icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
             icu.apostarCampeonato(4, montos[5]);
             icu.confirmarAltaApuesta(true,false);
         }
         catch(Exception e){fail();}
         
         
         try{
             icu.seleccionarCompeticion(2);
             icu.apostarPartido(TipoDividendo.Visitante, montos[6]);
             icu.confirmarAltaApuesta(true,false);
         }
         catch(Exception e){fail();}
         
        
         DataFechaHora fechaIni,fechaFin;
         List<DataApuesta> list_res;
         
         fechaIni = new DataFechaHora(13,21,2000,14,24);
         fechaFin = new DataFechaHora(14,1,2330,14,21);
         
         try{
             icu.mostrarHistorialApuestas(fechaIni, fechaFin);
             fail();
         }
         catch(ExFechaInvalida e){
             assertTrue("fechas validas?",true);
         }
         
         
         fechaIni = new DataFechaHora(21,4,2000,77,24);
         fechaFin = new DataFechaHora(31,4,2330,14,21);
         
         try{
             icu.mostrarHistorialApuestas(fechaIni, fechaFin);
             fail();
         }
         catch(ExFechaInvalida e){
             assertTrue("fechas validas?",true);
         }
         
         
         fechaIni = new DataFechaHora(10,3,2005,10,21);
         fechaFin = new DataFechaHora(8,7,2002,22,33);
         
         try{
             icu.mostrarHistorialApuestas(fechaIni, fechaFin);
             fail();
         }
         catch(ExFechaInvalida e){
             assertTrue(false);
         }
         catch(ExRangoInvalido e){
             assertTrue(true);
         }
         
         
         fechaIni = new DataFechaHora(8,7,2002,10,21);
         fechaFin = new DataFechaHora(8,7,2002,2,33);
         
         try{
             icu.mostrarHistorialApuestas(fechaIni, fechaFin);
             fail();
         }
         catch(ExFechaInvalida e){
             assertTrue(false);
         }
         catch(ExRangoInvalido e){
             assertTrue(true);
         }
         
         fechaIni = new DataFechaHora(10,3,2005,10,21);
         fechaFin = new DataFechaHora(8,7,2011,22,33);
         
         list_res = null;
         try{
             list_res = icu.mostrarHistorialApuestas(fechaIni, fechaFin);
         }
         catch(ExFechaInvalida e){
             assertTrue(false);
         }
         catch(ExRangoInvalido e){
             assertTrue(false);
         }
         
         assertEquals(list_res.size(),0);
         
         
         
         fechaIni = new DataFechaHora(10,3,2005,10,21);
         fechaFin = new DataFechaHora(28,12,2011,22,33);
         
         list_res = null;
         try{
             list_res = icu.mostrarHistorialApuestas(fechaIni, fechaFin);
         }
         catch(ExFechaInvalida e){
             assertTrue(false);
         }
         catch(ExRangoInvalido e){
             assertTrue(false);
         }
         
         assertEquals(list_res.size(),7);
         
         DataApuesta[] resultados = new DataApuesta[list_res.size()];
         IControladorFecha icf = (new Fabrica()).getIControladorFecha();
         DataFechaHora fAct = icf.getFecha();
         
         
         /*public DataApuesta (EstadoApuesta ea, float ben, float m, float div,
                               TipoApuesta t){*/
         
         float div=0, ben=0;
         
         int k;
         int h = 0; int r = 0;
         for (k = 0; k < 7; k++){
             if (k % 2 == 0){
                div = divsPartidos[comps_elejidas[k].intValue()-1][tipos_ap_ps[r]];
                r++;
             }
             else{
                div = divsCampeonatos[comps_elejidas[k].intValue()-11][eqs_elejidos[h].intValue()-1];
                h++;
             }
             ben = div*montos[k];
             
             /*public DataApuestaPartido(float monto, float saldoNuevo, float beneficio,
                float dividendo, DataPartido dataP, TipoDividendo tipoD,
                    EstadoApuesta est, int paquete){

              * public DataApuestaCampeon(float monto, float saldoNuevo, float beneficio,
                    float dividendo, DataEquipo dataE, DataCompeticion dataC,
                        EstadoApuesta est, int paquete){
              *
              */

             if (k % 2 == 0) {

                  resultados[k] = new DataApuestaPartido((float)(montos[k]),ben,ben,div,null,TipoDividendo.Empate,EstadoApuesta.Pendiente,0);
             }
             else {
                resultados[k] =  new DataApuestaCampeon(montos[k],ben,
                        ben,div,null,null,EstadoApuesta.Pendiente,0);
             }
             
             /*System.out.println("APUESTA"+(k+1));
             System.out.println("beneficio = "+ben+"    monto = "+montos[k]+"    dividendo = "+div);
             if (k % 2 == 0)
                System.out.println("Id competencia = "+comps_elejidas[k].intValue()+"\n");
             else{
                System.out.println("Id competencia = "+(comps_elejidas[k].intValue()));
                System.out.println("equipo = "+eqs_elejidos[h-1].intValue()+"\n");
             
             }*/
         }
         

         this.compararResultados(list_res, resultados);
         
         /// ejemplo grandee con 800 apuestas !!!
         (new Fabrica()).getIControladorCompeticiones().asignarDividendos(2, 21, 6, 5, 21);
         
         Integer[][][] divs_camp_partidos = {{{37,10,4},{37,4,4},{59,3,13},{59,4,13}},{{41,6,5},{41,3,5},{65,9,6},{65,3,6}},
                                            {{45,2,5},{45,2,5},{71,2,6},{71,2,6}},{{49,11,4},{49,14,4},{77,8,13},{77,14,13}},
                                            {{53,7,2},{53,13,2},{83,14,2},{83,13,2}},{{6,2,21},{6,5,21},{0,0,0},{0,0,0}} /* copa 21 */};
         icu.ingresarSaldoUsuario(630000);
         int size = 800;
         comps_elejidas = new Integer[size];
         montos = new Integer[size];
         eqs_elejidos = new Integer[size];
         TipoDividendo[] tipos_div = new TipoDividendo[size];
         Integer[] tipos_div_num = new Integer[size];
         Integer[] partido_camp = new Integer[size];
         int gastado = 0;
         int cant_apuestas_part = 0;
         int cant_apuestas_equipo = 0;
         int cant_apuestas_al=0;
         //int tellme = 0;
         
         for (int i = 0; i < size; i++){
             
             montos[i] = (i*i*7)%1000+4;
             
             comps_elejidas[i] = (((i*i*i*17+6)/7)%21)+1;
             if (comps_elejidas[i] >= 6 &&  comps_elejidas[i] <= 10)
                 comps_elejidas[i] = 21;
             if (comps_elejidas[i] >= 16 && comps_elejidas[i] <= 20)
                 comps_elejidas[i] = 11;
             
             
             if (comps_elejidas[i] <= 10){
                 
                 comps_elejidas[i] = ((i*11)%5)+1;
                 tipos_div[i] = TipoDividendo.Local;
                 tipos_div_num[i] = 0;
                 cant_apuestas_part++;
                 
                 if (i % 2 == 0){
                     tipos_div[i] = TipoDividendo.Visitante;
                     tipos_div_num[i] = 1;
                 }
                 if (i % 3 == 0){
                     tipos_div[i] = TipoDividendo.Empate;
                     tipos_div_num[i] = 2;
                 }
             }
             else{
                 if (comps_elejidas[i] > 10){
                     if (i % 2 == 0){
                         cant_apuestas_al++;
                         partido_camp[i] = ((i*i*11)%2)+1;
                         
                         tipos_div[i] = TipoDividendo.Local;
                         tipos_div_num[i] = 0;

                         if (i % 4 == 0){
                             tipos_div[i] = TipoDividendo.Visitante;
                             tipos_div_num[i] = 1;
                         }
                         if (i % 8 == 0){
                             tipos_div[i] = TipoDividendo.Empate;
                             tipos_div_num[i] = 2;
                         }
                         
                     }
                     else{
                         eqs_elejidos[i] = ((6*i)%3)+1;
                         cant_apuestas_equipo++;
                     }
                     
                 }
             }
         
         }
         
//         System.out.println("apuestas a partido individual = "+cant_apuestas_part);
//         System.out.println("apuestas a equipo = "+cant_apuestas_equipo);
//         System.out.println("apuestas a partidos de campeonatos = "+cant_apuestas_al);
//         //System.out.println("apuestas a copa = "+tellme);
         
         for (int i = 0; i < size; i++){
             gastado += montos[i];
             if (comps_elejidas[i] <= 10){
                 icu.seleccionarCompeticion(comps_elejidas[i].intValue());
                 icu.apostarPartido(tipos_div[i], montos[i].intValue());
                 icu.confirmarAltaApuesta(true,false);
             }
             else{
                 if (i % 2 == 0){
                    icu.seleccionarCompeticion(comps_elejidas[i].intValue());
                    icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Partido);
                    icu.seleccionarPartidoCamp(partido_camp[i].intValue());
                    icu.apostarPartido(tipos_div[i], montos[i].intValue());
                    icu.confirmarAltaApuesta(true,false);
                 }
                 else{
                     icu.seleccionarCompeticion(comps_elejidas[i].intValue());
                     icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
                     icu.apostarCampeonato(eqs_elejidos[i], montos[i].intValue());
                     icu.confirmarAltaApuesta(true,false);
                 }
             }
         }

         
         /*public DataApuesta (EstadoApuesta ea, float ben, float m, float div,
                               TipoApuesta t){*/
         
         list_res = icu.mostrarHistorialApuestas(fechaIni, fechaFin);
         
         assertEquals(list_res.size(),size+7);
         assertEquals((long)icu.mostrarSaldoUsuario(),(long)(630000-gastado));
         //System.out.println("gastado = "+gastado);
         
         DataApuesta[] res = new DataApuesta[7];
         for (int i = 0; i < 7; i++)
             res[i] = resultados[i];
         
         resultados = new DataApuesta[list_res.size()];
         
         
         for (int i = 0; i < size; i++){
             if (comps_elejidas[i] <= 10 || i % 2 == 0){
                 if (comps_elejidas[i].intValue() <= 5)
                    div = divsPartidos[comps_elejidas[i].intValue()-1][tipos_div_num[i].intValue()];
                 else{
                     if (comps_elejidas[i] != 21)
                         div = divs_camp_partidos[comps_elejidas[i].intValue()-11][partido_camp[i]-1][tipos_div_num[i]];
                     else
                         div = divs_camp_partidos[5][partido_camp[i]-1][tipos_div_num[i]];
                 }
                 /*DataApuestaPartido(float monto, float saldoNuevo, float beneficio,
                    float dividendo, DataPartido dataP, TipoDividendo tipoD,
                     EstadoApuesta est, int paquete){

                  */
                 ben = div*montos[i];
                 resultados[i] = new DataApuestaPartido((float)(montos[i].intValue()),ben,ben,div,null,TipoDividendo.Empate,EstadoApuesta.Pendiente,0);
             }
             else{
                 div = divsCampeonatos[comps_elejidas[i].intValue()-11][eqs_elejidos[i].intValue()-1];
                 ben = div*montos[i];
                 resultados[i] = new DataApuestaCampeon((float)(montos[i].intValue()),ben,ben,div,null,null,EstadoApuesta.Pendiente,0);
             }
         }
         
         for (int i = 0; i < 7; i++)
             resultados[i+size] = res[i];
         assertTrue(list_res.size()==resultados.length && list_res.size()==807);
         this.compararResultados(list_res, resultados);
         
         icu.cerrarSesion();
         
    }
    
    
    @org.junit.Test
    public void testMostrarApuestas(){
        IControladorUsuarios icu = (new Fabrica()).getIControladorUsuarios();
        IControladorFecha icf = (new Fabrica()).getIControladorFecha();
        DataFechaHora f1=null,f2=null;
        
        try{
             icu.iniciarSesion("nickA", "passA");
             icu.ingresarSaldoUsuario(5000);
             
             // apuesta 1 en a partido de camp en el 2007
             f1 = new DataFechaHora(2,4,2007,2,1);
             icf.setFecha(f1);
             icu.seleccionarCompeticion(12);
             icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Partido);
             icu.seleccionarPartidoCamp(4);
             icu.apostarPartido(TipoDividendo.Local, 100);
             icu.confirmarAltaApuesta(true,false);
             
             
             // apuesta 2 a partido indiv en el 2007
             f1 = new DataFechaHora(3,10,2007,2,1);
             icf.setFecha(f1);
             icu.seleccionarCompeticion(4);
             icu.apostarPartido(TipoDividendo.Empate, 150);
             icu.confirmarAltaApuesta(true,false);
             
             
             // apuesta 3 a equipo de camp en el 2008
             f1 = new DataFechaHora(6,6,2008,2,1);
             icf.setFecha(f1);
             icu.seleccionarCompeticion(20);
             icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
             icu.apostarCampeonato(4, 80);
             icu.confirmarAltaApuesta(true,false);
             
             
             // apuesta 4 a equipo de camp en el 2008
             f1 = new DataFechaHora(7,12,2008,2,22);
             icf.setFecha(f1);
             icu.seleccionarCompeticion(11);
             icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
             icu.apostarCampeonato(2, 20);
             icu.confirmarAltaApuesta(true,false);
             
             
             // apuesta 5 a part de camp en el 2009
             f1 = new DataFechaHora(12,3,2009,1,1);
             icf.setFecha(f1);
             icu.seleccionarCompeticion(19);
             icu.seleccionarPartidoCamp(1);
             icu.apostarPartido(TipoDividendo.Visitante, 200);
             icu.confirmarAltaApuesta(true,false);
             
             
             // apuesta 6 a part indiv en el 2009
             f1 = new DataFechaHora(5,4,2009,2,1);
             icf.setFecha(f1);
             icu.seleccionarCompeticion(1);
             icu.apostarPartido(TipoDividendo.Local, 100);
             icu.confirmarAltaApuesta(true,false);
             
             
             // apuesta 7 a part indiv en el 2010
             f1 = new DataFechaHora(5,4,2010,2,1);
             icf.setFecha(f1);
             icu.seleccionarCompeticion(1);
             icu.apostarPartido(TipoDividendo.Empate, 500);
             icu.confirmarAltaApuesta(true,false);
             
             
             //apuesta 8 a equipo de camp en el 2010
             f1 = new DataFechaHora(10,9,2010,11,1);
             icf.setFecha(f1);
             icu.seleccionarCompeticion(18);
             icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
             icu.apostarCampeonato(2, 200);
             icu.confirmarAltaApuesta(true,false);
             
             
             //apuesta 9 a equipo de camp en el 2010
             f1 = new DataFechaHora(25,12,2010,11,1);
             icf.setFecha(f1);
             icu.seleccionarCompeticion(21);
             icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
             icu.apostarCampeonato(4, 50);
             icu.confirmarAltaApuesta(true,false);
             
             
             //apuesta 10 a part indiv en el 2011
             f1 = new DataFechaHora(7,1,2011,1,1);
             icf.setFecha(f1);
             icu.seleccionarCompeticion(2);
             icu.apostarPartido(TipoDividendo.Empate, 100);
             icu.confirmarAltaApuesta(true,false);
             
        }
        catch(Exception e){fail();}
        
        
        f1 = new DataFechaHora(4,1,2008,2,1);
        f2 = new DataFechaHora(3,4,2006,3,2);
        try{
            icu.mostrarHistorialApuestas(f1, f2);
            fail();
        }
        catch(Excepciones.ExRangoInvalido e){
            assertTrue(true);
        }
        catch(Excepciones.ExFechaInvalida e){
            assertTrue(false);
        }
        
        
        f1 = new DataFechaHora(6,2,2000,2,2);
        f2 = new DataFechaHora(32,6,2001,2,1);
        try{
            icu.mostrarHistorialApuestas(f1, f2);
            fail();
        }
        catch(Excepciones.ExRangoInvalido e){
            assertTrue(false);
        }
        catch(Excepciones.ExFechaInvalida e){
            assertTrue(true);
        }
        
        
        
        List<DataApuesta> list_da=null;
        DataApuesta[] resultados;
        int size;
        
        
        f1 = new DataFechaHora(30,2,2010,2,4);
        f2 = new DataFechaHora(27,12,2010,5,1);
        try{
            // muestro las apuestas del 2010
            list_da = icu.mostrarHistorialApuestas(f1, f2);
        }
        catch(Excepciones.ExRangoInvalido e){
            assertTrue(false);
        }
        catch(Excepciones.ExFechaInvalida e){
            assertTrue(false);
        }
        
        size = 3;
        resultados = new DataApuesta[size];
        resultados[0] = new DataApuestaPartido((float)500,(float)3000,(float)3000,(float)6,null,
                TipoDividendo.Empate,EstadoApuesta.Pendiente,0);
        
        resultados[1] = new DataApuestaCampeon(200,800,800,4,null,null,EstadoApuesta.Pendiente,0);
        resultados[2] = new DataApuestaCampeon(50,350,350,7,null,null,EstadoApuesta.Pendiente,0);
        assertTrue(list_da.size()==3 && resultados.length==list_da.size());
        this.compararResultados(list_da, resultados);
        
        
        f1 = new DataFechaHora(1,1,2007,2,4);
        f2 = new DataFechaHora(30,12,2007,5,1);
        try{
            // muestro las apuestas del 2007
            list_da = icu.mostrarHistorialApuestas(f1, f2);
        }
        catch(Excepciones.ExRangoInvalido e){
            assertTrue(false);
        }
        catch(Excepciones.ExFechaInvalida e){
            assertTrue(false);
        }
        size = 2;
        resultados = new DataApuesta[size];
        resultados[0] = new DataApuestaPartido(100,6500,6500,65,null,TipoDividendo.Empate,EstadoApuesta.Pendiente,0);
        resultados[1] = new DataApuestaPartido(150,1350,1350,9,null,TipoDividendo.Empate,EstadoApuesta.Pendiente,0);

        assertTrue(list_da.size()==2 && resultados.length==list_da.size());
        this.compararResultados(list_da, resultados);
        
        f1 = new DataFechaHora(10,1,2010,2,4);
        f2 = new DataFechaHora(20,12,2011,5,1);
        try{
            // muestro las apuestas del 2010-2011
            list_da = icu.mostrarHistorialApuestas(f1, f2);
        }
        catch(Excepciones.ExRangoInvalido e){
            assertTrue(false);
        }
        catch(Excepciones.ExFechaInvalida e){
            assertTrue(false);
        }
        
        size = 4;
        resultados = new DataApuesta[size];
        resultados[0] = new DataApuestaPartido(500,3000,3000,6,null,TipoDividendo.Empate,EstadoApuesta.Pendiente,0);
        resultados[1] = new DataApuestaCampeon(200,800,800,4,null,null,EstadoApuesta.Pendiente,0);
        resultados[2] = new DataApuestaCampeon(50,350,350,7,null,null,EstadoApuesta.Pendiente,0);
        resultados[3] = new DataApuestaPartido(100,700,700,7,null,TipoDividendo.Empate,EstadoApuesta.Pendiente,0);
        

        assertTrue(list_da.size()==4 && resultados.length==list_da.size());
        this.compararResultados(list_da, resultados);
        
        
        f1 = new DataFechaHora(1,1,2007,2,4);
        f2 = new DataFechaHora(31,12,2009,5,1);
        try{
            // muestro las apuestas del 2010-2011
            list_da = icu.mostrarHistorialApuestas(f1, f2);
        }
        catch(Excepciones.ExRangoInvalido e){
            assertTrue(false);
        }
        catch(Excepciones.ExFechaInvalida e){
            assertTrue(false);
        }
        
        
        
        size = 6;
        resultados = new DataApuesta[size];
        resultados[0] = new DataApuestaPartido(100,6500,6500,65,null,TipoDividendo.Empate,EstadoApuesta.Pendiente,0);
        resultados[1] = new DataApuestaPartido(150,1350,1350,9,null,TipoDividendo.Empate,EstadoApuesta.Pendiente,0);
        resultados[2] = new DataApuestaCampeon(80,320,320,4,null,null,EstadoApuesta.Pendiente,0);
        resultados[3] = new DataApuestaCampeon(20,360,360,18,null,null,EstadoApuesta.Pendiente,0);
        resultados[4] = new DataApuestaPartido(200,800,800,4,null,TipoDividendo.Empate,EstadoApuesta.Pendiente,0);
        resultados[5] = new DataApuestaPartido(100,600,600,6,null,TipoDividendo.Empate,EstadoApuesta.Pendiente,0);
        

        assertTrue(list_da.size()==6 && resultados.length==list_da.size());
        this.compararResultados(list_da, resultados);
        
        
        
        f1 = new DataFechaHora(3,7,2010,2,4);
        f2 = new DataFechaHora(10,11,2010,5,1);
        try{
            // solo 1 apuesta del 2010
            list_da = icu.mostrarHistorialApuestas(f1, f2);
        }
        catch(Excepciones.ExRangoInvalido e){
            assertTrue(false);
        }
        catch(Excepciones.ExFechaInvalida e){
            assertTrue(false);
        }
        
        size = 1;
        resultados = new DataApuesta[size];
        resultados[0] = new DataApuestaCampeon(200,800,800,4,null,null,EstadoApuesta.Pendiente,0);
        assertTrue(list_da.size()==size && resultados.length==list_da.size());
        this.compararResultados(list_da, resultados);

    }

    @org.junit.Test
    public void testPaqueteApuestas() throws Exception {
        System.out.println("PaqueteApuestas");
        IControladorUsuarios icu = (new Fabrica()).getIControladorUsuarios();
        
        DataFechaHora f1=null,f2=null;

        // Pruebo ver el paquete sin iniciar sesion
        try {
            icu.mostrarPaqueteApuestas();
            fail();
        }
        catch (Exception exc) {
            assertTrue(exc.getMessage().equals("Error: No ha iniciado sesion"));
        }
        
        // Pruebo crear paquete sin iniciar sesion
        try {
            icu.crearPaqueteApuestas();
            assertTrue(false);
        }
        catch (Exception exc) {
            assertTrue(exc.getMessage().equals("Error: No ha iniciado sesion"));
        }

        // Inicio sesion con un usuario creado que tiene 0 apuestas
        boolean registroExitoso = icu.registrarUsuario("nomUsu", "nickUsu", "mailUsu",
                "dirUsu", "passUsu", TipoSexo.Mujer, "paisUsu", "ciUsu",new DataFechaHora(1,1,2008,5,1));
        
        icu.iniciarSesion("nickUsu", "passUsu");

        /// Pruebo ver el paquete y que no esta inicializado!!
        try {
            icu.mostrarPaqueteApuestas();
            fail();
        }
        catch (Exception exc) {
            assertTrue(exc.getMessage().equals("No se ha activado el paquete de apuestas"));
        }


        f1 = new DataFechaHora(1,1,1990,2,4);
        f2 = new DataFechaHora(12,12,2020,5,1);
        List<DataApuesta> list_da = null;
        try{
            // tiene 0 apuestas
            list_da = icu.mostrarHistorialApuestas(f1, f2);
        }
        catch(Exception e){
            assertTrue(false);
        }

        assertTrue(list_da.isEmpty());

        // Creo un paquete de apuestas para el usuario
        try {
            icu.crearPaqueteApuestas();
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Pruebo ver el paquete de apuestas vacio!!
        DataPaqueteApuestas paqApuestas = null;
        try {
            paqApuestas = icu.mostrarPaqueteApuestas();
            assertTrue(paqApuestas.getApuestas().isEmpty());
            assertTrue(paqApuestas.getMontoTotal()==0);
        }
        catch (Exception exc) {
            assertTrue(false);
        }
        
        Fabrica f = new Fabrica();
        IControladorCompeticiones icc = f.getIControladorCompeticiones();

        
        /* Competicion id=15 nombre=comp10
         * Liga
         * eq1,eq2,eq3
         * Partidos:
         *      eq1-eq2 No fin-Div Asig 1
         *      eq1-eq3 No fin-Div Asig 2
         *      eq2-eq1 No fin-Div Asig 3
         *      eq2-eq3 No fin-Div Asig 4
         *      eq3-eq1 No fin-No Div Asig 5
         *      eq3-eq2 No fin-No Div Asig 6
         */

        // Pruebo agregar a paquete apuesta partido sin div asignados
        try {
            icu.seleccionarCompeticion(15);
            icu.seleccionarPartidoCamp(5);
            fail();
            //icu.apostarPartido(TipoDividendo.Visitante, 200);
            icu.apostarPartidoPaquete(TipoDividendo.Local, 10);
            icu.confirmarAltaApuesta(true,true);
            
        }
        catch (Exception exc) {
            assertTrue(exc.getMessage().equals("Los dividendos del partido no estan asignados"));
        }


        // Pruebo agregar apuesta de partido a un paquete en una competicion LIGA
        try {
            icu.seleccionarCompeticion(15);
            icu.seleccionarPartidoCamp(1);
            //icu.apostarPartido(TipoDividendo.Visitante, 200);
            icu.apostarPartidoPaquete(TipoDividendo.Local, 10);
            icu.agregarApuestaPaquete();
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Usuario nick=nickA tiene una apuesta en el paquete por partido id=1
        // aposto local y aposto 10
        paqApuestas = null;
        try {
            paqApuestas = icu.mostrarPaqueteApuestas();
            assertTrue(paqApuestas.getApuestas().size()==1);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        DataApuesta dataAp = paqApuestas.getApuestas().get(0);
        assertTrue(dataAp instanceof DataApuestaPartido);

        DataApuestaPartido dataApPart = (DataApuestaPartido) dataAp;
        assertTrue(dataApPart.getPartido().getDataInfoPart().getIdComp()==15);
        assertTrue(dataApPart.getTipoResultado()==TipoDividendo.Local);
        assertTrue(dataApPart.getPartido().getDataInfoPart().getIdPar()==1);

        // Pruebo terminar el paquete
        boolean paqValido = true;
        try {
            paqValido = icu.validarPaqueteApuestas();
            assertTrue(!paqValido);
        }
        catch (Exception exc) {
            assertTrue(false);
        }


        // Agrego nuevamente la apuesta al paquete
        // Pruebo agregar apuesta de partido a un paquete en una competicion LIGA
        try {
            icu.seleccionarCompeticion(15);
            icu.seleccionarPartidoCamp(1);
            //icu.apostarPartido(TipoDividendo.Visitante, 200);
            icu.apostarPartidoPaquete(TipoDividendo.Local, 10);
            icu.agregarApuestaPaquete();
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Pruebo agregar apuesta de partido a un paquete en una competicion LIGA
        try {
            icu.seleccionarCompeticion(15);
            icu.seleccionarPartidoCamp(2);
            //icu.apostarPartido(TipoDividendo.Visitante, 200);
            icu.apostarPartidoPaquete(TipoDividendo.Empate, 20);
            icu.agregarApuestaPaquete();
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Pruebo agregar apuesta de partido a un paquete en una competicion LIGA
        try {
            icu.seleccionarCompeticion(15);
            icu.seleccionarPartidoCamp(3);
            //icu.apostarPartido(TipoDividendo.Visitante, 200);
            icu.apostarPartidoPaquete(TipoDividendo.Visitante, 30);
            icu.agregarApuestaPaquete();
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Usuario nick=nickA
        //      Apuesta Partido id=1 Local 10
        //      Apuesta Partido id=2 Local 15
        //      Apuesta Partido id=3 Local 20

        paqApuestas = null;
        try {
            paqApuestas = icu.mostrarPaqueteApuestas();
            assertTrue(paqApuestas.getApuestas().size()==3);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        dataAp = paqApuestas.getApuestas().get(0);
        assertTrue(dataAp instanceof DataApuestaPartido);
        dataApPart = (DataApuestaPartido) dataAp;
        assertTrue(dataApPart.getPartido().getDataInfoPart().getIdComp()==15);
        assertTrue(dataApPart.getTipoResultado()==TipoDividendo.Local);
        assertTrue(dataApPart.getPartido().getDataInfoPart().getIdPar()==1);
        assertTrue(dataApPart.getMonto()==10);

        dataAp = paqApuestas.getApuestas().get(1);
        assertTrue(dataAp instanceof DataApuestaPartido);
        dataApPart = (DataApuestaPartido) dataAp;
        assertTrue(dataApPart.getPartido().getDataInfoPart().getIdComp()==15);
        assertTrue(dataApPart.getTipoResultado()==TipoDividendo.Empate);
        assertTrue(dataApPart.getPartido().getDataInfoPart().getIdPar()==2);
        assertTrue(dataApPart.getMonto()==20);

        dataAp = paqApuestas.getApuestas().get(2);
        assertTrue(dataAp instanceof DataApuestaPartido);
        dataApPart = (DataApuestaPartido) dataAp;
        assertTrue(dataApPart.getPartido().getDataInfoPart().getIdComp()==15);
        assertTrue(dataApPart.getTipoResultado()==TipoDividendo.Visitante);
        assertTrue(dataApPart.getPartido().getDataInfoPart().getIdPar()==3);
        assertTrue(dataApPart.getMonto()==30);

        // Veo que el usuario tiene 10 apuestas como al principio
        f1 = new DataFechaHora(1,1,1990,2,4);
        f2 = new DataFechaHora(12,12,2020,5,1);
        list_da = null;
        try{
            // tiene 10 apuestas
            list_da = icu.mostrarHistorialApuestas(f1, f2);
        }
        catch(Exception e){
            assertTrue(false);
        }

        assertTrue(list_da.isEmpty());


        icu.ingresarSaldoUsuario(1000);

        // Termino el paquete
        paqValido = true;
        try {
            System.out.println("############################ Valido");
            paqValido = icu.validarPaqueteApuestas();
            assertTrue(paqValido);
            icu.confirmarPaqueteApuestas();
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Veo que el usuario tiene 13 apuestas
        f1 = new DataFechaHora(1,1,1990,2,4);
        f2 = new DataFechaHora(12,12,2020,5,1);
        list_da = null;
        try{
            list_da = icu.mostrarHistorialApuestas(f1, f2);
        }
        catch(Exception e){
            assertTrue(false);
        }

        assertTrue(list_da.size()==3);

        assertTrue(list_da.get(0).getMonto()==10);
        assertTrue(list_da.get(0) instanceof DataApuestaPartido);
        assertTrue(list_da.get(1).getMonto()==20);
        assertTrue(list_da.get(1) instanceof DataApuestaPartido);
        assertTrue(list_da.get(2).getMonto()==30);
        assertTrue(list_da.get(2) instanceof DataApuestaPartido);

        // Vemos que el usuario tiene un unico paquete con tales apuestas
        List<DataPaqueteApuestas> listDataPaqAp = null;
        try{
            listDataPaqAp = icu.verPaqueteApuestas();
            assertTrue(listDataPaqAp.size()==1);
        }
        catch(Exception e){
            assertTrue(false);
        }

        DataPaqueteApuestas dataPaqApu = listDataPaqAp.get(0);
        List<DataApuesta> listDataAp = dataPaqApu.getApuestas();
        assertTrue(listDataAp.get(0).getMonto()==10);
        assertTrue(listDataAp.get(0) instanceof DataApuestaPartido);
        assertTrue(listDataAp.get(1).getMonto()==20);
        assertTrue(listDataAp.get(1) instanceof DataApuestaPartido);
        assertTrue(listDataAp.get(2).getMonto()==30);
        assertTrue(listDataAp.get(2) instanceof DataApuestaPartido);

        // Creo una competicion Liga id= con 2 equipos eq1 y eq2 y creo los partidos y los finalizo
        // Existen dividendos para jugadores 1,2,3,4
        int idLiga = 0;
        try {
            icc.ingresarCompeticion("Liga1",TipoCompeticion.Liga,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);
            TipoCriterio[] dc2 = {TipoCriterio.GolesFavor,TipoCriterio.DiferenciaGoles,TipoCriterio.Resultado};
            icc.ingresarOrdenLiga(dc2);
            icc.agregarIJC(1,2);
            icc.agregarIJC(2,4);
            icc.agregarIJC(3,6);
            icc.agregarIJC(4,8);
            icc.ingresarDividendoEquipo(1,2);
            icc.ingresarDividendoEquipo(2,4);
            idLiga = icc.darDeAltaCompetencia();
            
            icc.finalizar();

            icc.ingresarIdLiga(idLiga);
            icc.seleccionarEncuentroADefinir (1,2,new DataFechaHora(1,1,2001,5,1),"");

            // Intento apostar a partido sin div asig
            icu.seleccionarCompeticion(idLiga);
            icu.seleccionarPartidoCamp(1);
            fail();

        }
        catch (Exception exc) {            
        }

        try {
            
            
            icc.asignarDividendos(1,idLiga,111,112,113);
            icc.almacenarAsigDividendoRes (0,0,2);
            icc.almacenarAsigDividendoRes (0,1,3);
            icc.almacenarAsigDividendoRes (1,0,4);
            icc.almacenarAsigDividendoRes (1,1,5);
            icc.almacenarAsigDividendoRes (1,0,4);
            icc.almacenarAsigDividendoRes (3,3,33);
            icc.asignarDividendoPartidoResultado (1,idLiga);

            icc.ingresarIdLiga(idLiga);
            icc.seleccionarEncuentroADefinir (2,1,new DataFechaHora(1,1,2001,5,1),"");

            icc.asignarDividendos(2,idLiga,111,112,113);
            icc.almacenarAsigDividendoRes (0,0,2);
            icc.almacenarAsigDividendoRes (0,1,3);
            icc.almacenarAsigDividendoRes (1,0,4);
            icc.almacenarAsigDividendoRes (1,1,5);
            icc.almacenarAsigDividendoRes (1,0,4);
            icc.almacenarAsigDividendoRes (3,3,33);
            icc.asignarDividendoPartidoResultado (2,idLiga);


            List<DataInfoPartido> listDataIP = icc.listarPartidosConDivAsignado(idLiga);
            icc.seleccionarPartido(listDataIP.get(0));
            icc.ingresarResultado(2,0,1,0);
            icc.ingresarJugador(1,1);
            icc.ingresarJugador(2,1);
            icc.ingresarJugador(3,2);
            icc.ingresarJugador(4,2);
            
            icc.listarIngresos();
            icc.confirmarIngreso(true);
            

//            listDataIP = icc.listarPartidosConDivAsignado(idLiga);
//            icc.seleccionarPartido(listDataIP.get(0));
//            icc.ingresarResultado(1,0,1,0);
//            icc.ingresarJugador(1,1);
//            icc.ingresarJugador(2,1);
//            icc.ingresarJugador(3,2);
//            icc.ingresarJugador(4,2);
//            icc.listarIngresos();
//            icc.confirmarIngreso(true);
            
        }
        catch(Exception exc) {
            System.out.println(exc.getMessage());
            assertTrue(false);
        }

        // Creo otro paquete pero creandolo cuando agrego una apuesta
        // Pruebo agregar apuesta de goleador a un paquete en una competicion LIGA
        try {
            icu.seleccionarCompeticion(idLiga);
            icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Goleador);
            icu.apostarGoleadorPaquete(1, 10);
            icu.agregarApuestaPaquete();

        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Liga finalizada con idLiga nombre=Liga1 eq1 y eq2
        // Partidos:
        //      eq1 eq2 Fin-DivAsig
        //      eq2 eq1 No Fin-DivAsig

        // Usuario nick=nickA
        // Paquete1:
        //      Apuesta Partido id=1 Local 10
        //      Apuesta Partido id=2 Local 15
        //      Apuesta Partido id=3 Local 20

        // PaqueteMemoriaTemporal:
        //      Apuesta Goleador idJug=1 idComp=idLiga

        listDataPaqAp = null;
        try{
            listDataPaqAp = icu.verPaqueteApuestas();
            assertTrue(listDataPaqAp.size()==1);
            dataPaqApu = icu.mostrarPaqueteApuestas();
            assertTrue(listDataPaqAp.size()==1);
            dataAp = dataPaqApu.getApuestas().get(0);
            assertTrue(dataAp instanceof DataApuestaGoleador);
        }
        catch(Exception e){
            assertTrue(false);
        }

        // Apuesto por el mismo goleador y por un equipo en la misma comp veces mas e intento confirmar
        try {
            icu.seleccionarCompeticion(idLiga);
            icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
            icu.apostarCampeonatoPaquete(1, 20);
            icu.agregarApuestaPaquete();

        }
        catch (Exception exc) {
            assertTrue(false);
        }

        try {
            icu.seleccionarCompeticion(idLiga);
            icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Goleador);
            icu.apostarGoleadorPaquete(1, 30);
            icu.agregarApuestaPaquete();
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Confirmo y veo que es invalido y vacio el paquete
        try{
            dataPaqApu = icu.mostrarPaqueteApuestas();
            assertTrue(dataPaqApu.getApuestas().size()==3);
            dataAp = dataPaqApu.getApuestas().get(0);
            assertTrue(dataAp instanceof DataApuestaGoleador);
            assertTrue(dataAp.getMonto()==10);
            dataAp = dataPaqApu.getApuestas().get(1);
            assertTrue(dataAp instanceof DataApuestaCampeon);
            assertTrue(dataAp.getMonto()==20);
            dataAp = dataPaqApu.getApuestas().get(2);
            assertTrue(dataAp instanceof DataApuestaGoleador);
            assertTrue(dataAp.getMonto()==30);
        }
        catch(Exception e){
            assertTrue(false);
        }

        try{
            paqValido = icu.validarPaqueteApuestas();
            assertTrue(!paqValido);
        }
        catch(Exception e){
            assertTrue(false);
        }

        // Confirmo que no tenga nada
        try{
            dataPaqApu = icu.mostrarPaqueteApuestas();
            assertTrue(dataPaqApu.getApuestas().isEmpty());
        }
        catch(Exception e){
            assertTrue(false);
        }

        // Creo otro paquete y lo hago mal de vuelta!
        try {
            icu.seleccionarCompeticion(idLiga);
            icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Goleador);
            icu.apostarGoleadorPaquete(1, 10);
            icu.agregarApuestaPaquete();

            icu.seleccionarCompeticion(idLiga);
            icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
            icu.apostarCampeonatoPaquete(1, 20);
            icu.agregarApuestaPaquete();

            icu.seleccionarCompeticion(idLiga);
            icu.seleccionarPartidoCamp(2);
            icu.apostarPartidoPaquete(TipoDividendo.Local, 10);
            icu.agregarApuestaPaquete();

            paqValido = icu.validarPaqueteApuestas();
            assertTrue(!paqValido);
        }
        catch (Exception exc) {
            assertTrue(false);
        }

        // Creo otro paquete y lo hago bien!
        // Creo un PartidoIndividual para apostar sobre el partido
        int idPartInd = 0;
        try {
            icc.ingresarCompeticion("PartInd",TipoCompeticion.PartidoIndividual,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);
            idPartInd = icc.darDeAltaCompetencia();
            icc.finalizar();

            icc.ingresarDatosPartidoIndividual(idPartInd,1,2,new DataFechaHora(1,1,2001,5,1),"");

            icc.asignarDividendos(1,idPartInd,2,3,4);
            icc.almacenarAsigDividendoRes (0,0,2);
            icc.almacenarAsigDividendoRes (0,1,3);
            icc.almacenarAsigDividendoRes (1,0,4);
            icc.almacenarAsigDividendoRes (1,1,5);
            icc.asignarDividendoPartidoResultado (1,idPartInd);

            icu.seleccionarCompeticion(idLiga);
            icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Goleador);
            icu.apostarGoleadorPaquete(1, 10);
            icu.agregarApuestaPaquete();

            icu.seleccionarCompeticion(idLiga);
            icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
            icu.apostarCampeonatoPaquete(1, 20);
            icu.agregarApuestaPaquete();

            icu.seleccionarCompeticion(idPartInd);
            
            icu.apostarPartidoPaquete(TipoDividendo.Local, 30);
            icu.agregarApuestaPaquete();

            paqValido = icu.validarPaqueteApuestas();            
            assertTrue(paqValido);
            icu.confirmarPaqueteApuestas();

        }
        catch (Exception exc) {
            System.out.println(exc.getMessage());
            assertTrue(false);
        }

        listDataPaqAp = null;
        try{
            listDataPaqAp = icu.verPaqueteApuestas();
            assertTrue(listDataPaqAp.size()==2);
            assertTrue(listDataPaqAp.get(0).getApuestas().get(0) instanceof DataApuestaPartido);
            assertTrue(listDataPaqAp.get(0).getApuestas().get(1) instanceof DataApuestaPartido);
            assertTrue(listDataPaqAp.get(0).getApuestas().get(2) instanceof DataApuestaPartido);

            assertTrue(listDataPaqAp.get(1).getApuestas().get(0) instanceof DataApuestaGoleador);
            assertTrue(listDataPaqAp.get(1).getApuestas().get(1) instanceof DataApuestaCampeon);
            assertTrue(listDataPaqAp.get(1).getApuestas().get(2) instanceof DataApuestaPartido);
        }
        catch(Exception e){
            assertTrue(false);
        }

        /* Competicion id=15 nombre=comp10
         * Liga
         * eq1,eq2,eq3
         * Partidos:
         *      eq1-eq2 No fin-Div Asig 1
         *      eq1-eq3 No fin-Div Asig 2
         *      eq2-eq1 No fin-Div Asig 3
         *      eq2-eq3 No fin-Div Asig 4
         *      eq3-eq1 No fin-No Div Asig 5
         *      eq3-eq2 No fin-No Div Asig 6
         */

        // Liga finalizada con idLiga nombre=Liga1 eq1 y eq2
        // Partidos:
        //      eq1 eq2 Fin-DivAsig
        //      eq2 eq1 No Fin-DivAsig

        // PartidoIndividual no finalizado eq1 y eq2
        // Partido:
        //      eq1 eq2 No Fin-DivAsig

        // Usuario nick=nickA
        // Paquete1:
        //      Apuesta Partido idCamp=idLiga id=1 Local 10
        //      Apuesta Partido idCamp=idLiga id=2 Local 15
        //      Apuesta Partido idCamp=idLiga id=3 Local 20
        // Paquete2:
        //      Apuesta Goleador idCamp=idLiga idJug=1  10
        //      Apuesta Campeon idCamp=idLiga id=1 20
        //      Apuesta Partido idCamp=idPartInd id=1 Local 30
        // PaqueteMemoriaTemporal:
        //      Apuesta Goleador idJug=1 idComp=idLiga

        // Apuesto Goleador en un PartidoIndividual
        try {
            icu.seleccionarCompeticion(idPartInd);
            icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Goleador);
            icu.apostarGoleadorPaquete(1, 10);
            icu.agregarApuestaPaquete();
            fail();

        }
        catch (Exception exc) {
        }

        // Creo otro paquete y lo hago bien!
        try {
            icu.seleccionarCompeticion(idLiga);
            icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Goleador);
            icu.apostarGoleadorPaquete(1, 10);
            icu.agregarApuestaPaquete();

            icu.seleccionarCompeticion(idLiga);
            icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
            icu.apostarCampeonatoPaquete(1, 20);
            icu.agregarApuestaPaquete();

            icu.seleccionarCompeticion(idPartInd);
            icu.apostarResultadoExactoPaquete(1,1,30);
            icu.agregarApuestaPaquete();

            icu.seleccionarCompeticion(idLiga);
            icu.seleccionarCampApuesta(TipoCampeonatoApuesta.ResExacto);
            icu.seleccionarPartidoCamp(2);
            icu.apostarResultadoExactoPaquete(1,1,40);
            icu.agregarApuestaPaquete();

            paqValido = icu.validarPaqueteApuestas();
            assertTrue(paqValido);
            icu.confirmarPaqueteApuestas();

        }
        catch (Exception exc) {
            System.out.println(exc.getMessage());
            assertTrue(false);
        }

        // confirmo que tengo 3 paquetes y que no hay ninguno activo
        listDataPaqAp = null;
        try{
            listDataPaqAp = icu.verPaqueteApuestas();
            assertTrue(listDataPaqAp.size()==3);
            dataPaqApu = icu.mostrarPaqueteApuestas();
            assertTrue(false);
        }
        catch(Exception e){
        }

        // Usuario nick=nickA
        // Paquete1:
        //      Apuesta Partido idCamp=idLiga id=1 Local 10
        //      Apuesta Partido idCamp=idLiga id=2 Local 15
        //      Apuesta Partido idCamp=idLiga id=3 Local 20
        // Paquete2:
        //      Apuesta Goleador idCamp=idLiga idJug=1  10
        //      Apuesta Campeon idCamp=idLiga id=1 20
        //      Apuesta Partido idCamp=idPartInd id=1 Local 30
        // Paquete3:
        //      Apuesta Goleador idCamp=idLiga idJug=1  10
        //      Apuesta Campeon idCamp=idLiga id=1 20
        //      Apuesta ResExacto idCamp=idPartInd id=1 1-1 30
        //      Apuesta ResExacto idCamp=idLiga id=2 3-3 40
        // PaqueteMemoriaTemporal:
        //      No existe

//        Copa id=20
//        partidos:
//            eq1 eq2 No fin-No div asig id=1
//            eq3 eq4 No fin-No div asig id=2

        // Asigno dividendos a un partido de copa y creo paquete
        int idCopa = 20;
        try {
            icc.asignarDividendos(1,idCopa,2,3,4);
            icc.almacenarAsigDividendoRes (0,0,100);
            icc.almacenarAsigDividendoRes (0,1,101);
            icc.almacenarAsigDividendoRes (0,2,102);
            icc.almacenarAsigDividendoRes (0,3,103);
            icc.asignarDividendoPartidoResultado (1,idCopa);

            icu.seleccionarCompeticion(idLiga);
            icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Goleador);
            icu.apostarGoleadorPaquete(4, 10);
            icu.agregarApuestaPaquete();

            dataPaqApu = icu.mostrarPaqueteApuestas();
            assertTrue(dataPaqApu.getApuestas().size()==1);

            icu.seleccionarCompeticion(idCopa);
            icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Goleador);
            icu.apostarGoleadorPaquete(7, 20);
            icu.agregarApuestaPaquete();

            dataPaqApu = icu.mostrarPaqueteApuestas();
            assertTrue(dataPaqApu.getApuestas().size()==2);

            icu.seleccionarCompeticion(idCopa);
            icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
            icu.apostarCampeonatoPaquete(4, 30);
            icu.agregarApuestaPaquete();

            dataPaqApu = icu.mostrarPaqueteApuestas();
            assertTrue(dataPaqApu.getApuestas().size()==3);

            icu.seleccionarCompeticion(idCopa);
            icu.seleccionarCampApuesta(TipoCampeonatoApuesta.Partido);
            icu.seleccionarPartidoCamp(1);
            icu.apostarPartidoPaquete(TipoDividendo.Local, 40);
            icu.agregarApuestaPaquete();

            dataPaqApu = icu.mostrarPaqueteApuestas();
            assertTrue(dataPaqApu.getApuestas().size()==4);

            icu.seleccionarCompeticion(idCopa);
            icu.seleccionarCampApuesta(TipoCampeonatoApuesta.ResExacto);
            icu.seleccionarPartidoCamp(1);
            icu.apostarResultadoExactoPaquete(0, 3, 50);
            icu.agregarApuestaPaquete();

            dataPaqApu = icu.mostrarPaqueteApuestas();
            assertTrue(dataPaqApu.getApuestas().size()==5);

            paqValido = icu.validarPaqueteApuestas();
            assertTrue(paqValido);
            icu.confirmarPaqueteApuestas();

        }
        catch (Exception exc) {
            System.out.println(exc.getMessage());
            assertTrue(false);
        }


        // confirmo que tengo 4 paquetes y que no hay ninguno activo
        listDataPaqAp = null;
        try{
            listDataPaqAp = icu.verPaqueteApuestas();
            assertTrue(listDataPaqAp.size()==4);

            listDataAp = listDataPaqAp.get(3).getApuestas();
            dataAp = listDataAp.get(0);
            assertTrue(dataAp instanceof DataApuestaGoleador);
            assertTrue(((DataApuestaGoleador)dataAp).getJugador().getId()==4);
            assertTrue(((DataApuestaGoleador)dataAp).getCompeticion().getId()==idLiga);
            assertTrue(((DataApuestaGoleador)dataAp).getMonto()==10);
            assertTrue((dataAp).getEstadoApuesta()==EstadoApuesta.Pendiente);
            dataAp = listDataAp.get(1);
            assertTrue(dataAp instanceof DataApuestaGoleador);
            assertTrue(((DataApuestaGoleador)dataAp).getJugador().getId()==7);
            assertTrue(((DataApuestaGoleador)dataAp).getCompeticion().getId()==idCopa);
            assertTrue(((DataApuestaGoleador)dataAp).getMonto()==20);
            assertTrue((dataAp).getEstadoApuesta()==EstadoApuesta.Pendiente);
            dataAp = listDataAp.get(2);
            assertTrue(dataAp instanceof DataApuestaCampeon);
            assertTrue(((DataApuestaCampeon)dataAp).getEquipo().getId()==4);
            assertTrue(((DataApuestaCampeon)dataAp).getCompeticion().getId()==idCopa);
            assertTrue(((DataApuestaCampeon)dataAp).getMonto()==30);
            assertTrue((dataAp).getEstadoApuesta()==EstadoApuesta.Pendiente);
            dataAp = listDataAp.get(3);
            assertTrue(dataAp instanceof DataApuestaPartido);
            assertTrue(((DataApuestaPartido)dataAp).getPartido().getDataInfoPart().getIdPar()==1);
            assertTrue(((DataApuestaPartido)dataAp).getTipoResultado()==TipoDividendo.Local);
            assertTrue(((DataApuestaPartido)dataAp).getMonto()==40);
            assertTrue((dataAp).getEstadoApuesta()==EstadoApuesta.Pendiente);
            dataAp = listDataAp.get(4);
            assertTrue(dataAp instanceof DataApuestaResExacto);
            assertTrue(((DataApuestaResExacto)dataAp).getPartido().getDataInfoPart().getIdPar()==1);
            assertTrue(((DataApuestaResExacto)dataAp).getGolesL()==0);
            assertTrue(((DataApuestaResExacto)dataAp).getGolesV()==3);
            assertTrue(((DataApuestaResExacto)dataAp).getMonto()==50);
            assertTrue((dataAp).getEstadoApuesta()==EstadoApuesta.Pendiente);

            dataPaqApu = icu.mostrarPaqueteApuestas();
            assertTrue(false);
        }
        catch(Exception e){
        }

        /* Competicion id=15 nombre=comp10
         * Liga
         * eq1,eq2,eq3
         * Partidos:
         *      eq1-eq2 No fin-Div Asig 1
         *      eq1-eq3 No fin-Div Asig 2
         *      eq2-eq1 No fin-Div Asig 3
         *      eq2-eq3 No fin-Div Asig 4
         *      eq3-eq1 No fin-No Div Asig 5
         *      eq3-eq2 No fin-No Div Asig 6
         */

        // Liga finalizada con idLiga nombre=Liga1 eq1 y eq2
        // Partidos:
        //      eq1 eq2 Fin-DivAsig
        //      eq2 eq1 No Fin-DivAsig

        // PartidoIndividual no finalizado eq1 y eq2
        // Partido:
        //      eq1 eq2 No Fin-DivAsig

        //        Copa id=20
        //        partidos:
        //            eq1 eq2 No fin-Div asig id=1
        //            eq3 eq4 No fin-No div asig id=2

        // Usuario nick=nickA saldo=630
        // Paquete1:
        //      Apuesta Partido idCamp=idLiga id=1 Local 10 Pendiente
        //      Apuesta Partido idCamp=idLiga id=2 Local 20 Pendiente
        //      Apuesta Partido idCamp=idLiga id=3 Local 30 Pendiente
        // Paquete2:
        //      Apuesta Goleador idCamp=idLiga idJug=1  10 Pendiente
        //      Apuesta Campeon idCamp=idLiga id=1 20 Pendiente
        //      Apuesta Partido idCamp=idPartInd id=1 Local 30 Pendiente
        // Paquete3:
        //      Apuesta Goleador idCamp=idLiga idJug=1  10 Pendiente
        //      Apuesta Campeon idCamp=idLiga id=1 20 Pendiente
        //      Apuesta ResExacto idCamp=idPartInd id=1 1-1 30 Pendiente
        //      Apuesta ResExacto idCamp=idLiga id=1 3-3 40 Pendiente
        // Paquete4:
        //      Apuesta Goleador idCamp=idLiga idJug=7  10 Pendiente
        //      Apuesta Goleador idCamp=idCopa idJug=7  20 Pendiente
        //      Apuesta Campeon idCamp=idCopa id=4 30 Pendiente
        //      Apuesta Partido idCamp=idCopa id=1 Local 40 Pendiente
        //      Apuesta ResExacto idCamp=idCopa id=1 0-3 50 Pendiente
        // PaqueteMemoriaTemporal:
        //      No existe

        // PROBAR APOSTAR Y VER COMO SE LLEVA EL PAQUETE DE APUESTAS

        // Termino el partido idCamp=idLiga id=1 y que gane Local
        int idLiga15 = 15;
        try{
            //System.out.println("################## "+icu.mostrarSaldoUsuario());
            assertTrue(icu.mostrarSaldoUsuario()==630);

            icc.almacenarAsigDividendoRes (0,0,2);
            icc.almacenarAsigDividendoRes (0,1,3);
            icc.almacenarAsigDividendoRes (1,0,4);
            icc.almacenarAsigDividendoRes (1,1,5);
            icc.almacenarAsigDividendoRes (1,0,4);
            icc.almacenarAsigDividendoRes (3,3,33);
            icc.asignarDividendoPartidoResultado (1,idLiga15);

            List<DataInfoPartido> listDataIP = icc.listarPartidosConDivAsignado(idLiga15);
            icc.seleccionarPartido(listDataIP.get(0));
            assertTrue(listDataIP.get(0).getIdPar()==1);
            icc.ingresarResultado(1,0,0,0);
            icc.ingresarJugador(1,1);
            icc.ingresarJugador(2,1);
            icc.ingresarJugador(3,2);
            icc.ingresarJugador(4,2);
            icc.listarIngresos();
            icc.confirmarIngreso(true);

            icc.almacenarAsigDividendoRes (0,0,2);
            icc.almacenarAsigDividendoRes (0,1,3);
            icc.almacenarAsigDividendoRes (1,0,4);
            icc.almacenarAsigDividendoRes (1,1,5);
            icc.almacenarAsigDividendoRes (1,0,4);
            icc.almacenarAsigDividendoRes (3,3,33);
            icc.asignarDividendoPartidoResultado (2,idLiga15);
            
            listDataIP = icc.listarPartidosConDivAsignado(idLiga15);
            icc.seleccionarPartido(listDataIP.get(0));
            assertTrue(listDataIP.get(0).getIdPar()==2);
            icc.ingresarResultado(0,0,0,0);
            icc.ingresarJugador(1,1);
            icc.ingresarJugador(2,1);
            icc.ingresarJugador(3,3);
            icc.ingresarJugador(4,3);
            icc.listarIngresos();
            icc.confirmarIngreso(true);

            icc.almacenarAsigDividendoRes (0,0,2);
            icc.almacenarAsigDividendoRes (0,1,3);
            icc.almacenarAsigDividendoRes (1,0,4);
            icc.almacenarAsigDividendoRes (1,1,5);
            icc.almacenarAsigDividendoRes (1,0,4);
            icc.almacenarAsigDividendoRes (3,3,33);
            icc.asignarDividendoPartidoResultado (3,idLiga15);

            listDataIP = icc.listarPartidosConDivAsignado(idLiga15);
            icc.seleccionarPartido(listDataIP.get(0));
            assertTrue(listDataIP.get(0).getIdPar()==3);
            icc.ingresarResultado(0,0,1,0);
            icc.ingresarJugador(1,2);
            icc.ingresarJugador(2,2);
            icc.ingresarJugador(3,1);
            icc.ingresarJugador(4,1);
            icc.listarIngresos();
            icc.confirmarIngreso(true);

            listDataIP = icc.listarPartidosConDivAsignado(idLiga);
            assertTrue(listDataIP.size()==1);
            icc.seleccionarPartido(listDataIP.get(0));
            assertTrue(listDataIP.get(0).getIdPar()==2);
            icc.ingresarResultado(100,0,1,0);
            icc.ingresarJugador(1,2);
            icc.ingresarJugador(2,2);
            icc.ingresarJugador(3,1);
            icc.ingresarJugador(4,1);
            icc.listarIngresos();
            icc.confirmarIngreso(true);
        }
        catch (Exception exc) {
            System.out.println(exc.getMessage());
            assertTrue(false);
        }

        // Veo que gano las apuestas terminadas
        try {
            listDataPaqAp = icu.verPaqueteApuestas();
            assertTrue(listDataPaqAp.size()==4);
            assertTrue(listDataPaqAp.get(0).getEstadoPaquete()==EstadoPaqueteApuestas.Ganado);
            assertTrue(listDataPaqAp.get(1).getEstadoPaquete()==EstadoPaqueteApuestas.Pendiente);
            assertTrue(listDataPaqAp.get(2).getEstadoPaquete()==EstadoPaqueteApuestas.Pendiente);
            assertTrue(listDataPaqAp.get(3).getEstadoPaquete()==EstadoPaqueteApuestas.Pendiente);

            // Paquete1
            assertTrue(listDataPaqAp.get(0).getMontoTotal()==990);
            assertTrue(listDataPaqAp.get(0).getApuestas().get(0) instanceof DataApuestaPartido);
            assertTrue(listDataPaqAp.get(0).getApuestas().get(0).getEstadoApuesta()==EstadoApuesta.Gano);
            assertTrue(listDataPaqAp.get(0).getApuestas().get(1) instanceof DataApuestaPartido);
            assertTrue(listDataPaqAp.get(0).getApuestas().get(1).getEstadoApuesta()==EstadoApuesta.Gano);
            assertTrue(listDataPaqAp.get(0).getApuestas().get(2) instanceof DataApuestaPartido);
            assertTrue(listDataPaqAp.get(0).getApuestas().get(2).getEstadoApuesta()==EstadoApuesta.Gano);
            
            // Paquete2
            assertTrue(listDataPaqAp.get(1).getApuestas().get(0) instanceof DataApuestaGoleador);
            assertTrue(listDataPaqAp.get(1).getApuestas().get(0).getEstadoApuesta()==EstadoApuesta.Gano);
            assertTrue(listDataPaqAp.get(1).getApuestas().get(1) instanceof DataApuestaCampeon);
            assertTrue(listDataPaqAp.get(1).getApuestas().get(1).getEstadoApuesta()==EstadoApuesta.Perdio);
            assertTrue(listDataPaqAp.get(1).getApuestas().get(2) instanceof DataApuestaPartido);
            assertTrue(listDataPaqAp.get(1).getApuestas().get(2).getEstadoApuesta()==EstadoApuesta.Pendiente);

            // Paquete3
            assertTrue(listDataPaqAp.get(2).getApuestas().get(0) instanceof DataApuestaGoleador);
            assertTrue(listDataPaqAp.get(2).getApuestas().get(0).getEstadoApuesta()==EstadoApuesta.Gano);
            assertTrue(listDataPaqAp.get(2).getApuestas().get(1) instanceof DataApuestaCampeon);
            assertTrue(listDataPaqAp.get(2).getApuestas().get(1).getEstadoApuesta()==EstadoApuesta.Perdio);
            assertTrue(listDataPaqAp.get(2).getApuestas().get(2) instanceof DataApuestaResExacto);
            assertTrue(listDataPaqAp.get(2).getApuestas().get(2).getEstadoApuesta()==EstadoApuesta.Pendiente);
            assertTrue(listDataPaqAp.get(2).getApuestas().get(3) instanceof DataApuestaResExacto);
            assertTrue(listDataPaqAp.get(2).getApuestas().get(3).getEstadoApuesta()==EstadoApuesta.Perdio);

            // Paquete4
            assertTrue(listDataPaqAp.get(3).getApuestas().get(0) instanceof DataApuestaGoleador);
            assertTrue(listDataPaqAp.get(3).getApuestas().get(0).getEstadoApuesta()==EstadoApuesta.Gano);
            assertTrue(listDataPaqAp.get(3).getApuestas().get(1) instanceof DataApuestaGoleador);
            assertTrue(listDataPaqAp.get(3).getApuestas().get(1).getEstadoApuesta()==EstadoApuesta.Pendiente);
            assertTrue(listDataPaqAp.get(3).getApuestas().get(2) instanceof DataApuestaCampeon);
            assertTrue(listDataPaqAp.get(3).getApuestas().get(2).getEstadoApuesta()==EstadoApuesta.Pendiente);
            assertTrue(listDataPaqAp.get(3).getApuestas().get(3) instanceof DataApuestaPartido);
            assertTrue(listDataPaqAp.get(3).getApuestas().get(3).getEstadoApuesta()==EstadoApuesta.Pendiente);
            assertTrue(listDataPaqAp.get(3).getApuestas().get(4) instanceof DataApuestaResExacto);
            assertTrue(listDataPaqAp.get(3).getApuestas().get(4).getEstadoApuesta()==EstadoApuesta.Pendiente);
            
            assertTrue(icu.mostrarSaldoUsuario()==1789.5);//630+990+((990*5)/100)+20+20+80=1789,5
            //System.out.println("#############################################"+listDataPaqAp.get(2).getApuestas().get(0).getBeneficio());
        }
        catch(Exception exc){
            System.out.println(exc.getMessage());
        }
        
        /* Competicion id=15 nombre=comp10
         * Liga
         * eq1,eq2,eq3
         * Partidos:
         *      eq1-eq2 Fin-Div Asig 1 1-0
         *      eq1-eq3 Fin-Div Asig 2 0-0
         *      eq2-eq1 Fin-Div Asig 3 0-1
         *      eq2-eq3 No fin-Div Asig 4
         *      eq3-eq1 No fin-No Div Asig 5
         *      eq3-eq2 No fin-No Div Asig 6
         */

        // Liga finalizada con idLiga nombre=Liga1 eq1 y eq2
        // Partidos:
        //      eq1 eq2 Fin-DivAsig 2-1
        //      eq2 eq1 Fin-DivAsig 100-1
        // Goleadores:
        //      idJug=1,idJug=2,idJug=3,idJug=4

        // PartidoIndividual no finalizado eq1 y eq2
        // Partido:
        //      eq1 eq2 No Fin-DivAsig

        //        Copa id=20
        //        partidos:
        //            eq1 eq2 No fin-Div asig id=1
        //            eq3 eq4 No fin-No div asig id=2


        // Usuario nick=nickA saldo=3240+10*53+20*2+30*14+beneficioApuesta=4230+(990*5/100)=4279,5
        // Paquete1: Ganado
        //      Apuesta Partido idCamp=idLiga2 id=1 Local 10 Ganada
        //      Apuesta Partido idCamp=idLiga2 id=2 Empate 20 Ganada
        //      Apuesta Partido idCamp=idLiga2 id=3 Visitante 30 Ganada
        // Paquete2: Pendiente
        //      Apuesta Goleador idCamp=idLiga idJug=1  10 Gano
        //      Apuesta Campeon idCamp=idLiga id=1 20 Perdio
        //      Apuesta Partido idCamp=idPartInd id=1 Local 30 Pendiente
        // Paquete3: Pendiente
        //      Apuesta Goleador idCamp=idLiga idJug=1  10 Gano
        //      Apuesta Campeon idCamp=idLiga id=1 20 Perdio
        //      Apuesta ResExacto idCamp=idPartInd id=1 1-1 30 Pendiente
        //      Apuesta ResExacto idCamp=idLiga id=2 3-3 40 Perdio
        // Paquete4: Pendiente
        //      Apuesta Goleador idCamp=idLiga idJug=4  10 Gano
        //      Apuesta Goleador idCamp=idCopa idJug=7  20 Pendiente
        //      Apuesta Campeon idCamp=idCopa id=4 30 Pendiente
        //      Apuesta Partido idCamp=idCopa id=1 Local 40 Pendiente
        //      Apuesta ResExacto idCamp=idCopa id=1 0-3 50 Pendiente
        // PaqueteMemoriaTemporal:
        //      No existe

        try{
            List<DataInfoPartido> listDataIP = icc.listarPartidosConDivAsignado(idPartInd);
            icc.seleccionarPartido(listDataIP.get(0));
            assertTrue(listDataIP.get(0).getIdPar()==1);
            icc.ingresarResultado(1,0,0,0);
            icc.ingresarJugador(1,1);
            icc.ingresarJugador(2,1);
            icc.ingresarJugador(3,2);
            icc.ingresarJugador(4,2);
            icc.listarIngresos();
            icc.confirmarIngreso(true);

            //        Copa id=20
            //        partidos:
            //            eq1 eq2 No fin-Div asig id=1
            //            eq3 eq4 No fin-No div asig id=2

            //        Copa id=20 campeon eq4
            //        partidos:
            //            eq1 eq2 Fin-Div asig id=1
            //            eq3 eq4 Fin-Div asig id=2
            //            eq2 eq4 final Fin-DivAsig id=3

            icc.asignarDividendos(2,idCopa,2,3,4);

            icc.ingresarDatosLlaveDeCopa(idCopa,"final");
            icc.ingresarLlavesPredecesoras("cuartos1","cuartos2");
            icc.ingresarTipoLlave(false,true);
            icc.confirmarAltaLlaveCopa(new DataFechaHora(1,1,1,1,1),"lugCopa");

            listDataIP = icc.listarPartidosConDivAsignado(idCopa);
            assertTrue(listDataIP.size()==2);
            icc.seleccionarPartido(listDataIP.get(1));
            assertTrue(listDataIP.get(1).getIdPar()==1);
            icc.ingresarResultado(0,0,3,0);
            icc.ingresarJugador(1,1);
            icc.ingresarJugador(2,1);
            icc.ingresarJugador(3,2);
            icc.ingresarJugador(4,2);
            icc.listarIngresos();
            icc.confirmarIngreso(true);

            listDataIP = icc.listarPartidosConDivAsignado(idCopa);
            icc.seleccionarPartido(listDataIP.get(0));
            assertTrue(listDataIP.get(0).getIdPar()==2);
            icc.ingresarResultado(0,1,0,3);
            icc.ingresarJugador(1,3);
            icc.ingresarJugador(2,3);
            icc.ingresarJugador(3,4);
            icc.ingresarJugador(4,4);
            icc.listarIngresos();
            icc.confirmarIngreso(true);

            icc.asignarDividendos(3,idCopa,2,3,4);

            listDataIP = icc.listarPartidosConDivAsignado(idCopa);
            icc.seleccionarPartido(listDataIP.get(0));
            assertTrue(listDataIP.get(0).getIdPar()==3);
            icc.ingresarResultado(1,0,3,0);
            icc.ingresarJugador(1,1);
            icc.ingresarJugador(2,1);
            icc.ingresarJugador(3,4);
            icc.ingresarJugador(4,4);
            icc.listarIngresos();
            icc.confirmarIngreso(true);
        }
        catch (Exception exc) {
            System.out.println(exc.getMessage());
            assertTrue(false);
        }

        /* Competicion id=15 nombre=comp10
         * Liga
         * eq1,eq2,eq3
         * Partidos:
         *      eq1-eq2 Fin-Div Asig 1 1-0
         *      eq1-eq3 Fin-Div Asig 2 0-0
         *      eq2-eq1 Fin-Div Asig 3 0-1
         *      eq2-eq3 No fin-Div Asig 4
         *      eq3-eq1 No fin-No Div Asig 5
         *      eq3-eq2 No fin-No Div Asig 6
         */


        //        Copa id=20 campeon eq4
        //        partidos:
        //            eq1 eq2 Fin-Div asig id=1
        //            eq3 eq4 Fin-Div asig id=2
        //            eq1 eq4 final Fin-DivAsig id=3

        // Liga finalizada con idLiga nombre=Liga1 eq1 y eq2
        // Partidos:
        //      eq1 eq2 Fin-DivAsig 2-1
        //      eq2 eq1 Fin-DivAsig 100-1
        // Goleadores:
        //      idJug=1,idJug=2,idJug=3,idJug=4

        // PartidoIndividual Finalizado eq1 y eq2
        // Partido:
        //      eq1 eq2 Fin-DivAsig 1-0

        //        Copa id=20
        //        partidos:
        //            eq1 eq2 No fin-Div asig id=1
        //            eq3 eq4 No fin-No div asig id=2


        // Usuario nick=nickA saldo=3240+10*53+20*2+30*14+beneficioApuesta=4230+(990*5/100)=4279,5
        // Paquete1: Ganado
        //      Apuesta Partido idCamp=idLiga2 id=1 Local 10 Ganada
        //      Apuesta Partido idCamp=idLiga2 id=2 Empate 20 Ganada
        //      Apuesta Partido idCamp=idLiga2 id=3 Visitante 30 Ganada
        // Paquete2: Perdido
        //      Apuesta Goleador idCamp=idLiga idJug=1  10 Gano
        //      Apuesta Campeon idCamp=idLiga id=1 20 Perdio
        //      Apuesta Partido idCamp=idPartInd id=1 Local 30 Gano
        // Paquete3:Perdido
        //      Apuesta Goleador idCamp=idLiga idJug=1  10 Gano
        //      Apuesta Campeon idCamp=idLiga id=1 20 Perdio
        //      Apuesta ResExacto idCamp=idPartInd id=1 1-1 30 Perdio
        //      Apuesta ResExacto idCamp=idLiga id=2 3-3 40 Perdio
        // Paquete4:Perdido
        //      Apuesta Goleador idCamp=idLiga idJug=4  10 Gano
        //      Apuesta Goleador idCamp=idCopa idJug=7  20 Gano div=7
        //      Apuesta Campeon idCamp=idCopa id=4 30 Gano div=4
        //      Apuesta Partido idCamp=idCopa id=1 Local 40 Perdio
        //      Apuesta ResExacto idCamp=idCopa id=1 0-3 50 Gano
        // PaqueteMemoriaTemporal:
        //      No existe

        // Veo que gano las apuestas terminadas
        try {
            
            List<DataApuesta> listDataDetApu = icu.mostrarHistorialApuestas(new DataFechaHora(1,1,1600,5,1), new DataFechaHora(1,1,2020,5,1));

            System.out.println("######################## "+listDataDetApu.size());
            assertTrue(listDataDetApu.size()==15);

            listDataPaqAp = icu.verPaqueteApuestas();
            assertTrue(listDataPaqAp.size()==4);
            assertTrue(listDataPaqAp.get(0).getEstadoPaquete()==EstadoPaqueteApuestas.Ganado);
            assertTrue(listDataPaqAp.get(1).getEstadoPaquete()==EstadoPaqueteApuestas.Perdido);
            assertTrue(listDataPaqAp.get(2).getEstadoPaquete()==EstadoPaqueteApuestas.Perdido);
            assertTrue(listDataPaqAp.get(3).getEstadoPaquete()==EstadoPaqueteApuestas.Perdido);

            // Paquete1
            assertTrue(listDataPaqAp.get(0).getMontoTotal()==990);
            assertTrue(listDataPaqAp.get(0).getApuestas().size()==3);
            assertTrue(listDataPaqAp.get(0).getApuestas().get(0) instanceof DataApuestaPartido);
            assertTrue(listDataPaqAp.get(0).getApuestas().get(0).getEstadoApuesta()==EstadoApuesta.Gano);
            assertTrue(listDataPaqAp.get(0).getApuestas().get(1) instanceof DataApuestaPartido);
            assertTrue(listDataPaqAp.get(0).getApuestas().get(1).getEstadoApuesta()==EstadoApuesta.Gano);
            assertTrue(listDataPaqAp.get(0).getApuestas().get(2) instanceof DataApuestaPartido);
            assertTrue(listDataPaqAp.get(0).getApuestas().get(2).getEstadoApuesta()==EstadoApuesta.Gano);

            // Paquete2
            assertTrue(listDataPaqAp.get(1).getApuestas().size()==3);
            assertTrue(listDataPaqAp.get(1).getApuestas().get(0) instanceof DataApuestaGoleador);
            assertTrue(listDataPaqAp.get(1).getApuestas().get(0).getEstadoApuesta()==EstadoApuesta.Gano);
            assertTrue(listDataPaqAp.get(1).getApuestas().get(1) instanceof DataApuestaCampeon);
            assertTrue(listDataPaqAp.get(1).getApuestas().get(1).getEstadoApuesta()==EstadoApuesta.Perdio);
            assertTrue(listDataPaqAp.get(1).getApuestas().get(2) instanceof DataApuestaPartido);
            assertTrue(listDataPaqAp.get(1).getApuestas().get(2).getEstadoApuesta()==EstadoApuesta.Gano);

            // Paquete3
            assertTrue(listDataPaqAp.get(2).getApuestas().size()==4);
            assertTrue(listDataPaqAp.get(2).getApuestas().get(0) instanceof DataApuestaGoleador);
            assertTrue(listDataPaqAp.get(2).getApuestas().get(0).getEstadoApuesta()==EstadoApuesta.Gano);
            assertTrue(listDataPaqAp.get(2).getApuestas().get(1) instanceof DataApuestaCampeon);
            assertTrue(listDataPaqAp.get(2).getApuestas().get(1).getEstadoApuesta()==EstadoApuesta.Perdio);
            assertTrue(listDataPaqAp.get(2).getApuestas().get(2) instanceof DataApuestaResExacto);
            assertTrue(listDataPaqAp.get(2).getApuestas().get(2).getEstadoApuesta()==EstadoApuesta.Perdio);
            assertTrue(listDataPaqAp.get(2).getApuestas().get(3) instanceof DataApuestaResExacto);
            assertTrue(listDataPaqAp.get(2).getApuestas().get(3).getEstadoApuesta()==EstadoApuesta.Perdio);

            // Paquete4
            assertTrue(listDataPaqAp.get(3).getApuestas().size()==5);
            assertTrue(listDataPaqAp.get(3).getApuestas().get(0) instanceof DataApuestaGoleador);
            assertTrue(listDataPaqAp.get(3).getApuestas().get(0).getEstadoApuesta()==EstadoApuesta.Gano);
            assertTrue(listDataPaqAp.get(3).getApuestas().get(1) instanceof DataApuestaGoleador);
            assertTrue(listDataPaqAp.get(3).getApuestas().get(1).getEstadoApuesta()==EstadoApuesta.Gano);
            assertTrue(listDataPaqAp.get(3).getApuestas().get(2) instanceof DataApuestaCampeon);
            assertTrue(listDataPaqAp.get(3).getApuestas().get(2).getEstadoApuesta()==EstadoApuesta.Gano);
            assertTrue(listDataPaqAp.get(3).getApuestas().get(3) instanceof DataApuestaPartido);
            assertTrue(listDataPaqAp.get(3).getApuestas().get(3).getEstadoApuesta()==EstadoApuesta.Perdio);
            assertTrue(listDataPaqAp.get(3).getApuestas().get(4) instanceof DataApuestaResExacto);
            assertTrue(listDataPaqAp.get(3).getApuestas().get(4).getEstadoApuesta()==EstadoApuesta.Gano);
            
            
            assertTrue(icu.mostrarSaldoUsuario()==7259.5);//630+ (990+((990*5)/100))+  (80)+  (20)+ (80+140+120+5150)=7259,5
            
        }
        catch(Exception exc){
            System.out.println(exc.getMessage());
        }

        System.out.println("FIN");
    }

        // TESTS PENCAAAAAAA
    @org.junit.Test
    public void testParticiparEnPenca(){
        /// creo una liga y una copa
        IControladorCompeticiones icc= (new Fabrica()).getIControladorCompeticiones();
       
        int idLiga=0;
        int idCopa=0;
        try{
             //alta liga
             icc.ingresarCompeticion("comp3",TipoCompeticion.Liga,"");
            icc.seleccionarEquipo(1);
            icc.seleccionarEquipo(2);
            TipoCriterio[] dc = {TipoCriterio.GolesFavor,TipoCriterio.DiferenciaGoles,TipoCriterio.Resultado};
            icc.ingresarOrdenLiga(dc);
            icc.ingresarDividendoEquipo(1,2);
            icc.ingresarDividendoEquipo(2,4);
            icc.setMontoPenca(200);
            //id24
          idLiga=icc.darDeAltaCompetencia();
             
            icc.finalizar();


            //alta copa
              icc.ingresarCompeticion("comp", TipoCompeticion.Copa, "");

                    icc.seleccionarEquipo(1);
                    icc.seleccionarEquipo(2);
                    icc.seleccionarEquipo(3);
                    icc.seleccionarEquipo(4);

                    icc.ingresarDividendoEquipo(1,11);
                    icc.ingresarDividendoEquipo(2,4);
                    icc.ingresarDividendoEquipo(3,3*3);
                    icc.ingresarDividendoEquipo(4,(7+3*7)%13+2);
                    icc.setMontoPenca(200);
                    //id25
                   idCopa= icc.darDeAltaCompetencia();
                    icc.finalizar();
                    
            
        }
        catch (Exception e){
            assertTrue(false);
        }
           



       
        IControladorUsuarios icu= (new Fabrica()).getIControladorUsuarios();
        boolean ok;
        //usuario no logueado
        try{
          ok = icu.participarEnPenca(idLiga);
          fail();
        }
        catch (Exception e ){
            assertTrue(true);
        }

        // no existe competicion
        try{
          ok = icu.participarEnPenca(99);
          fail();
        }
        catch (Excepciones.ExNoExisteCompeticion e ){
            assertTrue(true);
        }
        catch (Excepciones.ExCampeonatoIncorrecto e){
            assertTrue(false);
        }
        catch(Exception e){
            assertTrue(false);
        }

        // competicion incorrecta
        try{
          ok = icu.participarEnPenca(8);
          fail();
        }

        catch (Excepciones.ExNoExisteCompeticion e ){
            assertTrue(false);
        }
        catch (Excepciones.ExCampeonatoIncorrecto e){
            assertTrue(true);
        }
        catch (Exception e ){
            assertTrue(false);
        }

        //inicio sesion usuario
         icu.iniciarSesion("nickA", "passA");
         try{
           icu.ingresarSaldoUsuario(5000);
        }
         catch (Exception e){
             assertTrue(false);
         }


        // confirma participacion
         try{

           ok = icu.participarEnPenca(idLiga);
           icu.confirmarParticipacion(true);
           assertTrue(ok);
           
         }
        catch (Exception e ){
            assertTrue(false);
        }
       

         //usuario ya participa de Penca
         try{
           ok = icu.participarEnPenca(idLiga);
           assertFalse(ok);
           icu.confirmarParticipacion(ok);
         }
         catch (Exception e){
             assertTrue(false);
         }

        try{
         icu.cerrarSesion();
        }
         catch (Exception e){
             assertTrue(false);
         }

         icu.iniciarSesion("nick2", "pass2");

         //no le da el saldo
         try{
              ok = icu.participarEnPenca(idLiga);
              assertFalse(ok);
              icu.confirmarParticipacion(ok);
         }
         catch(Exception e){
             assertTrue(false);
         }

    }

    @org.junit.Test
    public void testConfirmarParticipacion (){
       IControladorUsuarios icu= (new Fabrica()).getIControladorUsuarios();
       boolean ok=false;
       icu.iniciarSesion("nickB","passB");
       try{
           ok= icu.participarEnPenca(21);
       }
       catch(Exception e){
          assertTrue(false);
       }
       try{
         icu.cerrarSesion();
        }
       catch(Exception e){
           assertTrue(false);
       }

       //usuario no logueado
      try{
         icu.confirmarParticipacion(ok);
         fail();
        }
        catch(Exception e){
           assertTrue(true);
       }
      icu.iniciarSesion("nickA","passA");
       // datos no asignados
       try{
           icu.confirmarParticipacion(true);
       }
       catch(Excepciones.ExDatosNoAsignados e){
           assertTrue(true);
       }
       catch(Exception e){
          assertTrue(false);
       }
      try{
        ok=icu.participarEnPenca(25);
        icu.confirmarParticipacion(ok);
        
        }
       catch(Exception e){
           assertTrue(false);
       }

      try {
          icu.cerrarSesion();
      }
      catch (Exception e){
          assertTrue(false);
      }

       icu.iniciarSesion("nickA","passA");

       try{
        ok=icu.participarEnPenca(25);
        assertFalse(ok);
        }
       catch(Exception e){
           assertTrue(false);
       }
       try {
          icu.cerrarSesion();
      }
      catch (Exception e){
          assertTrue(false);
      }
    }

    @org.junit.Test
    public void obtenerPartidosNoFinalizadosPenca(){
         IControladorUsuarios icu= (new Fabrica()).getIControladorUsuarios();
         icu.iniciarSesion("nickA","passA");
         try{
           List<DataInfoPartido> l = icu.obtenerPartidosNoFinalizadosPenca(30);
           fail();
        }
        catch(Excepciones.ExNoExisteCompeticion e){
            assertTrue(true);
        }
         catch (Exception e){
             assertTrue(false);
         }

         try{
           List<DataInfoPartido> l = icu.obtenerPartidosNoFinalizadosPenca(1);
           fail();
        }
        catch(Excepciones.ExCampeonatoIncorrecto e){
            assertTrue(true);
        }
         catch (Exception e){
             assertTrue(false);
         }
         try{
           icu.cerrarSesion();
         }
         catch(Exception e){
             assertTrue(false);
         }

         try{
           List<DataInfoPartido> l = icu.obtenerPartidosNoFinalizadosPenca(1);
           fail();
        }
        catch(Exception e){
            assertTrue(true);
        }

         icu.iniciarSesion("nickB","passB");

        try{
             icu.obtenerPartidosNoFinalizadosPenca(24);
         }
         catch(Excepciones.ExUsuarioNoParticipaEnPenca e){
             assertTrue(true);
         }
         catch (Exception e){
             assertTrue(false);
         }
         try{
             icu.ingresarSaldoUsuario(2000);
              boolean ok=icu.participarEnPenca(24);
              icu.confirmarParticipacion(ok);
              assertTrue(ok);
        }
         catch(Exception e){
             assertTrue(false);
         }

         // Agregamos partidos a la liga///
       DataFechaHora dt1 = new DataFechaHora(10,2,2011,0,0,0);
         DataFechaHora dt2 = new DataFechaHora(13,2,2011,0,0,0);


         IControladorCompeticiones icc= (new Fabrica()).getIControladorCompeticiones();
        try{
              icc.ingresarIdLiga(24);
              icc.seleccionarEncuentroADefinir (1,2,dt1,"Melo");
              icc.ingresarIdLiga(24);
              icc.seleccionarEncuentroADefinir (2,1,dt2,"Melo");
        }


         catch(Exception e){
             assertTrue(false);
         }



       try{
            List<DataInfoPartido> l =icu.obtenerPartidosNoFinalizadosPenca(24);
           
                assertTrue(l.get(0).getIdLocal()==1);
                assertTrue(l.get(0).getIdVisita()==2);
                assertTrue(l.get(0).getIdPar()==1);

            
                assertTrue(l.get(1).getIdLocal()==2);
                assertTrue(l.get(1).getIdVisita()==1);
                 icu.apostarPartidoPenca(1,0, 0);
                 icu.cerrarSesion();

          }
         catch (Exception e){
             assertTrue(false);
        }

    }

    @org.junit.Test
    public void apostarPartidoPenca(){
        IControladorUsuarios icu=(new Fabrica()).getIControladorUsuarios();
        icu.iniciarSesion("nickB", "passB");
        
        // datos no asignados
        try {
            icu.apostarPartidoPenca(2, 1, 3);
            fail();
        }
        catch (ExDatosNoAsignados e){
            assertTrue(true);
        }
        catch(Exception e){
            assertTrue(false);
        }

        try{
            List<DataInfoPartido> l = icu.obtenerPartidosNoFinalizadosPenca(24);
            icu.cerrarSesion();
        }
        catch(Exception e){
            assertTrue(false);
        }
        // usuario no logueado
       try {
            icu.apostarPartidoPenca(2, 1, 3);
            fail();
        }
        catch(Exception e){
            assertTrue(true);
        }

        icu.iniciarSesion("nickB", "passB");
        //ya aposto partido//
        try{
          icu.apostarPartidoPenca(1, 3, 0);
        }
        catch (Excepciones.ExUsuarioYaApostoPartido e){
            assertTrue(true);
        }
        catch(Exception e){
            assertTrue(false);
        }

        try{
            List<DataInfoPartido> l = icu.obtenerPartidosNoFinalizadosPenca(24);
            icu.apostarPartidoPenca(2,1,3);
            icu.cerrarSesion();
        }
        catch(Exception e){
            assertTrue(false);
        }
        
        
        icu.iniciarSesion("nickA", "passA");
        
        try{
            List<DataInfoPartido> l = icu.obtenerPartidosNoFinalizadosPenca(24);
            icu.apostarPartidoPenca(1,1,0);
             l = icu.obtenerPartidosNoFinalizadosPenca(24);
            icu.apostarPartidoPenca(2,1,3);
            icu.cerrarSesion();
            
        }
        catch(Exception e){
            assertTrue(false);
        }


        

    }
     @org.junit.Test
    public void verTablaPosicionesPenca(){
         IControladorCompeticiones icc= (new Fabrica()).getIControladorCompeticiones();
         IControladorUsuarios icu= (new Fabrica()).getIControladorUsuarios();

         try{
           icc.asignarDividendos(1,24, (float)1.2,(float) 1.6,(float) 3.8);
           icc.asignarDividendos(2,24, (float)1.7, (float)3.7, (float)4.9);
         }
        catch(Exception e){
            assertTrue(false);
        } 


     try{

         //termino partido 1 de liga 1 - 0
        List<DataInfoPartido> l =icc.listarPartidosConDivAsignado(24);
        icc.seleccionarPartido((DataInfoPartido)l.get(0));
        icc.ingresarResultado(1, 0, 0, 0);
        icc.ingresarJugador(1, 1);
        icc.ingresarJugador(2, 2);
        icc.listarIngresos();
        icc.confirmarIngreso(true);


        //termino partido 2 de liga
         l =icc.listarPartidosConDivAsignado(24);
        icc.seleccionarPartido((DataInfoPartido)l.get(0));
        icc.ingresarResultado(2, 0, 3, 0);
        icc.ingresarJugador(1, 1);
        icc.ingresarJugador(2, 2);
        icc.listarIngresos();
        icc.confirmarIngreso(true);
       }

     catch(Exception e){
         assertTrue(false);
     }
       icu.iniciarSesion("nickB", "passB");

       try{
            DataInfoPenca info =icu.verTablaPosicionesPenca(24);
            assertTrue(info.getMontoMin()==200);
            assertTrue(info.getParticipaUsuario()==true);
            assertTrue(info.getTablaPosiciones().get(0).getNick().equals("nickA"));
            assertTrue(info.getTablaPosiciones().get(0).getPuntos()==13);
            System.out.println("montooooooooo"+Float.toString(info.getPozo()));
            assertTrue(info.getPozo()==380);

       }

         catch(Exception e){
         assertTrue(false);
     }
       try{
            icu.cerrarSesion();
         }
       catch(Exception e){
            assertTrue(false);
       };


       icu.iniciarSesion("nickA", "passA");
       DataUsuario dtu = icu.obtenerUsuarioLogueado();
       System.out.println("Saldoooooooooo"+Float.toString(dtu.getSaldo()));
       assertTrue(dtu.getSaldo()==8710);
       DataFechaHora dtf=new DataFechaHora(12,12,2011,0,0,0);
       try{
           //partido1
       icc.ingresarDatosLlaveDeCopa(25, "par1");
       icc.listarEquiposNoAsignadosCopa();
       icc.ingresarDatosPartida(1,2);
       icc.ingresarTipoLlave(true, false);
       icc.confirmarAltaLlaveCopa(dtf, "Estadio");
       icc.limpiarMemoriaAuxiliar();
       //partido2
       icc.ingresarDatosLlaveDeCopa(25, "par2");
       icc.listarEquiposNoAsignadosCopa();
       icc.ingresarDatosPartida(3,4);
       icc.ingresarTipoLlave(true, false);
       icc.confirmarAltaLlaveCopa(dtf, "Estadio");
       icc.limpiarMemoriaAuxiliar();

       //final
       icc.ingresarDatosLlaveDeCopa(25, "Final");
       icc.listarEquiposNoAsignadosCopa();
       icc.ingresarLlavesPredecesoras("par1", "par2");
       icc.ingresarTipoLlave(false, true);
       icc.confirmarAltaLlaveCopa(dtf, "Estadio");
       icc.limpiarMemoriaAuxiliar();

       //asignamos dividendos
       icc.asignarDividendos(1, 25,(float)1.35,(float)2.5, (float)3.9);
       icc.asignarDividendos(2, 25,(float)2.35,(float)2.7, (float)4.9);
       icc.asignarDividendos(3, 25,(float)1.7,(float)2.4, (float)13.9);

       //nickA
       System.out.println("Saldo nickA"+Float.toString(icu.mostrarSaldoUsuario()));
       icu.obtenerPartidosNoFinalizadosPenca(25);
       icu.apostarPartidoPenca(1,2,0);

       icu.obtenerPartidosNoFinalizadosPenca(25);
       icu.apostarPartidoPenca(3, 2, 2);

       try{
           icu.cerrarSesion();
       }
       catch(Exception e){}

       icu.iniciarSesion("nickB", "passB");
       
       boolean ok=icu.participarEnPenca(25);
       assertTrue(ok);
       icu.confirmarParticipacion(ok);
       System.out.println("Saldo nickB"+Float.toString(icu.mostrarSaldoUsuario()));
       icu.obtenerPartidosNoFinalizadosPenca(25);
       icu.apostarPartidoPenca(1, 3, 0);

       icu.obtenerPartidosNoFinalizadosPenca(25);
       icu.apostarPartidoPenca(2, 2, 1);

       //finalizar partido

        List<DataInfoPartido> l =icc.listarPartidosConDivAsignado(25);
        icc.seleccionarPartido((DataInfoPartido)l.get(2));
        icc.ingresarResultado(1, 0, 0, 0);
        icc.ingresarJugador(1, 1);
        icc.ingresarJugador(2, 2);
        icc.listarIngresos();
        icc.confirmarIngreso(true);

        l =icc.listarPartidosConDivAsignado(25);
        icc.seleccionarPartido((DataInfoPartido)l.get(1));
        icc.ingresarResultado(2, 0, 0, 0);
        icc.ingresarJugador(3, 3);
        icc.ingresarJugador(1, 4);
        icc.listarIngresos();
        icc.confirmarIngreso(true);

        l =icc.listarPartidosConDivAsignado(25);
       icc.seleccionarPartido((DataInfoPartido)l.get(0));
        icc.ingresarResultado(3, 5, 3, 4);
        icc.ingresarJugador(2, 3);
        icc.ingresarJugador(1, 1);
        icc.listarIngresos();
        icc.confirmarIngreso(true);

        DataInfoPenca dtip= icu.verTablaPosicionesPenca(25);
        System.out.println("Pozo total"+Float.toString(dtip.getPozo()));
        assertTrue(dtip.getTablaPosiciones().get(0).getPuntos()==6);
         //ganaron los 2 divido el pozo
        assertTrue(icu.mostrarSaldoUsuario()==1600+190);
 }
       catch (Exception e){
           assertTrue(false);
       }

       try {
            Fabrica f = new Fabrica();
            IControladorDatos icd = f.getIControladorDatos();
            icd.guardar("testUsu.xls");
            icd.cargar("testUsu.xls");
            icd.guardar("testUsu2.xls");
        }
        catch (Exception exc){
            assertTrue(false);
        }

    }

}

