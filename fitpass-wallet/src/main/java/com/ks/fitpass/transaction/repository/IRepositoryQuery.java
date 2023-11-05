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
}
