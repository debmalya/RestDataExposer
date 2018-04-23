package com.ebak.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EbakRestController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/tables/{tableName}", produces = "application/json;charset=UTF-8")
	public List<Map<String, Object>> displayAll(@PathVariable String tableName) {
		try {
			return jdbcTemplate.queryForList("select * from " + tableName);
		} catch (Throwable sqle) {
			List<Map<String,Object>> errorList = new ArrayList<Map<String,Object>>();
			Map<String,Object> errorMap = new HashMap<>();
			errorMap.put("Error Message",sqle.getMessage());
			errorList.add(errorMap);
			return errorList;
		}
	}

}
