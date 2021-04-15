create table Album
(
    id          bigint identity
        primary key,
    releaseDate datetime2,
    title       varchar(255)
)
go

create table Artist
(
    id        bigint identity
        primary key,
    firstName varchar(255),
    lastName  varchar(255),
    pseudonym varchar(255)
)
go

create table Track
(
    id          bigint identity
        primary key,
    releaseDate datetime2,
    title       varchar(255),
    album_id    bigint
        constraint FKbvgarccx945r5j9owhpoyh2hx
            references Album
)
go

create table Artist_Track
(
    artist_id bigint not null
        constraint FKi3gnbikqa8568lxmvw341b6lf
            references Artist,
    track_id  bigint not null
        constraint FKmoclej7u6v46a6h1las3g6oxp
            references Track,
    primary key (artist_id, track_id)
)
go

