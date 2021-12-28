USE mydb;

DROP PROCEDURE IF EXISTS insert_into_pharmacy;
DROP PROCEDURE IF EXISTS insert_into_junction_table;
DROP PROCEDURE IF EXISTS create_tables;

DELIMITER //
CREATE PROCEDURE insert_into_pharmacy(
  name VARCHAR (45),
  ministry_code BIGINT,
  recipe TINYINT,
  narcotic TINYINT,
  psychotropic TINYINT
)
BEGIN
INSERT INTO medecinelist (name, ministry_code, recipe, narcotic, psychotropic)
VALUES (name, ministry_code, recipe, narcotic, psychotropic);
END //

DELIMITER //
CREATE PROCEDURE insert_into_junction_table()
BEGIN
  INSERT INTO medecinelist (name, ministry_code, recipe, narcotic, psychotropic) VALUES ('Metheora', '1168', '1', '1', '0');
  INSERT INTO effect_zone (name) VALUES ('liver');
  INSERT INTO medecine_zone (medecine_id, effect_zone_id) VALUES ('9', '9');
  SELECT medecinelist.id, medecinelist.name, medecinelist.ministry_code, medecinelist.recipe, medecinelist.narcotic, medecinelist.psychotropic, effect_zone.id, effect_zone.name 
  FROM medecinelist
  JOIN medecine_zone ON (medecinelist.id = medecine_zone.medecine_id)
  JOIN effect_zone ON (effect_zone.id = medecine_zone.effect_zone_id);
END//


DELIMITER //
CREATE PROCEDURE create_tables()
BEGIN
  DECLARE done INT DEFAULT FALSE;
  DECLARE name VARCHAR(45);
  DECLARE posts CURSOR FOR
  SELECT post.name FROM post;
  DECLARE CONTINUE HANDLER FOR NOT FOUND
  SET done = TRUE;
  OPEN posts;
  posts_loop: LOOP
   FETCH posts INTO name;
   IF done = TRUE THEN LEAVE posts_loop;
   END IF;
   SET @table_count = 1;
   while_loop: WHILE @table_count < 5 DO
    SET @new_table = CONCAT('CREATE TABLE IF NOT EXISTS ', name, '(id INT, name VARCHAR(45));');
    SELECT @new_table;
    PREPARE myquery FROM @new_table;
    EXECUTE myquery;
    SET @table_count = @table_count + 1;
   END WHILE;
  END LOOP;
  CLOSE posts;
END ;
//



