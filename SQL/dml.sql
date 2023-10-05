--Admin creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address,
                         date_of_birth,
                         gender)
VALUES (1, 'John', 'Doe', 'johndoe@example.com', '1234567890', '123 Main St, City, Country', '1990-01-01', 'Male');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted,user_detail_id)
VALUES (1, 'admin', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 1);

--Gym Owner creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address,
                         date_of_birth,
                         gender)
VALUES (2, 'Jane', 'Doe', 'janedoe@example.com', '1234567890', '123 Main St, City, Country', '1990-01-01', 'Female');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted,user_detail_id)
VALUES (2, 'gymowner', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 2);


INSERT INTO role (role_id, role_name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO role (role_id, role_name) VALUES (2, 'ROLE_MANAGER');
INSERT INTO role (role_id, role_name) VALUES (3, 'ROLE_EMPLOYEE');
INSERT INTO role (role_id, role_name) VALUES (4, 'ROLE_MEMBER');


INSERT INTO user_role (user_role_id, user_id, role_id) VALUES (1, 1, 1);
INSERT INTO user_role (user_role_id, user_id, role_id) VALUES (2, 2, 2);

--Gym Department Creation
--Create Department Status
INSERT INTO mst_kbn (mst_kbn_name, mst_kbn_key, mst_kbn_value) VALUES ('DEPARTMENT_STATUS', 1, 'Hoạt Động');
INSERT INTO mst_kbn (mst_kbn_name, mst_kbn_key, mst_kbn_value) VALUES ('DEPARTMENT_STATUS', 2, 'Không Hoạt Động');

--Create Department
INSERT INTO gym_department (gym_department_id, gym_department_status_key, user_id,
                            name, address, contact_number, logo_url, opening_hours, image_url, description)
VALUES (1, 1, 2, 'Gym A', '123 Main St, City, State 12345', '555-123-4567',
        'https://example.com/logo.png', 'Mon-Fri 6am-10pm, '  'Sat-Sun 8am-8pm',
        'https://example.com/gym.jpg', 'This is an example gym department');

INSERT INTO gym_department
(gym_department_id, gym_department_status_key, user_id, name, address, contact_number)
VALUES
    (2, 1, 2, 'Gym 2', '124 Main St', '555-123-4568');

INSERT INTO gym_department
SET
    gym_department_id = 3,
gym_department_status_key = 1,
user_id = 2,
name = 'Gym 3',
address = '125 Main St',
contact_number = '555-123-4569';

INSERT INTO gym_department
SET
    gym_department_id = 4,
gym_department_status_key = 1,
user_id = 2,
name = 'Gym 4',
address = '126 Main St',
contact_number = '555-123-4570';

INSERT INTO gym_department
SET
    gym_department_id = 5,
gym_department_status_key = 1,
user_id = 2,
name = 'Gym 5',
address = '127 Main St',
contact_number = '555-123-4571';