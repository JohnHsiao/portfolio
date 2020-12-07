package dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @author JOHN
 */
public class JdbcDAO extends JdbcDaoSupport {

	/**
	 * @return 標準SQL查詢結果
	 * @param sql 一句標準SQL
	 * @return 結果為ListOfMap
	 */
	public List StandardSqlQuery(String sql) {
		return getJdbcTemplate().queryForList(sql);		
	}
	
	/**
	 * 標準SQL執行
	 * @param sql 一句標準SQL
	 */
	public void exQuery(String sql){		
		getJdbcTemplate().execute(sql);
	}
	
	/**
	 * 標準語法查詢1個數值
	 * @param sql 一句標準SQL
	 * @return 1個數值
	 */
	public int sqlGetInt(String sql){		
		return getJdbcTemplate().queryForInt(sql);
	}	
	
	/**
	 * 標準語法查詢1列資料
	 * @param sql
	 * @return 1筆map型態的資料
	 */
	public Map sqlGetMap(String sql){
		try{			
			return getJdbcTemplate().queryForMap(sql);
		}catch(Exception e){
			return null;
		}
		
	}
	
	/**
	 * 標準語法查詢1個字串
	 * @param sql
	 * @return java.lang.String
	 */
	public String sqlGetString(String sql){
		try{
			return (String)getJdbcTemplate().queryForObject(sql, java.lang.String.class);
		}catch(org.springframework.dao.IncorrectResultSizeDataAccessException e){
			return "";
		}
	}
	
	
}
