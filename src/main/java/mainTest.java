/**
 * User: yunshu.xw
 * Date: 13-1-8
 * Time: 上午10:55
 */
public class mainTest {
    public static void main(String argus[]){
        NullObject test = NullObject.NULL;
        NullObject e = NullObjectImpl.NULL;
        NullObject d = new NullObjectImpl();
        test.sayHello("yunshu");
        e.sayHello("yunshu");
        d.sayHello("yunshu");
    }
}
