CREATE DATABASE library;

USE library;

CREATE TABLE author (
Id INT(11) NOT NULL AUTO_INCREMENT,
Name VARCHAR(60) NOT NULL,
BirthDate DATE NOT NULL,
PRIMARY KEY(Id)
);

CREATE TABLE book (
Id INT(11) NOT NULL AUTO_INCREMENT,
Title VARCHAR(60) NOT NULL,
AuthorId INT(11) NOT NULL,
Status ENUM('AVAILABLE', 'UNAVAILABLE') NOT NULL,
RegistrationDate DATETIME NOT NULL,
UpdateDate DATETIME NOT NULL,
PRIMARY KEY (Id),
FOREIGN KEY (AuthorId) REFERENCES author (Id)
);

CREATE TABLE customer (
Id INT(11) NOT NULL AUTO_INCREMENT,
Name VARCHAR(60) NOT NULL,
Email VARCHAR(100) NOT NULL,
PRIMARY KEY (Id)
);

CREATE TABLE loan (
Id INT(11) NOT NULL AUTO_INCREMENT,
BookId INT(11) NOT NULL,
CustomerId INT(11) NOT NULL,
LoanDate DATETIME NOT NULL,
ReturnDate DATETIME NOT NULL,
PRIMARY KEY (Id),
FOREIGN KEY (BookId) REFERENCES book (Id),
FOREIGN KEY (CustomerId) REFERENCES customer (Id)
);

INSERT INTO author
(Name, BirthDate)
VALUES
('Clarice Lispector','1920-12-10'),
('Machado de Assis', '1839-06-21'),
('Guimarães Rosa', '1908-06-27'),
('Jorge Amado', '1912-08-10'),
('Carlos Drummond de Andrade', '1902-10-31'),
('Conceição Evaristo', '1946-11-29'),
('Graciliano Ramos', '1892-10-27'),
('Lygia Fagundes Telles', '1918-04-19'),
('Luís Fernando Verissimo', '1936-09-26'),
('Itamar Vieira Junior', '1979-08-06')
;

INSERT INTO book
(Title, AuthorId, Status, RegistrationDate, UpdateDate)
VALUES
('A Hora da Estrela', '1', 'AVAILABLE', '2026/02/20', '2026/02/20'),
('Água Viva', '1', 'AVAILABLE', '2026/02/20', '2026/02/20'),
('Memórias Póstumas de Brás Cubas', '2', 'AVAILABLE', '2026/02/20', '2026/02/20'),
('Dom Casmurro', '2', 'AVAILABLE', '2026/02/20', '2026/02/20'),
('Grande Sertão: Veredas', '3', 'AVAILABLE', '2026/02/20', '2026/02/20'),
('Capitães de Areia', '4', 'AVAILABLE', '2026/02/20', '2026/02/20'),
('Alguma Poesia', '5', 'AVAILABLE', '2026/02/20', '2026/02/20'),
('Canção para Ninar Menino Grande', '6', 'AVAILABLE', '2026/02/20', '2026/02/20'),
('Vidas Secas', '7', 'AVAILABLE', '2026/02/20', '2026/02/20'),
('As Meninas', '8', 'AVAILABLE', '2026/02/20', '2026/02/20'),
('O Analista de Bagé', '9', 'AVAILABLE', '2026/02/20', '2026/02/20'),
('Torto Arado', '10', 'AVAILABLE', '2026/02/20', '2026/02/20')
;

INSERT INTO customer
(Name, Email)
VALUES
('Bob Brown','bob@gmail.com'),
('Maria Green','maria@gmail.com'),
('Alex Grey','alex@gmail.com'),
('Martha Red','martha@gmail.com'),
('Donald Blue','donald@gmail.com'),
('Alex Pink','bob@gmail.com');
