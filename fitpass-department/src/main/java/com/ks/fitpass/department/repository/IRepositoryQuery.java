package com.ks.fitpass.department.repository;

public interface IRepositoryQuery {
    String GET_ALL_DEPARTMENT_BY_STATUS = """
                 SELECT
                     d.gym_department_id,
                     d.brand_id,
                     d.name,
                     d.address,
                     d.contact_number,
                     d.logo_url,

                     d.wallpaper_url,

                     d.description,
                     d.latitude,
                     d.longitude,
                     d.rating,

                     d.capacity,
                     d.area,

                     d.gym_department_status_key,
                     kbn_department_status.mst_kbn_value AS gym_department_status_name                     
                 FROM gym_department d
                 LEFT JOIN mst_kbn kbn_department_status
                     ON d.gym_department_status_key = kbn_department_status.mst_kbn_key
                     AND kbn_department_status.mst_kbn_name = 'DEPARTMENT_STATUS'  
                     WHERE d.gym_department_status_key = ?
            """;
    String GET_ALL_DEPARTMENT_ORDER_BY_RATING = """
                 SELECT
                     d.gym_department_id,
                     d.brand_id,
                     d.name,
                     d.address,
                     d.contact_number,
                     d.logo_url,
                     d.wallpaper_url,
                     d.description,
                     d.latitude,
                     d.longitude,
                     d.rating,

                     d.capacity,
                     d.area,

                     d.gym_department_status_key,
                     kbn_department_status.mst_kbn_value AS gym_department_status_name                     
                 FROM gym_department d
                 LEFT JOIN mst_kbn kbn_department_status
                     ON d.gym_department_status_key = kbn_department_status.mst_kbn_key
                     AND kbn_department_status.mst_kbn_name = 'DEPARTMENT_STATUS'  
                 WHERE d.gym_department_status_key = ?
                 ORDER BY d.rating DESC
            """;

    String GET_ALL_DEPARTMENT_BY_BRAND_ID = """
                 SELECT
                     d.gym_department_id,
                     d.brand_id,
                     d.name,
                     d.address,
                     d.contact_number,
                     d.logo_url,
                     d.wallpaper_url,
                     d.description,
                     d.latitude,
                     d.longitude,
                     d.rating,

                     d.capacity,
                     d.area,

                     d.gym_department_status_key,
                     kbn_department_status.mst_kbn_value AS gym_department_status_name                     
                 FROM gym_department d
                 LEFT JOIN mst_kbn kbn_department_status
                     ON d.gym_department_status_key = kbn_department_status.mst_kbn_key
                     AND kbn_department_status.mst_kbn_name = 'DEPARTMENT_STATUS'  
                 WHERE d.brand_id = ?
                 AND d.gym_department_status_key = ?
                 ORDER BY d.rating DESC
            """;

    String GET_DEPARTMENT_BY_BRAND_ID = """
                 SELECT
                                                            d.gym_department_id,
                                                            d.brand_id,
                                                            d.name,
                                                            d.address,
                                                            d.contact_number,
                                                            d.logo_url,
                                                            d.wallpaper_url,
                                                            d.description,
                                                            d.latitude,
                                                            d.longitude,
                                                            d.rating,
                               
                                                            d.capacity,
                                                            d.area,
                               
                                                            d.gym_department_status_key,
                                                            kbn_department_status.mst_kbn_value AS gym_department_status_name                    \s
                                                        FROM gym_department d
                                                        LEFT JOIN mst_kbn kbn_department_status
                                                            ON d.gym_department_status_key = kbn_department_status.mst_kbn_key
                                                            AND kbn_department_status.mst_kbn_name = 'DEPARTMENT_STATUS' \s
                                                        WHERE d.gym_department_status_key = ? AND d.brand_id = ?
                                                        ORDER BY d.rating DESC
            """;

            String GET_DEPARTMENT_BY_ID = """
            SELECT  
                     d.gym_department_id,
                     d.brand_id,
                     d.name,
                     d.address,
                     d.contact_number,
                     d.logo_url,

                     d.wallpaper_url,
                     d.description,
                     d.latitude,
                     d.longitude,
                     d.rating,   
                     d.capacity,
                     d.area,                  

                d.gym_department_status_key,
                kbn_department_status.mst_kbn_value AS gym_department_status_name
                FROM gym_department d
                LEFT JOIN mst_kbn kbn_department_status
                ON d.gym_department_status_key = kbn_department_status.mst_kbn_key
                AND kbn_department_status.mst_kbn_name = 'DEPARTMENT_STATUS'
                WHERE d.gym_department_id =?
                """;

    String GET_DEPARTMENT_BY_USER = """
                 SELECT 
                     d.gym_department_id,
                     d.brand_id,
                     d.name,
                     d.address,
                     d.contact_number,
                     d.logo_url,

                     d.wallpaper_url,
                     d.description,
                     d.latitude,
                     d.longitude,
                     d.rating,
                     d.capacity,
                     d.area,                     

                     d.gym_department_status_key,
                     kbn_department_status.mst_kbn_value AS gym_department_status_name
                 FROM gym_department d
                 LEFT JOIN mst_kbn kbn_department_status
                     ON d.gym_department_status_key = kbn_department_status.mst_kbn_key
                     AND kbn_department_status.mst_kbn_name = 'DEPARTMENT_STATUS'
                 INNER JOIN user u ON d.user_id = u.user_id
                 INNER JOIN user_role ur ON u.user_id = ur.user_id
                     WHERE u.user_id = ?        
            """;

    String UPDATE_DEPARTMENT = """
                    UPDATE gym_department
                    SET
                        user_id = ?,
                        name = ?,
                        address = ?,
                        contact_number = ?,
                        logo_url = ?,
                        image_url = ?,
                        description = ?,
                        gym_department_status_key = ?
                WHERE gym_department_id = ?
            """;

    String GET_DEPARTMENT_SCHEDULE_BY_ID_DEPARTMENT = """
            SELECT  
                     dc.id,
                     dc.gym_department_id,
                     dc.day,
                     dc.open_time,
                     dc.close_time
                FROM gym_department_schedule dc
                    WHERE dc.gym_department_id =?
                """;

    String GET_DEPARTMENT_ALBUMS_BY_ID_DEPARTMENT = """
            SELECT  
                     da.id,
                     da.gym_department_id,
                     da.photo_url,
                     da.description
                FROM gym_department_albums da
                    WHERE da.gym_department_id =?
                """;

    // Query to get all plans of a gym department
    String GET_ALL_GYM_PLANS_BY_DEPARTMENT_ID = """
                SELECT gp.*, mkv.mst_kbn_value AS gym_plan_type
                FROM gym_plan gp
                JOIN mst_kbn mkv ON gp.gym_plan_type_key = mkv.mst_kbn_key
                WHERE gp.gym_department_id = ?
                AND mkv.mst_kbn_name = 'Gym Plan Type';
            """;

    // Query to get a gym plan by ID and department ID
    String GET_GYM_PLAN_BY_ID = """
    SELECT
        gp.plan_id,
        gp.gym_department_id,
        gp.gym_plan_key,
        gp.gym_plan_status_key,
        gp.name,
        gp.description,
        gp.price,
        gp.price_per_hours,
        gp.plan_sold,
        gp.duration,
        gp.plan_before_active_validity,
        gp.plan_after_active_validity,
        gp.image_url,
        kbn_plan_type.mst_kbn_value AS gym_plan_type,
        kbn_plan_status.mst_kbn_value AS gym_plan_status
    FROM gym_plan gp
    LEFT JOIN mst_kbn kbn_plan_type
        ON gp.gym_plan_type_key = kbn_plan_type.mst_kbn_key
        AND kbn_plan_type.mst_kbn_name = 'PLAN_TYPE'
    LEFT JOIN mst_kbn kbn_plan_status
        ON gp.gym_plan_status_key = kbn_plan_status.mst_kbn_key
        AND kbn_plan_status.mst_kbn_name = 'PLAN_STATUS'
    WHERE gp.plan_id = ? AND gp.gym_department_id = ?
""";

    // Query to create a new gym plan
    String CREATE_GYM_PLAN = """
    INSERT INTO gym_plan (
        gym_department_id,
        gym_plan_key,
        gym_plan_status_key,
        gym_plan_type_key,
        name,
        description,
        price,
        price_per_hours,
        plan_sold,
        duration,
        plan_before_active_validity,
        plan_after_active_validity,
        image_url
    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
""";

    // Query to update a gym plan
    String UPDATE_GYM_PLAN = """
    UPDATE gym_plan
    SET
        gym_department_id = ?,
        gym_plan_key = ?,
        gym_plan_status_key = ?,
        gym_plan_type_key = ?,
        name = ?,
        description = ?,
        price = ?,
        price_per_hours = ?,
        plan_sold = ?,
        duration = ?,
        plan_before_active_validity = ?,
        plan_after_active_validity = ?,
        image_url = ?
    WHERE plan_id = ?
""";

    // Query to delete a gym plan
    String DELETE_GYM_PLAN = """
    DELETE FROM gym_plan
    WHERE plan_id = ?
""";


    String GET_DEPARTMENT_FEEDBACK = """
    SELECT
        uf.feedback_id,
        uf.user_id,
        ud.first_name,
        ud.last_name,
        uf.department_id,
        uf.rating,
        uf.comments,
        uf.feedback_time,
        uf.feedback_status
    FROM
        user_feedback uf
        INNER JOIN `user` u ON uf.user_id = u.user_id
        INNER JOIN user_detail ud ON u.user_detail_id = ud.user_detail_id
    WHERE
        uf.department_id = ?
""";

    String GET_DEPARTMENT_FEATURES = """
    SELECT
        feature_id,
        gym_department_id,
        feature_icon,
        feature_name,
        isSelected
    FROM
        gym_department_features
    WHERE
        gym_department_id = ?
""";


    String GET_DEPARTMENT_AMENITIES_DEPARTMENT_ID = """
    SELECT
        amenitie_id,
        gym_department_id,
        photo_url,
        amenitie_name,
        description
    FROM
        gym_department_amenities
    WHERE
        gym_department_id = ?
""";


String GET_GYM_PLAN_BY_GYM_PLAN_ID = """
                SELECT
                    gp.plan_id,
                    gp.gym_department_id,
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
                    (SELECT name from fitpass.gym_department gd
                                where gp.gym_department_id = gd.gym_department_id) as name_department
                FROM gym_plan gp
                WHERE
                    gp.plan_id = ?
            """;

    String INSERT_ITEM_INVENTORY = """
                INSERT INTO item_inventory 
                    ( 
                    user_id, 
                    plan_id, 
                    plan_active_time, 
                    item_status_key, 
                    plan_expired_time)  
                    VALUES (?, ?, ?, ?, ?)
            """;
}
