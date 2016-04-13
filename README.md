# BadgeUtil
Android 不同Launcher添加桌面角标Util

* Android桌面角标的适配确实是非常坑爹的需求。原生系统根本就没有这个功能，国内很多厂家效仿ios都自己定义了该功能。Android程序员就很苦逼，要适配很多机型。建议玩不得以情况下还是不要进行这项工作。
其实这个功能和手机厂家没有直接的关系，而是和手机当前使用的launcher有直接关系。比如三星的手机我安装Asus桌面，这时候我们就不能适配三星launcher而要去适配华硕launcher。
* 在开发中主要参考了[ShortcutBadger](https://github.com/leolin310148/ShortcutBadger "ShortcutBadger")，[Badge分析&如何逼死处女座](http://www.jianshu.com/p/0992ff9eeeb6 "Badge分析&如何逼死处女座")
* 在开发中问题总结
 * nova launcher 的免费版本是没有桌面角标的功能,nova launcher prime版本才有(需要收费且国内各大应用市场没有提供下载)。
 * apex launcher 也是需要收费。
 * adw launcher 是免费的功能正常，但是ui太难看。
 * asus launcher 是免费的功能正常，但是发现如果连续发送未读消息，角标显示有延迟。
 * htc launcher 没有机器没测试
 * sony launcher 没有机器没有测试
 * LG launcher 没有机器没有测试
 * 小米/三星/asus/adw亲测可以正常使用
 * meizu 目前不支持(在Flyme4.5测试微信)
 * 联想 VIBE ui目前不支持(在VIBE UIV3.1测试微信)
 * 锤子 launcher 目前不支持第三方app的角标(但是微信有，应该是给单独适配了微信)
 * oppo等其他手机没有测试也没有考虑
 * 最后说说华为。Emui3.0上确实第三方应用都没有角标包括微信。Emui4.1开始支持第三方app角标[华为桌面角标介绍](http://developer.huawei.com/cn/consumer/wiki/index.php?title=%E5%8D%8E%E4%B8%BA%E6%A1%8C%E9%9D%A2%E8%A7%92%E6%A0%87%E4%BB%8B%E7%BB%8D "华为桌面角标介绍")(很复杂！！！)。按照这个逻辑EMui3.1所有的第三方都不支持，但是就是微信，钉钉却有角标。我给领导的结论是EMui3.1上是不支持的，但领导说为什么她的手机微信钉钉能做到。为了解决这个疑惑发帖，进群，发邮件，在折腾2天后，华为一个技术回邮件说"角标属于受限开放能力，仅面向即时通信领域前五的app开放"，终于解决了这个疑惑。真是鄙视这些为大户开绿灯看不清小众app的厂商。决定暂时不支持华为
* [ShortcutBadger](https://github.com/leolin310148/ShortcutBadger)实际使用中 发现这个项目有些不合理的地方。重新整理了一个[library](https://github.com/lixiangers/BadgeUtil "library")
 * 一般情况下设置角标和notification是并行的，改成发送notification的同时修改角标。
 * 还有小米中count 并不是所有notification的总和。二是要传入当前notificationId 对应的count。对小米launcher进行了改进
* badge 真是个费力不讨好的需求，希望有同样需求的伙伴少走些弯路。当有新的手机launcher需要适配的时候继续进行完善


