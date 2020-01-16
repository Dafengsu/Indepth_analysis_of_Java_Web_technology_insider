import java.io.File;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author dafengsu
 * @description:
 * @date 2020/1/14 15:27
 */
public class ProFileName {
    public static void main(String[] args) {
        Arrays.stream(Objects.requireNonNull(new File(".").listFiles()))
                .forEach(f -> f.renameTo(new File(f.getName().replaceAll("]", ""))));
    }
}
