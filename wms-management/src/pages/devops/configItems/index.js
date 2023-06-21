// import useBreadcrumb from '@/framework/useBreadcrumb';
import React from 'react';
import ZEle from 'zero-element';
import config from './config/index';

export default function () {

  // useBreadcrumb([
  //   { title: '首页', path: '' },
  //   { title: '配置项' },
  // ]);

  return <div>
    <ZEle
      namespace="configItems"
      config={config}
    />
  </div>
}