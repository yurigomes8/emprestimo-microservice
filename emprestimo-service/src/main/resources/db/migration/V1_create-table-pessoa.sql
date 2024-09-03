CREATE TABLE Pessoa (
                        id INT NOT NULL PRIMARY KEY,
                        nome VARCHAR(50) NOT NULL,
                        identificador VARCHAR(50) NOT NULL,
                        data_nascimento DATE NOT NULL,
                        tipo_identificador VARCHAR(50) NOT NULL,
                        valor_min_mensal DECIMAL(18, 4) NOT NULL,
                        valor_max_emprestimo DECIMAL(18, 4) NOT NULL
);