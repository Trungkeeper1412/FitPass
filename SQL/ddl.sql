-- Bảng lưu trữ thông tin người dùng
CREATE TABLE user (
                      user_id          INT AUTO_INCREMENT PRIMARY KEY,
                      user_account     VARCHAR(250) CHARACTER SET utf8mb4 NOT NULL,
                      user_password    VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL,
                      user_create_time VARCHAR(20) NOT NULL,
                      user_deleted     TINYINT(1) NOT NULL
);


-- Bảng lưu trữ các vai trò của người dùng
CREATE TABLE role (
                      role_id   INT AUTO_INCREMENT PRIMARY KEY,
                      role_name VARCHAR(20) CHARACTER SET utf8mb4 NOT NULL
);


-- Bảng lưu trữ quan hệ giữa người dùng và vai trò
CREATE TABLE user_role (
                           user_role_id INT AUTO_INCREMENT PRIMARY KEY,
                           user_id      INT NOT NULL,
                           role_id      INT NOT NULL,
                           FOREIGN KEY (user_id) REFERENCES user (user_id),
                           FOREIGN KEY (role_id) REFERENCES role (role_id)
);