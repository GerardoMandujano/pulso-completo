USE pulso_db;

CREATE TABLE IF NOT EXISTS dia_rutina (
  id_dia_rutina INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  id_plantilla_rutina INT NOT NULL,
  dia_semana VARCHAR(250) NOT NULL,
  fecha_creacion TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  activo TINYINT(1) DEFAULT 1,
  CONSTRAINT fk_dia_rutina_plantilla
    FOREIGN KEY (id_plantilla_rutina) REFERENCES plantilla_rutina(id_plantilla_rutina)
);

CREATE TABLE IF NOT EXISTS actividad_rutina (
  id_actividad_rutina INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  id_dia_rutina INT NOT NULL,
  id_actividad INT NOT NULL,
  series INT NOT NULL,
  repeticiones INT DEFAULT NULL,
  duracion_minutos INT DEFAULT NULL,
  descanso INT DEFAULT NULL,
  orden INT NOT NULL,
  fecha_creacion TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  activo TINYINT(1) DEFAULT 1,
  CONSTRAINT fk_actividad_rutina_dia
    FOREIGN KEY (id_dia_rutina) REFERENCES dia_rutina(id_dia_rutina),
  CONSTRAINT fk_actividad_rutina_actividad
    FOREIGN KEY (id_actividad) REFERENCES actividad(id_actividad)
);

CREATE INDEX idx_dia_rutina_plantilla_activo
ON dia_rutina(id_plantilla_rutina, activo);

CREATE INDEX idx_actividad_rutina_dia_activo_orden
ON actividad_rutina(id_dia_rutina, activo, orden);
