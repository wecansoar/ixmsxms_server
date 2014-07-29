package net.flower.ixmsxms_server.dao;

import net.flower.ixmsxms_server.domain.GrowthItem;

import java.util.List;

@Master
public interface GrowthItemDao {
    public List<GrowthItem> selectList(Long growthId);
    public int insert(GrowthItem growthItems);
    public int delete(GrowthItem growthItems);
}
