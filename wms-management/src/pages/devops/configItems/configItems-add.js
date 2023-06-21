import React from 'react';
import ZEle from 'zero-element';
import config from './config/configItems-add';
import useBreadcrumb from '@/framework/useBreadcrumb';

export default function () {

    useBreadcrumb([
      { title: '首页' },
      { title: '配置项', path: '/configItems' },
      { title: '添加配置' }
    ]);
  
    return <div>
     <ZEle namespace="configItems_add" config={config} />
    </div>
  }

// export default () => <ZEle namespace="spirit_add" config={config} />;
