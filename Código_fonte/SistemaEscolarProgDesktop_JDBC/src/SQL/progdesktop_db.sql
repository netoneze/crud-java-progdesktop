DROP DATABASE IF EXISTS progdesktop;
CREATE DATABASE progdesktop;
USE progdesktop;

CREATE table aluno (
    id_aluno INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    registro_academico VARCHAR(45) NOT NULL,
    PRIMARY KEY(id_aluno)
);

CREATE table professor (
    id_professor INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    disciplina VARCHAR(45) NOT NULL,
    PRIMARY KEY(id_professor)
);

CREATE table terceiro (
    id_terceiro INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    funcao VARCHAR(45) NOT NULL,
    PRIMARY KEY(id_terceiro)
);

INSERT INTO aluno (id_aluno, nome, email, registro_academico) VALUES (1, "Matheus Silva", "matheussilva@gmail.com", "1916041");
INSERT INTO aluno (id_aluno, nome, email, registro_academico) VALUES (2, "Fábio Silva", "fabiosilva@gmail.com", "1923233");
INSERT INTO professor (id_professor, nome, email, disciplina) VALUES (1, "José Antônio", "zegatinho@gmail.com", "programação orientada a objetos");
INSERT INTO professor (id_professor, nome, email, disciplina) VALUES (2, "Fabrício Lopes", "fabrilopes@gmail.com", "programação desktop");
INSERT INTO terceiro (id_terceiro, nome, email, funcao) VALUES (1, "Maria Josefa", "josefa@gmail.com", "auxiliar de serviços gerais");
INSERT INTO terceiro (id_terceiro, nome, email, funcao) VALUES (2, "Sebastião Moraes", "bastiao@gmail.com", "auxiliar de serviços gerais");