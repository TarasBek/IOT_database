USE mydb;

drop trigger if exists post_update;

DELIMITER //
CREATE TRIGGER post_update
    BEFORE UPDATE
    on post
    for EACH ROW
BEGIN
    IF (new.id != old.id and old in (select id from post )) then
SIGNAL SQLSTATE '45000'
set MESSAGE_TEXT = 'no such data found';
end if;
end //
DELIMITER ;

drop trigger if exists post_delete;
DELIMITER //
CREATE TRIGGER post_delete
    before DELETE
    on post
    for EACH ROW
BEGIN
    if (old.id in (select post_id from employee)) then
signal sqlstate '45000'
 set message_text = 'there is at least one person with such data, you cannot delete it';
end if;
end //
 DELIMITER ;


-- EFFECT ZONE


drop trigger if exists effect_zone_update;
DELIMITER //
CREATE TRIGGER effect_zone_update
    before update
    on effect_zone
    for each row
BEGIN
    IF (new.id != old.id and old in (select id from effect_zone )) then
SIGNAL SQLSTATE '45000'
set MESSAGE_TEXT = 'no such data found';
end if;
end //
DELIMITER ;

drop trigger if exists effect_zone_delete;
DELIMITER //
CREATE TRIGGER effect_zone_delete
    before  DELETE
    on effect_zone
    for each row
BEGIN
    if (old.id in (select effect_zone_id from medecine_zone)) then
signal sqlstate '45000'
 set message_text = 'there is at least one person with such data, you cannot delete it';
end if;
end //
 DELIMITER ;

-- STREET
drop trigger if exists street_update;
DELIMITER //
CREATE TRIGGER  street_update
    before update
    on street
    for each row
BEGIN
    IF (new.id != old.id and old in (select id from street )) then
SIGNAL SQLSTATE '45000'
set MESSAGE_TEXT = 'no such data found';
end if;
end //
DELIMITER ;

drop trigger if exists street_delete;
DELIMITER //
CREATE TRIGGER  street_delete
    before  DELETE
    on street
    for each row
BEGIN
    if (old.id in (select street_id from pharmacy)) then
signal sqlstate '45000'
 set message_text = 'there is at least one person with such data, you cannot delete it';
end if;
end //
 DELIMITER ;


-- MEDeCINE LIST
drop trigger if exists  medecine_update;
DELIMITER //
CREATE TRIGGER medecine_update
    before update
    on medecinelist
    for each row
BEGIN
    IF (new.id != old.id and old in (select id from medecinelist ))  then
SIGNAL SQLSTATE '45000'
set MESSAGE_TEXT = 'no such data found';
end if;


end //
DELIMITER ;


drop trigger if exists medecine_delete;
DELIMITER //
CREATE TRIGGER  medecine_delete
    before  DELETE
    on medecinelist
    for each row
BEGIN
    if (old.id in (select medecine_id from medecine_zone )) then
signal sqlstate '45000'
 set message_text = 'there is at least one person with such data, you cannot delete it';
end if;
if (old.id in (select medecine_id from pharmacy_medecine )) then
signal sqlstate '45000'
 set message_text = 'there is at least one person with such data, you cannot delete it';
end if;
end //
 DELIMITER ;


-- Employee
drop trigger if exists employee_insert;

DELIMITER //
create trigger 	employee_insert
    before insert
    on employee
    for each row
begin
    if (new.post_id not in (select id from post)) then
			signal sqlstate '45000'
			set message_text = 'FK error. no such data found';
end if;
if (new.pharmacy_id not in (select id from pharmacy)) then
			signal sqlstate '45000'
			set message_text = 'FK error. no such data found';
end if;
end//
DELIMITER ;

drop trigger if exists employee_update;

DELIMITER //
create trigger employee_update
    before update
    on employee
    for each row
begin
    if (new.id != old.id and old in (select id from employee)) then
		signal sqlstate '45000'
        set message_text = 'no such data found';
end if;
end//
DELIMITER ;


-- Pharmacy

drop trigger if exists pharmacy_insert;

DELIMITER //
create trigger 	pharmacy_insert
    before insert
    on pharmacy
    for each row
begin
    if (new.street_id not in (select id from street)) then
			signal sqlstate '45000'
			set message_text = 'FK error. no such data found';
end if;

end//
DELIMITER ;
drop trigger if exists pharmacy_update;
DELIMITER //
create trigger pharmacy_update
    before update
    on pharmacy
    for each row
begin
    if (new.id != old.id and old in (select id from pharmacy)) then
		signal sqlstate '45000'
        set message_text = 'no such data found';
end if;
end//
DELIMITER ;

drop trigger if exists pharmacy_delete;
DELIMITER //
CREATE TRIGGER  pharmacy_delete
    before  DELETE
    on pharmacy
    for each row
BEGIN
    if (old.id in (select pharmacy_id from pharmacy_medecine )) then
signal sqlstate '45000'
 set message_text = 'there is at least one person with such data, you cannot delete it';
end if;
end //
 DELIMITER ;

-- Medecine ZONE

drop trigger if exists medecine_zone_insert;
DELIMITER //
CREATE TRIGGER  medecine_zone_insert
    before INSERT on medecine_zone
    for each row
begin
    if (new.medecine_id not in (select id from medecinelist)) then
		signal sqlstate '45000'
        set message_text = 'FK error. no such data found';
end if;
if (new.effect_zone_id not in (select id from medecine_zone)) then
		signal sqlstate '45000'
        set message_text = 'FK error. no such data found';
end if;
end
// DELIMITER ;

drop trigger if exists medecine_zone_update;
DELIMITER //
CREATE TRIGGER  medecine_zone_update
    before UPDATE on medecine_zone
    for each row
begin
    if (new.medecine_id != old.medecine_id and old.medecine_id in (select medecine_id from medecinelist)) then
		signal sqlstate '45000'
        set message_text = 'no such link in the linking table';
end if;
if (new.effect_zone_id != old.effect_zone_id and old.effect_zone_id in (select effect_zone_id from medecine_zone)) then
		signal sqlstate '45000'
        set message_text = 'no such link in the linking table';
end if;

end
// DELIMITER ;


-- pharmacy medecine

drop trigger if exists medecine_zone_insert;
DELIMITER //
CREATE TRIGGER  medecine_zone_insert
before INSERT on medecine_zone 
for each row 
begin
	if (new.medecine_id not in (select id from medecinelist)) then
		signal sqlstate '45000'
        set message_text = 'FK error. no such data found';
	end if;
    if (new.effect_zone_id not in (select id from medecine_zone)) then
		signal sqlstate '45000'
        set message_text = 'FK error. no such data found';
	end if;
end
// DELIMITER ;

drop trigger if exists medecine_zone_update;
DELIMITER //
CREATE TRIGGER  medecine_zone_update
before UPDATE on medecine_zone 
for each row 
begin
if (new.medecine_id != old.medecine_id and old.medecine_id in (select medecine_id from medecinelist)) then
		signal sqlstate '45000'
        set message_text = 'no such link in the linking table';
	end if;
    if (new.effect_zone_id != old.effect_zone_id and old.effect_zone_id in (select effect_zone_id from medecine_zone)) then
		signal sqlstate '45000'
        set message_text = 'no such link in the linking table';
	end if;
    
end
// DELIMITER ;


-- TASK 2

DROP TRIGGER IF EXISTS employee_id_insert;

DELIMITER //
CREATE TRIGGER employee_id_insert
before insert
ON employee
for each row precedes employee_insert
begin
set new.id_number = lpad(new.id_number, 10,1);
end //
DELIMITER ;

-- TASK 3

DROP TRIGGER IF EXISTS medecine_code_insert;

DELIMITER //
CREATE TRIGGER medecine_code_insert
before insert 
on medecinelist
for each row 
begin
  declare cut_name VARCHAR(45);
  declare cut_minCode VARCHAR(45);
  declare mixed VARCHAR(45);
  
  set cut_name = lpad(new.name, 1, 0);
  set cut_minCode = lpad(new.ministry_code, 3, 0);
  set mixed = concat(cut_name, cut_minCode);
  
  set new.ministry_code = mixed;
end //
DELIMITER ;

-- Task 4

DROP TRIGGER IF EXISTS minimal_cardinality_insert;

DELIMITER //
CREATE TRIGGER minimal_cardinality_insert
AFTER DELETE
ON pharmacy FOR EACH ROW
BEGIN
  IF(SELECT COUNT(*) FROM pharmacy)<4
  THEN SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Delete error MIN cardinality';
END IF;
END //
DELIMITER ;








