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
    location varchar(512)
);
insert into exchangedvalue values (918273, 'P vs NP', 54254305, '/Library/WebServer/Documents');
insert into exchangedvalue values (918273, 'video', 'educational', NULL, 102938, 'P vs NP', 54254305, 283746, 10, '/Library/WebServer/Documents');

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

create table cryptoperson (
    id int primary key,
    name varchar(512),
    wallet varchar(512),
    role varchar(8)
);
select id , name , wallet , role from cryptoperson;
