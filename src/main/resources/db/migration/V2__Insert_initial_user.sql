CREATE EXTENSION IF NOT EXISTS pgcrypto;

INSERT INTO espaco_desapego_brecho.TB_USERS (login, password, name)
VALUES (
           'efraimfonseca@gmail.com',
           crypt('efraim06', gen_salt('bf', 12)),
           'Efraim Fonseca'
       )
    ON CONFLICT (login) DO NOTHING;