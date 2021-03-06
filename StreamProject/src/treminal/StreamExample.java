package treminal;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamExample {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("Lamda Expressions", "Java8 supports lamda expressions",
				"This is a java book");

		list.stream().flatMap(new Function<String, Stream<String>>() {
			@Override
			public Stream<String> apply(String t) {
				return Stream.of(t.toUpperCase());
			}
		}).filter(new Predicate<String>() {

			@Override
			public boolean test(String t) {
				return t.indexOf("JAVA") >= 0;
			}
		}).forEach(new Consumer<String>() {

			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		});

//		list.stream().filter(new Predicate<String>() {
//
//			@Override
//			public boolean test(String t) {
////				System.out.println(t);
//				return t.indexOf("ava") > 0;
//			}
//		}).forEach(s -> System.out.println(s));
	}
}