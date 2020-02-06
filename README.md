Ge'ez Utils
=============
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/231cf6d0c2cd469bbad76d764c5efbc9)](https://app.codacy.com/manual/kidusmakonnen/geez?utm_source=github.com&utm_medium=referral&utm_content=kidusmakonnen/geez&utm_campaign=Badge_Grade_Dashboard)
[![Coverage Status](https://coveralls.io/repos/github/kidusmakonnen/geez/badge.svg?branch=master)](https://coveralls.io/github/kidusmakonnen/geez?branch=master)
[![Build Status](https://travis-ci.com/kidusmakonnen/geez.svg?branch=master)](https://travis-ci.com/kidusmakonnen/geez)
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg)](http://opensource.org/licenses/MIT)
[![](https://jitpack.io/v/kidusmakonnen/geez.svg)](https://jitpack.io/#kidusmakonnen/geez)

Ge'ez utils currently has a tool to convert any numbers into their [Ge'ez number](https://en.wikipedia.org/wiki/Ge%CA%BDez_script#Numerals "Ge'ez number") representations. More utilities will be added soon.

Installation
=============

Gradle
------
**Step 1.**  Add the JitPack repository to your build file
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
**Step 2.** Add the dependency
```
dependencies {
  implementation 'com.github.kidusmakonnen:geez:Tag'
}
```

Maven
-----
**Step 1.** Add the JitPack repository to your build file
```
<repositories>
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>
```

Step 2. Add the dependency
```
<dependency>
    <groupId>com.github.kidusmakonnen</groupId>
    <artifactId>geez</artifactId>
    <version>Tag</version>
</dependency>
```

Usage
=======
To convert numbers to Ge'ez
----------------------------
All you need to do is call  `GeezUtils.toGeez` and specify a number. Your number will be converted to its Ge'ez representation.
```
GeezUtil.toGeez("1298")//returns "፲፪፻፺፰"
```

To convert numbers from Ge'ez
-----------------------------
To convert numbers from Ge'ez, call `GeezUtils.fromGeez` with the Ge'ez number you want to convert as a parameter.
```
GeezUtil.fromGeez("፫፼፫፻፫፼፫፻፫")//returns 303030303
```
**Note:** The method throws an IllegalArgumentException if you provide a malformed Ge'ez number string.

Releases
--------
### 0.2.0
> * Added number conversion from Ge'ez numerals
### 0.1.3
> * Fixed regex issue

### 0.1.0
> * Initial Release

Used in
========
-  [Ge'ez number converter bot](https://t.me/GeezNumberBot "Link")


## License

```

Copyright (c) 2020 Kidus Makonnen

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
OR OTHER DEALINGS IN THE SOFTWARE.

```
