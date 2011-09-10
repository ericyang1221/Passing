package test;zxvz

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.passing.hibernate.dao.JPwordDao;

public class JPwordDaoBeanTest {
	
	JPwordDao jpwordDao;

	@Before
	public void setUp() throws Exception {
		ApplicationContext ctx=new FileSystemXmlApplicationContext("WebContent/WEB-INF/config/spring/data-access-config.xml");
		jpwordDao=(JPwordDao)ctx.getBean("jpwordDao");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetWordListByKana() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWorldListByWord() {
		fail("Not yet implemented");
	}

	//TODO
//	@Test
//	public void testGetWorldListByKana_like() {
//		System.out.println(jpwordDao.getWorldListByKana_like("").size());
//		assertNotNull(jpwordDao.getWorldListByKana_like(""));
//		fail("Not yet implemented");
//	}

}
