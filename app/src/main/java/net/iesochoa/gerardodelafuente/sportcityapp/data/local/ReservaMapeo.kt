package net.iesochoa.gerardodelafuente.sportcityapp.data.local

import net.iesochoa.gerardodelafuente.sportcityapp.model.Reserva

fun ReservaEntity.toDomain(): Reserva =
    Reserva(
        id = id,
        pistaId = pistaId,
        pistaNombre = pistaNombre,
        fecha = fecha,
        hora = hora,
        nombreCliente = nombreCliente,
        telefonoCliente = telefonoCliente,
        comentario = comentario,
        deporte = deporte
    )

// De modelo de dominio a Entity para guardar en la base de datos

fun Reserva.toEntity(): ReservaEntity =
    ReservaEntity(
        id = id ?: 0,
        pistaId = pistaId,
        pistaNombre = pistaNombre,
        fecha = fecha,
        hora = hora,
        nombreCliente = nombreCliente,
        telefonoCliente = telefonoCliente,
        comentario = comentario,
        deporte = deporte
    )