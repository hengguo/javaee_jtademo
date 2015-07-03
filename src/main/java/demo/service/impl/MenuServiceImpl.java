package demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import demo.mapper.MenuDao;
import demo.service.MenuService;

import javax.annotation.Resource;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Resource
	private MenuDao menuDao;
	
	@Override
	public List list(Map<String, Object> map) {
		return menuDao.list(map);
	}

}
