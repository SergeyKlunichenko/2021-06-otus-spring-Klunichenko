insert into autors (id, name) values (1, 'Булгаков Михаил Афанасьевич');
insert into autors (id, name) values (2, 'Стругацкий Аркадий Натанович');
--insert into autors (id, name) values (3, 'Пушкин Александр Сергеевич');
insert into genres (id, name) values (1, 'драма');
insert into genres (id, name) values (2, 'фантастика');
--insert into genres (id, name) values (3, 'Стихи');
--insert into genres (id, name) values (4, 'Приключения');
--insert into books(id, name, genreid, autorid) values(1, 'Белая гвардия', 1, 1);
--insert into books(id, name, genreid, autorid) values(2, 'Полдень XXII вес',2, 2);
insert into books(name, genreid, autorid) values('Белая гвардия', 1, 1);
insert into books(name, genreid, autorid) values('Полдень XXII вес',2, 2);
