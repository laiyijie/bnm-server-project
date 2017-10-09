package cn.bangnongmang.admin.swagger.controller.impl;

import cn.bangnongmang.admin.business.TeamBusiness;
import cn.bangnongmang.admin.business.WorkTeamManageBusiness;
import cn.bangnongmang.admin.io.swagger.show.TeamShow;
import cn.bangnongmang.admin.swagger.api.TeamApi;
import cn.bangnongmang.admin.swagger.controller.base.BaseConf;
import cn.bangnongmang.admin.swagger.model.TeamBasic;
import cn.bangnongmang.admin.swagger.model.TeamDetail;
import cn.bangnongmang.data.domain.Account;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by lucq on 2017/6/23.
 */
@RequestMapping(BaseConf.BASE_URL)
@RestController
public class TeamContoller implements TeamApi {

	@Autowired
	private TeamShow teamShow;
	@Autowired
	private TeamBusiness teamBusiness;


	@Override
	public ResponseEntity<List<TeamBasic>> teamGet(
			@ApiParam(value = "") @RequestParam(value = "orderId", required = true) String orderId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		return new ResponseEntity<List<TeamBasic>>(teamShow.getTeamBasicByOrderId(orderId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> teamTeamIdDelete(
			@ApiParam(value = "",required=true ) @PathVariable("teamId") Long teamId,
			@NotNull @ApiParam(value = "", required = true) @RequestParam(value = "tel", required = true) String tel,
			@NotNull @ApiParam(value = "", required = true) @RequestParam(value = "punishInsurance", required = true) Integer punishInsurance,
			@NotNull @ApiParam(value = "", required = true) @RequestParam(value = "isPunished", required = true) Boolean isPunished,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		teamBusiness.removeTeamMeber(tel,teamId,punishInsurance,isPunished);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TeamDetail> teamTeamIdGet(
			@ApiParam(value = "", required = true) @PathVariable("teamId") Long teamId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		TeamDetail teamDetail = teamShow.getTeamDetailByTeamId(teamId);
		if (teamDetail == null) {
			return new ResponseEntity<TeamDetail>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TeamDetail>(teamDetail, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> teamTeamIdPost(
			@ApiParam(value = "", required = true) @PathVariable("teamId") Long teamId,
			@ApiParam(value = "", required=true) @RequestParam(value="tel", required=true) String tel,
			@ApiParam(value = "", required=true) @RequestParam(value="isPay", required=true) Boolean isPay,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		    teamBusiness.addTeamMember(tel,teamId,isPay);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> teamTeamIdPut(
			@ApiParam(value = "", required = true) @PathVariable("teamId") Long teamId,
			@ApiParam(value = "", required=true) @RequestParam(value="tel", required=true) String tel,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		    teamBusiness.changeLeader(tel,teamId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}


}
