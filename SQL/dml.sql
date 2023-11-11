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
    ( 5,1, 'Citi Gym',
      'https://bom.so/T5HfJy',
      'https://bom.so/tr8QwM',
      'https://bom.so/ph2Fq2',
      'Thành lập từ 2018, Citigym đã phục vụ hơn 100.000 khách hàng. Với hệ thống phòng tập đầy đủ tiện nghi và nhiều chi nhánh, vận hành các khu phức hợp thể thao.

  Citigym mang đến cho học viên hơn 45 bộ môn tập luyện như Gym,Yoga, các lớp tập nhóm Lesmill bản quyền Quốc tế cùng huấn luyện viên hướng dẫn tận tình,…

  Ngoài ra, nếu sở hữu thẻ tập hội viên, bạn được quyền đến bất kỳ phòng tập tại TPHCM của thương hiệu để luyện tập thể thao và chăm sóc sức khỏe.',
      4, '1900 633 638', 'marketing@citigym.com.vn', 1),

    ( 6,1, 'California Fitness & Yoga',
      'https://bom.so/6MN7vp',
      'https://bom.so/IuUyjS',
      'https://bom.so/bGRVgN',
      'Là thương hiệu sức khỏe lớn nhất tại Việt Nam. Đã sở hữu hơn 35 câu lạc bộ khắp cả nước. Đến với California, bạn sẽ được tận hưởng môi trường luyện tập đẳng cấp với trang thiết bị tốt nhất.

  Ngoài gym, tại phòng tập California, bạn sẽ được thỏa sức “phiêu” Pop dance, Sexy dance, Pole dance,…hoặc thực hành chuẩn xác yoga dưới sự hướng dẫn của thầy yoga đến từ Ấn Độ.',
      4.5, '(028) 7308 2277', 'cali@gmail.com', 1),


    ( 7,1, 'Fit 24 – Fitness & Yoga Center',
      'https://bom.so/gaVGez',
      'https://bom.so/68NAw5',
      'https://bom.so/TpJFtS',
      'Bạn sẽ được trải nghiệm đa dạng các lớp từ Dance cho đến Yoga, được hướng dẫn bài bản để học viên đạt được mục tiêu trong thời gian ngắn nhất.

  Trong đó, Yoga sẽ được hướng dẫn bởi master Ấn Độ và Việt Nam giàu kinh nghiệm, tận tâm, gym cùng các chuyên gia hàng đầu. FIT24 tổ chức cả lớp nhảy dành cho các bạn nhỏ.',
      4.2,'1900 2624', 'fit24@gmail.com', 1);


INSERT INTO brand_amenities (brand_id, photo_url, amenitie_name, description, amenitie_status)
VALUES
    (5, 'https://citigym.com.vn/storage/uploads/atn9629-1.jpg', 'MÁY TẬP NHẬP KHẨU', 'Hệ thống máy tập nhập khẩu từ các thương hiệu nổi tiếng nước ngoài Technogym, LifeFitness, Escape, Reebok, Octane, Les Mills.',1),
    (5, 'https://citigym.com.vn/storage/uploads/rin-8058citigym-1.jpg', 'HLV CHUYÊN NGHIỆP', 'Đội ngũ huấn luyện viên chuyên nghiệp, lắng nghe khách hàng, đồng hành cùng hội viên vượt qua những thử thách về hình thể và sức khỏe.',1),
    (5, 'https://citigym.com.vn/storage/uploads/he-thong-khoa-tu-2.jpg', 'HỆ THỐNG TỦ VÀ KHÓA', 'Hệ thống khóa từ thông minh bảo mật tối đa Esmart Lock đầu tiên tại Việt Nam. Hội viên có thể yên tâm gửi đồ và khóa tủ bằng vòng tay locker tự động',1),
    (5, 'https://citigym.com.vn/storage/uploads/khu-vuc-functional-2.jpg', 'PHÒNG TẬP ĐA NĂNG', 'Khu vực Functional đa dạng các loại máy tập Technogym, tích hợp các kênh truyền hình giải trí, kết nối Wifi và Bluetooth, sở hữu Cloud lưu trữ tiến trình tập luyện.',1),
    (5, 'https://citigym.com.vn/storage/uploads/quay-giai-khat-2.jpg', 'TIỆN NGHI PHÒNG TẬP', 'Được trang bị đầy đủ tiện nghi 5 sao (quầy giải khát, phòng xông hơi, khu vực trang điểm, phòng tắm), là nơi mang đến giây phút thư giãn cho bạn.',1),
    (5, 'https://citigym.com.vn/storage/uploads/chi-2000-ty-dau-tu-cho-cau-lac-bo-gym-cao-cap-voi-quy-mo-hon-6000m2-tai-tphcm-cau-lac-bo-1-1561539908-613-width600height400.jpg', 'VƯỜN YOGA THƯ THÁI', 'Lớp học yoga buổi sáng được tổ chức ngoài trời để hội viên được hít thở không khí trong lành và tươi mát của cây xanh, mang lại tinh thần thư thái cho hội viên.',1),

    (6, 'https://bom.so/jvQH13', 'PHÒNG TẬP THỂ DỤC', 'Cung cấp các thiết bị hiện đại để tập luyện cardio và tăng cường cơ bắp. Phòng tập chuyên biệt cho các hoạt động như tập TRX, tập luyện chịu lực, và các lớp tập luyện nhóm.',1),
    (6, 'https://bom.so/cwFZ2a', 'LỚP HỌP YOGA', 'Cung cấp nhiều loại hình yoga như Hatha, Vinyasa, Ashtanga, và Yoga dành cho người mới bắt đầu. Lớp yoga đặc biệt như Hot Yoga (yoga trong phòng nhiệt độ cao) hoặc Aerial Yoga (yoga trên dây đu).',1),
    (6, 'https://bom.so/t0MwlU', 'LỚP HỌC NHÓM', 'Các buổi tập luyện nhóm như Zumba, Pilates, và các loại lớp tập khác.Các buổi hướng dẫn từ giáo viên chuyên nghiệp..',1),
    (6, 'https://bom.so/tGqbD2', 'HƯỚNG DẪN CÁ NHÂN', 'Cung cấp dịch vụ hướng dẫn tập luyện cá nhân để cá nhân hóa chương trình tập luyện.',1),
    (6, 'https://bom.so/g9lwgO', 'KHU VỰC XÔNG HƠI VÀ SPA', 'Các tiện nghi như phòng xông hơi, sauna, và các liệu pháp spa để thư giãn và tái tạo năng lượng.',1),
    (6, 'https://bom.so/rNl08Q', 'TRUNG TÂM DINH DƯỠNG', 'Cung cấp tư vấn dinh dưỡng và chế độ ăn lành mạnh để hỗ trợ mục tiêu tập luyện của khách hàng.',1),

    (7, 'https://bom.so/aOajxQ', 'GROUPX-DANCE', 'Các bộ môn luyện tập theo nhóm như Zumba, BodyCombat, Body pump, B&T… trên nền nhạc sôi động sẽ mang cho bạn những sẽ mang đến cho bạn những buổi tập năng lượng và vui tươi.',1),
    (7, 'https://bom.so/7J8yoG', 'CÔNG NGHỆ EMS', 'Máy EMS tại FIT24 là dòng máy tập luyện bằng công nghệ hiện đại đến từ Đức, đây là một phương pháp tập luyện an toàn, hiệu quả trong việc tăng cơ giảm mỡ, cải thiện vóc dáng và sức khỏe nhanh chóng cho người bận rộn.',1),
    (7, 'https://bom.so/3WBQnI', 'PERSONAL TRAINER', 'Tập luyện với Huấn luyện viên cá nhân theo hình thức 1 kèm 1, được thiết kế đặc biệt phù hợp với thể trạng và mục tiêu thể hình mà bạn mong muốn.',1),
    (7, 'https://bom.so/hMeMA9', 'LESMILLS', 'Lesmill là các bộ môn tập luyện có bản quyền và vô cùng hiệu quả tại FIT24. Với mục đích cuối cùng: giúp người tập khỏe mạnh, vui vẻ, sảng khoái; giải phóng năng lượng, giảm mỡ & thân hình thon gọn, cân đối.',1),
    (7, 'https://bom.so/5tgS91', 'CĂNG CƠ – TRỊ LIỆU', 'FIT24 với các Master chuyên môn cao không những giúp bạn phục hồi cơ thể sau những giờ tập luyện mà còn hỗ trợ trị liệu về các vấn đề xương khớp mà bạn mắc phải.',1);


/********** Gym Department Creation ***********/
-- Create Gym Department Status
INSERT INTO mst_kbn (mst_kbn_name, mst_kbn_key, mst_kbn_value) VALUES ('DEPARTMENT_STATUS', 1, 'Hoạt Động');
INSERT INTO mst_kbn (mst_kbn_name, mst_kbn_key, mst_kbn_value) VALUES ('DEPARTMENT_STATUS', 2, 'Không Hoạt Động');
INSERT INTO mst_kbn (mst_kbn_name, mst_kbn_key, mst_kbn_value) VALUES ('BRAND_STATUS', 1, 'Không Hoạt Động');
INSERT INTO mst_kbn (mst_kbn_name, mst_kbn_key, mst_kbn_value) VALUES ('BRAND_STATUS', 2, 'Không Hoạt Động');


-- Create Gym Department infos
INSERT INTO gym_department (gym_department_id, brand_id,gym_department_status_key, name, address, contact_number,
                            logo_url, wallpaper_url, thumbnail_url,description, latitude, longitude, capacity, area)
VALUES
    (1,5,1, 'CITIGYM THÀNH THÁI', ' 52 Thành Thái, Phường 12, Quận 10, Thành phố Hồ Chí Minh', '1900633638',
     'https://bom.so/T5HfJy',
     'https://bom.so/i2mp74',
     'https://bom.so/PrIhQF',
     'Phòng gym quận 10 Citigym Thành Thái là phòng tập đẳng cấp được khai trương đầu tiên của hệ thống Citigym tọa lạc tại trung tâm quận 10. Nơi đây có không gian luyện tập mang hơi thở thiên nhiên năng động, thoải mái, đem đến cho bạn cảm giác thật hào hứng, sảng khoái khi tập luyện. Phòng tập của Citigym Thành Thái được trang bị đầy đủ các thiết bị hiện đại, cao cấp từ những thương hiệu hàng đầu thế giới như Technogym, LifeFitness, Escape, Reebok, Octane, Les Mills. Đây cũng là hệ thống phòng tập đầu tiên tại TP HCM sử dụng dụng cụ tạ đòn, bục nhảy SmartBar, SmartStep của LesMills.',
     10.770294326858476, 106.66638328225508, 200, 5500),

    (2,5,1, 'CITIGYM PHỔ QUANG', '119 Phổ Quang, Phường 09, Quận Phú Nhuận, Thành phố Hồ Chí Minh', ' 1900633638',
     'https://bom.so/T5HfJy',
     'https://bom.so/whpmm3',
     'https://bom.so/agm7XG',
     'Phòng tập gym quận Phú Nhuận CITIGYM Phổ Quang với thiết kế lấy cảm hứng thiên nhiên, đưa cây xanh vào phòng tập cho phép hội viên thoải mái tập luyện với diện tích 4000m2. Phòng tập hiện đại mang hơi thở của thiên nhiên giúp hội viên cảm nhận năng lượng nhiệt huyết đồng thời cảm giác thư thái sau ngày dài làm việc. Ngoài ra, khi đến CLB Phổ Quang hội viên còn có cơ hội trải nghiệm hệ thống máy tập và dụng cụ hiện đại bậc nhất, nhập khẩu từ các thương hiệu nước ngoài hàng đầu cũng như chất lượng dịch vụ đẳng cấp 5 sao.',
     10.809065977594821, 106.67174681287801, 200, 4000),

    (3,5,1, 'CITIGYM BẾN VÂN ĐỒN', '34-35 Bến Vân Đồn, Phường 12, Quận 4, Thành phố Hồ Chí Minh', ' 1900633638',
     'https://bom.so/T5HfJy',
     'https://bom.so/XDQUdd',
     'https://bom.so/jtjjOf',
     'Phòng gym quận 4 CITIGYM Bến Vân Đồn tọa lạc tại vị trí đắc địa tại mặt tiền Bến Vân Đồn, cách quận 1 chỉ vài phút đi bộ, hướng nhìn ra sông Sài Gòn. Đến ngay CLB Bến Vân Đồn để trải nghiệm không gian tập luyện sang trọng và rộng rãi có một không hai với tầm nhìn thành phố 2 mặt tiền, thỏa thích tập các bộ môn nhóm đông người. Không chỉ là cơ sở hạ tầng, CLB Bến Vân Đồn còn được biết đến với hệ thống máy tập và dụng cụ hiện đại bậc nhất, nhập khẩu từ các thương hiệu nổi tiếng nước ngoài.',
     10.767283248645954, 106.70370099574525, 200, 5000),

    (4,5,1, 'CITIGYM VẠN HẠNH MALL', 'Lầu 7 TTTM Vạn Hạnh Mall, Phường 12, Quận 10, Thành phố Hồ Chí Minh', '1900633638',
     'https://bom.so/T5HfJy',
     'https://bom.so/wEoPa9',
     'https://bom.so/Nmuqc0',
     'CITIGYM Vạn Hạnh Mall là phòng tập lớn nhất Việt Nam nằm trong trung tâm thương mại với diện tích 6000m2, tọa lạc tại trung tâm quận 10. Phòng tập gym quận 10 được thiết kế như thành phố New York sôi động, tại đây hội viên sẽ có trải nghiệm thú vị vừa tập luyện vừa ngắm nhìn thành phố từ trên cao. Ngoài ra, câu lạc bộ còn được trang bị hệ thống máy tập hiện đại nhất và tổ chức đa dạng các lớp gym, yoga, group x, dance...',
     10.770899546871222, 106.66895996876498, 200, 2000),


    (5,6,1, 'CALIFORNIA WEST POINT - QUẬN TÂY HỒ', 'Tầng 2 & 3 , Somerset West Point, Số 2 Tây Hồ, P. Quảng An, Q. Tây Hồ, Hà Nội.', '(024) 7300 1777',
     'https://bom.so/6MN7vp',
     'https://bom.so/bpbfDn',
     'https://bom.so/wEoPa9',
     'Sang trọng, đẳng cấp, tinh tế trong từng chi tiết đó chính là những trải nghiệm tuyệt vời chỉ có khi bạn bước chân vào CLB California Centuryon West Point quận Tây Hồ. Một nơi tập luyện đạt chuẩn quốc tế cùng với các tiện ích phục hồi năng lượng tích hợp, đặc biệt có hồ bơi thư giãn chắc chắn sẽ mang lại cho bạn những trải nghiệm chưa từng có. Khám phá ngay hôm nay!',
     21.066572880558276, 105.8262971113118, 100, 2000),

    (6,6,1, 'CALIFORNIA VINCOM STAR CITY - QUẬN CẦU GIẤY', 'Tầng 3, Trung tâm thương mại Vincom Star City, 119 đường Trần Duy Hưng, P. Trung Hòa, Q. Cầu Giấy, Hà Nội', '(024) 7300 1277',
     'https://bom.so/6MN7vp',
     'https://bom.so/w8xQZa',
     'https://bom.so/YjALju',
     'Sang trọng, đẳng cấp, tinh tế trong từng chi tiết đó chính là những trải nghiệm tuyệt vời chỉ có khi bạn bước chân vào CLB California Centuryon West Point quận Tây Hồ. Một nơi tập luyện đạt chuẩn quốc tế cùng với các tiện ích phục hồi năng lượng tích hợp, đặc biệt có hồ bơi thư giãn chắc chắn sẽ mang lại cho bạn những trải nghiệm chưa từng có. Khám phá ngay hôm nay!',
     21.00592534375174, 105.79509157389299, 100, 2000),

    (7,6,1, 'CALIFORNIA TIMES CITY - QUẬN HAI BÀ TRƯNG', 'Time City Megamall, Tòa nhà T18, 458 Minh Khai, P. Vĩnh Tuy, Q. Hai Bà Trưng, Hà Nội', '(024) 7107 9999',
     'https://bom.so/6MN7vp',
     'https://bom.so/w8xQZa',
     'https://bom.so/YjALju',
     'Vượt qua mọi trở ngại khiến bạn mất động lực khi tập luyện. Trải nghiệm ngay California Fitness & Yoga quận Hai Bà Trưng - cộng đồng yêu thích lối sống năng động và khoẻ mạnh lớn nhất tại Hà Nội. Với trang thiết bị đầy đủ cùng với chương trình tập luyện đa dạng và các tiện ích phục hồi năng lượng tiêu chuẩn 5 sao như hồ bơi trong nhà, bạn sẽ luôn được truyền cảm hứng để duy trì lối sống lành mạnh.',
     21.00592534375174, 105.79509157389299, 100, 2000),

    (8,6,1, 'CALIFORNIA SKY CITY TOWER - QUẬN ĐỐNG ĐA', 'Sky City, Tầng M, 88 Láng Hạ, P.Láng Hạ, Q. Đống Đa, Hà Nội', '(024) 7109 7899',
     'https://bom.so/6MN7vp',
     'https://bom.so/Iv6YYL',
     'https://bom.so/HwuZpU',
     'Không chỉ có lịch học trải đều các khung giờ, Trung tâm California Fitness and Yoga Quận Đống Đa còn sở hữu khu vực Hydro – Therapy hệ thống phòng tắm hơi lớn nhất Việt Nam, cung cấp không gian thư giãn sau tập luyện độc đáo khó nơi nào sánh được.',
     21.012892257325145, 105.81135818247371, 100, 2000),

    (9,7,1, 'FIT24 PHẠM VĂN HAI- Q. TÂN BÌNH', 'Central Plaza, 91 Phạm Văn Hai, Phường 3, Quận Tân Bình, TP. HCM', '028 7307 2424',
     'https://bom.so/gaVGez',
     'https://bom.so/SKbgQF',
     'https://bom.so/JOhWQU',
     'Tọa lạc tại tầng K Tòa nhà Central Plaza, trang thiết bị tập luyện cao cấp cùng đa dạng các môn học và đặc biệt, hồ bơi ngoài trời chính là những điểm cộng tuyệt vời của phòng tập Gym – Yoga cao cấp nhất Quận Tân Bình.',
     10.794118291505661, 106.66311798225539, 70, 1500),

    (10,7,1, 'FIT24 HỒ XUÂN HƯƠNG- QUẬN 3', '02 Hồ Xuân Hương, Phường Võ Thị Sáu, Quận 3, Hồ Chí Minh', '028 7307 2424',
     'https://bom.so/gaVGez',
     'https://bom.so/xzKhs8',
     'https://bom.so/lnVZCn',
     'Tọa lạc tại tầng K Tòa nhà Central Plaza, trang thiết bị tập luyện cao cấp cùng đa dạng các môn học và đặc biệt, hồ bơi ngoài trời chính là những điểm cộng tuyệt vời của phòng tập Gym – Yoga cao cấp nhất Quận Tân Bình.',
     10.77720992036007, 106.68773747611712, 70, 1500),


    (11,7,1, 'FIT24 BA THÁNG HAI – QUẬN 10', 'Lầu 6-7-8 Số 3 Ba Tháng Hai, Phường 11, Quận 10, TP. HCM', '028 7307 2424',
     'https://bom.so/gaVGez',
     'https://bom.so/YVDRpm',
     'https://bom.so/Y3aZNS',
     'Là phòng tập Gym và Yoga LỚN NHẤT – ĐẸP NHẤT với quy mô 3 tầng và diện tích trên 2,000m2, Fit24 Ba Tháng Hai được thiết kế sang trọng và ấn tượng theo phong cách Châu Âu hiện đại và là chi nhánh mới nhất của Hệ thống Fit24.',
     10.777031921131604, 106.68105969760164, 70, 2000);


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
VALUES
    (1, 'https://bom.so/i2mp74', 'Album 1'),
    (1, 'https://bom.so/i2mp74', 'Album 1'),
    (1, 'https://bom.so/IkKUPw', 'Album 1'),
    (1, 'https://bom.so/zU3jbE', 'Album 1'),
    (1, 'https://bom.so/e3ZzB2', 'Album 1'),

    (2, 'https://bom.so/wiMnZY', 'Album 1'),
    (2, 'https://bom.so/8nyE9i', 'Album 1'),
    (2, 'https://bom.so/feNtwJ', 'Album 1'),
    (2, 'https://bom.so/g5jRnI', 'Album 1'),
    (2, 'https://bom.so/g5jRnI', 'Album 1'),
    (2, 'https://bom.so/oR4B8d', 'Album 1'),


    (3, 'https://bom.so/wiMnZY', 'Album 1'),
    (3, 'https://bom.so/8nyE9i', 'Album 1'),
    (3, 'https://bom.so/feNtwJ', 'Album 1'),
    (3, 'https://bom.so/g5jRnI', 'Album 1'),
    (3, 'https://bom.so/g5jRnI', 'Album 1'),
    (3, 'https://bom.so/oR4B8d', 'Album 1'),

    (4, 'https://bom.so/i2mp74', 'Album 1'),
    (4, 'https://bom.so/i2mp74', 'Album 1'),
    (4, 'https://bom.so/IkKUPw', 'Album 1'),
    (4, 'https://bom.so/zU3jbE', 'Album 1'),
    (4, 'https://bom.so/e3ZzB2', 'Album 1'),


    (5, 'https://bom.so/70zX1i', 'Album 1'),
    (5, 'https://bom.so/M4RSmu', 'Album 1'),
    (5, 'https://bom.so/M4RSmu', 'Album 1'),
    (5, 'https://bom.so/7RWMbJ', 'Album 1'),
    (5, 'https://bom.so/QymJDd', 'Album 1'),
    (5, 'https://bom.so/JEMnzf', 'Album 1'),
    (5, 'https://bom.so/hrrAnR', 'Album 1'),

    (6, 'https://bom.so/70zX1i', 'Album 1'),
    (6, 'https://bom.so/M4RSmu', 'Album 1'),
    (6, 'https://bom.so/M4RSmu', 'Album 1'),
    (6, 'https://bom.so/7RWMbJ', 'Album 1'),
    (6, 'https://bom.so/QymJDd', 'Album 1'),
    (6, 'https://bom.so/JEMnzf', 'Album 1'),
    (6, 'https://bom.so/hrrAnR', 'Album 1'),

    (7, 'https://bom.so/70zX1i', 'Album 1'),
    (7, 'https://bom.so/M4RSmu', 'Album 1'),
    (7, 'https://bom.so/M4RSmu', 'Album 1'),
    (7, 'https://bom.so/7RWMbJ', 'Album 1'),
    (7, 'https://bom.so/QymJDd', 'Album 1'),
    (7, 'https://bom.so/JEMnzf', 'Album 1'),
    (7, 'https://bom.so/hrrAnR', 'Album 1'),

    (8, 'https://bom.so/70zX1i', 'Album 1'),
    (8, 'https://bom.so/M4RSmu', 'Album 1'),
    (8, 'https://bom.so/M4RSmu', 'Album 1'),
    (8, 'https://bom.so/7RWMbJ', 'Album 1'),
    (8, 'https://bom.so/QymJDd', 'Album 1'),
    (8, 'https://bom.so/JEMnzf', 'Album 1'),
    (8, 'https://bom.so/hrrAnR', 'Album 1'),

    (9, 'https://bom.so/DbDzIX', 'Album 1'),
    (9, 'https://bom.so/3QnLs7', 'Album 1'),
    (9, 'https://bom.so/lnVZCn', 'Album 1'),
    (9, 'https://bom.so/rii7bT', 'Album 1'),
    (9, 'https://bom.so/exmWmn', 'Album 1'),
    (9, 'https://bom.so/PjgFM5', 'Album 1'),


    (10, 'https://bom.so/DbDzIX', 'Album 1'),
    (10, 'https://bom.so/3QnLs7', 'Album 1'),
    (10, 'https://bom.so/lnVZCn', 'Album 1'),
    (10, 'https://bom.so/rii7bT', 'Album 1'),
    (10, 'https://bom.so/exmWmn', 'Album 1'),
    (10, 'https://bom.so/PjgFM5', 'Album 1'),

    (11, 'https://bom.so/DbDzIX', 'Album 1'),
    (11, 'https://bom.so/3QnLs7', 'Album 1'),
    (11, 'https://bom.so/lnVZCn', 'Album 1'),
    (11, 'https://bom.so/rii7bT', 'Album 1'),
    (11, 'https://bom.so/exmWmn', 'Album 1'),
    (11, 'https://bom.so/PjgFM5', 'Album 1');


-- Create Gym Department Schedule
INSERT INTO gym_department_schedule (gym_department_id, day, open_time, close_time)
VALUES (1, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
       (1, 'Thứ Ba', '9:00 AM', '11:00 PM'),
       (1, 'Thứ Tư', '9:00 AM', '11:00 PM'),
       (1, 'Thứ Năm', '9:00 AM', '11:00 PM'),
       (1, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
       (1, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
       (1, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (2, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
       (2, 'Thứ Ba', '9:00 AM', '11:00 PM'),
       (2, 'Thứ Tư', '9:00 AM', '11:00 PM'),
       (2, 'Thứ Năm', '9:00 AM', '11:00 PM'),
       (2, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
       (2, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
       (2, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (3, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
       (3, 'Thứ Ba', '9:00 AM', '11:00 PM'),
       (3, 'Thứ Tư', '9:00 AM', '11:00 PM'),
       (3, 'Thứ Năm', '9:00 AM', '11:00 PM'),
       (3, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
       (3, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
       (3, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (4, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
       (4, 'Thứ Ba', '9:00 AM', '11:00 PM'),
       (4, 'Thứ Tư', '9:00 AM', '11:00 PM'),
       (4, 'Thứ Năm', '9:00 AM', '11:00 PM'),
       (4, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
       (4, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
       (4, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (5, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
       (5, 'Thứ Ba', '9:00 AM', '11:00 PM'),
       (5, 'Thứ Tư', '9:00 AM', '11:00 PM'),
       (5, 'Thứ Năm', '9:00 AM', '11:00 PM'),
       (5, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
       (5, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
       (5, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (6, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
       (6, 'Thứ Ba', '9:00 AM', '11:00 PM'),
       (6, 'Thứ Tư', '9:00 AM', '11:00 PM'),
       (6, 'Thứ Năm', '9:00 AM', '11:00 PM'),
       (6, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
       (6, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
       (6, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (7, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
       (7, 'Thứ Ba', '9:00 AM', '11:00 PM'),
       (7, 'Thứ Tư', '9:00 AM', '11:00 PM'),
       (7, 'Thứ Năm', '9:00 AM', '11:00 PM'),
       (7, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
       (7, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
       (7, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (8, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
       (8, 'Thứ Ba', '9:00 AM', '11:00 PM'),
       (8, 'Thứ Tư', '9:00 AM', '11:00 PM'),
       (8, 'Thứ Năm', '9:00 AM', '11:00 PM'),
       (8, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
       (8, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
       (8, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (9, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
       (9, 'Thứ Ba', '9:00 AM', '11:00 PM'),
       (9, 'Thứ Tư', '9:00 AM', '11:00 PM'),
       (9, 'Thứ Năm', '9:00 AM', '11:00 PM'),
       (9, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
       (9, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
       (9, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (10, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
       (10, 'Thứ Ba', '9:00 AM', '11:00 PM'),
       (10, 'Thứ Tư', '9:00 AM', '11:00 PM'),
       (10, 'Thứ Năm', '9:00 AM', '11:00 PM'),
       (10, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
       (10, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
       (10, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

       (11, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
       (11, 'Thứ Ba', '9:00 AM', '11:00 PM'),
       (11, 'Thứ Tư', '9:00 AM', '11:00 PM'),
       (11, 'Thứ Năm', '9:00 AM', '11:00 PM'),
       (11, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
       (11, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
       (11, 'Chủ Nhật', '9:00 AM', '11:00 PM');

/********** Department Feedback Creation ***********/
INSERT INTO user_feedback
(user_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES
    (1, 1, 4, 'Phòng tập thể dục rất sạch sẽ và thoải mái.', NOW(),1 ),
    (1, 2, 4, 'Phòng tập thể dục rất sạch sẽ và thoải mái.', NOW(),1 ),
    (1, 3, 4, 'Phòng tập thể dục rất sạch sẽ và thoải mái.', NOW(),1 ),
    (1, 4, 4, 'Phòng tập thể dục rất sạch sẽ và thoải mái.', NOW(),1 ),
    (1, 5, 4, 'Phòng tập thể dục rất sạch sẽ và thoải mái.', NOW(),1 ),
    (1, 6, 4, 'Phòng tập thể dục rất sạch sẽ và thoải mái.', NOW(),1 ),
    (1, 7, 4, 'Phòng tập thể dục rất sạch sẽ và thoải mái.', NOW(),1 ),
    (1, 8, 4, 'Phòng tập thể dục rất sạch sẽ và thoải mái.', NOW(),1 ),
    (1, 9, 4, 'Phòng tập thể dục rất sạch sẽ và thoải mái.', NOW(),1 ),
    (1, 10, 4, 'Phòng tập thể dục rất sạch sẽ và thoải mái.', NOW(),1 ),
    (1, 11, 4, 'Phòng tập thể dục rất sạch sẽ và thoải mái.', NOW(),1 ),



    (2, 1, 3, 'Nhân viên phục vụ tận tình.', NOW(), 1),
    (2, 2, 3, 'Nhân viên phục vụ tận tình.', NOW(), 1),
    (2, 3, 3, 'Nhân viên phục vụ tận tình.', NOW(), 1),
    (2, 4, 3, 'Nhân viên phục vụ tận tình.', NOW(), 1),
    (2, 5, 3, 'Nhân viên phục vụ tận tình.', NOW(), 1),
    (2, 6, 3, 'Nhân viên phục vụ tận tình.', NOW(), 1),
    (2, 7, 3, 'Nhân viên phục vụ tận tình.', NOW(), 1),
    (2, 8, 3, 'Nhân viên phục vụ tận tình.', NOW(), 1),
    (2, 9, 3, 'Nhân viên phục vụ tận tình.', NOW(), 1),
    (2, 10, 3, 'Nhân viên phục vụ tận tình.', NOW(), 1),
    (2, 11, 3, 'Nhân viên phục vụ tận tình.', NOW(), 1),


    (3, 1, 5, 'Thích không khí ở phòng tập.', NOW(),1),
    (3, 2, 5, 'Thích không khí ở phòng tập.', NOW(),1),
    (3, 3, 5, 'Thích không khí ở phòng tập.', NOW(),1),
    (3, 4, 5, 'Thích không khí ở phòng tập.', NOW(),1),
    (3, 5, 5, 'Thích không khí ở phòng tập.', NOW(),1),
    (3, 6, 5, 'Thích không khí ở phòng tập.', NOW(),1),
    (3, 7, 5, 'Thích không khí ở phòng tập.', NOW(),1),
    (3, 8, 5, 'Thích không khí ở phòng tập.', NOW(),1),
    (3, 9, 5, 'Thích không khí ở phòng tập.', NOW(),1),
    (3, 10, 5, 'Thích không khí ở phòng tập.', NOW(),1),
    (3, 11, 5, 'Thích không khí ở phòng tập.', NOW(),1),

    (4, 1, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(),1),
    (4, 2, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(),1),
    (4, 3, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(),1),
    (4, 4, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(),1),
    (4, 5, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(),1),
    (4, 6, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(),1),
    (4, 7, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(),1),
    (4, 8, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(),1),
    (4, 9, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(),1),
    (4, 10, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(),1),
    (4, 11, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(),1),

    (5, 1, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
    (5, 2, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
    (5, 3, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
    (5, 4, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
    (5, 5, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
    (5, 6, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
    (5, 7, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
    (5, 8, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
    (5, 9, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
    (5, 10, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1),
    (5, 11, 2, 'Cần cải thiện về thiết bị tại phòng tập cardio.', NOW(), 1);



/********** Department Gym Plan Creation ***********/
-- Create Gym plan status
INSERT INTO mst_kbn (mst_kbn_name, mst_kbn_key, mst_kbn_value)
VALUES ('Gym Plan Type', 1, 'Gói theo giờ'),
       ('Gym Plan Type', 2, 'Gói không theo giờ');

-- Create Gym plan infos
INSERT INTO gym_plan (gym_department_id, user_id, gym_plan_key, gym_plan_status_key, gym_plan_type_key, name, description, price, price_per_hours, plan_sold, duration, plan_before_active_validity, plan_after_active_validity)
VALUES

    (1,1, 1, 1, 1, 'Gói giờ',
     'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp.',
     0, 10.00, 0, 0, 7, 14),

    (1,1, 2, 1, 2, 'Gói 3 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam',
     400, 0, 0, 90, 10, 90),

    (1,1, 2, 1, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ',
     150.00, 0, 0, 30, 10, 30),

    (2,1, 1, 1, 1, 'Gói giờ',
     'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp.',
     0, 10.00, 0, 0, 7, 14),

    (2,1, 2, 1, 2, 'Gói 3 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam',
     400, 0, 0, 90, 10, 90),

    (2,1, 2, 1, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ',
     150.00, 0, 0, 30, 10, 30),

    (3,1, 1, 1, 1, 'Gói giờ',
     'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp.',
     0, 10.00, 0, 0, 7, 14),

    (3,1, 2, 1, 2, 'Gói 3 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam',
     400, 0, 0, 90, 10, 90),

    (3,1, 2, 1, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ',
     150.00, 0, 0, 30, 10, 30),

    (4,1, 1, 1, 1, 'Gói giờ',
     'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp.',
     0, 10.00, 0, 0, 7, 14),

    (4,1, 2, 1, 2, 'Gói 3 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam',
     400, 0, 0, 90, 10, 90),

    (4,1, 2, 1, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ',
     150.00, 0, 0, 30, 10, 30),

    (5,1, 1, 1, 1, 'Gói giờ',
     'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp.',
     0, 10.00, 0, 0, 7, 14),

    (5,1, 2, 1, 2, 'Gói 3 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam',
     400, 0, 0, 90, 10, 90),

    (5,1, 2, 1, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ',
     150.00, 0, 0, 30, 10, 30),

    (6,1, 1, 1, 1, 'Gói giờ',
     'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp.',
     0, 10.00, 0, 0, 7, 14),

    (6,1, 2, 1, 2, 'Gói 3 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam',
     400, 0, 0, 90, 10, 90),

    (6,1, 2, 1, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ',
     150.00, 0, 0, 30, 10, 30),

    (7,1, 1, 1, 1, 'Gói giờ',
     'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp.',
     0, 10.00, 0, 0, 7, 14),

    (7,1, 2, 1, 2, 'Gói 3 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam',
     400, 0, 0, 90, 10, 90),

    (7,1, 2, 1, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ',
     150.00, 0, 0, 30, 10, 30),

    (8,1, 1, 1, 1, 'Gói giờ',
     'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp.',
     0, 10.00, 0, 0, 7, 14),

    (8,1, 2, 1, 2, 'Gói 3 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam',
     400, 0, 0, 90, 10, 90),

    (8,1, 2, 1, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ',
     150.00, 0, 0, 30, 10, 30),

    (9,1, 1, 1, 1, 'Gói giờ',
     'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp.',
     0, 10.00, 0, 0, 7, 14),

    (9,1, 2, 1, 2, 'Gói 3 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam',
     400, 0, 0, 90, 10, 90),

    (9,1, 2, 1, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ',
     150.00, 0, 0, 30, 10, 30),

    (10,1, 1, 1, 1, 'Gói giờ',
     'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp.',
     0, 10.00, 0, 0, 7, 14),

    (10,1, 2, 1, 2, 'Gói 3 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam',
     400, 0, 0, 90, 10, 90),

    (10,1, 2, 1, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ',
     150.00, 0, 0, 30, 10, 30),

    (11,1, 1, 1, 1, 'Gói giờ',
     'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp.',
     0, 10.00, 0, 0, 7, 14),

    (11,1, 2, 1, 2, 'Gói 3 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam',
     400, 0, 0, 90, 10, 90),

    (11,1, 2, 1, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ',
     150.00, 0, 0, 30, 10, 30);

/********** Brand Creation ***********/
-- Create brand infos
INSERT INTO brand (brand_id,user_id,name,logo_url,wallpaper_url,thumbnail_url,description,rating,contact_number,contact_email,brand_status_key)
VALUES
    (8, 1, 'Getfit Gym & Yoga', 'https://s.net.vn/nmj5','https://s.net.vn/qpJJ' ,'https://s.net.vn/zEWu' , 'Getfit với kinh nghiệm hơn 10 năm huấn luyện thể hình, được đánh giá là thương hiệu có chuyên môn cao và huấn luyện học viên theo chương trình chuẩn quốc tế. Hiện nay, sau đại dịch, Getfit đã và đang cung cấp đến học viên gói phục hồi sức khỏe sau Covid. Huấn luyện viên đã chuẩn bị sẵn lịch trình và bài tập phù hợp dành cho những người đang có triệu chứng của hậu Covid như: khó thở, hụt hơi khi thở…', 5, '0345 535 454', 'info@getfit.vn', 1),
    (9, 1, 'Elite Fitness', 'https://s.net.vn/GDPl', 'https://s.net.vn/zFjJ','https://s.net.vn/pXdC', 'Khám phá Elite Fitness để trải nghiệm và tận hưởng không gian phòng tập sang trọng, đẹp bậc nhất Đông Nam Á. Xâm lấn vào thị trường gym năm 2010, thương hiệu gym này đã phát triển từ câu lạc bộ đầu tiên tại Xuân Diệu, Hà Nội. Hướng đến hệ thống câu lạc bộ thể thao đẳng cấp 5 sao, tiêu chuẩn Quốc tế. Dưới sự quản lý của tập đoàn BIM Việt Nam, hiện nay hệ thống đã sở hữu 14 câu lạc bộ trải khắp toàn quốc. Elite Fitness cung cấp đa dạng dịch vụ, tùy theo nhu cầu của bạn. Đến đây, bạn không cần phải bâng khuâng về lịch tập, vì các huấn luyện viên sẽ là người lên lịch phù hợp để bạn đạt mục tiêu của mình nhanh nhất.', 3, '(028) 7307 9899', 'elitefitness@gmail.com', 1),
    (10, 1, 'Fit365 Fitness & Yoga', 'https://s.net.vn/3rlq', 'https://s.net.vn/XJiB','https://s.net.vn/TGAf', 'Fit365 đầu tư trang thiết bị tập luyện tối tân cho học viên được trải nghiệm cùng huấn luyện viên chuyên nghiệp. Khác biệt với thương hiệu khác, Fit365 đầu tư hồ thủy lực. Massage giúp nâng cao quá trình loại bỏ chất thải, cải thiện giấc ngủ và cải thiện độ đàn hồi của cơ bắp. Học viên được thỏa sức thư giãn thả lỏng trong bồn massage và cảm nhận sự tái sinh của cơ thể sau 1 ngày mệt mỏi.', 4, '0909 290 880', 'fit365vn@gmail.com', 1);
--
INSERT INTO brand_amenities (brand_id, photo_url, amenitie_name, description,amenitie_status)
VALUES (8, 'https://s.net.vn/ZsDW', 'Yoga Master Class', 'Bừng sức sống mới cùng Yoga', 1),
       (8, 'https://s.net.vn/3F2r', 'Yoga 1:1', 'Trải nghiệm mới mẻ cùng Yoga', 1),
       (8, 'https://s.net.vn/dOQ0', 'Boxing cùng HLV', 'Boxing cùng những huấn luyện viên chuyên nghiệp', 1),

       (9, 'https://s.net.vn/v2mr', 'Yoga Thiền Cho Sức Khỏe Tinh Thần', 'Tham gia các buổi thiền yoga giúp giảm căng thẳng, tăng cường sức khỏe tinh thần một cách tự nhiên.', 1),

       (9, 'https://s.net.vn/mC0i', 'Lớp học độc quyền LESMILLS ', 'Chương trình bài tập LESMILLS sôi động, giúp đốt cháy calo và tăng cường sức bền.', 1),

       (9, 'https://s.net.vn/du49', 'Học Viện Boxing Chuyên Nghiệp', 'Học viên sẽ được hướng dẫn kỹ thuật đấm bốc từ các huấn luyện viên chuyên nghiệp.', 1),

       (9, 'https://s.net.vn/B06r', 'Khóa Học Yoga Cấp Độ Cao', 'Tham gia các khóa học yoga cấp độ cao, tập trung vào các động tác phức tạp và hiểu sâu về yoga.', 1);
--
INSERT INTO brand_amenities (brand_id, photo_url, amenitie_name, description, amenitie_status)
VALUES (10, 'https://s.net.vn/Kz6U', 'Hồ Massage Thủy Lực', 'Là một sản phẩm của sự kết hợp độc đáo giữa công nghệ hiện đại, tiên tiến nhất và cổ xưa nhất với các phương pháp trị liệu thư giãn của hương thơm tinh dầu, massage, thư giãn, chăm sóc da và giải trí mang lại cho bạn nhiều lợi ích về sức khỏe cũng như sắc đẹp.', 1);
INSERT INTO brand_amenities (brand_id, photo_url, amenitie_name, description, amenitie_status)
VALUES (10, 'https://s.net.vn/LZ0g', 'Lớp Zumba Sôi Động', 'Tham gia lớp Zumba sôi động với nhịp điệu âm nhạc, giúp tăng cường sức khỏe và vui vẻ trong quá trình tập luyện.', 1);

INSERT INTO brand_amenities (brand_id, photo_url, amenitie_name, description, amenitie_status)
VALUES (10, 'https://s.net.vn/BwvM', 'Lớp Boxing Chuyên Nghiệp', 'Dành cho người muốn nâng cao kỹ thuật và tăng cường sức mạnh cùng với huấn luyện viên chuyên nghiệp.', 1);

INSERT INTO brand_amenities (brand_id, photo_url, amenitie_name, description, amenitie_status)
VALUES (10, 'https://s.net.vn/zMIA', 'Yoga Cho Tâm Hồn', 'Trải nghiệm các buổi yoga tập trung vào tinh thần, giúp cân bằng tâm hồn và cơ thể.', 1);

-- Create Gym Department infos

INSERT INTO gym_department (gym_department_id, brand_id, gym_department_status_key, name, address, contact_number, logo_url, wallpaper_url, thumbnail_url, description, latitude, longitude, capacity, area)
VALUES (57, 8, 1, 'Getfit Chi nhánh Oriental Tân Phú', 'Lầu M (lầu 1) Oriental Plaza, 685 Âu Cơ, P.Tân Thành,Q.Tân Phú.', '(097) 769-9490', 'https://s.net.vn/nmj5', 'https://s.net.vn/F4yH', 'https://s.net.vn/F4yH', 'Với phương châm lấy sức khỏe khách hàng làm mục tiêu phát triển, Getfit không ngừng nâng cấp, đổi mới và sáng tạo để vận dụng những khoa học công nghệ hàng đầu thế giới vào việc xây dựng thể chất của người Việt. Cùng với sứ mệnh “Nâng tầm thể chất người Việt” của Getfit Gym & Yoga, hàng loạt các hệ sinh thái ra đời và không ngừng mở rộng nhằm đem đến những giá trị trọn vẹn nhất đến với khách hàng.', 10.789814194191278, 106.63987821995256, 200, 200);

INSERT INTO gym_department (gym_department_id, brand_id, gym_department_status_key, name, address, contact_number, logo_url, wallpaper_url, thumbnail_url, description, latitude, longitude, capacity, area)
VALUES (83, 8, 1, 'Getfit Chi nhánh Mia Center', 'Lầu 4, Saigon Mia Center, 202 Đường số 9A, KDC Trung Sơn.', '(096) 611-1241', 'https://s.net.vn/nmj5', 'https://s.net.vn/l57q', 'https://s.net.vn/z1Ms', 'Phòng tập được trang bị các thiết bị tập luyện hiện đại nhằm đem lại môi trường luyện tập đẳng cấp xứng tầm CLB 5 sao, đây đủ dụng cụ tập đáp ứng đa dạng nhu cầu của quý hội viên.', 10.733395069054875, 106.68892360080237, 200, 200);

INSERT INTO gym_department (gym_department_id, brand_id, gym_department_status_key, name, address, contact_number, logo_url, wallpaper_url, thumbnail_url, description, latitude, longitude, capacity, area)
VALUES (63, 8, 1, 'Getfit Chi Nhánh HOÀNG DIỆU', 'Lầu 3, Cao Ốc H3, 384 Hoàng Diệu, Phường 6, Quận 4, TP. HCM', '(098) 849-9745', 'https://s.net.vn/nmj5', 'https://s.net.vn/Jy7I', 'https://s.net.vn/ZI2D', 'Phòng tập được trang bị các thiết bị tập luyện hiện đại nhằm đem lại môi trường luyện tập đẳng cấp xứng tầm CLB 5 sao. Tất cả Khách hàng sẽ được kiểm tra thể chất bằng in Body & được tư vấn dinh dưỡng cũng như cách tập luyện phù hợp.', 10.76051532090389, 106.69908029869617, 200, 200);

INSERT INTO gym_department (gym_department_id, brand_id, gym_department_status_key, name, address, contact_number, logo_url, wallpaper_url, thumbnail_url, description, latitude, longitude, capacity, area)
VALUES (87, 9, 1, 'Elite Fitness XUÂN DIỆU', '51 Xuân Diệu, Quảng An, Tây Hồ, Hà Nội', '024 7302 0888', 'https://s.net.vn/GDPl', 'https://s.net.vn/vjc7', 'https://s.net.vn/nmwf', 'CLB tiên phong của Elite Fitness, tọa lạc tại tòa tháp Syrena ngự trên mảnh đất trung tâm Hồ Tây đáng sống bậc nhất tại Hà Nội; Elite Fitness Xuân Diệu là sự lựa chọn hoàn hảo để tận hưởng trọn vẹn không gian tập luyện đẳng cấp, cộng đồng văn minh, một nhịp sống bình yên rất Hà Nội mà không kém phần sôi động.', 21.063912459638402, 105.82813606669859, 200, 200);

INSERT INTO gym_department (gym_department_id, brand_id, gym_department_status_key, name, address, contact_number, logo_url, wallpaper_url, thumbnail_url, description, latitude, longitude, capacity, area)
VALUES (78, 9, 1, 'Elite Fitness Bà Triệu', 'Tầng 6, Tháp C, Vincom Center, 191 Bà Triệu, Hà Nội', '024 39749191', 'https://s.net.vn/GDPl', 'https://s.net.vn/HQon', 'https://s.net.vn/jik4', 'Tự hào là một trong những Câu lạc bộ Elite Fitness Top 1 về sự sang trọng và đẳng cấp nhất của hệ thống Elite Fitness, Elite Fitness Vincom Bà Triệu luôn luôn nỗ lực không ngừng để tạo nên một định nghĩa khác về trải nghiệm tập luyện thể thao cao cấp trong suốt 8 năm qua. Chúng tôi với đội ngũ của nhiệt huyết và sự khát khao tạo nên những dịch vụ tuyệt vời trong một môi trường phòng tập hàng đầu. Mỗi Hội viên, mỗi khách hàng là một trọng tâm của chăm sóc và hỗ trợ một cách tận tâm để luôn đạt được mục tiêu sức khỏe trọn vẹn khi đến với chúng tôi. Hãy trở thành một thành viên trong hành trình kiến tạo giá trị sức khỏe cho cộng đồng của chúng tôi! Let’s be Elite!', 21.01108468531799, 105.84965020691072, 200, 200);

INSERT INTO gym_department (gym_department_id, brand_id, gym_department_status_key, name, address, contact_number, logo_url, wallpaper_url, thumbnail_url, description, latitude, longitude, capacity, area)
VALUES (91, 9, 1, 'Elite Fitness Nguyễn Chí Than', 'Tầng 6, Vinhomes 54A Nguyễn Chí Thanh, Ngọc Khánh, Ba Đình, Hà Nội', '024 7307 8889', 'https://s.net.vn/GDPl', 'https://s.net.vn/d1Vb', 'https://s.net.vn/bu8z', 'Tọa lạc tại vị trí trung tâm, Elite Fitness Nguyễn Chí Thanh là một trong những Câu lạc bộ có không gian tập rộng và sang trọng bậc nhất trong hệ thống Elite Fitness. Chúng tôi mong muốn mang đến những giá trị tích cực nhất; xây dựng một cộng đồng khỏe mạnh không những từ thể chất mà còn từ tinh thần bên trong; nơi mà những kiến thức luôn được chia sẻ. Hãy cùng bắt đầu hành trình cho cuộc sống của bạn trở nên tốt đẹp hơn cùng với Chúng tôi từ hôm nay!', 21.025367760381087, 105.80951344612583, 200, 200);

INSERT INTO gym_department (gym_department_id, brand_id, gym_department_status_key, name, address, contact_number, logo_url, wallpaper_url, thumbnail_url, description, latitude, longitude, capacity, area)
VALUES (52, 10, 1, 'Fit365 Fitness &Yoga Q.11', '219 Lý Thường Kiệt, Phường 15, Quận 11, Tp. Hồ Chí Minh , Ho Chi Minh City, Vietnam', '090 929 08 80', 'https://s.net.vn/w1ho', 'https://s.net.vn/H3lr', 'https://s.net.vn/EYRN', 'Hãy đến ngay với 𝐅𝐈𝐓𝟑𝟔𝟓  hôm nay để tập luyện, tại đây bạn sẽ được tư vấn các bài tập phụ hợp với tình trạng sức khỏe của bản thân, nâng cấp body và trải nghiệm hệ thống máy tập an toàn cùng đội ngũ PT tận tâm nhất.Liên hệ ngay với 𝐅𝐈𝐓𝟑𝟔𝟓 để được tư vấn miễn phí, nhận ngay ưu đãi và đăng kí tập luyện sớm nhất có thể !!!', 10.767766928241029, 106.65824315116448, 200, 200);

--
INSERT INTO features (feature_icon, feature_name, feature_status)
VALUES
    ('<i class="bi bi-p-circle"></i>', 'Bãi Đỗ Xe', 1),
    ('<i class="fa-solid fa-shower"></i>', 'Phòng Tắm Nóng Lạnh', 1),
    ('<i class="fa-solid fa-wifi"></i>', 'WIFI', 1),
    ('<i class="fas fa-scroll"></i>', 'Khăn Miễn Phí', 1),
    ('<i class="fa-solid fa-couch"></i>', 'Khu Nghỉ Ngơi', 1),
    ('<i class="fa-solid fa-martini-glass-citrus"></i>', 'Quầy Nước', 1),
    ('<i class="fa-solid fa-suitcase-medical"></i>', 'Phòng Y Tế', 1);

--
INSERT INTO gym_department_features (feature_id, gym_department_id, feature_status)
VALUES
    (1, 57, 1),
    (2, 57, 1),
    (3, 57, 1),
    (4, 57, 1),
    (5, 57, 1);

INSERT INTO gym_department_features (feature_id, gym_department_id, feature_status)
VALUES
    (1, 83, 1),
    (2, 83, 1),
    (3, 83, 1),
    (6, 83, 1),
    (7, 83, 1);

INSERT INTO gym_department_features (feature_id, gym_department_id, feature_status)
VALUES
    (1, 63, 1),
    (3, 63, 1),
    (4, 63, 1),
    (5, 63, 1),
    (7, 63, 1);

--

INSERT INTO gym_department_features (feature_id, gym_department_id, feature_status)
VALUES
    (1, 91, 1),
    (2, 91, 1),
    (3, 91, 1),
    (4, 91, 1),
    (5, 91, 1);

INSERT INTO gym_department_features (feature_id, gym_department_id, feature_status)
VALUES
    (1, 78, 1),
    (2, 78, 1),
    (3, 78, 1),
    (6, 78, 1),
    (7, 78, 1);

INSERT INTO gym_department_features (feature_id, gym_department_id, feature_status)
VALUES
    (1, 87, 1),
    (3, 87, 1),
    (4, 87, 1),
    (5, 87, 1),
    (7, 87, 1);

--
INSERT INTO gym_department_features (feature_id, gym_department_id, feature_status)
VALUES
    (1, 52, 1),
    (2, 52, 1),
    (3, 52, 1),
    (4, 52, 1),
    (5, 52, 1);


-- Create Gym Department Gallery

-- Gym Department Gallery for Getfit Chi nhánh Oriental (Tân Phú)
INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (57, 'https://s.net.vn/F4yH', 'Getfit Tan phu - Image 1 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (57, 'https://s.net.vn/jWnd', 'Getfit Tan Phu - Image 2 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (57, 'https://s.net.vn/vS0V', 'Getfit Tan Phu - Image 3 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (57, 'https://s.net.vn/vNWz', 'Getfit Tan Phu - Image 4 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (57, 'https://s.net.vn/ou8q', 'Getfit Tan Phu - Image 5 Description');

-- Gym Department Gallery for Getfit Chi nhánh Mia Center
INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (83, 'https://aicjsc.com/wp-content/uploads/2023/04/CHUC-NANG.png', 'Getfit Tan phu - Image 1 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (83, 'https://aicjsc.com/wp-content/uploads/2023/04/TAP-LUYEN-2.png', 'Getfit Tan Phu - Image 2 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (83, 'https://aicjsc.com/wp-content/uploads/2023/04/TAP-LUYEN-3.png', 'Getfit Tan phu - Image 2 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (83, 'https://aicjsc.com/wp-content/uploads/2023/04/TAP-LUYEN.png', 'Getfit Tan Phu - Image 3 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (83, 'https://aicjsc.com/wp-content/uploads/2023/04/BOXING-2.png', 'Getfit Tan phu - Image 4 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (83, 'https://aicjsc.com/wp-content/uploads/2023/04/BOXING.png', 'Getfit Tan Phu - Image 5 Description');

-- Gym Department Gallery for Getfit CHI NHÁNH HOÀNG DIỆU
INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (63, 'https://aicjsc.com/wp-content/uploads/2023/04/BAR-LOUNGE-2.png', 'Getfit Tan phu - Image 1 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (63, 'https://aicjsc.com/wp-content/uploads/2023/04/BAR-LOUNGE-3.png', 'Getfit Tan Phu - Image 2 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (63, 'https://aicjsc.com/wp-content/uploads/2023/04/KHU-DAP-XE-2.png', 'Getfit Tan phu - Image 3 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (63, 'https://aicjsc.com/wp-content/uploads/2023/04/KHU-DAP-XE-3.png', 'Getfit Tan Phu - Image 4 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (63, 'https://aicjsc.com/wp-content/uploads/2023/04/YOGA.png', 'Getfit Tan phu - Image 5 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (63, 'https://aicjsc.com/wp-content/uploads/2023/04/YOGA-2.png', 'Getfit Tan Phu - Image 6 Description');

-- Gym Department Gallery for Elite Fitness XUÂN DIỆU
INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (87, 'https://cms.elitefitness.com.vn/Upload2/20230821/82fd103a-d60c-4c48-9e0c-a7bb01f88980.JPG?w=865&h=487&mode=max', 'Elite Fitness XUÂN DIỆU - Image 1 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (87, 'https://cms.elitefitness.com.vn/Upload2/20230821/aff6ba33-7fac-46fa-997b-55c77eaf71ff.jpg?w=865&h=487&mode=max', 'Elite Fitness XUÂN DIỆU - Image 2 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (87, 'https://cms.elitefitness.com.vn/Upload2/20230821/c5a38c53-be2c-4868-87b1-e436e5def25b.jpg?w=865&h=487&mode=max', 'Elite Fitness XUÂN DIỆU - Image 3 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (87, 'https://cms.elitefitness.com.vn/Upload2/20230821/c32d3e24-1791-49cb-a392-498ee29771e7.jpg?w=865&h=487&mode=max', 'Elite Fitness XUÂN DIỆU - Image 4 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (87, 'https://cms.elitefitness.com.vn/Upload2/20231010/037c42be-61d2-450c-aa6c-d9dc974572d7.jpg?w=865&h=487&mode=max', 'Elite Fitness XUÂN DIỆU - Image 5 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (87, 'https://cms.elitefitness.com.vn/Upload2/20230821/494b19ef-fbcf-45df-b75a-e7a7607588ae.jpg?w=865&h=487&mode=max', 'Elite Fitness XUÂN DIỆU - Image 6 Description');

-- Gym Department Gallery for Elite Fitness Nguyễn Chí Thanh
INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (91, 'https://cms.elitefitness.com.vn/Upload2/20230216/c0d0bfd0-1904-4485-b338-9b300986bc15.jpg?w=865&h=487&mode=max', 'Elite Fitness Bà Triệu - Image 1 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (91, 'https://cms.elitefitness.com.vn/Upload2/20230216/d5b3a2cf-2aa2-4ecc-ab69-8bede36f7283.jpg?w=865&h=487&mode=max', 'Elite Fitness Bà Triệu - Image 2 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (91, 'https://cms.elitefitness.com.vn/Upload2/20230216/680b98f8-5c60-4d0a-8625-36eb7e75872a.jpg?w=865&h=487&mode=max', 'Elite Fitness Bà Triệu - Image 2 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (91, 'https://cms.elitefitness.com.vn/Upload2/20230216/d1ccb077-afde-4065-8bf2-ffd9fabc39b4.jpg?w=865&h=487&mode=max', 'Elite Fitness Bà Triệu - Image 2 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (91, 'https://cms.elitefitness.com.vn/Upload2/20230216/51b8d924-4f81-4692-a0dd-942bcc829ba1.jpg?w=865&h=487&mode=max', 'Elite Fitness Bà Triệu - Image 2 Description');

-- Add more images as needed...
-- Gym Department Gallery for Elite Fitness Bà Triệu
INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (78, 'https://cms.elitefitness.com.vn/Upload2/20230109/ce3d98b6-92e4-4a3c-80ef-55c2f69e2bf7.jpg?w=865&h=487&mode=max', 'Elite Fitness Nguyễn Chí Thanh - Image 1 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (78, 'https://cms.elitefitness.com.vn/Upload2/20230107/c9f9f0ee-0ad0-45c6-8bc9-df932c5986e6.jpg?w=865&h=487&mode=max', 'Elite Fitness Nguyễn Chí Than - Image 2 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (78, 'https://cms.elitefitness.com.vn/Upload2/20230107/9478337f-d62c-43b0-9f10-fe39592b9ce1.jpg?w=865&h=487&mode=max', 'Elite Fitness Nguyễn Chí Than - Image 3 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (78, 'https://cms.elitefitness.com.vn/Upload2/20230107/9f33e6cf-bb46-490e-a81d-f52c1ade73f6.jpg?w=865&h=487&mode=max', 'Elite Fitness Nguyễn Chí Than - Image 4 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (78, 'https://cms.elitefitness.com.vn/Upload2/20230107/db32dd8d-cdf7-4991-99a0-7dc9129d846b.jpg?w=865&h=487&mode=max', 'Elite Fitness Nguyễn Chí Than - Image 5 Description');


-- Add more images as needed...
-- Gym Department Gallery for Fit365 Fitness &Yoga Q.11
INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (52, 'https://s.net.vn/Zc2u', 'Fit365 Fitness &Yoga Q.11 - Image 1 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (52, 'https://s.net.vn/zXG7', 'Fit365 Fitness &Yoga Q.11 - Image 2 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (52, 'https://s.net.vn/1Ihz', 'Fit365 Fitness &Yoga Q.11 - Image 2 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (52, 'https://s.net.vn/9zZB', 'Fit365 Fitness &Yoga Q.11 - Image 2 Description');

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES (52, 'https://s.net.vn/L3Ku', 'Fit365 Fitness &Yoga Q.11 - Image 2 Description');

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
VALUES
    (83, 'Thứ 2', '6:00 AM', '11:00 PM'),
    (83, 'Thứ 3', '6:00 AM', '11:00 PM'),
    (83, 'Thứ 4', '6:00 AM', '11:00 PM'),
    (83, 'Thứ 5', '6:00 AM', '11:00 PM'),
    (83, 'Thứ 6', '6:00 AM', '11:00 PM'),
    (83, 'Thứ 7', '6:00 AM', '11:00 PM'),
    (83, 'Chủ nhật', '6:00 AM', '11:00 PM');

INSERT INTO gym_department_schedule (gym_department_id, day, open_time, close_time)
VALUES
    (63, 'Thứ 2', '7:00 AM', '10:00 PM'),
    (63, 'Thứ 3', '7:00 AM', '10:00 PM'),
    (63, 'Thứ 4', '7:00 AM', '10:00 PM'),
    (63, 'Thứ 5', '7:00 AM', '10:00 PM'),
    (63, 'Thứ 6', '7:00 AM', '10:00 PM'),
    (63, 'Thứ 7', '7:00 AM', '10:00 PM'),
    (63, 'Chủ nhật', '7:00 AM', '10:00 PM');

INSERT INTO gym_department_schedule (gym_department_id, day, open_time, close_time)
VALUES
    (87, 'Thứ 2', '5:00 AM', '11:00 PM'),
    (87, 'Thứ 3', '5:00 AM', '11:00 PM'),
    (87, 'Thứ 4', '5:00 AM', '11:00 PM'),
    (87, 'Thứ 5', '5:00 AM', '11:00 PM'),
    (87, 'Thứ 6', '5:00 AM', '11:00 PM'),
    (87, 'Thứ 7', '5:00 AM', '11:00 PM'),
    (87, 'Chủ nhật', '5:00 AM', '11:00 PM');

INSERT INTO gym_department_schedule (gym_department_id, day, open_time, close_time)
VALUES
    (78, 'Thứ 2', '6:30 AM', '10:00 PM'),
    (78, 'Thứ 3', '6:30 AM', '10:00 PM'),
    (78, 'Thứ 4', '6:30 AM', '10:00 PM'),
    (78, 'Thứ 5', '6:30 AM', '10:00 PM'),
    (78, 'Thứ 6', '6:30 AM', '10:00 PM'),
    (78, 'Thứ 7', '6:30 AM', '10:00 PM'),
    (78, 'Chủ nhật', '6:30 AM', '10:00 PM');

INSERT INTO gym_department_schedule (gym_department_id, day, open_time, close_time)
VALUES
    (91, 'Thứ 2', '6:00 AM', '9:00 PM'),
    (91, 'Thứ 3', '6:00 AM', '9:00 PM'),
    (91, 'Thứ 4', '6:00 AM', '9:00 PM'),
    (91, 'Thứ 5', '6:00 AM', '9:00 PM'),
    (91, 'Thứ 6', '6:00 AM', '9:00 PM'),
    (91, 'Thứ 7', '6:00 AM', '9:00 PM'),
    (91, 'Chủ nhật', '6:00 AM', '9:00 PM');

INSERT INTO gym_department_schedule (gym_department_id, day, open_time, close_time)
VALUES
    (52, 'Thứ 2', '7:00 AM', '10:00 PM'),
    (52, 'Thứ 3', '7:00 AM', '10:00 PM'),
    (52, 'Thứ 4', '7:00 AM', '10:00 PM'),
    (52, 'Thứ 5', '7:00 AM', '10:00 PM'),
    (52, 'Thứ 6', '7:00 AM', '10:00 PM'),
    (52, 'Thứ 7', '7:00 AM', '10:00 PM'),
    (52, 'Chủ nhật', '7:00 AM', '10:00 PM');



/********** Department Feedback Creation ***********/

INSERT INTO user_feedback (user_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES
    (1, 57, 4, 'Cơ sở vật chất tốt!', '2023-10-15 11:30:00', 1),
    (2, 57, 3, 'Cần nhiều hơn về đa dạng thiết bị', '2023-10-17 14:45:00', 1),
    (3, 57, 3, 'Thiếu sự phong phú về lịch tập', '2023-10-16 16:00:00', 1),
    (4, 57,5, 'Hài lòng với dịch vụ', '2023-10-15 14:00:00', 1),
    (5, 57, 2, 'Có thể cải thiện chất lượng thiết bị', '2023-10-19 09:00:00', 1);


INSERT INTO user_feedback (user_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES
    (1, 83, 5, 'Nhân viên tuyệt vời!', '2023-10-16 09:30:00', 1),
    (1, 83, 4, 'Môi trường sạch sẽ và thiết bị được bảo quản tốt', '2023-10-17 10:20:00', 1),
    (3, 83, 3, 'Thiếu sự phong phú về lịch tập', '2023-10-16 16:00:00', 1),
    (4, 83,5, 'Hài lòng với dịch vụ', '2023-10-15 14:00:00', 1),
    (5, 83, 2, 'Có thể cải thiện chất lượng thiết bị', '2023-10-19 09:00:00', 1);


INSERT INTO user_feedback (user_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES
    (1, 63, 4, 'Môi trường tốt và huấn luyện viên thân thiện', '2023-10-15 15:00:00', 1),
    (1, 63, 3, 'Có thể cải thiện về vấn đề vệ sinh', '2023-10-16 16:30:00', 1),
    (3, 63, 3, 'Thiếu sự phong phú về lịch tập', '2023-10-16 16:00:00', 1),
    (4, 63,5, 'Hài lòng với dịch vụ', '2023-10-15 14:00:00', 1),
    (5, 63, 2, 'Có thể cải thiện chất lượng thiết bị', '2023-10-19 09:00:00', 1);

INSERT INTO user_feedback (user_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES
    (1, 87, 5, 'Hoàn toàn yêu thích!', '2023-10-17 13:45:00', 1),
    (1, 87, 4, 'Nhân viên hữu ích và am hiểu', '2023-10-18 11:00:00', 1),
    (3, 87, 3, 'Thiếu sự phong phú về lịch tập', '2023-10-16 16:00:00', 1),
    (4, 87,5, 'Hài lòng với dịch vụ', '2023-10-15 14:00:00', 1),
    (5, 87, 2, 'Có thể cải thiện chất lượng thiết bị', '2023-10-19 09:00:00', 1);

INSERT INTO user_feedback (user_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES
    (1, 78, 5, 'Dịch vụ tuyệt vời!', '2023-10-17 18:20:00', 1),
    (1, 78, 3, 'Có thể cung cấp thiết bị tốt hơn', '2023-10-18 19:00:00', 1),
    (3, 78, 3, 'Thiếu sự phong phú về lịch tập', '2023-10-16 16:00:00', 1),
    (4, 78,5, 'Hài lòng với dịch vụ', '2023-10-15 14:00:00', 1),
    (5, 78, 2, 'Có thể cải thiện chất lượng thiết bị', '2023-10-19 09:00:00', 1);
INSERT INTO user_feedback (user_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES
    (1, 91, 4, 'Các huấn luyện viên tuyệt vời!', '2023-10-15 12:00:00', 1),
    (1, 91, 2, 'Vệ sinh kém', '2023-10-16 17:30:00', 1),
    (3, 91, 3, 'Thiếu sự phong phú về lịch tập', '2023-10-16 16:00:00', 1),
    (4, 91,5, 'Hài lòng với dịch vụ', '2023-10-15 14:00:00', 1),
    (5, 91, 2, 'Có thể cải thiện chất lượng thiết bị', '2023-10-19 09:00:00', 1);
INSERT INTO user_feedback (user_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES
    (1, 52, 3, 'Cần cải thiện dịch vụ', '2023-10-18 14:15:00', 1),
    (1, 52, 4, 'Huấn luyện viên nhiệt tình', '2023-10-19 09:45:00', 1),
    (3, 52, 3, 'Thiếu sự phong phú về lịch tập', '2023-10-16 16:00:00', 1),
    (4, 52,5, 'Hài lòng với dịch vụ', '2023-10-15 14:00:00', 1),
    (5, 52, 2, 'Có thể cải thiện chất lượng thiết bị', '2023-10-19 09:00:00', 1);



-- Create Gym plan infos for Getfit Chi nhánh Oriental (Tân Phú)
INSERT INTO gym_plan (gym_department_id, user_id, gym_plan_key, gym_plan_status_key, gym_plan_type_key, name, description, price, price_per_hours, plan_sold, duration, plan_before_active_validity, plan_after_active_validity)
VALUES
    (57, 1, 1, 1, 1, 'Gói Linh Hoạt', 'Gói tập linh hoạt, phù hợp cho người muốn trải nghiệm các phòng tập. Đặc quyền tập luyện ở mọi cơ sở.', 0, 50.00, 0, 0, 10, 30),
    (57,1, 2, 1, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ', 150.00, 0, 0, 30, 10, 30),
    (57,1, 2, 1, 2, 'Gói 3 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ', 450.00, 0, 0, 90, 10, 90);

-- Create Gym plan infos for Getfit CHI NHÁNH HOÀNG DIỆU
INSERT INTO gym_plan (gym_department_id, user_id, gym_plan_key, gym_plan_status_key, gym_plan_type_key, name, description, price, price_per_hours, plan_sold, duration, plan_before_active_validity, plan_after_active_validity)
VALUES
    (63, 1, 1, 1, 1, 'Gói Linh Hoạt', 'Gói tập linh hoạt, phù hợp cho người muốn trải nghiệm các phòng tập. Đặc quyền tập luyện ở mọi cơ sở.', 0, 50.00, 0, 0, 10, 30),
    (63,1, 2, 1, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ', 150.00, 0, 0, 30, 10, 30),
    (63,1, 2, 1, 2, 'Gói 3 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ', 450.00, 0, 0, 90, 10, 90);

-- Create Gym plan infos for Getfit Chi nhánh Mia Center
INSERT INTO gym_plan (gym_department_id, user_id, gym_plan_key, gym_plan_status_key, gym_plan_type_key, name, description, price, price_per_hours, plan_sold, duration, plan_before_active_validity, plan_after_active_validity)
VALUES
    (83, 1, 1, 1, 1, 'Gói Linh Hoạt', 'Gói tập linh hoạt, phù hợp cho người muốn trải nghiệm các phòng tập. Đặc quyền tập luyện ở mọi cơ sở.', 0, 50.00, 0, 0, 10, 30),
    (83,1, 2, 1, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ', 150.00, 0, 0, 30, 10, 30),
    (83,1, 2, 1, 2, 'Gói 3 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ', 450.00, 0, 0, 90, 10, 90);

-- Create Gym plan infos for Elite Fitness XUÂN DIỆU
INSERT INTO gym_plan (gym_department_id, user_id, gym_plan_key, gym_plan_status_key, gym_plan_type_key, name, description, price, price_per_hours, plan_sold, duration, plan_before_active_validity, plan_after_active_validity)
VALUES
    (87, 1, 1, 1, 1, 'Gói Linh Hoạt', 'Gói tập linh hoạt, phù hợp cho người muốn trải nghiệm các phòng tập. Đặc quyền tập luyện ở mọi cơ sở.', 0, 50.00, 0, 0, 10, 30),
    (87,1, 2, 1, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ', 150.00, 0, 0, 30, 10, 30),
    (87,1, 2, 1, 2, 'Gói 3 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ', 450.00, 0, 0, 90, 10, 90);

-- Create Gym plan infos for Elite Fitness Bà Triệu
INSERT INTO gym_plan (gym_department_id, user_id, gym_plan_key, gym_plan_status_key, gym_plan_type_key, name, description, price, price_per_hours, plan_sold, duration, plan_before_active_validity, plan_after_active_validity)
VALUES
    (78, 1, 1, 1, 1, 'Gói Linh Hoạt', 'Gói tập linh hoạt, phù hợp cho người muốn trải nghiệm các phòng tập. Đặc quyền tập luyện ở mọi cơ sở.', 0, 50.00, 0, 0, 10, 30),
    (78,1, 2, 1, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ', 150.00, 0, 0, 30, 10, 30),
    (78,1, 2, 1, 2, 'Gói 3 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ', 450.00, 0, 0, 90, 10, 90);

-- Create Gym plan infos for Elite Fitness Nguyễn Chí Thanh
INSERT INTO gym_plan (gym_department_id, user_id, gym_plan_key, gym_plan_status_key, gym_plan_type_key, name, description, price, price_per_hours, plan_sold, duration, plan_before_active_validity, plan_after_active_validity)
VALUES
    (91, 1, 1, 1, 1, 'Gói Linh Hoạt', 'Gói tập linh hoạt, phù hợp cho người muốn trải nghiệm các phòng tập. Đặc quyền tập luyện ở mọi cơ sở.', 0, 50.00, 0, 0, 10, 30),
    (91,1, 2, 1, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ', 150.00, 0, 0, 30, 10, 30),
    (91,1, 2, 1, 2, 'Gói 3 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ', 450.00, 0, 0, 90, 10, 90);

-- Create Gym plan infos Gallery for Fit365 Fitness &Yoga Q.11
INSERT INTO gym_plan (gym_department_id, user_id, gym_plan_key, gym_plan_status_key, gym_plan_type_key, name, description, price, price_per_hours, plan_sold, duration, plan_before_active_validity, plan_after_active_validity)
VALUES
    (52, 1, 1, 1, 1, 'Gói Linh Hoạt', 'Gói tập linh hoạt, phù hợp cho người muốn trải nghiệm các phòng tập. Đặc quyền tập luyện ở mọi cơ sở.', 0, 50.00, 0, 0, 10, 30),
    (52,1, 2, 1, 2, 'Gói 1 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ', 150.00, 0, 0, 30, 10, 30),
    (52,1, 2, 1, 2, 'Gói 3 tháng', 'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập ở tất cả CLB cũng như phòng tập trên toàn bộ hệ thống theo từng khu vực Bắc, Trung hoặc Nam ', 450.00, 0, 0, 90, 10, 90);



INSERT INTO brand (brand_id,user_id,name,logo_url,wallpaper_url,thumbnail_url,description,rating,contact_number,contact_email,brand_status_key)

VALUES
    ( 11,1, 'Advance Fitness & Gym',
      'https://bom.so/JjIOXa',
      'https://bom.so/z68t4s',
      'https://bom.so/VPBYrp',
      'Đến với Advance, học viên sẽ được kiểm tra thể chất bằng máy Check in Body – một trong những thiết bị hiện đại nhất Việt Nam hiện nay, được tư vấn dinh dưỡng như cách ăn uống và phân tích thức cả khi ở nhà.

  Nếu trở thành học viên ở Advance, bạn sẽ được kiểm tra sức khỏe đều đặn. Thông qua đó, huấn luyện viên sẽ đánh giá thể trạng và đưa ra phương pháp tập luyện phù hợp cho từng người.',
      0, '1900 63 35 31', ' advancefitnessgym@gmail.com', 1),

    ( 12,1, 'The New Gym',
      'https://bom.so/T0IpUX',
      'https://bom.so/tdJyS0',
      'https://bom.so/lLdgCq',
      'Hơn 200 máy tập từ cơ đến cardio và các loại tạ tự do. Điểm khác biệt của The New Gym chính là phòng tập gym 24/7, phòng tập Fitness and Yoga 24h, học viên ở đây có thể tập bất cứ khi nào rảnh.

  Nếu mong muốn hiện tại của bạn là tìm phòng tập gym Quận Tân Bình giá rẻ, phòng tập gym Bình Thạnh giá rẻ hay phòng tập gym quận 6 giá rẻ thì The New Gym là dành cho bạn.

  Giá tập tại đây chỉ với 300.000 VNĐ/ 1 tháng, huấn luyện viên tại The New Gym sẵn sàng tư vấn bài tập và chế độ ăn cho học viên mà không phát sinh bất kỳ chi phí nào',
      0, '1900 63 69 20', 'info@thenewgym.vn', 1),

    ( 13,1, 'VShape Gym & Yoga',
      'https://bom.so/thNR4Y',
      'https://bom.so/uMhrfX',
      'https://bom.so/thNR4Y',
      'Vshape được đầu tư cơ sở vật chất hiện đại, không gian thoáng mát, rộng rãi và trang thiết bị nhập khẩu hoàn toàn.

  Với diện tích 650m2 , tuy nhỏ hơn so với những phòng tập trên. Nhưng phần đầu tư trang thiết bị, máy móc thì không hề thua kém. Tất cả đều đạt tiêu chuẩn quốc tế để phục vụ cho việc tập luyện.

  Tại VShape hỗ trợ máy đánh giá sức khỏe, đo chỉ số cơ thể chi tiết và chuyên nghiệp của Inbody, công nghệ Mỹ.',
      0, '(08) 7108 0815', 'vshapegym@gmail.com', 1);

INSERT INTO brand_amenities (brand_id, photo_url, amenitie_name, description,amenitie_status)

VALUES
    (11,'https://bom.so/qBs9AN', 'Aerobic', 'Chương trình tập luyện năng động với nhịp nhàng, kết hợp các bài tập cardio để cải thiện sức khỏe tim mạch và sự linh hoạt.', 1),
    (11,'https://bom.so/h2BBn6', 'Yoga', ' Mang lại sự cân bằng giữa tâm hồn và cơ thể thông qua các động tác linh hoạt, thở đều, và thiền. Dành cho những người muốn giảm căng thẳng và tăng cường sức khỏe tinh thần.', 1),
    (11,'https://bom.so/ugZMXk', 'Zumba', 'Buổi tập năng động với những động tác nhảy và nhịp điệu âm nhạc sôi động. Phù hợp cho những người yêu thích không khí vui nhộn và muốn đốt cháy calo hiệu quả..', 1),
    (11,'https://bom.so/lAl2Ff', 'Combat Fit', 'Kết hợp các phong cách võ thuật và bài tập cường độ cao để phát triển sức mạnh, sự linh hoạt và sự chịu đựng. Dành cho những người muốn nâng cao kỹ thuật tự vệ và cường độ tập luyện.', 1),
    (11,'https://bom.so/XE4XKx', 'Fight Fit', 'Tập luyện dựa trên các phương pháp đối kháng và võ thuật, giúp cải thiện sức mạnh, tăng cường cardio và phản xạ. Là lựa chọn phù hợp cho những người muốn thách thức bản thân trong môi trường tập luyện độc đáo.', 1),

    (12,'https://bom.so/6vD5Ve', 'SEXY DANCE', 'Sexy dance hay còn gọi là nhảy sexy là những điệu nhảy hiện đại đầy chất trẻ, quyến rũ, chủ yếu sử dụng sự mềm dẻo và linh động làm nổi bật nên đường nét quyến rũ của cơ thể. Sexy dance với đặc thù nhằm vào việc làm nổi bật những vùng ngực, eo, bụng dưới, đùi, mông khi nhảy, vì vậy các nhóm cơ trên toàn bộ cơ thể như cơ bụng, lưng, chân,… đều phải vận động', 1),
    (12,'https://bom.so/LJAxsj', 'BUM N TUM', 'Bum n Tum là chương trình 45 phút được thiết kế gồm 3 phần. Phần 1 là các bài tập phần mông. Phần 2 là các bài tập phần đùi. Phần 3 là các bài tập phần cơ bụng. Chỉ với 45 phút sẽ giúp bạn đôt cháy nhiều năng lượng và có 1 phần cơ bụng, mông, đùi săn chắc.', 1),
    (12,'https://bom.so/vXxtJb', 'NEW PUMP', 'New Pump là môn học được thiết kế với thanh bar, tạ và bục. Bộ môn này giúp bạn tập luyện toàn thân, hoàn toàn lý tưởng cho bất kỳ ai muốn có được thân hình mảnh mai, săn chắc . Học viên sẽ sử dụng mức tạ nhẹ đến trung bình. Các giảng viên sẽ hướng dẫn bạn từng bước di chuyển, kĩ thuật được thiết kế trên nền âm nhạc một cách khoa học giúp bạn đạt được kết quả cao.', 1),
    (12,'https://bom.so/r6xRvp', 'NEW COMBAT', 'New Combat là môn học cường độ cao lấy cảm hứng từ võ thuật giúp bạn giải tỏa căng thẳng và tăng cường sức khỏe thể chất. Suốt 1 giờ đồng hồ được “đấm và đá” theo âm nhạc sôi động, bạn sẽ có cảm giác sung sức như một nhà vô địch', 1),
    (12,'https://bom.so/BWO4LI', 'DANCE FIT', 'Dance Fit là bộ môn dance fitness kết hợp những vũ đạo tự do, đơn giản trên nền nhạc sôi động trong thời gian dài, giúp đốt cháy mỡ thừa, giảm cân hiệu quả và thư giãn cùng âm nhạc. Khác với Zumba cùng những vũ điệu Latin, đến với New Dance bạn sẽ đắm chìm trong bữa tiệc âm nhạc nhiều màu sắc: Pop, Hip Hop ...', 1),

    (13,'https://bom.so/oojyMX', 'Aerobic', 'Cung cấp các buổi tập năng động với nhịp điệu âm nhạc, nhằm cải thiện sức khỏe tim mạch, tăng cường sức mạnh cơ bắp và đốt cháy calo. Đây là lựa chọn phổ biến cho những người muốn tăng cường thể lực và giảm cân.', 1),
    (13,'https://bom.so/JXvAlA', 'Gym', 'Trang bị phòng tập gym đầy đủ các máy móc và trang thiết bị để phục vụ mọi nhu cầu tập luyện cơ bản hoặc chuyên sâu. Hướng dẫn viên sẽ hỗ trợ hội viên trong việc lên kế hoạch và thực hiện các bài tập tùy thuộc vào mục tiêu cá nhân.', 1),
    (13,'https://bom.so/4OC25J', 'Yoga', 'Tạo không gian yên tĩnh và tập trung, Yoga giúp cải thiện linh hoạt, giảm căng thẳng và kích thích sự ổn định tinh thần. Cung cấp các lớp hướng dẫn từ cơ bản đến nâng cao, phù hợp với mọi đối tượng và trình độ.', 1);

INSERT INTO gym_department (gym_department_id, brand_id,gym_department_status_key, name, address, contact_number,
                            logo_url, wallpaper_url, thumbnail_url,description, latitude, longitude, capacity, area)

VALUES
    (12,11,1,'Advance Fitness & Gym Tôn Dật Tiên','SE05, GARDEN COURT 1, Tôn Dật Tiên, Phú Mỹ Hưng, Q7, Tp.HCM','08 5416 0555',
     'https://bom.so/JjIOXa',
     'https://bom.so/ab77Ui',
     'https://bom.so/1KSXnM',
     'Phòng tập gym Advance Fitness & Gym không hoành tráng như phố Nguyễn Huệ, nhưng lợi thế phố đi bộ Tôn Dật Tiên kết hợp hài hòa cây xanh, mặt nước, lối dạo, cảnh quan tự nhiên những chỗ dừng chân.

 Đến với phòng tập bạn sẽ được kiểm tra thể chất bằng máy Check in Body (một trong những thiết bị hiện đại nhất tại Việt Nam hiện nay), sau đó được các chuyên gia dinh dưỡng tư vấn về cách ăn uống, phân tích thức ăn (ngay cả khi ở nhà)',
     10.722874426578613, 106.71464114001405,
     200, 800),

    (13,11,1,'Advance Fitness & Gym Nguyễn Lương Bằng','ST05 Block E, Riverside Residence, Nguyễn Lương Bằng, Phú Mỹ Hưng, Q7, Tp.HCM','08 5411 8777',
     'https://bom.so/JjIOXa',
     'https://bom.so/iJjVqj',
     'https://bom.so/x75prK',
     'Phòng tập gym Advance Fitness & Gym với mong muốn mang lại môi trường tập luyện chuyên nghiệp theo hình thức 1:1, tức một huấn luyện viên hướng dẫn cho một học viên đã được trung tâm cam kết áp dụng tuyệt đối.

 Đến với phòng tập bạn sẽ được kiểm tra thể chất bằng máy Check in Body (một trong những thiết bị hiện đại nhất tại Việt Nam hiện nay), sau đó được các chuyên gia dinh dưỡng tư vấn về cách ăn uống, phân tích thức ăn (ngay cả khi ở nhà)',
     10.720789817865652, 106.72672455649781,
     300, 1100),

    (14,11,1,'Advance Fitness & Gym Kỳ Đồng','05 Kỳ Đồng, Phường 6 Quận 3 TPHCM.','08 6298 2111',
     'https://bom.so/JjIOXa',
     'https://bom.so/543E8a',
     'https://bom.so/KgyGXq',
     'Phòng tập gym Advance Fitness & Gym được thành lập với mong muốn mang lại môi trường tập luyện chuyên nghiệp theo hình thức 1:1, đó là hình thức của một huấn luyện viên hướng dẫn cho một học viên đã được trung tâm cam kết áp dụng tuyệt đối. Đến với Trung tâm các học viên sẽ được kiểm tra thể chất bằng máy Check in Body sau đó được các chuyên gia dinh dưỡng tư vấn về cách ăn uống, phân tích thức ăn.',
     10.78467544118492, 106.68288665202464,
     450, 1200),

    (15,12,1,'The New Gym Nguyễn Chí Thanh','332 Nguyễn Chí Thanh, Phường 5, Quận 10, Thành phố Hồ Chí Minh','1900 63 69 20',
     'https://bom.so/JjIOXa',
     'https://bom.so/Dgxb1I',
     'https://bom.so/CtBw9Z',
     'The New Gym có diện tích 2.000m2, gồm các khu vực tập luyện chính như Cardio, Strength, Free weights, Functional Training, Studio, Stretching, phù hợp với nhu cầu tập luyện cho mọi đối tượng.',
     10.760832460144496, 106.66690168835657,
     600, 2000),

    (16,12,1,'The New Gym Điện Biên Phủ','256 Điện Biên Phủ, Phường 7, Quận 3, Thành phố Hồ Chí Minh','1900 63 69 20',
     'https://bom.so/JjIOXa',
     'https://bom.so/d9i9v8',
     'https://bom.so/M7W2Cz',
     'The New Gym có diện tích 1.800m2 nằm ở vị trí đẹp trên đường Điện Biên Phủ, gồm các khu vực tập luyện chính như Cardio, Strength, Free weights, Functional Training, Studio, Stretching, phù hợp với nhu cầu tập luyện cho mọi đối tượng.',
     10.779821919040735, 106.68661522494476,
     400, 1800),

    (17,12,1,'The New Gym Hoàng Văn Thụ','Lầu 5, 1/1 Hoàng Việt, Phường 4, Quận Tân Bình, Thành phố Hồ Chí Minh','1900 63 69 20',
     'https://bom.so/JjIOXa',
     'https://bom.so/ZDph1b',
     'https://bom.so/GzOWcD',
     'The New Gym có diện tích không quá lớn nhưng lợi thế về vị trí thuận lợi view từ trên cao, gồm các khu vực tập luyện chính như Cardio, Strength, Free weights, Functional Training, Studio, Stretching, phù hợp với nhu cầu tập luyện cho mọi đối tượng.',
     10.797626444808325, 106.65933092494488,
     300, 1200),

    (18,12,1,'The New Gym Ung Văn Khiêm','58D Ung Văn Khiêm, Phường 25, Quận Bình Thạnh, Thành phố Hồ Chí Minh','1900 63 69 20',
     'https://bom.so/JjIOXa',
     'https://bom.so/FhQ0Nz',
     'https://bom.so/unAbjV',
     'The New Gym có lợi thế về vị trí đắc địa trên Quận Bình Thạnh, gồm các khu vực tập luyện chính như Cardio, Strength, Free weights, Functional Training, Studio, Stretching, phù hợp với nhu cầu tập luyện cho mọi đối tượng.',
     10.808073113779995, 106.71534072494497,
     500, 1800),

    (19,13,1,'VShape Fitness & Yoga Center, Trường Chinh','491/21-23 Đường Trường Chinh, Phường 14, Quận Tân Bình, Hồ Chí Minh','(08) 71080815',
     'https://bom.so/thNR4Y',
     'https://bom.so/6JicJC',
     'https://bom.so/AgiwNL',
     'Phòng tập gym VShape Fitness & Yoga Center tạo dựng hình tượng cho mình là 1 trung tâm thể dục thể hình – thẩm mỹ – yoga đáp ứng mọi nhu cầu về tập luyện thể hình và rèn luyện sức khỏe với mong muốn mang đến cho hội viên 1 không gian tập luyện chuyên nghiệp, thân thiện và luôn tạo niềm tin, niềm hứng thú tập luyện cho hội viên.',
     10.793479126084165, 106.65255131145184,
     200, 650),

    (20,13,1,'VShape Fitness & Yoga Center, Vincom Plus','Lầu 3 – 307 Nguyễn Duy Trinh, Quận 2, Hồ Chí Minh','(08) 71080815',
     'https://bom.so/thNR4Y',
     'https://bom.so/hoy8bD',
     'https://bom.so/ZKU6Rb',
     'Bạn là người yêu thích các bộ môn thể dục và mong muốn có được sức khoẻ tốt cùng với vóc dáng thon gọn, săn chắc? Hoặc bạn đã bắt đầu kế hoạch tập luyện nhưng chưa thể vượt qua các trở ngại và nỗi lo lắng? Đến với phòng tập gym VShape Fitness & Yoga quận 2, bạn sẽ được các huấn luyện viên hỗ trợ, tư vấn và chia sẻ kinh nghiệm trong các bài tập thể thao để bạn tận hưởng cuộc sống thật trọn vẹn và tràn đầy năng lượng.',
     10.789020254962827, 106.76547901599146,
     180, 550);

INSERT INTO gym_department_albums (gym_department_id, photo_url, description)

VALUES
    (12, 'https://bom.so/1KSXnM', 'Advance Fitness & Gym Tôn Dật Tiên'),
    (12, 'https://bom.so/7pEK02', 'Advance Fitness & Gym Tôn Dật Tiên'),
    (12, 'https://bom.so/7B2Mzt', 'Advance Fitness & Gym Tôn Dật Tiên'),
    (12, 'https://bom.so/voh8ng', 'Advance Fitness & Gym Tôn Dật Tiên'),
    (12, 'https://bom.so/UrVOOT', 'Advance Fitness & Gym Tôn Dật Tiên'),

    (13, 'https://bom.so/NOs6fZ', 'Advance Fitness & Gym Nguyễn Lương Bằng'),
    (13, 'https://bom.so/b4n4Tr', 'Advance Fitness & Gym Nguyễn Lương Bằng'),
    (13, 'https://bom.so/v8NMXD', 'Advance Fitness & Gym Nguyễn Lương Bằng'),
    (13, 'https://bom.so/WAPkb3', 'Advance Fitness & Gym Nguyễn Lương Bằng'),
    (13, 'https://bom.so/1KSXnM', 'Advance Fitness & Gym Nguyễn Lương Bằng'),

    (14, 'https://bom.so/B3eGLt', 'Advance Fitness & Gym Kỳ Đồng'),
    (14, 'https://bom.so/gBIrhB', 'Advance Fitness & Gym Kỳ Đồng'),
    (14, 'https://bom.so/rhbXsp', 'Advance Fitness & Gym Kỳ Đồng'),
    (14, 'https://bom.so/IpGdeE', 'Advance Fitness & Gym Kỳ Đồng'),
    (14, 'https://bom.so/mMsGvS', 'Advance Fitness & Gym Kỳ Đồng'),

    (15, 'https://bom.so/RXEacT', 'The New Gym Nguyễn Chí Thanh'),
    (15, 'https://bom.so/OOqz5t', 'The New Gym Nguyễn Chí Thanh'),
    (15, 'https://bom.so/aHSdjP', 'The New Gym Nguyễn Chí Thanh'),
    (15, 'https://bom.so/fvtDuD', 'The New Gym Nguyễn Chí Thanh'),
    (15, 'https://bom.so/NmhvHq', 'The New Gym Nguyễn Chí Thanh'),

    (16, 'https://bom.so/jvqb2d', 'The New Gym Điện Biên Phủ'),
    (16, 'https://bom.so/Fl0skO', 'The New Gym Điện Biên Phủ'),
    (16, 'https://bom.so/FUul95', 'The New Gym Điện Biên Phủ'),
    (16, 'https://bom.so/DALjJZ', 'The New Gym Điện Biên Phủ'),
    (16, 'https://bom.so/pKSRYy', 'The New Gym Điện Biên Phủ'),

    (17, 'https://bom.so/fxkFv8', 'The New Gym Hoàng Văn Thụ'),
    (17, 'https://bom.so/YkwevS', 'The New Gym Hoàng Văn Thụ'),
    (17, 'https://bom.so/SSL3g3', 'The New Gym Hoàng Văn Thụ'),
    (17, 'https://bom.so/4TdGCJ', 'The New Gym Hoàng Văn Thụ'),
    (17, 'https://bom.so/bHEgKJ', 'The New Gym Hoàng Văn Thụ'),
    (17, 'https://bom.so/gEy8zl', 'The New Gym Hoàng Văn Thụ'),
    (17, 'https://bom.so/6UNcfa', 'The New Gym Hoàng Văn Thụ'),
    (17, 'https://bom.so/azrzVF', 'The New Gym Hoàng Văn Thụ'),

    (18, 'https://bom.so/CHsVvw', 'The New Gym Ung Văn Khiêm'),
    (18, 'https://bom.so/6kSQgr', 'The New Gym Ung Văn Khiêm'),
    (18, 'https://bom.so/xUxfbR', 'The New Gym Ung Văn Khiêm'),
    (18, 'https://bom.so/j0sBh0', 'The New Gym Ung Văn Khiêm'),
    (18, 'https://bom.so/ZBgOWI', 'The New Gym Ung Văn Khiêm'),
    (18, 'https://bom.so/ET5kP6', 'The New Gym Ung Văn Khiêm'),
    (18, 'https://bom.so/PIvHmF', 'The New Gym Ung Văn Khiêm'),
    (18, 'https://bom.so/x9iNfH', 'The New Gym Ung Văn Khiêm'),
    (18, 'https://bom.so/ObkOBa', 'The New Gym Ung Văn Khiêm'),

    (19, 'https://bom.so/R9xEBp', 'VShape Fitness & Yoga Center, Trường Chinh'),
    (19, 'https://bom.so/v7aocI', 'VShape Fitness & Yoga Center, Trường Chinh'),
    (19, 'https://bom.so/4Vokpr', 'VShape Fitness & Yoga Center, Trường Chinh'),
    (19, 'https://bom.so/6fQg7m', 'VShape Fitness & Yoga Center, Trường Chinh'),
    (19, 'https://bom.so/eYWdAf', 'VShape Fitness & Yoga Center, Trường Chinh'),
    (19, 'https://bom.so/bCJ4rz', 'VShape Fitness & Yoga Center, Trường Chinh'),
    (19, 'https://bom.so/eOwx2D', 'VShape Fitness & Yoga Center, Trường Chinh'),


    (20, 'https://bom.so/VlE26M', 'VShape Fitness & Yoga Center, Vincom Plus'),
    (20, 'https://bom.so/pVfS6U', 'VShape Fitness & Yoga Center, Vincom Plus'),
    (20, 'https://bom.so/v7aocI', 'VShape Fitness & Yoga Center, Vincom Plus'),
    (20, 'https://bom.so/c3IBz2', 'VShape Fitness & Yoga Center, Vincom Plus'),
    (20, 'https://bom.so/dytfVw', 'VShape Fitness & Yoga Center, Vincom Plus'),
    (20, 'https://bom.so/qPjY4f', 'VShape Fitness & Yoga Center, Vincom Plus'),
    (20, 'https://bom.so/MxRM0d', 'VShape Fitness & Yoga Center, Vincom Plus');

INSERT INTO gym_department_schedule (gym_department_id, day, open_time, close_time)

VALUES
    (12, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (12, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (12, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (12, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (12, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (12, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (12, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    (13, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (13, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (13, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (13, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (13, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (13, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (13, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    (14, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (14, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (14, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (14, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (14, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (14, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (14, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    (15, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (15, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (15, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (15, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (15, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (15, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (15, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    (16, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (16, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (16, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (16, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (16, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (16, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (16, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    (17, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (17, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (17, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (17, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (17, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (17, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (17, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    (18, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (18, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (18, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (18, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (18, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (18, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (18, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    (19, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (19, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (19, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (19, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (19, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (19, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (19, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    (20, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (20, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (20, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (20, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (20, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (20, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (20, 'Chủ Nhật', '9:00 AM', '11:00 PM');

INSERT INTO gym_plan (gym_department_id, user_id, gym_plan_key, gym_plan_status_key, gym_plan_type_key, name, description,
                      price, price_per_hours, plan_sold, duration, plan_before_active_validity, plan_after_active_validity)

VALUES
    (12, 1, 1, 1, 1, 'Gói giờ',
     'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp. ',
     0, 100.00, 0, 0, 7, 14),

    (12, 1, 2, 1, 2, 'Gói 8 tuần',
     'Advance Fitness & Gym cam kết sẽ hoàn tiền 100% nếu học viên tập luyện nhưng không hiệu quả: giảm 5 kg/1 tháng cho người tập giảm cân và tăng cân, tăng cơ cho người gầy trong 8 tuần.',
     6000.00, 0, 0, 90, 10, 90),

    (12, 1, 3, 1, 2, 'Gói 20 buổi tập luyện với PT',
     'Có huấn luyện viên 1:1. Phòng tập rộng rãi, thoáng mát. Trang thiết bị hiện đại. Được kiểm tra sức khỏe đều đặn. Miễn phí các chương trình tập khác như Yoga, Spinning, Zumba….',
     10300.00, 0, 0, 30, 10, 30),

    (12,1, 2, 1, 2, 'Gói 30 buổi tập luyện với PT',
     'Có huấn luyện viên 1:1. Phòng tập rộng rãi, thoáng mát. Trang thiết bị hiện đại. Được kiểm tra sức khỏe đều đặn. Miễn phí các chương trình tập khác như Yoga, Spinning, Zumba….',
     13400.00, 0, 0, 30, 10, 30),

    (12,1, 2, 1, 2, 'Gói 50 buổi tập luyện với PT',
     'Có huấn luyện viên 1:1. Phòng tập rộng rãi, thoáng mát. Trang thiết bị hiện đại. Được kiểm tra sức khỏe đều đặn. Miễn phí các chương trình tập khác như Yoga, Spinning, Zumba….',
     2000.00, 0, 0, 30, 10, 30),

    (12,1, 2, 1, 2, 'Gói 100 buổi tập luyện với PT',
     'Có huấn luyện viên 1:1. Phòng tập rộng rãi, thoáng mát. Trang thiết bị hiện đại. Được kiểm tra sức khỏe đều đặn. Miễn phí các chương trình tập khác như Yoga, Spinning, Zumba….',
     35800.00, 0, 0, 30, 10, 30),

    (13,1, 1, 1, 1, 'Gói giờ',
     'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp. ',
     0, 100.00, 0, 0, 7, 14),

    (13,1, 2, 1, 2, 'Gói 8 tuần',
     'Advance Fitness & Gym cam kết sẽ hoàn tiền 100% nếu học viên tập luyện nhưng không hiệu quả: giảm 5 kg/1 tháng cho người tập giảm cân và tăng cân, tăng cơ cho người gầy trong 8 tuần.',
     6000.00, 0, 0, 90, 10, 90),

    (13,1, 2, 1, 2, 'Gói 20 buổi tập luyện với PT',
     'Có huấn luyện viên 1:1. Phòng tập rộng rãi, thoáng mát. Trang thiết bị hiện đại. Được kiểm tra sức khỏe đều đặn. Miễn phí các chương trình tập khác như Yoga, Spinning, Zumba….',
     10300.00, 0, 0, 30, 10, 30),

    (13,1, 2, 1, 2, 'Gói 30 buổi tập luyện với PT',
     'Có huấn luyện viên 1:1. Phòng tập rộng rãi, thoáng mát. Trang thiết bị hiện đại. Được kiểm tra sức khỏe đều đặn. Miễn phí các chương trình tập khác như Yoga, Spinning, Zumba….',
     13400.00, 0, 0, 30, 10, 30),

    (13,1, 2, 1, 2, 'Gói 50 buổi tập luyện với PT',
     'Có huấn luyện viên 1:1. Phòng tập rộng rãi, thoáng mát. Trang thiết bị hiện đại. Được kiểm tra sức khỏe đều đặn. Miễn phí các chương trình tập khác như Yoga, Spinning, Zumba….',
     2000.00, 0, 0, 30, 10, 30),

    (13,1, 2, 1, 2, 'Gói 100 buổi tập luyện với PT',
     'Có huấn luyện viên 1:1. Phòng tập rộng rãi, thoáng mát. Trang thiết bị hiện đại. Được kiểm tra sức khỏe đều đặn. Miễn phí các chương trình tập khác như Yoga, Spinning, Zumba….',
     35800.00, 0, 0, 30, 10, 30),

    (14,1, 1, 1, 1, 'Gói giờ',
     'Đây là một trong các hạng thẻ siêu tiết kiệm tại phòng gym của chúng tôi. Nếu bạn là người không có nhu cầu di chuyển nhiều và mong muốn được tập luyện ở bất kỳ khung giờ nào thì gói tập này sẽ là một lựa chọn thích hợp. ',
     0, 100.00, 0, 0, 7, 14),

    (14,1, 2, 1, 2, 'Gói 8 tuần',
     'Advance Fitness & Gym cam kết sẽ hoàn tiền 100% nếu học viên tập luyện nhưng không hiệu quả: giảm 5 kg/1 tháng cho người tập giảm cân và tăng cân, tăng cơ cho người gầy trong 8 tuần.',
     6000.00, 0, 0, 90, 10, 90),

    (14,1, 2, 1, 2, 'Gói 20 buổi tập luyện với PT',
     'Có huấn luyện viên 1:1. Phòng tập rộng rãi, thoáng mát. Trang thiết bị hiện đại. Được kiểm tra sức khỏe đều đặn. Miễn phí các chương trình tập khác như Yoga, Spinning, Zumba….',
     10300.00, 0, 0, 30, 10, 30),

    (14,1, 2, 1, 2, 'Gói 30 buổi tập luyện với PT',
     'Có huấn luyện viên 1:1. Phòng tập rộng rãi, thoáng mát. Trang thiết bị hiện đại. Được kiểm tra sức khỏe đều đặn. Miễn phí các chương trình tập khác như Yoga, Spinning, Zumba….',
     13400.00, 0, 0, 30, 10, 30),

    (14,1, 2, 1, 2, 'Gói 50 buổi tập luyện với PT',
     'Có huấn luyện viên 1:1. Phòng tập rộng rãi, thoáng mát. Trang thiết bị hiện đại. Được kiểm tra sức khỏe đều đặn. Miễn phí các chương trình tập khác như Yoga, Spinning, Zumba….',
     2000.00, 0, 0, 30, 10, 30),

    (14,1, 2, 1, 2, 'Gói 100 buổi tập luyện với PT',
     'Có huấn luyện viên 1:1. Phòng tập rộng rãi, thoáng mát. Trang thiết bị hiện đại. Được kiểm tra sức khỏe đều đặn. Miễn phí các chương trình tập khác như Yoga, Spinning, Zumba….',
     35800.00, 0, 0, 30, 10, 30),

    (15,1, 1, 1, 1, 'Gói giờ',
     'Gói linh hoạt tính theo giờ tại phòng tập gym của chúng tôi mang đến cho bạn sự thoải mái và linh hoạt tối đa trong việc rèn luyện sức khỏe. ',
     0, 150.00, 0, 0, 7, 14),

    (15,1, 2, 1, 2, 'Gói 1 tháng',
     'Thanh toán một lần. Tập luyện không giới hạn thời gian. Gửi xe miễn phí. Lớp học miễn phí. Nước uống miễn phí. Đo chỉ số Inbody. Buổi tập PT miễn phí.',
     2000.00, 0, 0, 90, 10, 90),

    (15,1, 2, 1, 2, 'Gói 6 tháng',
     'Miễn phí Hội viên. Thanh toán một lần. Tập luyện không giới hạn thời gian. Gửi xe miễn phí. Lớp học miễn phí. Nước uống miễn phí. Đo chỉ số Inbody. Buổi tập PT miễn phí.',
     5000.00, 0, 0, 30, 10, 30),

    (15,1, 2, 1, 2, 'Gói 12 tháng',
     'Miễn phí Hội viên. Thanh toán một lần. Tập luyện không giới hạn thời gian. Gửi xe miễn phí. Lớp học miễn phí. Nước uống miễn phí. Đo chỉ số Inbody. Buổi tập PT miễn phí.',
     9000.00, 0, 0, 30, 10, 30),

    (16,1, 1, 1, 1, 'Gói giờ',
     'Gói linh hoạt tính theo giờ tại phòng tập gym của chúng tôi mang đến cho bạn sự thoải mái và linh hoạt tối đa trong việc rèn luyện sức khỏe.',
     0, 150.00, 0, 0, 7, 14),

    (16,1, 2, 1, 2, 'Gói 1 tháng',
     'Thanh toán một lần. Tập luyện không giới hạn thời gian. Gửi xe miễn phí. Lớp học miễn phí. Nước uống miễn phí. Đo chỉ số Inbody. Buổi tập PT miễn phí.',
     2000.00, 0, 0, 90, 10, 90),

    (16,1, 2, 1, 2, 'Gói 6 tháng',
     'Miễn phí Hội viên. Thanh toán một lần. Tập luyện không giới hạn thời gian. Gửi xe miễn phí. Lớp học miễn phí. Nước uống miễn phí. Đo chỉ số Inbody. Buổi tập PT miễn phí.',
     5000.00, 0, 0, 30, 10, 30),

    (16,1, 2, 1, 2, 'Gói 12 tháng',
     'Miễn phí Hội viên. Thanh toán một lần. Tập luyện không giới hạn thời gian. Gửi xe miễn phí. Lớp học miễn phí. Nước uống miễn phí. Đo chỉ số Inbody. Buổi tập PT miễn phí.',
     9000.00, 0, 0, 30, 10, 30),

    (17,1, 1, 1, 1, 'Gói giờ',
     'Gói linh hoạt tính theo giờ tại phòng tập gym của chúng tôi mang đến cho bạn sự thoải mái và linh hoạt tối đa trong việc rèn luyện sức khỏe.',
     0, 150.00, 0, 0, 7, 14),

    (17,1, 2, 1, 2, 'Gói 1 tháng',
     'Thanh toán một lần. Tập luyện không giới hạn thời gian. Gửi xe miễn phí. Lớp học miễn phí. Nước uống miễn phí. Đo chỉ số Inbody. Buổi tập PT miễn phí.',
     2000.00, 0, 0, 90, 10, 90),

    (17,1, 2, 1, 2, 'Gói 6 tháng',
     'Miễn phí Hội viên. Thanh toán một lần. Tập luyện không giới hạn thời gian. Gửi xe miễn phí. Lớp học miễn phí. Nước uống miễn phí. Đo chỉ số Inbody. Buổi tập PT miễn phí.',
     5000.00, 0, 0, 30, 10, 30),

    (17,1, 2, 1, 2, 'Gói 12 tháng',
     'Miễn phí Hội viên. Thanh toán một lần. Tập luyện không giới hạn thời gian. Gửi xe miễn phí. Lớp học miễn phí. Nước uống miễn phí. Đo chỉ số Inbody. Buổi tập PT miễn phí.',
     9000.00, 0, 0, 30, 10, 30),

    (18,1, 1, 1, 1, 'Gói giờ',
     'Gói linh hoạt tính theo giờ tại phòng tập gym của chúng tôi mang đến cho bạn sự thoải mái và linh hoạt tối đa trong việc rèn luyện sức khỏe.',
     0, 150.00, 0, 0, 7, 14),

    (18,1, 2, 1, 2, 'Gói 1 tháng',
     'Thanh toán một lần. Tập luyện không giới hạn thời gian. Gửi xe miễn phí. Lớp học miễn phí. Nước uống miễn phí. Đo chỉ số Inbody. Buổi tập PT miễn phí.',
     2000.00, 0, 0, 90, 10, 90),

    (18,1, 2, 1, 2, 'Gói 6 tháng',
     'Miễn phí Hội viên. Thanh toán một lần. Tập luyện không giới hạn thời gian. Gửi xe miễn phí. Lớp học miễn phí. Nước uống miễn phí. Đo chỉ số Inbody. Buổi tập PT miễn phí.',
     5000.00, 0, 0, 30, 10, 30),

    (18,1, 2, 1, 2, 'Gói 12 tháng',
     'Miễn phí Hội viên. Thanh toán một lần. Tập luyện không giới hạn thời gian. Gửi xe miễn phí. Lớp học miễn phí. Nước uống miễn phí. Đo chỉ số Inbody. Buổi tập PT miễn phí.',
     9000.00, 0, 0, 30, 10, 30),

    (19, 1, 1, 1, 1, 'Gói giờ',
     'Gói linh hoạt tính theo giờ của phòng tập gym là sự lựa chọn hoàn hảo cho những người có lịch trình bận rộn hoặc muốn tận hưởng sự thoải mái và linh hoạt trong việc tập luyện.',
     0, 150.00, 0, 0, 7, 14),

    (19,1, 2, 1, 2, 'Gói 1 tháng',
     'VShape Fitness cung cấp cho hội viên các dịch vụ: fitness, gym, yoga, xông hơi khô, khu vực trang điểm, bãi giữ xe rộng rãi miễn phí….',
     2000.00, 0, 0, 90, 10, 90),

    (19,1, 2, 1, 2, 'Gói 6 tháng',
     'VShape Fitness cung cấp cho hội viên các dịch vụ: fitness, gym, yoga, xông hơi khô, khu vực trang điểm, bãi giữ xe rộng rãi miễn phí….',
     5000.00, 0, 0, 30, 10, 30),

    (19,1, 2, 1, 2, 'Gói 12 tháng',
     'VShape Fitness cung cấp cho hội viên các dịch vụ: fitness, gym, yoga, xông hơi khô, khu vực trang điểm, bãi giữ xe rộng rãi miễn phí….',
     9000.00, 0, 0, 30, 10, 30),

    (20,1, 1, 1, 1, 'Gói giờ',
     'Gói linh hoạt tính theo giờ của phòng tập gym là sự lựa chọn hoàn hảo cho những người có lịch trình bận rộn hoặc muốn tận hưởng sự thoải mái và linh hoạt trong việc tập luyện.',
     0, 150.00, 0, 0, 7, 14),

    (20,1, 2, 1, 2, 'Gói 1 tháng',
     'VShape Fitness cung cấp cho hội viên các dịch vụ: fitness, gym, yoga, xông hơi khô, khu vực trang điểm, bãi giữ xe rộng rãi miễn phí….',
     2000.00, 0, 0, 90, 10, 90),

    (20,1, 2, 1, 2, 'Gói 6 tháng',
     'VShape Fitness cung cấp cho hội viên các dịch vụ: fitness, gym, yoga, xông hơi khô, khu vực trang điểm, bãi giữ xe rộng rãi miễn phí….',
     5000.00, 0, 0, 30, 10, 30),

    (20, 1, 2, 1, 2, 'Gói 12 tháng',
     'VShape Fitness cung cấp cho hội viên các dịch vụ: fitness, gym, yoga, xông hơi khô, khu vực trang điểm, bãi giữ xe rộng rãi miễn phí….',
     9000.00, 0, 0, 30, 10, 30);

INSERT INTO user_feedback
(user_id, department_id, rating, comments, feedback_time, feedback_status)

VALUES
    (1, 12, 4, 'Phòng tập cùng các thiết bị rất sạch sẽ và thoải mái.', NOW(),1 ),
    (1, 13, 4, 'Phòng tập cùng các thiết bị rất sạch sẽ và thoải mái.', NOW(),1 ),
    (1, 14, 4, 'Phòng tập cùng các thiết bị rất sạch sẽ và thoải mái.', NOW(),1 ),
    (1, 15, 4, 'Phòng tập cùng các thiết bị rất sạch sẽ và thoải mái.', NOW(),1 ),
    (1, 16, 4, 'Phòng tập cùng các thiết bị rất sạch sẽ và thoải mái.', NOW(),1 ),
    (1, 17, 4, 'Phòng tập cùng các thiết bị rất sạch sẽ và thoải mái.', NOW(),1 ),
    (1, 18, 4, 'Phòng tập cùng các thiết bị rất sạch sẽ và thoải mái.', NOW(),1 ),
    (1, 19, 4, 'Phòng tập cùng các thiết bị rất sạch sẽ và thoải mái.', NOW(),1 ),
    (1, 20, 4, 'Phòng tập cùng các thiết bị rất sạch sẽ và thoải mái.', NOW(),1 ),

    (2, 12, 3, 'Phòng tập có không khí tốt nhưng thiếu một số thiết bị cần thiết.', NOW(), 1),
    (2, 13, 5, 'Rất hài lòng với dịch vụ, nhân viên phục vụ nhiệt tình.', NOW(), 1),
    (2, 14, 4, 'Thiết bị ở phòng tập rất chất lượng, giúp tôi có buổi tập hiệu quả.', NOW(), 1),
    (2, 15, 3, 'Phòng tập có không khí tốt nhưng thiếu một số thiết bị cần thiết.', NOW(), 1),
    (2, 16, 5, 'Rất hài lòng với dịch vụ, nhân viên phục vụ nhiệt tình.', NOW(), 1),
    (2, 17, 4, 'Thiết bị ở phòng tập rất chất lượng, giúp tôi có buổi tập hiệu quả.', NOW(), 1),
    (2, 18, 3, 'Phòng tập có không khí tốt nhưng thiếu một số thiết bị cần thiết.', NOW(), 1),
    (2, 19, 5, 'Rất hài lòng với dịch vụ, nhân viên phục vụ nhiệt tình.', NOW(), 1),
    (2, 20, 4, 'Thiết bị ở phòng tập rất chất lượng, giúp tôi có buổi tập hiệu quả.', NOW(), 1),

    (3, 12, 4, 'Phòng tập cùng các thiết bị rất sạch sẽ và thoải mái.', NOW(),1 ),
    (3, 13, 3, 'Phòng tập có không khí tốt nhưng thiếu một số thiết bị cần thiết.', NOW(), 1),
    (3, 14, 5, 'Rất hài lòng với dịch vụ, nhân viên phục vụ nhiệt tình.', NOW(), 1),
    (3, 15, 4, 'Thiết bị ở phòng tập rất chất lượng, giúp tôi có buổi tập hiệu quả.', NOW(), 1),
    (3, 16, 3, 'Phòng tập có không khí tốt nhưng thiếu một số thiết bị cần thiết.', NOW(), 1),
    (3, 17, 2, 'Gặp một số vấn đề với việc bảo dưỡng thiết bị, mong cải thiện.', NOW(), 1),
    (3, 18, 3, 'Phòng tập cần được làm mới, không gian hơi chật hẹp.', NOW(), 1),
    (3, 19, 2, 'Gặp một số vấn đề với việc bảo dưỡng thiết bị, mong cải thiện.', NOW(), 1),
    (3, 20, 3, 'Phòng tập cần được làm mới, không gian hơi chật hẹp.', NOW(), 1),

    (4, 12, 4, 'Phòng tập cùng các thiết bị rất sạch sẽ và thoải mái.', NOW(),1 ),
    (4, 13, 3, 'Phòng tập có không khí tốt nhưng thiếu một số thiết bị cần thiết.', NOW(), 1),
    (4, 14, 5, 'Rất hài lòng với dịch vụ, nhân viên phục vụ nhiệt tình.', NOW(), 1),
    (4, 15, 4, 'Thiết bị ở phòng tập rất chất lượng, giúp tôi có buổi tập hiệu quả.', NOW(), 1),
    (4, 16, 3, 'Phòng tập có không khí tốt nhưng thiếu một số thiết bị cần thiết.', NOW(), 1),
    (4, 17, 2, 'Gặp một số vấn đề với việc bảo dưỡng thiết bị, mong cải thiện.', NOW(), 1),
    (4, 18, 3, 'Phòng tập cần được làm mới, không gian hơi chật hẹp.', NOW(), 1),
    (4, 19, 4, 'Rất thích không gian và cách bố trí thiết bị ở phòng tập này.', NOW(), 1),
    (4, 20, 5, 'Nhân viên tư vấn rất nhiệt tình và giúp đỡ.', NOW(), 1),

    (5, 12, 5, 'Rất hài lòng với dịch vụ, nhân viên phục vụ nhiệt tình.', NOW(), 1),
    (5, 13, 4, 'Thiết bị ở phòng tập rất chất lượng, giúp tôi có buổi tập hiệu quả.', NOW(), 1),
    (5, 14, 3, 'Phòng tập có không khí tốt nhưng thiếu một số thiết bị cần thiết.', NOW(), 1),
    (5, 15, 2, 'Gặp một số vấn đề với việc bảo dưỡng thiết bị, mong cải thiện.', NOW(), 1),
    (5, 16, 3, 'Phòng tập cần được làm mới, không gian hơi chật hẹp.', NOW(), 1),
    (5, 17, 4, 'Rất thích không gian và cách bố trí thiết bị ở phòng tập này.', NOW(), 1),
    (5, 18, 5, 'Nhân viên tư vấn rất nhiệt tình và giúp đỡ.', NOW(), 1),
    (5, 19, 3, 'Cần cải thiện về việc duy trì sự sạch sẽ của phòng tập.', NOW(), 1),
    (5, 20, 4, 'Được biết đến qua bạn bè và không hối hận khi đăng ký thành viên.', NOW(), 1);


INSERT INTO brand (brand_id,user_id,name,logo_url,wallpaper_url,thumbnail_url,description,rating,contact_number,contact_email,brand_status_key)
VALUES

    /***************************************** Kickfit Sport Brand ******************************************************/
    (1,1,'Kickfit Sport',
     'https://bom.so/CHjTsR',
     'https://kickfit-sports.com/wp-content/uploads/2022/06/Co-so-vat-chat-tai-Kickfit-Sports-dat-chuan-chat-luong-cao.png.webp',
     'https://kickfitvietnam.com/wp-content/uploads/2023/02/trai-nghiem-phong-tap-7.jpg',
     'Kickfit Sports đã đang và sẽ phát triển theo hướng: “Chuyên nghiệp – Chất Lượng – Luôn đổi mới và hoàn thiện” đồng thời mong muốn mang lại sức khỏe và thịnh vượng cho hàng triệu người Việt Nam.Với hơn 10 năm xây dựng và phát triển, hiện tại Kickfit Sports đang sở hữu 14 cơ sở phòng tập trải rộng khắp Hà Nội. Trung tâm luôn chú trọng đầu tư, cải tiến các sản phẩm, dịch vụ để mang đến môi trường tập luyện 5 sao, uy tín và chất lượng.',
     5,'093 63 999 88','hethong@kickfit-sports.com',1),

    /****************************************** 25 FIT Brand *****************************************************/
    (2,1,'25 Fit',
     'https://shorturl.at/nwTUX',
     'https://shorturl.at/xEFNR',
     'https://phongtap.25fit.net/hubfs/Image%20from%20iOS.jpg',
     '25 Fit là viết tắt của cụm từ 25 minutes to get fit, là hệ thống phòng tập công nghệ EMS đầu tiên. EMS là phương pháp dùng xung điện để tác động, kích thích cơ bắp phát triển và đánh tan mỡ thừa.
     Với phương pháp này bạn không cần dùng tạ nên cũng không cần lo ngại về chấn thương, chỉ cần tập luyện 25 phút và 1 – 2 buổi/ tuần đã có thể đạt kết quả giảm mỡ nhanh hơn, tiết kiệm tối ưu thời gian.
     Trang thiết bị nhập khẩu từ Đức, đặc biệt là tại phòng tập 25 FIT, quần áo và thức uống pre-workout sẽ được chuẩn bị sẵn và miễn phí.',
     4.7,'18006176','welisten@25fit.net',1),

    /******************************************** S'Life Brand ***************************************************/
    (3,1,'S’Life Gym & Yoga',
     'https://shorturl.at/jrP35',
     'https://shorturl.at/foNUZ',
     'https://shorturl.at/eyENT',
     '"S’Life là hệ thống phòng tập thể hình không gian xanh, môi trường lý tưởng để tập luyện với trang thiết bị hiện đại cùng huấn luyện viên chuyên nghiệp sẽ giúp hội viên đạt hiệu quả tập luyện cao nhất.
Bài tập luyện của học viện đều được huấn luyện viên của S’Life cá nhân hóa để phù hợp với thể trạng của mỗi người."',
     4.7,'0902 635 124',' slifegym@gmail.com',1),

    /********************************************* Diamond Fitness Brand **************************************************/
    (4,1,'Diamond Fitness Center',
     'https://shorturl.at/iFO17',
     'https://bom.so/V0CxT4',
     'https://shorturl.at/blpK4',
     '"Diamond Fitness Center tự hào đem lại trải nghiệm tốt nhất cho bạn với hệ thống gồm 14 câu lạc bộ đẳng cấp 5 sao. Được trang bị các thiết bị hiện đại, cùng hệ thống hồ bơi trong nhà, phòng tập Yoga, Kick Boxing, Thiền,…
Sự khác biệt của thương hiệu này, chính là chương trình đo khám chỉ số BMI. Thông qua chỉ số này, bạn sẽ hiểu hơn về tình trạng cơ thể của mình và từ đó xác định nhu cầu của cơ thể là gì.
Dựa vào BMI, huấn luyện viên của Diamond Fitness sẽ vạch ra lịch tập cũng như bài tập phù hợp với nhu cầu hoàn thiện cơ thể của bạn."',
     4.5,'(086) 212-3247','diamondfitness@gmail.com',1);

INSERT INTO brand_amenities (brand_id, photo_url, amenitie_name, description,amenitie_status)
VALUES

    /******************************************** Kickfit Sport Amenities ***************************************************/
    (1,'https://bom.so/FZ3XO5','Hệ thống cơ sở rộng lớn',
     'Hệ thống cơ sở của chúng tôi là một mạng lưới các trung tâm thể dục hiện đại và chuyên nghiệp, cung cấp dịch vụ tốt nhất để giúp bạn đạt được mục tiêu về sức khỏe và thể lực.',
     1),
    (1,'https://bom.so/0fv3by','Chăm sóc khách hàng tận tâm',
     'Với một cam kết không ngừng nỗ lực, Kickfit tạo ra một môi trường chuyên nghiệp, thân thiện và đồng điệu, nơi mỗi khách hàng được coi là cá nhân độc lập với nhu cầu và mong muốn riêng.',
     1),
    (1,'https://bom.so/eXr75Q','Khóa học đa dạng',
     'Với các khóa học từ cấp độ beginner đến advanced, chúng tôi tự tin cung cấp cho bạn những trải nghiệm tập luyện độc đáo và hiệu quả.',
     1),
    (1,'https://bom.so/NnIaoB','ĐỘI NGŨ HUẤN LUYỆN VIÊN',
     'Đội ngũ huấn luyện viên của Kickfit Sports được sàng lọc kĩ càng, phải trải qua giai đoạn trainning ít nhất 3 tháng để có đủ kĩ năng giao tiếp và kiến thức chuyên môn hướng dẫn cho khách hàng',
     1),

    /********************************************** 25 FIT Amenities *************************************************/
    (2,'https://bom.so/zUwK5C','Công nghê EMS Training',
     'EMS Training là phương pháp tập luyện sử dụng xung điện kích thích cơ bắp với hiệu quả cao, tiết kiệm thời gian và tăng cường sức mạnh cơ bắp.',
     1),
    (2,'https://bom.so/zgjE5G','Cơ sở vật chất hiện đại',
     'Chương trình tập luyện cá nhân được thiết kế dựa trên nhu cầu và mục tiêu riêng của từng khách hàng. Huấn luyện viên chuyên nghiệp sẽ làm việc một cách tận tâm với bạn, giúp bạn tập trung vào mục tiêu tập luyện và đạt được kết quả tốt nhất.',
     1),
    (2,'https://bom.so/ndsemq','Hướng dẫn từ chuyên gia',
     'Phòng tập 25 Fit được thiết kế với sự chú trọng đến không gian sử dụng hiệu quả và tiện ích. Các khu vực tập luyện được bố trí hợp lý, tạo cảm giác rộng rãi và thoải mái cho các thành viên.',
     1),
    (2,'https://bom.so/UaoDeA','Phương pháp cá nhân hóa',
     'Phòng tập 25 Fit cung cấp hướng dẫn và hỗ trợ từ chuyên gia để đảm bảo rằng thành viên nhận được sự chỉ dẫn chuyên nghiệp và hỗ trợ trong quá trình tập luyện.',
     1),

    /****************************************** S'Life Amenities *****************************************************/
    (3,'https://bom.so/QlqoEO','Không gian xanh rộng 4.000m2',
     'Hệ thống lọc khí ion cùng trang thiết bị cao cấp nhập khẩu trực tiếp từ Mỹ, đem đến môi trường tập luyện tốt nhất',
     1),
    (3,'https://bom.so/I9e2Am','Dịch Vụ Đẳng Cấp',
     'Miễn phí xông hơi, phòng tắm, nước uống detox, khăn thêu tên cá nhân, phục vụ tốt nhất quá trình luyện tập của hội viên',
     1),
    (3,'https://bom.so/RARYPt','Cam kết hiệu quả tập luyện',
     '100% hội viên đã đạt được kết quả tập luyện khi thực hiện đúng chương trình Cá Nhân Hóa của S''LIFE',
     1),
    (3,'https://bom.so/9ofjlh','Huấn luyện viên chuẩn quốc tế',
     'Chứng chỉ NASM Hoa Kỳ, 5 năm kinh nghiệm, động viên, theo sát quá trình luyện tập, đem lại vóc dáng cân đối cho hàng ngàn hội viên',
     1),

    /**************************************** Diamond Fitness Amenities *******************************************************/
    (4,'https://bom.so/9vIWSg','Hệ thống CLB 5 sao',
     'Diamond Fitness là một phòng tập thể hình thuộc hệ thống CLB 5 sao cao cấp. Với môi trường sang trọng và tiện nghi, chúng tôi cam kết mang đến cho bạn trải nghiệm tập luyện đẳng cấp và đáng nhớ.',
     1),
    (4,'https://bom.so/FFazQ1','Dịch vụ tập luyện đa dạng',
     'Chúng tôi cung cấp một loạt dịch vụ tập luyện đa dạng nhằm đáp ứng nhu cầu và mục tiêu của khách hàng, mang lại nhiều trải nghiệm tập luyện khác nhau và mới lạ',
     1),
    (4,'https://bom.so/yZ81es','Điểm đến của người nổi tiếng',
     'Tự hào là cơ sở được hàng ngàn hội viên cũng như những nhân vật nổi tiếng như Angela Phương Trinh, Lương Thế Thành, .. tin dùng và lựa chọn',
     1),
    (4,'https://bom.so/fDf1YR','Đội ngũ HLV giàu kinh nghiệm',
     'Đội ngũ huấn luyện viên chuyên nghiệp và giàu kinh nghiệm của chúng tôi sẽ đồng hành cùng bạn trong hành trình tập luyện. Họ sẽ cung cấp hướng dẫn chuyên môn, tư vấn dinh dưỡng và lập kế hoạch tập luyện cá nhân hóa dựa trên mục tiêu và nhu cầu của bạn.',
     1);

INSERT INTO gym_department (gym_department_id, brand_id,gym_department_status_key, name, address, contact_number,
                            logo_url, wallpaper_url, thumbnail_url,description, latitude, longitude, capacity, area)
VALUES
    /************************************************ Kickfit Sport Departments ***********************************************/
    (21,1,1,'Kickfit Sports Nguyễn Phong Sắc',
     'Tầng 03 Tòa nhà Lâm Viễn, 107 Đ. Nguyễn Phong Sắc, Hà Nội','0903411257',
     'https://bom.so/CHjTsR',
     'https://bom.so/YJ7xgS',
     'https://bom.so/lNBYrM',
     'Kickfit Sports Nguyễn Phong Sắc được biết đến là phòng tập kickfit chất lượng tốt nhất tại quận Cầu Giấy, địa chỉ quen thuộc của giới yêu võ thuật trên địa bàn Hà Nội. Không gian tập luyện có diện tích gần 1000m2 được phân chia theo từng khu riêng biệt, hợp lý đảm bảo hội viên có không gian riêng khi tập luyện và thư giãn.',
     21.040954973167608, 105.79023379536018,200,1000
    ),
    (22,1,1,'Kickfit Sports Trần Duy Hưng',
     'Ngõ 196 Đ. Trần Duy Hưng, Trung Hoà, Cầu Giấy, Hà Nội','0903411257',
     'https://bom.so/CHjTsR',
     'https://bit.ly/kickfit-tranduyhung',
     'https://shorturl.at/vyJ17',
     'Kickfit Sports Trần Duy Hưng được ra đời vào năm 2012 và là cơ sở đầu tiên của chuỗi phòng tập Kickfit Sports. Dịch vụ phòng tập chủ yếu về các bộ môn võ đối kháng hỗ trợ giảm cân, tăng cơ, cải thiện vóc dáng cũng như trang bị các kỹ năng phòng vệ hiệu quả giúp xử lý các tình huống nguy hiểm trong cuộc sống. Với không gian rộng rãi, cơ sở vật chất hiện đại tối tân, Kickfit Sports Trần Duy Hưng mang tới cho hội viên sự thoải mái, thư giãn sau những giờ học tập và làm việc mệt mỏi.',
     21.009542311744028, 105.79714248159206,200,1000
    ),
    (23,1,1,'Kickfit Sports Thiên Đường Bảo Sơn',
     'Tầng 5, Tòa Gemek 1, Thiên Đường Bảo Sơn, Hoài Đức, Hà Nội','093 63 999 88',
     'https://bom.so/CHjTsR',
     'https://shorturl.at/finvz',
     'https://shorturl.at/DHVX2',
     'Kickfit Sports Thiên Đường Bảo Sơn tọa lạc tại vị trí đắc địa thuộc toà Gemek 1 ngay cổng chào của Thiên Đường Bảo Sơn. Đây một trong những cơ sở phòng tập được đầu tư lớn với nhiều trang thiết bị hiện đại và tiện ích sang chảnh mang đến trải nghiệm tập luyện thể thao tuyệt vời nhất cho khách hàng. Kickfit Sports Thiên Đường Bảo Sơn tự hào khi nhận được vô số những đánh giá tốt từ khách hàng và số lượng người đăng ký tập luyện ngày càng đông đảo.',
     21.00855235502214, 105.7307156200674,400,1500
    ),
    (24,1,1,'Kickfit Sports Metro Hà Đông',
     'Tầng 2, Siêu thị Metro - Melinh Plaza, Hà Đông','093 63 999 88',
     'https://bom.so/CHjTsR',
     'https://shorturl.at/BCFI3',
     'https://shorturl.at/boPZ1',
     'Kickfit Sports Metro Hà Đông có quy mô diện tích lớn, cung cấp đa dạng các loại hình tập luyện từ A-Z gồm cả võ thuật và thể hình. Lợi thế tọa lạc tại Mê Linh Plaza, Kickfit Sports được nhiều hội viên lựa chọn bởi đẳng cấp 5 sao cùng dịch vụ tuyệt vời, trang thiết bị hiện đại tạo cảm giác an tâm, thoải mái.',
     20.964556785162678, 105.77230071391557,280,800
    ),

    /************************************************ 25 FIT Departments ***********************************************/
    (25,2,1,'25 FIT Lý Thường Kiệt',
     '42Z P. Lý Thường Kiệt, Tràng Tiền, Hoàn Kiếm, Hà Nội','18006176',
     'https://shorturl.at/nwTUX',
     'https://g2.by/f1WS',
     'https://by.tn/oTZP',
     '25 FIT Lý Thường Kiệt là studio đầu tiên ở Hà Nội đánh dấu những bước tiến quan trọng của 25 FIT tại khu vực phía Bắc trong việc mang công nghệ EMS đến với người dùng thủ đô',
     21.023985014649746,105.85026204718575,5,60
    ),
    (26,2,1,'25 FIT Duy Tân',
     '22 Duy Tân, Cầu Giấy, Hà Nội Hà Nội','18006176',
     'https://shorturl.at/nwTUX',
     'https://shorturl.at/gjls2',
     'https://g2.by/baBC',
     'Nằm tại cung đường sầm uất và nhộn nhịp, tiếp giáp khu dân cư, văn phòng, studio mới 25 FIT Duy Tân hứa hẹn sẽ đem đến cho khách hàng những trải nghiệm tập luyện giá trị tốt nhất về sức khỏe, dịch vụ với công nghệ EMS Training hiện đại, đảm bảo tiết kiệm thời gian.',
     21.030865188489173,105.78566187313876,5,60
    ),
    (27,2,1,'25 FIT Times City',
     'T1, SO.02, Khu đô thị Times City, Hai Bà Trưng, Hà Nội','18006176',
     'https://shorturl.at/nwTUX',
     'https://shorturl.at/lwLT5',
     'https://shorturl.at/jCESV',
     'Đặt ở vị trí thuận tiện tại khu dân cư Times City, Quận Hai Bà Trưng. Chỉ 20 phút tập, 2 buổi/tuần cùng huấn luyện viên chuyên nghiệp, quý khách không cần mang bất cứ thứ gì kể cả quần áo hay giày tập để có thể đạt kết quả tăng cơ, giảm mỡ.',
     20.99661352642664,105.86945587284531,5,55
    ),
    (28,2,1,'25 FIT Vinhomes Gardenia Hàm Nghi',
     'B17 05A Shophouse Vinhomes Gardenia, P. Hàm Nghi, Cầu Diễn, Nam Từ Liêm, Hà Nội.','18006176',
     'https://shorturl.at/nwTUX',
     'https://g2.by/RBe2',
     'https://shorturl.at/fCUY9',
     'Chính thức từ ngày 23/07/2022 - studio 25 FIT Hàm Nghi đi vào hoạt động, sẵn sàng chào đón quý hội viên trải nghiệm công nghệ EMS Training mới tại B17.05A Shophouse Vinhomes Gardenia, Hàm Nghi, Phường Cầu Diễn, Quận Nam Từ Liêm, Hà Nội.',
     21.035466912246545,105.76214903467383,6,70
    ),

    /********************************************** S'Life Departments *************************************************/
    (29,3,1,'Phòng tập Gym S''Life Quận 10',
     '575 Đ. Sư Vạn Hạnh, Phường 12, Quận 10, Thành phố Hồ Chí Minh','(093) 889-1436',
     'https://shorturl.at/jrP35',
     'https://t.ly/Y_iVF',
     'https://shorturl.at/cMUWX',
     'Nổi bật khắp HCM với không gian xanh đẳng cấp rộng hơn 3.000m2 và đầy đủ bộ môn, S''Life Quận 10 sẽ mang đến cho bạn những trải nghiệm tuyệt vời và hiệu quả nhất.',
     10.776571562971277,106.6669378322132,400,3000
    ),
    (30,3,1,'Phòng tập Gym S''Life Quận 6',
     '893 Đ. Hậu Giang, P.11, Quận 6, Thành phố Hồ Chí Minh','(090) 263-5124',
     'https://shorturl.at/jrP35',
     'https://t.ly/XG2SC',
     'https://shorturl.at/ivIPW',
     'Bạn đang tìm kiếm phòng tập tại quận 6? S''Life GYM chính là sự lựa chọn dành cho bạn với chất lượng hàng đầu',
     10.746277023301626,106.62821508856594,300,2000
    ),
    (31,3,1,'Phòng tập Gym S''Life Bình Chánh',
     '34 Đ. Phạm Hùng, Bình Hưng, Bình Chánh, Thành phố Hồ Chí Minh','(090) 263-5124',
     'https://shorturl.at/jrP35',
     'https://t.ly/i-rgw',
     'https://shorturl.at/gzBQ1',
     '"Với không gian đẳng cấp và sang trọng, S''Life GYM Bình Chánh chính là sự lựa chọn hoàn hảo dành cho bạn để cải thiện sức khỏe và vóc dáng hiệu quả.',
     10.73050975241317,106.67597421199494,260,1800
    ),
    (32,3,1,'Phòng tập Gym S''Life Bình Thạch',
     '26 Nguyễn Huy Lượng, Phường 14, Bình Thạnh, Thành phố Hồ Chí Minh, Vietnam','(090) 263-5124',
     'https://shorturl.at/jrP35',
     'https://shorturl.at/nwKU7',
     'https://shorturl.at/FLNTY',
     '"Bạn đang tìm phòng tập để cải thiện sức khỏe và vóc dáng? S''Life GYM chính là sự lựa chọn dành cho bạn.',
     10.805837559943653,106.69624297299924,350,2400
    ),

    /******************************************** Diamond Fitness Departments ***************************************************/
    (33,4,1,'Diamond Fitness Center Trần Quang Khải',
     'Cao ốc Horizon, 214 Đ. Trần Quang Khải, P, Quận 1, Thành phố Hồ Chí Minh','02866569555',
     'https://shorturl.at/iFO17',
     'https://shorturl.at/pBCGP',
     'https://shorturl.at/zCJ59',
     'DIAMOND FITNESS CENTER mang trong mình sứ mệnh “Chăm sóc sức khỏe và vẻ đẹp toàn diện của Bạn”, hướng dẫn và mang đến cho bạn một chương trình luyện tập cho cả thể chất và tinh thần.
Khám phá và trải nghiệm một lối sống khỏe mạnh và tích cực nhất tại DIAMOND FITNESS CENTER ngay bạn nhé!',
     10.79154285000631,106.68804505835055,650,6600
    ),
    (34,4,1,'Diamond Fitness Center Lê Văn Sỹ',
     '338 Đ. Lê Văn Sỹ, Phường 14, Quận 3, Thành phố Hồ Chí Minh','02866825544',
     'https://shorturl.at/iFO17',
     'https://t.ly/jVwO9',
     'https://shorturl.at/wxCRT',
     'DIAMOND FITNESS CENTER mang trong mình sứ mệnh “Chăm sóc sức khỏe và vẻ đẹp toàn diện của Bạn”, hướng dẫn và mang đến cho bạn một chương trình luyện tập cho cả thể chất và tinh thần.
Khám phá và trải nghiệm một lối sống khỏe mạnh và tích cực nhất tại DIAMOND FITNESS CENTER ngay bạn nhé!',
     10.78743076402733,106.67907250244333,120,576
    ),
    (35,4,1,'Diamond Fitness Paragon Lê Hồng Phong',
     'TTTM Maslight, lô 20 Đ. Lê Hồng Phong, Đông Khê, Ngô Quyền, Hải Phòng.','02256626686',
     'https://shorturl.at/iFO17',
     'https://t.ly/F92Dw',
     'https://t.ly/kyaTf',
     'Viên kim cương khổng lồ Diamond Fitness Paragon tại TD Plaza, Lê Hồng Phong, TP Hải Phòng là dự án lớn nhất TP Hải Phòng với dòng thiết bị cao cấp nhất Hàn Quốc – DRAX cùng 2 phân khu đẳng cấp nhất.',
     20.846699998604187,106.70741856252596,400,3000
    ),
    (36,4,1,'Diamond Fitness Paragon Bạch Đằng',
     '97 Bạch Đằng, Hạ Lý, Hồng Bàng, Hải Phòng','(089) 916-4562',
     'https://shorturl.at/iFO17',
     'https://t.ly/IkZum',
     'https://t.ly/HPuva',
     'Diamond Fitness Paragon Bạch Đằng Hải Phòng tọa lạc ngay vị trí trung tâm của thành phố hoa phượng đỏ, được đầu tư lên đến 40 tỷ đồng với quy mô diện tích hơn 5.000m2, thiết kế hiện đại, sang trọng chuẩn "hoàng gia" cùng trang bị máy móc hiện đại. Với không gian tập luyện chuyên biệt vừa sang trọng vừa riêng tư đi kèm là những đặc quyền V.I.P độc nhất, Diamond Fitness Paragon Bạch Đằng không chỉ là nơi tập luyện, giải trí và chăm sóc sức khỏe vóc dáng đơn thuần mà còn mang đến những trải nghiệm tuyệt vời dành cho các Hội viên Diamond Fitness Paragon.',
     20.86149391226383, 106.67305678210231,480,5000
    );
INSERT INTO gym_department_schedule (gym_department_id, day, open_time, close_time)
VALUES
    /******************************************** Kickfit Sport Departments Schedule ***************************************************/
    (21, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (21, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (21, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (21, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (21, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (21, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (21, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    (22, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (22, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (22, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (22, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (22, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (22, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (22, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    (23, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (23, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (23, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (23, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (23, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (23, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (23, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    (24, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (24, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (24, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (24, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (24, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (24, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (24, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    /******************************************** 25 FIT Departments Schedule ***************************************************/
    (25, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (25, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (25, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (25, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (25, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (25, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (25, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    (26, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (26, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (26, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (26, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (26, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (26, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (26, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    (27, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (27, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (27, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (27, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (27, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (27, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (27, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    (28, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (28, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (28, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (28, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (28, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (28, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (28, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    /******************************************** S'Life Gym Departments Schedule ***************************************************/
    (29, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (29, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (29, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (29, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (29, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (29, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (29, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    (30, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (30, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (30, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (30, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (30, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (30, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (30, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    (31, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (31, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (31, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (31, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (31, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (31, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (31, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    (32, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (32, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (32, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (32, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (32, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (32, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (32, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    /******************************************** Diamond Fitness Departments Schedule ***************************************************/
    (33, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (33, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (33, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (33, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (33, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (33, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (33, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    (34, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (34, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (34, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (34, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (34, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (34, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (34, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    (35, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (35, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (35, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (35, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (35, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (35, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (35, 'Chủ Nhật', '9:00 AM', '11:00 PM'),

    (36, 'Thứ Hai.', '6:00 AM', '10:00 PM'),
    (36, 'Thứ Ba', '9:00 AM', '11:00 PM'),
    (36, 'Thứ Tư', '9:00 AM', '11:00 PM'),
    (36, 'Thứ Năm', '9:00 AM', '11:00 PM'),
    (36, 'Thứ Sáu', '9:00 AM', '11:00 PM'),
    (36, 'Thứ Bảy', '9:00 AM', '11:00 PM'),
    (36, 'Chủ Nhật', '9:00 AM', '11:00 PM');

INSERT INTO gym_plan (gym_department_id, user_id, gym_plan_key, gym_plan_status_key, gym_plan_type_key, name, description, price, price_per_hours, plan_sold, duration,
                      plan_before_active_validity, plan_after_active_validity)
VALUES
    /******************************************** Kickfit Sport Departments Feedback ***************************************************/
    (21,1, 2, 1, 2, 'Gói Bạc',
     'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập các loại hình như Gym – Yoga – Group X. Miễn phí tủ đồ và phòng xông hơi',
     599.00, 0, 0, 30, 10, 30),
    (21,1, 2, 1, 2, 'Gói Kim cương',
     'Gói tập Gym – Kickfit – Bơi – Yoga – Group X, miễn phí tủ đồ, Xông hơi, Khăn Tập, Khăn Tắm và Nước. (Đặc biệt được dẫn theo một người đi cùng)',
     2199.00, 0, 0, 30, 10, 30),
    (21,1, 2, 1, 2, 'Gói PT',
     'Gói Tập Gym với PT chuyên nghiệp, miễn phí tủ đồ, Xông hơi Khăn Tập, Khăn Tắm, Nước',
     399.00, 0, 0, 30, 10, 30),

    (22,1, 2, 1, 2, 'Gói Bạc',
     'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập các loại hình như Gym – Yoga – Group X. Miễn phí tủ đồ và phòng xông hơi',
     599.00, 0, 0, 30, 10, 30),
    (22,1, 2, 1, 2, 'Gói Kim cương',
     'Gói tập Gym – Kickfit – Bơi – Yoga – Group X, miễn phí tủ đồ, Xông hơi, Khăn Tập, Khăn Tắm và Nước. (Đặc biệt được dẫn theo một người đi cùng)',
     2199.00, 0, 0, 30, 10, 30),
    (22,1, 2, 1, 2, 'Gói PT',
     'Gói Tập Gym với PT chuyên nghiệp, miễn phí tủ đồ, Xông hơi Khăn Tập, Khăn Tắm, Nước',
     399.00, 0, 0, 30, 10, 30),

    (23,1, 2, 1, 2, 'Gói Bạc',
     'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập các loại hình như Gym – Yoga – Group X. Miễn phí tủ đồ và phòng xông hơi',
     599.00, 0, 0, 30, 10, 30),
    (23,1, 2, 1, 2, 'Gói Kim cương',
     'Gói tập Gym – Kickfit – Bơi – Yoga – Group X, miễn phí tủ đồ, Xông hơi, Khăn Tập, Khăn Tắm và Nước. (Đặc biệt được dẫn theo một người đi cùng)',
     2199.00, 0, 0, 30, 10, 30),
    (23,1, 2, 1, 2, 'Gói PT',
     'Gói Tập Gym với PT chuyên nghiệp, miễn phí tủ đồ, Xông hơi Khăn Tập, Khăn Tắm, Nước',
     399.00, 0, 0, 30, 10, 30),

    (24,1, 2, 1, 2, 'Gói Bạc',
     'Đây là một trong những loại thẻ tập chính. Khi tham gia gói tập này bạn sẽ có rất nhiều đặc quyền như được tập các loại hình như Gym – Yoga – Group X. Miễn phí tủ đồ và phòng xông hơi',
     599.00, 0, 0, 30, 10, 30),
    (24,1, 2, 1, 2, 'Gói Kim cương',
     'Gói tập Gym – Kickfit – Bơi – Yoga – Group X, miễn phí tủ đồ, Xông hơi, Khăn Tập, Khăn Tắm và Nước. (Đặc biệt được dẫn theo một người đi cùng)',
     2199.00, 0, 0, 30, 10, 30),
    (24,1, 2, 1, 2, 'Gói PT',
     'Gói Tập Gym với PT chuyên nghiệp, miễn phí tủ đồ, Xông hơi Khăn Tập, Khăn Tắm, Nước',
     399.00, 0, 0, 30, 10, 30),

    /******************************************** 25 FIT Departments Feedback ***************************************************/
    (25,1, 2, 1, 2, 'Gói Platinum',
     'Khi mua gói tập này, bạn sẽ nhận được các quyền lợi như: Miễn phí reworkout, nước uống và khăn tập. Ngoài ra voucher tập 1 tuần và HLV cá nhân cũng sẽ được cung cấp',
     53760.00, 0, 0, 365 , 10, 365),
    (25,1, 2, 1, 2, 'Gói Platinum 2 Năm',
     'Khi mua gói tập này, bạn sẽ nhận được các quyền lợi như: Miễn phí reworkout, nước uống và khăn tập tặng kèm với voucher tập 1 tháng và HLV cá nhân',
     79999.00, 0, 0, 730 , 10, 730),
    (25,1, 2, 1, 2, 'Gói trải nghiệm',
     'Khi mua gói tập này, bạn sẽ nhận được các quyền lợi của 25 FIT trong vòng 1 tháng với mức giá phải chăng',
     6099.00, 0, 0, 30 , 10, 30),

    (26,1, 2, 1, 2, 'Gói Platinum',
     'Khi mua gói tập này, bạn sẽ nhận được các quyền lợi như: Miễn phí reworkout, nước uống và khăn tập. Ngoài ra voucher tập 1 tuần và HLV cá nhân cũng sẽ được cung cấp',
     53760.00, 0, 0, 365 , 10, 365),
    (26,1, 2, 1, 2, 'Gói Platinum 2 Năm',
     'Khi mua gói tập này, bạn sẽ nhận được các quyền lợi như: Miễn phí reworkout, nước uống và khăn tập tặng kèm với voucher tập 1 tháng và HLV cá nhân',
     79999.00, 0, 0, 730 , 10, 730),
    (26,1, 2, 1, 2, 'Gói trải nghiệm',
     'Khi mua gói tập này, bạn sẽ nhận được các quyền lợi của 25 FIT trong vòng 1 tháng với mức giá phải chăng',
     6099.00, 0, 0, 30 , 10, 30),

    (27,1, 2, 1, 2, 'Gói Platinum',
     'Khi mua gói tập này, bạn sẽ nhận được các quyền lợi như: Miễn phí reworkout, nước uống và khăn tập. Ngoài ra voucher tập 1 tuần và HLV cá nhân cũng sẽ được cung cấp',
     53760.00, 0, 0, 365 , 10, 365),
    (27,1, 2, 1, 2, 'Gói Platinum 2 Năm',
     'Khi mua gói tập này, bạn sẽ nhận được các quyền lợi như: Miễn phí reworkout, nước uống và khăn tập tặng kèm với voucher tập 1 tháng và HLV cá nhân',
     79999.00, 0, 0, 730 , 10, 730),
    (27,1, 2, 1, 2, 'Gói trải nghiệm',
     'Khi mua gói tập này, bạn sẽ nhận được các quyền lợi của 25 FIT trong vòng 1 tháng với mức giá phải chăng',
     6099.00, 0, 0, 30 , 10, 30),

    (28,1, 2, 1, 2, 'Gói Platinum',
     'Khi mua gói tập này, bạn sẽ nhận được các quyền lợi như: Miễn phí reworkout, nước uống và khăn tập. Ngoài ra voucher tập 1 tuần và HLV cá nhân cũng sẽ được cung cấp',
     53760.00, 0, 0, 365 , 10, 365),
    (28,1, 2, 1, 2, 'Gói Platinum 2 Năm',
     'Khi mua gói tập này, bạn sẽ nhận được các quyền lợi như: Miễn phí reworkout, nước uống và khăn tập tặng kèm với voucher tập 1 tháng và HLV cá nhân',
     79999.00, 0, 0, 730 , 10, 730),
    (28,1, 2, 1, 2, 'Gói trải nghiệm',
     'Khi mua gói tập này, bạn sẽ nhận được các quyền lợi của 25 FIT trong vòng 1 tháng với mức giá phải chăng',
     6099.00, 0, 0, 30 , 10, 30),

    /******************************************** S'Life Gym Departments Feedback ***************************************************/
    (29,1, 1, 1, 1, 'Gói Linh Hoạt',
     'Gói tập linh hoạt, phù hợp cho người muốn trải nghiệm các phòng tập. Đặc quyền tập luyện ở mọi cơ sở.',
     0, 50.00, 0, 0, 10, 30),
    (29,1, 2, 1, 2, 'Gói Silver',
     'Quý khách sẽ có quyền lợi được tham gia tất cả các lớp tập 1 một chi nhánh bạn lựa chọn.Tham gia các lớp dance, Group X, Yoga, Gym, Kickfit,….Có Locker (tủ) đựng đồ, được xông hơi, sử dụng phòng tắm,…',
     9000.00, 0, 0, 720, 10, 720),
    (29,1, 2, 1, 2, 'Gói Gold ',
     'Quý Khách sẽ có quyền lợi như gói Silver.Có thêm khăn mỗi buổi tập nên không cần mang theo. Được tập tại tất cả các chi nhánh của S’life Gym',
     12500.00, 0, 0, 1080, 10, 1080),
    (29,1, 2, 1, 2, 'Gói Titaninum ',
     'Quý Khách sẽ được hưởng tất cả các quyền lợi như của thẻ Silver và Gold thì còn được uống detox sau mỗi buổi tập.Được tham gia tất cả các lớp tập tại tất cả các chi nhánh',
     13000.00, 0, 0, 900, 10, 900),

    (30,1, 1, 1, 1, 'Gói Linh Hoạt',
     'Gói tập linh hoạt, phù hợp cho người muốn trải nghiệm các phòng tập. Đặc quyền tập luyện ở mọi cơ sở.',
     0, 50.00, 0, 0, 10, 30),
    (30,1, 2, 1, 2, 'Gói Silver',
     'Quý khách sẽ có quyền lợi được tham gia tất cả các lớp tập 1 một chi nhánh bạn lựa chọn.Tham gia các lớp dance, Group X, Yoga, Gym, Kickfit,….Có Locker (tủ) đựng đồ, được xông hơi, sử dụng phòng tắm,…',
     9000.00, 0, 0, 720, 10, 720),
    (30,1, 2, 1, 2, 'Gói Gold ',
     'Quý Khách sẽ có quyền lợi như gói Silver.Có thêm khăn mỗi buổi tập nên không cần mang theo. Được tập tại tất cả các chi nhánh của S’life Gym',
     12500.00, 0, 0, 1080, 10, 1080),
    (30,1, 2, 1, 2, 'Gói Titaninum ',
     'Quý Khách sẽ được hưởng tất cả các quyền lợi như của thẻ Silver và Gold thì còn được uống detox sau mỗi buổi tập.Được tham gia tất cả các lớp tập tại tất cả các chi nhánh',
     13000.00, 0, 0, 900, 10, 900),

    (31,1, 1, 1, 1, 'Gói Linh Hoạt',
     'Gói tập linh hoạt, phù hợp cho người muốn trải nghiệm các phòng tập. Đặc quyền tập luyện ở mọi cơ sở.',
     0, 50.00, 0, 0, 10, 30),
    (31,1, 2, 1, 2, 'Gói Silver',
     'Quý khách sẽ có quyền lợi được tham gia tất cả các lớp tập 1 một chi nhánh bạn lựa chọn.Tham gia các lớp dance, Group X, Yoga, Gym, Kickfit,….Có Locker (tủ) đựng đồ, được xông hơi, sử dụng phòng tắm,…',
     9000.00, 0, 0, 720, 10, 720),
    (31,1, 2, 1, 2, 'Gói Gold ',
     'Quý Khách sẽ có quyền lợi như gói Silver.Có thêm khăn mỗi buổi tập nên không cần mang theo. Được tập tại tất cả các chi nhánh của S’life Gym',
     12500.00, 0, 0, 1080, 10, 1080),
    (31,1, 2, 1, 2, 'Gói Titaninum ',
     'Quý Khách sẽ được hưởng tất cả các quyền lợi như của thẻ Silver và Gold thì còn được uống detox sau mỗi buổi tập.Được tham gia tất cả các lớp tập tại tất cả các chi nhánh',
     13000.00, 0, 0, 900, 10, 900),

    (32,1, 1, 1, 1, 'Gói Linh Hoạt',
     'Gói tập linh hoạt, phù hợp cho người muốn trải nghiệm các phòng tập. Đặc quyền tập luyện ở mọi cơ sở.',
     0, 50.00, 0, 0, 10, 30),
    (32,1, 2, 1, 2, 'Gói Silver',
     'Quý khách sẽ có quyền lợi được tham gia tất cả các lớp tập 1 một chi nhánh bạn lựa chọn.Tham gia các lớp dance, Group X, Yoga, Gym, Kickfit,….Có Locker (tủ) đựng đồ, được xông hơi, sử dụng phòng tắm,…',
     9000.00, 0, 0, 720, 10, 720),
    (32,1, 2, 1, 2, 'Gói Gold ',
     'Quý Khách sẽ có quyền lợi như gói Silver.Có thêm khăn mỗi buổi tập nên không cần mang theo. Được tập tại tất cả các chi nhánh của S’life Gym',
     12500.00, 0, 0, 1080, 10, 1080),
    (32,1, 2, 1, 2, 'Gói Titaninum ',
     'Quý Khách sẽ được hưởng tất cả các quyền lợi như của thẻ Silver và Gold thì còn được uống detox sau mỗi buổi tập.Được tham gia tất cả các lớp tập tại tất cả các chi nhánh',
     13000.00, 0, 0, 900, 10, 900),

    /******************************************** Diamond Fitness Departments Feedback ***************************************************/
    (33, 1, 1, 1, 1, 'Gói Linh Hoạt',
     'Gói tập linh hoạt, phù hợp cho người muốn trải nghiệm các phòng tập. Đặc quyền tập luyện ở mọi cơ sở.',
     0, 50.00, 0, 0, 10, 30),
    (33,1, 2, 1, 2, 'Gói Siêu Quyền Lợi',
     'Hơn 45 bộ môn : Gym, Kick Boxing, GroupX, Cycling. Hơn 100+ lớp học thú vị: Dance, Zumba, Múa cổ trang, Aerobics, TikTok Dance,. Đội ngũ HLV giàu kinh nghiệm chuyên môn ',
     3400.00, 0, 0, 365, 10, 365),
    (33,1, 2, 1, 2, 'Gói Full Dịch Vụ',
     'Quý khách sẽ được hưởng quyền lợi như Gói Siêu Quyền Lợi cộng thêm tự do tập toàn khung giờ từ 5:30 - 22:00.Dịch vụ tiện ích thư giãn cao cấp: xông hơi, bơi lội,...',
     4000.00, 0, 0, 365, 10, 365),

    (34, 1, 1, 1, 1, 'Gói Linh Hoạt',
     'Gói tập linh hoạt, phù hợp cho người muốn trải nghiệm các phòng tập. Đặc quyền tập luyện ở mọi cơ sở.',
     0, 50.00, 0, 0, 10, 30),
    (34,1, 2, 1, 2, 'Gói Siêu Quyền Lợi',
     'Hơn 45 bộ môn : Gym, Kick Boxing, GroupX, Cycling. Hơn 100+ lớp học thú vị: Dance, Zumba, Múa cổ trang, Aerobics, TikTok Dance,. Đội ngũ HLV giàu kinh nghiệm chuyên môn ',
     3400.00, 0, 0, 365, 10, 365),
    (34,1, 2, 1, 2, 'Gói Full Dịch Vụ',
     'Quý khách sẽ được hưởng quyền lợi như Gói Siêu Quyền Lợi cộng thêm tự do tập toàn khung giờ từ 5:30 - 22:00.Dịch vụ tiện ích thư giãn cao cấp: xông hơi, bơi lội,...',
     4000.00, 0, 0, 365, 10, 365),

    (35, 1, 1, 1, 1, 'Gói Linh Hoạt',
     'Gói tập linh hoạt, phù hợp cho người muốn trải nghiệm các phòng tập. Đặc quyền tập luyện ở mọi cơ sở.',
     0, 50.00, 0, 0, 10, 30),
    (35,1, 2, 1, 2, 'Gói Siêu Quyền Lợi',
     'Hơn 45 bộ môn : Gym, Kick Boxing, GroupX, Cycling. Hơn 100+ lớp học thú vị: Dance, Zumba, Múa cổ trang, Aerobics, TikTok Dance,. Đội ngũ HLV giàu kinh nghiệm chuyên môn ',
     3400.00, 0, 0, 365, 10, 365),
    (35,1, 2, 1, 2, 'Gói Full Dịch Vụ',
     'Quý khách sẽ được hưởng quyền lợi như Gói Siêu Quyền Lợi cộng thêm tự do tập toàn khung giờ từ 5:30 - 22:00.Dịch vụ tiện ích thư giãn cao cấp: xông hơi, bơi lội,...',
     4000.00, 0, 0, 365, 10, 365),

    (36, 1, 1, 1, 1, 'Gói Linh Hoạt',
     'Gói tập linh hoạt, phù hợp cho người muốn trải nghiệm các phòng tập. Đặc quyền tập luyện ở mọi cơ sở.',
     0, 50.00, 0, 0, 10, 30),
    (36,1, 2, 1, 2, 'Gói Siêu Quyền Lợi',
     'Hơn 45 bộ môn : Gym, Kick Boxing, GroupX, Cycling. Hơn 100+ lớp học thú vị: Dance, Zumba, Múa cổ trang, Aerobics, TikTok Dance,. Đội ngũ HLV giàu kinh nghiệm chuyên môn ',
     3400.00, 0, 0, 365, 10, 365),
    (36,1, 2, 1, 2, 'Gói Full Dịch Vụ',
     'Quý khách sẽ được hưởng quyền lợi như Gói Siêu Quyền Lợi cộng thêm tự do tập toàn khung giờ từ 5:30 - 22:00.Dịch vụ tiện ích thư giãn cao cấp: xông hơi, bơi lội,...',
     4000.00, 0, 0, 365, 10, 365);

INSERT INTO user_feedback
(user_id, department_id, rating, comments, feedback_time, feedback_status)
VALUES
    /******************************************** Kickfit Sport Departments Feedback ***************************************************/
    (1,21,5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(),1 ),
    (1,21,5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(),1 ),
    (1,21,5, 'Chăm sóc khách hàng rất tận tâm, cực kì hài lòng', NOW(),1 ),
    (1,21,5, 'Cơ sở vật chất cực kì ok, pt hướng dẫn thì cực kì chuyên nghiệp, rất ủng hộ', NOW(),1 ),
    (1,21,5, 'Cơ sở vật chất hiện đại và tiện nghi, đội ngũ huấn luyện viên am hiểu và nhiệt tình', NOW(), 1),
    (1,21,4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,21,4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,21,4, 'Phòng tập thể dục sạch sẽ và thoải mái, đội ngũ huấn luyện chuyên nghiệp', NOW(),1 ),
    (1,21,3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(),1 ),
    (1,21,3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1,22,5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(),1 ),
    (1,22,5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(),1 ),
    (1,22,5, 'Chăm sóc khách hàng rất tận tâm, cực kì hài lòng', NOW(),1 ),
    (1,22,5, 'Cơ sở vật chất cực kì ok, pt hướng dẫn thì cực kì chuyên nghiệp, rất ủng hộ', NOW(),1 ),
    (1,22,5, 'Cơ sở vật chất hiện đại và tiện nghi, đội ngũ huấn luyện viên am hiểu và nhiệt tình', NOW(), 1),
    (1,22,4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,22,4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,22,4, 'Phòng tập thể dục sạch sẽ và thoải mái, đội ngũ huấn luyện chuyên nghiệp', NOW(),1 ),
    (1,22,3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(),1 ),
    (1,22,3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1,23,5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(),1 ),
    (1,23,5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(),1 ),
    (1,23,5, 'Chăm sóc khách hàng rất tận tâm, cực kì hài lòng', NOW(),1 ),
    (1,23,5, 'Cơ sở vật chất cực kì ok, pt hướng dẫn thì cực kì chuyên nghiệp, rất ủng hộ', NOW(),1 ),
    (1,23,5, 'Cơ sở vật chất hiện đại và tiện nghi, đội ngũ huấn luyện viên am hiểu và nhiệt tình', NOW(), 1),
    (1,23,4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,23,4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,23,4, 'Phòng tập thể dục sạch sẽ và thoải mái, đội ngũ huấn luyện chuyên nghiệp', NOW(),1 ),
    (1,23,3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(),1 ),
    (1,23,3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1,24,5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(),1 ),
    (1,24,5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(),1 ),
    (1,24,5, 'Chăm sóc khách hàng rất tận tâm, cực kì hài lòng', NOW(),1 ),
    (1,24,5, 'Cơ sở vật chất cực kì ok, pt hướng dẫn thì cực kì chuyên nghiệp, rất ủng hộ', NOW(),1 ),
    (1,24,5, 'Cơ sở vật chất hiện đại và tiện nghi, đội ngũ huấn luyện viên am hiểu và nhiệt tình', NOW(), 1),
    (1,24,4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,24,4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,24,4, 'Phòng tập thể dục sạch sẽ và thoải mái, đội ngũ huấn luyện chuyên nghiệp', NOW(),1 ),
    (1,24,3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(),1 ),
    (1,24,3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    /******************************************** 25 FIT Departments Feedback ***************************************************/
    (1,25,5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(),1 ),
    (1,25,5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(),1 ),
    (1,25,5, 'Chăm sóc khách hàng rất tận tâm, cực kì hài lòng', NOW(),1 ),
    (1,25,5, 'Cơ sở vật chất cực kì ok, pt hướng dẫn thì cực kì chuyên nghiệp, rất ủng hộ', NOW(),1 ),
    (1,25,5, 'Cơ sở vật chất hiện đại và tiện nghi, đội ngũ huấn luyện viên am hiểu và nhiệt tình', NOW(), 1),
    (1,25,4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,25,4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,25,4, 'Phòng tập thể dục sạch sẽ và thoải mái, đội ngũ huấn luyện chuyên nghiệp', NOW(),1 ),
    (1,25,3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(),1 ),
    (1,25,3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1,26,5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(),1 ),
    (1,26,5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(),1 ),
    (1,26,5, 'Chăm sóc khách hàng rất tận tâm, cực kì hài lòng', NOW(),1 ),
    (1,26,5, 'Cơ sở vật chất cực kì ok, pt hướng dẫn thì cực kì chuyên nghiệp, rất ủng hộ', NOW(),1 ),
    (1,26,5, 'Cơ sở vật chất hiện đại và tiện nghi, đội ngũ huấn luyện viên am hiểu và nhiệt tình', NOW(), 1),
    (1,26,4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,26,4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,26,4, 'Phòng tập thể dục sạch sẽ và thoải mái, đội ngũ huấn luyện chuyên nghiệp', NOW(),1 ),
    (1,26,3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(),1 ),
    (1,26,3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1,27,5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(),1 ),
    (1,27,5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(),1 ),
    (1,27,5, 'Chăm sóc khách hàng rất tận tâm, cực kì hài lòng', NOW(),1 ),
    (1,27,5, 'Cơ sở vật chất cực kì ok, pt hướng dẫn thì cực kì chuyên nghiệp, rất ủng hộ', NOW(),1 ),
    (1,27,5, 'Cơ sở vật chất hiện đại và tiện nghi, đội ngũ huấn luyện viên am hiểu và nhiệt tình', NOW(), 1),
    (1,27,4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,27,4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,27,4, 'Phòng tập thể dục sạch sẽ và thoải mái, đội ngũ huấn luyện chuyên nghiệp', NOW(),1 ),
    (1,27,3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(),1 ),
    (1,27,3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1,28,5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(),1 ),
    (1,28,5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(),1 ),
    (1,28,5, 'Chăm sóc khách hàng rất tận tâm, cực kì hài lòng', NOW(),1 ),
    (1,28,5, 'Cơ sở vật chất cực kì ok, pt hướng dẫn thì cực kì chuyên nghiệp, rất ủng hộ', NOW(),1 ),
    (1,28,5, 'Cơ sở vật chất hiện đại và tiện nghi, đội ngũ huấn luyện viên am hiểu và nhiệt tình', NOW(), 1),
    (1,28,4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,28,4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,28,4, 'Phòng tập thể dục sạch sẽ và thoải mái, đội ngũ huấn luyện chuyên nghiệp', NOW(),1 ),
    (1,28,3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(),1 ),
    (1,28,3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    /******************************************** S'Life Gym Departments Feedback ***************************************************/
    (1,29,5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(),1 ),
    (1,29,5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(),1 ),
    (1,29,5, 'Chăm sóc khách hàng rất tận tâm, cực kì hài lòng', NOW(),1 ),
    (1,29,5, 'Cơ sở vật chất cực kì ok, pt hướng dẫn thì cực kì chuyên nghiệp, rất ủng hộ', NOW(),1 ),
    (1,28,5, 'Cơ sở vật chất hiện đại và tiện nghi, đội ngũ huấn luyện viên am hiểu và nhiệt tình', NOW(), 1),
    (1,29,4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,29,4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,29,4, 'Phòng tập thể dục sạch sẽ và thoải mái, đội ngũ huấn luyện chuyên nghiệp', NOW(),1 ),
    (1,29,3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(),1 ),
    (1,29,3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1,30,5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(),1 ),
    (1,30,5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(),1 ),
    (1,30,5, 'Chăm sóc khách hàng rất tận tâm, cực kì hài lòng', NOW(),1 ),
    (1,30,5, 'Cơ sở vật chất cực kì ok, pt hướng dẫn thì cực kì chuyên nghiệp, rất ủng hộ', NOW(),1 ),
    (1,30,5, 'Cơ sở vật chất hiện đại và tiện nghi, đội ngũ huấn luyện viên am hiểu và nhiệt tình', NOW(), 1),
    (1,30,4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,30,4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,30,4, 'Phòng tập thể dục sạch sẽ và thoải mái, đội ngũ huấn luyện chuyên nghiệp', NOW(),1 ),
    (1,30,3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(),1 ),
    (1,30,3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1,31,5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(),1 ),
    (1,31,5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(),1 ),
    (1,31,5, 'Chăm sóc khách hàng rất tận tâm, cực kì hài lòng', NOW(),1 ),
    (1,31,5, 'Cơ sở vật chất cực kì ok, pt hướng dẫn thì cực kì chuyên nghiệp, rất ủng hộ', NOW(),1 ),
    (1,31,5, 'Cơ sở vật chất hiện đại và tiện nghi, đội ngũ huấn luyện viên am hiểu và nhiệt tình', NOW(), 1),
    (1,31,4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,31,4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,31,4, 'Phòng tập thể dục sạch sẽ và thoải mái, đội ngũ huấn luyện chuyên nghiệp', NOW(),1 ),
    (1,31,3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(),1 ),
    (1,31,3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1,32,5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(),1 ),
    (1,32,5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(),1 ),
    (1,32,5, 'Chăm sóc khách hàng rất tận tâm, cực kì hài lòng', NOW(),1 ),
    (1,32,5, 'Cơ sở vật chất cực kì ok, pt hướng dẫn thì cực kì chuyên nghiệp, rất ủng hộ', NOW(),1 ),
    (1,32,5, 'Cơ sở vật chất hiện đại và tiện nghi, đội ngũ huấn luyện viên am hiểu và nhiệt tình', NOW(), 1),
    (1,32,4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,32,4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,32,4, 'Phòng tập thể dục sạch sẽ và thoải mái, đội ngũ huấn luyện chuyên nghiệp', NOW(),1 ),
    (1,32,3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(),1 ),
    (1,32,3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    /******************************************** Diamond Fitness Departments Feedback ***************************************************/
    (1,33,5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(),1 ),
    (1,33,5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(),1 ),
    (1,33,5, 'Chăm sóc khách hàng rất tận tâm, cực kì hài lòng', NOW(),1 ),
    (1,33,5, 'Cơ sở vật chất cực kì ok, pt hướng dẫn thì cực kì chuyên nghiệp, rất ủng hộ', NOW(),1 ),
    (1,33,5, 'Cơ sở vật chất hiện đại và tiện nghi, đội ngũ huấn luyện viên am hiểu và nhiệt tình', NOW(), 1),
    (1,33,4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,33,4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,33,4, 'Phòng tập thể dục sạch sẽ và thoải mái, đội ngũ huấn luyện chuyên nghiệp', NOW(),1 ),
    (1,33,3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(),1 ),
    (1,33,3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1,34,5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(),1 ),
    (1,34,5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(),1 ),
    (1,34,5, 'Chăm sóc khách hàng rất tận tâm, cực kì hài lòng', NOW(),1 ),
    (1,34,5, 'Cơ sở vật chất cực kì ok, pt hướng dẫn thì cực kì chuyên nghiệp, rất ủng hộ', NOW(),1 ),
    (1,34,5, 'Cơ sở vật chất hiện đại và tiện nghi, đội ngũ huấn luyện viên am hiểu và nhiệt tình', NOW(), 1),
    (1,34,4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,34,4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,34,4, 'Phòng tập thể dục sạch sẽ và thoải mái, đội ngũ huấn luyện chuyên nghiệp', NOW(),1 ),
    (1,34,3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(),1 ),
    (1,34,3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1,35,5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(),1 ),
    (1,35,5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(),1 ),
    (1,35,5, 'Chăm sóc khách hàng rất tận tâm, cực kì hài lòng', NOW(),1 ),
    (1,35,5, 'Cơ sở vật chất cực kì ok, pt hướng dẫn thì cực kì chuyên nghiệp, rất ủng hộ', NOW(),1 ),
    (1,35,5, 'Cơ sở vật chất hiện đại và tiện nghi, đội ngũ huấn luyện viên am hiểu và nhiệt tình', NOW(), 1),
    (1,35,4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,35,4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,35,4, 'Phòng tập thể dục sạch sẽ và thoải mái, đội ngũ huấn luyện chuyên nghiệp', NOW(),1 ),
    (1,35,3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(),1 ),
    (1,35,3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1),

    (1,36,5, 'Phòng tập thể dục rất sạch sẽ và thoải mái. Loại hình tập luyện đa dạng', NOW(),1 ),
    (1,36,5, 'Đội ngũ HLV cực kì chuyên nghiệp và tận tâm, sẽ recommend cho những anh em mới bắt đầu tập', NOW(),1 ),
    (1,36,5, 'Chăm sóc khách hàng rất tận tâm, cực kì hài lòng', NOW(),1 ),
    (1,36,5, 'Cơ sở vật chất cực kì ok, pt hướng dẫn thì cực kì chuyên nghiệp, rất ủng hộ', NOW(),1 ),
    (1,36,5, 'Cơ sở vật chất hiện đại và tiện nghi, đội ngũ huấn luyện viên am hiểu và nhiệt tình', NOW(), 1),
    (1,36,4, 'Phòng tập thể dục rất rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,36,4, 'Phòng tập thể dục rộng rãi và thoải mái, dịch vụ ổn', NOW(),1 ),
    (1,36,4, 'Phòng tập thể dục sạch sẽ và thoải mái, đội ngũ huấn luyện chuyên nghiệp', NOW(),1 ),
    (1,36,3, 'Phòng tập thể dục sạch sẽ và thoải mái, tuy nhiên dịch vụ chưa tương xứng', NOW(),1 ),
    (1,36,3, 'Phòng tập rộng rãi nhưng cần cải thiện về sự sạch sẽ và quản lý', NOW(), 1);


INSERT INTO gym_department_albums (gym_department_id, photo_url, description)
VALUES
    (21,'https://t.ly/f0WD0','Cơ sở vật chất'),
    (21,'https://t.ly/s3Ord','Cơ sở vật chất'),
    (21,'https://t.ly/PlwJn','Cơ sở vật chất'),
    (21,'https://t.ly/W13Hn','Kết quả của khách hàng'),
    (21,'https://t.ly/lCtuJ','Tập luyện yoga ở Kickfit'),
    (21,'https://shorturl.at/fruQ9','Cơ sở vật chất'),
    (21,'https://shorturl.at/cmrF4','Cơ sở vật chất'),
    (21,'https://shorturl.at/qFIW8','Cơ sở vật chất'),
    (21,'https://shorturl.at/apJ49','Kết quả của khách hàng'),

    (22,'https://t.ly/f0WD0','Cơ sở vật chất'),
    (22,'https://t.ly/s3Ord','Cơ sở vật chất'),
    (22,'https://t.ly/PlwJn','Cơ sở vật chất'),
    (22,'https://t.ly/W13Hn','Kết quả của khách hàng'),
    (22,'https://t.ly/lCtuJ','Tập luyện yoga ở Kickfit'),
    (22,'https://shorturl.at/fruQ9','Cơ sở vật chất'),
    (22,'https://shorturl.at/cmrF4','Cơ sở vật chất'),
    (22,'https://shorturl.at/qFIW8','Cơ sở vật chất'),
    (22,'https://shorturl.at/apJ49','Kết quả của khách hàng'),

    (23,'https://t.ly/f0WD0','Cơ sở vật chất'),
    (23,'https://t.ly/s3Ord','Cơ sở vật chất'),
    (23,'https://t.ly/PlwJn','Cơ sở vật chất'),
    (23,'https://t.ly/W13Hn','Kết quả của khách hàng'),
    (23,'https://t.ly/lCtuJ','Tập luyện yoga ở Kickfit'),
    (23,'https://shorturl.at/fruQ9','Cơ sở vật chất'),
    (23,'https://shorturl.at/cmrF4','Cơ sở vật chất'),
    (23,'https://shorturl.at/qFIW8','Cơ sở vật chất'),
    (23,'https://shorturl.at/apJ49','Kết quả của khách hàng'),

    (24,'https://t.ly/f0WD0','Cơ sở vật chất'),
    (24,'https://t.ly/s3Ord','Cơ sở vật chất'),
    (24,'https://t.ly/PlwJn','Cơ sở vật chất'),
    (24,'https://t.ly/W13Hn','Kết quả của khách hàng'),
    (24,'https://t.ly/lCtuJ','Tập luyện yoga ở Kickfit'),
    (24,'https://shorturl.at/fruQ9','Cơ sở vật chất'),
    (24,'https://shorturl.at/cmrF4','Cơ sở vật chất'),
    (24,'https://shorturl.at/qFIW8','Cơ sở vật chất'),
    (24,'https://shorturl.at/apJ49','Kết quả của khách hàng');