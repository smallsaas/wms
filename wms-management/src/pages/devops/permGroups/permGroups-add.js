import React from 'react';
import ZEle from 'zero-element';
import config from './config/permGroups-add';
import useBreadcrumb from '@/framework/useBreadcrumb';

export default function () {

    useBreadcrumb([
      { title: '首页' },
      { title: '权限分组', path: '/permGroups' },
      { title: '添加分组' }
    ]);
  
    return <div>
     <ZEle namespace="permGroups_add" config={config} />
    </div>
  }

// export default () => <ZEle namespace="spirit_add" config={config} />;
