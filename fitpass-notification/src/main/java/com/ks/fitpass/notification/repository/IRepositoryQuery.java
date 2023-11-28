package com.ks.fitpass.notification.repository;

public interface IRepositoryQuery {
    String INSERT_NOTIFICATION = """
            INSERT INTO notification (user_id_send, user_id_receive, message, time_send, department_id, message_type, status)
            VALUES (?, ?, ?, ?, ?, ?, ?);
                """;

    String UPDATE_STATUS_NOTIFICATION_BY_ID = """
                UPDATE notification
                SET status = ?
                WHERE notification_id = ?;
            """;
    String GET_ALL_NOTIFICATION_FOR_USER = """
            SELECT notification_id, user_id_send, user_id_receive, message, time_send, department_id, message_type, status
            FROM notification
            WHERE user_id_receive = ?
            ORDER BY status, time_send DESC
            LIMIT ? OFFSET ?
            """;
    String GET_ALL_3_NEWEST_UNSEEN_NOTIFICATION_FOR_USER = """
            SELECT notification_id, user_id_send, user_id_receive, message, time_send, department_id, message_type, status
                FROM notification
            WHERE user_id_receive = ?
                AND status = 0
            ORDER BY time_send DESC
            LIMIT 3;
            """;
    String GET_ALL_NOTIFICATION_FOR_EMPLOYEE = """
            SELECT notification_id, user_id_send, user_id_receive, message, time_send, department_id, message_type, status
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
