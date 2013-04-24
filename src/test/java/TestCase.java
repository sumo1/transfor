import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * User: yunshu.xw
 * Date: 13-3-5
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring-test.xml",
        "classpath:spring-dataSource-test.xml"
})
@Transactional
public class TestCase extends AbstractTransactionalDataSourceSpringContextTests {
    @Autowired
    @Qualifier("dataSource")
    private DriverManagerDataSource dataSource;

    @Resource
    private BigData ibatisBigData;

    @Test
    public void testBigData(){
        List<BigDataDO> bigDatas = new ArrayList<BigDataDO>();
        bigDatas = ibatisBigData.findBigData();
        System.out.println(bigDatas.size());
    }
}
