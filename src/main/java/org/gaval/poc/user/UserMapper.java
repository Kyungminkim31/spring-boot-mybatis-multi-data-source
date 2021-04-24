package org.gaval.poc.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	public List<User> selectUsers();
}
