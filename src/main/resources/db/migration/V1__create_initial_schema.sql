USE forumhub;

CREATE TABLE Curso (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       nome VARCHAR(255) NOT NULL,
                       categoria VARCHAR(255) NOT NULL,
                       PRIMARY KEY (id)
);

CREATE TABLE Topico (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        titulo VARCHAR(255) NOT NULL,
                        mensagem TEXT NOT NULL,
                        dataCriacao DATETIME NOT NULL,
                        status VARCHAR(50) NOT NULL,
                        autor BIGINT NOT NULL,
                        curso BIGINT NOT NULL,
                        PRIMARY KEY (id)
);

CREATE TABLE Usuario (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         nome VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL,
                         senha VARCHAR(255) NOT NULL,
                         PRIMARY KEY (id)
);

CREATE TABLE Perfil (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        nome VARCHAR(255) NOT NULL,
                        PRIMARY KEY (id)
);

CREATE TABLE Resposta (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          mensagem TEXT NOT NULL,
                          topico BIGINT NOT NULL,
                          dataCriacao DATETIME NOT NULL,
                          autor BIGINT NOT NULL,
                          solucao BOOLEAN NOT NULL,
                          PRIMARY KEY (id)
);

CREATE TABLE Usuario_Perfil (
                                usuario_id BIGINT NOT NULL,
                                perfil_id BIGINT NOT NULL,
                                PRIMARY KEY (usuario_id, perfil_id)
);


ALTER TABLE Topico
    ADD FOREIGN KEY (autor) REFERENCES Usuario(id),
    ADD FOREIGN KEY (curso) REFERENCES Curso(id);

ALTER TABLE Resposta
    ADD FOREIGN KEY (topico) REFERENCES Topico(id),
    ADD FOREIGN KEY (autor) REFERENCES Usuario(id);

ALTER TABLE Usuario_Perfil
    ADD FOREIGN KEY (usuario_id) REFERENCES Usuario(id),
    ADD FOREIGN KEY (perfil_id) REFERENCES Perfil(id);
