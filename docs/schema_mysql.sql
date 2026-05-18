-- =========================================================
-- Script SQL de referencia para el modelo relacional
-- Proyecto: AppWeb-MiguelRojas
-- Motor objetivo: MySQL 8+
-- =========================================================
--
-- Este script refleja el modelo actual del proyecto con una
-- estructura legible para entender mejor las relaciones.
-- Está pensado como apoyo visual y base de BD.
--
-- Relaciones principales:
-- 1) usuario 1:1 abogado
-- 2) cliente 1:N caso
-- 3) abogado 1:N caso
-- 4) cliente 1:N cita
-- 5) abogado 1:N cita
-- 6) caso 1:N audiencia
-- 7) abogado 1:N audiencia
-- 8) caso 1:1 expediente
-- 9) expediente 1:N documentos_legales
-- 10) caso 1:N pago
-- 11) usuario 1:N notificacion
-- 12) abogado N:M area_derecho por abogado_area
-- 13) abogado N:M servicio_legal por abogado_servicio
--
-- =========================================================

CREATE DATABASE IF NOT EXISTS rentalappdb
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE rentalappdb;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS abogado_servicio;
DROP TABLE IF EXISTS abogado_area;
DROP TABLE IF EXISTS documentos_legales;
DROP TABLE IF EXISTS pago;
DROP TABLE IF EXISTS audiencia;
DROP TABLE IF EXISTS expediente;
DROP TABLE IF EXISTS cita;
DROP TABLE IF EXISTS caso;
DROP TABLE IF EXISTS especialista;
DROP TABLE IF EXISTS servicio_legal;
DROP TABLE IF EXISTS area_derecho;
DROP TABLE IF EXISTS notificacion;
DROP TABLE IF EXISTS abogado;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS usuario;

SET FOREIGN_KEY_CHECKS = 1;

-- =========================================================
-- TABLAS BASE
-- =========================================================

CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    rol VARCHAR(50) NOT NULL,
    contrasena VARCHAR(255) NOT NULL
);

CREATE TABLE cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(70) NOT NULL,
    descripcion VARCHAR(200),
    dni VARCHAR(15) NOT NULL,
    ruc VARCHAR(15),
    telefono VARCHAR(15),
    direccion VARCHAR(150),
    correo VARCHAR(100),
    estado BOOLEAN NOT NULL,
    tipo_cliente BOOLEAN NOT NULL
);

CREATE TABLE abogado (
    id_abogado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    apellido VARCHAR(150) NOT NULL,
    telefono VARCHAR(50) NOT NULL,
    dni VARCHAR(20),
    correo VARCHAR(100),
    especialidad VARCHAR(50),
    estado BOOLEAN,
    id_usuario INT NOT NULL UNIQUE,
    CONSTRAINT fk_abogado_usuario
        FOREIGN KEY (id_usuario)
        REFERENCES usuario(id_usuario)
);

CREATE TABLE area_derecho (
    id_area INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    estado BOOLEAN NOT NULL
);

CREATE TABLE servicio_legal (
    id_servicio INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500) NOT NULL,
    estado VARCHAR(30) NOT NULL,
    costo_base DECIMAL(10,2)
);

CREATE TABLE especialista (
    id_especialista INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500) NOT NULL,
    estado VARCHAR(30) NOT NULL,
    dni VARCHAR(20),
    disponibilidad BOOLEAN NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    correo VARCHAR(100) NOT NULL
);

-- =========================================================
-- TABLAS DEL NEGOCIO
-- =========================================================

CREATE TABLE caso (
    id_caso INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500) NOT NULL,
    estado VARCHAR(30) NOT NULL,
    status BOOLEAN NOT NULL,
    id_abogado INT NOT NULL,
    id_cliente INT NOT NULL,
    CONSTRAINT fk_caso_abogado
        FOREIGN KEY (id_abogado)
        REFERENCES abogado(id_abogado),
    CONSTRAINT fk_caso_cliente
        FOREIGN KEY (id_cliente)
        REFERENCES cliente(id_cliente)
);

CREATE TABLE cita (
    id_cita INT AUTO_INCREMENT PRIMARY KEY,
    asunto_legal VARCHAR(150) NOT NULL,
    detalles_adicionales VARCHAR(500),
    fecha_hora DATETIME NOT NULL,
    activa BOOLEAN NOT NULL,
    id_cliente INT NOT NULL,
    id_abogado INT NOT NULL,
    CONSTRAINT fk_cita_cliente
        FOREIGN KEY (id_cliente)
        REFERENCES cliente(id_cliente),
    CONSTRAINT fk_cita_abogado
        FOREIGN KEY (id_abogado)
        REFERENCES abogado(id_abogado)
);

CREATE TABLE audiencia (
    id_audiencia INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    hora DATETIME,
    tipo_audiencia VARCHAR(200),
    lugar_link VARCHAR(200),
    id_abogado INT NOT NULL,
    id_caso INT NOT NULL,
    CONSTRAINT fk_audiencia_abogado
        FOREIGN KEY (id_abogado)
        REFERENCES abogado(id_abogado),
    CONSTRAINT fk_audiencia_caso
        FOREIGN KEY (id_caso)
        REFERENCES caso(id_caso)
);

CREATE TABLE expediente (
    id_expediente INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(70) NOT NULL,
    tipo_expediente VARCHAR(70) NOT NULL,
    resumen_expediente VARCHAR(200),
    estado_expediente BOOLEAN,
    fecha_inicio VARCHAR(50),
    fecha_cierre VARCHAR(50),
    id_caso INT NOT NULL UNIQUE,
    CONSTRAINT fk_expediente_caso
        FOREIGN KEY (id_caso)
        REFERENCES caso(id_caso)
);

CREATE TABLE documentos_legales (
    id_documento INT AUTO_INCREMENT PRIMARY KEY,
    nombre_archivo VARCHAR(70) NOT NULL,
    ruta VARCHAR(255) NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    id_expediente INT NOT NULL,
    CONSTRAINT fk_documento_expediente
        FOREIGN KEY (id_expediente)
        REFERENCES expediente(id_expediente)
);

CREATE TABLE pago (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    metodo_pago VARCHAR(50),
    estado_pago BOOLEAN,
    monto DECIMAL(10,2),
    fecha_pago DATETIME,
    id_caso INT NOT NULL,
    CONSTRAINT fk_pago_caso
        FOREIGN KEY (id_caso)
        REFERENCES caso(id_caso)
);

CREATE TABLE notificacion (
    id_notificacion INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(70) NOT NULL,
    mensaje VARCHAR(500) NOT NULL,
    leido BOOLEAN NOT NULL,
    fecha DATETIME,
    id_usuario INT NOT NULL,
    CONSTRAINT fk_notificacion_usuario
        FOREIGN KEY (id_usuario)
        REFERENCES usuario(id_usuario)
);

-- =========================================================
-- TABLAS INTERMEDIAS
-- =========================================================

CREATE TABLE abogado_area (
    id_abogado_area INT AUTO_INCREMENT PRIMARY KEY,
    id_abogado INT NOT NULL,
    id_area INT NOT NULL,
    CONSTRAINT uq_abogado_area UNIQUE (id_abogado, id_area),
    CONSTRAINT fk_abogado_area_abogado
        FOREIGN KEY (id_abogado)
        REFERENCES abogado(id_abogado),
    CONSTRAINT fk_abogado_area_area
        FOREIGN KEY (id_area)
        REFERENCES area_derecho(id_area)
);

CREATE TABLE abogado_servicio (
    id_abogado_servicio INT AUTO_INCREMENT PRIMARY KEY,
    id_abogado INT NOT NULL,
    id_servicio INT NOT NULL,
    CONSTRAINT uq_abogado_servicio UNIQUE (id_abogado, id_servicio),
    CONSTRAINT fk_abogado_servicio_abogado
        FOREIGN KEY (id_abogado)
        REFERENCES abogado(id_abogado),
    CONSTRAINT fk_abogado_servicio_servicio
        FOREIGN KEY (id_servicio)
        REFERENCES servicio_legal(id_servicio)
);

-- =========================================================
-- ÍNDICES ÚTILES
-- =========================================================

CREATE INDEX idx_caso_abogado ON caso(id_abogado);
CREATE INDEX idx_caso_cliente ON caso(id_cliente);
CREATE INDEX idx_cita_cliente ON cita(id_cliente);
CREATE INDEX idx_cita_abogado ON cita(id_abogado);
CREATE INDEX idx_audiencia_abogado ON audiencia(id_abogado);
CREATE INDEX idx_audiencia_caso ON audiencia(id_caso);
CREATE INDEX idx_pago_caso ON pago(id_caso);
CREATE INDEX idx_documento_expediente ON documentos_legales(id_expediente);
CREATE INDEX idx_notificacion_usuario ON notificacion(id_usuario);

-- =========================================================
-- CONSULTAS DE APOYO PARA "VER" EL DIAGRAMA RELACIONAL
-- =========================================================

-- Ver todas las tablas del esquema
-- SHOW TABLES;

-- Ver columnas de una tabla
-- DESCRIBE caso;

-- Ver relaciones del esquema
-- SELECT
--     TABLE_NAME,
--     COLUMN_NAME,
--     CONSTRAINT_NAME,
--     REFERENCED_TABLE_NAME,
--     REFERENCED_COLUMN_NAME
-- FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
-- WHERE TABLE_SCHEMA = 'rentalappdb'
--   AND REFERENCED_TABLE_NAME IS NOT NULL
-- ORDER BY TABLE_NAME, COLUMN_NAME;
