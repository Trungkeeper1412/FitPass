package com.ks.fitpass.brand.repository;

public interface IRepositoryQuery {
    String GET_ALL_BRAND_BY_STATUS = """
                 SELECT
                     b.brand_id,
                     b.user_id,
                     b.name,
                     b.logo_url,
                     b.wallpaper_url,
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
            """;

    String GET_ALL_BRAND_ORDER_BY_RATING = """
                 SELECT
                     b.brand_id,
                     b.user_id,
                     b.name,
                     b.logo_url,
                     b.wallpaper_url,
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
    String GET_BRAND_ALBUMS_BY_ID_BRAND = """
            SELECT  
                     da.id,
                     da.brand_id,
                     da.photo_url,
                     da.description
                FROM brand_albums da
                    WHERE da.brand_id =?
                """;

    String GET_BRAND_AMENITIES_BRAND_ID = """
    SELECT
        amenitie_id,
        brand_id,
        photo_url,
        amenitie_name,
        description
    FROM
        brand_amenities
    WHERE
        brand_id = ?
""";

}