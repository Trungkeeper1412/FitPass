package com.ks.fitpass.wallet.repository;

public interface IRepositoryQuery {
    String GET_WALLET_BALANCE_BY_USER_ID = """
            SELECT w.balance
            FROM wallet w
            JOIN user u ON w.user_id = u.user_id
            WHERE u.user_id = ?;
                """;

    String UPDATE_WALLET_BALANCE_BY_USER_ID = """
            UPDATE wallet
            SET balance = ?
            WHERE user_id = ?;
                """;

    String GET_WALLET_ID_BY_USER_ID = """
                SELECT w.wallet_id
                FROM wallet w
                JOIN user u ON w.user_id = u.user_id
                WHERE u.user_id = ?;
            """;
}
