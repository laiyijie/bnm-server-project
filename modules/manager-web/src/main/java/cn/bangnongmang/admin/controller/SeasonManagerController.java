package cn.bangnongmang.admin.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.bangnongmang.service.service.GrabSeasonService;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.admin.util.PageResult;
import cn.bangnongmang.data.dao.GrabSeasonMapper;
import cn.bangnongmang.data.dao.SeasonOrdersMapper;
import cn.bangnongmang.data.domain.GrabSeason;
import cn.bangnongmang.data.domain.GrabSeasonCriteria;
import cn.bangnongmang.data.domain.SeasonOrdersCriteria;

@Controller
@RequestMapping("/season")
public class SeasonManagerController {

	@Autowired
	private GrabSeasonService grabSeasonService;
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SeasonOrdersMapper seasonOrdersMapper;

	@Autowired
	private GrabSeasonMapper grabSeasonMapper;

	@ResponseBody
	@RequestMapping("/test")
	public String test() {

		return "message";
	}

	/**
	 * 
	 * 获取所有的场次，依照id倒叙
	 * 
	 * @Title getAllSeasons
	 * 
	 * @param currentPage
	 *            当前的页数 默认 1
	 * @param pageSize
	 *            每页显示的属相 默认 20
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllSeasons")
	public PageResult<GrabSeason> getAllSeasons(
			@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
		GrabSeasonCriteria grabSeasonCriteria = new GrabSeasonCriteria();
		grabSeasonCriteria.or();
		grabSeasonCriteria.setOrderByClause(" id desc ");

		PageHelper.startPage(currentPage, pageSize);
		return new PageResult<>((Page<GrabSeason>) grabSeasonMapper.selectByExample(grabSeasonCriteria));
	}

	@ResponseBody
	@RequestMapping("/getSeason")
	public GrabSeason getSeason(@RequestParam("id") String id) {

		GrabSeason grabSeason = grabSeasonService.getSeasonById(id);

		return grabSeason;
	}

	/**
	 * 
	 * 传回关联订单的 id
	 * 
	 * @Title getSeasonDetail
	 * 
	 * @param seasonId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSeasonDetail")
	public List<String> getSeasonDetail(@RequestParam("seasonId") String seasonId) {
		SeasonOrdersCriteria seasonOrdersCriteria = new SeasonOrdersCriteria();
		seasonOrdersCriteria.or().andSeason_idEqualTo(seasonId);

		return seasonOrdersMapper.selectByExample(seasonOrdersCriteria).stream().map(a -> a.getOrder_id())
				.collect(Collectors.toList());
	}

	@ResponseBody
	@RequestMapping("/createSeasons")
	public GrabSeason createSeasons(@RequestParam("start_time") long startTime,
			@RequestParam("end_time") long endTime, @RequestParam("ps") String ps) throws ParseException {
		
		GrabSeason grabSeason = grabSeasonService.createSeason(startTime, endTime, ps);

		return grabSeason;
	}

	@ResponseBody
	@RequestMapping("/editSeasons")
	public boolean editSeasons(@RequestParam("start_time") long startTime, @RequestParam("end_time") long endTime,
			@RequestParam("ps") String ps, @RequestParam("id") String id) throws ParseException, BusinessException {

		boolean grabSeason = grabSeasonService.editSeason(id, startTime, endTime, ps);

		if (grabSeason) {
			return grabSeason;
		} else {
			throw new BusinessException();
		}

	}

	@ResponseBody
	@RequestMapping("/editSesonOrders")
	public String editSeasonOrders(@RequestParam("orders") String orderString,
			@RequestParam("seasonId") String seasonId)
			throws JsonParseException, JsonMappingException, IOException, BusinessException {

		List<String> fatherOrderIds = JSON.parseArray(orderString, String.class);

		boolean flag = grabSeasonService.editSeasonOrders(seasonId, fatherOrderIds);

		if (flag) {

			return "good";
		} else {

			throw new BusinessException();
		}

	}

	@ResponseBody
	@RequestMapping("/addOrdersToSeason")
	public String addOrdersToSeason(@RequestParam("orders") String orderString,
			@RequestParam("seasonId") String seasonId)
			throws JsonParseException, JsonMappingException, IOException, BusinessException {

		List<String> fatherOrderIds = objectMapper.readValue(orderString, List.class);

		boolean flag = grabSeasonService.addOrdersToSeason(seasonId, fatherOrderIds);

		if (flag) {

			return "good";
		} else {

			throw new BusinessException();
		}

	}

	@ResponseBody
	@RequestMapping("/publishSeason")
	public String publishSeason(@RequestParam("seasonId") String id) throws BusinessException {

		boolean flag = grabSeasonService.publishSeason(id);

		if (flag) {
			return "done";
		} else {
			throw new BusinessException();
		}

	}

	@ResponseBody
	@RequestMapping("/cancelPublish")
	public String cancelPublish(@RequestParam("seasonId") String id) throws BusinessException {
		boolean flag = grabSeasonService.cancelSeason(id);

		if (flag) {
			return "done";
		} else {
			throw new BusinessException();
		}
	}

	@ResponseBody
	@RequestMapping("/deleteOrdersFromSeason")
	public String deleteOrders(@RequestParam("orders") String orderString, @RequestParam("seasonId") String seasonId)
			throws JsonParseException, JsonMappingException, IOException, BusinessException {
		List<String> fatherOrderIds = objectMapper.readValue(orderString, List.class);

		boolean flag = grabSeasonService.deleteOrderFromSeason(seasonId, fatherOrderIds);

		if (flag) {

			return "good";
		} else {

			throw new BusinessException();
		}

	}

	@ResponseBody
	@RequestMapping("/deleteSeason")
	public String deleteSeason(@RequestParam("seasonId") String id) throws BusinessException {
		boolean flag = grabSeasonService.deleteSeason(id);

		if (flag) {
			return "done";
		} else {
			throw new BusinessException();
		}
	}

}
