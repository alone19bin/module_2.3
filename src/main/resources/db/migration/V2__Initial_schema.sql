
INSERT INTO users (id,name) values (1,'user_1');
INSERT INTO users (id,name) values (2,'user_2');
INSERT INTO users (id,name) values (3,'user_3');
INSERT INTO users (id,name) values (4,'user_4');



INSERT INTO files (id,name,file_path,status) values (1,'test1','C:/JavaProjects/module_2.3/src/main/resources/test1.txt', 'ACTIVE');
INSERT INTO files (id,name,file_path,status) values (2,'test2','C:/JavaProjects/module_2.3/src/main/resources/test2.txt','ACTIVE');


INSERT INTO events (id,user_id,file_id) values (1,1,2);
INSERT INTO events (id,user_id,file_id) values (2,2,2);
INSERT INTO events (id,user_id,file_id) values (3,1,1);
INSERT INTO events (id,user_id,file_id) values (4,2,1);

