USE mydb;

SET GLOBAL log_bin_trust_function_creators = 1;

DROP FUNCTION IF EXISTS get_average_experience;
DROP FUNCTION IF EXISTS get_employee;

DELIMITER //
CREATE FUNCTION get_average_experience()
RETURNS FLOAT
BEGIN
return(SELECT AVG(experience) FROM employee);
END //	

DELIMITER //
CREATE FUNCTION get_employee(
employee_id INT
)
RETURNS VARCHAR(45)
BEGIN
RETURN(
  SELECT CONCAT(name,'       ', open_time,'       ', close_time) from pharmacy where id =ANY (SELECT pharmacy_id from employee where id = employee_id)
);
end//

select name , surname, get_employee(id) AS 'name      Open        Closed'  from employee;