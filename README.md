TextFlow Builder [![Release](https://jitpack.io/v/adrianromero/textflowbuilder.svg)](https://jitpack.io/#adrianromero/textflowbuilder)
================

TextFlow Builder is a JavaFX library to create styled text labels.

Examples
========

```java
Pane text1 = TextContainer.createTextFlow("{-fx-fill:red; -fx-font-weight: bold;}Simple styled text.");
Pane text2 = TextContainer.createTextFlow("{-fx-fill:red; -fx-font-weight: bold;}More{-fx-fill: blue; -fx-font-size: 30;} than{} one style.");
Pane text3 = TextContainer.createTextFlow("And if you do not want style, do not add style tags...");
Pane text4 = TextContainer.createTextFlow("Remember to {-fx-font-weight: bold;}\\{escape\\}{} brackets if you want to use it.");
Pane text5 = TextContainer.createFlowPane("With FlowPanel you can {-fx-background-color: lightgray; -fx-background-radius: 5;} style {} the background.");
```

Screenshots
===========

![Demo examples](https://raw.github.com/adrianromero/textflowbuilder/master/screenshot-demo.png)

Demo examples

Install
=======

To install the library add: 
 
   ```gradle
   repositories { 
        jcenter()
        maven { url "https://jitpack.io" }
   }
   dependencies {
         compile 'com.github.adrianromero:textflowbuilder:1.0.0'
   }
   ```  


License
=======

TextFlow Builder is licensed under the Apache License, Version 2.0, January 2004.
