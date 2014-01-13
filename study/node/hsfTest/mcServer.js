var hsf = require('hsf');

/**
 * 创建client
 * create a new hsfClient
 * @param  {Object} options global config
 *  - {String} configSvr              configServer的host地址，默认为日常环境
 *  - {Number} connectTimeout         此client下全局的建立连接超时时间，默认为3秒
 *  - {Number} responseTimeout        此client下全局的响应超时时间，默认为3秒
 *  - {Number} routeInterval          此client下全局的向configSvr重新请求服务端地址、更新地址列表的间隔时间，默认为1分钟
 *  - {Boolean} snapshot              是否使用快照功能，使用快照则在启动的时候如果无法连接到config server，则读取本地缓存的服务者地址。
 *  - {String|Stream|function} logger 记录日志的路径或者Stream或者方法用于日志的写入
 *  - {Boolean} logOff                关闭日志
 *  - {Boolean} keepAlive             设置此client下生成的所有consumer是否与服务端维持长连接，默认为true
 *  - {Boolean} noDelay               设置此client下生成的所有consumer是否关闭nagle算法，默认为true  
 * @return {HsfClient}
 * @public
 * 关于 configServer host 的配置值：
 * 日常: 10.232.16.8
 * 线上: commonconfig.config-host.taobao.com
 * 预发: 172.23.226.84
 */

var client = hsf.createClient({
    configSvr: '10.232.16.8'
});

/**
 * 创建一个consumer，可以同时创建多个consumer来调用多个HSF服务
 * @param  {string} interface   服务接口名
 * @param  {string} version     服务版本号
 * @param  {object} options     
 *  - group                     服务分组，默认为hsf分组，一般不需要更改
 *  - routeInterval             此consumer的重新请求服务端地址、更新地址列表的间隔时间
 *  - connectTimeout            此consumer的建立连接超时时间
 *  - responseTimeout           此consumer的响应超时时间
 *  - id                        interface:version，在不传这两个参数的时候可以用id来替代
 *  - keepAlive                 此consumer是否与服务器维持长连接
 *  - noDelay                   此consumer是否关闭nagle算法  
 * @return {Consumer}
 * @public
 */
var mcMessageSenderService = client.createConsumer('com.taobao.messenger.service.MessageSenderService',
        '1.3.0.daily');

/**
 * 及时发送参数
 * @param address  发送地址, 手机号码，或者 邮件地址，或者旺旺号
 * @param subject  发送主题
 * @param content  发送内容
 * @param channel  通道选择
 * @param sourceId  发送源地址
 * @return
 * @throws MessageException
 */
var channel = {
    $class: 'com.taobao.messenger.service.common.Channel',
    $: {
        value: {
            $class: 'int',
            $: 0xFFFFFFFF
        }
    }
};

var args = ['libiao.lb@tmall.com', 'test', 'test content', channel, '9527'];

mcMessageSenderService.invoke('send', args, function(err, data) {
    console.log(err, data);
  });



