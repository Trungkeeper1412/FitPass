package com.ks.fitpass.request_withdrawal_history.repository;

public interface IRepositoryQuery {
    String GET_ALL = """
                SELECT request_withdrawal_history_id, credit_card_id, withdrawal_code, withdrawal_time, amount_credit, actual_money, status
                FROM request_withdrawal_history;
            """;

    String GET_ALL_BY_USER_ID = """
                SELECT rwh.request_withdrawal_history_id,
                       rwh.withdrawal_code,
                       rwh.withdrawal_time,
                       rwh.amount_credit,
                       rwh.actual_money,
                       rwh.status,
                       cc.credit_card_id
                FROM request_withdrawal_history rwh
                JOIN credit_card cc ON rwh.credit_card_id = cc.credit_card_id
                JOIN user u ON cc.user_id = u.user_id
                WHERE u.user_id = ?;
            """;

    String GET_ALL_BY_USER_ID_AND_STATUS = """
                SELECT rwh.request_withdrawal_history_id,
                       rwh.withdrawal_code,
                       rwh.withdrawal_time,
                       rwh.amount_credit,
                       rwh.actual_money,
                       rwh.status,
                       cc.credit_card_id
                FROM request_withdrawal_history rwh
                JOIN credit_card cc ON rwh.credit_card_id = cc.credit_card_id
                JOIN user u ON cc.user_id = u.user_id
                WHERE u.user_id = ?
                  AND rwh.status = ?;
            """;

    String GET_STATS_BY_USER_ID = """
                SELECT SUM(CASE WHEN rwh.status = 'Thành công' THEN rwh.amount_credit ELSE 0 END) AS total_amount_credit,
                       SUM(CASE WHEN rwh.status = 'Thành công' THEN rwh.actual_money ELSE 0 END) AS total_actual_money,
                       COUNT(rwh.request_withdrawal_history_id) AS total_request,
                       COUNT(CASE WHEN rwh.status = 'Đang xử lý' THEN 1 END) AS total_pending,
                       COUNT(CASE WHEN rwh.status = 'Thành công' THEN 1 END) AS total_approved
                FROM request_withdrawal_history rwh
                JOIN credit_card cc ON rwh.credit_card_id = cc.credit_card_id
                JOIN user u ON cc.user_id = u.user_id
                WHERE u.user_id = ?;
            """;

    String GET_ALL_STATS = """
                SELECT\s
                    SUM(CASE WHEN rwh.status = 'Thành công' THEN rwh.amount_credit ELSE 0 END) AS total_amount_credit,
                    SUM(CASE WHEN rwh.status = 'Thành công' THEN rwh.actual_money ELSE 0 END) AS total_actual_money,
                    COUNT(rwh.request_withdrawal_history_id) AS total_request,
                    COUNT(CASE WHEN rwh.status = 'Đang xử lý' THEN 1 END) AS total_pending,
                    COUNT(CASE WHEN rwh.status = 'Thành công' THEN 1 END) AS total_approved
                FROM request_withdrawal_history rwh;
            """;

    String CREATE = """
                INSERT INTO request_withdrawal_history
                (credit_card_id, withdrawal_code, withdrawal_time, amount_credit, actual_money, status)
                VALUES(?, ?, ?, ?, ?, ?);
            """;

    String GET_ALL_BY_STATUS = """
                SELECT request_withdrawal_history_id, credit_card_id, withdrawal_code, withdrawal_time, amount_credit, actual_money, status
                FROM request_withdrawal_history
                WHERE status = ?;
            """;

    String GET_ALL_BY_STATUS_WITH_BRAND_NAME = """
                SELECT rwh.request_withdrawal_history_id,
                       rwh.credit_card_id,
                       rwh.withdrawal_code,
                       rwh.withdrawal_time,
                       rwh.amount_credit,
                       rwh.actual_money,
                       rwh.status,
                       b.money_percent,
                       b.name
                FROM request_withdrawal_history rwh
                JOIN credit_card cc ON rwh.credit_card_id = cc.credit_card_id
                JOIN brand b ON cc.user_id = b.user_id
                WHERE rwh.status = ?;
            """;

    String GET_ALL_WITH_BRAND_NAME = """
                SELECT rwh.request_withdrawal_history_id,
                       rwh.credit_card_id,
                       rwh.withdrawal_code,
                       rwh.withdrawal_time,
                       rwh.amount_credit,
                       rwh.actual_money,
                       rwh.status,
                       b.money_percent,
                       b.name
                FROM request_withdrawal_history rwh
                JOIN credit_card cc ON rwh.credit_card_id = cc.credit_card_id
                JOIN brand b ON cc.user_id = b.user_id;
            """;

    String GET_BY_ID = """
                SELECT rwh.request_withdrawal_history_id,
                       rwh.amount_credit,
                       rwh.actual_money,
                       b.name,
                       cc.bank_name,
                       cc.card_number,
                       cc.card_owner_name
                FROM request_withdrawal_history rwh
                JOIN credit_card cc ON rwh.credit_card_id = cc.credit_card_id
                JOIN brand b ON cc.user_id = b.user_id
                WHERE rwh.request_withdrawal_history_id = ?;
            """;

    String UPDATE_STATUS = """
                UPDATE request_withdrawal_history
                SET status = ?
                WHERE request_withdrawal_history_id = ?;
            """;

    String GET_NUMBER_PERCENTAGE = """
                SELECT b.money_percent
                FROM request_withdrawal_history
                JOIN credit_card cc ON request_withdrawal_history.credit_card_id = cc.credit_card_id
                JOIN brand b ON cc.user_id = b.user_id
                WHERE request_withdrawal_history_id = ?;
            """;

    String GET_USER_ID_BY_REQUEST_HISTORY_ID = """
                SELECT u.user_id
                FROM request_withdrawal_history rwh
                JOIN credit_card cc ON rwh.credit_card_id = cc.credit_card_id
                JOIN user u ON cc.user_id = u.user_id
                WHERE rwh.request_withdrawal_history_id = ?;
            """;

    String COUNT_ALL_BRAND_CREDIT = """
                SELECT SUM(rwh.amount_credit)
                FROM request_withdrawal_history rwh
                WHERE rwh.status = 'Thành công';
            """;

    String GET_ALL_REQUEST_HISTORY_BRAND_ADMIN = """
                SELECT
                    b.name AS name,
                    COUNT(rwh.request_withdrawal_history_id) AS total_request,
                    COALESCE(SUM(rwh.amount_credit), 0) AS amount_credit,
                    COALESCE(SUM(rwh.actual_money), 0) AS actual_money
                FROM
                    brand b
                LEFT JOIN
                    credit_card cc ON b.user_id = cc.user_id
                LEFT JOIN
                    request_withdrawal_history rwh ON cc.credit_card_id = rwh.credit_card_id AND rwh.status = 'Thành công'
                GROUP BY
                    b.brand_id;
            """;
}
