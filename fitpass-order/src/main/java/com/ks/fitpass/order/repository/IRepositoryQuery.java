package com.ks.fitpass.order.repository;

public interface IRepositoryQuery {
    String INSERT_ORDER = """
            INSERT INTO `order` (user_id, order_create_time, order_status_key,
                                 discount, order_total_money, order_note)
            VALUES (?, ?, ?, ?, ?, ?);
                """;
    String UPDATE_ORDER_DETAIL_ITEM_STATUS = """
            UPDATE order_plan_detail
            SET
                plan_active_time = ?,
                item_status_key = ?,
                plan_expired_time = ?
            WHERE
                order_detail_id = ?
                """;

    String SELECT_LAST_INSERT_ID = """
            SELECT LAST_INSERT_ID()
                """;

    String UPDATE_ORDER_DETAIL_ITEM_STATUS_USE = """
            UPDATE order_plan_detail
            SET use_status = ?
            WHERE order_detail_id = ?;
                """;

    String SELECT_INFO_CHECK_IN_BY_ID = """
            SELECT o.user_id, concat(ud.first_name, ' ',ud.last_name), gym_department_id
                       FROM order_plan_detail od
                       JOIN
                           `order` o ON od.order_id = o.order_id
                       JOIN
                           `user` u ON o.user_id = u.user_id
                       JOIN
                       	user_detail ud ON u.user_detail_id = ud.user_detail_id
                       WHERE order_detail_id = ?;
                """;

    String INSERT_ORDER_PLAN_DETAIL= """
                INSERT INTO order_plan_detail (order_id, name, gym_department_id, quantity, price_per_hours, price, duration,
                                               plan_before_active_validity, plan_after_active_validity, item_status_key, description, plan_expired_time)
                VALUES
                (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
            """;

    String GET_ALL_ORDER_ITEM_BY_USER_ID = """
                SELECT
                    od.order_detail_id,
                    od.order_id,
                    od.name,
                    od.quantity,
                    od.price_per_hours,
                    od.price,
                    od.duration,
                    od.plan_before_active_validity,
                    od.plan_after_active_validity,
                    od.gym_department_id,
                    gd.name as department_name,
                    od.plan_active_time,
                    od.item_status_key,
                    od.plan_expired_time,
                    od.description,
                    o.order_create_time
                FROM
                    order_plan_detail od
                JOIN
                    `order` o ON od.order_id = o.order_id
                JOIN 
                    gym_department gd ON gd.gym_department_id = od.gym_department_id
                WHERE
                    o.user_id = ?;
            """;

    String GET_ORDER_ITEM_BY_STATUS_AND_USER_ID = """
                SELECT
                    od.order_detail_id,
                    od.order_id,
                    od.name,
                    od.quantity,
                    od.price_per_hours,
                    od.price,
                    od.duration,
                    od.plan_before_active_validity,
                    od.plan_after_active_validity,
                    od.gym_department_id,
                    gd.name as department_name,
                    od.plan_active_time,
                    od.item_status_key,
                    od.plan_expired_time,
                    od.description,
                    o.order_create_time
                FROM
                    order_plan_detail od
                JOIN
                    `order` o ON od.order_id = o.order_id
                JOIN 
                    gym_department gd ON gd.gym_department_id = od.gym_department_id
                WHERE
                    o.user_id = ? 
                    AND
                    od.item_status_key = ?;
            """;

    String GET_PRICE_PER_HOURS_BY_ORDER_DETAIL_ID = """
                SELECT price_per_hours
                FROM order_plan_detail
                WHERE order_detail_id = ?;
            """;

    String GET_DEPARTMENT_NAME_BY_ORDER_DETAIL_ID = """
                SELECT gd.name AS gym_department_name
                FROM gym_department gd
                JOIN order_plan_detail opd ON gd.gym_department_id = opd.gym_department_id
                WHERE opd.order_detail_id = ?;
            """;

    String GET_ORDER_DETAIL_CONFIRM_CHECKOUT_BY_DETAIL_ID = """
                SELECT gd.name AS gym_department_name, opd.name, opd.price_per_hours
                FROM order_plan_detail opd
                JOIN gym_department gd ON opd.gym_department_id = gd.gym_department_id
                WHERE opd.order_detail_id = ?;
            """;

    String GET_USER_NAME_BY_ORDER_DETAIL_ID = """
                SELECT CONCAT(ud.first_name, ' ', ud.last_name) AS user_name
                FROM order_plan_detail opd
                JOIN `order` o ON opd.order_id = o.order_id
                JOIN `user` u ON o.user_id = u.user_id
                JOIN user_detail ud ON u.user_detail_id = ud.user_detail_id
                WHERE opd.order_detail_id = ?;
            """;

    String UPDATE_ALL_FIXED_TO_CHECK_IN = """
                UPDATE order_plan_detail
                SET use_status = 'Chưa tập'
                WHERE plan_expired_time > NOW()
                AND price > 0;
            """;

    String IS_FIXED_GYM_PLAN = """
                SELECT CASE WHEN price > 0 THEN TRUE ELSE FALSE END AS result
                FROM order_plan_detail
                WHERE order_detail_id = ?;
            """;

    String DECREASE_DURATION = """
                UPDATE order_plan_detail SET duration = duration - 1 WHERE order_detail_id = ?
            """;

    String GET_LIST_ORDER_DETAIL_EXPIRED = """
                SELECT order_detail_id
                FROM order_plan_detail
                WHERE plan_expired_time IS NOT NULL AND DATE(plan_expired_time) < CURDATE();
            """;

    String UPDATE_ORDER_DETAIL_EXPIRED_STATUS= """
                UPDATE order_plan_detail
                SET item_status_key = ?
                WHERE order_detail_id = your_order_detail_id;
            """;

    String GET_LATEST_ORDER_DETAIL_ID = """
                SELECT order_detail_id
                FROM order_plan_detail
                ORDER BY order_detail_id DESC
                LIMIT 1;
            """;

    String GET_ADMIN_STAT = """
                SELECT
                    COUNT(CASE WHEN price > 0 THEN 1 END) AS total_fixed,
                    COUNT(CASE WHEN price_per_hours > 0 THEN 1 END) AS total_flex
                FROM
                    order_plan_detail;
            """;

    // Select number of order by brand id

    String SELECT_NUMBER_OF_ORDER = """
            SELECT
                COUNT(o.order_id) AS totalOrders
            FROM
                `order` o
            JOIN
                order_plan_detail opd ON o.order_id = opd.order_id
            JOIN
                gym_department g ON opd.gym_department_id = g.gym_department_id
            JOIN
                brand b ON g.brand_id = b.brand_id
            WHERE
                b.brand_id = ?
            """;

    // Select total revenue by brand id
    String SELECT_TOTAL_REVENUE = """
                        SELECT
                            SUM(opd.price) AS totalRevenue
                        FROM
                            `order` o
                        JOIN
                            order_plan_detail opd ON o.order_id = opd.order_id
                        JOIN
                            gym_department g ON opd.gym_department_id = g.gym_department_id
                        JOIN
                            brand b ON g.brand_id = b.brand_id
                        WHERE
                            b.brand_id = ?
            """;

    String GET_TOTAL_BUY_BY_DEPARTMENT_ID = """
                SELECT COUNT(*) AS total_buy
                FROM order_plan_detail opd
                JOIN gym_department gd ON opd.gym_department_id = gd.gym_department_id
                WHERE gd.gym_department_id = ?;
            """;

    String GET_TOTAL_REVENUE_BY_DEPARTMENT_ID = """
                SELECT COALESCE(SUM(opd.price), 0) AS total_revenue
                FROM order_plan_detail opd
                JOIN gym_department gd ON opd.gym_department_id = gd.gym_department_id
                WHERE gd.gym_department_id = ?;           
            """;
}
