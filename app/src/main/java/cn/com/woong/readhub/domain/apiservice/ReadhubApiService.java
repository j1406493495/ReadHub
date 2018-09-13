package cn.com.woong.readhub.domain.apiservice;

import java.util.Map;

import cn.com.woong.readhub.bean.TopicDetailMo;
import cn.com.woong.readhub.bean.TopicMo;
import cn.com.woong.readhub.resp.BaseResponse;
import cn.com.woong.readhub.resp.NewsResp;
import cn.com.woong.readhub.resp.TopicResp;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author woong
 * ReadhubApiService 中方法命名和接口名称一致 例:api/login=apiLogin
 */

public interface ReadhubApiService {

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
    Observable<TopicDetailMo> apiTopicDetail(
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
}
