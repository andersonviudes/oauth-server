
insert into oauth_client_details (client_id, client_secret,
                                  resource_ids,
                                  scope,
                                  authorized_grant_types,
                                  web_server_redirect_uri, authorities,
                                  access_token_validity, refresh_token_validity,
                                  additional_information, autoapprove)
values ('viudes_mobile_app', 'pass',
        'user_client_resource,user_admin_resource',
        'owner',
        'authorization_code,password,refresh_token,implicit',
        null, null,
        900, 3600,
        '{}', null);

insert into permission (id, name)
values ('47f0e09c-1324-4f24-bf2f-d663c7279042', 'can_create_user'),
       ('97d15265-8916-4fd8-b78c-90249dc8e8dc', 'can_update_user'),
       ('c66bd23a-c0c1-467f-954c-8ae389d6c333', 'can_read_user'),
       ('fce48f0f-0413-4326-9a92-a78084e3958e', 'can_delete_user');

insert into role (id, name)
values ('abb71127-9100-4b90-aeee-08d68d7ef4ec', 'owner');

insert into permission_role (permission_id, role_id)
values ('47f0e09c-1324-4f24-bf2f-d663c7279042', 'abb71127-9100-4b90-aeee-08d68d7ef4ec'),
       ('97d15265-8916-4fd8-b78c-90249dc8e8dc', 'abb71127-9100-4b90-aeee-08d68d7ef4ec'),
       ('c66bd23a-c0c1-467f-954c-8ae389d6c333', 'abb71127-9100-4b90-aeee-08d68d7ef4ec'),
       ('fce48f0f-0413-4326-9a92-a78084e3958e', 'abb71127-9100-4b90-aeee-08d68d7ef4ec');

insert into "user" (id, name, pwd,
                    email, enabled, account_expired, credentials_expired, account_locked)
values ('aea6c140-5047-4273-a80e-9a8e21bd6904', 'viudes',
        '1234',
        'viudes@gmail.com', true, false, false, false);

insert into role_user (role_id, user_id)
values ('abb71127-9100-4b90-aeee-08d68d7ef4ec', 'aea6c140-5047-4273-a80e-9a8e21bd6904');
