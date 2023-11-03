package com.ks.fitpass.checkInHistory.repository;

public interface IRepositoryQuery {
    String GET_CHECK_IN_TIME_BY_ORDER_DETAIL_ID = """
                SELECT check_in_time
                FROM check_in_history
                WHERE order_detail_id = ?
                  AND check_out_time IS NULL;
            """;

    String GET_CHECK_IN_HISTORY_ID_BY_ORDER_DETAIL_ID_AND_CHECK_IN_TIME = """
                SELECT check_in_history_id\s
                FROM check_in_history
                WHERE order_detail_id = ?
                  AND check_in_time = ?;
            """;

    String UPDATE_CHECK_OUT_TIME_AND_TOTAL_CREDIT = """
                UPDATE check_in_history
                SET check_out_time = ?,
                    total_credit = ?
                WHERE check_in_history_id = ?;
            """;

    String GET_LIST_CHECK_IN_HISTORY_ID_NEED_CHECK_OUT = """
                SELECT check_in_history_id
                FROM check_in_history c
                JOIN order_plan_detail od ON c.order_detail_id = od.order_detail_id
                WHERE check_out_time IS NULL
                AND od.price > 0;
            """;

    String GET_LIST_CHECK_IN_HISTORY_FLEXIBLE_BY_DEPARTMENT_ID = """
                SELECT
                    CONCAT(ud.first_name, ' ', ud.last_name) AS username,
                    ud.phone_number,
                    CONCAT(empd.first_name, ' ', empd.last_name) AS emp_name,
                    ci.check_in_time AS checkInTime,
                    ci.check_out_time AS checkOutTime,
                    opd.price_per_hours AS pricePerHours,
                    ci.total_credit AS totalCredit
                FROM check_in_history ci
                JOIN order_plan_detail opd ON ci.order_detail_id = opd.order_detail_id
                JOIN `order` o ON opd.order_id = o.order_id
                JOIN user u ON o.user_id = u.user_id
                JOIN user_detail ud ON u.user_detail_id = ud.user_detail_id
                LEFT JOIN user emp ON ci.emp_checkin_id = emp.user_id
                LEFT JOIN user_detail empd ON emp.user_detail_id = empd.user_detail_id
                WHERE opd.gym_department_id = ?
                AND opd.price_per_hours > 0
            """;

    String GET_LIST_CHECK_IN_HISTORY_FIXED_BY_DEPARTMENT_ID = """
            SELECT
                                CONCAT(ud.first_name, ' ', ud.last_name) AS username,
                                ud.phone_number,
                                CONCAT(empd.first_name, ' ', empd.last_name) AS emp_name,
                                ci.check_in_time AS checkInTime,
                                opd.name ,
                                opd.price
                            FROM check_in_history ci
                            JOIN order_plan_detail opd ON ci.order_detail_id = opd.order_detail_id
                            JOIN `order` o ON opd.order_id = o.order_id
                            JOIN user u ON o.user_id = u.user_id
                            JOIN user_detail ud ON u.user_detail_id = ud.user_detail_id
                            JOIN user emp ON ci.emp_checkin_id = emp.user_id
                            JOIN user_detail empd ON emp.user_detail_id = empd.user_detail_id
                            WHERE opd.gym_department_id = ?
                            AND opd.price  > 0
            """;
}
