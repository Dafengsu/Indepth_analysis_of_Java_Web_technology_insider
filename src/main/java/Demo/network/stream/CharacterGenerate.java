package Demo.network.stream;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @description:
 * @author: su
 * @date: 2020/1/24
 */
public class CharacterGenerate {
    public static void main(String[] args) throws IOException {
        generateCharacters(System.out);
    }

    private static void generateCharacters(OutputStream out) throws IOException {
        //第一个打印的字符
        int firsPrintableCharacter = 33;
        //可以打印的字符数
        int numberOfPrintableCharacters = 94;
        //每行的字符数
        int numberOfCharactersPerLine = 72;
        //第一个打印的字符
        int start = firsPrintableCharacter;

        for (int j = 0; j < 100; j++) {
            for (int i = start; i < start + numberOfCharactersPerLine; i++) {
                out.write(((i - firsPrintableCharacter) % numberOfPrintableCharacters) + firsPrintableCharacter);
            }
            out.write('\r');
            out.write('\n');
            start = ((start + 1) - firsPrintableCharacter) % numberOfPrintableCharacters + firsPrintableCharacter;
        }
    }
}
