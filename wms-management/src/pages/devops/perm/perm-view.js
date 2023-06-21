import React from 'react';
import ZEle from 'zero-element';
import config from './config/perm-view';
import useBreadcrumb from '@/framework/useBreadcrumb';

export default function () {

    useBreadcrumb([
      { title: '首页' },
      { title: '权限管理', path: '/perm' },
      { title: '查看详情' }
    ]);
  
    return <div>
     <ZEle namespace="perm_view" config={config} />
    </div>
  }

