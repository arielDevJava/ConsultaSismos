# ConsultaSismos
- Microservico que consulta sismos de acuerdo a parametros ingresados

1.- clonar proyecto  
2.- ejecutar en linea de comando 'grundle build'  
3.- ejecutar en linea de comando 'grundle run'  
4.- se abrira puerto por defecto 8080  

- Ocupar cualquier herramienta de ejecucion de servicio(soap ui, postman, etc) 
- Estos servicios fueron desarrollados con arquitectura REST ocupando microservicos spring-boot, java 8
- La documentacion esta integrada con Swagger en la siguiente url: http://localhost:8080/swagger-ui.html una vez compilador y puesto en marcha el servicio

- Se ha configurado securidad jwt por lo que inicalmente se debera ejecutar el siguiente servicio:
  endpoint: localhost:8080/authenticate 
  method: post
  body joson:{"username":"1234","password":"1234"} 
  
- Una vez ejecutado el sistema devolvera un jwttoken lo cual deberan copiar con 'Authorizacion Bearer' en los siguientes endpoint:
  key: Authorization
  value: token generado con el primer servicio

-  Servicio que consulta sismos dadas dos fechas formateadas 'yyyy-MM-dd'
  endpoint: localhost:8080/consulta/sismos/fechas?starttime=2019-10-13&endtime=2019-10-14
  method: get
  response: json
  
-  Servicio que consulta sismos dadas dos magnitudes con formato '0.0'
  endpoint: localhost:8080/consulta/sismos/magnitudes?minmagnitude=5.0&maxmagnitude=6.0
  method: get
  response: json
  
-  Servicio que consulta sismos dadas cuatro fechas formateadas 'yyyy-MM-dd'
  endpoint: localhost:8080/consulta/sismos/fechas/rango?fechaInicioR1=2019-10-13&fechaTerminoR1=2019-10-14&fechaInicioR2=2019-10-15&fechaTerminoR2=2019-10-16
  method: get
  response: json
  
-  Servicio que consulta sismos dado un pais
  endpoint: localhost:8080/consulta/sismos/pais?pais=chile
  method: get
  response: json
  
-  Servicio que consulta sismos dado dos paises y dos fechas formateadas 'yyyy-MM-dd'
  endpoint: localhost:8080/consulta/sismos/paises/fechas?paisR1=chile&paisR2=alaska&starttime=2019-10-15&endtime=2019-10-16
  method: get
  response: json
  


