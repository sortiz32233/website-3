package com.taobao.tcif.service;

import java.util.List;
import java.util.Map;

import com.taobao.tcif.annotaion.Param;

/**
 * 提供用户的读取服务
 */
public interface TCIFService {
    /**
     * 得到单个用户的信息
     * 
     * @param appname 分配给应用的名称
     * @param appkey 分配给应用对于的密钥
     * @param uid 淘宝用户id
     * @param fields 指定需要返回的字段，用(,)号分割，默认值为应用授权的默认字段
     * @return 单个用户的信息字符串
     */
	@Deprecated
    public String getUser(@Param(name="appname") String appname, @Param(name="appkey") String appkey, @Param(name="uid") String uid, @Param(name="fields") String fields);
    
    public Map fetchUser(@Param(name="appname") String appname, @Param(name="appkey") String appkey, @Param(name="uid") String uid, @Param(name="fields") List<String> fields);

    /**
     * 更新单个用户的信息
     * 
     * @param appname 分配给应用的名称
     * @param appkey 分配给应用对于的密钥
     * @param userJSONString 淘宝用户信息的JSON格式 - {String} uid, 必须包含uid字段
     * @return 操作结果的JSON信息字符串
     */
    @Deprecated
    public String updateUser(@Param(name="appname") String appname, @Param(name="appkey")  String appkey, @Param(name="userJSONString") String userJSONString);
    
    public Map modifyUser(@Param(name="appname") String appname, @Param(name="appkey")  String appkey, @Param(name="user") Map user);
    
    /**
     * 检测用户是否满足标签表达式
     * 
     * @param appname 分配给应用的名称
     * @param appkey 分配给应用对于的密钥
     * @param uid 淘宝用户id
     * @param expressions 标签表达式，每个表达式之前使用","分隔； 
          比较操作符支持 " >, <, >=, <=, =, != " 6种。
          如 "判断用户是否女性并且最近1年女装类目购买笔数少于等于3": "trade_count_16<=3,user_gender=0"
     * @return 按表达式的顺序返回表达式的结果字符串, 如 {"status": 200, "results": [1, 0]}
     */
    @Deprecated
    public String checkUser(@Param(name="appname") String appname, @Param(name="appkey")  String appkey, @Param(name="uid")  String uid, @Param(name="expressions")  String expressions);
    
    public Map verifyUser(@Param(name="appname") String appname, @Param(name="appkey")  String appkey,  @Param(name="uid") String uid, @Param(name="expressions")  List<String> expressions);

    /**
     * 获取新用户在某类目下的信息
     * 
     * @param appname 分配给应用的名称
     * @param appkey 分配给应用对于的密钥
     * @param uid 淘宝用户id
     * @param cid 类目id
     * @return 1: 是, 0: 否, -1: 网络错误, -2: 授权错误
     */
    public int isNew(@Param(name="appname") String appname, @Param(name="appkey")  String appkey, @Param(name="uid")  String uid, @Param(name="cid")  String cid);
    
    /**
     * 向权限管理中添加用户，应用调用方必须为工作流平台。
     * 
     * @param appname 分配给应用的名称
     * @param appkey 分配给应用对于的密钥
     * @param userJSONString 用户信息的格式 -
     *    { "uid": "test",
     *      "permission": {"dictionary": true, "monitor": false, "random": false, "search": false},
     *      "name": "测试",
     *      "wangwang": "test",
     *      "department": "EDP",
     *      "note": "开发人员"
     *     }
     *     permission 可选值  [dictionary, monitor, random, search]
     * @return 操作结果的JSON信息字符串
     */
    public String adminAddUser(@Param(name="appname") String appname, @Param(name="appkey")  String appkey, @Param(name="userJSONString")  String userJSONString);
    
    /**
     * 获取用户字典表
     * 
     * @param appname 分配给应用的名称
     * @param appkey 分配给应用对于的密钥
     * @param lastupdate 上次获取时间，如果设置为空字符串 "" ，则获取全部字段. 日期格式: `yyyy-MM-dd hh:mm:ss`, e.g.: '2012-07-08 13:14:10'
     * @return 结果以JSON字符串返回 
     *  - 请求成功: {
     *    status: 200, // 200: success, 500: error
     *    fields: [{
     *      name: 'tag_user_wangwang',
     *      cname: '旺旺号',
     *      type: 'String', // 'String', 'Number', 'Array', 'Object'
     *      map: null,
     *      desc: '用户旺旺名称',
     *      category: 'base' // 字段分类
     *    }, ...]
     *  }
     * - 请求失败: {
     *    status: 500,
     *    error: 'something error message',
     *    error: 'SomeError'
     *  }
     */
    public String listFields(@Param(name="appname") String appname, @Param(name="appkey")  String appkey, @Param(name="lastupdate") String lastupdate);
    
    /**
     *  根据指定条件查询用户信息
     * @param appname
     * @para m appkey
     * @param table
     *   目前只接受 tcifsns 表，将来回扩展
     * @param queryJSONString
    *   {"q1": {"where": "hasavatar = 1", "order": "meishi_buyitem", "limit": "100"}, "q2": {...}}
    *   1，where 使用建议使用 “=a”或 “between a and b" 操作，如果要使用 ">" 或 "<"  或 ">=" 或 "<=" 则开口闭口都必须要有。
    *   2，随机查询 order : "rand()" 注意：随机查询不能再指定字段进行排序。
    *   3，order 支持多字段排序,多个字段之间用逗号隔开
    *   4，limit 指定行数，如果不指定的话默认返回2条。
     * @return
     */
    @Deprecated
    public String queryUser(@Param(name="appname") String appname, @Param(name="appkey")  String appkey, @Param(name="table")  String table, @Param(name="queryJSONString")  String queryJSONString);
    
    public Map findUsers(@Param(name="appname") String appname, @Param(name="appkey")  String appkey, @Param(name="table")  String table, @Param(name="query")  Map query);
   
    /**
     * 热搜索品牌，顶级类目下的热门搜索品牌
     * @param {String} cid 类目ID或类目别名
     * @return {Object} 热门搜索品牌 
     * example:
     * ```
     *  {
     *    "brands":[
     *      [17301504,"楼兰蜜语",601034,601034,0],
     *      [10122,"other/其它",585549,585549,0],
     *      [110416,"好想你",317608,317608,0],
     *      [48898270,"尧杰",243479,243479,0],
     *      [85759303,"天山佳园",229167,229167,0],
     *      [57254,"昆仑山",210213,210213,0],
     *      [111501,"修文",15249,15249,0],
     *      [7585318,"和情",14184,14184,0]
     *      ...
     *    ],
     *    "endDate":"2012-01-01",
     *    "startDate":"2011-12-26"
     *  }
     * ```
     */
    public Map getTopHotSearchBrands(@Param(name="appname") String appname, @Param(name="appkey")  String appkey, @Param(name="cid")  String cid);
    
    /**
     * 热门搜索品牌，按二级类目分组的热门搜索品牌
     * @param {String} cid 类目ID或类目别名
     * @return {Object} 热门搜索品牌 
     * example:
     * ```
     * {
     *  "categories":[
     *    ["50010511","饼干",[
     *      [10122,"other/其它",159844,159844,0],
     *      [7259025,"TIPO",40254,40254,0],
     *      [66557,"乐天",35696,35696,0],
     *      [7037893,"白鹤",22526,22526,0],
     *      [119540,"Crown可拉奥",22182,22182,0],
     *      [119080,"AJI",21891,21891,0],
     *      [119543,"海太",21547,21547,0],
     *      [15354607,"甜甜乐",18879,18879,0]
     *    ]],
     *    [
     *      "50008618","鸭肉类",[
     *        [111496,"周黑鸭",111796,111796,0]
     *      ]
     *    ],
     *    "endDate":"2012-01-01",
     *    "startDate":"2011-12-26"
     *  }
     * ```
     */
    public Map getHotSearchBrands(@Param(name="appname") String appname, @Param(name="appkey")  String appkey,  @Param(name="cid")  String cid);
    
    /**
     * 一级类目下的热搜词
     * @param {String} cid 类目ID或类目别名
     * @return {Object} 热门搜索品牌 
     * example:
     * ```
     *  {
     *    "keywords":[
     *      ["无花果 80后 怀旧",14219,14219,0],
     *      ["红枣",12043,12043,0],
     *      ["特产",8801,8801,0],
     *      ["鸭脖",8434,8434,0],
     *      ["凤爪",635,635,0],
     *      ["77松塔",615,615,0],
     *      ...
     *    ],
     *    "endDate":"2012-01-01",
     *    "startDate":"2011-12-26"
     *  }
     * ```
     */
    public Map getHotSearchWords(@Param(name="appname") String appname, @Param(name="appkey")  String appkey,  @Param(name="cid")  String cid);

    /**
     * 热搜类目
     * @param {String} cid 类目ID或类目别名
     * @param {Object} tags tcif标签
     * @return {Object} 热门搜索品牌 
     * example:
     * ```
     *  {
     *    "categories": [
     *      {
     *        "categories":[
     *          {"category":"饼干","categoryId":"50010511","uv":40822},
     *          {"category":"膨化食品","categoryId":"50010535","uv":33845},
     *          {"category":"传统糕点","categoryId":"50010513","uv":30501},
     *        ],
     *        "categoryId":"50010550",
     *        "category":"饼干/糕点/小点心/膨化",
     *        "uv":119454
     *      },
     *      {
     *        "categories":[
     *          {"category":"枣类制品","categoryId":"50013099","uv":37864},
     *          ...
     *          {"categories":[{"category":"内蒙古奶酪","categoryId":"50008431","uv":2164},
     *          {"category":"奶油","categoryId":"50012390","uv":2028},
     *          {"category":"黄油","categoryId":"50016426","uv":1866},
     *          {"category":"芝士/西式奶酪","categoryId":"50016425","uv":1018}
     *        ]
     *      }
     *    ],
     *    "endDate":"2012-01-01",
     *    "startDate":"2011-12-26"
     *  }
     * ```
     */
    public Map getHotSearchCategories(@Param(name="appname") String appname, @Param(name="appkey")  String appkey,  @Param(name="cid")  String cid, @Param(name="tags")  Map tags);
}
