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





CREATE TABLE IF NOT EXISTS user_detail (
                                           user_detail_id       INT AUTO_INCREMENT PRIMARY KEY,
                                           first_name    VARCHAR(50) NOT NULL,
    last_name     VARCHAR(50) NOT NULL,
    email         VARCHAR(100) NOT NULL,
    phone_number  VARCHAR(20) NOT NULL,
    address       VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender        VARCHAR(10) NOT NULL

    );
-- User table to store user information
CREATE TABLE IF NOT EXISTS `user` (
                                      user_id          INT AUTO_INCREMENT PRIMARY KEY,
                                      user_account     VARCHAR(250) NOT NULL,
    user_password    VARCHAR(100) NOT NULL,
    user_detail_id        INT NOT NULL,
    user_create_time VARCHAR(20) NOT NULL,
    user_deleted     TINYINT NOT NULL,
    FOREIGN KEY (user_detail_id) REFERENCES user_detail(user_detail_id)
    );
-- User Wallet table to store user wallet information
CREATE TABLE IF NOT EXISTS wallet (
                                      wallet_id     INT AUTO_INCREMENT PRIMARY KEY,
                                      user_id       INT NOT NULL,
                                      balance       DECIMAL(10, 2) DEFAULT 0.0,
    FOREIGN KEY (user_id) REFERENCES user(user_id)
    );
-- Role table to store user roles
CREATE TABLE IF NOT EXISTS role (
                                    role_id   INT AUTO_INCREMENT PRIMARY KEY,
                                    role_name VARCHAR(20) NOT NULL
    );

-- User Role table to store the relationship between users and roles
CREATE TABLE IF NOT EXISTS user_role (
                                         user_role_id INT AUTO_INCREMENT PRIMARY KEY,
                                         user_id      INT NOT NULL,
                                         role_id      INT NOT NULL,
                                         FOREIGN KEY (user_id) REFERENCES `user` (user_id),
    FOREIGN KEY (role_id) REFERENCES role (role_id)
    );



-- Gym Department table to store gym department information
CREATE TABLE IF NOT EXISTS gym_department (
                                              gym_department_id INT AUTO_INCREMENT PRIMARY KEY,
                                              gym_department_status_key INT NOT NULL,
                                              user_id           INT NOT NULL,
                                              name              VARCHAR(255) NOT NULL,
    address           VARCHAR(255) NOT NULL,
    contact_number    VARCHAR(20) NOT NULL,
    logo_url          VARCHAR(255) NOT NULL,
    opening_hours     VARCHAR(255) NOT NULL,
    image_url         VARCHAR(255) NOT NULL,
    description       TEXT NOT NULL,
    latitude 			DECIMAL(10,8) NOT NULL,
    longitude 		DECIMAL(11,8) NOT NULL,
    rating            DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES `user`(user_id)
    );

-- table name mst_kbn to store type,status of all tables
CREATE TABLE IF NOT EXISTS mst_kbn (
                                       mst_kbn_id    INT AUTO_INCREMENT PRIMARY KEY,
                                       mst_kbn_name  VARCHAR(50) NOT NULL,
    mst_kbn_key   INT NOT NULL,
    mst_kbn_value VARCHAR(50) NOT NULL
    );

-- Gym Plan table to store gym plan information
CREATE TABLE IF NOT EXISTS gym_plan (
                                        plan_id        INT AUTO_INCREMENT PRIMARY KEY,
                                        gym_deparment_id         INT NOT NULL,
                                        gym_plan_key   INT NOT NULL,
                                        gym_plan_status_key    INT NOT NULL,
                                        name           VARCHAR(255) NOT NULL,
    description    VARCHAR(255) NOT NULL,
    price          DECIMAL(10, 2) ,
    price_per_hours         DECIMAL(10, 2) ,
    plan_sold      INT NOT NULL,
    duration       INT ,
    plan_before_active_validity       INT NOT NULL,
    plan_after_active_validity        INT NOT NULL,
    image_url      VARCHAR(255),
    FOREIGN KEY (gym_deparment_id) REFERENCES gym_department(gym_department_id)
    );

-- Shift table to store shift information
CREATE TABLE IF NOT EXISTS shift (
                                     shift_id     INT AUTO_INCREMENT PRIMARY KEY,
                                     gym_department_id INT NOT NULL,
                                     start_time   TIME NOT NULL,
                                     end_time     TIME NOT NULL,
                                     FOREIGN KEY (gym_department_id) REFERENCES gym_department(gym_department_id)
    );

-- Gymer Booking table to store user booking information
CREATE TABLE IF NOT EXISTS gymer_booking (
                                             booking_id       INT AUTO_INCREMENT PRIMARY KEY,
                                             user_id         INT NOT NULL,
                                             gym_department_id INT NOT NULL,
                                             shift_id         INT NOT NULL,
                                             booking_create_time     DATETIME NOT NULL,
                                             booking_time     DATETIME NOT NULL,
                                             booking_status   INT NOT NULL,
                                             FOREIGN KEY (user_id) REFERENCES `user`(user_id),
    FOREIGN KEY (gym_department_id) REFERENCES gym_department(gym_department_id),
    FOREIGN KEY (shift_id) REFERENCES shift(shift_id)
    );


-- Transfer table to do the function related to transferring credit
CREATE TABLE IF NOT EXISTS transfer (
                                        transfer_id    INT AUTO_INCREMENT PRIMARY KEY,
                                        sender_id      INT NOT NULL,
                                        receiver_id    INT NOT NULL,
                                        amount         DECIMAL(10, 2) NOT NULL,
    transfer_date  DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES `user`(user_id),
    FOREIGN KEY (receiver_id) REFERENCES `user`(user_id)
    );

-- Transaction table to do the function with transactions between credit and real money
CREATE TABLE IF NOT EXISTS `transaction` (
                                             transaction_id   INT AUTO_INCREMENT PRIMARY KEY,
                                             wallet_id        INT NOT NULL,
                                             amount           DECIMAL(10, 2) NOT NULL,
    transaction_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (wallet_id) REFERENCES wallet(wallet_id)
    );

-- Order table to store order information
CREATE TABLE IF NOT EXISTS `order` (
                                       order_id        INT AUTO_INCREMENT PRIMARY KEY,
                                       user_id         INT NOT NULL,
                                       gym_department_id INT NOT NULL,
                                       order_create_time      DATETIME NOT NULL,
                                       order_status_key    INT NOT NULL,
                                       order_money     DECIMAL(10, 2) NOT NULL,
    discount              INT NOT NULL,
    order_total_money     DECIMAL(10, 2) NOT NULL,
    order_note                 VARCHAR(500),
    FOREIGN KEY (user_id) REFERENCES `user`(user_id)
    );

-- Order Plan Detail table to store order plan details
CREATE TABLE IF NOT EXISTS order_plan_detail (
                                                 order_detail_id INT AUTO_INCREMENT PRIMARY KEY,
                                                 order_id        INT NOT NULL,
                                                 name              VARCHAR(255) NOT NULL,
    quantity        INT NOT NULL,
    price_per_hours         DECIMAL(10, 2) NOT NULL,
    price           DECIMAL(10, 2) NOT NULL,
    duration       INT NOT NULL,
    plan_before_active_validity       INT NOT NULL,
    plan_after_active_validity        INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES `order`(order_id)
    );

-- User Inventory table to store user inventory information
CREATE TABLE IF NOT EXISTS item_inventory (
                                              inventory_id   INT AUTO_INCREMENT PRIMARY KEY,
                                              user_id       INT NOT NULL,
                                              plan_id        INT NOT NULL,
                                              plan_active_time DATETIME NOT NULL,
                                              item_status_key INT NOT NULL,
                                              plan_expired_time       DATETIME NOT NULL,
                                              FOREIGN KEY (user_id) REFERENCES `user`(user_id),
    FOREIGN KEY (plan_id) REFERENCES gym_plan(plan_id)
    );

-- User Feedback table to store user feedback information
CREATE TABLE IF NOT EXISTS user_feedback (
                                             feedback_id    INT AUTO_INCREMENT PRIMARY KEY,
                                             user_id        INT NOT NULL,
                                             department_id  INT NOT NULL,
                                             rating         INT NOT NULL,
                                             comments       TEXT,
                                             feedback_time  DATETIME NOT NULL,
                                             feedback_status INT NOT NULL,
                                             FOREIGN KEY (user_id) REFERENCES `user`(user_id),
    FOREIGN KEY (department_id) REFERENCES gym_department(gym_department_id)
    );

DELIMITER $$
CREATE TRIGGER update_gym_rating_avg
    AFTER INSERT ON user_feedback
    FOR EACH ROW
BEGIN

    DECLARE dept_id INT;
DECLARE total, count INT;

SET dept_id = NEW.department_id;

    SELECT IFNULL(SUM(rating),0), COUNT(*) INTO total, count
    FROM user_feedback
    WHERE department_id = dept_id;

    UPDATE gym_department
    SET rating = total/count
    WHERE gym_department_id = dept_id;

END$$
DELIMITER ;