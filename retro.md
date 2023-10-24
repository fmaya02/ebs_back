# Fecha de la Retrospectiva: 4/10
## Participantes:
* Fernandez, Alejandro 46963 
* Duran, Tatiana 41385 
* Espeche, Marcos 49023 
* Maya, Facundo 48997 
* Schulz, Santiago 47783
* Facundo Pino

## Resumen del Sprint:
### Duración del Sprint: 2 semanas
* Objetivos del Sprint: Presentar las entidades en Spring con todas sus relaciones, correspondientes a las modificaciones realizadas en el diagrama de clases, con las tablas implementadas en la base de datos H2, con un ejemplo de código hardcodeado en el método main. 
* Modificaciones al Diagrama de Clases. DetallePedido y DetalleFactura apuntan al articulo Manufacturado. Cambio de nombre de ArticuloManufacturado a Articulo. Cambio de nombre de ArticuloInsumo a Insumo. Clase intermedia entre Insumo y Articulo modificada, con navegabilidad de Articulo a Insumo. Relación recursiva de ArticuloRubro eliminada, solo permanece la que representa quién es el padre del rubro. Añadimos relacion nullable entre Pedido y Domicilio. Clase nueva añadida: Localidad, para parametrizar ese dato y tenerlo aparte del domicilio.
* Problemas notables: Nada que mencionar

### Retrospectiva:
* Lo que salió bien: Logramos desarrollar todo lo solicitado sin mayores dificultades
* Lo que podría mejorar: Comunicación entre los miembros del grupo.
* Acciones concretas: Realizar reuniones semanales para ponernos al día con lo que hay que hacer y con la distribución de actividades
* Celebraciones: Mejor manejo a la hora de trabajar con Spring

## Conclusiones:
Logramos presentar el Sprint en tiempo y forma a pesar de no tener buena comunicación en el equipo, pero es un problema que no podemos ignorar ya que en el largo plazo nos va a afectar negativamente.