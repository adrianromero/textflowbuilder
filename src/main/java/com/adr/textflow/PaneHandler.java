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

import javafx.scene.layout.Pane;

/**
 *
 * @author adrian
 */
public interface PaneHandler extends ParseHandler {

    public Pane toPane();
}
