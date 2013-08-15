#!/bin/bash

path=pcunix69

cd src

wsimport -p WebServices.DispMovilWS -keep http://$path:8280/dispMovilWS?wsdl
wsimport -p WebServices.MostrarFecha1 -keep http://$path:8280/mostrarFecha1?wsdl
