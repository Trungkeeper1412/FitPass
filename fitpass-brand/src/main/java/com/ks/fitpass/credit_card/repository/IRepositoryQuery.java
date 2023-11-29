package com.ks.fitpass.credit_card.repository;

public interface IRepositoryQuery {
    String GET_ALL_CREDIT_CARD = """
                SELECT credit_card_id, user_id, card_owner_name, card_number, status, bank_name
                FROM credit_card
                WHERE status = 'Đang hoạt động' AND user_id = ?;
            """;

    String GET_ONE_CREDIT_CARD = """
                SELECT credit_card_id, user_id, card_owner_name, card_number, status, bank_name
                FROM credit_card
                WHERE credit_card_id = ?;
            """;

    String CREATE_CREDIT_CARD = """
                INSERT INTO credit_card
                (user_id, card_owner_name, card_number, status, bank_name)
                VALUES(?, ?, ?, ?, ?);
            """;

    String UPDATE_CREDIT_CARD = """
                UPDATE credit_card
                SET card_owner_name=?, card_number=?, status=?, bank_name=?
                WHERE credit_card_id= ?;
            """;

    String DELETE_CREDIT_CARD = """
                UPDATE credit_card
                SET status= 'Không hoạt động'
                WHERE credit_card_id= ?;
            """;

    String GET_LAST_CREDIT_CARD_ID = """
                SELECT credit_card_id
                FROM credit_card
                ORDER BY credit_card_id DESC
                LIMIT 1;
            """;

    String CHECK_CREDIT_CARD_EXIST = """
                SELECT COUNT(credit_card_id)
                FROM credit_card
                WHERE card_number = ? AND bank_name = ? AND status = 'Đang hoạt động'
                AND user_id = ?;
            """;
}
