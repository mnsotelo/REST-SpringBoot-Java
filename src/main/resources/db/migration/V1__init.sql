create table calendar_event
(
    id         bigserial
        primary key,
    created_at timestamp(6) with time zone not null,
    date       timestamp(6)                not null,
    name       varchar(255)                not null
);

INSERT INTO calendar_event(name, created_at, date) VALUES
('Renovacion pasaporte', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Date with X', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Walk dogs', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Movie with mom', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
;

