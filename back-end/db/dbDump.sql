CREATE TABLE IF NOT EXISTS public.igracka
(
    id integer NOT NULL,
    naziv character varying(50) COLLATE pg_catalog."default" NOT NULL,
    boja character varying(20) COLLATE pg_catalog."default" NOT NULL,
    ljubimacid integer,
    CONSTRAINT igracka_pkey PRIMARY KEY (id),
    CONSTRAINT igracka_ljubimacid_fkey FOREIGN KEY (ljubimacid)
        REFERENCES public.ljubimac (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)


CREATE TABLE IF NOT EXISTS public.ljubimac
(
    id integer NOT NULL,
    ime character varying(50) COLLATE pg_catalog."default" NOT NULL,
    vrsta character varying(50) COLLATE pg_catalog."default" NOT NULL,
    spol character varying(10) COLLATE pg_catalog."default" NOT NULL,
    starost integer NOT NULL,
    zivotnivijek integer NOT NULL,
    boja character varying(20) COLLATE pg_catalog."default" NOT NULL,
    imevlasnika character varying(50) COLLATE pg_catalog."default" NOT NULL,
    prehrana character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT ljubimac_pkey PRIMARY KEY (id)
)

INSERT INTO Ljubimac (ID, Ime, Vrsta, Spol, Starost, ZivotniVijek, Boja, ImeVlasnika, Prehrana)
VALUES
	(1, 'Kira', 'Pas', 'Ženka', 3, 11, 'Žuta', 'Ivan', 'Mesožder'),
	(2, 'Korni', 'Kornjača', 'Ženka', 6, 30, 'Zelena', 'Fran', 'Svežder'),
	(3, 'Micika', 'Mačka', 'Ženka', 2, 17, 'Bijela', 'Tomislav', 'Mesožder'),
	(4, 'Anđa', 'Riba', 'Ženka', 3, 13, 'Narančasta', 'Ivona', 'Biljožder'),
	(5, 'Gricko', 'Hrčak', 'Mužjak', 1, 3, 'Smeđa', 'Lora', 'Mesožder'),
	(6, 'Rex', 'Pas', 'Mužjak', 4, 12, 'Crna', 'Marija', 'Mesožder'),
	(7, 'Rafaelo', 'Kornjača', 'Ženka', 7, 40, 'Zelena', 'Marko', 'Svežder'),
	(8, 'Leo', 'Mačka', 'Mužjak', 5, 15, 'Siva', 'Ana', 'Mesožder'),
	(9, 'Šara', 'Riba', 'Ženka', 2, 10, 'Zlatna', 'David', 'Biljožder'),
	(10, 'Koko', 'Kakadu', 'Mužjak', 10, 50, 'Bijela', 'Tamara', 'Biljožder');
	
INSERT INTO Igracka (ID, Naziv, Boja, LjubimacID)
VALUES
	(1, 'Lopta', 'Crvena', 1),
	(2, 'Uže', 'Plava', 1);
	(3, 'Tobogan', 'Žuta', 2);
	(3, 'Tobogan', 'Plava', 2);
	(5, 'Zvonce', 'Roza', 3),
	(6, 'Plišanac', 'Smeđa', 3);
	(7, 'Kuglice', 'Siva', 4),
	(8, 'Štapići', 'Zelena', 4);
	(9, 'Kolotur', 'Plava', 5),
	(10, 'Tobogan', 'Zelena', 5);
	(11, 'Lopta', 'Žuta', 6),
	(12, 'Pliš', 'Roza', 6);
	(13, 'Kamen', 'Siva', 7),
	(14, 'Plastika', 'Narančasta', 7);
	(15, 'Miš', 'Smeđa', 8),
	(16, 'Konop', 'Zelena', 8);
	(17, 'Alge', 'Zelena', 9),
	(18, 'Kamen', 'Siva', 9);
	(19, 'Zvonce', 'Zlatna', 10),
	(20, 'Ljuljačka', 'Crvena', 10);

