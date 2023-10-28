package com.ks.fitpass.employee.repository;

public interface IRepositoryQuery {
    String GET_LIST_NEED_CHECK_IN_FLEXIBLE_BY_DEPARTMENT_ID = """
            SELECT
                order_detail_id ,
                CONCAT(user_detail.first_name, ' ', user_detail.last_name) AS user_name,
                order_plan_detail.name AS product_name,
                user_detail.phone_number,
                order_plan_detail.price_per_hours,
                order_plan_detail.price,
                user_detail.image_url
            FROM
                order_plan_detail
            INNER JOIN
                `order` ON order_plan_detail.order_id = `order`.order_id
            INNER JOIN
                user ON `order`.user_id = `user`.user_id
            INNER JOIN
                user_detail ON `user`.user_detail_id = user_detail.user_detail_id
            WHERE
                NOW() < order_plan_detail.plan_expired_time
                AND order_plan_detail.gym_department_id = ?
                AND price_per_hours > 0
                AND use_status = "Chưa tập";
                """;

    String GET_LIST_NEED_CHECK_IN_FIXED_BY_DEPARTMENT_ID = """
            SELECT
                order_plan_detail.order_detail_id ,
                CONCAT(user_detail.first_name, ' ', user_detail.last_name) AS user_name,
                order_plan_detail.name AS product_name,
                user_detail.phone_number,
                order_plan_detail.price_per_hours,
                order_plan_detail.price,
                order_plan_detail.duration,
                user_detail.image_url
            FROM
                order_plan_detail
            INNER JOIN
                `order` ON order_plan_detail.order_id = `order`.order_id
            INNER JOIN
                user ON `order`.user_id = `user`.user_id
            INNER JOIN
                user_detail ON `user`.user_detail_id = user_detail.user_detail_id
            WHERE
                NOW() < order_plan_detail.plan_expired_time
                AND order_plan_detail.gym_department_id = ?
                AND price > 0
                AND use_status = "Chưa tập";
                """;

    String GET_LIST_CHECKED_IN_FIXED_BY_DEPARTMENT_ID = """
            SELECT
                order_plan_detail.order_detail_id ,
                CONCAT(user_detail.first_name, ' ', user_detail.last_name) AS user_name,
                order_plan_detail.name AS product_name,
                user_detail.phone_number,
                order_plan_detail.price_per_hours,
                order_plan_detail.price,
                order_plan_detail.duration,
                check_in_history.check_in_time,
                user_detail.image_url
            FROM
                order_plan_detail
            INNER JOIN
                `order` ON order_plan_detail.order_id = `order`.order_id
            INNER JOIN
                user ON `order`.user_id = `user`.user_id
            INNER JOIN
                user_detail ON `user`.user_detail_id = user_detail.user_detail_id
            INNER JOIN
                check_in_history ON check_in_history.order_detail_id = order_plan_detail.order_detail_id
            WHERE
                NOW() < order_plan_detail.plan_expired_time
                AND order_plan_detail.gym_department_id = ?
                AND price > 0
                AND use_status = "Đang tập"
                AND check_in_history.check_out_time IS NULL;
                """;

    String GET_LIST_NEED_CHECK_OUT_FLEXIBLE_BY_DEPARTMENT_ID = """
            SELECT
                order_detail_id ,
                CONCAT(user_detail.first_name, ' ', user_detail.last_name) AS user_name,
                order_plan_detail.name AS product_name,
                user_detail.phone_number,
                order_plan_detail.price_per_hours,
                order_plan_detail.price,
                user_detail.image_url
            FROM
                order_plan_detail
            INNER JOIN
                `order` ON order_plan_detail.order_id = `order`.order_id
            INNER JOIN
                user ON `order`.user_id = `user`.user_id
            INNER JOIN
                user_detail ON `user`.user_detail_id = user_detail.user_detail_id
            WHERE
                NOW() < order_plan_detail.plan_expired_time
                AND order_plan_detail.gym_department_id = ?
                AND price_per_hours > 0
                AND use_status = "Đang tập";
                """;

    String SEARCH_LIST_CHECK_IN_BY_USERNAME = """
            SELECT
                order_detail_id ,
                CONCAT(user_detail.first_name, ' ', user_detail.last_name) AS user_name,
                order_plan_detail.name AS product_name,
                user_detail.phone_number,
                order_plan_detail.price_per_hours,
                order_plan_detail.price,
                user_detail.image_url
            FROM
                order_plan_detail
            INNER JOIN
                `order` ON order_plan_detail.order_id = `order`.order_id
            INNER JOIN
                user ON `order`.user_id = `user`.user_id
            INNER JOIN
                user_detail ON `user`.user_detail_id = user_detail.user_detail_id
            WHERE
                NOW() < order_plan_detail.plan_expired_time
                AND order_plan_detail.gym_department_id = ?
                AND price_per_hours > 0
                AND CONCAT(user_detail.first_name, ' ', user_detail.last_name) like ?
                AND use_status = "Chưa tập";
                """;

    String SEARCH_LIST_FIXED_CHECK_IN_BY_USERNAME = """
            SELECT
                order_detail_id ,
                CONCAT(user_detail.first_name, ' ', user_detail.last_name) AS user_name,
                order_plan_detail.name AS product_name,
                user_detail.phone_number,
                order_plan_detail.price,
                user_detail.image_url
            FROM
                order_plan_detail
            INNER JOIN
                `order` ON order_plan_detail.order_id = `order`.order_id
            INNER JOIN
                user ON `order`.user_id = `user`.user_id
            INNER JOIN
                user_detail ON `user`.user_detail_id = user_detail.user_detail_id
            WHERE
                NOW() < order_plan_detail.plan_expired_time
                AND order_plan_detail.gym_department_id = ?
                AND price > 0
                AND CONCAT(user_detail.first_name, ' ', user_detail.last_name) like ?
                AND use_status = "Chưa tập";
                """;

    String SEARCH_LIST_FIXED_CHECKED_IN_BY_USERNAME = """
            SELECT
                order_plan_detail.order_detail_id ,
                CONCAT(user_detail.first_name, ' ', user_detail.last_name) AS user_name,
                order_plan_detail.name AS product_name,
                user_detail.phone_number,
                order_plan_detail.price,
                order_plan_detail.duration,
                check_in_history.check_in_time,
                user_detail.image_url
            FROM
                order_plan_detail
            INNER JOIN
                `order` ON order_plan_detail.order_id = `order`.order_id
            INNER JOIN
                user ON `order`.user_id = `user`.user_id
            INNER JOIN
                user_detail ON `user`.user_detail_id = user_detail.user_detail_id
            INNER JOIN
                check_in_history ON check_in_history.order_detail_id = order_plan_detail.order_detail_id
            WHERE
                NOW() < order_plan_detail.plan_expired_time
                AND order_plan_detail.gym_department_id = ?
                AND price > 0
                AND CONCAT(user_detail.first_name, ' ', user_detail.last_name) like ?
                AND use_status = "Đang tập"
                AND check_in_history.check_out_time IS NULL;
                """;

    String SEARCH_LIST_FIXED_CHECK_IN_BY_PHONE = """
            SELECT
                order_detail_id ,
                CONCAT(user_detail.first_name, ' ', user_detail.last_name) AS user_name,
                order_plan_detail.name AS product_name,
                user_detail.phone_number,
                order_plan_detail.price,
                user_detail.image_url
            FROM
                order_plan_detail
            INNER JOIN
                `order` ON order_plan_detail.order_id = `order`.order_id
            INNER JOIN
                user ON `order`.user_id = `user`.user_id
            INNER JOIN
                user_detail ON `user`.user_detail_id = user_detail.user_detail_id
            WHERE
                NOW() < order_plan_detail.plan_expired_time
                AND order_plan_detail.gym_department_id = ?
                AND price > 0
                AND phone_number like ?
                AND use_status = "Chưa tập";
                """;

    String SEARCH_LIST_FIXED_CHECKED_IN_BY_PHONE = """
            SELECT
                order_plan_detail.order_detail_id ,
                CONCAT(user_detail.first_name, ' ', user_detail.last_name) AS user_name,
                order_plan_detail.name AS product_name,
                user_detail.phone_number,
                order_plan_detail.price,
                order_plan_detail.duration,
                check_in_history.check_in_time,
                user_detail.image_url
            FROM
                order_plan_detail
            INNER JOIN
                `order` ON order_plan_detail.order_id = `order`.order_id
            INNER JOIN
                user ON `order`.user_id = `user`.user_id
            INNER JOIN
                user_detail ON `user`.user_detail_id = user_detail.user_detail_id
            INNER JOIN
                check_in_history ON check_in_history.order_detail_id = order_plan_detail.order_detail_id
            WHERE
                NOW() < order_plan_detail.plan_expired_time
                AND order_plan_detail.gym_department_id = ?
                AND price > 0
                AND phone_number like ?
                AND use_status = "Đang tập"
                AND check_in_history.check_out_time IS NULL;
                """;
    String SEARCH_LIST_CHECK_OUT_BY_USERNAME = """
            SELECT
                order_detail_id ,
                CONCAT(user_detail.first_name, ' ', user_detail.last_name) AS user_name,
                order_plan_detail.name AS product_name,
                user_detail.phone_number,
                order_plan_detail.price_per_hours,
                order_plan_detail.price,
                user_detail.image_url
            FROM
                order_plan_detail
            INNER JOIN
                `order` ON order_plan_detail.order_id = `order`.order_id
            INNER JOIN
                user ON `order`.user_id = `user`.user_id
            INNER JOIN
                user_detail ON `user`.user_detail_id = user_detail.user_detail_id
            WHERE
                NOW() < order_plan_detail.plan_expired_time
                AND order_plan_detail.gym_department_id = ?
                AND price_per_hours > 0
                AND CONCAT(user_detail.first_name, ' ', user_detail.last_name) like ?
                AND use_status = "Đang tập";
                """;

    String SEARCH_LIST_CHECK_IN_BY_PHONE = """
            SELECT
                order_detail_id ,
                CONCAT(user_detail.first_name, ' ', user_detail.last_name) AS user_name,
                order_plan_detail.name AS product_name,
                user_detail.phone_number,
                order_plan_detail.price_per_hours,
                order_plan_detail.price,
                user_detail.image_url
            FROM
                order_plan_detail
            INNER JOIN
                `order` ON order_plan_detail.order_id = `order`.order_id
            INNER JOIN
                user ON `order`.user_id = `user`.user_id
            INNER JOIN
                user_detail ON `user`.user_detail_id = user_detail.user_detail_id
            WHERE
                NOW() < order_plan_detail.plan_expired_time
                AND order_plan_detail.gym_department_id = ?
                AND price_per_hours > 0
                AND phone_number like ?
                AND use_status = "Chưa tập";
                """;

    String SEARCH_LIST_CHECK_OUT_BY_PHONE = """
            SELECT
                order_detail_id ,
                CONCAT(user_detail.first_name, ' ', user_detail.last_name) AS user_name,
                order_plan_detail.name AS product_name,
                user_detail.phone_number,
                order_plan_detail.price_per_hours,
                order_plan_detail.price,
                user_detail.image_url
            FROM
                order_plan_detail
            INNER JOIN
                `order` ON order_plan_detail.order_id = `order`.order_id
            INNER JOIN
                user ON `order`.user_id = `user`.user_id
            INNER JOIN
                user_detail ON `user`.user_detail_id = user_detail.user_detail_id
            WHERE
                NOW() < order_plan_detail.plan_expired_time
                AND order_plan_detail.gym_department_id = ?
                AND price_per_hours > 0
                AND phone_number like ?
                AND use_status = "Đang tập";
                """;

    String INSERT_CHECK_IN_HISTORY = """
            INSERT INTO check_in_history (order_detail_id, status_key, check_in_time, check_out_time, total_credit, emp_checkin_id)
            VALUES (?, ?, ?, ?, ?, ?);
                """;

    String GET_USER_RECEIVE = """
                 SELECT o.user_id, gym_department_id
                            FROM order_plan_detail od
                            JOIN
                                `order` o ON od.order_id = o.order_id
                            JOIN
                                `user` u ON o.user_id = u.user_id
                            join
                            	user_detail ud on u.user_detail_id = ud.user_detail_id
                            WHERE order_detail_id = ?;
            """;
}
