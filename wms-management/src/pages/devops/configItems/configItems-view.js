import React from 'react';
import ZEle from 'zero-element';
import config from './config/configItems-view';
import useBreadcrumb from '@/framework/useBreadcrumb';

export default function () {

    useBreadcrumb([
      { title: '首页' },
      { title: '配置项', path: '/configItems' },
      { title: '查看详情' }
    ]);
  
    return <div>
     <ZEle namespace="configItems_view" config={config} />
    </div>
  }

// export default () => <ZEle namespace="spirit_view" config={config} />;
