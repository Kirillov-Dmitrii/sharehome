-- liquibase formatted sql

-- changeset amalashenko:1
CREATE TABLE shedule
(
    id    SERIAL
        primary key,
    day_of_week varchar(255),
    time_begin time,
    time_end time,
    constraint day_time
        unique (day_of_week, time_begin, time_end)
);

-- changeset amalashenko:2
CREATE TABLE avatar
(
    id    SERIAL
        primary key,
    data       oid,
    file_path  varchar(255),
    file_size  bigint not null,
    media_type varchar(255)
);

-- changeset amalashenko:3
CREATE TABLE client
(
    id    SERIAL
        primary key,
    name varchar(255),
    phone varchar(50),
    id_chat           bigint        not null,
    owner boolean,
    count_days integer
);

-- changeset amalashenko:4
CREATE TABLE ref_info
(
    id    SERIAL
        primary key,
    document_name varchar(255),
    description text
);

-- changeset amalashenko:5
CREATE TABLE report
(
    id    SERIAL
        primary key,
    diet text,
    health varchar(500),
    actions varchar(400),
    day_report date
);

-- changeset amalashenko:6
CREATE TABLE scheme_run
(
    id    SERIAL
        primary key,
    data       oid,
    file_path  varchar(255),
    file_size  bigint not null,
    media_type varchar(255)
);

-- changeset amalashenko:7
CREATE TABLE animal
(
    id    SERIAL
        primary key,
    name varchar(255) unique
);

-- changeset amalashenko:8
CREATE TABLE share_home
(
    id    BIGINT
        primary key,
    name varchar(200),
    address varchar(500),
    security varchar(500)
);

-- changeset amalashenko:9
ALTER TABLE shedule ADD COLUMN share_home_id BIGINT;
ALTER TABLE shedule ADD FOREIGN KEY(share_home_id) REFERENCES share_home(id);

-- changeset amalashenko:10
ALTER TABLE avatar ADD COLUMN report_id SERIAL;
ALTER TABLE avatar ADD FOREIGN KEY (report_id) REFERENCES report(id);

-- changeset amalashenko:11
ALTER TABLE client ADD COLUMN share_home_id BIGINT;
ALTER TABLE client ADD FOREIGN KEY(share_home_id) REFERENCES share_home(id);

-- changeset amalashenko:12
ALTER TABLE ref_info ADD COLUMN share_home_id BIGINT;
ALTER TABLE ref_info ADD FOREIGN KEY(share_home_id) REFERENCES share_home(id);

-- changeset amalashenko:13
ALTER TABLE report ADD COLUMN animal_id SERIAL;
ALTER TABLE report ADD FOREIGN KEY(animal_id) REFERENCES animal(id);

-- changeset amalashenko:14
ALTER TABLE scheme_run ADD COLUMN share_home_id BIGINT;
ALTER TABLE scheme_run ADD FOREIGN KEY(share_home_id) REFERENCES share_home(id);

-- changeset amalashenko:15
ALTER TABLE animal ADD COLUMN owner bigint;
ALTER TABLE animal ADD FOREIGN KEY(owner) REFERENCES client(id);

-- changeset amalashenko:16
ALTER TABLE animal ADD COLUMN share_home_id BIGINT;
ALTER TABLE animal ADD FOREIGN KEY(share_home_id) REFERENCES share_home(id);

-- changeset amalashenko:17
ALTER TABLE share_home ADD COLUMN description varchar(1000);

-- changeset amalashenko:18
CREATE TABLE type_animal
(
    id    SERIAL
        primary key,
    code varchar(50),
    name varchar(200)
);
ALTER TABLE share_home ADD COLUMN type_animal_id SERIAL;
ALTER TABLE share_home ADD FOREIGN KEY(type_animal_id) REFERENCES type_animal(id);

--chageset kirillov:19
ALTER TABLE animal ADD COLUMN owner_id INTEGER ;

-- changeset amalashenko:20
INSERT INTO type_animal (code, name)
VALUES ('CAT', 'Кошка');
INSERT INTO type_animal (code, name)
VALUES ('DOG', 'Собака');

-- changeset amalashenko:21
INSERT INTO share_home (id, name, address, description, security, type_animal_id)
VALUES (1, 'Снежок', 'г.Москва, ул.Судостроительная д.12.', 'В нашем приюте “Снежок” находится более 200 собак, которые хотят обрести семью и друзей.
Все животные регулярно осматриваются ветеринарами, вакцинированы, обработаны от блох и клещей.
Волонтеры ежедневно ухаживают, выгуливают, кормят и заботятся о собаках, а специалисты по социализации помогают приучить к комфортному общению с человеком.', '+7 812 335 65 54', 2);

-- changeset amalashenko:22
INSERT INTO shedule (day_of_week, time_begin, time_end, share_home_id)
VALUES ('Понедельник', '10:00', '18:00', 1);
INSERT INTO shedule (day_of_week, time_begin, time_end, share_home_id)
VALUES ('Вторник', '10:00', '18:00', 1);
INSERT INTO shedule (day_of_week, time_begin, time_end, share_home_id)
VALUES ('Среда', '10:00', '18:00', 1);
INSERT INTO shedule (day_of_week, time_begin, time_end, share_home_id)
VALUES ('Четверг', '10:00', '18:00', 1);
INSERT INTO shedule (day_of_week, time_begin, time_end, share_home_id)
VALUES ('Пятница', '10:00', '18:00', 1);
INSERT INTO shedule (day_of_week, time_begin, time_end, share_home_id)
VALUES ('Суббота', '10:00', '18:00', 1);
INSERT INTO shedule (day_of_week, time_begin, time_end, share_home_id)
VALUES ('Воскресенье', '10:00', '18:00', 1);

-- changeset amalashenko:23
INSERT INTO ref_info (document_name, description, share_home_id)
VALUES ('RULES_VISIT', 'Уважаемые посетители приюта!
Для того, чтобы посещение приюта прошло безопасно для вас и для его жителей, просим соблюдать следующие правила:
	1.	Посещение приюта возможно строго в установленное время: ПН-ВС, с 10:00 до 18:00.
	2.	Угощать собак можно лакомствами из зоомагазина (собачье печенье, легкое, вяленое мясо).
	3.	Посещение приюта лицами, младше 16 лет возможно строго в сопровождении родителей, либо с письменного согласия.
    4. Гулять с собаками можно строго по маршруту, указанному сотрудниками     приюта.
    5. Посещая приют, придерживайтесь обычных правил гигиены при контактах с  животными: тщательно мойте руки и лицо с мылом.', 1);

-- changeset amalashenko:24
INSERT INTO ref_info (document_name, description, share_home_id)
VALUES ('DOCUMENTS', 'Уважаемые посетители приюта!
Для того, чтобы взять животное из приюта, вам потребуется только паспорт.', 1);

-- changeset amalashenko:25
INSERT INTO ref_info (document_name, description, share_home_id)
VALUES ('DATING_RULES', 'Краткие правила первого знакомства с собакой:
Что стоит делать:
1. Оставайтесь неподвижными, пока собака нюхает вашу сжатую руку.
2. Встаньте прямо или присядьте.
3. Не смотрите в глаза собаке.
4. Погладьте тело собаки, а не ее голову или морду.
5. Избегайте объятий.
6. Позвольте собаке контролировать взаимодействие.
7. Играйте с ней.

Чего не стоит делать при первой встрече с собакой:

1. Не приближайтесь к собаке.
2. Отводить взгляд.
3. Встаньте прямо или присядьте, но не приседайте над собакой.
4. Держите свое тело свободным и расслабленным.
5. Повернитесь так, чтобы вы не смотрели на собаку.
6. Если вы говорите, говорите спокойным, обнадеживающим тоном.', 1);

-- changeset amalashenko:26
INSERT INTO ref_info (document_name, description, share_home_id)
VALUES ('DATING_RULES', 'Краткие правила первого знакомства с собакой:
Что стоит делать:
1. Оставайтесь неподвижными, пока собака нюхает вашу сжатую руку.
2. Встаньте прямо или присядьте.
3. Не смотрите в глаза собаке.
4. Погладьте тело собаки, а не ее голову или морду.
5. Избегайте объятий.
6. Позвольте собаке контролировать взаимодействие.
7. Играйте с ней.

Чего не стоит делать при первой встрече с собакой:

1. Не приближайтесь к собаке.
2. Отводить взгляд.
3. Встаньте прямо или присядьте, но не приседайте над собакой.
4. Держите свое тело свободным и расслабленным.
5. Повернитесь так, чтобы вы не смотрели на собаку.
6. Если вы говорите, говорите спокойным, обнадеживающим тоном.', 1);

-- changeset amalashenko:27
INSERT INTO ref_info (document_name, description, share_home_id)
VALUES ('FAILURE', 'Список причин по которым вам могут отказать:
  1. Отказ обеспечить безопасность питомца на новом месте.
  2. Нестабильные отношения в семье.
  3. Антинаучное мышление.
  4. Наличие дома большого количества животных.
  5. Маленькие дети в семье.
  6. Аллергия.
  7. Животное забирают в подарок кому-то.
  8. Животное забирают в целях использования его рабочих качеств.
  9. Отказ приехать познакомиться с животным.
  10. Претендент — пожилой человек, проживающий один.
  11. Отсутствие регистрации и собственного жилья или его несоответствие нормам приюта.
  12. Без объяснения причин.', 1);

-- changeset amalashenko:28
INSERT INTO ref_info (document_name, description, share_home_id)
VALUES ('TRANSPORTATION', 'Рекомендации по транспортировке собаки:
 - Перевозить собаку из приюта домой лучше в специальном контейнере-переноске.
 - Некоторые собаки испытывают стресс в автомобиле, особенно если раньше они на автомобиле не катались.
 - Наличие специальной перевозки сделает поездку легче и для Вас, и для собаки. Если не хотите покупать себе собственную перевозку, можете попросить у человека, у которого берете собаку, или у друзей.
 - Запаситесь дополнительными покрывалами на сидения,  бумажными полотенцами, спросите в приюте или у волонтеров об особенностях перевозки собаки в машине.', 1);

-- changeset amalashenko:29
INSERT INTO ref_info (document_name, description, share_home_id)
VALUES ('EQUIPMENT_PUPPY', 'Рекомендации по обустройству дома для щенка:
  1. Для личного места щенка выбирайте тихий уголок, где ему никто не будет мешать. В этом месте не должно быть сквозняков и яркого света.
     Ваш питомец не должен жить на проходе, кафельном или цементном покрытии пола.
  2. В качестве подстилки для собаки вполне подойдет мягкое одеяло или специальные покрывала, которые продаются в зоомагазинах. Не используйте шерстяные покрытия, поскольку в них могут завестись паразиты.
     Также рассмотрите большую подушку со съемным чехлом, которая будет исполнять роль мягкой подстилки.
  3. Собаки любят быть слегка на возвышенности. Поэтому можете подложить непосредственно под подстилку специальный поддон.
  4. Часто случается ситуация, что щенку не по душе предложенное вами поприще, и он настойчиво ложиться на другое место в доме.
     Стоит прислушаться к собаке и установить его пространство в том месте, к которому он неравнодушен.
  5. Щенки очень восприимчивы к внешним инфекциям. Старайтесь всегда держать его личное место в чистоте.
  6. Внимательно осмотрите пространство, где будет жить ваша собачка. Посмотрите, или ничего не сможет на нее упасть.
     Желательно, чтобы рядом не было обуви. Надеемся, вам известно, к чему это может привести.
  7. Разместите вокруг жилья питомца разные игрушки. Миски с едой нежелательно ставить здесь.
     Для приема трапезы выберите спокойное место, можно и на кухне. Когда собака ест, не шумите и не мешайте ей.
     И еще одно, не делайте неприятные для собаки процедуры - уколы, стрижка ногтей, в ее личном пространстве.', 1);

-- changeset amalashenko:30
INSERT INTO ref_info(document_name, description, share_home_id)
VALUES ('EQUIPMENT_DOG', 'Рекомендации по обустройству дома для взрослого животного:

Заранее определите рамки поведения собаки.
Если питомец не ограничен строгими правилами, может залезать на диваны или лежать на пороге, то расположение спального места хозяин может определить самостоятельно.
Общие принципы выбора следующие:
  1. Отсутствие сквозняка.
  2. Удаленность от батарей, кондиционеров, аудиосистем и других климатических и электрических приборов.
  3. Не стоит располагать место вблизи от дверей или кухни.
  4. Территория должна быть уединенной, даже если в доме гости.
  5. Доступность для уборки.
  6. Нужно оборудовать альтернативный вариант на случай стирки или санитарной обработки лежака.', 1);

-- changeset amalashenko:31
INSERT INTO ref_info(document_name, description, share_home_id)
VALUES ('EQUIPMENT_DOG_DISABLED', 'Рекомендации по обустройству дома для животного с ограниченными возможностями:

Всё тоже самое, что и с обычным животным, за исключением, что требуется повышенное внимание со стороны хозяина. Заранее определите рамки поведения собаки.
Если питомец не ограничен строгими правилами, может залезать на диваны или лежать на пороге, то расположение спального места хозяин может определить самостоятельно.
Общие принципы выбора следующие:
  1. Отсутствие сквозняка.
  2. Удаленность от батарей, кондиционеров, аудиосистем и других климатических и электрических приборов.
  3. Не стоит располагать место вблизи от дверей или кухни.
  4. Территория должна быть уединенной, даже если в доме гости.
  5. Доступность для уборки.
  6. Нужно оборудовать альтернативный вариант на случай стирки или санитарной обработки лежака.', 1);

-- changeset amalashenko:32
INSERT INTO ref_info(document_name, description, share_home_id)
VALUES ('CYNOLOGIST', 'Советы кинолога по первичному общению с собакой:
1. Разговаривайте со своей собакой
2. Следите за своими эмоциями
3. Наблюдайте за выражением внутреннего состояния собаки и приводите его в норму
4. Учитывайте желания собаки
5. Гибко реагируйте на поведение собаки
6. Ставьте границы допустимого без использования наказания
7. Введите сигналы, которые вам помогут строить контакт', 1);

-- changeset amalashenko:33
INSERT INTO ref_info(document_name, description, share_home_id)
VALUES ('CYNOLOGIST_LIST', 'Список проверенных кинологов:
 - Ирина Оралова;
 - Школа дрессировки «Чили»;
 - Елена Молодцова;
 - Анна Казакова;
 - Татьяна Вишнякова;
 - Виктория Тетеркина;
 - Мария Фандеева;
 - Дарина Баялина;
 - Наташа Юрова;
 - Софья Морозова;
 - Анна Гаврилова;
 - Вероника Ларюшкина;
 - Лиана Соболева.', 1);