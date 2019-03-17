# Readhub
Readhub非官方Android客户端。

**master 分支为 Kotlin 版本，java 分支为 Java 版本。**

[Kotlin 转换指南（一、环境搭建）](http://woong.cn/2019/01/21/readhub-kotlin1.html)

[Kotlin 转换指南（二、数据库和网络请求](http://woong.cn/2019/03/08/readhub-kotlin2.html)

[Kotlin 转换指南（三、MVP 架构](http://woong.cn/2019/03/17/readhub-kotlin3.html)

扫码下载apk：

![](https://ws2.sinaimg.cn/large/006tKfTcly1g165ivxdq5j30as0au3yv.jpg)



### 项目预览

gif图清晰度不足，还请见谅。



##### 热门话题、科技动态、开发者资讯、区块链资讯

![](https://ws2.sinaimg.cn/large/006tNc79ly1fz439mne7cg30840e8qv5.gif)

##### 热门详情、资讯详情

![](https://ws4.sinaimg.cn/large/006tNc79ly1fz42831b8dg30820e8hdw.gif)

##### 稍后读

![](https://ws1.sinaimg.cn/large/006tNc79ly1fz42aw5qxbg30820e8hdw.gif)

##### 截图分享

![](https://ws1.sinaimg.cn/large/006tNc79ly1fz42w0ovbgg30820e8hdt.gif)

### 项目简介

##### 技术简介

- MVP 架构
- Retrofit+RxJava 请求接口数据
- Glide 图片加载
- AgentWeb 显示新闻
- greenDao 实现稍后读功能
- RxPermissions 动态权限管理
- [ResultBack](https://github.com/j1406493495/ResultBack)(startActivityForResult 回调封装，一行代码解决 onActivityResult 维护繁琐，支持 RxJava)
- eventbus



##### Readhub api

```
private val BASE_URL = "https://api.readhub.me/"
```

```
      /**
     * 热门话题
     *
     * @param lastCursor
     * @param pageSize
     * @return
     */
    @GET("topic")
    fun apiTopic(
            @Query("lastCursor") lastCursor: String,
            @Query("pageSize") pageSize: Int
    ): Observable<TopicResp>

    /**
     * topic detail
     * @param topicId
     * @return
     */
    @GET("topic/{topicId}")
    fun apiTopicDetail(
            @Path("topicId") topicId: String
    ): Observable<TopicDetailMo>

    /**
     * 科技动态
     *
     * @param lastCursor
     * @param pageSize
     * @return
     */
    @GET("news")
    fun apiTeachNews(
            @Query("lastCursor") lastCursor: String,
            @Query("pageSize") pageSize: Int
    ): Observable<NewsResp>


    /**
     * 开发者资讯
     *
     * @param lastCursor
     * @param pageSize
     * @return
     */
    @GET("technews")
    fun apiDevelopNews(
            @Query("lastCursor") lastCursor: String,
            @Query("pageSize") pageSize: Int
    ): Observable<NewsResp>


    /**
     * 区块链资讯
     *
     * @param lastCursor
     * @param pageSize
     * @return
     */
    @GET("blockchain")
    fun apiBlockchainNews(
            @Query("lastCursor") lastCursor: String,
            @Query("pageSize") pageSize: Int
    ): Observable<NewsResp>
```



---

本文由 [Woong](http://woong.com.cn/) 创作，采用 [知识共享署名4.0](https://creativecommons.org/licenses/by/4.0/) 国际许可协议进行许可

本站文章除注明转载/出处外，均为本站原创或翻译，转载前请务必署名

最后编辑时间为:2018-05-17 18:00:00