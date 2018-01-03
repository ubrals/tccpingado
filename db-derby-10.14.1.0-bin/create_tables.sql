connect 'jdbc:derby:tccpingado;create=true';
connect 'jdbc:derby:tccpingado';
connect 'jdbc:derby:tccpingado;user=app;password=app';
connect 'jdbc:derby:tccpingado;user=sys;password=sys';
connect 'jdbc:derby://localhost:1527/tccpingado;create=true';
connect 'jdbc:derby://localhost:1527/tccpingado';


drop table "cryptoperson"
create table CRYPTOPERSON (
    ID int primary key,
    NAME varchar(512),
    WALLET varchar(512),
    ROLE varchar(8)
);
select id , name , wallet , role from cryptoperson;
insert into cryptoperson (id, name, wallet, role) values (10000, 'Mack Content Service Provider', 'a4e3dc902ea0b53', 'ISP');
insert into cryptoperson (id, name, wallet, role) values (18374, 'Computer Science Expert', '9e948dc3a4e9ab5', 'Producer');
insert into cryptoperson (id, name, wallet, role) values (29172, 'Andre Miguel', 'cd832ae95017e5d', 'Customer');
---
insert into cryptoperson values (10000, 'Mack Content Service Provider', 'a4e3dc902ea0b53', 'ISP'), (18374, 'Computer Science Expert', '9e948dc3a4e9ab5', 'Producer'), (29172, 'Andre Miguel', 'cd832ae95017e5d', 'Customer');

-----------------------------------
drop table "econtract"
create table ECONTRACT (
    ID int primary key,
    CONTENTID int,
    PARTYID1 int,
    PARTYID2 int,
    MICROFRACTION int,
    JITTIMETOSTART varchar(14),
    ENACTMENTVALID int,
    MANAGEMENTSTATUS int
);
select id, contentid, partyid1, partyid2, microfraction, jittimetostart, enactmentvalid, managementstatus from econtract;
                              ID,           CONTENTID,    
                                                    PARTYID1,    
                                                           PARTYID2,    
                                                                  MICROFRACTION,    
                                                                      JITTIMETOSTART, ENACTMENTVALID,    
                                                                                         MANAGEMENTSTATUS
insert into econtract values (102938,       918273, 18374, 10000, 60, '201801022345', 1, 1);
insert into econtract values (231562075273, 0,      888,   29172, 60, '0',            0, 0)

-----------------------------------
drop table "exchangedvalue"
create table EXCHANGEDVALUE (
    ID int primary key,
    TYPE varchar(128),
    SUBTYPE varchar(128),
    VALUE varchar(128),
    ECONTRACTID int,
    TITLE varchar(512),
    SIZE int,
    PRODUCERID int,
    ISPID int,
    LOCATION varchar(512),
    FILENAME varchar(512)
);
-- insert into exchangedvalue values (918273, 'P vs NP', 54254305, '/Library/WebServer/Documents');
insert into exchangedvalue values (918273, 'video', 'educational', NULL, 102938, 'P vs NP', 54254305, 18374, 10000, '/Library/WebServer/Documents', 'PvsNP_480p.mp4');
select id, type, subtype, value, econtractid, title, size, producerid, ispid, location, filename from exchangedvalue;

select id, title, size, location from exchangedvalue;
select id, type, subtype, value, econtractid, title, size, producerid, ispid, location from exchangedvalue;
