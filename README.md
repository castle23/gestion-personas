# Gestion de Personas

API REST con las operaciones CRUD necesarias para gestionar el recurso Persona.  
Realizado con Maven, Java 17 y SpringBoot 3.

### Requisitos
Tener instalados Maven 3.8+, Java 17+ y una BD de su preferencia.

Esta realizada para que se conecte a una BD PostgreSQL por lo que debe reemplazar los datos de conexión en el archivo **'application.properties'**

    spring.datasource.url=jdbc:postgresql://host:puerto/nombre_bd
    spring.datasource.username=usuario_bd
    spring.datasource.password=clave_bd
Tenga en cuenta que la primera vez que inicia el programa se van a crear las tablas, secuencias en índices en caso de que no existan y/o va actualizar los cambios que tenga respecto al código, esto se debe a la propiedad

    spring.jpa.hibernate.ddl-auto=update

En caso de que use otra BD que no sea PostgreSQL recuerde cambiar la propertie por el valor que usted vaya a utilizar

    spring.jpa.properties.hibernate.dialect=
Asi como tambien debe reemplazar la libreria de postgres por la de su BD en el archivo **'pom.xml'**

    <dependency>  
       <groupId>org.postgresql</groupId>  
       <artifactId>postgresql</artifactId>  
    </dependency>

Una vez creada la BD puedes ejecutar el script **'inserts_paises.sql'** que se encuentra en el proyecto para cargar los datos de la tabla.

## Iniciar App
Al ser un proyecto Maven solo debes correr el siguiente comando en una terminal en el directorio donde te hayas bajado el proyecto para iniciar la app

    mvn spring-boot:run
Así como también puedes ir al IDE de tu preferencias y darle clic derecho al archivo **'PersonasApplication'** y darle RUN en el menú que despliega.

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
