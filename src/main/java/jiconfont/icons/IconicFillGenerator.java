package jiconfont.icons;

import jiconfont.AbstractIconCodeGenerator;
import org.omg.CORBA.StringSeqHelper;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Copyright (c) 2016 jIconFont <BR>
 * <BR>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:<BR>
 * <BR>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.<BR>
 * <BR>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class IconicFillGenerator extends AbstractIconCodeGenerator {

  public IconicFillGenerator() {
    super("IF_");
  }

  public void start() {
    try {
      URI uri =
        IconicFillGenerator.class.getResource("/iconic_fill.css.scss").toURI();
      Path path = Paths.get(uri);
      List<String> list = Files.readAllLines(path, Charset.forName("UTF-8"));

      for (String line : list) {
        if (line.contains("icon") && line.contains("before")
          && line.contains("content")) {
          String split[] = line.toUpperCase().split("'");
          String name = split[1];
          String unicode = split[3].replace("\\", "");
          registerIcon(name, unicode);
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(generateIconCodes());
  }

  public static void main(String[] args) {
    // Copy the iconic_fill.css.scss file from
    // https://github.com/somerandomdude/Iconic/blob/master/font/Iconic%20Fill/iconic_fill.css.scss
    // into resources directory
    // TODO read the file using http
    IconicFillGenerator iconicFillGenerator = new IconicFillGenerator();
    iconicFillGenerator.start();
  }

}
