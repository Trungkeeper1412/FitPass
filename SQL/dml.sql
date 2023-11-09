/********** Role Creation ***********/
INSERT INTO role (role_id, role_name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO role (role_id, role_name) VALUES (2, 'ROLE_MANAGER');
INSERT INTO role (role_id, role_name) VALUES (3, 'ROLE_EMPLOYEE');
INSERT INTO role (role_id, role_name) VALUES (4, 'ROLE_USER');

/********** User Creation ***********/
-- Admin creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address,
                         date_of_birth,
                         gender, image_url)
VALUES (1, 'John', 'Doe', 'johndoe@example.com', '1234567890', '123 Main St',
        '1990-01-01', 'Male','/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted,user_detail_id)
VALUES (1, 'admin', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 1);

-- Gym Owner creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address,
                         date_of_birth,
                         gender, image_url)
VALUES (2, 'Jane', 'Doe', 'janedoe@example.com', '1234567890', '123 Main St',
        '1990-01-01', 'Female','/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted,user_detail_id)
VALUES (2, 'gymowner', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 2);

-- Employee creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address,
                         date_of_birth,
                         gender, image_url)
VALUES (3, 'Nguyen Van Bao', 'Linh', 'cuongbulu@gmail.com', '0987654321', '123 Main St',
        '2001-01-01', 'Male','/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted,user_detail_id)
VALUES (3, 'employee', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 3);

-- User creation
INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address,
                         date_of_birth,
                         gender, image_url)
VALUES (4, 'Le Dinh', 'Tuan', 'tuana1@gmail.com', '0987654321', '123 Main St',
        '2001-01-01', 'Male','/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted,user_detail_id)
VALUES (4, 'tuanld', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 4);

INSERT INTO user_detail (user_detail_id, first_name, last_name, email, phone_number, address,
                         date_of_birth,
                         gender, image_url)
VALUES (5, 'Dinh', 'Tuan Anh', 'tuananh@gmail.com', '0987654321', '123 Main St',
        '1995-01-01', 'Male','/images/system/v.png');

INSERT INTO user (user_id, user_account, user_password, user_create_time, user_deleted,user_detail_id)
VALUES (5, 'anhdt', '$2a$12$RtKhDBN9Ba8UlVcAulEenOsxqHK5ZNQ1Lj62508aqPwg9Jbbv6/c2', '1655989807', 0, 5);

-- Assign role for users
INSERT INTO user_role (user_role_id, user_id, role_id) VALUES (1, 1, 1);
INSERT INTO user_role (user_role_id, user_id, role_id) VALUES (2, 2, 2);
INSERT INTO user_role (user_role_id, user_id, role_id) VALUES (3, 3, 3);
INSERT INTO user_role (user_role_id, user_id, role_id) VALUES (4, 4, 4);
INSERT INTO user_role (user_role_id, user_id, role_id) VALUES (5, 5, 4);

-- User Wallet insert
INSERT INTO wallet (user_id, balance) VALUES (1,20000);
INSERT INTO wallet (user_id, balance) VALUES (2,20000);
INSERT INTO wallet (user_id, balance) VALUES (3,0);
INSERT INTO wallet (user_id, balance) VALUES (4,1000);
INSERT INTO wallet (user_id, balance) VALUES (5,1000);

/********** Brand Creation ***********/
-- Create brand infos
INSERT INTO brand (brand_id,user_id,name,logo_url,wallpaper_url,thumbnail_url,description,rating,contact_number,contact_email,brand_status_key)
VALUES
    ( 1,1, 'Kickfit Sports', 'https://kickfit-sports.com/wp-content/uploads/2021/03/Do-Den.png', 'https://diadiemvietnam.vn/wp-content/uploads/2022/09/Thiet-ke-chua-co-ten-3-1.png','https://diadiemvietnam.vn/wp-content/uploads/2022/09/Thiet-ke-chua-co-ten-3-1.png', 'Công ty cổ phần thể thao Kickfit Sports sở hữu tổ hợp đầy đủ các bộ môn từ võ thuật, thể hình, rèn luyện Thân - Tâm - Trí. Định hướng chính của Kickfit Sports là đưa các bộ môn Kickfit và Fitness trở thành thói quen sinh hoạt tích cực hàng ngày cho tất cả mọi người.Kickfit Sports đã đang và sẽ phát triển theo hướng: “Chuyên nghiệp – Chất Lượng – Luôn đổi mới và hoàn thiện” đồng thời mong muốn mang lại sức khỏe và thịnh vượng cho hàng triệu người Việt Nam.Với hơn 10 năm xây dựng và phát triển, hiện tại Kickfit Sports đang sở hữu 14 cơ sở phòng tập trải rộng khắp Hà Nội. Trung tâm luôn chú trọng đầu tư, cải tiến các sản phẩm, dịch vụ để mang đến môi trường tập luyện 5 sao, uy tín và chất lượng.', 4.5, '9991234567', 'brand1@email.com', 1),
    ( 2,1, 'Elite Fitness', 'https://bimgroup.com/uploads/catalog/2022/6/4ff9ee27-1562-4d32-be81-8f7f52fe3eab.png', 'https://diadiemvietnam.vn/wp-content/uploads/2022/09/Thiet-ke-chua-co-ten-6.png','https://diadiemvietnam.vn/wp-content/uploads/2022/09/Thiet-ke-chua-co-ten-6.png', 'HỆ THỐNG CÂU LẠC BỘ THỂ THAO CAO CẤP ELITE FITNESS
Ra đời từ năm 2010, Elite Fitness đã từng bước khẳng định vị thế và danh tiếng của mình là hệ thống câu lạc bộ thể dục thể thao cao cấp, hàng đầu tại Việt Nam.

Tại Elite Fitness, những mục tiêu tập luyện của hội viên được hiện thực hóa bởi các chuyên gia hàng đầu trong ngành thể dục thẩm mỹ như: Giảm cân; Tạo hình cơ thể; Tăng cường cơ bắp, sức mạnh, sức bền; Tăng cường sự linh hoạt, dẻo dai; Tăng cường vẻ đẹp hình thể; Cải thiện và khắc phục các chấn thương thể thao; Cải thiện và khắc phục những vấn đề về sức khỏe; Phục vụ các nhu cầu tập luyện riêng biệt khác…', 3.5, '9981234567', 'brand2@email.com', 1),
    ( 3,1, 'Citi Gym', 'https://citigym.com.vn/storage/uploads/logocitigym.png', 'https://novaworld.info/wp-content/uploads/2022/10/citigym.jpg','https://novaworld.info/wp-content/uploads/2022/10/citigym.jpg', 'CITIGYM - Thương hiệu dẫn đầu phong cách sống, được thành lập từ năm 2018, đến nay CITIGYM đã có 7 chi nhánh tại TP.HCM và phục vụ hơn 100.000 khách hàng. Cơ sở vật chất, trang thiết bị tối tân từ các thương hiệu hàng đầu thế giới. Đội ngũ HLV chuyên nghiệp, giàu kinh nghiệm, mang đến những trải nghiệm tập luyện thú vị, đáp ứng mong muốn về vóc dáng và sức khoẻ cho người Việt. Đặc biệt, Citigym là thương hiệu fitness đầu tiên mang không gian xanh vào phòng tập, cho bạn cảm nhận sự trong lành, nhẹ nhàng thư thái.', 4, '9971234567', 'brand3@email.com', 1),
    ( 4,1, 'California Fitness', 'https://cali.vn/storage/app/media/2022/System/Cali-Link-Thumbnail.png', 'https://cali.vn/storage/app/media/gia-tap-california-thumb.jpg', 'https://cali.vn/storage/app/media/gia-tap-california-thumb.jpg','Là thương hiệu về sức khỏe lớn nhất Việt Nam, California Fitness & Yoga được xây dựng để mang lại hạnh phúc và tạo ra những khoảnh khắc viên mãn cho bạn trong cuộc sống bằng việc cung cấp các dịch vụ phát triển sức khỏe thể chất, dinh dưỡng và tinh thần toàn diện.', 4.5, '9961234567', 'brand4@email.com', 1),
    ( 5,1, 'Getfit Gym', 'https://getfit-gym.vn/wp-content/uploads/newlogogetfit2021-04.png', 'https://images.foody.vn/res/g12/111029/prof/s640x400/foody-mobile-hmb-et-fit-jpg-439-635555465733080414.jpg', 'https://images.foody.vn/res/g12/111029/prof/s640x400/foody-mobile-hmb-et-fit-jpg-439-635555465733080414.jpg','Thành lập năm 2010, Getfit nhanh chóng trở thành một trong những thương hiệu Fitness lớn nhất Việt Nam. Với cơ sở vật chất đẳng cấp, thiết bị luyện tập thể thao hiện đại, đội ngũ Huấn luyện viên có chuyên môn, Getfit tạo dấu ấn đặc biệt trong giới thể hình Việt.', 3, '9951234567', 'brand5@email.com', 1);

INSERT INTO brand_amenities (brand_id, photo_url, amenitie_name, description)
VALUES
    (1, 'https://citigym.com.vn/storage/uploads/atn9629-1.jpg', 'MÁY TẬP NHẬP KHẨU', 'Hệ thống máy tập nhập khẩu từ các thương hiệu nổi tiếng nước ngoài Technogym, LifeFitness, Escape, Reebok, Octane, Les Mills.'),
    (1, 'https://citigym.com.vn/storage/uploads/rin-8058citigym-1.jpg', 'HLV CHUYÊN NGHIỆP', 'Đội ngũ huấn luyện viên chuyên nghiệp, lắng nghe khách hàng, đồng hành cùng hội viên vượt qua những thử thách về hình thể và sức khỏe.'),
    (1, 'https://citigym.com.vn/storage/uploads/he-thong-khoa-tu-2.jpg', 'HỆ THỐNG TỦ VÀ KHÓA', 'Hệ thống khóa từ thông minh bảo mật tối đa Esmart Lock đầu tiên tại Việt Nam. Hội viên có thể yên tâm gửi đồ và khóa tủ bằng vòng tay locker tự động'),
    (1, 'https://citigym.com.vn/storage/uploads/khu-vuc-functional-2.jpg', 'PHÒNG TẬP ĐA NĂNG', 'Khu vực Functional đa dạng các loại máy tập Technogym, tích hợp các kênh truyền hình giải trí, kết nối Wifi và Bluetooth, sở hữu Cloud lưu trữ tiến trình tập luyện.'),
    (1, 'https://citigym.com.vn/storage/uploads/quay-giai-khat-2.jpg', 'TIỆN NGHI PHÒNG TẬP', 'Được trang bị đầy đủ tiện nghi 5 sao (quầy giải khát, phòng xông hơi, khu vực trang điểm, phòng tắm), là nơi mang đến giây phút thư giãn cho bạn.'),
    (1, 'https://citigym.com.vn/storage/uploads/chi-2000-ty-dau-tu-cho-cau-lac-bo-gym-cao-cap-voi-quy-mo-hon-6000m2-tai-tphcm-cau-lac-bo-1-1561539908-613-width600height400.jpg', 'VƯỜN YOGA THƯ THÁI', 'Lớp học yoga buổi sáng được tổ chức ngoài trời để hội viên được hít thở không khí trong lành và tươi mát của cây xanh, mang lại tinh thần thư thái cho hội viên.'),


    (2, 'https://citigym.com.vn/storage/uploads/atn9629-1.jpg', 'MÁY TẬP NHẬP KHẨU', 'Hệ thống máy tập nhập khẩu từ các thương hiệu nổi tiếng nước ngoài Technogym, LifeFitness, Escape, Reebok, Octane, Les Mills.'),
    (2, 'https://citigym.com.vn/storage/uploads/rin-8058citigym-1.jpg', 'HLV CHUYÊN NGHIỆP', 'Đội ngũ huấn luyện viên chuyên nghiệp, lắng nghe khách hàng, đồng hành cùng hội viên vượt qua những thử thách về hình thể và sức khỏe.'),
    (2, 'https://citigym.com.vn/storage/uploads/he-thong-khoa-tu-2.jpg', 'HỆ THỐNG TỦ VÀ KHÓA', 'Hệ thống khóa từ thông minh bảo mật tối đa Esmart Lock đầu tiên tại Việt Nam. Hội viên có thể yên tâm gửi đồ và khóa tủ bằng vòng tay locker tự động'),
    (2, 'https://citigym.com.vn/storage/uploads/khu-vuc-functional-2.jpg', 'PHÒNG TẬP ĐA NĂNG', 'Khu vực Functional đa dạng các loại máy tập Technogym, tích hợp các kênh truyền hình giải trí, kết nối Wifi và Bluetooth, sở hữu Cloud lưu trữ tiến trình tập luyện.'),

    (3, 'https://citigym.com.vn/storage/uploads/atn9629-1.jpg', 'MÁY TẬP NHẬP KHẨU', 'Hệ thống máy tập nhập khẩu từ các thương hiệu nổi tiếng nước ngoài Technogym, LifeFitness, Escape, Reebok, Octane, Les Mills.'),
    (3, 'https://citigym.com.vn/storage/uploads/rin-8058citigym-1.jpg', 'HLV CHUYÊN NGHIỆP', 'Đội ngũ huấn luyện viên chuyên nghiệp, lắng nghe khách hàng, đồng hành cùng hội viên vượt qua những thử thách về hình thể và sức khỏe.'),
    (3, 'https://citigym.com.vn/storage/uploads/he-thong-khoa-tu-2.jpg', 'HỆ THỐNG TỦ VÀ KHÓA', 'Hệ thống khóa từ thông minh bảo mật tối đa Esmart Lock đầu tiên tại Việt Nam. Hội viên có thể yên tâm gửi đồ và khóa tủ bằng vòng tay locker tự động'),
    (3, 'https://citigym.com.vn/storage/uploads/khu-vuc-functional-2.jpg', 'PHÒNG TẬP ĐA NĂNG', 'Khu vực Functional đa dạng các loại máy tập Technogym, tích hợp các kênh truyền hình giải trí, kết nối Wifi và Bluetooth, sở hữu Cloud lưu trữ tiến trình tập luyện.'),
    (3, 'https://citigym.com.vn/storage/uploads/quay-giai-khat-2.jpg', 'TIỆN NGHI PHÒNG TẬP', 'Được trang bị đầy đủ tiện nghi 5 sao (quầy giải khát, phòng xông hơi, khu vực trang điểm, phòng tắm), là nơi mang đến giây phút thư giãn cho bạn.'),

    (4, 'https://citigym.com.vn/storage/uploads/khu-vuc-functional-2.jpg', 'PHÒNG TẬP ĐA NĂNG', 'Khu vực Functional đa dạng các loại máy tập Technogym, tích hợp các kênh truyền hình giải trí, kết nối Wifi và Bluetooth, sở hữu Cloud lưu trữ tiến trình tập luyện.'),
    (4, 'https://citigym.com.vn/storage/uploads/quay-giai-khat-2.jpg', 'TIỆN NGHI PHÒNG TẬP', 'Được trang bị đầy đủ tiện nghi 5 sao (quầy giải khát, phòng xông hơi, khu vực trang điểm, phòng tắm), là nơi mang đến giây phút thư giãn cho bạn.'),
    (4, 'https://citigym.com.vn/storage/uploads/chi-2000-ty-dau-tu-cho-cau-lac-bo-gym-cao-cap-voi-quy-mo-hon-6000m2-tai-tphcm-cau-lac-bo-1-1561539908-613-width600height400.jpg', 'VƯỜN YOGA THƯ THÁI', 'Lớp học yoga buổi sáng được tổ chức ngoài trời để hội viên được hít thở không khí trong lành và tươi mát của cây xanh, mang lại tinh thần thư thái cho hội viên.'),

    (5, 'https://citigym.com.vn/storage/uploads/rin-8058citigym-1.jpg', 'HLV CHUYÊN NGHIỆP', 'Đội ngũ huấn luyện viên chuyên nghiệp, lắng nghe khách hàng, đồng hành cùng hội viên vượt qua những thử thách về hình thể và sức khỏe.'),
    (5, 'https://citigym.com.vn/storage/uploads/he-thong-khoa-tu-2.jpg', 'HỆ THỐNG TỦ VÀ KHÓA', 'Hệ thống khóa từ thông minh bảo mật tối đa Esmart Lock đầu tiên tại Việt Nam. Hội viên có thể yên tâm gửi đồ và khóa tủ bằng vòng tay locker tự động');


-- INSERT INTO brand_albums (brand_id, photo_url, description)
-- VALUES
--     (1, 'https://johnsonfitness.com.vn/wp-content/uploads/2023/04/z4302803743176_ddc1709663091cae6578d949562e2255-scaled.jpg', 'Desc1'),
--     (1, 'https://top1danhgia.com/uploads/2022/phong-gym-quan-tay-ho-Kickfit-Sports.jpg', 'Desc1'),
--     (1, 'https://kickfit-sports.com/wp-content/uploads/2022/07/hinh-anh-cua-khu-vuc-tap-kickfit-boxing-kickboxing-muay-thai.jpg.webp', 'Desc1'),
--     (2, 'https://cdn-ecommerce.estore.net.vn/img/s768x0/20230323_uu-dai-soc-giam-25--tai-elite-fitness-13_14032023103300.jpg?sign=C7n8QMtG29R0j1O4oEAA5w', 'Desc2'),
--     (2, 'https://cms.elitefitness.com.vn/Upload2/20230216/a44a1cbd-7735-449b-b704-5f893b09e03d.jpg?w=865&h=487&mode=max', 'Desc2'),
--     (2, 'https://cms.elitefitness.com.vn/Upload2/20230107/8b4ea796-2f02-434f-b2e6-d12bca7b3b5a.jpg?w=865&h=487&mode=max','Desc2'),
--     (3, 'https://channel.mediacdn.vn/428462621602512896/2023/4/8/photo-4-16809197242851942299068.png',  'Desc3'),
--     (3, 'https://images2.thanhnien.vn/uploaded/linhnt.qc/2021_01_07/citigym/citigym_3_DJUK.jpg?width=500','Desc3'),
--     (3, 'https://images.foody.vn/res/g80/796426/prof/s/sheis-094cf491-cedb-4173-baeb-35dcf77b5daf-20181108093222761.jpg','Desc3'),
--     (4, 'https://aeonmall-binhtan.com.vn/wp-content/uploads/2018/12/yoga-750x468.jpg','Desc4'),
--     (4, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1vHAcDALnte2CfERwNXR_ZfgU2zeqaw0I3w&usqp=CAU', 'Desc4'),
--     (4, 'https://cali.vn/storage/app/media/Editors/627a06aaabda5gia-tap-california-10.png', 'Desc4'),

--     (5, 'https://clbdoanhnhansaigon.vn/dtool/data/computer/web670/viber-image-2020-10-06-09-49-11.jpg','Desc5'),
--     (5, 'https://salt.tikicdn.com/ts/tmp/65/43/b7/461d7536feff1391eeec99b203a16d16.jpg','Desc5'),
--     (5, 'https://salt.tikicdn.com/ts/tmp/65/43/b7/461d7536feff1391eeec99b203a16d16.jpg', 'Desc5');

/********** Gym Department Creation ***********/
-- Create Gym Department Status
INSERT INTO mst_kbn (mst_kbn_name, mst_kbn_key, mst_kbn_value) VALUES ('DEPARTMENT_STATUS', 1, 'Hoạt Động');
INSERT INTO mst_kbn (mst_kbn_name, mst_kbn_key, mst_kbn_value) VALUES ('DEPARTMENT_STATUS', 2, 'Không Hoạt Động');
INSERT INTO mst_kbn (mst_kbn_name, mst_kbn_key, mst_kbn_value) VALUES ('BRAND_STATUS', 1, 'Không Hoạt Động');
INSERT INTO mst_kbn (mst_kbn_name, mst_kbn_key, mst_kbn_value) VALUES ('BRAND_STATUS', 2, 'Không Hoạt Động');


-- Create Gym Department infos
INSERT INTO gym_department (gym_department_id, brand_id,gym_department_status_key, name, address, contact_number,
                            logo_url, wallpaper_url, thumbnail_url,description, latitude, longitude, capacity, area)
VALUES (1,1,1, 'Kickfit Sports', '123 Main St', '555-123-4567', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSFg0vALj7wnJ-W6SbdUr7vhA278f3BPsMnQQ&usqp=CAU',
        '/user-homepage-assets/assets/img/gym/gym_1_10.jpg', '/user-homepage-assets/assets/img/gym/gym_1_10.jpg','Không chỉ là 1 phòng tập gym -
thể hình đơn lẻ, Kickfit Sports là một trung tâm thể dụng thể thao với quy mô lớn
và hiện đại hàng đầu Hà Nội. Các trang thiết bị luyện tập đều được nhập khẩu từ các thương hiệu
lớn tại Châu Âu và Mỹ đem đến trải nghiệm tập luyện tốt nhất. Phòng tập được tích hợp đầy đủ bể bơi
4 mùa, phòng xông hơi, spa, sân tập ngoài trời và trên cao thoáng mát, tủ đồ, khu vực nghỉ ngơi... Ngoài tập gym bạn còn có thể trải nghiệm nhiều loại hình thể thao khác như: Boxing,
Muay Thái', 37.12345678, -122.12345678, 100, 200.50),
       (2,1,1, 'Elite Fitness', '456 Elm St', '555-987-6543',
        'https://example.com/logo.png', '/user-homepage-assets/assets/img/gym/gym_1_5.jpg', '/user-homepage-assets/assets/img/gym/gym_1_5.jpg','Không chỉ là 1 phòng tập gym -
thể hình đơn lẻ, Elite Fitness là một trung tâm thể dụng thể thao với quy mô lớn
và hiện đại hàng đầu Hà Nội. Các trang thiết bị luyện tập đều được nhập khẩu từ các thương hiệu
lớn tại Châu Âu và Mỹ đem đến trải nghiệm tập luyện tốt nhất. Phòng tập được tích hợp đầy đủ bể bơi
4 mùa, phòng xông hơi, spa, sân tập ngoài trời và trên cao thoáng mát, tủ đồ, khu vực nghỉ ngơi... Ngoài tập gym bạn còn có thể trải nghiệm nhiều loại hình thể thao khác như: Boxing,
Muay Thái', 37.98765432, -122.98765432, 150, 300.75),
       (3,2, 1, 'Super Gym Hoa Lac', '123 Main St', '555-123-1234',
        'https://example.com/logo.png','/user-homepage-assets/assets/img/gym/gym_1_2.jpg', '/user-homepage-assets/assets/img/gym/gym_1_2.jpg','Không chỉ là 1 phòng tập gym -
thể hình đơn lẻ, Super Gym Hoa Lac là một trung tâm thể dụng thể thao với quy mô lớn
và hiện đại hàng đầu Hà Nội. Các trang thiết bị luyện tập đều được nhập khẩu từ các thương hiệu
lớn tại Châu Âu và Mỹ đem đến trải nghiệm tập luyện tốt nhất. Phòng tập được tích hợp đầy đủ bể bơi
4 mùa, phòng xông hơi, spa, sân tập ngoài trời và trên cao thoáng mát, tủ đồ, khu vực nghỉ ngơi... Ngoài tập gym bạn còn có thể trải nghiệm nhiều loại hình thể thao khác như: Boxing,
Muay Thái', 20.994853642138313, 105.52473891128982,250, 400),
       (4,3,1, 'Gym Hoa Lac', '124 Main St', '555-123-1235',
        'https://example.com/logo.png','/user-homepage-assets/assets/img/gym/gym_1_3.jpg','/user-homepage-assets/assets/img/gym/gym_1_3.jpg', 'Không chỉ là 1 phòng tập gym -
thể hình đơn lẻ, Gym Hoa Lac là một trung tâm thể dụng thể thao với quy mô lớn
và hiện đại hàng đầu Hà Nội. Các trang thiết bị luyện tập đều được nhập khẩu từ các thương hiệu
lớn tại Châu Âu và Mỹ đem đến trải nghiệm tập luyện tốt nhất. Phòng tập được tích hợp đầy đủ bể bơi
4 mùa, phòng xông hơi, spa, sân tập ngoài trời và trên cao thoáng mát, tủ đồ, khu vực nghỉ ngơi... Ngoài tập gym bạn còn có thể trải nghiệm nhiều loại hình thể thao khác như: Boxing,
Muay Thái', 20.984756591708862, 105.5304037366675, 200, 350.75),
       (5,3,1, 'FitWay Kickboxing', '125 Main St', '555-123-1236',
        'https://example.com/logo.png','/user-homepage-assets/assets/img/gym/gym_1_4.jpg', '/user-homepage-assets/assets/img/gym/gym_1_4.jpg', 'Không chỉ là 1 phòng tập gym -
thể hình đơn lẻ, FitWay Kickboxing là một trung tâm thể dụng thể thao với quy mô lớn
và hiện đại hàng đầu Hà Nội. Các trang thiết bị luyện tập đều được nhập khẩu từ các thương hiệu
lớn tại Châu Âu và Mỹ đem đến trải nghiệm tập luyện tốt nhất. Phòng tập được tích hợp đầy đủ bể bơi
4 mùa, phòng xông hơi, spa, sân tập ngoài trời và trên cao thoáng mát, tủ đồ, khu vực nghỉ ngơi... Ngoài tập gym bạn còn có thể trải nghiệm nhiều loại hình thể thao khác như: Boxing,
Muay Thái', 21.013202882232747, 105.5189882552246,100, 240.25);



INSERT INTO features (feature_icon, feature_name, feature_status)
VALUES
    ('<i class="bi bi-p-circle"></i>', 'Bãi Đỗ Xe', 1),
    ('<i class="fa-solid fa-shower"></i>', 'Phòng Tắm Nóng Lạnh', 1),
    ('<i class="fa-solid fa-wifi"></i>', 'WIFI', 1),
    ('<i class="fas fa-scroll"></i>', 'Khăn Miễn Phí', 1),
    ('<i class="fa-solid fa-couch"></i>', 'Khu Nghỉ Ngơi', 1),
    ('<i class="fa-solid fa-martini-glass-citrus"></i>', 'Quầy Nước', 1),
    ('<i class="fa-solid fa-suitcase-medical"></i>', 'Phòng Y Tế', 1);

-- Create Gym Department Features
INSERT INTO gym_department_features (feature_id, gym_department_id, feature_status)
VALUES
    (1, 1, 1),
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
    (7, 3, 1);

-- Create Gym Department Gallery
INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES(1, 'https://i.pinimg.com/236x/6c/c4/49/6cc4498dfac9d232b9c49f46d1948f8b.jpg', 'Album 1'),
      (1, 'https://i.pinimg.com/236x/62/ce/d1/62ced13c5fd204f575f47d4b026243dd.jpg', 'Album 2'),
      (2, 'https://i.pinimg.com/236x/6c/c4/49/6cc4498dfac9d232b9c49f46d1948f8b.jpg', 'Album 1'),
      (2, 'https://i.pinimg.com/236x/62/ce/d1/62ced13c5fd204f575f47d4b026243dd.jpg', 'Album 2'),
      (3, 'https://i.pinimg.com/236x/6c/c4/49/6cc4498dfac9d232b9c49f46d1948f8b.jpg', 'Album 1'),
      (3, 'https://i.pinimg.com/236x/62/ce/d1/62ced13c5fd204f575f47d4b026243dd.jpg', 'Album 2'),
      (4, 'https://i.pinimg.com/236x/6c/c4/49/6cc4498dfac9d232b9c49f46d1948f8b.jpg', 'Album 1'),
      (4, 'https://i.pinimg.com/236x/62/ce/d1/62ced13c5fd204f575f47d4b026243dd.jpg', 'Album 2'),
      (5, 'https://i.pinimg.com/236x/6c/c4/49/6cc4498dfac9d232b9c49f46d1948f8b.jpg', 'Album 1'),
      (5, 'https://i.pinimg.com/236x/62/ce/d1/62ced13c5fd204f575f47d4b026243dd.jpg', 'Album 2');

-- Create Gym Department Schedule
INSERT INTO gym_department_schedule (gym_department_id, day, open_time, close_time)
VALUES (1, 'Monday', '8:00 AM', '10:00 PM'),
       (2, 'Tuesday', '9:00 AM', '11:00 PM');

/********** Department Feedback Creation ***********/
INSERT INTO user_feedback
(user_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES
    -- 2 records with department_id = 1
    (1, 1, 3, 'Equipment needs updating', NOW(), 1),
    (1, 1, 2, 'Floor was dirty', NOW(), 1),
    -- 2 records with department_id = 2
    (1, 2, 5, 'Great service', NOW(), 1),
    (1, 2, 4, 'Staff was helpful', NOW(), 1),
    -- 2 records with department_id = 3
    (1, 3, 5, 'Loved the environment', NOW(), 1),
    (1, 3, 4, 'Instructors were knowledgeable', NOW(), 1),
    -- 2 records with department_id = 4
    (1, 1, 5, 'Great service!', '2021-10-15 09:30:00', 1),
    (1, 1, 3, 'Could be better.', '2021-10-16 14:45:00', 1);

/********** Department Gym Plan Creation ***********/
-- Create Gym plan status
INSERT INTO mst_kbn (mst_kbn_name, mst_kbn_key, mst_kbn_value)
VALUES ('Gym Plan Type', 1, 'Gói theo giờ'),
       ('Gym Plan Type', 2, 'Gói không theo giờ'),
       ('ITEM_STATUS', 0, 'Chưa kích hoạt'),
       ('ITEM_STATUS', 1, 'Đang sử dụng'),
       ('ITEM_STATUS', 2, 'Đã sử dụng'),
       ('ITEM_STATUS', 3, 'Quá hạn');

-- Create Gym plan infos
INSERT INTO gym_plan (gym_department_id, user_id, gym_plan_key, gym_plan_status_key, gym_plan_type_key, name, description, price, price_per_hours, plan_sold,
                      duration, plan_before_active_validity, plan_after_active_validity)
VALUES ( 1,1, 2, 2, 2, 'Gói 2', 'Gói không theo giờ ', 150.00, 15.00, 30, 5, 10, 20),
       ( 1,1, 1, 1, 1, 'Gói giờ', 'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp. ', 100.00, 10.00, 20, 3, 7, 14),
       (1,1, 2, 2, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính tại Gym Hòa Lạc. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ', 150.00, 15.00, 30, 5, 10, 20),

       (2,1, 2, 2, 2, 'Gói 2', 'Gói không theo giờ ', 150.00, 15.00, 30, 5, 10, 20),
       (2,1, 1, 1, 1, 'Gói giờ', 'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp. ', 100.00, 10.00, 20, 3, 7, 14),
       ( 2,1, 2, 2, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính tại Gym Hòa Lạc. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ', 150.00, 15.00, 30, 5, 10, 20),

       ( 3,1, 2, 2, 2, 'Gói 2', 'Gói không theo giờ ', 150.00, 15.00, 30, 5, 10, 20),
       ( 3,1, 1, 1, 1, 'Gói giờ', 'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp. ', 100.00, 10.00, 20, 3, 7, 14),
       ( 3,1, 2, 2, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính tại Gym Hòa Lạc. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ', 150.00, 15.00, 30, 5, 10, 20),

       ( 4,1, 2, 2, 2, 'Gói 2', 'Gói không theo giờ ', 150.00, 15.00, 30, 5, 10, 20),
       ( 4,1, 1, 1, 1, 'Gói giờ', 'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp. ', 100.00, 10.00, 20, 3, 7, 14),
       (4,1, 2, 2, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính tại Gym Hòa Lạc. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ', 150.00, 15.00, 30, 5, 10, 20),

       ( 5,1, 2, 2, 2, 'Gói 2', 'Gói không theo giờ ', 150.00, 15.00, 30, 5, 10, 20),
       ( 5,1, 1, 1, 1, 'Gói giờ', 'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp. ', 100.00, 10.00, 20, 3, 7, 14),
       (5,1, 2, 2, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính tại Gym Hòa Lạc. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ', 150.00, 15.00, 30, 5, 10, 20);