-- Tabela semestres
CREATE TABLE IF NOT EXISTS semestres (
    id SERIAL PRIMARY KEY,
    rotulo VARCHAR(10) UNIQUE NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL
);

-- Tabela locais
CREATE TABLE IF NOT EXISTS locais (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    sigla VARCHAR(20),
    endereco TEXT,
    cidade VARCHAR(50),
    latitude DECIMAL(9,6),
    longitude DECIMAL(9,6)
);

-- Tabela especialidades
CREATE TABLE IF NOT EXISTS especialidades (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) UNIQUE NOT NULL
);

-- Tabela usuarios (coordenador, preceptor, aluno, secretaria)
CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    papel VARCHAR(30) NOT NULL CHECK (papel IN ('COORDENADOR','PRECEPTOR','ALUNO','SECRETARIA'))
);

-- Tabela plantoes (rodízios)
CREATE TABLE IF NOT EXISTS plantoes (
    id SERIAL PRIMARY KEY,
    semestre_id INT NOT NULL REFERENCES semestres(id),
    local_id INT NOT NULL REFERENCES locais(id),
    especialidade_id INT NOT NULL REFERENCES especialidades(id),
    preceptor_id INT REFERENCES usuarios(id),
    data_hora_inicio TIMESTAMP NOT NULL,
    data_hora_fim TIMESTAMP NOT NULL,
    vagas INT NOT NULL CHECK (vagas > 0)
);

-- Tabela alocacoes
CREATE TABLE IF NOT EXISTS alocacoes (
    id SERIAL PRIMARY KEY,
    plantao_id INT NOT NULL REFERENCES plantoes(id),
    aluno_id INT NOT NULL REFERENCES usuarios(id),
    check_in TIMESTAMP,
    check_out TIMESTAMP,
    horas_reais INT,
    status VARCHAR(20) DEFAULT 'PENDENTE'
);

-- Tabela local_especialidade (vagas por turno)
CREATE TABLE IF NOT EXISTS local_especialidade (
    local_id INT NOT NULL REFERENCES locais(id),
    especialidade_id INT NOT NULL REFERENCES especialidades(id),
    vagas_por_turno INT NOT NULL DEFAULT 1,
    PRIMARY KEY (local_id, especialidade_id)
);