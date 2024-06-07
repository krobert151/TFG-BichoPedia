insert into app_user (id, username, password, email, account_non_expired, account_non_locked, credentials_non_expired, enabled, last_password_change_at , password_expirate_at,created_at ) values ('80d768ef-831a-4cfe-94e6-fda1eb4452a6', 'admin', '{bcrypt}$2a$12$mv5C7qnwXDJG1D/T9KdX0.AFetwjCv/siDdmFc9hOq.gQblIJpajW', 'robertorebolledo151@gmail.com', true, true, true, true, '2023-12-17 17:22:00', '2024-12-17 17:24:00','2023-12-17 17:24:00');
insert into user_roles (roles, user_id) values (1,'80d768ef-831a-4cfe-94e6-fda1eb4452a6');
insert into user_roles (roles, user_id) values (2,'80d768ef-831a-4cfe-94e6-fda1eb4452a6');
insert into user_roles (roles, user_id) values (0,'80d768ef-831a-4cfe-94e6-fda1eb4452a6');
insert into user_data(id,profile_photo,user_id,exp) values ('80d762ef-831a-4cfe-94e6-fda1eb445564','profilephoto.png','80d768ef-831a-4cfe-94e6-fda1eb4452a6',150);

insert into app_user (id, username, password, email, account_non_expired, account_non_locked, credentials_non_expired, enabled, last_password_change_at , password_expirate_at,created_at ) values ('a9a17f3e-47c3-40d9-bc23-76f2c4e39a69', 'user', '{bcrypt}$2a$12$FlkKfiFli7olvo0KsjIld.u9KtFwJqq7iNRhFwW9LoieS.CbK8vvy', 'useruser@gmail.com', true, true, true, true, '2023-12-17 17:22:00', '2024-12-17 17:24:00','2023-12-17 17:24:00');
insert into user_roles (roles, user_id) values (2,'a9a17f3e-47c3-40d9-bc23-76f2c4e39a69');
insert into user_data(id,profile_photo,user_id,exp) values ('c4368cb2-750a-4aa9-881d-a9ec9584d9be','profilephoto.png','a9a17f3e-47c3-40d9-bc23-76f2c4e39a69',150);

insert into app_user (id, username, password, email, account_non_expired, account_non_locked, credentials_non_expired, enabled, last_password_change_at , password_expirate_at,created_at ) values ('813637ec-a950-4bba-b276-4b90cd73ff3d', 'user2', '{bcrypt}$2a$12$Zf3d35CAraoOgLXpR2kbRe2Pxj2ECfFtCcNNe06FlAVp37GD9B10O', 'useruser2@gmail.com', true, true, true, true, '2023-12-17 17:22:00', '2024-12-17 17:24:00','2023-12-17 17:24:00');
insert into user_roles (roles, user_id) values (2,'813637ec-a950-4bba-b276-4b90cd73ff3d');
insert into user_data(id,profile_photo,user_id,exp) values ('aefb2ab8-8e59-4e7b-9221-710e122e95bc','profilephoto.png','813637ec-a950-4bba-b276-4b90cd73ff3d',150);

insert into app_user (id, username, password, email, account_non_expired, account_non_locked, credentials_non_expired, enabled, last_password_change_at , password_expirate_at,created_at ) values ('8f4d7c58-9acf-46bf-871d-9dd6a3cd07d4', 'writer', '{bcrypt}$2a$12$lbe3AT8nlg1AtUtlh5FHrOtzXwaq4.3BkC9e6VklJye5U.WUxdALS', 'writerwriter@gmail.com', true, true, true, true, '2023-12-17 17:22:00', '2024-12-17 17:24:00','2023-12-17 17:24:00');
insert into user_roles (roles, user_id) values (1,'8f4d7c58-9acf-46bf-871d-9dd6a3cd07d4');
insert into user_roles (roles, user_id) values (2,'8f4d7c58-9acf-46bf-871d-9dd6a3cd07d4');

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




insert into encounter(id,date,specie_id,user_data_id,location,description,likes,medias) values ('80d768ef-871a-4cfe-9426-fda1eb490464','12-12-2012','80d768ef-831a-4cfe-94e6-fda1eb445564','80d762ef-831a-4cfe-94e6-fda1eb445564','37.5244947,-6.121443','Hoy, en el bosque de Roble Viejo, me encontré con un gallipato fascinante (Pleurodeles waltl), notable por su vibrante coloración naranja y negro en su piel moteada.',50,ARRAY['gallipatoen1.JPG','gallipatoen2.JPG','gallipatoen3.jpg','gallipatoen4.jpg','gallipatoen5.JPG','gallipatoen6.jpg','gallipatoen7.jpg']);
insert into encounter(id,date,specie_id,user_data_id,location,description,likes,medias) values ('7369296a-0f0f-4feb-a7be-79b07e164e90','12-12-2012','80d768ef-831a-4cfe-9426-fda1eb490464','80d762ef-831a-4cfe-94e6-fda1eb445564','37.5144947,-6.141443','En el bosque oscuro, me encontré con una araña lobo, su aspecto imponente y sus ojos brillantes me dejaron sin aliento. Observé cautelosamente su elegante movimiento antes de desaparecer en la noche, dejando una impresión duradera de su majestuosidad en mi mente.',50,ARRAY['profilephoto.png']);
insert into encounter(id,date,specie_id,user_data_id,location,description,likes,medias) values ('9cbd9641-0274-4c96-983b-fd04e45b23b1','12-12-2012','bc19003c-b896-459b-a45a-a67decb5f89c','80d762ef-831a-4cfe-94e6-fda1eb445564','37.5374947,-6.111443','Durante mi encuentro con un nautilo, quedé maravillado por su belleza y elegancia mientras nadaba en las profundidades del océano. Sus movimientos fluidos y su caparazón único me dejaron sin aliento mientras observaba su gracia natural en su hábitat submarino. Fue una experiencia inolvidable que me hizo apreciar aún más la asombrosa diversidad de la vida marina.',50,ARRAY['profilephoto.png']);
insert into encounter(id,date,specie_id,user_data_id,location,description,likes,medias) values ('6eb7fc11-ec0a-4afe-84ab-a5d573e0f362','12-12-2012','5a802b11-6d39-4b2d-b7fe-21fbec3e7f4c','80d762ef-831a-4cfe-94e6-fda1eb445564','37.5195947,-6.131443','Durante mi encuentro con un tritón jaspeado, quedé fascinado por su colorido y su singularidad mientras exploraba su entorno acuático. Sus patrones moteados y su elegante nado me dejaron maravillado mientras lo observaba deslizarse entre las plantas acuáticas. Fue una experiencia única que me permitió apreciar la belleza de la vida en los humedales y la importancia de conservar estos delicados ecosistemas.',50,ARRAY['profilephoto.png']);


insert into article(id,approved,specie_id, user_data_id,title,text,type,medias) values ('80d768ef-871a-4cfe-9436-fda1eb490495',true,'80d768ef-831a-4cfe-94e6-fda1eb445564','80d762ef-831a-4cfe-94e6-fda1eb445564','Description','El gallipato o ofegabous (Pleurodeles waltl)​ es el mayor anfibio urodelo de Europa, un tritón que puede llegar a alcanzar los 30 cm de longitud, de los que aproximadamente la mitad corresponden a la cola. Se encuadra en la familia Salamandridae, la misma que la salamandra común y la mayoría de los urodelos europeos.',0,ARRAY['gallipatoen1.JPG','gallipatoen2.JPG','gallipatoen3.jpg','gallipatoen4.jpg','gallipatoen5.JPG','gallipatoen6.jpg','gallipatoen7.jpg']);
insert into article(id,approved,specie_id, user_data_id,title,text,type,medias) values ('80d768ef-871a-4cfe-9436-fda1eb490492',true,'80d768ef-831a-4cfe-94e6-fda1eb445564','80d762ef-831a-4cfe-94e6-fda1eb445564','Titulo wapo2','texto wapo2',1,ARRAY['profilephoto.png']);
insert into article(id,approved,specie_id, user_data_id,title,text,type,medias) values ('80d768ef-871a-4cfe-9436-fda1eb490292',true,'80d768ef-831a-4cfe-94e6-fda1eb445564','80d762ef-831a-4cfe-94e6-fda1eb445564','Titulo wapo3','texto wapo2',2,ARRAY['profilephoto.png']);
insert into article(id,approved,specie_id, user_data_id,title,text,type,medias) values ('80d768ef-871a-4cfe-9436-fda1eb490242',false,'80d768ef-831a-4cfe-94e6-fda1eb445564','80d762ef-831a-4cfe-94e6-fda1eb445564','Titulo waponanai','texto wapo2',0,ARRAY['profilephoto.png']);



insert into saved_list(id,title,description) values ('80d768ef-971a-4cfe-9436-fda1eb490495','lista wapa','lista wapa molona');

insert into saved_list_species(saved_list_id,species_id) values ('80d768ef-971a-4cfe-9436-fda1eb490495','80d768ef-831a-4cfe-94e6-fda1eb445564');
insert into saved_list_species(saved_list_id,species_id) values ('80d768ef-971a-4cfe-9436-fda1eb490495','80d768ef-831a-4cfe-94e6-fda1eb444464');
insert into saved_list_species(saved_list_id,species_id) values ('80d768ef-971a-4cfe-9436-fda1eb490495','80d768ef-831a-4cfe-9426-fda1eb456464');
insert into saved_list_species(saved_list_id,species_id) values ('80d768ef-971a-4cfe-9436-fda1eb490495','80d768ef-831a-4cfe-9426-fda1eb490464');

insert into user_data_saved_lists (saved_lists_id,user_data_id) values ('80d768ef-971a-4cfe-9436-fda1eb490495','80d762ef-831a-4cfe-94e6-fda1eb445564');