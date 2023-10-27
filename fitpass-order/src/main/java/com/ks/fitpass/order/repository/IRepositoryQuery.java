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

    String INSERT_ORDER_PLAN_DETAIL= """
                INSERT INTO order_plan_detail (order_id, name, gym_department_id, quantity, price_per_hours, price, duration,
                                               plan_before_active_validity, plan_after_active_validity, item_status_key, description)
                VALUES
                (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
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

}
