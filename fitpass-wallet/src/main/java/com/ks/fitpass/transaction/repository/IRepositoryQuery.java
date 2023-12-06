package com.ks.fitpass.transaction.repository;

public interface IRepositoryQuery {
    String INSERT_INTO_TRANSACTION = """
                INSERT INTO transaction (wallet_id, amount, transaction_date, status)
                VALUES (?, ?, ?, ?);
            """;

    String GET_LIST_BY_USER_ID = """
                SELECT t.transaction_id, t.wallet_id, t.amount, t.transaction_date, t.status
                FROM transaction t
                JOIN wallet w ON t.wallet_id = w.wallet_id
                WHERE w.user_id = ?;
            """;

    String INSERT_INTO_TRANSFER_CREDIT_HISTORY = """
                INSERT INTO `transfer_credit_history` (
                  `sender_id`,
                  `receiver_id`,
                  `amount`,
                  `order_detail_id`,
                  `transfer_date`
                ) VALUES (
                  ?,
                  ?,
                  ?,
                  ?,
                  ?
                );
            """;

    String GET_TOTAL_AMOUNT_TRANSACTION_BY_USER_ID = """
            SELECT
                SUM(t.amount) AS total_amount
            FROM
                wallet w
                    JOIN
                `transaction` t ON w.wallet_id = t.wallet_id
            WHERE
                    w.user_id = ?
            GROUP BY
                w.user_id, w.wallet_id;
            """;

    String COUNT_ALL_CREDIT = """
            SELECT
                SUM(t.amount) AS total_amount
            FROM
                wallet w
                    JOIN
                `transaction` t ON w.wallet_id = t.wallet_id
            GROUP BY
                w.user_id, w.wallet_id;
            """;
}
