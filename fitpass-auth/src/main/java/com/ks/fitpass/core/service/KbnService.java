package com.ks.fitpass.core.service;

import com.ks.fitpass.core.entity.Kbn;

import java.util.List;

public interface KbnService {

    List<Kbn> getKbnByName(String kbnName);

    String getGymPlanTypeByPlanKey(int gymPlanKey);

}
