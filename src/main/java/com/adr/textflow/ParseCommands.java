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
package com.adr.textflow;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 *
 * @author adrian
 */
public class ParseCommands {

    private final Reader r;
    private final ParseHandler handler;

    public ParseCommands(String s, ParseHandler handler) {
        this.r = new StringReader(s);
        this.handler = handler;
    }

    public ParseCommands(Reader r, ParseHandler handler) {
        this.r = r;
        this.handler = handler;
    }

    public void parse() throws IOException {
        int i;
        char c;
        StringBuilder command = new StringBuilder();
        StringBuilder text = new StringBuilder();

        int status = 0;

        while ((i = r.read()) != -1) {
            c = (char) i;
            if (status == 0) {
                if (c == '\\') {
                    status = 1;
                } else if (c == '{') {
                    if (command.length() > 0 || text.length() > 0) {
                        handler.process(command.toString(), text.toString());
                    }
                    status = 2;
                    command = new StringBuilder();
                    text = new StringBuilder();
                } else {
                    text.append(c);
                }
            } else if (status == 1) {
                text.append(c);
                status = 0;
            } else if (status == 2) {
                if (c == '\\') {
                    status = 3;
                } else if (c == '}') {
                    status = 0;
                } else {
                    command.append(c);
                }
            } else if (status == 3) {
                command.append(c);
                status = 2;
            }
        }

        if (status != 0) {
            throw new IOException("Invalid status.");
        }

        if (command.length() > 0 || text.length() > 0) {
            handler.process(command.toString(), text.toString());
        }
    }
}
