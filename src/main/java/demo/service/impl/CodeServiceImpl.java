package demo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import demo.common.page.Page;
import demo.mapper.CodeDao;
import demo.service.CodeService;


@Service("codeService")
public class CodeServiceImpl implements CodeService {
	@Resource
	private CodeDao codeDao;

	@Override
	public List<Map<String, Object>> getHxCodePageList(Page page) {
		return codeDao.getHxCodePageList(page);
	}

	@Override
	public List<Map<String, Object>> getHxCodeSettingByCodeId(String codeId) {
		return codeDao.getHxCodeSettingByCodeId(codeId);
	}


	@Override
	public List<Map<String, String>> getOrgCombobox() {
		return codeDao.getOrgCombobox();
	}

	@Override
	public List<Map<String, String>> getFittingMenu(String fittingPositionId) {
		return codeDao.getFittingMenu(fittingPositionId);
	}

	@Override
	public List<Map<String, String>> getList(String q) {
		return this.codeDao.getList(q);
	}


}
