INSERT INTO roles (role_id, name)
VALUES (1, 'ROLE_USER')
    ON DUPLICATE KEY UPDATE role_id = VALUES(role_id), name = VALUES(name);

INSERT INTO roles (role_id, name)
VALUES (2, 'ROLE_ADMIN')
    ON DUPLICATE KEY UPDATE role_id = VALUES(role_id), name = VALUES(name);