-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-09-2024 a las 06:07:56
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
-- Base de datos: `taskflow`
--
CREATE DATABASE IF NOT EXISTS `taskflow` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `taskflow`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dbo_tasks`
--

CREATE TABLE `dbo_tasks` (
  `id_task` bigint(20) NOT NULL,
  `date_create` varchar(10) DEFAULT NULL,
  `date_expiration` varchar(10) DEFAULT NULL,
  `description` varchar(1500) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `id_usuario` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `dbo_tasks`
--

INSERT INTO `dbo_tasks` (`id_task`, `date_create`, `date_expiration`, `description`, `name`, `status`, `id_usuario`) VALUES
(1, '2024-09-03', '2024-10-03', 'Creacion de API SpringBoot para control y registro de tareas y seguimientod e las mismas', 'taskflow.api', '1', 1),
(2, '2024-09-03', '2024-11-03', 'API SpringBoot para control de la aplicación web que permite a los usuarios reservar habitaciones de hotel, verificar disponibilidad y gestionar sus reservas.', 'reservite.api', '1', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dbo_task_followups`
--

CREATE TABLE `dbo_task_followups` (
  `id_task_followup` bigint(20) NOT NULL,
  `date_create` varchar(10) DEFAULT NULL,
  `description` varchar(1500) DEFAULT NULL,
  `id_task` bigint(20) NOT NULL,
  `id_usuario` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dbo_users`
--

CREATE TABLE `dbo_users` (
  `id_usuario` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `dbo_users`
--

INSERT INTO `dbo_users` (`id_usuario`, `name`, `password`, `role`, `username`) VALUES
(1, 'Eliezer Navarro Pérez', '$2a$10$DRtD6u4iWycTIrKGcrYuTOITp0JZq88lClhTIgzJ3YMvzlB7LnWOG', 'USER', 'enp');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `dbo_tasks`
--
ALTER TABLE `dbo_tasks`
  ADD PRIMARY KEY (`id_task`),
  ADD KEY `FKau1nhjty23v4v8n2nkytfb647` (`id_usuario`);

--
-- Indices de la tabla `dbo_task_followups`
--
ALTER TABLE `dbo_task_followups`
  ADD PRIMARY KEY (`id_task_followup`),
  ADD KEY `FK473ommd02fmlwhih73tpv1ifn` (`id_task`),
  ADD KEY `FK6e7yvxnhc1jsksecf3avp41eu` (`id_usuario`);

--
-- Indices de la tabla `dbo_users`
--
ALTER TABLE `dbo_users`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `dbo_tasks`
--
ALTER TABLE `dbo_tasks`
  MODIFY `id_task` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `dbo_task_followups`
--
ALTER TABLE `dbo_task_followups`
  MODIFY `id_task_followup` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `dbo_users`
--
ALTER TABLE `dbo_users`
  MODIFY `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `dbo_tasks`
--
ALTER TABLE `dbo_tasks`
  ADD CONSTRAINT `FKau1nhjty23v4v8n2nkytfb647` FOREIGN KEY (`id_usuario`) REFERENCES `dbo_users` (`id_usuario`);

--
-- Filtros para la tabla `dbo_task_followups`
--
ALTER TABLE `dbo_task_followups`
  ADD CONSTRAINT `FK473ommd02fmlwhih73tpv1ifn` FOREIGN KEY (`id_task`) REFERENCES `dbo_tasks` (`id_task`),
  ADD CONSTRAINT `FK6e7yvxnhc1jsksecf3avp41eu` FOREIGN KEY (`id_usuario`) REFERENCES `dbo_users` (`id_usuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
