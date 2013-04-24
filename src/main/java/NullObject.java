/**
 * User: yunshu.xw
 * Date: 13-1-8
 * Time: 上午10:50
 */
public interface NullObject {
    public void sayHello(String name);
    public static NullObject NULL = new NullObject() {
        @Override
        public void sayHello(String name) {
            System.out.println("null say hello");
        }
    };
}
