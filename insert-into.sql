INSERT INTO employee (surname, name, patronymic, id_number, passport, experience, birth_date, pharmacy_id, post_id) VALUES 
('Shevchenko', 'Mykola', 'Vasylovich', 162561812, '654182459', '2.6', '1985-11-21', '1', '1'),
('Monashko', 'Vasyl', 'Abrahimovich', 182577812, '752382459', '1.6', '1995-01-20', '2', '2'),
('Zliy', 'Zenyk', 'Reper', 117861812, '654181569', '10', '1975-11-21', '3', '3'),
('Kukushkina', 'Marina', 'Mykolaivna', 458261812, '854615459', '13.6', '1965-09-24', '4', '4'),
('Dzondzik', 'Ihor', 'Vasylovich', 142561853, '496182459', '5', '1995-06-25', '5', '5'),
('Shevchenko', 'Mykola', 'Vasylovich', 182561812, '954182459', '2.6', '1985-11-21', '6', '5'),
('Fufaika', 'Volodymyr', 'Volodymyrovich', 192468912, '213542459', '7', '1997-07-07', '7', '4');

INSERT INTO pharmacy (name, building_number, street_id, web_address, open_time, close_time, saturday, sunday) VALUES 
('Znahar', '18', '1', 'www.znahar.com', '9:00', '18:00', '1', '1'),
('Podoroznyk', '23', '2', 'www.podoroznyk.com', '8:00', '20:00', '2', '2'),
('3i', '45', '3', 'www.3i.com', '8:00', '21:00', '3', '3'),
('Abobus', '15', '4', 'www.abobus.com', '8:00', '20:00', '4', '4'),
('Narodna', '16', '5', 'www.narodna.com', '8:00', '00:00', '5', '5'),
('Pharmacy', '37', '6', 'www.pharmacy.com', '7:00', '22:00', '6', '6'),
('Apteka', '32', '7', 'www.apteka.com', '8:00', '21:00', '7', '7');

INSERT INTO medecinelist (name, ministry_code, recipe, narcotic, psychotropic) VALUES 
('Tabletka', '1337', '0', '0', '0'),
('NeTabletka', '1488', '0', '0', '1'),
('Ibuprofen', '6548', '0', '0', '0'),
('Kodein', '7894', '1', '1', '1'),
('Mezym', '1726', '0', '0', '0'),
('Neoprazol', '1854', '0', '0', '0'),
('Aboba', '3456', '1', '1', '1');

INSERT INTO post (name) VALUES 
('assistent'),
('clerk'),
('manager'),
('director'),
('pharmacist');

INSERT INTO street (name) VALUES
('Shevchenka'), 
('Volodymyra Velykogo'), 
('Kulisha'),
('Kos Anatolskogo'),
('Kavaleridze'),
('Klochnika'),
('Fufaiky'),
('Panasenka'),
('Dovzenka'),
('Syhivska');

INSERT INTO effect_zone (name) VALUES
('lungs'),
('heart'), 
('bones'),
('neck'),
('tooth'),
('eyes'), 
('ears');

INSERT INTO pharmacy_medecine (pharmacy_id, medecine_id) VALUES
(3, 6),
(2, 5),
(4, 3),
(5, 4),
(1, 2),
(6, 7),
(7, 1);

INSERT INTO medecine_zone (medecine_id, effect_zone_id) VALUES
(4, 6),
(6, 7),
(7, 2),
(1, 7),
(3, 5),
(2, 1),
(5, 3);


 
