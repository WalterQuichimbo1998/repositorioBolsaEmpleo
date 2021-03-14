CREATE DATABASE  IF NOT EXISTS `bolsa_empleo_istl` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `bolsa_empleo_istl`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: bolsa_empleo_istl
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.14-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `area_estudio`
--

DROP TABLE IF EXISTS `area_estudio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `area_estudio` (
  `id_area_estudio` int(11) NOT NULL AUTO_INCREMENT,
  `area_estudio` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_area_estudio`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area_estudio`
--

LOCK TABLES `area_estudio` WRITE;
/*!40000 ALTER TABLE `area_estudio` DISABLE KEYS */;
INSERT INTO `area_estudio` VALUES (1,'CIENCIA'),(2,'QUIMICA'),(3,'MATEMATICAS'),(4,'LITERATURA');
/*!40000 ALTER TABLE `area_estudio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `area_trabajo`
--

DROP TABLE IF EXISTS `area_trabajo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `area_trabajo` (
  `id_area_trabajo` int(11) NOT NULL AUTO_INCREMENT,
  `area_trabajo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_area_trabajo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area_trabajo`
--

LOCK TABLES `area_trabajo` WRITE;
/*!40000 ALTER TABLE `area_trabajo` DISABLE KEYS */;
INSERT INTO `area_trabajo` VALUES (1,'TECNOLOGÍA'),(2,'DESARROLLO');
/*!40000 ALTER TABLE `area_trabajo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baul_recomendaciones`
--

DROP TABLE IF EXISTS `baul_recomendaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `baul_recomendaciones` (
  `id_baul_recomendaciones` int(11) NOT NULL AUTO_INCREMENT,
  `id_hoja_vida_estudiante` int(11) NOT NULL,
  `recomendacion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_baul_recomendaciones`),
  KEY `baul_recomendacionesHoja_vida_estudiante_idx` (`id_hoja_vida_estudiante`),
  CONSTRAINT `baul_recomendacionesHoja_vida_estudiante` FOREIGN KEY (`id_hoja_vida_estudiante`) REFERENCES `hoja_vida_estudiante` (`id_hoja_vida_estudiante`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baul_recomendaciones`
--

LOCK TABLES `baul_recomendaciones` WRITE;
/*!40000 ALTER TABLE `baul_recomendaciones` DISABLE KEYS */;
INSERT INTO `baul_recomendaciones` VALUES (12,69,'ninguna');
/*!40000 ALTER TABLE `baul_recomendaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `canton`
--

DROP TABLE IF EXISTS `canton`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `canton` (
  `id_canton` int(11) NOT NULL AUTO_INCREMENT,
  `id_provincia` int(11) DEFAULT NULL,
  `canton` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_canton`),
  KEY `cantonProvincia_idx` (`id_provincia`),
  CONSTRAINT `cantonProvincia` FOREIGN KEY (`id_provincia`) REFERENCES `provincia` (`id_provincia`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `canton`
--

LOCK TABLES `canton` WRITE;
/*!40000 ALTER TABLE `canton` DISABLE KEYS */;
INSERT INTO `canton` VALUES (1,1,'LIMON INDANZA'),(2,2,'AZUAY'),(3,3,'ZAPAN'),(4,1,'SUCUA'),(5,2,'ORQUIDEAS'),(6,2,'MACABEO'),(7,3,'PANGUI'),(8,1,'SAN JUAN BOSCO'),(9,1,'YUNGANZA'),(10,6,'QUITO');
/*!40000 ALTER TABLE `canton` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `capacitacion`
--

DROP TABLE IF EXISTS `capacitacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `capacitacion` (
  `id_capacitacion` int(11) NOT NULL AUTO_INCREMENT,
  `institucion` varchar(100) DEFAULT NULL,
  `nombre_evento` varchar(100) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `id_horas_capacitaciones` int(11) DEFAULT NULL,
  `dias` int(11) DEFAULT NULL,
  `id_tipo_evento` int(11) DEFAULT NULL,
  `id_area_estudio` int(11) DEFAULT NULL,
  `id_tipo_certificado` int(11) DEFAULT NULL,
  `id_hoja_vida_estudiante` int(11) NOT NULL,
  PRIMARY KEY (`id_capacitacion`),
  KEY `fk_capacitacion_tipo_evento1_idx` (`id_tipo_evento`),
  KEY `fk_capacitacion_area_estudio1_idx` (`id_area_estudio`),
  KEY `fk_capacitacion_tipo_cerficado1_idx` (`id_tipo_certificado`),
  KEY `fk_capacitacion_hoja_vida_estudiante1_idx` (`id_hoja_vida_estudiante`),
  KEY `capacitacionHoras_capacitaciones_idx` (`id_horas_capacitaciones`),
  CONSTRAINT `capacitacionArea_estudio` FOREIGN KEY (`id_area_estudio`) REFERENCES `area_estudio` (`id_area_estudio`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `capacitacionHoja_vida_estudiante` FOREIGN KEY (`id_hoja_vida_estudiante`) REFERENCES `hoja_vida_estudiante` (`id_hoja_vida_estudiante`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `capacitacionHoras_capacitaciones` FOREIGN KEY (`id_horas_capacitaciones`) REFERENCES `horas_capacitaciones` (`id_horas_capacitaciones`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `capacitacionTipo_certificado` FOREIGN KEY (`id_tipo_certificado`) REFERENCES `tipo_certificado` (`id_tipo_certificado`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `capacitacionTipo_evento` FOREIGN KEY (`id_tipo_evento`) REFERENCES `tipo_evento` (`id_tipo_evento`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `capacitacion`
--

LOCK TABLES `capacitacion` WRITE;
/*!40000 ALTER TABLE `capacitacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `capacitacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carrera`
--

DROP TABLE IF EXISTS `carrera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carrera` (
  `id_carrera` int(11) NOT NULL AUTO_INCREMENT,
  `carrera` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_carrera`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrera`
--

LOCK TABLES `carrera` WRITE;
/*!40000 ALTER TABLE `carrera` DISABLE KEYS */;
INSERT INTO `carrera` VALUES (4,'ANÁLISIS DE SISTEMAS'),(5,'CONTABILIDAD DE COSTOS');
/*!40000 ALTER TABLE `carrera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empresa` (
  `id_empresa` int(11) NOT NULL AUTO_INCREMENT,
  `id_persona` int(11) NOT NULL,
  `ruc_cedula` varchar(45) DEFAULT NULL,
  `razon_social` varchar(45) DEFAULT NULL,
  `nombre_comercial` varchar(60) DEFAULT NULL,
  `id_tipo_empresa` int(11) DEFAULT NULL,
  `id_tipo_persona` int(11) DEFAULT NULL,
  `pagina_web` varchar(45) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `ciudad` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `telefono_oficina` varchar(45) DEFAULT NULL,
  `telefono_particular` varchar(45) DEFAULT NULL,
  `celular` varchar(45) DEFAULT NULL,
  `logotipo` varchar(80) DEFAULT NULL,
  `servicios` varchar(500) DEFAULT NULL,
  `experiencias` varchar(500) DEFAULT NULL,
  `actividades` varchar(500) DEFAULT NULL,
  `id_provincia` int(11) DEFAULT NULL,
  `id_canton` int(11) DEFAULT NULL,
  `id_parroquia` int(11) DEFAULT NULL,
  `fecha_creacion` date DEFAULT NULL,
  PRIMARY KEY (`id_empresa`),
  KEY `empresaPersona_idx` (`id_persona`),
  KEY `empresaTipo_empresa_idx` (`id_tipo_empresa`),
  KEY `empresaTipo_persona_idx` (`id_tipo_persona`),
  KEY `empresaProvincia_idx` (`id_provincia`),
  KEY `empresaCanton_idx` (`id_canton`),
  KEY `empresaParroquia_idx` (`id_parroquia`),
  CONSTRAINT `empresaCanton` FOREIGN KEY (`id_canton`) REFERENCES `canton` (`id_canton`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `empresaParroquia` FOREIGN KEY (`id_parroquia`) REFERENCES `parroquia` (`id_parroquia`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `empresaPersona` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `empresaProvincia` FOREIGN KEY (`id_provincia`) REFERENCES `provincia` (`id_provincia`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `empresaTipo_empresa` FOREIGN KEY (`id_tipo_empresa`) REFERENCES `tipo_empresa` (`id_tipo_empresa`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `empresaTipo_persona` FOREIGN KEY (`id_tipo_persona`) REFERENCES `tipo_persona` (`id_tipo_persona`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (7,73,'568797897897','','TIENDA MAX',2,1,'',NULL,NULL,'Limón','',NULL,'','requerido/sin_logotipo.jpg','','','',1,1,1,'2021-03-09');
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_civil`
--

DROP TABLE IF EXISTS `estado_civil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado_civil` (
  `id_estado_civil` int(11) NOT NULL AUTO_INCREMENT,
  `estado_civil` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_estado_civil`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_civil`
--

LOCK TABLES `estado_civil` WRITE;
/*!40000 ALTER TABLE `estado_civil` DISABLE KEYS */;
INSERT INTO `estado_civil` VALUES (1,'SOLTERO'),(2,'CASADO'),(3,'DIVORSIADO'),(4,'CASADA'),(5,'VIUDA'),(6,'SOLTERA');
/*!40000 ALTER TABLE `estado_civil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `etnia`
--

DROP TABLE IF EXISTS `etnia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `etnia` (
  `id_etnia` int(11) NOT NULL AUTO_INCREMENT,
  `etnia` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_etnia`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `etnia`
--

LOCK TABLES `etnia` WRITE;
/*!40000 ALTER TABLE `etnia` DISABLE KEYS */;
INSERT INTO `etnia` VALUES (1,'MESTIZO');
/*!40000 ALTER TABLE `etnia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `experiencia_laboral`
--

DROP TABLE IF EXISTS `experiencia_laboral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `experiencia_laboral` (
  `id_experiencia` int(11) NOT NULL AUTO_INCREMENT,
  `id_area_trabajo` int(11) DEFAULT NULL,
  `institucion` varchar(45) DEFAULT NULL,
  `puesto` varchar(100) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `actividades` varchar(500) DEFAULT NULL,
  `trabaja_actualmente` int(11) DEFAULT NULL,
  `id_hoja_vida_estudiante` int(11) NOT NULL,
  PRIMARY KEY (`id_experiencia`),
  KEY `experiencia_laboralArea_trabajo_idx` (`id_area_trabajo`),
  KEY `fk_experiencia_laboral_hoja_vida_estudiante1_idx` (`id_hoja_vida_estudiante`),
  CONSTRAINT `experiencia_laboralArea_trabajo` FOREIGN KEY (`id_area_trabajo`) REFERENCES `area_trabajo` (`id_area_trabajo`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `experiencia_laboralHoja_vida_estudiante` FOREIGN KEY (`id_hoja_vida_estudiante`) REFERENCES `hoja_vida_estudiante` (`id_hoja_vida_estudiante`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experiencia_laboral`
--

LOCK TABLES `experiencia_laboral` WRITE;
/*!40000 ALTER TABLE `experiencia_laboral` DISABLE KEYS */;
/*!40000 ALTER TABLE `experiencia_laboral` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoja_vida_estudiante`
--

DROP TABLE IF EXISTS `hoja_vida_estudiante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hoja_vida_estudiante` (
  `id_hoja_vida_estudiante` int(11) NOT NULL AUTO_INCREMENT,
  `id_persona` int(11) NOT NULL,
  `estado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_hoja_vida_estudiante`),
  KEY `fk_hoja_vida_estudiante_persona1_idx` (`id_persona`),
  CONSTRAINT `hoja_vida_estudiantePersona` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoja_vida_estudiante`
--

LOCK TABLES `hoja_vida_estudiante` WRITE;
/*!40000 ALTER TABLE `hoja_vida_estudiante` DISABLE KEYS */;
INSERT INTO `hoja_vida_estudiante` VALUES (10,12,'Graduado'),(11,13,'Graduado'),(12,14,'Graduado'),(13,15,'Graduado'),(14,16,'Graduado'),(15,17,'Graduado'),(16,18,'Graduado'),(17,19,'Graduado'),(18,20,'Graduado'),(19,21,'Graduado'),(20,22,'Graduado'),(21,23,'Graduado'),(22,24,'Graduado'),(23,25,'Graduado'),(24,26,'Graduado'),(25,27,'Graduado'),(26,28,'Graduado'),(27,29,'Graduado'),(28,30,'Graduado'),(29,31,'Graduado'),(30,32,'Graduado'),(31,33,'Graduado'),(32,34,'Graduado'),(33,35,'Graduado'),(34,36,'Graduado'),(35,37,'Graduado'),(36,38,'Graduado'),(37,39,'Graduado'),(38,40,'Graduado'),(39,41,'Graduado'),(40,42,'Graduado'),(41,43,'Graduado'),(42,44,'Graduado'),(43,45,'Graduado'),(44,46,'Graduado'),(45,47,'Graduado'),(46,48,'Graduado'),(47,49,'Graduado'),(48,50,'Graduado'),(49,54,'Graduado'),(50,51,'Graduado'),(51,52,'Graduado'),(52,53,'Graduado'),(53,57,'Egresado'),(54,58,'Egresado'),(55,59,'Egresado'),(56,60,'Egresado'),(57,61,'Egresado'),(58,62,'Egresado'),(59,64,'Egresado'),(60,65,'Egresado'),(61,66,'Egresado'),(62,67,'Egresado'),(63,68,'Egresado'),(64,69,'Egresado'),(65,70,'Egresado'),(66,71,'Egresado'),(67,72,'Egresado'),(68,63,'Egresado'),(69,74,'Graduado');
/*!40000 ALTER TABLE `hoja_vida_estudiante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horas_capacitaciones`
--

DROP TABLE IF EXISTS `horas_capacitaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `horas_capacitaciones` (
  `id_horas_capacitaciones` int(11) NOT NULL AUTO_INCREMENT,
  `horas_capacitaciones` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_horas_capacitaciones`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horas_capacitaciones`
--

LOCK TABLES `horas_capacitaciones` WRITE;
/*!40000 ALTER TABLE `horas_capacitaciones` DISABLE KEYS */;
INSERT INTO `horas_capacitaciones` VALUES (1,'0-50'),(2,'50-100');
/*!40000 ALTER TABLE `horas_capacitaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `idioma`
--

DROP TABLE IF EXISTS `idioma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `idioma` (
  `id_idioma` int(11) NOT NULL AUTO_INCREMENT,
  `id_lista_idiomas` int(11) DEFAULT NULL,
  `id_nivel_hablado` int(11) DEFAULT NULL,
  `id_nivel_escrito` int(11) DEFAULT NULL,
  `id_hoja_vida_estudiante` int(11) NOT NULL,
  PRIMARY KEY (`id_idioma`),
  KEY `fk_idioma_nivel_hablado1_idx` (`id_nivel_hablado`),
  KEY `fk_idioma_nivel_escrito1_idx` (`id_nivel_escrito`),
  KEY `fk_idioma_lista_idiomas1_idx` (`id_lista_idiomas`),
  KEY `fk_idioma_hoja_vida_estudiante1_idx` (`id_hoja_vida_estudiante`),
  CONSTRAINT `idiomaHoja_vida_estudiante` FOREIGN KEY (`id_hoja_vida_estudiante`) REFERENCES `hoja_vida_estudiante` (`id_hoja_vida_estudiante`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idiomaLista_idiomas` FOREIGN KEY (`id_lista_idiomas`) REFERENCES `lista_idiomas` (`id_lista_idiomas`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `idiomaNivel_escrito` FOREIGN KEY (`id_nivel_escrito`) REFERENCES `nivel_escrito` (`id_nivel_escrito`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `idiomaNivel_hablado` FOREIGN KEY (`id_nivel_hablado`) REFERENCES `nivel_hablado` (`id_nivel_hablado`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `idioma`
--

LOCK TABLES `idioma` WRITE;
/*!40000 ALTER TABLE `idioma` DISABLE KEYS */;
/*!40000 ALTER TABLE `idioma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imagen_portada`
--

DROP TABLE IF EXISTS `imagen_portada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imagen_portada` (
  `id_imagen` int(11) NOT NULL AUTO_INCREMENT,
  `imagen` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_imagen`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagen_portada`
--

LOCK TABLES `imagen_portada` WRITE;
/*!40000 ALTER TABLE `imagen_portada` DISABLE KEYS */;
INSERT INTO `imagen_portada` VALUES (24,'10333-PortadaSeguimiento.jpeg'),(26,'19018-PortadaSeguimiento.jpeg'),(27,'15424-PortadaSeguimiento.jpeg');
/*!40000 ALTER TABLE `imagen_portada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instruccion_formal`
--

DROP TABLE IF EXISTS `instruccion_formal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instruccion_formal` (
  `id_instruccion` int(11) NOT NULL AUTO_INCREMENT,
  `institucion` varchar(70) DEFAULT NULL,
  `titulo` varchar(80) DEFAULT NULL,
  `codigo_senescyt` varchar(45) DEFAULT NULL,
  `id_nivel_academico` int(11) DEFAULT NULL,
  `id_hoja_vida_estudiante` int(11) NOT NULL,
  PRIMARY KEY (`id_instruccion`),
  KEY `fk_instruccion_formal_nivel_academico1_idx` (`id_nivel_academico`),
  KEY `fk_instruccion_formal_hoja_vida_estudiante1_idx` (`id_hoja_vida_estudiante`),
  CONSTRAINT `instruccion_formalHoja_vida_estudiante` FOREIGN KEY (`id_hoja_vida_estudiante`) REFERENCES `hoja_vida_estudiante` (`id_hoja_vida_estudiante`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `instruccion_formalNivel_academico` FOREIGN KEY (`id_nivel_academico`) REFERENCES `nivel_academico` (`id_nivel_academico`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instruccion_formal`
--

LOCK TABLES `instruccion_formal` WRITE;
/*!40000 ALTER TABLE `instruccion_formal` DISABLE KEYS */;
/*!40000 ALTER TABLE `instruccion_formal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lista_idiomas`
--

DROP TABLE IF EXISTS `lista_idiomas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lista_idiomas` (
  `id_lista_idiomas` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_idioma` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_lista_idiomas`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lista_idiomas`
--

LOCK TABLES `lista_idiomas` WRITE;
/*!40000 ALTER TABLE `lista_idiomas` DISABLE KEYS */;
INSERT INTO `lista_idiomas` VALUES (1,'ESPAÑOL'),(2,'INGLES'),(3,'CHINO'),(4,'FRANCES');
/*!40000 ALTER TABLE `lista_idiomas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logro_personal`
--

DROP TABLE IF EXISTS `logro_personal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logro_personal` (
  `id_logro_personal` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(1000) DEFAULT NULL,
  `id_tipo_logro` int(11) DEFAULT NULL,
  `id_hoja_vida_estudiante` int(11) NOT NULL,
  PRIMARY KEY (`id_logro_personal`),
  KEY `fk_logro_personal_tipo_logro1_idx` (`id_tipo_logro`),
  KEY `fk_logro_personal_hoja_vida_estudiante1_idx` (`id_hoja_vida_estudiante`),
  CONSTRAINT `logro_personalHoja_vida_estudiante` FOREIGN KEY (`id_hoja_vida_estudiante`) REFERENCES `hoja_vida_estudiante` (`id_hoja_vida_estudiante`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `logro_personalTipo_logro` FOREIGN KEY (`id_tipo_logro`) REFERENCES `tipo_logro` (`id_tipo_logro`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logro_personal`
--

LOCK TABLES `logro_personal` WRITE;
/*!40000 ALTER TABLE `logro_personal` DISABLE KEYS */;
/*!40000 ALTER TABLE `logro_personal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensaje`
--

DROP TABLE IF EXISTS `mensaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mensaje` (
  `id_mensaje` int(11) NOT NULL AUTO_INCREMENT,
  `id_postulante` int(11) DEFAULT NULL,
  `id_oferta` int(11) DEFAULT NULL,
  `mensaje` varchar(300) NOT NULL,
  `emisor` int(11) DEFAULT NULL COMMENT '1=ESTUDIANTE\n2=EMPLEADOR',
  `fecha_registro` date NOT NULL,
  `estado` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_mensaje`),
  KEY `mensaje_postulante_idx` (`id_postulante`),
  KEY `mensaje_oferta_laboral_idx` (`id_oferta`),
  CONSTRAINT `mensaje_oferta_laboral` FOREIGN KEY (`id_oferta`) REFERENCES `oferta_laboral` (`id_oferta`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `mensaje_postulante` FOREIGN KEY (`id_postulante`) REFERENCES `postulante` (`id_postulante`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensaje`
--

LOCK TABLES `mensaje` WRITE;
/*!40000 ALTER TABLE `mensaje` DISABLE KEYS */;
INSERT INTO `mensaje` VALUES (16,41,15,'hola',2,'2021-03-09',1),(18,41,15,'esta bien',2,'2021-03-09',1),(21,40,14,'srfsfhklhferl ñeth erthe etyerhithertoerhte oheotn etreh tetueheruge gehge  eyeryeo ey etne yeortoierytioeryhti erun eotyerotyeroitne  et e8yteirterhyortyeroñi ery toerterti ertoyerotyeroit eryteor er.',1,'2021-03-09',1),(23,41,15,'si estoy bien',1,'2021-03-09',1),(24,41,15,'mañana le voy a ver el pedido',1,'2021-03-09',1),(25,40,14,'si puede venir a las 3pm',2,'2021-03-09',0),(26,40,14,'dfgdfgfdg',1,'2021-03-09',0),(27,40,14,'si se puede',1,'2021-03-09',1),(28,41,15,'si',1,'2021-03-09',0),(29,41,15,'como',2,'2021-03-09',0);
/*!40000 ALTER TABLE `mensaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nivel_academico`
--

DROP TABLE IF EXISTS `nivel_academico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nivel_academico` (
  `id_nivel_academico` int(11) NOT NULL AUTO_INCREMENT,
  `nivel_academico` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_nivel_academico`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nivel_academico`
--

LOCK TABLES `nivel_academico` WRITE;
/*!40000 ALTER TABLE `nivel_academico` DISABLE KEYS */;
INSERT INTO `nivel_academico` VALUES (1,'SECUNDARIA'),(2,'TERCER NIVEL');
/*!40000 ALTER TABLE `nivel_academico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nivel_escrito`
--

DROP TABLE IF EXISTS `nivel_escrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nivel_escrito` (
  `id_nivel_escrito` int(11) NOT NULL AUTO_INCREMENT,
  `nivel_escrito` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_nivel_escrito`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nivel_escrito`
--

LOCK TABLES `nivel_escrito` WRITE;
/*!40000 ALTER TABLE `nivel_escrito` DISABLE KEYS */;
INSERT INTO `nivel_escrito` VALUES (1,'BASICO'),(2,'MEDIO'),(3,'AVANZADO');
/*!40000 ALTER TABLE `nivel_escrito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nivel_hablado`
--

DROP TABLE IF EXISTS `nivel_hablado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nivel_hablado` (
  `id_nivel_hablado` int(11) NOT NULL AUTO_INCREMENT,
  `nivel_hablado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_nivel_hablado`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nivel_hablado`
--

LOCK TABLES `nivel_hablado` WRITE;
/*!40000 ALTER TABLE `nivel_hablado` DISABLE KEYS */;
INSERT INTO `nivel_hablado` VALUES (1,'BASICO'),(2,'MEDIO'),(3,'AVANZADO'),(4,'ukgjkj');
/*!40000 ALTER TABLE `nivel_hablado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oferta_laboral`
--

DROP TABLE IF EXISTS `oferta_laboral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oferta_laboral` (
  `id_oferta` int(11) NOT NULL AUTO_INCREMENT,
  `cargo_solicitado` varchar(60) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `jornada_trabajo` varchar(45) DEFAULT NULL,
  `remuneracion` varchar(45) DEFAULT NULL,
  `experiencia` varchar(500) DEFAULT NULL,
  `conocimiento_cargo` varchar(500) DEFAULT NULL,
  `actividades` varchar(500) DEFAULT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `tiempo_experiencia` varchar(45) DEFAULT NULL,
  `id_empresa` int(11) NOT NULL,
  `id_tipo_contrato` int(11) DEFAULT NULL,
  `id_area_estudio` int(11) DEFAULT NULL,
  `id_horas_capacitaciones` int(11) DEFAULT NULL,
  `id_nivel_academico` int(11) DEFAULT NULL,
  `fecha_creacion` date DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id_oferta`),
  KEY `fk_oferta_laboral_empresa1_idx` (`id_empresa`),
  KEY `fk_oferta_laboral_tipo_contrato1_idx` (`id_tipo_contrato`),
  KEY `fk_oferta_laboral_area_estudio1_idx` (`id_area_estudio`),
  KEY `fk_oferta_laboral_horas_capacitaciones1_idx` (`id_horas_capacitaciones`),
  KEY `fk_oferta_laboral_nivel_academico1_idx` (`id_nivel_academico`),
  CONSTRAINT `oferta_laboralArea_estudio` FOREIGN KEY (`id_area_estudio`) REFERENCES `area_estudio` (`id_area_estudio`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `oferta_laboralEmpresa` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `oferta_laboralHoras_capacitaciones` FOREIGN KEY (`id_horas_capacitaciones`) REFERENCES `horas_capacitaciones` (`id_horas_capacitaciones`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `oferta_laboralNivel_academico` FOREIGN KEY (`id_nivel_academico`) REFERENCES `nivel_academico` (`id_nivel_academico`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `oferta_laboralTipo_contrato` FOREIGN KEY (`id_tipo_contrato`) REFERENCES `tipo_contrato` (`id_tipo_contrato`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oferta_laboral`
--

LOCK TABLES `oferta_laboral` WRITE;
/*!40000 ALTER TABLE `oferta_laboral` DISABLE KEYS */;
INSERT INTO `oferta_laboral` VALUES (14,'EMPLEADO DE SERVICIOS','2021-03-09','2021-04-01','8','400','','','','','1',7,1,1,1,1,'2021-03-09',''),(15,'ADMINISTRADOR DE CAJERO','2021-03-09','2021-03-31','8','400','','','','','3',7,1,3,1,2,'2021-03-09','');
/*!40000 ALTER TABLE `oferta_laboral` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oficio`
--

DROP TABLE IF EXISTS `oficio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oficio` (
  `id_oficio` int(11) NOT NULL AUTO_INCREMENT,
  `oficio` varchar(45) DEFAULT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `id_hoja_vida_estudiante` int(11) NOT NULL,
  PRIMARY KEY (`id_oficio`),
  KEY `fk_oficio_hoja_vida_estudiante1_idx` (`id_hoja_vida_estudiante`),
  CONSTRAINT `oficioHoja_vida_estudiante` FOREIGN KEY (`id_hoja_vida_estudiante`) REFERENCES `hoja_vida_estudiante` (`id_hoja_vida_estudiante`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oficio`
--

LOCK TABLES `oficio` WRITE;
/*!40000 ALTER TABLE `oficio` DISABLE KEYS */;
/*!40000 ALTER TABLE `oficio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parroquia`
--

DROP TABLE IF EXISTS `parroquia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parroquia` (
  `id_parroquia` int(11) NOT NULL AUTO_INCREMENT,
  `id_canton` int(11) DEFAULT NULL,
  `parroquia` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_parroquia`),
  KEY `parroquiaCanton_idx` (`id_canton`),
  CONSTRAINT `parroquiaCanton` FOREIGN KEY (`id_canton`) REFERENCES `canton` (`id_canton`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parroquia`
--

LOCK TABLES `parroquia` WRITE;
/*!40000 ALTER TABLE `parroquia` DISABLE KEYS */;
INSERT INTO `parroquia` VALUES (1,1,'GENERAL LEONIDAS PLAZA'),(2,1,'INDANZA'),(3,2,'CATACACHI'),(5,1,'EL ROSARIO'),(6,9,'YUNGANZA'),(7,10,'TARQUI');
/*!40000 ALTER TABLE `parroquia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona` (
  `id_persona` int(11) NOT NULL AUTO_INCREMENT,
  `id_carrera` int(11) DEFAULT NULL,
  `id_promocion` int(11) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `cedula` varchar(45) DEFAULT NULL,
  `celular` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `genero` int(11) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `ciudad` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `codigo_postal` varchar(45) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `estado_laboral` int(11) DEFAULT NULL,
  `foto` varchar(60) DEFAULT NULL,
  `id_tipo_sangre` int(11) DEFAULT NULL,
  `id_etnia` int(11) DEFAULT NULL,
  `id_estado_civil` int(11) DEFAULT NULL,
  `id_provincia` int(11) DEFAULT NULL,
  `id_canton` int(11) DEFAULT NULL,
  `id_parroquia` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_persona`),
  KEY `fk_persona_tipo_sangre1_idx` (`id_tipo_sangre`),
  KEY `fk_persona_etnia1_idx` (`id_etnia`),
  KEY `fk_persona_estado_civil1_idx` (`id_estado_civil`),
  KEY `personaProvincia_idx` (`id_provincia`),
  KEY `personaCanton_idx` (`id_canton`),
  KEY `personaParroquia_idx` (`id_parroquia`),
  KEY `personaCarrera_idx` (`id_carrera`),
  KEY `personaPromocion_idx` (`id_promocion`),
  CONSTRAINT `personaCanton` FOREIGN KEY (`id_canton`) REFERENCES `canton` (`id_canton`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `personaCarrera` FOREIGN KEY (`id_carrera`) REFERENCES `carrera` (`id_carrera`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `personaEstado_civil` FOREIGN KEY (`id_estado_civil`) REFERENCES `estado_civil` (`id_estado_civil`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `personaEtnia` FOREIGN KEY (`id_etnia`) REFERENCES `etnia` (`id_etnia`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `personaParroquia` FOREIGN KEY (`id_parroquia`) REFERENCES `parroquia` (`id_parroquia`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `personaPromocion` FOREIGN KEY (`id_promocion`) REFERENCES `promocion` (`id_promocion`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `personaProvincia` FOREIGN KEY (`id_provincia`) REFERENCES `provincia` (`id_provincia`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `personaTipo_sangre` FOREIGN KEY (`id_tipo_sangre`) REFERENCES `tipo_sangre` (`id_tipo_sangre`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (3,NULL,NULL,'IST','LIMÓN','1401007045','','',NULL,NULL,'tecnologicolimon@gmail.com','','Limon','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,4,3,'ANA LUCIA','CABRERA CHACON','1400493084','','',1,NULL,'ana@gmail.com',NULL,'Limón','',25,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(13,4,3,'ANGEL MAURICIO','LOJA SALINAS','1400671887','','',0,NULL,'mauricio@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(14,4,3,'CECILIA MARLENE','PEÑARANDA LOPEZ','1400755706','','',NULL,NULL,'cecilia@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(15,4,3,'EDDY PATRICIO','TORRES ESPINOZA','1401043342','','',NULL,NULL,'patricio@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(16,4,3,'WILMER DANILO','TORRES REINOSO','1400816763','','',NULL,NULL,'wilmer@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(17,4,4,'KARLA JESSENIA','ESPINOZA ZHUMI','1401021082','','',NULL,NULL,'karla@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(18,4,4,'JACKELINE GISSELA','MARIN RODRIGUEZ','1400947931','','',NULL,NULL,'jackeline@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(19,4,4,'MAYRA XIMENA ','BONI YUNGA','1401038789','','',NULL,NULL,'mayra@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(20,4,4,'WILMER NOLBERTO','GUZMAN JARA','1400476519','','',NULL,NULL,'nolberto@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(21,4,4,'DORIS EULALIA','JADAN GUAILLAZACA','1400776702','','',NULL,NULL,'doris@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(22,4,4,'JOMAYRA LIZETH','LLIGUIN CUJI','1401189376','','',NULL,NULL,'jomayra@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(23,4,4,'MIRIAM LISSETH','LLIVICURA DURAN','1401008022','','',NULL,NULL,'miriam@gmail.com',NULL,'Indanza',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(24,4,4,'MARIA ROSA ','LUCERO CHALCO','1400963813','','',NULL,NULL,'rosa@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(25,4,4,'GLADYS CARMINA','PACHECO PAUCAR','1400936579','','',NULL,NULL,'gladys@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(26,4,4,'WILFRIDO ORLANDO','QUIROGA CHIMBO','1400764880','','',NULL,NULL,'wilfrido@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(27,4,4,'MILTON GIOVANNI','ZHUMI DOTA','0106461726','','',NULL,NULL,'giovanni@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(28,5,5,'LUIS ANDRES','AREVALO SAMANIEGO','1400766844','','',NULL,NULL,'luis@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(29,5,5,'DIANA CAROLINA','BONILLA MARIN','1400755979','','',NULL,NULL,'diana_marin@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(30,5,5,'JESSICA ISABEL','BRITO LLIVICURA','1400947535','','',NULL,NULL,'jessica_brito@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(31,5,5,'LOYDA GRACIELA','DOMINGUEZ MOROCHO','1400856942','','',NULL,NULL,'loyda@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(32,5,5,'MARIANITA DE JESUS','GOMEZ GOMEZ','1400777320','','',NULL,NULL,'marianitadejesus@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(33,5,5,'SHIRLEY MELANIA','GUZMAN RODRIGUEZ','1400704423','','',NULL,NULL,'shirley@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(34,5,5,'MIRIAM FANNY','JARA CHILLOGALLO','1400959357','','',NULL,NULL,'miriam_jara@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(35,5,5,'GLADYS MARIBEL','QUITUIZACA TELLO','1401160294','','',NULL,NULL,'gladys_tello@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(36,5,5,'TANIA MILAGROS','RODRIGUEZ SUAREZ','1401032758','','',NULL,NULL,'tania_rodriguez@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(37,5,4,'JESSICA ALEXANDRA','ANGAMARCA PEÑARANDA','1401038532','','',NULL,NULL,'jessica_angamarca@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(38,5,4,'CARMEN ELIZABETH','DIAZ LOPEZ','1900717388','','',NULL,NULL,'carmen_diaz@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(39,5,4,'CESIA MARGARITA','GAMEZ PROAÑO','1450046188','','',NULL,NULL,'cesia_gamez@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(40,5,4,'SANDRA LORENA','GUAYLLZACA BONILLA','1400754865','','',NULL,NULL,'sandra_bonilla@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(41,5,4,'FANNY MARISOL','GUZMAN NIVELO','1400705321','','',NULL,NULL,'fanny_guzman@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(42,5,4,'OLIVER MOISES','SHARUP MACIAS','1400658264','','',NULL,NULL,'oliver_sharup@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(43,5,4,'MARIA CRUZ','SICCHA SANCHEZ','0105494629','','',NULL,NULL,'maria_siccha@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(44,5,4,'CECILIA ELIZABETH','VILLA GOMEZ','1401033731','','',NULL,NULL,'cecilia_villa@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(45,4,5,'MELINA CARMITA','CHACON MARIN','1400673578','','',NULL,NULL,'melina_chacon@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(46,4,5,'MAYTE JEANETH','CUJI IDROVO','1400795991','','',NULL,NULL,'mayte_cuji@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(47,4,5,'JEFFERSON FERNANDO','CUJI SUMBA','1400968259','','',NULL,NULL,'jefferson_cuji@gmail.com',NULL,'Plan de Milagro',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(48,4,5,'LUZ ALICIA','GUZMAN YUNGA','1400959340','','',NULL,NULL,'luz_guzman@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(49,4,5,'DARWIN EFRAIN','GUZMAN YUNGA','1401309784','','',NULL,NULL,'darwin_guzman@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(50,4,5,'CRISTINA MARISOL','PEÑARANDA LOPEZ','1400755672','','',NULL,NULL,'cristina_lopez@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(51,4,5,'MARCIA GERMANIA','SANCHEZ PATIÑO','1400490759','','',NULL,NULL,'marcia_sanchez@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(52,4,5,'JALENNY MARISOL','ZHUNIO LOJA','1401040124','','',NULL,NULL,'jelenny_loja@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(53,4,5,'DENIS ALEXANDER','ZAMORA CASTRO','1400756324','','',NULL,NULL,'denis_zamora@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(54,4,5,'JESSICA MARISOL','QUIROGA CHIMBO','1400948905','','',NULL,NULL,'jessica_chimbo@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(57,5,6,'BERTHA SUSANA','ANGAMARCA LUCERO','1400966873','','',NULL,NULL,'sangamarca17@gmail.com',NULL,'Indanza',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(58,5,6,'MARIA ZOLAYDA','DOMINGUEZ MOROCHO','1400856454','','',NULL,NULL,'zolaydadominguez17@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(59,5,6,'JOHANNA PAOLA','GUARACA GUNCAY','1401006331','','',NULL,NULL,'guaracapaola@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(60,5,6,'JEFFERSON RAIMUNDO','GUZMAN CARDENAS','0105433569','','',NULL,NULL,'gepeguzman1998@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(61,5,6,'JENNY DAYANA','JAMA UNUP','1401035207','','',NULL,NULL,'dayapark12@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(62,5,6,'LIDIA PAOLA','JARA SIGUENCIA','1401037286','','',NULL,NULL,'jarapaola@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(63,5,6,'GLENDA MARIUXI','JAYA CALLE','1401297450','','',NULL,NULL,'glendajayac@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(64,5,6,'SANDRA TATIANA','RODRIGUEZ LITUMA','1401298482','','',NULL,NULL,'tatirodriz105000@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(65,5,6,'MIRIAM CAROLINA','ROMERO CHACON','1401033103','','',NULL,NULL,'miriamcrc1999@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(66,4,6,'ANGEL ALFREDO','GARCIA ALVAREZ','1400952667','','',NULL,NULL,'angelzgarciaz664@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(67,4,6,'MARISOL TSUNKINUA','JIMPIKIT WAMPASH','1401035850','','',NULL,NULL,'jimpikitmarisol@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(68,4,6,'CRISTIAN GEOVANNY','PELAEZ YUNGA','0105432314','','',NULL,NULL,'crisyunga1998@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(69,4,6,'HERNÁN DARIO','QUICHIMBO CASUAL','1401007099','','',NULL,NULL,'quichimbodario@gmail.com',NULL,'Indanza',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(70,4,6,'JONATHAN ISMAEL','ROMERO CHACON','1401033079','','',NULL,NULL,'adelezapatanxt@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(71,4,6,'MGUEL ADRIAN','SIGUENZA SALINAS','1401228083','','',NULL,NULL,'siadrian47013@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(72,4,6,'ANDREA ELIZABETH','TORRES AJILA','0150320570','','',NULL,NULL,'anshytorres@gmail.com',NULL,'Limón',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,NULL,NULL,NULL),(73,NULL,NULL,'PRUEBA','PRUEBA','1256678697','','',0,'2021-03-09','prueba@gmail.com',NULL,'','',34,NULL,'requerido/sin_foto_perfil.png',3,1,NULL,1,1,1),(74,NULL,NULL,'ESTUDIANTE','ESTUDIANTE','3453434654','','',NULL,NULL,'estudiante@gmail.com',NULL,'',NULL,NULL,NULL,'requerido/sin_foto_perfil.png',NULL,NULL,NULL,1,1,2);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postulante`
--

DROP TABLE IF EXISTS `postulante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `postulante` (
  `id_postulante` int(11) NOT NULL AUTO_INCREMENT,
  `oferta_laboral_id_oferta` int(11) NOT NULL,
  `persona_id_persona` int(11) NOT NULL,
  `estado_postulante` bit(1) DEFAULT NULL,
  `fecha_postulante` date DEFAULT NULL,
  `confirmacion` bit(1) DEFAULT NULL,
  `fecha_confirmacion` date DEFAULT NULL,
  PRIMARY KEY (`id_postulante`),
  KEY `fk_postulante_oferta_laboral1_idx` (`oferta_laboral_id_oferta`),
  KEY `fk_postulante_persona1_idx` (`persona_id_persona`),
  CONSTRAINT `fk_postulante_oferta_laboral1` FOREIGN KEY (`oferta_laboral_id_oferta`) REFERENCES `oferta_laboral` (`id_oferta`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_postulante_persona1` FOREIGN KEY (`persona_id_persona`) REFERENCES `persona` (`id_persona`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postulante`
--

LOCK TABLES `postulante` WRITE;
/*!40000 ALTER TABLE `postulante` DISABLE KEYS */;
INSERT INTO `postulante` VALUES (40,14,74,'','2021-03-09','','2021-03-09'),(41,15,74,'','2021-03-09','','2021-03-09');
/*!40000 ALTER TABLE `postulante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preferencia_laboral`
--

DROP TABLE IF EXISTS `preferencia_laboral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `preferencia_laboral` (
  `id_preferencia_laboral` int(11) NOT NULL AUTO_INCREMENT,
  `sector_publico` int(11) DEFAULT NULL,
  `sector_prvado` int(11) DEFAULT NULL,
  `aspiracion_salarial` varchar(45) DEFAULT NULL,
  `trabajar_residencia` int(11) DEFAULT NULL,
  `id_hoja_vida_estudiante` int(11) NOT NULL,
  PRIMARY KEY (`id_preferencia_laboral`),
  KEY `fk_preferencia_laboral_hoja_vida_estudiante1_idx` (`id_hoja_vida_estudiante`),
  CONSTRAINT `preferencia_laboralHoja_vida_estudiante` FOREIGN KEY (`id_hoja_vida_estudiante`) REFERENCES `hoja_vida_estudiante` (`id_hoja_vida_estudiante`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preferencia_laboral`
--

LOCK TABLES `preferencia_laboral` WRITE;
/*!40000 ALTER TABLE `preferencia_laboral` DISABLE KEYS */;
/*!40000 ALTER TABLE `preferencia_laboral` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promocion`
--

DROP TABLE IF EXISTS `promocion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promocion` (
  `id_promocion` int(11) NOT NULL AUTO_INCREMENT,
  `promocion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_promocion`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promocion`
--

LOCK TABLES `promocion` WRITE;
/*!40000 ALTER TABLE `promocion` DISABLE KEYS */;
INSERT INTO `promocion` VALUES (3,'NOVIEMBRE 2017 - ABRIL 2018'),(4,'MAYO - OCTUBRE 2018'),(5,'OCTUBRE 2019 - ABRIL 2020'),(6,'NOVIEMBRE 2019 - ABRIL 2020');
/*!40000 ALTER TABLE `promocion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincia`
--

DROP TABLE IF EXISTS `provincia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provincia` (
  `id_provincia` int(11) NOT NULL AUTO_INCREMENT,
  `provincia` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_provincia`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincia`
--

LOCK TABLES `provincia` WRITE;
/*!40000 ALTER TABLE `provincia` DISABLE KEYS */;
INSERT INTO `provincia` VALUES (1,'MORONA SANTIAGO'),(2,'AZUAY'),(3,'ZAMORA'),(4,'CAÑAR'),(6,'PICHINCHA');
/*!40000 ALTER TABLE `provincia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `referencia_personal`
--

DROP TABLE IF EXISTS `referencia_personal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `referencia_personal` (
  `id_referencia_personal` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `correo_electronico` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `id_hoja_vida_estudiante` int(11) NOT NULL,
  PRIMARY KEY (`id_referencia_personal`),
  KEY `fk_referencia_personal_hoja_vida_estudiante1_idx` (`id_hoja_vida_estudiante`),
  CONSTRAINT `referencia_personalHoja_vida_estudiante` FOREIGN KEY (`id_hoja_vida_estudiante`) REFERENCES `hoja_vida_estudiante` (`id_hoja_vida_estudiante`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referencia_personal`
--

LOCK TABLES `referencia_personal` WRITE;
/*!40000 ALTER TABLE `referencia_personal` DISABLE KEYS */;
/*!40000 ALTER TABLE `referencia_personal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_certificado`
--

DROP TABLE IF EXISTS `tipo_certificado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_certificado` (
  `id_tipo_certificado` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_certificado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_certificado`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_certificado`
--

LOCK TABLES `tipo_certificado` WRITE;
/*!40000 ALTER TABLE `tipo_certificado` DISABLE KEYS */;
INSERT INTO `tipo_certificado` VALUES (1,'ASISTENCIA'),(2,'APROBADO');
/*!40000 ALTER TABLE `tipo_certificado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_contrato`
--

DROP TABLE IF EXISTS `tipo_contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_contrato` (
  `id_tipo_contrato` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_contrato` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_contrato`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_contrato`
--

LOCK TABLES `tipo_contrato` WRITE;
/*!40000 ALTER TABLE `tipo_contrato` DISABLE KEYS */;
INSERT INTO `tipo_contrato` VALUES (1,'DEFINIDO'),(2,'INDEFINIDO'),(4,'OCASIONAL');
/*!40000 ALTER TABLE `tipo_contrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_empresa`
--

DROP TABLE IF EXISTS `tipo_empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_empresa` (
  `id_tipo_empresa` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_empresa` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_empresa`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_empresa`
--

LOCK TABLES `tipo_empresa` WRITE;
/*!40000 ALTER TABLE `tipo_empresa` DISABLE KEYS */;
INSERT INTO `tipo_empresa` VALUES (1,'PUBLICA'),(2,'PRIVADA'),(3,'MIXTA');
/*!40000 ALTER TABLE `tipo_empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_evento`
--

DROP TABLE IF EXISTS `tipo_evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_evento` (
  `id_tipo_evento` int(11) NOT NULL AUTO_INCREMENT,
  `evento` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_evento`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_evento`
--

LOCK TABLES `tipo_evento` WRITE;
/*!40000 ALTER TABLE `tipo_evento` DISABLE KEYS */;
INSERT INTO `tipo_evento` VALUES (1,'ACADEMICO'),(2,'ARTISTICO'),(3,'CULTURAL');
/*!40000 ALTER TABLE `tipo_evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_logro`
--

DROP TABLE IF EXISTS `tipo_logro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_logro` (
  `id_tipo_logro` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_logro` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_logro`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_logro`
--

LOCK TABLES `tipo_logro` WRITE;
/*!40000 ALTER TABLE `tipo_logro` DISABLE KEYS */;
INSERT INTO `tipo_logro` VALUES (1,'ACADEMICO'),(2,'PERSONAL');
/*!40000 ALTER TABLE `tipo_logro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_persona`
--

DROP TABLE IF EXISTS `tipo_persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_persona` (
  `id_tipo_persona` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_persona` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_persona`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_persona`
--

LOCK TABLES `tipo_persona` WRITE;
/*!40000 ALTER TABLE `tipo_persona` DISABLE KEYS */;
INSERT INTO `tipo_persona` VALUES (1,'NATURAL'),(2,'JURIDCA');
/*!40000 ALTER TABLE `tipo_persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_sangre`
--

DROP TABLE IF EXISTS `tipo_sangre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_sangre` (
  `id_tipo_sangre` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_sangre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_sangre`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_sangre`
--

LOCK TABLES `tipo_sangre` WRITE;
/*!40000 ALTER TABLE `tipo_sangre` DISABLE KEYS */;
INSERT INTO `tipo_sangre` VALUES (2,'A+'),(3,'AB+'),(4,'AB-');
/*!40000 ALTER TABLE `tipo_sangre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `id_persona` int(11) DEFAULT NULL,
  `rol` varchar(45) DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `clave` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `usuarioPersona_idx` (`id_persona`),
  CONSTRAINT `usuarioPersona` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id_persona`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (3,3,'ADMINISTRADOR','admin@istl','admin@istl'),(20,12,'ESTUDIANTE','1400493084','1400493084'),(21,13,'ESTUDIANTE','1400671887','1400671887'),(22,14,'ESTUDIANTE','1400755706','1400755706'),(23,15,'ESTUDIANTE','1401043342','1401043342'),(24,16,'ESTUDIANTE','1400816763','1400816763'),(25,17,'ESTUDIANTE','1401021082','1401021082'),(26,18,'ESTUDIANTE','1400947931','1400947931'),(27,19,'ESTUDIANTE','1401038789','1401038789'),(28,20,'ESTUDIANTE','1400476519','1400476519'),(29,21,'ESTUDIANTE','1400776702','1400776702'),(30,22,'ESTUDIANTE','1401189376','1401189376'),(31,23,'ESTUDIANTE','1401008022','1401008022'),(32,24,'ESTUDIANTE','1400963813','1400963813'),(33,25,'ESTUDIANTE','1400936579','1400936579'),(34,26,'ESTUDIANTE','1400764880','1400764880'),(35,27,'ESTUDIANTE','0106461726','0106461726'),(36,28,'ESTUDIANTE','1400766844','1400766844'),(37,29,'ESTUDIANTE','1400755979','1400755979'),(38,30,'ESTUDIANTE','1400947535','1400947535'),(39,31,'ESTUDIANTE','1400856942','1400856942'),(40,32,'ESTUDIANTE','1400777320','1400777320'),(41,33,'ESTUDIANTE','1400704423','1400704423'),(42,34,'ESTUDIANTE','1400959357','1400959357'),(43,35,'ESTUDIANTE','1401160294','1401160294'),(44,36,'ESTUDIANTE','1401032758','1401032758'),(45,37,'ESTUDIANTE','1401038532','1401038532'),(46,38,'ESTUDIANTE','1900717388','1900717388'),(47,39,'ESTUDIANTE','1450046188','1450046188'),(48,40,'ESTUDIANTE','1400754865','1400754865'),(49,41,'ESTUDIANTE','1400705321','1400705321'),(50,42,'ESTUDIANTE','1400658264','1400658264'),(51,43,'ESTUDIANTE','0105494629','0105494629'),(52,44,'ESTUDIANTE','1401033731','1401033731'),(53,45,'ESTUDIANTE','1400673578','1400673578'),(54,46,'ESTUDIANTE','1400795991','1400795991'),(55,47,'ESTUDIANTE','1400968259','1400968259'),(56,48,'ESTUDIANTE','1400959340','1400959340'),(57,49,'ESTUDIANTE','1401309784','1401309784'),(58,50,'ESTUDIANTE','1400755672','1400755672'),(59,51,'ESTUDIANTE','1400490759','1400490759'),(60,52,'ESTUDIANTE','1401040124','1401040124'),(61,53,'ESTUDIANTE','1400756324','1400756324'),(62,54,'ESTUDIANTE','1400948905','1400948905'),(64,57,'ESTUDIANTE','1400966873','1400966873'),(65,58,'ESTUDIANTE','1400856454','1400856454'),(66,59,'ESTUDIANTE','1401006331','1401006331'),(67,60,'ESTUDIANTE','0105433569','0105433569'),(68,61,'ESTUDIANTE','1401035207','1401035207'),(69,62,'ESTUDIANTE','1401037286','1401037286'),(70,63,'ESTUDIANTE','1401297450','1401297450'),(71,64,'ESTUDIANTE','1401298482','1401298482'),(72,65,'ESTUDIANTE','1401033103','1401033103'),(73,66,'ESTUDIANTE','1400952667','1400952667'),(74,67,'ESTUDIANTE','1401035850','1401035850'),(75,68,'ESTUDIANTE','0105432314','0105432314'),(76,69,'ESTUDIANTE','1401007099','1401007099'),(77,70,'ESTUDIANTE','1401033079','1401033079'),(78,71,'ESTUDIANTE','1401228083','1401228083'),(79,72,'ESTUDIANTE','0150320570','0150320570'),(80,73,'EMPLEADOR','prueba','prueba'),(81,74,'ESTUDIANTE','estudiante','estudiante');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'bolsa_empleo_istl'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-09 17:53:12
