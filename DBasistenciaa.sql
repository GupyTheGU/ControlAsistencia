DROP DATABASE ControlAsistencia;
CREATE DATABASE ControlAsistencia;
USE ControlAsistencia;

CREATE TABLE administrador(
	usuario	VARCHAR(30) NOT NULL,
	contra	VARCHAR(30) NOT NULL,
	PRIMARY KEY(usuario,contra)
);

CREATE TABLE empleados(
  idEmp		INT UNSIGNED NOT NULL,
  idDatos	INT UNSIGNED NOT NULL,
  idHorario	VARCHAR(30) DEFAULT NULL,
  PRIMARY KEY (idEmp)
);

CREATE TABLE detalleEmpleado(
	idDatos	INT UNSIGNED NOT NULL,
	Nombre	VARCHAR(35) NOT NULL,
	ApeUno	VARCHAR(35) NOT NULL,
	ApeDos	VARCHAR(35) DEFAULT NULL,
	Curp		VARCHAR(18) NOT NULL,
	FechaIn	DATE NOT NULL,
	Calle		VARCHAR(35) NOT NULL,
	Colonia 	VARCHAR(35) NOT NULL,
	Estado	DECIMAL(2,0) NOT NULL,
	Municip	DECIMAL(3,0) NOT NULL,
	NoExt		VARCHAR(4) NOT NULL,
	NoInt		VARCHAR(4) DEFAULT NULL,
	Cpostal	DECIMAL(5,0) NOT NULL,
	DStatus	CHAR(1) NOT NULL,
	PRIMARY KEY (idDatos)
);

CREATE TABLE horario(
	idHorario VARCHAR(30) NOT NULL,
	PRIMARY KEY(idHorario)
);

CREATE TABLE relHorarioJornada(
	idRel		 INT UNSIGNED AUTO_INCREMENT,
	idHorario VARCHAR(30) NOT NULL,
	idJornada INT UNSIGNED NOT NULL,
	PRIMARY KEY(idRel) 
);

CREATE TABLE jornada(
	idJornada INT UNSIGNED AUTO_INCREMENT,
	idDia		 DECIMAL(1,0) NOT NULL,
	horaIn	VARCHAR(5) NOT NULL,
	horaOut	VARCHAR(5) NOT NULL,
	PRIMARY KEY (idJornada)
);

CREATE TABLE dia(
	idDia		DECIMAL(1,0) NOT NULL,
	diaDesc	VARCHAR(9)	NOT NULL,
	PRIMARY KEY (idDia)
);

CREATE TABLE incidencias(
	idInci		DECIMAL(1,0) NOT NULL,
	descr			VARCHAR(14) NOT NULL,
	PRIMARY KEY(idInci)
);

CREATE TABLE tipoinOut(
	tipoES	DECIMAL(1,0) NOT NULL,
	descr		VARCHAR(8) NOT NULL,
	PRIMARY KEY(tipoES)
);
#DROP TABLE asistencias
CREATE TABLE asistencias(
	idAsist		INT UNSIGNED AUTO_INCREMENT,
	idEmp			INT UNSIGNED NOT NULL,
	FechaAsis	DATE NOT NULL,
	horaReg		VARCHAR(8) NOT NULL, 
	tipoES		DECIMAL(1,0) NOT NULL,
	idInci		DECIMAL(1,0) DEFAULT NULL,
	NoBiom		INT UNSIGNED NOT NULL,
	PRIMARY KEY (idAsist),
	FOREIGN KEY (idEmp) REFERENCES empleados(idEmp) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (idInci) REFERENCES incidencias(idInci) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (tipoES) REFERENCES tipoinOut(tipoES)ON DELETE CASCADE ON UPDATE CASCADE
);



## Creacion de FK detalleEmpleado -> empleado;
alter table empleados
	add foreign key(idDatos)
	references detalleEmpleado(idDatos) ON DELETE CASCADE ON UPDATE CASCADE;
	#DESCRIBE rel_ProfAlmn;

## Creacion de FK horario -> empleado;
alter table empleados
	add foreign key(idHorario)
	REFERENCES horario(idHorario) ON DELETE SET NULL ON UPDATE CASCADE;
	#DESCRIBE rel_ProfAlmn;
	
## Creacion de FK empleado -> asistencias;
alter table asistencias
	add foreign key(idEmp)
	references empleados(idEmp) ON DELETE CASCADE ON UPDATE CASCADE;
	#DESCRIBE rel_ProfAlmn;

alter TABLE relhorariojornada
	add foreign key(idHorario)
	references horario(idHorario) ON DELETE CASCADE ON UPDATE CASCADE;
	#DESCRIBE rel_ProfAlmn;

alter TABLE relhorariojornada
	add foreign key(idJornada)
	references jornada(idJornada) ON DELETE CASCADE ON UPDATE CASCADE;
	#DESCRIBE rel_ProfAlmn;

ALTER TABLE jornada
	ADD FOREIGN KEY(idDia)
	REFERENCES dia(idDia) ON DELETE CASCADE ON UPDATE CASCADE;
	
INSERT INTO dia (idDia,diaDesc) VALUES (1,"LUNES");
INSERT INTO dia (idDia,diaDesc) VALUES (2,"MARTES");
INSERT INTO dia (idDia,diaDesc) VALUES (3,"MIERCOLES");
INSERT INTO dia (idDia,diaDesc) VALUES (4,"JUEVES");
INSERT INTO dia (idDia,diaDesc) VALUES (5,"VIERNES");
INSERT INTO dia (idDia,diaDesc) VALUES (6,"SÁBADO");
INSERT INTO dia (idDia,diaDesc) VALUES (7,"DOMINGO"); 

INSERT INTO incidencias(idInci,descr) VALUES (0,"EN HORARIO");
INSERT INTO incidencias(idInci,descr) VALUES (1,"RETARDO MENOR");
INSERT INTO incidencias(idInci,descr) VALUES (2,"RETARDO MAYOR");
INSERT INTO incidencias(idInci,descr) VALUES (3,"INASISTENCIA");

INSERT INTO tipoinout(tipoES,descr)VALUES(1,"ENTRADA");
INSERT INTO tipoinout(tipoES,descr)VALUES(2,"SALIDA");

INSERT INTO administrador(usuario,contra)VALUES("admin","admin");
#SELECT * FROM empleados INNER JOIN detalleEmpleado WHERE detalleEmpleado.idDatos = empleados.idDatos;
############################################################################################################################
## para resultados   0-no existe | -1-existe_ej_exito | -2-existe pero no se puede ejecutar

#drop procedure if exists sp_registraEmpleado;
delimiter $$
create procedure sp_registraEmpleado(IN num INT UNSIGNED,IN nomb VARCHAR(35),IN materno VARCHAR(35),IN paterno VARCHAR(35),IN rfc VARCHAR(18),IN fecha DATE, IN street VARCHAR(35),IN col VARCHAR(35),IN edo DECIMAL(2,0),IN mun DECIMAL(3,0),IN exterior VARCHAR(4),IN interior VARCHAR(4),IN postal DECIMAL(5,0),IN estatus CHAR(1) )
begin
	declare idCont 	int UNSIGNED DEFAULT 0;
   declare existe		int default 0;
   DECLARE idinserta INT UNSIGNED DEFAULT 0;
    
   set idCont =(select count(*)from empleados WHERE idEmp = num);
    
	if idCont = 0 then
		set idCont=(select ifnull(MAX(idDatos),0)+1 from detalleEmpleado);
		INSERT INTO detalleEmpleado (idDatos,Nombre,ApeUno,ApeDos,Curp,FechaIn,Calle,Colonia,Estado,Municip,NoExt,NoInt,Cpostal,DStatus) VALUES (idCont,nomb,materno,paterno,rfc,fecha,street,col,edo,mun,exterior,interior,postal,estatus);
		INSERT INTO empleados (idEmp,idDatos) VALUES (num,idCont);
		SET existe = 0;
		SELECT existe;
	else
		SET existe = -2;
		SELECT existe;
    end if;
end$$
delimiter ;

#drop procedure if exists sp_consultaEmpleado;
delimiter $$
create procedure sp_consultaEmpleado(IN num INT UNSIGNED)
begin
	declare idCont 		int default 0;
   declare existe		VARCHAR(30);

   set idCont =(select count(*)from empleados WHERE idEmp = num);
    
	if idCont = 1 then
		SET existe = (select ifnull(empleados.idHorario,-10) from empleados WHERE idEmp = num);
		SELECT existe,empleados.idEmp,detalleEmpleado.Nombre,detalleEmpleado.ApeUno,detalleEmpleado.ApeDos,detalleEmpleado.Curp,detalleEmpleado.FechaIn,
				 					  detalleEmpleado.Calle,detalleEmpleado.Colonia,detalleEmpleado.Estado,detalleEmpleado.Municip,detalleEmpleado.NoExt,
									  detalleEmpleado.NoInt,detalleEmpleado.Cpostal,detalleEmpleado.DStatus FROM empleados INNER JOIN detalleEmpleado WHERE empleados.idDatos = detalleEmpleado.idDatos AND empleados.idEmp = num;
	else
		SET idCont = 0;
		SELECT idCont;
    end if;
end$$
delimiter ;

#drop procedure if sp_registraHorario;
delimiter $$
create procedure sp_registraHorario(IN nombre VARCHAR(30))
begin
	declare idCont 		int default 0;
   declare existe		int default 0;

   set idCont =(select count(*)FROM horario WHERE idHorario = nombre);

	if idCont = 0 then
		INSERT INTO horario (idHorario)VALUES(nombre);
		SET existe = 0;
		SELECT existe;
	else
		SET existe = -2;
		SELECT existe;
    end if;
end$$

#drop procedure if exists sp_registrarJornada;
delimiter $$
CREATE PROCEDURE sp_registrarJornada(IN diaSemana DECIMAL(1,0),IN entrada VARCHAR(5),IN salida VARCHAR(5), OUT outJornada INT UNSIGNED)
BEGIN
	DECLARE idCont		INT UNSIGNED DEFAULT 0;
	DECLARE existe 	INT DEFAULT 0;
	DECLARE kjornada  INT UNSIGNED DEFAULT 0;
	
	SET existe = (SELECT COUNT(idJornada) FROM jornada WHERE idDia = diaSemana AND horaIn = entrada AND horaOut = salida);
	if existe = 0 then
		set idCont = (select ifnull(MAX(idJornada),0)+1 from jornada);
		INSERT INTO jornada (idJornada,idDia,horaIn,horaOut) VALUES (idCont,diaSemana,entrada,salida);
		set outJornada = idCont;
		SELECT @outJornada;
	else
		SET kJornada = (SELECT idJornada FROM jornada WHERE idDia = diaSemana AND horaIn = entrada AND horaOut = salida);
		set outJornada = kJornada;
		SELECT @outJornada;
	END if;
end$$
delimiter ;


#drop procedure if exists sp_removerJornada;
delimiter $$
CREATE PROCEDURE sp_removerJornada(IN nombre VARCHAR(30),IN diaSemana DECIMAL(1,0))
BEGIN
	DECLARE idCont		INT UNSIGNED DEFAULT 0;
	DECLARE existe 	INT DEFAULT 0;
	DECLARE kjornada  INT UNSIGNED DEFAULT 0;
	
	SET idCont = (SELECT jornada.idJornada FROM jornada INNER JOIN relhorariojornada WHERE jornada.idJornada = relhorariojornada.idJornada AND jornada.idDia = diaSemana AND relhorariojornada.idHorario = nombre);
	SET existe = (SELECT COUNT(*) FROM relhorariojornada WHERE idJornada = idCont);
	if existe > 1 then
		DELETE FROM relhorariojornada WHERE idHorario = nombre AND idJornada = idCont;	
	else
		DELETE FROM jornada WHERE idJornada = idCont;
	END if;
end$$
delimiter ;

#drop procedure if exists sp_modificaHorario;
delimiter $$
create procedure sp_modificaHorario(IN nombre VARCHAR(30),IN diaSemana DECIMAL(1,0),IN entrada VARCHAR(5),IN salida VARCHAR(5))
begin
	DECLARE idCont 		int default 0;
   DECLARE existe		int default 0;
   DECLARE kJornada	INT UNSIGNED DEFAULT 0;
   
   SET existe = (SELECT COUNT(*) FROM relhorariojornada inner join jornada WHERE relhorariojornada.idHorario = nombre AND jornada.idDia = diaSemana AND jornada.horaIn = entrada AND jornada.horaOut = salida);
   
   if existe = 0 then
   	SET existe = (SELECT COUNT(relhorariojornada.idJornada) FROM relhorariojornada INNER JOIN jornada WHERE relhorariojornada.idJornada = jornada.idJornada AND jornada.idDia = diaSemana AND relhorariojornada.idHorario = nombre);
		if existe = 0 then
			CALL sp_registrarJornada(diaSemana,entrada,salida,@outJornada);
			INSERT INTO relhorariojornada (idHorario,idJornada) VALUES (nombre,@outJornada);
		ELSE
			SET kJornada = (SELECT relhorariojornada.idJornada FROM relhorariojornada INNER JOIN jornada WHERE relhorariojornada.idJornada = jornada.idJornada AND jornada.idDia = diaSemana AND relhorariojornada.idHorario = nombre);
			SET idCont = (SELECT COUNT(*) FROM relhorariojornada WHERE idJornada = kJornada);
			if idCont = 1 then
				DELETE FROM jornada WHERE idJornada = kJornada;
			else
				DELETE FROM relhorariojornada WHERE idHorario = nombre AND idJornada = kJornada;
			END if;
			CALL sp_registrarJornada(diaSemana,entrada,salida,@outJornada);
			INSERT INTO relhorariojornada (idHorario,idJornada) VALUES (nombre,@outJornada);
		END if;
	ELSE	
		SELECT 0;
	END if;
end$$
delimiter ;

#drop procedure if exists sp_getHorario;
delimiter $$
CREATE PROCEDURE sp_getHorario(IN nombre VARCHAR(30))
BEGIN
	SELECT jornada.idDia,jornada.horaIn,jornada.horaOut 
		FROM relhorariojornada INNER JOIN jornada WHERE relhorariojornada.idJornada = jornada.idJornada 
		AND relhorariojornada.idHorario = nombre ORDER BY idDia;
END$$
delimiter ;

#drop procedure if exists sp_modificarEmpleado;
delimiter $$
CREATE PROCEDURE sp_modificarEmpleado(IN num INT UNSIGNED,IN nomb VARCHAR(35),IN materno VARCHAR(35),IN paterno VARCHAR(35),IN rfc VARCHAR(18),IN fecha DATE, IN street VARCHAR(35),IN col VARCHAR(35),IN edo DECIMAL(2,0),IN mun DECIMAL(3,0),IN exterior VARCHAR(4),IN interior VARCHAR(4),IN postal DECIMAL(5,0),IN estatus CHAR(1) )
BEGIN
	DECLARE idDetalles INT UNSIGNED DEFAULT 0;
	SET idDetalles = (SELECT idDatos FROM empleados WHERE empleados.idEmp = num);
	UPDATE detalleempleado SET Nombre =nomb, ApeUno = materno, ApeDos = paterno, Curp = rfc, FechaIn = fecha, Calle = street, Colonia = col, Estado = edo, Municip = mun, NoExt = exterior, NoInt = interior, Cpostal = postal, DStatus = estatus WHERE idDatos = idDetalles;
	SELECT 2;
END$$
delimiter ;

#drop procedure if exists sp_asignarHorario;
delimiter $$
CREATE PROCEDURE sp_asignarHorario(IN num INT UNSIGNED,IN nombre VARCHAR(30))
BEGIN
	UPDATE empleados SET idHorario=nombre WHERE idEmp = num;
END$$
delimiter ;

#drop procedure if exists sp_removerHorario;
delimiter $$
CREATE PROCEDURE sp_removerHorario(IN num INT UNSIGNED)
BEGIN
	UPDATE empleados SET idHorario=NULL WHERE idEmp = num;
END$$
delimiter ;

#drop procedure if exists sp_eliminarEmpleado;
delimiter $$
CREATE PROCEDURE sp_eliminarEmpleado (IN num INT UNSIGNED)
BEGIN
	DELETE FROM empleados WHERE idEmp = num;
END$$
delimiter ;

#drop procedure if exists sp_eliminarHorario;
delimiter $$
CREATE PROCEDURE sp_eliminarHorario (IN nombre VARCHAR(30))
BEGIN
	DELETE FROM horario WHERE horario.idHorario = nombre;
END$$
delimiter ;

#drop procedure if exists sp_altaAsistencia ;
delimiter $$
CREATE PROCEDURE sp_altaAsistencia (IN clave INT UNSIGNED, in fecha DATE, in hora VARCHAR(8), in ES DECIMAL(1,0), in inci DECIMAL(1,0),IN lector INT UNSIGNED)
BEGIN
	DECLARE existe INT UNSIGNED DEFAULT 0;
	SET existe = (SELECT COUNT(*) FROM asistencias WHERE idEmp=clave AND FechaAsis=fecha AND horaReg = hora AND tipoES = ES AND idInci = inci AND NoBiom = lector);
	if existe = 0 then
		INSERT INTO asistencias (idEmp,FechaAsis,horaReg,tipoES,idInci,NoBiom) VALUES (clave,fecha,hora,ES,inci,lector);
	END if;
END$$
delimiter ;

#drop procedure if exists sp_conAsistenciasFecha;
delimiter $$
CREATE PROCEDURE sp_conAsistenciasFecha (IN clave INT UNSIGNED, in fecha DATE)
BEGIN
	
	SELECT asistencias.FechaAsis,asistencias.horaReg,tipoinout.descr,incidencias.descr, asistencias.NoBiom FROM incidencias INNER JOIN  asistencias INNER JOIN tipoinout 
			WHERE incidencias.idInci = asistencias.idInci AND tipoinout.tipoES = asistencias.tipoES AND asistencias.idEmp = clave AND asistencias.FechaAsis=fecha;
END$$
delimiter ;

#drop procedure if exists sp_conIncidencias;
delimiter $$
CREATE PROCEDURE sp_conIncidencias (IN clave INT UNSIGNED)
BEGIN
	
	SELECT asistencias.FechaAsis,asistencias.horaReg,tipoinout.descr,incidencias.descr, asistencias.NoBiom FROM incidencias INNER JOIN  asistencias INNER JOIN tipoinout 
			WHERE incidencias.idInci = asistencias.idInci AND tipoinout.tipoES = asistencias.tipoES AND asistencias.idEmp = clave AND asistencias.idInci > 0;
END$$
delimiter ;

#DROP VIEW if EXISTS getNumeroHorarios;
USE controlasistencia;
CREATE VIEW getNumeroHorarios AS (SELECT COUNT(*) FROM horario);

#drop procedure if exists sp_iniciaSesion;
delimiter $$
CREATE PROCEDURE sp_iniciaSesion (IN usr VARCHAR(30), IN passw VARCHAR(30))
BEGIN
		DECLARE existe INT DEFAULT 0;
		SET existe = (SELECT COUNT(*) FROM administrador WHERE administrador.usuario=usr AND administrador.contra=passw);
		SELECT existe;
END$$
delimiter ;

CALL sp_registraHorario("Matutino 2A");
CALL sp_modificaHorario("Matutino 2A", 1, "12:07", "15:24");
CALL sp_modificaHorario("Matutino 2A", 3, "08:51", "13:24");
CALL sp_modificaHorario("Matutino 2A", 5, "08:51", "13:24");
#_____________________________________________________________________________view
