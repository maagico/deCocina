-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Jan 17, 2019 at 09:56 PM
-- Server version: 5.7.23
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `decocina`
--
CREATE DATABASE IF NOT EXISTS `decocina` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `decocina`;
 
-- --------------------------------------------------------

--
-- Table structure for table `administradores`
--

DROP TABLE IF EXISTS `administradores`;
CREATE TABLE `administradores` (
  `id` int(10) UNSIGNED NOT NULL,
  `id_alt` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `usuario` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `administradores`
--

INSERT INTO `administradores` (`id`, `id_alt`, `usuario`, `password`, `token`) VALUES
(1, 'OIXTYEDFR', 'superuser', '0baea2f0ae20150db78f58cddac442a9', '4e582fc3a7bea664a13818700c8e9511');

-- --------------------------------------------------------

--
-- Table structure for table `articulos`
--

DROP TABLE IF EXISTS `articulos`;
CREATE TABLE `articulos` (
  `id` int(10) NOT NULL,
  `nombre` varchar(150) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `meta_descripcion` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `meta_keywords` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `url_amigable` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `descripcion` mediumtext COLLATE utf8_unicode_ci,
  `fabricante_id` int(10) DEFAULT NULL,
  `categoria_id` int(10) DEFAULT NULL,
  `precio` double(10,2) NOT NULL,
  `oferta` double(10,2) DEFAULT NULL,
  `peso` double NOT NULL,
  `impuesto_id` int(10) NOT NULL,
  `descripcion_corta` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `cantidad` int(10) UNSIGNED NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  `orden` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `destacado` int(1) NOT NULL DEFAULT '0',
  `visitas` int(11) NOT NULL DEFAULT '0',
  `ventas` int(10) DEFAULT NULL,
  `fecha` datetime NOT NULL,
  `fecha_modificacion` datetime NOT NULL,
  `twitter_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='InnoDB free: 4096 kB; (`img_p_id`) REFER `ucocina/imagenes`(' ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `articulos`
--

INSERT INTO `articulos` (`id`, `nombre`, `meta_descripcion`, `meta_keywords`, `url_amigable`, `descripcion`, `fabricante_id`, `categoria_id`, `precio`, `oferta`, `peso`, `impuesto_id`, `descripcion_corta`, `cantidad`, `activo`, `orden`, `destacado`, `visitas`, `ventas`, `fecha`, `fecha_modificacion`, `twitter_id`) VALUES
(1154, 'Cuchillo para queso', 'Cuchillo para cortar queso con hoja perforada y dentada.', 'Cuchillos de cocina, cuchillería', 'cuchillo-para-queso-a-1154', '<p><span style=\"color: #333333; font-family: \'Helvetica Neue\', Helvetica, Arial, sans-serif; font-size: 14px;\">Cuchillo para cortar queso con hoja perforada y dentada y punta doble tipo tenedor que nos permite pinchar los quesos una vez cortados. perfecto para cortar y sevrvir quesos blandos</span></p>', NULL, 188, 123.00, NULL, 100, 1, 'Cuchillo para cortar queso con hoja perforada y dentada y punta doble tipo tenedor que nos permite pinchar los quesos una vez cortados. perfecto para cortar y sevrvir quesos blandos', 10, 1, 1, 0, 0, 0, '2019-01-17 00:00:00', '2019-01-17 22:48:31', NULL),
(1155, 'Kit de sferificación básica', 'Se trata de la gelificación controlada de un líquido que, sumergido en un  baño forma esferas.', 'Kit de sferificación básica, sferificación', 'kit-de-sferificacion-basica-a-1155', '<p><span style=\"color: #333333; font-family: \'Helvetica Neue\', Helvetica, Arial, sans-serif; font-size: 14px;\">Se trata de la gelificaci&oacute;n controlada de un l&iacute;quido que, sumergido en un ba&ntilde;o forma esferas.</span></p>', NULL, 192, 20.00, NULL, 100, 1, 'Se trata de la gelificación controlada de un líquido que, sumergido en un  baño forma esferas.', 10, 1, 1, 0, 0, 0, '2019-01-17 00:00:00', '2019-01-17 22:49:07', NULL),
(1156, 'Kit de sferificación inversa', 'La sferificación Inversa es de una técnica de gran versatilidad.', 'sferificación Inversa, sferificación', 'kit-de-sferificacion-inversa-a-1156', '<p>La sferificaci&oacute;n Inversa es de una t&eacute;cnica de gran versatilidad, ya que permite hacer esferas con casi todos los productos. Est&aacute; especialmente indicada para productos con mucho contenido de calcio o bien de alcohol.</p>', NULL, 192, 20.00, NULL, 100, 1, 'La sferificación Inversa es de una técnica de gran versatilidad.', 10, 1, 1, 0, 0, 0, '2019-01-17 00:00:00', '2019-01-17 22:53:17', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `articulo_pedido`
--

DROP TABLE IF EXISTS `articulo_pedido`;
CREATE TABLE `articulo_pedido` (
  `id` int(11) NOT NULL,
  `pedido_id` int(11) NOT NULL,
  `articulo_id` int(10) NOT NULL,
  `uri` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `precio` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `iva` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `cantidad` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `precio_total` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
CREATE TABLE `categorias` (
  `id` int(10) NOT NULL,
  `meta_descripcion` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `meta_keywords` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `descripcion` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `orden` int(1) NOT NULL,
  `url_amigable` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `activa` int(1) NOT NULL DEFAULT '0',
  `id_padre` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `categorias`
--

INSERT INTO `categorias` (`id`, `meta_descripcion`, `meta_keywords`, `descripcion`, `nombre`, `orden`, `url_amigable`, `activa`, `id_padre`) VALUES
(160, 'Cuchillos japoneses, alemanes y muchas más variedades', 'Cuchillos japoneses, cuchillos Global', 'Cuchillos japoneses, alemanes y muchas más variedades', 'Cuchillería', 100, 'cuchilleria-c-160', 1, NULL),
(188, 'Los cuchillos japoneses se caracterizan por su afilado incomparable ya que facilita los cortes más difíciles como ningún otro.', 'Cuchillos japoneses, cuchillos de calidad', 'Los cuchillos japoneses se caracterizan por su afilado incomparable ya que facilita los cortes más difíciles como ningún otro, gracias al filo que posee y su peso equilibrado permite que los trabajos sean sencillos sin tanto esfuerzo. No solo facilita el trabajo sino que además permite una buena terminación del mismo.	', 'Cuchillos japoneses', 1, 'cuchillos-japoneses-c-188', 1, 160),
(191, 'El termino de cocina molecular se usa usualmente para describir el estilo de cocina que algunos chef exploran varias formas culinarias con la ayuda de la herramientas del laboratorio de ciencias y algunos ingredientes de la industria de alimentación.', 'Cocina molecular', 'El termino de cocina molecular se usa usualmente para describir el estilo de cocina que algunos chef exploran varias formas culinarias con la ayuda de la herramientas del laboratorio de ciencias y algunos ingredientes de la industria de alimentación.', 'Cocina molecular', 2, 'cocina-molecular-c-191', 1, NULL),
(192, 'Kits para cocina molecular', 'Kits de cocina molecular', 'Kits para cocina molecular', 'Kits', 1, 'kits-c-192', 1, 191);

-- --------------------------------------------------------

--
-- Table structure for table `cestas`
--

DROP TABLE IF EXISTS `cestas`;
CREATE TABLE `cestas` (
  `id` int(11) NOT NULL,
  `cliente_id` int(11) NOT NULL,
  `articulo_id` int(10) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE `clientes` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `apellidos` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `telefono` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `direccion_id` int(11) DEFAULT NULL,
  `token_rp` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Token para la recuperación de la contraseña',
  `fecha_rp` datetime DEFAULT NULL,
  `borrado` int(1) DEFAULT NULL,
  `tratamiento_fiscal_id` int(1) DEFAULT NULL,
  `nif` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cif` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nombre_empresa` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `clientes`
--

INSERT INTO `clientes` (`id`, `nombre`, `apellidos`, `email`, `telefono`, `password`, `direccion_id`, `token_rp`, `fecha_rp`, `borrado`, `tratamiento_fiscal_id`, `nif`, `cif`, `nombre_empresa`) VALUES
(1, 'Admin', 'Admin', 'admin@decocina.es', '123456789', 'd1fa0dde783f4045c5dc196f34c4814b', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `cupones`
--

DROP TABLE IF EXISTS `cupones`;
CREATE TABLE `cupones` (
  `id` int(10) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `descuento` double(10,2) NOT NULL,
  `fecha_desde` date DEFAULT NULL,
  `fecha_hasta` date DEFAULT NULL,
  `precio_minimo` double(10,2) DEFAULT NULL,
  `numero_usos` int(11) DEFAULT NULL,
  `tipo_importe` int(11) NOT NULL,
  `descripcion` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cupones`
--

INSERT INTO `cupones` (`id`, `nombre`, `descuento`, `fecha_desde`, `fecha_hasta`, `precio_minimo`, `numero_usos`, `tipo_importe`, `descripcion`) VALUES
(5, 'deCocina', 8.00, NULL, NULL, 60.00, NULL, 1, '8€ de descuento para pedidos superiores a 60€'),
(6, 'Cupón de Prueba', 5.51, '2014-12-12', '2015-12-25', 10.00, 314, 1, 'Cupón de prueba de 5.51€');

-- --------------------------------------------------------

--
-- Table structure for table `direcciones`
--

DROP TABLE IF EXISTS `direcciones`;
CREATE TABLE `direcciones` (
  `id` int(11) NOT NULL,
  `calle` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `codigo_postal` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `zona_id` int(11) NOT NULL,
  `poblacion` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `cliente_id` int(10) DEFAULT NULL,
  `nombre` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `apellidos` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pais_id` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `estados`
--

DROP TABLE IF EXISTS `estados`;
CREATE TABLE `estados` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `descripcion` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `estados`
--

INSERT INTO `estados` (`id`, `nombre`, `descripcion`) VALUES
(1, 'Pendiente', NULL),
(2, 'Procesado', NULL),
(3, 'Enviado', NULL),
(4, 'Entregado', NULL),
(5, 'Cancelado', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `fabricantes`
--

DROP TABLE IF EXISTS `fabricantes`;
CREATE TABLE `fabricantes` (
  `id` int(10) NOT NULL,
  `nombre` varchar(99) COLLATE utf8_unicode_ci NOT NULL,
  `descripcion` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `fabricantes`
--

INSERT INTO `fabricantes` (`id`, `nombre`, `descripcion`) VALUES
(4, 'Arcoroc', 'Fabricante de vajilla'),
(5, 'San Ignacio', 'Varios productos para la cocina'),
(6, 'Global', 'Los cuchillos Global, afilados y excepcionalmente ligeros, son únicos en el mundo de la cuchillería fina y profesional.'),
(7, 'Microplane', 'Los ralladores microplane son los mas afilados del mercado y elejidos por los mejores chefs del mundo'),
(8, 'Lacor', 'Lacor es uno de los principales fabricantes de menaje y utensilios de cocina de alta gama'),
(9, 'Kyocera', 'fabricante de cuchillos de ceramica de las alta calidad'),
(10, 'Lekue', 'Líder mundial en la creación de productos de calidad y diseño en silicona para disfrutar de tu hogar'),
(11, 'Chefs Choice', 'Lider mundial en fabricacion de afiladores de chuchillos, sus hojas de diamante permiter afilar todo tipo de cuchillos.'),
(12, 'Westmark', 'fabricante de peladores y todo tipo de utensilios de cocina'),
(13, 'Robot Coupe', 'El mayor fabricante de brazos trituradores y robots de cocina del mundo. La mejor calidad solo en losutensiliosdecocina.es'),
(15, 'EL corte ingles', ''),
(16, 'Hotery', 'Uno de los mejores sopletes de cocina');

-- --------------------------------------------------------

--
-- Table structure for table `formas_envio`
--

DROP TABLE IF EXISTS `formas_envio`;
CREATE TABLE `formas_envio` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `descripcion` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `formula` varchar(1024) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `formas_envio`
--

INSERT INTO `formas_envio` (`id`, `nombre`, `descripcion`, `formula`) VALUES
(1, 'Mensajería urgente', 'Plazo de entrega en 24/48H', 'es.cgarcia.decocina.admin.formula.envio.MensajeriaUrgenteFE'),
(3, 'Recoger el pedido en nuestra tienda', '', 'es.cgarcia.decocina.admin.formula.envio.RecogerTiendaFE'),
(6, 'Mensajería urgente Baleares', 'Gastos de envío para Baleares a 15€.', 'es.cgarcia.decocina.admin.formula.envio.MensajeriaUrgenteBalearesFE');

-- --------------------------------------------------------

--
-- Table structure for table `formas_envio_formas_pago`
--

DROP TABLE IF EXISTS `formas_envio_formas_pago`;
CREATE TABLE `formas_envio_formas_pago` (
  `id` int(11) NOT NULL,
  `zona_id` int(11) NOT NULL,
  `forma_envio_id` int(11) NOT NULL,
  `forma_pago_id` int(11) NOT NULL,
  `orden` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `formas_envio_formas_pago`
--

INSERT INTO `formas_envio_formas_pago` (`id`, `zona_id`, `forma_envio_id`, `forma_pago_id`, `orden`) VALUES
(27, 150, 1, 3, 0),
(30, 150, 1, 4, 0),
(38, 150, 1, 1, 0),
(47, 32, 3, 5, 0),
(48, 32, 1, 4, 0),
(49, 32, 1, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `formas_pago`
--

DROP TABLE IF EXISTS `formas_pago`;
CREATE TABLE `formas_pago` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `descripcion` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `formula` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `comentario` varchar(2048) COLLATE utf8_unicode_ci DEFAULT NULL,
  `paso_adicional` int(1) NOT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nombre_boton` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `test` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `formas_pago`
--

INSERT INTO `formas_pago` (`id`, `nombre`, `descripcion`, `formula`, `comentario`, `paso_adicional`, `url`, `nombre_boton`, `test`) VALUES
(1, 'Contrareembolso', 'El pago contrareembolso tiene un recargo de <span class = \"precio-realizar-pedido\">${1}€</span>. ', 'es.cgarcia.decocina.admin.formula.pago.ContrareembolsoFP', '', 0, '', 'Confirmar y finalizar', 0),
(3, 'Transferencia bancaria', '', 'es.cgarcia.decocina.admin.formula.pago.TransferenciaBancariaFP', 'Banco de Santander<br>\r\nNúmero de cuenta : 0000 0000 00 1234567890<br>\r\nEl pedido se enviará cuando se reciba el comprobante de pago,<br>\r\nPuedes enviar el comprobante por email a deCocinaPFC@gmail.es o por teléfono al número de fax 91 0123 45 67', 0, '', 'Confirmar y finalizar', 0),
(4, 'Paypal', '', 'es.cgarcia.decocina.admin.formula.pago.PayPalFP', '', 1, 'pago-paypal', 'Confirmar y pagar con Paypal', 1),
(5, 'Pago en nuestra tienda al recoger el pedido', '', 'es.cgarcia.decocina.admin.formula.pago.RecogerTiendaFP', '', 0, '', 'Confirmar y finalizar', 0);

-- --------------------------------------------------------

--
-- Table structure for table `imagenes`
--

DROP TABLE IF EXISTS `imagenes`;
CREATE TABLE `imagenes` (
  `id` int(10) NOT NULL,
  `articulo_id` int(11) DEFAULT NULL,
  `title` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `uri` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `alt` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tipo` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `principal` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `imagenes`
--

INSERT INTO `imagenes` (`id`, `articulo_id`, `title`, `uri`, `alt`, `nombre`, `tipo`, `principal`) VALUES
(5295, 1154, 'Cuchillo para queso_3969', 'img/articulos/Cuchillo_para_queso_3969.jpg', 'Cuchillo para queso_3969', 'Cuchillo_para_queso_3969.jpg', 'G', 1),
(5296, 1154, 'Cuchillo para queso_3969', 'img/articulos/Cuchillo_para_queso_3969_p.jpg', 'Cuchillo para queso_3969', 'Cuchillo_para_queso_3969_p.jpg', 'P', 1),
(5297, 1154, 'Cuchillo para queso_3969', 'img/articulos/Cuchillo_para_queso_3969_t.jpg', 'Cuchillo para queso_3969', 'Cuchillo_para_queso_3969_t.jpg', 'T', 1),
(5298, 1155, 'Kit de sferificación básica_3273', 'img/articulos/Kit_de_sferificacion_basica_3273.jpg', 'Kit de sferificación básica_3273', 'Kit_de_sferificacion_basica_3273.jpg', 'G', 1),
(5299, 1155, 'Kit de sferificación básica_3273', 'img/articulos/Kit_de_sferificacion_basica_3273_p.jpg', 'Kit de sferificación básica_3273', 'Kit_de_sferificacion_basica_3273_p.jpg', 'P', 1),
(5300, 1155, 'Kit de sferificación básica_3273', 'img/articulos/Kit_de_sferificacion_basica_3273_t.jpg', 'Kit de sferificación básica_3273', 'Kit_de_sferificacion_basica_3273_t.jpg', 'T', 1),
(5301, 1156, 'Kit de sferificación inversa_4353', 'img/articulos/Kit_de_sferificacion_inversa_4353.jpg', 'Kit de sferificación inversa_4353', 'Kit_de_sferificacion_inversa_4353.jpg', 'G', 1),
(5302, 1156, 'Kit de sferificación inversa_4353', 'img/articulos/Kit_de_sferificacion_inversa_4353_p.jpg', 'Kit de sferificación inversa_4353', 'Kit_de_sferificacion_inversa_4353_p.jpg', 'P', 1),
(5303, 1156, 'Kit de sferificación inversa_4353', 'img/articulos/Kit_de_sferificacion_inversa_4353_t.jpg', 'Kit de sferificación inversa_4353', 'Kit_de_sferificacion_inversa_4353_t.jpg', 'T', 1);

-- --------------------------------------------------------

--
-- Table structure for table `impuestos`
--

DROP TABLE IF EXISTS `impuestos`;
CREATE TABLE `impuestos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `valor` decimal(8,2) NOT NULL,
  `descripcion` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `para_articulo` int(1) NOT NULL,
  `para_zona` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `impuestos`
--

INSERT INTO `impuestos` (`id`, `nombre`, `valor`, `descripcion`, `para_articulo`, `para_zona`) VALUES
(1, '21% España', '21.00', 'Impuesto del 21% para España', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
CREATE TABLE `pedidos` (
  `id` int(11) NOT NULL,
  `cliente_id` int(11) NOT NULL,
  `nombre_cliente` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nombre_empresa` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pais` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `zona` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `poblacion` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `codigo_postal` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `telefono` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `nombre_forma_pago` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `precio_forma_pago` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `descripcion_forma_pago` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `comentario_forma_pago` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nombre_forma_envio` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `descripcion_forma_envio` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `precio_forma_envio` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `fecha_creacion` datetime NOT NULL,
  `fecha_modificacion` datetime NOT NULL,
  `iva_total` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `precio_total` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `comentario` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pedido_estado_historial_id` int(11) DEFAULT NULL,
  `precio_total_cesta` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `peso_forma_envio` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `calle` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `observaciones` varchar(2048) COLLATE utf8_unicode_ci DEFAULT NULL,
  `iva_direccion` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `cupon` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `descuento` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `porcentaje_descuento` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `borrado` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pedido_estado_historial`
--

DROP TABLE IF EXISTS `pedido_estado_historial`;
CREATE TABLE `pedido_estado_historial` (
  `id` int(11) NOT NULL,
  `pedido_id` int(11) NOT NULL,
  `estado_id` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `cliente_notificado` int(11) NOT NULL,
  `comentario` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `zonas`
--

DROP TABLE IF EXISTS `zonas`;
CREATE TABLE `zonas` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `zona_padre_id` int(11) DEFAULT NULL,
  `heredar_formas` int(11) NOT NULL,
  `activa` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `zonas`
--

INSERT INTO `zonas` (`id`, `nombre`, `zona_padre_id`, `heredar_formas`, `activa`) VALUES
(10, 'Barcelona', 150, 1, 1),
(32, 'Madrid', 150, 0, 1),
(150, 'España', NULL, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `zona_forma_envio`
--

DROP TABLE IF EXISTS `zona_forma_envio`;
CREATE TABLE `zona_forma_envio` (
  `id` int(11) NOT NULL,
  `zona_id` int(11) NOT NULL,
  `forma_envio_id` int(11) NOT NULL,
  `orden` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `zona_forma_envio`
--

INSERT INTO `zona_forma_envio` (`id`, `zona_id`, `forma_envio_id`, `orden`) VALUES
(17, 150, 1, 0),
(27, 32, 3, 0),
(28, 32, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `zona_impuestos`
--

DROP TABLE IF EXISTS `zona_impuestos`;
CREATE TABLE `zona_impuestos` (
  `id` int(11) NOT NULL,
  `zona_id` int(11) NOT NULL,
  `impuesto_id` int(11) NOT NULL,
  `prioridad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `zona_impuestos`
--

INSERT INTO `zona_impuestos` (`id`, `zona_id`, `impuesto_id`, `prioridad`) VALUES
(33, 150, 1, 0),
(45, 32, 1, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administradores`
--
ALTER TABLE `administradores`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `articulos`
--
ALTER TABLE `articulos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_categoria_articulo_idx` (`categoria_id`),
  ADD KEY `fk_impuesto_articulo_idx` (`impuesto_id`),
  ADD KEY `fk_fabricante_articulo_idx` (`fabricante_id`),
  ADD KEY `ventas_indx` (`ventas`),
  ADD KEY `fecha_a_indx` (`activo`,`fecha`);

--
-- Indexes for table `articulo_pedido`
--
ALTER TABLE `articulo_pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_pedido_id` (`pedido_id`),
  ADD KEY `articulo_id_indx` (`articulo_id`) USING BTREE;

--
-- Indexes for table `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_categorias_id_id_padre_idx` (`id_padre`);

--
-- Indexes for table `cestas`
--
ALTER TABLE `cestas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_cestas_cliente_id` (`cliente_id`),
  ADD KEY `fk_cestas_articulo_id` (`articulo_id`);

--
-- Indexes for table `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_clientes_direccion_id` (`direccion_id`);

--
-- Indexes for table `cupones`
--
ALTER TABLE `cupones`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `direcciones`
--
ALTER TABLE `direcciones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_direcciones_provincia_id` (`zona_id`),
  ADD KEY `fk_clientes_direcciones_idx` (`cliente_id`);

--
-- Indexes for table `estados`
--
ALTER TABLE `estados`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `fabricantes`
--
ALTER TABLE `fabricantes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `formas_envio`
--
ALTER TABLE `formas_envio`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `formas_envio_formas_pago`
--
ALTER TABLE `formas_envio_formas_pago`
  ADD PRIMARY KEY (`id`),
  ADD KEY `forma_pago_id` (`forma_pago_id`),
  ADD KEY `zona_id` (`zona_id`),
  ADD KEY `forma_envio_id` (`forma_envio_id`);

--
-- Indexes for table `formas_pago`
--
ALTER TABLE `formas_pago`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `imagenes`
--
ALTER TABLE `imagenes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_imagenes_articulos_idx` (`articulo_id`);

--
-- Indexes for table `impuestos`
--
ALTER TABLE `impuestos`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_pedidos_peh_id` (`pedido_estado_historial_id`),
  ADD KEY `fk_pedidos_cliente_id` (`cliente_id`);

--
-- Indexes for table `pedido_estado_historial`
--
ALTER TABLE `pedido_estado_historial`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_peh_pedido_id` (`pedido_id`),
  ADD KEY `fk_estados_pef_idx` (`estado_id`);

--
-- Indexes for table `zonas`
--
ALTER TABLE `zonas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `zona_padre_id` (`zona_padre_id`);

--
-- Indexes for table `zona_forma_envio`
--
ALTER TABLE `zona_forma_envio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `zona_id` (`zona_id`),
  ADD KEY `forma_envio_id` (`forma_envio_id`);

--
-- Indexes for table `zona_impuestos`
--
ALTER TABLE `zona_impuestos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `zona_id` (`zona_id`),
  ADD KEY `impuesto_id` (`impuesto_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `administradores`
--
ALTER TABLE `administradores`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `articulos`
--
ALTER TABLE `articulos`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1157;

--
-- AUTO_INCREMENT for table `articulo_pedido`
--
ALTER TABLE `articulo_pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=788;

--
-- AUTO_INCREMENT for table `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=193;

--
-- AUTO_INCREMENT for table `cestas`
--
ALTER TABLE `cestas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=709;

--
-- AUTO_INCREMENT for table `cupones`
--
ALTER TABLE `cupones`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `direcciones`
--
ALTER TABLE `direcciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=709;

--
-- AUTO_INCREMENT for table `estados`
--
ALTER TABLE `estados`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `fabricantes`
--
ALTER TABLE `fabricantes`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `formas_envio`
--
ALTER TABLE `formas_envio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `formas_envio_formas_pago`
--
ALTER TABLE `formas_envio_formas_pago`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT for table `formas_pago`
--
ALTER TABLE `formas_pago`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `imagenes`
--
ALTER TABLE `imagenes`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5304;

--
-- AUTO_INCREMENT for table `impuestos`
--
ALTER TABLE `impuestos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1546;

--
-- AUTO_INCREMENT for table `pedido_estado_historial`
--
ALTER TABLE `pedido_estado_historial`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=746;

--
-- AUTO_INCREMENT for table `zonas`
--
ALTER TABLE `zonas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=160;

--
-- AUTO_INCREMENT for table `zona_forma_envio`
--
ALTER TABLE `zona_forma_envio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `zona_impuestos`
--
ALTER TABLE `zona_impuestos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `articulos`
--
ALTER TABLE `articulos`
  ADD CONSTRAINT `fk_categoria_articulo` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_fabricante_articulo` FOREIGN KEY (`fabricante_id`) REFERENCES `fabricantes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_impuesto_articulo` FOREIGN KEY (`impuesto_id`) REFERENCES `impuestos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `articulo_pedido`
--
ALTER TABLE `articulo_pedido`
  ADD CONSTRAINT `fk_articulos_ap` FOREIGN KEY (`articulo_id`) REFERENCES `articulos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_pedido_ap` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `categorias`
--
ALTER TABLE `categorias`
  ADD CONSTRAINT `fk_categorias_id_id_padre` FOREIGN KEY (`id_padre`) REFERENCES `categorias` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `cestas`
--
ALTER TABLE `cestas`
  ADD CONSTRAINT `fk_articulos_cesta` FOREIGN KEY (`articulo_id`) REFERENCES `articulos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_clientes_cesta` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `direcciones`
--
ALTER TABLE `direcciones`
  ADD CONSTRAINT `fk_clientes_direcciones` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_zonas_direcciones` FOREIGN KEY (`zona_id`) REFERENCES `zonas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `formas_envio_formas_pago`
--
ALTER TABLE `formas_envio_formas_pago`
  ADD CONSTRAINT `fk_fe_fefp` FOREIGN KEY (`forma_envio_id`) REFERENCES `formas_envio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_fp_fefp` FOREIGN KEY (`forma_pago_id`) REFERENCES `formas_pago` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_zonas_fefp` FOREIGN KEY (`zona_id`) REFERENCES `zonas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `imagenes`
--
ALTER TABLE `imagenes`
  ADD CONSTRAINT `fk_imagenes_articulos` FOREIGN KEY (`articulo_id`) REFERENCES `articulos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `fk_clientes_pedidos` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `pedido_estado_historial`
--
ALTER TABLE `pedido_estado_historial`
  ADD CONSTRAINT `fk_estados_pef` FOREIGN KEY (`estado_id`) REFERENCES `estados` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_pedidos_peh` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `zona_forma_envio`
--
ALTER TABLE `zona_forma_envio`
  ADD CONSTRAINT `fk_fe_zfe` FOREIGN KEY (`forma_envio_id`) REFERENCES `formas_envio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_zonas_zfe` FOREIGN KEY (`zona_id`) REFERENCES `zonas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `zona_impuestos`
--
ALTER TABLE `zona_impuestos`
  ADD CONSTRAINT `fk_impuestos_zi` FOREIGN KEY (`impuesto_id`) REFERENCES `impuestos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_zonas_zi` FOREIGN KEY (`zona_id`) REFERENCES `zonas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
