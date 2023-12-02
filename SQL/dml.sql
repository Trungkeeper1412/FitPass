/********** Role Creation ***********/
INSERT INTO role (role_id, role_name)
VALUES (1, 'ADMIN');
INSERT INTO role (role_id, role_name)
VALUES (2, 'GYM_OWNER');
INSERT INTO role (role_id, role_name)
VALUES (3, 'EMPLOYEE');
INSERT INTO role (role_id, role_name)
VALUES (4, 'USER');
INSERT INTO role (role_id, role_name)
VALUES (5, 'BRAND_OWNER');

/********** User Creation ***********/
-- Admin creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address,
                         date_of_birth,
                         gender, image_url,securityId)
VALUES (1, 'John', 'Doe', 'johndoe@example.com', '1234567890', '123 Main St',
        '1990-01-01', 'Male', '/images/system/v.png',568743029172);

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id)
VALUES (1, 'admin', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 1);
-- Brand Owner Creation
INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, created_by)
VALUES (6, 'brandOwner1', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 1),
       (7, 'brandOwner2', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 1),
       (8, 'brandOwner3', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 1),
       (9, 'brandOwner4', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 1),
       (10, 'brandOwner5', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 1),
       (11, 'brandOwner6', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 1),
       (12, 'brandOwner7', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 1),
       (13, 'brandOwner8', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 1),
       (14, 'brandOwner9', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 1),
       (15, 'brandOwner10', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 1),
       (16, 'brandOwner11', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 1),
       (17, 'brandOwner12', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 1),
       (18, 'brandOwner13', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 1);
-- Gym Owner creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address,
                         date_of_birth,
                         gender, image_url,securityId)
VALUES (2, 'Jane', 'Doe', 'janedoe@example.com', '1234567890', '123 Main St',
        '1990-01-01', 'Female', '/images/system/v.png',568743029172);

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (2, 'gymowner', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 2, 15);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url,securityId)
VALUES (19, 'Nguyễn', 'Văn A', 'gymowner1@example.com', '1234567890', '123 Main St', '1990-01-01', 'Male',
        '/images/system/v.png',568743029172);

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (19, 'gymowner1', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 19, 6);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url,securityId)
VALUES (20, 'Trần', 'Thị B', 'gymowner2@example.com', '2345678901', '456 Oak St', '1985-07-15', 'Female',
        '/images/system/v.png',568743029172);

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (20, 'gymowner2', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 20, 6);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url,securityId)
VALUES (21, 'Lê', 'Văn C', 'gymowner3@example.com', '3456789012', '789 Elm St', '1992-03-25', 'Male',
        '/images/system/v.png',568743029172);

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (21, 'gymowner3', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 21, 6);

-- Gym Owner Creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url,securityId)
VALUES (22, 'Ngô', 'Thị D', 'gymowner4@example.com', '4567890123', '789 Elm St', '1995-09-12', 'Female',
        '/images/system/v.png',568743029172);

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (22, 'gymowner4', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 22, 6);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url,securityId)
VALUES (23, 'Hoàng', 'Văn E', 'gymowner5@example.com', '5678901234', '567 Pine St', '1988-11-30', 'Male',
        '/images/system/v.png',568743029172);

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (23, 'gymowner5', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 23, 7);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url,securityId)
VALUES (24, 'Đặng', 'Thị F', 'gymowner6@example.com', '6789012345', '890 Maple St', '1993-06-20', 'Female',
        '/images/system/v.png',568743029172);

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (24, 'gymowner6', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 24, 7);

-- Gym Owner Creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url,securityId)
VALUES (25, 'Phạm', 'Văn G', 'gymowner7@example.com', '7890123456', '123 Cherry St', '1991-04-18', 'Male',
        '/images/system/v.png',568743029172);

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (25, 'gymowner7', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 25, 7);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url,securityId)
VALUES (26, 'Vũ', 'Thị H', 'gymowner8@example.com', '8901234567', '456 Walnut St', '1987-02-09', 'Female',
        '/images/system/v.png',568743029172);

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (26, 'gymowner8', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 26, 7);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url,securityId)
VALUES (27, 'Nguyễn', 'Văn I', 'gymowner9@example.com', '9012345678', '789 Walnut St', '1994-12-05', 'Male',
        '/images/system/v.png',568743029172);

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (27, 'gymowner9', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 27, 8);

-- Gym Owner Creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url,securityId)
VALUES (28, 'Hồ', 'Thị J', 'gymowner10@example.com', '0123456789', '123 Oak St', '1996-08-22', 'Female',
        '/images/system/v.png',568743029172);

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (28, 'gymowner10', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 28, 8);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url,securityId)
VALUES (29, 'Trương', 'Văn K', 'gymowner11@example.com', '1234567890', '456 Maple St', '1997-11-13', 'Male',
        '/images/system/v.png',568743029172);

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (29, 'gymowner11', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 29, 8);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url,securityId)
VALUES (30, 'Lý', 'Thị L', 'gymowner12@example.com', '2345678901', '789 Pine St', '1998-05-26', 'Female',
        '/images/system/v.png',568743029172);

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (30, 'gymowner12', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 30, 9);
-- Gym Owner Creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url,securityId)
VALUES (31, 'Trần', 'Văn M', 'gymowner13@example.com', '3456789012', '123 Elm St', '1999-02-28', 'Male',
        '/images/system/v.png',568743029172);

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (31, 'gymowner13', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 31, 9);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (32, 'Nguyễn', 'Thị N', 'gymowner14@example.com', '4567890123', '456 Cherry St', '2000-09-15', 'Female',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (32, 'gymowner14', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 32, 9);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (33, 'Đỗ', 'Văn P', 'gymowner15@example.com', '5678901234', '789 Oak St', '2001-04-10', 'Male',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (33, 'gymowner15', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 33, 10);
-- Gym Owner Creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (34, 'Lê', 'Thị Q', 'gymowner16@example.com', '6789012345', '123 Pine St', '2002-11-25', 'Female',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (34, 'gymowner16', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 34, 10);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (35, 'Hoàng', 'Văn R', 'gymowner17@example.com', '7890123456', '456 Elm St', '2003-08-15', 'Male',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (35, 'gymowner17', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 35, 10);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (36, 'Nguyễn', 'Thị S', 'gymowner18@example.com', '8901234567', '789 Cherry St', '2004-05-10', 'Female',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (36, 'gymowner18', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 36, 11);

-- Gym Owner Creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (37, 'Trần', 'Văn T', 'gymowner19@example.com', '9012345678', '123 Walnut St', '2005-02-28', 'Male',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (37, 'gymowner19', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 37, 12);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (38, 'Phạm', 'Thị U', 'gymowner20@example.com', '0123456789', '456 Pine St', '2006-09-15', 'Female',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (38, 'gymowner20', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 38, 12);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (39, 'Nguyễn', 'Văn V', 'gymowner21@example.com', '1234567890', '789 Oak St', '2007-04-10', 'Male',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (39, 'gymowner21', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 39, 12);

-- Gym Owner Creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (40, 'Ngô', 'Thị X', 'gymowner22@example.com', '2345678901', '123 Maple St', '2008-11-25', 'Female',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (40, 'gymowner22', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 40, 13);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (41, 'Vũ', 'Văn Y', 'gymowner23@example.com', '3456789012', '456 Walnut St', '2009-08-15', 'Male',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (41, 'gymowner23', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 41, 13);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (42, 'Hoàng', 'Thị Z', 'gymowner24@example.com', '4567890123', '789 Elm St', '2010-05-10', 'Female',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (42, 'gymowner24', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 42, 13);

-- Gym Owner Creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (43, 'Trần', 'Thị A', 'gymowner25@example.com', '5678901234', '123 Oak St', '2011-02-28', 'Female',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (43, 'gymowner25', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 43, 13);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (44, 'Nguyễn', 'Văn B', 'gymowner26@example.com', '6789012345', '456 Cherry St', '2012-09-15', 'Male',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (44, 'gymowner26', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 44, 14);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (45, 'Phạm', 'Thị C', 'gymowner27@example.com', '7890123456', '789 Walnut St', '2013-04-10', 'Female',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (45, 'gymowner27', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 45, 14);
-- Gym Owner Creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (46, 'Lê', 'Văn F', 'gymowner30@example.com', '9012345678', '123 Maple St', '1995-08-20', 'Male',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (46, 'gymowner28', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 46, 15);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (47, 'Hoàng', 'Thị G', 'gymowner31@example.com', '0123456789', '456 Cherry St', '1998-04-15', 'Female',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (47, 'gymowner29', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 47, 15);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (48, 'Nguyễn', 'Văn H', 'gymowner32@example.com', '1234567890', '789 Elm St', '2000-01-10', 'Male',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (48, 'gymowner30', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 48, 15);

-- Gym Owner Creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (49, 'Nguyen', 'Thị I', 'gymowner33@example.com', '2345678901', '789 Oak St', '1992-03-25', 'Female',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (49, 'gymowner31', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 49, 16);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (50, 'Tran', 'Văn J', 'gymowner34@example.com', '3456789012', '456 Walnut St', '1994-11-12', 'Male',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (50, 'gymowner32', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 50, 16);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (51, 'Pham', 'Thị K', 'gymowner35@example.com', '4567890123', '789 Maple St', '1997-07-05', 'Female',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (51, 'gymowner33', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 51, 16);

-- Gym Owner Creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (52, 'Nguyen', 'Van L', 'gymowner36@example.com', '5678901234', '123 Oak St', '1988-09-18', 'Male',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (52, 'gymowner34', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 52, 16);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (53, 'Tran', 'Thi M', 'gymowner37@example.com', '6789012345', '456 Cherry St', '1991-05-31', 'Female',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (53, 'gymowner35', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 53, 17);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (54, 'Pham', 'Van N', 'gymowner38@example.com', '7890123456', '789 Walnut St', '1986-12-10', 'Male',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (54, 'gymowner36', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 54, 17);

-- Gym Owner Creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (55, 'Trần', 'Thị O', 'gymowner39@example.com', '8901234567', '123 Elm St', '1993-06-28', 'Female',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (55, 'gymowner37', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 55, 17);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (56, 'Lê', 'Văn P', 'gymowner40@example.com', '9012345678', '456 Oak St', '1990-02-15', 'Male',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (56, 'gymowner38', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 56, 17);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (57, 'Nguyễn', 'Thị Q', 'gymowner41@example.com', '0123456789', '789 Cherry St', '1996-09-10', 'Female',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (57, 'gymowner39', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 57, 18);

-- Gym Owner Creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (58, 'Nguyen', 'Van R', 'gymowner42@example.com', '2345678901', '123 Walnut St', '1994-07-22', 'Male',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (58, 'gymowner40', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 58, 18);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (59, 'Tran', 'Thi S', 'gymowner43@example.com', '3456789012', '456 Maple St', '1997-03-12', 'Female',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (59, 'gymowner41', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 59, 18);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (60, 'Pham', 'Van T', 'gymowner44@example.com', '4567890123', '789 Oak St', '1999-10-05', 'Male',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (60, 'gymowner42', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 60, 18);

-- Gym Owner Creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (61, 'Nguyen', 'Thị U', 'gymowner45@example.com', '5678901234', '123 Cherry St', '1992-08-15', 'Female',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (61, 'gymowner43', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 61, 18);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (62, 'Tran', 'Văn V', 'gymowner46@example.com', '6789012345', '456 Elm St', '1995-11-28', 'Male',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (62, 'gymowner44', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 62, 18);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender,
                         image_url)
VALUES (63, 'Pham', 'Thị X', 'gymowner47@example.com', '7890123456', '789 Walnut St', '1998-04-10', 'Female',
        '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id, created_by)
VALUES (63, 'gymowner45', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 63, 18);

-- Employee creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address,
                         date_of_birth,
                         gender, image_url)
VALUES (3, 'Nguyen Van Bao', 'Linh', 'linhnvb2@fpt.com', '0987654321', '123 Main St',
        '2001-01-01', 'Male', '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id)
VALUES (3, 'employee', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 3);

-- User creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address,
                         date_of_birth,
                         gender, image_url)
VALUES (4, 'Le Dinh', 'Tuan', 'tuana1@gmail.com', '0987654321', '123 Main St',
        '2001-01-01', 'Male', '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id)
VALUES (4, 'tuanld', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 4);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address,
                         date_of_birth,
                         gender, image_url)
VALUES (5, 'Dinh', 'Tuan Anh', 'tuananh@gmail.com', '0987654321', '123 Main St',
        '1995-01-01', 'Male', '/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id)
VALUES (5, 'anhdt', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 5);

-- User 64
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender, image_url)
VALUES (64, 'Nguyễn', 'Trần', 'nguyentran@example.com', '0987654321', '123 Đường Trần Phú', '1990-01-01', 'Male', '/images/system/v.png');
INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id)
VALUES (64, 'nguyentran', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 64);

-- User 65
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender, image_url)
VALUES (65, 'Nguyễn', 'Đặng', 'nguyendang@example.com', '0987654321', '456 Đường Nguyễn Văn Linh', '1991-02-02', 'Female', '/images/system/v.png');
INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id)
VALUES (65, 'nguyendang', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 65);

-- User 66
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender, image_url)
VALUES (66, 'Trần', 'Lê', 'tranle@example.com', '0987654321', '789 Đường Trần Hưng Đạo', '1992-03-03', 'Male', '/images/system/v.png');
INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id)
VALUES (66, 'tranle', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 66);

-- User 67
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender, image_url)
VALUES (67, 'Lê', 'Phạm', 'lepham@example.com', '0987654321', '123 Đường Lê Lợi', '1993-04-04', 'Female', '/images/system/v.png');
INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id)
VALUES (67, 'lepham', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 67);

-- User 68
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender, image_url)
VALUES (68, 'Phạm', 'Hoàng', 'phamhoang@example.com', '0987654321', '456 Đường Phạm Văn Đồng', '1994-05-05', 'Male', '/images/system/v.png');
INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id)
VALUES (68, 'phamhoang', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 68);

-- User 69
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender, image_url)
VALUES (69, 'Hoàng', 'Nguyễn', 'hoangnguyen@example.com', '0987654321', '789 Đường Hoàng Văn Thụ', '1995-06-06', 'Female', '/images/system/v.png');
INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id)
VALUES (69, 'hoangnguyen', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 69);

-- User 70
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender, image_url)
VALUES (70, 'Vũ', 'Nguyễn', 'vunguyen@example.com', '0987654321', '123 Đường Vũ Trọng Phụng', '1996-07-07', 'Male', '/images/system/v.png');
INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id)
VALUES (70, 'vunguyen', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 70);

-- User 71
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender, image_url)
VALUES (71, 'Nguyễn', 'Lý', 'nguyenly@example.com', '0987654321', '456 Đường Lý Thường Kiệt', '1997-08-08', 'Female', '/images/system/v.png');
INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id)
VALUES (71, 'nguyenly', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 71);

-- User 72
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender, image_url)
VALUES (72, 'Lý', 'Trần', 'lytran@example.com', '0987654321', '789 Đường Trần Quang Diệu', '1998-09-09', 'Male', '/images/system/v.png');
INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id)
VALUES (72, 'lytran', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 72);

-- User 73
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender, image_url)
VALUES (73, 'Trần', 'Đặng', 'trandang@example.com', '0987654321', '123 Đường Đặng Thai Mai', '1999-10-10', 'Female', '/images/system/v.png');
INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id)
VALUES (73, 'trandang', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 73);

-- User 74
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender, image_url)
VALUES (74, 'Đặng', 'Bùi', 'dangbui@example.com', '0987654321', '456 Đường Bùi Thị Xuân', '2000-11-11', 'Male', '/images/system/v.png');
INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id)
VALUES (74, 'dangbui', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 74);

-- User 75
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address, date_of_birth, gender, image_url)
VALUES (75, 'Phan', 'Ngô', 'phango@example.com', '0987654321', '123 Đường Ngô Gia Tự', '2002-01-01', 'Male', '/images/system/v.png');
INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted, user_detail_id)
VALUES (75, 'phanngo', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 75);

-- Assign role for users
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (1, 1, 1);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (2, 2, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (3, 3, 3);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (4, 4, 4);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (5, 5, 4);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES
    (64, 64, 4),
    (65, 65, 4),
    (66, 66, 4),
    (67, 67, 4),
    (68, 68, 4),
    (69, 69, 4),
    (70, 70, 4),
    (71, 71, 4),
    (72, 72, 4),
    (73, 73, 4),
    (74, 74, 4),
    (75, 75, 4);

-- Assign role for brand owner
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (6, 6, 5);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (7, 7, 5);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (8, 8, 5);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (9, 9, 5);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (10, 10, 5);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (11, 11, 5);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (12, 12, 5);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (13, 13, 5);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (14, 14, 5);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (15, 15, 5);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (16, 16, 5);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (17, 17, 5);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (18, 18, 5);

-- Assign role for gym owner
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (19, 19, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (20, 20, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (21, 21, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (22, 22, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (23, 23, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (24, 24, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (25, 25, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (26, 26, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (27, 27, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (28, 28, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (29, 29, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (30, 30, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (31, 31, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (32, 32, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (33, 33, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (34, 34, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (35, 35, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (36, 36, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (37, 37, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (38, 38, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (39, 39, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (40, 40, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (41, 41, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (42, 42, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (43, 43, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (44, 44, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (45, 45, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (46, 46, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (47, 47, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (48, 48, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (49, 49, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (50, 50, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (51, 51, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (52, 52, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (53, 53, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (54, 54, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (55, 55, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (56, 56, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (57, 57, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (58, 58, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (59, 59, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (60, 60, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (61, 61, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (62, 62, 2);
INSERT INTO user_role (user_role_id, user_id, role_id)
VALUES (63, 63, 2);

-- User Wallet insert
INSERT INTO wallet (user_id, balance)
VALUES (1, 20000);
INSERT INTO wallet (user_id, balance)
VALUES (2, 20000);
INSERT INTO wallet (user_id, balance)
VALUES (3, 0);
INSERT INTO wallet (user_id, balance)
VALUES (4, 1000);
INSERT INTO wallet (user_id, balance)
VALUES (5, 1000);
INSERT INTO wallet (user_id, balance)
VALUES
    (64, 2500),
    (65, 3000),
    (66, 4000),
    (67, 3500),
    (68, 2200),
    (69, 4800),
    (70, 2700),
    (71, 4200),
    (72, 3900),
    (73, 2100),
    (74, 4700),
    (75, 3200);

-- Brand Owner Wallet insert
INSERT INTO wallet (user_id, balance)
VALUES (6, 20000);
INSERT INTO wallet (user_id, balance)
VALUES (7, 2000);
INSERT INTO wallet (user_id, balance)
VALUES (8, 10000);
INSERT INTO wallet (user_id, balance)
VALUES (9, 1000);
INSERT INTO wallet (user_id, balance)
VALUES (10, 20000);
INSERT INTO wallet (user_id, balance)
VALUES (11, 20000);
INSERT INTO wallet (user_id, balance)
VALUES (12, 20000);
INSERT INTO wallet (user_id, balance)
VALUES (13, 20000);
INSERT INTO wallet (user_id, balance)
VALUES (14, 20000);
INSERT INTO wallet (user_id, balance)
VALUES (15, 20000);
INSERT INTO wallet (user_id, balance)
VALUES (16, 20000);
INSERT INTO wallet (user_id, balance)
VALUES (17, 20000);
INSERT INTO wallet (user_id, balance)
VALUES (18, 20000);

INSERT INTO wallet (user_id, balance)
VALUES (19, 20000);

INSERT INTO wallet (user_id, balance)
VALUES
    (20, 0),
    (21, 0),
    (22, 0),
    (23, 0),
    (24, 0),
    (25, 0),
    (26, 0),
    (27, 0),
    (28, 0),
    (29, 0),
    (30, 0),
    (31, 0),
    (32, 0),
    (33, 0),
    (34, 0),
    (35, 0),
    (36, 0),
    (37, 0),
    (38, 0),
    (39, 0),
    (40, 0),
    (41, 0),
    (42, 0),
    (43, 0),
    (44, 0),
    (45, 0),
    (46, 0),
    (47, 0),
    (48, 0),
    (49, 0),
    (50, 0),
    (51, 0),
    (52, 0),
    (53, 0),
    (54, 0),
    (55, 0),
    (56, 0),
    (57, 0),
    (58, 0),
    (59, 0),
    (60, 0),
    (61, 0),
    (62, 0),
    (63, 0);
/********** Brand Creation ***********/
-- Create brand infos
INSERT INTO brand (brand_id, user_id, name, logo_url, wallpaper_url, thumbnail_url, description, rating, contact_number,
                   contact_email, brand_status_key)
VALUES (5, 6, 'Citi Gym',
        '/img/brands/logos/5_logo.jpg',
        '/img/brands/wallpapers/5_wallpaper.jpg',
        '/img/brands/thumbnails/5_thumbnail.jpg',
        'Thành lập từ 2018, Citigym đã phục vụ hơn 100.000 khách hàng. Với hệ thống phòng tập đầy đủ tiện nghi và nhiều chi nhánh, vận hành các khu phức hợp thể thao.

  Citigym mang đến cho học viên hơn 45 bộ môn tập luyện như Gym,Yoga, các lớp tập nhóm Lesmill bản quyền Quốc tế cùng huấn luyện viên hướng dẫn tận tình,…

  Ngoài ra, nếu sở hữu thẻ tập hội viên, bạn được quyền đến bất kỳ phòng tập tại TPHCM của thương hiệu để luyện tập thể thao và chăm sóc sức khỏe.',
        4, '1900633638', 'marketing@citigym.com.vn', 1),

       (6, 7, 'California Fitness & Yoga',
        '/img/brands/logos/6_logo.jpg',
        '/img/brands/wallpapers/6_wallpaper.jpg',
        '/img/brands/thumbnails/6_thumbnail.jpg',
        'Là thương hiệu sức khỏe lớn nhất tại Việt Nam. Đã sở hữu hơn 35 câu lạc bộ khắp cả nước. Đến với California, bạn sẽ được tận hưởng môi trường luyện tập đẳng cấp với trang thiết bị tốt nhất.

  Ngoài gym, tại phòng tập California, bạn sẽ được thỏa sức “phiêu” Pop dance, Sexy dance, Pole dance,…hoặc thực hành chuẩn xác yoga dưới sự hướng dẫn của thầy yoga đến từ Ấn Độ.',
        4.5, '02873082277', 'cali@gmail.com', 1),


       (7, 8, 'Fit 24 – Fitness & Yoga Center',
        '/img/brands/logos/7_logo.jpg',
        '/img/brands/wallpapers/7_wallpaper.jpg',
        '/img/brands/thumbnails/7_thumbnail.jpg',
        'Bạn sẽ được trải nghiệm đa dạng các lớp từ Dance cho đến Yoga, được hướng dẫn bài bản để học viên đạt được mục tiêu trong thời gian ngắn nhất.

  Trong đó, Yoga sẽ được hướng dẫn bởi master Ấn Độ và Việt Nam giàu kinh nghiệm, tận tâm, gym cùng các chuyên gia hàng đầu. FIT24 tổ chức cả lớp nhảy dành cho các bạn nhỏ.',
        4.2, '1900262442', 'fit24@gmail.com', 1);


INSERT INTO brand_amenities (amenitie_id, brand_id, photo_url, amenitie_name, description, amenitie_status)
VALUES (1, 5, '/img/brandAmenities/1701103600069_brandAmenities.jpg', 'MÁY TẬP NHẬP KHẨU',
        'Hệ thống máy tập nhập khẩu từ các thương hiệu nổi tiếng nước ngoài Technogym, LifeFitness, Escape, Reebok, Octane, Les Mills.',
        1),
       (2, 5, '/img/brandAmenities/1701103808800_brandAmenities.jpg', 'HLV CHUYÊN NGHIỆP',
        'Đội ngũ huấn luyện viên chuyên nghiệp, lắng nghe khách hàng, đồng hành cùng hội viên vượt qua những thử thách về hình thể và sức khỏe.',
        1),
       (3, 5, '/img/brandAmenities/1701103823819_brandAmenities.jpg', 'HỆ THỐNG TỦ VÀ KHÓA',
        'Hệ thống khóa từ thông minh bảo mật tối đa Esmart Lock đầu tiên tại Việt Nam. Hội viên có thể yên tâm gửi đồ và khóa tủ bằng vòng tay locker tự động',
        1),
       (4, 5, '/img/brandAmenities/1701103988437_brandAmenities.jpg', 'PHÒNG TẬP ĐA NĂNG',
        'Khu vực Functional đa dạng các loại máy tập Technogym, tích hợp các kênh truyền hình giải trí, kết nối Wifi và Bluetooth, sở hữu Cloud lưu trữ tiến trình tập luyện.',
        1),
       (5, 5, '/img/brandAmenities/1701103971478_brandAmenities.jpg', 'TIỆN NGHI PHÒNG TẬP',
        'Được trang bị đầy đủ tiện nghi 5 sao (quầy giải khát, phòng xông hơi, khu vực trang điểm, phòng tắm), là nơi mang đến giây phút thư giãn cho bạn.',
        1),
       (6, 5,
        '/img/brandAmenities/1701103834502_brandAmenities.jpg',
        'VƯỜN YOGA THƯ THÁI',
        'Lớp học yoga buổi sáng được tổ chức ngoài trời để hội viên được hít thở không khí trong lành và tươi mát của cây xanh, mang lại tinh thần thư thái cho hội viên.',
        1),







       (7, 6, '/img/brandAmenities/1701104592515_brandAmenities.jpg', 'PHÒNG TẬP THỂ DỤC',
        'Cung cấp các thiết bị hiện đại để tập luyện cardio và tăng cường cơ bắp. Phòng tập chuyên biệt cho các hoạt động như tập TRX, tập luyện chịu lực, và các lớp tập luyện nhóm.',
        1),
       (8, 6, '/img/brandAmenities/1701104637094_brandAmenities.jpg', 'LỚP HỌP YOGA',
        'Cung cấp nhiều loại hình yoga như Hatha, Vinyasa, Ashtanga, và Yoga dành cho người mới bắt đầu. Lớp yoga đặc biệt như Hot Yoga (yoga trong phòng nhiệt độ cao) hoặc Aerial Yoga (yoga trên dây đu).',
        1),
       (9, 6, '/img/brandAmenities/1701104646969_brandAmenities.jpg', 'LỚP HỌC NHÓM',
        'Các buổi tập luyện nhóm như Zumba, Pilates, và các loại lớp tập khác.Các buổi hướng dẫn từ giáo viên chuyên nghiệp..',
        1),
       (10, 6, '/img/brandAmenities/1701104662440_brandAmenities.jpg', 'HƯỚNG DẪN CÁ NHÂN',
        'Cung cấp dịch vụ hướng dẫn tập luyện cá nhân để cá nhân hóa chương trình tập luyện.', 1),
       (11, 6, '/img/brandAmenities/1701104669542_brandAmenities.jpg', 'KHU VỰC XÔNG HƠI VÀ SPA',
        'Các tiện nghi như phòng xông hơi, sauna, và các liệu pháp spa để thư giãn và tái tạo năng lượng.', 1),
       (12, 6, '/img/brandAmenities/1701104679085_brandAmenities.jpg', 'TRUNG TÂM DINH DƯỠNG',
        'Cung cấp tư vấn dinh dưỡng và chế độ ăn lành mạnh để hỗ trợ mục tiêu tập luyện của khách hàng.', 1),







       (13, 7, '/img/brandAmenities/1701105355258_brandAmenities.jpg', 'GROUPX-DANCE',
        'Các bộ môn luyện tập theo nhóm như Zumba, BodyCombat, Body pump, B&T… trên nền nhạc sôi động sẽ mang cho bạn những sẽ mang đến cho bạn những buổi tập năng lượng và vui tươi.',
        1),
       (14, 7, '/img/brandAmenities/1701105361944_brandAmenities.jpg', 'CÔNG NGHỆ EMS',
        'Máy EMS tại FIT24 là dòng máy tập luyện bằng công nghệ hiện đại đến từ Đức, đây là một phương pháp tập luyện an toàn, hiệu quả trong việc tăng cơ giảm mỡ, cải thiện vóc dáng và sức khỏe nhanh chóng cho người bận rộn.',
        1),
       (15, 7, '/img/brandAmenities/1701105371125_brandAmenities.jpg', 'PERSONAL TRAINER',
        'Tập luyện với Huấn luyện viên cá nhân theo hình thức 1 kèm 1, được thiết kế đặc biệt phù hợp với thể trạng và mục tiêu thể hình mà bạn mong muốn.',
        1),
       (16, 7, '/img/brandAmenities/1701105377379_brandAmenities.jpg', 'LESMILLS',
        'Lesmill là các bộ môn tập luyện có bản quyền và vô cùng hiệu quả tại FIT24. Với mục đích cuối cùng: giúp người tập khỏe mạnh, vui vẻ, sảng khoái; giải phóng năng lượng, giảm mỡ & thân hình thon gọn, cân đối.',
        1),
       (17, 7, '/img/brandAmenities/1701105382902_brandAmenities.jpg', 'CĂNG CƠ – TRỊ LIỆU',
        'FIT24 với các Master chuyên môn cao không những giúp bạn phục hồi cơ thể sau những giờ tập luyện mà còn hỗ trợ trị liệu về các vấn đề xương khớp mà bạn mắc phải.',
        1);


/********** Gym Department Creation ***********/
-- Create Gym Department Status
INSERT INTO mst_kbn (mst_kbn_name, mst_kbn_key, mst_kbn_value)
VALUES ('DEPARTMENT_STATUS', 1, 'Hoạt Động');
INSERT INTO mst_kbn (mst_kbn_name, mst_kbn_key, mst_kbn_value)
VALUES ('DEPARTMENT_STATUS', 2, 'Không Hoạt Động');
INSERT INTO mst_kbn (mst_kbn_name, mst_kbn_key, mst_kbn_value)
VALUES ('BRAND_STATUS', 1, 'Hoạt Động');
INSERT INTO mst_kbn (mst_kbn_name, mst_kbn_key, mst_kbn_value)
VALUES ('BRAND_STATUS', 2, 'Không Hoạt Động');


-- Create Gym Department infos
INSERT INTO gym_department (gym_department_id, brand_id, user_id, gym_department_status_key, name, address,
                            contact_number,
                            logo_url, wallpaper_url, thumbnail_url, description, latitude, longitude,
                            capacity, area,city)
VALUES (1, 5, 19, 1, 'CITIGYM THÀNH THÁI', ' 52 Thành Thái, Phường 12, Quận 10, Thành phố Hồ Chí Minh', '1900633638',
        '/img/departments/logos/1_logo.jpg',
        '/img/departments/wallpapers/1_wallpaper.jpg',
        '/img/departments/thumbnails/1_thumbnail.jpg',
        'Phòng gym quận 10 Citigym Thành Thái là phòng tập đẳng cấp được khai trương đầu tiên của hệ thống Citigym tọa lạc tại trung tâm quận 10. Nơi đây có không gian luyện tập mang hơi thở thiên nhiên năng động, thoải mái, đem đến cho bạn cảm giác thật hào hứng, sảng khoái khi tập luyện. Phòng tập của Citigym Thành Thái được trang bị đầy đủ các thiết bị hiện đại, cao cấp từ những thương hiệu hàng đầu thế giới như Technogym, LifeFitness, Escape, Reebok, Octane, Les Mills. Đây cũng là hệ thống phòng tập đầu tiên tại TP HCM sử dụng dụng cụ tạ đòn, bục nhảy SmartBar, SmartStep của LesMills.',
        10.770294326858476, 106.66638328225508, 200, 5500,'Hồ Chí Minh'),

       (2, 5, 20, 1, 'CITIGYM PHỔ QUANG', '119 Phổ Quang, Phường 09, Quận Phú Nhuận, Thành phố Hồ Chí Minh',
        '1900633638',
        '/img/departments/logos/2_logo.jpg',
        '/img/departments/wallpapers/2_wallpaper.jpg',
        '/img/departments/thumbnails/2_thumbnail.jpg',
        'Phòng tập gym quận Phú Nhuận CITIGYM Phổ Quang với thiết kế lấy cảm hứng thiên nhiên, đưa cây xanh vào phòng tập cho phép hội viên thoải mái tập luyện với diện tích 4000m2. Phòng tập hiện đại mang hơi thở của thiên nhiên giúp hội viên cảm nhận năng lượng nhiệt huyết đồng thời cảm giác thư thái sau ngày dài làm việc. Ngoài ra, khi đến CLB Phổ Quang hội viên còn có cơ hội trải nghiệm hệ thống máy tập và dụng cụ hiện đại bậc nhất, nhập khẩu từ các thương hiệu nước ngoài hàng đầu cũng như chất lượng dịch vụ đẳng cấp 5 sao.',
        10.809065977594821, 106.67174681287801, 200, 4000,'Hồ Chí Minh'),

       (3, 5, 21, 1, 'CITIGYM BẾN VÂN ĐỒN', '34-35 Bến Vân Đồn, Phường 12, Quận 4, Thành phố Hồ Chí Minh',
        '1900633638',
        '/img/departments/logos/3_logo.jpg',
        '/img/departments/wallpapers/3_wallpaper.jpg',
        '/img/departments/thumbnails/3_thumbnail.jpg',
        'Phòng gym quận 4 CITIGYM Bến Vân Đồn tọa lạc tại vị trí đắc địa tại mặt tiền Bến Vân Đồn, cách quận 1 chỉ vài phút đi bộ, hướng nhìn ra sông Sài Gòn. Đến ngay CLB Bến Vân Đồn để trải nghiệm không gian tập luyện sang trọng và rộng rãi có một không hai với tầm nhìn thành phố 2 mặt tiền, thỏa thích tập các bộ môn nhóm đông người. Không chỉ là cơ sở hạ tầng, CLB Bến Vân Đồn còn được biết đến với hệ thống máy tập và dụng cụ hiện đại bậc nhất, nhập khẩu từ các thương hiệu nổi tiếng nước ngoài.',
        10.767283248645954, 106.70370099574525, 200, 5000,'Hồ Chí Minh'),

       (4, 5, 22, 1, 'CITIGYM VẠN HẠNH MALL', 'Lầu 7 TTTM Vạn Hạnh Mall, Phường 12, Quận 10, Thành phố Hồ Chí Minh',
        '1900633638',
        '/img/departments/logos/4_logo.jpg',
        '/img/departments/wallpapers/4_wallpaper.jpg',
        '/img/departments/thumbnails/4_thumbnail.jpg',
        'CITIGYM Vạn Hạnh Mall là phòng tập lớn nhất Việt Nam nằm trong trung tâm thương mại với diện tích 6000m2, tọa lạc tại trung tâm quận 10. Phòng tập gym quận 10 được thiết kế như thành phố New York sôi động, tại đây hội viên sẽ có trải nghiệm thú vị vừa tập luyện vừa ngắm nhìn thành phố từ trên cao. Ngoài ra, câu lạc bộ còn được trang bị hệ thống máy tập hiện đại nhất và tổ chức đa dạng các lớp gym, yoga, group x, dance...',
        10.770899546871222, 106.66895996876498, 200, 2000,'Hồ Chí Minh'),


       (5, 6, 23, 1, 'CALIFORNIA WEST POINT - QUẬN TÂY HỒ',
        'Tầng 2 & 3 , Somerset West Point, Số 2 Tây Hồ, P. Quảng An, Q. Tây Hồ, Hà Nội.', '(024) 7300 1777',
        '/img/departments/logos/5_logo.jpg',
        '/img/departments/wallpapers/5_wallpaper.jpg',
        '/img/departments/thumbnails/5_thumbnail.jpg',
        'Sang trọng, đẳng cấp, tinh tế trong từng chi tiết đó chính là những trải nghiệm tuyệt vời chỉ có khi bạn bước chân vào CLB California Centuryon West Point quận Tây Hồ. Một nơi tập luyện đạt chuẩn quốc tế cùng với các tiện ích phục hồi năng lượng tích hợp, đặc biệt có hồ bơi thư giãn chắc chắn sẽ mang lại cho bạn những trải nghiệm chưa từng có. Khám phá ngay hôm nay!',
        21.066572880558276, 105.8262971113118, 100, 2000,'Hà Nội'),

       (6, 6, 24, 1, 'CALIFORNIA VINCOM STAR CITY - QUẬN CẦU GIẤY',
        'Tầng 3, Trung tâm thương mại Vincom Star City, 119 đường Trần Duy Hưng, P. Trung Hòa, Q. Cầu Giấy, Hà Nội',
        '02473001277',
        '/img/departments/logos/6_logo.jpg',
        '/img/departments/wallpapers/6_wallpaper.jpg',
        '/img/departments/thumbnails/6_thumbnail.jpg',
        'Sang trọng, đẳng cấp, tinh tế trong từng chi tiết đó chính là những trải nghiệm tuyệt vời chỉ có khi bạn bước chân vào CLB California Centuryon West Point quận Tây Hồ. Một nơi tập luyện đạt chuẩn quốc tế cùng với các tiện ích phục hồi năng lượng tích hợp, đặc biệt có hồ bơi thư giãn chắc chắn sẽ mang lại cho bạn những trải nghiệm chưa từng có. Khám phá ngay hôm nay!',
        21.00592534375174, 105.79509157389299, 100, 2000,'Hà Nội'),

       (7, 6, 25, 1, 'CALIFORNIA TIMES CITY - QUẬN HAI BÀ TRƯNG',
        'Time City Megamall, Tòa nhà T18, 458 Minh Khai, P. Vĩnh Tuy, Q. Hai Bà Trưng, Hà Nội', '02471079999',
        '/img/departments/logos/7_logo.jpg',
        '/img/departments/wallpapers/7_wallpaper.jpg',
        '/img/departments/thumbnails/7_thumbnail.jpg',
        'Vượt qua mọi trở ngại khiến bạn mất động lực khi tập luyện. Trải nghiệm ngay California Fitness & Yoga quận Hai Bà Trưng - cộng đồng yêu thích lối sống năng động và khoẻ mạnh lớn nhất tại Hà Nội. Với trang thiết bị đầy đủ cùng với chương trình tập luyện đa dạng và các tiện ích phục hồi năng lượng tiêu chuẩn 5 sao như hồ bơi trong nhà, bạn sẽ luôn được truyền cảm hứng để duy trì lối sống lành mạnh.',
        21.00592534375174, 105.79509157389299, 100, 2000,'Hà Nội'),

       (8, 6, 26, 1, 'CALIFORNIA SKY CITY TOWER - QUẬN ĐỐNG ĐA',
        'Sky City, Tầng M, 88 Láng Hạ, P.Láng Hạ, Q. Đống Đa, Hà Nội', '02471097899',
        '/img/departments/logos/8_logo.jpg',
        '/img/departments/wallpapers/8_wallpaper.jpg',
        '/img/departments/thumbnails/8_thumbnail.jpg',
        'Không chỉ có lịch học trải đều các khung giờ, Trung tâm California Fitness and Yoga Quận Đống Đa còn sở hữu khu vực Hydro – Therapy hệ thống phòng tắm hơi lớn nhất Việt Nam, cung cấp không gian thư giãn sau tập luyện độc đáo khó nơi nào sánh được.',
        21.012892257325145, 105.81135818247371, 100, 2000,'Hà Nội'),


       (9, 7, 27, 1, 'FIT24 PHẠM VĂN HAI- Q. TÂN BÌNH',
        'Central Plaza, 91 Phạm Văn Hai, Phường 3, Quận Tân Bình, TP. HCM', '02873072424',
        '/img/departments/logos/9_logo.jpg',
        '/img/departments/wallpapers/9_wallpaper.jpg',
        '/img/departments/thumbnails/9_thumbnail.jpg',
        'Tọa lạc tại tầng K Tòa nhà Central Plaza, trang thiết bị tập luyện cao cấp cùng đa dạng các môn học và đặc biệt, hồ bơi ngoài trời chính là những điểm cộng tuyệt vời của phòng tập Gym – Yoga cao cấp nhất Quận Tân Bình.',
        10.794118291505661, 106.66311798225539, 70, 1500,'Hồ Chí Minh'),

       (10, 7, 28, 1, 'FIT24 HỒ XUÂN HƯƠNG- QUẬN 3', '02 Hồ Xuân Hương, Phường Võ Thị Sáu, Quận 3, Hồ Chí Minh',
        '02873072424',
        '/img/departments/logos/10_logo.jpg',
        '/img/departments/wallpapers/10_wallpaper.jpg',
        '/img/departments/thumbnails/10_thumbnail.jpg',
        'Tọa lạc tại tầng K Tòa nhà Central Plaza, trang thiết bị tập luyện cao cấp cùng đa dạng các môn học và đặc biệt, hồ bơi ngoài trời chính là những điểm cộng tuyệt vời của phòng tập Gym – Yoga cao cấp nhất Quận Tân Bình.',
        10.77720992036007, 106.68773747611712, 70, 1500,'Hồ Chí Minh'),

       (11, 7, 29, 1, 'FIT24 BA THÁNG HAI – QUẬN 10', 'Lầu 6-7-8 Số 3 Ba Tháng Hai, Phường 11, Quận 10, TP. HCM',
        '02873072424',
        '/img/departments/logos/11_logo.jpg',
        '/img/departments/wallpapers/11_wallpaper.jpg',
        '/img/departments/thumbnails/11_thumbnail.jpg',
        'Là phòng tập Gym và Yoga LỚN NHẤT – ĐẸP NHẤT với quy mô 3 tầng và diện tích trên 2,000m2, Fit24 Ba Tháng Hai được thiết kế sang trọng và ấn tượng theo phong cách Châu Âu hiện đại và là chi nhánh mới nhất của Hệ thống Fit24.',
        10.777031921131604, 106.68105969760164, 70, 2000,'Hồ Chí Minh');

INSERT INTO gym_department_amenities (gym_department_id, amenitie_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),

       (2, 1),
       (2, 2),
       (2, 3),
       (2, 4),
       (2, 5),
       (2, 6),

       (3, 1),
       (3, 2),
       (3, 3),
       (3, 4),
       (3, 5),
       (3, 6),

       (4, 1),
       (4, 2),
       (4, 3),
       (4, 4),
       (4, 5),
       (4, 6),


       (5, 7),
       (5, 8),
       (5, 9),
       (5, 10),
       (5, 11),
       (5, 12),

       (6, 7),
       (6, 8),
       (6, 9),
       (6, 10),
       (6, 11),
       (6, 12),

       (7, 7),
       (7, 8),
       (7, 9),
       (7, 10),
       (7, 11),
       (7, 12),

       (8, 7),
       (8, 8),
       (8, 9),
       (8, 10),
       (8, 11),
       (8, 12),

       (9, 13),
       (9, 14),
       (9, 15),
       (9, 16),
       (9, 17),

       (10, 13),
       (10, 14),
       (10, 15),
       (10, 16),
       (10, 17),

       (11, 13),
       (11, 14),
       (11, 15),
       (11, 16),
       (11, 17);

/********** Department Gym Plan Creation ***********/
-- Create Gym plan status
INSERT INTO mst_kbn (mst_kbn_name, mst_kbn_key, mst_kbn_value)
VALUES ('Gym Plan Type', 1, 'Gói theo giờ'),
       ('Gym Plan Type', 2, 'Gói không theo giờ');

-- Create Gym plan infos
INSERT INTO gym_plan (plan_id, brand_id, gym_plan_key, gym_plan_status_key, gym_plan_type_key, name, description,
                      price, price_per_hours, plan_sold, duration, plan_before_active_validity,
                      plan_after_active_validity)

VALUES (1, 5, 1, 1, 1, 'Gói giờ',
        'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp.',
        0, 10.00, 0, 0, 7, 14),

       (2, 5, 2, 1, 2, 'Gói 3 tháng',
        'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam',
        400, 0, 0, 90, 10, 90),

       (3, 5, 2, 1, 2, 'Gói 1 tháng',
        'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ',
        150.00, 0, 0, 30, 10, 30),

       (4, 6, 1, 1, 1, 'Gói giờ',
        'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp.',
        0, 10.00, 0, 0, 7, 14),

       (5, 6, 2, 1, 2, 'Gói 3 tháng',
        'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam',
        400, 0, 0, 90, 10, 90),

       (6, 6, 2, 1, 2, 'Gói 1 tháng',
        'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ',
        150.00, 0, 0, 30, 10, 30),

       (7, 7, 1, 1, 1, 'Gói giờ',
        'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp.',
        0, 10.00, 0, 0, 7, 14),

       (8, 7, 2, 1, 2, 'Gói 3 tháng',
        'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam',
        400, 0, 0, 90, 10, 90),

       (9, 7, 2, 1, 2, 'Gói 1 tháng',
        'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ',
        150.00, 0, 0, 30, 10, 30);

INSERT INTO gym_department_plans(gym_department_id, plan_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),

       (2, 1),
       (2, 2),
       (2, 3),

       (3, 1),
       (3, 2),
       (3, 3),

       (4, 1),
       (4, 2),
       (4, 3),

       (5, 4),
       (5, 5),
       (5, 6),

       (6, 4),
       (6, 5),
       (6, 6),

       (7, 4),
       (7, 5),
       (7, 6),

       (8, 4),
       (8, 5),
       (8, 6),

       (9, 7),
       (9, 8),
       (9, 9),

       (10, 7),
       (10, 8),
       (10, 9),

       (11, 7),
       (11, 8),
       (11, 9);

INSERT INTO features (feature_icon, feature_name, feature_status)
VALUES ('<i class="bi bi-p-circle"></i>', 'Bãi Đỗ Xe', 1),
       ('<i class="fa-solid fa-shower"></i>', 'Phòng Tắm Nóng Lạnh', 1),
       ('<i class="fa-solid fa-wifi"></i>', 'WIFI', 1),
       ('<i class="fas fa-scroll"></i>', 'Khăn Miễn Phí', 1),
       ('<i class="fa-solid fa-couch"></i>', 'Khu Nghỉ Ngơi', 1),
       ('<i class="fa-solid fa-martini-glass-citrus"></i>', 'Quầy Nước', 1),
       ('<i class="fa-solid fa-suitcase-medical"></i>', 'Phòng Y Tế', 1);

-- Create Gym Department Features
INSERT INTO gym_department_features (feature_id, gym_department_id, feature_status)
VALUES (1, 1, 1),
       (2, 1, 1),
       (3, 1, 1),
       (4, 1, 1),
       (5, 1, 1),

       (1, 2, 1),
       (2, 2, 1),
       (3, 2, 1),
       (6, 2, 1),
       (7, 2, 1),

       (1, 3, 1),
       (3, 3, 1),
       (4, 3, 1),
       (5, 3, 1),
       (7, 3, 1),

       (1, 4, 1),
       (3, 4, 1),
       (4, 4, 1),
       (5, 4, 1),
       (7, 4, 1),

       (1, 5, 1),
       (3, 5, 1),
       (4, 5, 1),
       (5, 5, 1),
       (7, 5, 1),

       (1, 6, 1),
       (3, 6, 1),
       (4, 6, 1),
       (5, 6, 1),
       (7, 6, 1),

       (1, 7, 1),
       (3, 7, 1),
       (4, 7, 1),
       (5, 7, 1),
       (7, 7, 1),

       (1, 8, 1),
       (3, 8, 1),
       (4, 8, 1),
       (5, 8, 1),
       (7, 8, 1),

       (1, 9, 1),
       (3, 9, 1),
       (4, 9, 1),
       (5, 9, 1),
       (7, 9, 1),

       (1, 10, 1),
       (3, 10, 1),
       (4, 10, 1),
       (5, 10, 1),
       (7, 10, 1),

       (1, 11, 1),
       (3, 11, 1),
       (4, 11, 1),
       (5, 11, 1),
       (7, 11, 1);

-- Create Gym Department Gallery
INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (1, '/img/departments/albums/1_albums_1701104437519.jpg'),
       (1, '/img/departments/albums/1_albums_1701104573073.jpg'),
       (1, '/img/departments/albums/1_albums_1701104577588.jpg'),
       (1, '/img/departments/albums/1_albums_1701104604105.jpg'),
       (1, '/img/departments/albums/1_albums_1701104610141.jpg'),

       (2, '/img/departments/albums/2_albums_1701107454741.jpg'),
       (2, '/img/departments/albums/2_albums_1701107459613.jpg'),
       (2, '/img/departments/albums/2_albums_1701107370430.jpg'),
       (2, '/img/departments/albums/2_albums_1701107386390.jpg'),
       (2, '/img/departments/albums/2_albums_1701107397062.jpg'),
       (2, '/img/departments/albums/2_albums_1701107408165.jpg'),
       (2, '/img/departments/albums/2_albums_1701107415150.jpg'),

       (3, '/img/departments/albums/3_albums_1701108321944.jpg'),
       (3, '/img/departments/albums/3_albums_1701108325845.jpg'),
       (3, '/img/departments/albums/3_albums_1701108330099.jpg'),
       (3, '/img/departments/albums/3_albums_1701108360677.jpg'),

       (4, '/img/departments/albums/4_albums_1701109289985.jpg'),
       (4, '/img/departments/albums/4_albums_1701109283752.jpg'),
       (4, '/img/departments/albums/4_albums_1701109278412.jpg'),
       (4, '/img/departments/albums/4_albums_1701109270813.jpg'),
       (4, '/img/departments/albums/4_albums_1701109258500.jpg'),
       (4, '/img/departments/albums/4_albums_1701109250481.jpg'),
       (4, '/img/departments/albums/4_albums_1701109243505.jpg'),
       (4, '/img/departments/albums/4_albums_1701109238142.jpg'),
       (4, '/img/departments/albums/4_albums_1701109234789.jpg'),


       (5, '/img/departments/albums/5_albums_1701111013408.jpg'),
       (5, '/img/departments/albums/5_albums_1701111017098.jpg'),
       (5, '/img/departments/albums/5_albums_1701111025539.jpg'),
       (5, '/img/departments/albums/5_albums_1701111029674.jpg'),
       (5, '/img/departments/albums/5_albums_1701111034081.jpg'),
       (5, '/img/departments/albums/5_albums_1701111038214.jpg'),
       (5, '/img/departments/albums/5_albums_1701111041845.jpg'),

       (6, '/img/departments/albums/6_albums_1701142174578.jpg'),
       (6, '/img/departments/albums/6_albums_1701142178608.jpg'),
       (6, '/img/departments/albums/6_albums_1701142181497.jpg'),
       (6, '/img/departments/albums/6_albums_1701142185421.jpg'),
       (6, '/img/departments/albums/6_albums_1701142189410.jpg'),
       (6, '/img/departments/albums/6_albums_1701142193981.jpg'),
       (6, '/img/departments/albums/6_albums_1701142198305.jpg'),

       (7, '/img/departments/albums/7_albums_1701143098188.jpg'),
       (7, '/img/departments/albums/7_albums_1701143105629.jpg'),
       (7, '/img/departments/albums/7_albums_1701143109872.jpg'),
       (7, '/img/departments/albums/7_albums_1701143120671.jpg'),
       (7, '/img/departments/albums/7_albums_1701143132113.jpg'),
       (7, '/img/departments/albums/7_albums_1701143138069.jpg'),
       (7, '/img/departments/albums/7_albums_1701143145141.jpg'),

       (8, '/img/departments/albums/8_albums_1701143553816.jpg'),
       (8, '/img/departments/albums/8_albums_1701143559552.jpg'),
       (8, '/img/departments/albums/8_albums_1701143566241.jpg'),
       (8, '/img/departments/albums/8_albums_1701143572260.jpg'),
       (8, '/img/departments/albums/8_albums_1701143578119.jpg'),
       (8, '/img/departments/albums/8_albums_1701143581000.jpg'),
       (8, '/img/departments/albums/8_albums_1701143585399.jpg'),


       (9, '/img/departments/albums/9_albums_1701144128162.jpg'),
       (9, '/img/departments/albums/9_albums_1701144132976.jpg'),
       (9, '/img/departments/albums/9_albums_1701144136663.jpg'),
       (9, '/img/departments/albums/9_albums_1701144140433.jpg'),
       (9, '/img/departments/albums/9_albums_1701144143669.jpg'),
       (9, '/img/departments/albums/9_albums_1701144145922.jpg'),

       (10, '/img/departments/albums/10_albums_1701144556457.jpg'),
       (10, '/img/departments/albums/10_albums_1701144562320.jpg'),
       (10, '/img/departments/albums/10_albums_1701144567954.jpg'),
       (10, '/img/departments/albums/10_albums_1701144573122.jpg'),
       (10, '/img/departments/albums/10_albums_1701144579887.jpg'),
       (10, '/img/departments/albums/10_albums_1701144626383.jpg'),

       (11, '/img/departments/albums/11_albums_1701144936622.jpg'),
       (11, '/img/departments/albums/11_albums_1701144943120.jpg'),
       (11, '/img/departments/albums/11_albums_1701144949475.jpg'),
       (11, '/img/departments/albums/11_albums_1701144953905.jpg'),
       (11, '/img/departments/albums/11_albums_1701144969646.jpg'),
       (11, '/img/departments/albums/11_albums_1701144991980.jpg');


-- Create Gym Department Schedule
INSERT INTO gym_department_schedule (gym_department_id, day, open_time, close_time)
VALUES (1, 'Thứ 2', '6:00 AM', '10:00 PM'),
       (1, 'Thứ 3', '9:00 AM', '11:00 PM'),
       (1, 'Thứ 4', '9:00 AM', '11:00 PM'),
       (1, 'Thứ 5', '9:00 AM', '11:00 PM'),
       (1, 'Thứ 6', '9:00 AM', '11:00 PM'),
       (1, 'Thứ 7', '9:00 AM', '11:00 PM'),
       (1, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (2, 'Thứ 2', '6:00 AM', '10:00 PM'),
       (2, 'Thứ 3', '9:00 AM', '11:00 PM'),
       (2, 'Thứ 4', '9:00 AM', '11:00 PM'),
       (2, 'Thứ 5', '9:00 AM', '11:00 PM'),
       (2, 'Thứ 6', '9:00 AM', '11:00 PM'),
       (2, 'Thứ 7', '9:00 AM', '11:00 PM'),
       (2, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (3, 'Thứ 2', '6:00 AM', '10:00 PM'),
       (3, 'Thứ 3', '9:00 AM', '11:00 PM'),
       (3, 'Thứ 4', '9:00 AM', '11:00 PM'),
       (3, 'Thứ 5', '9:00 AM', '11:00 PM'),
       (3, 'Thứ 6', '9:00 AM', '11:00 PM'),
       (3, 'Thứ 7', '9:00 AM', '11:00 PM'),
       (3, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (4, 'Thứ 2', '6:00 AM', '10:00 PM'),
       (4, 'Thứ 3', '9:00 AM', '11:00 PM'),
       (4, 'Thứ 4', '9:00 AM', '11:00 PM'),
       (4, 'Thứ 5', '9:00 AM', '11:00 PM'),
       (4, 'Thứ 6', '9:00 AM', '11:00 PM'),
       (4, 'Thứ 7', '9:00 AM', '11:00 PM'),
       (4, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (5, 'Thứ 2', '6:00 AM', '10:00 PM'),
       (5, 'Thứ 3', '9:00 AM', '11:00 PM'),
       (5, 'Thứ 4', '9:00 AM', '11:00 PM'),
       (5, 'Thứ 5', '9:00 AM', '11:00 PM'),
       (5, 'Thứ 6', '9:00 AM', '11:00 PM'),
       (5, 'Thứ 7', '9:00 AM', '11:00 PM'),
       (5, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (6, 'Thứ 2', '6:00 AM', '10:00 PM'),
       (6, 'Thứ 3', '9:00 AM', '11:00 PM'),
       (6, 'Thứ 4', '9:00 AM', '11:00 PM'),
       (6, 'Thứ 5', '9:00 AM', '11:00 PM'),
       (6, 'Thứ 6', '9:00 AM', '11:00 PM'),
       (6, 'Thứ 7', '9:00 AM', '11:00 PM'),
       (6, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (7, 'Thứ 2', '6:00 AM', '10:00 PM'),
       (7, 'Thứ 3', '9:00 AM', '11:00 PM'),
       (7, 'Thứ 7', '9:00 AM', '11:00 PM'),
       (7, 'Thứ 8', '9:00 AM', '11:00 PM'),
       (7, 'Thứ 6', '9:00 AM', '11:00 PM'),
       (7, 'Thứ 7', '9:00 AM', '11:00 PM'),
       (7, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (8, 'Thứ 2', '6:00 AM', '10:00 PM'),
       (8, 'Thứ 3', '9:00 AM', '11:00 PM'),
       (8, 'Thứ 4', '9:00 AM', '11:00 PM'),
       (8, 'Thứ 5', '9:00 AM', '11:00 PM'),
       (8, 'Thứ 6', '9:00 AM', '11:00 PM'),
       (8, 'Thứ 7', '9:00 AM', '11:00 PM'),
       (8, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (9, 'Thứ 2', '6:00 AM', '10:00 PM'),
       (9, 'Thứ 3', '9:00 AM', '11:00 PM'),
       (9, 'Thứ 4', '9:00 AM', '11:00 PM'),
       (9, 'Thứ 5', '9:00 AM', '11:00 PM'),
       (9, 'Thứ 6', '9:00 AM', '11:00 PM'),
       (9, 'Thứ 7', '9:00 AM', '11:00 PM'),
       (9, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (10, 'Thứ 2', '6:00 AM', '10:00 PM'),
       (10, 'Thứ 3', '9:00 AM', '11:00 PM'),
       (10, 'Thứ 4', '9:00 AM', '11:00 PM'),
       (10, 'Thứ 5', '9:00 AM', '11:00 PM'),
       (10, 'Thứ 6', '9:00 AM', '11:00 PM'),
       (10, 'Thứ 7', '9:00 AM', '11:00 PM'),
       (10, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (11, 'Thứ 2', '6:00 AM', '10:00 PM'),
       (11, 'Thứ 3', '9:00 AM', '11:00 PM'),
       (11, 'Thứ 4', '9:00 AM', '11:00 PM'),
       (11, 'Thứ 5', '9:00 AM', '11:00 PM'),
       (11, 'Thứ 6', '9:00 AM', '11:00 PM'),
       (11, 'Thứ 7', '9:00 AM', '11:00 PM'),
       (11, 'Chủ Nhật', '9:00 AM', '11:00 PM');

/********** Department Feedback Creation ***********/
INSERT INTO user_feedback
(user_id, gym_plan_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES (1, 1, 1, 4, 'Phòng tập thể dục rất sạch sẽ và thoải mái.', NOW(), 1),
       (4, 2, 2, 4, 'Phòng tập thể dục rất sạch sẽ và thoải mái.', NOW(), 1),
       (5, 3, 3, 4, 'Phòng tập thể dục rất sạch sẽ và thoải mái.', NOW(), 1),
       (5, 1, 4, 4, 'Phòng tập thể dục rất sạch sẽ và thoải mái.', NOW(), 1),

       (1, 1, 1, 3, 'Nhân viên phục vụ tận tình.', NOW(), 1),
       (4, 2, 2, 3, 'Nhân viên phục vụ tận tình.', NOW(), 1),
       (5, 3, 3, 3, 'Nhân viên phục vụ tận tình.', NOW(), 1),
       (5, 1, 4, 3, 'Nhân viên phục vụ tận tình.', NOW(), 1),

       (1, 1, 1, 5, 'Thích không khí ở phòng tập.', NOW(), 1),
       (4, 2, 2, 5, 'Thích không khí ở phòng tập.', NOW(), 1),
       (5, 3, 3, 5, 'Thích không khí ở phòng tập.', NOW(), 1),
       (5, 1, 4, 5, 'Thích không khí ở phòng tập.', NOW(), 1),

       (1, 1, 1, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
       (4, 2, 2, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
       (5, 3, 3, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
       (5, 1, 4, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),

       (1, 1, 1, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
       (4, 2, 2, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
       (5, 3, 3, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
       (5, 1, 4, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),


       (1, 1, 5, 4, 'Phòng tập thể dục rất sạch sẽ và thoải mái.', NOW(), 1),
       (4, 2, 6, 4, 'Phòng tập thể dục rất sạch sẽ và thoải mái.', NOW(), 1),
       (5, 3, 7, 4, 'Phòng tập thể dục rất sạch sẽ và thoải mái.', NOW(), 1),
       (5, 1, 8, 4, 'Phòng tập thể dục rất sạch sẽ và thoải mái.', NOW(), 1),

       (1, 1, 5, 3, 'Nhân viên phục vụ tận tình.', NOW(), 1),
       (4, 2, 6, 3, 'Nhân viên phục vụ tận tình.', NOW(), 1),
       (5, 3, 7, 3, 'Nhân viên phục vụ tận tình.', NOW(), 1),
       (5, 1, 8, 3, 'Nhân viên phục vụ tận tình.', NOW(), 1),

       (1, 1, 5, 5, 'Thích không khí ở phòng tập.', NOW(), 1),
       (4, 2, 6, 5, 'Thích không khí ở phòng tập.', NOW(), 1),
       (5, 3, 7, 5, 'Thích không khí ở phòng tập.', NOW(), 1),
       (5, 1, 8, 5, 'Thích không khí ở phòng tập.', NOW(), 1),

       (1, 1, 5, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
       (4, 2, 6, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
       (5, 3, 7, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
       (5, 1, 8, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),

       (1, 1, 5, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
       (4, 2, 6, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
       (5, 3, 7, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
       (5, 1, 8, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),

       (1, 1, 9, 4, 'Phòng tập thể dục rất sạch sẽ và thoải mái.', NOW(), 1),
       (4, 2, 10, 4, 'Phòng tập thể dục rất sạch sẽ và thoải mái.', NOW(), 1),
       (5, 3, 11, 4, 'Phòng tập thể dục rất sạch sẽ và thoải mái.', NOW(), 1),

       (1, 1, 9, 3, 'Nhân viên phục vụ tận tình.', NOW(), 1),
       (4, 2, 10, 3, 'Nhân viên phục vụ tận tình.', NOW(), 1),
       (5, 3, 11, 3, 'Nhân viên phục vụ tận tình.', NOW(), 1),

       (1, 1, 9, 5, 'Thích không khí ở phòng tập.', NOW(), 1),
       (4, 2, 10, 5, 'Thích không khí ở phòng tập.', NOW(), 1),
       (5, 3, 11, 5, 'Thích không khí ở phòng tập.', NOW(), 1),

       (1, 1, 9, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
       (4, 2, 10, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
       (5, 3, 11, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),

       (1, 1, 9, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
       (4, 2, 10, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
       (5, 3, 11, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1);

/**************************************************************************************** Brand Creation - DUC **********************************************************************/
-- Create brand infos
INSERT INTO brand (brand_id, user_id, name, logo_url, wallpaper_url, thumbnail_url, description, rating, contact_number,
                   contact_email, brand_status_key)
VALUES (8, 9, 'Getfit Gym & Yoga', '/img/brands/logos/8_logo.jpg', '/img/brands/wallpapers/8_wallpaper.jpg', '/img/brands/thumbnails/8_thumbnail.jpg',
        'Getfit với kinh nghiệm hơn 10 năm huấn luyện thể hình, được đánh giá là thương hiệu có chuyên môn cao và huấn luyện học viên theo chương trình chuẩn quốc tế. Hiện nay, sau đại dịch, Getfit đã và đang cung cấp đến học viên gói phục hồi sức khỏe sau Covid. Huấn luyện viên đã chuẩn bị sẵn lịch trình và bài tập phù hợp dành cho những người đang có triệu chứng của hậu Covid như: khó thở, hụt hơi khi thở…',
        5, '0345 535 454', 'info@getfit.vn', 1),
       (9, 10, 'Elite Fitness', '/img/brands/logos/9_logo.jpg', '/img/brands/wallpapers/9_wallpaper.jpg', '/img/brands/thumbnails/9_thumbnail.jpg',
        'Khám phá Elite Fitness để trải nghiệm và tận hưởng không gian phòng tập sang trọng, đẹp bậc nhất Đông Nam Á. Xâm lấn vào thị trường gym năm 2010, thương hiệu gym này đã phát triển từ câu lạc bộ đầu tiên tại Xuân Diệu, Hà Nội. Hướng đến hệ thống câu lạc bộ thể thao đẳng cấp 5 sao, tiêu chuẩn Quốc tế. Dưới sự quản lý của tập đoàn BIM Việt Nam, hiện nay hệ thống đã sở hữu 14 câu lạc bộ trải khắp toàn quốc. Elite Fitness cung cấp đa dạng dịch vụ, tùy theo nhu cầu của bạn. Đến đây, bạn không cần phải bâng khuâng về lịch tập, vì các huấn luyện viên sẽ là người lên lịch phù hợp để bạn đạt mục tiêu của mình nhanh nhất.',
        3, '(028) 7307 9899', 'elitefitness@gmail.com', 1),
       (10, 11, 'Fit365 Fitness & Yoga', '/img/brands/logos/10_logo.jpg', '/img/brands/wallpapers/10_wallpaper.jpg', '/img/brands/thumbnails/10_thumbnail.jpg',
        'Fit365 đầu tư trang thiết bị tập luyện tối tân cho học viên được trải nghiệm cùng huấn luyện viên chuyên nghiệp. Khác biệt với thương hiệu khác, Fit365 đầu tư hồ thủy lực. Massage giúp nâng cao quá trình loại bỏ chất thải, cải thiện giấc ngủ và cải thiện độ đàn hồi của cơ bắp. Học viên được thỏa sức thư giãn thả lỏng trong bồn massage và cảm nhận sự tái sinh của cơ thể sau 1 ngày mệt mỏi.',
        4, '0909 290 880', 'fit365vn@gmail.com', 1);
--


-- Create brand_amenities
INSERT INTO brand_amenities (amenitie_id, brand_id, photo_url, amenitie_name, description, amenitie_status)
VALUES (51, 8, '/img/brandAmenities/1701107286929_brandAmenities.jpg', 'Yoga Master Class', 'Bừng sức sống mới cùng Yoga', 1),
       (52, 8, '/img/brandAmenities/1701107294515_brandAmenities.jpg', 'Yoga 1:1', 'Trải nghiệm mới mẻ cùng Yoga', 1),
       (53, 8, '/img/brandAmenities/1701107307246_brandAmenities.jpg', 'Boxing cùng HLV', 'Boxing cùng những huấn luyện viên chuyên nghiệp', 1),

       (54, 9, '/img/brandAmenities/1701107912519_brandAmenities.jpgr', 'Yoga Thiền Cho Sức Khỏe Tinh Thần',
        'Tham gia các buổi thiền yoga giúp giảm căng thẳng, tăng cường sức khỏe tinh thần một cách tự nhiên.', 1),
       (55, 9, '/img/brandAmenities/1701107919873_brandAmenities.jpg', 'Lớp học độc quyền LESMILLS ',
        'Chương trình bài tập LESMILLS sôi động, giúp đốt cháy calo và tăng cường sức bền.', 1),
       (56, 9, '/img/brandAmenities/1701107925858_brandAmenities.jpg', 'Học Viện Boxing Chuyên Nghiệp',
        'Học viên sẽ được hướng dẫn kỹ thuật đấm bốc từ các huấn luyện viên chuyên nghiệp.', 1),
       (57, 9, '/img/brandAmenities/1701107936213_brandAmenities.jpg', 'Khóa Học Yoga Cấp Độ Cao',
        'Tham gia các khóa học yoga cấp độ cao, tập trung vào các động tác phức tạp và hiểu sâu về yoga.', 1);

--





INSERT INTO brand_amenities (amenitie_id, brand_id, photo_url, amenitie_name, description, amenitie_status)
VALUES (59, 10, '/img/brandAmenities/1701108717831_brandAmenities.jpg', 'Hồ Massage Thủy Lực',
        'Là một sản phẩm của sự kết hợp độc đáo giữa công nghệ hiện đại, tiên tiến nhất và cổ xưa nhất với các phương pháp trị liệu thư giãn của hương thơm tinh dầu, massage, thư giãn, chăm sóc da và giải trí mang lại cho bạn nhiều lợi ích về sức khỏe cũng như sắc đẹp.',
        1);
INSERT INTO brand_amenities (amenitie_id, brand_id, photo_url, amenitie_name, description, amenitie_status)
VALUES (60, 10, '/img/brandAmenities/1701108770457_brandAmenities.jpg', 'Lớp Zumba Sôi Động',
        'Tham gia lớp Zumba sôi động với nhịp điệu âm nhạc, giúp tăng cường sức khỏe và vui vẻ trong quá trình tập luyện.',
        1);

INSERT INTO brand_amenities (amenitie_id, brand_id, photo_url, amenitie_name, description, amenitie_status)
VALUES (61, 10, '/img/brandAmenities/1701108777548_brandAmenities.jpg', 'Lớp Boxing Chuyên Nghiệp',
        'Dành cho người muốn nâng cao kỹ thuật và tăng cường sức mạnh cùng với huấn luyện viên chuyên nghiệp.', 1);

INSERT INTO brand_amenities (amenitie_id, brand_id, photo_url, amenitie_name, description, amenitie_status)
VALUES (62, 10, '/img/brandAmenities/1701108783673_brandAmenities.jpg', 'Yoga Cho Tâm Hồn',
        'Trải nghiệm các buổi yoga tập trung vào tinh thần, giúp cân bằng tâm hồn và cơ thể.', 1);

-- Create Gym Department infos
INSERT INTO gym_department (gym_department_id, brand_id, user_id, gym_department_status_key, name, address,
                            contact_number, logo_url, wallpaper_url, thumbnail_url, description, latitude, longitude,
                            capacity, area,city)
VALUES (57, 8, 30, 1, 'Getfit Chi nhánh Oriental Tân Phú',
        'Lầu M (lầu 1) Oriental Plaza, 685 Âu Cơ, P.Tân Thành,Q.Tân Phú.',
        '0977699490',
        '/img/departments/logos/57_logo.jpg',
        '/img/departments/wallpapers/57_wallpaper.jpg',
        '/img/departments/thumbnails/57_thumbnail.jpg',
        'Với phương châm lấy sức khỏe khách hàng làm mục tiêu phát triển, Getfit không ngừng nâng cấp, đổi mới và sáng tạo để vận dụng những khoa học công nghệ hàng đầu thế giới vào việc xây dựng thể chất của người Việt. Cùng với sứ mệnh “Nâng tầm thể chất người Việt” của Getfit Gym & Yoga, hàng loạt các hệ sinh thái ra đời và không ngừng mở rộng nhằm đem đến những giá trị trọn vẹn nhất đến với khách hàng.',
        10.789814194191278, 106.63987821995256, 200, 200,'Hồ Chí Minh');

INSERT INTO gym_department (gym_department_id, brand_id, user_id, gym_department_status_key, name, address,
                            contact_number, logo_url, wallpaper_url, thumbnail_url, description, latitude, longitude,
                            capacity, area,city)
VALUES (83, 8, 31, 1, 'Getfit Chi nhánh Mia Center',
        'Lầu 4, Saigon Mia Center, 202 Đường số 9A, KDC Trung Sơn.',
        '0966111241',
        '/img/departments/logos/83_logo.jpg',
        '/img/departments/wallpapers/83_wallpaper.jpg',
        '/img/departments/thumbnails/83_thumbnail.jpg',
        'Phòng tập được trang bị các thiết bị tập luyện hiện đại nhằm đem lại môi trường luyện tập đẳng cấp xứng tầm CLB 5 sao, đây đủ dụng cụ tập đáp ứng đa dạng nhu cầu của quý hội viên.',
        10.733395069054875, 106.68892360080237, 200, 200,'Hồ Chí Minh');

INSERT INTO gym_department (gym_department_id, brand_id, user_id, gym_department_status_key, name, address,
                            contact_number, logo_url, wallpaper_url, thumbnail_url, description, latitude, longitude,
                            capacity, area,city)
VALUES (63, 8, 32, 1, 'Getfit Chi Nhánh HOÀNG DIỆU',
        'Lầu 3, Cao Ốc H3, 384 Hoàng Diệu, Phường 6, Quận 4, TP. HCM',
        '0988499745',
        '/img/departments/logos/63_logo.jpg',
        '/img/departments/wallpapers/63_wallpaper.jpg',
        '/img/departments/thumbnails/63_thumbnail.jpg',
        'Phòng tập được trang bị các thiết bị tập luyện hiện đại nhằm đem lại môi trường luyện tập đẳng cấp xứng tầm CLB 5 sao. Tất cả Khách hàng sẽ được kiểm tra thể chất bằng in Body & được tư vấn dinh dưỡng cũng như cách tập luyện phù hợp.',
        10.76051532090389, 106.69908029869617, 200, 200,'Hồ Chí Minh');

INSERT INTO gym_department (gym_department_id, brand_id, user_id, gym_department_status_key, name, address,
                            contact_number, logo_url, wallpaper_url, thumbnail_url, description, latitude, longitude,
                            capacity, area,city)
VALUES (87, 9, 33, 1, 'Elite Fitness XUÂN DIỆU',
        '51 Xuân Diệu, Quảng An, Tây Hồ, Hà Nội',
        '02473020888',
        '/img/departments/logos/87_logo.jpg',
        '/img/departments/wallpapers/87_wallpaper.jpg',
        '/img/departments/thumbnails/87_thumbnail.jpg',
        'CLB tiên phong của Elite Fitness, tọa lạc tại tòa tháp Syrena ngự trên mảnh đất trung tâm Hồ Tây đáng sống bậc nhất tại Hà Nội; Elite Fitness Xuân Diệu là sự lựa chọn hoàn hảo để tận hưởng trọn vẹn không gian tập luyện đẳng cấp, cộng đồng văn minh, một nhịp sống bình yên rất Hà Nội mà không kém phần sôi động.',
        21.063912459638402, 105.82813606669859, 200, 200,'Hà Nội');

INSERT INTO gym_department (gym_department_id, brand_id, user_id, gym_department_status_key, name, address,
                            contact_number, logo_url, wallpaper_url, thumbnail_url, description, latitude, longitude,
                            capacity, area,city)
VALUES (78, 9, 34, 1, 'Elite Fitness Bà Triệu',
        'Tầng 6, Tháp C, Vincom Center, 191 Bà Triệu, Hà Nội',
        '024 39749191',
        '/img/departments/logos78_logo.jpg',
        '/img/departments/wallpapers/78_wallpaper.jpg',
        '/img/departments/thumbnails/78_thumbnail.jpg',
        'Tự hào là một trong những Câu lạc bộ Elite Fitness Top 1 về sự sang trọng và đẳng cấp nhất của hệ thống Elite Fitness, Elite Fitness Vincom Bà Triệu luôn luôn nỗ lực không ngừng để tạo nên một định nghĩa khác về trải nghiệm tập luyện thể thao cao cấp trong suốt 8 năm qua. Chúng tôi với đội ngũ của nhiệt huyết và sự khát khao tạo nên những dịch vụ tuyệt vời trong một môi trường phòng tập hàng đầu. Mỗi Hội viên, mỗi khách hàng là một trọng tâm của chăm sóc và hỗ trợ một cách tận tâm để luôn đạt được mục tiêu sức khỏe trọn vẹn khi đến với chúng tôi. Hãy trở thành một thành viên trong hành trình kiến tạo giá trị sức khỏe cho cộng đồng của chúng tôi! Let’s be Elite!',
        21.01108468531799, 105.84965020691072, 200, 200,'Hà Nội');

INSERT INTO gym_department (gym_department_id, brand_id, user_id, gym_department_status_key, name, address,
                            contact_number, logo_url, wallpaper_url, thumbnail_url, description, latitude, longitude,
                            capacity, area,city)
VALUES (91, 9, 35, 1, 'Elite Fitness Nguyễn Chí Than',
        'Tầng 6, Vinhomes 54A Nguyễn Chí Thanh, Ngọc Khánh, Ba Đình, Hà Nội',
        '024 7307 8889',
        '/img/departments/logos/91_logo.jpg',
        '/img/departments/wallpapers/91_wallpaper.jpg',
        '/img/departments/thumbnails/91_thumbnail.jpg',
        'Tọa lạc tại vị trí trung tâm, Elite Fitness Nguyễn Chí Thanh là một trong những Câu lạc bộ có không gian tập rộng và sang trọng bậc nhất trong hệ thống Elite Fitness. Chúng tôi mong muốn mang đến những giá trị tích cực nhất; xây dựng một cộng đồng khỏe mạnh không những từ thể chất mà còn từ tinh thần bên trong; nơi mà những kiến thức luôn được chia sẻ. Hãy cùng bắt đầu hành trình cho cuộc sống của bạn trở nên tốt đẹp hơn cùng với Chúng tôi từ hôm nay!',
        21.025367760381087, 105.80951344612583, 200, 200,'Hà Nội');

INSERT INTO gym_department (gym_department_id, brand_id, user_id, gym_department_status_key, name, address,
                            contact_number, logo_url, wallpaper_url, thumbnail_url, description, latitude, longitude,
                            capacity, area,city)
VALUES (52, 10, 36, 1, 'Fit365 Fitness &Yoga Q.11',
        '219 Lý Thường Kiệt, Phường 15, Quận 11, Tp. Hồ Chí Minh , Ho Chi Minh City, Vietnam',
        '0909290880',
        '/img/departments/logos/52_logo.jpg',
        '/img/departments/wallpapers/52_wallpaper.jpg',
        '/img/departments/thumbnails/52_thumbnail.jpg',
        'Hãy đến ngay với 𝐅𝐈𝐓𝟑𝟔𝟓  hôm nay để tập luyện, tại đây bạn sẽ được tư vấn các bài tập phụ hợp với tình trạng sức khỏe của bản thân, nâng cấp body và trải nghiệm hệ thống máy tập an toàn cùng đội ngũ PT tận tâm nhất.Liên hệ ngay với 𝐅𝐈𝐓𝟑𝟔𝟓 để được tư vấn miễn phí, nhận ngay ưu đãi và đăng kí tập luyện sớm nhất có thể !!!',
        10.767766928241029, 106.65824315116448, 200, 200,'Hồ Chí Minh');

-- create Brand Amentites
-- Getfit Chi nhánh Oriental Tân Phú Amenities Associations
INSERT INTO gym_department_amenities (gym_department_id, amenitie_id)
VALUES (57, 51),
       (57, 52),
       (57, 53);
-- Getfit Chi nhánh Mia Center Amenities Associations
INSERT INTO gym_department_amenities (gym_department_id, amenitie_id)
VALUES (83, 51),
       (83, 52),
       (83, 53);
-- Getfit Chi Nhánh HOÀNG DIỆU Amenities Associations
INSERT INTO gym_department_amenities (gym_department_id, amenitie_id)
VALUES (63, 51),
       (63, 52),
       (63, 53);
-- Elite Fitness XUÂN DIỆU Amenities Associations
INSERT INTO gym_department_amenities (gym_department_id, amenitie_id)
VALUES (87, 54),
       (87, 55),
       (87, 56),
       (87, 57);
-- Elite Fitness Bà Triệu Amenities Associations
INSERT INTO gym_department_amenities (gym_department_id, amenitie_id)
VALUES (78, 54),
       (78, 55),
       (78, 56),
       (78, 57);
-- Elite Fitness Nguyễn Chí Than Amenities Associations
INSERT INTO gym_department_amenities (gym_department_id, amenitie_id)
VALUES (91, 54),
       (91, 55),
       (91, 56),
       (91, 57);

-- Fit365 Fitness &Yoga Q.11 Amenities Associations
INSERT INTO gym_department_amenities (gym_department_id, amenitie_id)
VALUES (52, 59),
       (52, 60),
       (52, 61),
       (52, 62);
--
INSERT INTO gym_department_features (feature_id, gym_department_id, feature_status)
VALUES (1, 57, 1),
       (2, 57, 1),
       (3, 57, 1),
       (4, 57, 1),
       (5, 57, 1);

INSERT INTO gym_department_features (feature_id, gym_department_id, feature_status)
VALUES (1, 83, 1),
       (2, 83, 1),
       (3, 83, 1),
       (6, 83, 1),
       (7, 83, 1);

INSERT INTO gym_department_features (feature_id, gym_department_id, feature_status)
VALUES (1, 63, 1),
       (3, 63, 1),
       (4, 63, 1),
       (5, 63, 1),
       (7, 63, 1);
--
INSERT INTO gym_department_features (feature_id, gym_department_id, feature_status)
VALUES (1, 91, 1),
       (2, 91, 1),
       (3, 91, 1),
       (4, 91, 1),
       (5, 91, 1);

INSERT INTO gym_department_features (feature_id, gym_department_id, feature_status)
VALUES (1, 78, 1),
       (2, 78, 1),
       (3, 78, 1),
       (6, 78, 1),
       (7, 78, 1);

INSERT INTO gym_department_features (feature_id, gym_department_id, feature_status)
VALUES (1, 87, 1),
       (3, 87, 1),
       (4, 87, 1),
       (5, 87, 1),
       (7, 87, 1);

--
INSERT INTO gym_department_features (feature_id, gym_department_id, feature_status)
VALUES (1, 52, 1),
       (2, 52, 1),
       (3, 52, 1),
       (4, 52, 1),
       (5, 52, 1);
-- Create Gym plan infos for Getfit
INSERT INTO gym_plan (plan_id, brand_id, gym_plan_key, gym_plan_status_key, gym_plan_type_key, name, description, price,
                      price_per_hours, plan_sold, duration, plan_before_active_validity, plan_after_active_validity)
VALUES (51, 8, 1, 1, 1, 'Gói Linh Hoạt',
        'Gói tập linh hoạt, phù hợp cho người muốn trải nghiệm các phòng tập. Đặc quyền tập luyện ở mọi cơ sở.', 0,
        50.00, 0, 0, 10, 30),
       (52, 8, 2, 1, 2, 'Gói 1 tháng',
        'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ',
        150.00, 0, 0, 30, 10, 30),
       (53, 8, 2, 1, 2, 'Gói 3 tháng',
        'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ',
        450.00, 0, 0, 90, 10, 90);

-- Create Gym plan infos for Elite
INSERT INTO gym_plan (plan_id, brand_id, gym_plan_key, gym_plan_status_key, gym_plan_type_key, name, description, price,
                      price_per_hours, plan_sold, duration, plan_before_active_validity, plan_after_active_validity)
VALUES (54, 9, 1, 1, 1, 'Gói Linh Hoạt',
        'Gói tập linh hoạt, phù hợp cho người muốn trải nghiệm các phòng tập. Đặc quyền tập luyện ở mọi cơ sở.', 0,
        50.00, 0, 0, 10, 30),
       (55, 9, 2, 1, 2, 'Gói 1 tháng',
        'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ',
        150.00, 0, 0, 30, 10, 30),
       (56, 9, 2, 1, 2, 'Gói 3 tháng',
        'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ',
        450.00, 0, 0, 90, 10, 90);

-- Create Gym plan infos for Fit365
INSERT INTO gym_plan (plan_id, brand_id, gym_plan_key, gym_plan_status_key, gym_plan_type_key, name, description, price,
                      price_per_hours, plan_sold, duration, plan_before_active_validity, plan_after_active_validity)
VALUES (57, 10, 1, 1, 1, 'Gói Linh Hoạt',
        'Gói tập linh hoạt, phù hợp cho người muốn trải nghiệm các phòng tập. Đặc quyền tập luyện ở mọi cơ sở.', 0,
        50.00, 0, 0, 10, 30),
       (58, 10, 2, 1, 2, 'Gói 1 tháng',
        'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ',
        150.00, 0, 0, 30, 10, 30),
       (59, 10, 2, 1, 2, 'Gói 3 tháng',
        'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ',
        450.00, 0, 0, 90, 10, 90);

-- For Getfit Chi nhánh Oriental Tân Phú (department ID 57)
INSERT INTO gym_department_plans(gym_department_id, plan_id)
VALUES (57, 51), -- Gói Linh Hoạt
       (57, 52), -- Gói 1 tháng
       (57, 53); -- Gói 3 tháng

-- For Getfit Chi nhánh HOÀNG DIỆU (department ID 63)
INSERT INTO gym_department_plans(gym_department_id, plan_id)
VALUES (63, 51), -- Gói Linh Hoạt
       (63, 52), -- Gói 1 tháng
       (63, 53); -- Gói 3 tháng

-- For Getfit Chi nhánh Mia Center (department ID 83)
INSERT INTO gym_department_plans(gym_department_id, plan_id)
VALUES (83, 51), -- Gói Linh Hoạt
       (83, 52), -- Gói 1 tháng
       (83, 53); -- Gói 3 tháng

-- For Elite Fitness XUÂN DIỆU (department ID 87)
INSERT INTO gym_department_plans(gym_department_id, plan_id)
VALUES (87, 54), -- Gói Linh Hoạt
       (87, 55), -- Gói 1 tháng
       (87, 56); -- Gói 3 tháng

-- For Elite Fitness Bà Triệu (department ID 78)
INSERT INTO gym_department_plans(gym_department_id, plan_id)
VALUES (78, 54), -- Gói Linh Hoạt
       (78, 55), -- Gói 1 tháng
       (78, 56); -- Gói 3 tháng

-- For Elite Fitness Nguyễn Chí Thanh (department ID 91)
INSERT INTO gym_department_plans(gym_department_id, plan_id)
VALUES (91, 54), -- Gói Linh Hoạt
       (91, 55), -- Gói 1 tháng
       (91, 56); -- Gói 3 tháng

-- For Fit365 Fitness & Yoga Q.11 (department ID 52)
INSERT INTO gym_department_plans(gym_department_id, plan_id)
VALUES (52, 57), -- Gói Linh Hoạt
       (52, 58), -- Gói 1 tháng
       (52, 59); -- Gói 3 tháng


-- Create Gym Department Gallery

-- Create Gym Department Gallery

-- Gym Department Gallery for Getfit Chi nhánh Oriental (Tân Phú)
INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (57, '/img/departments/albums/57_albums_1701165141821.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (57, '/img/departments/albums/57_albums_1701165146517.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (57, '/img/departments/albums/57_albums_1701165154409.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (57, '/img/departments/albums/57_albums_1701165205215.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (57, '/img/departments/albums/57_albums_1701165208307.jpg');

-- Gym Department Gallery for Getfit Chi nhánh Mia Center
INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (83, '/img/departments/albums/83_albums_1701167708799.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (83, '/img/departments/albums/83_albums_1701167712479.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (83, '/img/departments/albums/83_albums_1701167716572.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (83, '/img/departments/albums/83_albums_1701167726412.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (83, '/img/departments/albums/83_albums_1701167731963.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (83, '/img/departments/albums/83_albums_1701167735604.jpg');

-- Gym Department Gallery for Getfit CHI NHÁNH HOÀNG DIỆU
INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (63, '/img/departments/albums/63_albums_1701168326395.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (63, '/img/departments/albums/63_albums_1701168329987.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (63, '/img/departments/albums/63_albums_1701168335998.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (63, '/img/departments/albums/63_albums_1701168341023.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (63, '/img/departments/albums/63_albums_1701168345097.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (63, '/img/departments/albums/63_albums_1701168359118.jpg');

-- Gym Department Gallery for Elite Fitness XUÂN DIỆU
INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (87,'/img/departments/albums/87_albums_1701176093401.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (87,'/img/departments/albums/87_albums_1701176096809.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (87,'/img/departments/albums/87_albums_1701176101174.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (87,'/img/departments/albums/87_albums_1701176104813.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (87,'/img/departments/albums/87_albums_1701176107670.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (87,'/img/departments/albums/87_albums_1701176110169.jpg');

-- Gym Department Gallery for Elite Fitness Nguyễn Chí Thanh
INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (91,'/img/departments/albums/91_albums_1701177622583.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (91,'/img/departments/albums/91_albums_1701177625728.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (91,'/img/departments/albums/91_albums_1701177629250.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (91,'/img/departments/albums/91_albums_1701177632612.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (91,'/img/departments/albums/91_albums_1701177635440.jpg');

-- Add more images as needed...
-- Gym Department Gallery for Elite Fitness Bà Triệu
INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (78,'/img/departments/albums/78_albums_1701176588906.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (78,'/img/departments/albums/78_albums_1701176593448.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (78,'/img/departments/albums/78_albums_1701176597218.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (78,'/img/departments/albums/78_albums_1701176600865.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (78,'/img/departments/albums/78_albums_1701176604640.jpg');

-- Add more images as needed...
-- Gym Department Gallery for Fit365 Fitness &Yoga Q.11
INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (52, '/img/departments/albums/52_albums_1701178346542.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (52, '/img/departments/albums/52_albums_1701178339564.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (52, '/img/departments/albums/52_albums_1701178352469.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (52, '/img/departments/albums/52_albums_1701178356901.jpg');

INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES (52, '/img/departments/albums/52_albums_1701178361013.jpg');

-- Add more images as needed...


-- Create Gym Department Schedule for Getfit Chi nhánh Oriental Tân Phú
INSERT INTO gym_department_schedule (gym_department_id, day, open_time, close_time)
VALUES (57, 'Thứ 2', '7:00 AM', '9:00 PM'),
       (57, 'Thứ 3', '7:00 AM', '9:00 PM'),
       (57, 'Thứ 4', '7:00 AM', '9:00 PM'),
       (57, 'Thứ 5', '7:00 AM', '9:00 PM'),
       (57, 'Thứ 6', '7:00 AM', '9:00 PM'),
       (57, 'Thứ 7', '8:00 AM', '10:00 PM'),
       (57, 'Chủ nhật', '8:00 AM', '10:00 PM');

INSERT INTO gym_department_schedule (gym_department_id, day, open_time, close_time)
VALUES (83, 'Thứ 2', '6:00 AM', '11:00 PM'),
       (83, 'Thứ 3', '6:00 AM', '11:00 PM'),
       (83, 'Thứ 4', '6:00 AM', '11:00 PM'),
       (83, 'Thứ 5', '6:00 AM', '11:00 PM'),
       (83, 'Thứ 6', '6:00 AM', '11:00 PM'),
       (83, 'Thứ 7', '6:00 AM', '11:00 PM'),
       (83, 'Chủ nhật', '6:00 AM', '11:00 PM');

INSERT INTO gym_department_schedule (gym_department_id, day, open_time, close_time)
VALUES (63, 'Thứ 2', '7:00 AM', '10:00 PM'),
       (63, 'Thứ 3', '7:00 AM', '10:00 PM'),
       (63, 'Thứ 4', '7:00 AM', '10:00 PM'),
       (63, 'Thứ 5', '7:00 AM', '10:00 PM'),
       (63, 'Thứ 6', '7:00 AM', '10:00 PM'),
       (63, 'Thứ 7', '7:00 AM', '10:00 PM'),
       (63, 'Chủ nhật', '7:00 AM', '10:00 PM');

INSERT INTO gym_department_schedule (gym_department_id, day, open_time, close_time)
VALUES (87, 'Thứ 2', '5:00 AM', '11:00 PM'),
       (87, 'Thứ 3', '5:00 AM', '11:00 PM'),
       (87, 'Thứ 4', '5:00 AM', '11:00 PM'),
       (87, 'Thứ 5', '5:00 AM', '11:00 PM'),
       (87, 'Thứ 6', '5:00 AM', '11:00 PM'),
       (87, 'Thứ 7', '5:00 AM', '11:00 PM'),
       (87, 'Chủ nhật', '5:00 AM', '11:00 PM');

INSERT INTO gym_department_schedule (gym_department_id, day, open_time, close_time)
VALUES (78, 'Thứ 2', '6:30 AM', '10:00 PM'),
       (78, 'Thứ 3', '6:30 AM', '10:00 PM'),
       (78, 'Thứ 4', '6:30 AM', '10:00 PM'),
       (78, 'Thứ 5', '6:30 AM', '10:00 PM'),
       (78, 'Thứ 6', '6:30 AM', '10:00 PM'),
       (78, 'Thứ 7', '6:30 AM', '10:00 PM'),
       (78, 'Chủ nhật', '6:30 AM', '10:00 PM');

INSERT INTO gym_department_schedule (gym_department_id, day, open_time, close_time)
VALUES (91, 'Thứ 2', '6:00 AM', '9:00 PM'),
       (91, 'Thứ 3', '6:00 AM', '9:00 PM'),
       (91, 'Thứ 4', '6:00 AM', '9:00 PM'),
       (91, 'Thứ 5', '6:00 AM', '9:00 PM'),
       (91, 'Thứ 6', '6:00 AM', '9:00 PM'),
       (91, 'Thứ 7', '6:00 AM', '9:00 PM'),
       (91, 'Chủ nhật', '6:00 AM', '9:00 PM');

INSERT INTO gym_department_schedule (gym_department_id, day, open_time, close_time)
VALUES (52, 'Thứ 2', '7:00 AM', '10:00 PM'),
       (52, 'Thứ 3', '7:00 AM', '10:00 PM'),
       (52, 'Thứ 4', '7:00 AM', '10:00 PM'),
       (52, 'Thứ 5', '7:00 AM', '10:00 PM'),
       (52, 'Thứ 6', '7:00 AM', '10:00 PM'),
       (52, 'Thứ 7', '7:00 AM', '10:00 PM'),
       (52, 'Chủ nhật', '7:00 AM', '10:00 PM');


/********** Department Feedback Creation ***********/
-- For Getfit Chi nhánh Oriental Tân Phú (department ID 57)
INSERT INTO user_feedback (user_id, gym_plan_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES (1, 51, 57, 4, 'Cơ sở vật chất tốt!', '2023-10-15 11:30:00', 1),
       (2, 52, 57, 3, 'Cần nhiều hơn về đa dạng thiết bị', '2023-10-17 14:45:00', 1),
       (3, 53, 57, 3, 'Thiếu sự phong phú về lịch tập', '2023-10-16 16:00:00', 1),
       (4, 51, 57, 5, 'Hài lòng với dịch vụ', '2023-10-15 14:00:00', 1),
       (5, 52, 57, 2, 'Có thể cải thiện chất lượng thiết bị', '2023-10-19 09:00:00', 1);

-- For Getfit Chi nhánh HOÀNG DIỆU (department ID 63)
INSERT INTO user_feedback (user_id, gym_plan_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES (1, 51, 63, 4, 'Môi trường tốt và huấn luyện viên thân thiện', '2023-10-15 15:00:00', 1),
       (1, 52, 63, 3, 'Có thể cải thiện về vấn đề vệ sinh', '2023-10-16 16:30:00', 1),
       (3, 53, 63, 3, 'Thiếu sự phong phú về lịch tập', '2023-10-16 16:00:00', 1),
       (4, 51, 63, 5, 'Hài lòng với dịch vụ', '2023-10-15 14:00:00', 1),
       (5, 52, 63, 2, 'Có thể cải thiện chất lượng thiết bị', '2023-10-19 09:00:00', 1);

-- For Getfit Chi nhánh Mia Center (department ID 83)
INSERT INTO user_feedback (user_id, gym_plan_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES (1, 51, 83, 5, 'Nhân viên tuyệt vời!', '2023-10-16 09:30:00', 1),
       (1, 52, 83, 4, 'Môi trường sạch sẽ và thiết bị được bảo quản tốt', '2023-10-17 10:20:00', 1),
       (3, 53, 83, 3, 'Thiếu sự phong phú về lịch tập', '2023-10-16 16:00:00', 1),
       (4, 51, 83, 5, 'Hài lòng với dịch vụ', '2023-10-15 14:00:00', 1),
       (5, 52, 83, 2, 'Có thể cải thiện chất lượng thiết bị', '2023-10-19 09:00:00', 1);

-- For Elite Fitness XUÂN DIỆU (department ID 87)
INSERT INTO user_feedback (user_id, gym_plan_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES (1, 54, 87, 5, 'Hoàn toàn yêu thích!', '2023-10-17 13:45:00', 1),
       (1, 55, 87, 4, 'Nhân viên hữu ích và am hiểu', '2023-10-18 11:00:00', 1),
       (3, 56, 87, 3, 'Thiếu sự phong phú về lịch tập', '2023-10-16 16:00:00', 1),
       (4, 54, 87, 5, 'Hài lòng với dịch vụ', '2023-10-15 14:00:00', 1),
       (5, 56, 87, 2, 'Có thể cải thiện chất lượng thiết bị', '2023-10-19 09:00:00', 1);

-- For Elite Fitness Bà Triệu (department ID 78)
INSERT INTO user_feedback (user_id, gym_plan_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES (1, 54, 78, 5, 'Dịch vụ tuyệt vời!', '2023-10-17 18:20:00', 1),
       (1, 55, 78, 3, 'Có thể cung cấp thiết bị tốt hơn', '2023-10-18 19:00:00', 1),
       (3, 56, 78, 3, 'Thiếu sự phong phú về lịch tập', '2023-10-16 16:00:00', 1),
       (4, 54, 78, 5, 'Hài lòng với dịch vụ', '2023-10-15 14:00:00', 1),
       (5, 56, 78, 2, 'Có thể cải thiện chất lượng thiết bị', '2023-10-19 09:00:00', 1);

-- For Elite Fitness Nguyễn Chí Thanh (department ID 91)
INSERT INTO user_feedback (user_id, gym_plan_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES (1, 54, 91, 4, 'Các huấn luyện viên tuyệt vời!', '2023-10-15 12:00:00', 1),
       (1, 55, 91, 2, 'Vệ sinh kém', '2023-10-16 17:30:00', 1),
       (3, 56, 91, 3, 'Thiếu sự phong phú về lịch tập', '2023-10-16 16:00:00', 1),
       (4, 54, 91, 5, 'Hài lòng với dịch vụ', '2023-10-15 14:00:00', 1),
       (5, 56, 91, 2, 'Có thể cải thiện chất lượng thiết bị', '2023-10-19 09:00:00', 1);

-- For Fit365 Fitness & Yoga Q.11 (department ID 52)
INSERT INTO user_feedback (user_id, gym_plan_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES (1, 57, 52, 3, 'Cần cải thiện dịch vụ', '2023-10-18 14:15:00', 1),
       (1, 58, 52, 4, 'Huấn luyện viên nhiệt tình', '2023-10-19 09:45:00', 1),
       (3, 59, 52, 3, 'Thiếu sự phong phú về lịch tập', '2023-10-16 16:00:00', 1),
       (4, 57, 52, 5, 'Hài lòng với dịch vụ', '2023-10-15 14:00:00', 1),
       (5, 58, 52, 2, 'Có thể cải thiện chất lượng thiết bị', '2023-10-19 09:00:00', 1);

/**************************************************************************************** Brand Creation - LINH **********************************************************************/
INSERT INTO brand (brand_id, user_id, name, logo_url, wallpaper_url, thumbnail_url, description, rating, contact_number,
                   contact_email, brand_status_key)

VALUES (11, 12, 'Advance Fitness & Gym',
        '/img/brands/logos/11_logo.jpg',
        '/img/brands/wallpapers/11_wallpaper.jpg',
        '/img/brands/thumbnails/11_thumbnail.jpg',
        'Đến với Advance, học viên sẽ được kiểm tra thể chất bằng máy Check in Body – một trong những thiết bị hiện đại nhất Việt Nam hiện nay, được tư vấn dinh dưỡng như cách ăn uống và phân tích thức cả khi ở nhà.

  Nếu trở thành học viên ở Advance, bạn sẽ được kiểm tra sức khỏe đều đặn. Thông qua đó, huấn luyện viên sẽ đánh giá thể trạng và đưa ra phương pháp tập luyện phù hợp cho từng người.',
        0, '1900633531', ' advancefitnessgym@gmail.com', 1),

       (12, 13, 'The New Gym',
        '/img/brands/logos/12_logo.jpg',
        '/img/brands/wallpapers/12_wallpaper.jpg',
        '/img/brands/thumbnails/12_thumbnail.jpg',
        'Hơn 200 máy tập từ cơ đến cardio và các loại tạ tự do. Điểm khác biệt của The New Gym chính là phòng tập gym 24/7, phòng tập Fitness and Yoga 24h, học viên ở đây có thể tập bất cứ khi nào rảnh.

  Nếu mong muốn hiện tại của bạn là tìm phòng tập gym Quận Tân Bình giá rẻ, phòng tập gym Bình Thạnh giá rẻ hay phòng tập gym quận 6 giá rẻ thì The New Gym là dành cho bạn.

  Giá tập tại đây chỉ với 300.000 VNĐ/ 1 tháng, huấn luyện viên tại The New Gym sẵn sàng tư vấn bài tập và chế độ ăn cho học viên mà không phát sinh bất kỳ chi phí nào',
        0, '1900636920', 'info@thenewgym.vn', 1),

       (13, 14, 'VShape Gym & Yoga',
        '/img/brands/logos/13_logo.jpg',
        '/img/brands/wallpapers/13_wallpaper.jpg',
        '/img/brands/thumbnails/13_thumbnail.jpg',
        'Vshape được đầu tư cơ sở vật chất hiện đại, không gian thoáng mát, rộng rãi và trang thiết bị nhập khẩu hoàn toàn.

  Với diện tích 650m2 , tuy nhỏ hơn so với những phòng tập trên. Nhưng phần đầu tư trang thiết bị, máy móc thì không hề thua kém. Tất cả đều đạt tiêu chuẩn quốc tế để phục vụ cho việc tập luyện.

  Tại VShape hỗ trợ máy đánh giá sức khỏe, đo chỉ số cơ thể chi tiết và chuyên nghiệp của Inbody, công nghệ Mỹ.',
        0, '0871080815', 'vshapegym@gmail.com', 1);

INSERT INTO brand_amenities (amenitie_id, brand_id, photo_url, amenitie_name, description, amenitie_status)

VALUES (151, 11, '/img/brandAmenities/1701142144961_brandAmenities.jpg', 'Aerobic',
        'Chương trình tập luyện năng động với nhịp nhàng, kết hợp các bài tập cardio để cải thiện sức khỏe tim mạch và sự linh hoạt.',
        1),
       (152, 11, '/img/brandAmenities/1701142151557_brandAmenities.jpg', 'Yoga',
        ' Mang lại sự cân bằng giữa tâm hồn và cơ thể thông qua các động tác linh hoạt, thở đều, và thiền. Dành cho những người muốn giảm căng thẳng và tăng cường sức khỏe tinh thần.',
        1),
       (153, 11, '/img/brandAmenities/1701142157022_brandAmenities.jpg', 'Zumba',
        'Buổi tập năng động với những động tác nhảy và nhịp điệu âm nhạc sôi động. Phù hợp cho những người yêu thích không khí vui nhộn và muốn đốt cháy calo hiệu quả..',
        1),
       (154, 11, '/img/brandAmenities/1701142164233_brandAmenities.jpg', 'Combat Fit',
        'Kết hợp các phong cách võ thuật và bài tập cường độ cao để phát triển sức mạnh, sự linh hoạt và sự chịu đựng. Dành cho những người muốn nâng cao kỹ thuật tự vệ và cường độ tập luyện.',
        1),
       (155, 11, '/img/brandAmenities/1701142168951_brandAmenities.jpg', 'Fight Fit',
        'Tập luyện dựa trên các phương pháp đối kháng và võ thuật, giúp cải thiện sức mạnh, tăng cường cardio và phản xạ. Là lựa chọn phù hợp cho những người muốn thách thức bản thân trong môi trường tập luyện độc đáo.',
        1),

       (156, 12, '/img/brandAmenities/1701143169446_brandAmenities.jpg', 'SEXY DANCE',
        'Sexy dance hay còn gọi là nhảy sexy là những điệu nhảy hiện đại đầy chất trẻ, quyến rũ, chủ yếu sử dụng sự mềm dẻo và linh động làm nổi bật nên đường nét quyến rũ của cơ thể. Sexy dance với đặc thù nhằm vào việc làm nổi bật những vùng ngực, eo, bụng dưới, đùi, mông khi nhảy, vì vậy các nhóm cơ trên toàn bộ cơ thể như cơ bụng, lưng, chân,… đều phải vận động',
        1),
       (157, 12, '/img/brandAmenities/1701143214325_brandAmenities.jpg', 'BUM N TUM',
        'Bum n Tum là chương trình 45 phút được thiết kế gồm 3 phần. Phần 1 là các bài tập phần mông. Phần 2 là các bài tập phần đùi. Phần 3 là các bài tập phần cơ bụng. Chỉ với 45 phút sẽ giúp bạn đôt cháy nhiều năng lượng và có 1 phần cơ bụng, mông, đùi săn chắc.',
        1),
       (158, 12, '/img/brandAmenities/1701143234612_brandAmenities.jpg', 'NEW PUMP',
        'New Pump là môn học được thiết kế với thanh bar, tạ và bục. Bộ môn này giúp bạn tập luyện toàn thân, hoàn toàn lý tưởng cho bất kỳ ai muốn có được thân hình mảnh mai, săn chắc . Học viên sẽ sử dụng mức tạ nhẹ đến trung bình. Các giảng viên sẽ hướng dẫn bạn từng bước di chuyển, kĩ thuật được thiết kế trên nền âm nhạc một cách khoa học giúp bạn đạt được kết quả cao.',
        1),
       (159, 12, '/img/brandAmenities/1701143247303_brandAmenities.jpg', 'NEW COMBAT',
        'New Combat là môn học cường độ cao lấy cảm hứng từ võ thuật giúp bạn giải tỏa căng thẳng và tăng cường sức khỏe thể chất. Suốt 1 giờ đồng hồ được “đấm và đá” theo âm nhạc sôi động, bạn sẽ có cảm giác sung sức như một nhà vô địch',
        1),
       (160, 12, '/img/brandAmenities/1701143253322_brandAmenities.jpg', 'DANCE FIT',
        'Dance Fit là bộ môn dance fitness kết hợp những vũ đạo tự do, đơn giản trên nền nhạc sôi động trong thời gian dài, giúp đốt cháy mỡ thừa, giảm cân hiệu quả và thư giãn cùng âm nhạc. Khác với Zumba cùng những vũ điệu Latin, đến với New Dance bạn sẽ đắm chìm trong bữa tiệc âm nhạc nhiều màu sắc: Pop, Hip Hop ...',
        1),

       (161, 13, '/img/brandAmenities/1701143641506_brandAmenities.jpg', 'Aerobic',
        'Cung cấp các buổi tập năng động với nhịp điệu âm nhạc, nhằm cải thiện sức khỏe tim mạch, tăng cường sức mạnh cơ bắp và đốt cháy calo. Đây là lựa chọn phổ biến cho những người muốn tăng cường thể lực và giảm cân.',
        1),
       (162, 13, '/img/brandAmenities/1701143646667_brandAmenities.jpg', 'Gym',
        'Trang bị phòng tập gym đầy đủ các máy móc và trang thiết bị để phục vụ mọi nhu cầu tập luyện cơ bản hoặc chuyên sâu. Hướng dẫn viên sẽ hỗ trợ hội viên trong việc lên kế hoạch và thực hiện các bài tập tùy thuộc vào mục tiêu cá nhân.',
        1),
       (163, 13, '/img/brandAmenities/1701143651396_brandAmenities.jpg', 'Yoga',
        'Tạo không gian yên tĩnh và tập trung, Yoga giúp cải thiện linh hoạt, giảm căng thẳng và kích thích sự ổn định tinh thần. Cung cấp các lớp hướng dẫn từ cơ bản đến nâng cao, phù hợp với mọi đối tượng và trình độ.',
        1);



INSERT INTO gym_department (gym_department_id, brand_id, user_id, gym_department_status_key, name, address,
                            contact_number,
                            logo_url, wallpaper_url, thumbnail_url, description, latitude, longitude, capacity, area,city)

VALUES (12, 11, 37, 1, 'Advance Fitness & Gym Tôn Dật Tiên',
        'SE05, GARDEN COURT 1, Tôn Dật Tiên, Phú Mỹ Hưng, Q7, Tp.HCM', '0854160555',
        '/img/departments/logos/12_logo.jpg',
        '/img/departments/wallpapers/12_wallpaper.jpg',
        '/img/departments/thumbnails/12_thumbnail.jpg',
        'Phòng tập gym Advance Fitness & Gym không hoành tráng như phố Nguyễn Huệ, nhưng lợi thế phố đi bộ Tôn Dật Tiên kết hợp hài hòa cây xanh, mặt nước, lối dạo, cảnh quan tự nhiên những chỗ dừng chân.

 Đến với phòng tập bạn sẽ được kiểm tra thể chất bằng máy Check in Body (một trong những thiết bị hiện đại nhất tại Việt Nam hiện nay), sau đó được các chuyên gia dinh dưỡng tư vấn về cách ăn uống, phân tích thức ăn (ngay cả khi ở nhà)',
        10.722874426578613, 106.71464114001405,
        200, 800, 'Hồ Chí Minh'),

       (13, 11, 38, 1, 'Advance Fitness & Gym Nguyễn Lương Bằng',
        'ST05 Block E, Riverside Residence, Nguyễn Lương Bằng, Phú Mỹ Hưng, Q7, Tp.HCM', '0854118777',
        '/img/departments/logos/13_logo.jpg',
        '/img/departments/wallpapers/13_wallpaper.jpg',
        '/img/departments/thumbnails/13_thumbnail.jpg',
        'Phòng tập gym Advance Fitness & Gym với mong muốn mang lại môi trường tập luyện chuyên nghiệp theo hình thức 1:1, tức một huấn luyện viên hướng dẫn cho một học viên đã được trung tâm cam kết áp dụng tuyệt đối.

 Đến với phòng tập bạn sẽ được kiểm tra thể chất bằng máy Check in Body (một trong những thiết bị hiện đại nhất tại Việt Nam hiện nay), sau đó được các chuyên gia dinh dưỡng tư vấn về cách ăn uống, phân tích thức ăn (ngay cả khi ở nhà)',
        10.720789817865652, 106.72672455649781,
        300, 1100,'Hồ Chí Minh'),

       (14, 11, 39, 1, 'Advance Fitness & Gym Kỳ Đồng', '05 Kỳ Đồng, Phường 6 Quận 3 TPHCM.', '0862982111',
        '/img/departments/logos/14_logo.jpg',
        '/img/departments/wallpapers/14_wallpaper.jpg',
        '/img/departments/thumbnails/14_thumbnail.jpg',
        'Phòng tập gym Advance Fitness & Gym được thành lập với mong muốn mang lại môi trường tập luyện chuyên nghiệp theo hình thức 1:1, đó là hình thức của một huấn luyện viên hướng dẫn cho một học viên đã được trung tâm cam kết áp dụng tuyệt đối. Đến với Trung tâm các học viên sẽ được kiểm tra thể chất bằng máy Check in Body sau đó được các chuyên gia dinh dưỡng tư vấn về cách ăn uống, phân tích thức ăn.',
        10.78467544118492, 106.68288665202464,
        450, 1200,'Hồ Chí Minh'),

       (15, 12, 40, 1, 'The New Gym Nguyễn Chí Thanh', '332 Nguyễn Chí Thanh, Phường 5, Quận 10, Thành phố Hồ Chí Minh',
        '1900636920',
        '/img/departments/logos/15_logo.jpg',
        '/img/departments/wallpapers/15_wallpaper.jpg',
        '/img/departments/thumbnails/15_thumbnail.jpg',
        'The New Gym có diện tích 2.000m2, gồm các khu vực tập luyện chính như Cardio, Strength, Free weights, Functional Training, Studio, Stretching, phù hợp với nhu cầu tập luyện cho mọi đối tượng.',
        10.760832460144496, 106.66690168835657,
        600, 2000,'Hồ Chí Minh'),

       (16, 12, 41, 1, 'The New Gym Điện Biên Phủ', '256 Điện Biên Phủ, Phường 7, Quận 3, Thành phố Hồ Chí Minh',
        '1900 63 69 20',
        '/img/departments/logos/16_logo.jpg',
        '/img/departments/wallpapers/16_wallpaper.jpg',
        '/img/departments/thumbnails/16_thumbnail.jpg',
        'The New Gym có diện tích 1.800m2 nằm ở vị trí đẹp trên đường Điện Biên Phủ, gồm các khu vực tập luyện chính như Cardio, Strength, Free weights, Functional Training, Studio, Stretching, phù hợp với nhu cầu tập luyện cho mọi đối tượng.',
        10.779821919040735, 106.68661522494476,
        400, 1800,'Hồ Chí Minh'),

       (17, 12, 42, 1, 'The New Gym Hoàng Văn Thụ',
        'Lầu 5, 1/1 Hoàng Việt, Phường 4, Quận Tân Bình, Thành phố Hồ Chí Minh', '1900636920',
        '/img/departments/logos/17_logo.jpg',
        '/img/departments/wallpapers/17_wallpaper.jpg',
        '/img/departments/thumbnails/17_thumbnail.jpg',
        'The New Gym có diện tích không quá lớn nhưng lợi thế về vị trí thuận lợi view từ trên cao, gồm các khu vực tập luyện chính như Cardio, Strength, Free weights, Functional Training, Studio, Stretching, phù hợp với nhu cầu tập luyện cho mọi đối tượng.',
        10.797626444808325, 106.65933092494488,
        300, 1200, 'Hồ Chí Minh'),

       (18, 12, 43, 1, 'The New Gym Ung Văn Khiêm',
        '58D Ung Văn Khiêm, Phường 25, Quận Bình Thạnh, Thành phố Hồ Chí Minh', '1900636920',
        '/img/departments/logos/18_logo.jpg',
        '/img/departments/wallpapers/18_wallpaper.jpg',
        '/img/departments/thumbnails/18_thumbnail.jpg',
        'The New Gym có lợi thế về vị trí đắc địa trên Quận Bình Thạnh, gồm các khu vực tập luyện chính như Cardio, Strength, Free weights, Functional Training, Studio, Stretching, phù hợp với nhu cầu tập luyện cho mọi đối tượng.',
        10.808073113779995, 106.71534072494497,
        500, 1800, 'Hồ Chí Minh'),

       (19, 13, 44, 1, 'VShape Fitness & Yoga Center, Trường Chinh',
        '491/21-23 Đường Trường Chinh, Phường 14, Quận Tân Bình, Hồ Chí Minh', '0871080815',
        '/img/departments/logos/19_logo.jpg',
        '/img/departments/wallpapers/19_wallpaper.jpg',
        '/img/departments/thumbnails/19_thumbnail.jpg',
        'Phòng tập gym VShape Fitness & Yoga Center tạo dựng hình tượng cho mình là 1 trung tâm thể dục thể hình – thẩm mỹ – yoga đáp ứng mọi nhu cầu về tập luyện thể hình và rèn luyện sức khỏe với mong muốn mang đến cho hội viên 1 không gian tập luyện chuyên nghiệp, thân thiện và luôn tạo niềm tin, niềm hứng thú tập luyện cho hội viên.',
        10.793479126084165, 106.65255131145184,
        200, 650, 'Hồ Chí Minh'),

       (20, 13, 45, 1, 'VShape Fitness & Yoga Center, Vincom Plus', 'Lầu 3 – 307 Nguyễn Duy Trinh, Quận 2, Hồ Chí Minh',
        '0871080815',
        '/img/departments/logos/20_logo.jpg',
        '/img/departments/wallpapers/20_wallpaper.jpg',
        '/img/departments/thumbnails/20_thumbnail.jpg',
        'Bạn là người yêu thích các bộ môn thể dục và mong muốn có được sức khoẻ tốt cùng với vóc dáng thon gọn, săn chắc? Hoặc bạn đã bắt đầu kế hoạch tập luyện nhưng chưa thể vượt qua các trở ngại và nỗi lo lắng? Đến với phòng tập gym VShape Fitness & Yoga quận 2, bạn sẽ được các huấn luyện viên hỗ trợ, tư vấn và chia sẻ kinh nghiệm trong các bài tập thể thao để bạn tận hưởng cuộc sống thật trọn vẹn và tràn đầy năng lượng.',
        10.789020254962827, 106.76547901599146,
        180, 550, 'Hồ Chí Minh');


INSERT INTO gym_department_amenities (gym_department_id, amenitie_id)

VALUES (12, 151),
       (12, 152),
       (12, 153),
       (12, 154),
       (12, 155),

       (13, 151),
       (13, 152),
       (13, 153),
       (13, 154),
       (13, 155),

       (14, 151),
       (14, 152),
       (14, 153),
       (14, 154),
       (14, 155),

       (15, 156),
       (15, 157),
       (15, 158),
       (15, 159),
       (15, 160),

       (16, 156),
       (16, 157),
       (16, 158),
       (16, 159),
       (16, 160),

       (17, 156),
       (17, 157),
       (17, 158),
       (17, 159),
       (17, 160),

       (18, 156),
       (18, 157),
       (18, 158),
       (18, 159),
       (18, 160),

       (19, 161),
       (19, 162),
       (19, 163),

       (20, 161),
       (20, 162),
       (20, 163);

-- Create Gym Department Features
INSERT INTO gym_department_features (feature_id, gym_department_id, feature_status)
VALUES (1, 12, 1),
       (2, 12, 1),
       (3, 12, 1),
       (4, 12, 1),
       (5, 12, 1),

       (1, 13, 1),
       (2, 13, 1),
       (3, 13, 1),
       (6, 13, 1),
       (7, 13, 1),

       (1, 14, 1),
       (3, 14, 1),
       (4, 14, 1),
       (5, 14, 1),
       (7, 14, 1),

       (1, 15, 1),
       (3, 15, 1),
       (4, 15, 1),
       (5, 15, 1),
       (7, 15, 1),

       (1, 16, 1),
       (3, 16, 1),
       (4, 16, 1),
       (5, 16, 1),
       (7, 16, 1),

       (1, 17, 1),
       (3, 17, 1),
       (4, 17, 1),
       (5, 17, 1),
       (7, 17, 1),

       (1, 18, 1),
       (3, 18, 1),
       (4, 18, 1),
       (5, 18, 1),
       (7, 18, 1),

       (1, 19, 1),
       (3, 19, 1),
       (4, 19, 1),
       (5, 19, 1),
       (7, 19, 1),

       (1, 20, 1),
       (3, 20, 1),
       (4, 20, 1),
       (5, 20, 1),
       (7, 20, 1);

INSERT INTO gym_department_albums (gym_department_id, photo_url)

VALUES (12, '/img/departments/albums/12_albums_1701178952407.jpg'),
       (12, '/img/departments/albums/12_albums_1701178958581.jpg'),
       (12, '/img/departments/albums/12_albums_1701179042607.jpg'),
       (12, '/img/departments/albums/12_albums_1701179045461.jpg'),
       (12, '/img/departments/albums/12_albums_1701179048098.jpg'),

       (13, '/img/departments/albums/13_albums_1701179805196.jpg'),
       (13, '/img/departments/albums/13_albums_1701179808551.jpg'),
       (13, '/img/departments/albums/13_albums_1701179811684.jpg'),
       (13, '/img/departments/albums/13_albums_1701179814840.jpg'),
       (13, '/img/departments/albums/13_albums_1701179818067.jpg'),

       (14, '/img/departments/albums/14_albums_1701180043245.jpg'),
       (14, '/img/departments/albums/14_albums_1701180053532.jpg'),
       (14, '/img/departments/albums/14_albums_1701180056776.jpg'),
       (14, '/img/departments/albums/14_albums_1701180059792.jpg'),
       (14, '/img/departments/albums/14_albums_1701180063158.jpg'),

       (15, '/img/departments/albums/15_albums_1701180388930.jpg'),
       (15, '/img/departments/albums/15_albums_1701180393344.jpg'),
       (15, '/img/departments/albums/15_albums_1701180396587.jpg'),
       (15, '/img/departments/albums/15_albums_1701180399586.jpg'),
       (15, '/img/departments/albums/15_albums_1701180402529.jpg'),

       (16, '/img/departments/albums/16_albums_1701181350071.jpg'),
       (16, '/img/departments/albums/16_albums_1701181367303.jpg'),
       (16, '/img/departments/albums/16_albums_1701181357007.jpg'),
       (16, '/img/departments/albums/16_albums_1701181360169.jpg'),
       (16, '/img/departments/albums/16_albums_1701181376832.jpg'),

       (17, '/img/departments/albums/17_albums_1701181709997.jpg'),
       (17, '/img/departments/albums/17_albums_1701181713341.jpg'),
       (17, '/img/departments/albums/17_albums_1701181718536.jpg'),
       (17, '/img/departments/albums/17_albums_1701181722026.jpg'),
       (17, '/img/departments/albums/17_albums_1701181724948.jpg'),
       (17, '/img/departments/albums/17_albums_1701181727556.jpg'),
       (17, '/img/departments/albums/17_albums_1701181730196.jpg'),
       (17, '/img/departments/albums/17_albums_1701181732924.jpg'),

       (18, '/img/departments/albums/18_albums_1701182354945.jpg'),
       (18, '/img/departments/albums/18_albums_1701182358962.jpg'),
       (18, '/img/departments/albums/18_albums_1701182362402.jpg'),
       (18, '/img/departments/albums/18_albums_1701182365515.jpg'),
       (18, '/img/departments/albums/18_albums_1701182369139.jpg'),
       (18, '/img/departments/albums/18_albums_1701182373083.jpg'),
       (18, '/img/departments/albums/18_albums_1701182381239.jpg'),
       (18, '/img/departments/albums/18_albums_1701182393799.jpg'),
       (18, '/img/departments/albums/18_albums_1701182400942.jpg'),

       (19, '/img/departments/albums/19_albums_1701182858235.jpg'),
       (19, '/img/departments/albums/19_albums_1701182861385.jpg'),
       (19, '/img/departments/albums/19_albums_1701182865479.jpg'),
       (19, '/img/departments/albums/19_albums_1701182868827.jpg'),
       (19, '/img/departments/albums/19_albums_1701182876208.jpg'),
       (19, '/img/departments/albums/19_albums_1701182878870.jpg'),
       (19, '/img/departments/albums/19_albums_1701182881736.jpg'),

       (20, '/img/departments/albums/20_albums_1701183267924.jpg'),
       (20, '/img/departments/albums/20_albums_1701183274601.jpg'),
       (20, '/img/departments/albums/20_albums_1701183287500.jpg'),
       (20, '/img/departments/albums/20_albums_1701183292841.jpg'),
       (20, '/img/departments/albums/20_albums_1701183296017.jpg'),
       (20, '/img/departments/albums/20_albums_1701183300939.jpg'),
       (20, '/img/departments/albums/20_albums_1701183306210.jpg');

INSERT INTO gym_department_schedule (gym_department_id, day, open_time, close_time)

VALUES (12, 'Thứ 2', '6:00 AM', '10:00 PM'),
       (12, 'Thứ 3', '9:00 AM', '11:00 PM'),
       (12, 'Thứ 4', '9:00 AM', '11:00 PM'),
       (12, 'Thứ 5', '9:00 AM', '11:00 PM'),
       (12, 'Thứ 6', '9:00 AM', '11:00 PM'),
       (12, 'Thứ 7', '9:00 AM', '11:00 PM'),
       (12, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (13, 'Thứ 2', '6:00 AM', '10:00 PM'),
       (13, 'Thứ 3', '9:00 AM', '11:00 PM'),
       (13, 'Thứ 4', '9:00 AM', '11:00 PM'),
       (13, 'Thứ 5', '9:00 AM', '11:00 PM'),
       (13, 'Thứ 6', '9:00 AM', '11:00 PM'),
       (13, 'Thứ 7', '9:00 AM', '11:00 PM'),
       (13, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (14, 'Thứ 2', '6:00 AM', '10:00 PM'),
       (14, 'Thứ 3', '9:00 AM', '11:00 PM'),
       (14, 'Thứ 4', '9:00 AM', '11:00 PM'),
       (14, 'Thứ 5', '9:00 AM', '11:00 PM'),
       (14, 'Thứ 6', '9:00 AM', '11:00 PM'),
       (14, 'Thứ 7', '9:00 AM', '11:00 PM'),
       (14, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (15, 'Thứ 2', '6:00 AM', '10:00 PM'),
       (15, 'Thứ 3', '9:00 AM', '11:00 PM'),
       (15, 'Thứ 4', '9:00 AM', '11:00 PM'),
       (15, 'Thứ 5', '9:00 AM', '11:00 PM'),
       (15, 'Thứ 6', '9:00 AM', '11:00 PM'),
       (15, 'Thứ 7', '9:00 AM', '11:00 PM'),
       (15, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (16, 'Thứ 2', '6:00 AM', '10:00 PM'),
       (16, 'Thứ 3', '9:00 AM', '11:00 PM'),
       (16, 'Thứ 4', '9:00 AM', '11:00 PM'),
       (16, 'Thứ 5', '9:00 AM', '11:00 PM'),
       (16, 'Thứ 6', '9:00 AM', '11:00 PM'),
       (16, 'Thứ 7', '9:00 AM', '11:00 PM'),
       (16, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (17, 'Thứ 2', '6:00 AM', '10:00 PM'),
       (17, 'Thứ 3', '9:00 AM', '11:00 PM'),
       (17, 'Thứ 4', '9:00 AM', '11:00 PM'),
       (17, 'Thứ 5', '9:00 AM', '11:00 PM'),
       (17, 'Thứ 6', '9:00 AM', '11:00 PM'),
       (17, 'Thứ 7', '9:00 AM', '11:00 PM'),
       (17, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (18, 'Thứ 2', '6:00 AM', '10:00 PM'),
       (18, 'Thứ 3', '9:00 AM', '11:00 PM'),
       (18, 'Thứ 4', '9:00 AM', '11:00 PM'),
       (18, 'Thứ 5', '9:00 AM', '11:00 PM'),
       (18, 'Thứ 6', '9:00 AM', '11:00 PM'),
       (18, 'Thứ 7', '9:00 AM', '11:00 PM'),
       (18, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (19, 'Thứ 2', '6:00 AM', '10:00 PM'),
       (19, 'Thứ 3', '9:00 AM', '11:00 PM'),
       (19, 'Thứ 4', '9:00 AM', '11:00 PM'),
       (19, 'Thứ 5', '9:00 AM', '11:00 PM'),
       (19, 'Thứ 6', '9:00 AM', '11:00 PM'),
       (19, 'Thứ 7', '9:00 AM', '11:00 PM'),
       (19, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (20, 'Thứ 2', '6:00 AM', '10:00 PM'),
       (20, 'Thứ 3', '9:00 AM', '11:00 PM'),
       (20, 'Thứ 4', '9:00 AM', '11:00 PM'),
       (20, 'Thứ 5', '9:00 AM', '11:00 PM'),
       (20, 'Thứ 6', '9:00 AM', '11:00 PM'),
       (20, 'Thứ 7', '9:00 AM', '11:00 PM'),
       (20, 'Chủ Nhật', '9:00 AM', '11:00 PM');

INSERT INTO gym_plan (plan_id, brand_id, gym_plan_key, gym_plan_status_key, gym_plan_type_key, name, description,
                      price, price_per_hours, plan_sold, duration, plan_before_active_validity,
                      plan_after_active_validity)

VALUES (151, 11, 1, 1, 1, 'Gói giờ',
        'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp. ',
        0, 100.00, 0, 0, 7, 14),

       (152, 11, 2, 1, 2, 'Gói 8 tuần',
        'Advance Fitness & Gym cam kết sẽ hoàn tiền 100% nếu học viên tập luyện nhưng không hiệu quả: giảm 5 kg/1 tháng cho người tập giảm cân và tăng cân, tăng cơ cho người gầy trong 8 tuần.',
        6000.00, 0, 0, 90, 10, 90),

       (153, 11, 2, 1, 2, 'Gói 20 buổi tập luyện với PT',
        'Có huấn luyện viên 1:1. Phòng tập rộng rãi, thoáng mát. Trang thiết bị hiện đại. Được kiểm tra sức khỏe đều đặn. Miễn phí các chương trình tập khác như Yoga, Spinning, Zumba….',
        10300.00, 0, 0, 30, 10, 30),

       (154, 11, 2, 1, 2, 'Gói 30 buổi tập luyện với PT',
        'Có huấn luyện viên 1:1. Phòng tập rộng rãi, thoáng mát. Trang thiết bị hiện đại. Được kiểm tra sức khỏe đều đặn. Miễn phí các chương trình tập khác như Yoga, Spinning, Zumba….',
        13400.00, 0, 0, 30, 10, 30),

       (155, 11, 2, 1, 2, 'Gói 50 buổi tập luyện với PT',
        'Có huấn luyện viên 1:1. Phòng tập rộng rãi, thoáng mát. Trang thiết bị hiện đại. Được kiểm tra sức khỏe đều đặn. Miễn phí các chương trình tập khác như Yoga, Spinning, Zumba….',
        20000.00, 0, 0, 30, 10, 30),

       (156, 11, 2, 1, 2, 'Gói 100 buổi tập luyện với PT',
        'Có huấn luyện viên 1:1. Phòng tập rộng rãi, thoáng mát. Trang thiết bị hiện đại. Được kiểm tra sức khỏe đều đặn. Miễn phí các chương trình tập khác như Yoga, Spinning, Zumba….',
        35800.00, 0, 0, 30, 10, 30),

       (157, 12, 1, 1, 1, 'Gói giờ',
        'Gói linh hoạt tính theo giờ tại phòng tập gym của chúng tôi mang đến cho bạn sự thoải mái và linh hoạt tối đa trong việc rèn luyện sức khỏe. ',
        0, 150.00, 0, 0, 7, 14),

       (158, 12, 2, 1, 2, 'Gói 1 tháng',
        'Thanh toán một lần. Tập luyện không giới hạn thời gian. Gửi xe miễn phí. Lớp học miễn phí. Nước uống miễn phí. Đo chỉ số Inbody. Buổi tập PT miễn phí.',
        2000.00, 0, 0, 90, 10, 90),

       (159, 12, 2, 1, 2, 'Gói 6 tháng',
        'Miễn phí Hội viên. Thanh toán một lần. Tập luyện không giới hạn thời gian. Gửi xe miễn phí. Lớp học miễn phí. Nước uống miễn phí. Đo chỉ số Inbody. Buổi tập PT miễn phí.',
        5000.00, 0, 0, 30, 10, 30),

       (160, 12, 2, 1, 2, 'Gói 12 tháng',
        'Miễn phí Hội viên. Thanh toán một lần. Tập luyện không giới hạn thời gian. Gửi xe miễn phí. Lớp học miễn phí. Nước uống miễn phí. Đo chỉ số Inbody. Buổi tập PT miễn phí.',
        9000.00, 0, 0, 30, 10, 30),

       (161, 13, 1, 1, 1, 'Gói giờ',
        'Gói linh hoạt tính theo giờ của phòng tập gym là sự lựa chọn hoàn hảo cho những người có lịch trình bận rộn hoặc muốn tận hưởng sự thoải mái và linh hoạt trong việc tập luyện.',
        0, 150.00, 0, 0, 7, 14),

       (162, 13, 2, 1, 2, 'Gói 1 tháng',
        'VShape Fitness cung cấp cho hội viên các dịch vụ: fitness, gym, yoga, xông hơi khô, khu vực trang điểm, bãi giữ xe rộng rãi miễn phí….',
        2000.00, 0, 0, 90, 10, 90),

       (163, 13, 2, 1, 2, 'Gói 6 tháng',
        'VShape Fitness cung cấp cho hội viên các dịch vụ: fitness, gym, yoga, xông hơi khô, khu vực trang điểm, bãi giữ xe rộng rãi miễn phí….',
        5000.00, 0, 0, 30, 10, 30),

       (164, 13, 2, 1, 2, 'Gói 12 tháng',
        'VShape Fitness cung cấp cho hội viên các dịch vụ: fitness, gym, yoga, xông hơi khô, khu vực trang điểm, bãi giữ xe rộng rãi miễn phí….',
        9000.00, 0, 0, 30, 10, 30);


INSERT INTO gym_department_plans(gym_department_id, plan_id)

VALUES (12, 151),
       (12, 152),
       (12, 153),
       (12, 154),
       (12, 155),
       (12, 156),

       (13, 151),
       (13, 152),
       (13, 153),
       (13, 154),
       (13, 155),
       (13, 156),

       (14, 151),
       (14, 152),
       (14, 153),
       (14, 154),
       (14, 155),
       (14, 156),

       (15, 157),
       (15, 158),
       (15, 159),
       (15, 160),

       (16, 157),
       (16, 158),
       (16, 159),
       (16, 160),

       (17, 157),
       (17, 158),
       (17, 159),
       (17, 160),

       (18, 157),
       (18, 158),
       (18, 159),
       (18, 160),

       (19, 161),
       (19, 162),
       (19, 163),
       (19, 164),

       (20, 161),
       (20, 162),
       (20, 163),
       (20, 164);

/****Department ID 12 Feedback****/
INSERT INTO user_feedback
(user_id, gym_plan_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES
    (1, 151, 12, 5, 'Phòng tập cùng các thiết bị rất sạch sẽ và thoải mái.', NOW(), 1),
    (2, 152, 12, 5, 'Phòng tập có không khí tốt nhưng thiếu một số thiết bị cần thiết.', NOW(), 1),
    (3, 153, 12, 4, 'Phòng tập cùng các thiết bị rất sạch sẽ và thoải mái.', NOW(), 1),
    (4, 154, 12, 4, 'Phòng tập cùng các thiết bị rất sạch sẽ và thoải mái.', NOW(), 1),
    (5, 155, 12, 5, 'Rất hài lòng với dịch vụ, nhân viên phục vụ nhiệt tình.', NOW(), 1),
    (5, 156, 12, 5, 'Rất hài lòng với dịch vụ, nhân viên phục vụ nhiệt tình.', NOW(), 1);

/****Department ID 13 Feedback****/
INSERT INTO user_feedback
(user_id, gym_plan_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES
    (1, 151, 13, 5, 'Phòng tập cùng các thiết bị rất sạch sẽ và thoải mái.', NOW(), 1),
    (2, 152, 13, 5, 'Rất hài lòng với dịch vụ, nhân viên phục vụ nhiệt tình.', NOW(), 1),
    (3, 153, 13, 5, 'Phòng tập có không khí tốt nhưng thiếu một số thiết bị cần thiết.', NOW(), 1),
    (5, 156, 13, 4, 'Thiết bị ở phòng tập rất chất lượng, giúp tôi có buổi tập hiệu quả.', NOW(), 1);

/****Department ID 14 Feedback****/
INSERT INTO user_feedback
(user_id, gym_plan_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES
    (1, 151, 14, 5, 'Phòng tập cùng các thiết bị rất sạch sẽ và thoải mái.', NOW(), 1),
    (2, 152, 14, 5, 'Thiết bị ở phòng tập rất chất lượng, giúp tôi có buổi tập hiệu quả.', NOW(), 1),
    (4, 154, 14, 5, 'Rất hài lòng với dịch vụ, nhân viên phục vụ nhiệt tình.', NOW(), 1),
    (5, 156, 14, 3, 'Phòng tập có không khí tốt nhưng thiếu một số thiết bị cần thiết.', NOW(), 1);

/****Department ID 15 Feedback****/
INSERT INTO user_feedback
(user_id, gym_plan_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES
    (1, 157, 15, 4, 'Phòng tập cùng các thiết bị rất sạch sẽ và thoải mái.', NOW(), 1),
    (2, 158, 15, 5, 'Phòng tập có không khí tốt nhưng thiếu một số thiết bị cần thiết.', NOW(), 1),
    (3, 159, 15, 5, 'Thiết bị ở phòng tập rất chất lượng, giúp tôi có buổi tập hiệu quả.', NOW(), 1),
    (5, 160, 15, 3, 'Gặp một số vấn đề với việc bảo dưỡng thiết bị, mong cải thiện.', NOW(), 1);

/****Department ID 16 Feedback****/
INSERT INTO user_feedback
(user_id, gym_plan_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES
    (1,  157, 16, 4, 'Phòng tập cùng các thiết bị rất sạch sẽ và thoải mái.', NOW(), 1),
    (2,  158, 16, 5, 'Rất hài lòng với dịch vụ, nhân viên phục vụ nhiệt tình.', NOW(), 1),
    (3,  159, 16, 3, 'Phòng tập có không khí tốt nhưng thiếu một số thiết bị cần thiết.', NOW(), 1),
    (5,  160, 16, 3, 'Phòng tập cần được làm mới, không gian hơi chật hẹp.', NOW(), 1);

/****Department ID 17 Feedback****/
INSERT INTO user_feedback
(user_id, gym_plan_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES
    (1, 157, 17, 5, 'Phòng tập cùng các thiết bị rất sạch sẽ và thoải mái.', NOW(), 1),
    (2, 158, 17, 4, 'Thiết bị ở phòng tập rất chất lượng, giúp tôi có buổi tập hiệu quả.', NOW(), 1),
    (3, 159, 17, 2, 'Gặp một số vấn đề với việc bảo dưỡng thiết bị, mong cải thiện.', NOW(), 1),
    (5, 160, 17, 4, 'Rất thích không gian và cách bố trí thiết bị ở phòng tập này.', NOW(), 1);

/****Department ID 18 Feedback****/
INSERT INTO user_feedback
(user_id, gym_plan_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES
    (1, 157, 18, 4, 'Phòng tập cùng các thiết bị rất sạch sẽ và thoải mái.', NOW(), 1),
    (2, 158, 18, 3, 'Phòng tập có không khí tốt nhưng thiếu một số thiết bị cần thiết.', NOW(), 1),
    (3, 159, 18, 3, 'Phòng tập cần được làm mới, không gian hơi chật hẹp.', NOW(), 1),
    (5, 160, 18, 5, 'Nhân viên tư vấn rất nhiệt tình và giúp đỡ.', NOW(), 1);

/****Department ID 19 Feedback****/
INSERT INTO user_feedback
(user_id, gym_plan_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES
    (1, 161, 19, 4, 'Phòng tập cùng các thiết bị rất sạch sẽ và thoải mái.', NOW(), 1),
    (2, 162, 19, 5, 'Rất hài lòng với dịch vụ, nhân viên phục vụ nhiệt tình.', NOW(), 1),
    (3, 163, 19, 2, 'Gặp một số vấn đề với việc bảo dưỡng thiết bị, mong cải thiện.', NOW(), 1),
    (5, 164, 19, 3, 'Cần cải thiện về việc duy trì sự sạch sẽ của phòng tập.', NOW(), 1);

/****Department ID 20 Feedback****/
INSERT INTO user_feedback
(user_id, gym_plan_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES
    (2, 161, 20, 4, 'Thiết bị ở phòng tập rất chất lượng, giúp tôi có buổi tập hiệu quả.', NOW(), 1),
    (3, 162, 20, 3, 'Phòng tập cần được làm mới, không gian hơi chật hẹp.', NOW(), 1),
    (5, 163, 20, 4, 'Được biết đến qua bạn bè và không hối hận khi đăng ký thành viên.', NOW(), 1),
    (5, 164, 20, 4, 'Được biết đến qua bạn bè và không hối hận khi đăng ký thành viên.', NOW(), 1);







/**************************************************************************************** Brand Creation - TRUNG **********************************************************************/
INSERT INTO brand (brand_id, user_id, name, logo_url, wallpaper_url, thumbnail_url, description, rating, contact_number,
                   contact_email, brand_status_key)
VALUES

    /***************************************** Kickfit Sport Brand ******************************************************/
    (1, 15, 'Kickfit Sport',
     '/img/brands/logos/1_logo.jpg',
     '/img/brands/wallpapers/1_wallpaper.jpg',
     '/img/brands/thumbnails/1_thumbnail.jpg',
     'Kickfit Sports đã đang và sẽ phát triển theo hướng: “Chuyên nghiệp – Chất Lượng – Luôn đổi mới và hoàn thiện” đồng thời mong muốn mang lại sức khỏe và thịnh vượng cho hàng triệu người Việt Nam.Với hơn 10 năm xây dựng và phát triển, hiện tại Kickfit Sports đang sở hữu 14 cơ sở phòng tập trải rộng khắp Hà Nội. Trung tâm luôn chú trọng đầu tư, cải tiến các sản phẩm, dịch vụ để mang đến môi trường tập luyện 5 sao, uy tín và chất lượng.',
     5, '0936399988', 'hethong@kickfit-sports.com', 1),

    /****************************************** 25 FIT Brand *****************************************************/
    (2, 16, '25 Fit',
     '/img/brands/logos/2_logo.jpg',
     '/img/brands/wallpapers/2_wallpaper.jpg',
     '/img/brands/thumbnails/2_thumbnail.jpg',
     '25 Fit là viết tắt của cụm từ 25 minutes to get fit, là hệ thống phòng tập công nghệ EMS đầu tiên. EMS là phương pháp dùng xung điện để tác động, kích thích cơ bắp phát triển và đánh tan mỡ thừa.
     Với phương pháp này bạn không cần dùng tạ nên cũng không cần lo ngại về chấn thương, chỉ cần tập luyện 25 phút và 1 – 2 buổi/ tuần đã có thể đạt kết quả giảm mỡ nhanh hơn, tiết kiệm tối ưu thời gian.
     Trang thiết bị nhập khẩu từ Đức, đặc biệt là tại phòng tập 25 FIT, quần áo và thức uống pre-workout sẽ được chuẩn bị sẵn và miễn phí.',
     4.7, '1800617654', 'welisten@25fit.net', 1),

    /******************************************** S'Life Brand ***************************************************/
    (3, 17, 'S’Life Gym & Yoga',
     '/img/brands/logos/3_logo.jpg',
     '/img/brands/wallpapers/3_wallpaper.jpg',
     '/img/brands/thumbnails/3_thumbnail.jpg',
     '"S’Life là hệ thống phòng tập thể hình không gian xanh, môi trường lý tưởng để tập luyện với trang thiết bị hiện đại cùng huấn luyện viên chuyên nghiệp sẽ giúp hội viên đạt hiệu quả tập luyện cao nhất.
Bài tập luyện của học viện đều được huấn luyện viên của S’Life cá nhân hóa để phù hợp với thể trạng của mỗi người."',
     4.7, '0902635124', ' slifegym@gmail.com', 1),

    /********************************************* Diamond Fitness Brand **************************************************/
    (4, 18, 'Diamond Fitness Center',
     '/img/brands/logos/4_logo.jpg',
     '/img/brands/wallpapers/4_wallpaper.jpg',
     '/img/brands/thumbnails/4_thumbnail.jpg',
     '"Diamond Fitness Center tự hào đem lại trải nghiệm tốt nhất cho bạn với hệ thống gồm 14 câu lạc bộ đẳng cấp 5 sao. Được trang bị các thiết bị hiện đại, cùng hệ thống hồ bơi trong nhà, phòng tập Yoga, Kick Boxing, Thiền,…
Sự khác biệt của thương hiệu này, chính là chương trình đo khám chỉ số BMI. Thông qua chỉ số này, bạn sẽ hiểu hơn về tình trạng cơ thể của mình và từ đó xác định nhu cầu của cơ thể là gì.
Dựa vào BMI, huấn luyện viên của Diamond Fitness sẽ vạch ra lịch tập cũng như bài tập phù hợp với nhu cầu hoàn thiện cơ thể của bạn."',
     4.5, '0862123247', 'diamondfitness@gmail.com', 1);

INSERT INTO gym_department (gym_department_id, brand_id, user_id, gym_department_status_key, name, address,
                            contact_number,
                            logo_url, wallpaper_url, thumbnail_url, description, latitude, longitude, capacity, area,city)
VALUES
    /************************************************ Kickfit Sport Departments ***********************************************/
    (21, 1, 2, 1, 'Kickfit Sports Nguyễn Phong Sắc',
     'Tầng 03 Tòa nhà Lâm Viễn, 107 Đ. Nguyễn Phong Sắc, Hà Nội', '0903411257',
     '/img/departments/logos/21_logo.jpg',
     '/img/departments/wallpapers/21_wallpaper.jpg',
     '/img/departments/thumbnails/21_thumbnail.jpg',
     'Kickfit Sports Nguyễn Phong Sắc được biết đến là phòng tập kickfit chất lượng tốt nhất tại quận Cầu Giấy, địa chỉ quen thuộc của giới yêu võ thuật trên địa bàn Hà Nội. Không gian tập luyện có diện tích gần 1000m2 được phân chia theo từng khu riêng biệt, hợp lý đảm bảo hội viên có không gian riêng khi tập luyện và thư giãn.',
     21.040954973167608, 105.79023379536018, 200, 1000,'Hà Nội'),
    (22, 1, 46, 1, 'Kickfit Sports Trần Duy Hưng',
     'Ngõ 196 Đ. Trần Duy Hưng, Trung Hoà, Cầu Giấy, Hà Nội', '0903411257',
     '/img/departments/logos/22_logo.jpg',
     '/img/departments/wallpapers/22_wallpaper.jpg',
     '/img/departments/thumbnails/22_thumbnail.jpg',
     'Kickfit Sports Trần Duy Hưng được ra đời vào năm 2012 và là cơ sở đầu tiên của chuỗi phòng tập Kickfit Sports. Dịch vụ phòng tập chủ yếu về các bộ môn võ đối kháng hỗ trợ giảm cân, tăng cơ, cải thiện vóc dáng cũng như trang bị các kỹ năng phòng vệ hiệu quả giúp xử lý các tình huống nguy hiểm trong cuộc sống. Với không gian rộng rãi, cơ sở vật chất hiện đại tối tân, Kickfit Sports Trần Duy Hưng mang tới cho hội viên sự thoải mái, thư giãn sau những giờ học tập và làm việc mệt mỏi.',
     21.009542311744028, 105.79714248159206, 200, 1000,'Hà Nội'),
    (23, 1, 47, 1, 'Kickfit Sports Thiên Đường Bảo Sơn',
     'Tầng 5, Tòa Gemek 1, Thiên Đường Bảo Sơn, Hoài Đức, Hà Nội', '0936399988',
     '/img/departments/logos/23_logo.jpg',
     '/img/departments/wallpapers/23_wallpaper.jpg',
     '/img/departments/thumbnails/23_thumbnail.jpg',
     'Kickfit Sports Thiên Đường Bảo Sơn tọa lạc tại vị trí đắc địa thuộc toà Gemek 1 ngay cổng chào của Thiên Đường Bảo Sơn. Đây một trong những cơ sở phòng tập được đầu tư lớn với nhiều trang thiết bị hiện đại và tiện ích sang chảnh mang đến trải nghiệm tập luyện thể thao tuyệt vời nhất cho khách hàng. Kickfit Sports Thiên Đường Bảo Sơn tự hào khi nhận được vô số những đánh giá tốt từ khách hàng và số lượng người đăng ký tập luyện ngày càng đông đảo.',
     21.00855235502214, 105.7307156200674, 400, 1500,'Hà Nội'),
    (24, 1, 48, 1, 'Kickfit Sports Metro Hà Đông',
     'Tầng 2, Siêu thị Metro - Melinh Plaza, Hà Đông', '0936399988',
     '/img/departments/logos/24_logo.jpg',
     '/img/departments/wallpapers/24_wallpaper.jpg',
     '/img/departments/thumbnails/24_thumbnail.jpg',
     'Kickfit Sports Metro Hà Đông có quy mô diện tích lớn, cung cấp đa dạng các loại hình tập luyện từ A-Z gồm cả võ thuật và thể hình. Lợi thế tọa lạc tại Mê Linh Plaza, Kickfit Sports được nhiều hội viên lựa chọn bởi đẳng cấp 5 sao cùng dịch vụ tuyệt vời, trang thiết bị hiện đại tạo cảm giác an tâm, thoải mái.',
     20.964556785162678, 105.77230071391557, 280, 800,'Hà Nội'),

    /************************************************ 25 FIT Departments ***********************************************/
    (25, 2, 49, 1, '25 FIT Lý Thường Kiệt',
     '42Z P. Lý Thường Kiệt, Tràng Tiền, Hoàn Kiếm, Hà Nội', '1800617612',
     '/img/departments/logos/25_logo.jpg',
     '/img/departments/wallpapers/25_wallpaper.jpg',
     '/img/departments/thumbnails/25_thumbnail.jpg',
     '25 FIT Lý Thường Kiệt là studio đầu tiên ở Hà Nội đánh dấu những bước tiến quan trọng của 25 FIT tại khu vực phía Bắc trong việc mang công nghệ EMS đến với người dùng thủ đô',
     21.023985014649746, 105.85026204718575, 5, 60, 'Hà Nội'),
    (26, 2, 50, 1, '25 FIT Duy Tân',
     '22 Duy Tân, Cầu Giấy, Hà Nội Hà Nội', '1800617645',
     '/img/departments/logos/26_logo.jpg',
     '/img/departments/wallpapers/26_wallpaper.jpg',
     '/img/departments/thumbnails/26_thumbnail.jpg',
     'Nằm tại cung đường sầm uất và nhộn nhịp, tiếp giáp khu dân cư, văn phòng, studio mới 25 FIT Duy Tân hứa hẹn sẽ đem đến cho khách hàng những trải nghiệm tập luyện giá trị tốt nhất về sức khỏe, dịch vụ với công nghệ EMS Training hiện đại, đảm bảo tiết kiệm thời gian.',
     21.030865188489173, 105.78566187313876, 5, 60, 'Hà Nội'),
    (27, 2, 51, 1, '25 FIT Times City',
     'T1, SO.02, Khu đô thị Times City, Hai Bà Trưng, Hà Nội', '1800617645',
     '/img/departments/logos/27_logo.jpg',
     '/img/departments/wallpapers/27_wallpaper.jpg',
     '/img/departments/thumbnails/27_thumbnail.jpg',
     'Đặt ở vị trí thuận tiện tại khu dân cư Times City, Quận Hai Bà Trưng. Chỉ 20 phút tập, 2 buổi/tuần cùng huấn luyện viên chuyên nghiệp, quý khách không cần mang bất cứ thứ gì kể cả quần áo hay giày tập để có thể đạt kết quả tăng cơ, giảm mỡ.',
     20.99661352642664, 105.86945587284531, 5, 55, 'Hà Nội'),
    (28, 2, 52, 1, '25 FIT Vinhomes Gardenia Hàm Nghi',
     'B17 05A Shophouse Vinhomes Gardenia, P. Hàm Nghi, Cầu Diễn, Nam Từ Liêm, Hà Nội.', '1800617612',
     '/img/departments/logos/28_logo.jpg',
     '/img/departments/wallpapers/28_wallpaper.jpg',
     '/img/departments/thumbnails/28_thumbnail.jpg',
     'Chính thức từ ngày 23/07/2022 - studio 25 FIT Hàm Nghi đi vào hoạt động, sẵn sàng chào đón quý hội viên trải nghiệm công nghệ EMS Training mới tại B17.05A Shophouse Vinhomes Gardenia, Hàm Nghi, Phường Cầu Diễn, Quận Nam Từ Liêm, Hà Nội.',
     21.035466912246545, 105.76214903467383, 6, 70,'Hà Nội'),

    /********************************************** S'Life Departments *************************************************/
    (29, 3, 53, 1, 'Phòng tập Gym S''Life Quận 10',
     '575 Đ. Sư Vạn Hạnh, Phường 12, Quận 10, Thành phố Hồ Chí Minh', '0938891436',
     '/img/departments/logos/29_logo.jpg',
     '/img/departments/wallpapers/29_wallpaper.jpg',
     '/img/departments/thumbnails/29_thumbnail.jpg',
     'Nổi bật khắp HCM với không gian xanh đẳng cấp rộng hơn 3.000m2 và đầy đủ bộ môn, S''Life Quận 10 sẽ mang đến cho bạn những trải nghiệm tuyệt vời và hiệu quả nhất.',
     10.776571562971277, 106.6669378322132, 400, 3000,'Hồ Chí Minh'),
    (30, 3, 54, 1, 'Phòng tập Gym S''Life Quận 6',
     '893 Đ. Hậu Giang, P.11, Quận 6, Thành phố Hồ Chí Minh', '0902635124',
     '/img/departments/logos/30_logo.jpg',
     '/img/departments/wallpapers/30_wallpaper.jpg',
     '/img/departments/thumbnails/30_thumbnail.jpg',
     'Bạn đang tìm kiếm phòng tập tại quận 6? S''Life GYM chính là sự lựa chọn dành cho bạn với chất lượng hàng đầu',
     10.746277023301626, 106.62821508856594, 300, 2000,'Hồ Chí Minh'),
    (31, 3, 55, 1, 'Phòng tập Gym S''Life Bình Chánh',
     '34 Đ. Phạm Hùng, Bình Hưng, Bình Chánh, Thành phố Hồ Chí Minh', '0902635124',
     '/img/departments/logos/31_logo.jpg',
     '/img/departments/wallpapers/31_wallpaper.jpg',
     '/img/departments/thumbnails/31_thumbnail.jpg',
     '"Với không gian đẳng cấp và sang trọng, S''Life GYM Bình Chánh chính là sự lựa chọn hoàn hảo dành cho bạn để cải thiện sức khỏe và vóc dáng hiệu quả.',
     10.73050975241317, 106.67597421199494, 260, 1800, 'Hồ Chí Minh'),
    (32, 3, 56, 1, 'Phòng tập Gym S''Life Bình Thạch',
     '26 Nguyễn Huy Lượng, Phường 14, Bình Thạnh, Thành phố Hồ Chí Minh, Vietnam', '0902635124',
     '/img/departments/logos/32_logo.jpg',
     '/img/departments/wallpapers/32_wallpaper.jpg',
     '/img/departments/thumbnails/32_thumbnail.jpg',
     '"Bạn đang tìm phòng tập để cải thiện sức khỏe và vóc dáng? S''Life GYM chính là sự lựa chọn dành cho bạn.',
     10.805837559943653, 106.69624297299924, 350, 2400,'Hồ Chí Minh'),

    /******************************************** Diamond Fitness Departments ***************************************************/
    (33, 4, 57, 1, 'Diamond Fitness Center Trần Quang Khải',
     'Cao ốc Horizon, 214 Đ. Trần Quang Khải, P, Quận 1, Thành phố Hồ Chí Minh', '02866569555',
     '/img/departments/logos/33_logo.jpg',
     '/img/departments/wallpapers/33_wallpaper.jpg',
     '/img/departments/thumbnails/33_thumbnail.jpg',
     'DIAMOND FITNESS CENTER mang trong mình sứ mệnh “Chăm sóc sức khỏe và vẻ đẹp toàn diện của Bạn”, hướng dẫn và mang đến cho bạn một chương trình luyện tập cho cả thể chất và tinh thần.
Khám phá và trải nghiệm một lối sống khỏe mạnh và tích cực nhất tại DIAMOND FITNESS CENTER ngay bạn nhé!',
     10.79154285000631, 106.68804505835055, 650, 6600,'Hồ Chí Minh'),
    (34, 4, 58, 1, 'Diamond Fitness Center Lê Văn Sỹ',
     '338 Đ. Lê Văn Sỹ, Phường 14, Quận 3, Thành phố Hồ Chí Minh', '02866825544',
     '/img/departments/logos/34_logo.jpg',
     '/img/departments/wallpapers/34_wallpaper.jpg',
     '/img/departments/thumbnails/34_thumbnail.jpg',
     'DIAMOND FITNESS CENTER mang trong mình sứ mệnh “Chăm sóc sức khỏe và vẻ đẹp toàn diện của Bạn”, hướng dẫn và mang đến cho bạn một chương trình luyện tập cho cả thể chất và tinh thần.
Khám phá và trải nghiệm một lối sống khỏe mạnh và tích cực nhất tại DIAMOND FITNESS CENTER ngay bạn nhé!',
     10.78743076402733, 106.67907250244333, 120, 576,'Hồ Chí Minh'),
    (35, 4, 59, 1, 'Diamond Fitness Paragon Lê Hồng Phong',
     'TTTM Maslight, lô 20 Đ. Lê Hồng Phong, Đông Khê, Ngô Quyền, Hải Phòng.', '02256626686',
     '/img/departments/logos/35_logo.jpg',
     '/img/departments/wallpapers/35_wallpaper.jpg',
     '/img/departments/thumbnails/35_thumbnail.jpg',
     'Viên kim cương khổng lồ Diamond Fitness Paragon tại TD Plaza, Lê Hồng Phong, TP Hải Phòng là dự án lớn nhất TP Hải Phòng với dòng thiết bị cao cấp nhất Hàn Quốc – DRAX cùng 2 phân khu đẳng cấp nhất.',
     20.846699998604187, 106.70741856252596, 400, 3000,'Hồ Chí Minh'),
    (36, 4, 60, 1, 'Diamond Fitness Paragon Bạch Đằng',
     '97 Bạch Đằng, Hạ Lý, Hồng Bàng, Hải Phòng', '0899164562',
     '/img/departments/logos/36_logo.jpg',
     '/img/departments/wallpapers/36_wallpaper.jpg',
     '/img/departments/thumbnails/36_thumbnail.jpg',
     'Diamond Fitness Paragon Bạch Đằng Hải Phòng tọa lạc ngay vị trí trung tâm của thành phố hoa phượng đỏ, được đầu tư lên đến 40 tỷ đồng với quy mô diện tích hơn 5.000m2, thiết kế hiện đại, sang trọng chuẩn "hoàng gia" cùng trang bị máy móc hiện đại. Với không gian tập luyện chuyên biệt vừa sang trọng vừa riêng tư đi kèm là những đặc quyền V.I.P độc nhất, Diamond Fitness Paragon Bạch Đằng không chỉ là nơi tập luyện, giải trí và chăm sóc sức khỏe vóc dáng đơn thuần mà còn mang đến những trải nghiệm tuyệt vời dành cho các Hội viên Diamond Fitness Paragon.',
     20.86149391226383, 106.67305678210231, 480, 5000,'Hồ Chí Minh');

-- Create Gym Department Gallery
INSERT INTO gym_department_albums (gym_department_id, photo_url)
VALUES
    /******************************************** Kickfit Sport Departments Plans ***************************************************/
    (21, '/img/departments/albums/21_albums_1701167846382.jpg'),
    (21, '/img/departments/albums/21_albums_1701167849808.jpg'),
    (21, '/img/departments/albums/21_albums_1701167853533.jpg'),
    (21, '/img/departments/albums/21_albums_1701167860007.jpg'),
    (21, '/img/departments/albums/21_albums_1701167862975.jpg'),
    (21, '/img/departments/albums/21_albums_1701167865986.jpg'),
    (21, '/img/departments/albums/21_albums_1701167869633.jpg'),
    (21, '/img/departments/albums/21_albums_1701167872678.jpg'),
    (21, '/img/departments/albums/21_albums_1701167875868.jpg'),

    (22, '/img/departments/albums/22_albums_1701168464602.jpg'),
    (22, '/img/departments/albums/22_albums_1701168468601.jpg'),
    (22, '/img/departments/albums/22_albums_1701168471754.jpg'),
    (22, '/img/departments/albums/22_albums_1701168474895.jpg'),
    (22, '/img/departments/albums/22_albums_1701168477590.jpg'),
    (22, '/img/departments/albums/22_albums_1701168480855.jpg'),
    (22, '/img/departments/albums/22_albums_1701168483781.jpg'),
    (22, '/img/departments/albums/22_albums_1701168486666.jpg'),
    (22, '/img/departments/albums/22_albums_1701168489604.jpg'),

    (23, '/img/departments/albums/23_albums_1701169081567.jpg'),
    (23, '/img/departments/albums/23_albums_1701169084848.jpg'),
    (23, '/img/departments/albums/23_albums_1701169088608.jpg'),
    (23, '/img/departments/albums/23_albums_1701169092774.jpg'),
    (23, '/img/departments/albums/23_albums_1701169095399.jpg'),
    (23, '/img/departments/albums/23_albums_1701169098079.jpg'),
    (23, '/img/departments/albums/23_albums_1701169100987.jpg'),
    (23, '/img/departments/albums/23_albums_1701169104012.jpg'),
    (23, '/img/departments/albums/23_albums_1701169106679.jpg'),

    (24, '/img/departments/albums/24_albums_1701169494489.jpg'),
    (24, '/img/departments/albums/24_albums_1701169501010.jpg'),
    (24, '/img/departments/albums/24_albums_1701169504064.jpg'),
    (24, '/img/departments/albums/24_albums_1701169507099.jpg'),
    (24, '/img/departments/albums/24_albums_1701169510204.jpg'),
    (24, '/img/departments/albums/24_albums_1701169513170.jpg'),
    (24, '/img/departments/albums/24_albums_1701169516206.jpg'),
    (24, '/img/departments/albums/24_albums_1701169518945.jpg'),
    (24, '/img/departments/albums/24_albums_1701169521971.jpg'),

    (25, '/img/departments/albums/25_albums_1701170650279.jpg'),
    (25, '/img/departments/albums/25_albums_1701170647617.jpg'),
    (25, '/img/departments/albums/25_albums_1701170644674.jpg'),
    (25, '/img/departments/albums/25_albums_1701170641167.jpg'),
    (25, '/img/departments/albums/25_albums_1701170637603.jpg'),
    (25, '/img/departments/albums/25_albums_1701170633699.jpg'),


    (26, '/img/departments/albums/26_albums_1701171726804.jpg'),
    (26, '/img/departments/albums/26_albums_1701171723498.jpg'),
    (26, '/img/departments/albums/26_albums_1701171716017.jpg'),
    (26, '/img/departments/albums/26_albums_1701171710653.jpg'),
    (26, '/img/departments/albums/26_albums_1701171706756.jpg'),
    (26, '/img/departments/albums/26_albums_1701171700502.jpg'),

    (27, '/img/departments/albums/27_albums_1701172060315.jpg'),
    (27, '/img/departments/albums/27_albums_1701172057568.jpg'),
    (27, '/img/departments/albums/27_albums_1701172054880.jpg'),
    (27, '/img/departments/albums/27_albums_1701172051735.jpg'),
    (27, '/img/departments/albums/27_albums_1701172048731.jpg'),
    (27, '/img/departments/albums/27_albums_1701172044712.jpg'),

    (28, '/img/departments/albums/28_albums_1701172424191.jpg'),
    (28, '/img/departments/albums/28_albums_1701172417839.jpg'),
    (28, '/img/departments/albums/28_albums_1701172412682.jpg'),
    (28, '/img/departments/albums/28_albums_1701172408573.jpg'),
    (28, '/img/departments/albums/28_albums_1701172405674.jpg'),
    (28, '/img/departments/albums/28_albums_1701172402608.jpg'),

    (29, '/img/departments/albums/29_albums_1701174789330.jpg'),
    (29, '/img/departments/albums/29_albums_1701174786626.jpg'),
    (29, '/img/departments/albums/29_albums_1701174782090.jpg'),
    (29, '/img/departments/albums/29_albums_1701174774025.jpg'),
    (29, '/img/departments/albums/29_albums_1701174771092.jpg'),
    (29, '/img/departments/albums/29_albums_1701174768468.jpg'),

    (30, '/img/departments/albums/30_albums_1701175364484.jpg'),
    (30, '/img/departments/albums/30_albums_1701175361528.jpg'),
    (30, '/img/departments/albums/30_albums_1701175358712.jpg'),
    (30, '/img/departments/albums/30_albums_1701175353372.jpg'),
    (30, '/img/departments/albums/30_albums_1701175349816.jpg'),
    (30, '/img/departments/albums/30_albums_1701175345989.jpg'),

    (31, '/img/departments/albums/31_albums_1701175671931.jpg'),
    (31, '/img/departments/albums/31_albums_1701175668379.jpg'),
    (31, '/img/departments/albums/31_albums_1701175665263.jpg'),
    (31, '/img/departments/albums/31_albums_1701175662389.jpg'),
    (31, '/img/departments/albums/31_albums_1701175658965.jpg'),
    (31, '/img/departments/albums/31_albums_1701175655143.jpg'),

    (32, '/img/departments/albums/32_albums_1701176057568.jpg'),
    (32, '/img/departments/albums/32_albums_1701176047898.jpg'),
    (32, '/img/departments/albums/32_albums_1701176044884.jpg'),
    (32, '/img/departments/albums/32_albums_1701176039147.jpg'),
    (32, '/img/departments/albums/32_albums_1701176035493.jpg'),
    (32, '/img/departments/albums/32_albums_1701176032130.jpg'),

    (33, '/img/departments/albums/33_albums_1701176629133.jpg'),
    (33, '/img/departments/albums/33_albums_1701176626172.jpg'),
    (33, '/img/departments/albums/33_albums_1701176621489.jpg'),
    (33, '/img/departments/albums/33_albums_1701176617834.jpg'),
    (33, '/img/departments/albums/33_albums_1701176615418.jpg'),
    (33, '/img/departments/albums/33_albums_1701176612718.jpg'),


    (34, '/img/departments/albums/34_albums_1701176964185.jpg'),
    (34, '/img/departments/albums/34_albums_1701176960198.jpg'),
    (34, '/img/departments/albums/34_albums_1701176955994.jpg'),
    (34, '/img/departments/albums/34_albums_1701176953172.jpg'),
    (34, '/img/departments/albums/34_albums_1701176949398.jpg'),
    (34, '/img/departments/albums/34_albums_1701176945483.jpg'),

    (35, '/img/departments/albums/35_albums_1701177372839.jpg'),
    (35, '/img/departments/albums/35_albums_1701177369109.jpg'),
    (35, '/img/departments/albums/35_albums_1701177365973.jpg'),
    (35, '/img/departments/albums/35_albums_1701177362976.jpg'),
    (35, '/img/departments/albums/35_albums_1701177360184.jpg'),
    (35, '/img/departments/albums/35_albums_1701177355412.jpg'),

    (36, '/img/departments/albums/36_albums_1701177804778.jpg'),
    (36, '/img/departments/albums/36_albums_1701177794458.jpg'),
    (36, '/img/departments/albums/36_albums_1701177791945.jpg'),
    (36, '/img/departments/albums/36_albums_1701177788876.jpg'),
    (36, '/img/departments/albums/36_albums_1701177785604.jpg'),
    (36, '/img/departments/albums/36_albums_1701177779930.jpg');

INSERT INTO brand_amenities (amenitie_id, brand_id, photo_url, amenitie_name, description, amenitie_status)
VALUES
    /******************************************** Kickfit Sport Amenities ***************************************************/
    (101, 1, '/img/brandAmenities/1701144638809_brandAmenities.jpg', 'Hệ thống cơ sở rộng lớn',
     'Hệ thống cơ sở của chúng tôi là một mạng lưới các trung tâm thể dục hiện đại và chuyên nghiệp, cung cấp dịch vụ tốt nhất để giúp bạn đạt được mục tiêu về sức khỏe và thể lực.',
     1),
    (102, 1, '/img/brandAmenities/1701144643900_brandAmenities.jpg', 'Chăm sóc khách hàng tận tâm',
     'Với một cam kết không ngừng nỗ lực, Kickfit tạo ra một môi trường chuyên nghiệp, thân thiện và đồng điệu, nơi mỗi khách hàng được coi là cá nhân độc lập với nhu cầu và mong muốn riêng.',
     1),
    (103, 1, '/img/brandAmenities/1701144650150_brandAmenities.jpg', 'Khóa học đa dạng',
     'Với các khóa học từ cấp độ beginner đến advanced, chúng tôi tự tin cung cấp cho bạn những trải nghiệm tập luyện độc đáo và hiệu quả.',
     1),
    (104, 1, '/img/brandAmenities/1701144654474_brandAmenities.jpg', 'ĐỘI NGŨ HUẤN LUYỆN VIÊN',
     'Đội ngũ huấn luyện viên của Kickfit Sports được sàng lọc kĩ càng, phải trải qua giai đoạn trainning ít nhất 3 tháng để có đủ kĩ năng giao tiếp và kiến thức chuyên môn hướng dẫn cho khách hàng',
     1),

    /********************************************** 25 FIT Amenities *************************************************/
    (105, 2, '/img/brandAmenities/1701145220842_brandAmenities.jpg', 'Công nghê EMS Training',
     'EMS Training là phương pháp tập luyện sử dụng xung điện kích thích cơ bắp với hiệu quả cao, tiết kiệm thời gian và tăng cường sức mạnh cơ bắp.',
     1),
    (106, 2, '/img/brandAmenities/1701145226661_brandAmenities.jpg', 'Cơ sở vật chất hiện đại',
     'Chương trình tập luyện cá nhân được thiết kế dựa trên nhu cầu và mục tiêu riêng của từng khách hàng. Huấn luyện viên chuyên nghiệp sẽ làm việc một cách tận tâm với bạn, giúp bạn tập trung vào mục tiêu tập luyện và đạt được kết quả tốt nhất.',
     1),
    (107, 2, '/img/brandAmenities/1701145231830_brandAmenities.jpg', 'Hướng dẫn từ chuyên gia',
     'Phòng tập 25 Fit được thiết kế với sự chú trọng đến không gian sử dụng hiệu quả và tiện ích. Các khu vực tập luyện được bố trí hợp lý, tạo cảm giác rộng rãi và thoải mái cho các thành viên.',
     1),
    (108, 2, '/img/brandAmenities/1701145238537_brandAmenities.jpg', 'Phương pháp cá nhân hóa',
     'Phòng tập 25 Fit cung cấp hướng dẫn và hỗ trợ từ chuyên gia để đảm bảo rằng thành viên nhận được sự chỉ dẫn chuyên nghiệp và hỗ trợ trong quá trình tập luyện.',
     1),

    /****************************************** S'Life Amenities *****************************************************/
    (109, 3, '/img/brandAmenities/1701159952911_brandAmenities.jpg', 'Không gian xanh rộng 4.000m2',
     'Hệ thống lọc khí ion cùng trang thiết bị cao cấp nhập khẩu trực tiếp từ Mỹ, đem đến môi trường tập luyện tốt nhất',
     1),
    (110, 3, '/img/brandAmenities/1701159958271_brandAmenities.jpg', 'Dịch Vụ Đẳng Cấp',
     'Miễn phí xông hơi, phòng tắm, nước uống detox, khăn thêu tên cá nhân, phục vụ tốt nhất quá trình luyện tập của hội viên',
     1),
    (111, 3, '/img/brandAmenities/1701159962607_brandAmenities.jpg', 'Cam kết hiệu quả tập luyện',
     '100% hội viên đã đạt được kết quả tập luyện khi thực hiện đúng chương trình Cá Nhân Hóa của S''LIFE',
     1),
    (112, 3, '/img/brandAmenities/1701159967093_brandAmenities.jpg', 'Huấn luyện viên chuẩn quốc tế',
     'Chứng chỉ NASM Hoa Kỳ, 5 năm kinh nghiệm, động viên, theo sát quá trình luyện tập, đem lại vóc dáng cân đối cho hàng ngàn hội viên',
     1),

    /**************************************** Diamond Fitness Amenities *******************************************************/
    (113, 4, '/img/brandAmenities/1701160439151_brandAmenities.jpg', 'Hệ thống CLB 5 sao',
     'Diamond Fitness là một phòng tập thể hình thuộc hệ thống CLB 5 sao cao cấp. Với môi trường sang trọng và tiện nghi, chúng tôi cam kết mang đến cho bạn trải nghiệm tập luyện đẳng cấp và đáng nhớ.',
     1),
    (114, 4, '/img/brandAmenities/1701160444909_brandAmenities.jpg', 'Dịch vụ tập luyện đa dạng',
     'Chúng tôi cung cấp một loạt dịch vụ tập luyện đa dạng nhằm đáp ứng nhu cầu và mục tiêu của khách hàng, mang lại nhiều trải nghiệm tập luyện khác nhau và mới lạ',
     1),
    (115, 4, '/img/brandAmenities/1701160449773_brandAmenities.jpg', 'Điểm đến của người nổi tiếng',
     'Tự hào là cơ sở được hàng ngàn hội viên cũng như những nhân vật nổi tiếng như Angela Phương Trinh, Lương Thế Thành, .. tin dùng và lựa chọn',
     1),
    (116, 4, '/img/brandAmenities/1701160454425_brandAmenities.jpg', 'Đội ngũ HLV giàu kinh nghiệm',
     'Đội ngũ huấn luyện viên chuyên nghiệp và giàu kinh nghiệm của chúng tôi sẽ đồng hành cùng bạn trong hành trình tập luyện. Họ sẽ cung cấp hướng dẫn chuyên môn, tư vấn dinh dưỡng và lập kế hoạch tập luyện cá nhân hóa dựa trên mục tiêu và nhu cầu của bạn.',
     1);

INSERT INTO gym_plan (plan_id, brand_id, gym_plan_key, gym_plan_status_key, gym_plan_type_key, name, description, price,
                      price_per_hours, plan_sold, duration,
                      plan_before_active_validity, plan_after_active_validity)
VALUES
    /******************************************** Kickfit Sport Plans ***************************************************/
    (101, 1, 2, 1, 2, 'Gói Bạc',
     'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập các loại hình như Gym – Yoga – Group X. Miễn phí tủ đồ và phòng xông hơi',
     599.00, 0, 0, 30, 10, 30),
    (102, 1, 2, 1, 2, 'Gói Kim cương',
     'Gói tập Gym – Kickfit – Bơi – Yoga – Group X, miễn phí tủ đồ, Xông hơi, Khăn Tập, Khăn Tắm và Nước. (Đặc biệt được dẫn theo một người đi cùng)',
     2199.00, 0, 0, 30, 10, 30),
    (103, 1, 2, 1, 2, 'Gói PT',
     'Gói Tập Gym với PT chuyên nghiệp, miễn phí tủ đồ, Xông hơi Khăn Tập, Khăn Tắm, Nước',
     399.00, 0, 0, 30, 10, 30),

    /******************************************** 25 FIT Plans ***************************************************/
    (104, 2, 2, 1, 2, 'Gói Platinum',
     'Khi mua gói tập này, bạn sẽ nhận được các quyền lợi như: Miễn phí reworkout, nước uống và khăn tập. Ngoài ra voucher tập 1 tuần và HLV cá nhân cũng sẽ được cung cấp',
     53760.00, 0, 0, 365, 10, 365),
    (105, 2, 2, 1, 2, 'Gói Platinum 2 Năm',
     'Khi mua gói tập này, bạn sẽ nhận được các quyền lợi như: Miễn phí reworkout, nước uống và khăn tập tặng kèm với voucher tập 1 tháng và HLV cá nhân',
     79999.00, 0, 0, 730, 10, 730),
    (106, 2, 2, 1, 2, 'Gói trải nghiệm',
     'Khi mua gói tập này, bạn sẽ nhận được các quyền lợi của 25 FIT trong vòng 1 tháng với mức giá phải chăng',
     6099.00, 0, 0, 30, 10, 30),

    /******************************************** S'Life Gym  ***************************************************/
    (107, 3, 1, 1, 1, 'Gói Linh Hoạt',
     'Gói tập linh hoạt, phù hợp cho người muốn trải nghiệm các phòng tập. Đặc quyền tập luyện ở mọi cơ sở.',
     0, 50.00, 0, 0, 10, 30),
    (108, 3, 2, 1, 2, 'Gói Silver',
     'Quý khách sẽ có quyền lợi được tham gia tất cả các lớp tập 1 một chi nhánh bạn lựa chọn.Tham gia các lớp dance, Group X, Yoga, Gym, Kickfit,….Có Locker (tủ) đựng đồ, được xông hơi, sử dụng phòng tắm,…',
     9000.00, 0, 0, 720, 10, 720),
    (109, 3, 2, 1, 2, 'Gói Gold ',
     'Quý Khách sẽ có quyền lợi như gói Silver.Có thêm khăn mỗi buổi tập nên không cần mang theo. Được tập tại tất cả các chi nhánh của S’life Gym',
     12500.00, 0, 0, 1080, 10, 1080),
    (110, 3, 2, 1, 2, 'Gói Titaninum ',
     'Quý Khách sẽ được hưởng tất cả các quyền lợi như của thẻ Silver và Gold thì còn được uống detox sau mỗi buổi tập.Được tham gia tất cả các lớp tập tại tất cả các chi nhánh',
     13000.00, 0, 0, 900, 10, 900),

    /******************************************** Diamond Fitness  ***************************************************/
    (111, 4, 1, 1, 1, 'Gói Linh Hoạt',
     'Gói tập linh hoạt, phù hợp cho người muốn trải nghiệm các phòng tập. Đặc quyền tập luyện ở mọi cơ sở.',
     0, 50.00, 0, 0, 10, 30),
    (112, 4, 2, 1, 2, 'Gói Siêu Quyền Lợi',
     'Hơn 45 bộ môn : Gym, Kick Boxing, GroupX, Cycling. Hơn 100+ lớp học thú vị: Dance, Zumba, Múa cổ trang, Aerobics, TikTok Dance,. Đội ngũ HLV giàu kinh nghiệm chuyên môn ',
     3400.00, 0, 0, 365, 10, 365),
    (113, 4, 2, 1, 2, 'Gói Full Dịch Vụ',
     'Quý khách sẽ được hưởng quyền lợi như Gói Siêu Quyền Lợi cộng thêm tự do tập toàn khung giờ từ 5:30 - 22:00.Dịch vụ tiện ích thư giãn cao cấp: xông hơi, bơi lội,...',
     4000.00, 0, 0, 365, 10, 365);


INSERT INTO gym_department_amenities (gym_department_id, amenitie_id)
VALUES
    /******************************************** Kickfit Sport Departments  ***************************************************/
    (21, 101),
    (21, 102),
    (21, 103),
    (21, 104),

    (22, 101),
    (22, 102),
    (22, 103),
    (22, 104),

    (23, 101),
    (23, 102),
    (23, 103),
    (23, 104),

    (24, 101),
    (24, 102),
    (24, 103),
    (24, 104),
    /******************************************** 25 FIT Departments  ***************************************************/
    (25, 105),
    (25, 106),
    (25, 107),
    (25, 108),

    (26, 105),
    (26, 106),
    (26, 107),
    (26, 108),

    (27, 105),
    (27, 106),
    (27, 107),
    (27, 108),

    (28, 105),
    (28, 106),
    (28, 107),
    (28, 108),
    /******************************************** S'Life Gym Departments  ***************************************************/
    (29, 109),
    (29, 110),
    (29, 111),
    (29, 112),

    (30, 109),
    (30, 110),
    (30, 111),
    (30, 112),

    (31, 109),
    (31, 110),
    (31, 111),
    (31, 112),

    (32, 109),
    (32, 110),
    (32, 111),
    (32, 112),
    /******************************************** Diamond Fitness Departments  ***************************************************/
    (33, 113),
    (33, 114),
    (33, 115),
    (33, 116),

    (34, 113),
    (34, 114),
    (34, 115),
    (34, 116),

    (35, 113),
    (35, 114),
    (35, 115),
    (35, 116),

    (36, 113),
    (36, 114),
    (36, 115),
    (36, 116);

INSERT INTO gym_department_plans(gym_department_id, plan_id)
VALUES
    /******************************************** Kickfit Sport Departments Plans ***************************************************/
    (21, 101),
    (21, 102),
    (21, 103),

    (22, 101),
    (22, 102),
    (22, 103),

    (23, 101),
    (23, 102),
    (23, 103),

    (24, 101),
    (24, 102),
    (24, 103),
    /******************************************** 25 FIT Departments Plans ***************************************************/
    (25, 104),
    (25, 105),
    (25, 106),

    (26, 104),
    (26, 105),
    (26, 106),

    (27, 104),
    (27, 105),
    (27, 106),

    (28, 104),
    (28, 105),
    (28, 106),
    /******************************************** S'Life Gym Departments Plans ***************************************************/
    (29, 107),
    (29, 108),
    (29, 109),
    (29, 110),

    (30, 107),
    (30, 108),
    (30, 109),
    (30, 110),

    (31, 107),
    (31, 108),
    (31, 109),
    (31, 110),

    (32, 107),
    (32, 108),
    (32, 109),
    (32, 110),
    /******************************************** Diamond Fitness Departments Plans ***************************************************/
    (33, 111),
    (33, 112),
    (33, 113),

    (34, 111),
    (34, 112),
    (34, 113),

    (35, 111),
    (35, 112),
    (35, 113),

    (36, 111),
    (36, 112),
    (36, 113);

INSERT INTO gym_department_schedule (gym_department_id, day, open_time, close_time)
VALUES
    /******************************************** Kickfit Sport Departments Schedule ***************************************************/
    (21, 'Thứ 2', '05:00 AM', '10:00 PM'),
    (21, 'Thứ 3', '05:00 AM', '10:00 PM'),
    (21, 'Thứ 4', '05:00 AM', '10:00 PM'),
    (21, 'Thứ 5', '05:00 AM', '10:00 PM'),
    (21, 'Thứ 6', '05:00 AM', '10:00 PM'),
    (21, 'Thứ 7', '05:00 AM', '10:00 PM'),
    (21, 'Chủ Nhật', '05:00 AM', '10:00 PM'),

    (22, 'Thứ 2', '06:00 AM', '10:00 PM'),
    (22, 'Thứ 3', '06:00 AM', '10:00 PM'),
    (22, 'Thứ 4', '06:00 AM', '10:00 PM'),
    (22, 'Thứ 5', '06:00 AM', '10:00 PM'),
    (22, 'Thứ 6', '06:00 AM', '10:00 PM'),
    (22, 'Thứ 7', '06:00 AM', '10:00 PM'),
    (22, 'Chủ Nhật', '06:00 AM', '10:00 PM'),

    (23, 'Thứ 2', '05:30 AM', '09:30 PM'),
    (23, 'Thứ 3', '05:30 AM', '09:30 PM'),
    (23, 'Thứ 4', '05:30 AM', '09:30 PM'),
    (23, 'Thứ 5', '05:30 AM', '09:30 PM'),
    (23, 'Thứ 6', '05:30 AM', '09:30 PM'),
    (23, 'Thứ 7', '05:30 AM', '09:30 PM'),
    (23, 'Chủ Nhật', '05:30 AM', '09:30 PM'),

    (24, 'Thứ 2', '05:00 AM', '10:00 PM'),
    (24, 'Thứ 3', '05:00 AM', '10:00 PM'),
    (24, 'Thứ 4', '05:00 AM', '10:00 PM'),
    (24, 'Thứ 5', '05:00 AM', '10:00 PM'),
    (24, 'Thứ 6', '05:00 AM', '10:00 PM'),
    (24, 'Thứ 7', '05:00 AM', '10:00 PM'),
    (24, 'Chủ Nhật', '05:00 AM', '10:00 PM'),

    /******************************************** 25 FIT Departments Schedule ***************************************************/
    (25, 'Thứ 2', '06:30 AM', '08:30 PM'),
    (25, 'Thứ 3', '06:30 AM', '08:30 PM'),
    (25, 'Thứ 4', '06:30 AM', '08:30 PM'),
    (25, 'Thứ 5', '06:30 AM', '08:30 PM'),
    (25, 'Thứ 6', '06:30 AM', '08:30 PM'),
    (25, 'Thứ 7', '06:30 AM', '08:30 PM'),
    (25, 'Chủ Nhật', '08:00 AM', '05:30 PM'),

    (26, 'Thứ 2', '06:00 AM', '10:00 PM'),
    (26, 'Thứ 3', '06:00 AM', '10:00 PM'),
    (26, 'Thứ 4', '06:00 AM', '10:00 PM'),
    (26, 'Thứ 5', '06:00 AM', '10:00 PM'),
    (26, 'Thứ 6', '06:00 AM', '10:00 PM'),
    (26, 'Thứ 7', '06:00 AM', '10:00 PM'),
    (26, 'Chủ Nhật', '08:00 AM', '06:00 PM'),

    (27, 'Thứ 2', '06:30 AM', '08:30 PM'),
    (27, 'Thứ 3', '06:30 AM', '08:30 PM'),
    (27, 'Thứ 4', '06:30 AM', '08:30 PM'),
    (27, 'Thứ 5', '06:30 AM', '08:30 PM'),
    (27, 'Thứ 6', '06:30 AM', '08:30 PM'),
    (27, 'Thứ 7', '06:30 AM', '08:30 PM'),
    (27, 'Chủ Nhật', '08:00 AM', '05:30 PM'),

    (28, 'Thứ 2', '06:30 AM', '08:30 PM'),
    (28, 'Thứ 3', '06:30 AM', '08:30 PM'),
    (28, 'Thứ 4', '06:30 AM', '08:30 PM'),
    (28, 'Thứ 5', '06:30 AM', '08:30 PM'),
    (28, 'Thứ 6', '06:30 AM', '08:30 PM'),
    (28, 'Thứ 7', '06:30 AM', '08:30 PM'),
    (28, 'Chủ Nhật', '08:00 AM', '05:30 PM'),

    /******************************************** S'Life Gym Departments Schedule ***************************************************/
    (29, 'Thứ 2', '06:00 AM', '10:00 PM'),
    (29, 'Thứ 3', '06:00 AM', '10:00 PM'),
    (29, 'Thứ 4', '06:00 AM', '10:00 PM'),
    (29, 'Thứ 5', '06:00 AM', '10:00 PM'),
    (29, 'Thứ 6', '06:00 AM', '10:00 PM'),
    (29, 'Thứ 7', '06:00 AM', '10:00 PM'),
    (29, 'Chủ Nhật', '08:00 AM', '08:00 PM'),

    (30, 'Thứ 2', '06:00 AM', '10:00 PM'),
    (30, 'Thứ 3', '06:00 AM', '10:00 PM'),
    (30, 'Thứ 4', '06:00 AM', '10:00 PM'),
    (30, 'Thứ 5', '06:00 AM', '10:00 PM'),
    (30, 'Thứ 6', '06:00 AM', '10:00 PM'),
    (30, 'Thứ 7', '06:00 AM', '10:00 PM'),
    (30, 'Chủ Nhật', '08:00 AM', '08:00 PM'),

    (31, 'Thứ 2', '06:00 AM', '10:00 PM'),
    (31, 'Thứ 3', '06:00 AM', '10:00 PM'),
    (31, 'Thứ 4', '06:00 AM', '10:00 PM'),
    (31, 'Thứ 5', '06:00 AM', '10:00 PM'),
    (31, 'Thứ 6', '06:00 AM', '10:00 PM'),
    (31, 'Thứ 7', '06:00 AM', '10:00 PM'),
    (31, 'Chủ Nhật', '08:00 AM', '08:00 PM'),

    (32, 'Thứ 2', '06:00 AM', '10:00 PM'),
    (32, 'Thứ 3', '06:00 AM', '10:00 PM'),
    (32, 'Thứ 4', '06:00 AM', '10:00 PM'),
    (32, 'Thứ 5', '06:00 AM', '10:00 PM'),
    (32, 'Thứ 6', '06:00 AM', '10:00 PM'),
    (32, 'Thứ 7', '06:00 AM', '10:00 PM'),
    (32, 'Chủ Nhật', '08:00 AM', '08:00 PM'),

    /******************************************** Diamond Fitness Departments Schedule ***************************************************/
    (33, 'Thứ 2', '6:00 AM', '10:00 PM'),
    (33, 'Thứ 3', '9:00 AM', '11:00 PM'),
    (33, 'Thứ 4', '9:00 AM', '11:00 PM'),
    (33, 'Thứ 5', '9:00 AM', '11:00 PM'),
    (33, 'Thứ 6', '9:00 AM', '11:00 PM'),
    (33, 'Thứ 7', '9:00 AM', '11:00 PM'),
    (33, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    (34, 'Thứ 2', '6:00 AM', '10:00 PM'),
    (34, 'Thứ 3', '9:00 AM', '11:00 PM'),
    (34, 'Thứ 4', '9:00 AM', '11:00 PM'),
    (34, 'Thứ 5', '9:00 AM', '11:00 PM'),
    (34, 'Thứ 6', '9:00 AM', '11:00 PM'),
    (34, 'Thứ 7', '9:00 AM', '11:00 PM'),
    (34, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    (35, 'Thứ 2', '6:00 AM', '10:00 PM'),
    (35, 'Thứ 3', '9:00 AM', '11:00 PM'),
    (35, 'Thứ 4', '9:00 AM', '11:00 PM'),
    (35, 'Thứ 5', '9:00 AM', '11:00 PM'),
    (35, 'Thứ 6', '9:00 AM', '11:00 PM'),
    (35, 'Thứ 7', '9:00 AM', '11:00 PM'),
    (35, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    (36, 'Thứ 2', '6:00 AM', '10:00 PM'),
    (36, 'Thứ 3', '9:00 AM', '11:00 PM'),
    (36, 'Thứ 4', '9:00 AM', '11:00 PM'),
    (36, 'Thứ 5', '9:00 AM', '11:00 PM'),
    (36, 'Thứ 6', '9:00 AM', '11:00 PM'),
    (36, 'Thứ 7', '9:00 AM', '11:00 PM'),
    (36, 'Chủ Nhật', '9:00 AM', '11:00 PM');

INSERT INTO user_feedback
(user_id, gym_plan_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES
    /******************************************** Kickfit Sport Departments Feedback ***************************************************/
    (1, 101, 21, 5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(), 1),
    (2, 102, 21, 5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(), 1),
    (3, 103, 21, 4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (4, 101, 21, 4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (5, 102, 21, 3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(), 1),
    (1, 103, 21, 3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1, 101, 22, 5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(), 1),
    (2, 102, 22, 5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(), 1),
    (3, 103, 22, 4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (4, 101, 22, 4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (5, 102, 22, 3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(), 1),
    (1, 103, 22, 3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1, 101, 23, 5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(), 1),
    (2, 102, 23, 5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(), 1),
    (3, 103, 23, 4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (4, 101, 23, 4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (5, 102, 23, 3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(), 1),
    (1, 103, 23, 3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1, 101, 24, 5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(), 1),
    (2, 102, 24, 5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(), 1),
    (3, 103, 24, 4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (4, 101, 24, 4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (5, 102, 24, 3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(), 1),
    (1, 103, 24, 3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    /******************************************** 25 FIT Departments Feedback ***************************************************/
    (1, 104, 25, 5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(), 1),
    (2, 105, 25, 5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(), 1),
    (3, 106, 25, 4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (4, 104, 25, 4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (5, 105, 25, 3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(), 1),
    (1, 106, 25, 3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1, 104, 26, 5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(), 1),
    (2, 105, 26, 5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(), 1),
    (3, 106, 26, 4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (4, 104, 26, 4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (5, 105, 26, 3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(), 1),
    (1, 106, 26, 3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1, 104, 27, 5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(), 1),
    (2, 105, 27, 5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(), 1),
    (3, 106, 27, 4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (4, 104, 27, 4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (5, 105, 27, 3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(), 1),
    (1, 106, 27, 3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1, 104, 28, 5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(), 1),
    (2, 105, 28, 5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(), 1),
    (3, 106, 28, 4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (4, 104, 28, 4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (5, 105, 28, 3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(), 1),
    (1, 106, 28, 3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    /******************************************** S'Life Gym Departments Feedback ***************************************************/
    (1, 107, 29, 5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(), 1),
    (2, 108, 29, 5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(), 1),
    (3, 109, 29, 4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (4, 110, 29, 4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (5, 107, 29, 3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(), 1),
    (1, 108, 29, 3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1, 107, 30, 5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(), 1),
    (2, 108, 30, 5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(), 1),
    (3, 109, 30, 4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (4, 110, 30, 4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (5, 107, 30, 3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(), 1),
    (1, 108, 30, 3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1, 107, 31, 5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(), 1),
    (2, 108, 31, 5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(), 1),
    (3, 109, 31, 4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (4, 110, 31, 4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (5, 107, 31, 3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(), 1),
    (1, 108, 31, 3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1, 107, 32, 5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(), 1),
    (2, 108, 32, 5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(), 1),
    (3, 109, 32, 4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (4, 110, 32, 4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (5, 107, 32, 3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(), 1),
    (1, 108, 32, 3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    /******************************************** Diamond Fitness Departments Feedback ***************************************************/
    (1, 111, 33, 5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(), 1),
    (2, 112, 33, 5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(), 1),
    (3, 113, 33, 4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (4, 111, 33, 4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (5, 112, 33, 3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(), 1),
    (1, 113, 33, 3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1, 111, 34, 5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(), 1),
    (2, 112, 34, 5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(), 1),
    (3, 113, 34, 4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (4, 111, 34, 4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (5, 112, 34, 3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(), 1),
    (1, 113, 34, 3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1, 111, 35, 5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(), 1),
    (2, 112, 35, 5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(), 1),
    (3, 113, 35, 4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (4, 111, 35, 4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (5, 112, 35, 3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(), 1),
    (1, 113, 35, 3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1, 111, 36, 5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(), 1),
    (2, 112, 36, 5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(), 1),
    (3, 113, 36, 4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (4, 111, 36, 4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(), 1),
    (5, 112, 36, 3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(), 1),
    (1, 113, 36, 3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1);

INSERT INTO gym_department_features (feature_id, gym_department_id, feature_status)
VALUES (1, 21, 1),
       (2, 21, 1),
       (3, 21, 1),
       (4, 21, 1),
       (5, 21, 1),

       (1, 22, 1),
       (2, 22, 1),
       (3, 22, 1),
       (6, 22, 1),
       (7, 22, 1),

       (1, 23, 1),
       (3, 23, 1),
       (4, 23, 1),
       (5, 23, 1),
       (7, 23, 1),

       (1, 24, 1),
       (3, 24, 1),
       (4, 24, 1),
       (5, 24, 1),
       (7, 24, 1),

       (1, 25, 1),
       (3, 25, 1),
       (4, 25, 1),
       (5, 25, 1),
       (7, 25, 1),

       (1, 26, 1),
       (3, 26, 1),
       (4, 26, 1),
       (5, 26, 1),
       (7, 26, 1),

       (1, 27, 1),
       (3, 27, 1),
       (4, 27, 1),
       (5, 27, 1),
       (7, 27, 1),

       (1, 28, 1),
       (3, 28, 1),
       (4, 28, 1),
       (5, 28, 1),
       (7, 28, 1),

       (1, 29, 1),
       (3, 29, 1),
       (4, 29, 1),
       (5, 29, 1),
       (7, 29, 1),

       (1, 30, 1),
       (3, 30, 1),
       (4, 30, 1),
       (5, 30, 1),
       (7, 30, 1),

       (1, 31, 1),
       (3, 31, 1),
       (4, 31, 1),
       (5, 31, 1),
       (7, 31, 1),

       (1, 32, 1),
       (3, 32, 1),
       (4, 32, 1),
       (5, 32, 1),
       (7, 32, 1),

       (1, 33, 1),
       (3, 33, 1),
       (4, 33, 1),
       (5, 33, 1),
       (7, 33, 1),

       (1, 34, 1),
       (3, 34, 1),
       (4, 34, 1),
       (5, 34, 1),
       (7, 34, 1),

       (1, 35, 1),
       (3, 35, 1),
       (4, 35, 1),
       (5, 35, 1),
       (7, 35, 1),


       (1, 36, 1),
       (3, 36, 1),
       (4, 36, 1),
       (5, 36, 1),
       (7, 36, 1);