package Formularios;//GEN-FIRST:event_jMenuItem20ActionPerformed
//GEN-LAST:event_jMenuItem20ActionPerformed

import Interface.*;
import DataTypes.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import org.jfree.ui.RefineryUtilities;
import org.jasypt.util.password.BasicPasswordEncryptor;

import javax.swing.*; 
public class Tarea1 extends javax.swing.JFrame {

    public Tarea1() {
        initComponents();
        Fondo f = new Fondo();
        this.add(f);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);//para iniciar el jframe maximizado
        this.pack();
    }

//tarea 1
public void cargarDatos()throws Exception{
        Fabrica f = new Fabrica();
        IControladorEquipos ice = f.getIControladorEquipos();
        IControladorCompeticiones icc = f.getIControladorCompeticiones();
        IControladorJugadores icj = f.getIControladorJugadores();
        IControladorFecha icf= f.getIControladorFecha();
        DataFechaHora fechaHora;
        TipoPosicion tipoPos;
        DataJugador dataJ;
        //fecha inicial

        icf.setFecha(new DataFechaHora(1,9,2010,15,0));


        fechaHora = new DataFechaHora(3,12,1981,0,0);
        tipoPos = TipoPosicion.Delantero;
        dataJ = new DataJugador(1,"Iron Man","Tony Stark",tipoPos,fechaHora,0,"USA",1.70,80,"images/1.jpeg");
        int id1 =icj.ingresarJugador(dataJ);
        fechaHora = new DataFechaHora(22,8,1961,0,0);
        tipoPos = TipoPosicion.Defensa;
        dataJ = new DataJugador(2,"Nick Fury","Colonel Nick Fury",tipoPos,fechaHora,0,"USA",1.80,80,"images/2.jpeg");
        int id2 =icj.ingresarJugador(dataJ);

        fechaHora = new DataFechaHora(15,3,1980,0,0);
        tipoPos = TipoPosicion.Delantero;
        dataJ = new DataJugador(3,"The Hulk","Bruce Banner",tipoPos,fechaHora,0,"USA",2.06,120,"images/3.jpeg");
        int id3=icj.ingresarJugador(dataJ);

        fechaHora = new DataFechaHora(12,10,1963,0,0);
        tipoPos = TipoPosicion.Defensa;
        dataJ = new DataJugador(4,"Panthro","Panthro",tipoPos,fechaHora,0,"Thundera",2.00,100,"images/4.jpeg");
        int id4=icj.ingresarJugador(dataJ);

        fechaHora = new DataFechaHora(03,11,1980,0,0);
        tipoPos = TipoPosicion.Delantero;
        dataJ = new DataJugador(5,"Wolverine","Logan",tipoPos,fechaHora,0,"USA",1.80,80,"images/5.jpeg");
        int id5=icj.ingresarJugador(dataJ);

        fechaHora = new DataFechaHora(11,9,1984,0,0);
        tipoPos = TipoPosicion.Defensa;
        dataJ = new DataJugador(6,"Silk Spectre","Laurie Juspeczyk",tipoPos,fechaHora,0,"USA",1.76,52,"images/6.jpeg");
        int id6=icj.ingresarJugador(dataJ);

        fechaHora = new DataFechaHora(21,3,1977,0,0);
        tipoPos = TipoPosicion.Defensa;
        dataJ = new DataJugador(7,"Batman","Bruce Wayne",tipoPos,fechaHora,0,"USA",1.78,80,"images/7.jpeg");
        int id7=icj.ingresarJugador(dataJ);

        fechaHora = new DataFechaHora(10,3,1980,0,0);
        tipoPos = TipoPosicion.Delantero;
        dataJ = new DataJugador(8,"Green Lantern","Harold Jordan",tipoPos,fechaHora,0,"USA",1.90,80,"images/8.jpeg");
        int id8=icj.ingresarJugador(dataJ);

         // CREO EQUIPOS
        int idEq1=ice.darAltaEquipo("Thundercats","");
        int idEq2=ice.darAltaEquipo("Avengers","");
        int idEq3=ice.darAltaEquipo("X-Mens","");
        int idEq4=ice.darAltaEquipo("S.H.I.E.L.D.","");
        int idEq5=ice.darAltaEquipo("Green Lantern Corps","");
        int idEq6=ice.darAltaEquipo("Justice League","");
        int idEq7=ice.darAltaEquipo("Watchmen","");
        int idEq8=ice.darAltaEquipo("Future Foundation","");

        // CREO COMPETICION PARTIDOINDIVIDUAL
        icc.ingresarCompeticion("Fabs. Match",TipoCompeticion.PartidoIndividual,"");
        icc.seleccionarEquipo(idEq7);
        icc.seleccionarEquipo(idEq2);
        int C1=icc.darDeAltaCompetencia();
        icc.finalizar();

        // CREO COMPETICION PARTIDOINDIVIDUAL
        icc.ingresarCompeticion("Marbles Just",TipoCompeticion.PartidoIndividual,"");
        icc.seleccionarEquipo(idEq8);
        icc.seleccionarEquipo(idEq4);
        int C2=icc.darDeAltaCompetencia();
        icc.finalizar();

        // CREO COMPETICION PARTIDOINDIVIDUAL
        icc.ingresarCompeticion("Epic Competition",TipoCompeticion.PartidoIndividual,"");
        icc.seleccionarEquipo(idEq5);
        icc.seleccionarEquipo(idEq6);
        int C3=icc.darDeAltaCompetencia();
        icc.finalizar();

        icc.ingresarCompeticion("Special Cup",TipoCompeticion.Copa,"");
        icc.seleccionarEquipo(idEq1);
        icc.seleccionarEquipo(idEq2);
        icc.seleccionarEquipo(idEq3);
        icc.seleccionarEquipo(idEq4);
        icc.ingresarDividendoEquipo(idEq1,(float)3.2);
        icc.ingresarDividendoEquipo(idEq2,(float)2.1);
        icc.ingresarDividendoEquipo(idEq3,(float)3.2);
        icc.ingresarDividendoEquipo(idEq4,(float)2.1);
        // NUEVO
        icc.agregarIJC(id1, (float)2);
        icc.agregarIJC(id2, (float)3);
        icc.agregarIJC(id3, (float)4);
        icc.agregarIJC(id4, (float)5);
        icc.agregarIJC(id5, (float)6);
        icc.agregarIJC(id6, (float)7);
        icc.agregarIJC(id7, (float)8);
        icc.agregarIJC(id8, (float)9);

        icc.setMontoPenca(100);
        // NUEVO
        int C4 = icc.darDeAltaCompetencia();
        icc.finalizar();


        // CREO COMPETICION LIGA(float)
        icc.ingresarCompeticion("Awesome League",TipoCompeticion.Liga,"");
        icc.seleccionarEquipo(idEq5);
        icc.seleccionarEquipo(idEq6);
        icc.seleccionarEquipo(idEq7);
        icc.seleccionarEquipo(idEq8);
        TipoCriterio[] dc = {TipoCriterio.GolesFavor,TipoCriterio.DiferenciaGoles,TipoCriterio.Resultado};
        icc.ingresarOrdenLiga(dc);
        icc.ingresarDividendoEquipo(idEq5,(float)1.2);
        icc.ingresarDividendoEquipo(idEq6,(float)3.1);
        icc.ingresarDividendoEquipo(idEq7,(float)1.2);
        icc.ingresarDividendoEquipo(idEq8,(float)3.1);
        // NUEVO
        icc.agregarIJC(id1, (float)2.1);
        icc.agregarIJC(id2, (float)2.2);
        icc.agregarIJC(id3, (float)2.1);
        icc.agregarIJC(id4, (float)2.2);
        icc.agregarIJC(id5, (float)3.5);
        icc.agregarIJC(id6, (float)4.5);
        icc.agregarIJC(id7, (float)3.5);
        icc.agregarIJC(id8, (float)4.5);
        icc.setMontoPenca(150);
        // NUEVO
        int C5=icc.darDeAltaCompetencia();
        icc.finalizar();

        //Asignaciones y finalizacion

        //usuarios
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        String encryptedPassword;

        System.out.println("ante de usuarios");
        IControladorUsuarios cu=f.getIControladorUsuarios();
        fechaHora = new DataFechaHora(30,10,1960,0,0);
        encryptedPassword = passwordEncryptor.encryptPassword("ennapoli");
        cu.registrarUsuario("Diego Maradona","eldiego","gonzalomelov@gmail.com", "",
        encryptedPassword, DataTypes.TipoSexo.Hombre,"Argentina","1.101.010-1",fechaHora);//10


        fechaHora = new DataFechaHora(3,7,1964,0,0);
        encryptedPassword = passwordEncryptor.encryptPassword("choripan");
        cu.registrarUsuario("Claudia Villafañe","claudia","claudia@gm.co", "",
        encryptedPassword, DataTypes.TipoSexo.Mujer,"Argentina","1.234.567-8",fechaHora);//20


        fechaHora = new DataFechaHora(23,10,1940,0,0);
        encryptedPassword = passwordEncryptor.encryptPassword("unpibe");
        cu.registrarUsuario("Edson Arantes","pele","orei@fi.co", "",
        encryptedPassword, DataTypes.TipoSexo.Hombre,"Brasil","9.999.999-9",fechaHora);//199



        fechaHora = new DataFechaHora(17,07,1974,0,0);
        encryptedPassword = passwordEncryptor.encryptPassword("yumbo");
        cu.registrarUsuario("Claudio Lopez","piojo","clopez@gm.co", "",
        encryptedPassword, DataTypes.TipoSexo.Hombre,"Argentina","6.777.888-9",fechaHora);//2


        fechaHora = new DataFechaHora(2,9,1961,0,0);
        encryptedPassword = passwordEncryptor.encryptPassword("teconleche");
        cu.registrarUsuario("Carlos Valderrama","elpibe","carlitos@gm.co", "",
        encryptedPassword, DataTypes.TipoSexo.Hombre,"Colombia","2.233.233-3",fechaHora);//12


        fechaHora = new DataFechaHora(22,7,1975,0,0);
        encryptedPassword = passwordEncryptor.encryptPassword("buenaonda");
        cu.registrarUsuario("Dolores Barreiro","dbarreiro","dolores@gm.co", "",
        encryptedPassword, DataTypes.TipoSexo.Mujer,"Argentina","4.444.444-5",fechaHora);//23


        // agrego a Penca//
        IControladorUsuarios icu = (new Fabrica()).getIControladorUsuarios();
 icu.setNotificarAltaApuestas(false);
        icu.iniciarSesion("eldiego","ennapoli");
        icu.ingresarSaldoUsuario(150);
        boolean ok= icu.participarEnPenca(C5);
        icu.confirmarParticipacion(ok);
        icu.cerrarSesion();

        icu.iniciarSesion("claudia","choripan");
        icu.ingresarSaldoUsuario(150);
        ok= icu.participarEnPenca(C5);
        icu.confirmarParticipacion(ok);
        icu.cerrarSesion();

        icu.iniciarSesion("pele","unpibe");
        icu.ingresarSaldoUsuario(150);
        ok= icu.participarEnPenca(C5);
        icu.confirmarParticipacion(ok);
        icu.cerrarSesion();



        fechaHora=new DataFechaHora(1,9,2011,0,0);
        icc.ingresarDatosPartidoIndividual(C1,idEq7, idEq2,fechaHora,"Anexo");
        icc.asignarDividendos(1,C1,(float)2.1,(float) 1.2, (float)1.3);
        // RESULTADO EXACTO
        icc.almacenarAsigDividendoRes (0,0,200);
        icc.almacenarAsigDividendoRes (0,1,201);
        icc.almacenarAsigDividendoRes (0,2,202);
        icc.almacenarAsigDividendoRes (0,3,203);
        icc.almacenarAsigDividendoRes (1,0,210);
        icc.almacenarAsigDividendoRes (1,1,211);
        icc.almacenarAsigDividendoRes (1,2,212);
        icc.almacenarAsigDividendoRes (1,3,213);
        icc.almacenarAsigDividendoRes (2,0,220);
        icc.almacenarAsigDividendoRes (2,1,221);
        icc.almacenarAsigDividendoRes (2,2,222);
        icc.almacenarAsigDividendoRes (2,3,223);
        icc.almacenarAsigDividendoRes (3,0,230);
        icc.almacenarAsigDividendoRes (3,1,231);
        icc.almacenarAsigDividendoRes (3,2,232);
        icc.almacenarAsigDividendoRes (3,3,233);
        icc.asignarDividendoPartidoResultado (1, C1);
        icc.finalizarAsigDivsResultados();
        // RESULTADO EXACTO

        List<DataInfoPartido> lista=icc.listarPartidosConDivAsignado(C1);
        icc.seleccionarPartido(lista.get(0));
        icc.ingresarResultado(2,0,1,0);
        icc.ingresarJugador(id8,idEq7);
        icc.ingresarJugador(id5,idEq2);
        icc.ingresarJugador(id3,idEq2);

        //EVENTOS
        
        DataIdNombre j3 = new DataIdNombre(3,"The Hulk","");
        DataEvento de= new DataEvento(1,TipoPeriodo.PrimerTiempo,j3,null,TipoEvento.Tarjeta,true);
        icc.ingresarEventos(de);


        DataIdNombre j8 = new DataIdNombre(8,"Green Lantern","");
        DataEvento de1= new DataEvento(24,TipoPeriodo.SegundoTiempo,j8,null,TipoEvento.Tarjeta,true);
        icc.ingresarEventos(de1);


        DataEvento de2= new DataEvento(5,TipoPeriodo.PrimerTiempo,j3,null,TipoEvento.Tarjeta,false);
        icc.ingresarEventos(de2);

        DataEvento de3= new DataEvento(5,TipoPeriodo.SegundoTiempo,j8,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de3);


        DataEvento de4= new DataEvento(15,TipoPeriodo.SegundoTiempo,j8,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de4);

        DataIdNombre j5= new DataIdNombre(5,"Wolverine","");
        DataEvento de5= new DataEvento(35,TipoPeriodo.SegundoTiempo,j5,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de5);

        //icc.confirmarEventos();
        icc.listarIngresos();
        icc.confirmarIngreso(true);


        fechaHora=new DataFechaHora(11,9,2011,0,0);
        icc.ingresarDatosPartidoIndividual(C2,idEq4, idEq8,fechaHora,"Aulario");
        icc.asignarDividendos(1,C2,(float)1.1,(float) 2.1,(float) 3.0);
        // RESULTADO EXACTO
        
        icc.almacenarAsigDividendoRes (0,0,(float)3.5);
        icc.almacenarAsigDividendoRes (0,1,(float)3.0);
        icc.almacenarAsigDividendoRes (1,0,(float)1.5);
        icc.almacenarAsigDividendoRes (1,1,(float)5.0);
        icc.almacenarAsigDividendoRes (1,2,(float)2.5);
        icc.almacenarAsigDividendoRes (2,1,(float)2.0);
        icc.asignarDividendoPartidoResultado (1, C2);
        icc.finalizarAsigDivsResultados();

        // RESULTADO EXACTO

        fechaHora=new DataFechaHora(9,9,2011,0,0);
        icc.ingresarDatosPartidoIndividual(C3,idEq5, idEq6,fechaHora,"2do Piso");
        icc.asignarDividendos(1,C3,(float)3.1,(float) 2.1,(float) 1.1);
        // RESULTADO EXACTO
        
        icc.almacenarAsigDividendoRes (0,0,(float)1.5);
        icc.almacenarAsigDividendoRes (0,1,(float)2.0);
        icc.almacenarAsigDividendoRes (1,0,(float)4.0);
        icc.almacenarAsigDividendoRes (1,1,(float)1.5);
        icc.almacenarAsigDividendoRes (1,2,(float)2.5);
        icc.almacenarAsigDividendoRes (2,1,(float)5.0);
        icc.asignarDividendoPartidoResultado (1, C3);
        icc.finalizarAsigDivsResultados();

        // RESULTADO EXACTO



        //P4
        icc.ingresarIdLiga(C5);
        fechaHora = new DataFechaHora(1,9,2011,0,0);
        icc.seleccionarEncuentroADefinir (idEq5,idEq6, fechaHora,"Camp Nou");
        icc.finalizar();
        icc.asignarDividendos(1,C5,(float)1.3,(float) 6.0, (float)3.2);
        // RESULTADO EXACTO
        
        icc.almacenarAsigDividendoRes (0,0,200);
        icc.almacenarAsigDividendoRes (0,1,201);
        icc.almacenarAsigDividendoRes (0,2,202);
        icc.almacenarAsigDividendoRes (0,3,203);
        icc.almacenarAsigDividendoRes (1,0,210);
        icc.almacenarAsigDividendoRes (1,1,211);
        icc.almacenarAsigDividendoRes (1,2,212);
        icc.almacenarAsigDividendoRes (1,3,213);
        icc.almacenarAsigDividendoRes (2,0,220);
        icc.almacenarAsigDividendoRes (2,1,221);
        icc.almacenarAsigDividendoRes (2,2,222);
        icc.almacenarAsigDividendoRes (2,3,223);
        icc.almacenarAsigDividendoRes (3,0,230);
        icc.almacenarAsigDividendoRes (3,1,231);
        icc.almacenarAsigDividendoRes (3,2,232);
        icc.almacenarAsigDividendoRes (3,3,233);
        icc.asignarDividendoPartidoResultado (1, C5);
        icc.finalizarAsigDivsResultados();

        //apuestas penca x P4
        icu.iniciarSesion("eldiego","ennapoli");
        icu.obtenerPartidosNoFinalizadosPenca(C5);
        icu.apostarPartidoPenca(1,1,0);


         icu.cerrarSesion();

        icu.iniciarSesion("claudia","choripan");
        icu.obtenerPartidosNoFinalizadosPenca(C5);
        icu.apostarPartidoPenca(1,0,1);
        icu.cerrarSesion();

        icu.iniciarSesion("pele","unpibe");
        icu.obtenerPartidosNoFinalizadosPenca(C5);
        icu.apostarPartidoPenca(1,3,1);
        icu.cerrarSesion();



        // RESULTADO EXACTO
        List<DataInfoPartido> listay=icc.listarPartidosConDivAsignado(C5);
        icc.seleccionarPartido(listay.get(0));
        icc.ingresarResultado(1,0,0,0);
        icc.ingresarJugador(id2,idEq5);
        icc.ingresarJugador(id3,idEq6);
       

        //EVENTOS

        DataIdNombre j2 = new DataIdNombre(2,"Nick Fury","");
        DataEvento de6= new DataEvento(10,TipoPeriodo.PrimerTiempo,j2,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de6);
        //icc.confirmarEventos();

        icc.listarIngresos();
        icc.confirmarIngreso(true);

        //P5
        icc.ingresarIdLiga(C5);
        fechaHora = new DataFechaHora(2,9,2011,0,0);
        icc.seleccionarEncuentroADefinir (idEq6,idEq5, fechaHora,"Mestalla");
        icc.finalizar();


        icc.asignarDividendos(2,C5,(float)4.1,(float) 1.5, (float)2.1);
        // RESULTADO EXACTO
        
        icc.almacenarAsigDividendoRes (0,0,200);
        icc.almacenarAsigDividendoRes (0,1,201);
        icc.almacenarAsigDividendoRes (0,2,202);
        icc.almacenarAsigDividendoRes (0,3,203);
        icc.almacenarAsigDividendoRes (1,0,210);
        icc.almacenarAsigDividendoRes (1,1,211);
        icc.almacenarAsigDividendoRes (1,2,212);
        icc.almacenarAsigDividendoRes (1,3,213);
        icc.almacenarAsigDividendoRes (2,0,220);
        icc.almacenarAsigDividendoRes (2,1,221);
        icc.almacenarAsigDividendoRes (2,2,222);
        icc.almacenarAsigDividendoRes (2,3,223);
        icc.almacenarAsigDividendoRes (3,0,230);
        icc.almacenarAsigDividendoRes (3,1,231);
        icc.almacenarAsigDividendoRes (3,2,232);
        icc.almacenarAsigDividendoRes (3,3,233);

        icc.asignarDividendoPartidoResultado (2, C5);
        icc.finalizarAsigDivsResultados();

        //apuestas penca x P5
        icu.iniciarSesion("eldiego","ennapoli");
        icu.obtenerPartidosNoFinalizadosPenca(C5);
        icu.apostarPartidoPenca(2,0,0);
        icu.cerrarSesion();

        icu.iniciarSesion("claudia","choripan");
        icu.obtenerPartidosNoFinalizadosPenca(C5);
        icu.apostarPartidoPenca(2,3,0);
        icu.cerrarSesion();

        icu.iniciarSesion("pele","unpibe");
        icu.obtenerPartidosNoFinalizadosPenca(C5);
        icu.apostarPartidoPenca(2,3,3);
        icu.cerrarSesion();

        // RESULTADO EXACTO
        listay=icc.listarPartidosConDivAsignado(C5);
        icc.seleccionarPartido(listay.get(0));
        icc.ingresarResultado(3,0,3,0);
        icc.ingresarJugador(id7,idEq6);
        icc.ingresarJugador(id2,idEq5);
        icc.ingresarJugador(id8,idEq5);
        

        //EVENTOS
        DataIdNombre j7 = new DataIdNombre(7,"Batman","");
        DataEvento de7= new DataEvento(5,TipoPeriodo.PrimerTiempo,j7,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de7);

        DataEvento de8= new DataEvento(15,TipoPeriodo.PrimerTiempo,j7,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de8);

        DataEvento de9= new DataEvento(25,TipoPeriodo.PrimerTiempo,j7,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de9);

        DataEvento de10= new DataEvento(10,TipoPeriodo.PrimerTiempo,j2,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de10);

        DataEvento de11= new DataEvento(20,TipoPeriodo.PrimerTiempo,j2,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de11);

        DataEvento de12= new DataEvento(25,TipoPeriodo.SegundoTiempo,j8,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de12);

        //icc.confirmarEventos();

        icc.listarIngresos();
        icc.confirmarIngreso(true);

        //P6 E7 E8 (1.8, 2.5, 2.2) C5 01/09/2011 Riazor SI 1 - 3
        icc.ingresarIdLiga(C5);
        fechaHora = new DataFechaHora(1,9,2011,0,0);
        icc.seleccionarEncuentroADefinir (idEq7,idEq8, fechaHora,"Riazor");
        icc.finalizar();
        icc.asignarDividendos(3,C5,(float)1.8,(float) 2.5, (float)2.2);
        // RESULTADO EXACTO
        
        icc.almacenarAsigDividendoRes (0,0,200);
        icc.almacenarAsigDividendoRes (0,1,201);
        icc.almacenarAsigDividendoRes (0,2,202);
        icc.almacenarAsigDividendoRes (0,3,203);
        icc.almacenarAsigDividendoRes (1,0,210);
        icc.almacenarAsigDividendoRes (1,1,211);
        icc.almacenarAsigDividendoRes (1,2,212);
        icc.almacenarAsigDividendoRes (1,3,213);
        icc.almacenarAsigDividendoRes (2,0,220);
        icc.almacenarAsigDividendoRes (2,1,221);
        icc.almacenarAsigDividendoRes (2,2,222);
        icc.almacenarAsigDividendoRes (2,3,223);
        icc.almacenarAsigDividendoRes (3,0,230);
        icc.almacenarAsigDividendoRes (3,1,231);
        icc.almacenarAsigDividendoRes (3,2,232);
        icc.almacenarAsigDividendoRes (3,3,233);
        icc.asignarDividendoPartidoResultado (3, C5);
        icc.finalizarAsigDivsResultados();


        //apuestas penca x P6
        icu.iniciarSesion("eldiego","ennapoli");
        icu.obtenerPartidosNoFinalizadosPenca(C5);
        icu.apostarPartidoPenca(3,1,0);
        icu.cerrarSesion();

        icu.iniciarSesion("claudia","choripan");
        icu.obtenerPartidosNoFinalizadosPenca(C5);
        icu.apostarPartidoPenca(3,1,3);
        icu.cerrarSesion();

        icu.iniciarSesion("pele","unpibe");
        icu.obtenerPartidosNoFinalizadosPenca(C5);
        icu.apostarPartidoPenca(3,0,0);
        icu.cerrarSesion();

        // RESULTADO EXACTO
        listay=icc.listarPartidosConDivAsignado(C5);
        icc.seleccionarPartido(listay.get(0));
        icc.ingresarResultado(1,0,3,0);
        icc.ingresarJugador(id4,idEq7);
        icc.ingresarJugador(id1,idEq8);
        icc.ingresarJugador(id3,idEq8);
       

        //EVENTOS
        DataEvento de13= new DataEvento(5,TipoPeriodo.PrimerTiempo,j3,null,TipoEvento.Tarjeta,false);
        icc.ingresarEventos(de13);

        DataIdNombre j4= new DataIdNombre(4,"Panthro","");
        DataEvento de14= new DataEvento(13,TipoPeriodo.PrimerTiempo,j4,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de14);

        DataIdNombre j1= new DataIdNombre(1,"Iron Man","");
        DataEvento de15= new DataEvento(1,TipoPeriodo.PrimerTiempo,j1,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de15);

        DataEvento de16= new DataEvento(2,TipoPeriodo.PrimerTiempo,j1,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de16);

         DataEvento de17= new DataEvento(3,TipoPeriodo.PrimerTiempo,j1,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de17);

        //icc.confirmarEventos();

        icc.listarIngresos();
        icc.confirmarIngreso(true);

        //P7 E8 E7 (1.7, 3.4, 2.0) C5 02/09/2011 San Mamés SI 2 - 0
        icc.ingresarIdLiga(C5);
        fechaHora = new DataFechaHora(2,9,2011,0,0);
        icc.seleccionarEncuentroADefinir (idEq8,idEq7, fechaHora,"San Mamés");
        icc.finalizar();
        icc.asignarDividendos(4,C5,(float)1.7,(float) 3.4, (float)2.0);
        // RESULTADO EXACTO
        
        icc.almacenarAsigDividendoRes (0,0,200);
        icc.almacenarAsigDividendoRes (0,1,201);
        icc.almacenarAsigDividendoRes (0,2,202);
        icc.almacenarAsigDividendoRes (0,3,203);
        icc.almacenarAsigDividendoRes (1,0,210);
        icc.almacenarAsigDividendoRes (1,1,211);
        icc.almacenarAsigDividendoRes (1,2,212);
        icc.almacenarAsigDividendoRes (1,3,213);
        icc.almacenarAsigDividendoRes (2,0,220);
        icc.almacenarAsigDividendoRes (2,1,221);
        icc.almacenarAsigDividendoRes (2,2,222);
        icc.almacenarAsigDividendoRes (2,3,223);
        icc.almacenarAsigDividendoRes (3,0,230);
        icc.almacenarAsigDividendoRes (3,1,231);
        icc.almacenarAsigDividendoRes (3,2,232);
        icc.almacenarAsigDividendoRes (3,3,233);
        icc.asignarDividendoPartidoResultado (4, C5);
        icc.finalizarAsigDivsResultados();

        // RESULTADO EXACTO
        listay=icc.listarPartidosConDivAsignado(C5);
        icc.seleccionarPartido(listay.get(0));
        icc.ingresarResultado(2,0,0,0);
        icc.ingresarJugador(id6,idEq8);
        icc.ingresarJugador(id7,idEq7);
        icc.ingresarJugador(id3,idEq7);
        


        //EVENTOS
         DataEvento de18= new DataEvento(5,TipoPeriodo.PrimerTiempo,j3,null,TipoEvento.Tarjeta,false);
         icc.ingresarEventos(de18);

         DataIdNombre j6= new DataIdNombre(6,"Silk Spectre","");
         DataEvento de19= new DataEvento(10,TipoPeriodo.PrimerTiempo,j6,null,TipoEvento.Gol,false);
         icc.ingresarEventos(de19);

         DataEvento de20= new DataEvento(20,TipoPeriodo.PrimerTiempo,j6,null,TipoEvento.Gol,false);
         icc.ingresarEventos(de20);

        //icc.confirmarEventos();

        icc.listarIngresos();
        icc.confirmarIngreso(true);

        //P8 E5 E7 (1.2, 9.0, 3.3) C5 03/09/2011 Camp Nou Si 5 - 2
        icc.ingresarIdLiga(C5);
        fechaHora = new DataFechaHora(2,9,2011,0,0);
        icc.seleccionarEncuentroADefinir (idEq5,idEq7, fechaHora,"Camp Nou");
        icc.finalizar();
        icc.asignarDividendos(5,C5,(float)1.2,(float) 9.0, (float)3.3);
        // RESULTADO EXACTO
        
        icc.almacenarAsigDividendoRes (0,0,200);
        icc.almacenarAsigDividendoRes (0,1,201);
        icc.almacenarAsigDividendoRes (0,2,202);
        icc.almacenarAsigDividendoRes (0,3,203);
        icc.almacenarAsigDividendoRes (1,0,210);
        icc.almacenarAsigDividendoRes (1,1,211);
        icc.almacenarAsigDividendoRes (1,2,212);
        icc.almacenarAsigDividendoRes (1,3,213);
        icc.almacenarAsigDividendoRes (2,0,220);
        icc.almacenarAsigDividendoRes (2,1,221);
        icc.almacenarAsigDividendoRes (2,2,222);
        icc.almacenarAsigDividendoRes (2,3,223);
        icc.almacenarAsigDividendoRes (3,0,230);
        icc.almacenarAsigDividendoRes (3,1,231);
        icc.almacenarAsigDividendoRes (3,2,232);
        icc.almacenarAsigDividendoRes (3,3,233);
        icc.asignarDividendoPartidoResultado (5, C5);
        icc.finalizarAsigDivsResultados();

        // RESULTADO EXACTO
        listay=icc.listarPartidosConDivAsignado(C5);
        icc.seleccionarPartido(listay.get(0));
        icc.ingresarResultado(5,0,2,0);
        icc.ingresarJugador(id2,idEq5);
        icc.ingresarJugador(id3,idEq7);
        


        DataEvento de21= new DataEvento(2,TipoPeriodo.PrimerTiempo,j3,null,TipoEvento.Tarjeta,true);
        icc.ingresarEventos(de21);

        DataEvento de22= new DataEvento(5,TipoPeriodo.PrimerTiempo,j3,null,TipoEvento.Tarjeta,false);
        icc.ingresarEventos(de22);

        DataEvento de23= new DataEvento(6,TipoPeriodo.PrimerTiempo,j2,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de23);

        de23= new DataEvento(7,TipoPeriodo.PrimerTiempo,j2,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de23);

        de23= new DataEvento(8,TipoPeriodo.PrimerTiempo,j2,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de23);

        de23= new DataEvento(9,TipoPeriodo.PrimerTiempo,j2,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de23);

        de23= new DataEvento(10,TipoPeriodo.PrimerTiempo,j2,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de23);

        de23= new DataEvento(2,TipoPeriodo.PrimerTiempo,j3,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de23);

        de23= new DataEvento(5,TipoPeriodo.PrimerTiempo,j3,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de23);

        //icc.confirmarEventos();

        icc.listarIngresos();
        icc.confirmarIngreso(true);


       // P9 E7 E5 (7.5, 1.4, 2.2) C5 04/09/2011 Riazor Si 0 - 4
        icc.ingresarIdLiga(C5);
        fechaHora = new DataFechaHora(4,9,2011,0,0);
        icc.seleccionarEncuentroADefinir (idEq7,idEq5, fechaHora,"Riazor");
        icc.finalizar();
        icc.asignarDividendos(6,C5,(float)7.5,(float) 1.4, (float)2.2);
        // RESULTADO EXACTO
        
        icc.almacenarAsigDividendoRes (0,0,200);
        icc.almacenarAsigDividendoRes (0,1,201);
        icc.almacenarAsigDividendoRes (0,2,202);
        icc.almacenarAsigDividendoRes (0,3,203);
        icc.almacenarAsigDividendoRes (1,0,210);
        icc.almacenarAsigDividendoRes (1,1,211);
        icc.almacenarAsigDividendoRes (1,2,212);
        icc.almacenarAsigDividendoRes (1,3,213);
        icc.almacenarAsigDividendoRes (2,0,220);
        icc.almacenarAsigDividendoRes (2,1,221);
        icc.almacenarAsigDividendoRes (2,2,222);
        icc.almacenarAsigDividendoRes (2,3,223);
        icc.almacenarAsigDividendoRes (3,0,230);
        icc.almacenarAsigDividendoRes (3,1,231);
        icc.almacenarAsigDividendoRes (3,2,232);
        icc.almacenarAsigDividendoRes (3,3,233);
        icc.asignarDividendoPartidoResultado (6, C5);
        icc.finalizarAsigDivsResultados();

        // RESULTADO EXACTO
        listay=icc.listarPartidosConDivAsignado(C5);
        icc.seleccionarPartido(listay.get(0));
        icc.ingresarResultado(0,0,4,0);
        icc.ingresarJugador(id7,idEq7);
        icc.ingresarJugador(id2,idEq5);
        icc.ingresarJugador(id8,idEq5);
        icc.ingresarJugador(id4,idEq5);
        icc.ingresarJugador(id3,idEq5);
        


        //Eventos

        de23= new DataEvento(44,TipoPeriodo.SegundoTiempo,j3,null,TipoEvento.Tarjeta,false);
        icc.ingresarEventos(de23);

        de23= new DataEvento(44,TipoPeriodo.SegundoTiempo,j3,j4,TipoEvento.Sustitucion,false);
        icc.ingresarEventos(de23);

        de23= new DataEvento(10,TipoPeriodo.PrimerTiempo,j2,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de23);

        de23= new DataEvento(20,TipoPeriodo.PrimerTiempo,j2,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de23);

        de23= new DataEvento(15,TipoPeriodo.PrimerTiempo,j8,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de23);

        de23= new DataEvento(25,TipoPeriodo.PrimerTiempo,j8,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de23);

        //icc.confirmarEventos();

        icc.listarIngresos();
        icc.confirmarIngreso(true);


        //P10 E6 E8 (1.5, 3.5, 1.8) C5 03/09/2011 Mestalla Si 2 - 1
        icc.ingresarIdLiga(C5);
        fechaHora = new DataFechaHora(3,9,2011,0,0);
        icc.seleccionarEncuentroADefinir (idEq6,idEq8, fechaHora,"Mestalla");
        icc.finalizar();
        icc.asignarDividendos(7,C5,(float)1.5,(float) 3.5, (float)1.8);

        // RESULTADO EXACTO
        
        icc.almacenarAsigDividendoRes (0,0,200);
        icc.almacenarAsigDividendoRes (0,1,201);
        icc.almacenarAsigDividendoRes (0,2,202);
        icc.almacenarAsigDividendoRes (0,3,203);
        icc.almacenarAsigDividendoRes (1,0,210);
        icc.almacenarAsigDividendoRes (1,1,211);
        icc.almacenarAsigDividendoRes (1,2,212);
        icc.almacenarAsigDividendoRes (1,3,213);
        icc.almacenarAsigDividendoRes (2,0,220);
        icc.almacenarAsigDividendoRes (2,1,221);
        icc.almacenarAsigDividendoRes (2,2,222);
        icc.almacenarAsigDividendoRes (2,3,223);
        icc.almacenarAsigDividendoRes (3,0,230);
        icc.almacenarAsigDividendoRes (3,1,231);
        icc.almacenarAsigDividendoRes (3,2,232);
        icc.almacenarAsigDividendoRes (3,3,233);
        icc.asignarDividendoPartidoResultado (7, C5);
        icc.finalizarAsigDivsResultados();

        // RESULTADO EXACTO
        listay=icc.listarPartidosConDivAsignado(C5);
        icc.seleccionarPartido(listay.get(0));
        icc.ingresarResultado(2,0,1,0);
        icc.ingresarJugador(id7,idEq6);
        icc.ingresarJugador(id1,idEq8);
        

        //EVENTOS
        de23= new DataEvento(1,TipoPeriodo.PrimerTiempo,j7,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de23);

        de23= new DataEvento(10,TipoPeriodo.PrimerTiempo,j7,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de23);

        de23= new DataEvento(30,TipoPeriodo.PrimerTiempo,j1,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de23);


        //icc.confirmarEventos();

        icc.listarIngresos();
        icc.confirmarIngreso(true);

        //P11 E8 E6 (1.7, 2.8, 2.1) C5 04/09/2011 San Mamés Si 1-1
        icc.ingresarIdLiga(C5);
        fechaHora = new DataFechaHora(4,9,2011,0,0);
        icc.seleccionarEncuentroADefinir (idEq8,idEq6, fechaHora,"San Mamés");
        icc.finalizar();
        icc.asignarDividendos(8,C5,(float)1.7,(float) 2.8, (float)2.1);
        // RESULTADO EXACTO
        
        icc.almacenarAsigDividendoRes (0,0,200);
        icc.almacenarAsigDividendoRes (0,1,201);
        icc.almacenarAsigDividendoRes (0,2,202);
        icc.almacenarAsigDividendoRes (0,3,203);
        icc.almacenarAsigDividendoRes (1,0,210);
        icc.almacenarAsigDividendoRes (1,1,211);
        icc.almacenarAsigDividendoRes (1,2,212);
        icc.almacenarAsigDividendoRes (1,3,213);
        icc.almacenarAsigDividendoRes (2,0,220);
        icc.almacenarAsigDividendoRes (2,1,221);
        icc.almacenarAsigDividendoRes (2,2,222);
        icc.almacenarAsigDividendoRes (2,3,223);
        icc.almacenarAsigDividendoRes (3,0,230);
        icc.almacenarAsigDividendoRes (3,1,231);
        icc.almacenarAsigDividendoRes (3,2,232);
        icc.almacenarAsigDividendoRes (3,3,233);
        icc.asignarDividendoPartidoResultado (8, C5);
        icc.finalizarAsigDivsResultados();
        // RESULTADO EXACTO
        listay=icc.listarPartidosConDivAsignado(C5);
        icc.seleccionarPartido(listay.get(0));
        icc.ingresarResultado(1,0,1,0);
        icc.ingresarJugador(id1,idEq8);
        icc.ingresarJugador(id7,idEq6);
        

        //Eventos
        de23= new DataEvento(45,TipoPeriodo.PrimerTiempo,j7,null,TipoEvento.Tarjeta,true);
        icc.ingresarEventos(de23);

        de23= new DataEvento(45,TipoPeriodo.PrimerTiempo,j7,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de23);

        de23= new DataEvento(1,TipoPeriodo.PrimerTiempo,j1,null,TipoEvento.Gol,false);
        icc.ingresarEventos(de23);

        //icc.confirmarEventos();

        icc.listarIngresos();
        icc.confirmarIngreso(true);

        //P12 E5 E8 (1.1, 15.0, 5.0) C5 05/09/2011 Camp Nou No No aplica
        icc.ingresarIdLiga(C5);
        fechaHora = new DataFechaHora(5,9,2011,0,0);
        icc.seleccionarEncuentroADefinir (idEq5,idEq8, fechaHora,"Camp Nou");
        icc.finalizar();
        icc.asignarDividendos(9,C5,(float)1.1,(float)15.0, (float)5.0);
        // RESULTADO EXACTO
        
        icc.almacenarAsigDividendoRes (0,0,(float)6.0);
        icc.almacenarAsigDividendoRes (0,1,(float)15.5);
        icc.almacenarAsigDividendoRes (1,0,(float)15.0);
        icc.almacenarAsigDividendoRes (1,1,(float)7.0);
        icc.almacenarAsigDividendoRes (1,2,(float)16.0);
        icc.almacenarAsigDividendoRes (2,1,(float)1.8);

        icc.asignarDividendoPartidoResultado (9, C5);
        icc.finalizarAsigDivsResultados();

        //apuestas penca x P12
        icu.iniciarSesion("eldiego","ennapoli");
        icu.obtenerPartidosNoFinalizadosPenca(C5);
        icu.apostarPartidoPenca(9,2,1);
        icu.cerrarSesion();

        icu.iniciarSesion("claudia","choripan");
        icu.obtenerPartidosNoFinalizadosPenca(C5);
        icu.apostarPartidoPenca(9,1,1);
        icu.cerrarSesion();

        icu.iniciarSesion("pele","unpibe");
        icu.obtenerPartidosNoFinalizadosPenca(C5);
        icu.apostarPartidoPenca(9,0,1);
        icu.cerrarSesion();

        // RESULTADO EXACTO

        //P13 E8 E5 (5.2, 1.3, 3.2) C5 06/09/2011 San Mamés No No aplica
        icc.ingresarIdLiga(C5);
        fechaHora = new DataFechaHora(6,9,2011,0,0);
        icc.seleccionarEncuentroADefinir (idEq8,idEq5, fechaHora,"San Mamés");
        icc.finalizar();
        icc.asignarDividendos(10,C5,(float)5.2,(float) 1.3, (float)3.2);
        // RESULTADO EXACTO
        
        icc.almacenarAsigDividendoRes (0,0,(float)3.4);
        icc.almacenarAsigDividendoRes (0,1,(float)1.5);
        icc.almacenarAsigDividendoRes (1,0,(float)5.5);
        icc.almacenarAsigDividendoRes (1,1,(float)3.5);
        icc.almacenarAsigDividendoRes (1,2,(float)1.4);
        icc.almacenarAsigDividendoRes (2,1,(float)6.6);
        icc.asignarDividendoPartidoResultado (10, C5);
        icc.finalizarAsigDivsResultados();

        //apuestas penca x P13
        icu.iniciarSesion("eldiego","ennapoli");
        icu.obtenerPartidosNoFinalizadosPenca(C5);
        icu.apostarPartidoPenca(10,0,1);
        icu.cerrarSesion();

        icu.iniciarSesion("claudia","choripan");
        icu.obtenerPartidosNoFinalizadosPenca(C5);
        icu.apostarPartidoPenca(10,1,1);
        icu.cerrarSesion();

        icu.iniciarSesion("pele","unpibe");
        icu.obtenerPartidosNoFinalizadosPenca(C5);
        icu.apostarPartidoPenca(10,0,0);
        icu.cerrarSesion();

        // RESULTADO EXACTO

        //P14 E6 E7 (1.4, 4.1, 1.2) C5 05/09/2011 Mestalla No No aplica
        icc.ingresarIdLiga(C5);
        fechaHora = new DataFechaHora(5,9,2011,0,0);
        icc.seleccionarEncuentroADefinir (idEq6,idEq7, fechaHora,"Mestalla");
        icc.finalizar();
        icc.asignarDividendos(11,C5,(float)1.4,(float)4.1, (float)1.2);
        // RESULTADO EXACTO
        
        icc.almacenarAsigDividendoRes (0,0,(float)2.2);
        icc.almacenarAsigDividendoRes (0,1,(float)6.1);
        icc.almacenarAsigDividendoRes (1,0,(float)2.4);
        icc.almacenarAsigDividendoRes (1,1,(float)3.2);
        icc.almacenarAsigDividendoRes (1,2,(float)5.1);
        icc.almacenarAsigDividendoRes (2,1,(float)3.4);
     
        icc.asignarDividendoPartidoResultado (11, C5);
        icc.finalizarAsigDivsResultados();

        //apuestas penca x P14
        icu.iniciarSesion("eldiego","ennapoli");
        icu.obtenerPartidosNoFinalizadosPenca(C5);
        icu.apostarPartidoPenca(11,0,0);
        icu.cerrarSesion();

        icu.iniciarSesion("claudia","choripan");
        icu.obtenerPartidosNoFinalizadosPenca(C5);
        icu.apostarPartidoPenca(11,1,1);
        icu.cerrarSesion();

        icu.iniciarSesion("pele","unpibe");
        icu.obtenerPartidosNoFinalizadosPenca(C5);
        icu.apostarPartidoPenca(11,0,1);
        icu.cerrarSesion();

        // RESULTADO EXACTO

        //P15 E7 E6 (1.8, 2.2, 2.7) C5 06/09/2011 Riazor No No aplica
        icc.ingresarIdLiga(C5);
        fechaHora = new DataFechaHora(6,9,2011,0,0);
        icc.seleccionarEncuentroADefinir (idEq7,idEq6, fechaHora,"Riazor");
        icc.finalizar();
        icc.asignarDividendos(12,C5,(float)1.8,(float) 2.2, (float)2.7);
        // RESULTADO EXACTO
        
        icc.almacenarAsigDividendoRes (0,0,(float)3.7);
        icc.almacenarAsigDividendoRes (0,1,(float)6.1);
        icc.almacenarAsigDividendoRes (1,0,(float)2.4);
        icc.almacenarAsigDividendoRes (1,1,(float)3.2);
        icc.almacenarAsigDividendoRes (1,2,(float)5.1);
        icc.almacenarAsigDividendoRes (2,1,(float)3.4);

        icc.asignarDividendoPartidoResultado (12, C5);
        icc.finalizarAsigDivsResultados();

        //apuestas penca x P4
        icu.iniciarSesion("eldiego","ennapoli");
        icu.obtenerPartidosNoFinalizadosPenca(C5);
        icu.apostarPartidoPenca(12,4,0);
        icu.cerrarSesion();

        icu.iniciarSesion("claudia","choripan");
        icu.obtenerPartidosNoFinalizadosPenca(C5);
        icu.apostarPartidoPenca(12,1,1);
        icu.cerrarSesion();

        icu.iniciarSesion("pele","unpibe");
        icu.obtenerPartidosNoFinalizadosPenca(C5);
        icu.apostarPartidoPenca(12,0,0);
        icu.cerrarSesion();

        // RESULTADO EXACTO






        
         

        //Apuestas

        List<DataApuestaPersistencia> listaApuestas=new java.util.ArrayList<DataApuestaPersistencia>();
        fechaHora = new DataFechaHora(1,7,2010,0,0);
        DataApuestaPersistencia apuesta=new DataApuestaPersistencia(TipoApuesta.Campeonato,C4,(float) 10,fechaHora,EstadoApuesta.Pendiente,
                null,idEq1, 1,"eldiego",-1,-1,-1,-1);
        listaApuestas.add(apuesta);

        fechaHora = new DataFechaHora(11,7,2010,0,0);
        apuesta=new DataApuestaPersistencia(TipoApuesta.Campeonato,C4,(float) 9,fechaHora,EstadoApuesta.Pendiente,
                null,idEq4, 1,"claudia",-1,-1,-1,-1);
        listaApuestas.add(apuesta);

        fechaHora = new DataFechaHora(9,7,2010,0,0);
        apuesta=new DataApuestaPersistencia(TipoApuesta.Campeonato,C5,(float) 30,fechaHora,EstadoApuesta.Pendiente,
                null,idEq6, 1,"pele",-1,-1,-1,-1);
        listaApuestas.add(apuesta);

        fechaHora = new DataFechaHora(9,7,2010,0,0);
        apuesta=new DataApuestaPersistencia(TipoApuesta.Campeonato,C5,(float) 15,fechaHora,EstadoApuesta.Pendiente,
                null,idEq5, 1,"elpibe",-1,-1,-1,-1);
        listaApuestas.add(apuesta);


        //apuestas partido

        //AP1 U1 C1 P1 15 Local 01/07/2010 Ganada
        fechaHora = new DataFechaHora(1,7,2010,0,0);
        apuesta=new DataApuestaPersistencia(TipoApuesta.Partido,C1,(float) 15,fechaHora,EstadoApuesta.Gano,
                TipoDividendo.Local,-1, 1,"eldiego",-1,-1,-1,-1);
        listaApuestas.add(apuesta);

        //AP2 U3 C1 P1 8 Empate 11/07/2010 Perdida
        fechaHora = new DataFechaHora(11,7,2010,0,0);
        apuesta=new DataApuestaPersistencia(TipoApuesta.Partido,C1,(float) 8,fechaHora,EstadoApuesta.Perdio,
                TipoDividendo.Empate,-1, 1,"pele",-1,-1,-1,-1);
        listaApuestas.add(apuesta);

        //AP3 U3 C1 P1 10 Visitante 11/07/2010 Perdida
        fechaHora = new DataFechaHora(11,7,2010,0,0);
        apuesta=new DataApuestaPersistencia(TipoApuesta.Partido,C1,(float) 10,fechaHora,EstadoApuesta.Perdio,
                TipoDividendo.Visitante,-1, 1,"pele",-1,-1,-1,-1);
        listaApuestas.add(apuesta);

        //AP4 U4 C5 P4 10 Local 01/02/2011 Ganada
        fechaHora = new DataFechaHora(1,2,2011,0,0);
        apuesta=new DataApuestaPersistencia(TipoApuesta.Partido,C5,(float) 10,fechaHora,EstadoApuesta.Gano,
                TipoDividendo.Local,-1, 1,"piojo",-1,-1,-1,-1);
        listaApuestas.add(apuesta);

        //AP5 U5 C5 P13 20 Empate 02/02/2011 Pendiente
        fechaHora = new DataFechaHora(2,2,2011,0,0);
        apuesta=new DataApuestaPersistencia(TipoApuesta.Partido,C5,(float) 20,fechaHora,EstadoApuesta.Pendiente,
                TipoDividendo.Empate,-1, 10,"elpibe",-1,-1,-1,-1);
        listaApuestas.add(apuesta);

        //AP6 U2 C2 P2 50 Local 09/07/2010 Pendiente
        fechaHora = new DataFechaHora(9,7,2010,0,0);
        apuesta=new DataApuestaPersistencia(TipoApuesta.Partido,C2,(float) 50,fechaHora,EstadoApuesta.Pendiente,
                TipoDividendo.Local,-1, 1,"claudia",-1,-1,-1,-1);
        listaApuestas.add(apuesta);

        //AP7 U3 C3 P3 12 Empate 01/01/2010 Pendiente
        fechaHora = new DataFechaHora(1,1,2010,0,0);
        apuesta=new DataApuestaPersistencia(TipoApuesta.Partido,C3,(float) 12,fechaHora,EstadoApuesta.Pendiente,
                TipoDividendo.Empate,-1, 1,"pele",-1,-1,-1,-1);
        listaApuestas.add(apuesta);

        //Apuesta goleador

        //AG1
        fechaHora = new DataFechaHora(1,7,2010,0,0);
        apuesta=new DataApuestaPersistencia(TipoApuesta.Goleador,C4,(float) 15,fechaHora,EstadoApuesta.Pendiente,
                null,-1, -1,"eldiego",id1,-1,-1,-1);
        listaApuestas.add(apuesta);

        //AG2
        fechaHora = new DataFechaHora(11,7,2010,0,0);
        apuesta=new DataApuestaPersistencia(TipoApuesta.Goleador,C4,(float) 20,fechaHora,EstadoApuesta.Pendiente,
                null,-1, -1,"claudia",id2,-1,-1,-1);
        listaApuestas.add(apuesta);
        //AG3
        fechaHora = new DataFechaHora(9,7,2010,0,0);
        apuesta=new DataApuestaPersistencia(TipoApuesta.Goleador,C5,(float) 15,fechaHora,EstadoApuesta.Pendiente,
                null,-1, -1,"pele",id1,-1,-1,-1);
        listaApuestas.add(apuesta);
        //AG4
        fechaHora = new DataFechaHora(9,7,2010,0,0);
        apuesta=new DataApuestaPersistencia(TipoApuesta.Goleador,C5,(float) 20,fechaHora,EstadoApuesta.Pendiente,
                null,-1, -1,"elpibe",id2,-1,-1,-1);
        listaApuestas.add(apuesta);


        icc.cargarApuesta(listaApuestas);

        cu.setearSaldo("eldiego", (float)10);
        cu.setearSaldo("pele", (float)199);
        cu.setearSaldo("claudia", (float)20);
        cu.setearSaldo("piojo", (float)2);
        cu.setearSaldo("elpibe", (float)12);
        cu.setearSaldo("dbarreiro", (float)23);

        cu.iniciarSesion("eldiego","ennapoli");
        cu.setNotificacion(true,true,true);
        cu.enviarMensaje(5,"claudia", "eldiego",true,"saludos");
        cu.cerrarSesion();


        JOptionPane.showMessageDialog(null,"se han cargado los datos correctamente", "Cargar Datos", JOptionPane.INFORMATION_MESSAGE);
    }

    /** This metEdad.sethod is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.gi-bin/admens_inco/show_notas.pl
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jDesktopPane1 = new Fondo()
        ;
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        DetallesUsuarios = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Laboratorio 3 - Taller de programacion - 2011");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jMenu1.setText("Archivo");

        jMenuItem20.setText("Cargar Datos de Prueba");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem20);

        jMenuItem21.setText("Cargar Datos");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem21);

        jMenuItem22.setText("Guardar Datos");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem22);

        jMenuItem24.setText("Registro de Acceso");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem24);
        jMenu1.add(jSeparator1);

        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Equipos");

        jMenuItem3.setText("Nuevo");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem3MouseClicked(evt);
            }
        });
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Ver informacion ");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Jugadores");

        jMenuItem5.setText("Nuevo");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem6.setText("Detalles");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem7.setText("Modificar");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem8.setText("Eliminar");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Competiciones");

        jMenu6.setText("Nueva Competicion");

        jMenuItem9.setText("Partido Individual");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem9);

        jMenuItem10.setText("Copa");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem10);

        jMenuItem11.setText("Liga");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem11);

        jMenu4.add(jMenu6);

        jMenu7.setText("Partido Nuevo");

        jMenuItem12.setText("Individual");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem12);

        jMenuItem13.setText("De Liga");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem13);

        jMenuItem14.setText("De Copa");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem14);

        jMenu4.add(jMenu7);

        jMenuItem15.setText("Asignar Dividendos");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem15);

        jMenuItem16.setText("Finalizar Partido");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem16);

        jMenuItem17.setText("Detalles Competiciones");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem17);

        jMenuBar1.add(jMenu4);

        jMenu9.setText("Usuarios");

        DetallesUsuarios.setText("Detalles Usuarios ");
        DetallesUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DetallesUsuariosActionPerformed(evt);
            }
        });
        jMenu9.add(DetallesUsuarios);

        jMenuItem23.setText("Ver Estadisticas");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem23);

        jMenuBar1.add(jMenu9);

        jMenu5.setText("Ayuda");

        jMenuItem2.setText("Acerca de");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem2);

        jMenuBar1.add(jMenu5);

        jMenu8.setText("Fecha");

        jMenuItem18.setText("Mostrar Fecha");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed1(evt);
            }
        });
        jMenu8.add(jMenuItem18);

        jMenuItem19.setText("Modificar Fecha");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem19);

        jMenuBar1.add(jMenu8);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1540, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        
/********************************************************************************FUNCION PARA CAMBIAR EL MODO, SI ESTA EN FALSE NO S PUEDE HACER NADA EN EL JFRAME TAREA1*/
    public void setModoActivado(boolean modo){
        this.ModoActivado = modo;
    }
/***********************************************************************************/    

        private void formWindowOpened(java.awt.event.WindowEvent evt) {                                  
            //salir.setVisible(false);
        }                                 

        private void jMenuItem3MouseClicked(java.awt.event.MouseEvent evt) {                                        
            // TODO add your handling code here:
            //AltaEquipo.setVisible(true);
        }                                       

        private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {                                           
            // TODO add your handling code here:
            if (!ModoActivado)
                return;

            JInternalFrame j = new AltaEquipo(this);
            j.setVisible(true);
            jDesktopPane1.add(j);
            java.awt.Component content = this.getContentPane();
            int x = (content.getWidth() - j.getWidth()) / 2;
            int y = (content.getHeight() - j.getHeight()) / 2;
            j.setLocation(x, y);
        }                                          

        private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {                                           
            // TODO add your handling code here:
            if (!ModoActivado)
                return;

            JInternalFrame j = new Jugador(-1,this);
            java.awt.Component content = this.getContentPane();
            int x = (content.getWidth() - j.getWidth()) / 2;
            int y = (content.getHeight() - j.getHeight()) / 2;
            j.setLocation(x, y);
            jDesktopPane1.add(j);
            j.setVisible(true);
            
        }                                          



                private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {                                           
                    // TODO add your handling code here:
                    if (!ModoActivado)
                        return;

                    JInternalFrame j = new ListaFiltro("Detalles Jugador",this);
                    j.setVisible(true);
                    this.jDesktopPane1.add(j);
                }                                          

                private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {                                           
                    // TODO add your handling code here:
                    if (!ModoActivado)
                        return;

                    JInternalFrame j = new ListaFiltro("Modificar Jugador",this);
                    jDesktopPane1.add(j);
                    j.setVisible(true);

                }                                          

                private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {                                           
                    // TODO add your handling code here:
                    if (!ModoActivado)
                        return;

                    JInternalFrame j = new ListaFiltro("Eliminar Jugador",this);
                    jDesktopPane1.add(j);
                    j.setVisible(true);

                }                                          

                private void formWindowClosing(java.awt.event.WindowEvent evt) {                                   
                    // TODO add your handling code here:
                    //preguntar si desea salir!!
                    int a = JOptionPane.showConfirmDialog(null, "Esta seguro que desea salir?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (a == JOptionPane.YES_OPTION){
                        System.exit(0);
                    }
                }                                  

                private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {                                           
                    // TODO add your handling code here:
                    if (!ModoActivado)
                        return;

                    JInternalFrame j2 = new ListaFiltro("Detalles Equipo",this);
                    j2.setVisible(true);
                    this.jDesktopPane1.add(j2);
                }                                          

                private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {                                           
                    // TODO add your handling code here:
                    //seteamos el tipo de lacompeticion a crear, tipo, partido indiviual
                    if (!ModoActivado)
                        return;

                    TipoCompeticion tipo = TipoCompeticion.PartidoIndividual;
                    JInternalFrame j = new AltaCompeticion(tipo,this);
                    j.setVisible(true);
                    this.jDesktopPane1.add(j);
                }                                          

                private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {                                            
                    // TODO add your handling code here:
                    if (!ModoActivado)
                        return;

                    TipoCompeticion tipo = null;
                    tipo = tipo.Copa;
                    JInternalFrame j = new AltaCompeticion(tipo,this);
                    j.setVisible(true);
                    this.jDesktopPane1.add(j);
                }                                           

                private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {                                            
                    // TODO add your handling code here:
                    if (!ModoActivado)
                        return;
                    
                    TipoCompeticion tipo = null;
                    tipo = tipo.Liga;
                    JInternalFrame j = new AltaCompeticion(tipo,this);
                    j.setVisible(true);
                    this.jDesktopPane1.add(j);
                }                                           

                private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {                                            
                    if (!ModoActivado)
                        return;

                    JInternalFrame j = new PartidoLiga(this);
                    this.jDesktopPane1.add(j);

                }                                           

                private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {                                            

                    if (!ModoActivado)
                        return;

                    JInternalFrame j = new ListaFiltro("Detalles Competicion",this);
                    this.jDesktopPane1.add(j);
                    j.setVisible(true);
                    
                }                                           

                private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {                                            
                    // TODO add your handling code here:

                    if (!ModoActivado)
                        return;

                    JInternalFrame j = new ListaPartidoDivNoAsignado(this);
                    this.jDesktopPane1.add(j);
                    j.setVisible(true);
                }                                           

                private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {                                            
                    // TODO add your handling code here:
                    if (!ModoActivado)
                        return;

                    JInternalFrame j = new PartidoIndividual(this);
                    this.jDesktopPane1.add(j);
                }                                           

                private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {                                            
                    // TODO add your handling code here:


                }                                           

                private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {                                            
                    // TODO add your handling code here:
                    if (!ModoActivado)
                       return;                    if (!ModoActivado)
                        return;

                    JInternalFrame j = new ModificarFecha(this);
                    this.getLayeredPane().add(j);
                }                                           

                private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
                    // TODO add your handling code here:
                    if (!ModoActivado)
                       return;
                    
                    JInternalFrame j = new AcercaDe(this);
                    this.getLayeredPane().add(j);
                    
                }                                          

                private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
                    // TODO add your handling code here:
                    int a = JOptionPane.showConfirmDialog(null, "Esta seguro que desea salir?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (a == JOptionPane.YES_OPTION){
                        System.exit(0);
                    }
                }                                          

                private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {                                            
                                // TODO add your handling code here:

                    if (!ModoActivado)
                        return;

                    Fabrica f = new Fabrica();
                    IControladorCompeticiones iCC = f.getIControladorCompeticiones();

                    List<DataIdNombre> lista = iCC.listarCopaNoDefinida();
                    if(lista.isEmpty()){
                        String str = "No existen copas a definir en el sistema";
                        JOptionPane.showMessageDialog(this,str,"Atencion",JOptionPane.ERROR_MESSAGE );
                        this.setModoActivado(true);
                    }
                    else{
                        JInternalFrame j = new AltaLlave(this,lista,iCC);
                        j.setVisible(true);
                        this.jDesktopPane1.add(j);
                    }

                                       
                }                                           

                private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {                                            
                    // TODO add your handling code here:
                    if (!ModoActivado)
                        return;
                    
                    JInternalFrame j = new ListaFiltro("Finalizar Partido",this);
                    this.jDesktopPane1.add(j);
                    j.setVisible(true);
                }                                           

                private void jMenuItem18ActionPerformed1(java.awt.event.ActionEvent evt) {                                             
                    // TODO add your handling code here:
                    if (!ModoActivado)
                        return;
                    
                    Fabrica f = new Fabrica();
                    IControladorFecha icF = f.getIControladorFecha();
                    DataFechaHora fecha = icF.getFecha();
                    JOptionPane.showMessageDialog(null, fecha.toString(), "Fecha", JOptionPane.INFORMATION_MESSAGE);
                }                                            

                private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {                                            
                    // TODO add your handling code here:
                    if (!this.ModoActivado)
                        return;
                    if (!datosCargados){
                        try{
                            cargarDatos();
                            datosCargados = true;
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(null,e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace();
                        }
                        this.setModoActivado(true);
                    }else {
                        JOptionPane.showMessageDialog(null, "Los Datos ya fueron cargados", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }                                           

                private void DetallesUsuariosActionPerformed(java.awt.event.ActionEvent evt) {                                                 
                    // TODO add your handling code here:
                     if (!this.ModoActivado)
                        return;

                     JInternalFrame j = new ListarUsuarios(this);
                        j.setVisible(true);
                        this.jDesktopPane1.add(j);
                }                                                

                private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {                                            
                    Fabrica f = new Fabrica();
                    IControladorDatos icd = f.getIControladorDatos();
                    JFileChooser chooser = new JFileChooser();
                    chooser.setMultiSelectionEnabled(false);
                    chooser.setFileFilter(new FiltroXLS());
                    chooser.setAcceptAllFileFilterUsed(false);
                    int e = chooser.showOpenDialog(null);

                    String path = "";
                    if (e == JFileChooser.APPROVE_OPTION){
                        if (chooser.getSelectedFile() != null) {
                            path = chooser.getSelectedFile().getPath();
                        }
                    }
                    icd.cargar(path);
                }                                           

                private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {                                            
                    Fabrica f = new Fabrica();
                    IControladorDatos icd = f.getIControladorDatos();
                    JFileChooser chooser = new JFileChooser();
                    chooser.setMultiSelectionEnabled(false);
                    chooser.setFileFilter(new FiltroXLS());
                    chooser.setAcceptAllFileFilterUsed(false);
                    int e = chooser.showOpenDialog(null);

                    String path = "";
                    if (e == JFileChooser.APPROVE_OPTION){
                        if (chooser.getSelectedFile() != null) {
                            path = chooser.getSelectedFile().getPath();
                        }
                    }
                    icd.guardar(path);
                }                                           


	private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {                                            

            if (Controladores.ManejadorUsuarios.getInstance().getUsuariosNick().isEmpty()){
                JOptionPane.showMessageDialog(this,"No existen usuarios en el sistema.","Atencion",JOptionPane.ERROR_MESSAGE );
                return;
            }

            GraficaApuestas ga = new GraficaApuestas("Apuestas", Controladores.ManejadorUsuarios.getInstance());
            ga.pack();
            RefineryUtilities.centerFrameOnScreen(ga);
            ga.setVisible(true);
	}                                           

        private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {                                            
            JInternalFrame j = new RegistroAcceso(this);
            this.getLayeredPane().add(j);
        }                                           


    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.out.println("Iniciado el endpoints....");
                new WebServices.MostrarFecha1().publicar();
                new WebServices.UsuarioWS().publicar();
                new WebServices.CompeticionesWS().publicar();
		new WebServices.ImageServer().publicar();
                new WebServices.RegistroAccesoWS().publicar();
                new WebServices.DispMovilWS().publicar();
                System.out.println("Fin inicio de endpoints....");


                new Tarea1().setVisible(true);
            }
        });


    }
    
    // Variables declaration - do not modify                     
    private javax.swing.JMenuItem DetallesUsuarios;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration                   
    //variables auxiliares de la parte grafica!

    private boolean ModoActivado = true;
    private boolean datosCargados = false;
}
