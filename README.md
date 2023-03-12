# Budget
A budgeting app that records income and expenses。

一个记账应用，语言主要为Kotlin，Java用于一部分工具类。



# Screen Shots

## Theme Blue

<div style="display:flex;justify-content:space-between;">
    <img src="img/QQ截图20230312120131.png" width="30%" />
    <img src="img/QQ截图20230312120218.png" width="30%" />
    <img src="img/QQ截图20230312120318.png" width="30%" />
</div>


---

<div style="display:flex;justify-content:space-between;">
    <img src="img/QQ截图20230312120343.png" width="30%" />
    <img src="img/4SVF95T({5GWNU6T8Q4]SO7.png" width="30%" />
    <img src="img/QQ截图20230312120429.png" width="30%" />
</div>

---

<div style="display:flex;justify-content:space-between;">
    <img src="img/QQ截图20230312121846.png" width="30%" />
    <img src="img/QQ截图20230312122021.png" width="30%" />
    <img src="img/QQ截图20230312122051.png" width="30%" />
</div>

---

<div style="display:flex;justify-content:space-between;">
    <img src="img/QQ截图20230312122108.png" width="30%" />
    <img src="img/QQ截图20230312122137.png" width="30%" />
    <img src="img/QQ截图20230312122206.png" width="30%" />
</div>

---

<div style="display:flex;justify-content:left;">
    <img src="img/QQ截图20230312122234.png" width="30%" />
    <img src="img/QQ截图20230312122256.png" width="30%" />
</div>



> 还有其它主题色，在右边 [release](https://github.com/JeckOnly/Budget/releases) 栏下载 apk ，可以尽情玩耍该应用

# Multi Module

采用多模块设计，解耦合。架构设计学习自 Google NowInAndroid 项目。[NowInAndroid Architecture](https://github.com/android/nowinandroid/blob/main/docs/ModularizationLearningJourney.md)

<div style="display:flex;justify-content:space-between;">
    <img src="https://i.pinimg.com/originals/6b/db/f9/6bdbf9e14df1dbfa04d64b86783cd8b0.png" width="60%" />
    <img src="https://i.pinimg.com/originals/70/47/bf/7047bf13e29254b1d3e5e33a9ef3641b.png" width="30%" />
</div>




# Library

这里列出一些用到的库

### UI

- Jetpack Compose
  - 基础
  - Constraint Layout
  - Material Design 3
  - Compose Navigation
  - Accompanist
    - Systemuicontroller
    - Pager
    - Navigation animation
  - Lottie
  - 列表的拖动排序框架 "org.burnoutcrew.composereorderable:reorderable"

### 依赖注入框架

- Hilt

### 数据存储框架

- Room
- DataStore protobuf

### 日志框架

- Timber

以及从 [Material Design 3算法库](https://github.com/material-foundation/material-color-utilities) copy 了一些算法相关的源代码。





