-- ============================================================
-- SEED DATA RECIENTE — Bufete Miguel Rojas (sin acentos)
-- Fecha referencial: 30/06/2026
-- ============================================================

INSERT INTO cliente (nombre, descripcion, dni, ruc, telefono, correo, estado, tipo_cliente)
VALUES
('Inversiones Lima Norte SAC', 'Empresa de inversiones inmobiliarias', '76543210', '20567890123', '999888777', 'contacto@inversioneslima.com', 1, 'Juridico'),
('Maria Fernanda Torres', 'Cliente particular para divorcio', '33445566', NULL, '987654321', 'mftorres@email.com', 1, 'Natural'),
('TechSolutions Peru', 'Startup de tecnologia', '88776655', '20678901234', '956789123', 'legal@techsolutions.pe', 1, 'Juridico');

INSERT INTO caso (titulo, descripcion, estado, id_abogado, id_cliente)
VALUES
('Asesoria legal para inversion inmobiliaria', 'Revision de contratos de compraventa para proyecto habitacional en San Miguel', 1, 1, 4),
('Proceso de divorcio de mutuo acuerdo', 'Separacion de bienes y custodia compartida de menores', 1, 2, 5),
('Registro de marca y propiedad intelectual', 'Solicitud de registro de marca ante INDECOPI para plataforma SaaS', 1, 1, 6),
('Defensa por incumplimiento de contrato', 'Demanda por incumplimiento de contrato de arrendamiento financiero', 0, 2, 1);

INSERT INTO cita (asunto_legal, detalles_adicionales, fecha_hora, activa, id_cliente, id_abogado)
VALUES
('Revision de contrato inversion', 'Entregar borrador de minuta para revision', '2026-07-01 09:00:00', 1, 4, 1),
('Primera consulta divorcio', 'Traer partida de matrimonio y DNI', '2026-07-01 11:30:00', 1, 5, 2),
('Registro de marca seguimiento', 'Revisar respuesta de INDECOPI', '2026-07-02 10:00:00', 1, 6, 1),
('Firma de contrato arrendamiento', 'Reunion presencial para firmar contrato final', '2026-07-03 15:00:00', 1, 1, 2),
('Seguimiento caso estafa', 'Revisar avances de la investigacion fiscal', '2026-07-05 08:30:00', 1, 1, 1),
('Consulta express cobro de deuda', 'Revisar documento de cobranza prejudicial', '2026-07-07 14:00:00', 1, 3, 2),
('Reunion junta directiva TechSolutions', 'Asesoria legal mensual con directorio', '2026-07-10 11:00:00', 1, 6, 1),
('Audiencia preparatoria recordatorio', 'Ultimar detalles de defensa antes de audiencia', '2026-07-12 16:00:00', 0, 1, 2),
('Cita cancelada reprogramar', 'Cliente solicito cancelacion por viaje', '2026-06-28 10:00:00', 0, 2, 1);

INSERT INTO pago (metodo_pago, estado_pago, monto, fecha_pago, id_caso)
VALUES
('Transferencia Bancaria', 1, 3000.00, '2026-06-18 10:30:00', 3),
('Tarjeta de Credito', 1, 1500.00, '2026-06-20 14:00:00', 6),
('Efectivo', 1, 800.00, '2026-06-22 09:15:00', 2),
('Deposito en Cuenta', 1, 5000.00, '2026-06-25 11:45:00', 4),
('Transferencia Bancaria', 1, 2500.00, '2026-06-28 16:20:00', 7),
('Yape', 1, 350.00, '2026-06-29 08:00:00', 5),
('Plin', 0, 4200.00, '2026-06-30 07:30:00', 8);

INSERT INTO expediente (titulo, tipo_expediente, resumen_expediente, fecha_inicio, fecha_cierre, estado_expediente, id_caso)
VALUES
('EXP-2026-001 Inversion Inmobiliaria', 'Notarial', 'Expediente de asesoria para inversion inmobiliaria en San Miguel', '2026-07-01', NULL, 1, 6),
('EXP-2026-002 Divorcio Mutuo Acuerdo', 'Judicial', 'Expediente de divorcio de mutuo acuerdo con separacion de bienes', '2026-07-01', NULL, 1, 7),
('EXP-2026-003 Registro de Marca', 'Administrativo', 'Solicitud de registro de marca TechSolutions ante INDECOPI', '2026-06-20', NULL, 1, 8);

INSERT INTO audiencia (fecha, direccion, hora, tipo_audiencia, lugar_link, id_abogado, id_caso)
VALUES
('2026-07-08', 'Corte Superior de Lima Sede Central', '2026-07-08 09:00:00', 'Audiencia de Conciliacion', 'Sala 105 Piso 1', 1, 6),
('2026-07-15', 'Plataforma Virtual', '2026-07-15 10:30:00', 'Audiencia Virtual', 'https://meet.estudio.com/audiencia-divorcio', 2, 7),
('2026-07-22', 'Corte Superior de Lima Sede Norte', '2026-07-22 11:00:00', 'Audiencia de Pruebas', 'Sala 204 Piso 2', 1, 8),
('2026-08-05', 'Tribunal Fiscal Sede Central', '2026-08-05 09:30:00', 'Audiencia Tributaria', 'Piso 5 Sala 502', 2, 4);

INSERT INTO notificacion (titulo, mensaje, leido, fecha, id_usuario)
VALUES
('Nueva cita agendada', 'Se agendo una cita con Inversiones Lima Norte para manana 01/07 a las 09:00.', 0, '2026-06-30 08:00:00', 2),
('Pago recibido', 'Se registro un pago de S/ 4,200.00 por el caso Defensa por Incumplimiento de Contrato.', 0, '2026-06-30 07:35:00', 2),
('Recordatorio de audiencia', 'Recordatorio: Audiencia de Conciliacion del caso Asesoria Inversion Inmobiliaria el 08/07 a las 09:00.', 0, '2026-06-29 16:00:00', 2),
('Caso cerrado', 'El caso Demanda por Despido Injustificado fue cerrado exitosamente.', 1, '2026-06-25 14:30:00', 3),
('Documento subido', 'Se adjunto la minuta de constitucion al expediente EXP-2026-001.', 0, '2026-06-28 11:20:00', 2),
('Audiencia programada', 'Se programo audiencia virtual para el caso de divorcio el 15/07 a las 10:30.', 0, '2026-06-27 09:00:00', 6),
('Pago atrasado', 'El caso Cobro de Deuda Civil tiene un pago pendiente de S/ 1,000.00 desde hace 15 dias.', 0, '2026-06-26 08:00:00', 2),
('Bienvenido', 'Se registro un nuevo cliente corporativo: TechSolutions Peru.', 1, '2026-06-20 10:00:00', 6);

INSERT INTO documentos_legales (nombre_archivo, ruta, fecha_creacion, id_expediente)
VALUES
('Minuta_Inversion_Inmobiliaria.pdf', '/documentos/exp006/minuta_inversion.pdf', '2026-07-01 09:30:00', 6),
('Contrato_Servicios_Divorcio.pdf', '/documentos/exp007/contrato_servicios.pdf', '2026-07-01 12:00:00', 7),
('Solicitud_Registro_Marca.pdf', '/documentos/exp008/solicitud_marca.pdf', '2026-06-20 15:00:00', 8),
('Poder_Especial_Representante.pdf', '/documentos/exp006/poder_especial.pdf', '2026-06-28 16:30:00', 6);

INSERT INTO abogado_area (id_abogado, id_area) VALUES (1, 4), (1, 5), (2, 4);
INSERT INTO abogado_servicio (id_abogado, id_servicio) VALUES (1, 4), (2, 2), (2, 3);
