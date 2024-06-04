create table oauth_client_details
(
    client_id               varchar(255)         not null primary key,
    client_secret           varchar(255) not null,
    resource_ids            varchar(255)  default null,
    scope                   varchar(255)  default null,
    authorized_grant_types  varchar(255)  default null,
    web_server_redirect_uri varchar(255)  default null,
    authorities             varchar(255)  default null,
    access_token_validity   integer       default null,
    refresh_token_validity  integer       default null,
    additional_information  varchar(4096) default null,
    autoapprove             varchar(255)  default null
);

create table permission
(
    id   UUID primary key,
    name varchar(60) unique
);

create table role
(
    id   UUID primary key,
    name varchar(60) unique
);

create table permission_role
(
    permission_id UUID,
    foreign key (permission_id) references permission (id),
    role_id       UUID,
    foreign key (role_id) references role (id)
);

create table "user"
(
    id                  UUID primary key,
    name                varchar(225) unique not null,
    pwd                 varchar(255)        not null,
    email               varchar(255)        not null,
    enabled             boolean             not null default false,
    account_expired     boolean             not null default false,
    credentials_expired boolean             not null default false,
    account_locked      boolean             not null default false
);

create table role_user
(
    role_id UUID,
    foreign key (role_id) references role (id),
    user_id UUID,
    foreign key (user_id) references "user" (id)
);
