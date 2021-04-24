package org.gaval.poc.app;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppMapper {
	public List<App> selectApp();
}
