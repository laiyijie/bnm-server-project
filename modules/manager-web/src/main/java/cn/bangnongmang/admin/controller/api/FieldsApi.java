package cn.bangnongmang.admin.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.bangnongmang.size.io.swagger.model.Field;
import cn.bangnongmang.size.io.swagger.model.Polygon;
import cn.bangnongmang.size.io.swagger.model.UpdateField;

import java.util.List;

public interface FieldsApi {

	@RequestMapping(value = "/fields/{id}/fieldsIdDelete", produces = { "application/json" })
	ResponseEntity<Void> fieldsIdDelete(@PathVariable("id") Integer id) throws Exception;

	@RequestMapping(value = "/fields/{id}/fieldsIdGet", produces = { "application/json" })
	ResponseEntity<Field> fieldsIdGet(@PathVariable("id") Integer id) throws Exception;

	@RequestMapping(value = "/fields/{id}/fieldsIdPut", produces = { "application/json" })
	ResponseEntity<Void> fieldsIdPut(@PathVariable("id") Integer id, @RequestBody Field field) throws Exception;

	@RequestMapping(value = "/fields/fieldsPost", produces = { "application/json" })
	ResponseEntity<Void> fieldsPost(@RequestBody UpdateField data) throws Exception;

	@RequestMapping(value = "/fields/view/fieldsViewPost", produces = { "application/json" })
	ResponseEntity<List<Field>> fieldsViewPost(@RequestBody Polygon viewPolygon) throws Exception;

}
