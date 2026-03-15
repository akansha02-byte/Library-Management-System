CREATE DATABASE IF NOT EXISTS library_db;
USE library_db;

CREATE TABLE books(
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    author VARCHAR(100),
    publisher VARCHAR(100),
    quantity INT
);

CREATE TABLE std(
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100)
);

CREATE TABLE issue_books(
    issue_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    book_id INT,
    FOREIGN KEY(student_id) REFERENCES std(student_id),
    FOREIGN KEY(book_id) REFERENCES books(book_id)
);

-- Sample data
INSERT INTO books (title, author, publisher, quantity)
VALUES
('Java Programming','James Gosling','Oracle Press',5),
('Data Structures','Cormen','Pearson',4),
('DBMS Concepts','Korth','McGraw Hill',6);

INSERT INTO std (name,email)
VALUES
('Rahul Sharma','rahul@gmail.com'),
('Priya Verma','priya@gmail.com');
