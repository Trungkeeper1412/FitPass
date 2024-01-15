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
                     da.photo_url
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
                    ud.image_url,
                    uf.feedback_time,
                    uf.feedback_status
                FROM
                    user_feedback uf
                    INNER JOIN `user` u ON uf.user_id = u.user_id
                    INNER JOIN user_detail ud ON u.user_detail_id = ud.user_detail_id
                WHERE
                    uf.department_id = ?
                ORDER BY
                    uf.rating DESC, uf.feedback_time DESC;
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
                            ORDER BY
                            uf.rating DESC, uf.feedback_time DESC;        
            """;

    String GET_DEPARTMENT_FEEDBACK_PAGINATION = """
                SELECT
                    uf.feedback_id,
                    uf.user_id,
                    ud.first_name,
                    ud.last_name,
                    uf.department_id,
                    uf.rating,
                    uf.comments,
                    uf.feedback_time,
                    ud.image_url,
                    uf.feedback_status
                FROM
                    user_feedback uf
                    INNER JOIN `user` u ON uf.user_id = u.user_id
                    INNER JOIN user_detail ud ON u.user_detail_id = ud.user_detail_id
                WHERE
                    uf.department_id = :departmentId
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
                INSERT INTO gym_department (name, brand_id, gym_department_status_key,first_time)
                VALUES (?, ?, ?,?);
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

    String GET_ALL_AMENITIES_OF_DEPARTMENT = """
                SELECT ba.amenitie_id, ba.brand_id, ba.photo_url, ba.amenitie_name, ba.description
                FROM gym_department_amenities gda
                INNER JOIN brand_amenities ba ON gda.amenitie_id = ba.amenitie_id
                WHERE gda.gym_department_id = ?;
            """;

    String GET_ALL_FEATURES_ACTIVE = """
                SELECT feature_id, feature_icon, feature_name, feature_status
                FROM features WHERE feature_status = 1;
            """;

    String GET_ALL_FEATURES = """
                SELECT feature_id, feature_icon, feature_name, feature_status
                FROM features;
            """;

    String UPDATE_GYM_OWNER_DEPARTMENT_INFO = """
                UPDATE gym_department
                SET
                    address = ?,
                    contact_number = ?,
                    description = ?,
                    capacity = ?,
                    area = ?,
                    city = ?
                WHERE gym_department_id = ?;
            """;

    String UPDATE_GYM_OWNER_DEPARTMENT_INFO_DETAIL = """
                UPDATE gym_department
                SET
                    address = ?,
                    contact_number = ?,
                    description = ?,
                    capacity = ?,
                    area = ?,
                    city = ?,
                    latitude = ?,
                    longitude = ?
                WHERE gym_department_id = ?;
            """;

    String ADD_DEPARTMENT_SCHEDULE = """
                INSERT INTO gym_department_schedule (gym_department_id, `day`, open_time, close_time)
                VALUES (?, ?, ?, ?);
            """;

    String INSERT_DEPARTMENT_AMENITY = """
                INSERT INTO gym_department_amenities (gym_department_id, amenitie_id)
                VALUES (?, ?);
            """;

    String INSERT_DEPARTMENT_FEATURE = """
                INSERT INTO gym_department_features
                (feature_id, gym_department_id, feature_status)
                VALUES(?, ?, 1);
            """;

    String DELETE_ALL_DEPARTMENT_AMENITY = """
                DELETE FROM gym_department_amenities
                WHERE gym_department_id = ?;
            """;

    String DELETE_ALL_DEPARTMENT_FEATURES = """
                DELETE FROM gym_department_features
                WHERE gym_department_id = ?;
            """;

    String DELETE_ALL_DEPARTMENT_SCHEDULE = """
                DELETE FROM gym_department_schedule
                WHERE gym_department_id = ?;
            """;

    String UPDATE_DEPARTMENT_IMAGE = """
                UPDATE gym_department
                SET logo_url = ?, thumbnail_url = ?, wallpaper_url = ?
                WHERE gym_department_id = ?;
            """;

    String UPDATE_DEPARTMENT_LONGITUDE_LATITUDE = """
                UPDATE gym_department
                SET longitude = ?, latitude = ?
                WHERE gym_department_id = ?;
            """;

    String DELETE_DEPARTMENT_ALBUMS_BY_ID_DEPARTMENT = """
                DELETE FROM gym_department_albums
                WHERE gym_department_id = ?;
            """;

    String INSERT_DEPARTMENT_ALBUM = """
                INSERT INTO gym_department_albums
                (gym_department_id, photo_url)
                VALUES(?, ?);
            """;

    String CHECK_FIRST_TIME_DEPARTMENT_CREATED = """
                SELECT COUNT(*)
                FROM gym_department
                WHERE gym_department_id = ?
                AND first_time = 1;
            """;

    String UPDATE_FIRST_TIME_DEPARTMENT_CREATED = """
                UPDATE gym_department
                SET first_time = 0
                WHERE gym_department_id = ?;
            """;

             String  GET_FEATURE_BY_FEATURE_ID = """
                SELECT feature_id, feature_icon, feature_name, feature_status
                FROM features WHERE feature_id = ?;
            """;

    String INSERT_FEATURE = """
                INSERT INTO features
                (feature_name, feature_icon, feature_status)
                VALUES(?, ?, ?);
            """;

    String UPDATE_FEATURE = """
                UPDATE features
                SET feature_name = ?, feature_icon = ?
                WHERE feature_id = ?;
            """;

    String UPDATE_FEATURE_STATUS = """
                UPDATE features
                SET feature_status = ?
                WHERE feature_id = ?;
            """;


    String COUNT_ALL_FEEDBACK = """
                SELECT COUNT(*)
                FROM user_feedback
                WHERE department_id = :departmentId
            """;
    String GET_ALL_DEPARTMENT_NAME_AND_LOGO_BY_ID = """
                SELECT
                    d.name,
                    d.logo_url
                FROM
                    gym_department d
                WHERE
                    d.gym_department_id = ?;
            """;

    String COUNT_ALL_DEPARTMENT = """
                SELECT COUNT(*)
                FROM gym_department
            """;

    String GET_DEPARTMENT_STAT_BRAND_OWNER = """
                SELECT
                    gd.name AS departmentName,
                    COUNT(od.order_detail_id) AS numberOfGymPlanSold,
                    SUM(od.price) AS totalAmount
                FROM
                    gym_department gd
                LEFT JOIN
                    order_plan_detail od ON gd.gym_department_id = od.gym_department_id
                LEFT JOIN
                    brand b ON gd.brand_id = b.brand_id
                WHERE
                    b.brand_id = ?
                GROUP BY
                    gd.name;
            """;

    String GET_DEPARTMENT_RATING_STAT_BRAND_OWNER = """
                SELECT
                    gd.name AS departmentName,
                    COUNT(uf.feedback_id) AS numberOfRating,
                    AVG(gd.rating) AS rating
                FROM
                    user_feedback uf
                JOIN
                    gym_department gd ON uf.department_id = gd.gym_department_id
                JOIN
                    brand b ON gd.brand_id = b.brand_id
                WHERE
                    b.brand_id = ?
                GROUP BY
                    gd.name;
            """;

    String GET_TOTAL_NUMBER_RATING_BY_DEPARTMENT_ID = """
                SELECT COUNT(uf.feedback_id) AS numberOfRating
                FROM user_feedback uf
                WHERE uf.department_id = ?;
            """;

    String GET_ALL_DEPOSIT_DENOMINATION = """
            SELECT deposit_denomination_id,credit,money,deposit_denomination_status 
            FROM deposit_denomination
            """;

    String GET_DEPOSIT_DENOMINATION_BY_ID = """
        SELECT deposit_denomination_id, credit, money, deposit_denomination_status 
        FROM deposit_denomination
        WHERE deposit_denomination_id = ?
        """;

    String UPDATE_DEPOSIT_DENOMINATION = """
        UPDATE deposit_denomination
        SET credit = ?, money = ?
        WHERE deposit_denomination_id = ?
        """;

    String UPDATE_DEPOSIT_DENOMINATION_STATUS = """
        UPDATE deposit_denomination
        SET deposit_denomination_status = ?
        WHERE deposit_denomination_id = ?
        """;

    String INSERT_DEPOSIT_DENOMINATION = """
        INSERT INTO deposit_denomination (credit, money, deposit_denomination_status)
        VALUES (?, ?, 1)
        """;


}
