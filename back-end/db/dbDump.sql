CREATE TABLE Vlasnik
(
    id SERIAL PRIMARY KEY,
    ime VARCHAR(50) NOT NULL,
    prezime VARCHAR(50) NOT NULL
);


CREATE TABLE Ljubimac
(
    id SERIAL PRIMARY KEY,
    ime VARCHAR(50) NOT NULL,
    vrsta VARCHAR(50) NOT NULL,
    spol VARCHAR(25) NOT NULL,
    dob INT NOT NULL,
    boja VARCHAR(50) NOT NULL,
    prehrana VARCHAR(25) NOT NULL,
    adresa VARCHAR(50) NOT NULL,
    veterinar VARCHAR(50) NOT NULL,
    id_vlasnik INT NOT NULL,
    FOREIGN KEY (id_vlasnik) REFERENCES Vlasnik(id),
    UNIQUE (id_vlasnik)
);


INSERT INTO vlasnik (ime, prezime)
VALUES
    ('Marko', 'Horvat'),
    ('Ana', 'Marić'),
    ('Petra', 'Novak'),
    ('Ivan', 'Kovačić'),
    ('Ivana', 'Barić'),
    ('Marko', 'Vuković'),
    ('Petra', 'Horvat'),
    ('Ana', 'Kovač'),
    ('Ivana', 'Novak'),
    ('Luka', 'Horvat');


INSERT INTO ljubimac (ime, vrsta, spol, dob, boja, prehrana, adresa, veterinar, id_vlasnik)
VALUES
    ('Buddy', 'pas', 'muški', 3, 'smeđa', 'mesožder', 'Zagreb', 'Dr. Ana Kovač', 1),
    ('Whiskers', 'mačka', 'ženski', 2, 'siva', 'mesožder', 'Split', 'Dr. Ivan Petrović', 2),
    ('Rocky', 'pas', 'muški', 4, 'crna i smeđa', 'mesožder', 'Rijeka', 'Dr. Marta Tomić', 3),
    ('Mittens', 'mačka', 'ženski', 1, 'bijela', 'mesožder', 'Osijek', 'Dr. Maja Horvat', 4),
    ('Fluffy', 'hrčak', 'neuter', 0.5, 'smeđi', 'biljožder', 'Zadar', 'Dr. Petra Kovač', 5),
    ('Lola', 'pas', 'ženski', 2, 'crna', 'mesožder', 'Pula', 'Dr. Luka Novak', 6),
    ('Oliver', 'mačka', 'muški', 3, 'narandžasta', 'mesožder', 'Varaždin', 'Dr. Marija Kovač', 7),
    ('Oreo', 'hrčak', 'neuter', 0.3, 'crno-bijeli', 'biljožder', 'Karlovac', 'Dr. Ivan Barić', 8),
    ('Polly', 'papiga', 'neuter', 2, 'šareno', 'biljožder', 'Zagreb', 'Dr. Ana Kovač', 9),
    ('Shelly', 'kornjača', 'neuter', 5, 'zelena', 'biljožder', 'Zadar', 'Dr. Petra Kovač', 10);