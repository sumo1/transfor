/**
* User: yunshu.xw
* Date: 13-1-8
* Time: 上午10:55
*/
public class NullObjectImpl implements NullObject {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello,"+name);
    }
}
