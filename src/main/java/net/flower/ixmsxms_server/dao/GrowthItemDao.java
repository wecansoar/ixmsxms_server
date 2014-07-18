package net.flower.ixmsxms_server.dao;

import net.flower.ixmsxms_server.domain.GrowthItem;

@Master
public interface GrowthItemDao {
    public int insert(GrowthItem growthItems);
    public int delete(GrowthItem growthItems);
}
