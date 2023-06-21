import React from 'react';
import ZEle from 'zero-element';
import config from './config/configGroups_edit';
import useBreadcrumb from '@/framework/useBreadcrumb';

export default function () {

    useBreadcrumb([
      { title: '首页' },
      { title: '配置分组', path: '/configGroups' },
      { title: '编辑分组' }
    ]);
  
    return <div>
    <ZEle namespace="configItems_edit" config={config} />
    </div>
  }

// export default () => <ZEle namespace="spirit_edit" config={config} />;
