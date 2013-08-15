#! /bin/bash



path=pcunix124



wsimport -p WebServices.CompeticionesWS -keep http://$path:8280/competicionesWS?wsdl
wsimport -p WebServices.UsuarioWS -keep http://$path:8280/usuarioWS?wsdl
wsimport -p WebServices.MostrarFecha1 -keep http://$path:8280/mostrarFecha1?wsdl
wsimport -p WebServices.ImageServer -keep http://$path:8280/imageServerWS?wsdl
wsimport -p WebServices.RegistroAccesoWS -keep http://$path:8280/registroAccesoWS?wsdl
