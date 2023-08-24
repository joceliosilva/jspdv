-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 24/08/2023 às 22:25
-- Versão do servidor: 10.4.28-MariaDB
-- Versão do PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `pdv`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `historico_vendas`
--

CREATE TABLE `historico_vendas` (
  `id` int(11) NOT NULL,
  `data_venda` datetime DEFAULT NULL,
  `itens_vendidos` text DEFAULT NULL,
  `valor_total` decimal(10,2) DEFAULT NULL,
  `valor_pago` decimal(10,2) DEFAULT NULL,
  `troco` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `historico_vendas`
--

INSERT INTO `historico_vendas` (`id`, `data_venda`, `itens_vendidos`, `valor_total`, `valor_pago`, `troco`) VALUES
(1, '2023-08-24 14:14:08', '========== Cupom de Venda ==========\nJS SISTEMAS INFO\nData da Venda: 24/08/2023 14:14:08\n====================================\nItens Comprados:\nLUSTRA MOVEIS YPE 200ML - R$5.99\nGRAMPOS P/GRAMPEADOR BCO - R$1.99\nGRAMPOS P/GRAMPEADOR BCO - R$1.99\n====================================\nValor Total: R$9,97\nValor Pago: R$10,00\nTroco: R$0,03\n', 9.97, 10.00, 0.03),
(2, '2023-08-24 14:37:27', '========== Cupom de Venda ==========\nJS SISTEMAS INFO\nData da Venda: 24/08/2023 14:37:27\n====================================\nItens Comprados:\nGRAMPOS P/GRAMPEADOR BCO - R$1.99\nGRAMPOS P/GRAMPEADOR BCO - R$1.99\nGRAMPOS P/GRAMPEADOR BCO - R$1.99\nGRAMPOS P/GRAMPEADOR BCO - R$1.99\nGRAMPOS P/GRAMPEADOR BCO - R$1.99\nGRAMPOS P/GRAMPEADOR BCO - R$1.99\nGRAMPOS P/GRAMPEADOR BCO - R$1.99\nGRAMPOS P/GRAMPEADOR BCO - R$1.99\n====================================\nValor Total: R$15,92\nValor Pago: R$20,00\nTroco: R$4,08\n', 15.92, 20.00, 4.08),
(3, '2023-08-24 15:20:12', '========== Cupom de Venda ==========\nJS SISTEMAS INFO\nData da Venda: 24/08/2023 15:20:12\n====================================\nItens Comprados:\nGRAMPOS P/GRAMPEADOR BCO - R$1.99\nGRAMPOS P/GRAMPEADOR BCO - R$1.99\nGRAMPOS P/GRAMPEADOR BCO - R$1.99\n====================================\nValor Total: R$5,97\nValor Pago: R$20,00\nTroco: R$14,03\n', 5.97, 20.00, 14.03),
(4, '2023-08-24 16:10:18', '========== Cupom de Venda ==========\nJS SISTEMAS INFO\nData da Venda: 24/08/2023 16:10:18\n====================================\nItens Comprados:\nGRAMPOS P/GRAMPEADOR BCO - R$1.99\nGRAMPOS P/GRAMPEADOR BCO - R$1.99\n====================================\nValor Total: R$3,98\nValor Pago: R$5,00\nTroco: R$1,02\n', 3.98, 5.00, 1.02),
(5, '2023-08-24 16:38:28', '========== Cupom de Venda ==========\nJS SISTEMAS INFO\nData da Venda: 24/08/2023 16:38:28\n====================================\nItens Comprados:\nGRAMPOS P/GRAMPEADOR BCO - R$1.99\nGRAMPOS P/GRAMPEADOR BCO - R$1.99\nLUSTRA MOVEIS YPE 200ML - R$5.99\n====================================\nValor Total: R$9,97\nValor Pago: R$50,00\nTroco: R$40,03\n', 9.97, 50.00, 40.03),
(6, '2023-08-24 17:10:23', '========== Cupom de Venda ==========\nJS SISTEMAS INFO\nData da Venda: 24/08/2023 17:10:23\n====================================\nItens Comprados:\nSALGADINHO PIPOS 30G - R$6.99\nGRAMPOS P/GRAMPEADOR BCO - R$1.99\n====================================\nValor Total: R$8,98\nValor Pago: R$10,00\nTroco: R$1,02\n', 8.98, 10.00, 1.02);

-- --------------------------------------------------------

--
-- Estrutura para tabela `produtos`
--

CREATE TABLE `produtos` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `ean` varchar(20) NOT NULL,
  `preco` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `produtos`
--

INSERT INTO `produtos` (`id`, `nome`, `ean`, `preco`) VALUES
(9, 'GRAMPOS P/GRAMPEADOR BCO', '7896303620068', 1.99),
(10, 'LUSTRA MOVEIS YPE 200ML', '7896098909980', 5.99),
(11, 'SALGADINHO PIPOS 30G', '7896071', 6.99);

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `senha` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuarios`
--

INSERT INTO `usuarios` (`id`, `username`, `senha`) VALUES
(1, 'admin', 'admin123'),
(2, 'admin', 'admin123');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `historico_vendas`
--
ALTER TABLE `historico_vendas`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `historico_vendas`
--
ALTER TABLE `historico_vendas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de tabela `produtos`
--
ALTER TABLE `produtos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de tabela `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
