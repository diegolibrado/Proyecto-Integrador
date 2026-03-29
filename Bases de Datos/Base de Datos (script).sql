DROP TABLE IF EXISTS COLABORA_EN;
DROP TABLE IF EXISTS TRAJE;
DROP TABLE IF EXISTS CITA;
DROP TABLE IF EXISTS EMPLEADO;
DROP TABLE IF EXISTS TALLER;
DROP TABLE IF EXISTS CLIENTE;

CREATE TABLE CLIENTE (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY, -- Para que el id empiece en 1 y cambie en cada creación
    nombre VARCHAR(100) NOT NULL,
    superpoder VARCHAR(100),
    colores VARCHAR(100)
);

CREATE TABLE TALLER (
    id_taller INT AUTO_INCREMENT PRIMARY KEY,
    nombre_sala VARCHAR(50) NOT NULL,
    tipo_sala ENUM('diseño', 'costura', 'pruebas') NOT NULL -- ENUM para que solo puedan ser esas opciones
);

CREATE TABLE EMPLEADO (
    id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apodo VARCHAR(50) UNIQUE NOT NULL,
    categoria ENUM('Aprendiz', 'Oficial', 'Maestro') NOT NULL,
    contrasena VARCHAR(255) NOT NULL
);

CREATE TABLE CITA (
    id_cita INT AUTO_INCREMENT PRIMARY KEY,
    dia DATE NOT NULL,
    hora TIME NOT NULL,
    duracion INT DEFAULT 1, -- Por defecto dura 1 hora
    id_cliente INT,
    id_taller INT,
    id_empleado_responsable INT,
    FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id_cliente) ON DELETE CASCADE,
    FOREIGN KEY (id_taller) REFERENCES TALLER(id_taller),
    FOREIGN KEY (id_empleado_responsable) REFERENCES EMPLEADO(id_empleado)
);

CREATE TABLE TRAJE (
    id_traje INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    estado ENUM('en diseño', 'costura', 'taller') NOT NULL,
    id_cliente INT,
    FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id_cliente) ON DELETE CASCADE
);

-- Relación
CREATE TABLE COLABORA_EN (
    id_empleado INT,
    id_cita INT,
    PRIMARY KEY (id_empleado, id_cita),
    FOREIGN KEY (id_empleado) REFERENCES EMPLEADO(id_empleado),
    FOREIGN KEY (id_cita) REFERENCES CITA(id_cita) ON DELETE CASCADE
);

-- Inserción de datos (3 de momento)
INSERT INTO TALLER (nombre_sala, tipo_sala) VALUES 
('Milán', 'diseño'),
('París', 'costura'),
('Madrid', 'pruebas');

INSERT INTO EMPLEADO (nombre, apodo, categoria, contrasena) VALUES 
('Diego', 'Abuelo', 'Maestro', '1234'),
('Pablo', 'Diez', 'Oficial', 'abcd'),
('Lucas', 'Lobo', 'Aprendiz', '0000');

INSERT INTO CLIENTE (nombre, superpoder, colores) VALUES 
('Mr. Increíble', 'Superfuerza', 'Rojo y Negro'),
('Síndrome', 'Tecnología', 'Blanco y Negro'),
('Elastic girl', 'Elasticidad', 'Rojo y Blanco');

INSERT INTO TRAJE (nombre, estado, id_cliente) VALUES 
('Traje Clásico', 'en diseño', 1),
('Armadura Final', 'taller', 2),
('Traje de Invisibilidad', 'costura', '3');
