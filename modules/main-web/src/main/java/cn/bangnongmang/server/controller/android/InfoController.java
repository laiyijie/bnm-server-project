package cn.bangnongmang.server.controller.android;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import cn.bangnongmang.data.dao.VersionControlMapper;
import cn.bangnongmang.data.domain.VersionControl;
import cn.bangnongmang.data.domain.VersionControlCriteria;
import cn.bangnongmang.driverapp.models.ApkVersion;

@Controller("appInfoController")
@RequestMapping("app/info")
public class InfoController {

    @Autowired
    private VersionControlMapper versionControlMapper;

    public static final Integer FORCE_UPDATE = 100;

    @ResponseBody
    @RequestMapping("checkVersion")
    public ApkVersion checkVersion() {

        ApkVersion apkVersion = new ApkVersion();

        PageHelper.startPage(1, 1);
        VersionControlCriteria versionControlCriteria = new VersionControlCriteria();
        versionControlCriteria.setOrderByClause(" id desc");
        List<VersionControl> versionControls = versionControlMapper.selectByExample(versionControlCriteria);
        if (versionControls.isEmpty()) {
            return null;
        }

        VersionControl versionControl = versionControls.get(0);
        apkVersion.setBugfix_version(versionControl.getBugfix_version());
        apkVersion.setForce_update(versionControl.getForce_update() == FORCE_UPDATE);
        apkVersion.setMain_version(versionControl.getMain_version());
        apkVersion.setSub_version(versionControl.getSub_version());
        apkVersion.setUpdate_items(versionControl.getUpdate_items());
        apkVersion.setUpdate_url(versionControl.getUpdate_url());

        return apkVersion;
    }

    @ResponseBody
    @RequestMapping("/test")
    public String test() {
        throw new NullPointerException();
    }

}
