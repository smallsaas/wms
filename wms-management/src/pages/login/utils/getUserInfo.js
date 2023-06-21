import { query } from 'zero-element-antd/lib/utils/request';
import { saveToken } from 'zero-element/lib/utils/request/token';

//  
// 
export default function getUserInfo() {

  const url = ['/api/adm/users/userInfo', '/api/u/crud/accounts/userInfo']

  return query(url[0])
    .then(data => {
      saveToken({
        userName: data.name,
        avatar: data.avatar,
        avatar: data.avatar,
        extra: data.name,
        remember: true,
      });
    })
}