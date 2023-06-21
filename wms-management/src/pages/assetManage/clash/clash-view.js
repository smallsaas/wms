import React from 'react';
import ZEle from 'zero-element';
import useBreadcrumb from '@/framework/useBreadcrumb';
import config from './config/clash-view';

export default function () {
    useBreadcrumb([
        { title: '主页', path: '/' },
        { title: '产权申诉详情' },
      ]);
		return <ZEle namespace="clash-view" config={config} />
  }