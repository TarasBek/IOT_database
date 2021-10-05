use labor_sql;
	
 /* 1 task  */   
SELECT class, type, country FROM classes
WHERE country = "Japan" ORDER BY type DESC;

/* 2 task  */
SELECT * FROM outcome_o
WHERE date rlike "^[0-9][0-9][0-9][0-9]-[0-9][0-9]-[1][4] [0-9][0-9]:[0-9][0-9]:[0-9][0-9]$";

/* 3 task (making right join) */
SELECT passenger.ID_psg, pass_in_trip.date, pass_in_trip.ID_psg
FROM pass_in_trip
right join passenger ON pass_in_trip.ID_psg = passenger.ID_psg WHERE date IS NOT NULL;

/* 4 task (using any) */
SELECT distinct maker type FROM product
where type = "PC" AND NOT maker = ANY( select distinct maker FROM product WHERE type = "Laptop"); 

/* 5 task  */
SELECT distinct maker type FROM product
where type = "laptop" AND  maker != ALL( select distinct maker FROM product WHERE type = "printer"); 

/* 6 task  */
SELECT DATE_FORMAT(date,'%d.%m.%Y') as date FROM income;

/* 7 task  */
SELECT price FROM printer  WHERE color = "n";
SELECT product.maker, min(price) FROM product
JOIN printer 
ON product.model = printer.model;

/* 8 task  */
SELECT AVG(hd) FROM product
JOIN pc ON product.model = pc.model
WHERE maker = ALL (SELECT maker FROM product WHERE type = "PC" AND type = "Printert");

/* 9 task  */
SELECT *
FROM (SELECT name, numGuns, bore, displacement, type,
country, launched, classes.class , (numGuns=8) + (bore=15) + (displacement=32000) + (type='bb') + (country='USA') + (launched=1915) + (classes.class='Kon') AS n
FROM ships JOIN classes ON ships.class  = classes.class ) AS x
WHERE n>=4;

/* 10 task */
SELECT product.type, product.model, min(selecion.price) FROM product
JOIN (SELECT model, price FROM pc
	  UNION
	  SELECT model, price FROM printer
      UNION
      SELECT model, price FROM laptop
      ) AS selecion
ON product.model = selecion.model GROUP BY product.type

