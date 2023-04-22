DROP TABLE IF EXISTS student;
CREATE TABLE student (
     id          int auto_increment primary key,
     `name`       varchar(100)    null,
     lastname    varchar(100)    null,
     status      boolean         null,
     age         int             null
);

INSERT INTO student (id, `name`, lastname, status, age) VALUES (1, 'Bryan', 'Cipriano', true, 24);
INSERT INTO student (id, `name`, lastname, status, age) VALUES (2, 'Jose', 'Ruiz', false, 30);
INSERT INTO student (id, `name`, lastname, status, age) VALUES (3, 'Manuel', 'Perez', true, 20);