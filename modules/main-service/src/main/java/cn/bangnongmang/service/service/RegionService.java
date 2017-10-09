package cn.bangnongmang.service.service;

import java.util.List;

import cn.bangnongmang.data.domain.Region;
import cn.bangnongmang.data.domain.RegionGeo;

public interface RegionService {
    Integer MAX_NUM = 30;
    Integer LEVEL_DEFAULT = 10;
    Integer LEVEL_COMMON = 1;

    Region saveRegion(Long uid, String province, String city, String county, String
            town, String village,
                      String detail, String name, String tel, int level);

    RegionGeo saveRegionGeo(String idRegion, Double longitude, Double latitude);

    RegionGeo editRegionGeo(String idRegion, Double longitude, Double latitude);

    boolean deleteRegionGeo(String idRegion);

    long getRegionNumberByUid(Long uid);

    Long getRegionUidByIdRegion(String idregion);

    Region editRegionByRegion(Long uid, String idregion, String province, String city,
                              String county,
                              String town, String village, String detail, String name, String tel);

    List<Region> getRegionsByUid(Long uid);

    boolean deleteRegionByIdRegion(String idregion);

    RegionGeo getRegionGeo(String regionId);

    Region getRegionById(String id);
}
