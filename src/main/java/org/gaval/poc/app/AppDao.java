package org.gaval.poc.app;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AppDao {
	
	private final SqlSession sqlSession;
	
	private AppDao(@Qualifier("mysqlSessionTemplate") SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<Object> selectApps(){
		return sqlSession.selectList("selectApp");
	}

}
