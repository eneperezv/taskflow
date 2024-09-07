# taskflow

Taskflow es una API REST que permite gestionar tareas y sus respectivos seguimientos. Se incluyen funcionalidades para crear, consultar y modificar tareas, ademas de poder crear, consultar y modificar seguimientos a las mismas. Cada seguimiento va asociado a una tarea y a un usuario. El usuario del seguimiento puede ser distinto al creador de la tarea. La API REST fue construida con Spring Boot y esta compuesta por 3 capas principales, las cuales son: Controller, Service y Repository.

- La capa **Controller** se encarga de recibir la información de la solicitud y asignarla al endpoint correspondiente.
- La capa **Service** incluye la logica del negocio para control de los datos. En este caso, no se realizan validaciones. Esto se hará en una segunda etapa de desarrollo.
- La capa **Repository** se encarga de controlar la persistencia de datos mediante Spring JPQ y la base de datos MySQL.
- No se incluyen endpoints para eliminar registros de tareas y seguimientos por trazabilidad de la información.

## Características

- **API REST**: Construida con Spring Boot, incluye endpoints seguros para la gestión de tareas.
- **Seguridad**: Autenticación y autorización basada en JWT.
- **Persistencia**: Integración con JPA/Hibernate para la gestión de la base de datos.
- **Maven**: Gestión de dependencias con Maven.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.3.2**
- **JPA/Hibernate**
- **JWT para seguridad**
- **Maven**

## Instalación

### Prerrequisitos

- Java 17 o superior
- Maven
- Base de datos (MySQL)

### Instrucciones de Instalación

1. **Clonar el repositorio**:
    ```bash
    git clone https://github.com/eneperezv/taskflow.git
    ```

2. **Configurar la API**:

    - Navegar al directorio de la API:
    ```bash
    cd bookmaster/taskflow.api
    ```
    - Configurar el archivo `application.properties` con los detalles de conexión a la base de datos.

3. **Ejecutar la API**:
    ```bash
    mvn spring-boot:run
    ```

## Endpoints de la API

| Método | Endpoint                                  | Descripción                            |
|--------|-------------------------------------------|----------------------------------------|
| POST   | `/api/v1/taskflow/auth`                   | Autenticacion para crear TOKEN         |
| GET    | `/api/v1/taskflow/user/all`               | Consultar todos los usuarios           |
| GET    | `/api/v1/taskflow/user/{usuario}`         | Consultar usuario                      |
| POST   | `/api/v1/taskflow/user/create`            | Crear nuevo usuario                    |
| PUT    | `/api/v1/taskflow/user/update`            | Actualizar datos de usuario            |
| GET    | `/api/v1/taskflow/task`                   | Consultar todas las tareas             |
| POST   | `/api/v1/taskflow/task`                   | Crear nueva tarea                      |
| PUT    | `/api/v1/taskflow/task`                   | Actualizar una tarea                   |
| GET    | `/api/v1/taskflow/task/followup/{idtask}` | Consultar seguimientos por idtarea     |
| POST   | `/api/v1/taskflow/task/followup`          | Crear nuevo seguimiento a una tarea    |
| PUT    | `/api/v1/taskflow/task/followup`          | Actualizar un seguimiento              |

## Contribución

¡Las contribuciones son bienvenidas! Por favor, sigue el estándar de código y crea un Pull Request con tus mejoras.

## Licencia

Este proyecto está bajo la Licencia GNU GPL v3. Ver el archivo `LICENSE` para más detalles.
