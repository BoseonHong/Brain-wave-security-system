create database brainwave;

grant all on brainwave.* to dbuser@localhost identified by 'abc123';

create table users (
  user_id varchar(255) not null primary key,
  name varchar(255) not null,
  password varchar(255) not null
);

create table egg (
  egg_id int not null auto_increment primary key,
  delta int not null,
  theta int not null,
  alpha int not null,
  beta int not null,
  gamma int not null,
  vec int not null,
  user_id varchar(255),
  foreign key(user_id) references users(user_id)
);

insert into users (user_id, name, password) values ('', '', '');

insert into egg (delta, theta, alpha, beta, gamma, vec, user_id) values (0, 0, 0, 0, 0, 0, '');
