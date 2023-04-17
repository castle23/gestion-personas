INSERT INTO pais (id, nombre) VALUES
  (NEXT VALUE FOR  seq_pais, 'Alemania'),
  (NEXT VALUE FOR  seq_pais, 'Argentina'),
  (NEXT VALUE FOR  seq_pais, 'Aruba'),
  (NEXT VALUE FOR  seq_pais, 'Australia'),
  (NEXT VALUE FOR  seq_pais, 'Brasil'),
  (NEXT VALUE FOR  seq_pais, 'Camerún'),
  (NEXT VALUE FOR  seq_pais, 'Canadá'),
  (NEXT VALUE FOR  seq_pais, 'Chile'),
  (NEXT VALUE FOR  seq_pais, 'China'),
  (NEXT VALUE FOR  seq_pais, 'Costa Rica'),
  (NEXT VALUE FOR  seq_pais, 'Dinamarca'),
  (NEXT VALUE FOR  seq_pais, 'España'),
  (NEXT VALUE FOR  seq_pais, 'Estados Unidos'),
  (NEXT VALUE FOR  seq_pais, 'Francia'),
  (NEXT VALUE FOR  seq_pais, 'Grecia'),
  (NEXT VALUE FOR  seq_pais, 'Irlanda'),
  (NEXT VALUE FOR  seq_pais, 'Japón'),
  (NEXT VALUE FOR  seq_pais, 'Luxemburgo'),
  (NEXT VALUE FOR  seq_pais, 'Países Bajos, Holanda'),
  (NEXT VALUE FOR  seq_pais, 'Tailandia'),
  (NEXT VALUE FOR  seq_pais, 'Taiwan'),
  (NEXT VALUE FOR  seq_pais, 'Venezuela')
;
INSERT INTO persona(id, nombre, apellido, fecha_nacimiento, mail, numero_documento, telefono, tipo_documento, id_padre, id_pais)
	VALUES (NEXT VALUE FOR  seq_persona, 'prueba', 'prueba', '1990-01-01', 'mail@mail.com', '11111111', '11111111', 'DNI', null, 1)
	;