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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `data_cadastro` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'João Silva','joao.silva@email.com','(11) 99999-1234','2025-10-06 10:00:00'),(2,'Maria Santos','maria.santos@email.com','(11) 98888-5678','2025-10-06 10:15:00'),(3,'Pedro Oliveira','pedro.oliveira@email.com','(11) 97777-9012','2025-10-06 11:30:00'),(4,'Ana Costa','ana.costa@email.com','(11) 96666-3456','2025-10-06 12:45:00'),(5,'Carlos Rodrigues','carlos.rodrigues@email.com','(11) 95555-7890','2025-10-06 14:20:00'),(6,'Aero Oliveira','peassdro.oliveira@email.com','(11) 91777-9012','2025-10-06 11:30:00'),(7,'Marcos Filho Diniz','mdiniz.esquema@email.com','(11) 91457-0313','2025-10-06 11:30:00'),(8,'Bruno Moreira Feliz','brunofeliz.ema@email.com','(11) 97527-0313','2022-02-02 10:25:00');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
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
  `cliente_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_pedido_status` (`status`),
  KEY `idx_pedido_data` (`data_pedido`),
  KEY `fk_pedido_cliente` (`cliente_id`),
  CONSTRAINT `fk_pedido_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (1,'Rua das Flores, 123 - Centro, São Paulo - SP','2025-10-06 10:30:00','PENDENTE',1),(2,'Avenida Paulista, 1000 - Bela Vista, São Paulo - SP','2025-10-06 11:00:00','ENVIADO',2),(3,'Rua Augusta, 500 - Consolação, São Paulo - SP','2025-10-06 12:00:00','ENTREGUE',3),(4,'Alameda Santos, 200 - Jardins, São Paulo - SP','2025-10-06 13:30:00','PENDENTE',4),(5,'Rua da Consolação, 1500 - Cerqueira César, São Paulo - SP','2025-10-06 14:45:00','ENVIADO',5),(6,'Rua Haddock Lobo, 300 - Cerqueira César, São Paulo - SP','2025-10-06 15:20:00','ENTREGUE',1),(7,'Avenida Rebouças, 800 - Pinheiros, São Paulo - SP','2025-10-06 16:10:00','PENDENTE',2),(8,'Rua Oscar Freire, 900 - Jardins, São Paulo - SP','2025-10-06 17:05:00','ENVIADO',3),(20,'Rua Exemplo, 123 - Centro - São Paulo/SP','2025-10-07 23:27:01','PENDENTE',NULL),(21,'Rua Oscar Freire, 900 - Jardins, São Paulo - SP','2025-10-07 23:29:42','ENVIADO',NULL),(22,'Rua Oscar Freire, 900 - Jardins, São Paulo - SP','2025-10-07 23:31:54','ENVIADO',NULL),(23,'Rua Nova, 456 - Centro - Rio de Janeiro/RJ','2025-10-07 23:41:39','PENDENTE',NULL),(24,'Rua Teste, 789 - Vila Madalena - São Paulo/SP','2025-10-07 23:42:24','PENDENTE',NULL),(25,'Alameda Santos, 1000 - Jardins - São Paulo/SP','2025-10-07 23:44:45','PROCESSANDO',2),(26,'Alameda Santos, 1000 - Jardins - São Paulo/SP','2025-10-07 23:45:53','PROCESSANDO',2),(27,'Rua Oscar Freire, 900 - Jardins, São Paulo - SP','2025-10-07 23:51:47','ENVIADO',3),(28,'Rua Oscar Freire, 900 - Jardins, São Paulo - SP','2025-10-07 23:52:43','ENVIADO',3),(29,'Rua Oscar Freire, 900 - Jardins, São Paulo - SP','2025-10-07 23:54:37','ENVIADO',NULL),(30,'Rua das Flores, 123 - Centro, São Paulo - SP','2025-10-07 23:57:36','PENDENTE',1),(31,'Avenida Paulista, 1500 - Bela Vista - São Paulo/SP','2025-10-08 00:37:09','PENDENTE',1),(32,'Avenida getúlio Vargas , 865 - baeta Nevesa - São Bernardo do Campo/SP','2025-10-08 00:42:12','PENDENTE',6),(33,'Rua Marechal Deodoro  , 1465 - centro - São Bernardo do Campo/SP','2025-10-08 00:43:38','PROCESSANDO',5),(34,'Rua Professor Licínio  , 45 - casa 2  - Santo André/SP','2025-10-08 00:44:53','ENTREGUE',3),(35,'Rua Teste, 789 - Vila Madalena - São Paulo/SP','2025-10-08 00:55:40','PENDENTE',4);
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
INSERT INTO `pedido_produtos` VALUES (1,1),(1,2),(1,3),(2,4),(2,5),(2,6),(3,7),(3,8),(3,9),(4,10),(4,11),(4,12),(5,13),(5,14),(5,15),(6,16),(6,17),(6,18),(7,19),(7,20),(7,21),(8,22),(8,23),(8,24),(20,1),(20,2),(20,3),(21,22),(21,23),(21,24),(22,22),(22,23),(22,24),(23,1),(23,2),(23,3),(24,4),(24,5),(25,6),(25,7),(25,8),(26,6),(26,7),(26,8),(27,23),(28,23),(29,23),(30,1),(30,2),(30,3),(31,1),(31,2),(31,3),(32,8),(32,12),(33,4),(33,7),(34,2),(35,4),(35,5);
/*!40000 ALTER TABLE `pedido_produtos` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,'Arroz Integral',8.50,'Arroz integral tipo 1, pacote 5kg','2025-10-06 09:00:00','2025-10-06 09:00:00'),(2,'Feijão Carioca',7.80,'Feijão carioca especial, pacote 1kg','2025-10-06 09:00:00','2025-10-06 09:00:00'),(3,'Açúcar Cristal',4.20,'Açúcar cristal refinado, pacote 1kg','2025-10-06 09:00:00','2025-10-06 09:00:00'),(4,'Café em Pó',12.90,'Café torrado e moído, pacote 500g','2025-10-06 09:00:00','2025-10-06 09:00:00'),(5,'Leite UHT',4.50,'Leite integral UHT, caixa 1L','2025-10-06 09:00:00','2025-10-06 09:00:00'),(6,'Óleo de Soja',7.90,'Óleo de soja refinado, garrafa 900ml','2025-10-06 09:00:00','2025-10-06 09:00:00'),(7,'Farinha de Trigo',3.90,'Farinha de trigo especial, pacote 1kg','2025-10-06 09:00:00','2025-10-06 09:00:00'),(8,'Macarrão Espaguete',3.50,'Macarrão espaguete, pacote 500g','2025-10-06 09:00:00','2025-10-06 09:00:00'),(9,'Molho de Tomate',2.90,'Molho de tomate tradicional, sachê 340g','2025-10-06 09:00:00','2025-10-06 09:00:00'),(10,'Sal Refinado',2.50,'Sal refinado iodado, pacote 1kg','2025-10-06 09:00:00','2025-10-06 09:00:00'),(11,'Detergente Líquido',1.90,'Detergente líquido neutro, frasco 500ml','2025-10-06 09:00:00','2025-10-06 09:00:00'),(12,'Sabão em Pó',12.50,'Sabão em pó para roupas, pacote 1kg','2025-10-06 09:00:00','2025-10-06 09:00:00'),(13,'Amaciante',8.90,'Amaciante de roupas, frasco 2L','2025-10-06 09:00:00','2025-10-06 09:00:00'),(14,'Água Sanitária',3.50,'Água sanitária, frasco 1L','2025-10-06 09:00:00','2025-10-06 09:00:00'),(15,'Desinfetante',6.90,'Desinfetante pinho, frasco 1L','2025-10-06 09:00:00','2025-10-06 09:00:00'),(16,'Esponja de Aço',2.20,'Esponja de aço para limpeza, pacote com 5 unidades','2025-10-06 09:00:00','2025-10-06 09:00:00'),(17,'Papel Toalha',5.90,'Papel toalha, pacote com 2 rolos','2025-10-06 09:00:00','2025-10-06 09:00:00'),(18,'Sabonete Líquido',4.80,'Sabonete líquido para mãos, frasco 200ml','2025-10-06 09:00:00','2025-10-06 09:00:00'),(19,'Panela de Pressão',89.90,'Panela de pressão 4,5L, alumínio','2025-10-06 09:00:00','2025-10-06 09:00:00'),(20,'Jogo de Panelas',129.90,'Jogo com 3 panelas antiaderentes','2025-10-06 09:00:00','2025-10-06 09:00:00'),(21,'Conjunto de Talheres',45.50,'Conjunto de talheres inox, 24 peças','2025-10-06 09:00:00','2025-10-06 09:00:00'),(22,'Toalha de Banho',29.90,'Toalha de banho felpuda, tamanho banho','2025-10-06 09:00:00','2025-10-06 09:00:00'),(23,'Jogo de Copos',35.80,'Jogo de copos americanos, 6 unidades','2025-10-06 09:00:00','2025-10-06 09:00:00'),(24,'Vassoura',15.90,'Vassoura de piaçava com cabo de madeira','2025-10-06 09:00:00','2025-10-06 09:00:00'),(25,'Produto modificado',10223.50,'esse produto foi modificado','2025-10-07 20:04:51','2025-10-07 20:05:49');
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-08 11:58:54
