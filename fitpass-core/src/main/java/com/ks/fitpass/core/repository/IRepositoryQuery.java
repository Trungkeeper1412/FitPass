package com.ks.fitpass.core.repository;

public interface IRepositoryQuery {

    String GET_USER_BY_USER_ACCOUNT = """
            SELECT * FROM user WHERE user_account = ? AND user_deleted = 0
        """;

    String GET_ROLES_BY_USER_ACCOUNT = """
            SELECT
                T3.role_id,
                T3.role_name
            FROM user T1
            LEFT JOIN user_role T2
                ON T1.user_id = T2.user_id
            LEFT JOIN role T3
                ON T3.role_id = T2.role_id
            WHERE T1.user_account = ?
                AND T1.user_deleted = 0
        """;

    String UPDATE_USER_PASSWORD = """
            UPDATE user
            SET user_password = ?
            WHERE user_id = ?
        """;

    String GET_KBN_BY_NAME = """
            SELECT * FROM mst_kbn WHERE mst_kbn_name = ?
        """;
}
