INSERT INTO cliente (id,ci,direccion,email,nacionalidad,nombre_completo,patente,telefono) VALUES(1,'11111111-1','Carrera 12','pedro@correo.com', 'Chileno','Pedro Dominguez','AABB00',923478541);
INSERT INTO cliente (id,ci,direccion,email,nacionalidad,nombre_completo,patente,telefono) VALUES(2,'22222222-2','Av.Argentina 10','natalia@correo.com','Chilena','Natalia Marín','BBJJ90',540834098);
INSERT INTO cliente (id,ci,direccion,email,nacionalidad,nombre_completo,patente,telefono) VALUES(3,'33333333-3','Buenos Aires','diego@correo.com','Argentino','Diego DePaul','CAA123',950285937);
INSERT INTO cliente (id,ci,direccion,email,nacionalidad,nombre_completo,patente,telefono) VALUES(4,'44444444-4','Rio de Janiero','clima@correo.com','Brasileña','Clarisse Lima','SDF2A45',560929714);
INSERT INTO cliente (id,ci,direccion,email,nacionalidad,nombre_completo,patente,telefono) VALUES(5,'55555555-5','Av.Brasil 134','juan@correo.com','Chileno','Juan Horton','CCNG74',926788456);
INSERT INTO cliente (id,ci,direccion,email,nacionalidad,nombre_completo,patente,telefono) VALUES(6,'66666666-6','Itihue 34','maria@correo.com','Chilena','Maria Lopez','FDGT00',883455670);
INSERT INTO cliente (id,ci,direccion,email,nacionalidad,nombre_completo,patente,telefono) VALUES(7,'77777777-7','Paris','jean@correo.com','Frances','Jean Baptiste','BVJH43',775993498);
INSERT INTO cliente (id,ci,direccion,email,nacionalidad,nombre_completo,patente,telefono) VALUES(8,'88888888-8','Vicuña Mackenna 123','nicole@correo.com','Chilena','Nicole Lama','FRPO47',774430091);
INSERT INTO cliente (id,ci,direccion,email,nacionalidad,nombre_completo,patente,telefono) VALUES(9,'99999999-9','Roble 125','ignacio@correo.com','Chileno','Ignacio Castaño','JCFS09',889934556);
INSERT INTO cliente (id,ci,direccion,email,nacionalidad,nombre_completo,patente,telefono) VALUES(10,'12222222-2','Las Camelias 125','labra@correo.com','Chilena','Francisca Labra','XXPL11',991144567);
INSERT INTO cliente (id,ci,direccion,email,nacionalidad,nombre_completo,patente,telefono) VALUES(11,'13333333-3','El Carmen 2','alexis@correo.com','Chileno','Alexis Gutierrez','BZLM91',796455539);
INSERT INTO cliente (id,ci,direccion,email,nacionalidad,nombre_completo,patente,telefono) VALUES(12,'14444444-4','Londres','bjones@correo.com','Inglesa','Brooke Jones','SSLL30',986774567);
INSERT INTO cliente (id,ci,direccion,email,nacionalidad,nombre_completo,patente,telefono) VALUES(13,'15555555-5','New York','andy@correo.com','Estadounidense','Andy Brown','FFNS00',978863356);
INSERT INTO cliente (id,ci,direccion,email,nacionalidad,nombre_completo,patente,telefono) VALUES(14,'16666666-6','Quito','karla@correo.com','Ecuatoriana','Karla Zambrano','BGT2398',944345634);
INSERT INTO cliente (id,ci,direccion,email,nacionalidad,nombre_completo,patente,telefono) VALUES(15,'17777777-7','Lima','luz@correo.com','Peruano','Luz Quispe','JAL098',987765362);



INSERT INTO reserva (`id`,`cantidad_habitaciones`,`check_in`,`check_out`,`descripcion`,`fecha`,`cliente_id`,`venta_id`) VALUES (1,3,'2019-09-17','2019-09-25','gadgagd','2019-09-24',2,NULL);
INSERT INTO reserva (`id`,`cantidad_habitaciones`,`check_in`,`check_out`,`descripcion`,`fecha`,`cliente_id`,`venta_id`) VALUES (2,1,'2019-09-24','2019-09-25','sin estacionamiento','2019-09-24',1,NULL);
INSERT INTO reserva (`id`,`cantidad_habitaciones`,`check_in`,`check_out`,`descripcion`,`fecha`,``cliente_id`,`venta_id`) VALUES (3,1,'2019-09-25','2019-09-26','con estacionamiento','2019-09-24',1,NULL);
INSERT INTO reserva (`id`,`cantidad_habitaciones`,`check_in`,`check_out`,`descripcion`,`fecha`,`cliente_id`,`venta_id`) VALUES (4,1,'2019-09-26','2019-09-27','desayuno vegano','2019-09-24',3,NULL);
INSERT INTO reserva (`id`,`cantidad_habitaciones`,`check_in`,`check_out`,`descripcion`,`fecha`,`cliente_id`,`venta_id`) VALUES (5,1,'2019-09-27','2019-09-28','llegada a las 14:00','2019-09-24',4,NULL);
INSERT INTO reserva (`id`,`cantidad_habitaciones`,`check_in`,`check_out`,`descripcion`,`fecha`,`cliente_id`,`venta_id`) VALUES (6,1,'2019-09-28','2019-09-29','llegada a las 15:00','2019-09-24',2,NULL);
INSERT INTO reserva (`id`,`cantidad_habitaciones`,`check_in`,`check_out`,`descripcion`,`fecha`,`cliente_id`,`venta_id`) VALUES (7,1,'2019-09-29','2019-09-30','llegada a las 16:00','2019-09-24',3,NULL);
INSERT INTO reserva (`id`,`cantidad_habitaciones`,`check_in`,`check_out`,`descripcion`,`fecha`,`cliente_id`,`venta_id`) VALUES (8,1,'2019-09-29','2019-09-30','llegada a las 17:00','2019-09-24',4,NULL);
INSERT INTO reserva (`id`,`cantidad_habitaciones`,`check_in`,`check_out`,`descripcion`,`fecha`,`cliente_id`,`venta_id`) VALUES (9,1,'2019-09-29','2019-09-30','llegada a las 17:00','2019-09-24',1,NULL);
INSERT INTO reserva (`id`,`cantidad_habitaciones`,`check_in`,`check_out`,`descripcion`,`fecha`,`cliente_id`,`venta_id`) VALUES (10,1,'2019-09-28','2019-09-29','llegada a las 16:00','2019-09-24',1,NULL);
INSERT INTO reserva (`id`,`cantidad_habitaciones`,`check_in`,`check_out`,`descripcion`,`fecha`,`cliente_id`,`venta_id`) VALUES (11,1,'2019-09-27','2019-09-28','llegada a las 16:00','2019-09-24',2,NULL);



INSERT INTO tipo_habitacion (`id`,`nombre`, `precio`) VALUES (1,'Single',39000);
INSERT INTO tipo_habitacion (`id`,`nombre`, `precio`) VALUES (2,'Doble',45000);
INSERT INTO tipo_habitacion (`id`,`nombre`, `precio`) VALUES (3,'Matrimonial',45000);
INSERT INTO tipo_habitacion (`id`,`nombre`, `precio`) VALUES (4,'Triple',59000);
INSERT INTO tipo_habitacion (`id`,`nombre`, `precio`) VALUES (5,'Matrimonial+ 1 Cama',59000);
INSERT INTO tipo_habitacion (`id`,`nombre`, `precio`) VALUES (6,'Cuadruple',66000);
INSERT INTO tipo_habitacion (`id`,`nombre`, `precio`) VALUES (7,'Matrimonial + 2 Camas',66000);
INSERT INTO tipo_habitacion (`id`,`nombre`, `precio`) VALUES (8,'Suite',66000);


INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (1,1,5);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES(2,2,3);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES(3,3,1);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES(4,4,6);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (5,5,2);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (6,6,3);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES(7,7,3);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES(8,8,2);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES(9,9,4);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES(10,10,5);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (11,11,1);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (12,12,4);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (13,14,2);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (14,21,3);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (15,22,1);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (16,23,1);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (17,24,1);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (18,25,1);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (19,26,1);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (20,27,2);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (21,28,1);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (22,29,3);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (23,30,1);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (24,31,3);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (25,32,5);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (26,33,3);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (27,34,2);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (28,35,8);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (29,41,3);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (30,42,7);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (31,43,3);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (32,44,2);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (33,45,7);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (34,51,5);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (35,52,7);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (36,53,3);
INSERT INTO `habitacion` (`id`,`numero`,`tipo_habitacion_id`) VALUES (37,54,3);

