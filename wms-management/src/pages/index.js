import React, { useEffect } from 'react';
import { history } from 'umi';
import win from 'zero-element/lib/utils/window';

import { getToken, saveToken } from 'zero-element/lib/utils/request/token';

export default function () {

  // if (win.ZEle.indexPage) {
  //   history.push(win.ZEle.indexPage);
  // }

  const token = window.location.href.split('=')[1]

  function gToken(token) {
    saveToken({ token: token })
    console.log('getToken =', getToken())
  }
  // console.log('getToken =', window.location.href)

  setTimeout(() => {
      history.push('/home');
    // if (getToken()) {
    //   history.push('/home');
    // } else {
    //   history.push('/login');
    // }
  }, 200)

  useEffect(_ => {
      // gToken(token)
  }, [])

  return (
    <div>
      首页
    </div>
  );
}
