package com.ks.fitpass.gymplan.repository;

public interface IRepositoryQuery {
    // Query to get all plans of a gym department
    String GET_ALL_GYM_PLANS_BY_DEPARTMENT_ID = """
            SELECT gp.*,gdp.gym_department_id, mkv.mst_kbn_value AS gym_plan_type
            FROM gym_plan gp
            JOIN mst_kbn mkv ON gp.gym_plan_type_key = mkv.mst_kbn_key
            JOIN gym_department_plans gdp ON gp.plan_id = gdp.plan_id
            WHERE gdp.gym_department_id = ?
            AND mkv.mst_kbn_name = 'Gym Plan Type';
            """;


    String GET_GYM_PLAN_BY_GYM_PLAN_ID = """
        SELECT\s
            gp.plan_id,
            gp.brand_id,
            gp.gym_plan_key,
            gp.gym_plan_status_key,
            gp.gym_plan_type_key,
            gp.name,
            gp.description,
            gp.price,
            gp.price_per_hours,
            gp.plan_sold,
            gp.duration,
            gp.plan_before_active_validity,
            gp.plan_after_active_validity,
            gd.name AS name_department,
            gd.gym_department_id
        FROM gym_plan gp
        JOIN gym_department_plans gdp ON gp.plan_id = gdp.plan_id
        JOIN gym_department gd ON gdp.gym_department_id = gd.gym_department_id
        WHERE gp.plan_id = ? AND gdp.gym_department_id = ?;
            """;

    String GET_ALL_GYM_PLAN_FLEX_BY_BRAND_ID = """
                SELECT plan_id,
                	   name,
                	   price_per_hours,
                	   plan_before_active_validity,
                	   plan_after_active_validity,
                	   gym_plan_status_key as status
                FROM gym_plan
                where brand_id = ?
                and gym_plan_type_key = 1
            """;

    String CREATE_GYM_PLAN_FLEX = """
            INSERT INTO gym_plan
                (brand_id, gym_plan_status_key, gym_plan_type_key, name, description, price_per_hours, plan_before_active_validity, plan_after_active_validity)
                VALUES(?, ?, ?, ?, ?, ?, ?, ?);
            """;

    String GET_GYM_PLAN_FLEX_DETAIL_BY_GYM_PLAN_ID = """
                SELECT plan_id,
                	   name,
                	   price_per_hours,
                	   plan_before_active_validity,
                	   plan_after_active_validity,
                	   gym_plan_status_key as status,
                	   description
                FROM gym_plan
                where plan_id = ?
                and gym_plan_type_key = 1
            """;

    String UPDATE_GYM_PLAN_FLEX = """
                UPDATE gym_plan
                SET gym_plan_status_key= ?, name=?, description=?, price_per_hours=?, plan_before_active_validity=?, plan_after_active_validity=?
                WHERE plan_id= ?;
            """;

    String GET_ALL_GYM_PLAN_FIXED_BY_BRAND_ID = """
                SELECT plan_id,
                	   name,
                	   price,
                	   duration,
                	   plan_before_active_validity,
                	   plan_after_active_validity,
                	   gym_plan_status_key as status
                FROM gym_plan
                where brand_id = ?
                and gym_plan_type_key = 2
            """;

    String CREATE_GYM_PLAN_FIXED = """
            INSERT INTO gym_plan
                (brand_id, gym_plan_status_key, gym_plan_type_key, name, description, price, duration, plan_before_active_validity, plan_after_active_validity)
                VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);
            """;

    String GET_GYM_PLAN_FIXED_DETAIL_BY_GYM_PLAN_ID = """
                SELECT plan_id,
                	   name,
                	   price,
                	   duration,
                	   plan_before_active_validity,
                	   plan_after_active_validity,
                	   gym_plan_status_key as status,
                	   description
                FROM gym_plan
                where plan_id = ?
                and gym_plan_type_key = 2
            """;

    String UPDATE_GYM_PLAN_FIXED = """
                UPDATE gym_plan
                SET gym_plan_status_key= ?, name=?, description=?, price=?, plan_before_active_validity=?, plan_after_active_validity=?, duration = ?
                WHERE plan_id= ?;
            """;
}
