connect 'jdbc:derby:tccpingado;create=true';
connect 'jdbc:derby:tccpingado';
connect 'jdbc:derby:tccpingado;user=app;password=app';
connect 'jdbc:derby:tccpingado;user=sys;password=sys';
connect 'jdbc:derby://localhost:1527/tccpingado;create=true';

-- usar esta conexao
connect 'jdbc:derby://localhost:1527/tccpingado';


drop table cryptoperson
create table CRYPTOPERSON (
    ID int primary key,
    NAME varchar(512),
    WALLET varchar(512),
    PASSWORD varchar(32),
    ROLE varchar(8),
    ACCOUNT varchar(42)
);
select id , name , wallet , role from cryptoperson;
insert into cryptoperson values (10000, 'Mack Content Service Provider', 'a4e3dc902ea0b53', 'bobbob12', 'ISP', '0xae72eb6f58f92809940fdfaf3d292d08b55ed58a');
insert into cryptoperson values (18374, 'Computer Science Expert', '9e948dc3a4e9ab5', 'bobbob12', 'Producer', '0xnull');
insert into cryptoperson values (29172, 'Andre Miguel', 'cd832ae95017e5d', 'bobbob12', 'Customer', '0xcd2c65c3d10e45836e0c9309ba8f773c18c2fb5d');
---
--insert into cryptoperson values (10000, 'Mack Content Service Provider', 'a4e3dc902ea0b53', 'ISP'), (18374, 'Computer Science Expert', '9e948dc3a4e9ab5', 'Producer'), (29172, 'Andre Miguel', 'cd832ae95017e5d', 'Customer');

-----------------------------------
drop table econtract
delete from econtract
create table ECONTRACT (
    ID bigint primary key,
    CONTENTID int,
    PARTYID1 int,
    PARTYID2 int,
    MICROFRACTION int,
    JITTIMETOSTART varchar(14),
    ENACTMENTVALID int,
    MANAGEMENTSTATUS int,
    FRAMEWORK varchar(24),
    FRAMEWORK_REFERENCE varchar(24),
    FRAMEWORK_PRICE decimal(3,3)
);
select id, contentid, partyid1, partyid2, microfraction, jittimetostart, enactmentvalid, managementstatus from econtract;
                              ID,           CONTENTID,    
                                                    PARTYID1,    
                                                           PARTYID2,    
                                                                  MICROFRACTION,    
                                                                      JITTIMETOSTART, ENACTMENTVALID,    
                                                                                         MANAGEMENTSTATUS,
                                                                                            FRAMEWORK,
                                                                                                    FRAMEWORK_VALUE
select * from econtract;
insert into econtract values (102938,       918273, 18374, 10000, 60, '201801022345', 1, 1, 'TIME', 'MINUTE', 0.005);
select * from econtract;
insert into econtract values (231562075273, 0,      888,   29172, 60, '0',            0, 0);

-----------------------------------
drop table TRANSACTIONS
create table TRANSACTIONS (
    ID bigint,
    ECONTRACTID bigint,
    TIMESTAMP varchar(14),
    PRICE decimal(3,3),
    primary key(ID, ECONTRACTID)
);

-----------------------------------
drop table exchangedvalue
create table EXCHANGEDVALUE (
    ID bigint primary key,
    TYPE varchar(128),
    SUBTYPE varchar(128),
    VALUE varchar(128),
    ECONTRACTID int,
    TITLE varchar(512),
    SIZE bigint,
    PRODUCERID int,
    ISPID int,
    LOCATION varchar(512),
    FILENAME varchar(512)
);
select * from exchangedvalue;
-- insert into exchangedvalue values (918273, 'P vs NP', 54254305, '/Library/WebServer/Documents');
delete from exchangedvalue where id in (234945, 547102, 156834, 481457, 334943)
insert into exchangedvalue values (918273, 'video', 'educational', NULL, 102938, 'P vs NP', 54254305, 18374, 10000, '/Library/WebServer/Documents', 'PvsNP_480p.mp4');
insert into exchangedvalue values (234945, 'video', 'entertainment', NULL, 102938, 'Futurama - O grande golpe de Bender - parte 1', 195354818, 18374, 10000, '/Library/WebServer/Documents', 'Futurama_O_grande_golpe_de_Bender_parte_1.mp4');
insert into exchangedvalue values (547102, 'video', 'entertainment', NULL, 102938, 'Futurama - O grande golpe de Bender - parte 2', 192927129, 18374, 10000, '/Library/WebServer/Documents', 'Futurama_O_grande_golpe_de_Bender_parte_2.mp4');
insert into exchangedvalue values (156834, 'video', 'entertainment', NULL, 102938, 'Futurama - O grande golpe de Bender - parte 3', 190248823, 18374, 10000, '/Library/WebServer/Documents', 'Futurama_O_grande_golpe_de_Bender_parte_3.mp4');
insert into exchangedvalue values (481457, 'video', 'entertainment', NULL, 102938, 'Futurama - O grande golpe de Bender - parte 4', 200387952, 18374, 10000, '/Library/WebServer/Documents', 'Futurama_O_grande_golpe_de_Bender_parte_4.mp4');
insert into exchangedvalue values (334943, 'video', 'entertainment', NULL, 102938, 'Futurama - O grande golpe de Bender - parte 1 m4v', 122016459, 18374, 10000, '/Library/WebServer/Documents', 'Futurama_O_grande_golpe_de_Bender_parte_1.m4v');
insert into exchangedvalue values (647205, 'video', 'entertainment', NULL, 102938, 'Futurama - O grande golpe de Bender - parte 2 m4v', 117055538, 18374, 10000, '/Library/WebServer/Documents', 'Futurama_O_grande_golpe_de_Bender_parte_2.m4v');
insert into exchangedvalue values (356836, 'video', 'entertainment', NULL, 102938, 'Futurama - O grande golpe de Bender - parte 3 m4v', 112115742, 18374, 10000, '/Library/WebServer/Documents', 'Futurama_O_grande_golpe_de_Bender_parte_3.m4v');
insert into exchangedvalue values (381455, 'video', 'entertainment', NULL, 102938, 'Futurama - O grande golpe de Bender - parte 4 m4v', 126699336, 18374, 10000, '/Library/WebServer/Documents', 'Futurama_O_grande_golpe_de_Bender_parte_4.m4v');
select id, type, subtype, value, econtractid, title, size, producerid, ispid, location, filename from exchangedvalue;

select id, title, size, location from exchangedvalue;
select id, type, subtype, value, econtractid, title, size, producerid, ispid, location from exchangedvalue;
