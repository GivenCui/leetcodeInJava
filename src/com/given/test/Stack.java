/**
 * @author : givencui
 * @date: 2021/4/16 - 04 - 16 - 3:05 下午
 * @Description: 参考  https://kaiwu.lagou.com/minor_course/index.html?courseId=11#/detail?barrierId=113&courseId=11
 * @version: 1.0
 */
public class Stack<E> {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        System.out.println(s.isEmpty());
        s.push(1);
        s.push(2);
        s.push(3);
        System.out.println(s.peek()); // 3
        System.out.println(s.pop()); // 3
        System.out.println(s.isEmpty()); // false
        System.out.println(s.size()); // 2

        System.out.println(s.pop()); // 2
        System.out.println(s.pop()); // 1
        System.out.println(s.pop()); // null
        System.out.println(s.peek()); // null
        System.out.println(s.size()); // 0
    }

    Object[] element = new Object[10000];
    int index = -1; // 栈顶索引
    int size = 0;

    public Stack() {
    }

    public void push(E e) {
        element[++index] = e;
        size++;
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E)element[index];
    }

    public E pop() {
        E e = peek();
        if (!isEmpty()) {
            element[index] = null;  // 移除引用
            index--;
            size--;
        }
        return e;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
}
