-- Migration 1: Criação das tabelas base

-- Tabela de clientes
CREATE TABLE IF NOT EXISTS `clientes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `data_cadastro` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela de produtos
CREATE TABLE IF NOT EXISTS `produto` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela de pedidos
CREATE TABLE IF NOT EXISTS `pedido` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `endereco` varchar(255) NOT NULL,
  `data_pedido` timestamp NULL DEFAULT NULL,
  `status` varchar(50) NOT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `observacoes` text DEFAULT NULL,
  `data_atualizacao_status` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_pedido_status` (`status`),
  KEY `idx_pedido_data` (`data_pedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela de itens do pedido
CREATE TABLE IF NOT EXISTS `pedido_produtos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pedido_id` bigint(20) NOT NULL,
  `produto_id` bigint(20) NOT NULL,
  `quantidade` int(11) NOT NULL DEFAULT 1,
  `preco_unitario` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_pedido_produto` (`pedido_id`,`produto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabela de usuários
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `role` varchar(20) NOT NULL DEFAULT 'USER',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Tabela de sequência do Hibernate (se necessário)
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
