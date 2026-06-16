-- ============================================================
-- SCRIPT DE DATOS DE PRUEBA - AppWeb MiguelRojas
-- Base de datos: appweb_miguelrojas
-- ============================================================
-- IMPORTANTE: Las contraseñas usan BCrypt.
-- Ejecuta este snippet en la consola de la app para generar
-- el hash de "123456":
--
-- System.out.println(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("123456"));
--
-- Luego reemplaza TODAS las ocurrencias de 'AQUI_EL_HASH_BCRYPT'
-- con el hash generado.
-- ============================================================

-- 1. ROLES (IDs manuales)
INSERT INTO rol (id_role, name, description) VALUES
(1, 'ADMIN', 'Administrador del sistema'),
(2, 'ABOGADO', 'Abogado de la firma'),
(3, 'CLIENTE', 'Cliente registrado');

-- 2. MENÚ (IDs manuales)
INSERT INTO menu (id_menu, icon, name, url) VALUES
(1, 'dashboard', 'Dashboard', '/dashboard'),
(2, 'people', 'Clientes', '/clientes'),
(3, 'gavel', 'Abogados', '/abogados'),
(4, 'folder', 'Casos', '/casos'),
(5, 'calendar_month', 'Citas', '/citas'),
(6, 'payments', 'Pagos', '/pagos'),
(7, 'notifications', 'Notificaciones', '/notificaciones'),
(8, 'description', 'Documentos', '/documentos'),
(9, 'admin_panel_settings', 'Usuarios', '/usuarios'),
(10, 'fact_check', 'Audiencias', '/audiencias'),
(11, 'work', 'Expedientes', '/expedientes'),
(12, 'handyman', 'Servicios', '/servicios'),
(13, 'account_balance', 'Áreas', '/areas'),
(14, 'psychology', 'Especialistas', '/especialistas');

-- 3. MENÚ POR ROL
-- ADMIN (1) -> todo
INSERT INTO menu_role (id_menu, id_role) VALUES
(1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (7, 1), (8, 1), (9, 1), (10, 1), (11, 1), (12, 1), (13, 1), (14, 1);
-- ABOGADO (2)
INSERT INTO menu_role (id_menu, id_role) VALUES
(1, 2), (2, 2), (3, 2), (4, 2), (5, 2), (6, 2), (7, 2), (8, 2), (10, 2), (11, 2), (13, 2);
-- CLIENTE (3)
INSERT INTO menu_role (id_menu, id_role) VALUES
(1, 3), (5, 3), (7, 3);

-- 4. USUARIOS
-- Contraseña para todos: 123456 (BCrypt)
INSERT INTO usuarios (id_usuario, username, rol, password) VALUES
(1, 'admin',     'ADMIN',   'AQUI_EL_HASH_BCRYPT'),
(2, 'cgutierrez','ABOGADO', 'AQUI_EL_HASH_BCRYPT'),
(3, 'mramirez',  'ABOGADO', 'AQUI_EL_HASH_BCRYPT'),
(4, 'jlopez',    'CLIENTE', 'AQUI_EL_HASH_BCRYPT'),
(5, 'mrodriguez','CLIENTE', 'AQUI_EL_HASH_BCRYPT');

-- 5. USER_ROLE
INSERT INTO user_role (id_user, id_role) VALUES
(1, 1),
(2, 2),
(3, 2),
(4, 3),
(5, 3);

-- 6. ABOGADOS (OneToOne con Usuario)
INSERT INTO abogado (id_abogado, nombre, apellido, telefono, dni, correo, especialidad, estado, id_usuario) VALUES
(1, 'Carlos',   'Gutiérrez', '987654321', '12345678', 'carlos.gutierrez@estudio.com', 'Penal',      1, 2),
(2, 'María',    'Ramírez',   '987654322', '87654321', 'maria.ramirez@estudio.com',   'Civil',      1, 3);

-- 7. CLIENTES
INSERT INTO cliente (id_cliente, nombre, descripcion, dni, ruc, telefono, dirección, correo, estado, tipo_cliente) VALUES
(1, 'Constructora ABC SAC',    'Empresa de construcción civil',         '20123456789', '20123456789', '999888777', 'Av. Principal 123 - Lima',       'info@constructoraabc.com', 1, 'EMPRESA'),
(2, 'Juan Antonio López',      'Cliente particular - divorcio',        '12345678',    NULL,          '999888111', 'Jr. Las Flores 456 - Miraflores','juan.lopez@email.com',     1, 'NATURAL'),
(3, 'María Elena Rodríguez',   'Cliente particular - demanda laboral', '87654321',    NULL,          '999888222', 'Av. Los Olivos 789 - San Isidro','maria.rodriguez@email.com',1, 'NATURAL');

-- 8. ÁREAS DE DERECHO
INSERT INTO area_derecho (id_area, nombre, descripcion, estado) VALUES
(1, 'Derecho Penal',        'Delitos, faltas y procesos penales',                    1),
(2, 'Derecho Civil',        'Contratos, obligaciones y responsabilidad civil',       1),
(3, 'Derecho Laboral',      'Relaciones laborales y despidos',                       1),
(4, 'Derecho Tributario',   'Impuestos y obligaciones fiscales',                     1),
(5, 'Derecho Corporativo',  'Constitución de empresas y sociedades',                 1),
(6, 'Derecho de Familia',   'Divorcios, tenencia y alimentos',                       1);

-- 9. ABOGADO - ÁREA
INSERT INTO abogado_area (id_abogado_area, id_abogado, id_area) VALUES
(1, 1, 1),  -- Carlos Gutiérrez -> Penal
(2, 1, 2),  -- Carlos Gutiérrez -> Civil
(3, 2, 2),  -- María Ramírez    -> Civil
(4, 2, 3),  -- María Ramírez    -> Laboral
(5, 2, 5),  -- María Ramírez    -> Corporativo
(6, 2, 6);  -- María Ramírez    -> Familia

-- 10. SERVICIOS LEGALES
INSERT INTO servicio_legal (id_servicio, nombre, descripcion, estado, costo_base) VALUES
(1, 'Asesoría Legal General',  'Consulta jurídica integral',                'ACTIVO', 150.00),
(2, 'Defensa Penal',           'Representación en procesos penales',        'ACTIVO', 500.00),
(3, 'Litigios Civiles',        'Demandas y procesos civiles',               'ACTIVO', 400.00),
(4, 'Redacción de Contratos',  'Elaboración y revisión de contratos',       'ACTIVO', 200.00),
(5, 'Constitución de Empresas','Trámites notariales y registrales',         'ACTIVO', 350.00),
(6, 'Divorcio',                'Proceso de divorcio ante notaría o juzgado','ACTIVO', 300.00);

-- 11. ABOGADO - SERVICIO
INSERT INTO abogado_servicio (id_abogado_servicio, id_abogado, id_servicio) VALUES
(1, 1, 1),  -- Carlos -> Asesoría
(2, 1, 2),  -- Carlos -> Defensa Penal
(3, 1, 3),  -- Carlos -> Litigios Civiles
(4, 2, 1),  -- María  -> Asesoría
(5, 2, 4),  -- María  -> Contratos
(6, 2, 5),  -- María  -> Constitución
(7, 2, 6);  -- María  -> Divorcio

-- 12. CASOS
INSERT INTO caso (id_caso, titulo, descripcion, estado, id_abogado, id_cliente) VALUES
(1, 'Defensa por Estafa Empresarial', 'Empresa acusada de estafa en licitación pública',  1, 1, 1),
(2, 'Divorcio Contencioso',           'Divorcio con disputa de bienes y tenencia',         1, 2, 2),
(3, 'Demanda por Despido Injustificado','Despido sin causa justa luego de 8 años',        0, 2, 3),
(4, 'Constitución de Nueva Empresa',  'Constitución de filial para Constructora ABC',      1, 2, 1),
(5, 'Cobro de Deuda Civil',           'Deuda impaga de S/ 25,000 por servicios prestados', 1, 1, 1);

-- 13. EXPEDIENTES (OneToOne con Caso)
INSERT INTO expediente (id_ex_pediente, titulo, tipo_expediente, resumen_expediente, estado_expediente, fecha_inicio, fecha_cierre, id_caso) VALUES
(1, 'EXP-2024-001', 'Judicial',   'Expediente judicial por estafa en licitación',              1, '2024-01-15', NULL,  1),
(2, 'EXP-2024-002', 'Judicial',   'Expediente de divorcio contencioso',                        1, '2024-03-20', NULL,  2),
(3, 'EXP-2024-003', 'Judicial',   'Demanda laboral por despido injustificado',                 0, '2024-06-10', '2024-12-20', 3),
(4, 'EXP-2025-001', 'Notarial',   'Constitución de empresa filial',                            1, '2025-01-10', NULL,  4),
(5, 'EXP-2025-002', 'Judicial',   'Proceso de cobro de deuda civil',                           1, '2025-02-01', NULL,  5);

-- 14. AUDIENCIAS
INSERT INTO audiencia (id_audiencia, fecha, direccion, hora, tipo_audiencia, lugar_link, id_abogado, id_caso) VALUES
(1, '2024-02-20', 'Corte Superior de Lima - Sede Central', '2024-02-20T09:00:00', 'Audiencia Preliminar',       'Sala 301 - Piso 3', 1, 1),
(2, '2024-04-15', 'Corte Superior de Lima - Sede Central', '2024-04-15T10:00:00', 'Audiencia de Conciliación',  'Sala 105 - Piso 1', 2, 2),
(3, '2024-07-25', 'Corte Superior de Lima - Sede Norte',  '2024-07-25T09:30:00', 'Audiencia de Juicio',        'Sala 202 - Piso 2', 2, 3),
(4, '2025-03-10', 'Corte Superior de Lima - Sede Central', '2025-03-10T11:00:00', 'Audiencia de Pruebas',       'Sala 105 - Piso 1', 2, 2),
(5, '2025-05-12', 'Plataforma Virtual',                   '2025-05-12T15:00:00', 'Audiencia Virtual',          'https://meet.estudio.com/audiencia5', 1, 5);

-- 15. CITAS
INSERT INTO cita (id_cita, asunto_legal, detalles_adicionales, fecha_hora, activa, id_cliente, id_abogado) VALUES
(1, 'Consulta inicial sobre estafa',         'Primera consulta - traer documentos',    '2024-01-10T15:00:00', 0, 1, 1),
(2, 'Asesoría para divorcio',                NULL,                                     '2024-03-05T10:00:00', 0, 2, 2),
(3, 'Consulta por despido',                  'Revisar carta de despido y contratos',    '2024-06-01T11:00:00', 0, 3, 2),
(4, 'Constitución de empresa filial',        'Llevar DNI, RUC y minuta',               '2025-01-08T09:00:00', 0, 1, 2),
(5, 'Seguimiento de cobro de deuda',         'Actualización del proceso judicial',     '2025-04-20T16:00:00', 1, 1, 1);

-- 16. PAGOS
INSERT INTO pago (id_pago, metodo_pago, estado_pago, monto, fecha_pago, id_caso) VALUES
(1, 'Transferencia Bancaria', 1, 2000.00, '2024-01-20T14:00:00', 1),
(2, 'Efectivo',              1, 1500.00, '2024-04-01T11:00:00', 2),
(3, 'Tarjeta de Crédito',    0,  500.00, '2024-07-01T10:00:00', 3),
(4, 'Transferencia Bancaria', 1, 3500.00, '2025-01-15T09:00:00', 4),
(5, 'Depósito en Cuenta',    1, 1000.00, '2025-02-15T12:00:00', 5),
(6, 'Tarjeta de Crédito',    1, 2500.00, '2025-03-01T16:00:00', 1);

-- 17. DOCUMENTOS LEGALES
INSERT INTO documentos_legales (id_documento, nombre_archivo, ruta, fecha_creacion, id_expediente) VALUES
(1, 'Demanda_Estafa.pdf',              '/documentos/exp001/demanda_estafa.pdf',              '2024-01-16T10:00:00', 1),
(2, 'Contrato_Servicios_Legales.pdf',  '/documentos/exp001/contrato_servicios.pdf',          '2024-01-20T11:00:00', 1),
(3, 'Solicitud_Divorcio.pdf',          '/documentos/exp002/solicitud_divorcio.pdf',          '2024-03-25T09:00:00', 2),
(4, 'Demanda_Laboral.pdf',             '/documentos/exp003/demanda_laboral.pdf',             '2024-06-15T08:00:00', 3),
(5, 'Minuta_Constitucion.pdf',         '/documentos/exp004/minuta_constitucion.pdf',         '2025-01-12T14:00:00', 4),
(6, 'Contrato_Comercial_Deuda.pdf',    '/documentos/exp005/contrato_deuda.pdf',              '2025-02-05T09:00:00', 5),
(7, 'Acta_Conciliacion.pdf',           '/documentos/exp002/acta_conciliacion.pdf',           '2024-04-20T15:00:00', 2);

-- 18. NOTIFICACIONES
INSERT INTO notificacion (id_notificacion, titulo, mensaje, leido, fecha, id_usuario) VALUES
(1, 'Nueva audiencia programada',    'Se ha programado una audiencia preliminar para el caso "Defensa por Estafa Empresarial" el 20/02/2024.', 0, '2024-01-18T09:00:00', 2),
(2, 'Pago recibido',                 'Se registró un pago de S/ 2,000.00 para el caso "Defensa por Estafa Empresarial".',                        1, '2024-01-20T14:30:00', 2),
(3, 'Nueva cita agendada',           'Tiene una nueva cita con Constructora ABC para el 08/01/2025 a las 09:00.',                                 0, '2024-12-30T15:00:00', 2),
(4, 'Recordatorio de audiencia',     'Recordatorio: Audiencia de Juicio del caso "Demanda por Despido" el 25/07/2024 a las 09:30.',               0, '2024-07-20T08:00:00', 3),
(5, 'Documento subido',              'Se adjuntó un nuevo documento al expediente EXP-2024-002.',                                                   1, '2024-04-20T15:30:00', 3),
(6, 'Caso cerrado',                  'El caso "Demanda por Despido Injustificado" ha sido cerrado.',                                              1, '2024-12-20T17:00:00', 3);

-- 19. ESPECIALISTAS
INSERT INTO especialista (id_especialista, nombre, descripcion, estado, dni, disponibilidad, telefono, correo) VALUES
(1, 'Dr. Ricardo Mendoza',    'Perito contable con 15 años de experiencia en auditoría forense',       'ACTIVO', '11223344', 1, '999111222', 'ricardo.mendoza@peritos.com'),
(2, 'Lic. Ana Torres',        'Psicóloga forense especializada en casos de familia y menores',         'ACTIVO', '22334455', 1, '999333444', 'ana.torres@psicologos.com'),
(3, 'Ing. Pedro Sánchez',     'Ingeniero informático para peritajes digitales y análisis de datos',   'ACTIVO', '33445566', 0, '999555666', 'pedro.sanchez@ingenieros.com');

-- ============================================================
-- FIN DEL SCRIPT - DATOS INSERTADOS CORRECTAMENTE
-- ============================================================
