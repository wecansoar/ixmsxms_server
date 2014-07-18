package net.flower.ixmsxms_server.dao;

import net.flower.ixmsxms_server.domain.Growth;

import java.util.List;

@Master
public interface GrowthDao {
    public List<Growth> selectListByUserId(Growth growth);
    public Growth select(Long growthId);
    public int selectListCountByUserId(Growth growth);
    public int insert(Growth growth);
    public int update(Growth growth);
    public int delete(Long growthId);
}
