package org.codeart.datastructure.algorithm.prefixTree;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀树的实现
 */
public class TrieTree {
    
    public static class Node1 {
        public int pass;
        public int end;
        public Node1[] next;

        /**
         * 创建长度为26的数组存储小写字母字符串
         * char tmp = 'a'  (tmp - 'a')
         * 0    a
         * 1    b
         * 2    c
         * ..   ..
         * 25   z
         * next[i] == null  i方向的路不存在
         * next[i] != null  i方向的路存在
         */
        public Node1() {
            next = new Node1[26];
        }
    }
    
    public static class  Trie1 {
        private final Node1 root;
        
        public Trie1() {
            root = new Node1();
        }
        
        public void insert(String word) {
            if (word == null) {
                return;
            }
            
            char[] chars = word.toCharArray();
            Node1 node = root;
            node.pass++;
            int path = 0;

            // 从左往右遍历字符串
            for (char c : chars) {
                path = c - 'a';  // 由字符，对应成走向哪条路
                if (node.next[path] == null) {
                    node.next[path] = new Node1();
                }
                node = node.next[path];
                node.pass++;
            }
            
            node.end++;
        }

        /**
         * 从前缀树中删除字符串
         */
        public void delete(String word) {
            if (search(word) != 0) {
                char[] chars = word.toCharArray();
                Node1 node = root;
                node.pass--;
                int path = 0;

                for (char c : chars) {
                    path = c - 'a';
                    if (--node.next[path].pass == 0) {
                        node.next[path] = null;
                        return;
                    }
                    node = node.next[path];
                }
                
                node.end--;
            }
        }

        /**
         * 查找单词word出现的次数
         */
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            
            char[] chars = word.toCharArray();
            Node1 node = root;
            int path = 0;

            for (char c : chars) {
                path = c - 'a';
                if (node.next[path] == null) {
                    return 0;
                }
                node = node.next[path];
            }
            
            return node.end;
        }

        /**
         * 在前缀树中找出以当前字符串pre为前缀的字符串数量
         */
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }

            char[] chars = pre.toCharArray();
            Node1 node = root;
            int path = 0;

            for (char c : chars) {
                path = c - 'a';
                if (node.next[path] == null) {
                    return 0;
                }
                node = node.next[path];
            }
            
            return node.pass;
        }
    }

    public static class Node2 {
        public int pass;
        public int end;
        public Map<Integer, Node2> nexts;

        public Node2() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    public static class Trie2 {
        private final Node2 root;

        public Trie2() {
            root = new Node2();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chs = word.toCharArray();
            Node2 node = root;
            node.pass++;
            int index;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    node.nexts.put(index, new Node2());
                }
                node = node.nexts.get(index);
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                char[] chs = word.toCharArray();
                Node2 node = root;
                node.pass--;
                int index;
                for (int i = 0; i < chs.length; i++) {
                    index = chs[i];
                    if (--node.nexts.get(index).pass == 0) {
                        node.nexts.remove(index);
                        return;
                    }
                    node = node.nexts.get(index);
                }
                node.end--;
            }
        }

        // word这个单词之前加入过几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chs = word.toCharArray();
            Node2 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.end;
        }

        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            Node2 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.pass;
        }
    }

    public static class Right {
        private final Map<String, Integer> box;

        public Right() {
            box = new HashMap<>();
        }

        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            if (!box.containsKey(word)) {
                return 0;
            } else {
                return box.get(word);
            }
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)) {
                    count += box.get(cur);
                }
            }
            return count;
        }
    }


    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            Trie1 trie1 = new Trie1();
            Trie2 trie2 = new Trie2();
            Right right = new Right();
            for (String s : arr) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(s);
                    trie2.insert(s);
                    right.insert(s);
                } else if (decide < 0.5) {
                    trie1.delete(s);
                    trie2.delete(s);
                    right.delete(s);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(s);
                    int ans2 = trie2.search(s);
                    int ans3 = right.search(s);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(s);
                    int ans2 = trie2.prefixNumber(s);
                    int ans3 = right.prefixNumber(s);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");

    }
}
