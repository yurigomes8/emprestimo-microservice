CREATE TABLE Emprestimo (
                            id INT NOT NULL PRIMARY KEY,
                            id_pessoa INT NOT NULL,
                            valor_emprestimo DECIMAL(18, 4) NOT NULL,
                            numero_parcelas INT NOT NULL,
                            status_pagamento VARCHAR(50) NOT NULL,
                            data_criacao DATE NOT NULL,
                            CONSTRAINT FK_Pessoa_Emprestimo FOREIGN KEY (id_pessoa) REFERENCES Pessoa(id)
);