-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 15/06/2023 às 02:17
-- Versão do servidor: 10.4.28-MariaDB
-- Versão do PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `alchimicus`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `categoria`
--

CREATE TABLE `categoria` (
  `codCat` int(11) NOT NULL,
  `nomeCat` varchar(45) DEFAULT NULL,
  `descricaoCat` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `categoria`
--

INSERT INTO `categoria` (`codCat`, `nomeCat`, `descricaoCat`) VALUES
(1, 'Ingredientes', 'oiuy'),
(2, 'Pocao', ''),
(3, 'teste', ''),
(4, 'Ingredientes', ''),
(5, 'Ingrediente', ''),
(6, 'Batata', '');

-- --------------------------------------------------------

--
-- Estrutura para tabela `clientes`
--

CREATE TABLE `clientes` (
  `codBruxo` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `nomeCliente` varchar(50) DEFAULT NULL,
  `nascCliente` varchar(10) DEFAULT NULL,
  `enderecoCliente` varchar(60) DEFAULT NULL,
  `emailCliente` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `clientes`
--

INSERT INTO `clientes` (`codBruxo`, `nomeCliente`, `nascCliente`, `enderecoCliente`, `emailCliente`) VALUES
(0, 'teste', '23/06/1990', 'teste2', 'lpkjhg');

-- --------------------------------------------------------

--
-- Estrutura para tabela `fornecedores`
--

CREATE TABLE `fornecedores` (
  `codForn` int(11) NOT NULL,
  `cnbruxo` varchar(45) DEFAULT NULL,
  `nomeForn` varchar(45) DEFAULT NULL,
  `enderecoForn` varchar(150) NOT NULL,
  `emailForn` varchar(60) NOT NULL,
  `telefoneForn` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `fornecedores`
--

INSERT INTO `fornecedores` (`codForn`, `cnbruxo`, `nomeForn`, `enderecoForn`, `emailForn`, `telefoneForn`) VALUES
(1, '666.777.888.9-10', 'Americanas', 'Rua 1234', 'Americanas@gmail.com', '11 973706578'),
(2, '111.222.333.4-56', 'Daniel Froes', 'São Caetano do Sul, centro', 'Daniel@gmail.com', '11 978564123');

-- --------------------------------------------------------

--
-- Estrutura para tabela `funcionarios`
--

CREATE TABLE `funcionarios` (
  `codFunc` int(11) NOT NULL,
  `cargoFunc` varchar(20) DEFAULT NULL,
  `nomeFunc` varchar(40) DEFAULT NULL,
  `sobrenomeFunc` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `funcionarios`
--

INSERT INTO `funcionarios` (`codFunc`, `cargoFunc`, `nomeFunc`, `sobrenomeFunc`) VALUES
(1, 'Vendedor', 'Leonardo', 'Reis'),
(2, 'Vendedor', 'Geovanna', 'do Carmo'),
(3, 'Administrador', 'Thaiane', 'Pereira');

-- --------------------------------------------------------

--
-- Estrutura para tabela `login`
--

CREATE TABLE `login` (
  `codLogin` int(11) NOT NULL,
  `user` varchar(60) NOT NULL,
  `senha` varchar(20) NOT NULL,
  `codFunc` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `login`
--

INSERT INTO `login` (`codLogin`, `user`, `senha`, `codFunc`) VALUES
(1, 'leonardo', '12qwaszx', 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `produto`
--

CREATE TABLE `produto` (
  `codProd` int(11) NOT NULL,
  `nomeProd` varchar(45) DEFAULT NULL,
  `precoProd` int(11) DEFAULT NULL,
  `qtdEstoqueProd` int(11) DEFAULT NULL,
  `codCat` int(11) DEFAULT NULL,
  `codForn` int(11) DEFAULT NULL,
  `descricao` varchar(250) DEFAULT NULL,
  `foto` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `produto`
--

INSERT INTO `produto` (`codProd`, `nomeProd`, `precoProd`, `qtdEstoqueProd`, `codCat`, `codForn`, `descricao`, `foto`) VALUES
(2, 'Amortentia', 10, 10, 2, 2, 'É a poção do amor.Tem um aspecto brilhante como o de pérolas e tem um vapor único, por este subir em espirais.\nContrariamente ao que a maioria pensa, esta poção não causa o amor, mas sim uma leve paixão, ou em casos mais fortes, obsessão.', 'amortentia.jpg'),
(3, 'Bezoares', 2, 60, 1, 1, NULL, 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\bezoares.jpg'),
(4, 'Hidromel', 2, 15, 1, 1, NULL, 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\hidromel.jpg'),
(5, 'Mandrágoras cozidas', 2, 23, 1, 1, NULL, 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\mandagorasCozidas.jpg'),
(6, 'Folhas de menta', 2, 20, 1, 1, NULL, 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\folhasDeMenta.jpg'),
(7, 'Chifre de unicórnio móido', 2, 63, 1, 1, NULL, 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\ChifreDeUnicornioMoido.jpg'),
(8, 'Frutos de azevinho', 2, 5, 1, 1, NULL, 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\frutosDeAzevinho.jpg'),
(9, 'Essência de lavanda', 2, 15, 1, 1, NULL, 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\EssenciaDeLavanda'),
(10, 'Orvalho da lua', 2, 20, 1, 1, NULL, 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\OrvalhoDaLua.jpg'),
(11, 'Muco de verme-cego', 2, 2, 1, 1, NULL, 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\mucoDeVermeCego'),
(12, 'Esporos de vagens rosadas', 2, 9, 4, 1, NULL, 'EsporosDeVagensRosadas.jpg'),
(14, 'Antídoto a venenos incomuns', 16, 35, 2, 2, 'O Antídoto a Venenos Incomuns é uma poção que, como seu nome já diz É uma mistura simples, que é aprendida por crianças em idade escolar. Aparentemente, uma reação alérgica aos ferrões de gira-gira, um dos componentes da poção, poderá fazer a pessoa ', 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\AntidoAVenenosIncomuns.jpg'),
(15, 'Sementes de fogo', 2, 60, 1, 1, 'Batata', 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\SementesDeFogo.jpg'),
(16, 'Chifres de Arpéu pulverizados', 3, 4567, 1, 1, NULL, 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\ChifresDeArpeuPulverizados.jpg'),
(17, 'Ferrões de gira-gira', 123, 60, 1, 1, NULL, 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\FerroesDeGiraGira'),
(18, 'Carapaças de chizácaro', 2, 60, 1, 1, NULL, 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\CarapaçaDeChizacaro.jpg'),
(19, 'Arrepiar os Cabelos', 3, 93, 2, 2, 'Arrepia os cabelos mesmo', 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\PocaoArrepiarOsCabelos.jpg'),
(20, 'Rabos de rato', 10, 5, 1, 1, NULL, 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\RabosDeRatos.jpg\r\n'),
(21, 'produtos de beleza', 12345, 60, 1, 1, NULL, 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\ProdutosDeBeleza'),
(22, 'Elixir da Vida', 10, 5, 2, 2, 'Este elixir prolonga o tempo de vida de quem o bebe. Porém seu uso deve tornar-se regular para que o mesmo mantenha sua \"imortalidade\". Sua forma de preparo é desconhecida, mas sabe-se que seu principal \"ingrediente\" é a pedra filosofal (criada pelo ', 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\ElixirDaVida'),
(24, 'Pedra Filosofal', 0, 5, 1, 1, NULL, 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\PedraFilosofal'),
(25, 'Elixir para induzir euforia', 45, 15, 2, 2, 'Como o nome já diz, esse elixir serve para animar a pessoa que o toma, a deixando mais leve e feliz. Não é tão poderoso e tão complicado como o Felix Felicis. Seus efeitos colaterais podem ser excessivas cantorias e coceiras no nariz.', 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\ElixirParaInduzirEuforia.jpg'),
(26, 'Galho de menta', 10, 60, 1, 1, NULL, 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\GalhoDeMenta.jpg'),
(27, 'Orvalho colhido na sexta-feira 13', 10, 60, 1, 1, NULL, 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\OrvalhoColhidoNaSextaFeira13.jpg'),
(28, 'Ditamno', 12345, 60, 1, 1, NULL, 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\Ditamno.jpg'),
(29, 'Pó de chifre de bicórnio', 10, 5, 1, 1, NULL, 'demo(2)\\demo\\demo\\src\\main\\resources\\images\\PoDeChifreDeBicornio.jpg'),
(6532, 'kjh', 852, 864512, 1, 1, 'lçkoljikhjg', NULL),
(8451, 'lkjhg', 85612, 852, 1, 1, 'tegsbx', 'ClienteDAO.java');

-- --------------------------------------------------------

--
-- Estrutura para tabela `venda`
--

CREATE TABLE `venda` (
  `codVend` int(11) NOT NULL,
  `valorTotal` decimal(10,2) DEFAULT NULL,
  `data` datetime DEFAULT NULL,
  `codBruxo` int(11) DEFAULT NULL,
  `codFunc` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `venda`
--

INSERT INTO `venda` (`codVend`, `valorTotal`, `data`, `codBruxo`, `codFunc`) VALUES
(1, 10.00, '0000-00-00 00:00:00', 0, 1),
(2, 45.00, '0000-00-00 00:00:00', 0, 2),
(5, 10.00, '0000-00-00 00:00:00', 0, 1),
(6, 10.00, '2023-06-11 11:08:03', 0, 1),
(9, 10.00, '0000-00-00 00:00:00', 0, 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `venda_produto`
--

CREATE TABLE `venda_produto` (
  `codVend` int(11) DEFAULT NULL,
  `codProd` int(11) DEFAULT NULL,
  `qtdProduto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`codCat`);

--
-- Índices de tabela `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`codBruxo`);

--
-- Índices de tabela `fornecedores`
--
ALTER TABLE `fornecedores`
  ADD PRIMARY KEY (`codForn`);

--
-- Índices de tabela `funcionarios`
--
ALTER TABLE `funcionarios`
  ADD PRIMARY KEY (`codFunc`);

--
-- Índices de tabela `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`codLogin`),
  ADD KEY `codFunc` (`codFunc`);

--
-- Índices de tabela `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`codProd`),
  ADD KEY `codCat` (`codCat`),
  ADD KEY `codForn` (`codForn`);

--
-- Índices de tabela `venda`
--
ALTER TABLE `venda`
  ADD PRIMARY KEY (`codVend`),
  ADD KEY `codBruxo` (`codBruxo`),
  ADD KEY `codFunc` (`codFunc`);

--
-- Índices de tabela `venda_produto`
--
ALTER TABLE `venda_produto`
  ADD KEY `codVend` (`codVend`),
  ADD KEY `codProd` (`codProd`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `categoria`
--
ALTER TABLE `categoria`
  MODIFY `codCat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de tabela `fornecedores`
--
ALTER TABLE `fornecedores`
  MODIFY `codForn` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `funcionarios`
--
ALTER TABLE `funcionarios`
  MODIFY `codFunc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `login`
--
ALTER TABLE `login`
  MODIFY `codLogin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `produto`
--
ALTER TABLE `produto`
  MODIFY `codProd` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8452;

--
-- AUTO_INCREMENT de tabela `venda`
--
ALTER TABLE `venda`
  MODIFY `codVend` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `login`
--
ALTER TABLE `login`
  ADD CONSTRAINT `login_ibfk_1` FOREIGN KEY (`codFunc`) REFERENCES `funcionarios` (`codFunc`);

--
-- Restrições para tabelas `produto`
--
ALTER TABLE `produto`
  ADD CONSTRAINT `produto_ibfk_1` FOREIGN KEY (`codCat`) REFERENCES `categoria` (`codCat`),
  ADD CONSTRAINT `produto_ibfk_2` FOREIGN KEY (`codForn`) REFERENCES `fornecedores` (`codForn`);

--
-- Restrições para tabelas `venda`
--
ALTER TABLE `venda`
  ADD CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`codBruxo`) REFERENCES `clientes` (`codBruxo`),
  ADD CONSTRAINT `venda_ibfk_2` FOREIGN KEY (`codFunc`) REFERENCES `funcionarios` (`codFunc`);

--
-- Restrições para tabelas `venda_produto`
--
ALTER TABLE `venda_produto`
  ADD CONSTRAINT `venda_produto_ibfk_1` FOREIGN KEY (`codVend`) REFERENCES `venda` (`codVend`),
  ADD CONSTRAINT `venda_produto_ibfk_2` FOREIGN KEY (`codProd`) REFERENCES `produto` (`codProd`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
