import React from 'react';
import ZEle from 'zero-element';
import config from './config/configItems_edit';
import useBreadcrumb from '@/framework/useBreadcrumb';

export default function () {

    useBreadcrumb([
      { title: '首页' },
      { title: '配置项', path: '/configItems' },
      { title: '编辑配置' }
    ]);
  
    return <div>
    <ZEle namespace="configItems_edit" config={config} />
    </div>
  }

// export default () => <ZEle namespace="spirit_edit" config={config} />;
