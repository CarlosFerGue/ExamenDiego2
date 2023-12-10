-- Base de datos: `particulares`
CREATE DATABASE IF NOT EXISTS `particulares`;
USE `particulares`;

-- Estructura de tabla para la tabla `articulos`
CREATE TABLE `articulos` (
  `id_articulo` int NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `precio` int DEFAULT NULL,
  `stock` int DEFAULT NULL
)
INSERT INTO `articulos` (`id_articulo`, `nombre`, `precio`, `stock`) VALUES
(1, 'Producto A', 11, 100),
(2, 'Producto B', 6, 50),
(3, 'Producto C', 8, 75),
(4, 'Producto D', 13, 30),
(5, 'Producto E', 8, 60),
(9, 'Producto F', 16, 50),
(10, 'Producto H', 16, 50);

-- Estructura de tabla para la tabla `dir_envio`
CREATE TABLE `dir_envio` (
  `id_direccion` int NOT NULL,
  `nombre` varchar(60) DEFAULT NULL
)
INSERT INTO `dir_envio` (`id_direccion`, `nombre`) VALUES
(4, 'Avenida M, Ciudad N'),
(2, 'Avenida X, Ciudad Y'),
(1, 'Calle A, Ciudad B'),
(35, 'Calle Del Carmen 2, Zaragoza'),
(5, 'Calle La Jota 65, Tudela'),
(3, 'Calle Z, Ciudad W');
--
-- Estructura de tabla para la tabla `particulares`
--
CREATE TABLE `particulares` (
  `id_particular` int NOT NULL,
  `nombre` varchar(35) DEFAULT NULL,
  `dni` varchar(9) DEFAULT NULL,
  `telefono` varchar(9) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL
)
INSERT INTO `particulares` (`id_particular`, `nombre`, `dni`, `telefono`, `direccion`) VALUES
(1, 'Juan Pérez', '123456789', '555555555', 'Calle Principal 123'),
(2, 'María Gómez', '987654321', '666666666', 'Avenida Secundaria 456'),
(3, 'Pedro Martínez', '111111111', '777777777', 'Plaza Central 789'),
(10, 'Jorge Alquezar', '79845681F', '649856248', 'Calle Arkadia');
--
-- Estructura de tabla para la tabla `tickets`
--
CREATE TABLE `tickets` (
  `id_ticket` int NOT NULL,
  `fecha` date DEFAULT NULL,
  `titular` varchar(40) DEFAULT NULL,
  `num_tarjeta` varchar(16) DEFAULT NULL,
  `tipo_tarjeta` varchar(20) DEFAULT NULL
)
INSERT INTO `tickets` (`id_ticket`, `fecha`, `titular`, `num_tarjeta`, `tipo_tarjeta`) VALUES
(1, '2023-10-28', '', '', ''),
(2, '2023-10-29', 'María González', '9876543210987654', 'MasterCard'),
(3, '2023-10-30', '', '', ''),
(4, '2023-10-31', '', '', ''),
(5, '2023-11-01', 'Pedro Sánchez', '9012345678901234', 'MasterCard'),
(6, '2023-10-12', 'Gabriel Milagro', '7568490387562847', 'Visa'),
(7, '2023-10-12', 'Gabriel Milagro', '7568490387562847', 'Visa'),
(8, '2023-10-12', 'Gabriel Milagro', '7568490387562847', 'Visa'),
(9, '2023-10-12', 'Gabriel Milagro', '7568490387562847', 'Visa'),
(10, '2023-10-12', 'Gabriel Milagro', '7568490387562847', 'Visa'),
(12, '2023-10-12', '', '', '');


-- Indices de la tabla `articulos`
ALTER TABLE `articulos`
  ADD PRIMARY KEY (`id_articulo`);

-- Indices de la tabla `dir_envio`
ALTER TABLE `dir_envio`
  ADD PRIMARY KEY (`id_direccion`),
  ADD UNIQUE KEY `nombre_UNIQUE` (`nombre`);

-- Indices de la tabla `particulares`
ALTER TABLE `particulares`
  ADD PRIMARY KEY (`id_particular`),
  ADD UNIQUE KEY `dni` (`dni`);

-- Indices de la tabla `tickets`
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`id_ticket`);


-- AUTO_INCREMENT de las tablas volcadas

-- AUTO_INCREMENT de la tabla `articulos`
ALTER TABLE `articulos`
  MODIFY `id_articulo` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

-- AUTO_INCREMENT de la tabla `dir_envio`
ALTER TABLE `dir_envio`
  MODIFY `id_direccion` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

-- AUTO_INCREMENT de la tabla `particulares`
ALTER TABLE `particulares`
  MODIFY `id_particular` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

-- AUTO_INCREMENT de la tabla `tickets`
ALTER TABLE `tickets`
  MODIFY `id_ticket` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

