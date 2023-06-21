import React from 'react';
import ZEle from 'zero-element';
import useBreadcrumb from '@/framework/useBreadcrumb';
import config from './config/houseUserAssets-view';

export default function () {
    useBreadcrumb([
        { title: '主页', path: '/' },
        { title: '用户资产详情' },
      ]);
		return <ZEle namespace="houseUserAssets-view" config={config} />
  }