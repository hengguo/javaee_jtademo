package demo.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import demo.domain.Menu;

@Repository("menuDao")
public interface MenuDao {
	/**
     * 
     * @param map
     * @return
     * @throws BaseException
     */
    public List<Menu> list(Map<String,Object> map);
}
