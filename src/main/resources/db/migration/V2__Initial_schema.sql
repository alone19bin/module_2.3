
INSERT INTO users (id,name,status) values (1,'user_1','ACTIVE');
INSERT INTO users (id,name,status) values (2,'user_2','ACTIVE');
INSERT INTO users (id,name,status) values (3,'user_3','ACTIVE');
INSERT INTO users (id,name,status) values (4,'user_4','ACTIVE');



INSERT INTO files (id,name,file_path,status) values (1,'application.json','C:/JavaProjects/module_2.4/src/main/resources/uploads/', 'ACTIVE');
INSERT INTO files (id,name,file_path,status) values (2,'application.json','C:/JavaProjects/module_2.4/src/main/resources/','ACTIVE');


INSERT INTO events (id,user_id,file_id,status) values (1,1,2,'ACTIVE');
INSERT INTO events (id,user_id,file_id,status) values (2,2,2,'ACTIVE');
INSERT INTO events (id,user_id,file_id,status) values (3,1,1,'ACTIVE');
INSERT INTO events (id,user_id,file_id,status) values (4,2,1,'ACTIVE');

