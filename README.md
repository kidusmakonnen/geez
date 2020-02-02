Ge'ez Utils
=============
[![](https://jitpack.io/v/kidusmakonnen/geez.svg)](https://jitpack.io/#kidusmakonnen/geez)
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg)](http://opensource.org/licenses/MIT)

Ge'ez utils currently has a tool to convert any numbers into their [Ge'ez number](https://en.wikipedia.org/wiki/Ge%CA%BDez_script#Numerals "Ge'ez number") representations. More utilities will be added soon.

Installation
=============

Gradle
---------------------------
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
-------
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
All you need to do is call  `GeezUtils.toGeez` and specify a number. Your number will be converted to its Ge'ez representation.
```
GeezUtils.toGeez(1298)//'፲፪፻፺፰'
```

Releases
------------------
### 0.1.3
> * Fixed regex issue

### 0.1.0
> * Initial Release

Used in
==================
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