insert into autors (id, name) values (1, 'Булгаков Михаил Афанасьевич');
insert into autors (id, name) values (2, 'Стругацкий Аркадий Натанович');
insert into genres (id, name) values (1, 'драма');
insert into genres (id, name) values (2, 'фантастика');
insert into books(name, genreid, autorid) values('Белая гвардия', 1, 1);
insert into books(name, genreid, autorid) values('Полдень XXII век',2, 2);
insert into books(name, genreid, autorid) values('Мастер и Маргарита', 2, 1);
insert into notes(note, book_id) values('Коммент к белой гвардии', 1);
insert into notes(note, book_id) values('Коммент к Полдню', 2);
