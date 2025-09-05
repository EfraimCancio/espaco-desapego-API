-- V1__Create_table_TB_USERS.sql
CREATE TABLE IF NOT EXISTS espaco_desapego_brecho.TB_USERS (
    id BIGSERIAL PRIMARY KEY,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Comentários para documentação
COMMENT ON TABLE espaco_desapego_brecho.TB_USERS IS 'Tabela de usuários do sistema';
COMMENT ON COLUMN espaco_desapego_brecho.TB_USERS.id IS 'Identificador único do usuário';
COMMENT ON COLUMN espaco_desapego_brecho.TB_USERS.login IS 'Login de acesso do usuário (único)';
COMMENT ON COLUMN espaco_desapego_brecho.TB_USERS.password IS 'Senha criptografada do usuário';
COMMENT ON COLUMN espaco_desapego_brecho.TB_USERS.name IS 'Nome completo do usuário';