package net.flower.ixmsxms_server.service;

import net.flower.ixmsxms_server.domain.Code;
import net.flower.ixmsxms_server.domain.Growth;
import net.flower.ixmsxms_server.domain.GrowthChildMap;
import net.flower.ixmsxms_server.domain.GrowthItem;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hyeyoungkang on 2014. 7. 15..
 */
public class TestGrowthServcie extends TestCore {


    @Resource
    private GrowthService growthService;

    @Test
    public void insert(){
        Growth growth = new Growth();
        growth.setGrowDate("20140102");
        growth.setStature(Float.parseFloat("100"));
        growth.setWeight(Float.parseFloat("10.5"));
        growth.setNote("오늘은 잘   먹는다.ㅡ");

        List<GrowthChildMap> growthChildMapList = new ArrayList<GrowthChildMap>();

        GrowthChildMap growthChildMap = new GrowthChildMap();
        growthChildMap.setChildId(Long.parseLong("1"));

        growthChildMapList.add(growthChildMap);

        List<GrowthItem> growthItemList = new ArrayList<GrowthItem>();

        GrowthItem growthItem = new GrowthItem();
        growthItem.setGrowthType(Code.GROWTH_ITEM_TYPE_IMAGE);
        growthItem.setImageUrl("http://i2.media.daumcdn.net/svc/image/U03/news/201407/18/yonhap/20140718212408213.jpg");
        growthItemList.add(growthItem);


        Map<String, Object> map =  this.growthService.insert(growth, growthChildMapList, growthItemList);
        System.out.println(map);
        Assert.assertNotNull(map);
    }
}
