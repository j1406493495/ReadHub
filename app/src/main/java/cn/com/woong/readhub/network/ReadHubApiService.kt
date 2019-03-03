package cn.com.woong.readhub.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ReadhubApiService {

    /**
     * 热门话题
     *
     * @param lastCursor
     * @param pageSize
     * @return
     */
//    @GET("topic")
//    fun apiTopic(
//            @Query("lastCursor") lastCursor: String,
//            @Query("pageSize") pageSize: Int
//    ): Observable<TopicResp>

    /**
     * topic detail
     * @param topicId
     * @return
     */
//    @GET("topic/{topicId}")
//    fun apiTopicDetail(
//            @Path("topicId") topicId: String): Observable<TopicDetailMo>

    /**
     * 科技动态
     *
     * @param lastCursor
     * @param pageSize
     * @return
     */
//    @GET("news")
//    fun apiTeachNews(
//            @Query("lastCursor") lastCursor: String,
//            @Query("pageSize") pageSize: Int
//    ): Observable<NewsResp>


    /**
     * 开发者资讯
     *
     * @param lastCursor
     * @param pageSize
     * @return
     */
//    @GET("technews")
//    fun apiDevelopNews(
//            @Query("lastCursor") lastCursor: String,
//            @Query("pageSize") pageSize: Int
//    ): Observable<NewsResp>


    /**
     * 区块链资讯
     *
     * @param lastCursor
     * @param pageSize
     * @return
     */
//    @GET("blockchain")
//    fun apiBlockchainNews(
//            @Query("lastCursor") lastCursor: String,
//            @Query("pageSize") pageSize: Int
//    ): Observable<NewsResp>
}