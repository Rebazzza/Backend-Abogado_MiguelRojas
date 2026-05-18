/*
    Script de referencia para SQL Server
    Basado en los modelos actuales del proyecto Spring Boot.

    Objetivo:
    - Crear una version clara del esquema para visualizar el diagrama de relaciones.
    - Mantener las relaciones principales del dominio.
    - Usar nombres de tablas y columnas consistentes para SQL Server.

    Nota:
    - Este script representa el modelo logico/relacional recomendado segun el estado actual del proyecto.
    - No intenta replicar literalmente todos los nombres raros que Hibernate podria generar.
*/

SET NOCOUNT ON;
GO

/* Drop en orden inverso por dependencias */
IF OBJECT_ID('dbo.abogado_servicio', 'U') IS NOT NULL DROP TABLE dbo.abogado_servicio;
IF OBJECT_ID('dbo.abogado_area', 'U') IS NOT NULL DROP TABLE dbo.abogado_area;
IF OBJECT_ID('dbo.notificacion', 'U') IS NOT NULL DROP TABLE dbo.notificacion;
IF OBJECT_ID('dbo.documentos_legales', 'U') IS NOT NULL DROP TABLE dbo.documentos_legales;
IF OBJECT_ID('dbo.pago', 'U') IS NOT NULL DROP TABLE dbo.pago;
IF OBJECT_ID('dbo.audiencia', 'U') IS NOT NULL DROP TABLE dbo.audiencia;
IF OBJECT_ID('dbo.cita', 'U') IS NOT NULL DROP TABLE dbo.cita;
IF OBJECT_ID('dbo.expediente', 'U') IS NOT NULL DROP TABLE dbo.expediente;
IF OBJECT_ID('dbo.caso', 'U') IS NOT NULL DROP TABLE dbo.caso;
IF OBJECT_ID('dbo.especialista', 'U') IS NOT NULL DROP TABLE dbo.especialista;
IF OBJECT_ID('dbo.servicio_legal', 'U') IS NOT NULL DROP TABLE dbo.servicio_legal;
IF OBJECT_ID('dbo.area_derecho', 'U') IS NOT NULL DROP TABLE dbo.area_derecho;
IF OBJECT_ID('dbo.abogado', 'U') IS NOT NULL DROP TABLE dbo.abogado;
IF OBJECT_ID('dbo.cliente', 'U') IS NOT NULL DROP TABLE dbo.cliente;
IF OBJECT_ID('dbo.usuario', 'U') IS NOT NULL DROP TABLE dbo.usuario;
GO

CREATE TABLE dbo.usuario (
    id_usuario INT IDENTITY(1,1) NOT NULL,
    nombre NVARCHAR(50) NOT NULL,
    rol NVARCHAR(50) NOT NULL,
    contrasena NVARCHAR(50) NOT NULL,
    CONSTRAINT PK_usuario PRIMARY KEY (id_usuario)
);
GO

CREATE TABLE dbo.abogado (
    id_abogado INT IDENTITY(1,1) NOT NULL,
    nombre NVARCHAR(150) NOT NULL,
    apellido NVARCHAR(150) NOT NULL,
    telefono NVARCHAR(50) NOT NULL,
    dni NVARCHAR(20) NULL,
    correo NVARCHAR(100) NULL,
    especialidad NVARCHAR(50) NULL,
    estado BIT NULL,
    id_usuario INT NOT NULL,
    CONSTRAINT PK_abogado PRIMARY KEY (id_abogado),
    CONSTRAINT UQ_abogado_id_usuario UNIQUE (id_usuario),
    CONSTRAINT FK_abogado_usuario FOREIGN KEY (id_usuario)
        REFERENCES dbo.usuario(id_usuario)
);
GO

CREATE TABLE dbo.cliente (
    id_cliente INT IDENTITY(1,1) NOT NULL,
    nombre NVARCHAR(70) NOT NULL,
    descripcion NVARCHAR(200) NULL,
    dni NVARCHAR(15) NOT NULL,
    ruc NVARCHAR(15) NULL,
    telefono NVARCHAR(15) NULL,
    direccion NVARCHAR(150) NULL,
    correo NVARCHAR(150) NULL,
    estado BIT NOT NULL,
    tipo_cliente BIT NOT NULL,
    CONSTRAINT PK_cliente PRIMARY KEY (id_cliente)
);
GO

CREATE TABLE dbo.area_derecho (
    id_area INT IDENTITY(1,1) NOT NULL,
    nombre NVARCHAR(100) NOT NULL,
    descripcion NVARCHAR(200) NOT NULL,
    estado BIT NOT NULL,
    CONSTRAINT PK_area_derecho PRIMARY KEY (id_area)
);
GO

CREATE TABLE dbo.servicio_legal (
    id_servicio INT IDENTITY(1,1) NOT NULL,
    nombre NVARCHAR(100) NOT NULL,
    descripcion NVARCHAR(500) NOT NULL,
    estado NVARCHAR(30) NOT NULL,
    costo_base DECIMAL(10,2) NULL,
    CONSTRAINT PK_servicio_legal PRIMARY KEY (id_servicio)
);
GO

CREATE TABLE dbo.especialista (
    id_especialista INT IDENTITY(1,1) NOT NULL,
    nombre NVARCHAR(100) NOT NULL,
    descripcion NVARCHAR(500) NOT NULL,
    estado NVARCHAR(30) NOT NULL,
    dni BIT NOT NULL,
    disponibilidad BIT NOT NULL,
    telefono NVARCHAR(20) NOT NULL,
    correo NVARCHAR(20) NOT NULL,
    CONSTRAINT PK_especialista PRIMARY KEY (id_especialista)
);
GO

CREATE TABLE dbo.caso (
    id_caso INT IDENTITY(1,1) NOT NULL,
    titulo NVARCHAR(100) NOT NULL,
    descripcion NVARCHAR(500) NOT NULL,
    estado NVARCHAR(30) NOT NULL,
    status BIT NOT NULL,
    id_abogado INT NOT NULL,
    id_cliente INT NOT NULL,
    CONSTRAINT PK_caso PRIMARY KEY (id_caso),
    CONSTRAINT FK_caso_abogado FOREIGN KEY (id_abogado)
        REFERENCES dbo.abogado(id_abogado),
    CONSTRAINT FK_caso_cliente FOREIGN KEY (id_cliente)
        REFERENCES dbo.cliente(id_cliente)
);
GO

CREATE TABLE dbo.cita (
    id_cita INT IDENTITY(1,1) NOT NULL,
    asunto_legal NVARCHAR(150) NOT NULL,
    detalles_adicionales NVARCHAR(500) NULL,
    fecha_hora DATETIME2 NOT NULL,
    activa BIT NOT NULL,
    id_cliente INT NOT NULL,
    id_abogado INT NOT NULL,
    CONSTRAINT PK_cita PRIMARY KEY (id_cita),
    CONSTRAINT FK_cita_cliente FOREIGN KEY (id_cliente)
        REFERENCES dbo.cliente(id_cliente),
    CONSTRAINT FK_cita_abogado FOREIGN KEY (id_abogado)
        REFERENCES dbo.abogado(id_abogado)
);
GO

CREATE TABLE dbo.expediente (
    id_expediente INT IDENTITY(1,1) NOT NULL,
    titulo NVARCHAR(70) NOT NULL,
    tipo_expediente NVARCHAR(70) NOT NULL,
    resumen_expediente NVARCHAR(200) NULL,
    estado_expediente BIT NULL,
    fecha_inicio NVARCHAR(50) NULL,
    fecha_cierre NVARCHAR(50) NULL,
    id_caso INT NOT NULL,
    CONSTRAINT PK_expediente PRIMARY KEY (id_expediente),
    CONSTRAINT UQ_expediente_id_caso UNIQUE (id_caso),
    CONSTRAINT FK_expediente_caso FOREIGN KEY (id_caso)
        REFERENCES dbo.caso(id_caso)
);
GO

CREATE TABLE dbo.documentos_legales (
    id_documento INT IDENTITY(1,1) NOT NULL,
    nombre_archivo NVARCHAR(70) NOT NULL,
    ruta NVARCHAR(70) NOT NULL,
    fecha_creacion DATETIME2 NOT NULL,
    id_expediente INT NOT NULL,
    CONSTRAINT PK_documentos_legales PRIMARY KEY (id_documento),
    CONSTRAINT FK_documentos_legales_expediente FOREIGN KEY (id_expediente)
        REFERENCES dbo.expediente(id_expediente)
);
GO

CREATE TABLE dbo.audiencia (
    id_audiencia INT IDENTITY(1,1) NOT NULL,
    fecha DATE NOT NULL,
    direccion NVARCHAR(100) NOT NULL,
    hora DATETIME2 NULL,
    tipo_audiencia NVARCHAR(200) NULL,
    lugar_link NVARCHAR(200) NULL,
    id_abogado INT NOT NULL,
    id_caso INT NOT NULL,
    CONSTRAINT PK_audiencia PRIMARY KEY (id_audiencia),
    CONSTRAINT FK_audiencia_abogado FOREIGN KEY (id_abogado)
        REFERENCES dbo.abogado(id_abogado),
    CONSTRAINT FK_audiencia_caso FOREIGN KEY (id_caso)
        REFERENCES dbo.caso(id_caso)
);
GO

CREATE TABLE dbo.pago (
    id_pago INT IDENTITY(1,1) NOT NULL,
    metodo_pago NVARCHAR(50) NULL,
    estado_pago BIT NULL,
    monto DECIMAL(12,2) NULL,
    fecha_pago DATETIME2 NULL,
    id_caso INT NOT NULL,
    CONSTRAINT PK_pago PRIMARY KEY (id_pago),
    CONSTRAINT FK_pago_caso FOREIGN KEY (id_caso)
        REFERENCES dbo.caso(id_caso)
);
GO

CREATE TABLE dbo.notificacion (
    id_notificacion INT IDENTITY(1,1) NOT NULL,
    titulo NVARCHAR(70) NOT NULL,
    mensaje NVARCHAR(500) NOT NULL,
    leido BIT NOT NULL,
    fecha DATETIME2 NULL,
    id_usuario INT NOT NULL,
    CONSTRAINT PK_notificacion PRIMARY KEY (id_notificacion),
    CONSTRAINT FK_notificacion_usuario FOREIGN KEY (id_usuario)
        REFERENCES dbo.usuario(id_usuario)
);
GO

CREATE TABLE dbo.abogado_area (
    id_abogado_area INT IDENTITY(1,1) NOT NULL,
    id_abogado INT NOT NULL,
    id_area INT NOT NULL,
    CONSTRAINT PK_abogado_area PRIMARY KEY (id_abogado_area),
    CONSTRAINT UQ_abogado_area UNIQUE (id_abogado, id_area),
    CONSTRAINT FK_abogado_area_abogado FOREIGN KEY (id_abogado)
        REFERENCES dbo.abogado(id_abogado),
    CONSTRAINT FK_abogado_area_area FOREIGN KEY (id_area)
        REFERENCES dbo.area_derecho(id_area)
);
GO

CREATE TABLE dbo.abogado_servicio (
    id_abogado_servicio INT IDENTITY(1,1) NOT NULL,
    id_abogado INT NOT NULL,
    id_servicio INT NOT NULL,
    CONSTRAINT PK_abogado_servicio PRIMARY KEY (id_abogado_servicio),
    CONSTRAINT UQ_abogado_servicio UNIQUE (id_abogado, id_servicio),
    CONSTRAINT FK_abogado_servicio_abogado FOREIGN KEY (id_abogado)
        REFERENCES dbo.abogado(id_abogado),
    CONSTRAINT FK_abogado_servicio_servicio FOREIGN KEY (id_servicio)
        REFERENCES dbo.servicio_legal(id_servicio)
);
GO

/*
Resumen visual de relaciones:

usuario           1 --- 1 abogado
usuario           1 --- N notificacion
abogado           1 --- N caso
cliente           1 --- N caso
abogado           1 --- N cita
cliente           1 --- N cita
caso              1 --- 1 expediente
expediente        1 --- N documentos_legales
caso              1 --- N audiencia
abogado           1 --- N audiencia
caso              1 --- N pago
abogado           N --- M area_derecho       (via abogado_area)
abogado           N --- M servicio_legal     (via abogado_servicio)

especialista:
- tabla actualmente aislada, sin relaciones en los modelos.
*/
