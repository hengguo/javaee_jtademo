import java.util.HashSet;

public class HashSetAndHashCode2 {
	public static void main(String[] args) {
		HashSet<Point2> hs2 = new HashSet<Point2>();
		Point2 p21 = new Point2(3, 3);
		Point2 p22 = new Point2(3, 5);
		hs2.add(p21);
		hs2.add(p22);
		p22.setY(7);
		hs2.remove(p22);
		System.out.println(hs2.size());
	}
}