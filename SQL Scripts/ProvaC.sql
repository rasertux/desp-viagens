create database ProvaC;

use ProvaC;

create table viagem(
	idviagem int(6) not null  auto_increment,
    tipo varchar(30) not null,
    dtinicio timestamp not null,
    dtencerramento timestamp not null,
    cidade varchar(30) not null,
    uf char(2) not null,
    vldiaria decimal(15,2) not null,
    nomecolaborador varchar(30) not null,
    nomecliente varchar(30) not null,
    totaldespesas decimal(15,2) default 0,
    primary key(idviagem)
);

create table despesa(
	iddespesa int(6) not null auto_increment,
    vldespesa decimal(15,2) not null,
    dthoraentrada timestamp not null,
    dthorasaida timestamp not null,
    nomehotel varchar(30) not null,
    cidadehotel varchar(30) not null,
    idviagem int(6) not null,
    primary key(iddespesa),
    foreign key(idviagem) references viagem(idviagem)
);

delimiter |
create trigger tgrindespesa after insert on despesa for each row 
begin update viagem set totaldespesas=(select sum(vldespesa) from despesa where 
idviagem=new.idviagem) where idviagem=new.idviagem; end; |
delimiter ;

delimiter |
create trigger tgrupdespesa after update on despesa for each row 
begin update viagem set totaldespesas=(select sum(vldespesa) from despesa where 
idviagem=new.idviagem) where idviagem=new.idviagem; end; |
delimiter ;

delimiter |
create trigger tgrdedespesa after delete on despesa for each row 
begin update viagem set totaldespesas=(select sum(vldespesa) from despesa where 
idviagem=old.idviagem) where idviagem=old.idviagem; end; |
delimiter ;