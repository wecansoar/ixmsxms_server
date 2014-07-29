package net.flower.ixmsxms_server.service;

import net.flower.ixmsxms_server.dao.GrowthChildMapDao;
import net.flower.ixmsxms_server.dao.GrowthDao;
import net.flower.ixmsxms_server.dao.GrowthItemDao;
import net.flower.ixmsxms_server.domain.Code;
import net.flower.ixmsxms_server.domain.Growth;
import net.flower.ixmsxms_server.domain.GrowthChildMap;
import net.flower.ixmsxms_server.domain.GrowthItem;
import net.flower.ixmsxms_server.utils.PaginateTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class GrowthService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private GrowthDao growthDao;

    @Resource
    private GrowthChildMapDao growthChildMapDao;

    @Resource
    private GrowthItemDao growthItemDao;

    public Growth selectByGrowthId(Long growthId) {
        Growth growth = this.growthDao.select(growthId);
        growth.setGrowthItems(this.growthItemDao.selectList(growthId));
        growth.setGrowthChildMaps(this.growthChildMapDao.selectList(growthId));

        return growth;
    }

    public Map<String, Object> selectListByUserId(Growth growth) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", this.growthDao.selectListByUserId(growth));
        int totalCount = this.growthDao.selectListCountByUserId(growth);
        map.put("totalCount", totalCount);
        map.put("page", PaginateTool.paginate(growth.getPage(), 10, growth.getCount(), totalCount));
        return map;
    }


    public Map<String, Object> insert(Growth growth, List<GrowthChildMap> growthChildMaps, List<GrowthItem> growthItems) {
        Map<String, Object> map = new HashMap<String, Object>();
        this.logger.debug("@@@ growthID LONG");
        map.put("row", this.growthDao.insert(growth));

        Long growthId = growth.getGrowthId();
        this.logger.debug("@@@ growthID = " + growth.getGrowthId());

        if(growthId != 0){
            for( GrowthChildMap growthChildMap : growthChildMaps){
                growthChildMap.setGrowthId(Long.parseLong(growthId.toString()));
                if( !growthChildMap.getCount().equals("")){
                    this.growthChildMapDao.insert(growthChildMap);
                }else{
                    throw new IllegalArgumentException("childId needed");
                }
            }

            for( GrowthItem growthItem : growthItems){
                growthItem.setGrowthId(Long.parseLong(growthId.toString()));
                if( growthItem.getGrowthType().equals(Code.GROWTH_ITEM_TYPE_IMAGE) || growthItem.getGrowthType().equals(Code.GROWTH_ITEM_TYPE_PROGRESS)){
                    this.growthItemDao.insert(growthItem);
                }else{
                    throw new IllegalArgumentException("growth_item_type Illegal");
                }
            }
        }

        return map;
    }

    public Map<String, Object> update(Growth growth) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("row", this.growthDao.update(growth));
        return map;
    }

    public Map<String, Object> delete(Long growthId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("row", this.growthDao.delete(growthId));
        return map;
    }
}