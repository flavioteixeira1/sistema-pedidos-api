-- Adicionar FK para pedido -> cliente
ALTER TABLE pedido 
ADD CONSTRAINT fk_pedido_cliente 
FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE SET NULL;

-- Adicionar FKs para pedido_produtos
ALTER TABLE pedido_produtos 
ADD CONSTRAINT pedido_produtos_ibfk_1 
FOREIGN KEY (pedido_id) REFERENCES pedido(id) ON DELETE CASCADE;

ALTER TABLE pedido_produtos 
ADD CONSTRAINT pedido_produtos_ibfk_2 
FOREIGN KEY (produto_id) REFERENCES produto(id) ON DELETE CASCADE;