import com.google.common.base.Splitter;

/**
 * User: yunshu.xw
 */
public class DB2XML {
    static String PRE = "<column ";
    static String NAME = "name=";
    static String VALUE_TYPE = "valueType=";
    static String VARCHAR = "VARCHAR";
    static String NUMBER = "NUMBER";
    static String SYSTIME = "SYSTIME";
    static String INCREASE = "increase=";
    static String VALUE = "value=";
    static String LAST = "/>\n";
    static Boolean IS_LAST = false;
    static Boolean IS_FIRST = false;
    static Boolean COMMENTSFLAG = false;
    static String COMMENT = "";
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
                "  `auditcomments` varchar(1024) DEFAULT NULL COMMENT '审核备注',\n";
        System.out.println(trans2XML(db));
    }
    private static String trans2XML(String db) {
        StringBuilder result = new StringBuilder();
        Iterable<String> rows = Splitter.on("\n").split(db);
        for (String row : rows) {
            if(row.isEmpty()){
                continue;
            }
            Iterable<String> columns = Splitter.on(" ").split(row);
            result.append(PRE);
            COMMENT = "";
            IS_LAST = false;
            IS_FIRST = true;
            COMMENTSFLAG = false;
            for (String column : columns) {
                result.append(trans2Value(column, IS_FIRST));
                if(IS_LAST){
                    break;
                }
            }
            if(COMMENTSFLAG){
                result.append(format(COMMENT.substring(1, COMMENT.length()-2)));
            }
            result.append(LAST);
        }
        return result.toString();
    }

    private static String trans2Value(String column, Boolean isFirst) {
        if(column.isEmpty()){
            return "";
        }
        if(isFirst){
            IS_FIRST = false;
            return NAME + format(column.substring(1, column.length()-1));
        }
        if(column.contains("int")){
            return VALUE_TYPE + format(NUMBER);
        }
        if(column.contains("char")){
            return VALUE_TYPE + format(VARCHAR);
        }
        if(column.contains("date")){
            IS_LAST = true;
            return VALUE_TYPE + format(SYSTIME);
        }
        if(column.contains("INCREMENT")){
            return INCREASE + format("true");
        }
        if(column.contains("COMMENT")){
            COMMENTSFLAG = true;
            return VALUE;
        }
        if(COMMENTSFLAG){
            COMMENT += column;
            return  "";
        }
        return "";
    }

    private static String format(String column) {
        return "\""+column+"\" ";
    }
}
