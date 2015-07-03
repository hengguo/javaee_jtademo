package demo.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import demo.common.page.Page;
import demo.domain.Code;

@Repository("codeDao")
public interface CodeDao {
	List<Code> getAll();
	
	List<Code> getCodeByKey(String codeKey);
	
	List<Map<String, Object>> getHxCodePageList(Page page);
	
	List<Map<String, Object>> getHxCodeSettingByCodeId(String codeId);
	
	void insertSetting(Code hxCode);

	List<Map<String, String>> getOrgCombobox();

	List<Map<String, String>> getFittingMenu(String fittingPositionId);

	List<Map<String, String>> getList(String q);
}
