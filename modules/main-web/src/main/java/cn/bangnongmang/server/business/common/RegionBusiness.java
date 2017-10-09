package cn.bangnongmang.server.business.common;

import java.util.List;

import cn.bangnongmang.data.domain.Region;
import cn.bangnongmang.server.io.BusinessException;


public interface RegionBusiness {

    Region createRegion(Long uid, String name, String province, String city, String county,
                        String town,
                        String village, String detail, String tel, Double longitude,
                        Double latitude) throws BusinessException;

    Region editRegion(Long uid, String idregion, String name, String province, String city,
                      String county,
                      String town, String village, String detail, String tel, Double longitude,
                      Double latitude)
            throws BusinessException;

    boolean deleteRegion(Long uid, String idregion) throws BusinessException;

    List<Region> getAllRegion(Long uid);
}
