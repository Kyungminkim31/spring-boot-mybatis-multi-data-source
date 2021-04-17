package org.gaval.poc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class MultiDatasourcePocApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiDatasourcePocApplication.class, args);
	}

}

@RestController
@RequestMapping("/api/v1/memo")
class MemoController{
	
	Logger logger = LoggerFactory.getLogger(MemoController.class);
	
	@GetMapping
	public ResponseEntity<String> getMemo(@RequestParam String param1){
		logger.info(param1);
		return ResponseEntity.ok("{Message:\"Hello, World!\"}");
	}
}
