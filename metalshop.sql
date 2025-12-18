-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-12-2025 a las 15:35:38
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `metalshop`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autos`
--

CREATE TABLE `autos` (
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `patentes` varchar(6) NOT NULL,
  `modelo` varchar(50) NOT NULL,
  `precio` int(10) UNSIGNED NOT NULL,
  `marca_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `autos`
--

INSERT INTO `autos` (`created_at`, `updated_at`, `patentes`, `modelo`, `precio`, `marca_id`) VALUES
(NULL, NULL, 'ABC123', 'Corolla', 25000, 1),
(NULL, NULL, 'DEF456', 'Focus', 22000, 2),
(NULL, NULL, 'GHI789', 'Civic', 24000, 3),
(NULL, NULL, 'JKL012', 'Malibu', 23000, 4),
(NULL, NULL, 'LMN567', 'Elantra', 20000, 10),
(NULL, NULL, 'MNO345', 'Sentra', 21000, 5),
(NULL, NULL, 'PQR678', 'X3', 45000, 6),
(NULL, NULL, 'STU901', 'C-Class', 50000, 7),
(NULL, NULL, 'VWX234', 'Golf', 28000, 8),
(NULL, NULL, 'YZ1234', 'A4', 32000, 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL,
  `rut_cliente` varchar(10) NOT NULL,
  `nombre_cliente` varchar(244) NOT NULL,
  `direccion_cliente` varchar(244) NOT NULL,
  `estado_cliente` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `rut_cliente`, `nombre_cliente`, `direccion_cliente`, `estado_cliente`) VALUES
(1, '14557302-5', 'Evelyn Paz Fernandez', 'Almte Cochrane casa 6 san marco', 'Activo'),
(3, '10323618-5', 'Rodrigo Figueroa', 'Almirante cochrane casa 6', 'Inactivo'),
(4, '16892634-6', 'Javiera Perez', 'Los Almendros 700', 'Activo'),
(6, '15235957-8', 'Pedro Pe', 'Estrella Olar 567', 'Activo'),
(8, '12586324-6', 'Carlos Guerra', 'Clle Noregua 652', 'Activo'),
(10, '1111111', 'eeeeee', 'hhhhhh', 'Inactivo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle`
--

CREATE TABLE `detalle` (
  `id_detalle` int(11) NOT NULL,
  `venta_detalle` int(11) NOT NULL,
  `producto_detalle` int(11) NOT NULL,
  `cantidad_detalle` int(11) NOT NULL,
  `precio_venta_detalle` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle`
--

INSERT INTO `detalle` (`id_detalle`, `venta_detalle`, `producto_detalle`, `cantidad_detalle`, `precio_venta_detalle`) VALUES
(1, 16, 1, 1, 15000),
(7, 18, 1, 2, 16000),
(8, 18, 2, 4, 15000),
(9, 19, 1, 2, 16000),
(10, 20, 2, 3, 15000),
(11, 21, 1, 2, 16000),
(12, 22, 1, 1, 16000),
(13, 23, 1, 1, 16000),
(14, 24, 2, 1, 15000),
(15, 26, 5, 2, 15000),
(16, 27, 5, 1, 15000),
(17, 28, 2, 3, 15000),
(18, 29, 2, 2, 15000),
(19, 30, 6, 2, 25000),
(20, 31, 6, 2, 25000),
(21, 32, 2, 3, 15000),
(22, 33, 1, 1, 16000),
(23, 34, 1, 1, 16000),
(24, 35, 1, 1, 16000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marcas`
--

CREATE TABLE `marcas` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `marcas`
--

INSERT INTO `marcas` (`id`, `created_at`, `updated_at`, `nombre`, `deleted_at`) VALUES
(1, NULL, NULL, 'TOYOTA', NULL),
(2, NULL, NULL, 'FORD', NULL),
(3, NULL, NULL, 'HONDA', NULL),
(4, NULL, NULL, 'CHEVROLET', NULL),
(5, NULL, NULL, 'NISSAN', NULL),
(6, NULL, NULL, 'BMW', NULL),
(7, NULL, NULL, 'MERCEDES-BENZ', NULL),
(8, NULL, NULL, 'VOLKSWAGEN', NULL),
(9, NULL, NULL, 'AUDI', NULL),
(10, NULL, NULL, 'HYUNDAI', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `migrations`
--

CREATE TABLE `migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(255) NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2024_10_04_120658_create_marcas_table', 1),
(2, '2024_10_04_120736_create_autos_table', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL,
  `nombre_producto` varchar(244) NOT NULL,
  `banda_producto` varchar(244) NOT NULL,
  `album_producto` varchar(244) NOT NULL,
  `edicion_producto` varchar(244) NOT NULL,
  `precio_producto` double NOT NULL,
  `stock_producto` int(11) NOT NULL,
  `estado_producto` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `nombre_producto`, `banda_producto`, `album_producto`, `edicion_producto`, `precio_producto`, `stock_producto`, `estado_producto`) VALUES
(1, 'Cd', 'Paradise Lost', 'Gothic', 'Peaceville 2014', 16000, 2, 'Activo'),
(2, 'Cassette', 'Carcass', 'Heartwork', 'Earache 1994', 15000, 0, 'Activo'),
(4, 'Vinilo 12 Pulgadas', 'Brutal Truth', 'Need to Control', 'Earache 1994', 60000, 1, 'Activo'),
(5, 'Cd', 'Anthrax', 'State of Euphoria', 'Wb 1990', 15000, 1, 'Activo'),
(6, 'Vinilo 10 Pulgadas', 'Aleste', 'Hay un Limite', 'Emi 1992', 25000, 1, 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vendedor`
--

CREATE TABLE `vendedor` (
  `id_vendedor` int(11) NOT NULL,
  `rut_vendedor` varchar(10) NOT NULL,
  `nombre_vendedor` varchar(244) NOT NULL,
  `telefono_vendedor` varchar(12) NOT NULL,
  `estado_vendedor` varchar(20) NOT NULL,
  `user_vendedor` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `vendedor`
--

INSERT INTO `vendedor` (`id_vendedor`, `rut_vendedor`, `nombre_vendedor`, `telefono_vendedor`, `estado_vendedor`, `user_vendedor`) VALUES
(1, '21697281-3', 'Fernanda ', '+56975486523', 'Activo', 'emp01'),
(3, '15235847-6', 'Mauricio', '+56955748615', 'Activo', 'mau1985'),
(4, '24101104-6', 'Felipe', '+56912463587', 'Inactivo', 'pipe2004');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `id_venta` int(11) NOT NULL,
  `cliente_venta` int(11) NOT NULL,
  `vendedor_venta` int(11) NOT NULL,
  `numero_venta` varchar(244) NOT NULL,
  `fecha_venta` varchar(10) NOT NULL,
  `monto_venta` double NOT NULL,
  `estado_venta` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`id_venta`, `cliente_venta`, `vendedor_venta`, `numero_venta`, `fecha_venta`, `monto_venta`, `estado_venta`) VALUES
(16, 3, 1, '1', '2024-04-15', 15000, 'Activo'),
(18, 1, 1, '0000002', '2024-5-21', 92000, 'Activo'),
(19, 1, 1, '0000002', '2024-5-22', 32000, 'Activo'),
(20, 1, 1, '0000002', '2024-5-22', 45000, 'Activo'),
(21, 1, 1, '0000002', '2024-5-22', 32000, 'Activo'),
(22, 1, 1, '0000003', '2024-5-22', 16000, 'Activo'),
(23, 1, 1, '0000004', '2024-5-22', 16000, 'Activo'),
(24, 1, 3, '0000005', '2024-5-22', 15000, 'Activo'),
(25, 1, 3, '0000005', '2024-5-22', 15000, 'Activo'),
(26, 4, 3, '0000006', '2024-5-22', 30000, 'Activo'),
(27, 1, 4, '0000007', '2024-5-22', 15000, 'Activo'),
(28, 3, 4, '0000008', '2024-5-22', 45000, 'Activo'),
(29, 1, 1, '0000009', '2024-5-22', 30000, 'Activo'),
(30, 1, 1, '0000010', '23-5-2024', 50000, 'Activo'),
(31, 1, 1, '0000011', '24-5-2024', 50000, 'Activo'),
(32, 1, 4, '0000012', '25-6-2024', 45000, 'Activo'),
(33, 3, 3, '0000013', '25-6-2024', 16000, 'Activo'),
(34, 1, 1, '0000014', '25-6-2024', 16000, 'Activo'),
(35, 10, 3, '0000015', '7-8-2024', 16000, 'Activo');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `autos`
--
ALTER TABLE `autos`
  ADD PRIMARY KEY (`patentes`),
  ADD KEY `autos_marca_id_foreign` (`marca_id`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`);

--
-- Indices de la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD PRIMARY KEY (`id_detalle`,`venta_detalle`,`producto_detalle`),
  ADD KEY `venta_detalle` (`venta_detalle`),
  ADD KEY `producto_detalle` (`producto_detalle`);

--
-- Indices de la tabla `marcas`
--
ALTER TABLE `marcas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`);

--
-- Indices de la tabla `vendedor`
--
ALTER TABLE `vendedor`
  ADD PRIMARY KEY (`id_vendedor`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`id_venta`),
  ADD KEY `cliente_venta` (`cliente_venta`),
  ADD KEY `vendedor_venta` (`vendedor_venta`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `detalle`
--
ALTER TABLE `detalle`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `marcas`
--
ALTER TABLE `marcas`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `vendedor`
--
ALTER TABLE `vendedor`
  MODIFY `id_vendedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `id_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `autos`
--
ALTER TABLE `autos`
  ADD CONSTRAINT `autos_marca_id_foreign` FOREIGN KEY (`marca_id`) REFERENCES `marcas` (`id`);

--
-- Filtros para la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD CONSTRAINT `detalle_ibfk_1` FOREIGN KEY (`producto_detalle`) REFERENCES `producto` (`id_producto`) ON UPDATE CASCADE,
  ADD CONSTRAINT `detalle_ibfk_2` FOREIGN KEY (`venta_detalle`) REFERENCES `venta` (`id_venta`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`vendedor_venta`) REFERENCES `vendedor` (`id_vendedor`) ON UPDATE CASCADE,
  ADD CONSTRAINT `venta_ibfk_2` FOREIGN KEY (`cliente_venta`) REFERENCES `cliente` (`id_cliente`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
