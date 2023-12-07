package com.ks.fitpass.notification.repository;

public interface IRepositoryQuery {
    String INSERT_NOTIFICATION = """
            INSERT INTO notification (user_id_send, user_id_receive, message, time_send, department_id, order_detail_id, message_type, status)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?);
                """;

    String UPDATE_STATUS_NOTIFICATION_BY_ID = """
                UPDATE notification
                SET status = ?
                WHERE notification_id = ?;
            """;
    String GET_ALL_NOTIFICATION_FOR_USER = """
            SELECT\s
                n.notification_id,\s
                n.user_id_send,\s
                n.user_id_receive,\s
                n.message,\s
                n.time_send,\s
                n.department_id,\s
                n.order_detail_id,\s
                n.message_type,\s
                n.status,
                gd.name AS department_name,
                gd.logo_url AS department_logo
            FROM
                notification n
            JOIN
                gym_department gd ON n.department_id = gd.gym_department_id
            WHERE
                n.user_id_receive = ?
            ORDER BY n.status, n.time_send DESC
            LIMIT ? OFFSET ?;
            """;
    String GET_ALL_3_NEWEST_UNSEEN_NOTIFICATION_FOR_USER = """
            SELECT notification_id, user_id_send, user_id_receive, message, time_send, department_id, order_detail_id, message_type, status
                FROM notification
            WHERE user_id_receive = ?
                AND status = 0
            ORDER BY time_send DESC
            LIMIT 3;
            """;
    String GET_ALL_NOTIFICATION_FOR_EMPLOYEE = """
            SELECT notification_id, user_id_send, user_id_receive, message, time_send, department_id, order_detail_id, message_type, status
                FROM notification
            WHERE user_id_receive = ?
                AND DATE(time_send) = CURDATE()
            ORDER BY time_send DESC;
            """;
    String GET_NUM_OF_UNSEEN_NOTIFICATION_BY_USER_RECEIVE_ID = """
                SELECT COUNT(*) AS num_unseen_notifications
                FROM notification
                WHERE user_id_receive = ?
                AND status = 0;
            """;
    String GET_NUM_OF_TOTAL_NOTIFICATION_BY_USER_RECEIVE_ID = """
                SELECT COUNT(*)
                FROM notification
                WHERE user_id_receive = ?
            """;
}
