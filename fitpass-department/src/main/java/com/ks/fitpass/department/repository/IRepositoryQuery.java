package com.ks.fitpass.department.repository;

public interface IRepositoryQuery {

    String GET_ALL_DEPARTMENT_BY_STATUS = """
                 SELECT
                     d.gym_department_id,
                     d.user_id,
                     d.name,
                     d.address,
                     d.contact_number,
                     d.logo_url,
                     d.opening_hours,
                     d.closing_hours,
                     d.image_url,
                     d.description,                     
                     d.gym_department_status_key,
                     kbn_department_status.mst_kbn_value AS gym_department_status_name
                 FROM department d
                 LEFT JOIN mst_kbn kbn_department_status
                     ON d.gym_department_status_key = kbn_department_status.mst_kbn_key
                     AND kbn_department_status.mst_kbn_name = 'DEPARTMENT_STATUS'  
                     WHERE d.gym_department_status_key = ?
            """;

    String GET_DEPARTMENT_BY_ID = """
            SELECT INTO 
                d.gym_department_id,
                d.user_id,
                d.name,
                d.address,
                d.contact_number,
                d.logo_url,
                d.opening_hours,
                d.closing_hours,
                d.image_url,
                d.description,                     
                d.gym_department_status_key,
                kbn_department_status.mst_kbn_value AS gym_department_status_name
                FROM gym_department d
                LEFT JOIN mst_kbn kbn_department_status
                ON d.gym_department_status_key = kbn_department_status.mst_kbn_key
                AND kbn_department_status.mst_kbn_name = 'DEPARTMENT_STATUS'
                WHERE d.gym_department_id =?
                """;
    String GET_DEPARTMENT_BY_USER = """
                 SELECT INTO 
                     d.gym_department_id,
                     d.user_id,
                     d.name,
                     d.address,
                     d.contact_number,
                     d.logo_url,
                     d.opening_hours,
                     d.closing_hours,
                     d.image_url,
                     d.description,                     
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
                        opening_hours = ?,
                        closing_hours = ?,
                        image_url = ?,
                        description = ?,
                        gym_department_status_key = ?
                WHERE gym_department_id = ?
            """;
}
