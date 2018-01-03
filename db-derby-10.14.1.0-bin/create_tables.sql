connect 'jdbc:derby:tccpingado;create=true';

create table exchangedvalue (
    id int primary key,
    type varchar(128),
    subType varchar(128),
    value varchar(128),
    econtractId int,
    title varchar(512),
    size int,
    producerId int,
    ispId int,
    location varchar(512),
    filename varchar(512)
);
-- insert into exchangedvalue values (918273, 'P vs NP', 54254305, '/Library/WebServer/Documents');
insert into exchangedvalue values (918273, 'video', 'educational', NULL, 102938, 'P vs NP', 54254305, 18374, 10000, '/Library/WebServer/Documents', 'PvsNP_480p.mp4');

select id, title, size, location from exchangedvalue;
select id, type, subType, value, econtractId, title, size, producerId, ispId, location from exchangedvalue;

create table econtract (
    id int primary key,
    contentId int,
    partyId1 int,
    partyId2 int,
    microFraction int,
    jitTimeToStart varchar(14),
    enactmentValid int,
    managementStatus int
);
select id, contentId, partyId1, partyId2, microFraction, jitTimeToStart, enactmentValid, managementStatus from econtract;
insert into econtract values (102938, 918273, 18374, 10000, 60, '201801022345', 1, 1);

create table cryptoperson (
    id int primary key,
    name varchar(512),
    wallet varchar(512),
    role varchar(8)
);
select id , name , wallet , role from cryptoperson;
insert into cryptoperson (id, name, wallet, role) values (10000, 'Mack Content Service Provider', 'a4e3dc902ea0b53', 'ISP');
insert into cryptoperson (id, name, wallet, role) values (18374, 'Computer Science Expert', '9e948dc3a4e9ab5', 'Producer');
insert into cryptoperson (id, name, wallet, role) values (29172, 'Andre Miguel', 'cd832ae95017e5d', 'Customer');
---
insert into cryptoperson (id, name, wallet, role) values (10000, 'Mack Content Service Provider', 'a4e3dc902ea0b53', 'ISP'), (18374, 'Computer Science Expert', '9e948dc3a4e9ab5', 'Producer'), (29172, 'Andre Miguel', 'cd832ae95017e5d', 'Customer');
