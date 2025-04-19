-- Tabela de clientes
CREATE TABLE clientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de produtos
CREATE TABLE produtos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco NUMERIC(10, 2) NOT NULL,
    estoque INT DEFAULT 0,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de pedidos
CREATE TABLE pedidos (
    id SERIAL PRIMARY KEY,
    cliente_id INT REFERENCES clientes(id) ON DELETE CASCADE,
    data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total NUMERIC(10, 2) NOT NULL
);


-- Inserindo clientes
INSERT INTO clientes (nome, email, telefone) VALUES
('Ana Souza', 'ana.souza@email.com', '11999990001'),
('Carlos Mendes', 'carlos.mendes@email.com', '11999990002'),
('Julia Lima', 'julia.lima@email.com', '11999990003'),
('Marcos Silva', 'marcos.silva@email.com', '11999990004'),
('Fernanda Rocha', 'fernanda.rocha@email.com', '11999990005'),
('Paulo Almeida', 'paulo.almeida@email.com', '11999990006'),
('Bruna Castro', 'bruna.castro@email.com', '11999990007'),
('Roberto Dias', 'roberto.dias@email.com', '11999990008'),
('Isabela Ferreira', 'isabela.ferreira@email.com', '11999990009'),
('Diego Ramos', 'diego.ramos@email.com', '11999990010');

--Update tabela clientes
ALTER TABLE public.clientes ADD atualizado_em timestamp NULL;

-- Inserindo produtos
INSERT INTO produtos (nome, descricao, preco, estoque) VALUES
('Fone Bluetooth', 'Fone sem fio com qualidade premium', 199.90, 50),
('Mouse Gamer', 'Mouse com sensor óptico de alta precisão', 149.90, 30),
('Teclado Mecânico', 'Teclado RGB com switches azuis', 299.90, 20),
('Webcam Full HD', 'Câmera para videochamadas em alta definição', 179.90, 15),
('Carregador Turbo', 'Carregador rápido USB-C', 89.90, 40),
('Hub USB 4 portas', 'Expansor de portas USB 3.0', 59.90, 60),
('Smartwatch Fit', 'Relógio inteligente com monitor cardíaco', 249.90, 25),
('Cabo HDMI 2m', 'Cabo HDMI 4K', 29.90, 100),
('Suporte de Celular', 'Suporte ajustável para mesa', 39.90, 80),
('Caixa de Som Bluetooth', 'Caixinha portátil com som estéreo', 129.90, 35);

-- Inserindo pedidos
INSERT INTO pedidos (cliente_id, data_pedido, total) VALUES
(1, NOW(), 379.80),
(2, NOW(), 89.90),
(3, NOW(), 299.90),
(4, NOW(), 149.90),
(5, NOW(), 199.90),
(6, NOW(), 59.90),
(7, NOW(), 429.80),
(8, NOW(), 29.90),
(9, NOW(), 249.90),
(10, NOW(), 179.90);
