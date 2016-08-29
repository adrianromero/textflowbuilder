//    TextFlow Builder is a JavaFX library create styled labels
//    Copyright (C) 2016 Adrián Romero Corchado.
//
//    This file is part of TextFlow Builder
//
//     Licensed under the Apache License, Version 2.0 (the "License");
//     you may not use this file except in compliance with the License.
//     You may obtain a copy of the License at
//     
//         http://www.apache.org/licenses/LICENSE-2.0
//     
//     Unless required by applicable law or agreed to in writing, software
//     distributed under the License is distributed on an "AS IS" BASIS,
//     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//     See the License for the specific language governing permissions and
//     limitations under the License.

import com.adr.textflow.ParseCommands;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;
import com.adr.textflow.ParseHandler;
import junit.framework.Assert;

/**
 *
 * @author adrian
 */
public class ParserTest {

    @Test
    public void testParser() throws IOException {
        verify("The most simple text.", "(((The most simple text.)));;;\n");
        verify("{command}Simple text with a command.", "command(((Simple text with a command.)));;;\n");
        verify("{}{command in the middle}{}Empty commands.{}Dummy text{}.", "command in the middle((()));;;\n"
                + "(((Empty commands.)));;;\n"
                + "(((Dummy text)));;;\n"
                + "(((.)));;;\n");
        verify("{ab\\\\c\\{\\}}Testing escape chars.\\\\ \\a \\{ \\}.", "ab\\c{}(((Testing escape chars.\\ a { }.)));;;\n");
        verify("{command1}more \\{escape{command\\}2} chars.", "command1(((more {escape)));;;\n"
                + "command}2((( chars.)));;;\n");
    }

    private void verify(String in, String expected) throws IOException {
        TestHandler t = new TestHandler();
        Reader r = new StringReader(in);
        ParseCommands creator = new ParseCommands(r, t);
        creator.parse();
        Assert.assertEquals(expected, t.toString());
    }

    private static class TestHandler implements ParseHandler {

        private StringBuilder b = new StringBuilder();

        @Override
        public void process(String command, String value) {
            b.append(command);
            b.append("(((");
            b.append(value);
            b.append(")));;;\n");
        }

        @Override
        public String toString() {
            return b.toString();
        }
    }
}
