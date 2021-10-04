DROP TABLE IF EXISTS autors;
CREATE TABLE autors(ID BIGINT PRIMARY KEY  auto_increment, NAME VARCHAR(255));
DROP TABLE IF EXISTS genres;
CREATE TABLE genres(ID BIGINT PRIMARY KEY  auto_increment, NAME VARCHAR(255));
DROP TABLE IF EXISTS books;
CREATE TABLE books(ID BIGINT PRIMARY KEY  auto_increment
					, NAME VARCHAR(255)
					, AUTORID BIGINT 
					, foreign key (autorid) references autors(id)
					, GENREID BIGINT 
					, foreign key (genreid) references genres(id)
				  );

DROP TABLE IF EXISTS notes;
CREATE TABLE notes(ID BIGINT PRIMARY KEY  auto_increment, NOTE VARCHAR(255), book_id bigint, foreign key (book_id) references books(id) on delete cascade);

DROP TABLE IF EXISTS users;
CREATE TABLE Users(id bigint primary key auto_increment,  login varchar(20), name VARCHAR(255),  password VARCHAR(255), role varchar(20));