insert into app_user (id, username, password, email, account_non_expired, account_non_locked, credentials_non_expired, enabled, last_password_change_at , password_expirate_at ) values ('80d768ef-831a-4cfe-94e6-fda1eb4452a6', 'krobert151', '{bcrypt}$2a$12$DqkAg6PV3vOSBt63BfsUWupnsAGh/SESJfNGKOGaGneZAiqcGCaDW', 'robertorebolledo151@gmail.com', true, true, true, true, '2023-12-17 17:22:00', '2024-12-17 17:24:00');
insert into user_roles (roles, user_id) values (2,'80d768ef-831a-4cfe-94e6-fda1eb4452a6');

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
insert into specie(danger,type,id,scientific_name,media) values (2,'Amphibian','80d768ef-831a-4cfe-94e6-fda1eb445564','Pleurodelest walts','gallipato.png');
--American Eagle 80d768ef-831a-4cfe-94e6-fda1eb444464
insert into specie(danger,type,id,scientific_name,media) values (5,'Bird','80d768ef-831a-4cfe-94e6-fda1eb444464','American Eagle','american_eagle.jpg');
--Gineta 80d768ef-831a-4cfe-9426-fda1eb456464
insert into specie(danger,type,id,scientific_name,media) values (5,'Mammal','80d768ef-831a-4cfe-9426-fda1eb456464','Gineta','gineta.png');
--Araña Lobo 80d768ef-831a-4cfe-9426-fda1eb490464
insert into specie(danger,type,id,scientific_name,media) values (0,'Arachnid','80d768ef-831a-4cfe-9426-fda1eb490464','Araña Lobo','araniaa_lobo.jpg');
--Nautilus bc19003c-b896-459b-a45a-a67decb5f89c
insert into specie(danger,type,id,scientific_name,media) values (5,'Fish','bc19003c-b896-459b-a45a-a67decb5f89c','Nautilus','nautilus.jpg');
--Triturus marmoratus 5a802b11-6d39-4b2d-b7fe-21fbec3e7f4c
insert into specie(danger,type,id,scientific_name,media) values (0,'Amphibian','5a802b11-6d39-4b2d-b7fe-21fbec3e7f4c','Triturus marmoratus','t_jaspeado.JPG');
--Dendroaspis polylepis 6ae67db8-88c3-4313-a300-0da47b218758
insert into specie(danger,type,id,scientific_name,media) values (0,'Snake','6ae67db8-88c3-4313-a300-0da47b218758','Dendroaspis polylepis','dendroaspis_polylepis.jpeg');
--Lynx pardinus dfa69695-6202-4c7f-b748-dfef86a7c627
insert into specie(danger,type,id,scientific_name,media) values (3,'Mammal','dfa69695-6202-4c7f-b748-dfef86a7c627','Lynx pardinus','lince.jpg');
--Lucanus cervus cd905a7f-db86-41d6-bd2c-b0b4b508e2ae
insert into specie(danger,type,id,scientific_name,media) values (1,'Insect','cd905a7f-db86-41d6-bd2c-b0b4b508e2ae','Lucanus cervus','ciervo-volante.jpg');
--Phelusma Quadriocellata dd87d249-6946-4628-921e-bf1277fcb100
insert into specie(danger,type,id,scientific_name,media) values (0,'Lizzard','dd87d249-6946-4628-921e-bf1277fcb100','Phelusma Quadriocellata','phelsuma_quadriocellata.jpg');


insert into user_data(id,profile_photo,current_location,user_id,exp) values ('80d762ef-831a-4cfe-94e6-fda1eb445564','profilephoto.png','aqui','80d768ef-831a-4cfe-94e6-fda1eb4452a6',150);


insert into encounter(id,date,specie_id,user_data_id,location,description,likes,medias) values ('80d768ef-871a-4cfe-9426-fda1eb490464','2012-12-12','80d768ef-831a-4cfe-94e6-fda1eb445564','80d762ef-831a-4cfe-94e6-fda1eb445564','37.5244947,-6.121443','Hoy, en el bosque de Roble Viejo, me encontré con un gallipato fascinante (Pleurodeles waltl), notable por su vibrante coloración naranja y negro en su piel moteada.',50,ARRAY['gallipatoen1.JPG','gallipatoen2.JPG','gallipatoen3.jpg','gallipatoen4.jpg','gallipatoen5.JPG','gallipatoen6.jpg','gallipatoen7.jpg']);
insert into encounter(id,date,specie_id,user_data_id,location,description,likes,medias) values ('7369296a-0f0f-4feb-a7be-79b07e164e90','2012-12-12','80d768ef-831a-4cfe-9426-fda1eb490464','80d762ef-831a-4cfe-94e6-fda1eb445564','37.5144947,-6.141443','En el bosque oscuro, me encontré con una araña lobo, su aspecto imponente y sus ojos brillantes me dejaron sin aliento. Observé cautelosamente su elegante movimiento antes de desaparecer en la noche, dejando una impresión duradera de su majestuosidad en mi mente.',50,ARRAY['profilephoto.png']);
insert into encounter(id,date,specie_id,user_data_id,location,description,likes,medias) values ('9cbd9641-0274-4c96-983b-fd04e45b23b1','2012-12-12','bc19003c-b896-459b-a45a-a67decb5f89c','80d762ef-831a-4cfe-94e6-fda1eb445564','37.5374947,-6.111443','Durante mi encuentro con un nautilo, quedé maravillado por su belleza y elegancia mientras nadaba en las profundidades del océano. Sus movimientos fluidos y su caparazón único me dejaron sin aliento mientras observaba su gracia natural en su hábitat submarino. Fue una experiencia inolvidable que me hizo apreciar aún más la asombrosa diversidad de la vida marina.',50,ARRAY['profilephoto.png']);
insert into encounter(id,date,specie_id,user_data_id,location,description,likes,medias) values ('6eb7fc11-ec0a-4afe-84ab-a5d573e0f362','2012-12-12','5a802b11-6d39-4b2d-b7fe-21fbec3e7f4c','80d762ef-831a-4cfe-94e6-fda1eb445564','37.5195947,-6.131443','Durante mi encuentro con un tritón jaspeado, quedé fascinado por su colorido y su singularidad mientras exploraba su entorno acuático. Sus patrones moteados y su elegante nado me dejaron maravillado mientras lo observaba deslizarse entre las plantas acuáticas. Fue una experiencia única que me permitió apreciar la belleza de la vida en los humedales y la importancia de conservar estos delicados ecosistemas.',50,ARRAY['profilephoto.png']);


insert into article(id,approved,specie_, user_data_id,title,text,type,medias) values ('80d768ef-871a-4cfe-9436-fda1eb490495',true,'80d768ef-831a-4cfe-94e6-fda1eb445564','80d762ef-831a-4cfe-94e6-fda1eb445564','Titulo wapo','texto wapo',0,ARRAY['profilephoto.png']);
insert into article(id,approved,specie_, user_data_id,title,text,type,medias) values ('80d768ef-871a-4cfe-9436-fda1eb490492',true,'80d768ef-831a-4cfe-94e6-fda1eb445564','80d762ef-831a-4cfe-94e6-fda1eb445564','Titulo wapo2','texto wapo2',1,ARRAY['profilephoto.png']);
insert into article(id,approved,specie_, user_data_id,title,text,type,medias) values ('80d768ef-871a-4cfe-9436-fda1eb490292',true,'80d768ef-831a-4cfe-94e6-fda1eb445564','80d762ef-831a-4cfe-94e6-fda1eb445564','Titulo wapo3','texto wapo2',2,ARRAY['profilephoto.png']);
insert into article(id,approved,specie_, user_data_id,title,text,type,medias) values ('80d768ef-871a-4cfe-9436-fda1eb490242',false,'80d768ef-831a-4cfe-94e6-fda1eb445564','80d762ef-831a-4cfe-94e6-fda1eb445564','Titulo waponanai','texto wapo2',0,ARRAY['profilephoto.png']);



insert into saved_list(id,title,description) values ('80d768ef-971a-4cfe-9436-fda1eb490495','lista wapa','lista wapa molona');

insert into saved_list_species(saved_list_id,species_id) values ('80d768ef-971a-4cfe-9436-fda1eb490495','80d768ef-831a-4cfe-94e6-fda1eb445564');
insert into saved_list_species(saved_list_id,species_id) values ('80d768ef-971a-4cfe-9436-fda1eb490495','80d768ef-831a-4cfe-94e6-fda1eb444464');
insert into saved_list_species(saved_list_id,species_id) values ('80d768ef-971a-4cfe-9436-fda1eb490495','80d768ef-831a-4cfe-9426-fda1eb456464');
insert into saved_list_species(saved_list_id,species_id) values ('80d768ef-971a-4cfe-9436-fda1eb490495','80d768ef-831a-4cfe-9426-fda1eb490464');

insert into user_data_saved_lists (saved_lists_id,user_data_id) values ('80d768ef-971a-4cfe-9436-fda1eb490495','80d762ef-831a-4cfe-94e6-fda1eb445564');