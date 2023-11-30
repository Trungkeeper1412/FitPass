package com.ks.fitpass.core.repository;

public interface IRepositoryQuery {


    String GET_ALL_USER_BY_ROLE_USER = """
                SELECT
                u.user_id,
                ud.user_detail_id,
                ud.first_name,
                ud.last_name,
                ud.email,
                ud.image_url,
                ud.phone_number,
                u.user_deleted
                FROM user u
                JOIN user_detail ud ON u.user_detail_id = ud.user_detail_id
                JOIN user_role ur ON u.user_id = ur.user_id
                JOIN role r ON ur.role_id = r.role_id
                WHERE r.role_id = 4
            """;

    String GET_USER_BY_USER_ACCOUNT = """
                SELECT * FROM user WHERE user_account = ? AND user_deleted = 0
            """;

    String GET_ROLES_BY_USER_ACCOUNT = """
                SELECT
                    T3.role_id,
                    T3.role_name
                FROM user T1
                LEFT JOIN user_role T2
                    ON T1.user_id = T2.user_id
                LEFT JOIN role T3
                    ON T3.role_id = T2.role_id
                WHERE T1.user_account = ?
                    AND T1.user_deleted = 0
            """;

    String UPDATE_USER_PASSWORD = """
                UPDATE user
                SET user_password = ?
                WHERE user_id = ?
            """;

    String GET_KBN_BY_NAME = """
                SELECT * FROM mst_kbn WHERE mst_kbn_name = ?
            """;
    String GET_GYM_PLAN_TYPE_BY_PLAN_KEY = """
                SELECT DISTINCT mkv.mst_kbn_value AS gym_plan_type
                            FROM gym_plan gp
                            JOIN mst_kbn mkv ON gp.gym_plan_type_key = mkv.mst_kbn_key
                            WHERE gp.gym_plan_type_key = ?
                            AND mkv.mst_kbn_name = 'Gym Plan Type'
            """;

    String GET_STATUS_BY_ITEM_STATUS_KEY = """
                SELECT DISTINCT mkv.mst_kbn_value AS item_status
                  FROM order_plan_detail opd
                  JOIN mst_kbn mkv ON opd.item_status_key = mkv.mst_kbn_key
                  WHERE opd.item_status_key = ?
                  AND mkv.mst_kbn_name = 'Gym Plan Status'
            """;

    String INSERT_INTO_USER_DETAIL = """
                INSERT INTO user_detail (first_name, last_name, email, phone_number, address, date_of_birth, gender, image_url,securityId)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?,?);
            """;

    String GET_LAST_INSERT_USER_DETAIL_ID = """
                SELECT user_detail_id
                FROM user_detail
                WHERE\s
                    first_name = ? AND
                    last_name = ? AND
                    email = ? AND
                    phone_number = ? AND
                    address = ? AND
                    date_of_birth = ? AND
                    image_url = ? AND
                    securityId =?;
            """;

    String INSERT_INTO_USER = """
                INSERT INTO user (user_account, user_password, user_detail_id, user_create_time, user_deleted, created_by)
                VALUES (?, ?, ?, ?, ?, ?);
            """;

    String GET_LAST_USER_INSERT_ID = """
                SELECT user_id
                FROM user
                WHERE\s
                    user_account = ? AND
                    user_password = ? AND
                    user_detail_id = ? AND
                    user_create_time = ?;
            """;

    String INSERT_INTO_USER_ROLE = """
                INSERT INTO user_role (user_id, role_id)
                VALUES(?, ?);
            """;

    String GET_NUMBER_OF_ACCOUNT_CREATED_BY_BRAND_ID = """
            SELECT COUNT(DISTINCT user.user_id) AS total_accounts_created
            FROM user
            JOIN brand ON user.created_by = brand.user_id
            WHERE brand.brand_id = ?;
            """;

    String GET_ALL_ACCOUNT_CREATED_BY_BRAND_ID = """
                SELECT u.user_id , u.user_detail_id , ud.first_name , ud.last_name , ud.email , ud.phone_number , ud.image_url, ud.securityId ,gd.name, u.user_deleted
                            FROM user u
                            JOIN brand ON u.created_by = brand.user_id
                            JOIN user_detail ud ON ud.user_detail_id  = u.user_detail_id\s
                            LEFT JOIN gym_department gd ON gd.user_id = u.user_id
                            WHERE brand.brand_id = ?;
            """;

    String GET_USER_DETAIL_BY_USER_DETAIL_ID = """
            SELECT ud.user_detail_id, first_name, last_name, email, phone_number, ud.address, date_of_birth, gender, image_url, ud.securityId, u.user_deleted, gd.name, gd.gym_department_id\s
            FROM user_detail ud
            JOIN `user` u  ON u.user_detail_id = ud.user_detail_id
            LEFT JOIN gym_department gd ON gd.user_id = u.user_id\s
            WHERE ud.user_detail_id = ?
            """;

    String GET_USER_DETAIL_BY_USER_ID = """
            SELECT u.user_id ,ud.user_detail_id, first_name, last_name, email, phone_number, ud.address, date_of_birth, gender, image_url, u.user_deleted
            FROM user_detail ud
            JOIN `user` u  ON u.user_detail_id = ud.user_detail_id
            WHERE u.user_id = ?
            """;


    String UPDATE_USER_DETAIL_BY_USER_DETAIL_ID = """
                UPDATE user_detail
                SET first_name=?, last_name=?, email=?, phone_number=?, address=?, date_of_birth=?, gender=?, image_url=?, securityId = ?
                WHERE user_detail_id=?;
            """;

    String UPDATE_USER_STATUS_BY_USER_ID = """
                UPDATE `user`
                SET user_deleted = ?
                WHERE user_id = ?;
            """;

    String CHECK_EMAIL_EXIST = """
                SELECT COUNT(*) FROM user_detail WHERE email = ?;
            """;

    String CHECK_USERNAME_EXIST = """
                SELECT COUNT(*) FROM user WHERE user_account LIKE '%?%';
            """;

    String CHECK_ACCOUNT_FIRST_TIME_LOGIN = """
                SELECT COUNT(*) FROM user WHERE user_id = ? AND first_time = 1;
            """;

    String UPDATE_FIRST_TIME_LOGIN_STATUS = """
                UPDATE `user`
                SET first_time = ?
                WHERE user_id = ?;
            """;

    String GET_ALL_ACCOUNT_EMPLOYEE_CREATED_BY_DEPARTMENT_ID = """
                 SELECT u.user_id , u.user_detail_id , ud.first_name , ud.last_name , ud.email , ud.phone_number , ud.image_url,ud.securityId, u.user_deleted
                                 FROM user u
                                 JOIN user_detail ud ON ud.user_detail_id  = u.user_detail_id
                                 JOIN user_role ur ON u.user_id = ur.user_id
                                 WHERE ur.role_id = 3
            AND u.created_by IN (
                SELECT user_id
                FROM gym_department
                WHERE gym_department_id = ?
            );
                 """;

    String GET_NUMBER_OF_ACCOUNT_EMPLOYEE_CREATED_BY_DEPARTMENT_ID = """
            SELECT COUNT(u.user_id) AS total_accounts
                	    FROM user u
                		JOIN user_role ur ON u.user_id = ur.user_id
                		WHERE ur.role_id = 3
                		AND u.user_id  IN (
                			SELECT u2.user_id
                			FROM gym_department gd
                			JOIN user u2 ON gd.user_id = u2.user_id
                			WHERE gd.gym_department_id = ?
                		)
            """;

    String GET_USER_EMAIL_BY_USER_ID = """
            SELECT ud.email
                FROM user_detail ud
                JOIN user u ON u.user_detail_id = ud.user_detail_id
            WHERE u.user_id = ?;
            """;
}
