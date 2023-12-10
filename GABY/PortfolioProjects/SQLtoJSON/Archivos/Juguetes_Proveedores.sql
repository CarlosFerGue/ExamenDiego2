-- ARTICULOS
CREATE TABLE `articulos` (
  `id_articulo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `precio` INT NULL,
  `stock` INT NULL,
  PRIMARY KEY (`id_articulo`));


-- PARTICULARES
CREATE TABLE `particulares` (
  `id_particular` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `DNI` VARCHAR(9) NULL,
  `telefono` VARCHAR(9) NULL,
  `direccion` VARCHAR(60) NULL,
  PRIMARY KEY (`id_particular`));


-- NOTAS PAGO
CREATE TABLE `nota_pago` (
  `id_nota` INT NOT NULL,
  `fecha` DATE NULL,
  `id_particular` INT NOT NULL,
  PRIMARY KEY (`id_nota`),
  INDEX `fk_nota_pago_particulares_idx` (`id_particular` ASC),
  CONSTRAINT `fk_nota_pago_particulares`
    FOREIGN KEY (`id_particular`)
    REFERENCES `particulares` (`id_particular`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- LINEAS NOTA
CREATE TABLE `lineas_nota` (
  `cantidad` INT NULL,
  `id_nota` INT NOT NULL,
  `id_articulo` INT NOT NULL,
  INDEX `fk_lineas_nota_nota_pago1_idx` (`id_nota` ASC),
  INDEX `fk_lineas_nota_articulos1_idx` (`id_articulo` ASC),
  CONSTRAINT `fk_lineas_nota_nota_pago1`
    FOREIGN KEY (`id_nota`)
    REFERENCES `nota_pago` (`id_nota`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lineas_nota_articulos1`
    FOREIGN KEY (`id_articulo`)
    REFERENCES `articulos` (`id_articulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- PROVEEDORES
CREATE TABLE `proveedores` (
  `id_proveedor` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `CIF` VARCHAR(9) NULL,
  `DP` VARCHAR(5) NULL,
  `web` VARCHAR(45) NULL,
  `telefono` VARCHAR(9) NULL,
  `contacto` VARCHAR(45) NULL,
  PRIMARY KEY (`id_proveedor`));


-- PEDIDOS
CREATE TABLE `pedidos` (
  `id_pedido` INT NOT NULL,
  `fecha` DATE NULL,
  `dir_envio` VARCHAR(45) NULL,
  `id_proveedor` INT NOT NULL,
  PRIMARY KEY (`id_pedido`),
  INDEX `fk_pedidos_proveedores1_idx` (`id_proveedor` ASC),
  CONSTRAINT `fk_pedidos_proveedores1`
    FOREIGN KEY (`id_proveedor`)
    REFERENCES `proveedores` (`id_proveedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- ALBARANES
CREATE TABLE `albaranes` (
  `id_albaran` INT NOT NULL,
  `fecha` DATE NULL,
  `aceptado` TINYINT NULL,
  `id_pedido` INT NOT NULL,
  `id_factura` INT NULL,
  PRIMARY KEY (`id_albaran`),
  INDEX `fk_albaranes_pedidos1_idx` (`id_pedido` ASC),
  INDEX `fk_albaranes_factura1_idx` (`id_factura` ASC),
  CONSTRAINT `fk_albaranes_pedidos1`
    FOREIGN KEY (`id_pedido`)
    REFERENCES `pedidos` (`id_pedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_albaranes_factura1`
    FOREIGN KEY (`id_factura`)
    REFERENCES `factura` (`id_factura`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- FACTURA
CREATE TABLE IF NOT EXISTS `juguetes_compras`.`factura` (
  `id_factura` INT NOT NULL,
  `fecha` DATE NULL,
  `metodo_pago` VARCHAR(45) NULL,
  PRIMARY KEY (`id_factura`));


-- LINEAS ALBARAN
CREATE TABLE `lineas_albaran` (
  `id_articulo` INT NOT NULL,
  `id_albaran` INT NOT NULL,
  `cantidad` INT NULL,
  INDEX `fk_articulos_has_albaranes_albaranes1_idx` (`id_albaran` ASC),
  INDEX `fk_articulos_has_albaranes_articulos1_idx` (`id_articulo` ASC),
  CONSTRAINT `fk_articulos_has_albaranes_articulos1`
    FOREIGN KEY (`id_articulo`)
    REFERENCES `articulos` (`id_articulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_articulos_has_albaranes_albaranes1`
    FOREIGN KEY (`id_albaran`)
    REFERENCES `albaranes` (`id_albaran`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- LINEAS PEDIDO
CREATE TABLE `lineas_pedido` (
  `id_articulo` INT NOT NULL,
  `id_pedido` INT NOT NULL,
  `cantidad` INT NULL,
  INDEX `fk_articulos_has_pedidos_pedidos1_idx` (`id_pedido` ASC),
  INDEX `fk_articulos_has_pedidos_articulos1_idx` (`id_articulo` ASC),
  CONSTRAINT `fk_articulos_has_pedidos_articulos1`
    FOREIGN KEY (`id_articulo`)
    REFERENCES `articulos` (`id_articulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_articulos_has_pedidos_pedidos1`
    FOREIGN KEY (`id_pedido`)
    REFERENCES `pedidos` (`id_pedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
