-- Migration 3: Inserção dos dados de teste para PostgreSQL

-- Inserir clientes (remover as aspas invertidas e usar DEFAULT para IDs)
INSERT INTO clientes (id, nome, email, telefone, data_cadastro) VALUES
(1, 'João Silva', 'joao.silva@email.com', '(11) 99999-1234', '2025-10-06 10:00:00'),
(2, 'Maria Santos', 'maria.santos@email.com', '(11) 98888-5678', '2025-10-06 10:15:00'),
(3, 'Pedro Oliveira', 'pedro.oliveira@email.com', '(11) 97777-9012', '2025-10-06 11:30:00'),
(4, 'Ana Costa', 'ana.costa@email.com', '(11) 96666-3456', '2025-10-06 12:45:00'),
(5, 'Carlos Rodrigues', 'carlos.rodrigues@email.com', '(11) 95555-7890', '2025-10-06 14:20:00'),
(6, 'Aero Oliveira', 'peassdro.oliveira@email.com', '(11) 91777-9012', '2025-10-06 11:30:00'),
(7, 'Marcos Filho Diniz', 'mdiniz.esquema@email.com', '(11) 91457-0313', '2025-10-06 11:30:00'),
(8, 'Bruno Moreira Feliz', 'brunofeliz.ema@email.com', '(11) 97527-0313', '2022-02-02 10:25:00'),
(9, 'Carlos Rodrigues Abreu', 'CRAbreu24@ig.com.br', '(11) 9 9457-1235', '2025-10-12 01:05:38'),
(10, 'Mauricio da Silva Canario', 'canario@temdetudo.com', '(13)9 1542-8845', '2025-10-11 00:19:36'),
(11, 'Esse cliente é um teste', 'teste@teste.com', '55 11 9 2222 -2211', '2025-10-12 01:26:47');

-- Ajustar a sequência de clientes para o próximo ID
SELECT setval('clientes_id_seq', (SELECT MAX(id) FROM clientes));

-- Inserir produtos
INSERT INTO produto (id, nome, preco, descricao, data_criacao, data_atualizacao) VALUES
(1, 'Arroz Integral', 8.50, 'Arroz integral tipo 1, pacote 5kg', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(2, 'Feijão Carioca', 7.80, 'Feijão carioca especial, pacote 1kg', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(3, 'Açúcar Cristal', 4.20, 'Açúcar cristal refinado, pacote 1kg', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(4, 'Café em Pó', 12.90, 'Café torrado e moído, pacote 500g', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(5, 'Leite UHT', 4.50, 'Leite integral UHT, caixa 1L', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(6, 'Óleo de Soja', 7.90, 'Óleo de soja refinado, garrafa 900ml', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(7, 'Farinha de Trigo', 3.90, 'Farinha de trigo especial, pacote 1kg', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(8, 'Macarrão Espaguete', 3.50, 'Macarrão espaguete, pacote 500g', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(9, 'Molho de Tomate', 2.90, 'Molho de tomate tradicional, sachê 340g', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(10, 'Sal Refinado', 2.50, 'Sal refinado iodado, pacote 1kg', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(11, 'Detergente Líquido', 1.90, 'Detergente líquido neutro, frasco 500ml', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(12, 'Sabão em Pó', 12.50, 'Sabão em pó para roupas, pacote 1kg', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(13, 'Amaciante', 8.90, 'Amaciante de roupas, frasco 2L', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(14, 'Água Sanitária', 3.50, 'Água sanitária, frasco 1L', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(15, 'Desinfetante', 6.90, 'Desinfetante pinho, frasco 1L', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(16, 'Esponja de Aço', 2.20, 'Esponja de aço para limpeza, pacote com 5 unidades', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(17, 'Papel Toalha', 5.90, 'Papel toalha, pacote com 2 rolos', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(18, 'Sabonete Líquido', 4.80, 'Sabonete líquido para mãos, frasco 200ml', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(19, 'Panela de Pressão', 89.90, 'Panela de pressão 4,5L, alumínio', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(20, 'Jogo de Panelas', 129.90, 'Jogo com 3 panelas antiaderentes', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(21, 'Conjunto de Talheres', 45.50, 'Conjunto de talheres inox, 24 peças', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(22, 'Toalha de Banho', 29.90, 'Toalha de banho felpuda, tamanho banho', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(23, 'Jogo de Copos', 35.80, 'Jogo de copos americanos, 6 unidades', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(24, 'Vassoura', 15.90, 'Vassoura de piaçava com cabo de madeira', '2025-10-06 09:00:00', '2025-10-06 09:00:00'),
(25, 'Smartphone Motorola MotoG44', 1220.50, 'Smartphone com armazenamento de 150G + carregador 40W e cabo usb tipoC', '2025-10-07 20:04:51', '2025-10-10 22:08:19'),
(28, 'Novo produto de teste Modificado', 85.24, 'Esse produto foi modificado para testar a modificação do produto', '2025-10-12 01:23:41', '2025-10-12 01:24:23');

-- Ajustar a sequência de produtos
SELECT setval('produto_id_seq', (SELECT MAX(id) FROM produto));

-- Inserir pedidos
INSERT INTO pedido (id, endereco, data_pedido, status, cliente_id, observacoes, data_atualizacao_status) VALUES
(1, 'Rua das Flores, 123 - Centro, São Paulo - SP', '2025-10-06 10:30:00', 'PENDENTE', 1, NULL, '2025-10-06 10:30:00'),
(2, 'Avenida Paulista, 1000 - Bela Vista, São Paulo - SP', '2025-10-06 11:00:00', 'ENVIADO', 2, NULL, '2025-10-06 11:00:00'),
(3, 'Rua Augusta, 500 - Consolação, São Paulo - SP', '2025-10-06 12:00:00', 'ENTREGUE', 3, NULL, '2025-10-06 12:00:00'),
(4, 'Alameda Santos, 200 - Jardins, São Paulo - SP', '2025-10-06 13:30:00', 'PENDENTE', 4, NULL, '2025-10-06 13:30:00'),
(5, 'Rua da Consolação, 1500 - Cerqueira César, São Paulo - SP', '2025-10-06 14:45:00', 'ENVIADO', 5, NULL, '2025-10-06 14:45:00'),
(6, 'Rua Haddock Lobo, 300 - Cerqueira César, São Paulo - SP', '2025-10-06 15:20:00', 'ENTREGUE', 1, NULL, '2025-10-06 15:20:00'),
(7, 'Avenida Rebouças, 800 - Pinheiros, São Paulo - SP', '2025-10-06 16:10:00', 'PENDENTE', 2, NULL, '2025-10-06 16:10:00'),
(8, 'Rua Oscar Freire, 900 - Jardins, São Paulo - SP', '2025-10-06 17:05:00', 'ENVIADO', 3, NULL, '2025-10-06 17:05:00'),
(22, 'Rua Oscar Freire, 900 - Jardins, São Paulo - SP', '2025-10-07 23:31:54', 'ENVIADO', 5, NULL, '2025-10-07 23:31:54'),
(25, 'Alameda Santos, 1000 - Jardins - São Paulo/SP', '2025-10-07 23:44:45', 'PROCESSANDO', 2, NULL, '2025-10-07 23:44:45'),
(26, 'Alameda Santos, 1000 - Jardins - São Paulo/SP', '2025-10-07 23:45:53', 'PROCESSANDO', 2, NULL, '2025-10-07 23:45:53'),
(27, 'Rua Oscar Freire, 900 - Jardins, São Paulo - SP', '2025-10-07 23:51:47', 'ENVIADO', 3, NULL, '2025-10-07 23:51:47'),
(28, 'Rua Oscar Freire, 900 - Jardins, São Paulo - SP', '2025-10-07 23:52:43', 'ENVIADO', 3, NULL, '2025-10-07 23:52:43'),
(29, 'Rua Oscar Freire, 900 - Jardins, São Paulo - SP', '2025-10-07 23:54:37', 'ENVIADO', 3, NULL, '2025-10-07 23:54:37'),
(30, 'Rua das Flores, 123 - Centro, São Paulo - SP', '2025-10-07 23:57:36', 'PENDENTE', 1, NULL, '2025-10-07 23:57:36'),
(31, 'Avenida Paulista, 1500 - Bela Vista - São Paulo/SP', '2025-10-08 00:37:09', 'PENDENTE', 1, NULL, '2025-10-08 00:37:09'),
(32, 'Avenida getúlio Vargas , 865 - baeta Nevesa - São Bernardo do Campo/SP', '2025-10-08 00:42:12', 'PENDENTE', 6, NULL, '2025-10-08 00:42:12'),
(33, 'Rua Marechal Deodoro  , 1465 - centro - São Bernardo do Campo/SP', '2025-10-08 00:43:38', 'PROCESSANDO', 5, NULL, '2025-10-08 00:43:38'),
(34, 'Rua Professor Licínio  , 45 - casa 2  - Santo André/SP', '2025-10-08 00:44:53', 'ENTREGUE', 3, NULL, '2025-10-08 00:44:53'),
(35, 'Rua Teste, 789 - Vila Madalena - São Paulo/SP', '2025-10-08 00:55:40', 'PROCESSANDO', 2, NULL, '2025-10-08 00:55:40'),
(37, 'Avenida Paulista, 1000 - Bela Vista, São Paulo - SP', '2025-10-08 19:04:28', 'PENDENTE', 4, NULL, '2025-10-08 19:04:28'),
(38, 'Avenida Getúlio Vargas 851 - Baeta Neves - São Bernardo do Campo ', '2025-10-08 19:40:04', 'PENDENTE', 2, NULL, '2025-10-08 19:40:04'),
(40, 'Praça Cardeal Arcoverde  154 - loja 5 Centro São Caetano do Sul SP', '2025-10-11 01:13:36', 'PENDENTE', 10, NULL, '2025-10-11 01:13:36'),
(43, 'Praça República Lituana 134 ap 2 Vila Zelina São Paulo SP', '2025-10-12 03:58:14', 'ENVIADO', 10, NULL, '2025-10-12 03:58:14'),
(44, 'Rua Teste, 789 - Vila Madalena - São Paulo/SP', '2025-10-12 04:01:33', 'PENDENTE', 10, NULL, '2025-10-12 04:01:33'),
(45, 'Rua Exemplo, 123 - Centro - São Paulo/SP', '2025-10-12 04:02:54', 'PENDENTE', 2, NULL, '2025-10-12 04:02:54'),
(46, 'Rua Oscar Freire, 900 - Jardins, São Paulo - SP', '2025-10-12 04:03:42', 'ENVIADO', 6, NULL, '2025-10-12 04:03:42'),
(47, 'Rua Nova, 456 - Centro - Rio de Janeiro/RJ', '2025-10-12 04:04:14', 'PENDENTE', 3, NULL, '2025-10-12 04:04:14'),
(49, 'Rua Ubiracica 47 fundos casa3  Boaçava  São Paulo/SP', '2025-10-12 04:27:25', 'PROCESSANDO', 11, NULL, '2025-10-12 04:27:25');

-- Ajustar a sequência de pedidos
SELECT setval('pedido_id_seq', (SELECT MAX(id) FROM pedido));

-- Inserir itens dos pedidos
INSERT INTO pedido_produtos (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES
(1, 4, 11, 1, 1.90),
(2, 6, 16, 1, 2.20),
(3, 4, 10, 1, 2.50),
(4, 3, 9, 1, 2.90),
(5, 3, 8, 1, 3.50),
(6, 25, 8, 1, 3.50),
(7, 26, 8, 1, 3.50),
(8, 32, 8, 1, 3.50),
(9, 5, 14, 1, 3.50),
(10, 3, 7, 1, 3.90),
(11, 25, 7, 1, 3.90),
(12, 26, 7, 1, 3.90),
(13, 33, 7, 1, 3.90),
(14, 1, 3, 1, 4.20),
(17, 30, 3, 1, 4.20),
(18, 31, 3, 1, 4.20),
(19, 2, 5, 1, 4.50),
(21, 35, 5, 1, 4.50),
(22, 6, 18, 1, 4.80),
(23, 6, 17, 1, 5.90),
(24, 5, 15, 1, 6.90),
(25, 1, 2, 1, 7.80),
(28, 30, 2, 1, 7.80),
(29, 31, 2, 1, 7.80),
(30, 34, 2, 1, 7.80),
(31, 2, 6, 1, 7.90),
(32, 25, 6, 1, 7.90),
(33, 26, 6, 1, 7.90),
(34, 1, 1, 1, 8.50),
(37, 30, 1, 1, 8.50),
(38, 31, 1, 1, 8.50),
(39, 5, 13, 1, 8.90),
(40, 4, 12, 1, 12.50),
(41, 32, 12, 1, 12.50),
(42, 2, 4, 1, 12.90),
(44, 33, 4, 1, 12.90),
(45, 35, 4, 1, 12.90),
(46, 8, 24, 1, 15.90),
(48, 22, 24, 1, 15.90),
(49, 8, 22, 1, 29.90),
(51, 22, 22, 1, 29.90),
(52, 8, 23, 1, 35.80),
(54, 22, 23, 1, 35.80),
(55, 27, 23, 1, 35.80),
(56, 28, 23, 1, 35.80),
(57, 29, 23, 1, 35.80),
(58, 7, 21, 1, 45.50),
(59, 7, 19, 1, 89.90),
(60, 7, 20, 1, 129.90),
(66, 37, 2, 2, 7.80),
(67, 37, 5, 3, 4.50),
(69, 38, 4, 3, 12.90),
(70, 38, 3, 1, 4.20),
(78, 40, 15, 1, 6.90),
(79, 40, 12, 1, 12.50),
(80, 40, 14, 3, 3.50),
(84, 43, 22, 4, 29.90),
(85, 44, 4, 1, 12.90),
(86, 44, 5, 1, 4.50),
(87, 45, 1, 1, 8.50),
(88, 45, 2, 1, 7.80),
(89, 45, 3, 1, 4.20),
(90, 46, 24, 1, 15.90),
(91, 46, 22, 1, 29.90),
(92, 46, 23, 1, 35.80),
(93, 47, 3, 1, 4.20),
(94, 47, 2, 1, 7.80),
(95, 47, 1, 1, 8.50),
(97, 49, 13, 4, 8.90),
(98, 49, 6, 1, 7.90);

-- Ajustar a sequência de pedido_produtos
SELECT setval('pedido_produtos_id_seq', (SELECT MAX(id) FROM pedido_produtos));

-- Inserir usuários
INSERT INTO users (id, name, password, email, role) VALUES
(3, 'flavio', '$2a$10$VLpIFpglAJDYyJqdsPVRqeNDQdMwRO/R5wn7bmy5a/PaBFoj62Zv.', 'fl@dev.com', 'USER'),
(4, 'Administrador', '$2a$10$D97mk3ONmKuJUu4wu3FssOqLa4smueusGS4kv4LfhGidFOS73Kc9e', NULL, 'ADMIN'),
(5, 'teste11', '$2a$10$oHpuhbq1mZJuGgpGntfqc.NS0CQ.ibINFExgJxrSRURj6AMp31e4m', 'segredo@segredo.com', 'ADMIN'),
(6, 'usuario_padrão', '$2a$10$p98.G0QM5BNG1tKy7KjV2.ff64b4NkjlgANCRF8k.bpE5PXaDll4S', 'usuariomail@email.com', 'USER'),
(7, 'admin', '$2a$10$TC3kUPFvtVeYaWjwa2KuYOdl/mrSjLDw4PQguPr2xguZpSdLL/bvG', 'admin@admin.com', 'ADMIN'),
(8, 'CarlosDantas', '$2a$10$4Ui4lUY95yfXn/BEfH1Qg.b4bGZTxqBTvswCt.92dXGCVjlV18/mK', 'CDantas@agricola.com', 'INFRA');

-- Ajustar a sequência de users
SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));

-- NOTA: PostgreSQL não usa hibernate_sequence quando se usa BIGSERIAL
-- Remover a inserção na tabela hibernate_sequence