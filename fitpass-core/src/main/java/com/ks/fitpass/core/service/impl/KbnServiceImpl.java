package com.ks.fitpass.core.service.impl;

import com.ks.fitpass.core.entity.Kbn;
import com.ks.fitpass.core.repository.KbnRepository;
import com.ks.fitpass.core.service.KbnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KbnServiceImpl implements KbnService {

    private KbnRepository kbnRepository;

    @Autowired
    public KbnServiceImpl(KbnRepository kbnRepository) {
        this.kbnRepository = kbnRepository;
    }

    @Override
    public List<Kbn> getKbnByName(String kbnName) {
        return kbnRepository.getKbnByName(kbnName);
    }

    @Override
    public String getGymPlanTypeByPlanKey(int gymPlanKey) {
        return kbnRepository.getGymPlanTypeByPlanKey(gymPlanKey);
    }
}
