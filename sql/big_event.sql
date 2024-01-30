-- Crear base de datos
create database big_event;

-- Utilizar la base de datos
use big_event;

-- Tabla de usuarios
create table user (
    id int unsigned primary key auto_increment comment 'ID',
    username varchar(20) not null unique comment 'Nombre de usuario',
    password varchar(32) comment 'Contraseña',
    nickname varchar(10) default '' comment 'Apodo',
    email varchar(128) default '' comment 'Correo electrónico',
    user_pic varchar(128) default '' comment 'Imagen de perfil',
    create_time datetime not null comment 'Fecha de creación',
    update_time datetime not null comment 'Fecha de modificación'
) comment 'Tabla de usuarios';

-- Tabla de categorías
create table category (
    id int unsigned primary key auto_increment comment 'ID',
    category_name varchar(32) not null comment 'Nombre de la categoría',
    category_alias varchar(32) not null comment 'Alias de la categoría',
    create_user int unsigned not null comment 'ID del creador',
    create_time datetime not null comment 'Fecha de creación',
    update_time datetime not null comment 'Fecha de modificación',
    constraint fk_category_user foreign key (create_user) references user(id) -- Restricción de clave externa
);

-- Tabla de artículos
create table article (
    id int unsigned primary key auto_increment comment 'ID',
    title varchar(30) not null comment 'Título del artículo',
    content varchar(10000) not null comment 'Contenido del artículo',
    cover_img varchar(128) not null comment 'Imagen de portada del artículo',
    state varchar(9) default 'Borrador' comment 'Estado del artículo: solo puede ser [Publicado] o [Borrador]',
    category_id int unsigned comment 'ID de la categoría del artículo',
    create_user int unsigned not null comment 'ID del creador',
    create_time datetime not null comment 'Fecha de creación',
    update_time datetime not null comment 'Fecha de modificación',
    constraint fk_article_category foreign key (category_id) references category(id), -- Restricción de clave externa
    constraint fk_article_user foreign key (create_user) references user(id) -- Restricción de clave externa
);
