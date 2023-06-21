import React from 'react';
import ZEle from 'zero-element';
import config from './config/perm-add';
import useBreadcrumb from '@/framework/useBreadcrumb';

export default function () {

    useBreadcrumb([
      { title: '首页' },
      { title: '权限管理', path: '/perm' },
      { title: '添加权限' }
    ]);
  
    return <div>
     <ZEle namespace="perm_add" config={config} />
    </div>
  }

