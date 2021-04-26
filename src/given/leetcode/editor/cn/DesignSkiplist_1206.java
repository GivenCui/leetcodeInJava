package given.leetcode.editor.cn;
//不使用任何库函数，设计一个跳表。 
//
// 跳表是在 O(log(n)) 时间内完成增加、删除、搜索操作的数据结构。跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思想
//与链表相似。 
//
// 例如，一个跳表包含 [30, 40, 50, 60, 70, 90]，然后增加 80、45 到跳表中，以下图的方式操作： 
//
// 
//Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons 
//
// 跳表中有很多层，每一层是一个短的链表。在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。跳表的每一个操作的平均时间复杂度是 O(log(
//n))，空间复杂度是 O(n)。 
//
// 在本题中，你的设计应该要包含这些函数： 
//
// 
// bool search(int target) : 返回target是否存在于跳表中。 
// void add(int num): 插入一个元素到跳表。 
// bool erase(int num): 在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num ，删除其中任意一个即可。 
//
// 
//
// 了解更多 : https://en.wikipedia.org/wiki/Skip_list 
//
// 注意，跳表中可能存在多个相同的值，你的代码需要处理这种情况。 
//
// 样例: 
//
// Skiplist skiplist = new Skiplist();
//
//skiplist.add(1);
//skiplist.add(2);
//skiplist.add(3);
//skiplist.search(0);   // 返回 false
//skiplist.add(4);
//skiplist.search(1);   // 返回 true
//skiplist.erase(0);    // 返回 false，0 不在跳表中
//skiplist.erase(1);    // 返回 true
//skiplist.search(1);   // 返回 false，1 已被擦除
// 
//
// 约束条件: 
//
// 
// 0 <= num, target <= 20000 
// 最多调用 50000 次 search, add, 以及 erase操作。 
// 
// Related Topics 设计 
// 👍 63 👎 0


import java.util.Random;

// 1206 设计跳表
public class DesignSkiplist_1206 {
    public static void main(String[] args) {
        // 测试
        Skiplist skipList = new DesignSkiplist_1206().new Skiplist();

        // case01
        // ["Skiplist","add","add","add","search","add","search","erase","erase","search"]
        // [[],[1],[2],[3],[0],[4],[1],[0],[1],[1]]
//        skipList.add(1);
//        skipList.add(2);
//        skipList.add(3);
//        System.out.println(skipList.search(0));
//        skipList.add(4);
//        System.out.println(skipList.search(1));
//        skipList.print();
//        skipList.erase(0);
//        skipList.erase(1);
//        skipList.search(1);

        // case02
        // ["Skiplist","add","add","add","add","add","add","add","add","add","erase","search","add","erase","erase","erase","add","search","search","search"]
        // [  [],      [16],  [5],  [14], [13], [0],  [3],  [12], [9], [12],  [3],    [6],     [7],  [0],     [1],   [10],   [5],   [12],    [7],     [16]  ]
        skipList.add(5);
        skipList.add(16);
        skipList.add(14);
        skipList.add(13);
        skipList.add(0);
        skipList.add(3);
        skipList.add(12);
        skipList.add(9);
        skipList.add(12);
        skipList.print();
        skipList.erase(3);
        System.out.println("--- 删除 3 ---");
        skipList.print();
        skipList.add(7);
        System.out.println("--- 新增 7 ---");
        skipList.print();
        skipList.erase(0);
        skipList.erase(1);
        skipList.erase(10);
        System.out.println("--- 删除 0 1 10 ---");
        skipList.print();
        skipList.add(5);
        System.out.println("--- 新增 5 ---");
        skipList.print();
        System.out.println("search(16): " + skipList.search(16));
        System.out.println("search(5): " + skipList.search(5));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Skiplist {
        final int HEAD_VALUE = -1;
        final Node HEAD = new Node(HEAD_VALUE); // 原链表的头
        Node head; // 整个跳表的头
        int levels; // 层级

        public Skiplist() {
            head = HEAD;
            levels = 1;
        }

        // 跳表的链接节点类
        class Node {
            int val;
            Node right, down;
            int count = 0; // 用于区别重复值的情况, print 会用到

            public Node(int val) {
                this(val, null, null);
            }

            public Node(int val, Node right, Node down) {
                this.val = val;
                this.right = right;
                this.down = down;
            }
        }

        // 搜索
        public boolean search(int target) {
            Node p = head;
            while (p != null) {
                // 1. 同一层向右查找, 直到 p.right 是 null 或 >=
                while (p.right != null && p.right.val < target) {
                    p = p.right;
                }
                // 2. 若等于, 返回 true
                if (p.right != null && p.right.val == target) return true;

                // 3. p.right 是 null 或 >
                p = p.down;
            }

            return false;
        }

        // 删除
        public boolean erase(int num) {
            Node p = head;
            boolean exist = false;
            while (p != null) {
                // 同层链表遍历
                while (p.right != null && p.right.val < num) {
                    p = p.right;
                }
                // 找到后同层断开
                // 目标: p --> p.right --> xx
                // p --> xx
                // p.right -x-> xxx // GC优化
                Node next = p.right; // 待删结点
                if (next != null && next.val == num) {
                    p.right = next.right;
                    next.right = null; // GC优化
                    exist = true;
                }

                // 向下, 重复删除的过程
                p = p.down;
            }

            return exist;
        }

        // 插入节点
        public void add(int num) {
            // 记录向下时经过的节点, 是后续生成每层索引的前驱节点
            Node[] records = new Node[levels]; // 思考: 为啥是层数?
            int i = 0;
            // 1. 定位插入位置 ( 插入要在最底层, 即原链表插入 )
            Node p = head;
            while (p != null) {
                while (p.right != null && p.right.val < num) {
                    p = p.right;
                }

                // p.right 为 null, >= 的情况, 向下
                records[i++] = p; // 最后一次后, i会越界, i == levels
                p = p.down;

            }
            // 2. 插入新节点 (  p 是 null )
            // records 中是待后插的前驱节点
            p = records[--i]; // 数组中的最后一个元素
            Node newNode = new Node(num, p.right, null);

            // 测试用 start
            if (p.right != null && p.right.val == num) {
                newNode.count++;
            }
            // 测试用 end

//      newNode.right = p.right; // 放到了构造函数初始化时
            p.right = newNode;

            // 3. 扔硬币决定是否生成索引
//            addIndexByCoinFlip(newNode, records, i);  // 我的方法
            addIndicesByCoinFlip(newNode, records, i); // 视频方法
        }

        public void addIndicesByCoinFlip(Node target, Node[] records, int i) {
            Node downNode = target;
            Random random = new Random();
            int coin = random.nextInt(2);
            // 1.抛硬币, 在现有层创建索引
            while (coin == 1 && i > 0) {
                Node preNode = records[--i];
                Node newNode = new Node(target.val, preNode.right, downNode);

                // 测试用 start
                newNode.count = target.count;
                // 测试用 end

                preNode.right = newNode;
                downNode = newNode;
                coin = random.nextInt(2);
            }

            // 2. 抛硬币, 决定是否建立超出当前层的索引层 ( 连续是1 )
            if (coin == 1) {
                Node newNode = new Node(target.val, null, downNode);

                // 测试用 start
                newNode.count = target.count;
                // 测试用 end

                Node newHead = new Node(HEAD_VALUE, newNode, head);
                head = newHead;
                levels++;
            }
        }

        public void addIndexByCoinFlip(Node target, Node[] records, int i) {
            Node downNode = target;
            Random random = new Random();
            int coin = random.nextInt(2);
            // 1.抛硬币, 在现有层创建索引
            while (coin == 1 && i > 0) {
                Node preNode = records[--i];
                Node newNode = new Node(target.val, preNode.right, downNode);

                // 测试用 start
                newNode.count = target.count;
                // 测试用 end

                preNode.right = newNode;
                downNode = newNode;
                coin = random.nextInt(2);
            }

            // 2. 抛硬币, 决定是否建立超出当前层的索引层 ( 连续是1 )
            if (coin == 1) {
                Node newNode = null;
                // 当前插入元素是否是第一列 ?
                if (target.val != head.right.val) {
                    newNode = new Node(target.val, null, downNode);
                }
                Node preNode = new Node(head.right.val, newNode, head.right);
                Node newHead = new Node(HEAD_VALUE, preNode, head);
                head = newHead;
                levels++;
            }
        }

        // 遍历跳表
        public void print() {
            String s = "";
            Node first = head;
            while (first != null) {
                Node p = first;
                while (p != null) {
                    s += p.val + (p.count == 0 ? "" : "("+ p.count +")") + " -> ";
                    p = p.right;
                }
                s += "null \n";

                first = first.down;
            }

            System.out.println(s);
        }
    }

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */
//leetcode submit region end(Prohibit modification and deletion)

}