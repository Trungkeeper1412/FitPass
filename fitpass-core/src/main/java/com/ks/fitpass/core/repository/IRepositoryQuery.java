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
    String GET_GYM_PLAN_TYPE_BY_PLAN_KEY = """
              SELECT DISTINCT mkv.mst_kbn_value AS gym_plan_type
                  FROM gym_plan gp
                  JOIN mst_kbn mkv ON gp.gym_plan_type_key = mkv.mst_kbn_key
                  WHERE gp.gym_plan_key = ?
                  AND mkv.mst_kbn_name = 'Gym Plan Type'
            """;

    String GET_STATUS_BY_ITEM_STATUS_KEY = """
                SELECT DISTINCT mkv.mst_kbn_value AS item_status
                  FROM order_plan_detail opd
                  JOIN mst_kbn mkv ON opd.item_status_key = mkv.mst_kbn_key
                  WHERE opd.item_status_key = ?
                  AND mkv.mst_kbn_name = 'Gym Plan Status'
            """;
}
