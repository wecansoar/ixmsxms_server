package net.flower.ixmsxms_server.dao;

import net.flower.ixmsxms_server.domain.Growth;
import net.flower.ixmsxms_server.domain.GrowthChildMap;

import java.util.List;

@Master
public interface GrowthChildMapDao {
    public List<GrowthChildMap> selectList(Long growthId);
    public int insert(GrowthChildMap growthChildMap);
    public int delete(GrowthChildMap growthChildMap);
}
