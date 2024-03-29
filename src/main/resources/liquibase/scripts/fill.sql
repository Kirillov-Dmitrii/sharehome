-- liquibase formatted sql

-- changeset amalashenko:1
CREATE TABLE shedule
(
    id    SERIAL not null
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
    id    SERIAL not null
        primary key,
    data       oid,
    file_path  varchar(255),
    file_size  bigint not null,
    media_type varchar(255)
);

-- changeset amalashenko:3
CREATE TABLE client
(
    id    SERIAL not null
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
    id    SERIAL not null
        primary key,
    document_name varchar(255),
    description text
);

-- changeset amalashenko:5
CREATE TABLE report
(
    id    SERIAL not null
        primary key,
    diet text,
    health varchar(500),
    actions varchar(400),
    day_report date
);

-- changeset amalashenko:6
CREATE TABLE scheme_run
(
    id    SERIAL not null
        primary key,
    data       oid,
    file_path  varchar(255),
    file_size  bigint not null,
    media_type varchar(255)
);

-- changeset amalashenko:7
CREATE TABLE animal
(
    id    SERIAL not null
        primary key,
    name varchar(255) unique
);

-- changeset amalashenko:8
CREATE TABLE share_home
(
    id    SERIAL not null
        primary key,
    name varchar(200),
    address varchar(500)
);

-- changeset amalashenko:9
ALTER TABLE shedule ADD COLUMN share_home_id SERIAL;
ALTER TABLE shedule ADD FOREIGN KEY(share_home_id) REFERENCES share_home(id);

-- changeset amalashenko:10
ALTER TABLE avatar ADD COLUMN report_id SERIAL;
ALTER TABLE avatar ADD FOREIGN KEY (report_id) REFERENCES report(id);

-- changeset amalashenko:11
ALTER TABLE client ADD COLUMN share_home_id SERIAL;
ALTER TABLE client ADD FOREIGN KEY(share_home_id) REFERENCES share_home(id);

-- changeset amalashenko:12
ALTER TABLE ref_info ADD COLUMN share_home_id SERIAL;
ALTER TABLE ref_info ADD FOREIGN KEY(share_home_id) REFERENCES share_home(id);

-- changeset amalashenko:13
ALTER TABLE report ADD COLUMN animal_id SERIAL;
ALTER TABLE report ADD FOREIGN KEY(animal_id) REFERENCES animal(id);

-- changeset amalashenko:14
ALTER TABLE scheme_run ADD COLUMN share_home_id SERIAL;
ALTER TABLE scheme_run ADD FOREIGN KEY(share_home_id) REFERENCES share_home(id);

-- changeset amalashenko:15
ALTER TABLE animal ADD COLUMN owner bigint;
ALTER TABLE animal ADD FOREIGN KEY(owner) REFERENCES client(id);

-- changeset amalashenko:16
ALTER TABLE animal ADD COLUMN share_home_id SERIAL;
ALTER TABLE animal ADD FOREIGN KEY(share_home_id) REFERENCES share_home(id);

-- changeset amalashenko:17
ALTER TABLE share_home ADD COLUMN description varchar(1000);

-- changeset amalashenko:18
CREATE TABLE type_animal
(
    id    SERIAL not null
        primary key,
    code varchar(50),
    name varchar(200)
);
ALTER TABLE share_home ADD COLUMN type_animal_id SERIAL;
ALTER TABLE share_home ADD FOREIGN KEY(type_animal_id) REFERENCES type_animal(id);

--chageset kirillov:19
ALTER TABLE animal ADD COLUMN owner_id INTEGER ;