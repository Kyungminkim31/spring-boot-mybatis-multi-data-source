package org.gaval.poc.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1/app")
public class AppController {

	@Autowired
	private AppMapper appMapper;
	
	@Autowired
	private AppDao appDao;
	
	@GetMapping(path="/")
	public ResponseEntity<List<Object>> getApps() {
		return ResponseEntity.ok(appDao.selectApps());
	}
}
