DROP TABLE IF EXISTS Client;

CREATE TABLE Client (
  id BIGINT AUTO_INCREMENT,
  cpf VARCHAR(15) PRIMARY KEY,
  nome VARCHAR(250) NOT NULL,
  email VARCHAR(50),
  sexo VARCHAR(1),
  data_nasc DATE NOT NULL,
  naturalidade VARCHAR(100),
  nacionalidade VARCHAR(100),
  data_cad TIMESTAMP NOT NULL,
  data_alt TIMESTAMP NOT NULL
);

INSERT INTO Client (cpf, nome, email, sexo, data_nasc, naturalidade, nacionalidade, data_cad, data_alt) VALUES
  ('08474392918', 'Testerson Da Silva Teste', 'itsg_negrao@hotmail.com', 'M', '1996-10-15', 'Campo Mourão - Paraná', 'Brasil', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
  ('27106543004', 'Testelvina Da Silva Teste', 'itsgabriela@hotmail.com', 'F', '1996-10-16', 'Campo Mourão - Paraná', 'Brasil', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());