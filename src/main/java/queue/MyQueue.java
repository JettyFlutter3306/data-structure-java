package queue;

/**
 * 队列接口,描述队列抽象数据类型
 *
 * @param <T> 数据元素的数据类型
 */
public interface MyQueue<T> {

    boolean isEmpty(); //判断队列是否为空

    boolean add(T x);  //元素x入队,若添加成功,则返回true,否则返回false

    T peek(); //返回对头元素,没有删除,若队列为空,则返回null

    T poll(); //出队,返回队头元素,若队列为空,则返回null




}
