常用功能框架总结：
原则：低耦合，高内聚。

设计模式六大原则：
参考地址：http://www.uml.org.cn/sjms/201211023.asp


设计模式六大原则（1）：单一职责原则
一个类只负责一项职责



设计模式六大原则（2）：里氏替换原则
所有引用基类的地方必须能透明地使用其子类的对象
解决方案：当使用继承时，遵循里氏替换原则。类B继承类A时，除添加新的方法完成新增功能P2外，尽量不要重写父类A的方法，也尽量不要重载父类A的方法。



设计模式六大原则（3）：依赖倒置原则
高层模块不应该依赖低层模块，二者都应该依赖其抽象；抽象不应该依赖细节；细节应该依赖抽象。



设计模式六大原则（4）：接口隔离原则
客户端不应该依赖它不需要的接口；一个类对另一个类的依赖应该建立在最小的接口上。
建立单一接口，不要建立庞大臃肿的接口，尽量细化接口，接口中的方法尽量少。
解决方案：将臃肿的接口I拆分为独立的几个接口，类A和类C分别与他们需要的接口建立依赖关系。也就是采用接口隔离原则。


设计模式六大原则（5）：迪米特法则（最少知道原则）
一个对象应该对其他对象保持最少的了解。
解决方案：尽量降低类与类之间的耦合。


设计模式六大原则（6）：开闭原则
一个软件实体如类、模块和函数应该对扩展开放，对修改关闭
解决方案：当软件需要变化时，尽量通过扩展软件实体的行为来实现变化，而不是通过修改已有的代码来实现变化。


关键类及其含义

BaseActivity:实现IForm接口，封装常用方法，如设置标题等；
BaseFragment：实现IForm接口，封装常用方法，如设置标题等；

ViewHolder:负责获取文本，设置文本，显示图片，设置背景色等页面（Activity,Fragment,Adapter,Dialog,Popuwindow）相关功能；

CommonFun:公共方法，字符串处理，日期处理，金额处理等公共方法
CommonFumAndroid:处理Android特有公共方法：如获取屏幕大小，系统版本，获取设备唯一标示等

ICache:处理缓存



//Android 各种坑
//http://blog.csdn.net/cjpx00008/article/details/52100755



步骤1：
配置xUtils3框架：
a.Application中初始化
b.BaseActivit,BaseFragment中注入框架


1、启动页
避免启动APP黑屏，在配置文件中AndroidManifest.xml配置背景色或背景图，参考项目中GuideActivity.class



内存管理：
获取内存信息：CommFunAdromd中获取
命令行：
adb shell   进入adb
ps   查看运行的进程
dumpsys meminfo com.ysy15350.myutil   查看app进程相关信息

代码编写：
1、字符串拼接  用StringBuilder
2、ArrayMap、SparseArray  替换HashMap
3、内存抖动：避免在循环内部创建对象,例：MemoryActivity  doChurn()方法
4、数据结构优化，再小的Class也要耗费0.5kb
5、Hashmap一个entry需要额外占用的32B

