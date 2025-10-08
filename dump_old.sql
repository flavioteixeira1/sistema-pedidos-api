/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19  Distrib 10.5.29-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: rest_spring_db
-- ------------------------------------------------------
-- Server version	10.5.29-MariaDB-0+deb11u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Pessoas`
--

DROP TABLE IF EXISTS `Pessoas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Pessoas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pessoas`
--

LOCK TABLES `Pessoas` WRITE;
/*!40000 ALTER TABLE `Pessoas` DISABLE KEYS */;
/*!40000 ALTER TABLE `Pessoas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (11);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `endereco` varchar(255) NOT NULL,
  `data_pedido` timestamp NULL DEFAULT NULL,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_pedido_status` (`status`),
  KEY `idx_pedido_data` (`data_pedido`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (1,'Rua das Camélias , 123 - Vila Nazaré, São Bernardo do Campo - SP','2025-09-30 20:59:01','PENDENTE'),(2,'Avenida Brasil, 456 - Jardins, Rio de Janeiro - RJ','2025-09-30 20:59:01','ENVIADO'),(3,'Rua das Palmeiras, 789 - Vila Madalena, Curitiba - PR','2025-09-30 20:59:01','ENTREGUE'),(5,'Rua das Flores, 123','2025-09-30 22:37:44','PENDENTE'),(9,'Rua das Camélias , 123 - Vila Nazaré, São Bernardo do Campo - SP','2025-10-01 00:24:14','PENDENTE'),(10,'Rua das Camélias , 123 - Vila Nazaré, São Bernardo do Campo - SP','2025-10-01 00:25:07','PENDENTE'),(11,'Rua das Camélias , 123 - Vila Nazaré, São Bernardo do Campo - SP','2025-10-01 00:33:01','PENDENTE'),(12,'Rua das Camélias , 123 - Vila Nazaré, São Bernardo do Campo - SP','2025-10-01 00:36:02','PENDENTE'),(13,'Rua das Camélias , 1375 - Vila Nazaré, São Bernardo do Campo - SP','2025-10-01 00:41:00','PENDENTE'),(14,'Avenida Brasil, 456 - Jardins, Rio de Janeiro - RJ','2025-10-01 00:44:34','ENVIADO'),(15,'Avenida Brasil, 456 - Jardins, Rio de Janeiro - RJ','2025-10-01 00:44:38','ENVIADO'),(16,'Avenida Brasil, 456 - Jardins, Rio de Janeiro - RJ','2025-10-01 00:44:39','ENVIADO'),(18,'Avenida Main Street, 35 Ap 5','2025-10-01 00:56:43','PENDENTE'),(19,'Avenida Brasil, 456 - Jardins, Rio de Janeiro - RJ','2025-10-01 00:58:07','ENTREGUE');
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_produtos`
--

DROP TABLE IF EXISTS `pedido_produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_produtos` (
  `pedido_id` bigint(20) NOT NULL,
  `produtos_id` bigint(20) NOT NULL,
  PRIMARY KEY (`pedido_id`,`produtos_id`),
  KEY `produtos_id` (`produtos_id`),
  CONSTRAINT `pedido_produtos_ibfk_1` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`) ON DELETE CASCADE,
  CONSTRAINT `pedido_produtos_ibfk_2` FOREIGN KEY (`produtos_id`) REFERENCES `produto` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_produtos`
--

LOCK TABLES `pedido_produtos` WRITE;
/*!40000 ALTER TABLE `pedido_produtos` DISABLE KEYS */;
INSERT INTO `pedido_produtos` VALUES (1,3),(1,5),(2,2),(2,4),(2,7),(2,10),(3,15),(3,18),(3,20),(5,10),(5,22),(9,3),(9,5),(10,3),(10,5),(11,3),(11,5),(12,3),(12,5),(13,3),(13,5),(14,2),(14,4),(14,7),(14,10),(15,2),(15,4),(15,7),(15,10),(16,2),(16,4),(16,7),(16,10),(18,5),(18,7),(19,8),(19,12);
/*!40000 ALTER TABLE `pedido_produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoas`
--

DROP TABLE IF EXISTS `pessoas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoas`
--

LOCK TABLES `pessoas` WRITE;
/*!40000 ALTER TABLE `pessoas` DISABLE KEYS */;
INSERT INTO `pessoas` VALUES (1,'teste','segredo'),(3,'terceira pessoa','Senha3'),(4,'pessoa teste','$2a$10$qrV1xJeB9P0Re/ywSlLeo.kS9.I4Ftr9g0xZsKZsr10eQsgWQXJDa'),(5,'peste','$2a$10$89TVy0iIEcZk2Xmi7TyurubQm/fxiKZEHvfd/S7dgeXdRkRwtEZMy'),(6,'teste','$2a$10$1wxQGl0EH7U6K/tfXnZGmO1sr8II8cAfECu1St./CPBnnumm0SbNi');
/*!40000 ALTER TABLE `pessoas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `preco` decimal(10,2) NOT NULL,
  `descricao` text DEFAULT NULL,
  `data_criacao` timestamp NOT NULL DEFAULT current_timestamp(),
  `data_atualizacao` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `idx_produto_nome` (`nome`),
  KEY `idx_produto_preco` (`preco`),
  KEY `idx_produto_data_criacao` (`data_criacao`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (2,'Arroz Branco',6.50,'Arroz branco agulhinha, pacote 5kg','2025-09-30 20:59:01','2025-09-30 20:59:01'),(3,'Feijão Carioca',7.80,'Feijão carioca especial, pacote 1kg','2025-09-30 20:59:01','2025-09-30 20:59:01'),(4,'Feijão Preto',8.20,'Feijão preto tradicional, pacote 1kg','2025-09-30 20:59:01','2025-09-30 20:59:01'),(5,'Lentilha',9.50,'Lentilha seca, pacote 500g','2025-09-30 20:59:01','2025-09-30 20:59:01'),(6,'Grão de Bico',10.90,'Grão de bico seco, pacote 500g','2025-09-30 20:59:01','2025-09-30 20:59:01'),(7,'Aveia em Flocos',5.90,'Aveia em flocos finos, pacote 500g','2025-09-30 20:59:01','2025-09-30 20:59:01'),(8,'Quinoa',15.90,'Quinoa real em grãos, pacote 500g','2025-09-30 20:59:01','2025-09-30 20:59:01'),(9,'Granola Tradicional',12.50,'Granola com mel e castanhas, pacote 400g','2025-09-30 20:59:01','2025-09-30 20:59:01'),(10,'Milho para Pipoca',4.90,'Milho especial para pipoca, pacote 500g','2025-09-30 20:59:01','2025-09-30 20:59:01'),(11,'Farinha de Trigo',4.50,'Farinha de trigo especial, pacote 1kg','2025-09-30 20:59:01','2025-09-30 20:59:01'),(12,'Farinha de Mandioca',5.20,'Farinha de mandioca torrada, pacote 1kg','2025-09-30 20:59:01','2025-09-30 20:59:01'),(13,'Fubá Mimoso',3.90,'Fubá de milho mimoso, pacote 500g','2025-09-30 20:59:01','2025-09-30 20:59:01'),(14,'Polenta Instantânea',6.80,'Polenta instantânea, pacote 500g','2025-09-30 20:59:01','2025-09-30 20:59:01'),(15,'Farinha de Rosca',7.50,'Farinha de rosca fina, pacote 500g','2025-09-30 20:59:01','2025-09-30 20:59:01'),(16,'Macarrão Espaguete',3.90,'Macarrão espaguete número 8, pacote 500g','2025-09-30 20:59:01','2025-09-30 20:59:01'),(17,'Macarrão Parafuso',4.20,'Macarrão tipo parafuso, pacote 500g','2025-09-30 20:59:01','2025-09-30 20:59:01'),(18,'Massa para Lasanha',8.90,'Massa pré-cozida para lasanha, pacote 500g','2025-09-30 20:59:01','2025-09-30 20:59:01'),(19,'Talharim Fresco',12.90,'Talharim fresco, bandeja 300g','2025-09-30 20:59:01','2025-09-30 20:59:01'),(20,'Açúcar Cristal',3.50,'Açúcar cristal refinado, pacote 1kg','2025-09-30 20:59:01','2025-09-30 20:59:01'),(21,'Sal Refinado',2.90,'Sal refinado iodado, pacote 1kg','2025-09-30 20:59:01','2025-09-30 20:59:01'),(22,'Óleo de Soja',7.90,'Óleo de soja refinado, garrafa 900ml','2025-09-30 20:59:01','2025-09-30 20:59:01'),(23,'Azeite de Oliva',18.90,'Azeite de oliva extra virgem, garrafa 500ml','2025-09-30 20:59:01','2025-09-30 20:59:01'),(24,'Vinagre de Álcool',2.50,'Vinagre de álcool 4%, garrafa 750ml','2025-09-30 20:59:01','2025-09-30 20:59:01'),(25,'Sucrilhos Kelloggs',14.90,'Cereal matinal sucrilhos, caixa 300g','2025-09-30 20:59:01','2025-09-30 20:59:01'),(26,'Nescali',9.90,'Cereal matinal com chocolate, caixa 250g','2025-09-30 20:59:01','2025-09-30 20:59:01'),(27,'Musli Tradicional',16.50,'Mix de cereais e frutas secas, pacote 400g','2025-09-30 20:59:01','2025-09-30 20:59:01'),(28,'Flocos de Milho',6.90,'Flocos de milho sem açúcar, pacote 500g','2025-09-30 20:59:01','2025-09-30 20:59:01'),(29,'Linhaça Dourada',11.90,'Semente de linhaça dourada, pacote 200g','2025-09-30 20:59:01','2025-09-30 20:59:01'),(30,'Chia em Grãos',13.50,'Semente de chia, pacote 200g','2025-09-30 20:59:01','2025-09-30 20:59:01'),(31,'Gergelim Preto',8.90,'Gergelim preto torrado, pacote 100g','2025-09-30 20:59:01','2025-09-30 20:59:01'),(32,'Semente de Abóbora',12.90,'Semente de abóbora torrada, pacote 150g','2025-09-30 20:59:01','2025-09-30 20:59:01'),(34,'Smartphone Motorola MOtoG90',1450.00,'Smaptphone Motorola modelo MOTOG90 com carregador e cabo USB-C','2025-10-03 02:19:43','2025-10-03 02:19:43');
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,'flavio','$2a$10$VLpIFpglAJDYyJqdsPVRqeNDQdMwRO/R5wn7bmy5a/PaBFoj62Zv.',NULL),(4,'Administrador','$2a$10$D97mk3ONmKuJUu4wu3FssOqLa4smueusGS4kv4LfhGidFOS73Kc9e',NULL),(5,'teste11','$2a$10$oHpuhbq1mZJuGgpGntfqc.NS0CQ.ibINFExgJxrSRURj6AMp31e4m','segredo@segredo.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users1`
--

DROP TABLE IF EXISTS `users1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `users1` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users1`
--

LOCK TABLES `users1` WRITE;
/*!40000 ALTER TABLE `users1` DISABLE KEYS */;
/*!40000 ALTER TABLE `users1` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-06 21:18:07
