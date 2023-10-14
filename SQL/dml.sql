/********** Role Creation ***********/
INSERT INTO role (role_id, role_name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO role (role_id, role_name) VALUES (2, 'ROLE_MANAGER');
INSERT INTO role (role_id, role_name) VALUES (3, 'ROLE_EMPLOYEE');
INSERT INTO role (role_id, role_name) VALUES (4, 'ROLE_MEMBER');

/********** User Creation ***********/
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

-- Assign role for users
INSERT INTO user_role (user_role_id, user_id, role_id) VALUES (1, 1, 1);
INSERT INTO user_role (user_role_id, user_id, role_id) VALUES (2, 2, 2);

/********** Gym Department Creation ***********/
-- Create Department Status
INSERT INTO mst_kbn (mst_kbn_name, mst_kbn_key, mst_kbn_value) VALUES ('DEPARTMENT_STATUS', 1, 'Hoạt Động');
INSERT INTO mst_kbn (mst_kbn_name, mst_kbn_key, mst_kbn_value) VALUES ('DEPARTMENT_STATUS', 2, 'Không Hoạt Động');

-- Create Department
INSERT INTO gym_department
(gym_department_id, gym_department_status_key, user_id, name, address, contact_number,
 logo_url, opening_hours, image_url, description, latitude, longitude)
VALUES
    (1, 1, 2, 'Super Gym Hoa Lac', '123 Main St', '555-123-1234',
     'logo1.png', '8am-8pm', 'img1.jpg', 'Gym description', 20.994853642138313, 105.52473891128982),
    (2, 1, 2, 'Gym Hoa Lac', '124 Main St', '555-123-1235',
     'logo2.png', '9am-9pm', 'img2.jpg', 'Gym description', 20.984756591708862, 105.5304037366675),
    (3, 1, 2, 'FitWay Kickboxing', '125 Main St', '555-123-1236',
     'logo3.png', '10am-10pm', 'img3.jpg', 'Gym description', 21.013202882232747, 105.5189882552246);

/********** Department Feedback Creation ***********/
-- 2 records with department_id = 2
INSERT INTO user_feedback
(feedback_id, user_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES
    (1, 1, 2, 5, 'Great service', NOW(), 1),
    (2, 2, 2, 4, 'Staff was helpful', NOW(), 1);

-- 2 records with department_id = 1
INSERT INTO user_feedback
VALUES
    (3, 1, 1, 3, 'Equipment needs updating', NOW(), 1),
    (4, 2, 1, 2, 'Floor was dirty', NOW(), 1);

-- 2 records with department_id = 3
INSERT INTO user_feedback
VALUES
    (5, 1, 3, 5, 'Loved the environment', NOW(), 1),
    (6, 2, 3, 4, 'Instructors were knowledgeable', NOW(), 1);