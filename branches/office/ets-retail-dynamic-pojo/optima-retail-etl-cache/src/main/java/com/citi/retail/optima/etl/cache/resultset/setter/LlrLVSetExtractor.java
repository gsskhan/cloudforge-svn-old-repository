/**
 * 
 */
package com.citi.retail.optima.etl.cache.resultset.setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.citi.retail.optima.etl.common.model.cache.LlrLVDomain;

/**
 * @author TJ
 * 
 */
public class LlrLVSetExtractor implements ResultSetExtractor<List<LlrLVDomain>> {

	@Override
	public List<LlrLVDomain> extractData(ResultSet rs) throws SQLException,
			DataAccessException {

		List<LlrLVDomain> resultList = new ArrayList<LlrLVDomain>();
		LlrLVDomain llrLV;
		while (rs.next()) {
			llrLV = new LlrLVDomain();
			llrLV.setLvId(rs.getLong("lv_id"));
			llrLV.setSrcLvName(rs.getString("SRC_DATA_VAL"));
			llrLV.setTrgtLvName(rs.getString("TRGT_ATTR_VAL"));
			llrLV.setSysProcId(rs.getLong("SYS_PROC_ID"));
			resultList.add(llrLV);
		}
		return resultList;
	}
}
