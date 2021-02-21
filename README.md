# Wklife
WK生活APP Version 1.0
-------------------------
**注意事项**：
<br>1.下载后必须查看Gradle版本是否相同要不然android studio会自行下载（超级慢）</br>
<br>2.若版本不同需要打开给出的链接自行下载zip放在c盘的gradle.里，具体百度</br>
## APP介绍
*主要功能及设计思路*：生活类APP
<br>1.用百度提供的API实现地图功能，可以通过GPS或者网络定位当前所在告知用户具体位置(所在国家、省份、城市、市区、街道）</br>
<br>2.用ViewPager，PagerAdapter实现banner轮播图，可以间隔一定时间自动轮转图片，也可以手动拖动图片实现切换，当前显示的页面下标的圆点会变色，其余未显示的页面下标的圆点则都是白色，
代表未显示<br>
<br>3.基于地图功能开发规划的方法，输入起点终点即可形成规划，规划有步行、骑行和驾车规划，自由出行
</br>
<br>4.用floatingActionButton、onClick设置点击事件、swich case等实现记录功能，可以写行程安排，方便路程规划，也可以当便签来用记下生活的点滴。有本地数据缓存，可以当记事本，笔记本。
长按删除，点击添加按钮新建页面，已添加的便签会有添加的具体日期时间方便后续工作与回忆，左上角有分享功能，可以把内容分享给QQ,微信好友</br>
<br>5.主页面运用toolbar、drawerlayout、navigationview实现滑动菜单功能，用viewpager+fregment+tablayout实现底部导航栏，导航栏和菜单栏点击图标会变色，未点击的则无色</br>
<br>6.启动APP有启动页面，用intent跳转到主界面</br>
<br>7.运用了一些自定义的view</br>
### 功能展示
ps:由于时间原因暂时用APP内的截图展示（后续会改成gif动画）、应用截图下那个黑条是手机自带的不是app里的
![Image text](https://github.com/wksports/Wklife/blob/master/app/src/main/res/picture/picture_1.jpg)
![Image text](https://github.com/wksports/Wklife/blob/master/app/src/main/res/picture/picture_2.jpg)
![Image text](https://github.com/wksports/Wklife/blob/master/app/src/main/res/picture/picture_3.jpg)
![Image text](https://github.com/wksports/Wklife/blob/master/app/src/main/res/picture/picture_4.jpg)
![Image text](https://github.com/wksports/Wklife/blob/master/app/src/main/res/picture/picture_5.jpg)
![Image text](https://github.com/wksports/Wklife/blob/master/app/src/main/res/picture/picture_6.jpg)
![Image text](https://github.com/wksports/Wklife/blob/master/app/src/main/res/picture/picture_7.jpg)
![Image text](https://github.com/wksports/Wklife/blob/master/app/src/main/res/picture/picture_8.jpg)
![Image text](https://github.com/wksports/Wklife/blob/master/app/src/main/res/picture/picture_9.jpg)

#### 心得体会
这个项目从学习相关知识到开始敲代码做了很久，学会了多种布局方法和用API生成密钥key开发程序。
其中最难的是把各种功能综合结合在一起，但功夫不负有心人，wk生活1.0版终于出来了（虽然还有很多设想没有实现，但是跨出了第一步，成功就不会远了）

##### 待优化提升的地方
<br>1.初始化时APP会出现定位错误或无数据的问题
<br>2.App的UI设计</br>
<br>3.侧面滑动菜单诸多功能的实现</br>
<br>4.布局设计并不完美后续会把主界面布局改的更完美，添加顶部导航栏</br>
<br>5.这只是wk生活的1.0版本后续会把设置界面完善，增加更多功能，提高用户体验</br>
<br>6.暂时还没有登录注册界面</br>
<br>6.**爱拼才会赢，爱拼才能无愧生活**</br>
