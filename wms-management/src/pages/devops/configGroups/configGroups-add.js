import React from 'react';
import ZEle from 'zero-element';
import config from './config/configGroups-add';
import useBreadcrumb from '@/framework/useBreadcrumb';

export default function () {

    useBreadcrumb([
      { title: '首页' },
      { title: '配置分组', path: '/configGroups' },
      { title: '添加分组' }
    ]);
  
    return <div>
     <ZEle namespace="configGroups_add" config={config} />
    </div>
  }

// export default () => <ZEle namespace="spirit_add" config={config} />;
