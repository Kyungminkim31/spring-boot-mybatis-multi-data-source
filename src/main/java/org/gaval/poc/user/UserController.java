package org.gaval.poc.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/v1/user")
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping(path="/")
	public ResponseEntity<List<User>> getUserList() {
		return ResponseEntity.ok(userMapper.selectUsers());
	}
	

}
