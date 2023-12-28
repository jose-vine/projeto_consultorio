create database if not exists lista05;
use lista05;

select * from paciente;
select * from medico;
select * from consulta;

create table paciente (
	id int not null auto_increment,
    nome varchar(150) not null,
    cpf char(14) not null unique,
    doenca varchar(50) not null,
    index idx_nome(nome),
    primary key(id)
);

create table medico (
	id int not null auto_increment,
    nome varchar(150) not null,
    matricula int not null unique,
    especialidade varchar(50) not null,
    salario decimal(6, 2) not null,
    index idx_nome(nome),
    primary key(id)
);

create table consulta (
	id_medico int not null,
    id_paciente int not null,
    horario datetime not null,
    valor decimal(5, 2) not null,
    unique index idx_id_medico_horario(id_medico, horario),
    unique index idx_id_paciente_horario(id_paciente, horario),
    foreign key(id_medico) references medico(id)
		on update cascade
        on delete restrict,
    foreign key(id_paciente) references paciente(id)
		on update cascade
        on delete restrict,
    primary key(id_medico, id_paciente, horario)
);