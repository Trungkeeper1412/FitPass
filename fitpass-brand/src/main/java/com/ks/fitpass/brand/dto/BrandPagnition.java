package com.ks.fitpass.brand.dto;

import com.ks.fitpass.brand.entity.Brand;
import com.ks.fitpass.department.dto.DepartmentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandPagnition {
    private List<Brand> listBrand;
    private int totalPage;
    private Map<Integer, List<DepartmentDTO>> brandDepartmentsMap;
}
