package com.ks.fitpass.notification.repository;

public interface IRepositoryQuery {
    String INSERT_NOTIFICATION = """
            INSERT INTO notification (user_id_send, user_id_receive, message, time_send, department_id, message_type, status)
            VALUES (?, ?, ?, ?, ?, ?, ?);
                """;
    String GET_CONFIRM_CHECK_IN_BY_USER_RECEIVE_ID = """
            SELECT notification_id, user_id_send, message, time_send, department_id, message_type, user_id_receive
            FROM notification
            WHERE user_id_receive = ?
            AND time_send = (
                SELECT MAX(time_send)
                FROM notification
                WHERE user_id_receive = ?
            )
            AND message_type = 'Xác nhận check in'
            AND status = 0;
                """;

    String GET_ALL_CONFIRM_CHECK_IN_BY_EMP_RECEIVE_ID_AND_TYPE = """
                SELECT notification_id, user_id_send, user_id_receive, message, time_send, department_id, message_type
                FROM notification
                WHERE user_id_receive = ?
                AND message_type = ?
                AND status = 0;
            """;


    String GET_ALL_CONFIRM_CHECK_OUT_BY_EMP_RECEIVE_ID_AND_TYPE = """
            SELECT notification_id, user_id_send, user_id_receive, message, time_send, department_id, message_type
            FROM notification
            WHERE user_id_receive = ?
            AND message_type = ?
            AND status = 0;
                """;

    String GET_CONFIRM_CHECK_OUT_BY_USER_RECEIVE_ID = """
            SELECT notification_id, user_id_send, message, time_send, department_id, message_type, user_id_receive
            FROM notification
            WHERE user_id_receive = ?
            AND time_send = (
                SELECT MAX(time_send)
                FROM notification
                WHERE user_id_receive = ?
            )
            AND message_type = 'Xác nhận check out'
            AND status = 0;
                """;

    String UPDATE_STATUS_NOTIFICATION_BY_ID = """
                UPDATE notification
                SET status = ?
                WHERE notification_id = ?;
            """;
}
