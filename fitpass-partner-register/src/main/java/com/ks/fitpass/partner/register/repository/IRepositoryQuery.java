package com.ks.fitpass.partner.register.repository;

public interface IRepositoryQuery {
    String CREATE_BECOME_PARTNER_REQUEST = """
            INSERT INTO become_a_partner_request_history
            (brand_name, brand_owner_name, contact_number, address, web_url,
            contact_email, send_request_time, status)
            VALUES(?, ?, ?, ?, ?, ?, ?, ?);
            """;

    String GET_ALL_BECOME_PARTNER_REQUEST_BY_STATUS = """
            SELECT become_a_partner_request_history_id,brand_name, brand_owner_name, contact_number, address, web_url,
            contact_email, send_request_time, start_handle_request_time,
            cancel_request_time, approve_request_time, cancel_reason, status
            FROM become_a_partner_request_history
            WHERE status = ?;
            """;

    String GET_BECOME_PARTNER_REQUEST_BY_ID = """
            SELECT become_a_partner_request_history_id,brand_name, brand_owner_name, contact_number, address, web_url,
            contact_email, send_request_time, start_handle_request_time,
            cancel_request_time, approve_request_time, cancel_reason, status
            FROM become_a_partner_request_history
            WHERE become_a_partner_request_history_id = ?;
            """;

    String UPDATE_BECOME_PARTNER_REQUEST_STATUS = """
            UPDATE become_a_partner_request_history
            SET status = ?
            WHERE become_a_partner_request_history_id = ?;
            """;

    String UPDATE_START_HANDLE_REQUEST_TIME = """
            UPDATE become_a_partner_request_history
            SET start_handle_request_time = ?
            WHERE become_a_partner_request_history_id = ?;
            """;

    String UPDATE_CANCEL_HANDLE_REQUEST_TIME = """
            UPDATE become_a_partner_request_history
            SET cancel_request_time = ?, cancel_reason = ?
            WHERE become_a_partner_request_history_id = ?;
            """;

    String UPDATE_APPROVE_HANDLE_REQUEST_TIME = """
            UPDATE become_a_partner_request_history
            SET approve_request_time = ?
            WHERE become_a_partner_request_history_id = ?;
            """;
}
