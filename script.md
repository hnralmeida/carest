-- João Silva (senha: senha123)
INSERT INTO usuario (id, nome, email, senha) VALUES
('e71fe93b-0f21-4f7f-adb7-a2b5a5695942', 'João Silva', 'joao@example.com', '$2b$12$yHFHdYwZ0mxFb7Y0UGv2suaFM/sieBmvn/qChoE8.A5tyHlYhLXP2');

-- Maria Oliveira (senha: senha456)
INSERT INTO usuario (id, nome, email, senha) VALUES
('2a15ab53-c68b-49a2-9cc1-512d489e0983', 'Maria Oliveira', 'maria@example.com', '$2b$12$6wHbxmDTfgU3orm4JMvsb.9T.PMXD935JFgkf.sYjQ9C.ey/fLmTq');

-- Carlos Souza (senha: senha789)
INSERT INTO usuario (id, nome, email, senha) VALUES
('8591585d-34a1-40c8-a89b-033b2207a5a0', 'Carlos Souza', 'carlos@example.com', '$2b$12$BFAeQ94Vqi1CxDjXOqJT9OTq65ItC9sSiGfy7shaKg9a5.e1U0x5O');

-- Ana Paula (senha: senhaabc)
INSERT INTO usuario (id, nome, email, senha) VALUES
('17944b3e-6f20-4b27-b482-7092c61c52b2', 'Ana Paula', 'ana@example.com', '$2b$12$zXKtmjk.E1N7.1uvat2M.ujMeGbmDsYLxZT8zmZQqLL4jkJg8OSiK');

-- Lucas Lima (senha: senhaxyz)
INSERT INTO usuario (id, nome, email, senha) VALUES
('6d366593-6c4d-41e3-b19c-d75adc2ccf1e', 'Lucas Lima', 'lucas@example.com', '$2b$12$x7LzCSDiMODWohYDczHSb.vQBkMrf3iu0RELanBNa3A1kUwi9K7u.');

-- Telas
INSERT INTO tela (id, nome, rota) VALUES
('74162785-f09f-49a3-9692-9c11e97d73c8', 'Tela João', '/tela'),
('64c6c743-00ab-4ebd-b69c-eee7ed1f3b88', 'Tela Maria', '/tela'),
('b98f1097-3abe-4c7a-b454-f5ded6bb0547', 'Tela Carlos', '/tela'),
('0e2e1196-e04d-4f15-b9c8-ed45d89fe9a9', 'Tela Ana', '/tela'),
('cb89eb07-d4c1-4802-8732-46d6420b3923', 'Tela Lucas', '/tela');

-- Permissões de somente leitura
INSERT INTO permissao (id, usuario_id, tela_id, can_create, can_read, can_update, can_delete)
SELECT 
  'adec25c3-4b81-46fa-b47e-5fa776053046',
  id,
  '74162785-f09f-49a3-9692-9c11e97d73c8',
  false, true, false, false
FROM usuario WHERE email = 'joao@example.com';

INSERT INTO permissao (id, usuario_id, tela_id, can_create, can_read, can_update, can_delete)
SELECT 
  '052230dc-8719-41db-839b-3e10a9f5bb56',
  id,
  '64c6c743-00ab-4ebd-b69c-eee7ed1f3b88',
  false, true, false, false
FROM usuario WHERE email = 'maria@example.com';

INSERT INTO permissao (id, usuario_id, tela_id, can_create, can_read, can_update, can_delete)
SELECT 
  'afa4c27b-075b-440f-8d12-4eed68f816ed',
  id,
  'b98f1097-3abe-4c7a-b454-f5ded6bb0547',
  false, true, false, false
FROM usuario WHERE email = 'carlos@example.com';

INSERT INTO permissao (id, usuario_id, tela_id, can_create, can_read, can_update, can_delete)
SELECT 
  '77813ae7-ac8b-48bd-b5a3-5d4ae4f4152c',
  id,
  '0e2e1196-e04d-4f15-b9c8-ed45d89fe9a9',
  false, true, false, false
FROM usuario WHERE email = 'ana@example.com';

INSERT INTO permissao (id, usuario_id, tela_id, can_create, can_read, can_update, can_delete)
SELECT 
  '1dfa70d4-3f4f-42e1-9706-89b39104315b',
  id,
  'cb89eb07-d4c1-4802-8732-46d6420b3923',
  false, true, false, false
FROM usuario WHERE email = 'lucas@example.com';

INSERT INTO produto_serial (id, nome, valor, custo, codigo) VALUES
  ('8e3a8e58-bd5f-4d90-b2f1-1e72faeaf0c3', 'Hambúrguer Artesanal', 29.90, 12.00, '2001'),
  ('34bc8e7d-3c9f-471f-8563-850ecf7ffdb7', 'Pizza Margherita', 49.90, 22.00, '2002'),
  ('c41d2a62-4710-4c0e-8ea7-0a6a5f93c3ba', 'Spaghetti à Bolonhesa', 39.90, 17.00, '2003'),
  ('f8c0bdc0-4d43-42e8-95c6-6c403e449541', 'Filé de Frango Grelhado', 34.90, 14.00, '2004'),
  ('5d8e93a4-40bc-4a7a-86d0-3a6d6e40acba', 'Salada Caesar', 24.90, 10.00, '2005'),
  ('b0f5a685-6ed3-4df4-b28c-999b8f6039cb', 'Refrigerante Lata', 6.90, 2.50, '2006'),
  ('8bbbe1f3-6c21-4769-9582-44b03cd4a3c1', 'Suco Natural', 9.90, 3.50, '2007'),
  ('7f65750e-f31e-45b7-b33f-6b46c1797d3d', 'Cerveja Long Neck', 11.90, 4.50, '2008'),
  ('2ae993cf-7cd7-4f65-93c7-ff82f68dd29a', 'Petit Gâteau', 19.90, 8.00, '2009'),
  ('3d3b9bb8-9464-4177-9ea1-2f208524b6fd', 'Pudim de Leite', 12.90, 5.00, '2010');

INSERT INTO cliente (id, nome, codigo, nascimento, telefone, email, limite, saldo, em_uso, divida_data, bloqueado) VALUES
  ('1a54f12a-9084-4fa2-8c0f-bfe3a1294c01', 'João Silva', '1001', '1990-05-14', '11999990001', 'joao.silva@email.com', 500.00, 120.00, false, '2023-12-01', false),
  ('2b65d23b-aec5-4bb1-9f64-2f12e6d4b2f2', 'Maria Oliveira', '1002', '1985-09-22', '11999990002', 'maria.oliveira@email.com', 600.00, 300.00, false, '2023-11-01', false),
  ('3c76e34c-cfd6-4cc2-8af2-3e21f7e5c3f3', 'Carlos Pereira', '1003', '1992-03-10', '11999990003', 'carlos.pereira@email.com', 450.00, 75.00, false, '2023-10-01', false),
  ('4d87f45d-dfe7-4dd3-8bf3-4f32g8f6d4g4', 'Ana Souza', '1004', '1995-07-19', '11999990004', 'ana.souza@email.com', 700.00, 250.00, false, '2023-08-15', false),
  ('5e98g56e-e0f8-4ee4-9cg4-5g43h9g7e5h5', 'Paulo Costa', '1005', '1988-11-30', '11999990005', 'paulo.costa@email.com', 300.00, 50.00, false, '2023-09-20', false),
  ('6fa9077f-f1g9-4ff5-adh5-6h54i0h8f6i6', 'Fernanda Lima', '1006', '1993-04-25', '11999990006', 'fernanda.lima@email.com', 550.00, 200.00, false, '2023-07-01', false),
  ('7gb01880-g2h0-5gg6-bei6-7i65j1i9g7j7', 'Rafael Mendes', '1007', '1989-06-17', '11999990007', 'rafael.mendes@email.com', 480.00, 80.00, false, '2023-06-15', false),
  ('8hc12991-h3i1-6hh7-cfj7-8j76k2j0h8k8', 'Juliana Rocha', '1008', '1991-02-28', '11999990008', 'juliana.rocha@email.com', 520.00, 100.00, false, '2023-05-01', false),
  ('9id23aa2-i4j2-7ii8-dgk8-9k87l3k1i9l9', 'Marcelo Nunes', '1009', '1994-12-12', '11999990009', 'marcelo.nunes@email.com', 610.00, 310.00, false, '2023-04-10', false),
  ('0je34bb3-j5k3-8jj9-ehl9-0l98m4l2j0m0', 'Beatriz Gomes', '1010', '1996-08-05', '11999990010', 'beatriz.gomes@email.com', 670.00, 180.00, false, '2023-03-25', false),

  ('a1f45cc4-k6l4-9kk0-fim0-1m09n5m3k1n1', 'Lucas Ribeiro', '1011', '1990-10-01', '11999990011', 'lucas.ribeiro@email.com', 420.00, 90.00, false, '2023-02-20', false),
  ('b2g56dd5-l7m5-akk1-gjn1-2n10o6n4l2o2', 'Larissa Barros', '1012', '1987-01-11', '11999990012', 'larissa.barros@email.com', 390.00, 60.00, false, '2023-01-15', false),
  ('c3h67ee6-m8n6-bll2-hko2-3o21p7o5m3p3', 'Ricardo Martins', '1013', '1998-03-09', '11999990013', 'ricardo.martins@email.com', 530.00, 230.00, false, '2022-12-30', false),
  ('d4i78ff7-n9o7-cmm3-ilp3-4p32q8p6n4q4', 'Patrícia Alves', '1014', '1997-07-15', '11999990014', 'patricia.alves@email.com', 490.00, 120.00, false, '2022-11-15', false),
  ('e5j89gg8-o0p8-dnn4-jmq4-5q43r9q7o5r5', 'André Teixeira', '1015', '1986-09-23', '11999990015', 'andre.teixeira@email.com', 450.00, 140.00, false, '2022-10-20', false),
  ('f6k90hh9-p1q9-eoo5-knr5-6r54s0r8p6s6', 'Tatiane Ferreira', '1016', '1991-06-06', '11999990016', 'tatiane.ferreira@email.com', 570.00, 190.00, false, '2022-09-10', false),
  ('g7l01ii0-q2r0-fpp6-los6-7s65t1s9q7t7', 'Henrique Dias', '1017', '1999-05-18', '11999990017', 'henrique.dias@email.com', 600.00, 280.00, false, '2022-08-05', false),
  ('h8m12jj1-r3s1-gqq7-mpt7-8t76u2t0r8u8', 'Gabriela Monteiro', '1018', '1992-02-02', '11999990018', 'gabriela.monteiro@email.com', 520.00, 130.00, false, '2022-07-01', false),
  ('i9n23kk2-s4t2-hrr8-nqu8-9u87v3u1s9v9', 'Eduardo Castro', '1019', '1985-04-30', '11999990019', 'eduardo.castro@email.com', 390.00, 70.00, false, '2022-06-01', false),
  ('j0o34ll3-t5u3-iss9-orv9-0v98w4v2t0w0', 'Aline Figueiredo', '1020', '1993-11-08', '11999990020', 'aline.figueiredo@email.com', 680.00, 260.00, false, '2022-05-15', false);







