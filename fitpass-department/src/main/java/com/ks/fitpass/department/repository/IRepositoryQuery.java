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
                d.thumbnail_url,
                d.description,
                d.latitude,
                d.longitude,
                d.rating,
                d.capacity,
                d.area,
                d.city,
                                     (
                                                            SELECT COUNT(uf.feedback_id)
                                                            FROM user_feedback uf
                                                            WHERE uf.department_id = d.gym_department_id
                                                            ) AS feedback_count,
                d.gym_department_status_key,
                kbn_department_status.mst_kbn_value AS gym_department_status_name,
                COALESCE((SELECT MAX(gym_plan.price) AS max_price
                            FROM gym_plan
                            JOIN gym_department_plans ON gym_plan.plan_id = gym_department_plans.plan_id
                            WHERE gym_department_plans.gym_department_id = d.gym_department_id), 0) AS max_price,
                COALESCE((SELECT MIN(gym_plan.price) AS max_price
                        FROM gym_plan
                        JOIN gym_department_plans ON gym_plan.plan_id = gym_department_plans.plan_id
                        WHERE gym_department_plans.gym_department_id = d.gym_department_id), 0) AS min_price,
                (
                    6371 * acos(
                        cos(radians(?)) * cos(radians(d.latitude)) *
                        cos(radians(d.longitude) - radians(?)) +
                        sin(radians(?)) * sin(radians(d.latitude))
                    )
                ) AS distance
            FROM gym_department d
            LEFT JOIN mst_kbn kbn_department_status
                ON d.gym_department_status_key = kbn_department_status.mst_kbn_key
                AND kbn_department_status.mst_kbn_name = 'DEPARTMENT_STATUS'
            WHERE d.gym_department_status_key = ?
                        """;
//    String GET_ALL_DEPARTMENT_BY_STATUS ="""
//            SELECT * FROM (SELECT d.gym_department_id, d.brand_id, d.name, d.address, d.contact_number, d.logo_url, d.wallpaper_url, d.thumbnail_url, d.description, d.latitude, d.longitude, d.rating, d.capacity, d.area, d.city, d.gym_department_status_key, kbn_department_status.mst_kbn_value AS gym_department_status_name,
//            COALESCE((SELECT MAX(gp.price) FROM gym_plan gp WHERE gp.brand_id = d.brand_id), 0) AS max_price,
//            COALESCE((SELECT MIN(gp.price) FROM gym_plan gp WHERE gp.brand_id = d.brand_id), 0) AS min_price,
//            (6371 * acos(cos(radians(:userLatitude)) * cos(radians(d.latitude)) * cos(radians(d.longitude) - radians(:userLongitude)) + sin(radians(:userLatitude)) * sin(radians(d.latitude)))) AS distance
//            FROM gym_department d
//            LEFT JOIN mst_kbn kbn_department_status ON d.gym_department_status_key = kbn_department_status.mst_kbn_key
//            AND kbn_department_status.mst_kbn_name = 'DEPARTMENT_STATUS'
//            WHERE d.gym_department_status_key = :status
//            """;

    String GET_ALL_DEPARTMENT_ORDER_BY_RATING ="""
                 SELECT
                     d.gym_department_id,
                     d.brand_id,
                     d.name,
                     d.address,
                     d.contact_number,
                     d.logo_url,
                     d.wallpaper_url,
                     d.thumbnail_url,
                     d.description,
                     d.latitude,
                     d.longitude,
                     d.rating,

                     d.capacity,
                     d.area,
                     d.city,
                                          (
                                                            SELECT COUNT(uf.feedback_id)
                                                            FROM user_feedback uf
                                                            WHERE uf.department_id = d.gym_department_id
                                                            ) AS feedback_count,
                     d.gym_department_status_key,
                     kbn_department_status.mst_kbn_value AS gym_department_status_name,
                     COALESCE((SELECT MAX(gp.price) FROM gym_plan gp WHERE gp.gym_department_id = d.gym_department_id), 0) AS max_price,
                     COALESCE((SELECT MIN(gp.price) FROM gym_plan gp WHERE gp.gym_department_id = d.gym_department_id), 0) AS min_price
                 FROM gym_department d
                 LEFT JOIN mst_kbn kbn_department_status
                     ON d.gym_department_status_key = kbn_department_status.mst_kbn_key
                     AND kbn_department_status.mst_kbn_name = 'DEPARTMENT_STATUS'
                 WHERE d.gym_department_status_key = ?
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
                     d.thumbnail_url,
                     d.description,
                     d.latitude,
                     d.longitude,
                     d.rating,

                     d.capacity,
                     d.area,
                     d.city,
                     d.gym_department_status_key,
                     (
                                                            SELECT COUNT(uf.feedback_id)
                                                            FROM user_feedback uf
                                                            WHERE uf.department_id = d.gym_department_id
                                                            ) AS feedback_count,
                                                            
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
                                                            d.thumbnail_url,
                                                            d.description,
                                                            d.latitude,
                                                            d.longitude,
                                                            d.rating,
                               
                                                            d.capacity,
                                                            d.area,
                                                            d.city,
                                                            d.gym_department_status_key,
                                                            
                                                            (
                                                            SELECT COUNT(uf.feedback_id)
                                                            FROM user_feedback uf
                                                            WHERE uf.department_id = d.gym_department_id
                                                            ) AS feedback_count,
                                                            
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
                                          d.thumbnail_url,
                                          d.description,
                                          d.latitude,
                                          d.longitude,
                                          d.rating,
                                          d.capacity,
                                          d.area,
                                          d.city,
                                                               (
                                                            SELECT COUNT(uf.feedback_id)
                                                            FROM user_feedback uf
                                                            WHERE uf.department_id = d.gym_department_id
                                                            ) AS feedback_count,
                                     d.gym_department_status_key,
                                     kbn_department_status.mst_kbn_value AS gym_department_status_name,
                                     concat(ud.first_name, " ", ud.last_name) as user_name
                                     FROM gym_department d
                                     LEFT JOIN mst_kbn kbn_department_status
                                     ON d.gym_department_status_key = kbn_department_status.mst_kbn_key
                                     AND kbn_department_status.mst_kbn_name = 'DEPARTMENT_STATUS'
                                     LEFT JOIN user u ON d.user_id = u.user_id
                                     LEFT join user_detail ud on ud.user_detail_id  = u.user_detail_id
                                     WHERE d.gym_department_id = ?
                """;

    String GET_DEPARTMENT_BY_USER = """
                 SELECT
                     d.gym_department_id,
                     d.brand_id,
                     d.name,
                     d.user_id,
                     d.address,
                     d.contact_number,
                     d.logo_url,
                     d.wallpaper_url,
                     d.thumbnail_url,
                     d.description,
                     d.latitude,
                     d.longitude,
                     d.rating,
                     d.capacity,
                     d.area,
                     d.city,
                                          (
                                                            SELECT COUNT(uf.feedback_id)
                                                            FROM user_feedback uf
                                                            WHERE uf.department_id = d.gym_department_id
                                                            ) AS feedback_count,
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
            SELECT gp.*,gdp.gym_department_id, mkv.mst_kbn_value AS gym_plan_type
            FROM gym_plan gp
            JOIN mst_kbn mkv ON gp.gym_plan_type_key = mkv.mst_kbn_key
            JOIN gym_department_plans gdp ON gp.plan_id = gdp.plan_id
            WHERE gdp.gym_department_id = ?
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

    String GET_DEPARTMENT_FEEDBACK_OF_BRAND_OWNER = """
            SELECT
                                uf.feedback_id,
                                uf.user_id,
                                ud.first_name,
                                ud.last_name,
                                uf.department_id,
                                uf.rating,
                                uf.comments,
                                uf.feedback_time,
                                uf.feedback_status,
                                ud.email,
                                ud.phone_number,
                                gp.name
                            FROM
                                user_feedback uf
                                INNER JOIN `user` u ON uf.user_id = u.user_id
                                INNER JOIN user_detail ud ON u.user_detail_id = ud.user_detail_id
                                INNER JOIN gym_plan gp ON gp.plan_id = uf.gym_plan_id
                            WHERE
                                uf.department_id = ?
            """;

    String GET_DEPARTMENT_FEEDBACK_PAGNITION = """
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
                LIMIT ? OFFSET ?
            """;

    String GET_DEPARTMENT_FEATURES = """
            SELECT
              df.gym_department_feature_id,
              f.feature_id,
              f.feature_icon,
              f.feature_name,
              df.gym_department_id,
              df.feature_status
            FROM gym_department_features df
            INNER JOIN features f ON df.feature_id = f.feature_id
            WHERE df.gym_department_id = ? AND f.feature_status = 1 AND df.feature_status = 1
            """;

    String GET_DEPARTMENT_FEATURES_BY_STATUS_AND_DEPARTMENT_ID = """
                SELECT
                f.feature_icon, f.feature_name, gdf.feature_status
                FROM
                    gym_department_features gdf
                    JOIN features f ON f.feature_id = gdf.feature_id
                WHERE
                gdf.gym_department_id = ? AND gdf.feature_status = ?
            """;


    String GET_DEPARTMENT_AMENITIES_DEPARTMENT_ID = """
                                    SELECT\s
                                        gda.gym_department_id,
                        				ba.amenitie_id,
                                        ba.brand_id,
                                        ba.photo_url,
                                        ba.amenitie_name,
                                        ba.description,
                                        ba.amenitie_status
                        		FROM brand_amenities ba
                        INNER JOIN gym_department_amenities gda ON ba.amenitie_id = gda.amenitie_id \s
                        WHERE gda.gym_department_id = ? AND ba.amenitie_status = 1
            """;

//    String GET_DEPARTMENT_AMENITIES_BRAND_ID = """
//                                    SELECT\s
//                                        gda.gym_department_id,
//                        				ba.amenitie_id,
//                                        ba.brand_id,
//                                        ba.photo_url,
//                                        ba.amenitie_name,
//                                        ba.description,
//                                        ba.amenitie_status
//                        		FROM brand_amenities ba
//                        INNER JOIN gym_department_amenities gda ON ba.amenitie_id = gda.amenitie_id \s
//                        WHERE ba.brand_id = ? AND ba.amenitie_status = 1
//            """;

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

    String GET_ALL_DEPARTMENT_LIST_OF_BRAND = """
                SELECT
                    d.gym_department_id,
                    d.name,
                    d.contact_number,
                    d.logo_url,
                    d.wallpaper_url,
                    d.thumbnail_url,
                    d.description,
                    d.latitude,
                    d.longitude,
                    d.rating,
                    d.capacity,
                    d.area,
                    d.city,
                                         (
                                                            SELECT COUNT(uf.feedback_id)
                                                            FROM user_feedback uf
                                                            WHERE uf.department_id = d.gym_department_id
                                                            ) AS feedback_count,
                    d.gym_department_status_key,
                    kbn_department_status.mst_kbn_value AS gym_department_status_name,
                    CONCAT(ud.first_name, ' ', ud.last_name) as user_name
                FROM gym_department d
                LEFT JOIN mst_kbn kbn_department_status
                    ON d.gym_department_status_key = kbn_department_status.mst_kbn_key
                    AND kbn_department_status.mst_kbn_name = 'DEPARTMENT_STATUS'
                LEFT JOIN `user` u ON u.user_id = d.user_id
                LEFT JOIN user_detail ud ON u.user_detail_id = ud.user_detail_id
                WHERE d.brand_id = ?
                ORDER BY d.rating DESC
            """;

    String UPDATE_DEPARTMENT_STATUS = """
                UPDATE gym_department
                SET gym_department_status_key = ?
                WHERE gym_department_id = ?;
            """;

    String CREATE_DEPARTMENT_WITH_BRAND_ID = """
                INSERT INTO gym_department (name, brand_id, gym_department_status_key)
                VALUES (?, ?, ?);
            """;

    String UPDATE_DEPARTMENT_GYM_OWNER = """
                UPDATE gym_department
                SET user_id=?
                WHERE gym_department_id=?;
            """;

    String GET_ALL_DEPARTMENT_FEEDBACK_OF_BRAND_OWNER = """
                SELECT
                    d.gym_department_id,
                    d.brand_id,
                    d.name,
                    d.wallpaper_url,
                    d.rating,
                    (
                        SELECT COUNT(uf.feedback_id)
                        FROM user_feedback uf
                        WHERE uf.department_id = d.gym_department_id
                    ) AS feedback_count
                FROM gym_department d
                LEFT JOIN mst_kbn kbn_department_status
                    ON d.gym_department_status_key = kbn_department_status.mst_kbn_key
                    AND kbn_department_status.mst_kbn_name = 'DEPARTMENT_STATUS'
                WHERE d.gym_department_status_key = ? AND d.brand_id = ?
                ORDER BY d.rating DESC;
            """;

    String GET_ALL_AMENITIE_OF_DEPARTMENT = """
                SELECT ba.amenitie_id, ba.brand_id, ba.photo_url, ba.amenitie_name, ba.description
                FROM gym_department_amenities gda
                INNER JOIN brand_amenities ba ON gda.amenitie_id = ba.amenitie_id
                WHERE gda.gym_department_id = ?;
            """;
}
