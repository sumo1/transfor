import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: yunshu.xw
 * Date: 13-3-5
 */
@Component("ibatisBigData")
public class IbatisBigData extends SqlMapClientDaoSupport implements BigData{
    public List<BigDataDO> findBigData(){
        return (List<BigDataDO>) getSqlMapClientTemplate().queryForList("getBigDatas");
    }
}
