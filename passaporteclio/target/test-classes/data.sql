INSERT INTO user(email, perfil, senha) VALUES('admin@email.com', 'Administrador', '$2a$10$FjtQZMQuy8PhXP5AI0lEbe0f/8WnA8dI9Bg0f1jJtP0yU3c5tC06.');

INSERT INTO permission(id, perfil) VALUES(1, 'ROLE_ADMINISTRADOR');
INSERT INTO permission(id, perfil) VALUES(2, 'ROLE_VISITANTE');

INSERT INTO user_permissoes(user_id, permissoes_id) VALUES (1,1);