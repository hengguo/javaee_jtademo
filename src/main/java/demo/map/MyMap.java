package demo.map;

public class MyMap<K, V> {
	private int size;// 当前容量
	private static int INIT_CAPACITY = 16;// 默认容量
	private Entry<K, V>[] container;// 实际存储数据的数组对象
	private static float LOAD_FACTOR = 0.75f;// 装载因子
	private int max;// 能存的最大的数=capacity*factor

	// 自己设置容量和装载因子的构造器
	public MyMap(int init_Capaticy, float load_factor) {
		if (init_Capaticy < 0)
			throw new IllegalArgumentException("Illegal initial capacity: "
					+ init_Capaticy);
		if (load_factor <= 0 || Float.isNaN(load_factor))
			throw new IllegalArgumentException("Illegal load factor: "
					+ load_factor);
		this.LOAD_FACTOR = load_factor;
		max = (int) (init_Capaticy * load_factor);
		container = new Entry[init_Capaticy];
	}

	// 使用默认参数的构造器
	public MyMap() {
		this(INIT_CAPACITY, LOAD_FACTOR);
	}

	/**
	 * 存
	 * 
	 * @param k
	 * @param v
	 * @return
	 */
	public boolean put(K k, V v) {
		// 1.计算K的hash值
		// 因为自己很难写出对不同的类型都适用的Hash算法，故调用JDK给出的hashCode()方法来计算hash值
		int hash = k.hashCode();
		// 将所有信息封装为一个Entry
		Entry<K, V> temp = new Entry(k, v, hash);
		if (setEntry(temp, container)) {
			// 大小加一
			size++;
			return true;
		}
		return false;
	}

	/**
	 * 扩容的方法
	 * 
	 * @param newSize
	 *            新的容器大小
	 */
	private void reSize(int newSize) {
		// 1.声明新数组
		Entry<K, V>[] newTable = new Entry[newSize];
		max = (int) (newSize * LOAD_FACTOR);
		// 2.复制已有元素,即遍历所有元素，每个元素再存一遍
		for (int j = 0; j < container.length; j++) {
			Entry<K, V> entry = container[j];
			// 因为每个数组元素其实为链表，所以…………
			while (null != entry) {
				setEntry(entry, newTable);
				entry = entry.next;
			}
		}
		// 3.改变指向
		container = newTable;

	}

	/**
	 * 将指定的结点temp添加到指定的hash表table当中 添加时判断该结点是否已经存在 如果已经存在，返回false 添加成功返回true
	 * 
	 * @param temp
	 * @param table
	 * @return
	 */
	private boolean setEntry(Entry<K, V> temp, Entry[] table) {
		// 根据hash值找到下标
		int index = indexFor(temp.hash, table.length);
		// 根据下标找到对应元素
		Entry<K, V> entry = table[index];
		// 3.若存在
		if (null != entry) {
			// 3.1遍历整个链表，判断是否相等
			while (null != entry) {
				// 判断相等的条件时应该注意，除了比较地址相同外，引用传递的相等用equals()方法比较
				// 相等则不存，返回false
				if ((temp.key == entry.key || temp.key.equals(entry.key))
						&& temp.hash == entry.hash
						&& (temp.value == entry.value || temp.value
								.equals(entry.value))) {
					return false;
				}
				// 不相等则比较下一个元素
				else if (temp.key != entry.key && temp.value != entry.value) {
					// 到达队尾，中断循环
					if (null == entry.next) {
						break;
					}
					// 没有到达队尾，继续遍历下一个元素
					entry = entry.next;
				} 
				/**  heng_aa 写道
						无聊又测了一下，死循环是因为while没有这种情况的判断，我是加上这些代码的： 
						
						恩~~确实需要加上这个判断~ 
						不然当传入相同的key不同的value的时候，不会覆盖掉原理的value 
						这时候linked里面就会有相同的key，不同的value，后面那个value就没意义了。 
						  只是，这样还是没有死循环吖。。囧 
				 */
				else if (temp.key == entry.key && temp.value != entry.value) {
					entry.value = temp.value;
					return true;
				}
			}
			// 3.2当遍历到了队尾，如果都没有相同的元素，则将该元素挂在队尾
			addEntry2Last(entry, temp);
			return true;
		}
		// 4.若不存在,直接设置初始化元素
		setFirstEntry(temp, index, table);
		return true;
	}

	private void addEntry2Last(Entry<K, V> entry, Entry<K, V> temp) {
		if (size > max) {
			reSize(container.length * 4);
		}
		entry.next = temp;

	}

	/**
	 * 将指定结点temp，添加到指定的hash表table的指定下标index中
	 * 
	 * @param temp
	 * @param index
	 * @param table
	 */
	private void setFirstEntry(Entry<K, V> temp, int index, Entry[] table) {
		// 1.判断当前容量是否超标，如果超标，调用扩容方法
		if (size > max) {
			reSize(table.length * 4);
		}
		// 2.不超标，或者扩容以后，设置元素
		table[index] = temp;
		// ！！！！！！！！！！！！！！！
		// 因为每次设置后都是新的链表，需要将其后接的结点都去掉
		// NND，少这一行代码卡了哥哥7个小时（代码重构）
		temp.next = null;
	}

	/**
	 * 取
	 * 
	 * @param k
	 * @return
	 */
	public V get(K k) {
		Entry<K, V> entry = null;
		// 1.计算K的hash值
		int hash = k.hashCode();
		// 2.根据hash值找到下标
		int index = indexFor(hash, container.length);
		// 3。根据index找到链表
		entry = container[index];
		// 3。若链表为空，返回null
		if (null == entry) {
			return null;
		}
		// 4。若不为空，遍历链表，比较k是否相等,如果k相等，则返回该value
		while (null != entry) {
			if (k == entry.key || entry.key.equals(k)) {
				return entry.value;
			}
			entry = entry.next;
		}
		// 如果遍历完了不相等，则返回空
		return null;

	}

	/**
	 * 根据hash码，容器数组的长度,计算该哈希码在容器数组中的下标值
	 * 
	 * @param hashcode
	 * @param containerLength
	 * @return
	 */
	public int indexFor(int hashcode, int containerLength) {
		return hashcode & (containerLength - 1);

	}

	/**
	 * 用来实际保存数据的内部类,因为采用挂链法解决冲突，此内部类设计为链表形式
	 * 
	 * @param <K>key
	 * @param <V>
	 *            value
	 */
	class Entry<K, V> {
		Entry<K, V> next;// 下一个结点
		K key;// key
		V value;// value
		int hash;// 这个key对应的hash码，作为一个成员变量，当下次需要用的时候可以不用重新计算

		// 构造方法
		Entry(K k, V v, int hash) {
			this.key = k;
			this.value = v;
			this.hash = hash;

		}

		// 相应的getter()方法

	}
}
