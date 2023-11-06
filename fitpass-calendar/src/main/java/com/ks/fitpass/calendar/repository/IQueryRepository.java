package com.ks.fitpass.calendar.repository;

public interface IQueryRepository {


    String GET_ALL_CHECKIN_CALENDAR_BY_USER_ID =
            """
                select cih.check_in_history_id, gd.name as 'gym_department_name', gd.address , cih.check_in_time , opd.name as 'gym_plan_name' \s
                from check_in_history cih\s
                join order_plan_detail opd  on cih.order_detail_id = opd.order_detail_id\s
                join `order` o  on o.order_id = opd.order_id\s
                join `user` u on u.user_id = o.user_id\s
                join user_detail ud on u.user_detail_id = ud.user_detail_id\s
                join gym_department gd on gd.gym_department_id = opd.gym_department_id\s
                where u.user_id = ?
            """;


    String GET_CHECKIN_HISTORY_DETAIL_BY_USER_ID_AND_HISTORY_ID = """
                select cih.check_in_history_id, gd.gym_department_id, gd.name as 'gym_department_name', gd.address , cih.check_in_time , 
                opd.name as 'gym_plan_name', cih.feedback_id \s
                from check_in_history cih\s
                join order_plan_detail opd  on cih.order_detail_id = opd.order_detail_id\s
                join `order` o  on o.order_id = opd.order_id\s
                join `user` u on u.user_id = o.user_id\s
                join user_detail ud on u.user_detail_id = ud.user_detail_id\s
                join gym_department gd on gd.gym_department_id = opd.gym_department_id\s
                where u.user_id = ? 
                and cih.check_in_history_id = ?
            """;



    String INSERT_USER_FEEDBACK = """
                INSERT INTO user_feedback (user_id, department_id, rating, comments, feedback_time, feedback_status)
                VALUES (?, ?, ?, ?, ?, 1);
            """;

    String GET_USER_FEEDBACK_BY_CHECKIN_HISTORY_ID = """
                SELECT uf.feedback_id, uf.user_id, uf.department_id, uf.rating, uf.comments, uf.feedback_time, uf.feedback_status
                FROM user_feedback uf
                INNER JOIN check_in_history cih ON uf.feedback_id = cih.feedback_id
                WHERE cih.check_in_history_id = ?;
            """;
    
    String UPDATE_USER_FEEDBACK_RATTING_BY_FEEDBACK_ID = """
                UPDATE user_feedback
                SET rating = ?, comments = ?, feedback_time = ?
                WHERE feedback_id = ?;
            """;

    String GET_LAST_INSERT_FEEDBACK_ID = """
                SELECT LAST_INSERT_ID();
            """;

    String INSERT_CHECK_IN_HISTORY_FEEDBACK = """
                UPDATE check_in_history
                SET feedback_id = ?
                WHERE check_in_history_id = ?;
            """;
}
