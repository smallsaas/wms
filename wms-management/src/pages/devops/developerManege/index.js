import React from 'react';
import ZEle from 'zero-element';
import useBreadcrumb from '@/framework/useBreadcrumb';
import config from './config/index.config';

export default function User() {

  useBreadcrumb([
    { title: '首页', path: '/' },
    { title: '运维管理', path: '/devops' },
    { title: '开发用户' },
  ]);

  return <ZEle namespace='developer-manege' config={config} />;
}
