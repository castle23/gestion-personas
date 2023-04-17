# Gestion de Personas

API REST con las operaciones CRUD necesarias para gestionar el recurso Persona.  
Realizado con Maven, Java 17 y SpringBoot 3.

### Requisitos
Tener instalados Maven 3.8+, Java 17+ y una BD de su preferencia.

Está realizada para que se conecte a una BD PostgreSQL por lo que debe reemplazar los datos de conexión en el archivo **'application.properties'**

    spring.datasource.url=jdbc:postgresql://host:puerto/nombre_bd
    spring.datasource.username=usuario_bd
    spring.datasource.password=clave_bd
Tenga en cuenta que la primera vez que inicia el programa se van a crear las tablas, secuencias en índices en caso de que no existan y/o va a actualizar los cambios que tenga respecto al código, esto se debe a la propiedad

    spring.jpa.hibernate.ddl-auto=update

En caso de que use otra BD que no sea PostgreSQL recuerde cambiar la propertie por el valor que usted vaya a utilizar

    spring.jpa.properties.hibernate.dialect=
Asi como también debe reemplazar la libreria de postgres por la de su BD en el archivo **'pom.xml'**

    <dependency>  
       <groupId>org.postgresql</groupId>  
       <artifactId>postgresql</artifactId>  
    </dependency>

Una vez creada la BD puedes ejecutar el script **'inserts_paises.sql'** que se encuentra en el proyecto para cargar los datos de la tabla.

## Iniciar App Local
Al ser un proyecto Maven solo debes correr el siguiente comando en una terminal en el directorio donde te hayas bajado el proyecto para iniciar la app

    mvn spring-boot:run
Así como también puedes ir al IDE de tú preferencias y darle clic derecho al archivo **'PersonasApplication'** y darle RUN en el menú que despliega.

## Deploy App Google Cloud
Para desplegar el proyecto debes tener instalado el SDK de Google Cloud, tienes que haber creado un proyecto y habilitar el servicio de google app engine.
Al proyecto se le agrega el plugin de maven appengine para facilitar el deploy en google cloud.

En el archivo _src\main\appengine\app.yaml_ encontrarás las configuracion de las instancias necesarias para iniciar tu app en google.
Para realizar el deploy solo necesitas correr el siguiente comando:

    mvn clean package appengine:deploy -Dapp.deploy.projectId=<proyect-id> -Dapp.deploy.version=<version> -P gcp

En el cual debes reemplazar el **<proyect-id>** por el identificador de tu proyecto y **<version>** para identificar la version que vas a realizar el deploy.
El proyecto también fue configurado con un perfil de "gcp" para configurar las properties de la nube en el archivo "application-gcp.properties".
Para este proyecto se configuró que use una BD de H2 basada en memoria, pero puedes configurarla para conectarse a la BD de tu preferencia como se indica anteriormente.
Tenga en cuenta también que se agregó la propiedad

    spring.jpa.defer-datasource-initialization

Por lo que cuando inicie la app se carguen las tablas con los insert que se encuentran en el archivo _**data.sql**_.

## Test Performance

En el archivo _**test-performance.jmx**_ encontrarás una prueba de stress realizada con Apache Jmeter para generar una prueba de fluctuaciones de tráfico de 1 a 500 request por segundo.

## Servicios REST

### Listar todas las personas

**Endpoint**: `/personas`

**Método HTTP**: GET
**Respuesta Exitosa**: Retorna un arreglo JSON con todas las personas almacenadas en la base de datos. Ejemplo:

    [
        {
            "id": 1,
            "nombre": "prueba",
            "apellido": "prueba",
            "idPadre": null,
            "tipoDocumento": "DNI",
            "numeroDocumento": "12345678",
            "pais": {
                "id": 1,
                "nombre": "Argentina"
            },
            "telefono": "1111111111",
            "email": "prueba@gmail.com",
            "fechaNacimiento": "1990/01/01"
        }
    ]

### Obtener una persona por ID

**Endpoint**: `/personas/{id}`

**Método HTTP**: GET

**Respuesta Exitosa**: Retorna la persona con el ID especificado. Ejemplo:

    {
        "id": 1,
        "nombre": "prueba",
        "apellido": "prueba",
        "idPadre": null,
        "tipoDocumento": "DNI",
        "numeroDocumento": "12345678",
        "pais": {
            "id": 1,
            "nombre": "Argentina"
        },
        "telefono": "1111111111",
        "email": "prueba@gmail.com",
        "fechaNacimiento": "1990/01/01"
    }
**Errores posibles**:
-   Código HTTP `404` si no se encuentra una persona con el ID proporcionado.
-   Código HTTP `400` si los datos proporcionados son inválidos o incompletos.
-   Código HTTP `500` si ocurre un error interno en el servidor.


### Crear una nueva persona

**Endpoint**: `/personas`

**Método HTTP**: POST

**Cuerpo de la Petición**: JSON con los datos de la persona a crear. Ejemplo:


    {
        "nombre": "prueba2",
        "apellido": "prueba2",
        "tipoDocumento": "DNI",
        "numeroDocumento": "11111111",
        "pais": {
            "id": 3,
            "nombre": "España"
        },
        "telefono": "3333333333",
        "email": "prueba2@gmail.com",
        "fechaNacimiento": "1990/03/20"
    }

**Respuesta Exitosa**: Retorna la persona creada, incluyendo su ID asignado por la base de datos. Ejemplo:

    {
        "id": 2,
        "nombre": "prueba2",
        "apellido": "prueba2",
        "tipoDocumento": "DNI",
        "numeroDocumento": "11111111",
        "pais": {
            "id": 3,
            "nombre": "España"
        },
        "telefono": "3333333333",
        "email": "prueba2@gmail.com",
        "fechaNacimiento": "1990/03/20"
    }

**Errores posibles**:
-   Código HTTP `400` si los datos proporcionados son inválidos o incompletos.
-   Código HTTP `500` si ocurre un error interno en el servidor.

### Actualizar una persona

**Endpoint**: `/personas/{id}`

**Método HTTP**: PUT

**Cuerpo de la Petición**: JSON con los datos de la persona a crear. Ejemplo:


    {
        "nombre": "prueba3",
        "apellido": "prueba3",
        "tipoDocumento": "DNI",
        "numeroDocumento": "11111111",
        "pais": {
            "id": 3,
            "nombre": "España"
        },
        "telefono": "3333333333",
        "email": "prueba2@gmail.com",
        "fechaNacimiento": "1990/03/20"
    }

**Respuesta Exitosa**: Retorna la persona creada, incluyendo su ID asignado por la base de datos. Ejemplo:

    {
        "id": 2,
        "nombre": "prueba3",
        "apellido": "prueba3",
        "tipoDocumento": "DNI",
        "numeroDocumento": "11111111",
        "pais": {
            "id": 3,
            "nombre": "España"
        },
        "telefono": "3333333333",
        "email": "prueba2@gmail.com",
        "fechaNacimiento": "1990/03/20"
    }

**Errores posibles**:
-   Código HTTP `404` si no se encuentra una persona con el ID proporcionado.
-   Código HTTP `400` si los datos proporcionados son inválidos o incompletos.
-   Código HTTP `500` si ocurre un error interno en el servidor.

### Eliminar una persona

**Endpoint**: `/personas/{id}`

**Método HTTP**: DELETE

**Respuesta Exitosa**: Retorna Código HTTP `204` (No Content).

**Errores posibles**:
-   Código HTTP `404` si no se encuentra una persona con el ID proporcionado.
-   Código HTTP `500` si ocurre un error interno en el servidor.

### Asignar Padre

**Endpoint**: `/personas/{idPadre}/padre/{idPersona}`

**Método HTTP**: POST

**Respuesta Exitosa**: Retorna la persona con Padre especificado. Ejemplo:

    {
        "id": 1,
        "nombre": "prueba",
        "apellido": "prueba",
        "idPadre":     {
		        "id": 2,
		        "nombre": "prueba",
		        "apellido": "prueba",
		        "idPadre": null,
		        "tipoDocumento": "DNI",
		        "numeroDocumento": "12345678",
		        "pais": {
		            "id": 2,
		            "nombre": "Brasil"
		        },
		        "telefono": "1111111111",
		        "email": "prueba@gmail.com",
		        "fechaNacimiento": "1990/01/01"
		    },
        "tipoDocumento": "DNI",
        "numeroDocumento": "12345678",
        "pais": {
            "id": 1,
            "nombre": "Argentina"
        },
        "telefono": "1111111111",
        "email": "prueba@gmail.com",
        "fechaNacimiento": "1990/01/01"
    }

**Errores posibles**:
-   Código HTTP `404` si no se encuentra una persona con el ID proporcionado.
-   Código HTTP `400` si los datos proporcionados son inválidos.
-   Código HTTP `500` si ocurre un error interno en el servidor.

### Relacion entre dos pesonas

**Endpoint**: `/personas/relaciones/{id1}/{id2}`

**Método HTTP**: GET

**Respuesta Exitosa**: Retorna la relacion entre las dos personas. Valores posibles:

    HERMANO,PRIMO,TIO

**Errores posibles**:
-   Código HTTP `404` si no se encuentra una persona con el ID proporcionado.
-   Código HTTP `400` si los datos proporcionados son inválidos.
-   Código HTTP `500` si ocurre un error interno en el servidor.

### Estadisticas por paises

**Endpoint**: `/stats`

**Método HTTP**: GET

**Respuesta Exitosa**: Retorna la lista de paises con los porcentajes de personas. Ejemplo:

    [
        {
            "pais": "Andorra",
            "porcentaje": 20.0
        },
        {
            "pais": "Albania",
            "porcentaje": 20.0
        },
        {
            "pais": "Argentina",
            "porcentaje": 60.0
        }
    ]


**Errores posibles**:
-   Código HTTP `500` si ocurre un error interno en el servidor.
