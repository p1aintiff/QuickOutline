# Quick Outline

## 界面

![interface](image/screenshot.png)

## 使用方式
1. 拖动PDF文件到窗口
2. 写入目录文本，格式在下面
3. 设定 页面偏移量=PDF中的页码–原书的页码
4. 添加目录，完成！

### 按序号
```
1  我是标题  1
1.1  我是子标题  2
1.1.1  我是子子标题 3
```
此方式如有缩进将会自动去除，不会影响，最终生成的PDF中标题中也会带序号

### 按缩进（推荐使用制表符Tab键）
```
我是标题  1
    我是子标题  2
        我是子子标题  3
```
此方式如有序号将会视作标题，不会影响

### 查找想要的目录

1. 各大书评、卖书等网站，均能找到相应书的目录，这里推荐，京东、豆瓣、淘宝

2. PDF书籍中页面非图片（即文字可以选中）时，直接复制并粘贴到软件中 ★★★

演示

![screenvideo_1](image/screenvideo_1.gif)



### Tips
1. 页面偏移量是可双向使用的

即添加目录时，会自动加上页面偏移量

在获取目录时，会自动减去页面偏移量

2. 中文序号支持

仅顶级目录使用，顶级目录中可使用中文序号，如 第一章

> 可能有人问为什么子目录不支持，太难实现了，没办法知道子目录的所在层级，要是哪位大神可以训练个模型自动识别目录层次结构可以联系我。
> 
>不过没关系，我们还有按缩进的方式，这个不受序号影响！

3. 自动缩进

自动缩进是按序号进行的

不仅仅是会自动缩进， 同时也会自动格式化：

- 自动切分，如
```
第一章我是标题21
->
第一章 我是标题 21
```

- 其他，不列举了

注意，使用自动缩进得到的文本层次结构，与直接使用按序号的方式添加的目录层次结构是一样的

主要用于在软件无法按序号识别某条目的层级时，可手动添加缩进进行快速层级微调，添加目录时记得选按缩进方式


## 使用 VSCode 以使用高级编辑功能

本软件不提供高级编辑功能（如正则表达式，VSCode 自带此功能）

如想使用，请使用软件中提供的 VSCode 按钮以启动

VSCode 中的内容会自动同步至软件窗口中

注意此同步是单项同步，即 VSCode -> 本软件

但在此期间，你可以使用软件中的自动缩进功能，此时软件中文本也会立即至 VSCode 中

### 配置

请先下载[VSCode](https://code.visualstudio.com/)

需要添加至环境变量，方法也很简单

### Windows

参考[Visual Studio Code on Windows](https://code.visualstudio.com/docs/setup/windows)

安装时勾选"添加到Path"（默认已勾选，用户无需进行任何操作），安装后需重启

> **Tip:** 若在下载时将其不慎取消勾选，可在找到安装目录下的 bin 文件夹，将其添加到系统环境变量中的 Path

### MacOS

参考[Visual Studio Code on macOS](https://code.visualstudio.com/docs/setup/mac#_launching-from-the-command-line)

1. 启动 VS Code.

2. 按下组合键 (Cmd+Shift+P)，输入 'shell command' 找到命令行: Install 'code' command in PATH command.

## Licence

本软件由于使用 iText7，因此采用 AGPL 方式开源

## 下载

Windows（可直接运行）

[下载地址](https://github.com/ririv/QuickQutline/releases)

Mac（稍等中）

由于自己没有mac，所以没法验证mac上的程序是否可用，后续等我找个小伙伴验证下立即就发布（有小伙伴主动举手的也可以找我）

