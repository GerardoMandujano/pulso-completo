-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: pulso_db
-- ------------------------------------------------------
-- Server version	11.5.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actividad`
--

DROP TABLE IF EXISTS `actividad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actividad` (
  `id_actividad` int(11) NOT NULL AUTO_INCREMENT,
  `id_perfil_coach` int(11) NOT NULL,
  `nombre` varchar(500) NOT NULL,
  `tipo` varchar(250) NOT NULL,
  `grupo_muscular` varchar(250) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  `activo` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_actividad`),
  KEY `id_perfil_coauch` (`id_perfil_coach`),
  CONSTRAINT `actividad_ibfk_1` FOREIGN KEY (`id_perfil_coach`) REFERENCES `perfil_coach` (`id_perfil_coach`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividad`
--

LOCK TABLES `actividad` WRITE;
/*!40000 ALTER TABLE `actividad` DISABLE KEYS */;
INSERT INTO `actividad` VALUES (1,1,'sentadilla','gym','PIERNA','sentadilla con barra','2026-01-14 01:27:39',0),(2,1,'box','clase','CARDIO','Clase de Box 45 min','2026-01-14 01:27:39',1),(3,1,'levantamiento de peso','clase','todo','Levantamiento de mancuerna con compa invertida','2026-02-25 23:50:03',1),(4,1,'crossfit','clase','todo','Clase de crosfit','2026-02-27 02:28:24',1),(5,1,'Clase de baile','clase','todo','Una clse de baile que te hara sudar','2026-02-27 02:29:53',1);
/*!40000 ALTER TABLE `actividad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actividad_rutina`
--

DROP TABLE IF EXISTS `actividad_rutina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actividad_rutina` (
  `id_actividad_rutina` int(11) NOT NULL AUTO_INCREMENT,
  `id_dia_rutina` int(11) NOT NULL,
  `id_actividad` int(11) NOT NULL,
  `series` int(11) NOT NULL,
  `repeticiones` int(11) DEFAULT NULL,
  `duracion_minutos` int(11) DEFAULT NULL,
  `descanso` int(11) DEFAULT NULL,
  `orden` int(11) NOT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  `activo` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_actividad_rutina`),
  KEY `id_dia_rutina` (`id_dia_rutina`),
  KEY `id_actividad` (`id_actividad`),
  CONSTRAINT `actividad_rutina_ibfk_1` FOREIGN KEY (`id_dia_rutina`) REFERENCES `dia_rutina` (`id_dia_rutina`),
  CONSTRAINT `actividad_rutina_ibfk_2` FOREIGN KEY (`id_actividad`) REFERENCES `actividad` (`id_actividad`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividad_rutina`
--

LOCK TABLES `actividad_rutina` WRITE;
/*!40000 ALTER TABLE `actividad_rutina` DISABLE KEYS */;
INSERT INTO `actividad_rutina` VALUES (1,1,1,4,12,30,60,1,'2026-01-14 02:05:00',1),(2,1,1,4,8,20,10,2,'2026-01-14 02:05:00',1);
/*!40000 ALTER TABLE `actividad_rutina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `composicion_corporal`
--

DROP TABLE IF EXISTS `composicion_corporal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `composicion_corporal` (
  `id_composicion_corporal` int(11) NOT NULL AUTO_INCREMENT,
  `id_perfil_alumno` int(11) NOT NULL,
  `estatura` int(11) NOT NULL,
  `peso` double NOT NULL,
  `masa_muscular` double NOT NULL,
  `grasa` double NOT NULL,
  `grasa_vicersal` double NOT NULL,
  `agua` double NOT NULL,
  `masa_osea` double NOT NULL,
  `bmi` double NOT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_composicion_corporal`),
  KEY `id_perfil_alumno` (`id_perfil_alumno`),
  CONSTRAINT `composicion_corporal_ibfk_1` FOREIGN KEY (`id_perfil_alumno`) REFERENCES `perfil_alumno` (`id_perfil_alumno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `composicion_corporal`
--

LOCK TABLES `composicion_corporal` WRITE;
/*!40000 ALTER TABLE `composicion_corporal` DISABLE KEYS */;
/*!40000 ALTER TABLE `composicion_corporal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dia_rutina`
--

DROP TABLE IF EXISTS `dia_rutina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dia_rutina` (
  `id_dia_rutina` int(11) NOT NULL AUTO_INCREMENT,
  `id_plantilla_rutina` int(11) NOT NULL,
  `dia_semana` varchar(250) NOT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  `activo` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_dia_rutina`),
  KEY `id_plantilla_rutina` (`id_plantilla_rutina`),
  CONSTRAINT `dia_rutina_ibfk_1` FOREIGN KEY (`id_plantilla_rutina`) REFERENCES `plantilla_rutina` (`id_plantilla_rutina`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dia_rutina`
--

LOCK TABLES `dia_rutina` WRITE;
/*!40000 ALTER TABLE `dia_rutina` DISABLE KEYS */;
INSERT INTO `dia_rutina` VALUES (1,1,'LUNES','2026-01-14 01:50:28',1);
/*!40000 ALTER TABLE `dia_rutina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imagenes_perfil`
--

DROP TABLE IF EXISTS `imagenes_perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `imagenes_perfil` (
  `imagenes_perfil` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `imagen` text DEFAULT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  `activo` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`imagenes_perfil`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `imagenes_perfil_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagenes_perfil`
--

LOCK TABLES `imagenes_perfil` WRITE;
/*!40000 ALTER TABLE `imagenes_perfil` DISABLE KEYS */;
/*!40000 ALTER TABLE `imagenes_perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medidas_corporales`
--

DROP TABLE IF EXISTS `medidas_corporales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medidas_corporales` (
  `id_medidas_corporales` int(11) NOT NULL AUTO_INCREMENT,
  `id_perfil_alumno` int(11) NOT NULL,
  `pecho` int(11) NOT NULL,
  `cintura` int(11) NOT NULL,
  `cadera` int(11) NOT NULL,
  `brazo` int(11) NOT NULL,
  `muslo` int(11) NOT NULL,
  `pantorrilla` int(11) NOT NULL,
  `masa_osea` int(11) NOT NULL,
  `bmi` int(11) NOT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_medidas_corporales`),
  KEY `id_perfil_alumno` (`id_perfil_alumno`),
  CONSTRAINT `medidas_corporales_ibfk_1` FOREIGN KEY (`id_perfil_alumno`) REFERENCES `perfil_alumno` (`id_perfil_alumno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medidas_corporales`
--

LOCK TABLES `medidas_corporales` WRITE;
/*!40000 ALTER TABLE `medidas_corporales` DISABLE KEYS */;
/*!40000 ALTER TABLE `medidas_corporales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `onboarding_token`
--

DROP TABLE IF EXISTS `onboarding_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `onboarding_token` (
  `id_onboardin_token` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `token` varchar(100) NOT NULL,
  `usado` tinyint(1) DEFAULT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  `tiempo_expiracion` int(11) DEFAULT NULL,
  `fecha_expiracion` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_onboardin_token`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `onboarding_token_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `onboarding_token`
--

LOCK TABLES `onboarding_token` WRITE;
/*!40000 ALTER TABLE `onboarding_token` DISABLE KEYS */;
INSERT INTO `onboarding_token` VALUES (1,45,'1c63a0c5-aa38-4ab3-9a0e-04141f0dc607',0,'2026-03-21 06:00:00',12,NULL),(2,83,'717bbf9b-9549-4438-9363-db800e9cdb41',1,'2026-03-23 06:00:00',5,'2026-03-24 01:45:39'),(29,84,'00d1e2f3-74b8-4635-bddd-da2ee2559b10',1,'2026-03-23 06:00:00',5,'2026-03-24 02:22:48'),(35,90,'7aecdc48-4804-4b6d-8c6e-ab2fa34ca0c5',0,'2026-03-23 06:00:00',5,'2026-03-24 03:34:01'),(36,91,'7ad0a0ce-2b51-4660-b112-7520ee23b216',0,'2026-03-27 05:00:00',5,'2026-03-28 01:48:12'),(37,92,'216de722-70b9-48cd-9fc9-8d92b5b5c13e',0,'2026-03-30 05:00:00',5,'2026-03-30 21:13:12'),(38,93,'0d10c890-04c9-4841-bbf8-a2d1c0fba6be',0,'2026-03-30 05:00:00',5,'2026-03-30 22:44:34'),(39,94,'e139c81d-9f58-4e25-8d3c-d7517e57642d',0,'2026-04-05 06:00:00',5,'2026-04-06 00:11:39'),(40,95,'4e141eff-bcd6-43d5-ba50-42aa00023a74',0,'2026-04-05 06:00:00',5,'2026-04-06 00:14:09'),(41,96,'2759a2c7-1d62-4738-a7b4-385486f02b68',0,'2026-04-05 06:00:00',5,'2026-04-06 00:15:18'),(42,97,'e387dd9d-ec27-4126-b1eb-d8303daa0646',0,'2026-04-05 06:00:00',5,'2026-04-06 00:18:39'),(43,98,'2b231c05-f88b-4a67-85b1-867d1fa3696d',0,'2026-04-05 06:00:00',5,'2026-04-06 00:19:15'),(44,99,'7bfef2c0-14a0-4fd6-bc63-4cc3bc1873c7',0,'2026-04-05 06:00:00',5,'2026-04-06 00:20:31'),(45,100,'b58257a1-3500-4648-9b06-9f1aec1ec0b3',0,'2026-04-05 06:00:00',5,'2026-04-06 00:21:44'),(46,101,'dba75f10-7ff7-4ca7-a10b-78ffd81962e7',0,'2026-04-05 06:00:00',5,'2026-04-06 00:22:18'),(47,102,'5a5c4ca2-efcb-42c1-962b-e24469dddb27',0,'2026-04-05 06:00:00',5,'2026-04-06 00:23:00'),(48,103,'997a1857-0f03-4a11-a866-0f596246b282',0,'2026-04-05 06:00:00',5,'2026-04-06 00:24:10'),(49,104,'c5e184cd-7a3b-4ee8-85a3-84d8f9923837',0,'2026-04-05 06:00:00',5,'2026-04-06 00:24:59'),(50,105,'c6227bbd-df26-4d1b-bacb-7b34973537e7',0,'2026-04-05 06:00:00',5,'2026-04-06 00:25:55'),(51,106,'dbcc0b54-50e5-4dcf-b7c3-ab03c0eb7f4c',0,'2026-04-05 06:00:00',5,'2026-04-06 00:27:34'),(52,107,'1d7626ed-3736-476d-a268-03a7ba097a1b',0,'2026-04-05 06:00:00',5,'2026-04-06 00:29:13');
/*!40000 ALTER TABLE `onboarding_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil_alumno`
--

DROP TABLE IF EXISTS `perfil_alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfil_alumno` (
  `id_perfil_alumno` int(11) NOT NULL AUTO_INCREMENT,
  `id_perfil_coach` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(250) NOT NULL,
  `apellidop` varchar(500) NOT NULL,
  `apellidom` varchar(100) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `sexo` varchar(10) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `correo` varchar(100) NOT NULL,
  `frecuencia_entrenamiento` varchar(100) NOT NULL,
  `nivel` varchar(100) DEFAULT NULL,
  `status_onboarding` varchar(30) NOT NULL,
  `objetivo` varchar(100) DEFAULT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  `fecha_actualizacion` timestamp NULL DEFAULT NULL,
  `activo` tinyint(4) DEFAULT 1,
  PRIMARY KEY (`id_perfil_alumno`),
  KEY `id_usuario` (`id_usuario`),
  KEY `perfil_alumno_perfil_coach_FK` (`id_perfil_coach`),
  CONSTRAINT `perfil_alumno_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `perfil_alumno_perfil_coach_FK` FOREIGN KEY (`id_perfil_coach`) REFERENCES `perfil_coach` (`id_perfil_coach`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil_alumno`
--

LOCK TABLES `perfil_alumno` WRITE;
/*!40000 ALTER TABLE `perfil_alumno` DISABLE KEYS */;
INSERT INTO `perfil_alumno` VALUES (1,1,2,'GERARDO','MANDUJANO','ROLDAN','1993-12-02','H','5572297492','gmandujano60@gmail.com','1 a 2 dias','PRINCIPIANTE','PERFIL_COMPLETO','GANAR MASA MUSCULAR','2026-01-14 01:11:48',NULL,1),(24,1,83,'CECILIA','ACOSTA','MARTINEZ','1987-01-06','m','54287485','cecilia.acosta.mtz@gmail.com','1 A 2 DIAS',NULL,'CREADO','BAJAR DE PESO','2026-03-23 06:00:00','2026-03-23 06:00:00',1),(25,1,84,'ssdfsdf','sdfsdf','sdfsdf','2026-03-17','h','54287485','dfsdfsdf@gmail.com','1 A 2 DIAS',NULL,'CREADO','HIERTROFIA','2026-03-23 06:00:00','2026-03-23 06:00:00',1),(26,1,91,'Eduardo Daniel','Mora ','Acosta','2000-01-01','h','54287485','daniel@gmail.com','1 A 2 DIAS',NULL,'CREADO','GANAR MASA MUSCULAR','2026-03-27 05:00:00','2026-03-27 05:00:00',1),(27,1,92,'zxzxzx','xzzxcx','xzcxzcx','2026-03-30','h','54287485','xxzzxc@sdsdsd.com','1 A 2 DIAS',NULL,'CREADO','GANAR MASA MUSCULAR','2026-03-30 05:00:00','2026-03-30 05:00:00',1),(28,1,93,'sdsd','sdsd','sdsd','2026-03-04','h','54287485','sdssd@gmail.com','1 A 2 DIAS',NULL,'CREADO','GANAR MASA MUSCULAR','2026-03-30 05:00:00','2026-03-30 05:00:00',1),(29,1,94,'ssfsdf','sdfsdf','sdfsdf','2026-04-02','h','54287485','dsfdfs','1 A 2 DIAS',NULL,'CREADO','GANAR MASA MUSCULAR','2026-04-05 06:00:00','2026-04-05 06:00:00',1),(30,1,95,'sas','asas','asas','2026-04-28','h','54287485','asas','1 A 2 DIAS',NULL,'CREADO','HIERTROFIA','2026-04-05 06:00:00','2026-04-05 06:00:00',1),(31,1,96,'asas','asas','asas','2026-04-15','h','54287485','asa','1 A 2 DIAS',NULL,'CREADO','HIERTROFIA','2026-04-05 06:00:00','2026-04-05 06:00:00',1),(32,1,97,'asasasas','asasasa','asas','2026-04-05','h','54287485','asasasas','1 A 2 DIAS',NULL,'CREADO','GANAR MASA MUSCULAR','2026-04-05 06:00:00','2026-04-05 06:00:00',1),(33,1,98,'asasasassas','asasasaasas','asas','2026-04-05','h','54287485','asasasasasasas','1 A 2 DIAS',NULL,'CREADO','GANAR MASA MUSCULAR','2026-04-05 06:00:00','2026-04-05 06:00:00',1),(34,1,99,'111','dfsdf','sdfsdf','2026-04-05','h','54287485','dffdfdfd','1 A 2 DIAS',NULL,'CREADO','GANAR MASA MUSCULAR','2026-04-05 06:00:00','2026-04-05 06:00:00',1),(35,1,100,'dfdsf','dsfsdf','dfsdf','2026-04-30','h','54287485','dfsdfsdf','1 A 2 DIAS',NULL,'CREADO','GANAR MASA MUSCULAR','2026-04-05 06:00:00','2026-04-05 06:00:00',1),(36,1,101,'asas','asas','asas','2026-04-30','h','54287485','saasasasasas','1 A 2 DIAS',NULL,'CREADO','HIERTROFIA','2026-04-05 06:00:00','2026-04-05 06:00:00',1),(37,1,102,'asas','asas','asas','2026-04-29','h','54287485','asdfsdfdf','1 A 2 DIAS',NULL,'CREADO','HIERTROFIA','2026-04-05 06:00:00','2026-04-05 06:00:00',1),(38,1,103,'dfsdf','sdfsf','sdfsdf','2026-04-29','h','54287485','dsfsdfsdf','1 A 2 DIAS',NULL,'CREADO','GANAR MASA MUSCULAR','2026-04-05 06:00:00','2026-04-05 06:00:00',1),(39,1,104,'asas','asas','asas','2026-04-05','h','54287485','asasssss','1 A 2 DIAS',NULL,'CREADO','HIERTROFIA','2026-04-05 06:00:00','2026-04-05 06:00:00',1),(40,1,105,'asas','asas','asas','2026-04-22','h','54287485','asasasasaaaa','1 A 2 DIAS',NULL,'CREADO','GANAR MASA MUSCULAR','2026-04-05 06:00:00','2026-04-05 06:00:00',1),(41,1,106,'aaa','aaa','aa','2026-04-05','h','54287485','assasas','1 A 2 DIAS',NULL,'CREADO','GANAR MASA MUSCULAR','2026-04-05 06:00:00','2026-04-05 06:00:00',1),(42,1,107,'asasa','asas','asas','2026-04-14','h','54287485','asasaaaaaaaa','1 A 2 DIAS',NULL,'CREADO','GANAR MASA MUSCULAR','2026-04-05 06:00:00','2026-04-05 06:00:00',1);
/*!40000 ALTER TABLE `perfil_alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil_coach`
--

DROP TABLE IF EXISTS `perfil_coach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfil_coach` (
  `id_perfil_coach` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `nombre_gym` varchar(500) DEFAULT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  `activo` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_perfil_coach`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `perfil_coach_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil_coach`
--

LOCK TABLES `perfil_coach` WRITE;
/*!40000 ALTER TABLE `perfil_coach` DISABLE KEYS */;
INSERT INTO `perfil_coach` VALUES (1,1,'DEMO GYM','2026-01-14 01:07:27',1);
/*!40000 ALTER TABLE `perfil_coach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plantilla_rutina`
--

DROP TABLE IF EXISTS `plantilla_rutina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plantilla_rutina` (
  `id_plantilla_rutina` int(11) NOT NULL AUTO_INCREMENT,
  `id_perfil_coauch` int(11) NOT NULL,
  `nombre` varchar(500) NOT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  `activo` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_plantilla_rutina`),
  KEY `id_perfil_coauch` (`id_perfil_coauch`),
  CONSTRAINT `plantilla_rutina_ibfk_1` FOREIGN KEY (`id_perfil_coauch`) REFERENCES `perfil_coach` (`id_perfil_coach`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plantilla_rutina`
--

LOCK TABLES `plantilla_rutina` WRITE;
/*!40000 ALTER TABLE `plantilla_rutina` DISABLE KEYS */;
INSERT INTO `plantilla_rutina` VALUES (1,1,'Definicion Mujeres','2026-01-14 01:44:16',1),(2,1,'Fuerza Principiantes','2026-01-14 01:44:16',1);
/*!40000 ALTER TABLE `plantilla_rutina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro_actividad`
--

DROP TABLE IF EXISTS `registro_actividad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registro_actividad` (
  `id_registro_actividad` int(11) NOT NULL AUTO_INCREMENT,
  `id_sesion_entrenamiento` int(11) NOT NULL,
  `id_actividad` int(11) NOT NULL,
  `numero_serie` int(11) NOT NULL,
  `peso` double NOT NULL DEFAULT 0,
  `repeticiones` int(11) NOT NULL DEFAULT 1,
  `duracion_minutos` int(11) NOT NULL DEFAULT 1,
  `completado` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_registro_actividad`),
  KEY `id_sesion_entrenamiento` (`id_sesion_entrenamiento`),
  KEY `id_actividad` (`id_actividad`),
  CONSTRAINT `registro_actividad_ibfk_1` FOREIGN KEY (`id_sesion_entrenamiento`) REFERENCES `sesion_entrenamiento` (`id_sesion_entrenamiento`),
  CONSTRAINT `registro_actividad_ibfk_2` FOREIGN KEY (`id_actividad`) REFERENCES `actividad` (`id_actividad`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro_actividad`
--

LOCK TABLES `registro_actividad` WRITE;
/*!40000 ALTER TABLE `registro_actividad` DISABLE KEYS */;
INSERT INTO `registro_actividad` VALUES (1,1,1,1,70,1,1,1);
/*!40000 ALTER TABLE `registro_actividad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sesion_entrenamiento`
--

DROP TABLE IF EXISTS `sesion_entrenamiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sesion_entrenamiento` (
  `id_sesion_entrenamiento` int(11) NOT NULL AUTO_INCREMENT,
  `id_perfil_alumno` int(11) NOT NULL,
  `fecha_inicio` timestamp NULL DEFAULT current_timestamp(),
  `fecha_termino` date NOT NULL,
  `completado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_sesion_entrenamiento`),
  KEY `id_perfil_alumno` (`id_perfil_alumno`),
  CONSTRAINT `sesion_entrenamiento_ibfk_1` FOREIGN KEY (`id_perfil_alumno`) REFERENCES `perfil_alumno` (`id_perfil_alumno`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sesion_entrenamiento`
--

LOCK TABLES `sesion_entrenamiento` WRITE;
/*!40000 ALTER TABLE `sesion_entrenamiento` DISABLE KEYS */;
INSERT INTO `sesion_entrenamiento` VALUES (1,1,'2026-01-23 00:25:06','2026-01-02',1);
/*!40000 ALTER TABLE `sesion_entrenamiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `correo` varchar(500) NOT NULL,
  `contrasenia` text DEFAULT NULL,
  `rol` varchar(250) NOT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  `activo` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `idx_correo` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'coach@gmail.com','$2a$10$Bmsu2sfBLMnsbL1UAjpKDO0jTAmvBy0BYV2Af8HGLnguG6py7N0xW','COACH','2026-01-14 01:12:15',1),(2,'alumno@gmail.com','$2a$10$Bmsu2sfBLMnsbL1UAjpKDO0jTAmvBy0BYV2Af8HGLnguG6py7N0xW','ALUMNO','2026-01-14 01:11:15',1),(45,'juan6@mail.com',NULL,'ALUMNO',NULL,1),(82,'gmandujanaaaaa@gmail.com',NULL,'ALUMNO',NULL,1),(83,'cecilia.acosta.mtz@gmail.com','','ALUMNO',NULL,1),(84,'dfsdfsdf@gmail.com',NULL,'ALUMNO',NULL,1),(90,'',NULL,'ALUMNO',NULL,1),(91,'daniel@gmail.com',NULL,'ALUMNO',NULL,1),(92,'xxzzxc@sdsdsd.com',NULL,'ALUMNO',NULL,1),(93,'sdssd@gmail.com',NULL,'ALUMNO',NULL,1),(94,'dsfdfs',NULL,'ALUMNO',NULL,1),(95,'asas',NULL,'ALUMNO',NULL,1),(96,'asa',NULL,'ALUMNO',NULL,1),(97,'asasasas',NULL,'ALUMNO',NULL,1),(98,'asasasasasasas',NULL,'ALUMNO',NULL,1),(99,'dffdfdfd',NULL,'ALUMNO',NULL,1),(100,'dfsdfsdf',NULL,'ALUMNO',NULL,1),(101,'saasasasasas',NULL,'ALUMNO',NULL,1),(102,'asdfsdfdf',NULL,'ALUMNO',NULL,1),(103,'dsfsdfsdf',NULL,'ALUMNO',NULL,1),(104,'asasssss',NULL,'ALUMNO',NULL,1),(105,'asasasasaaaa',NULL,'ALUMNO',NULL,1),(106,'assasas',NULL,'ALUMNO',NULL,1),(107,'asasaaaaaaaa',NULL,'ALUMNO',NULL,1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'pulso_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-11 15:19:32
