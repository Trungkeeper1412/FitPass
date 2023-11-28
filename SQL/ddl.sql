-- Drop queries to remove existing tables (if they exist)
DROP TABLE IF EXISTS request_withdrawal_history;
DROP TABLE IF EXISTS credit_card;
DROP TABLE IF EXISTS check_in_history;
DROP TABLE IF EXISTS user_feedback;
DROP TABLE IF EXISTS item_inventory;
DROP TABLE IF EXISTS notification;
DROP TABLE IF EXISTS transfer_credit_history;
DROP TABLE IF EXISTS order_plan_detail;
DROP TABLE IF EXISTS transfer;
DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS `transaction`;
DROP TABLE IF EXISTS gymer_booking;
DROP TABLE IF EXISTS shift;
DROP TABLE IF EXISTS gym_department_plans;
DROP TABLE IF EXISTS gym_department_amenities;
DROP TABLE IF EXISTS gym_plan;
DROP TABLE IF EXISTS mst_kbn;
DROP TABLE IF EXISTS gym_department_services;
DROP TABLE IF EXISTS gym_department_schedule;
DROP TABLE IF EXISTS gym_department_albums;
DROP TABLE IF EXISTS gym_department_amenities;
DROP TABLE IF EXISTS gym_department_features;
DROP TABLE IF EXISTS features;
DROP TABLE IF EXISTS gym_department;
DROP TABLE IF EXISTS brand_albums;
DROP TABLE IF EXISTS brand_amenities;
DROP TABLE IF EXISTS brand;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS wallet;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS user_detail;


CREATE TABLE IF NOT EXISTS user_detail (
                                           user_detail_id       INT AUTO_INCREMENT PRIMARY KEY,
                                           first_name    VARCHAR(50) NOT NULL,
                                           last_name     VARCHAR(50) NOT NULL,
                                           email         VARCHAR(100) UNIQUE,
                                           phone_number  VARCHAR(20) NOT NULL,
                                           address       VARCHAR(255) NOT NULL,
                                           date_of_birth DATE NOT NULL,
                                           gender        VARCHAR(10) NOT NULL,
                                           securityId   varchar(12) null,
                                           image_url TEXT NULL
);
-- User table to store user information
CREATE TABLE IF NOT EXISTS `user` (
                                      user_id          INT AUTO_INCREMENT PRIMARY KEY,
                                      user_account     VARCHAR(250) NOT NULL,
                                      user_password    VARCHAR(100) NOT NULL,
                                      user_detail_id        INT ,
                                      user_create_time VARCHAR(20) NOT NULL,
                                      user_deleted     TINYINT NOT NULL,
                                      created_by INT NULL,
                                      first_time boolean default 1,
                                      FOREIGN KEY (user_detail_id) REFERENCES user_detail(user_detail_id),
                                      CONSTRAINT user_FK FOREIGN KEY (created_by) REFERENCES `user`(user_id)
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


CREATE TABLE IF NOT EXISTS brand (
                                     brand_id                    INT AUTO_INCREMENT PRIMARY KEY,
                                     user_id                     INT,
                                     name                        VARCHAR(50) NOT NULL,
                                     logo_url                    VARCHAR(255) NOT NULL,
                                     wallpaper_url               VARCHAR(255) NOT NULL,
                                     thumbnail_url               VARCHAR(255) NOT NULL,
                                     description                 text,
                                     rating                      DECIMAL(10, 2) DEFAULT 0,
                                     contact_number              VARCHAR(20) NOT NULL,
                                     contact_email				VARCHAR(50) NOT NULL,
                                     brand_status_key   		    INT NOT NULL,
                                     money_percent               INT DEFAULT 0 ,
                                     first_time boolean default 1,
                                     FOREIGN KEY (user_id) REFERENCES `user`(user_id)
);




CREATE TABLE IF NOT EXISTS brand_amenities  (
                                                amenitie_id             INT AUTO_INCREMENT PRIMARY KEY,
                                                brand_id                INT NOT NULL,
                                                photo_url               VARCHAR(255) NOT NULL,
                                                amenitie_name           VARCHAR(50),
                                                description             text,
                                                amenitie_status INT NOT NULL,
                                                FOREIGN KEY (brand_id) REFERENCES brand(brand_id)
);

-- Gym Department table to store gym department information
CREATE TABLE IF NOT EXISTS gym_department (
                                              gym_department_id           INT AUTO_INCREMENT PRIMARY KEY,
                                              gym_department_status_key   INT NOT NULL,
                                              brand_id                    INT NOT NULL,
                                              user_id                     INT,
                                              name                        VARCHAR(255) NOT NULL,
                                              address                     VARCHAR(255),
                                              contact_number              VARCHAR(20),
                                              logo_url                    VARCHAR(255),
                                              wallpaper_url               VARCHAR(255),
                                              thumbnail_url               VARCHAR(255),
                                              description                 text,
                                              latitude 		            DECIMAL(10,8),
                                              longitude 		            DECIMAL(11,8),
                                              rating                      DECIMAL(10, 2) DEFAULT 0,
                                              capacity                    INT,
                                              area                        DECIMAL(10, 2),
                                              city                        VARCHAR(255),
                                              first_time boolean default 0,
                                              FOREIGN KEY (brand_id) REFERENCES brand(brand_id)
);

CREATE TABLE IF NOT EXISTS gym_department_albums (
                                                     id INT PRIMARY KEY AUTO_INCREMENT,
                                                     gym_department_id INT NOT NULL,
                                                     photo_url VARCHAR(255) NOT NULL,
                                                     FOREIGN KEY(gym_department_id) REFERENCES gym_department(gym_department_id)
);

CREATE TABLE IF NOT EXISTS gym_department_schedule (
                                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                                       gym_department_id INT NOT NULL,
                                                       day VARCHAR(10) NOT NULL,
                                                       open_time VARCHAR(255),
                                                       close_time VARCHAR(255),
                                                       FOREIGN KEY (gym_department_id) REFERENCES gym_department(gym_department_id)
);


CREATE TABLE IF NOT EXISTS features (
                                        feature_id INT AUTO_INCREMENT PRIMARY KEY,
                                        feature_icon varchar(150),
                                        feature_name VARCHAR(50),
                                        feature_status INT NOT NULL
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
                                        brand_id         INT NOT NULL,
                                        gym_plan_key   INT,
                                        gym_plan_status_key    INT NOT NULL,
                                        gym_plan_type_key  INT NOT NULL,
                                        name           VARCHAR(255) NOT NULL,
                                        description    text,
                                        price          DECIMAL(10, 2) ,
                                        price_per_hours         DECIMAL(10, 2) ,
                                        plan_sold      INT,
                                        duration       INT ,
                                        plan_before_active_validity       INT NOT NULL,
                                        plan_after_active_validity        INT NOT NULL,
                                        FOREIGN KEY (brand_id) REFERENCES brand(brand_id)
);


CREATE TABLE IF NOT EXISTS gym_department_plans (
                                                    gym_department_id INT NOT NULL,
                                                    plan_id INT NOT NULL,
                                                    FOREIGN KEY (gym_department_id) REFERENCES gym_department(gym_department_id),
                                                    FOREIGN KEY (plan_id) REFERENCES gym_plan(plan_id),
                                                    PRIMARY KEY (gym_department_id, plan_id)
);

CREATE TABLE IF NOT EXISTS gym_department_amenities (
                                                        gym_department_id INT NOT NULL,
                                                        amenitie_id INT NOT NULL,
                                                        FOREIGN KEY (gym_department_id) REFERENCES gym_department(gym_department_id),
                                                        FOREIGN KEY (amenitie_id) REFERENCES brand_amenities(amenitie_id),
                                                        PRIMARY KEY (gym_department_id, amenitie_id)
);

CREATE TABLE IF NOT EXISTS gym_department_features (
                                                       gym_department_feature_id INT AUTO_INCREMENT PRIMARY KEY,
                                                       feature_id INT,
                                                       gym_department_id INT NOT NULL,
                                                       feature_status INT NOT NULL,
                                                       FOREIGN KEY (feature_id) REFERENCES features(feature_id),
                                                       FOREIGN KEY (gym_department_id) REFERENCES gym_department(gym_department_id)
);

-- Shift table to store shift information
CREATE TABLE IF NOT EXISTS shift (
                                     shift_id               INT AUTO_INCREMENT PRIMARY KEY,
                                     gym_department_id      INT NOT NULL,
                                     start_time             TIME NOT NULL,
                                     end_time               TIME NOT NULL,
                                     FOREIGN KEY (gym_department_id) REFERENCES gym_department(gym_department_id)
);



-- Transaction table to do the function with transactions between credit and real money
CREATE TABLE IF NOT EXISTS `transaction` (
                                             transaction_id   INT AUTO_INCREMENT PRIMARY KEY,
                                             wallet_id        INT NOT NULL,
                                             status           VARCHAR(30) NOT NULL,
                                             amount           DECIMAL(10, 2) NOT NULL,
                                             transaction_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                                             FOREIGN KEY (wallet_id) REFERENCES wallet(wallet_id)
);

-- Order table to store order information
CREATE TABLE IF NOT EXISTS `order` (
                                       order_id        INT AUTO_INCREMENT PRIMARY KEY,
                                       user_id         INT NOT NULL,
                                       order_create_time      DATETIME NOT NULL,
                                       order_status_key    INT NOT NULL,
                                       discount              INT NOT NULL,
                                       order_total_money     DECIMAL(10, 2) NOT NULL,
                                       order_note                 VARCHAR(500),
                                       FOREIGN KEY (user_id) REFERENCES `user`(user_id)
);

-- Order Plan Detail table to store order plan details
CREATE TABLE IF NOT EXISTS order_plan_detail (
                                                 order_detail_id INT AUTO_INCREMENT PRIMARY KEY,
                                                 order_id        INT NOT NULL,
                                                 name            VARCHAR(255) NOT NULL,
                                                 quantity        INT NOT NULL,
                                                 price_per_hours         DECIMAL(10, 2) NOT NULL,
                                                 price           DECIMAL(10, 2) NOT NULL,
                                                 duration        INT NOT NULL,
                                                 plan_before_active_validity       INT NOT NULL,
                                                 plan_after_active_validity        INT NOT NULL,
                                                 gym_department_id                 INT NOT NULL,
                                                 `plan_active_time`  DATETIME DEFAULT NULL,
                                                 `item_status_key`   INT NOT NULL,
                                                 `plan_expired_time` DATETIME DEFAULT NULL,
                                                 `description`       TEXT,
                                                 use_status          VARCHAR(100),
                                                 FOREIGN KEY (order_id) REFERENCES `order`(order_id),
                                                 FOREIGN KEY (gym_department_id) REFERENCES gym_department(gym_department_id)
);

-- Transfer table to do the function related to transferring credit history
CREATE TABLE IF NOT EXISTS transfer_credit_history (
                                                       transfer_id      INT AUTO_INCREMENT PRIMARY KEY,
                                                       sender_id        INT NOT NULL,
                                                       receiver_id      INT NOT NULL,
                                                       amount           DECIMAL(10, 2) NOT NULL,
                                                       order_detail_id  INT NOT NULL,
                                                       transfer_date    DATETIME DEFAULT CURRENT_TIMESTAMP,
                                                       FOREIGN KEY (order_detail_id) REFERENCES order_plan_detail (order_detail_id),
                                                       FOREIGN KEY (sender_id) REFERENCES `user`(user_id),
                                                       FOREIGN KEY (receiver_id) REFERENCES `user`(user_id)
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
                                             gym_plan_id    INT,
                                             department_id  INT NOT NULL,
                                             rating         INT NOT NULL,
                                             comments       TEXT,
                                             feedback_time  DATETIME NOT NULL,
                                             feedback_status INT NOT NULL,
                                             FOREIGN KEY (user_id) REFERENCES `user`(user_id),
                                             FOREIGN KEY (gym_plan_id) REFERENCES `gym_plan`(plan_id),
                                             FOREIGN KEY (department_id) REFERENCES gym_department(gym_department_id)
);


CREATE TABLE IF NOT EXISTS notification (
                                            notification_id INT auto_increment NOT NULL,
                                            user_id_send INT NOT NULL,
                                            user_id_receive INT NOT NULL,
                                            message TEXT NOT NULL,
                                            time_send DATETIME NOT NULL,
                                            department_id INT NOT NULL,
                                            order_detail_id INT NOT NULL,
                                            message_type VARCHAR(100) NOT NULL,
                                            status INT NOT NULL,
                                            CONSTRAINT Notification_pk PRIMARY KEY (notification_id),
                                            CONSTRAINT Notification_FK FOREIGN KEY (department_id) REFERENCES fitpass.gym_department(gym_department_id),
                                            CONSTRAINT Notification_FK_1 FOREIGN KEY (user_id_send) REFERENCES fitpass.`user`(user_id),
                                            CONSTRAINT Notification_FK_2 FOREIGN KEY (user_id_receive) REFERENCES fitpass.`user`(user_id),
                                            CONSTRAINT Notification_FK_3 FOREIGN KEY (order_detail_id) REFERENCES fitpass.order_plan_detail(order_detail_id)

);


CREATE TABLE IF NOT EXISTS check_in_history (
                                                check_in_history_id INT auto_increment NOT NULL,
                                                order_detail_id INT NOT NULL,
                                                status_key INT NOT NULL,
                                                `check_in_time` DATETIME NOT NULL,
                                                check_out_time DATETIME NULL,
                                                total_credit DECIMAL(10,2) NULL,
                                                emp_checkin_id INT NOT NULL,
                                                feedback_id INT NULL,
                                                CONSTRAINT check_in_history_pk PRIMARY KEY (check_in_history_id),
                                                CONSTRAINT check_in_history_FK FOREIGN KEY (order_detail_id) REFERENCES fitpass.order_plan_detail(order_detail_id),
                                                CONSTRAINT check_in_history_FK_1 FOREIGN KEY (emp_checkin_id) REFERENCES fitpass.`user`(user_id),
                                                CONSTRAINT check_in_history_FK_2 FOREIGN KEY (feedback_id) REFERENCES fitpass.user_feedback(feedback_id)
);

-- bảng credit card
CREATE TABLE IF NOT EXISTS credit_card (
                                           credit_card_id   INT AUTO_INCREMENT PRIMARY KEY,
                                           user_id  INT NOT NULL,
                                           card_owner_name           VARCHAR(100) NOT NULL,
                                           card_number       varchar(25) NOT NULL,
                                           status           VARCHAR(30) NOT NULL,
                                           FOREIGN KEY (user_id) REFERENCES user(user_id)
);

-- Bảng lịch sử rút tiền
CREATE TABLE IF NOT EXISTS request_withdrawal_history (
                                                          request_withdrawal_history_id   INT AUTO_INCREMENT PRIMARY KEY,
                                                          credit_card_id INT not null,
                                                          withdrawal_code        INT NOT NULL,
                                                          withdrawal_time   timestamp,
                                                          amount_credit    INT NOT NULL,
                                                          actual_money     INT NOT NULL,
                                                          status           VARCHAR(30) NOT NULL,
                                                          FOREIGN KEY (credit_card_id) REFERENCES credit_card(credit_card_id)
);

DELIMITER $$
CREATE TRIGGER update_gym_rating_avg
    AFTER INSERT ON user_feedback
    FOR EACH ROW
BEGIN

    DECLARE dept_id INT;
    DECLARE total DECIMAL(10,2);
    DECLARE count INT;

    SET dept_id = NEW.department_id;

    SELECT IFNULL(SUM(rating),0), COUNT(*) INTO total, count
    FROM user_feedback
    WHERE department_id = dept_id;

    UPDATE gym_department
    SET rating = total/count
    WHERE gym_department_id = dept_id;

END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER update_brand_rating
    AFTER UPDATE ON gym_department
    FOR EACH ROW
BEGIN

    DECLARE avg_rating DECIMAL(10,2);

    SELECT AVG(rating) INTO avg_rating
    FROM gym_department
    WHERE brand_id = NEW.brand_id;

    UPDATE brand
    SET rating = avg_rating
    WHERE brand_id = NEW.brand_id;

END$$
DELIMITER ;

