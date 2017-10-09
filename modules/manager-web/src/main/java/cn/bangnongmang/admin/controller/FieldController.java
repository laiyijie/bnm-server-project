package cn.bangnongmang.admin.controller;

import java.util.List;


import cn.bangnongmang.service.outerservice.SizeCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import cn.bangnongmang.admin.controller.api.FieldsApi;
import cn.bangnongmang.admin.util.BusinessException;
import cn.bangnongmang.size.io.swagger.ApiClient;
import cn.bangnongmang.size.io.swagger.api.DefaultApi;
import cn.bangnongmang.size.io.swagger.model.Error;
import cn.bangnongmang.size.io.swagger.model.Field;
import cn.bangnongmang.size.io.swagger.model.Polygon;
import cn.bangnongmang.size.io.swagger.model.UpdateField;
import retrofit2.Response;

@RestController
public class FieldController implements FieldsApi {

	private DefaultApi defaultApi = null;
	@Autowired
	public FieldController(SizeCounterService service){
		defaultApi = service.getDefaultApi();
	}

	public ResponseEntity<Void> fieldsIdDelete(@PathVariable("id") Integer id) throws Exception {

		Response<Void> response = defaultApi.fieldsIdDelete(id).execute();

		if (response.isSuccessful()) {
			return new ResponseEntity<>(HttpStatus.valueOf(response.code()));
		} else {
			Error error = JSON.parseObject(response.errorBody().string(), Error.class);
			throw new BusinessException(error.getErrorCode(), error.getErrorMessage());
		}
	}

	public ResponseEntity<Field> fieldsIdGet(@PathVariable("id") Integer id) throws Exception {

		Response<Field> response = defaultApi.fieldsIdGet(id).execute();

		if (response.isSuccessful()) {
			return new ResponseEntity<>(response.body(), HttpStatus.valueOf(response.code()));
		} else {
			Error error = JSON.parseObject(response.errorBody().string(), Error.class);
			throw new BusinessException(error.getErrorCode(), error.getErrorMessage());
		}

	}

	public ResponseEntity<Void> fieldsIdPut(@PathVariable("id") Integer id, @RequestBody Field field) throws Exception {

		Response<Void> response = defaultApi.fieldsIdPut(id, field).execute();

		if (response.isSuccessful()) {
			return new ResponseEntity<>(response.body(), HttpStatus.valueOf(response.code()));
		} else {
			Error error = JSON.parseObject(response.errorBody().string(), Error.class);
			throw new BusinessException(error.getErrorCode(), error.getErrorMessage());
		}
	}

	public ResponseEntity<Void> fieldsPost(@RequestBody UpdateField data) throws Exception {
		Response response = defaultApi.fieldsPost(data).execute();

		if (response.isSuccessful()) {
			return new ResponseEntity<>( HttpStatus.valueOf(response.code()));
		} else {
			Error error = JSON.parseObject(response.errorBody().string(), Error.class);
			throw new BusinessException(error.getErrorCode(), error.getErrorMessage());
		}
	}

	public ResponseEntity<List<Field>> fieldsViewPost(@RequestBody Polygon viewPolygon) throws Exception {
		Response<List<Field>> response = defaultApi.fieldsViewPost(viewPolygon).execute();
		if (response.isSuccessful()) {
			return new ResponseEntity<>(response.body(), HttpStatus.valueOf(response.code()));
		} else {
			Error error = JSON.parseObject(response.errorBody().string(), Error.class);
			throw new BusinessException(error.getErrorCode(), error.getErrorMessage());
		}
	}

}
