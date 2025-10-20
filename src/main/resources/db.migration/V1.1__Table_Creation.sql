-- Tabela de clientes
CREATE TABLE IF NOT EXISTS clientes (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    telefone VARCHAR(20),
    data_cadastro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de produtos
CREATE TABLE IF NOT EXISTS produto (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    descricao TEXT,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de pedidos
CREATE TABLE IF NOT EXISTS pedido (
    id BIGSERIAL PRIMARY KEY,
    endereco VARCHAR(255) NOT NULL,
    data_pedido TIMESTAMP,
    status VARCHAR(50) NOT NULL,
    cliente_id BIGINT,
    observacoes TEXT,
    data_atualizacao_status TIMESTAMP
);

-- Tabela de itens do pedido
CREATE TABLE IF NOT EXISTS pedido_produtos (
    id BIGSERIAL PRIMARY KEY,
    pedido_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade INTEGER NOT NULL DEFAULT 1,
    preco_unitario DECIMAL(10,2) NOT NULL,
    UNIQUE (pedido_id, produto_id)
);

-- Tabela de usuários
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    role VARCHAR(20) NOT NULL DEFAULT 'USER'
);

-- Índices para melhor performance
CREATE INDEX IF NOT EXISTS idx_produto_nome ON produto(nome);
CREATE INDEX IF NOT EXISTS idx_produto_preco ON produto(preco);
CREATE INDEX IF NOT EXISTS idx_produto_data_criacao ON produto(data_criacao);
CREATE INDEX IF NOT EXISTS idx_pedido_status ON pedido(status);
CREATE INDEX IF NOT EXISTS idx_pedido_data ON pedido(data_pedido);
CREATE INDEX IF NOT EXISTS idx_pedido_cliente_id ON pedido(cliente_id);
CREATE INDEX IF NOT EXISTS idx_pedido_produtos_pedido_id ON pedido_produtos(pedido_id);
CREATE INDEX IF NOT EXISTS idx_pedido_produtos_produto_id ON pedido_produtos(produto_id);