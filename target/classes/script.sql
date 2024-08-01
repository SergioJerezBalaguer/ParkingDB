
select * from COCHES
select * from COCHES where MARCA  = 'hyundai';

insert into COCHES values ('9999PPP', 'ferrari' , '413' ,current_time , null );
insert into COCHES values ('9998PPP', 'ferrari' , '413' ,current_time , null );
select * from COCHES

delete from COCHES where MATRICULA = '2323';

select * from COCHES

UPDATE Coches
SET Modelo = 'cooper'
WHERE Matricula = '7912LDR';

select * from COCHES

SELECT * FROM Coches WHERE Matricula LIKE '%4%';

SELECT * FROM Coches WHERE HoraEntrada > '2024-06-29';


