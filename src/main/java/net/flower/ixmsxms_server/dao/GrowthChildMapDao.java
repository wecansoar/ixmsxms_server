package net.flower.ixmsxms_server.dao;

import net.flower.ixmsxms_server.domain.Growth;
import net.flower.ixmsxms_server.domain.GrowthChildMap;

import java.util.List;

@Master
public interface GrowthChildMapDao {
    public int insert(GrowthChildMap growthChildMap);
    public int delete(GrowthChildMap growthChildMap);
}
