CREATE  database pulso_db;
use pulso_db;
CREATE USER 'PULSO_OWNER'@'pulso_db'  IDENTIFIED BY 'admin1234';
GRANT ALL PRIVILEGES ON *.* TO 'PULSO_OWNER'@'pulso_db';
FLUSH PRIVILEGES;

CREATE TABLE usuarios(
    id_usuario INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    correo VARCHAR(500) NOT NULL,
    contrasenia TEXT NOT NULL,
    rol VARCHAR(250) NOT NULL DEFAULT 'COAUCH',
    fecha_creacion TIMESTAMP DEFAULT now(),
    activo BOOLEAN DEFAULT true,
    UNIQUE idx_correo(correo)
);


INSERT INTO pulso_db.usuarios(correo, contrasenia, rol)VALUES('gmandujano60@gmail.com', '1234', 'COAUCH');
INSERT INTO pulso_db.usuarios(correo, contrasenia, rol)VALUES('alumno@gmail.com', '1234', 'ALUMNO');
select * from usuarios;


CREATE TABLE perfil_coauch(
    id_perfil_coauch INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL ,
    nombre_gym VARCHAR(500),
    fecha_creacion TIMESTAMP DEFAULT now(),
    activo BOOLEAN DEFAULT true,
    FOREIGN KEY(id_usuario)  REFERENCES usuarios
);

INSERT INTO perfil_coauch(id_usuario,nombre_gym)VALUES(1,'DEMO GYM');
SELECT * from perfil_coauch;



CREATE TABLE perfil_alumno(
    id_perfil_alumno INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL ,
    nombre VARCHAR(250) NOT NULL,
    apellido_p VARCHAR(500) NOT NULL,
    apellido_m VARCHAR (500),
    telefono VARCHAR(20),
    fecha_creacion TIMESTAMP DEFAULT now(),
    activo BOOLEAN DEFAULT true,
    FOREIGN KEY(id_usuario)  REFERENCES usuarios
);

INSERT INTO  perfil_alumno(id_usuario,nombre,apellido_p,apellido_m,telefono) VALUES(2,'GERARDO','MANDUJANO','ROLDAN','5572297492');
select * from perfil_alumno;

CREATE TABLE actividad(
    id_actividad INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    id_perfil_coauch int NOT NULL,
    nombre varchar(500) NOT NULL,
    tipo VARCHAR(250) NOT NULL,
    grupo_muscular varchar(250) NOT NULL,
    descripccion text,
    fecha_creacion TIMESTAMP DEFAULT now(),
    activo BOOLEAN DEFAULT true,
    FOREIGN KEY(id_perfil_coauch) REFERENCES perfil_coauch
);

INSERT INTO actividad(id_perfil_coauch,nombre,tipo,grupo_muscular,descripccion)
VALUES(1,'sentadilla','GIMNASIO','pierna','sentadilla con barra'),
(1,'box','CLASE','Cardio','Clase de Box 45 min')
select * from actividad;

CREATE TABLE plantilla_rutina(
    id_plantilla_rutina INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_perfil_coauch int NOT NULL,
    nombre varchar(500) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT now(),
    activo BOOLEAN DEFAULT true,
    FOREIGN KEY(id_perfil_coauch) REFERENCES perfil_coauch
); 

INSERT INTO plantilla_rutina(id_perfil_coauch,nombre)
VALUES(1,'Definicion Mujeres'),
(1,'Fuerza Principiantes');

select * from plantilla_rutina;

CREATE TABLE dia_rutina(
    id_dia_rutina INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_plantilla_rutina INT NOT NULL,
    dia_semana VARCHAR(250) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT now(),
    activo BOOLEAN DEFAULT true,
    FOREIGN KEY(id_plantilla_rutina) REFERENCES plantilla_rutina
);
INSERT INTO dia_rutina(id_plantilla_rutina,dia_semana)VALUES(1,'LUNES');
SELECT * FROM dia_rutina;


create TABLE actividad_rutina(
    id_actividad_rutina INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_dia_rutina INT NOT NULL,
    id_actividad INT NOT NULL,
    series INT NOT NULL,
    repeticiones INT,
    duracion_minutos INT,
    descanso INT,
    orden INT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT now(),
    activo BOOLEAN DEFAULT true,
   FOREIGN KEY(id_dia_rutina) REFERENCES dia_rutina,
   FOREIGN KEY(id_actividad) REFERENCES actividad
);


INSERT INTO actividad_rutina(id_dia_rutina,id_actividad,series,repeticiones,duracion_minutos,descanso,orden)
VALUES(1,1,4,12,30,60,1),
(1,1,4,8,20,10,2);
select  * from actividad_rutina;


use PULSO_DB;


CREATE TABLE sesion_entrenamiento(
 id_sesion_entrenamiento INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 id_perfil_alumno INT NOT NULL,
 fecha_inicio TIMESTAMP DEFAULT now(),
 fecha_termino DATE NOT NULL,
 completado BOOLEAN DEFAULT true,
 FOREIGN KEY(id_perfil_alumno) REFERENCES perfil_alumno
);


CREATE TABLE registro_actividad(
 id_registro_actividad INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
id_sesion_entrenamiento INT NOT NULL,
 id_actividad INT  NOT NULL,
 numero_serie INT  NOT NULL,
 peso DOUBLE NOT NULL DEFAULT 0.0,
 repeticiones  INT NOT NULL DEFAULT  1,
  duracion_minutos  INT NOT NULL DEFAULT  1,
  completado BOOLEAN NOT NULL ,
   FOREIGN KEY(id_sesion_entrenamiento) REFERENCES sesion_entrenamiento,
    FOREIGN KEY(id_actividad) REFERENCES actividad
);


CREATE TABLE composicion_corporal(
 id_composicion_corporal INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 id_perfil_alumno int NOT NULL,
 statura INT  NOT NULL,
 peso DOUBLE  NOT NULL,
 masa_muscular  INT  NOT NULL,
 grasa INT  NOT NULL,
 grasa_vicersal INT  NOT NULL,
 agua INT  NOT NULL,
 masa_osea INT  NOT NULL,
 bmi INT  NOT NULL,
 fecha_creacion TIMESTAMP DEFAULT now(),
 FOREIGN KEY(id_perfil_alumno) REFERENCES perfil_alumno
);

CREATE TABLE medidas_corporales(
 id_composicion_corporal INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 id_perfil_alumno int NOT NULL,
 pecho INT  NOT NULL,
 cintura INT  NOT NULL,
 cadera  INT  NOT NULL,
 brazo INT  NOT NULL,
 muslo INT  NOT NULL,
 pantorrilla INT  NOT NULL,
 masa_osea INT  NOT NULL,
 bmi INT  NOT NULL,
 fecha_creacion TIMESTAMP DEFAULT now(),
 FOREIGN KEY(id_perfil_alumno) REFERENCES perfil_alumno
);


CREATE TABLE onboarding_token(
 id_onboardin_token INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
 id_usuario int NOT NULL,
 token varchar(100)  NOT NULL,
 usado BOOLEAN ,
 fecha_expiracion TIMESTAMP DEFAULT now(),
 fecha_creacion TIMESTAMP,
 FOREIGN KEY(id_usuario) REFERENCES usuarios
);