import React from 'react';
import ZEle from 'zero-element';
import config from './config/perm-edit';
import useBreadcrumb from '@/framework/useBreadcrumb';

export default function () {

    useBreadcrumb([
      { title: '首页' },
      { title: '权限管理', path: '/perm' },
      { title: '编辑权限' }
    ]);
  
    return <div>
    <ZEle namespace="perm_edit" config={config} />
    </div>
  }

