  # GenTux:     *Generador de código C para Tuxedo*
     Generador de servicios de negocio y de datos en lenguaje C
  
  > Sea un 80% más productivo.
  > Desarrolle 80% más rápido.

# ¿Qué es GenTux?
  Generador de servicios de negocio y de datos en lenguaje C.
  Es muy útil crear servicios de datos CRUD y no para crear servicios de negocio con llamadas a muchos servicios.
  
# Inspiración
  Desarrollar aplicaciones en lenguaje C más rápido en el middleware Tuxedo.

# Beneficios
  Puede hacer servicios CRUD más su test unitario en 4 horas, y por lo general, esto puedo tomar 2 días para un programador novato.


# Características
* Genera servicios de datos CRUD a partir de tablas de bases de datos    (programación orientada a los datos).
* Genera servicios empresariales a partir de archivo xml.
* Genera servicios de prueba de datos desde la tabla de base de datos.
* Genera archivo data para conexión con JOLT.
* Define convenciones de nomenclatura a partir de archivos de propiedades.
* Usa plantillas para definir servicios.

# Requisitos y limitaciones

* Usa Eclipse
* Escrito en Java
* Java Emitter Template (JET).
Ver tutorial en :
https://www.eclipse.org/articles/Article-JET/jet_tutorial1.html y https://www.eclipse.org/articles/Article-JET2/jet_tutorial2.html


# Cómo funciona

* Ingresar a IDE Eclipse
* Abrir el proyecto Gentux

* Ejecutar los archivos como aplicación Java
 1. del paquete com.gentux.datos.generador:
 * GeneradorDefinicionFirmaServicios.java
 * GeneradorFMLs.java
 * GeneradorFuncionesDatosEnC.java
 * GeneradorFuncionesGenerales.java
 * GeneradorStructs.java

 2. del paquete com.gentux.negocio.generador:
* GeneradorDefinicionServiciosJolt.java
* GeneradorImplementacionServiciosNegocioTuxedo.java
* GeneradorServiciosTuxedoXML.java
* GeneradorUdTestServiciosNegocio.java


# Licencia
MIT

[//]: # (Estos son enlaces de referencia que se usan en el cuerpo de esta nota y se eliminan cuando el procesador de rebajas hace su trabajo. No es necesario formatear bien porque no debería verse. Gracias SO - http: //stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)
