package com.ks.fitpass.brand.repository;

public interface IRepositoryQuery {
    String GET_ALL_BRAND_BY_STATUS = """
                 SELECT
                     b.brand_id,
                     b.user_id,
                     b.name,
                     b.logo_url,
                     b.wallpaper_url,
                     b.thumbnail_url,
                     b.description,
                     b.rating,
                     b.contact_number,
                     b.contact_email,
                     b.brand_status_key,
                     kbn_brand_status.mst_kbn_value AS brand_status_name,
                     (SELECT COUNT(*)
                      FROM order_plan_detail opd
                      JOIN gym_department gd ON opd.gym_department_id = gd.gym_department_id
                      WHERE gd.brand_id = b.brand_id) AS total_order_details
                 FROM brand b
                 LEFT JOIN mst_kbn kbn_brand_status
                     ON b.brand_status_key = kbn_brand_status.mst_kbn_key
                     AND kbn_brand_status.mst_kbn_name = 'BRAND_STATUS'
                 WHERE b.brand_status_key = ?
            """;

    String COUNT_ALL_BRAND_BY_STATUS = """
                SELECT
                     COUNT(*)
                 FROM brand b
                 LEFT JOIN mst_kbn kbn_brand_status
                     ON b.brand_status_key = kbn_brand_status.mst_kbn_key
                     AND kbn_brand_status.mst_kbn_name = 'BRAND_STATUS'
                 WHERE b.brand_status_key = ?
            """;

    String GET_ALL_BRAND_ORDER_BY_RATING = """
                 SELECT
                     b.brand_id,
                     b.user_id,
                     b.name,
                     b.logo_url,
                     b.wallpaper_url,
                     b.thumbnail_url,
                     b.description,
                     b.rating,
                     b.contact_number,
                     b.contact_email,
                     b.brand_status_key,
                     kbn_brand_status.mst_kbn_value AS brand_status_name                     
                 FROM brand b
                 LEFT JOIN mst_kbn kbn_brand_status
                     ON b.brand_status_key = kbn_brand_status.mst_kbn_key
                     AND kbn_brand_status.mst_kbn_name = 'BRAND_STATUS'  
                     WHERE b.brand_status_key = ?
                     ORDER BY d.rating DESC
            """;
    String GET_BRAND_BY_ID = """
                 SELECT
                     b.brand_id,
                     b.user_id,
                     b.name,
                     b.logo_url,
                     b.wallpaper_url,
                     b.thumbnail_url,
                     b.description,
                     b.rating,
                     b.contact_number,
                     b.contact_email,

                     b.brand_status_key,
                     kbn_brand_status.mst_kbn_value AS brand_status_name                     
                 FROM brand b
                 LEFT JOIN mst_kbn kbn_brand_status
                     ON b.brand_status_key = kbn_brand_status.mst_kbn_key
                     AND kbn_brand_status.mst_kbn_name = 'BRAND_STATUS'  
                     WHERE b.brand_id = ?
            """;

    String GET_BRAND_AMENITIES_BRAND_ID = """
                SELECT
                    amenitie_id,
                    brand_id,
                    photo_url,
                    amenitie_name,
                    description,
                    amenitie_status
                FROM
                    brand_amenities
                WHERE
                    brand_id = ?
            """;
    String GET_BRAND_AMENITIES_BRAND_ID_ACTIVATE = """
                SELECT
                    amenitie_id,
                    brand_id,
                    photo_url,
                    amenitie_name,
                    description,
                    amenitie_status
                FROM
                    brand_amenities
                WHERE
                    brand_id = ? AND amenitie_status = 1
            """;

    String GET_BRAND_DETAIL_BY_USER_ID = """
                SELECT
                                  brand_id,
                                  user_id,
                                  name,
                                  logo_url,
                                  wallpaper_url,
                                  thumbnail_url,
                                  description,
                                  rating,
                                  contact_number,
                                  contact_email,
                                  brand_status_key,
                                  kbn_brand_status.mst_kbn_value AS brand_status_name
                                FROM
                                  brand b
                                  LEFT JOIN mst_kbn kbn_brand_status
                                     ON b.brand_status_key = kbn_brand_status.mst_kbn_key
                                     AND kbn_brand_status.mst_kbn_name = 'BRAND_STATUS'
                                WHERE
                                  user_id = ?;
            """;

    String UPDATE_BRAND_DETAIL_BY_BRAND_ID = """
                UPDATE brand
                SET
                  name = ?,
                  logo_url = ?,
                  wallpaper_url = ?,
                  thumbnail_url = ?,
                  description = ?,
                  contact_number = ?,
                  contact_email = ?,
                  brand_status_key = ?
                WHERE
                  brand_id = ?;
            """;

    String GET_BRAND_AMENITIES_DETAIL = """
                SELECT amenitie_id, brand_id, photo_url, amenitie_name, description, amenitie_status
                FROM brand_amenities
                WHERE amenitie_id = ?;
            """;

    String CREATE_BRAND_AMENITIES = """
                INSERT INTO brand_amenities
                (brand_id, photo_url, amenitie_name, description, amenitie_status)
                VALUES(?,?, ?, ?, ?);
            """;

    String UPDATE_BRAND_AMENITIES = """
                UPDATE brand_amenities
                SET photo_url=?, amenitie_name=?, description=?, amenitie_status=?
                WHERE amenitie_id=?;
            """;

    String UPDATE_BRAND_AMENITIES_STATUS = """
                UPDATE brand_amenities
                SET amenitie_status=?
                WHERE amenitie_id=?;
            """;

    String GET_ALL_FEEDBACK_OF_BRAND = """
                SELECT
                    uf.feedback_id,
                    uf.user_id,
                    ud.first_name,
                    ud.last_name,
                    ud.image_url,
                    uf.rating,
                    uf.comments,
                    uf.feedback_time
                FROM
                    user_feedback uf
                JOIN
                    user u ON uf.user_id = u.user_id
                JOIN
                    user_detail ud ON u.user_detail_id = ud.user_detail_id
                JOIN
                    gym_department gd ON uf.department_id = gd.gym_department_id
                WHERE
                    gd.brand_id = :brandId
            """;

    String GET_FEEDBACK_OF_BRAND_STAT = """
                SELECT
                    gd.brand_id,
                    COUNT(uf.feedback_id) AS totalFeedback,
                    COUNT(CASE WHEN uf.rating = 5 THEN 1 END) AS fiveStarFeedback,
                    COUNT(CASE WHEN uf.rating >= 4 AND uf.rating < 5 THEN 1 END) AS fourStarFeedback,
                    COUNT(CASE WHEN uf.rating >= 3 AND uf.rating < 4 THEN 1 END) AS threeStarFeedback,
                    COUNT(CASE WHEN uf.rating >= 2 AND uf.rating < 3 THEN 1 END) AS twoStarFeedback,
                    COUNT(CASE WHEN uf.rating >= 1 AND uf.rating < 2 THEN 1 END) AS oneStarFeedback
                FROM
                    user_feedback uf
                JOIN
                    gym_department gd ON uf.department_id = gd.gym_department_id
                WHERE
                    gd.brand_id  = ?;
            """;

    String COUNT_TOTAL_FEEDBACK_OF_BRAND = """
                SELECT
                    COUNT(uf.feedback_id) AS totalFeedback
                FROM
                    user_feedback uf
                JOIN
                    gym_department gd ON uf.department_id = gd.gym_department_id
                WHERE
                    gd.brand_id = :brandId
            """;

    String GET_BRAND_OWNER_ID_BY_DEPARTMENT_ID = """
                SELECT bd.`user_id`
                FROM `gym_department` gd
                JOIN `brand` bd ON gd.`brand_id` = bd.`brand_id`
                WHERE gd.`gym_department_id` = ?;
            """;
}
