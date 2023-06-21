import React from 'react';
import ZEle from 'zero-element';
import config from './config/permGroups-view';
import useBreadcrumb from '@/framework/useBreadcrumb';

export default function () {

    useBreadcrumb([
      { title: '首页' },
      { title: '权限分组', path: '/permGroups' },
      { title: '查看详情' }
    ]);
  
    return <div>
     <ZEle namespace="permGroups_view" config={config} />
    </div>
  }

// export default () => <ZEle namespace="spirit_view" config={config} />;
