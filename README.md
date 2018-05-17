# Readhub
Readhub非官方Android客户端。

扫码下载apk：

![](https://ws1.sinaimg.cn/large/006tKfTcgy1fre67aarugj307k07kmyc.jpg)



### 项目预览

gif图清晰度不足，还请见谅。



##### 热门话题、科技动态、开发者资讯、区块链资讯

![gif1](https://github.com/j1406493495/ReadHub/blob/master/apk/gif1.gif)

##### 热门详情、资讯详情

![gif2](https://github.com/j1406493495/ReadHub/blob/master/apk/gif2.gif)

##### 稍后读

![gif3](https://github.com/j1406493495/ReadHub/blob/master/apk/gif3.gif)

##### 截图分享

![](https://ws2.sinaimg.cn/large/006tKfTcgy1fre5z2pyzij30do13qwit.jpg)



### 项目简介



##### 技术简介

- MVP架构
- Retrofit+RxJava请求接口数据
- Dagger、ButterKnife简化代码
- Glide图片加载
- AgentWeb显示新闻
- greenDao实现稍后读功能
- RxPermissions动态权限管理
- [ResultBack](https://github.com/j1406493495/ResultBack)(startActivityForResult回调封装，一行代码解决onActivityResult维护繁琐，支持RxJava)
- eventbus



##### Readhub api

```
private static final String BASE_URL = "https://api.readhub.me/";
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
    Observable<TopicResp> apiTopic(
            @Query("lastCursor") String lastCursor,
            @Query("pageSize") int pageSize
    );

    /**
     * topic detail
     * @param topicId
     * @return
     */
    @GET("topic/{topicId}")
    Observable<TopicMo> apiTopicDetail(
            @Path("topicId") String topicId);

    /**
     * 科技动态
     *
     * @param lastCursor
     * @param pageSize
     * @return
     */
    @GET("news")
    Observable<NewsResp> apiTeachNews(
            @Query("lastCursor") String lastCursor,
            @Query("pageSize") int pageSize
    );


    /**
     * 开发者资讯
     *
     * @param lastCursor
     * @param pageSize
     * @return
     */
    @GET("technews")
    Observable<NewsResp> apiDevelopNews(
            @Query("lastCursor") String lastCursor,
            @Query("pageSize") int pageSize
    );


    /**
     * 区块链资讯
     *
     * @param lastCursor
     * @param pageSize
     * @return
     */
    @GET("blockchain")
    Observable<NewsResp> apiBlockchainNews(
            @Query("lastCursor") String lastCursor,
            @Query("pageSize") int pageSize
    );
```



---

本文由 [Woong](http://woong.com.cn/) 创作，采用 [知识共享署名4.0](https://creativecommons.org/licenses/by/4.0/) 国际许可协议进行许可

本站文章除注明转载/出处外，均为本站原创或翻译，转载前请务必署名

最后编辑时间为:2018-05-17 18:00:00