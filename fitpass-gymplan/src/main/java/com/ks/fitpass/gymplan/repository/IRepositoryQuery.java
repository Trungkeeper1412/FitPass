package com.ks.fitpass.gymplan.repository;

public interface IRepositoryQuery {
    // Query to get all plans of a gym department
    String GET_ALL_GYM_PLANS_BY_DEPARTMENT_ID = """
            SELECT                   gp.plan_id ,
                                     gp.brand_id,
                                     gp.gym_plan_status_key,
                                     gp.gym_plan_type_key,
            	gp.name,
            	gp.price,
            	gp.price_per_hours,
            	gp.duration,
            	gp.plan_before_active_validity,
            	gp.plan_after_active_validity,
            	gp.description,
            	gdp.gym_department_id
            	FROM gym_plan gp
            	JOIN gym_department_plans gdp ON gp.plan_id = gdp.plan_id
            	JOIN gym_department gd ON gdp.gym_department_id = gd.gym_department_id
            	WHERE gdp.gym_department_id = ?
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
                gd.logo_url AS logo_department,
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
                	   gym_plan_status_key as status,
                	   description
                FROM gym_plan
                where brand_id = ?
                and gym_plan_type_key = 1
            """;

    String GET_ALL_GYM_PLAN_FLEX_BY_BRAND_ID_ACTIVE = """
                SELECT plan_id,
                	   name,
                	   price_per_hours,
                	   plan_before_active_validity,
                	   plan_after_active_validity,
                	   gym_plan_status_key as status,
                	   description
                FROM gym_plan
                where brand_id = ?
                and gym_plan_type_key = 1 AND gym_plan_status_key = 1
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
                	   gym_plan_status_key as status,
                	   description
                FROM gym_plan
                where brand_id = ?
                and gym_plan_type_key = 2
            """;
    String GET_ALL_GYM_PLAN_FIXED_BY_BRAND_ID_ACTIVE = """
                SELECT plan_id,
                	   name,
                	   price,
                	   duration,
                	   plan_before_active_validity,
                	   plan_after_active_validity,
                	   gym_plan_status_key as status,
                	   description
                FROM gym_plan
                where brand_id = ?
                and gym_plan_type_key = 2 AND gym_plan_status_key = 1
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

    String INSERT_GYM_PLAN_DEPARTMENT = """
                INSERT INTO gym_department_plans
                (gym_department_id, plan_id)
                VALUES(?, ?);
            """;

    String GET_GYM_PLAN_DEPARTMENT_FIXED_BY_DEPARTMENT_ID = """
            								SELECT gp.plan_id ,
            												gp.name,
            												gp.price,
            												gp.duration,
            												gp.plan_before_active_validity,
            												gp.plan_after_active_validity,
            gp.description
            								FROM gym_plan gp
            								JOIN gym_department_plans gdp ON gp.plan_id = gdp.plan_id
            								JOIN gym_department gd ON gdp.gym_department_id = gd.gym_department_id
            								WHERE gdp.gym_department_id = ?
            								AND gp.gym_plan_type_key = 2;
            				""";

    String GET_GYM_PLAN_DEPARTMENT_FLEX_BY_DEPARTMENT_ID = """
                SELECT gp.plan_id ,
                	   gp.name,
                	   gp.price_per_hours,
                	   gp.plan_before_active_validity,
                	   gp.plan_after_active_validity,
                       gp.description
                FROM gym_plan gp
                JOIN gym_department_plans gdp ON gp.plan_id = gdp.plan_id
                JOIN gym_department gd ON gdp.gym_department_id = gd.gym_department_id
                WHERE gdp.gym_department_id = ?
                AND gp.gym_plan_type_key = 1;
            """;

    String DELETE_ALL_GYM_PLAN_BY_DEPARTMENT_ID = """
                DELETE FROM gym_department_plans
                WHERE gym_department_id = ?;
            """;

    String CHECK_GYM_PLAN_IN_DEPARTMENT_USE = """
            	SELECT COUNT(*) FROM gym_department_plans
            	WHERE plan_id = ?;
            """;

    String GET_NUMBER_OF_GYM_PLAN = """
            	SELECT COUNT(*) FROM gym_plan
            	WHERE brand_id = ?;
            """;

    String GET_TOTAL_GYM_PLAN_DEPARTMENT = """
            	SELECT COUNT(*) FROM gym_department_plans
            	WHERE gym_department_id = ?;
            """;

    String GET_GYM_PLAN_BUY_STAT = """
            SELECT
                opd.name,
                opd.price,
                opd.price_per_hours,
                COUNT(DISTINCT o.order_id) AS total_buy
            FROM
                order_plan_detail opd
            JOIN
                gym_department_plans gdp ON opd.gym_department_id = gdp.gym_department_id
            JOIN
                gym_plan gp ON gdp.plan_id = gp.plan_id
            JOIN
                `order` o ON opd.order_id = o.order_id
            WHERE
                opd.gym_department_id IN (SELECT gym_department_id FROM gym_department WHERE brand_id = ?) AND
                o.order_status_key = 1 -- Assuming 1 is the key for a successfully completed order
            GROUP BY
                opd.name, opd.price, opd.price_per_hours;
            """;

    String GET_GYM_PLAN_BUY_STAT_BY_DEPARTMENT_ID = """
            SELECT
                opd.name,
                opd.price,
                opd.price_per_hours,
                COUNT(DISTINCT o.order_id) AS total_buy
            FROM
                order_plan_detail opd
            JOIN
                gym_department_plans gdp ON opd.gym_department_id = gdp.gym_department_id
            JOIN
                gym_plan gp ON gdp.plan_id = gp.plan_id
            JOIN
                `order` o ON opd.order_id = o.order_id
            WHERE
                opd.gym_department_id = ? AND
                o.order_status_key = 1 -- Assuming 1 is the key for a successfully completed order
            GROUP BY
                opd.name, opd.price, opd.price_per_hours;
            """;
}
