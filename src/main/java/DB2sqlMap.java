import com.google.common.base.Splitter;

/**
 * User: yunshu.xw
 */
public class DB2sqlMap {
    public static void main(String[] args) {
        String db = "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',\n" +
                "  `logdate` datetime DEFAULT NULL COMMENT '审核时间',\n" +
                "  `qualityid` bigint(20) DEFAULT NULL COMMENT '资质id',\n" +
                "  `qualityname` varchar(64) DEFAULT NULL COMMENT '资质名称',\n" +
                "  `memberid` bigint(20) DEFAULT NULL COMMENT '用户id',\n" +
                "  `nickname` varchar(64) DEFAULT NULL COMMENT '用户昵称',\n" +
                "  `status` tinyint(4) DEFAULT NULL COMMENT '审核状态（1.待审核， 2.待确认，3.通过， 4.拒绝 ）',\n" +
                "  `qualitytag` varchar(64) DEFAULT NULL COMMENT '资质标签名称',\n" +
                "  `begintime` date DEFAULT NULL COMMENT '资质有效开始时间',\n" +
                "  `endtime` date DEFAULT NULL COMMENT '资质有效结束时间',\n" +
                "  `auditorid` bigint(20) DEFAULT NULL COMMENT '审核人id',\n" +
                "  `auditorname` varchar(64) DEFAULT NULL COMMENT '审核人旺旺昵称',\n" +
                "  `auditcomments` varchar(1024) DEFAULT NULL COMMENT '审核备注',";
        System.out.println(trans2sqlMap(db));
    }

    private static String trans2sqlMap(String db) {
        StringBuilder result = new StringBuilder();
        Iterable<String> rows = Splitter.on("\n").split(db);
        for (String row : rows) {
            if(row.isEmpty()){
                continue;
            }
            result.append(trans2value(row));
        }
        return  result.substring(0, result.length()-2);
    }

    private static String trans2value(String row) {
        Iterable<String> columns = Splitter.on(" ").split(row);
        String columnResult = "";
        for (String column : columns) {
            if(column.isEmpty()){
                continue;
            }
            columnResult = column.substring(1, column.length()-1);
            break;
        }
        return columnResult + "     " + columnResult + ",\n";
    }
}
