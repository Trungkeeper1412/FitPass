-- Admin creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address,
                         date_of_birth,
                         gender)
VALUES (1, 'John', 'Doe', 'johndoe@example.com', '1234567890', '123 Main St, City, Country', '1990-01-01', 'Male');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted,user_detail_id)
VALUES (1, 'admin', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 1);

-- Gym Owner creation
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

-- Gym Department Creation
-- Create Department Status
INSERT INTO mst_kbn (mst_kbn_name, mst_kbn_key, mst_kbn_value) VALUES ('DEPARTMENT_STATUS', 1, 'Hoạt Động');
INSERT INTO mst_kbn (mst_kbn_name, mst_kbn_key, mst_kbn_value) VALUES ('DEPARTMENT_STATUS', 2, 'Không Hoạt Động');

-- Create Department
INSERT INTO gym_department (gym_department_status_key, user_id, name, address, contact_number, logo_url, wallpaper_url, description, latitude, longitude, rating, capacity, area)
VALUES (1, 1, 'Gym A', '123 Main St', '555-123-4567', 'https://example.com/logo.png', 'https://example.com/wallpaper.png', 'This is Gym A', 37.12345678, -122.12345678, 0, 100, 200.50),
       (1, 2, 'Gym B', '456 Elm St', '555-987-6543', 'https://example.com/logo.png', 'https://example.com/wallpaper.png', 'This is Gym B', 37.98765432, -122.98765432, 0, 150, 300.75);

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES(1, 'https://example.com/photo1.png', 'Album 1'),
      (2, 'https://example.com/photo2.png', 'Album 2');


INSERT INTO gym_department_schedule (gym_department_id, day, open_time, close_time)
VALUES (1, 'Monday', '8:00 AM', '10:00 PM'),
       (2, 'Tuesday', '9:00 AM', '11:00 PM');

INSERT INTO gym_department_services (gym_department_id, massage, sauna, bathroom, air_conditioner, boxing, body_composition_analyzer, pool, bar)
VALUES (1, 1, 1, 1, 1, 0, 0, 1, 0),
       (2, 0, 1, 1, 0, 1, 0, 1, 0);

INSERT INTO mst_kbn (mst_kbn_name, mst_kbn_key, mst_kbn_value)
VALUES ('Gym Plan Type', 1, 'Gói theo giờ'),
       ('Gym Plan Type', 2, 'Gói không theo giờ'),
       ('Gym Plan Status', 1, 'Chưa kích hoạt'),
       ('Gym Plan Status', 2, 'Đang sử dụng'),
       ('Gym Plan Status', 3, 'Đã sử dụng'),
       ('Gym Plan Status', 4, 'Quá hạn');

INSERT INTO gym_plan (gym_department_id, user_id, gym_plan_key, gym_plan_status_key, gym_plan_type_key, name, description, price, price_per_hours, plan_sold, duration, plan_before_active_validity, plan_after_active_validity)
VALUES (1, 1, 1, 1, 1, 'Gói 1', 'Gói theo giờ 1', 100.00, 10.00, 20, 3, 7, 14),
       (1, 1, 2, 2, 2, 'Gói 2', 'Gói khong theo giờ ', 150.00, 15.00, 30, 5, 10, 20);
