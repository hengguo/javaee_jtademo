package demo.service;

import java.util.List;
import java.util.Map;

import demo.common.page.Page;

public interface CodeService {
	List<Map<String, Object>> getHxCodePageList(Page page);
	
	List<Map<String, Object>> getHxCodeSettingByCodeId(String codeId);
	
	List<Map<String, String>> getOrgCombobox();

	List<Map<String, String>> getFittingMenu(String fittingPositionId);

	List<Map<String, String>> getList(String q);
}
