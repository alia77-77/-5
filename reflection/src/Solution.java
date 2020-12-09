public class Solution {
    public static void main(String[] args) {
        SomeBean bean = (new Injector()).inject(new SomeBean());
        bean.start();
    }
}
