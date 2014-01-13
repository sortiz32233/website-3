var hsf = require('hsf');
var client = hsf.createClient({
  configSvr: '10.232.16.8'
});
var UicDeliverAddressService = client.createConsumer('com.taobao.uic.common.service.userinfo.UicDeliverAddressService', 
  '1.0.0.daily');
var baseDeliverAddressDO = {
  $class: 'com.taobao.uic.common.domain.BaseDeliverAddressDO',
  $: {
    id: 13254,
    idLong: {
      $class: 'long',
      $: 13254
    },
    fullName: 'gongyangyu',
    phone: '123456789',
    mobile: '123456789',
    address: 'chuangyedasha',
    postCode: '310018',
    userId: {
      $class: 'long',
      $: 24567
    },
    status: 1,
    city: 'hangzhou',
    province: 'zhejiang',
    area: 'xihu',
    devisionCode: 'abcde',
    'addressDetail': 'huaxinlu99hao'
  }
};
var args = [baseDeliverAddressDO, 'gongyangyutest'];
UicDeliverAddressService.invoke('insertDeliverAddr', args, function(err, data) {
  console.log(err, data);
});