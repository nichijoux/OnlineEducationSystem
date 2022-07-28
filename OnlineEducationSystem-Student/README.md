# 在线教育系统-前台页面

> 本项目基于**尚硅谷**的在线教育系统实现

## 前台页面技术

### 服务端渲染技术NUXT

#### 什么是服务端渲染

服务端渲染又称 **SSR** （***Server Side Render***）是在服务端完成页面的内容，而不是在客户端通过 *AJAX* 获取数据。

服务器端渲染（***SSR***）的优势主要在于：更好的 *SEO*，由于搜索引擎爬虫抓取工具可以直接查看完全渲染的页面。

如果你的应用程序初始展示 *loading* 菊花图，然后通过 *Ajax* 获取内容，抓取工具并不会等待异步完成后
再进行页面内容的抓取。也就是说，如果 *SEO* 对你的站点至关重要，而你的页面又是异步获取内容，则你可能需要服务器端渲染（*SSR*）解决此问题。
另外，使用服务器端渲染，我们可以获得更快的内容到达时间（*time-to-content*），无需等待所有的
*JavaScript* 都完成下载并执行，产生更好的用户体验，对于那些「内容到达时间（*time-to-content*）与转化率直接相关」的应用程序而言，服务器端渲染（*SSR*）至关重要。

#### 什么是 *NUXT*

***Nuxt.js*** 是一个基于 ***Vue.js*** 的轻量级应用框架，可用来创建服务端渲染 （***SSR***） 应用，也可充当静态站点引擎
生成静态站点应用，具有优雅的代码结构分层和热加载等特性

#### *NUXT* 的目录结构

- 资源目录 **assets**

> 用于组织未编译的静态资源,如 LESS、SASS 或 JavaScript

- 组件目录 **components**

> 用于组织应用的 *Vue.js* 组件。*Nuxt.js* 不会扩展增强该目录下的 *Vue.js* 组件，即这些组件不会像页面组件那样有 *asyncData* 方法的特性

- 布局目录 **layouts**

> 用于组织应用的布局组件

`layouts/default.vue` 设置布局方式，只包含**头尾信息**，中间内容是其他页面的内容

- 页面目录 **pages**

> 用于组织应用的路由及视图。*Nuxt.js* 框架读取该目录下的所有 *.vue* 文件并且自动生成对应的路由配置

- 插件目录 **plugins**

> 用于组织那些需要在根 *Vue.js* 应用实例化之前需要运行的 *JavaScript* 插件

- **nuxt.config.js** 文件

> nuxt.config.js 文件用于组织 *Nuxt.js* 应用的个性化配置，以便覆盖默认配置

## 启动方式

``` bash
# install dependencies
$ npm install # Or yarn install

# serve with hot reload at localhost:3000
$ npm run dev

# build for production and launch server
$ npm run build
$ npm start

# generate static project
$ npm run generate
```
