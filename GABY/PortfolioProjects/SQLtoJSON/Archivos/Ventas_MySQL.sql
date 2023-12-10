DROP TABLE `articulos` ;
CREATE TABLE `articulos` (
  `id_articulo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NULL,
  `precio` INT NULL,
  `stock` INT NULL,
  PRIMARY KEY (`id_articulo`))


DROP TABLE `tickets` ;
CREATE TABLE `tickets` (
  `id_ticket` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NULL,
  `titular` VARCHAR(40) NULL,
  `num_tarjeta` VARCHAR(16) NULL,
  `tipo_tarjeta` VARCHAR(20) NULL,
  PRIMARY KEY (`id_ticket`))


DROP TABLE `lineas_ticket` ;
CREATE TABLE `lineas_ticket` (
  `id_ticket` INT NOT NULL,
  `id_articulo` INT NOT NULL,
  `cantidad` INT NULL,
  `devuelto` TINYINT NULL,
  INDEX `fk_ticket_has_articulos_articulos1_idx` (`id_articulo` ASC) VISIBLE,
  INDEX `fk_ticket_has_articulos_ticket_idx` (`id_ticket` ASC) VISIBLE,
  CONSTRAINT `fk_ticket_has_articulos_ticket`
    FOREIGN KEY (`id_ticket`)
    REFERENCES `juguetes`.`tickets` (`id_ticket`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ticket_has_articulos_articulos1`
    FOREIGN KEY (`id_articulo`)
    REFERENCES `juguetes`.`articulos` (`id_articulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)


DROP TABLE `usuarios` ;
CREATE TABLE `usuarios` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `usuario` VARCHAR(35) NULL,
  `contrasena` VARCHAR(50) NULL,
  `nombre` VARCHAR(20) NULL,
  `apellidos` VARCHAR(40) NULL,
  `telefono` VARCHAR(9) NULL,
  `correo` VARCHAR(50) NULL,
  `metodo_pago` VARCHAR(25) NULL,
  PRIMARY KEY (`id_usuario`))


DROP TABLE `carritos` ;
CREATE TABLE `carritos` (
  `id_carrito` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NULL,
  `pagado` TINYINT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_carrito`),
  INDEX `fk_carritos_usuarios1_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_carritos_usuarios1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `juguetes`.`usuarios` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)



DROP TABLE `lineas_carrito` ;
CREATE TABLE `lineas_carrito` (
  `id_articulo` INT NOT NULL,
  `id_carrito` INT NOT NULL,
  `cantidad` INT NULL,
  INDEX `fk_articulos_has_carrito_carrito1_idx` (`id_carrito` ASC) VISIBLE,
  INDEX `fk_articulos_has_carrito_articulos1_idx` (`id_articulo` ASC) VISIBLE,
  CONSTRAINT `fk_articulos_has_carrito_articulos1`
    FOREIGN KEY (`id_articulo`)
    REFERENCES `juguetes`.`articulos` (`id_articulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_articulos_has_carrito_carrito1`
    FOREIGN KEY (`id_carrito`)
    REFERENCES `juguetes`.`carritos` (`id_carrito`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)


DROP TABLE `dts_facturacion` ;
CREATE TABLE `dts_facturacion` (
  `id_datos` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(20) NULL,
  `direccion` VARCHAR(60) NULL,
  `CIF` VARCHAR(9) NULL,
  PRIMARY KEY (`id_datos`),
  UNIQUE INDEX `CIF_UNIQUE` (`CIF` ASC) VISIBLE)


DROP TABLE `facturas` ;
CREATE TABLE `facturas` (
  `id_factura` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NULL,
  `id_datos` INT NOT NULL,
  PRIMARY KEY (`id_factura`),
  INDEX `fk_facturas_dts_facturacion1_idx` (`id_datos` ASC) VISIBLE,
  CONSTRAINT `fk_facturas_dts_facturacion1`
    FOREIGN KEY (`id_datos`)
    REFERENCES `juguetes`.`dts_facturacion` (`id_datos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)



DROP TABLE `dir_envio` ;
CREATE TABLE `dir_envio` (
  `id_direccion` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(60) NULL,
  PRIMARY KEY (`id_direccion`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) VISIBLE)


DROP TABLE `albaranes_v` ;
CREATE TABLE `albaranes_v` (
  `id_albaranes` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NULL,
  `id_carrito` INT NOT NULL,
  `id_factura` INT NOT NULL,
  `id_direccion` INT NOT NULL,
  PRIMARY KEY (`id_albaranes`),
  INDEX `fk_albaranes_v_carritos1_idx` (`id_carrito` ASC) VISIBLE,
  INDEX `fk_albaranes_v_facturas1_idx` (`id_factura` ASC) VISIBLE,
  INDEX `fk_albaranes_v_dir_envio1_idx` (`id_direccion` ASC) VISIBLE,
  CONSTRAINT `fk_albaranes_v_carritos1`
    FOREIGN KEY (`id_carrito`)
    REFERENCES `juguetes`.`carritos` (`id_carrito`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_albaranes_v_facturas1`
    FOREIGN KEY (`id_factura`)
    REFERENCES `juguetes`.`facturas` (`id_factura`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_albaranes_v_dir_envio1`
    FOREIGN KEY (`id_direccion`)
    REFERENCES `juguetes`.`dir_envio` (`id_direccion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)



DROP TABLE `lineas_albaran_v` ;
CREATE TABLE `lineas_albaran_v` (
  `id_albaran` INT NOT NULL,
  `id_articulo` INT NOT NULL,
  `cantidad` INT NULL,
  INDEX `fk_albaranes_v_has_articulos_articulos1_idx` (`id_articulo` ASC) VISIBLE,
  INDEX `fk_albaranes_v_has_articulos_albaranes_v1_idx` (`id_albaran` ASC) VISIBLE,
  CONSTRAINT `fk_albaranes_v_has_articulos_albaranes_v1`
    FOREIGN KEY (`id_albaran`)
    REFERENCES `juguetes`.`albaranes_v` (`id_albaranes`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_albaranes_v_has_articulos_articulos1`
    FOREIGN KEY (`id_articulo`)
    REFERENCES `juguetes`.`articulos` (`id_articulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)



DROP TABLE `usuarios_usa_dir` ;
CREATE TABLE `usuarios_usa_dir` (
  `usuarios_id_usuario` INT NOT NULL,
  `dir_envio_id_direccion` INT NOT NULL,
  INDEX `fk_usuarios_has_dir_envio_dir_envio1_idx` (`dir_envio_id_direccion` ASC) VISIBLE,
  INDEX `fk_usuarios_has_dir_envio_usuarios1_idx` (`usuarios_id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_usuarios_has_dir_envio_usuarios1`
    FOREIGN KEY (`usuarios_id_usuario`)
    REFERENCES `juguetes`.`usuarios` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuarios_has_dir_envio_dir_envio1`
    FOREIGN KEY (`dir_envio_id_direccion`)
    REFERENCES `juguetes`.`dir_envio` (`id_direccion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)


DROP TABLE `usuario_usa_datos` ;
CREATE TABLE `usuario_usa_datos` (
  `usuarios_id_usuario` INT NOT NULL,
  `dts_facturacion_id_datos` INT NOT NULL,
  INDEX `fk_usuarios_has_dts_facturacion_dts_facturacion1_idx` (`dts_facturacion_id_datos` ASC) VISIBLE,
  INDEX `fk_usuarios_has_dts_facturacion_usuarios1_idx` (`usuarios_id_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_usuarios_has_dts_facturacion_usuarios1`
    FOREIGN KEY (`usuarios_id_usuario`)
    REFERENCES `juguetes`.`usuarios` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuarios_has_dts_facturacion_dts_facturacion1`
    FOREIGN KEY (`dts_facturacion_id_datos`)
    REFERENCES `juguetes`.`dts_facturacion` (`id_datos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)

-- INSERCIONES PARA LA SECCIÓN DE TICKETS
INSERT INTO articulos (id_articulo, nombre, precio, stock) VALUES (1, 'Producto A', 10.99, 100);
INSERT INTO articulos (id_articulo, nombre, precio, stock) VALUES (2, 'Producto B', 5.99, 50);
INSERT INTO articulos (id_articulo, nombre, precio, stock) VALUES (3, 'Producto C', 7.50, 75);
INSERT INTO articulos (id_articulo, nombre, precio, stock) VALUES (4, 'Producto D', 12.75, 30);
INSERT INTO articulos (id_articulo, nombre, precio, stock) VALUES (5, 'Producto E', 8.25, 60);

INSERT INTO tickets (id_ticket, fecha, titular, num_tarjeta, tipo_tarjeta) VALUES (1, '2023-10-28', '', '', '');
INSERT INTO tickets (id_ticket, fecha, titular, num_tarjeta, tipo_tarjeta) VALUES (2, '2023-10-29', 'María González', '9876543210987654', 'MasterCard');
INSERT INTO tickets (id_ticket, fecha, titular, num_tarjeta, tipo_tarjeta) VALUES (3, '2023-10-30', '', '', '');
INSERT INTO tickets (id_ticket, fecha, titular, num_tarjeta, tipo_tarjeta) VALUES (4, '2023-10-31', '', '', '');
INSERT INTO tickets (id_ticket, fecha, titular, num_tarjeta, tipo_tarjeta) VALUES (5, '2023-11-01', 'Pedro Sánchez', '9012345678901234', 'MasterCard');

INSERT INTO lineas_ticket (id_ticket, id_articulo, cantidad, devuelto) VALUES (1, 1, 2, 0);
INSERT INTO lineas_ticket (id_ticket, id_articulo, cantidad, devuelto) VALUES (1, 3, 1, 0);
INSERT INTO lineas_ticket (id_ticket, id_articulo, cantidad, devuelto) VALUES (2, 2, 3, 1);
INSERT INTO lineas_ticket (id_ticket, id_articulo, cantidad, devuelto) VALUES (3, 4, 2, 0);
INSERT INTO lineas_ticket (id_ticket, id_articulo, cantidad, devuelto) VALUES (4, 5, 1, 1);


-- CONSULTAS A LAS TABLAS
SELECT * FROM articulos;
SELECT * FROM tickets;
SELECT * FROM lineas_ticket;