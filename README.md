## SPORTCITY APP

SportCity App es una aplicación móvil Android para la reserva de pistas deportivas.  
Permite registrarse, iniciar sesión con Firebase Authentication, consultar pistas deportivas, ver el
detalle de cada pista, crear reservas, consultar las reservas del usuario conectado y eliminarlas.

La aplicación consume una API REST desarrollada con Spring Boot, desplegada en Render y conectada a
una base de datos PostgreSQL.  
La autenticación de usuarios se realiza con Firebase Auth y cada reserva se asocia al `uid` del
usuario autenticado.

## carasteristicas principales

## Login en Firebase Auth

- Registro de nuevos usuarios.
- Inicio de sesión con correo y contraseña.
- Validación de campos.
- Identificación del usuario mediante `FirebaseAuth.currentUser.uid`.
- Cada usuario solo puede ver sus propias reservas.

## Home

Pantalla principal desde la que el usuario puede elegir el deporte que quiere reservar.

- Deportes disponibles:
    - Fútbol 7
    - Tenis
    - Pádel
    - Basket

## Listado pistas por deporte**

- Las pistas se obtienen desde una API REST usando Retrofit.
- La API devuelve las pistas almacenadas en PostgreSQL (en uns base de datos en Render).
- Las pistas se filtran por deporte.
- Cada pista muestra:
    - Nombre
    - Precio por hora
    - Deporte
- Al pulsar una pista, se navega a su pantalla de detalle.
- Se muestra estado de carga mientras Render despierta o responde.
- Se muestra mensaje de error y botón de reintentar si falla la conexión.

## Detalle de pista

- Muestra información detallada de la pista:
    - Nombre
    - Descripción
    - Precio por hora
- Permite seleccionar día y hora de forma sencilla.
- Incluye botón para iniciar el proceso de reserva.

## Formulario Reserva

- Formulario para introducir:
    - Nombre del cliente
    - Teléfono
    - Comentario opcional
- Validación de campos.
- Al confirmar la reserva:
    - Se obtiene el `uid` del usuario autenticado en Firebase.
    - Se crea una reserva asociada a ese usuario.
    - Se envía la reserva mediante Retrofit a la API Spring Boot.
    - La API guarda la reserva en PostgreSQL.
    - Se navega a la pantalla de confirmación.

## Confirmación reserva

- Muestra un mensaje visual indicando que la reserva se ha realizado correctamente.
- Enseña los datos principales de la reserva realizada.

## Mis Reservas

- Lista las reservas del usuario autenticado.
- Las reservas se obtienen desde la API usando el endpoint filtrado por usuario.
- Cada usuario solo ve las reservas asociadas a su `uid` de Firebase.
- Cada reserva se muestra en una tarjeta con:
    - Icono del deporte
    - Nombre de la pista
    - Fecha
    - Hora
    - Datos del cliente
- Permite borrar reservas.
- Al borrar una reserva:
    - Se llama al endpoint `DELETE` de la API.
    - La reserva se elimina de PostgreSQL.
    - La pantalla se actualiza.
- Incluye estados de carga, error y botón de reintentar.

### Pantalla de perfil

- Pantalla destinada a mostrar información del usuario autenticado.
- Permite acceder a datos básicos del usuario.

### Pantalla de ayuda

- Pantalla informativa para orientar al usuario sobre el uso de la aplicación.

## API REST

La aplicación consume una API REST desarrollada con Spring Boot y desplegada en Render.

URL base:

https://sportcity-api.onrender.com/

Endpoints principales usados por la app:

- Obtiene todas las pistas deportivas.
  - GET /api/pistas 

- Crea una nueva reserva en PostgreSQL.
  - GET /api/reservas/usuario/{usuarioId}

- Elimina una reserva por su identificador.
  - DELETE /api/reservas/{id}


## Base de datos remota

La API está conectada a una base de datos PostgreSQL alojada en Render.

Tablas principales:

**Pistas**
Campos principales:

- id
- nombre
- deporte
- descripcion
- precio_hora

**Reservas**

Campos principales:

- id
- pista_id
- pista_nombre
- fecha
- hora
- nombre_cliente
- telefono_cliente
- comentario
- deporte
- usuario_id

El campo usuario_id permite relacionar cada reserva con el usuario autenticado en Firebase.

**Arquitectura**

-## Arquitectura

La aplicación sigue una arquitectura basada en **MVVM**, separando la interfaz, la lógica de presentación, los modelos y el acceso a datos.

### Capas principales

- **ui**
    - Contiene la interfaz de usuario desarrollada con Jetpack Compose.
    - Incluye pantallas, navegación, componentes reutilizables y ViewModels.

- **model**
    - Contiene los modelos principales de la aplicación.
    - Incluye clases como `Pista`, `Reserva`, `PistasUiState`, `ReservasUiState` y `RequestStatus`.

- **data**
    - Contiene la lógica de acceso a datos.
    - Incluye la parte local con Room, la parte remota con Retrofit y los repositorios.

---

## Estructura del proyecto

## ui

Contiene todo lo relacionado con la interfaz de usuario.

## Components

Componentes reutilizables de la aplicación:

- `BottomNavBar`
- `PistaCard`
- `ReservaItemCard`

## navigation

Contiene la navegación de la aplicación:

- `NavHost`
- `ScreenNavigation`

`ScreenNavigation` se usa para centralizar las rutas de navegación de la app.

## screens

Pantallas principales de la aplicación:

- Login
- Registro
- Home
- Pistas por deporte
- Detalle de pista
- Formulario de reserva
- Confirmación de reserva
- Mis reservas
- Perfil de usuario
- Ayuda

## viewModel

Contiene los ViewModels encargados de gestionar el estado de las pantallas y comunicarse con los repositorios.

ViewModels principales:

- `PistasViewModel`
- `ReservasViewModel`
- `LoginViewModel`
- ViewModel de registro, si procede

---

## model

Contiene los modelos principales de la aplicación.

Modelos principales:

- `Pista`
- `Reserva`
- `PistasUiState`
- `ReservasUiState`
- `RequestStatus`

`Pista` representa una pista deportiva.

`Reserva` representa una reserva realizada por un usuario.

`PistasUiState` representa el estado de la pantalla de pistas.

`ReservasUiState` representa el estado de la pantalla de reservas.

`RequestStatus` se utiliza para controlar el estado de las peticiones a la API:

- `Idle`
- `Loading`
- `Success`
- `Error`

Esto permite diferenciar entre una pantalla cargando, una petición correcta, un error o una lista vacía real.

---

## data

Contiene la lógica de acceso a datos.

## local

Contiene la estructura relacionada con Room:

- DAOs
- Entidades Room
- Base de datos local
- Repositorios Room

Room se mantiene en el proyecto porque la migración a Retrofit se ha realizado de forma progresiva, sin eliminar de golpe la estructura anterior que usqaba room.

Elementos principales:

- `SportCityDataBase`
- `PistaDao`
- `ReservaDao`
- `PistaEntity`
- `ReservaEntity`
- `PistasRoomRepository`
- `ReservasRoomRepository`

## remote

Contiene la configuración de Retrofit y las llamadas a la API.

Elementos principales:

- `ApiService`
- `RetrofitClient`

`ApiService` define los endpoints que Android consume de la API.

Endpoints usados:

- `GET /api/pistas`
- `GET /api/reservas`
- `GET /api/reservas/usuario/{usuarioId}`
- `POST /api/reservas`
- `DELETE /api/reservas/{id}`

`RetrofitClient` configura:

- URL base de la API.
- Conversor Gson.
- Cliente OkHttp.
- Timeouts ampliados para Render.

Los timeouts se han ampliado porque Render puede tardar en despertar cuando el servicio lleva tiempo inactivo.

## repository

Contiene los repositorios encargados de separar el ViewModel del origen real de los datos.

Repositorios principales:

- `PistasApiRepository`
- `ReservasApiRepository`
- `PistasRoomRepository`
- `ReservasRoomRepository`

Actualmente, las pistas y reservas principales se gestionan mediante la API remota.

Room se conserva como parte de la migración progresiva desde la versión anterior del proyecto.

## auth

Contiene la lógica relacionada con Firebase Authentication.

Se usa Firebase Auth para:

- Registrar usuarios.
- Iniciar sesión.
- Obtener el `uid` del usuario autenticado.
- Asociar reservas a cada usuario mediante `usuarioId`.

---

## API REST

La aplicación consume una API REST desarrollada con **Spring Boot**.

La API está desplegada en Render y conectada a una base de datos PostgreSQL.

URL base:

https://sportcity-api.onrender.com/

## Tecnologías utilizadas

- Kotlin
- Jetpack Compose
- MVVM
- StateFlow
- Coroutines
- Retrofit
- FireBase Authentication Autenticación con Firebase (email y contraseña)
- Room
    - Tecnologias Externas para la API
        - SpringBoot (Java)
        - PostgreSQL (Base de datos)
        - Render (API)
        - DBeaver (Comprobación base de datos)
        - Postman (Comprobación EndPoint)
    - GitHub

## Estado actual del proyecto

Funcionalidades implementadas:

- Registro de usuario.
- Inicio de sesión con Firebase.
- Pantalla principal con selección de deportes.
- Listado de pistas obtenido desde la API.
- Detalle de cada pista.
- Creación de reservas mediante API REST.
- Guardado de reservas en PostgreSQL.
- Asociación de reservas al usuario autenticado mediante el `uid` de Firebase.
- Listado de reservas filtradas por usuario.
- Borrado de reservas mediante API.
- Pantalla de perfil de usuario.
- Pantalla de ayuda.
- Estados de carga y error en pistas y reservas.
- Botón de reintentar en pantallas que dependen de la API.
- Timeout ampliado en Retrofit para mejorar el comportamiento cuando Render tarda en responder.
- Mantenimiento temporal de Room durante la migración a Retrofit.

## Posibles mejoras futuras

- Mejorar el selector de fecha usando un calendario más completo.
- Añadir comprobación de disponibilidad para evitar reservas duplicadas en la misma pista, fecha y hora.
- Añadir recuperación de contraseña.
- Mejorar la pantalla de perfil con más información editable.
- Crear una zona de administración para gestionar pistas y reservas.
- Revisar si Room sigue siendo necesario o si puede eliminarse cuando la migración a API esté completamente consolidada.
- Añadir DTOs en la API para separar mejor las entidades internas de las respuestas enviadas al cliente.
- Mejorar los mensajes de error según el tipo de fallo.
- Añadir pruebas básicas


