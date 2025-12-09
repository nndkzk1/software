import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class thirdexperiment {
    // 定义状态机的状态枚举
    private enum State {
        INITIAL,    // 初始态：未进入字符串
        IN_STRING,  // 字符串内态：已进入双引号包裹的字符串
        ESCAPE      // 转义态：字符串内遇到反斜杠，等待处理转义字符
    }

    public List<String> extractStrings(String filePath) throws IOException {
        List<String> result = new ArrayList<>();
        StringBuilder currentStr = new StringBuilder(); // 字符串缓冲区
        State currentState = State.INITIAL; // 初始状态

        // 读取文件内容（按字节读取，处理UTF-8编码）
        File file = new File(filePath);
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] bytes = fis.readAllBytes();
            String content = new String(bytes, StandardCharsets.UTF_8);

            // 逐字符扫描处理
            for (char c : content.toCharArray()) {
                switch (currentState) {
                    case INITIAL:
                        if (c == '"') {
                            // 遇到双引号，进入字符串内态，重置缓冲区
                            currentState = State.IN_STRING;
                            currentStr.setLength(0);
                        }
                        // 其他字符：保持初始态，忽略
                        break;

                    case IN_STRING:
                        if (c == '\\') {
                            // 遇到反斜杠，进入转义态
                            currentState = State.ESCAPE;
                        } else if (c == '"') {
                            // 遇到结束双引号，保存字符串，回到初始态
                            result.add(currentStr.toString());
                            currentState = State.INITIAL;
                        } else {
                            // 普通字符，加入缓冲区
                            currentStr.append(c);
                        }
                        break;

                    case ESCAPE:
                        // 处理转义字符，转换后加入缓冲区，回到字符串内态
                        switch (c) {
                            case '"': currentStr.append('"'); break;
                            case '\\': currentStr.append('\\'); break;
                            case 'n': currentStr.append('\n'); break;
                            case 't': currentStr.append('\t'); break;
                            case 'r': currentStr.append('\r'); break;
                            default: currentStr.append(c); // 未知转义，按原样处理
                        }
                        currentState = State.IN_STRING;
                        break;
                }
            }
        }
        return result;
    }

    // 测试方法
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        thirdexperiment extractor = new thirdexperiment();
        try {
            List<String> strings = extractor.extractStrings("C://develop//代码//experiment4//src//test.txt");
            for (String s : strings) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
