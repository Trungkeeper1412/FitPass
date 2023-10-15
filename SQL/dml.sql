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
INSERT INTO gym_department (gym_department_status_key, user_id, name, address, contact_number, logo_url, wallpaper_url, description, latitude, longitude, rating, capacity, area)
VALUES (1, 1, 'Gym A', '123 Main St', '555-123-4567', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSFg0vALj7wnJ-W6SbdUr7vhA278f3BPsMnQQ&usqp=CAU', 'https://example.com/wallpaper.png', 'Không chỉ là 1 phòng tập gym - thể hình đơn lẻ, Kickfit Sports là một trung tâm thể dụng thể thao với quy mô lớn và hiện đại hàng đầu Hà Nội. Các trang thiết bị luyện tập đều được nhập khẩu từ các thương hiệu lớn tại Châu Âu và Mỹ đem đến trải nghiệm tập luyện tốt nhất. Phòng tập được tích hợp đầy đủ bể bơi 4 mùa, phòng xông hơi, spa, sân tập ngoài trời và trên cao thoáng mát, tủ đồ, khu vực nghỉ ngơi... Ngoài tập gym bạn còn có thể trải nghiệm nhiều loại hình thể thao khác như: Boxing, Muay Thá', 37.12345678, -122.12345678, 0, 100, 200.50),
       (1, 2, 'Gym B', '456 Elm St', '555-987-6543', 'https://example.com/logo.png', 'https://example.com/wallpaper.png', 'This is Gym B', 37.98765432, -122.98765432, 0, 150, 300.75);

/** INSERT INTO gym_department
(gym_department_id, gym_department_status_key, user_id, name, address, contact_number,
 logo_url, opening_hours, wallpaper_url, description, latitude, longitude,capacity,area)
VALUES
    (1, 1, 2, 'Super Gym Hoa Lac', '123 Main St', '555-123-1234',
     'logo1.png', '8am-8pm', 'img1.jpg', 'Gym description', 20.994853642138313, 105.52473891128982),
    (2, 1, 2, 'Gym Hoa Lac', '124 Main St', '555-123-1235',
     'logo2.png', '9am-9pm', 'img2.jpg', 'Gym description', 20.984756591708862, 105.5304037366675),
    (3, 1, 2, 'FitWay Kickboxing', '125 Main St', '555-123-1236',
     'logo3.png', '10am-10pm', 'img3.jpg', 'Gym description', 21.013202882232747, 105.5189882552246);
**/


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
INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES(1, 'https://i.pinimg.com/236x/6c/c4/49/6cc4498dfac9d232b9c49f46d1948f8b.jpg', 'Album 1'),
      (1, 'https://i.pinimg.com/236x/62/ce/d1/62ced13c5fd204f575f47d4b026243dd.jpg', 'Album 2');


INSERT INTO gym_department_schedule (gym_department_id, day, open_time, close_time)
VALUES (1, 'Monday', '8:00 AM', '10:00 PM'),
       (2, 'Tuesday', '9:00 AM', '11:00 PM');



INSERT INTO mst_kbn (mst_kbn_name, mst_kbn_key, mst_kbn_value)
VALUES ('Gym Plan Type', 1, 'Gói theo giờ'),
       ('Gym Plan Type', 2, 'Gói không theo giờ'),
       ('Gym Plan Status', 1, 'Chưa kích hoạt'),
       ('Gym Plan Status', 2, 'Đang sử dụng'),
       ('Gym Plan Status', 3, 'Đã sử dụng'),
       ('Gym Plan Status', 4, 'Quá hạn');

INSERT INTO gym_plan (gym_department_id, user_id, gym_plan_key, gym_plan_status_key, gym_plan_type_key, name, description, price, price_per_hours, plan_sold, duration, plan_before_active_validity, plan_after_active_validity)
VALUES (1, 1, 1, 1, 1, 'Gói giờ', 'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp. ', 100.00, 10.00, 20, 3, 7, 14),
       (1, 1, 2, 2, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính tại Gym Hòa Lạc. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ', 150.00, 15.00, 30, 5, 10, 20);

-- insert data to features
INSERT INTO gym_department_features (gym_department_id, feature_icon, feature_name, isSelected)
VALUES (1, 'icon1.png', 'Bể bơi', 1);

INSERT INTO gym_department_features (gym_department_id, feature_icon, feature_name, isSelected)
VALUES (1, 'icon2.png', 'Massage', 0);

INSERT INTO gym_department_features (gym_department_id, feature_icon, feature_name, isSelected)
VALUES (1, 'icon3.png', 'Xông hơi', 1);

INSERT INTO gym_department_features (gym_department_id, feature_icon, feature_name, isSelected)
VALUES (1, 'icon4.png', 'Phòng tắm', 1);

INSERT INTO gym_department_features (gym_department_id, feature_icon, feature_name, isSelected)
VALUES (1, 'icon5.png', 'Máy lạnh', 0);

INSERT INTO gym_department_features (gym_department_id, feature_icon, feature_name, isSelected)
VALUES (1, 'icon6.png', 'Boxing', 0);

INSERT INTO gym_department_features (gym_department_id, feature_icon, feature_name, isSelected)
VALUES (1, 'icon7.png', 'Quầy bar', 1);

INSERT INTO gym_department_features (gym_department_id, feature_icon, feature_name, isSelected)
VALUES (1, 'icon8.png', 'Máy đo chỉ số cơ thể', 1);