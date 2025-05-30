package com.ks.fitpass.core.repository;


import com.ks.fitpass.core.entity.Kbn;

import java.util.List;

public interface KbnRepository {

    List<Kbn> getKbnByName(String kbnName);

    String getGymPlanTypeByPlanTypeKey(int gymPlanKey);
}
