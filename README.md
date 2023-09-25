#es-inditex-rates creado por Pedro Rodriguez

Repositorio que contiene 1 microservicio (para propósitos de proceso de entrevista en Inditex), el cual se encarga de una búsqueda satisfactoria de tarifas aplicadas a productos.

# Instalación

Servicio construido con SpringBoot y Maven, con las siguientes versiones:

- `java 21`
- `spring boot 3.2.0-M3`

Para la base de datos h2 se utiliza el siguiente JDBC URL:

- `jdbc:h2:mem:es-inditex`

# Detalle del microservicio:

## es-inditex-rates

Microservicio que se encarga de gestionar las tarifas de los productos

### Request

`GET /rates/top?date={date}&brandId={brandId}&productId={productId}`

Se encarga de buscar la tarifa más adecuada para un producto.

Validaciónes:

- `date -> formato 'yyyy-MM-dd-HH.mm.ss', requerido`
- `brandId -> numérico de tipo long, requerido`
- `productId -> numérico de tipo long, requerido`

### Response

```json
{
  "internalCode": 200,
  "message": "Success",
  "data": {
    "brandId": 1,
    "productId": 35455,
    "priceList": 1,
    "startDate": "2020-06-14-00.00.00",
    "endDate": "2020-12-31-23.59.59",
    "price": 35.5
  }
}
```
Mensaje de error en caso de no encontrar tarifa:

```json
{
  "internalCode": 404,
  "message": "Rate not found",
  "data": null
}
```

Mensaje de error en caso de error en formato de fecha:
```json
{
"internalCode": 400,
"message": "Date format error",
"data": null
}
```


