insert into app_user (id, username, password, email, account_non_expired, account_non_locked, credentials_non_expired, enabled, last_password_change_at , password_expirate_at,created_at ) values ('80d768ef-831a-4cfe-94e6-fda1eb4452a6', 'admin', '{bcrypt}$2a$12$mv5C7qnwXDJG1D/T9KdX0.AFetwjCv/siDdmFc9hOq.gQblIJpajW', 'robertorebolledo151@gmail.com', true, true, true, true, '2023-12-17 17:22:00', '2024-12-17 17:24:00','2023-12-17 17:24:00');
insert into user_roles (roles, user_id) values (1,'80d768ef-831a-4cfe-94e6-fda1eb4452a6');
insert into user_roles (roles, user_id) values (2,'80d768ef-831a-4cfe-94e6-fda1eb4452a6');
insert into user_roles (roles, user_id) values (0,'80d768ef-831a-4cfe-94e6-fda1eb4452a6');
insert into user_data(id,profile_photo,user_id,exp) values ('80d762ef-831a-4cfe-94e6-fda1eb445564','profilephoto.png','80d768ef-831a-4cfe-94e6-fda1eb4452a6',150);


INSERT INTO app_user (id, username, password, email, account_non_expired, account_non_locked, credentials_non_expired, enabled, last_password_change_at , password_expirate_at,created_at ) VALUES ('a9a17f3e-47c3-40d9-bc23-76f2c4e39a69', 'Sarah Smith', '{bcrypt}$2a$12$FlkKfiFli7olvo0KsjIld.u9KtFwJqq7iNRhFwW9LoieS.CbK8vvy', 'sarah.smith@example.com', true, true, true, true, '2023-12-17 17:22:00', '2024-12-17 17:24:00','2023-12-17 17:24:00');
INSERT INTO user_roles (roles, user_id) VALUES (2,'a9a17f3e-47c3-40d9-bc23-76f2c4e39a69');
INSERT INTO user_data(id,profile_photo,user_id,exp) VALUES ('c4368cb2-750a-4aa9-881d-a9ec9584d9be','profilephoto.png','a9a17f3e-47c3-40d9-bc23-76f2c4e39a69',150);

INSERT INTO app_user (id, username, password, email, account_non_expired, account_non_locked, credentials_non_expired, enabled, last_password_change_at , password_expirate_at,created_at ) VALUES ('813637ec-a950-4bba-b276-4b90cd73ff3d', 'John Doe', '{bcrypt}$2a$12$Zf3d35CAraoOgLXpR2kbRe2Pxj2ECfFtCcNNe06FlAVp37GD9B10O', 'john.doe@example.com', true, true, true, true, '2023-12-17 17:22:00', '2024-12-17 17:24:00','2023-12-17 17:24:00');
INSERT INTO user_roles (roles, user_id) VALUES (2,'813637ec-a950-4bba-b276-4b90cd73ff3d');
INSERT INTO user_data(id,profile_photo,user_id,exp) VALUES ('aefb2ab8-8e59-4e7b-9221-710e122e95bc','profilephoto.png','813637ec-a950-4bba-b276-4b90cd73ff3d',150);

INSERT INTO app_user (id, username, password, email, account_non_expired, account_non_locked, credentials_non_expired, enabled, last_password_change_at , password_expirate_at,created_at ) VALUES ('8f4d7c58-9acf-46bf-871d-9dd6a3cd07d4', 'Emily Johnson', '{bcrypt}$2a$12$lbe3AT8nlg1AtUtlh5FHrOtzXwaq4.3BkC9e6VklJye5U.WUxdALS', 'emily.johnson@example.com', true, true, true, true, '2023-12-17 17:22:00', '2024-12-17 17:24:00','2023-12-17 17:24:00');
INSERT INTO user_roles (roles, user_id) VALUES (1,'8f4d7c58-9acf-46bf-871d-9dd6a3cd07d4');
INSERT INTO user_roles (roles, user_id) VALUES (2,'8f4d7c58-9acf-46bf-871d-9dd6a3cd07d4');

INSERT INTO app_user (id, username, password, email, account_non_expired, account_non_locked, credentials_non_expired, enabled, last_password_change_at , password_expirate_at,created_at ) VALUES ('4f5a4a26-d0af-4824-85cd-3c4e8c732c0f', 'Michael Brown', '{bcrypt}$2a$12$jXyOZKfF1Xj7z4VfGXK5Nua8cV4X2fgTZpWYpUXXlU9K/Cgpdkz7C', 'michael.brown@example.com', true, true, true, true, '2023-12-17 17:22:00', '2024-12-17 17:24:00','2023-12-17 17:24:00');
INSERT INTO user_roles (roles, user_id) VALUES (2,'4f5a4a26-d0af-4824-85cd-3c4e8c732c0f');

INSERT INTO app_user (id, username, password, email, account_non_expired, account_non_locked, credentials_non_expired, enabled, last_password_change_at , password_expirate_at,created_at ) VALUES ('6e12ae6e-4768-4da6-8d3a-96ab1a065ef6', 'Jennifer Smith', '{bcrypt}$2a$12$CMz8x2BnMd5qGG8JqI72GeU3DzqWc97UJUTqE6k8tMFeMV2S3o0Ky', 'jennifer.smith@example.com', true, true, true, true, '2023-12-17 17:22:00', '2024-12-17 17:24:00','2023-12-17 17:24:00');
INSERT INTO user_roles (roles, user_id) VALUES (2,'6e12ae6e-4768-4da6-8d3a-96ab1a065ef6');

INSERT INTO app_user (id, username, password, email, account_non_expired, account_non_locked, credentials_non_expired, enabled, last_password_change_at , password_expirate_at,created_at ) VALUES ('2469d363-dc89-4a6d-8ef4-1de49161cb39', 'William Johnson', '{bcrypt}$2a$12$htp8gJy31vsSUo3bLe8v1uvRso6vQJzUe.ljGzPEPt10Z1Kw/mRwu', 'william.johnson@example.com', true, true, true, true, '2023-12-17 17:22:00', '2024-12-17 17:24:00','2023-12-17 17:24:00');
INSERT INTO user_roles (roles, user_id) VALUES (2,'2469d363-dc89-4a6d-8ef4-1de49161cb39');

INSERT INTO app_user (id, username, password, email, account_non_expired, account_non_locked, credentials_non_expired, enabled, last_password_change_at , password_expirate_at,created_at ) VALUES ('ef12fde9-7cd8-4f8d-b9ab-76dcd22d4805', 'Jessica Davis', '{bcrypt}$2a$12$xSivuY2oTd.NU9vkl35kIu4W7/9EK8mNpZggDL4fL.G8tDgGkfeF2', 'jessica.davis@example.com', true, true, true, true, '2023-12-17 17:22:00', '2024-12-17 17:24:00','2023-12-17 17:24:00');
INSERT INTO user_roles (roles, user_id) VALUES (2,'ef12fde9-7cd8-4f8d-b9ab-76dcd22d4805');

INSERT INTO app_user (id, username, password, email, account_non_expired, account_non_locked, credentials_non_expired, enabled, last_password_change_at , password_expirate_at,created_at ) VALUES ('b327b58e-8882-45c5-881d-643aa9c3ed0c', 'Christopher Wilson', '{bcrypt}$2a$12$ffDbdRyRiyx0wFhItKkL9.5rlDabuV6JLCaB5Czvskf82xFuOViDa', 'christopher.wilson@example.com', true, true, true, true, '2023-12-17 17:22:00', '2024-12-17 17:24:00','2023-12-17 17:24:00');
INSERT INTO user_roles (roles, user_id) VALUES (2,'b327b58e-8882-45c5-881d-643aa9c3ed0c');

INSERT INTO app_user (id, username, password, email, account_non_expired, account_non_locked, credentials_non_expired, enabled, last_password_change_at , password_expirate_at,created_at ) VALUES ('fd39607f-5d5d-498e-b97b-d29b5ac5a883', 'Matthew Martinez', '{bcrypt}$2a$12$Tzq2aLWgxgI8qo7sggAhaO3SlGzAmJVVbKUz7qAVr3E9IXWjCVwmC', 'matthew.martinez@example.com', true, true, true, true, '2023-12-17 17:22:00', '2024-12-17 17:24:00','2023-12-17 17:24:00');
INSERT INTO user_roles (roles, user_id) VALUES (2,'fd39607f-5d5d-498e-b97b-d29b5ac5a883');

INSERT INTO app_user (id, username, password, email, account_non_expired, account_non_locked, credentials_non_expired, enabled, last_password_change_at , password_expirate_at,created_at ) VALUES ('9bf92e4d-fa7a-4b49-9af3-35bc2efc3a47', 'Amanda Jones', '{bcrypt}$2a$12$mlSnYhRGe9Pwyb/YFe/4dOwOn78KvTZSCt7gV7uvxUz2s12/hHe.2', 'amanda.jones@example.com', true, true, true, true, '2023-12-17 17:22:00', '2024-12-17 17:24:00','2023-12-17 17:24:00');
INSERT INTO user_roles (roles, user_id) VALUES (2,'9bf92e4d-fa7a-4b49-9af3-35bc2efc3a47');

INSERT INTO app_user (id, username, password, email, account_non_expired, account_non_locked, credentials_non_expired, enabled, last_password_change_at , password_expirate_at,created_at ) VALUES ('d0a02a06-ecfd-45df-9277-064594db15ac', 'Daniel Taylor', '{bcrypt}$2a$12$lCGd6z2fBFs.G36zZq0uQ.8XfY4kjUk7o4UaFAbdWncpziBPkq3s6', 'daniel.taylor@example.com', true, true, true, true, '2023-12-17 17:22:00', '2024-12-17 17:24:00','2023-12-17 17:24:00');
INSERT INTO user_roles (roles, user_id) VALUES (2,'d0a02a06-ecfd-45df-9277-064594db15ac');

INSERT INTO app_user (id, username, password, email, account_non_expired, account_non_locked, credentials_non_expired, enabled, last_password_change_at , password_expirate_at,created_at ) VALUES ('b4073491-830e-4689-8e22-83e30fca7993', 'Ashley Garcia', '{bcrypt}$2a$12$SDWeUJrMhwWzUe.WHU4pDuyumQRnuz7jEEOl7kDtx2qZoSh8LPh5G', 'ashley.garcia@example.com', true, true, true, true, '2023-12-17 17:22:00', '2024-12-17 17:24:00','2023-12-17 17:24:00');
INSERT INTO user_roles (roles, user_id) VALUES (2,'b4073491-830e-4689-8e22-83e30fca7993');

INSERT INTO app_user (id, username, password, email, account_non_expired, account_non_locked, credentials_non_expired, enabled, last_password_change_at , password_expirate_at,created_at ) VALUES ('971f77ef-1858-418d-bdde-7ff6a0451cf3', 'Emily Brown', '{bcrypt}$2a$12$fUUB1rAJWdW2c5JrXHDQX.ymG.7SuyizFk2GtKW51WRmUMNBnBZFW', 'emily.brown@example.com', true, true, true, true, '2023-12-17 17:22:00', '2024-12-17 17:24:00','2023-12-17 17:24:00');
INSERT INTO user_roles (roles, user_id) VALUES (2,'971f77ef-1858-418d-bdde-7ff6a0451cf3');

INSERT INTO app_user (id, username, password, email, account_non_expired, account_non_locked, credentials_non_expired, enabled, last_password_change_at , password_expirate_at,created_at ) VALUES ('a826fde0-d2a5-4e63-94a2-5d187dd083e1', 'James Wilson', '{bcrypt}$2a$12$Wo03wl9gJ6W.C4sKb9aFneuoEIdHZH1w39c.rIISJjI40cLeORWYy', 'james.wilson@example.com', true, true, true, true, '2023-12-17 17:22:00', '2024-12-17 17:24:00','2023-12-17 17:24:00');
INSERT INTO user_roles (roles, user_id) VALUES (2,'a826fde0-d2a5-4e63-94a2-5d187dd083e1');


insert into user_data(id,profile_photo,user_id,exp) values ('ca27a9b7-c3f4-4703-bd36-d332b0ca006a','profilephoto.png','8f4d7c58-9acf-46bf-871d-9dd6a3cd07d4',150);


insert into level_table(id,level_nevel,from_exp,to_exp) values ('80d768ef-6543-4cfe-9410-fda1eb4452a6',1,0,99);
insert into level_table(id,level_nevel,from_exp,to_exp) values ('80d768ef-6543-4cfe-9411-fda1eb4452a6',2,100,299);
insert into level_table(id,level_nevel,from_exp,to_exp) values ('80d768ef-6543-4cfe-9412-fda1eb4452a6',3,300,499);
insert into level_table(id,level_nevel,from_exp,to_exp) values ('80d768ef-6543-4cfe-9413-fda1eb4452a6',4,500,999);
insert into level_table(id,level_nevel,from_exp,to_exp) values ('80d768ef-6543-4cfe-9414-fda1eb4452a6',5,1000,1499);
insert into level_table(id,level_nevel,from_exp,to_exp) values ('80d768ef-6543-4cfe-9415-fda1eb4452a6',6,1500,1999);
insert into level_table(id,level_nevel,from_exp,to_exp) values ('80d768ef-6543-4cfe-9416-fda1eb4452a6',7,2000,2999);
insert into level_table(id,level_nevel,from_exp,to_exp) values ('80d768ef-6543-4cfe-9417-fda1eb4452a6',8,3000,3999);
insert into level_table(id,level_nevel,from_exp,to_exp) values ('80d768ef-6543-4cfe-9418-fda1eb4452a6',9,4000,7999);
insert into level_table(id,level_nevel,from_exp,to_exp) values ('80d768ef-6543-4cfe-9419-fda1eb4452a6',10,8000,10000);


--Gallipato 80d768ef-831a-4cfe-94e6-fda1eb445564
insert into specie(danger,type,id,scientific_name,media) values ('VU','Amphibian','80d768ef-831a-4cfe-94e6-fda1eb445564','pleurodelest walts','gallipato.png');
--American Eagle 80d768ef-831a-4cfe-94e6-fda1eb444464
insert into specie(danger,type,id,scientific_name,media) values ('EW','Bird','80d768ef-831a-4cfe-94e6-fda1eb444464','american Eagle','american_eagle.jpg');
--Gineta 80d768ef-831a-4cfe-9426-fda1eb456464
insert into specie(danger,type,id,scientific_name,media) values ('EW','Mammal','80d768ef-831a-4cfe-9426-fda1eb456464','gineta','gineta.png');
--Araña Lobo 80d768ef-831a-4cfe-9426-fda1eb490464
insert into specie(danger,type,id,scientific_name,media) values ('LC','Arachnid','80d768ef-831a-4cfe-9426-fda1eb490464','araña Lobo','araniaa_lobo.jpg');
--Nautilus bc19003c-b896-459b-a45a-a67decb5f89c
insert into specie(danger,type,id,scientific_name,media) values ('EW','Fish','bc19003c-b896-459b-a45a-a67decb5f89c','nautilus','nautilus.jpg');
--Triturus marmoratus 5a802b11-6d39-4b2d-b7fe-21fbec3e7f4c
insert into specie(danger,type,id,scientific_name,media) values ('LC','Amphibian','5a802b11-6d39-4b2d-b7fe-21fbec3e7f4c','triturus marmoratus','t_jaspeado.JPG');
--Dendroaspis polylepis 6ae67db8-88c3-4313-a300-0da47b218758
insert into specie(danger,type,id,scientific_name,media) values ('LC','Snake','6ae67db8-88c3-4313-a300-0da47b218758','dendroaspis polylepis','dendroaspis_polylepis.jpeg');
--Lynx pardinus dfa69695-6202-4c7f-b748-dfef86a7c627
insert into specie(danger,type,id,scientific_name,media) values ('EN','Mammal','dfa69695-6202-4c7f-b748-dfef86a7c627','lynx pardinus','lince.jpg');
--Lucanus cervus cd905a7f-db86-41d6-bd2c-b0b4b508e2ae
insert into specie(danger,type,id,scientific_name,media) values ('NT','Insect','cd905a7f-db86-41d6-bd2c-b0b4b508e2ae','lucanus cervus','ciervo-volante.jpg');
--Phelusma Quadriocellata dd87d249-6946-4628-921e-bf1277fcb100
insert into specie(danger,type,id,scientific_name,media) values ('LC','Lizzard','dd87d249-6946-4628-921e-bf1277fcb100','phelusma Quadriocellata','phelsuma_quadriocellata.jpg');
-- Tortuga Verde
INSERT INTO specie(danger, type, id, scientific_name, media) VALUES ('EN', 'Reptile', '2d4e8f36-af53-4d29-8f13-b41af1cb77d9', 'Chelonia mydas', 'tortuga_verde.jpg');

-- Elefante Africano
INSERT INTO specie(danger, type, id, scientific_name, media) VALUES ('VU', 'Mammal', '73d46542-3969-48cd-bff4-396180a9de20', 'Loxodonta africana', 'elefante_africano.jpg');

-- Orangután de Borneo
INSERT INTO specie(danger, type, id, scientific_name, media) VALUES ('CR', 'Primate', 'fa789de5-843f-4e0c-a6f1-f5b5e1742796', 'Pongo pygmaeus', 'orangutan_borneo.jpg');

-- Vaquita Marina
INSERT INTO specie(danger, type, id, scientific_name, media) VALUES ('CR', 'Mammal', '74b52ac6-5318-4f1a-9f97-bdb8ad5ddc71', 'Phocoena sinus', 'vaquita_marina.jpg');

-- Rinoceronte Blanco
INSERT INTO specie(danger, type, id, scientific_name, media) VALUES ('NT', 'Mammal', '29fd7a6d-5e10-4b7d-b8fc-f29d92db59c8', 'Ceratotherium simum', 'rinoceronte_blanco.jpg');
-- Oso Polar
INSERT INTO specie(danger, type, id, scientific_name, media) VALUES ('VU', 'Mammal', '0a35d16e-efce-4b2e-9be4-133c11e0a74d', 'Ursus maritimus', 'oso_polar.jpg');

-- Gorila de Montaña
INSERT INTO specie(danger, type, id, scientific_name, media) VALUES ('CR', 'Primate', '1d77d43d-7b9d-432b-a584-bf21e33db4dc', 'Gorilla beringei beringei', 'gorila_montana.jpg');

-- Tigre de Bengala
INSERT INTO specie(danger, type, id, scientific_name, media) VALUES ('EN', 'Mammal', 'd4a2fb6a-b9fb-429a-b6da-0a3f1a4169f2', 'Panthera tigris tigris', 'tigre_bengala.jpg');

-- Leopardo de las Nieves
INSERT INTO specie(danger, type, id, scientific_name, media) VALUES ('VU', 'Mammal', '4b6edf94-5362-4c58-a32b-1f2d37810b76', 'Panthera uncia', 'leopardo_nieves.jpg');

-- Orca
INSERT INTO specie(danger, type, id, scientific_name, media) VALUES ('VU', 'Mammal', '9ee43f05-1b7f-4691-b3a9-022425304c77', 'Orcinus orca', 'orca.jpg');
-- Panda Gigante
INSERT INTO specie(danger, type, id, scientific_name, media) VALUES ('EN', 'Mammal', '7ec97dd9-3f14-4c71-a28c-6d4175263e05', 'Ailuropoda melanoleuca', 'panda_gigante.jpg');

-- Pangolín
INSERT INTO specie(danger, type, id, scientific_name, media) VALUES ('CR', 'Mammal', 'c35f63d4-82a2-4bb9-8314-5932d5cf8a77', 'Manis spp.', 'pangolin.jpg');

-- Tortuga Laúd
INSERT INTO specie(danger, type, id, scientific_name, media) VALUES ('CR', 'Reptile', 'a3d7b31e-9d49-4676-9b21-4b5c927d3ecf', 'Dermochelys coriacea', 'tortuga_laud.jpg');

-- Delfín del Río Irrawaddy
INSERT INTO specie(danger, type, id, scientific_name, media) VALUES ('CR', 'Mammal', 'b0137249-4a3f-4d44-9e8d-9e7c303d9c36', 'Orcaella brevirostris', 'delfin_irrawaddy.jpg');

-- Orangután de Sumatra
INSERT INTO specie(danger, type, id, scientific_name, media) VALUES ('CR', 'Primate', 'bfa3b7ec-01b5-4387-b1f7-59d06a64ef51', 'Pongo abelii', 'orangutan_sumatra.jpg');
-- Tigre Siberiano
INSERT INTO specie(danger, type, id, scientific_name, media) VALUES ('EN', 'Mammal', 'beb2d4c4-b833-4b32-bae3-5f6c4db0e242', 'Panthera tigris altaica', 'tigre_siberiano.jpg');

-- Elefante Asiático
INSERT INTO specie(danger, type, id, scientific_name, media) VALUES ('EN', 'Mammal', '9cf9de37-5120-44f2-8cf2-7310c2b5e0b2', 'Elephas maximus', 'elefante_asiatico.jpg');

-- Rinoceronte Negro
INSERT INTO specie(danger, type, id, scientific_name, media) VALUES ('CR', 'Mammal', '4d30b8c7-98b0-4131-8195-c63b0a4cf34f', 'Diceros bicornis', 'rinoceronte_negro.jpg');

-- Bonobo
INSERT INTO specie(danger, type, id, scientific_name, media) VALUES ('EN', 'Primate', 'c26b0647-24e4-4f3e-a3d2-4d356a7f83d4', 'Pan paniscus', 'bonobo.jpg');

-- Oso de anteojos
INSERT INTO specie(danger, type, id, scientific_name, media) VALUES ('VU', 'Mammal', 'a57b2352-3ec2-4ba0-92b1-2b26c2404468', 'Tremarctos ornatus', 'oso_anteojos.jpg');




insert into encounter(id,date,specie_id,user_data_id,location,description,likes,medias) values ('80d768ef-871a-4cfe-9426-fda1eb490464','12-12-2012','80d768ef-831a-4cfe-94e6-fda1eb445564','80d762ef-831a-4cfe-94e6-fda1eb445564','37.5244947,-6.121443','Hoy, en el bosque de Roble Viejo, me encontré con un gallipato fascinante (Pleurodeles waltl), notable por su vibrante coloración naranja y negro en su piel moteada.',50,ARRAY['gallipatoen1.JPG','gallipatoen2.JPG','gallipatoen3.jpg','gallipatoen4.jpg','gallipatoen5.JPG','gallipatoen6.jpg','gallipatoen7.jpg']);
insert into encounter(id,date,specie_id,user_data_id,location,description,likes,medias) values ('7369296a-0f0f-4feb-a7be-79b07e164e90','12-12-2012','80d768ef-831a-4cfe-9426-fda1eb490464','80d762ef-831a-4cfe-94e6-fda1eb445564','37.5144947,-6.141443','En el bosque oscuro, me encontré con una araña lobo, su aspecto imponente y sus ojos brillantes me dejaron sin aliento. Observé cautelosamente su elegante movimiento antes de desaparecer en la noche, dejando una impresión duradera de su majestuosidad en mi mente.',50,ARRAY['profilephoto.png']);
insert into encounter(id,date,specie_id,user_data_id,location,description,likes,medias) values ('9cbd9641-0274-4c96-983b-fd04e45b23b1','12-12-2012','bc19003c-b896-459b-a45a-a67decb5f89c','80d762ef-831a-4cfe-94e6-fda1eb445564','37.5374947,-6.111443','Durante mi encuentro con un nautilo, quedé maravillado por su belleza y elegancia mientras nadaba en las profundidades del océano. Sus movimientos fluidos y su caparazón único me dejaron sin aliento mientras observaba su gracia natural en su hábitat submarino. Fue una experiencia inolvidable que me hizo apreciar aún más la asombrosa diversidad de la vida marina.',50,ARRAY['profilephoto.png']);
insert into encounter(id,date,specie_id,user_data_id,location,description,likes,medias) values ('6eb7fc11-ec0a-4afe-84ab-a5d573e0f362','12-12-2012','5a802b11-6d39-4b2d-b7fe-21fbec3e7f4c','80d762ef-831a-4cfe-94e6-fda1eb445564','37.5195947,-6.131443','Durante mi encuentro con un tritón jaspeado, quedé fascinado por su colorido y su singularidad mientras exploraba su entorno acuático. Sus patrones moteados y su elegante nado me dejaron maravillado mientras lo observaba deslizarse entre las plantas acuáticas. Fue una experiencia única que me permitió apreciar la belleza de la vida en los humedales y la importancia de conservar estos delicados ecosistemas.',50,ARRAY['profilephoto.png']);


insert into article(id,approved,specie_id, user_data_id,title,text,type,medias) values ('80d768ef-871a-4cfe-9436-fda1eb490496',true,'80d768ef-831a-4cfe-94e6-fda1eb445564','80d762ef-831a-4cfe-94e6-fda1eb445564','Habitat y Comportamiento','El gallipato se encuentra principalmente en charcas y ríos de la península ibérica. Es conocido por su capacidad de adaptarse a diferentes entornos acuáticos y por su comportamiento nocturno, saliendo a cazar insectos y pequeños crustáceos durante la noche.',0,ARRAY['gallipato1.jpg','gallipato2.jpg','gallipato3.jpg']);
insert into article(id,approved,specie_id, user_data_id,title,text,type,medias) values ('80d768ef-871a-4cfe-9436-fda1eb490497',true,'80d768ef-831a-4cfe-94e6-fda1eb445564','80d762ef-831a-4cfe-94e6-fda1eb445564','Alimentación del Gallipato','La dieta del gallipato incluye una variedad de invertebrados acuáticos, como insectos, larvas, y pequeños crustáceos. A veces, también consume otros anfibios y peces pequeños. Su alimentación es crucial para el control de la población de plagas en su hábitat natural.',0,ARRAY['gallipato1.jpg','gallipato2.jpg']);
insert into article(id,approved,specie_id, user_data_id,title,text,type,medias) values ('80d768ef-871a-4cfe-9436-fda1eb490498',true,'80d768ef-831a-4cfe-94e6-fda1eb445564','80d762ef-831a-4cfe-94e6-fda1eb445564','Conservación y Amenazas','El gallipato está amenazado por la pérdida de su hábitat debido a la urbanización y la contaminación de las aguas. Los esfuerzos de conservación se centran en la protección de sus hábitats naturales y la creación de reservas acuáticas que aseguren su supervivencia.',1,ARRAY['cgallipato1.jpg','gallipato2.jpg']);
insert into article(id,approved,specie_id, user_data_id,title,text,type,medias) values ('80d768ef-871a-4cfe-9436-fda1eb490499',true,'80d768ef-831a-4cfe-94e6-fda1eb445564','80d762ef-831a-4cfe-94e6-fda1eb445564','Reproducción del Gallipato','La reproducción del gallipato ocurre en primavera, cuando los machos buscan a las hembras en las aguas. Después de un cortejo complejo, la hembra pone huevos en el agua, que eclosionan en larvas acuáticas. Estas larvas pasan por una metamorfosis antes de convertirse en adultos.',2,ARRAY['gallipato1.jpg','gallipato2.jpg','gallipato3.jpg']);
insert into article(id,approved,specie_id, user_data_id,title,text,type,medias) values ('80d768ef-871a-4cfe-9436-fda1eb490500',false,'80d768ef-831a-4cfe-94e6-fda1eb445564','80d762ef-831a-4cfe-94e6-fda1eb445564','Mitos y Realidades','A lo largo de la historia, el gallipato ha sido sujeto de varios mitos y leyendas. Algunos creen que tiene propiedades mágicas, mientras que otros lo ven como un mal presagio. Sin embargo, la ciencia ha desmentido muchas de estas creencias, destacando su importancia ecológica.',0,ARRAY['gallipato1.jpg','gallipato3.jpg']);
insert into article(id,approved,specie_id, user_data_id,title,text,type,medias) values ('80d768ef-871a-4cfe-9436-fda1eb490501',true,'80d768ef-831a-4cfe-94e6-fda1eb445564','80d762ef-831a-4cfe-94e6-fda1eb445564','Anatomía y Características Físicas','El gallipato tiene una anatomía única que le permite adaptarse a la vida acuática. Su piel rugosa y coloración marrón con manchas amarillas le proporcionan camuflaje. Las glándulas de su piel pueden secretar una sustancia tóxica para defenderse de los depredadores.',0,ARRAY['gallipato1.jpg','gallipato2.jpg']);
insert into article(id,approved,specie_id, user_data_id,title,text,type,medias) values ('80d768ef-871a-4cfe-9436-fda1eb490502',true,'80d768ef-831a-4cfe-94e6-fda1eb445564','80d762ef-831a-4cfe-94e6-fda1eb445564','Comportamiento Social','El gallipato es conocido por su comportamiento solitario, aunque durante la época de reproducción puede encontrarse en grupos. Es un animal territorial y, a veces, muestra comportamientos agresivos hacia otros individuos de su especie.',1,ARRAY['gallipato1.jpg','gallipato2.jpg']);
insert into article(id,approved,specie_id, user_data_id,title,text,type,medias) values ('80d768ef-871a-4cfe-9436-fda1eb490503',true,'80d768ef-831a-4cfe-94e6-fda1eb445564','80d762ef-831a-4cfe-94e6-fda1eb445564','Ciclo de Vida del Gallipato','El ciclo de vida del gallipato comienza con la puesta de huevos en el agua. Las larvas nacen con branquias externas y, tras varias semanas, sufren una metamorfosis que las transforma en adultos con pulmones. Los adultos pueden vivir hasta 12 años en la naturaleza.',2,ARRAY['gallipato1.jpg','gallipato2.jpg','gallipato3.jpg']);
insert into article(id,approved,specie_id, user_data_id,title,text,type,medias) values ('80d768ef-871a-4cfe-9436-fda1eb490504',false,'80d768ef-831a-4cfe-94e6-fda1eb445564','80d762ef-831a-4cfe-94e6-fda1eb445564','El Gallipato en la Cultura Popular','El gallipato ha sido mencionado en la literatura y el folklore de varias culturas ibéricas. A menudo se le asocia con la sabiduría y la longevidad, y aparece en cuentos y leyendas como un símbolo de resiliencia y adaptación.',0,ARRAY['gallipato1.jpg','gallipato2.jpg']);
insert into article(id,approved,specie_id, user_data_id,title,text,type,medias) values ('80d768ef-871a-4cfe-9436-fda1eb490505',true,'80d768ef-831a-4cfe-94e6-fda1eb445564','80d762ef-831a-4cfe-94e6-fda1eb445564','Importancia Ecológica del Gallipato','El gallipato juega un papel crucial en el ecosistema acuático como controlador de plagas y depredador de invertebrados. Su presencia indica un medio ambiente saludable y su ausencia puede señalar problemas ambientales como la contaminación del agua.',1,ARRAY['gallipato1.jpg','gallipato2.jpg']);
insert into article(id,approved,specie_id, user_data_id,title,text,type,medias) values ('80d768ef-871a-4cfe-9436-fda1eb490506',true,'80d768ef-831a-4cfe-94e6-fda1eb445564','80d762ef-831a-4cfe-94e6-fda1eb445564','Investigaciones Recientes','Los estudios recientes sobre el gallipato han revelado detalles sobre su capacidad de regeneración. Los investigadores están particularmente interesados en cómo estos anfibios pueden regenerar extremidades y otras partes del cuerpo, lo que podría tener implicaciones médicas para los humanos.',2,ARRAY['gallipato1.jpg','gallipato2.jpg','gallipato3.jpg']);


insert into saved_list(id,title,description) values ('80d768ef-971a-4cfe-9436-fda1eb490495','lista wapa','lista wapa molona');

insert into saved_list_species(saved_list_id,species_id) values ('80d768ef-971a-4cfe-9436-fda1eb490495','80d768ef-831a-4cfe-94e6-fda1eb445564');
insert into saved_list_species(saved_list_id,species_id) values ('80d768ef-971a-4cfe-9436-fda1eb490495','80d768ef-831a-4cfe-94e6-fda1eb444464');
insert into saved_list_species(saved_list_id,species_id) values ('80d768ef-971a-4cfe-9436-fda1eb490495','80d768ef-831a-4cfe-9426-fda1eb456464');
insert into saved_list_species(saved_list_id,species_id) values ('80d768ef-971a-4cfe-9436-fda1eb490495','80d768ef-831a-4cfe-9426-fda1eb490464');

insert into user_data_saved_lists (saved_lists_id,user_data_id) values ('80d768ef-971a-4cfe-9436-fda1eb490495','80d762ef-831a-4cfe-94e6-fda1eb445564');